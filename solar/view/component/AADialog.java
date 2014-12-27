/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.Frame;
/*  4:   */ import javax.swing.JDialog;
/*  5:   */ 
/*  6:   */ public class AADialog
/*  7:   */   extends JDialog
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = -4709688372189032881L;
/* 11:   */   
/* 12:   */   public AADialog(Frame parent, boolean modal)
/* 13:   */   {
/* 14:18 */     super(parent, modal);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void setTitle(String title)
/* 18:   */   {
/* 19:   */     try
/* 20:   */     {
/* 21:24 */       super.setTitle(bd.getString(title));
/* 22:   */     }
/* 23:   */     catch (Exception ex)
/* 24:   */     {
/* 25:26 */       super.setTitle(title);
/* 26:   */     }
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void changeLang() {}
/* 30:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AADialog
 * JD-Core Version:    0.7.0.1
 */