/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class AutoMenuItem
/*  4:   */ {
/*  5:10 */   private String label = "";
/*  6:   */   private int data;
/*  7:12 */   private String type = "";
/*  8:   */   
/*  9:   */   public AutoMenuItem(String label, int data, String type)
/* 10:   */   {
/* 11:15 */     this.data = data;
/* 12:16 */     this.label = label;
/* 13:17 */     this.type = type;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public String getLabel()
/* 17:   */   {
/* 18:21 */     return this.label;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setLabel(String label)
/* 22:   */   {
/* 23:25 */     this.label = label;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public int getData()
/* 27:   */   {
/* 28:29 */     return this.data;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setData(int data)
/* 32:   */   {
/* 33:33 */     this.data = data;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public String getType()
/* 37:   */   {
/* 38:37 */     return this.type;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void setType(String type)
/* 42:   */   {
/* 43:41 */     this.type = type;
/* 44:   */   }
/* 45:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.AutoMenuItem
 * JD-Core Version:    0.7.0.1
 */