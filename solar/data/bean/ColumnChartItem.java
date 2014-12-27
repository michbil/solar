/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class ColumnChartItem
/*  4:   */ {
/*  5:11 */   private String showName = "";
/*  6:13 */   private String horizontalValue = "";
/*  7:15 */   private String verticalValue = "";
/*  8:   */   
/*  9:   */   public ColumnChartItem(String displayName, String xField, String yField)
/* 10:   */   {
/* 11:18 */     this.showName = displayName;
/* 12:19 */     this.horizontalValue = xField;
/* 13:20 */     this.verticalValue = yField;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public String getShowName()
/* 17:   */   {
/* 18:24 */     return this.showName;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setShowName(String showName)
/* 22:   */   {
/* 23:28 */     this.showName = showName;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public String getHorizontalValue()
/* 27:   */   {
/* 28:32 */     return this.horizontalValue;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setHorizontalValue(String horizontalValue)
/* 32:   */   {
/* 33:36 */     this.horizontalValue = horizontalValue;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public String getVerticalValue()
/* 37:   */   {
/* 38:40 */     return this.verticalValue;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void setVerticalValue(String verticalValue)
/* 42:   */   {
/* 43:44 */     this.verticalValue = verticalValue;
/* 44:   */   }
/* 45:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.ColumnChartItem
 * JD-Core Version:    0.7.0.1
 */