/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   4:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*   5:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   6:    */ 
/*   7:    */ class QueryParameterThread
/*   8:    */   extends Thread
/*   9:    */ {
/*  10:    */   public void run()
/*  11:    */   {
/*  12:    */     try
/*  13:    */     {
/*  14:171 */       AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/*  15:173 */       if (processor != null)
/*  16:    */       {
/*  17:174 */         ParameterJDialog.startQueryThread = true;
/*  18:175 */         processor.getProtocol().setDelayChanging(true);
/*  19:176 */         processor.queryMachineInfo();
/*  20:177 */         processor.queryCapability();
/*  21:178 */         processor.queryConfigData();
/*  22:    */       }
/*  23:    */     }
/*  24:    */     catch (Exception ex)
/*  25:    */     {
/*  26:182 */       ex.printStackTrace();
/*  27:    */     }
/*  28:    */     finally
/*  29:    */     {
/*  30:184 */       ParameterJDialog.startQueryThread = false;
/*  31:    */     }
/*  32:    */   }
/*  33:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.QueryParameterThread
 * JD-Core Version:    0.7.0.1
 */