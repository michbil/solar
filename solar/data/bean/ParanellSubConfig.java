/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class ParanellSubConfig
/*  4:   */ {
/*  5: 6 */   private int outputMode = 0;
/*  6: 7 */   private double maxcharngingcurrent = 20.0D;
/*  7: 8 */   private double maxmaxchargingcurrent = 50.0D;
/*  8: 9 */   private double minmaxchargingcurrent = 0.0D;
/*  9:10 */   private String chargingsource = "Utility";
/* 10:12 */   private double maxacchargingcurrent = 2.0D;
/* 11:   */   
/* 12:   */   public int getOutputMode()
/* 13:   */   {
/* 14:15 */     return this.outputMode;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void setOutputMode(int outputMode)
/* 18:   */   {
/* 19:18 */     this.outputMode = outputMode;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public double getMaxcharngingcurrent()
/* 23:   */   {
/* 24:21 */     return this.maxcharngingcurrent;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setMaxcharngingcurrent(double maxcharngingcurrent)
/* 28:   */   {
/* 29:24 */     this.maxcharngingcurrent = maxcharngingcurrent;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public double getMaxmaxchargingcurrent()
/* 33:   */   {
/* 34:27 */     return this.maxmaxchargingcurrent;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setMaxmaxchargingcurrent(double maxmaxchargingcurrent)
/* 38:   */   {
/* 39:30 */     this.maxmaxchargingcurrent = maxmaxchargingcurrent;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getChargingsource()
/* 43:   */   {
/* 44:33 */     return this.chargingsource;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setChargingsource(String chargingsource)
/* 48:   */   {
/* 49:36 */     this.chargingsource = chargingsource;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public double getMinmaxchargingcurrent()
/* 53:   */   {
/* 54:39 */     return this.minmaxchargingcurrent;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setMinmaxchargingcurrent(double minmaxchargingcurrent)
/* 58:   */   {
/* 59:44 */     this.minmaxchargingcurrent = minmaxchargingcurrent;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setMaxacchargingcurrent(double maxacchargingcurrent)
/* 63:   */   {
/* 64:48 */     this.maxacchargingcurrent = maxacchargingcurrent;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public double getMaxacchargingcurrent()
/* 68:   */   {
/* 69:52 */     return this.maxacchargingcurrent;
/* 70:   */   }
/* 71:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.ParanellSubConfig
 * JD-Core Version:    0.7.0.1
 */