/*   1:    */ package cn.com.voltronic.solar.control;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.communicate.IComUSBHandler;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ 
/*   6:    */ public class P30ComUSBControlModule
/*   7:    */   extends AbstractControlModule
/*   8:    */   implements IP30ControlModule
/*   9:    */ {
/*  10:    */   public P30ComUSBControlModule(Object handler)
/*  11:    */   {
/*  12: 19 */     super(handler);
/*  13:    */   }
/*  14:    */   
/*  15:    */   public IComUSBHandler getHandler()
/*  16:    */   {
/*  17: 23 */     return (IComUSBHandler)super.getHandler();
/*  18:    */   }
/*  19:    */   
/*  20:    */   public boolean setBatteryLow(double value)
/*  21:    */   {
/*  22: 28 */     boolean result = true;
/*  23:    */     try
/*  24:    */     {
/*  25: 30 */       result = excuteCommand("BATLOW", getFomatStr(value, 4));
/*  26:    */     }
/*  27:    */     catch (Exception e)
/*  28:    */     {
/*  29: 32 */       result = false;
/*  30:    */     }
/*  31: 34 */     return result;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public boolean setBatteryType(String value)
/*  35:    */   {
/*  36: 39 */     boolean result = true;
/*  37:    */     try
/*  38:    */     {
/*  39: 41 */       result = excuteCommand("PBT", getFomatStr(value, 2));
/*  40:    */     }
/*  41:    */     catch (Exception e)
/*  42:    */     {
/*  43: 43 */       result = false;
/*  44:    */     }
/*  45: 45 */     return result;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public boolean setBatteryUnder(double value)
/*  49:    */   {
/*  50: 50 */     boolean result = true;
/*  51:    */     try
/*  52:    */     {
/*  53: 52 */       result = excuteCommand("PSDV", String.format("%04.01f", new Object[] { Double.valueOf(value) }));
/*  54:    */     }
/*  55:    */     catch (Exception e)
/*  56:    */     {
/*  57: 54 */       result = false;
/*  58:    */     }
/*  59: 56 */     return result;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public boolean setChargerSource(String value)
/*  63:    */   {
/*  64: 61 */     boolean result = true;
/*  65:    */     try
/*  66:    */     {
/*  67: 63 */       result = excuteCommand("PCP", getFomatStr(value, 2));
/*  68:    */     }
/*  69:    */     catch (Exception e)
/*  70:    */     {
/*  71: 65 */       result = false;
/*  72:    */     }
/*  73: 67 */     return result;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean setGridWorkRange(String value)
/*  77:    */   {
/*  78: 73 */     boolean result = true;
/*  79:    */     try
/*  80:    */     {
/*  81: 75 */       result = excuteCommand("PGR", getFomatStr(value, 2));
/*  82:    */     }
/*  83:    */     catch (Exception e)
/*  84:    */     {
/*  85: 77 */       result = false;
/*  86:    */     }
/*  87: 79 */     return result;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public boolean setMaxChargingCurrent(int value)
/*  91:    */   {
/*  92: 84 */     boolean result = true;
/*  93:    */     try
/*  94:    */     {
/*  95: 86 */       result = excuteCommand("MCHGC", getFomatStr(value, 3));
/*  96:    */     }
/*  97:    */     catch (Exception e)
/*  98:    */     {
/*  99: 88 */       result = false;
/* 100:    */     }
/* 101: 90 */     return result;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public boolean setMaxChargingVoltage(double value)
/* 105:    */   {
/* 106: 96 */     boolean result = true;
/* 107:    */     try
/* 108:    */     {
/* 109: 98 */       result = excuteCommand("PCVV", String.format("%04.01f", new Object[] { Double.valueOf(value) }));
/* 110:    */     }
/* 111:    */     catch (Exception e)
/* 112:    */     {
/* 113:100 */       result = false;
/* 114:    */     }
/* 115:102 */     return result;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public boolean setOutputFrequency(int value)
/* 119:    */   {
/* 120:108 */     boolean result = true;
/* 121:    */     try
/* 122:    */     {
/* 123:110 */       result = excuteCommand("F", getFomatStr(value, 2));
/* 124:    */     }
/* 125:    */     catch (Exception e)
/* 126:    */     {
/* 127:112 */       result = false;
/* 128:    */     }
/* 129:114 */     return result;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public boolean setOutputSource(String value)
/* 133:    */   {
/* 134:121 */     boolean result = true;
/* 135:    */     try
/* 136:    */     {
/* 137:123 */       result = excuteCommand("POP", getFomatStr(value, 2));
/* 138:    */     }
/* 139:    */     catch (Exception e)
/* 140:    */     {
/* 141:125 */       result = false;
/* 142:    */     }
/* 143:127 */     return result;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public boolean setOutputVoltage(int value)
/* 147:    */   {
/* 148:134 */     boolean result = true;
/* 149:    */     try
/* 150:    */     {
/* 151:136 */       result = excuteCommand("V", getFomatStr(value, 3));
/* 152:    */     }
/* 153:    */     catch (Exception e)
/* 154:    */     {
/* 155:138 */       result = false;
/* 156:    */     }
/* 157:140 */     return result;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public boolean setCapability(String x, boolean isEnable)
/* 161:    */   {
/* 162:145 */     boolean result = true;
/* 163:    */     try
/* 164:    */     {
/* 165:147 */       result = excuteCommand(isEnable ? "PE" : "PD", x);
/* 166:    */     }
/* 167:    */     catch (Exception e)
/* 168:    */     {
/* 169:149 */       result = false;
/* 170:150 */       e.printStackTrace();
/* 171:    */     }
/* 172:152 */     return result;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public boolean setPF()
/* 176:    */   {
/* 177:157 */     boolean result = true;
/* 178:    */     try
/* 179:    */     {
/* 180:159 */       result = excuteCommand("PF", null);
/* 181:    */     }
/* 182:    */     catch (Exception e)
/* 183:    */     {
/* 184:161 */       result = false;
/* 185:    */     }
/* 186:163 */     return result;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public boolean excuteCommand(String command, String parmeter)
/* 190:    */     throws Exception
/* 191:    */   {
/* 192:169 */     IComUSBHandler _handler = getHandler();
/* 193:170 */     boolean result = false;
/* 194:171 */     String re = "(NAK";
/* 195:172 */     int times = 0;
/* 196:175 */     while ((re.equals("(NAK")) && (times < 3))
/* 197:    */     {
/* 198:176 */       if (_handler != null) {
/* 199:177 */         if ((parmeter == null) || ("".equals(parmeter)))
/* 200:    */         {
/* 201:178 */           re = _handler.excuteCommand(command, true);
/* 202:    */         }
/* 203:    */         else
/* 204:    */         {
/* 205:180 */           re = _handler.excuteCommand(command + parmeter, true);
/* 206:181 */           System.out.println("command=" + command + parmeter);
/* 207:    */         }
/* 208:    */       }
/* 209:184 */       times++;
/* 210:    */     }
/* 211:186 */     times = 0;
/* 212:187 */     System.out.println("ret=" + re);
/* 213:188 */     if (re.equals("(ACK")) {
/* 214:189 */       result = true;
/* 215:    */     } else {
/* 216:191 */       result = false;
/* 217:    */     }
/* 218:193 */     return result;
/* 219:    */   }
/* 220:    */   
/* 221:    */   public boolean setRechargingVol(double value)
/* 222:    */   {
/* 223:198 */     boolean result = true;
/* 224:    */     try
/* 225:    */     {
/* 226:201 */       result = excuteCommand("PBCV", String.format("%04.01f", new Object[] { Double.valueOf(value) }));
/* 227:    */     }
/* 228:    */     catch (Exception e)
/* 229:    */     {
/* 230:204 */       result = false;
/* 231:    */     }
/* 232:206 */     return result;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public boolean setReDisChargeVoltage(double value)
/* 236:    */   {
/* 237:211 */     boolean result = true;
/* 238:    */     try
/* 239:    */     {
/* 240:213 */       result = excuteCommand("PBDV", String.format("%04.01f", new Object[] { Double.valueOf(value) }));
/* 241:    */     }
/* 242:    */     catch (Exception e)
/* 243:    */     {
/* 244:216 */       result = false;
/* 245:    */     }
/* 246:218 */     return result;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public boolean setFloatingVoltage(double value)
/* 250:    */   {
/* 251:226 */     boolean result = true;
/* 252:    */     try
/* 253:    */     {
/* 254:228 */       result = excuteCommand("PBFT", String.format("%04.01f", new Object[] { Double.valueOf(value) }));
/* 255:    */     }
/* 256:    */     catch (Exception e)
/* 257:    */     {
/* 258:230 */       result = false;
/* 259:    */     }
/* 260:232 */     return result;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public boolean setOutputMode(int key, int mode)
/* 264:    */   {
/* 265:238 */     boolean result = true;
/* 266:    */     try
/* 267:    */     {
/* 268:240 */       result = excuteCommand("POPM", key + mode);
/* 269:    */     }
/* 270:    */     catch (Exception e)
/* 271:    */     {
/* 272:249 */       result = false;
/* 273:    */     }
/* 274:251 */     return result;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public boolean setPChargerSource(int key, String value)
/* 278:    */   {
/* 279:258 */     boolean result = true;
/* 280:    */     try
/* 281:    */     {
/* 282:260 */       result = excuteCommand("PPCP", key + value);
/* 283:    */     }
/* 284:    */     catch (Exception e)
/* 285:    */     {
/* 286:262 */       result = false;
/* 287:    */     }
/* 288:265 */     return result;
/* 289:    */   }
/* 290:    */   
/* 291:    */   public boolean setPMaxChargingCurrent(int key, int maxChargeCurrent)
/* 292:    */   {
/* 293:271 */     boolean result = true;
/* 294:    */     try
/* 295:    */     {
/* 296:273 */       result = excuteCommand("MCHGC", key + String.format("%02d", new Object[] { Integer.valueOf(maxChargeCurrent) }));
/* 297:    */     }
/* 298:    */     catch (Exception e)
/* 299:    */     {
/* 300:275 */       result = false;
/* 301:    */     }
/* 302:279 */     return result;
/* 303:    */   }
/* 304:    */   
/* 305:    */   public boolean setPMaxACChargeCurrent(int key, int maxChargeCurrent)
/* 306:    */   {
/* 307:282 */     boolean result = true;
/* 308:    */     try
/* 309:    */     {
/* 310:284 */       result = excuteCommand("MUCHGC", key + String.format("%02d", new Object[] { Integer.valueOf(maxChargeCurrent) }));
/* 311:    */     }
/* 312:    */     catch (Exception e)
/* 313:    */     {
/* 314:286 */       result = false;
/* 315:    */     }
/* 316:290 */     return result;
/* 317:    */   }
/* 318:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.control.P30ComUSBControlModule
 * JD-Core Version:    0.7.0.1
 */