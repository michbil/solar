/*  1:   */ package cn.com.voltronic.solar.system;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  4:   */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  5:   */ import cn.com.voltronic.solar.util.RunTools;
/*  6:   */ 
/*  7:   */ class ProtocolProcessorItem
/*  8:   */ {
/*  9:   */   private Class _processorclass;
/* 10:   */   private Class _protocolclass;
/* 11:   */   private Class _handlerclass;
/* 12:   */   
/* 13:   */   public ProtocolProcessorItem(Class protocolclass, Class handlerclass, Class processorclass)
/* 14:   */   {
/* 15:24 */     this._protocolclass = protocolclass;
/* 16:25 */     this._handlerclass = handlerclass;
/* 17:26 */     this._processorclass = processorclass;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public Class getProcessorclass()
/* 21:   */   {
/* 22:32 */     return this._processorclass;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public Class getProtocolclass()
/* 26:   */   {
/* 27:37 */     return this._protocolclass;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public Class getHandlerclass()
/* 31:   */   {
/* 32:42 */     return this._handlerclass;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public AbstractProcessor getMatchProcess(Object handler)
/* 36:   */   {
/* 37:46 */     AbstractProcessor process = null;
/* 38:47 */     if (handler.getClass() == this._handlerclass)
/* 39:   */     {
/* 40:48 */       IProtocol protocol = (IProtocol)RunTools.newInstance(
/* 41:49 */         this._protocolclass, null);
/* 42:50 */       if ((protocol != null) && (protocol.matchProtocol(handler)))
/* 43:   */       {
/* 44:51 */         process = (AbstractProcessor)RunTools.newInstance(
/* 45:52 */           this._processorclass, new Object[] { handler, protocol });
/* 46:53 */         process.queryMachineInfo();
/* 47:54 */         process.queryConfigData();
/* 48:55 */         process.queryCapability();
/* 49:56 */         process.queryDefaultData();
/* 50:   */       }
/* 51:   */     }
/* 52:59 */     return process;
/* 53:   */   }
/* 54:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.system.ProtocolProcessorItem
 * JD-Core Version:    0.7.0.1
 */