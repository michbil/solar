/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class TotalPower
/*  4:   */ {
/*  5:   */   private int year;
/*  6:   */   private double power;
/*  7:   */   private double money;
/*  8:   */   
/*  9:   */   public TotalPower(int year, double power, double money)
/* 10:   */   {
/* 11:15 */     this.year = year;
/* 12:16 */     this.power = power;
/* 13:17 */     this.money = money;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public int getYear()
/* 17:   */   {
/* 18:21 */     return this.year;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setYear(int year)
/* 22:   */   {
/* 23:25 */     this.year = year;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public double getMoney()
/* 27:   */   {
/* 28:29 */     return this.money;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public void setMoney(double money)
/* 32:   */   {
/* 33:33 */     this.money = money;
/* 34:   */   }
/* 35:   */   
/* 36:   */   public double getPower()
/* 37:   */   {
/* 38:37 */     return this.power;
/* 39:   */   }
/* 40:   */   
/* 41:   */   public void setPower(double power)
/* 42:   */   {
/* 43:41 */     this.power = power;
/* 44:   */   }
/* 45:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.TotalPower
 * JD-Core Version:    0.7.0.1
 */