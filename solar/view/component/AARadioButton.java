/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JRadioButton;
/*  5:   */ 
/*  6:   */ public class AARadioButton
/*  7:   */   extends JRadioButton
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:   */   private String key;
/* 12:   */   
/* 13:   */   public AARadioButton()
/* 14:   */   {
/* 15:17 */     compList.add(this);
/* 16:18 */     setFont(font12);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public AARadioButton(String text)
/* 20:   */   {
/* 21:22 */     setText(text);
/* 22:23 */     compList.add(this);
/* 23:24 */     setFont(font12);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void setText(String text)
/* 27:   */   {
/* 28:   */     try
/* 29:   */     {
/* 30:30 */       this.key = text;
/* 31:31 */       super.setText(bd.getString(text));
/* 32:   */     }
/* 33:   */     catch (Exception e)
/* 34:   */     {
/* 35:33 */       super.setText(text);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void changeLang()
/* 40:   */   {
/* 41:40 */     setText(this.key);
/* 42:   */   }
/* 43:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AARadioButton
 * JD-Core Version:    0.7.0.1
 */