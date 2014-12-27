/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JPanel;
/*  5:   */ 
/*  6:   */ public class AAPanel
/*  7:   */   extends JPanel
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 424573551910901814L;
/* 11:   */   
/* 12:   */   public AAPanel()
/* 13:   */   {
/* 14:17 */     compList.add(this);
/* 15:18 */     setFont(font12);
/* 16:19 */     setBackground(bgColor);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void changeLang() {}
/* 20:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAPanel
 * JD-Core Version:    0.7.0.1
 */