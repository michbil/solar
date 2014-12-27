/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JMenuItem;
/*  5:   */ 
/*  6:   */ public class AAJMenuItem
/*  7:   */   extends JMenuItem
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:   */   private String key;
/* 12:   */   
/* 13:   */   public AAJMenuItem()
/* 14:   */   {
/* 15:11 */     compList.add(this);
/* 16:12 */     setFont(font13);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public AAJMenuItem(String text)
/* 20:   */   {
/* 21:16 */     setText(text);
/* 22:17 */     compList.add(this);
/* 23:18 */     setFont(font13);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void setText(String text)
/* 27:   */   {
/* 28:   */     try
/* 29:   */     {
/* 30:24 */       this.key = text;
/* 31:25 */       super.setText(bd.getString(text));
/* 32:   */     }
/* 33:   */     catch (Exception e)
/* 34:   */     {
/* 35:27 */       super.setText(text);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void changeLang()
/* 40:   */   {
/* 41:32 */     setText(this.key);
/* 42:   */   }
/* 43:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAJMenuItem
 * JD-Core Version:    0.7.0.1
 */