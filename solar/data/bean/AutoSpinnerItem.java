/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class AutoSpinnerItem
/*  4:   */ {
/*  5:   */   private String caption;
/*  6:   */   private String minValue;
/*  7:   */   private String maxValue;
/*  8:   */   private String step;
/*  9:   */   private String value;
/* 10:   */   private String unit;
/* 11:   */   private String buttonName;
/* 12:   */   private boolean enable;
/* 13:   */   
/* 14:   */   public AutoSpinnerItem(String caption, String minValue, String maxValue, String step, String value, String unit, String buttonName, boolean enable)
/* 15:   */   {
/* 16:20 */     this.caption = caption;
/* 17:21 */     this.minValue = minValue;
/* 18:22 */     this.maxValue = maxValue;
/* 19:23 */     this.step = step;
/* 20:24 */     this.value = value;
/* 21:25 */     this.unit = unit;
/* 22:26 */     this.buttonName = buttonName;
/* 23:27 */     this.enable = enable;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String getCaption()
/* 27:   */   {
/* 28:31 */     return this.caption;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setCaption(String caption)
/* 32:   */   {
/* 33:34 */     this.caption = caption;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public String getMinValue()
/* 37:   */   {
/* 38:37 */     return this.minValue;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void setMinValue(String minValue)
/* 42:   */   {
/* 43:40 */     this.minValue = minValue;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public String getMaxValue()
/* 47:   */   {
/* 48:43 */     return this.maxValue;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public void setMaxValue(String maxValue)
/* 52:   */   {
/* 53:46 */     this.maxValue = maxValue;
/* 54:   */   }
/* 55:   */   
/* 56:   */   public String getStep()
/* 57:   */   {
/* 58:49 */     return this.step;
/* 59:   */   }
/* 60:   */   
/* 61:   */   public void setStep(String step)
/* 62:   */   {
/* 63:52 */     this.step = step;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public String getValue()
/* 67:   */   {
/* 68:55 */     return this.value;
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void setValue(String value)
/* 72:   */   {
/* 73:58 */     this.value = value;
/* 74:   */   }
/* 75:   */   
/* 76:   */   public String getUnit()
/* 77:   */   {
/* 78:61 */     return this.unit;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public void setUnit(String unit)
/* 82:   */   {
/* 83:64 */     this.unit = unit;
/* 84:   */   }
/* 85:   */   
/* 86:   */   public String getButtonName()
/* 87:   */   {
/* 88:67 */     return this.buttonName;
/* 89:   */   }
/* 90:   */   
/* 91:   */   public void setButtonName(String buttonName)
/* 92:   */   {
/* 93:70 */     this.buttonName = buttonName;
/* 94:   */   }
/* 95:   */   
/* 96:   */   public boolean isEnable()
/* 97:   */   {
/* 98:73 */     return this.enable;
/* 99:   */   }
/* :0:   */   
/* :1:   */   public void setEnable(boolean enable)
/* :2:   */   {
/* :3:76 */     this.enable = enable;
/* :4:   */   }
/* :5:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.AutoSpinnerItem
 * JD-Core Version:    0.7.0.1
 */