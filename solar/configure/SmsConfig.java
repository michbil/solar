/*  1:   */ package cn.com.voltronic.solar.configure;
/*  2:   */ 
/*  3:   */ public class SmsConfig
/*  4:   */   implements IConfigBean
/*  5:   */ {
/*  6:   */   private String comPort;
/*  7:   */   private String bit;
/*  8:   */   private String mobileNums;
/*  9:   */   
/* 10:   */   public String getMobileNums()
/* 11:   */   {
/* 12:16 */     return this.mobileNums;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setMobileNums(String mobileNums)
/* 16:   */   {
/* 17:20 */     this.mobileNums = mobileNums;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getComPort()
/* 21:   */   {
/* 22:24 */     return this.comPort;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setComPort(String comPort)
/* 26:   */   {
/* 27:28 */     this.comPort = comPort;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getBit()
/* 31:   */   {
/* 32:32 */     return this.bit;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setBit(String bit)
/* 36:   */   {
/* 37:36 */     this.bit = bit;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public String getSection()
/* 41:   */   {
/* 42:41 */     return "SMS";
/* 43:   */   }
/* 44:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.SmsConfig
 * JD-Core Version:    0.7.0.1
 */