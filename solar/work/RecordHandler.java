/*  1:   */ package cn.com.voltronic.solar.work;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.SystemEnv;
/*  4:   */ import cn.com.voltronic.solar.dao.IDao;
/*  5:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  6:   */ import cn.com.voltronic.solar.system.DaoManager;
/*  7:   */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  8:   */ import java.util.Collection;
/*  9:   */ 
/* 10:   */ public class RecordHandler
/* 11:   */   extends Thread
/* 12:   */ {
/* 13:   */   public void run()
/* 14:   */   {
/* 15:   */     try
/* 16:   */     {
/* 17:17 */       Collection<AbstractProcessor> processors = 
/* 18:18 */         GlobalProcessors.getProcessersCopy();
/* 19:19 */       for (AbstractProcessor processor : processors)
/* 20:   */       {
/* 21:21 */         if ((processor != null) && (!processor.isClosing()) && 
/* 22:22 */           (processor.reconnectTimes == 0))
/* 23:   */         {
/* 24:23 */           IDao dao = 
/* 25:24 */             DaoManager.getDaoInstance(processor.getBeanBag());
/* 26:   */           
/* 27:26 */           dao.setBeanBag(processor.getBeanBag());
/* 28:27 */           dao.writeRecords();
/* 29:   */         }
/* 30:32 */         if (SystemEnv.stopping) {
/* 31:   */           break;
/* 32:   */         }
/* 33:   */       }
/* 34:   */     }
/* 35:   */     catch (Exception e)
/* 36:   */     {
/* 37:37 */       e.printStackTrace();
/* 38:   */     }
/* 39:   */   }
/* 40:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.work.RecordHandler
 * JD-Core Version:    0.7.0.1
 */