/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.io.Serializable;
/*  4:   */ 
/*  5:   */ public class LocalComs
/*  6:   */   implements Serializable
/*  7:   */ {
/*  8:   */   private static final long serialVersionUID = -4512316891642931881L;
/*  9:   */   private String comName;
/* 10:   */   private boolean selected;
/* 11:   */   private boolean used;
/* 12:   */   
/* 13:   */   public boolean isUsed()
/* 14:   */   {
/* 15:19 */     return this.used;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void setUsed(boolean used)
/* 19:   */   {
/* 20:22 */     this.used = used;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getComName()
/* 24:   */   {
/* 25:25 */     return this.comName;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setComName(String comName)
/* 29:   */   {
/* 30:28 */     this.comName = comName;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public boolean isSelected()
/* 34:   */   {
/* 35:31 */     return this.selected;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void setSelected(boolean selected)
/* 39:   */   {
/* 40:34 */     this.selected = selected;
/* 41:   */   }
/* 42:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.LocalComs
 * JD-Core Version:    0.7.0.1
 */