/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class HistoryData
/*  4:   */ {
/*  5:   */   private String name;
/*  6:   */   private String value;
/*  7:   */   private boolean isView;
/*  8:   */   private boolean enable;
/*  9:   */   
/* 10:   */   public HistoryData(String name, String value, boolean isView, boolean enable)
/* 11:   */   {
/* 12:16 */     this.name = name;
/* 13:17 */     this.value = value;
/* 14:18 */     this.isView = isView;
/* 15:19 */     this.enable = enable;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String getName()
/* 19:   */   {
/* 20:23 */     return this.name;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setName(String name)
/* 24:   */   {
/* 25:26 */     this.name = name;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getValue()
/* 29:   */   {
/* 30:29 */     return this.value;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setValue(String value)
/* 34:   */   {
/* 35:32 */     this.value = value;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public boolean isView()
/* 39:   */   {
/* 40:35 */     return this.isView;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void setView(boolean isView)
/* 44:   */   {
/* 45:38 */     this.isView = isView;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public boolean isEnable()
/* 49:   */   {
/* 50:42 */     return this.enable;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void setEnable(boolean enable)
/* 54:   */   {
/* 55:46 */     this.enable = enable;
/* 56:   */   }
/* 57:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.HistoryData
 * JD-Core Version:    0.7.0.1
 */