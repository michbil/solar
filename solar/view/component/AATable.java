/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JTable;
/*  5:   */ 
/*  6:   */ public class AATable
/*  7:   */   extends JTable
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:   */   
/* 12:   */   public AATable()
/* 13:   */   {
/* 14:16 */     compList.add(this);
/* 15:17 */     setFont(font12);
/* 16:18 */     setBackground(bgColor);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void changeLang() {}
/* 20:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AATable
 * JD-Core Version:    0.7.0.1
 */