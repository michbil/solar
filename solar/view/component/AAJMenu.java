/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JMenu;
/*  5:   */ 
/*  6:   */ public class AAJMenu
/*  7:   */   extends JMenu
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:   */   private String key;
/* 12:   */   
/* 13:   */   public AAJMenu()
/* 14:   */   {
/* 15:11 */     compList.add(this);
/* 16:12 */     setFont(font13);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public AAJMenu(String text)
/* 20:   */   {
/* 21:16 */     setText(text);
/* 22:17 */     compList.add(this);
/* 23:18 */     setFont(font13);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void setText(String text)
/* 27:   */   {
/* 28:23 */     if (text.indexOf('[') == -1)
/* 29:   */     {
/* 30:   */       try
/* 31:   */       {
/* 32:25 */         this.key = text;
/* 33:26 */         super.setText(bd.getString(text));
/* 34:   */       }
/* 35:   */       catch (Exception e)
/* 36:   */       {
/* 37:28 */         super.setText(text);
/* 38:   */       }
/* 39:   */     }
/* 40:   */     else
/* 41:   */     {
/* 42:31 */       String str1 = text.substring(1, text.lastIndexOf(']'));
/* 43:32 */       String str2 = text.substring(text.lastIndexOf(']') + 1);
/* 44:   */       try
/* 45:   */       {
/* 46:34 */         this.key = text;
/* 47:35 */         super.setText(str1 + " " + bd.getString(str2));
/* 48:   */       }
/* 49:   */       catch (Exception e)
/* 50:   */       {
/* 51:37 */         super.setText(text);
/* 52:   */       }
/* 53:   */     }
/* 54:   */   }
/* 55:   */   
/* 56:   */   public void changeLang()
/* 57:   */   {
/* 58:43 */     setText(this.key);
/* 59:   */   }
/* 60:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAJMenu
 * JD-Core Version:    0.7.0.1
 */