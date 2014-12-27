/*   1:    */ package cn.com.voltronic.solar.data.bean;
/*   2:    */ 
/*   3:    */ public class DefaultData
/*   4:    */ {
/*   5:    */   private double acOutputVoltage;
/*   6:    */   private double acOutputFrequency;
/*   7:    */   private double maxChargingVoltage;
/*   8:    */   private double floatChargingVoltage;
/*   9:    */   private int maxACChargingCurrent;
/*  10:    */   private double batteryCutoffVoltage;
/*  11:    */   private double reChangingVoltage;
/*  12:    */   private double reDischargeVoltage;
/*  13:    */   private int maxChargingCurrent;
/*  14:    */   private String acInputVoltage;
/*  15:    */   private String outputSourcePriority;
/*  16:    */   private String chargerSourcePriority;
/*  17:    */   private String batteryType;
/*  18:    */   private String capableA;
/*  19:    */   private String capableB;
/*  20:    */   private String capableK;
/*  21:    */   private String outputMode;
/*  22:    */   private String capableJ;
/*  23:    */   private String capableU;
/*  24:    */   private String capableV;
/*  25:    */   private String capableW;
/*  26:    */   private String capableX;
/*  27:    */   private String capableY;
/*  28:    */   private String capableZ;
/*  29:    */   
/*  30:    */   public DefaultData()
/*  31:    */   {
/*  32: 64 */     this.acOutputVoltage = 230.0D;
/*  33:    */     
/*  34: 66 */     this.acOutputFrequency = 50.0D;
/*  35:    */     
/*  36: 68 */     this.maxChargingCurrent = 50;
/*  37:    */     
/*  38: 70 */     this.maxChargingVoltage = 27.0D;
/*  39:    */     
/*  40: 72 */     this.acInputVoltage = "Appliance";
/*  41:    */     
/*  42: 74 */     this.outputSourcePriority = "Utility";
/*  43:    */     
/*  44: 76 */     this.chargerSourcePriority = "Utility";
/*  45:    */     
/*  46: 78 */     this.batteryType = "AGM";
/*  47:    */     
/*  48: 80 */     this.capableA = "Enable";
/*  49:    */     
/*  50: 82 */     this.capableB = "Disable";
/*  51:    */     
/*  52: 84 */     this.capableJ = "Disable";
/*  53:    */     
/*  54: 86 */     this.capableU = "Disable";
/*  55:    */     
/*  56: 88 */     this.capableV = "Disable";
/*  57:    */     
/*  58: 90 */     this.capableW = "Disable";
/*  59:    */     
/*  60: 92 */     this.capableX = "Enable";
/*  61:    */     
/*  62: 94 */     this.capableY = "Enable";
/*  63:    */     
/*  64: 96 */     this.capableZ = "Disable";
/*  65:    */   }
/*  66:    */   
/*  67:    */   public double getAcOutputVoltage()
/*  68:    */   {
/*  69:100 */     return this.acOutputVoltage;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void setAcOutputVoltage(double acOutputVoltage)
/*  73:    */   {
/*  74:104 */     this.acOutputVoltage = acOutputVoltage;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public double getAcOutputFrequency()
/*  78:    */   {
/*  79:108 */     return this.acOutputFrequency;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void setAcOutputFrequency(double acOutputFrequency)
/*  83:    */   {
/*  84:112 */     this.acOutputFrequency = acOutputFrequency;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public int getMaxChargingCurrent()
/*  88:    */   {
/*  89:116 */     return this.maxChargingCurrent;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public void setMaxChargingCurrent(int maxChargingCurrent)
/*  93:    */   {
/*  94:120 */     this.maxChargingCurrent = maxChargingCurrent;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public String getAcInputVoltage()
/*  98:    */   {
/*  99:124 */     return this.acInputVoltage;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void setAcInputVoltage(String acInputVoltage)
/* 103:    */   {
/* 104:128 */     this.acInputVoltage = acInputVoltage;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public String getOutputSourcePriority()
/* 108:    */   {
/* 109:132 */     return this.outputSourcePriority;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void setOutputSourcePriority(String outputSourcePriority)
/* 113:    */   {
/* 114:136 */     this.outputSourcePriority = outputSourcePriority;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public String getChargerSourcePriority()
/* 118:    */   {
/* 119:140 */     return this.chargerSourcePriority;
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void setChargerSourcePriority(String chargerSourcePriority)
/* 123:    */   {
/* 124:144 */     this.chargerSourcePriority = chargerSourcePriority;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public String getBatteryType()
/* 128:    */   {
/* 129:148 */     return this.batteryType;
/* 130:    */   }
/* 131:    */   
/* 132:    */   public void setBatteryType(String batteryType)
/* 133:    */   {
/* 134:152 */     this.batteryType = batteryType;
/* 135:    */   }
/* 136:    */   
/* 137:    */   public String getCapableA()
/* 138:    */   {
/* 139:156 */     return this.capableA;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public void setCapableA(String capableA)
/* 143:    */   {
/* 144:160 */     this.capableA = capableA;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public String getCapableB()
/* 148:    */   {
/* 149:164 */     return this.capableB;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void setCapableB(String capableB)
/* 153:    */   {
/* 154:168 */     this.capableB = capableB;
/* 155:    */   }
/* 156:    */   
/* 157:    */   public String getCapableU()
/* 158:    */   {
/* 159:172 */     return this.capableU;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public void setCapableU(String capableU)
/* 163:    */   {
/* 164:176 */     this.capableU = capableU;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public String getCapableV()
/* 168:    */   {
/* 169:180 */     return this.capableV;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public void setCapableV(String capableV)
/* 173:    */   {
/* 174:184 */     this.capableV = capableV;
/* 175:    */   }
/* 176:    */   
/* 177:    */   public String getCapableW()
/* 178:    */   {
/* 179:188 */     return this.capableW;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public void setCapableW(String capableW)
/* 183:    */   {
/* 184:192 */     this.capableW = capableW;
/* 185:    */   }
/* 186:    */   
/* 187:    */   public String getCapableX()
/* 188:    */   {
/* 189:196 */     return this.capableX;
/* 190:    */   }
/* 191:    */   
/* 192:    */   public void setCapableX(String capableX)
/* 193:    */   {
/* 194:200 */     this.capableX = capableX;
/* 195:    */   }
/* 196:    */   
/* 197:    */   public String getCapableY()
/* 198:    */   {
/* 199:204 */     return this.capableY;
/* 200:    */   }
/* 201:    */   
/* 202:    */   public void setCapableY(String capableY)
/* 203:    */   {
/* 204:208 */     this.capableY = capableY;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public String getCapableZ()
/* 208:    */   {
/* 209:212 */     return this.capableZ;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public void setCapableZ(String capableZ)
/* 213:    */   {
/* 214:216 */     this.capableZ = capableZ;
/* 215:    */   }
/* 216:    */   
/* 217:    */   public void setCapableJ(String capableJ)
/* 218:    */   {
/* 219:220 */     this.capableJ = capableJ;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public String getCapableJ()
/* 223:    */   {
/* 224:224 */     return this.capableJ;
/* 225:    */   }
/* 226:    */   
/* 227:    */   public void setMaxChargingVoltage(double maxChargingVoltage)
/* 228:    */   {
/* 229:228 */     this.maxChargingVoltage = maxChargingVoltage;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public double getMaxChargingVoltage()
/* 233:    */   {
/* 234:232 */     return this.maxChargingVoltage;
/* 235:    */   }
/* 236:    */   
/* 237:    */   public double getReChangingVoltage()
/* 238:    */   {
/* 239:235 */     return this.reChangingVoltage;
/* 240:    */   }
/* 241:    */   
/* 242:    */   public void setReChangingVoltage(double reChangingVoltage)
/* 243:    */   {
/* 244:239 */     this.reChangingVoltage = reChangingVoltage;
/* 245:    */   }
/* 246:    */   
/* 247:    */   public double getFloatChargingVoltage()
/* 248:    */   {
/* 249:243 */     return this.floatChargingVoltage;
/* 250:    */   }
/* 251:    */   
/* 252:    */   public void setFloatChargingVoltage(double floatChargingVoltage)
/* 253:    */   {
/* 254:247 */     this.floatChargingVoltage = floatChargingVoltage;
/* 255:    */   }
/* 256:    */   
/* 257:    */   public int getMaxACChargingCurrent()
/* 258:    */   {
/* 259:251 */     return this.maxACChargingCurrent;
/* 260:    */   }
/* 261:    */   
/* 262:    */   public void setMaxACChargingCurrent(int maxACChargingCurrent)
/* 263:    */   {
/* 264:255 */     this.maxACChargingCurrent = maxACChargingCurrent;
/* 265:    */   }
/* 266:    */   
/* 267:    */   public double getBatteryCutoffVoltage()
/* 268:    */   {
/* 269:259 */     return this.batteryCutoffVoltage;
/* 270:    */   }
/* 271:    */   
/* 272:    */   public void setBatteryCutoffVoltage(double batteryCutoffVoltage)
/* 273:    */   {
/* 274:263 */     this.batteryCutoffVoltage = batteryCutoffVoltage;
/* 275:    */   }
/* 276:    */   
/* 277:    */   public String getCapableK()
/* 278:    */   {
/* 279:267 */     return this.capableK;
/* 280:    */   }
/* 281:    */   
/* 282:    */   public void setCapableK(String capableK)
/* 283:    */   {
/* 284:271 */     this.capableK = capableK;
/* 285:    */   }
/* 286:    */   
/* 287:    */   public String getOutputMode()
/* 288:    */   {
/* 289:275 */     return this.outputMode;
/* 290:    */   }
/* 291:    */   
/* 292:    */   public void setOutputMode(String outputMode)
/* 293:    */   {
/* 294:279 */     this.outputMode = outputMode;
/* 295:    */   }
/* 296:    */   
/* 297:    */   public double getReDischargeVoltage()
/* 298:    */   {
/* 299:283 */     return this.reDischargeVoltage;
/* 300:    */   }
/* 301:    */   
/* 302:    */   public void setReDischargeVoltage(double reDischargeVoltage)
/* 303:    */   {
/* 304:287 */     this.reDischargeVoltage = reDischargeVoltage;
/* 305:    */   }
/* 306:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.DefaultData
 * JD-Core Version:    0.7.0.1
 */