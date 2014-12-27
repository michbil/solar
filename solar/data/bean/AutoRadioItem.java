/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class AutoRadioItem
/*  4:   */ {
/*  5:   */   private String caption;
/*  6:   */   private String radioLabel1;
/*  7:   */   private String radioLabel2;
/*  8:   */   private String value;
/*  9:   */   private String buttonName;
/* 10:   */   private boolean enable;
/* 11:   */   
/* 12:   */   public AutoRadioItem(String caption, String radioLabel1, String radioLabel2, String value, String buttonName, boolean enable)
/* 13:   */   {
/* 14:18 */     this.caption = caption;
/* 15:19 */     this.radioLabel1 = radioLabel1;
/* 16:20 */     this.radioLabel2 = radioLabel2;
/* 17:21 */     this.value = value;
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
/* 29:30 */     this.caption = caption;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getRadioLabel1()
/* 33:   */   {
/* 34:33 */     return this.radioLabel1;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setRadioLabel1(String radioLabel1)
/* 38:   */   {
/* 39:36 */     this.radioLabel1 = radioLabel1;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getRadioLabel2()
/* 43:   */   {
/* 44:39 */     return this.radioLabel2;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setRadioLabel2(String radioLabel2)
/* 48:   */   {
/* 49:42 */     this.radioLabel2 = radioLabel2;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public String getValue()
/* 53:   */   {
/* 54:45 */     return this.value;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setValue(String value)
/* 58:   */   {
/* 59:48 */     this.value = value;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public String getButtonName()
/* 63:   */   {
/* 64:51 */     return this.buttonName;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void setButtonName(String buttonName)
/* 68:   */   {
/* 69:54 */     this.buttonName = buttonName;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public boolean isEnable()
/* 73:   */   {
/* 74:57 */     return this.enable;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public void setEnable(boolean enable)
/* 78:   */   {
/* 79:60 */     this.enable = enable;
/* 80:   */   }
/* 81:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.AutoRadioItem
 * JD-Core Version:    0.7.0.1
 */