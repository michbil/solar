/*   1:    */ package cn.com.voltronic.solar.exception;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.dao.FaultDataDao;
/*   4:    */ import cn.com.voltronic.solar.data.bean.DataBeforeFault;
/*   5:    */ 
/*   6:    */ public class FaultHandler
/*   7:    */ {
/*   8:    */   private static String faultEvent;
/*   9:    */   
/*  10:    */   public static void doFaultEvent(String faultKind, String prodid, String serialno, DataBeforeFault data)
/*  11:    */   {
/*  12: 26 */     if (faultKind.equals("01")) {
/*  13: 27 */       recordEvent(prodid, serialno, "1001", data);
/*  14: 28 */     } else if ("02".equals(faultKind)) {
/*  15: 29 */       recordEvent(prodid, serialno, "1002", data);
/*  16: 30 */     } else if ("03".equals(faultKind)) {
/*  17: 31 */       recordEvent(prodid, serialno, "1003", data);
/*  18: 32 */     } else if ("04".equals(faultKind)) {
/*  19: 33 */       recordEvent(prodid, serialno, "1004", data);
/*  20: 34 */     } else if ("05".equals(faultKind)) {
/*  21: 35 */       recordEvent(prodid, serialno, "1005", data);
/*  22: 36 */     } else if ("06".equals(faultKind)) {
/*  23: 37 */       recordEvent(prodid, serialno, "1006", data);
/*  24: 38 */     } else if ("07".equals(faultKind)) {
/*  25: 39 */       recordEvent(prodid, serialno, "1007", data);
/*  26: 40 */     } else if ("08".equals(faultKind)) {
/*  27: 41 */       recordEvent(prodid, serialno, "1008", data);
/*  28: 42 */     } else if ("09".equals(faultKind)) {
/*  29: 43 */       recordEvent(prodid, serialno, "1009", data);
/*  30: 44 */     } else if ("10".equals(faultKind)) {
/*  31: 45 */       recordEvent(prodid, serialno, "1010", data);
/*  32: 46 */     } else if ("11".equals(faultKind)) {
/*  33: 47 */       recordEvent(prodid, serialno, "1011", data);
/*  34: 48 */     } else if ("12".equals(faultKind)) {
/*  35: 49 */       recordEvent(prodid, serialno, "1012", data);
/*  36: 50 */     } else if ("13".equals(faultKind)) {
/*  37: 51 */       recordEvent(prodid, serialno, "1013", data);
/*  38: 52 */     } else if ("14".equals(faultKind)) {
/*  39: 53 */       recordEvent(prodid, serialno, "1014", data);
/*  40: 54 */     } else if ("15".equals(faultKind)) {
/*  41: 55 */       recordEvent(prodid, serialno, "1015", data);
/*  42: 56 */     } else if ("16".equals(faultKind)) {
/*  43: 57 */       recordEvent(prodid, serialno, "1016", data);
/*  44: 58 */     } else if ("17".equals(faultKind)) {
/*  45: 59 */       recordEvent(prodid, serialno, "1017", data);
/*  46: 60 */     } else if ("18".equals(faultKind)) {
/*  47: 61 */       recordEvent(prodid, serialno, "1018", data);
/*  48: 62 */     } else if ("19".equals(faultKind)) {
/*  49: 63 */       recordEvent(prodid, serialno, "1019", data);
/*  50: 64 */     } else if ("20".equals(faultKind)) {
/*  51: 65 */       recordEvent(prodid, serialno, "1020", data);
/*  52: 66 */     } else if ("21".equals(faultKind)) {
/*  53: 67 */       recordEvent(prodid, serialno, "1021", data);
/*  54: 68 */     } else if ("22".equals(faultKind)) {
/*  55: 69 */       recordEvent(prodid, serialno, "1022", data);
/*  56: 70 */     } else if ("23".equals(faultKind)) {
/*  57: 71 */       recordEvent(prodid, serialno, "1023", data);
/*  58: 72 */     } else if ("24".equals(faultKind)) {
/*  59: 73 */       recordEvent(prodid, serialno, "1024", data);
/*  60: 74 */     } else if ("25".equals(faultKind)) {
/*  61: 75 */       recordEvent(prodid, serialno, "1025", data);
/*  62: 76 */     } else if ("26".equals(faultKind)) {
/*  63: 77 */       recordEvent(prodid, serialno, "1026", data);
/*  64: 78 */     } else if ("27".equals(faultKind)) {
/*  65: 79 */       recordEvent(prodid, serialno, "1027", data);
/*  66:    */     }
/*  67:    */   }
/*  68:    */   
/*  69:    */   private static final void recordEvent(String prodid, String serialno, String eventCode, DataBeforeFault data)
/*  70:    */   {
/*  71: 87 */     faultEvent = eventCode;
/*  72: 88 */     data.setFaultString(eventCode);
/*  73: 89 */     FaultDataDao dao = new FaultDataDao();
/*  74: 90 */     if (!dao.alreadyInsert(data))
/*  75:    */     {
/*  76: 91 */       dao.insertData(data);
/*  77: 92 */       EventsHandler.handleEvent(prodid, serialno, data.getTrandate(), 
/*  78: 93 */         eventCode);
/*  79:    */     }
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static final void removeFaultEvent(String prodid, String serialno)
/*  83:    */   {
/*  84:102 */     faultEvent = null;
/*  85:    */   }
/*  86:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.exception.FaultHandler
 * JD-Core Version:    0.7.0.1
 */