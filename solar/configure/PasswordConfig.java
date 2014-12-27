/*  1:   */ package cn.com.voltronic.solar.configure;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.util.MD5Util;
/*  4:   */ 
/*  5:   */ public class PasswordConfig
/*  6:   */   implements IConfigBean
/*  7:   */ {
/*  8:13 */   private String _managerPassword = MD5Util.encodeByMD5("administrator");
/*  9:   */   
/* 10:   */   public String getManagerPassword()
/* 11:   */   {
/* 12:16 */     return this._managerPassword;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void setManagerPassword(String managerPassword)
/* 16:   */   {
/* 17:20 */     this._managerPassword = managerPassword;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getSection()
/* 21:   */   {
/* 22:26 */     return "PASSWORD";
/* 23:   */   }
/* 24:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.PasswordConfig
 * JD-Core Version:    0.7.0.1
 */