/*  1:   */ package cn.com.voltronic.solar.snmpprocessor;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.communicate.SNMPHandler;
/*  4:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  5:   */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  6:   */ 
/*  7:   */ public abstract class AbstractSNMPProcessor
/*  8:   */   extends AbstractProcessor
/*  9:   */ {
/* 10:   */   public AbstractSNMPProcessor(SNMPHandler handler, IProtocol protocol)
/* 11:   */   {
/* 12:10 */     super(handler, protocol);
/* 13:   */   }
/* 14:   */   
/* 15:   */   public SNMPHandler getHandler()
/* 16:   */   {
/* 17:16 */     return (SNMPHandler)super.getHandler();
/* 18:   */   }
/* 19:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.snmpprocessor.AbstractSNMPProcessor
 * JD-Core Version:    0.7.0.1
 */