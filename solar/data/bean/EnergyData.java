/*   1:    */ package cn.com.voltronic.solar.data.bean;
/*   2:    */ 
/*   3:    */ import java.util.ArrayList;
/*   4:    */ import java.util.List;
/*   5:    */ 
/*   6:    */ public class EnergyData
/*   7:    */ {
/*   8: 17 */   private double energyInY = 0.0D;
/*   9: 22 */   private double energyInM = 0.0D;
/*  10: 27 */   private double energyInD = 0.0D;
/*  11: 32 */   private double energyInH = 0.0D;
/*  12: 34 */   private String horizontalTitleH = "";
/*  13: 36 */   private String horizontalTitleD = "";
/*  14: 38 */   private String horizontalTitleM = "";
/*  15: 40 */   private String horizontalTitleY = "";
/*  16: 42 */   private double maxH = 100.0D;
/*  17: 43 */   private double maxD = 100.0D;
/*  18: 44 */   private double maxM = 100.0D;
/*  19: 45 */   private double maxY = 100.0D;
/*  20:    */   private List<ColumnChartItem> energyHours;
/*  21:    */   private List<ColumnChartItem> energyDays;
/*  22:    */   private List<ColumnChartItem> energyMonths;
/*  23:    */   private List<ColumnChartItem> energyYears;
/*  24:    */   
/*  25:    */   public EnergyData()
/*  26:    */   {
/*  27: 53 */     this.energyHours = new ArrayList();
/*  28: 54 */     this.energyDays = new ArrayList();
/*  29: 55 */     this.energyMonths = new ArrayList();
/*  30: 56 */     this.energyYears = new ArrayList();
/*  31:    */   }
/*  32:    */   
/*  33:    */   public List<ColumnChartItem> getEnergyHours()
/*  34:    */   {
/*  35: 60 */     return this.energyHours;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public void setEnergyHours(List<ColumnChartItem> energyHours)
/*  39:    */   {
/*  40: 63 */     this.energyHours = energyHours;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public List<ColumnChartItem> getEnergyDays()
/*  44:    */   {
/*  45: 66 */     return this.energyDays;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void setEnergyDays(List<ColumnChartItem> energyDays)
/*  49:    */   {
/*  50: 69 */     this.energyDays = energyDays;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public List<ColumnChartItem> getEnergyMonths()
/*  54:    */   {
/*  55: 72 */     return this.energyMonths;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public void setEnergyMonths(List<ColumnChartItem> energyMonths)
/*  59:    */   {
/*  60: 75 */     this.energyMonths = energyMonths;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public List<ColumnChartItem> getEnergyYears()
/*  64:    */   {
/*  65: 78 */     return this.energyYears;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void setEnergyYears(List<ColumnChartItem> energyYears)
/*  69:    */   {
/*  70: 81 */     this.energyYears = energyYears;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public double getEnergyInY()
/*  74:    */   {
/*  75: 85 */     return this.energyInY;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void setEnergyInY(double energyInY)
/*  79:    */   {
/*  80: 89 */     this.energyInY = energyInY;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public double getEnergyInM()
/*  84:    */   {
/*  85: 93 */     return this.energyInM;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void setEnergyInM(double energyInM)
/*  89:    */   {
/*  90: 97 */     this.energyInM = energyInM;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public double getEnergyInD()
/*  94:    */   {
/*  95:101 */     return this.energyInD;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void setEnergyInD(double energyInD)
/*  99:    */   {
/* 100:105 */     this.energyInD = energyInD;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public double getEnergyInH()
/* 104:    */   {
/* 105:109 */     return this.energyInH;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void setEnergyInH(double energyInH)
/* 109:    */   {
/* 110:113 */     this.energyInH = energyInH;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public String getHorizontalTitleH()
/* 114:    */   {
/* 115:117 */     return this.horizontalTitleH;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void setHorizontalTitleH(String horizontalTitleH)
/* 119:    */   {
/* 120:121 */     this.horizontalTitleH = horizontalTitleH;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public String getHorizontalTitleD()
/* 124:    */   {
/* 125:125 */     return this.horizontalTitleD;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public void setHorizontalTitleD(String horizontalTitleD)
/* 129:    */   {
/* 130:129 */     this.horizontalTitleD = horizontalTitleD;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public String getHorizontalTitleM()
/* 134:    */   {
/* 135:133 */     return this.horizontalTitleM;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public void setHorizontalTitleM(String horizontalTitleM)
/* 139:    */   {
/* 140:137 */     this.horizontalTitleM = horizontalTitleM;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public String getHorizontalTitleY()
/* 144:    */   {
/* 145:141 */     return this.horizontalTitleY;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public void setHorizontalTitleY(String horizontalTitleY)
/* 149:    */   {
/* 150:145 */     this.horizontalTitleY = horizontalTitleY;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public double getMaxH()
/* 154:    */   {
/* 155:149 */     return this.maxH;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public void setMaxH(double maxH)
/* 159:    */   {
/* 160:153 */     this.maxH = maxH;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public double getMaxD()
/* 164:    */   {
/* 165:157 */     return this.maxD;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public void setMaxD(double maxD)
/* 169:    */   {
/* 170:161 */     this.maxD = maxD;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public double getMaxM()
/* 174:    */   {
/* 175:165 */     return this.maxM;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void setMaxM(double maxM)
/* 179:    */   {
/* 180:169 */     this.maxM = maxM;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public double getMaxY()
/* 184:    */   {
/* 185:173 */     return this.maxY;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public void setMaxY(double maxY)
/* 189:    */   {
/* 190:177 */     this.maxY = maxY;
/* 191:    */   }
/* 192:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.EnergyData
 * JD-Core Version:    0.7.0.1
 */