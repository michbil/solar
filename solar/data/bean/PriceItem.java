/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.util.Calendar;
/*  4:   */ 
/*  5:   */ public class PriceItem
/*  6:   */ {
/*  7:   */   private Calendar startdate;
/*  8:   */   private Calendar enddate;
/*  9:   */   private double price;
/* 10:   */   
/* 11:   */   public PriceItem(Calendar startdate, Calendar enddate, double price)
/* 12:   */   {
/* 13:17 */     this.startdate = startdate;
/* 14:18 */     this.enddate = enddate;
/* 15:19 */     this.price = price;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public PriceItem() {}
/* 19:   */   
/* 20:   */   public Calendar getStartdate()
/* 21:   */   {
/* 22:25 */     return this.startdate;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setStartdate(Calendar startdate)
/* 26:   */   {
/* 27:28 */     this.startdate = startdate;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public Calendar getEnddate()
/* 31:   */   {
/* 32:31 */     return this.enddate;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setEnddate(Calendar enddate)
/* 36:   */   {
/* 37:34 */     this.enddate = enddate;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public double getPrice()
/* 41:   */   {
/* 42:37 */     return this.price;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setPrice(double price)
/* 46:   */   {
/* 47:40 */     this.price = price;
/* 48:   */   }
/* 49:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.PriceItem
 * JD-Core Version:    0.7.0.1
 */