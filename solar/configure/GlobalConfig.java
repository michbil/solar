/*   1:    */ package cn.com.voltronic.solar.configure;
/*   2:    */ 
/*   3:    */ public class GlobalConfig
/*   4:    */   implements IConfigBean
/*   5:    */ {
/*   6: 16 */   private String commPort = "COM1";
/*   7: 21 */   private int pageRefreshInterval = 2;
/*   8: 26 */   private int powerRefreshInterval = 10;
/*   9: 31 */   private int deviceSacanInterval = 5;
/*  10: 36 */   private int dataRecordInterval = 60;
/*  11: 41 */   private int showWarningForDatetime = 5;
/*  12: 46 */   private String dateFormat = "yyyy-MM-dd";
/*  13: 51 */   private String temperFormat = "Centigrade";
/*  14: 56 */   private String language = "en_US";
/*  15: 58 */   private String udpPort = "38694";
/*  16: 60 */   private boolean useUSB = true;
/*  17: 62 */   private boolean useCOM = true;
/*  18: 64 */   private boolean useSNMP = false;
/*  19: 66 */   private boolean useModbus = false;
/*  20:    */   
/*  21:    */   public String getCommPort()
/*  22:    */   {
/*  23: 69 */     return this.commPort;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public void setCommPort(String commPort)
/*  27:    */   {
/*  28: 73 */     this.commPort = commPort;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public int getPageRefreshInterval()
/*  32:    */   {
/*  33: 77 */     return this.pageRefreshInterval;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public void setPageRefreshInterval(int pageRefreshInterval)
/*  37:    */   {
/*  38: 81 */     this.pageRefreshInterval = pageRefreshInterval;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public int getDataRecordInterval()
/*  42:    */   {
/*  43: 85 */     return this.dataRecordInterval;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void setDataRecordInterval(int dataRecordInterval)
/*  47:    */   {
/*  48: 89 */     this.dataRecordInterval = dataRecordInterval;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public int getPowerRefreshInterval()
/*  52:    */   {
/*  53: 93 */     return this.powerRefreshInterval;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void setPowerRefreshInterval(int powerRefreshInterval)
/*  57:    */   {
/*  58: 97 */     this.powerRefreshInterval = powerRefreshInterval;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public int getDeviceSacanInterval()
/*  62:    */   {
/*  63:101 */     return this.deviceSacanInterval;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void setDeviceSacanInterval(int deviceSacanInterval)
/*  67:    */   {
/*  68:105 */     this.deviceSacanInterval = deviceSacanInterval;
/*  69:    */   }
/*  70:    */   
/*  71:    */   public int getShowWarningForDatetime()
/*  72:    */   {
/*  73:109 */     return this.showWarningForDatetime;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public void setShowWarningForDatetime(int showWarningForDatetime)
/*  77:    */   {
/*  78:113 */     this.showWarningForDatetime = showWarningForDatetime;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public String getDateFormat()
/*  82:    */   {
/*  83:117 */     return this.dateFormat;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void setDateFormat(String dateFormat)
/*  87:    */   {
/*  88:121 */     this.dateFormat = dateFormat;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public String getTemperFormat()
/*  92:    */   {
/*  93:125 */     return this.temperFormat;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void setTemperFormat(String temperFormat)
/*  97:    */   {
/*  98:129 */     this.temperFormat = temperFormat;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public boolean isUseUSB()
/* 102:    */   {
/* 103:133 */     return this.useUSB;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void setUseUSB(boolean useUSB)
/* 107:    */   {
/* 108:137 */     this.useUSB = useUSB;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public boolean isUseCOM()
/* 112:    */   {
/* 113:141 */     return this.useCOM;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public void setUseCOM(boolean useCOM)
/* 117:    */   {
/* 118:145 */     this.useCOM = useCOM;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public boolean isUseSNMP()
/* 122:    */   {
/* 123:149 */     return this.useSNMP;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public void setUseSNMP(boolean useSNMP)
/* 127:    */   {
/* 128:153 */     this.useSNMP = useSNMP;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public boolean isUseModbus()
/* 132:    */   {
/* 133:157 */     return this.useModbus;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public void setUseModbus(boolean useModbus)
/* 137:    */   {
/* 138:161 */     this.useModbus = useModbus;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public String getLanguage()
/* 142:    */   {
/* 143:165 */     return this.language;
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void setLanguage(String language)
/* 147:    */   {
/* 148:169 */     this.language = language;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public String getUdpPort()
/* 152:    */   {
/* 153:173 */     return this.udpPort;
/* 154:    */   }
/* 155:    */   
/* 156:    */   public void setUdpPort(String udpPort)
/* 157:    */   {
/* 158:177 */     this.udpPort = udpPort;
/* 159:    */   }
/* 160:    */   
/* 161:    */   public String getSection()
/* 162:    */   {
/* 163:182 */     return "GLOBAL_CONFIG";
/* 164:    */   }
/* 165:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.GlobalConfig
 * JD-Core Version:    0.7.0.1
 */