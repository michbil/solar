/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JLabel;
/*  5:   */ 
/*  6:   */ public class AALabelTip
/*  7:   */   extends JLabel
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:   */   private String key;
/* 12:13 */   private String[] values = null;
/* 13:   */   
/* 14:   */   public AALabelTip()
/* 15:   */   {
/* 16:17 */     compList.add(this);
/* 17:18 */     setFont(font12);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public AALabelTip(String key)
/* 21:   */   {
/* 22:22 */     setToolTipText(key);
/* 23:23 */     compList.add(this);
/* 24:24 */     setFont(font12);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setToolTipText(String text)
/* 28:   */   {
/* 29:   */     try
/* 30:   */     {
/* 31:29 */       this.key = text;
/* 32:30 */       super.setToolTipText(bd.getString(this.key));
/* 33:   */     }
/* 34:   */     catch (Exception e)
/* 35:   */     {
/* 36:32 */       super.setToolTipText(text);
/* 37:   */     }
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void changeLang()
/* 41:   */   {
/* 42:37 */     if (this.values == null)
/* 43:   */     {
/* 44:38 */       setToolTipText(this.key);
/* 45:   */     }
/* 46:   */     else
/* 47:   */     {
/* 48:40 */       String txt = bd.getString(this.key);
/* 49:41 */       super.setToolTipText(txt);
/* 50:   */     }
/* 51:   */   }
/* 52:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AALabelTip
 * JD-Core Version:    0.7.0.1
 */