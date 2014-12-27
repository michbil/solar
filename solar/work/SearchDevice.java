/*   1:    */ package cn.com.voltronic.solar.work;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.communicate.IComUSBHandler;
/*   4:    */ import cn.com.voltronic.solar.communicate.ModbusHandler;
/*   5:    */ import cn.com.voltronic.solar.communicate.ModbusHandlerMaster;
/*   6:    */ import cn.com.voltronic.solar.communicate.SNMPHandler;
/*   7:    */ import cn.com.voltronic.solar.communicate.SerialHandler;
/*   8:    */ import cn.com.voltronic.solar.communicate.SerialHandlerSolaris;
/*   9:    */ import cn.com.voltronic.solar.communicate.USBHandler;
/*  10:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*  11:    */ import cn.com.voltronic.solar.configure.SystemEnv;
/*  12:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  13:    */ import cn.com.voltronic.solar.socket.UdpClient;
/*  14:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  15:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  16:    */ import cn.com.voltronic.solar.system.ProcessorCategories;
/*  17:    */ import gnu.io.CommPortIdentifier;
/*  18:    */ import java.io.PrintStream;
/*  19:    */ import java.util.ArrayList;
/*  20:    */ import java.util.Enumeration;
/*  21:    */ import java.util.List;
/*  22:    */ import org.apache.commons.lang.SystemUtils;
/*  23:    */ import org.apache.log4j.Logger;
/*  24:    */ import usb.IUSBComm;
/*  25:    */ import usb.MacUSBRemote;
/*  26:    */ import usb.USBCommEx;
/*  27:    */ 
/*  28:    */ public class SearchDevice
/*  29:    */   extends Thread
/*  30:    */ {
/*  31: 41 */   protected static final Logger logger = Logger.getLogger(SearchDevice.class);
/*  32:    */   private IUSBComm usbComm;
/*  33:    */   private int currentLinkCount;
/*  34:    */   private int oldLinkCount;
/*  35:    */   private static final int MAX_WAIT_TIME = 4000;
/*  36:    */   private static final int MID_WAIT_TIME = 1000;
/*  37:    */   private static final int MIN_WAIT_TIME = 500;
/*  38: 51 */   private ArrayList<AbstractProcessor> templist = new ArrayList();
/*  39:    */   
/*  40:    */   public SearchDevice()
/*  41:    */   {
/*  42: 54 */     if (SystemUtils.IS_OS_MAC_OSX)
/*  43:    */     {
/*  44: 55 */       MacUSBRemote macusbdriver = new MacUSBRemote();
/*  45: 56 */       this.usbComm = macusbdriver;
/*  46:    */       try
/*  47:    */       {
/*  48: 58 */         Thread.sleep(1000L);
/*  49:    */       }
/*  50:    */       catch (Exception localException) {}
/*  51:    */     }
/*  52: 61 */     else if ((SystemUtils.IS_OS_WINDOWS) || (SystemUtils.IS_OS_LINUX) || 
/*  53: 62 */       (SystemUtils.IS_OS_SOLARIS))
/*  54:    */     {
/*  55: 63 */       USBCommEx usbdriver = new USBCommEx();
/*  56: 64 */       this.usbComm = usbdriver;
/*  57:    */     }
/*  58:    */     else
/*  59:    */     {
/*  60: 66 */       USBCommEx usbdriver = new USBCommEx();
/*  61: 67 */       this.usbComm = usbdriver;
/*  62:    */     }
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void run()
/*  66:    */   {
/*  67: 73 */     int time_i = 0;
/*  68: 74 */     while (!SystemEnv.stopping) {
/*  69: 75 */       if (time_i >= GlobalVariables.globalConfig.getDeviceSacanInterval())
/*  70:    */       {
/*  71: 76 */         searchLocalComs();
/*  72: 77 */         if (GlobalVariables.globalConfig.isUseUSB()) {
/*  73: 78 */           searchLocalUsb();
/*  74:    */         }
/*  75: 80 */         if (GlobalVariables.globalConfig.isUseSNMP()) {
/*  76: 81 */           searchSNMP();
/*  77:    */         }
/*  78: 83 */         time_i = 0;
/*  79: 84 */         sendToTray();
/*  80:    */       }
/*  81:    */       else
/*  82:    */       {
/*  83:    */         try
/*  84:    */         {
/*  85: 87 */           Thread.sleep(1000L);
/*  86:    */         }
/*  87:    */         catch (InterruptedException e)
/*  88:    */         {
/*  89: 89 */           e.printStackTrace();
/*  90:    */         }
/*  91: 91 */         time_i++;
/*  92:    */       }
/*  93:    */     }
/*  94: 97 */     SystemEnv.stoped += 1;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void sendToTray()
/*  98:    */   {
/*  99:101 */     int status = getLinkCount();
/* 100:102 */     UdpClient udpClient = null;
/* 101:103 */     if (status == 1) {
/* 102:    */       try
/* 103:    */       {
/* 104:105 */         String port = "38694";
/* 105:    */         try
/* 106:    */         {
/* 107:107 */           port = GlobalVariables.globalConfig.getUdpPort();
/* 108:    */         }
/* 109:    */         catch (Exception localException1) {}
/* 110:110 */         if ((port != null) && (!"".equals(port)))
/* 111:    */         {
/* 112:111 */           udpClient = new UdpClient("localhost", port);
/* 113:112 */           udpClient.send("(PVCount:" + this.currentLinkCount);
/* 114:    */         }
/* 115:    */       }
/* 116:    */       catch (Exception e)
/* 117:    */       {
/* 118:115 */         e.printStackTrace();
/* 119:    */       }
/* 120:    */       finally
/* 121:    */       {
/* 122:117 */         if (udpClient != null) {
/* 123:118 */           udpClient.disconnect();
/* 124:    */         }
/* 125:    */       }
/* 126:    */     }
/* 127:    */   }
/* 128:    */   
/* 129:    */   public int getLinkCount()
/* 130:    */   {
/* 131:125 */     this.currentLinkCount = GlobalProcessors.getProcesserSize();
/* 132:126 */     if (this.oldLinkCount != this.currentLinkCount)
/* 133:    */     {
/* 134:127 */       this.oldLinkCount = this.currentLinkCount;
/* 135:128 */       return 1;
/* 136:    */     }
/* 137:130 */     return -1;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public void searchLocalComs()
/* 141:    */   {
/* 142:136 */     Enumeration portEn = CommPortIdentifier.getPortIdentifiers();
/* 143:137 */     while (portEn.hasMoreElements())
/* 144:    */     {
/* 145:138 */       CommPortIdentifier portId = 
/* 146:139 */         (CommPortIdentifier)portEn.nextElement();
/* 147:140 */       if (portId.getPortType() == 1)
/* 148:    */       {
/* 149:141 */         Object handler = GlobalProcessors.getUsedHandlerbyName(portId
/* 150:142 */           .getName());
/* 151:143 */         if ((GlobalVariables.globalConfig.isUseCOM()) && 
/* 152:144 */           (!GlobalProcessors.getSMSCom().equalsIgnoreCase(portId.getName().trim())) && 
/* 153:145 */           (!GlobalProcessors.getExcludeComs().contains(portId.getName())) && 
/* 154:146 */           (handler == null)) {
/* 155:    */           try
/* 156:    */           {
/* 157:148 */             searchSerialPort(portId);
/* 158:149 */             handler = 
/* 159:150 */               GlobalProcessors.getUsedHandlerbyName(portId.getName());
/* 160:    */           }
/* 161:    */           catch (Exception e)
/* 162:    */           {
/* 163:153 */             e.printStackTrace();
/* 164:    */           }
/* 165:    */         }
/* 166:158 */         if ((GlobalVariables.globalConfig.isUseModbus()) && 
/* 167:159 */           (ModbusHandlerMaster.isUseCom(portId))) {
/* 168:160 */           if (handler == null) {
/* 169:161 */             searchModbus(portId);
/* 170:162 */           } else if ((handler instanceof ModbusHandler)) {
/* 171:164 */             searchModbus(((ModbusHandler)handler)
/* 172:165 */               .getMaster());
/* 173:    */           }
/* 174:    */         }
/* 175:    */       }
/* 176:    */     }
/* 177:    */   }
/* 178:    */   
/* 179:    */   private void searchSerialPort(CommPortIdentifier portId)
/* 180:    */     throws Exception
/* 181:    */   {
/* 182:173 */     IComUSBHandler handler = null;
/* 183:174 */     if ((SystemUtils.IS_OS_SOLARIS) || (SystemUtils.IS_OS_SUN_OS)) {
/* 184:175 */       handler = new SerialHandlerSolaris(portId);
/* 185:    */     } else {
/* 186:177 */       handler = new SerialHandler(portId);
/* 187:    */     }
/* 188:179 */     AbstractProcessor processor = 
/* 189:180 */       ProcessorCategories.getNewMonitor(handler);
/* 190:181 */     if (processor != null)
/* 191:    */     {
/* 192:182 */       processor.setDeviceName(handler.getDeviceName());
/* 193:183 */       doProcessor(processor);
/* 194:    */     }
/* 195:    */     else
/* 196:    */     {
/* 197:185 */       handler.close();
/* 198:    */     }
/* 199:    */     try
/* 200:    */     {
/* 201:188 */       for (AbstractProcessor process : this.templist)
/* 202:    */       {
/* 203:189 */         System.out.println(process.getDeviceName());
/* 204:    */         try
/* 205:    */         {
/* 206:191 */           process.startMonitor();
/* 207:    */         }
/* 208:    */         catch (Exception e)
/* 209:    */         {
/* 210:193 */           e.printStackTrace();
/* 211:    */         }
/* 212:    */       }
/* 213:196 */       this.templist.clear();
/* 214:197 */       sleep(2000L);
/* 215:    */     }
/* 216:    */     catch (InterruptedException e)
/* 217:    */     {
/* 218:199 */       e.printStackTrace();
/* 219:    */     }
/* 220:    */   }
/* 221:    */   
/* 222:    */   public void searchLocalUsb()
/* 223:    */   {
/* 224:205 */     String usbDevices = this.usbComm.findUSBDevices();
/* 225:206 */     String[] devices = usbDevices.split("#");
/* 226:207 */     System.out.println("usbDevices=" + usbDevices);
/* 227:208 */     if ((devices != null) && (devices.length > 0)) {
/* 228:209 */       for (String usbId : devices)
/* 229:    */       {
/* 230:210 */         Object existhandler = 
/* 231:211 */           GlobalProcessors.getUsedHandlerbyName("USB" + usbId);
/* 232:212 */         if (existhandler == null)
/* 233:    */         {
/* 234:213 */           USBHandler handler = new USBHandler(this.usbComm, usbId);
/* 235:214 */           AbstractProcessor processor = 
/* 236:215 */             ProcessorCategories.getNewMonitor(handler);
/* 237:216 */           if (processor != null)
/* 238:    */           {
/* 239:217 */             processor.setDeviceName(handler.getDeviceName());
/* 240:218 */             doProcessor(processor);
/* 241:    */           }
/* 242:    */           else
/* 243:    */           {
/* 244:220 */             handler.close();
/* 245:    */           }
/* 246:    */           try
/* 247:    */           {
/* 248:223 */             for (AbstractProcessor process : this.templist)
/* 249:    */             {
/* 250:224 */               System.out.println(process.getDeviceName());
/* 251:    */               try
/* 252:    */               {
/* 253:226 */                 process.startMonitor();
/* 254:    */               }
/* 255:    */               catch (Exception e)
/* 256:    */               {
/* 257:228 */                 e.printStackTrace();
/* 258:    */               }
/* 259:    */             }
/* 260:231 */             this.templist.clear();
/* 261:232 */             sleep(2000L);
/* 262:    */           }
/* 263:    */           catch (InterruptedException e)
/* 264:    */           {
/* 265:234 */             e.printStackTrace();
/* 266:    */           }
/* 267:    */         }
/* 268:    */       }
/* 269:    */     }
/* 270:    */   }
/* 271:    */   
/* 272:    */   private void searchModbus(CommPortIdentifier portId)
/* 273:    */   {
/* 274:    */     try
/* 275:    */     {
/* 276:243 */       System.out.println(portId.getName());
/* 277:244 */       ModbusHandlerMaster handlerMaster = new ModbusHandlerMaster(portId);
/* 278:245 */       searchModbus(handlerMaster);
/* 279:    */     }
/* 280:    */     catch (Exception e)
/* 281:    */     {
/* 282:247 */       System.err.println("----AA----" + e.getMessage());
/* 283:    */     }
/* 284:    */   }
/* 285:    */   
/* 286:    */   private void searchModbus(ModbusHandlerMaster handlerMaster)
/* 287:    */   {
/* 288:252 */     handlerMaster.generateHandlers();
/* 289:253 */     List<ModbusHandler> handlers = handlerMaster.getHandlers();
/* 290:254 */     List<ModbusHandler> closeHandlers = new ArrayList();
/* 291:255 */     this.templist.clear();
/* 292:    */     
/* 293:    */ 
/* 294:258 */     int oldActivehandlersize = handlerMaster.getActiveHandlesSize();
/* 295:259 */     int previoushandlersize = oldActivehandlersize;
/* 296:260 */     int allhandlesize = handlers.size();
/* 297:    */     
/* 298:262 */     int scanNumber = allhandlesize;
/* 299:263 */     int sleeptime = 0;
/* 300:264 */     int scanCount = 0;
/* 301:265 */     setPriority(10);
/* 302:    */     ModbusHandler handler;
/* 303:266 */     for (int item = 0; item < allhandlesize; item++)
/* 304:    */     {
/* 305:267 */       if (SystemEnv.stopping) {
/* 306:    */         break;
/* 307:    */       }
/* 308:274 */       handler = (ModbusHandler)handlers.get(item);
/* 309:275 */       if ((handler != null) && (!handler.isActive())) {
/* 310:276 */         if (handler.isMustClose())
/* 311:    */         {
/* 312:277 */           closeHandlers.add(handler);
/* 313:    */         }
/* 314:    */         else
/* 315:    */         {
/* 316:279 */           if (oldActivehandlersize == 0)
/* 317:    */           {
/* 318:280 */             scanNumber = allhandlesize;
/* 319:281 */             sleeptime = 10;
/* 320:    */           }
/* 321:282 */           else if (handlerMaster.maybeLost())
/* 322:    */           {
/* 323:283 */             scanNumber = 1;
/* 324:284 */             sleeptime = 4000;
/* 325:    */           }
/* 326:285 */           else if (handlerMaster.getActiveHandlesSize() > previoushandlersize)
/* 327:    */           {
/* 328:286 */             previoushandlersize = 
/* 329:287 */               handlerMaster.getActiveHandlesSize();
/* 330:288 */             scanNumber = (allhandlesize - previoushandlersize) / 2;
/* 331:289 */             if (scanNumber < 40) {
/* 332:290 */               scanNumber = 40;
/* 333:    */             }
/* 334:292 */             sleeptime = 500;
/* 335:    */           }
/* 336:    */           else
/* 337:    */           {
/* 338:293 */             if ((oldActivehandlersize > 0) && 
/* 339:294 */               (handlerMaster.getActiveHandlesSize() == 0))
/* 340:    */             {
/* 341:295 */               closeHandlers.add(handler);
/* 342:296 */               continue;
/* 343:    */             }
/* 344:297 */             if (allhandlesize > oldActivehandlersize * 4)
/* 345:    */             {
/* 346:298 */               if (oldActivehandlersize <= 20)
/* 347:    */               {
/* 348:299 */                 scanNumber = 20;
/* 349:300 */                 sleeptime = 1000;
/* 350:    */               }
/* 351:    */               else
/* 352:    */               {
/* 353:302 */                 scanNumber = oldActivehandlersize;
/* 354:303 */                 sleeptime = 1000;
/* 355:    */               }
/* 356:    */             }
/* 357:    */             else
/* 358:    */             {
/* 359:306 */               scanNumber = 10;
/* 360:307 */               sleeptime = 500;
/* 361:    */             }
/* 362:    */           }
/* 363:310 */           AbstractProcessor processor = 
/* 364:311 */             ProcessorCategories.getNewMonitor(handler);
/* 365:312 */           if ((processor != null) && (!processor.isClosing()) && 
/* 366:313 */             (handler != null))
/* 367:    */           {
/* 368:314 */             processor.setDeviceName(handler.getDeviceName());
/* 369:315 */             handler.setActive(true);
/* 370:316 */             doProcessor(processor);
/* 371:    */           }
/* 372:317 */           else if (handler != null)
/* 373:    */           {
/* 374:318 */             closeHandlers.add(handler);
/* 375:    */           }
/* 376:320 */           scanCount++;
/* 377:321 */           if (scanCount > scanNumber)
/* 378:    */           {
/* 379:    */             try
/* 380:    */             {
/* 381:323 */               for (AbstractProcessor process : this.templist)
/* 382:    */               {
/* 383:324 */                 System.out.println(process.getDeviceName());
/* 384:    */                 try
/* 385:    */                 {
/* 386:326 */                   process.startMonitor();
/* 387:    */                 }
/* 388:    */                 catch (Exception e)
/* 389:    */                 {
/* 390:329 */                   e.printStackTrace();
/* 391:    */                 }
/* 392:    */               }
/* 393:332 */               this.templist.clear();
/* 394:333 */               sleep(sleeptime);
/* 395:    */             }
/* 396:    */             catch (InterruptedException e)
/* 397:    */             {
/* 398:335 */               e.printStackTrace();
/* 399:    */             }
/* 400:337 */             scanCount = 0;
/* 401:338 */             oldActivehandlersize = handlerMaster
/* 402:339 */               .getActiveHandlesSize();
/* 403:340 */             previoushandlersize = oldActivehandlersize;
/* 404:    */           }
/* 405:    */         }
/* 406:    */       }
/* 407:    */     }
/* 408:354 */     for (ModbusHandler item : closeHandlers) {
/* 409:355 */       if (item != null) {
/* 410:356 */         item.close();
/* 411:    */       }
/* 412:    */     }
/* 413:360 */     for (AbstractProcessor process : this.templist)
/* 414:    */     {
/* 415:361 */       System.out.println(process.getDeviceName());
/* 416:    */       try
/* 417:    */       {
/* 418:363 */         process.startMonitor();
/* 419:    */       }
/* 420:    */       catch (Exception e)
/* 421:    */       {
/* 422:366 */         e.printStackTrace();
/* 423:    */       }
/* 424:    */     }
/* 425:370 */     setPriority(5);
/* 426:    */   }
/* 427:    */   
/* 428:    */   private void searchSNMP()
/* 429:    */   {
/* 430:374 */     SNMPHandler handler = new SNMPHandler();
/* 431:375 */     AbstractProcessor processor = 
/* 432:376 */       ProcessorCategories.getNewMonitor(handler);
/* 433:377 */     if (processor != null)
/* 434:    */     {
/* 435:378 */       processor.setDeviceName(handler.getDeviceName());
/* 436:379 */       doProcessor(processor);
/* 437:    */     }
/* 438:    */     else
/* 439:    */     {
/* 440:381 */       handler.close();
/* 441:    */     }
/* 442:    */   }
/* 443:    */   
/* 444:    */   private void doProcessor(AbstractProcessor processer)
/* 445:    */   {
/* 446:386 */     if (GlobalProcessors.findProcessor(processer.processorKey()) == null)
/* 447:    */     {
/* 448:387 */       GlobalProcessors.addProcessor(processer.processorKey(), processer);
/* 449:388 */       this.templist.add(processer);
/* 450:    */     }
/* 451:    */   }
/* 452:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.work.SearchDevice
 * JD-Core Version:    0.7.0.1
 */