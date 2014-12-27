/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class AutoComboBoxItem
/*  4:   */ {
/*  5:   */   private String caption;
/*  6:   */   private String[] selects;
/*  7:   */   private String value;
/*  8:   */   private String unit;
/*  9:   */   private String buttonName;
/* 10:   */   private boolean enable;
/* 11:   */   
/* 12:   */   public AutoComboBoxItem(String caption, String[] selects, String value, String unit, String buttonName, boolean enable)
/* 13:   */   {
/* 14:18 */     this.caption = caption;
/* 15:19 */     this.selects = selects;
/* 16:20 */     this.value = value;
/* 17:21 */     this.unit = unit;
/* 18:22 */     this.buttonName = buttonName;
/* 19:23 */     this.enable = enable;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public String getCaption()
/* 23:   */   {
/* 24:27 */     return this.caption;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setCaption(String caption)
/* 28:   */   {
/* 29:31 */     this.caption = caption;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getUnit()
/* 33:   */   {
/* 34:35 */     return this.unit;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setUnit(String unit)
/* 38:   */   {
/* 39:39 */     this.unit = unit;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getButtonName()
/* 43:   */   {
/* 44:43 */     return this.buttonName;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setButtonName(String buttonName)
/* 48:   */   {
/* 49:47 */     this.buttonName = buttonName;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public boolean isEnable()
/* 53:   */   {
/* 54:51 */     return this.enable;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setEnable(boolean enable)
/* 58:   */   {
/* 59:55 */     this.enable = enable;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public String[] getSelects()
/* 63:   */   {
/* 64:59 */     return this.selects;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void setSelects(String[] selects)
/* 68:   */   {
/* 69:63 */     this.selects = selects;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public String getValue()
/* 73:   */   {
/* 74:67 */     return this.value;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public void setValue(String value)
/* 78:   */   {
/* 79:71 */     this.value = value;
/* 80:   */   }
/* 81:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.AutoComboBoxItem
 * JD-Core Version:    0.7.0.1
 */