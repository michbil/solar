/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JTextField;
/*  5:   */ 
/*  6:   */ public class AATextField
/*  7:   */   extends JTextField
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = -8016042602453205376L;
/* 11:   */   
/* 12:   */   public AATextField()
/* 13:   */   {
/* 14:17 */     compList.add(this);
/* 15:18 */     setFont(font12);
/* 16:19 */     setBackground(bgColor);
/* 17:20 */     setEditable(false);
/* 18:21 */     setHorizontalAlignment(4);
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void changeLang() {}
/* 22:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AATextField
 * JD-Core Version:    0.7.0.1
 */