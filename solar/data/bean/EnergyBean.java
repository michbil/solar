/*   1:    */ package cn.com.voltronic.solar.data.bean;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.util.VolUtil;
/*   4:    */ import java.io.Serializable;
/*   5:    */ import java.util.Calendar;
/*   6:    */ 
/*   7:    */ public class EnergyBean
/*   8:    */   implements Serializable
/*   9:    */ {
/*  10:    */   private static final long serialVersionUID = -3675406613612091789L;
/*  11:    */   private int queryperiod;
/*  12: 11 */   private double price = 0.0D;
/*  13:    */   private int year;
/*  14: 13 */   private String yearmonth = "";
/*  15:    */   private int hour;
/*  16:    */   private Calendar trandate;
/*  17:    */   private Calendar enddate;
/*  18: 17 */   private double quantity = 0.0D;
/*  19: 18 */   private double amount = 0.0D;
/*  20:    */   private boolean complete;
/*  21:    */   
/*  22:    */   public boolean isComplete()
/*  23:    */   {
/*  24: 22 */     return this.complete;
/*  25:    */   }
/*  26:    */   
/*  27:    */   public void setComplete(boolean complete)
/*  28:    */   {
/*  29: 26 */     this.complete = complete;
/*  30:    */   }
/*  31:    */   
/*  32:    */   public int getQueryperiod()
/*  33:    */   {
/*  34: 30 */     return this.queryperiod;
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void setQueryperiod(int queryperiod)
/*  38:    */   {
/*  39: 34 */     this.queryperiod = queryperiod;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public double getPrice()
/*  43:    */   {
/*  44: 38 */     return this.price;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void setPrice(double price)
/*  48:    */   {
/*  49: 42 */     this.price = price;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public int getYear()
/*  53:    */   {
/*  54: 46 */     return this.year;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void setYear(int year)
/*  58:    */   {
/*  59: 50 */     this.year = year;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public String getYearmonth()
/*  63:    */   {
/*  64: 54 */     return this.yearmonth;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setYearmonth(String yearmonth)
/*  68:    */   {
/*  69: 58 */     this.yearmonth = yearmonth;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public Calendar getEnddate()
/*  73:    */   {
/*  74: 62 */     return this.enddate;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public void setEnddate(Calendar enddate)
/*  78:    */   {
/*  79: 66 */     this.enddate = enddate;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public int getHour()
/*  83:    */   {
/*  84: 70 */     return this.hour;
/*  85:    */   }
/*  86:    */   
/*  87:    */   public void setHour(int hour)
/*  88:    */   {
/*  89: 74 */     this.hour = hour;
/*  90:    */   }
/*  91:    */   
/*  92:    */   public Calendar getTrandate()
/*  93:    */   {
/*  94: 78 */     return this.trandate;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public void setTrandate(Calendar trandate)
/*  98:    */   {
/*  99: 82 */     this.trandate = trandate;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public double getQuantity()
/* 103:    */   {
/* 104: 86 */     return VolUtil.round(this.quantity, 3);
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void setQuantity(double quantity)
/* 108:    */   {
/* 109: 90 */     this.quantity = quantity;
/* 110:    */   }
/* 111:    */   
/* 112:    */   public double getAmount()
/* 113:    */   {
/* 114: 94 */     if (this.amount == 0.0D) {
/* 115: 95 */       return VolUtil.round(this.quantity * this.price, 2);
/* 116:    */     }
/* 117: 97 */     return VolUtil.round(this.amount, 2);
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void setAmount(double amount)
/* 121:    */   {
/* 122:101 */     this.amount = amount;
/* 123:    */   }
/* 124:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.EnergyBean
 * JD-Core Version:    0.7.0.1
 */