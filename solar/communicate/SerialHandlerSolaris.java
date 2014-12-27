/*   1:    */ package cn.com.voltronic.solar.communicate;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   4:    */ import cn.com.voltronic.solar.util.CRCUtil;
/*   5:    */ import gnu.io.CommPortIdentifier;
/*   6:    */ import gnu.io.SerialPort;
/*   7:    */ import gnu.io.SerialPortEvent;
/*   8:    */ import gnu.io.SerialPortEventListener;
/*   9:    */ import gnu.io.UnsupportedCommOperationException;
/*  10:    */ import java.io.IOException;
/*  11:    */ import java.io.InputStream;
/*  12:    */ import java.io.OutputStream;
/*  13:    */ import java.io.PrintStream;
/*  14:    */ import org.apache.log4j.Logger;
/*  15:    */ 
/*  16:    */ public class SerialHandlerSolaris
/*  17:    */   implements IComUSBHandler, ICommunicateDevice, SerialPortEventListener
/*  18:    */ {
/*  19: 34 */   private static final Logger logger = Logger.getLogger(SerialHandlerSolaris.class);
/*  20:    */   private CommPortIdentifier portId;
/*  21: 37 */   private InputStream input = null;
/*  22: 38 */   private OutputStream output = null;
/*  23:    */   private SerialPort serialPort;
/*  24:    */   private String dataBuff;
/*  25: 41 */   private int _errorcount = 0;
/*  26:    */   protected AbstractProcessor notifyProcesser;
/*  27:    */   
/*  28:    */   public SerialHandlerSolaris(CommPortIdentifier portId)
/*  29:    */     throws Exception
/*  30:    */   {
/*  31: 47 */     this.portId = portId;
/*  32: 48 */     int baudRate = 2400;
/*  33: 49 */     this.serialPort = ((SerialPort)portId.open("Arista", baudRate));
/*  34:    */     try
/*  35:    */     {
/*  36: 51 */       this.serialPort.enableReceiveTimeout(1200);
/*  37:    */     }
/*  38:    */     catch (UnsupportedCommOperationException e)
/*  39:    */     {
/*  40: 53 */       e.printStackTrace();
/*  41:    */     }
/*  42: 55 */     this.serialPort.setSerialPortParams(baudRate, 8, 
/*  43: 56 */       1, 0);
/*  44: 57 */     this.input = this.serialPort.getInputStream();
/*  45: 58 */     this.output = this.serialPort.getOutputStream();
/*  46:    */     
/*  47: 60 */     this.serialPort.addEventListener(this);
/*  48:    */     
/*  49: 62 */     this.serialPort.notifyOnDataAvailable(true);
/*  50: 63 */     this.serialPort.notifyOnCTS(true);
/*  51: 64 */     this.serialPort.notifyOnDSR(true);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public synchronized String excuteSimpleCommand(String command)
/*  55:    */   {
/*  56: 70 */     this.dataBuff = "";
/*  57: 71 */     boolean result = true;
/*  58:    */     try
/*  59:    */     {
/*  60: 74 */       int time = 0;
/*  61:    */       do
/*  62:    */       {
/*  63: 76 */         byte[] crc = CRCUtil.getCRCByte(command);
/*  64: 77 */         byte[] bytes = command.getBytes();
/*  65: 78 */         this.output.write(bytes);
/*  66: 79 */         this.output.write(crc);
/*  67: 80 */         this.output.write(13);
/*  68: 81 */         this.output.flush();
/*  69: 82 */         long end = System.currentTimeMillis() + 2000L;
/*  70: 83 */         while (System.currentTimeMillis() < end) {
/*  71: 84 */           if (this.dataBuff.length() > 0) {
/*  72:    */             break;
/*  73:    */           }
/*  74:    */         }
/*  75: 89 */         if (CRCUtil.checkCRC(this.dataBuff)) {
/*  76: 90 */           this.dataBuff = this.dataBuff.substring(0, this.dataBuff.length() - 2);
/*  77:    */         } else {
/*  78: 94 */           this.dataBuff = "";
/*  79:    */         }
/*  80: 96 */         time++;
/*  81: 75 */         if ((this.dataBuff != null) && (this.dataBuff.length() != 0) && (!this.dataBuff.startsWith("(NAK"))) {
/*  82:    */           break;
/*  83:    */         }
/*  84: 75 */       } while (time < 3);
/*  85:    */     }
/*  86:    */     catch (Exception ex)
/*  87:    */     {
/*  88: 99 */       result = false;
/*  89:    */     }
/*  90:    */     finally
/*  91:    */     {
/*  92:101 */       countErrorandNotifyProcesser(result);
/*  93:    */     }
/*  94:103 */     return this.dataBuff;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public synchronized String excuteCommand(String command, boolean isResponse)
/*  98:    */   {
/*  99:108 */     this.dataBuff = "";
/* 100:109 */     boolean result = true;
/* 101:    */     try
/* 102:    */     {
/* 103:112 */       int time = 0;
/* 104:    */       do
/* 105:    */       {
/* 106:114 */         byte[] crc = CRCUtil.getCRCByte(command);
/* 107:115 */         byte[] bytes = command.getBytes();
/* 108:116 */         this.output.write(bytes);
/* 109:117 */         this.output.write(crc);
/* 110:118 */         this.output.write(13);
/* 111:119 */         this.output.flush();
/* 112:120 */         logger.debug("发送命令：" + command);
/* 113:121 */         if (isResponse)
/* 114:    */         {
/* 115:122 */           long end = System.currentTimeMillis() + 2000L;
/* 116:123 */           while (System.currentTimeMillis() < end) {
/* 117:124 */             if (this.dataBuff.length() > 0) {
/* 118:    */               break;
/* 119:    */             }
/* 120:    */           }
/* 121:129 */           if (CRCUtil.checkCRC(this.dataBuff)) {
/* 122:130 */             this.dataBuff = this.dataBuff.substring(0, this.dataBuff.length() - 2);
/* 123:    */           } else {
/* 124:134 */             this.dataBuff = "";
/* 125:    */           }
/* 126:    */         }
/* 127:    */         else
/* 128:    */         {
/* 129:138 */           return null;
/* 130:    */         }
/* 131:140 */         time++;
/* 132:113 */         if ((this.dataBuff != null) && (this.dataBuff.length() != 0) && (!this.dataBuff.startsWith("(NAK"))) {
/* 133:    */           break;
/* 134:    */         }
/* 135:113 */       } while (time < 3);
/* 136:    */     }
/* 137:    */     catch (Exception ex)
/* 138:    */     {
/* 139:144 */       result = false;
/* 140:    */     }
/* 141:    */     finally
/* 142:    */     {
/* 143:146 */       countErrorandNotifyProcesser(result);
/* 144:    */     }
/* 145:148 */     return this.dataBuff;
/* 146:    */   }
/* 147:    */   
/* 148:    */   private void countErrorandNotifyProcesser(boolean success)
/* 149:    */   {
/* 150:152 */     if (success) {
/* 151:153 */       this._errorcount = 0;
/* 152:    */     } else {
/* 153:155 */       this._errorcount += 1;
/* 154:    */     }
/* 155:157 */     if ((this._errorcount >= 3) && 
/* 156:158 */       (this.notifyProcesser != null))
/* 157:    */     {
/* 158:159 */       System.out.println("---------communication exception---------" + this._errorcount);
/* 159:160 */       this.notifyProcesser.close();
/* 160:    */     }
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void close()
/* 164:    */   {
/* 165:166 */     if (this.input != null) {
/* 166:    */       try
/* 167:    */       {
/* 168:168 */         this.input.close();
/* 169:    */       }
/* 170:    */       catch (IOException localIOException) {}
/* 171:    */     }
/* 172:172 */     if (this.output != null) {
/* 173:    */       try
/* 174:    */       {
/* 175:174 */         this.output.close();
/* 176:    */       }
/* 177:    */       catch (IOException localIOException1) {}
/* 178:    */     }
/* 179:178 */     if (this.serialPort != null) {
/* 180:    */       try
/* 181:    */       {
/* 182:180 */         this.serialPort.close();
/* 183:    */       }
/* 184:    */       catch (Exception localException) {}
/* 185:    */     }
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void serialEvent(SerialPortEvent evt)
/* 189:    */   {
/* 190:187 */     switch (evt.getEventType())
/* 191:    */     {
/* 192:    */     case 3: 
/* 193:190 */       System.out.println("CTS event occured.");
/* 194:191 */       break;
/* 195:    */     case 6: 
/* 196:194 */       System.out.println("CD event occured.");
/* 197:195 */       break;
/* 198:    */     case 10: 
/* 199:198 */       System.out.println("BI event occured.");
/* 200:199 */       break;
/* 201:    */     case 4: 
/* 202:202 */       System.out.println("DSR event occured.");
/* 203:203 */       break;
/* 204:    */     case 9: 
/* 205:206 */       System.out.println("FE event occured.");
/* 206:207 */       break;
/* 207:    */     case 7: 
/* 208:210 */       System.out.println("OE event occured.");
/* 209:211 */       break;
/* 210:    */     case 8: 
/* 211:214 */       System.out.println("PE event occured.");
/* 212:215 */       break;
/* 213:    */     case 5: 
/* 214:218 */       System.out.println("RI event occured.");
/* 215:219 */       break;
/* 216:    */     case 2: 
/* 217:222 */       System.out.println("OUTPUT_BUFFER_EMPTY event occured.");
/* 218:223 */       break;
/* 219:    */     case 1: 
/* 220:228 */       StringBuilder sb = new StringBuilder();
/* 221:    */       try
/* 222:    */       {
/* 223:    */         int ch;
/* 224:230 */         while ((ch = this.input.read()) >= 0)
/* 225:    */         {
/* 226:    */           int ch;
/* 227:231 */           if (ch == 13) {
/* 228:    */             break;
/* 229:    */           }
/* 230:234 */           sb.append((char)ch);
/* 231:    */         }
/* 232:236 */         this.dataBuff = sb.toString();
/* 233:    */       }
/* 234:    */       catch (Exception e)
/* 235:    */       {
/* 236:238 */         this.dataBuff = "";
/* 237:239 */         e.printStackTrace();
/* 238:    */       }
/* 239:    */     }
/* 240:    */   }
/* 241:    */   
/* 242:    */   public String getDeviceName()
/* 243:    */   {
/* 244:247 */     String portName = this.portId.getName();
/* 245:248 */     int index = portName.lastIndexOf("/");
/* 246:249 */     if (index > 0) {
/* 247:250 */       portName = portName.substring(index + 1, portName.length());
/* 248:    */     }
/* 249:252 */     return portName;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public void setNotifyProcess(AbstractProcessor process)
/* 253:    */   {
/* 254:257 */     this.notifyProcesser = process;
/* 255:    */   }
/* 256:    */   
/* 257:    */   public int getMpptTrackNumber()
/* 258:    */   {
/* 259:263 */     int mpptTrackNumber = 2;
/* 260:    */     try
/* 261:    */     {
/* 262:265 */       String result = excuteCommand("QPIRI", true);
/* 263:266 */       if ((result != null) && (!"".equals(result)) && 
/* 264:267 */         (!result.equals("QPIRI")))
/* 265:    */       {
/* 266:268 */         String[] arr = result.split(" ");
/* 267:269 */         mpptTrackNumber = Integer.parseInt(arr[7]);
/* 268:    */       }
/* 269:    */     }
/* 270:    */     catch (Exception ex)
/* 271:    */     {
/* 272:272 */       ex.printStackTrace();
/* 273:    */     }
/* 274:274 */     return mpptTrackNumber;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public String getSerialNo()
/* 278:    */   {
/* 279:279 */     String serialno = "";
/* 280:280 */     String serialnoStr = "";
/* 281:    */     try
/* 282:    */     {
/* 283:282 */       int i = 0;
/* 284:283 */       while (i < 3)
/* 285:    */       {
/* 286:284 */         serialnoStr = excuteCommand("QID", true);
/* 287:285 */         if ((serialnoStr != null) && (!"".equals(serialnoStr)) && 
/* 288:286 */           (!serialnoStr.equalsIgnoreCase("(NAK")) && 
/* 289:287 */           (!serialnoStr.equalsIgnoreCase("(ACK")) && 
/* 290:288 */           (!serialnoStr.equals("QID")))
/* 291:    */         {
/* 292:289 */           serialno = serialnoStr.substring(1);
/* 293:290 */           break;
/* 294:    */         }
/* 295:292 */         i++;
/* 296:    */       }
/* 297:    */     }
/* 298:    */     catch (Exception e)
/* 299:    */     {
/* 300:295 */       e.printStackTrace();
/* 301:    */     }
/* 302:297 */     return serialno;
/* 303:    */   }
/* 304:    */   
/* 305:    */   public String getModeType()
/* 306:    */   {
/* 307:302 */     String machineTypeStr = "";
/* 308:    */     try
/* 309:    */     {
/* 310:304 */       String qpiriStr = excuteCommand("QPIRI", true);
/* 311:305 */       if ((!"".equals(qpiriStr)) && (!qpiriStr.equals("(NAK")) && 
/* 312:306 */         (!qpiriStr.equals("QPIRI")))
/* 313:    */       {
/* 314:307 */         String[] ratingInfo = qpiriStr.split(" ");
/* 315:308 */         return ratingInfo[8];
/* 316:    */       }
/* 317:    */     }
/* 318:    */     catch (Exception ex)
/* 319:    */     {
/* 320:312 */       ex.printStackTrace();
/* 321:    */     }
/* 322:314 */     return machineTypeStr;
/* 323:    */   }
/* 324:    */   
/* 325:    */   public String getDeviceModel()
/* 326:    */   {
/* 327:319 */     String _deviceModel = "";
/* 328:    */     try
/* 329:    */     {
/* 330:321 */       String qdmStr = excuteCommand("QDM", true);
/* 331:322 */       if ((!"".equals(qdmStr)) && (!qdmStr.equals("(NAK")) && 
/* 332:323 */         (!qdmStr.equals("QDM")))
/* 333:    */       {
/* 334:324 */         qdmStr = qdmStr.substring(1);
/* 335:325 */         _deviceModel = qdmStr;
/* 336:    */       }
/* 337:    */     }
/* 338:    */     catch (Exception ex)
/* 339:    */     {
/* 340:328 */       ex.printStackTrace();
/* 341:    */     }
/* 342:330 */     return _deviceModel;
/* 343:    */   }
/* 344:    */   
/* 345:    */   public boolean isSupportQCTH()
/* 346:    */   {
/* 347:335 */     String qchtStr = excuteCommand("QCHT", true);
/* 348:336 */     if ((qchtStr != null) && (!"".equals(qchtStr)) && (!qchtStr.equals("(NAK"))) {
/* 349:337 */       return true;
/* 350:    */     }
/* 351:339 */     return false;
/* 352:    */   }
/* 353:    */   
/* 354:    */   public boolean isSupportQPPS()
/* 355:    */   {
/* 356:344 */     String ppsStr = excuteCommand("QPPS", true);
/* 357:345 */     if ((ppsStr != null) && (!"".equals(ppsStr)) && (!ppsStr.equals("(NAK"))) {
/* 358:346 */       return true;
/* 359:    */     }
/* 360:348 */     return false;
/* 361:    */   }
/* 362:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.SerialHandlerSolaris
 * JD-Core Version:    0.7.0.1
 */