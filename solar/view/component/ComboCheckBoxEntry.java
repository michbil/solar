/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ public class ComboCheckBoxEntry
/*  4:   */ {
/*  5:   */   private boolean checked;
/*  6:   */   private boolean enable;
/*  7:   */   private String uniqueCode;
/*  8:   */   private String value;
/*  9:   */   
/* 10:   */   public ComboCheckBoxEntry()
/* 11:   */   {
/* 12:17 */     this.checked = false;
/* 13:18 */     this.enable = true;
/* 14:19 */     this.uniqueCode = "-1";
/* 15:20 */     this.value = "Empty Entry";
/* 16:   */   }
/* 17:   */   
/* 18:   */   public ComboCheckBoxEntry(String uniqueCode, String value)
/* 19:   */   {
/* 20:24 */     this.checked = false;
/* 21:25 */     this.enable = true;
/* 22:26 */     this.uniqueCode = uniqueCode;
/* 23:27 */     this.value = value;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public ComboCheckBoxEntry(boolean checked, String uniqueCode, String value)
/* 27:   */   {
/* 28:31 */     this.checked = checked;
/* 29:32 */     this.enable = true;
/* 30:33 */     this.uniqueCode = uniqueCode;
/* 31:34 */     this.value = value;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public ComboCheckBoxEntry(boolean checked, boolean enable, String uniqueCode, String value)
/* 35:   */   {
/* 36:39 */     this.checked = checked;
/* 37:40 */     this.enable = enable;
/* 38:41 */     this.uniqueCode = uniqueCode;
/* 39:42 */     this.value = value;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public boolean isChecked()
/* 43:   */   {
/* 44:46 */     return this.checked;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setChecked(boolean checked)
/* 48:   */   {
/* 49:50 */     this.checked = checked;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public boolean isEnable()
/* 53:   */   {
/* 54:54 */     return this.enable;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setEnable(boolean enable)
/* 58:   */   {
/* 59:58 */     this.enable = enable;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public String getUniqueCode()
/* 63:   */   {
/* 64:62 */     return this.uniqueCode;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void setUniqueCode(String uniqueCode)
/* 68:   */   {
/* 69:66 */     this.uniqueCode = uniqueCode;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public String getValue()
/* 73:   */   {
/* 74:70 */     return this.value;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public void setValue(String value)
/* 78:   */   {
/* 79:74 */     this.value = value;
/* 80:   */   }
/* 81:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.ComboCheckBoxEntry
 * JD-Core Version:    0.7.0.1
 */