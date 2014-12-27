/*  1:   */ package cn.com.voltronic.solar.system;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*  4:   */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*  5:   */ import cn.com.voltronic.solar.configure.EmailConfig;
/*  6:   */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*  7:   */ import cn.com.voltronic.solar.configure.IniProperties;
/*  8:   */ import cn.com.voltronic.solar.configure.LimitCOMConfig;
/*  9:   */ import cn.com.voltronic.solar.configure.PasswordConfig;
/* 10:   */ import cn.com.voltronic.solar.configure.SmsConfig;
/* 11:   */ import cn.com.voltronic.solar.configure.UpgradeConfig;
/* 12:   */ import cn.com.voltronic.solar.constants.Constants;
/* 13:   */ 
/* 14:   */ public class GlobalVariables
/* 15:   */ {
/* 16:22 */   public static IniProperties properties = new IniProperties();
/* 17:23 */   public static SmsConfig smsConfig = new SmsConfig();
/* 18:24 */   public static PasswordConfig passwordConfig = new PasswordConfig();
/* 19:25 */   public static EmailConfig emailConfig = new EmailConfig();
/* 20:26 */   public static LimitCOMConfig limitCOMConfig = new LimitCOMConfig();
/* 21:27 */   public static CustomerConfig customerConfig = new CustomerConfig();
/* 22:28 */   public static GlobalConfig globalConfig = new GlobalConfig();
/* 23:29 */   public static UpgradeConfig upgradeConfig = new UpgradeConfig();
/* 24:   */   
/* 25:   */   public static void initConfig(String propfile)
/* 26:   */   {
/* 27:   */     try
/* 28:   */     {
/* 29:33 */       properties.setProperyfile(propfile);
/* 30:34 */       properties.load();
/* 31:35 */       ConfigureTools.wrapProperties(smsConfig);
/* 32:36 */       ConfigureTools.wrapProperties(passwordConfig);
/* 33:37 */       ConfigureTools.wrapProperties(emailConfig);
/* 34:38 */       ConfigureTools.wrapProperties(limitCOMConfig);
/* 35:39 */       ConfigureTools.wrapProperties(customerConfig);
/* 36:40 */       ConfigureTools.wrapProperties(globalConfig);
/* 37:41 */       ConfigureTools.wrapProperties(upgradeConfig);
/* 38:   */     }
/* 39:   */     catch (Exception e)
/* 40:   */     {
/* 41:43 */       e.printStackTrace();
/* 42:   */     }
/* 43:   */   }
/* 44:   */   
/* 45:   */   public static void createConfig(String propfile)
/* 46:   */   {
/* 47:   */     try
/* 48:   */     {
/* 49:49 */       properties.setProperyfile(propfile);
/* 50:50 */       properties.load();
/* 51:51 */       ConfigureTools.updateProperties(smsConfig);
/* 52:52 */       ConfigureTools.updateProperties(passwordConfig);
/* 53:53 */       ConfigureTools.updateProperties(emailConfig);
/* 54:54 */       ConfigureTools.updateProperties(limitCOMConfig);
/* 55:55 */       ConfigureTools.updateProperties(customerConfig);
/* 56:56 */       ConfigureTools.updateProperties(globalConfig);
/* 57:57 */       ConfigureTools.updateProperties(upgradeConfig);
/* 58:   */     }
/* 59:   */     catch (Exception e)
/* 60:   */     {
/* 61:59 */       e.printStackTrace();
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public static void main(String[] args)
/* 66:   */   {
/* 67:64 */     createConfig(Constants.CONFIG_PATH);
/* 68:   */   }
/* 69:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.system.GlobalVariables
 * JD-Core Version:    0.7.0.1
 */