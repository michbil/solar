/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import gnu.io.CommPortIdentifier;
/*   4:    */ import gnu.io.SerialPort;
/*   5:    */ import java.io.DataInputStream;
/*   6:    */ import java.io.IOException;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.io.PrintWriter;
/*   9:    */ import java.util.Enumeration;
/*  10:    */ 
/*  11:    */ public class ModemAdapter
/*  12:    */ {
/*  13:    */   private String[] destinations;
/*  14:    */   private String mobileNum;
/*  15:    */   private String message;
/*  16:    */   private int baudRate;
/*  17:    */   private String portName;
/*  18: 23 */   String mstr2 = null;
/*  19:    */   private PrintWriter pw;
/*  20:    */   private DataInputStream in;
/*  21:    */   
/*  22:    */   public ModemAdapter(String portName, int baudRate, String[] destinations, String message)
/*  23:    */   {
/*  24: 31 */     this.destinations = destinations;
/*  25: 32 */     this.message = message;
/*  26: 33 */     this.baudRate = baudRate;
/*  27: 34 */     this.portName = portName;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public ModemAdapter(String portName, int baudRate, String mobileNum, String message)
/*  31:    */   {
/*  32: 39 */     this.mobileNum = mobileNum;
/*  33: 40 */     this.message = message;
/*  34: 41 */     this.baudRate = baudRate;
/*  35: 42 */     this.portName = portName;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public boolean startSendOne()
/*  39:    */   {
/*  40: 52 */     boolean result = false;
/*  41: 53 */     int keeptime = 5000;
/*  42: 54 */     Enumeration portList = CommPortIdentifier.getPortIdentifiers();
/*  43: 56 */     while (portList.hasMoreElements())
/*  44:    */     {
/*  45: 57 */       CommPortIdentifier portID = 
/*  46: 58 */         (CommPortIdentifier)portList.nextElement();
/*  47: 59 */       if (portID.getName().equals(this.portName))
/*  48:    */       {
/*  49: 60 */         SerialPort sPort = null;
/*  50:    */         
/*  51: 62 */         StringBuilder sb = new StringBuilder();
/*  52:    */         try
/*  53:    */         {
/*  54: 64 */           sPort = (SerialPort)portID.open("ats", keeptime);
/*  55: 65 */           sPort.setSerialPortParams(this.baudRate, 
/*  56: 66 */             8, 
/*  57: 67 */             1, 
/*  58: 68 */             0);
/*  59: 69 */           sPort.enableReceiveTimeout(1200);
/*  60: 70 */           this.pw = new PrintWriter(sPort.getOutputStream());
/*  61: 71 */           this.pw.println("at+cmgf=0;");
/*  62: 72 */           this.pw.flush();
/*  63: 73 */           int c = 0;
/*  64: 74 */           this.in = new DataInputStream(sPort.getInputStream());
/*  65: 75 */           long end = System.currentTimeMillis() + 1000L;
/*  66: 76 */           while (System.currentTimeMillis() < end) {
/*  67: 77 */             if ((c = this.in.read()) != -1)
/*  68:    */             {
/*  69: 78 */               sb.append((char)c);
/*  70: 79 */               String temp = sb.toString();
/*  71: 80 */               if (temp.endsWith("OK")) {
/*  72: 81 */                 result = true;
/*  73:    */               } else {
/*  74: 83 */                 if (temp.endsWith("ERROR")) {
/*  75:    */                   break;
/*  76:    */                 }
/*  77:    */               }
/*  78:    */             }
/*  79:    */           }
/*  80: 88 */           if (result) {
/*  81: 89 */             sendSMS(this.mobileNum, this.message);
/*  82:    */           }
/*  83:    */         }
/*  84:    */         catch (Exception e)
/*  85:    */         {
/*  86: 92 */           result = false;
/*  87:    */         }
/*  88:    */         finally
/*  89:    */         {
/*  90: 94 */           if (this.in != null) {
/*  91:    */             try
/*  92:    */             {
/*  93: 96 */               this.in.close();
/*  94:    */             }
/*  95:    */             catch (IOException localIOException1) {}
/*  96:    */           }
/*  97:100 */           if (this.pw != null) {
/*  98:101 */             this.pw.close();
/*  99:    */           }
/* 100:103 */           if (sPort != null) {
/* 101:104 */             sPort.close();
/* 102:    */           }
/* 103:    */         }
/* 104:    */       }
/* 105:    */     }
/* 106:109 */     return result;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public boolean startSend()
/* 110:    */   {
/* 111:119 */     boolean result = false;
/* 112:120 */     int keeptime = 5000;
/* 113:121 */     Enumeration portList = CommPortIdentifier.getPortIdentifiers();
/* 114:123 */     while (portList.hasMoreElements())
/* 115:    */     {
/* 116:124 */       CommPortIdentifier portID = 
/* 117:125 */         (CommPortIdentifier)portList.nextElement();
/* 118:126 */       if (portID.getName().equals(this.portName))
/* 119:    */       {
/* 120:127 */         SerialPort sPort = null;
/* 121:    */         
/* 122:129 */         StringBuilder sb = new StringBuilder();
/* 123:    */         try
/* 124:    */         {
/* 125:131 */           sPort = (SerialPort)portID.open("ats", keeptime);
/* 126:132 */           sPort.setSerialPortParams(this.baudRate, 
/* 127:133 */             8, 
/* 128:134 */             1, 
/* 129:135 */             0);
/* 130:136 */           sPort.enableReceiveTimeout(1200);
/* 131:137 */           this.pw = new PrintWriter(sPort.getOutputStream());
/* 132:138 */           this.pw.println("at+cmgf=0;");
/* 133:139 */           this.pw.flush();
/* 134:140 */           int c = 0;
/* 135:141 */           this.in = new DataInputStream(sPort.getInputStream());
/* 136:142 */           long end = System.currentTimeMillis() + 1000L;
/* 137:143 */           while (System.currentTimeMillis() < end) {
/* 138:144 */             if ((c = this.in.read()) != -1)
/* 139:    */             {
/* 140:145 */               sb.append((char)c);
/* 141:146 */               String temp = sb.toString();
/* 142:147 */               if (temp.endsWith("OK")) {
/* 143:148 */                 result = true;
/* 144:    */               } else {
/* 145:150 */                 if (temp.endsWith("ERROR")) {
/* 146:    */                   break;
/* 147:    */                 }
/* 148:    */               }
/* 149:    */             }
/* 150:    */           }
/* 151:155 */           if (result) {
/* 152:156 */             for (String destination : this.destinations)
/* 153:    */             {
/* 154:157 */               Thread.sleep(500L);
/* 155:158 */               sendSMS(destination, this.message);
/* 156:    */             }
/* 157:    */           }
/* 158:    */         }
/* 159:    */         catch (Exception e)
/* 160:    */         {
/* 161:162 */           result = false;
/* 162:    */         }
/* 163:    */         finally
/* 164:    */         {
/* 165:164 */           if (this.in != null) {
/* 166:    */             try
/* 167:    */             {
/* 168:166 */               this.in.close();
/* 169:    */             }
/* 170:    */             catch (IOException localIOException1) {}
/* 171:    */           }
/* 172:170 */           if (this.pw != null) {
/* 173:171 */             this.pw.close();
/* 174:    */           }
/* 175:173 */           if (sPort != null) {
/* 176:174 */             sPort.close();
/* 177:    */           }
/* 178:    */         }
/* 179:    */       }
/* 180:    */     }
/* 181:179 */     return result;
/* 182:    */   }
/* 183:    */   
/* 184:    */   private void sendMsg(String msg)
/* 185:    */   {
/* 186:188 */     this.pw.println(msg);
/* 187:189 */     this.pw.flush();
/* 188:    */   }
/* 189:    */   
/* 190:    */   public static void main(String[] args)
/* 191:    */   {
/* 192:193 */     String[] destinations = { "13538199005", "13480630524" };
/* 193:194 */     ModemAdapter adapter = new ModemAdapter("COM1", 9600, destinations, 
/* 194:195 */       "测试短信模块发送程序的代码");
/* 195:    */     try
/* 196:    */     {
/* 197:197 */       System.out.println(adapter.startSend());
/* 198:    */     }
/* 199:    */     catch (Exception localException) {}
/* 200:    */   }
/* 201:    */   
/* 202:    */   private void sendSMS(String phone, String message)
/* 203:    */   {
/* 204:204 */     if (phone.indexOf("-") != -1) {
/* 205:205 */       phone = phone.replaceAll("-", "");
/* 206:    */     }
/* 207:212 */     String phonelen = Integer.toHexString(phone.length()).toUpperCase();
/* 208:213 */     if (phonelen.length() < 2) {
/* 209:214 */       phonelen = "0" + phonelen;
/* 210:    */     }
/* 211:215 */     String sPhone = smsPhoneTranslate(phone);
/* 212:216 */     String sContent = smsMessageTranslateU(message);
/* 213:217 */     int len = sContent.length();
/* 214:218 */     len = (16 + sPhone.length() + len) / 2;
/* 215:219 */     String lenth = String.valueOf(len);
/* 216:220 */     int mlen = message.length();
/* 217:221 */     String messagelen = Integer.toHexString(mlen * 2);
/* 218:222 */     messagelen = messagelen.toUpperCase();
/* 219:223 */     if (messagelen.length() < 2) {
/* 220:224 */       messagelen = "0" + messagelen;
/* 221:    */     }
/* 222:227 */     String cstemp = "AT+CMGS=" + lenth;
/* 223:228 */     cstemp = cstemp + "\r";
/* 224:229 */     sendMsg(cstemp);
/* 225:230 */     long current = System.currentTimeMillis();
/* 226:231 */     while (System.currentTimeMillis() - current < 1000L) {
/* 227:    */       try
/* 228:    */       {
/* 229:233 */         Thread.sleep(200L);
/* 230:    */       }
/* 231:    */       catch (Exception localException) {}
/* 232:    */     }
/* 233:237 */     cstemp = "001100" + phonelen;
/* 234:238 */     cstemp = cstemp + "81";
/* 235:239 */     cstemp = cstemp + sPhone;
/* 236:240 */     cstemp = cstemp + "0008A7";
/* 237:241 */     cstemp = cstemp + messagelen;
/* 238:242 */     cstemp = cstemp + sContent;
/* 239:243 */     char charTemp = '\032';
/* 240:244 */     cstemp = cstemp + String.valueOf(charTemp);
/* 241:245 */     sendMsg(cstemp);
/* 242:246 */     current = System.currentTimeMillis();
/* 243:247 */     while (System.currentTimeMillis() - current < 1000L) {
/* 244:    */       try
/* 245:    */       {
/* 246:249 */         Thread.sleep(200L);
/* 247:    */       }
/* 248:    */       catch (Exception localException1) {}
/* 249:    */     }
/* 250:253 */     this.mstr2 = "";
/* 251:    */     
/* 252:    */ 
/* 253:    */ 
/* 254:257 */     current = System.currentTimeMillis();
/* 255:258 */     while ((System.currentTimeMillis() - current < 10000L) && 
/* 256:259 */       (this.mstr2.indexOf("OK", 0) == -1)) {
/* 257:    */       try
/* 258:    */       {
/* 259:261 */         Thread.sleep(200L);
/* 260:    */       }
/* 261:    */       catch (Exception localException2) {}
/* 262:    */     }
/* 263:    */   }
/* 264:    */   
/* 265:    */   private String smsPhoneTranslate(String number)
/* 266:    */   {
/* 267:270 */     String csNum = "";
/* 268:271 */     if (number.length() % 2 == 1) {
/* 269:272 */       number = number + "F";
/* 270:    */     }
/* 271:273 */     int len = number.length();
/* 272:274 */     int i = 0;
/* 273:275 */     while (i < len)
/* 274:    */     {
/* 275:276 */       String first = number.substring(i, i + 1);
/* 276:277 */       String second = number.substring(i + 1, i + 2);
/* 277:278 */       csNum = csNum + second + first;
/* 278:279 */       i++;
/* 279:280 */       i++;
/* 280:    */     }
/* 281:282 */     return csNum;
/* 282:    */   }
/* 283:    */   
/* 284:    */   private String smsMessageTranslateU(String Content)
/* 285:    */   {
/* 286:    */     try
/* 287:    */     {
/* 288:288 */       StringBuffer sb = new StringBuffer(500);
/* 289:    */       
/* 290:    */ 
/* 291:291 */       sb.setLength(0);
/* 292:292 */       for (int i = 0; i < Content.length(); i++)
/* 293:    */       {
/* 294:293 */         char c = Content.charAt(i);
/* 295:294 */         if (c > 'ÿ')
/* 296:    */         {
/* 297:295 */           int j = c >>> '\b';
/* 298:296 */           String tmp = Integer.toHexString(j);
/* 299:297 */           if (tmp.length() == 1) {
/* 300:298 */             sb.append("0");
/* 301:    */           }
/* 302:299 */           sb.append(tmp);
/* 303:300 */           j = c & 0xFF;
/* 304:301 */           tmp = Integer.toHexString(j);
/* 305:302 */           if (tmp.length() == 1) {
/* 306:303 */             sb.append("0");
/* 307:    */           }
/* 308:304 */           sb.append(tmp);
/* 309:    */         }
/* 310:    */         else
/* 311:    */         {
/* 312:306 */           sb.append("00" + Integer.toHexString(c));
/* 313:    */         }
/* 314:    */       }
/* 315:309 */       return new String(sb).toUpperCase();
/* 316:    */     }
/* 317:    */     catch (Exception e)
/* 318:    */     {
/* 319:311 */       e.printStackTrace();
/* 320:    */     }
/* 321:312 */     return "";
/* 322:    */   }
/* 323:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.ModemAdapter
 * JD-Core Version:    0.7.0.1
 */