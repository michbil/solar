/*   1:    */ package cn.com.voltronic.solar.processor;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   4:    */ import cn.com.voltronic.solar.configure.SystemEnv;
/*   5:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   6:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   7:    */ 
/*   8:    */ class WorkMonitor
/*   9:    */   extends Thread
/*  10:    */ {
/*  11:    */   private AbstractProcessor processer;
/*  12:    */   
/*  13:    */   public WorkMonitor(AbstractProcessor processer)
/*  14:    */   {
/*  15:340 */     this.processer = processer;
/*  16:    */   }
/*  17:    */   
/*  18:    */   public void run()
/*  19:    */   {
/*  20:345 */     int count = 0;
/*  21:346 */     while ((!SystemEnv.stopping) && (!this.processer.isClosing()) && 
/*  22:347 */       (this.processer.getHandler() != null)) {
/*  23:    */       try
/*  24:    */       {
/*  25:349 */         if (count == 0)
/*  26:    */         {
/*  27:350 */           this.processer.pollQuery();
/*  28:    */         }
/*  29:    */         else
/*  30:    */         {
/*  31:352 */           sleep(1000L);
/*  32:353 */           if (this.processer.isfirstquery) {
/*  33:354 */             count = 
/*  34:355 */               GlobalVariables.globalConfig.getPageRefreshInterval();
/*  35:    */           }
/*  36:    */         }
/*  37:358 */         count++;
/*  38:359 */         int maxwait = GlobalVariables.globalConfig
/*  39:360 */           .getDataRecordInterval();
/*  40:361 */         if (getPriority() == 10) {
/*  41:362 */           maxwait = 
/*  42:363 */             GlobalVariables.globalConfig.getPageRefreshInterval();
/*  43:    */         }
/*  44:365 */         if (count >= maxwait) {
/*  45:366 */           count = 0;
/*  46:    */         }
/*  47:368 */         if (GlobalProcessors.findProcessor(this.processer.processorKey()) == null) {
/*  48:369 */           this.processer.close();
/*  49:    */         }
/*  50:    */       }
/*  51:    */       catch (Exception e)
/*  52:    */       {
/*  53:373 */         e.printStackTrace();
/*  54:    */       }
/*  55:    */     }
/*  56:    */   }
/*  57:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.processor.WorkMonitor
 * JD-Core Version:    0.7.0.1
 */