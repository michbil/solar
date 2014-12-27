/*  1:   */ package cn.com.voltronic.solar.configure;
/*  2:   */ 
/*  3:   */ public class LimitCOMConfig
/*  4:   */   implements IConfigBean
/*  5:   */ {
/*  6:12 */   private String limitcoms = "";
/*  7:   */   
/*  8:   */   public String getLimitcoms()
/*  9:   */   {
/* 10:15 */     return this.limitcoms;
/* 11:   */   }
/* 12:   */   
/* 13:   */   public void setLimitcoms(String limitcoms)
/* 14:   */   {
/* 15:19 */     this.limitcoms = limitcoms;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String getSection()
/* 19:   */   {
/* 20:25 */     return "LIMITCOMS";
/* 21:   */   }
/* 22:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.LimitCOMConfig
 * JD-Core Version:    0.7.0.1
 */