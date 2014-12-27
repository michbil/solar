/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ 
/*  5:   */ public class BuyInfo
/*  6:   */ {
/*  7:12 */   private String prodid = "";
/*  8:14 */   private String serialno = "";
/*  9:16 */   private Date purchasedate = new Date();
/* 10:18 */   private Date batpurchasedate = new Date();
/* 11:   */   private int warranty;
/* 12:   */   private int batwarranty;
/* 13:24 */   private String pncode = "";
/* 14:   */   private boolean useprompt;
/* 15:   */   private int batuseperiod;
/* 16:   */   
/* 17:   */   public Date getPurchasedate()
/* 18:   */   {
/* 19:31 */     return this.purchasedate;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void setPurchasedate(Date purchasedate)
/* 23:   */   {
/* 24:35 */     this.purchasedate = purchasedate;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public Date getBatpurchasedate()
/* 28:   */   {
/* 29:39 */     return this.batpurchasedate;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void setBatpurchasedate(Date batpurchasedate)
/* 33:   */   {
/* 34:43 */     this.batpurchasedate = batpurchasedate;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public int getWarranty()
/* 38:   */   {
/* 39:47 */     return this.warranty;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setWarranty(int warranty)
/* 43:   */   {
/* 44:51 */     this.warranty = warranty;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public int getBatwarranty()
/* 48:   */   {
/* 49:55 */     return this.batwarranty;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void setBatwarranty(int batwarranty)
/* 53:   */   {
/* 54:59 */     this.batwarranty = batwarranty;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public String getPncode()
/* 58:   */   {
/* 59:63 */     return this.pncode;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setPncode(String pncode)
/* 63:   */   {
/* 64:67 */     this.pncode = pncode;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public boolean isUseprompt()
/* 68:   */   {
/* 69:71 */     return this.useprompt;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void setUseprompt(boolean useprompt)
/* 73:   */   {
/* 74:75 */     this.useprompt = useprompt;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public int getBatuseperiod()
/* 78:   */   {
/* 79:79 */     return this.batuseperiod;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public void setBatuseperiod(int batuseperiod)
/* 83:   */   {
/* 84:83 */     this.batuseperiod = batuseperiod;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public String getProdid()
/* 88:   */   {
/* 89:87 */     return this.prodid;
/* 90:   */   }
/* 91:   */   
/* 92:   */   public void setProdid(String prodid)
/* 93:   */   {
/* 94:91 */     this.prodid = prodid;
/* 95:   */   }
/* 96:   */   
/* 97:   */   public String getSerialno()
/* 98:   */   {
/* 99:95 */     return this.serialno;
/* :0:   */   }
/* :1:   */   
/* :2:   */   public void setSerialno(String serialno)
/* :3:   */   {
/* :4:99 */     this.serialno = serialno;
/* :5:   */   }
/* :6:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.BuyInfo
 * JD-Core Version:    0.7.0.1
 */