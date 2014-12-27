/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JCheckBox;
/*  5:   */ 
/*  6:   */ public class AACheckBox
/*  7:   */   extends JCheckBox
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = -8268749517586929840L;
/* 11:   */   private String _key;
/* 12:   */   
/* 13:   */   public AACheckBox()
/* 14:   */   {
/* 15:17 */     compList.add(this);
/* 16:18 */     setFont(font12);
/* 17:19 */     setBackground(bgColor);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setText(String text)
/* 21:   */   {
/* 22:   */     try
/* 23:   */     {
/* 24:25 */       this._key = text;
/* 25:26 */       super.setText(bd.getString(text));
/* 26:   */     }
/* 27:   */     catch (Exception e)
/* 28:   */     {
/* 29:28 */       super.setText(text);
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void changeLang()
/* 34:   */   {
/* 35:33 */     setText(this._key);
/* 36:   */   }
/* 37:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AACheckBox
 * JD-Core Version:    0.7.0.1
 */