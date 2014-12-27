/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import javax.swing.JFrame;
/*  4:   */ 
/*  5:   */ public class AAFrame
/*  6:   */   extends JFrame
/*  7:   */   implements I18NListener
/*  8:   */ {
/*  9:   */   private static final long serialVersionUID = -7652242908976125472L;
/* 10:   */   
/* 11:   */   public void setTitle(String title)
/* 12:   */   {
/* 13:   */     try
/* 14:   */     {
/* 15:18 */       super.setTitle(bd.getString(title));
/* 16:   */     }
/* 17:   */     catch (Exception ex)
/* 18:   */     {
/* 19:20 */       super.setTitle(title);
/* 20:   */     }
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void changeLang() {}
/* 24:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAFrame
 * JD-Core Version:    0.7.0.1
 */