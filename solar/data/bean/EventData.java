/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class EventData
/*  4:   */ {
/*  5:   */   private String eventId;
/*  6:   */   private int eventLevel;
/*  7:   */   private String eventName;
/*  8:   */   
/*  9:   */   public EventData() {}
/* 10:   */   
/* 11:   */   public EventData(String eventId, int eventLevel, String eventName)
/* 12:   */   {
/* 13:19 */     this.eventId = eventId;
/* 14:20 */     this.eventLevel = eventLevel;
/* 15:21 */     this.eventName = eventName;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public String getEventId()
/* 19:   */   {
/* 20:24 */     return this.eventId;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setEventId(String eventId)
/* 24:   */   {
/* 25:27 */     this.eventId = eventId;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public int getEventLevel()
/* 29:   */   {
/* 30:30 */     return this.eventLevel;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setEventLevel(int eventLevel)
/* 34:   */   {
/* 35:33 */     this.eventLevel = eventLevel;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public String getEventName()
/* 39:   */   {
/* 40:36 */     return this.eventName;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void setEventName(String eventName)
/* 44:   */   {
/* 45:39 */     this.eventName = eventName;
/* 46:   */   }
/* 47:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.EventData
 * JD-Core Version:    0.7.0.1
 */