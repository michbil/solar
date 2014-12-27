/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.protocol.P30;
/*  4:   */ 
/*  5:   */ public class ProtocolInfo
/*  6:   */ {
/*  7:16 */   private String prodid = "";
/*  8:18 */   private String serialno = "";
/*  9:   */   private ProductInfo productInfo;
/* 10:   */   private RatingInfo ratingInfo;
/* 11:   */   private PurchaseInfo purchaseInfo;
/* 12:   */   private BaseInfo baseInfo;
/* 13:   */   private MoreInfo moreInfo;
/* 14:   */   
/* 15:   */   public ProtocolInfo()
/* 16:   */   {
/* 17:31 */     P30 p = new P30();
/* 18:32 */     setBaseInfo(p.getBaseInfo());
/* 19:33 */     setProductInfo(p.getProductInfo());
/* 20:34 */     setRatingInfo(p.getRatingInfo());
/* 21:   */   }
/* 22:   */   
/* 23:   */   public String getProdid()
/* 24:   */   {
/* 25:38 */     return this.prodid;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public void setProdid(String prodid)
/* 29:   */   {
/* 30:42 */     this.prodid = prodid;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public String getSerialno()
/* 34:   */   {
/* 35:46 */     return this.serialno;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public void setSerialno(String serialno)
/* 39:   */   {
/* 40:50 */     this.serialno = serialno;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public ProductInfo getProductInfo()
/* 44:   */   {
/* 45:54 */     return this.productInfo;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void setProductInfo(ProductInfo productInfo)
/* 49:   */   {
/* 50:58 */     this.productInfo = productInfo;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public RatingInfo getRatingInfo()
/* 54:   */   {
/* 55:62 */     return this.ratingInfo;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public void setRatingInfo(RatingInfo ratingInfo)
/* 59:   */   {
/* 60:66 */     this.ratingInfo = ratingInfo;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public PurchaseInfo getPurchaseInfo()
/* 64:   */   {
/* 65:70 */     return this.purchaseInfo;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public void setPurchaseInfo(PurchaseInfo purchaseInfo)
/* 69:   */   {
/* 70:74 */     this.purchaseInfo = purchaseInfo;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public BaseInfo getBaseInfo()
/* 74:   */   {
/* 75:78 */     return this.baseInfo;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public void setBaseInfo(BaseInfo baseInfo)
/* 79:   */   {
/* 80:82 */     this.baseInfo = baseInfo;
/* 81:   */   }
/* 82:   */   
/* 83:   */   public MoreInfo getMoreInfo()
/* 84:   */   {
/* 85:86 */     return this.moreInfo;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public void setMoreInfo(MoreInfo moreInfo)
/* 89:   */   {
/* 90:90 */     this.moreInfo = moreInfo;
/* 91:   */   }
/* 92:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.ProtocolInfo
 * JD-Core Version:    0.7.0.1
 */