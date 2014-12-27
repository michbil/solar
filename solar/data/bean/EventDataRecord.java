/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ 
/*  5:   */ public class EventDataRecord
/*  6:   */ {
/*  7:12 */   private String prodId = "";
/*  8:13 */   private String serialNo = "";
/*  9:14 */   private String eventId = "";
/* 10:   */   private Date trandate;
/* 11:   */   
/* 12:   */   public EventDataRecord() {}
/* 13:   */   
/* 14:   */   public EventDataRecord(String prodId, String serialNo, String eventId, Date trandate)
/* 15:   */   {
/* 16:22 */     this.prodId = prodId;
/* 17:23 */     this.serialNo = serialNo;
/* 18:24 */     this.eventId = eventId;
/* 19:25 */     this.trandate = trandate;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public String getProdId()
/* 23:   */   {
/* 24:29 */     return this.prodId;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setProdId(String prodId)
/* 28:   */   {
/* 29:33 */     this.prodId = prodId;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getSerialNo()
/* 33:   */   {
/* 34:37 */     return this.serialNo;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setSerialNo(String serialNo)
/* 38:   */   {
/* 39:41 */     this.serialNo = serialNo;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getEventId()
/* 43:   */   {
/* 44:45 */     return this.eventId;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setEventId(String eventId)
/* 48:   */   {
/* 49:49 */     this.eventId = eventId;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public Date getTrandate()
/* 53:   */   {
/* 54:53 */     return this.trandate;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setTrandate(Date trandate)
/* 58:   */   {
/* 59:57 */     this.trandate = trandate;
/* 60:   */   }
/* 61:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.EventDataRecord
 * JD-Core Version:    0.7.0.1
 */