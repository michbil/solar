/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*  4:   */ import java.awt.TrayIcon;
/*  5:   */ import java.awt.TrayIcon.MessageType;
/*  6:   */ import javax.swing.JOptionPane;
/*  7:   */ 
/*  8:   */ public class DisplayMessage
/*  9:   */   implements I18NListener
/* 10:   */ {
/* 11:   */   private static final long serialVersionUID = -7279827345834260965L;
/* 12:   */   
/* 13:   */   public static void showErrorDialog(String message, String value)
/* 14:   */   {
/* 15:20 */     String title = bd.getString("message.tiptitle");
/* 16:   */     try
/* 17:   */     {
/* 18:22 */       JOptionPane.showMessageDialog(null, bd.getString(message) + ":" + value, title, 0);
/* 19:   */     }
/* 20:   */     catch (Exception ex)
/* 21:   */     {
/* 22:24 */       JOptionPane.showMessageDialog(null, message, title, 0);
/* 23:   */     }
/* 24:   */   }
/* 25:   */   
/* 26:   */   public static void showErrorDialog(String message)
/* 27:   */   {
/* 28:29 */     String title = bd.getString("message.tiptitle");
/* 29:   */     try
/* 30:   */     {
/* 31:31 */       JOptionPane.showMessageDialog(null, bd.getString(message), title, 0);
/* 32:   */     }
/* 33:   */     catch (Exception ex)
/* 34:   */     {
/* 35:33 */       JOptionPane.showMessageDialog(null, message, title, 0);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public static void showInfoDialog(String message)
/* 40:   */   {
/* 41:38 */     String title = bd.getString("message.info");
/* 42:   */     try
/* 43:   */     {
/* 44:40 */       JOptionPane.showMessageDialog(null, bd.getString(message), title, 1);
/* 45:   */     }
/* 46:   */     catch (Exception ex)
/* 47:   */     {
/* 48:42 */       JOptionPane.showMessageDialog(null, message, title, 1);
/* 49:   */     }
/* 50:   */   }
/* 51:   */   
/* 52:   */   public static void showWarningDialog(String message)
/* 53:   */   {
/* 54:47 */     String title = bd.getString("message.warning");
/* 55:   */     try
/* 56:   */     {
/* 57:49 */       JOptionPane.showMessageDialog(null, bd.getString(message), title, 2);
/* 58:   */     }
/* 59:   */     catch (Exception ex)
/* 60:   */     {
/* 61:51 */       JOptionPane.showMessageDialog(null, message, title, 2);
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public static int showConfirmDialog(String message, String title)
/* 66:   */   {
/* 67:56 */     int result = 0;
/* 68:   */     try
/* 69:   */     {
/* 70:58 */       result = JOptionPane.showConfirmDialog(null, bd.getString(message), 
/* 71:59 */         bd.getString(title), 0);
/* 72:   */     }
/* 73:   */     catch (Exception ex)
/* 74:   */     {
/* 75:61 */       result = JOptionPane.showConfirmDialog(null, message, title, 
/* 76:62 */         0);
/* 77:   */     }
/* 78:64 */     return result;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public static void showMesgToTary(String title, String message, TrayIcon.MessageType type)
/* 82:   */   {
/* 83:   */     try
/* 84:   */     {
/* 85:70 */       SolarPowerTray.trayIcon.displayMessage(bd.getString(title), 
/* 86:71 */         bd.getString(message), type);
/* 87:   */     }
/* 88:   */     catch (Exception ex)
/* 89:   */     {
/* 90:73 */       SolarPowerTray.trayIcon.displayMessage(title, message, type);
/* 91:   */     }
/* 92:   */   }
/* 93:   */   
/* 94:   */   public void changeLang() {}
/* 95:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.DisplayMessage
 * JD-Core Version:    0.7.0.1
 */