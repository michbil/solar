/*  1:   */ package cn.com.voltronic.solar.console;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.view.component.I18NBundle;
/*  4:   */ import java.awt.SystemTray;
/*  5:   */ import java.awt.TrayIcon;
/*  6:   */ import javax.swing.JLabel;
/*  7:   */ import javax.swing.JWindow;
/*  8:   */ 
/*  9:   */ public abstract class SolarPowerTray
/* 10:   */   extends JWindow
/* 11:   */ {
/* 12:   */   private static final long serialVersionUID = 4064239403455495733L;
/* 13:21 */   public static boolean isLogin = false;
/* 14:22 */   public SystemTray tray = null;
/* 15:23 */   public static TrayIcon trayIcon = null;
/* 16:   */   public JLabel icon;
/* 17:   */   
/* 18:   */   public abstract void addToSystemTray();
/* 19:   */   
/* 20:   */   public abstract void refreshUpgrade(boolean paramBoolean1, boolean paramBoolean2);
/* 21:   */   
/* 22:   */   public void changeLanguage(String currentLanguage)
/* 23:   */   {
/* 24:31 */     if (currentLanguage.equals("en_US")) {
/* 25:32 */       I18NBundle.changeUS();
/* 26:33 */     } else if (currentLanguage.equals("fr_FR")) {
/* 27:34 */       I18NBundle.changeFR();
/* 28:35 */     } else if (currentLanguage.equals("de_DE")) {
/* 29:36 */       I18NBundle.changeDE();
/* 30:37 */     } else if (currentLanguage.equals("it_IT")) {
/* 31:38 */       I18NBundle.changeIT();
/* 32:39 */     } else if (currentLanguage.equals("pl_PL")) {
/* 33:40 */       I18NBundle.changePL();
/* 34:41 */     } else if (currentLanguage.equals("pt_PT")) {
/* 35:42 */       I18NBundle.changePT();
/* 36:43 */     } else if (currentLanguage.equals("ru_RU")) {
/* 37:44 */       I18NBundle.changeRUS();
/* 38:45 */     } else if (currentLanguage.equals("es_ES")) {
/* 39:46 */       I18NBundle.changeSP();
/* 40:47 */     } else if (currentLanguage.equals("uk_UA")) {
/* 41:48 */       I18NBundle.changeUKR();
/* 42:49 */     } else if (currentLanguage.equals("tr_TR")) {
/* 43:50 */       I18NBundle.changeTUR();
/* 44:51 */     } else if (currentLanguage.equals("zh_CN")) {
/* 45:52 */       I18NBundle.changeCN();
/* 46:53 */     } else if (currentLanguage.equals("cs_CS")) {
/* 47:54 */       I18NBundle.changeCS();
/* 48:55 */     } else if (currentLanguage.equals("zh_TW")) {
/* 49:56 */       I18NBundle.changeTW();
/* 50:   */     } else {
/* 51:58 */       I18NBundle.changeUS();
/* 52:   */     }
/* 53:   */   }
/* 54:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.console.SolarPowerTray
 * JD-Core Version:    0.7.0.1
 */