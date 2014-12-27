/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   4:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   5:    */ 
/*   6:    */ class QueryRestoreThread
/*   7:    */   extends Thread
/*   8:    */ {
/*   9:    */   public void run()
/*  10:    */   {
/*  11:    */     try
/*  12:    */     {
/*  13:226 */       AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/*  14:227 */       if (processor != null)
/*  15:    */       {
/*  16:228 */         processor.queryMachineInfo();
/*  17:229 */         processor.queryDefaultData();
/*  18:230 */         processor.queryConfigData();
/*  19:    */       }
/*  20:    */     }
/*  21:    */     catch (Exception localException) {}
/*  22:    */   }
/*  23:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.QueryRestoreThread
 * JD-Core Version:    0.7.0.1
 */