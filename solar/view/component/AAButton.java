/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.JButton;
/*  5:   */ 
/*  6:   */ public class AAButton
/*  7:   */   extends JButton
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = -2400130258581286246L;
/* 11:   */   private String _key;
/* 12:   */   
/* 13:   */   public AAButton()
/* 14:   */   {
/* 15:16 */     compList.add(this);
/* 16:17 */     setFont(font12);
/* 17:18 */     setBackground(bgColor);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public AAButton(String text)
/* 21:   */   {
/* 22:22 */     this._key = text;
/* 23:23 */     setText(this._key);
/* 24:24 */     compList.add(this);
/* 25:25 */     setFont(font12);
/* 26:26 */     setBackground(bgColor);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setText(String text)
/* 30:   */   {
/* 31:   */     try
/* 32:   */     {
/* 33:32 */       this._key = text;
/* 34:33 */       super.setText(bd.getString(text));
/* 35:   */     }
/* 36:   */     catch (Exception e)
/* 37:   */     {
/* 38:35 */       super.setText(text);
/* 39:   */     }
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setToolTipText(String text)
/* 43:   */   {
/* 44:   */     try
/* 45:   */     {
/* 46:43 */       super.setToolTipText(bd.getString(text));
/* 47:   */     }
/* 48:   */     catch (Exception ex)
/* 49:   */     {
/* 50:45 */       super.setToolTipText(text);
/* 51:   */     }
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void changeLang()
/* 55:   */   {
/* 56:50 */     setText(this._key);
/* 57:   */   }
/* 58:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAButton
 * JD-Core Version:    0.7.0.1
 */