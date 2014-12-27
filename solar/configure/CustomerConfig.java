/*  1:   */ package cn.com.voltronic.solar.configure;
/*  2:   */ 
/*  3:   */ public class CustomerConfig
/*  4:   */   implements IConfigBean
/*  5:   */ {
/*  6:11 */   private String customerName = "SolarPower";
/*  7:13 */   private String version = "SolarPower V1.00";
/*  8:15 */   private String customerLogo = "";
/*  9:17 */   private String customerURL = "";
/* 10:19 */   private boolean isEnglish = true;
/* 11:21 */   private boolean isTurkish = false;
/* 12:23 */   private boolean isRussian = false;
/* 13:   */   
/* 14:   */   public String getCustomerName()
/* 15:   */   {
/* 16:26 */     return this.customerName;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setCustomerName(String customerName)
/* 20:   */   {
/* 21:30 */     this.customerName = customerName;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String getVersion()
/* 25:   */   {
/* 26:34 */     return this.version;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setVersion(String version)
/* 30:   */   {
/* 31:38 */     this.version = version;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public String getCustomerLogo()
/* 35:   */   {
/* 36:42 */     return this.customerLogo;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void setCustomerLogo(String customerLogo)
/* 40:   */   {
/* 41:46 */     this.customerLogo = customerLogo;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public String getCustomerURL()
/* 45:   */   {
/* 46:50 */     return this.customerURL;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void setCustomerURL(String customerURL)
/* 50:   */   {
/* 51:54 */     this.customerURL = customerURL;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public String getSection()
/* 55:   */   {
/* 56:60 */     return "CUSTOMER_INFO";
/* 57:   */   }
/* 58:   */   
/* 59:   */   public boolean isEnglish()
/* 60:   */   {
/* 61:64 */     return this.isEnglish;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public void setEnglish(boolean isEnglish)
/* 65:   */   {
/* 66:68 */     this.isEnglish = isEnglish;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public boolean isTurkish()
/* 70:   */   {
/* 71:72 */     return this.isTurkish;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void setTurkish(boolean isTurkish)
/* 75:   */   {
/* 76:76 */     this.isTurkish = isTurkish;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public boolean isRussian()
/* 80:   */   {
/* 81:80 */     return this.isRussian;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public void setRussian(boolean isRussian)
/* 85:   */   {
/* 86:84 */     this.isRussian = isRussian;
/* 87:   */   }
/* 88:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.CustomerConfig
 * JD-Core Version:    0.7.0.1
 */