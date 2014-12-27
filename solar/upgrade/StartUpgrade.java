/*  1:   */ package cn.com.voltronic.solar.upgrade;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*  4:   */ import cn.com.voltronic.solar.configure.UpgradeConfig;
/*  5:   */ import cn.com.voltronic.solar.constants.Constants;
/*  6:   */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  7:   */ import java.io.File;
/*  8:   */ import org.apache.log4j.Logger;
/*  9:   */ 
/* 10:   */ public class StartUpgrade
/* 11:   */ {
/* 12:19 */   protected Logger logger = Logger.getLogger(StartUpgrade.class);
/* 13:   */   
/* 14:   */   public void updateOnline()
/* 15:   */   {
/* 16:22 */     UpgradeUtil.refreshStatus(false, false);
/* 17:23 */     startDownload();
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void updateByUser()
/* 21:   */   {
/* 22:27 */     UpByUserDialog upByUserDialog = null;
/* 23:28 */     if (System.getProperty("java.version").startsWith("1.6"))
/* 24:   */     {
/* 25:29 */       upByUserDialog = UpByUserDialog16.getInstance();
/* 26:   */     }
/* 27:   */     else
/* 28:   */     {
/* 29:31 */       upByUserDialog = UpByUserDialog15.getInstance();
/* 30:32 */       upByUserDialog.setSize(550, 120);
/* 31:   */     }
/* 32:34 */     upByUserDialog.setLocationRelativeTo(null);
/* 33:35 */     upByUserDialog.setVisible(true);
/* 34:36 */     UpgradeUtil.refreshStatus(false, false);
/* 35:   */   }
/* 36:   */   
/* 37:   */   private void startDownload()
/* 38:   */   {
/* 39:40 */     String downloadDir = "";
/* 40:41 */     String saveDir = "";
/* 41:   */     try
/* 42:   */     {
/* 43:43 */       downloadDir = GlobalVariables.upgradeConfig.getFromURL();
/* 44:44 */       saveDir = GlobalVariables.upgradeConfig.getSavePath();
/* 45:   */     }
/* 46:   */     catch (Exception ex)
/* 47:   */     {
/* 48:46 */       ex.printStackTrace();
/* 49:   */     }
/* 50:49 */     if ((saveDir == null) || ("".equals(saveDir)))
/* 51:   */     {
/* 52:51 */       saveDir = 
/* 53:52 */         System.getProperty("user.dir") + File.separator + "UpgradeFiles";
/* 54:53 */       GlobalVariables.upgradeConfig.setSavePath(saveDir);
/* 55:   */       try
/* 56:   */       {
/* 57:55 */         ConfigureTools.updateProperties(GlobalVariables.upgradeConfig);
/* 58:56 */         ConfigureTools.wrapProperties(GlobalVariables.upgradeConfig);
/* 59:   */       }
/* 60:   */       catch (Exception localException1) {}
/* 61:   */     }
/* 62:61 */     Download download = new Download(downloadDir, saveDir);
/* 63:62 */     download.isStop = false;
/* 64:63 */     download.start();
/* 65:   */   }
/* 66:   */   
/* 67:   */   public static void runUpgrade()
/* 68:   */   {
/* 69:   */     try
/* 70:   */     {
/* 71:69 */       if (Constants.IS_OS_WINDOWS)
/* 72:   */       {
/* 73:70 */         String cmd = "cmd /c start \" \" /D \"" + 
/* 74:71 */           System.getProperty("user.dir") + "\" " + 
/* 75:72 */           "runUpgrade.bat";
/* 76:73 */         Runtime.getRuntime().exec(cmd);
/* 77:   */       }
/* 78:74 */       else if (Constants.IS_OS_LINUX)
/* 79:   */       {
/* 80:75 */         Runtime.getRuntime().exec(
/* 81:76 */           "chmod 777 " + System.getProperty("user.dir") + 
/* 82:77 */           File.separator + "runUpgrade.sh");
/* 83:78 */         Runtime.getRuntime().exec(
/* 84:79 */           System.getProperty("user.dir") + File.separator + 
/* 85:80 */           "runUpgrade.sh");
/* 86:   */       }
/* 87:81 */       else if (Constants.IS_OS_SOLARIS)
/* 88:   */       {
/* 89:82 */         Runtime.getRuntime().exec(
/* 90:83 */           "chmod 777 " + System.getProperty("user.dir") + 
/* 91:84 */           File.separator + "runUpgrade.sh");
/* 92:85 */         Runtime.getRuntime().exec(
/* 93:86 */           System.getProperty("user.dir") + File.separator + 
/* 94:87 */           "runUpgrade.sh");
/* 95:   */       }
/* 96:88 */       else if ((Constants.IS_OS_MAC) || (Constants.IS_OS_MAC_OSX))
/* 97:   */       {
/* 98:89 */         Runtime.getRuntime().exec(
/* 99:90 */           "chmod 777 " + System.getProperty("user.dir") + 
/* :0:91 */           File.separator + "runUpgrade.sh");
/* :1:92 */         Runtime.getRuntime().exec(
/* :2:93 */           System.getProperty("user.dir") + File.separator + 
/* :3:94 */           "runUpgrade.sh");
/* :4:   */       }
/* :5:   */     }
/* :6:   */     catch (Exception ex)
/* :7:   */     {
/* :8:97 */       ex.printStackTrace();
/* :9:   */     }
/* ;0:   */   }
/* ;1:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.StartUpgrade
 * JD-Core Version:    0.7.0.1
 */