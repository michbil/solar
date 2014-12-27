/*  1:   */ package cn.com.voltronic.solar.upgrade;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*  4:   */ import cn.com.voltronic.solar.console.linux.LinuxMonitorConsole;
/*  5:   */ import cn.com.voltronic.solar.console.mac.MacMonitorConsole;
/*  6:   */ import cn.com.voltronic.solar.console.windows.MainConsole;
/*  7:   */ import cn.com.voltronic.solar.constants.Constants;
/*  8:   */ 
/*  9:   */ public class UpgradeUtil
/* 10:   */ {
/* 11:   */   public static void refreshStatus(boolean online, boolean manually)
/* 12:   */   {
/* 13:11 */     if (Constants.IS_OS_WINDOWS) {
/* 14:12 */       MainConsole.getConsoleTray().refreshUpgrade(online, manually);
/* 15:13 */     } else if ((Constants.IS_OS_LINUX) || (Constants.IS_OS_SOLARIS)) {
/* 16:14 */       LinuxMonitorConsole.getConsoleTray().refreshUpgrade(online, 
/* 17:15 */         manually);
/* 18:   */     } else {
/* 19:17 */       MacMonitorConsole.getConsoleTray().refreshUpgrade(online, manually);
/* 20:   */     }
/* 21:   */   }
/* 22:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.UpgradeUtil
 * JD-Core Version:    0.7.0.1
 */