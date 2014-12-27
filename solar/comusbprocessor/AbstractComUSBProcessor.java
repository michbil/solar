/*  1:   */ package cn.com.voltronic.solar.comusbprocessor;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.communicate.ICommunicateDevice;
/*  4:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  5:   */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  6:   */ 
/*  7:   */ public abstract class AbstractComUSBProcessor
/*  8:   */   extends AbstractProcessor
/*  9:   */ {
/* 10:   */   public AbstractComUSBProcessor(ICommunicateDevice handler, IProtocol protocol)
/* 11:   */   {
/* 12:11 */     super(handler, protocol);
/* 13:   */   }
/* 14:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.comusbprocessor.AbstractComUSBProcessor
 * JD-Core Version:    0.7.0.1
 */