/*   1:    */ package cn.com.voltronic.solar.data.bean;
/*   2:    */ 
/*   3:    */ import java.util.Date;
/*   4:    */ 
/*   5:    */ public class DataBeforeFault
/*   6:    */ {
/*   7:  6 */   private String prodid = "";
/*   8:  7 */   private String serialno = "";
/*   9:  8 */   private String faultString = "";
/*  10:    */   private Date trandate;
/*  11:    */   private double pvinputvoltage1;
/*  12:    */   private double pvinputvoltage2;
/*  13:    */   private double pvinputvoltage3;
/*  14:    */   private double pvinputcurrent1;
/*  15:    */   private double pvinputcurrent2;
/*  16:    */   private double pvinputcurrent3;
/*  17:    */   private double inverterVoltage;
/*  18:    */   private double inverterCurrent;
/*  19:    */   private double gridVoltage;
/*  20:    */   private double gridCurrent;
/*  21:    */   private double gridFrequency;
/*  22:    */   private int outputLoadPercent;
/*  23:    */   private double outputLoadCurrent;
/*  24:    */   private double outputLoadVoltage;
/*  25:    */   private double outputLoadFrequency;
/*  26:    */   private double batteryVoltage;
/*  27:    */   private double maxTemperature;
/*  28: 27 */   private String runStatus = "";
/*  29:    */   
/*  30:    */   public String getRunStatus()
/*  31:    */   {
/*  32: 30 */     return this.runStatus;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public void setRunStatus(String runStatus)
/*  36:    */   {
/*  37: 34 */     this.runStatus = runStatus;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public String getProdid()
/*  41:    */   {
/*  42: 38 */     return this.prodid;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void setProdid(String prodid)
/*  46:    */   {
/*  47: 42 */     this.prodid = prodid;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public String getSerialno()
/*  51:    */   {
/*  52: 46 */     return this.serialno;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public void setSerialno(String serialno)
/*  56:    */   {
/*  57: 50 */     this.serialno = serialno;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public Date getTrandate()
/*  61:    */   {
/*  62: 54 */     return this.trandate;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public void setTrandate(Date trandate)
/*  66:    */   {
/*  67: 58 */     this.trandate = trandate;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public String getFaultString()
/*  71:    */   {
/*  72: 62 */     return this.faultString;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void setFaultString(String faultString)
/*  76:    */   {
/*  77: 66 */     this.faultString = faultString;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public double getPvinputvoltage1()
/*  81:    */   {
/*  82: 70 */     return this.pvinputvoltage1;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public void setPvinputvoltage1(double pvinputvoltage1)
/*  86:    */   {
/*  87: 74 */     this.pvinputvoltage1 = pvinputvoltage1;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public double getPvinputvoltage2()
/*  91:    */   {
/*  92: 78 */     return this.pvinputvoltage2;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setPvinputvoltage2(double pvinputvoltage2)
/*  96:    */   {
/*  97: 82 */     this.pvinputvoltage2 = pvinputvoltage2;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public double getPvinputvoltage3()
/* 101:    */   {
/* 102: 86 */     return this.pvinputvoltage3;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void setPvinputvoltage3(double pvinputvoltage3)
/* 106:    */   {
/* 107: 90 */     this.pvinputvoltage3 = pvinputvoltage3;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public double getPvinputcurrent1()
/* 111:    */   {
/* 112: 94 */     return this.pvinputcurrent1;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public void setPvinputcurrent1(double pvinputcurrent1)
/* 116:    */   {
/* 117: 98 */     this.pvinputcurrent1 = pvinputcurrent1;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public double getPvinputcurrent2()
/* 121:    */   {
/* 122:102 */     return this.pvinputcurrent2;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public void setPvinputcurrent2(double pvinputcurrent2)
/* 126:    */   {
/* 127:106 */     this.pvinputcurrent2 = pvinputcurrent2;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public double getPvinputcurrent3()
/* 131:    */   {
/* 132:110 */     return this.pvinputcurrent3;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public void setPvinputcurrent3(double pvinputcurrent3)
/* 136:    */   {
/* 137:114 */     this.pvinputcurrent3 = pvinputcurrent3;
/* 138:    */   }
/* 139:    */   
/* 140:    */   public double getInverterVoltage()
/* 141:    */   {
/* 142:118 */     return this.inverterVoltage;
/* 143:    */   }
/* 144:    */   
/* 145:    */   public void setInverterVoltage(double inverterVoltage)
/* 146:    */   {
/* 147:122 */     this.inverterVoltage = inverterVoltage;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public double getInverterCurrent()
/* 151:    */   {
/* 152:126 */     return this.inverterCurrent;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public void setInverterCurrent(double inverterCurrent)
/* 156:    */   {
/* 157:130 */     this.inverterCurrent = inverterCurrent;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public double getGridVoltage()
/* 161:    */   {
/* 162:134 */     return this.gridVoltage;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public void setGridVoltage(double gridVoltage)
/* 166:    */   {
/* 167:138 */     this.gridVoltage = gridVoltage;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public double getGridCurrent()
/* 171:    */   {
/* 172:142 */     return this.gridCurrent;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void setGridCurrent(double gridCurrent)
/* 176:    */   {
/* 177:146 */     this.gridCurrent = gridCurrent;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public double getGridFrequency()
/* 181:    */   {
/* 182:150 */     return this.gridFrequency;
/* 183:    */   }
/* 184:    */   
/* 185:    */   public void setGridFrequency(double gridFrequency)
/* 186:    */   {
/* 187:154 */     this.gridFrequency = gridFrequency;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public int getOutputLoadPercent()
/* 191:    */   {
/* 192:158 */     return this.outputLoadPercent;
/* 193:    */   }
/* 194:    */   
/* 195:    */   public void setOutputLoadPercent(int outputLoadPercent)
/* 196:    */   {
/* 197:162 */     this.outputLoadPercent = outputLoadPercent;
/* 198:    */   }
/* 199:    */   
/* 200:    */   public double getOutputLoadCurrent()
/* 201:    */   {
/* 202:166 */     return this.outputLoadCurrent;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public void setOutputLoadCurrent(double outputLoadCurrent)
/* 206:    */   {
/* 207:170 */     this.outputLoadCurrent = outputLoadCurrent;
/* 208:    */   }
/* 209:    */   
/* 210:    */   public double getOutputLoadVoltage()
/* 211:    */   {
/* 212:174 */     return this.outputLoadVoltage;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public void setOutputLoadVoltage(double outputLoadVoltage)
/* 216:    */   {
/* 217:178 */     this.outputLoadVoltage = outputLoadVoltage;
/* 218:    */   }
/* 219:    */   
/* 220:    */   public double getOutputLoadFrequency()
/* 221:    */   {
/* 222:182 */     return this.outputLoadFrequency;
/* 223:    */   }
/* 224:    */   
/* 225:    */   public void setOutputLoadFrequency(double outputLoadFrequency)
/* 226:    */   {
/* 227:186 */     this.outputLoadFrequency = outputLoadFrequency;
/* 228:    */   }
/* 229:    */   
/* 230:    */   public double getBatteryVoltage()
/* 231:    */   {
/* 232:190 */     return this.batteryVoltage;
/* 233:    */   }
/* 234:    */   
/* 235:    */   public void setBatteryVoltage(double batteryVoltage)
/* 236:    */   {
/* 237:194 */     this.batteryVoltage = batteryVoltage;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public double getMaxTemperature()
/* 241:    */   {
/* 242:198 */     return this.maxTemperature;
/* 243:    */   }
/* 244:    */   
/* 245:    */   public void setMaxTemperature(double maxTemperature)
/* 246:    */   {
/* 247:202 */     this.maxTemperature = maxTemperature;
/* 248:    */   }
/* 249:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.DataBeforeFault
 * JD-Core Version:    0.7.0.1
 */