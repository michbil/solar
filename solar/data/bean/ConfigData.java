/*   1:    */ package cn.com.voltronic.solar.data.bean;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.HashMap;
/*   6:    */ 
/*   7:    */ public class ConfigData
/*   8:    */ {
/*   9:    */   public static final String OUTPUT_PARENT = "SELF";
/*  10:    */   private String outputSource;
/*  11:    */   private String outputFrequency;
/*  12:    */   private String outputVoltage;
/*  13:    */   private String acInputRange;
/*  14:    */   private String batteryType;
/*  15:    */   private double batteryVoltage;
/*  16:    */   private String chargerSource;
/*  17:    */   private double maxChargeCurrent;
/*  18:    */   private double maxChargeVoltage;
/*  19:    */   private double minMaxChargeVoltage;
/*  20:    */   private double maxMaxChargeVoltage;
/*  21:    */   private double floatingChargeVoltage;
/*  22:    */   private double minFloatingChargeVoltage;
/*  23:    */   private double maxFloatingChargeVoltage;
/*  24:    */   private double minMaxChargeCurrent;
/*  25:    */   private double maxMaxChargeCurrent;
/*  26:    */   private double batteryLowAlarm;
/*  27:    */   private double minBatteryLowAlarm;
/*  28:    */   private double maxBatteryLowAlarm;
/*  29:    */   private double batteryUnder;
/*  30:    */   private double minBatteryUnder;
/*  31:    */   private double maxBatteryUnder;
/*  32:    */   private double rechargeVoltage;
/*  33:    */   private String reDischargeVoltage;
/*  34: 63 */   private String outputKey = "";
/*  35: 67 */   private int outputMode = 0;
/*  36: 69 */   private String[] chargingCurrentComBox = null;
/*  37: 71 */   private String[] acChargingCurrentCombox = null;
/*  38:    */   private double maxacchargingcurrent;
/*  39: 76 */   private HashMap<String, ParanellSubConfig> suboutputModeMap = new HashMap();
/*  40:    */   
/*  41:    */   public void setSubOutputMode(String serialno, int value)
/*  42:    */   {
/*  43: 79 */     ParanellSubConfig subConfig = null;
/*  44: 80 */     if (this.suboutputModeMap.containsKey(serialno))
/*  45:    */     {
/*  46: 81 */       subConfig = (ParanellSubConfig)this.suboutputModeMap.get(serialno);
/*  47:    */     }
/*  48:    */     else
/*  49:    */     {
/*  50: 83 */       subConfig = new ParanellSubConfig();
/*  51: 84 */       this.suboutputModeMap.put(serialno, subConfig);
/*  52:    */     }
/*  53: 86 */     subConfig.setOutputMode(value);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void setChargingCurrentComBox(String[] comboxList)
/*  57:    */   {
/*  58: 90 */     this.chargingCurrentComBox = comboxList;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void setAcChargingCurrentComBox(String[] comboxList)
/*  62:    */   {
/*  63: 93 */     this.acChargingCurrentCombox = comboxList;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public String[] getAcChargingCurrentComBox()
/*  67:    */   {
/*  68: 97 */     return this.acChargingCurrentCombox;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public void setOutputMode(int value)
/*  72:    */   {
/*  73:101 */     this.outputMode = value;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void setCurrentKey(String serialno)
/*  77:    */   {
/*  78:105 */     this.outputKey = serialno;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public int getOutputMode()
/*  82:    */   {
/*  83:109 */     if (this.outputKey == "SELF") {
/*  84:110 */       return this.outputMode;
/*  85:    */     }
/*  86:111 */     if (this.suboutputModeMap.containsKey(this.outputKey)) {
/*  87:112 */       return ((ParanellSubConfig)this.suboutputModeMap.get(this.outputKey)).getOutputMode();
/*  88:    */     }
/*  89:114 */     return 0;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public double getRechargeVoltage()
/*  93:    */   {
/*  94:119 */     return this.rechargeVoltage;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void setRechargeVoltage(double rechargeVoltage)
/*  98:    */   {
/*  99:123 */     this.rechargeVoltage = rechargeVoltage;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public String getOutputSource()
/* 103:    */   {
/* 104:127 */     return this.outputSource;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void setOutputSource(String outputSource)
/* 108:    */   {
/* 109:131 */     this.outputSource = outputSource;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public String getOutputFrequency()
/* 113:    */   {
/* 114:135 */     return this.outputFrequency;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public void setOutputFrequency(String outputFrequency)
/* 118:    */   {
/* 119:139 */     this.outputFrequency = outputFrequency;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public String getOutputVoltage()
/* 123:    */   {
/* 124:143 */     return this.outputVoltage;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public void setOutputVoltage(String outputVoltage)
/* 128:    */   {
/* 129:147 */     this.outputVoltage = outputVoltage;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public String getAcInputRange()
/* 133:    */   {
/* 134:151 */     return this.acInputRange;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public void setAcInputRange(String acInputRange)
/* 138:    */   {
/* 139:155 */     this.acInputRange = acInputRange;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public String getBatteryType()
/* 143:    */   {
/* 144:159 */     return this.batteryType;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public void setBatteryType(String batteryType)
/* 148:    */   {
/* 149:163 */     this.batteryType = batteryType;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public String getChargerSource()
/* 153:    */   {
/* 154:167 */     if (this.outputKey == "SELF") {
/* 155:168 */       return this.chargerSource;
/* 156:    */     }
/* 157:169 */     if (this.suboutputModeMap.containsKey(this.outputKey)) {
/* 158:170 */       return ((ParanellSubConfig)this.suboutputModeMap.get(this.outputKey)).getChargingsource();
/* 159:    */     }
/* 160:172 */     return "Utility";
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void setChargerSource(String chargerSource)
/* 164:    */   {
/* 165:176 */     this.chargerSource = chargerSource;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public void setChargerSource(String serialno, String chargerSource)
/* 169:    */   {
/* 170:179 */     ParanellSubConfig subConfig = null;
/* 171:180 */     if (this.suboutputModeMap.containsKey(serialno))
/* 172:    */     {
/* 173:181 */       subConfig = (ParanellSubConfig)this.suboutputModeMap.get(serialno);
/* 174:    */     }
/* 175:    */     else
/* 176:    */     {
/* 177:183 */       subConfig = new ParanellSubConfig();
/* 178:184 */       this.suboutputModeMap.put(serialno, subConfig);
/* 179:    */     }
/* 180:186 */     subConfig.setChargingsource(chargerSource);
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void setMaxacchargingcurrent(double maxacchargingcurrent)
/* 184:    */   {
/* 185:190 */     this.maxacchargingcurrent = maxacchargingcurrent;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void setMaxacchargingcurrent(String serialno, double maxacchargingcurrent)
/* 189:    */   {
/* 190:194 */     ParanellSubConfig subConfig = null;
/* 191:195 */     if (this.suboutputModeMap.containsKey(serialno))
/* 192:    */     {
/* 193:196 */       subConfig = (ParanellSubConfig)this.suboutputModeMap.get(serialno);
/* 194:    */     }
/* 195:    */     else
/* 196:    */     {
/* 197:198 */       subConfig = new ParanellSubConfig();
/* 198:199 */       this.suboutputModeMap.put(serialno, subConfig);
/* 199:    */     }
/* 200:201 */     subConfig.setMaxacchargingcurrent(maxacchargingcurrent);
/* 201:    */   }
/* 202:    */   
/* 203:    */   public double getMaxacchargingcurrent()
/* 204:    */   {
/* 205:205 */     if (this.outputKey == "SELF") {
/* 206:206 */       return this.maxacchargingcurrent;
/* 207:    */     }
/* 208:207 */     if (this.suboutputModeMap.containsKey(this.outputKey)) {
/* 209:208 */       return ((ParanellSubConfig)this.suboutputModeMap.get(this.outputKey)).getMaxacchargingcurrent();
/* 210:    */     }
/* 211:210 */     return 0.0D;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public double getMaxChargeCurrent()
/* 215:    */   {
/* 216:215 */     if (this.outputKey == "SELF") {
/* 217:216 */       return this.maxChargeCurrent;
/* 218:    */     }
/* 219:217 */     if (this.suboutputModeMap.containsKey(this.outputKey)) {
/* 220:218 */       return ((ParanellSubConfig)this.suboutputModeMap.get(this.outputKey)).getMaxcharngingcurrent();
/* 221:    */     }
/* 222:220 */     return 0.0D;
/* 223:    */   }
/* 224:    */   
/* 225:    */   public void setMaxChargeCurrent(double maxChargeCurrent)
/* 226:    */   {
/* 227:224 */     this.maxChargeCurrent = maxChargeCurrent;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public void setMaxChargeCurrent(String serialno, double maxChargeCurrent)
/* 231:    */   {
/* 232:228 */     ParanellSubConfig subConfig = null;
/* 233:229 */     if (this.suboutputModeMap.containsKey(serialno))
/* 234:    */     {
/* 235:230 */       subConfig = (ParanellSubConfig)this.suboutputModeMap.get(serialno);
/* 236:    */     }
/* 237:    */     else
/* 238:    */     {
/* 239:232 */       subConfig = new ParanellSubConfig();
/* 240:233 */       this.suboutputModeMap.put(serialno, subConfig);
/* 241:    */     }
/* 242:235 */     subConfig.setMaxcharngingcurrent(maxChargeCurrent);
/* 243:    */   }
/* 244:    */   
/* 245:    */   public double getMinMaxChargeCurrent()
/* 246:    */   {
/* 247:242 */     if (this.outputKey == "SELF") {
/* 248:243 */       return this.minMaxChargeCurrent;
/* 249:    */     }
/* 250:244 */     if (this.suboutputModeMap.containsKey(this.outputKey))
/* 251:    */     {
/* 252:245 */       System.out.println("outputKey=" + this.outputKey + " " + ((ParanellSubConfig)this.suboutputModeMap.get(this.outputKey)).getMinmaxchargingcurrent());
/* 253:246 */       return ((ParanellSubConfig)this.suboutputModeMap.get(this.outputKey)).getMinmaxchargingcurrent();
/* 254:    */     }
/* 255:248 */     return 0.0D;
/* 256:    */   }
/* 257:    */   
/* 258:    */   public void setMinMaxChargeCurrent(double minMaxChargeCurrent)
/* 259:    */   {
/* 260:252 */     this.minMaxChargeCurrent = minMaxChargeCurrent;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void setMinMaxChargeCurrent(String serialno, double minMaxChargeCurrent)
/* 264:    */   {
/* 265:256 */     ParanellSubConfig subConfig = null;
/* 266:257 */     if (this.suboutputModeMap.containsKey(serialno))
/* 267:    */     {
/* 268:258 */       subConfig = (ParanellSubConfig)this.suboutputModeMap.get(serialno);
/* 269:    */     }
/* 270:    */     else
/* 271:    */     {
/* 272:260 */       subConfig = new ParanellSubConfig();
/* 273:261 */       this.suboutputModeMap.put(serialno, subConfig);
/* 274:    */     }
/* 275:263 */     subConfig.setMinmaxchargingcurrent(minMaxChargeCurrent);
/* 276:    */   }
/* 277:    */   
/* 278:    */   public double getMaxMaxChargeCurrent()
/* 279:    */   {
/* 280:267 */     if (this.outputKey == "SELF") {
/* 281:268 */       return this.maxMaxChargeCurrent;
/* 282:    */     }
/* 283:269 */     if (this.suboutputModeMap.containsKey(this.outputKey)) {
/* 284:270 */       return ((ParanellSubConfig)this.suboutputModeMap.get(this.outputKey)).getMaxmaxchargingcurrent();
/* 285:    */     }
/* 286:272 */     return 50.0D;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public String[] getMaxChargeCurrentCombox(MachineInfo machine, int outputmode)
/* 290:    */   {
/* 291:277 */     if ((outputmode == 0) && 
/* 292:278 */       (machine.isChargeCurrentComBox())) {
/* 293:279 */       return this.chargingCurrentComBox;
/* 294:    */     }
/* 295:283 */     ArrayList<String> list = new ArrayList();
/* 296:284 */     double dbMaxMaxChargeCurrent = getMaxMaxChargeCurrent();
/* 297:285 */     double step = dbMaxMaxChargeCurrent - getMinMaxChargeCurrent();
/* 298:288 */     if (step > 10.0D) {
/* 299:289 */       step = 10.0D;
/* 300:    */     }
/* 301:291 */     int temp = (int)getMinMaxChargeCurrent();
/* 302:294 */     while (temp < (int)dbMaxMaxChargeCurrent)
/* 303:    */     {
/* 304:296 */       list.add(temp);
/* 305:297 */       temp = (int)(temp + step);
/* 306:    */     }
/* 307:299 */     list.add((int)dbMaxMaxChargeCurrent);
/* 308:    */     
/* 309:301 */     String[] result = new String[list.size()];
/* 310:302 */     list.toArray(result);
/* 311:    */     
/* 312:304 */     return result;
/* 313:    */   }
/* 314:    */   
/* 315:    */   public void setMaxMaxChargeCurrent(double maxMaxChargeCurrent)
/* 316:    */   {
/* 317:309 */     this.maxMaxChargeCurrent = maxMaxChargeCurrent;
/* 318:    */   }
/* 319:    */   
/* 320:    */   public void setMaxMaxChargeCurrent(String serialno, double maxMaxChargeCurrent)
/* 321:    */   {
/* 322:312 */     ParanellSubConfig subConfig = null;
/* 323:313 */     if (this.suboutputModeMap.containsKey(serialno))
/* 324:    */     {
/* 325:314 */       subConfig = (ParanellSubConfig)this.suboutputModeMap.get(serialno);
/* 326:    */     }
/* 327:    */     else
/* 328:    */     {
/* 329:316 */       subConfig = new ParanellSubConfig();
/* 330:317 */       this.suboutputModeMap.put(serialno, subConfig);
/* 331:    */     }
/* 332:319 */     subConfig.setMaxmaxchargingcurrent(maxMaxChargeCurrent);
/* 333:    */   }
/* 334:    */   
/* 335:    */   public double getBatteryLowAlarm()
/* 336:    */   {
/* 337:323 */     return this.batteryLowAlarm;
/* 338:    */   }
/* 339:    */   
/* 340:    */   public void setBatteryLowAlarm(double batteryLowAlarm)
/* 341:    */   {
/* 342:327 */     this.batteryLowAlarm = batteryLowAlarm;
/* 343:    */   }
/* 344:    */   
/* 345:    */   public double getMinBatteryLowAlarm()
/* 346:    */   {
/* 347:331 */     return this.minBatteryLowAlarm;
/* 348:    */   }
/* 349:    */   
/* 350:    */   public void setMinBatteryLowAlarm(double minBatteryLowAlarm)
/* 351:    */   {
/* 352:335 */     this.minBatteryLowAlarm = minBatteryLowAlarm;
/* 353:    */   }
/* 354:    */   
/* 355:    */   public double getMaxBatteryLowAlarm()
/* 356:    */   {
/* 357:339 */     return this.maxBatteryLowAlarm;
/* 358:    */   }
/* 359:    */   
/* 360:    */   public void setMaxBatteryLowAlarm(double maxBatteryLowAlarm)
/* 361:    */   {
/* 362:343 */     this.maxBatteryLowAlarm = maxBatteryLowAlarm;
/* 363:    */   }
/* 364:    */   
/* 365:    */   public double getBatteryUnder()
/* 366:    */   {
/* 367:347 */     return this.batteryUnder;
/* 368:    */   }
/* 369:    */   
/* 370:    */   public void setBatteryUnder(double batteryUnder)
/* 371:    */   {
/* 372:351 */     this.batteryUnder = batteryUnder;
/* 373:    */   }
/* 374:    */   
/* 375:    */   public double getMinBatteryUnder()
/* 376:    */   {
/* 377:355 */     return this.minBatteryUnder;
/* 378:    */   }
/* 379:    */   
/* 380:    */   public void setMinBatteryUnder(double minBatteryUnder)
/* 381:    */   {
/* 382:359 */     this.minBatteryUnder = minBatteryUnder;
/* 383:    */   }
/* 384:    */   
/* 385:    */   public double getMaxBatteryUnder()
/* 386:    */   {
/* 387:363 */     return this.maxBatteryUnder;
/* 388:    */   }
/* 389:    */   
/* 390:    */   public void setMaxBatteryUnder(double maxBatteryUnder)
/* 391:    */   {
/* 392:367 */     this.maxBatteryUnder = maxBatteryUnder;
/* 393:    */   }
/* 394:    */   
/* 395:    */   public void setMaxChargeVoltage(double maxChargeVoltage)
/* 396:    */   {
/* 397:371 */     this.maxChargeVoltage = maxChargeVoltage;
/* 398:    */   }
/* 399:    */   
/* 400:    */   public double getMaxChargeVoltage()
/* 401:    */   {
/* 402:375 */     return this.maxChargeVoltage;
/* 403:    */   }
/* 404:    */   
/* 405:    */   public void setMinMaxChargeVoltage(double minMaxChargeVoltage)
/* 406:    */   {
/* 407:379 */     this.minMaxChargeVoltage = minMaxChargeVoltage;
/* 408:    */   }
/* 409:    */   
/* 410:    */   public double getMinMaxChargeVoltage()
/* 411:    */   {
/* 412:383 */     return this.minMaxChargeVoltage;
/* 413:    */   }
/* 414:    */   
/* 415:    */   public int getMaxChargeCurrentInt()
/* 416:    */   {
/* 417:387 */     return (int)getMaxChargeCurrent();
/* 418:    */   }
/* 419:    */   
/* 420:    */   public void setMaxMaxChargeVoltage(double maxMaxChargeVoltage)
/* 421:    */   {
/* 422:391 */     this.maxMaxChargeVoltage = maxMaxChargeVoltage;
/* 423:    */   }
/* 424:    */   
/* 425:    */   public int getMaxacchargingcurrentInt()
/* 426:    */   {
/* 427:396 */     return (int)getMaxacchargingcurrent();
/* 428:    */   }
/* 429:    */   
/* 430:    */   public double getMaxMaxChargeVoltage()
/* 431:    */   {
/* 432:401 */     return this.maxMaxChargeVoltage;
/* 433:    */   }
/* 434:    */   
/* 435:    */   public double getBatteryVoltage()
/* 436:    */   {
/* 437:405 */     return this.batteryVoltage;
/* 438:    */   }
/* 439:    */   
/* 440:    */   public void setBatteryVoltage(double batteryVoltage)
/* 441:    */   {
/* 442:409 */     this.batteryVoltage = batteryVoltage;
/* 443:    */   }
/* 444:    */   
/* 445:    */   public double getFloatingChargeVoltage()
/* 446:    */   {
/* 447:413 */     return this.floatingChargeVoltage;
/* 448:    */   }
/* 449:    */   
/* 450:    */   public void setFloatingChargeVoltage(double floatingChargeVoltage)
/* 451:    */   {
/* 452:417 */     this.floatingChargeVoltage = floatingChargeVoltage;
/* 453:    */   }
/* 454:    */   
/* 455:    */   public double getMinFloatingChargeVoltage()
/* 456:    */   {
/* 457:421 */     return this.minFloatingChargeVoltage;
/* 458:    */   }
/* 459:    */   
/* 460:    */   public void setMinFloatingChargeVoltage(double minFloatingChargeVoltage)
/* 461:    */   {
/* 462:425 */     this.minFloatingChargeVoltage = minFloatingChargeVoltage;
/* 463:    */   }
/* 464:    */   
/* 465:    */   public double getMaxFloatingChargeVoltage()
/* 466:    */   {
/* 467:429 */     return this.maxFloatingChargeVoltage;
/* 468:    */   }
/* 469:    */   
/* 470:    */   public void setMaxFloatingChargeVoltage(double maxFloatingChargeVoltage)
/* 471:    */   {
/* 472:433 */     this.maxFloatingChargeVoltage = maxFloatingChargeVoltage;
/* 473:    */   }
/* 474:    */   
/* 475:    */   public String getReDischargeVoltage()
/* 476:    */   {
/* 477:439 */     return this.reDischargeVoltage;
/* 478:    */   }
/* 479:    */   
/* 480:    */   public void setReDischargeVoltage(String reDischargeVoltage)
/* 481:    */   {
/* 482:443 */     this.reDischargeVoltage = reDischargeVoltage;
/* 483:    */   }
/* 484:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.ConfigData
 * JD-Core Version:    0.7.0.1
 */