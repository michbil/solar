/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class HistoryChart
/*  4:   */ {
/*  5:   */   private String name;
/*  6:   */   private String value;
/*  7:   */   private double maximum;
/*  8:   */   private String unit;
/*  9:   */   private boolean isView;
/* 10:   */   
/* 11:   */   public HistoryChart(String name, String value, double maximum, String unit, boolean isView)
/* 12:   */   {
/* 13:17 */     this.name = name;
/* 14:18 */     this.value = value;
/* 15:19 */     this.maximum = maximum;
/* 16:20 */     this.unit = unit;
/* 17:21 */     this.isView = isView;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public double getMaximum()
/* 21:   */   {
/* 22:25 */     return this.maximum;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setMaximum(double maximum)
/* 26:   */   {
/* 27:29 */     this.maximum = maximum;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public String getName()
/* 31:   */   {
/* 32:33 */     return this.name;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setName(String name)
/* 36:   */   {
/* 37:37 */     this.name = name;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public String getValue()
/* 41:   */   {
/* 42:41 */     return this.value;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setValue(String value)
/* 46:   */   {
/* 47:45 */     this.value = value;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public String getUnit()
/* 51:   */   {
/* 52:49 */     return this.unit;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public void setUnit(String unit)
/* 56:   */   {
/* 57:53 */     this.unit = unit;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public boolean isView()
/* 61:   */   {
/* 62:57 */     return this.isView;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public void setView(boolean isView)
/* 66:   */   {
/* 67:61 */     this.isView = isView;
/* 68:   */   }
/* 69:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.HistoryChart
 * JD-Core Version:    0.7.0.1
 */