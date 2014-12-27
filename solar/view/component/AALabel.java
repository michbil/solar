/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.text.MessageFormat;
/*  4:   */ import java.util.List;
/*  5:   */ import javax.swing.JLabel;
/*  6:   */ 
/*  7:   */ public class AALabel
/*  8:   */   extends JLabel
/*  9:   */   implements I18NListener
/* 10:   */ {
/* 11:   */   private static final long serialVersionUID = 7193514544808903392L;
/* 12:   */   private String _key;
/* 13:16 */   private Object[] values = null;
/* 14:   */   
/* 15:   */   public AALabel()
/* 16:   */   {
/* 17:20 */     compList.add(this);
/* 18:21 */     setFont(font12);
/* 19:22 */     setBackground(bgColor);
/* 20:   */   }
/* 21:   */   
/* 22:   */   public AALabel(String text)
/* 23:   */   {
/* 24:26 */     this._key = text;
/* 25:27 */     setText(this._key);
/* 26:28 */     compList.add(this);
/* 27:29 */     setFont(font12);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setText(String text)
/* 31:   */   {
/* 32:34 */     if (text.indexOf('[') == -1)
/* 33:   */     {
/* 34:   */       try
/* 35:   */       {
/* 36:36 */         this._key = text;
/* 37:37 */         super.setText(bd.getString(this._key));
/* 38:   */       }
/* 39:   */       catch (Exception e)
/* 40:   */       {
/* 41:39 */         super.setText(text);
/* 42:   */       }
/* 43:   */     }
/* 44:   */     else
/* 45:   */     {
/* 46:42 */       String str1 = text.substring(0, text.lastIndexOf('['));
/* 47:43 */       String str2 = text.substring(text.lastIndexOf('[') + 1, text.lastIndexOf(']'));
/* 48:   */       try
/* 49:   */       {
/* 50:45 */         this._key = text;
/* 51:46 */         super.setText(bd.getString(str1) + str2);
/* 52:   */       }
/* 53:   */       catch (Exception e)
/* 54:   */       {
/* 55:48 */         super.setText(text);
/* 56:   */       }
/* 57:   */     }
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void setToolTipText(String text)
/* 61:   */   {
/* 62:   */     try
/* 63:   */     {
/* 64:56 */       this._key = text;
/* 65:57 */       super.setToolTipText(bd.getString(this._key));
/* 66:   */     }
/* 67:   */     catch (Exception e)
/* 68:   */     {
/* 69:59 */       super.setToolTipText(text);
/* 70:   */     }
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void setArgs(Object... values)
/* 74:   */   {
/* 75:64 */     this.values = values;
/* 76:65 */     String txt = bd.getString(this._key);
/* 77:66 */     txt = MessageFormat.format(txt, values);
/* 78:67 */     super.setText(txt);
/* 79:   */   }
/* 80:   */   
/* 81:   */   public void changeLang()
/* 82:   */   {
/* 83:71 */     if (this.values == null)
/* 84:   */     {
/* 85:72 */       setText(this._key);
/* 86:   */     }
/* 87:   */     else
/* 88:   */     {
/* 89:74 */       String txt = bd.getString(this._key);
/* 90:75 */       txt = MessageFormat.format(txt, this.values);
/* 91:76 */       super.setText(txt);
/* 92:   */     }
/* 93:   */   }
/* 94:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AALabel
 * JD-Core Version:    0.7.0.1
 */