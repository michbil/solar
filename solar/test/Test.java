/*  1:   */ package cn.com.voltronic.solar.test;
/*  2:   */ 
/*  3:   */ import com.jtattoo.plaf.smart.SmartLookAndFeel;
/*  4:   */ import java.util.Properties;
/*  5:   */ import javax.swing.JButton;
/*  6:   */ import javax.swing.JFrame;
/*  7:   */ import javax.swing.JPanel;
/*  8:   */ import javax.swing.UIManager;
/*  9:   */ 
/* 10:   */ public class Test
/* 11:   */   extends JFrame
/* 12:   */ {
/* 13:   */   private static final long serialVersionUID = 6322021506740226438L;
/* 14:   */   
/* 15:   */   static
/* 16:   */   {
/* 17:   */     try
/* 18:   */     {
/* 19:25 */       Properties props = new Properties();
/* 20:   */       
/* 21:27 */       props.put("foregroundColor", "255 255 255");
/* 22:28 */       props.put("frameColor", "130 130 130");
/* 23:29 */       props.put("gridColor", "210 210 210");
/* 24:30 */       props.put("tooltipBackgroundColor", "102 102 102");
/* 25:31 */       props.put("tooltipForegroundColor", "255 255 255");
/* 26:   */       
/* 27:33 */       props.put("selectionBackgroundColor", "230 230 230");
/* 28:   */       
/* 29:35 */       props.put("menuSelectionForegroundColor", "0 0 0");
/* 30:36 */       props.put("menuSelectionBackgroundColor", "230 230 230");
/* 31:37 */       props.put("menuSelectionBackgroundColorLight", "255 255 255");
/* 32:38 */       props.put("menuSelectionBackgroundColorDark", "230 230 230");
/* 33:39 */       props.put("menuBackgroundColor", "90 90 90");
/* 34:40 */       props.put("menuColorLight", "69 69 69");
/* 35:41 */       props.put("menuColorDark", "102 102 102");
/* 36:42 */       props.put("menuForegroundColor", "255 255 255");
/* 37:   */       
/* 38:44 */       props.put("controlColor", "102 102 102");
/* 39:45 */       props.put("controlColorLight", "102 102 102");
/* 40:46 */       props.put("controlColorDark", "69 69 69");
/* 41:   */       
/* 42:48 */       props.put("buttonForegroundColor", "255 255 255");
/* 43:49 */       props.put("buttonColor", "102 102 102");
/* 44:50 */       props.put("buttonBackgroundColor", "102 102 102");
/* 45:51 */       props.put("buttonColorLight", "230 230 230");
/* 46:52 */       props.put("buttonColorDark", "102 102 102");
/* 47:   */       
/* 48:54 */       props.put("rolloverColor", "102 102 102");
/* 49:55 */       props.put("rolloverColorLight", "102 102 102");
/* 50:56 */       props.put("rolloverColorDark", "69 69 69");
/* 51:   */       
/* 52:58 */       props.put("windowTitleForegroundColor", "0 0 0");
/* 53:59 */       props.put("windowTitleBackgroundColor", "69 69 69");
/* 54:60 */       props.put("windowTitleColorLight", "197 197 197");
/* 55:61 */       props.put("windowTitleColorDark", "69 69 69");
/* 56:62 */       props.put("windowBorderColor", "10 10 10");
/* 57:   */       
/* 58:64 */       props.put("tabAreaBackgroundColor", "102 102 102");
/* 59:65 */       props.put("inputForegroundColor", "255 255 255");
/* 60:66 */       props.put("inputBackgroundColor", "102 102 102");
/* 61:67 */       props.put("focusColor", "255 255 255");
/* 62:68 */       props.put("focusCellColor", "255 255 255");
/* 63:   */       
/* 64:70 */       props.put("backgroundColor", "102 102 102");
/* 65:71 */       props.put("backgroundColorLight", "255 255 255");
/* 66:72 */       props.put("backgroundColorDark", "102 102 102");
/* 67:   */       
/* 68:74 */       SmartLookAndFeel.setCurrentTheme(props);
/* 69:75 */       UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
/* 70:   */     }
/* 71:   */     catch (Exception ex)
/* 72:   */     {
/* 73:78 */       ex.printStackTrace();
/* 74:   */     }
/* 75:   */   }
/* 76:   */   
/* 77:   */   public static void main(String[] args)
/* 78:   */   {
/* 79:87 */     Test test = new Test();
/* 80:88 */     JPanel panel = new JPanel();
/* 81:89 */     panel.add(new JButton("Yes(Y)"));
/* 82:90 */     panel.add(new JButton("No(N)"));
/* 83:91 */     test.add(panel);
/* 84:92 */     test.setSize(300, 300);
/* 85:93 */     test.setVisible(true);
/* 86:   */   }
/* 87:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.test.Test
 * JD-Core Version:    0.7.0.1
 */