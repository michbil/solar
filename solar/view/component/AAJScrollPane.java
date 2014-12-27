/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.BorderFactory;
/*  5:   */ import javax.swing.JScrollPane;
/*  6:   */ 
/*  7:   */ public class AAJScrollPane
/*  8:   */   extends JScrollPane
/*  9:   */   implements I18NListener
/* 10:   */ {
/* 11:   */   private static final long serialVersionUID = 1L;
/* 12:   */   private String key;
/* 13:   */   
/* 14:   */   public AAJScrollPane()
/* 15:   */   {
/* 16:20 */     compList.add(this);
/* 17:21 */     setFont(font12);
/* 18:22 */     initCompents();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setTitle(String text)
/* 22:   */   {
/* 23:   */     try
/* 24:   */     {
/* 25:28 */       this.key = text;
/* 26:29 */       super.setBorder(
/* 27:   */       
/* 28:31 */         BorderFactory.createTitledBorder(null, bd.getString(this.key), 0, 0, font12));
/* 29:   */     }
/* 30:   */     catch (Exception ex)
/* 31:   */     {
/* 32:33 */       super.setBorder(
/* 33:   */       
/* 34:35 */         BorderFactory.createTitledBorder(null, this.key, 0, 0, font12));
/* 35:   */     }
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void changeLang()
/* 39:   */   {
/* 40:42 */     setTitle(this.key);
/* 41:   */   }
/* 42:   */   
/* 43:   */   private void initCompents() {}
/* 44:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAJScrollPane
 * JD-Core Version:    0.7.0.1
 */