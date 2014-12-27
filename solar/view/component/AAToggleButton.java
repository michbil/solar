/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JToggleButton;
/*  5:   */ 
/*  6:   */ public class AAToggleButton
/*  7:   */   extends JToggleButton
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:   */   private String key;
/* 12:   */   
/* 13:   */   public AAToggleButton()
/* 14:   */   {
/* 15:15 */     compList.add(this);
/* 16:16 */     setFont(font12);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public AAToggleButton(String text)
/* 20:   */   {
/* 21:21 */     setText(text);
/* 22:22 */     compList.add(this);
/* 23:23 */     setFont(font12);
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void setText(String text)
/* 27:   */   {
/* 28:   */     try
/* 29:   */     {
/* 30:29 */       this.key = text;
/* 31:30 */       super.setText(bd.getString(text));
/* 32:   */     }
/* 33:   */     catch (Exception e)
/* 34:   */     {
/* 35:32 */       super.setText(text);
/* 36:   */     }
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void changeLang()
/* 40:   */   {
/* 41:37 */     setText(this.key);
/* 42:   */   }
/* 43:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAToggleButton
 * JD-Core Version:    0.7.0.1
 */