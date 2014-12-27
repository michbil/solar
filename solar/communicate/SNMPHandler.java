/*  1:   */ package cn.com.voltronic.solar.communicate;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  4:   */ 
/*  5:   */ public class SNMPHandler
/*  6:   */   implements ICommunicateDevice
/*  7:   */ {
/*  8:   */   protected AbstractProcessor notifyProcesser;
/*  9:   */   
/* 10:   */   public String getDeviceName()
/* 11:   */   {
/* 12:12 */     return null;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public void close() {}
/* 16:   */   
/* 17:   */   public void setNotifyProcess(AbstractProcessor process)
/* 18:   */   {
/* 19:23 */     this.notifyProcesser = process;
/* 20:   */   }
/* 21:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.SNMPHandler
 * JD-Core Version:    0.7.0.1
 */