/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ public class CheckBoxData
/*  4:   */ {
/*  5:   */   private String name;
/*  6:   */   private boolean isSelected;
/*  7:   */   private boolean enable;
/*  8:   */   
/*  9:   */   public CheckBoxData() {}
/* 10:   */   
/* 11:   */   public CheckBoxData(String name, boolean isSelected, boolean enable)
/* 12:   */   {
/* 13:19 */     this.name = name;
/* 14:20 */     this.isSelected = isSelected;
/* 15:21 */     this.enable = enable;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String getName()
/* 19:   */   {
/* 20:25 */     return this.name;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setName(String name)
/* 24:   */   {
/* 25:29 */     this.name = name;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public boolean isSelected()
/* 29:   */   {
/* 30:33 */     return this.isSelected;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setSelected(boolean isSelected)
/* 34:   */   {
/* 35:37 */     this.isSelected = isSelected;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void invertSelected()
/* 39:   */   {
/* 40:41 */     this.isSelected = (!this.isSelected);
/* 41:   */   }
/* 42:   */   
/* 43:   */   public boolean isEnable()
/* 44:   */   {
/* 45:45 */     return this.enable;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void setEnable(boolean enable)
/* 49:   */   {
/* 50:49 */     this.enable = enable;
/* 51:   */   }
/* 52:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.CheckBoxData
 * JD-Core Version:    0.7.0.1
 */