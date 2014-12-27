/*   1:    */ package cn.com.voltronic.solar.data.bean;
/*   2:    */ 
/*   3:    */ import java.util.Calendar;
/*   4:    */ 
/*   5:    */ public class MachineInfo
/*   6:    */ {
/*   7:  7 */   private String serialno = "---";
/*   8:    */   private Calendar energyStartdate;
/*   9:    */   private int batteryPieceNumber;
/*  10:    */   private double batteryStandVoltage;
/*  11: 15 */   private String ioPhase = "---";
/*  12:    */   private double nominalInputVoltage;
/*  13:    */   private double nominalOutputVoltage;
/*  14:    */   private double nominalOutputFrequency;
/*  15:    */   private double nominalOutputCurrent;
/*  16:    */   private double outputPowerFactor;
/*  17:    */   private double outputRateVA;
/*  18: 29 */   private String mainFirmwareVersion = "---";
/*  19: 31 */   private String slaveFirmwareVersion = "---";
/*  20:    */   private double gridRatingVoltage;
/*  21:    */   private double gridRatingFrequency;
/*  22:    */   private double gridRatingCurrent;
/*  23:    */   private double acOutputRatingVoltage;
/*  24:    */   private double acOutputRatingFrequency;
/*  25:    */   private double acOutputRatingCurrent;
/*  26:    */   private double perMPPTRatingCurrent;
/*  27:    */   private double batteryRatingVoltage;
/*  28:    */   private double acOutputRatingApparentPower;
/*  29:    */   private double acOutputRatingActivePower;
/*  30: 54 */   private int mpptTrackNumber = 2;
/*  31: 56 */   private String machineName = "---";
/*  32: 58 */   private String machineType = "---";
/*  33: 60 */   private String topology = "---";
/*  34: 62 */   private String batteryType = "---";
/*  35: 64 */   private String inputVoltageRange = "---";
/*  36: 66 */   private String outputSource = "---";
/*  37: 68 */   private String chargeSource = "---";
/*  38:    */   
/*  39:    */   public String getBatteryType()
/*  40:    */   {
/*  41: 71 */     return this.batteryType;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public void setBatteryType(String batteryType)
/*  45:    */   {
/*  46: 75 */     this.batteryType = batteryType;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public String getInputVoltageRange()
/*  50:    */   {
/*  51: 79 */     return this.inputVoltageRange;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setInputVoltageRange(String inputVoltageRange)
/*  55:    */   {
/*  56: 83 */     this.inputVoltageRange = inputVoltageRange;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public String getOutputSource()
/*  60:    */   {
/*  61: 87 */     return this.outputSource;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setOutputSource(String outputSource)
/*  65:    */   {
/*  66: 91 */     this.outputSource = outputSource;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public String getChargeSource()
/*  70:    */   {
/*  71: 95 */     return this.chargeSource;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void setChargeSource(String chargeSource)
/*  75:    */   {
/*  76: 99 */     this.chargeSource = chargeSource;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public String getTopology()
/*  80:    */   {
/*  81:103 */     return this.topology;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void setTopology(String topology)
/*  85:    */   {
/*  86:107 */     this.topology = topology;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public int getBatteryPieceNumber()
/*  90:    */   {
/*  91:111 */     return this.batteryPieceNumber;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public void setBatteryPieceNumber(int batteryPieceNumber)
/*  95:    */   {
/*  96:115 */     this.batteryPieceNumber = batteryPieceNumber;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public double getBatteryStandVoltage()
/* 100:    */   {
/* 101:119 */     return this.batteryStandVoltage;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public void setBatteryStandVoltage(double batteryStandVoltage)
/* 105:    */   {
/* 106:123 */     this.batteryStandVoltage = batteryStandVoltage;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public String getIoPhase()
/* 110:    */   {
/* 111:127 */     return this.ioPhase;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void setIoPhase(String ioPhase)
/* 115:    */   {
/* 116:131 */     this.ioPhase = ioPhase;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public double getNominalInputVoltage()
/* 120:    */   {
/* 121:135 */     return this.nominalInputVoltage;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public void setNominalInputVoltage(double nominalInputVoltage)
/* 125:    */   {
/* 126:139 */     this.nominalInputVoltage = nominalInputVoltage;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public double getNominalOutputVoltage()
/* 130:    */   {
/* 131:143 */     return this.nominalOutputVoltage;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public void setNominalOutputVoltage(double nominalOutputVoltage)
/* 135:    */   {
/* 136:147 */     this.nominalOutputVoltage = nominalOutputVoltage;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public double getOutputPowerFactor()
/* 140:    */   {
/* 141:151 */     return this.outputPowerFactor;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public void setOutputPowerFactor(double outputPowerFactor)
/* 145:    */   {
/* 146:155 */     this.outputPowerFactor = outputPowerFactor;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public double getOutputRateVA()
/* 150:    */   {
/* 151:159 */     return this.outputRateVA;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setOutputRateVA(double outputRateVA)
/* 155:    */   {
/* 156:163 */     this.outputRateVA = outputRateVA;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public double getGridRatingVoltage()
/* 160:    */   {
/* 161:167 */     return this.gridRatingVoltage;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public void setGridRatingVoltage(double gridRatingVoltage)
/* 165:    */   {
/* 166:171 */     this.gridRatingVoltage = gridRatingVoltage;
/* 167:    */   }
/* 168:    */   
/* 169:    */   public double getGridRatingFrequency()
/* 170:    */   {
/* 171:175 */     return this.gridRatingFrequency;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void setGridRatingFrequency(double gridRatingFrequency)
/* 175:    */   {
/* 176:179 */     this.gridRatingFrequency = gridRatingFrequency;
/* 177:    */   }
/* 178:    */   
/* 179:    */   public double getGridRatingCurrent()
/* 180:    */   {
/* 181:183 */     return this.gridRatingCurrent;
/* 182:    */   }
/* 183:    */   
/* 184:    */   public void setGridRatingCurrent(double gridRatingCurrent)
/* 185:    */   {
/* 186:187 */     this.gridRatingCurrent = gridRatingCurrent;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public double getAcOutputRatingVoltage()
/* 190:    */   {
/* 191:191 */     return this.acOutputRatingVoltage;
/* 192:    */   }
/* 193:    */   
/* 194:    */   public void setAcOutputRatingVoltage(double acOutputRatingVoltage)
/* 195:    */   {
/* 196:195 */     this.acOutputRatingVoltage = acOutputRatingVoltage;
/* 197:    */   }
/* 198:    */   
/* 199:    */   public double getAcOutputRatingCurrent()
/* 200:    */   {
/* 201:199 */     return this.acOutputRatingCurrent;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setAcOutputRatingCurrent(double acOutputRatingCurrent)
/* 205:    */   {
/* 206:203 */     this.acOutputRatingCurrent = acOutputRatingCurrent;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public double getPerMPPTRatingCurrent()
/* 210:    */   {
/* 211:207 */     return this.perMPPTRatingCurrent;
/* 212:    */   }
/* 213:    */   
/* 214:    */   public void setPerMPPTRatingCurrent(double perMPPTRatingCurrent)
/* 215:    */   {
/* 216:211 */     this.perMPPTRatingCurrent = perMPPTRatingCurrent;
/* 217:    */   }
/* 218:    */   
/* 219:    */   public double getBatteryRatingVoltage()
/* 220:    */   {
/* 221:215 */     return this.batteryRatingVoltage;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public void setBatteryRatingVoltage(double batteryRatingVoltage)
/* 225:    */   {
/* 226:219 */     this.batteryRatingVoltage = batteryRatingVoltage;
/* 227:    */   }
/* 228:    */   
/* 229:    */   public int getMpptTrackNumber()
/* 230:    */   {
/* 231:223 */     return this.mpptTrackNumber;
/* 232:    */   }
/* 233:    */   
/* 234:    */   public void setMpptTrackNumber(int mpptTrackNumber)
/* 235:    */   {
/* 236:227 */     this.mpptTrackNumber = mpptTrackNumber;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public String getMachineType()
/* 240:    */   {
/* 241:231 */     return this.machineType;
/* 242:    */   }
/* 243:    */   
/* 244:    */   public void setMachineType(String machineType)
/* 245:    */   {
/* 246:235 */     this.machineType = machineType;
/* 247:    */   }
/* 248:    */   
/* 249:    */   public String getMainFirmwareVersion()
/* 250:    */   {
/* 251:239 */     return this.mainFirmwareVersion;
/* 252:    */   }
/* 253:    */   
/* 254:    */   public void setMainFirmwareVersion(String mainFirmwareVersion)
/* 255:    */   {
/* 256:243 */     this.mainFirmwareVersion = mainFirmwareVersion;
/* 257:    */   }
/* 258:    */   
/* 259:    */   public String getSlaveFirmwareVersion()
/* 260:    */   {
/* 261:247 */     return this.slaveFirmwareVersion;
/* 262:    */   }
/* 263:    */   
/* 264:    */   public void setSlaveFirmwareVersion(String slaveFirmwareVersion)
/* 265:    */   {
/* 266:251 */     this.slaveFirmwareVersion = slaveFirmwareVersion;
/* 267:    */   }
/* 268:    */   
/* 269:    */   public String getSerialno()
/* 270:    */   {
/* 271:255 */     return this.serialno;
/* 272:    */   }
/* 273:    */   
/* 274:    */   public void setSerialno(String serialno)
/* 275:    */   {
/* 276:259 */     this.serialno = serialno;
/* 277:    */   }
/* 278:    */   
/* 279:    */   public Calendar getEnergyStartdate()
/* 280:    */   {
/* 281:263 */     return this.energyStartdate;
/* 282:    */   }
/* 283:    */   
/* 284:    */   public void setEnergyStartdate(Calendar energyStartdate)
/* 285:    */   {
/* 286:267 */     this.energyStartdate = energyStartdate;
/* 287:    */   }
/* 288:    */   
/* 289:    */   public void setMachineName(String machineName)
/* 290:    */   {
/* 291:271 */     this.machineName = machineName;
/* 292:    */   }
/* 293:    */   
/* 294:    */   public String getMachineName()
/* 295:    */   {
/* 296:275 */     return this.machineName;
/* 297:    */   }
/* 298:    */   
/* 299:    */   public void setNominalOutputFrequency(double nominalOutputFrequency)
/* 300:    */   {
/* 301:279 */     this.nominalOutputFrequency = nominalOutputFrequency;
/* 302:    */   }
/* 303:    */   
/* 304:    */   public double getNominalOutputFrequency()
/* 305:    */   {
/* 306:283 */     return this.nominalOutputFrequency;
/* 307:    */   }
/* 308:    */   
/* 309:    */   public void setNominalOutputCurrent(double nominalOutputCurrent)
/* 310:    */   {
/* 311:287 */     this.nominalOutputCurrent = nominalOutputCurrent;
/* 312:    */   }
/* 313:    */   
/* 314:    */   public double getNominalOutputCurrent()
/* 315:    */   {
/* 316:291 */     return this.nominalOutputCurrent;
/* 317:    */   }
/* 318:    */   
/* 319:    */   public void setAcOutputRatingFrequency(double acOutputRatingFrequency)
/* 320:    */   {
/* 321:295 */     this.acOutputRatingFrequency = acOutputRatingFrequency;
/* 322:    */   }
/* 323:    */   
/* 324:    */   public double getAcOutputRatingFrequency()
/* 325:    */   {
/* 326:299 */     return this.acOutputRatingFrequency;
/* 327:    */   }
/* 328:    */   
/* 329:    */   public void setAcOutputRatingApparentPower(double acOutputRatingApparentPower)
/* 330:    */   {
/* 331:303 */     this.acOutputRatingApparentPower = acOutputRatingApparentPower;
/* 332:    */   }
/* 333:    */   
/* 334:    */   public double getAcOutputRatingApparentPower()
/* 335:    */   {
/* 336:307 */     return this.acOutputRatingApparentPower;
/* 337:    */   }
/* 338:    */   
/* 339:    */   public void setAcOutputRatingActivePower(double acOutputRatingActivePower)
/* 340:    */   {
/* 341:311 */     this.acOutputRatingActivePower = acOutputRatingActivePower;
/* 342:    */   }
/* 343:    */   
/* 344:    */   public double getAcOutputRatingActivePower()
/* 345:    */   {
/* 346:315 */     return this.acOutputRatingActivePower;
/* 347:    */   }
/* 348:    */   
/* 349:    */   public boolean isChargeCurrentComBox()
/* 350:    */   {
/* 351:319 */     if (((getAcOutputRatingApparentPower() == 4000.0D) || (getAcOutputRatingApparentPower() == 5000.0D)) && (
/* 352:320 */       (this.mainFirmwareVersion.compareToIgnoreCase("00052.00") >= 0) || (
/* 353:321 */       (this.mainFirmwareVersion.compareToIgnoreCase("00050.00") < 0) && 
/* 354:322 */       (this.mainFirmwareVersion.compareToIgnoreCase("00012.00") >= 0)))) {
/* 355:323 */       return true;
/* 356:    */     }
/* 357:324 */     if ((getAcOutputRatingApparentPower() == 1000.0D) || (getAcOutputRatingApparentPower() == 2000.0D) || 
/* 358:325 */       (getAcOutputRatingApparentPower() == 3000.0D))
/* 359:    */     {
/* 360:327 */       if ((this.mainFirmwareVersion.compareToIgnoreCase("00001.18") >= 0) && 
/* 361:328 */         (this.mainFirmwareVersion.compareToIgnoreCase("00010.00") < 0)) {
/* 362:329 */         return true;
/* 363:    */       }
/* 364:330 */       if (this.mainFirmwareVersion.compareToIgnoreCase("00015.33") >= 0) {
/* 365:331 */         return true;
/* 366:    */       }
/* 367:    */     }
/* 368:334 */     return false;
/* 369:    */   }
/* 370:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.MachineInfo
 * JD-Core Version:    0.7.0.1
 */