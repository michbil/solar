/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.border.TitledBorder;
/*  5:   */ 
/*  6:   */ public class AATitleBorder
/*  7:   */   extends TitledBorder
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:   */   private String key;
/* 12:   */   
/* 13:   */   public AATitleBorder(String title)
/* 14:   */   {
/* 15:17 */     super("");
/* 16:18 */     setTitle(title);
/* 17:19 */     compList.add(this);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setTitle(String title)
/* 21:   */   {
/* 22:24 */     this.key = title;
/* 23:25 */     super.setTitle(bd.getString(title));
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void changeLang()
/* 27:   */   {
/* 28:29 */     setTitle(this.key);
/* 29:   */   }
/* 30:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AATitleBorder
 * JD-Core Version:    0.7.0.1
 */