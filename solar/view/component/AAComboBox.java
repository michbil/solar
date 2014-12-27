/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.DefaultComboBoxModel;
/*  5:   */ import javax.swing.JComboBox;
/*  6:   */ 
/*  7:   */ public class AAComboBox
/*  8:   */   extends JComboBox
/*  9:   */   implements I18NListener
/* 10:   */ {
/* 11:   */   private static final long serialVersionUID = 7816572519901334955L;
/* 12:15 */   private DefaultComboBoxModel model = null;
/* 13:16 */   private String[] strArr = null;
/* 14:   */   
/* 15:   */   public void setModel(String[] arr)
/* 16:   */   {
/* 17:19 */     this.strArr = arr;
/* 18:   */     try
/* 19:   */     {
/* 20:21 */       String[] s = new String[this.strArr.length];
/* 21:22 */       for (int i = 0; i < this.strArr.length; i++) {
/* 22:23 */         s[i] = bd.getString(this.strArr[i]);
/* 23:   */       }
/* 24:25 */       this.model = new DefaultComboBoxModel(s);
/* 25:26 */       super.setModel(this.model);
/* 26:   */     }
/* 27:   */     catch (Exception ex)
/* 28:   */     {
/* 29:28 */       super.setModel(this.model);
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   public AAComboBox()
/* 34:   */   {
/* 35:33 */     compList.add(this);
/* 36:34 */     setFont(font12);
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void changeLang()
/* 40:   */   {
/* 41:37 */     setModel(this.strArr);
/* 42:   */   }
/* 43:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AAComboBox
 * JD-Core Version:    0.7.0.1
 */