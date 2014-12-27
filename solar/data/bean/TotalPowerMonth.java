/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class TotalPowerMonth
/*  4:   */ {
/*  5:   */   private String yearmonth;
/*  6:   */   private double power;
/*  7:   */   private double money;
/*  8:   */   
/*  9:   */   public TotalPowerMonth(String yearmonth, double power, double money)
/* 10:   */   {
/* 11:15 */     this.yearmonth = yearmonth;
/* 12:16 */     this.power = power;
/* 13:17 */     this.money = money;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public String getYearmonth()
/* 17:   */   {
/* 18:21 */     return this.yearmonth;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setYearmonth(String yearmonth)
/* 22:   */   {
/* 23:25 */     this.yearmonth = yearmonth;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public double getPower()
/* 27:   */   {
/* 28:29 */     return this.power;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setPower(double power)
/* 32:   */   {
/* 33:33 */     this.power = power;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public double getMoney()
/* 37:   */   {
/* 38:37 */     return this.money;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void setMoney(double money)
/* 42:   */   {
/* 43:41 */     this.money = money;
/* 44:   */   }
/* 45:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.TotalPowerMonth
 * JD-Core Version:    0.7.0.1
 */