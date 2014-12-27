/*   1:    */ package cn.com.voltronic.solar.communicate;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   4:    */ import cn.com.voltronic.solar.util.CRCUtil;
/*   5:    */ import gnu.io.CommPortIdentifier;
/*   6:    */ import gnu.io.SerialPort;
/*   7:    */ import gnu.io.UnsupportedCommOperationException;
/*   8:    */ import java.io.IOException;
/*   9:    */ import java.io.InputStream;
/*  10:    */ import java.io.OutputStream;
/*  11:    */ import java.io.PrintStream;
/*  12:    */ import java.util.Enumeration;
/*  13:    */ import org.apache.log4j.Logger;
/*  14:    */ 
/*  15:    */ public class SerialHandler
/*  16:    */   implements IComUSBHandler, ICommunicateDevice
/*  17:    */ {
/*  18: 28 */   protected static final Logger logger = Logger.getLogger(SerialHandler.class);
/*  19:    */   private CommPortIdentifier portId;
/*  20: 32 */   private InputStream input = null;
/*  21: 34 */   private OutputStream output = null;
/*  22:    */   private SerialPort serialPort;
/*  23: 38 */   private int _errorcount = 0;
/*  24:    */   protected AbstractProcessor notifyProcesser;
/*  25:    */   
/*  26:    */   private void clearbuffer()
/*  27:    */   {
/*  28:    */     try
/*  29:    */     {
/*  30: 44 */       int buflen = this.input.available();
/*  31: 45 */       while (buflen > 0)
/*  32:    */       {
/*  33: 46 */         this.input.read();
/*  34: 47 */         buflen--;
/*  35:    */       }
/*  36:    */     }
/*  37:    */     catch (Exception e)
/*  38:    */     {
/*  39: 50 */       e.printStackTrace();
/*  40:    */     }
/*  41:    */   }
/*  42:    */   
/*  43:    */   public SerialHandler(CommPortIdentifier portId)
/*  44:    */     throws Exception
/*  45:    */   {
/*  46: 54 */     this.portId = portId;
/*  47: 55 */     int baudRate = 2400;
/*  48: 56 */     this.serialPort = ((SerialPort)portId.open("Arista", baudRate));
/*  49:    */     try
/*  50:    */     {
/*  51: 58 */       this.serialPort.enableReceiveTimeout(1200);
/*  52:    */     }
/*  53:    */     catch (UnsupportedCommOperationException e)
/*  54:    */     {
/*  55: 60 */       e.printStackTrace();
/*  56:    */     }
/*  57: 62 */     this.serialPort.setSerialPortParams(baudRate, 8, 
/*  58: 63 */       1, 0);
/*  59: 64 */     this.input = this.serialPort.getInputStream();
/*  60: 65 */     this.output = this.serialPort.getOutputStream();
/*  61:    */   }
/*  62:    */   
/*  63:    */   public synchronized String excuteSimpleCommand(String command)
/*  64:    */   {
/*  65: 71 */     boolean result = true;
/*  66: 72 */     String returnValue = "";
/*  67:    */     try
/*  68:    */     {
/*  69: 74 */       int time = 0;
/*  70:    */       do
/*  71:    */       {
/*  72: 76 */         clearbuffer();
/*  73: 77 */         byte[] crc = CRCUtil.getCRCByte(command);
/*  74: 78 */         byte[] bytes = command.getBytes();
/*  75: 79 */         this.output.write(bytes);
/*  76: 80 */         this.output.write(crc);
/*  77:    */         
/*  78: 82 */         this.output.write(13);
/*  79: 83 */         this.output.flush();
/*  80: 84 */         long end = System.currentTimeMillis() + 3000L;
/*  81:    */         
/*  82: 86 */         StringBuilder sb = new StringBuilder();
/*  83:    */         
/*  84: 88 */         boolean flag = false;
/*  85: 89 */         while (System.currentTimeMillis() < end)
/*  86:    */         {
/*  87:    */           int ch;
/*  88: 90 */           if ((ch = this.input.read()) >= 0) {
/*  89: 91 */             if (ch != 13)
/*  90:    */             {
/*  91: 92 */               sb.append((char)ch);
/*  92:    */             }
/*  93:    */             else
/*  94:    */             {
/*  95: 94 */               flag = true;
/*  96: 95 */               break;
/*  97:    */             }
/*  98:    */           }
/*  99:    */         }
/* 100:100 */         if (!flag) {
/* 101:101 */           result = false;
/* 102:    */         }
/* 103:103 */         returnValue = sb.toString();
/* 104:105 */         if (CRCUtil.checkCRC(returnValue)) {
/* 105:106 */           returnValue = returnValue.substring(0, returnValue.length() - 2);
/* 106:    */         } else {
/* 107:110 */           returnValue = "";
/* 108:    */         }
/* 109:112 */         time++;
/* 110: 75 */         if ((returnValue != null) && (returnValue.length() != 0) && (!returnValue.startsWith("(NAK"))) {
/* 111:    */           break;
/* 112:    */         }
/* 113: 75 */       } while (time < 3);
/* 114:    */     }
/* 115:    */     catch (Exception ex)
/* 116:    */     {
/* 117:116 */       result = false;
/* 118:    */     }
/* 119:    */     finally
/* 120:    */     {
/* 121:118 */       countErrorandNotifyProcesser(result);
/* 122:    */     }
/* 123:120 */     return returnValue;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public synchronized String excuteCommand(String command, boolean isResponse)
/* 127:    */   {
/* 128:127 */     boolean result = true;
/* 129:128 */     String returnValue = "";
/* 130:    */     label247:
/* 131:    */     try
/* 132:    */     {
/* 133:131 */       int time = 0;
/* 134:    */       do
/* 135:    */       {
/* 136:133 */         clearbuffer();
/* 137:134 */         byte[] crc = CRCUtil.getCRCByte(command);
/* 138:135 */         byte[] bytes = command.getBytes();
/* 139:136 */         this.output.write(bytes);
/* 140:137 */         this.output.write(crc);
/* 141:138 */         this.output.write(13);
/* 142:139 */         this.output.flush();
/* 143:140 */         if (isResponse)
/* 144:    */         {
/* 145:141 */           long end = System.currentTimeMillis() + 3000L;
/* 146:    */           
/* 147:143 */           StringBuilder sb = new StringBuilder();
/* 148:    */           
/* 149:145 */           boolean flag = false;
/* 150:146 */           while (System.currentTimeMillis() < end)
/* 151:    */           {
/* 152:    */             int ch;
/* 153:147 */             if ((ch = this.input.read()) >= 0) {
/* 154:149 */               if (ch != 13)
/* 155:    */               {
/* 156:150 */                 sb.append((char)ch);
/* 157:    */               }
/* 158:    */               else
/* 159:    */               {
/* 160:152 */                 flag = true;
/* 161:153 */                 break;
/* 162:    */               }
/* 163:    */             }
/* 164:    */           }
/* 165:158 */           if (!flag) {
/* 166:159 */             result = false;
/* 167:    */           }
/* 168:161 */           returnValue = sb.toString();
/* 169:    */         }
/* 170:    */         else
/* 171:    */         {
/* 172:163 */           returnValue = null;
/* 173:    */           break label247;
/* 174:    */         }
/* 175:168 */         if (CRCUtil.checkCRC(returnValue)) {
/* 176:169 */           returnValue = returnValue.substring(0, returnValue.length() - 2);
/* 177:    */         } else {
/* 178:173 */           returnValue = "";
/* 179:    */         }
/* 180:175 */         time++;
/* 181:132 */         if ((returnValue != null) && (returnValue.length() != 0) && (!returnValue.startsWith("(NAK"))) {
/* 182:    */           break;
/* 183:    */         }
/* 184:132 */       } while (time < 3);
/* 185:    */     }
/* 186:    */     catch (Exception ex)
/* 187:    */     {
/* 188:179 */       result = false;
/* 189:    */     }
/* 190:    */     finally
/* 191:    */     {
/* 192:181 */       countErrorandNotifyProcesser(result);
/* 193:    */     }
/* 194:184 */     return returnValue;
/* 195:    */   }
/* 196:    */   
/* 197:    */   private void countErrorandNotifyProcesser(boolean success)
/* 198:    */   {
/* 199:188 */     if (success) {
/* 200:189 */       this._errorcount = 0;
/* 201:    */     } else {
/* 202:191 */       this._errorcount += 1;
/* 203:    */     }
/* 204:193 */     if ((this._errorcount >= 3) && 
/* 205:194 */       (this.notifyProcesser != null))
/* 206:    */     {
/* 207:195 */       System.out.println("---------communication exception---------" + this._errorcount);
/* 208:196 */       this.notifyProcesser.close();
/* 209:    */     }
/* 210:    */   }
/* 211:    */   
/* 212:    */   public void close()
/* 213:    */   {
/* 214:203 */     if (this.input != null) {
/* 215:    */       try
/* 216:    */       {
/* 217:205 */         this.input.close();
/* 218:    */       }
/* 219:    */       catch (IOException localIOException) {}
/* 220:    */     }
/* 221:209 */     if (this.output != null) {
/* 222:    */       try
/* 223:    */       {
/* 224:211 */         this.output.close();
/* 225:    */       }
/* 226:    */       catch (IOException localIOException1) {}
/* 227:    */     }
/* 228:215 */     if (this.serialPort != null) {
/* 229:    */       try
/* 230:    */       {
/* 231:217 */         this.serialPort.close();
/* 232:    */       }
/* 233:    */       catch (Exception localException) {}
/* 234:    */     }
/* 235:    */   }
/* 236:    */   
/* 237:    */   public String getDeviceName()
/* 238:    */   {
/* 239:225 */     String portName = this.portId.getName();
/* 240:226 */     int index = portName.lastIndexOf("/");
/* 241:227 */     if (index > 0) {
/* 242:228 */       portName = portName.substring(index + 1, portName.length());
/* 243:    */     }
/* 244:230 */     return portName;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public void setNotifyProcess(AbstractProcessor process)
/* 248:    */   {
/* 249:235 */     this.notifyProcesser = process;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public int getMpptTrackNumber()
/* 253:    */   {
/* 254:240 */     int mpptTrackNumber = 2;
/* 255:    */     try
/* 256:    */     {
/* 257:242 */       String result = excuteCommand("QPIRI", true);
/* 258:243 */       if ((result != null) && (!"".equals(result)) && 
/* 259:244 */         (!result.equals("QPIRI")))
/* 260:    */       {
/* 261:245 */         String[] arr = result.split(" ");
/* 262:246 */         mpptTrackNumber = Integer.parseInt(arr[7]);
/* 263:    */       }
/* 264:    */     }
/* 265:    */     catch (Exception ex)
/* 266:    */     {
/* 267:249 */       ex.printStackTrace();
/* 268:    */     }
/* 269:251 */     return mpptTrackNumber;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public String getSerialNo()
/* 273:    */   {
/* 274:256 */     String serialno = "";
/* 275:257 */     String serialnoStr = "";
/* 276:    */     try
/* 277:    */     {
/* 278:259 */       int i = 0;
/* 279:260 */       while (i < 3)
/* 280:    */       {
/* 281:261 */         serialnoStr = excuteCommand("QID", true);
/* 282:262 */         if ((serialnoStr != null) && (!"".equals(serialnoStr)) && 
/* 283:263 */           (!serialnoStr.equalsIgnoreCase("(NAK")) && 
/* 284:264 */           (!serialnoStr.equalsIgnoreCase("(ACK")) && 
/* 285:265 */           (!serialnoStr.equals("QID")))
/* 286:    */         {
/* 287:266 */           serialno = serialnoStr.substring(1);
/* 288:267 */           break;
/* 289:    */         }
/* 290:269 */         i++;
/* 291:    */       }
/* 292:    */     }
/* 293:    */     catch (Exception e)
/* 294:    */     {
/* 295:272 */       e.printStackTrace();
/* 296:    */     }
/* 297:274 */     return serialno;
/* 298:    */   }
/* 299:    */   
/* 300:    */   public String getModeType()
/* 301:    */   {
/* 302:279 */     String machineTypeStr = "";
/* 303:    */     try
/* 304:    */     {
/* 305:281 */       String qpiriStr = excuteCommand("QPIRI", true);
/* 306:282 */       if ((!"".equals(qpiriStr)) && (!qpiriStr.equals("(NAK")) && 
/* 307:283 */         (!qpiriStr.equals("QPIRI")))
/* 308:    */       {
/* 309:284 */         String[] ratingInfo = qpiriStr.split(" ");
/* 310:285 */         return ratingInfo[8];
/* 311:    */       }
/* 312:    */     }
/* 313:    */     catch (Exception ex)
/* 314:    */     {
/* 315:289 */       ex.printStackTrace();
/* 316:    */     }
/* 317:291 */     return machineTypeStr;
/* 318:    */   }
/* 319:    */   
/* 320:    */   public String getDeviceModel()
/* 321:    */   {
/* 322:295 */     String _deviceModel = "";
/* 323:    */     try
/* 324:    */     {
/* 325:297 */       String qdmStr = excuteCommand("QDM", true);
/* 326:298 */       if ((!"".equals(qdmStr)) && (!qdmStr.equals("(NAK")) && 
/* 327:299 */         (!qdmStr.equals("QDM")))
/* 328:    */       {
/* 329:300 */         qdmStr = qdmStr.substring(1);
/* 330:301 */         _deviceModel = qdmStr;
/* 331:    */       }
/* 332:    */     }
/* 333:    */     catch (Exception ex)
/* 334:    */     {
/* 335:304 */       ex.printStackTrace();
/* 336:    */     }
/* 337:306 */     return _deviceModel;
/* 338:    */   }
/* 339:    */   
/* 340:    */   public boolean isSupportQCTH()
/* 341:    */   {
/* 342:311 */     String qchtStr = excuteCommand("QCHT", true);
/* 343:312 */     if ((qchtStr != null) && (!"".equals(qchtStr)) && (!qchtStr.equals("(NAK"))) {
/* 344:313 */       return true;
/* 345:    */     }
/* 346:315 */     return false;
/* 347:    */   }
/* 348:    */   
/* 349:    */   public boolean isSupportQPPS()
/* 350:    */   {
/* 351:319 */     String ppsStr = excuteCommand("QPPS", true);
/* 352:320 */     if ((ppsStr != null) && (!"".equals(ppsStr)) && (!ppsStr.equals("(NAK"))) {
/* 353:321 */       return true;
/* 354:    */     }
/* 355:323 */     return false;
/* 356:    */   }
/* 357:    */   
/* 358:    */   public static void main(String[] args)
/* 359:    */   {
/* 360:327 */     Enumeration en = CommPortIdentifier.getPortIdentifiers();
/* 361:328 */     while (en.hasMoreElements())
/* 362:    */     {
/* 363:329 */       CommPortIdentifier portId = (CommPortIdentifier)en.nextElement();
/* 364:    */       
/* 365:    */ 
/* 366:332 */       System.out.println(portId.getName());
/* 367:    */       try
/* 368:    */       {
/* 369:334 */         SerialHandler comm = new SerialHandler(portId);
/* 370:    */         
/* 371:    */ 
/* 372:    */ 
/* 373:    */ 
/* 374:    */ 
/* 375:    */ 
/* 376:    */ 
/* 377:342 */         String id = comm.excuteCommand("QCHGS", true);
/* 378:343 */         System.out.println("QCHGS=" + id);
/* 379:    */         
/* 380:    */ 
/* 381:    */ 
/* 382:    */ 
/* 383:348 */         Thread.sleep(2000L);
/* 384:349 */         System.exit(0);
/* 385:    */       }
/* 386:    */       catch (Exception e)
/* 387:    */       {
/* 388:352 */         e.printStackTrace();
/* 389:    */       }
/* 390:    */     }
/* 391:    */   }
/* 392:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.SerialHandler
 * JD-Core Version:    0.7.0.1
 */