/*  1:   */ package cn.com.voltronic.solar.comusbprocessor;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*  4:   */ import cn.com.voltronic.solar.communicate.ICommunicateDevice;
/*  5:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  6:   */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  7:   */ import java.util.HashMap;
/*  8:   */ import java.util.Map.Entry;
/*  9:   */ 
/* 10:   */ public class ParallSubProcessor
/* 11:   */   extends AbstractProcessor
/* 12:   */ {
/* 13:   */   protected AbstractProcessor _parentProcessor;
/* 14:   */   
/* 15:   */   public ParallSubProcessor(AbstractProcessor parentProcessor, BeanBag bag)
/* 16:   */   {
/* 17:15 */     super(parentProcessor.getHandler(), parentProcessor.getProtocol());
/* 18:   */     
/* 19:17 */     this._handler.setNotifyProcess(parentProcessor);
/* 20:18 */     this._paralleltype = 2;
/* 21:19 */     this._parentProcessor = parentProcessor;
/* 22:   */     
/* 23:21 */     this._control = this._parentProcessor.getControlModule();
/* 24:   */     
/* 25:23 */     this._beanbag = bag;
/* 26:25 */     for (Map.Entry<String, Object> entry : this._parentProcessor.getBeanBag().getBeansMap().entrySet()) {
/* 27:26 */       if (!((String)entry.getKey()).equalsIgnoreCase("workinfo")) {
/* 28:27 */         this._beanbag.setBean((String)entry.getKey(), entry.getValue());
/* 29:   */       }
/* 30:   */     }
/* 31:30 */     this._protocol = parentProcessor.getProtocol();
/* 32:31 */     this._serialNo = this._protocol.getSerialNo();
/* 33:   */   }
/* 34:   */   
/* 35:   */   protected void initBeanBag() {}
/* 36:   */   
/* 37:   */   protected void initControlModule() {}
/* 38:   */   
/* 39:   */   public void initProtocol() {}
/* 40:   */   
/* 41:   */   public boolean pollQuery()
/* 42:   */   {
/* 43:57 */     return true;
/* 44:   */   }
/* 45:   */   
/* 46:   */   public boolean pollQueryStatus()
/* 47:   */   {
/* 48:63 */     return true;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public boolean queryCapability()
/* 52:   */   {
/* 53:68 */     return this._parentProcessor.queryCapability();
/* 54:   */   }
/* 55:   */   
/* 56:   */   public boolean queryConfigData()
/* 57:   */   {
/* 58:74 */     return this._parentProcessor.queryConfigData();
/* 59:   */   }
/* 60:   */   
/* 61:   */   public boolean queryDefaultData()
/* 62:   */   {
/* 63:79 */     return this._parentProcessor.queryConfigData();
/* 64:   */   }
/* 65:   */   
/* 66:   */   public boolean queryMachineInfo()
/* 67:   */   {
/* 68:84 */     return this._parentProcessor.queryMachineInfo();
/* 69:   */   }
/* 70:   */   
/* 71:   */   public void querySelfTestResult()
/* 72:   */   {
/* 73:89 */     this._parentProcessor.querySelfTestResult();
/* 74:   */   }
/* 75:   */   
/* 76:   */   public boolean supportSelfTest()
/* 77:   */   {
/* 78:95 */     return this._parentProcessor.supportSelfTest();
/* 79:   */   }
/* 80:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.comusbprocessor.ParallSubProcessor
 * JD-Core Version:    0.7.0.1
 */