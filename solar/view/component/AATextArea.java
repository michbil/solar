/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JTextArea;
/*  5:   */ 
/*  6:   */ public class AATextArea
/*  7:   */   extends JTextArea
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = 1L;
/* 11:12 */   private String key = "";
/* 12:   */   
/* 13:   */   public AATextArea()
/* 14:   */   {
/* 15:15 */     compList.add(this);
/* 16:16 */     setFont(font12);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void changeLang()
/* 20:   */   {
/* 21:20 */     setText(this.key);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setText(String txt)
/* 25:   */   {
/* 26:25 */     this.key = txt;
/* 27:26 */     super.setText(bd.getString(this.key));
/* 28:   */   }
/* 29:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AATextArea
 * JD-Core Version:    0.7.0.1
 */