/*   1:    */ package cn.com.voltronic.solar.test;
/*   2:    */ 
/*   3:    */ import gnu.io.CommPortIdentifier;
/*   4:    */ import gnu.io.SerialPort;
/*   5:    */ import gnu.io.UnsupportedCommOperationException;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.io.InputStream;
/*   8:    */ import java.io.OutputStream;
/*   9:    */ import java.io.PrintStream;
/*  10:    */ import java.util.Enumeration;
/*  11:    */ import java.util.TooManyListenersException;
/*  12:    */ 
/*  13:    */ public class TestComm
/*  14:    */ {
/*  15: 21 */   private InputStream input = null;
/*  16: 22 */   private static String RETURN_ANY = "ANY";
/*  17: 23 */   private OutputStream output = null;
/*  18:    */   private SerialPort serialPort;
/*  19:    */   
/*  20:    */   private void clearbuffer()
/*  21:    */   {
/*  22:    */     try
/*  23:    */     {
/*  24: 32 */       int buflen = this.input.available();
/*  25: 33 */       while (buflen > 0)
/*  26:    */       {
/*  27: 34 */         int x = this.input.read();
/*  28:    */         
/*  29: 36 */         buflen--;
/*  30:    */       }
/*  31:    */     }
/*  32:    */     catch (Exception e)
/*  33:    */     {
/*  34: 39 */       e.printStackTrace();
/*  35:    */     }
/*  36:    */   }
/*  37:    */   
/*  38:    */   public TestComm(SerialPort serialPort)
/*  39:    */     throws IOException, TooManyListenersException, UnsupportedCommOperationException
/*  40:    */   {
/*  41: 43 */     this.serialPort = serialPort;
/*  42: 44 */     this.input = serialPort.getInputStream();
/*  43: 45 */     this.output = serialPort.getOutputStream();
/*  44:    */     
/*  45:    */ 
/*  46:    */ 
/*  47: 49 */     this.serialPort.setSerialPortParams(2400, 8, 
/*  48: 50 */       1, 0);
/*  49:    */     
/*  50: 52 */     this.serialPort.notifyOnDataAvailable(true);
/*  51: 53 */     this.serialPort.notifyOnCTS(true);
/*  52: 54 */     this.serialPort.notifyOnDSR(true);
/*  53:    */   }
/*  54:    */   
/*  55:    */   private boolean executeComboxCommand(String command, String value, int timeout)
/*  56:    */     throws IOException
/*  57:    */   {
/*  58: 59 */     boolean match = false;
/*  59: 60 */     String firstvalue = null;
/*  60: 61 */     while (!match)
/*  61:    */     {
/*  62: 62 */       String result = executeOnecommand(command.getBytes(), timeout);
/*  63: 63 */       if (result.equalsIgnoreCase(value))
/*  64:    */       {
/*  65: 64 */         match = true;
/*  66: 65 */         break;
/*  67:    */       }
/*  68: 67 */       if (firstvalue == null) {
/*  69: 68 */         firstvalue = result;
/*  70: 69 */       } else if (firstvalue.equalsIgnoreCase(result)) {
/*  71: 70 */         return false;
/*  72:    */       }
/*  73: 72 */       result = executeOnecommand(new byte[] { 45 }, timeout);
/*  74:    */     }
/*  75: 75 */     return true;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public synchronized String executeSinglSetValue(String command, String value, int timeout)
/*  79:    */     throws IOException
/*  80:    */   {
/*  81: 81 */     executeOnecommand(command.getBytes(), 50);
/*  82: 82 */     String cmd = "-" + value;
/*  83: 83 */     return executeOnecommand(cmd.getBytes(), timeout);
/*  84:    */   }
/*  85:    */   
/*  86:    */   private boolean executeCommand9FFE()
/*  87:    */   {
/*  88: 91 */     boolean success = false;
/*  89:    */     try
/*  90:    */     {
/*  91: 93 */       success = "OK".equals(executeOnecommand(new byte[] { -97, -2 }, 50));
/*  92:    */     }
/*  93:    */     catch (Exception localException) {}
/*  94: 97 */     return success;
/*  95:    */   }
/*  96:    */   
/*  97:    */   private boolean executeCommand9FFF()
/*  98:    */   {
/*  99:100 */     boolean success = false;
/* 100:    */     try
/* 101:    */     {
/* 102:102 */       success = "OK".equals(executeOnecommand(new byte[] { -97, -2 }, 50));
/* 103:    */     }
/* 104:    */     catch (Exception localException) {}
/* 105:106 */     return success;
/* 106:    */   }
/* 107:    */   
/* 108:    */   private boolean executeNumber(String value)
/* 109:    */   {
/* 110:111 */     boolean success = true;
/* 111:112 */     byte[][] table = { { -97, -64 }, 
/* 112:113 */       { -97, -63 }, 
/* 113:114 */       { -97, -62 }, 
/* 114:115 */       { -97, -61 }, 
/* 115:116 */       { -97, -60 }, 
/* 116:117 */       { -97, -59 }, 
/* 117:118 */       { -97, -58 }, 
/* 118:119 */       { -97, -57 }, 
/* 119:120 */       { -97, -56 }, 
/* 120:121 */       { -97, -55 } };
/* 121:    */     
/* 122:123 */     value = value.trim();
/* 123:124 */     for (int index = 0; index < value.length(); index++)
/* 124:    */     {
/* 125:125 */       char curchar = value.charAt(index);
/* 126:    */       try
/* 127:    */       {
/* 128:127 */         if (curchar == '.')
/* 129:    */         {
/* 130:128 */           if ("OK".equals(executeOnecommand(new byte[] { -97, -49 }, 50))) {
/* 131:129 */             if ("OK".equals(executeOnecommand(new byte[] { -97, -50 }, 50))) {
/* 132:130 */               if (":.".equals(executeOnecommand(new byte[] { -97, -58 }, 50))) {
/* 133:    */                 continue;
/* 134:    */               }
/* 135:    */             }
/* 136:    */           }
/* 137:131 */           success = false;
/* 138:    */         }
/* 139:    */         else
/* 140:    */         {
/* 141:134 */           int pos = Integer.parseInt(curchar);
/* 142:135 */           if ((pos < 0) && (pos > 9)) {
/* 143:136 */             success = false;
/* 144:139 */           } else if (!(":" + curchar).equals(executeOnecommand(table[pos], 50))) {
/* 145:140 */             success = false;
/* 146:    */           }
/* 147:    */         }
/* 148:    */       }
/* 149:    */       catch (Exception e)
/* 150:    */       {
/* 151:145 */         success = false;
/* 152:    */       }
/* 153:    */     }
/* 154:152 */     return success;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public synchronized boolean executeDoubleSetValue(byte[][] cmdbuff, String value, String[] checkuff, int timeout)
/* 158:    */     throws IOException
/* 159:    */   {
/* 160:157 */     boolean bsuccess = true;
/* 161:158 */     int line_i = 0;
/* 162:159 */     if (executeCommand9FFE())
/* 163:    */     {
/* 164:160 */       if (executeNumber(value)) {
/* 165:161 */         for (line_i = 0; line_i < cmdbuff.length; line_i++)
/* 166:    */         {
/* 167:162 */           String result = executeOnecommand(cmdbuff[line_i], timeout);
/* 168:163 */           if (checkuff[line_i].equals(RETURN_ANY))
/* 169:    */           {
/* 170:164 */             if (result.length() == 0)
/* 171:    */             {
/* 172:165 */               bsuccess = false;
/* 173:166 */               break;
/* 174:    */             }
/* 175:    */           }
/* 176:168 */           else if (!result.equalsIgnoreCase(checkuff[line_i]))
/* 177:    */           {
/* 178:170 */             bsuccess = false;
/* 179:171 */             break;
/* 180:    */           }
/* 181:    */         }
/* 182:    */       }
/* 183:175 */       executeCommand9FFF();
/* 184:    */     }
/* 185:177 */     return bsuccess;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public synchronized boolean executeDoubleED(byte[][] sendbuff, String[] checkbuff, int timeout)
/* 189:    */     throws IOException
/* 190:    */   {
/* 191:182 */     boolean bsuccess = true;
/* 192:183 */     int line_i = 0;
/* 193:185 */     if (executeCommand9FFE())
/* 194:    */     {
/* 195:186 */       for (line_i = 0; line_i < sendbuff.length; line_i++)
/* 196:    */       {
/* 197:187 */         String result = executeOnecommand(sendbuff[line_i], timeout);
/* 198:188 */         if (checkbuff[line_i].equals(RETURN_ANY))
/* 199:    */         {
/* 200:189 */           if (result.length() == 0)
/* 201:    */           {
/* 202:190 */             bsuccess = false;
/* 203:191 */             break;
/* 204:    */           }
/* 205:    */         }
/* 206:193 */         else if (!result.equalsIgnoreCase(checkbuff[line_i]))
/* 207:    */         {
/* 208:195 */           bsuccess = false;
/* 209:196 */           break;
/* 210:    */         }
/* 211:    */       }
/* 212:199 */       executeCommand9FFF();
/* 213:    */     }
/* 214:201 */     return bsuccess;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public String executeOnecommand(byte[] bytes, int timeout)
/* 218:    */     throws IOException
/* 219:    */   {
/* 220:205 */     int index = 0;
/* 221:206 */     boolean firstcmd = true;
/* 222:207 */     clearbuffer();
/* 223:209 */     for (index = 0; index < bytes.length; index++)
/* 224:    */     {
/* 225:210 */       this.output.write(bytes[index]);
/* 226:211 */       this.output.flush();
/* 227:    */       try
/* 228:    */       {
/* 229:213 */         Thread.sleep(timeout);
/* 230:    */       }
/* 231:    */       catch (Exception localException) {}
/* 232:    */     }
/* 233:219 */     long end = System.currentTimeMillis() + 3000L;
/* 234:    */     
/* 235:221 */     StringBuilder sb = new StringBuilder();
/* 236:222 */     int flag = 0;
/* 237:225 */     while (System.currentTimeMillis() < end)
/* 238:    */     {
/* 239:    */       int ch;
/* 240:227 */       if ((ch = this.input.read()) >= 0)
/* 241:    */       {
/* 242:228 */         System.out.println(" " + ch + "=" + (char)ch);
/* 243:229 */         if ((!firstcmd) || ((ch != 33) && (ch != 36) && (ch != 37) && (ch != 43) && (ch != 63) && (ch != 61) && (ch != 42) && (ch != 35) && (ch != 38) && (ch != 124))) {
/* 244:231 */           if (ch == 13)
/* 245:    */           {
/* 246:232 */             flag = 1;
/* 247:    */           }
/* 248:    */           else
/* 249:    */           {
/* 250:233 */             if ((ch == 10) && (flag == 1))
/* 251:    */             {
/* 252:234 */               flag = 2;
/* 253:235 */               break;
/* 254:    */             }
/* 255:237 */             firstcmd = false;
/* 256:238 */             sb.append((char)ch);
/* 257:    */           }
/* 258:    */         }
/* 259:    */       }
/* 260:    */     }
/* 261:242 */     if (flag != 2) {
/* 262:243 */       throw new IOException();
/* 263:    */     }
/* 264:245 */     String returnValue = sb.toString();
/* 265:246 */     return returnValue;
/* 266:    */   }
/* 267:    */   
/* 268:    */   public synchronized String excuteCommand(String command, boolean isResponse)
/* 269:    */   {
/* 270:251 */     String returnValue = "";
/* 271:    */     try
/* 272:    */     {
/* 273:253 */       byte[] bytes = command.getBytes();
/* 274:254 */       this.output.write(bytes);
/* 275:255 */       this.output.write(13);
/* 276:256 */       this.output.flush();
/* 277:257 */       if (isResponse)
/* 278:    */       {
/* 279:258 */         long end = System.currentTimeMillis() + 3000L;
/* 280:    */         
/* 281:260 */         StringBuilder sb = new StringBuilder();
/* 282:261 */         while (System.currentTimeMillis() < end)
/* 283:    */         {
/* 284:    */           int ch;
/* 285:262 */           if ((ch = this.input.read()) > 0)
/* 286:    */           {
/* 287:263 */             if (ch == 13) {
/* 288:    */               break;
/* 289:    */             }
/* 290:264 */             sb.append((char)ch);
/* 291:    */           }
/* 292:    */         }
/* 293:270 */         returnValue = sb.toString();
/* 294:    */       }
/* 295:    */       else
/* 296:    */       {
/* 297:272 */         returnValue = null;
/* 298:    */       }
/* 299:    */     }
/* 300:    */     catch (Exception ex)
/* 301:    */     {
/* 302:275 */       ex.printStackTrace();
/* 303:    */     }
/* 304:    */     finally
/* 305:    */     {
/* 306:277 */       close();
/* 307:    */     }
/* 308:279 */     return returnValue;
/* 309:    */   }
/* 310:    */   
/* 311:    */   public void close()
/* 312:    */   {
/* 313:283 */     if (this.input != null) {
/* 314:    */       try
/* 315:    */       {
/* 316:285 */         this.input.close();
/* 317:    */       }
/* 318:    */       catch (IOException localIOException) {}
/* 319:    */     }
/* 320:289 */     if (this.output != null) {
/* 321:    */       try
/* 322:    */       {
/* 323:291 */         this.output.close();
/* 324:    */       }
/* 325:    */       catch (IOException localIOException1) {}
/* 326:    */     }
/* 327:295 */     if (this.serialPort != null) {
/* 328:    */       try
/* 329:    */       {
/* 330:297 */         this.serialPort.close();
/* 331:    */       }
/* 332:    */       catch (Exception localException) {}
/* 333:    */     }
/* 334:    */   }
/* 335:    */   
/* 336:    */   public static void main(String[] args)
/* 337:    */   {
/* 338:306 */     Enumeration en = CommPortIdentifier.getPortIdentifiers();
/* 339:307 */     while (en.hasMoreElements())
/* 340:    */     {
/* 341:308 */       CommPortIdentifier portId = (CommPortIdentifier)en.nextElement();
/* 342:309 */       if ((portId.getPortType() == 1) && (portId.getName().equalsIgnoreCase("COM8")))
/* 343:    */       {
/* 344:310 */         System.out.println(portId.getName());
/* 345:    */         try
/* 346:    */         {
/* 347:312 */           SerialPort serialPort = (SerialPort)portId.open("Arista", 2400);
/* 348:313 */           TestComm comm = new TestComm(serialPort);
/* 349:    */           
/* 350:    */ 
/* 351:316 */           System.out.println(comm.executeOnecommand(new byte[] { 89 }, 10));
/* 352:    */           
/* 353:318 */           System.out.println(comm.executeOnecommand(new byte[] { 26 }, 50));
/* 354:    */           
/* 355:    */ 
/* 356:    */ 
/* 357:    */ 
/* 358:    */ 
/* 359:324 */           System.out.println(comm.executeSinglSetValue("x", "09/07/13", 50));
/* 360:    */           
/* 361:326 */           System.out.println(comm.executeOnecommand(new byte[] { 120 }, 50));
/* 362:    */           
/* 363:    */ 
/* 364:329 */           System.out.println(comm.executeOnecommand(new byte[] { 57 }, 50));
/* 365:    */           
/* 366:    */ 
/* 367:    */ 
/* 368:333 */           System.out.println(comm.executeComboxCommand("p", "180", 50));
/* 369:    */           
/* 370:    */ 
/* 371:    */ 
/* 372:337 */           System.out.println(comm.executeOnecommand(new byte[] { -97, -46 }, 50));
/* 373:    */           
/* 374:339 */           System.out.println(comm.executeOnecommand(new byte[] { -97, -47 }, 50));
/* 375:    */           
/* 376:341 */           System.out.println(comm.executeOnecommand(new byte[] { -97, -41 }, 50));
/* 377:    */           
/* 378:343 */           System.out.println(comm.executeOnecommand(new byte[] { -97, -42 }, 50));
/* 379:    */           
/* 380:    */ 
/* 381:346 */           System.out.println("E2=" + comm.executeOnecommand(new byte[] { -97, -30 }, 50));
/* 382:    */           
/* 383:348 */           System.out.println("E3=" + comm.executeOnecommand(new byte[] { -97, -29 }, 50));
/* 384:    */           
/* 385:    */ 
/* 386:351 */           System.out.println(comm
/* 387:    */           
/* 388:353 */             .executeDoubleED(new byte[][] { { -97, -30 }, { -97, -53 } }, new String[] { RETURN_ANY, "OK" }, 50));
/* 389:    */           
/* 390:    */ 
/* 391:356 */           System.out.println(comm
/* 392:    */           
/* 393:358 */             .executeDoubleED(new byte[][] { { -97, -29 }, { -97, -54 } }, new String[] { RETURN_ANY, "OK" }, 50));
/* 394:    */           
/* 395:    */ 
/* 396:    */ 
/* 397:    */ 
/* 398:363 */           System.out.println(comm
/* 399:364 */             .executeDoubleED(new byte[][] { { -97, -32 } }, new String[] { "OK" }, 50));
/* 400:    */           
/* 401:    */ 
/* 402:367 */           System.out.println(comm
/* 403:    */           
/* 404:369 */             .executeDoubleSetValue(new byte[][] { { -97, -16 }, { -97, -54 } }, "52.0", new String[] { RETURN_ANY, "OK" }, 50));
/* 405:    */           
/* 406:    */ 
/* 407:    */ 
/* 408:    */ 
/* 409:374 */           System.out.println(comm
/* 410:    */           
/* 411:376 */             .executeDoubleSetValue(new byte[][] { { -97, -15 }, { -97, -54 } }, "250", new String[] { RETURN_ANY, "OK" }, 50));
/* 412:    */           
/* 413:    */ 
/* 414:    */ 
/* 415:380 */           System.out.println(comm
/* 416:381 */             .executeDoubleSetValue(new byte[][] { { -97, -31 } }, "01", new String[] { "OK" }, 50));
/* 417:    */           
/* 418:    */ 
/* 419:384 */           Thread.sleep(2000L);
/* 420:385 */           System.exit(0);
/* 421:    */         }
/* 422:    */         catch (Exception e)
/* 423:    */         {
/* 424:388 */           e.printStackTrace();
/* 425:    */         }
/* 426:    */       }
/* 427:    */     }
/* 428:    */   }
/* 429:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.test.TestComm
 * JD-Core Version:    0.7.0.1
 */