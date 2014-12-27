/*  1:   */ package cn.com.voltronic.solar.work;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.SystemEnv;
/*  4:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  5:   */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  6:   */ import java.util.Collection;
/*  7:   */ 
/*  8:   */ public class PollProcessorStatus
/*  9:   */   extends Thread
/* 10:   */ {
/* 11:   */   private static final int MAX_COUNT = 20;
/* 12:   */   
/* 13:   */   public void run()
/* 14:   */   {
/* 15:15 */     long currenttime = System.currentTimeMillis();
/* 16:16 */     while (!SystemEnv.stopping) {
/* 17:   */       try
/* 18:   */       {
/* 19:18 */         int count = 0;
/* 20:19 */         Collection<AbstractProcessor> processors = 
/* 21:20 */           GlobalProcessors.getProcessersCopy();
/* 22:21 */         currenttime = System.currentTimeMillis();
/* 23:22 */         for (AbstractProcessor processor : processors)
/* 24:   */         {
/* 25:23 */           if (SystemEnv.stopping) {
/* 26:   */             break;
/* 27:   */           }
/* 28:30 */           if ((processor != null) && (!processor.isClosing()) && 
/* 29:31 */             (processor.getHandler() != null))
/* 30:   */           {
/* 31:32 */             processor.pollQueryStatus();
/* 32:33 */             count++;
/* 33:34 */             if (count >= 20)
/* 34:   */             {
/* 35:35 */               sleep(20L);
/* 36:36 */               count = 0;
/* 37:   */             }
/* 38:   */           }
/* 39:   */         }
/* 40:40 */         long diff = System.currentTimeMillis() - currenttime;
/* 41:41 */         int sleeptime = 1000;
/* 42:42 */         if (diff < 4000L)
/* 43:   */         {
/* 44:43 */           if (processors.size() < 20) {
/* 45:44 */             sleeptime = 2500;
/* 46:   */           } else {
/* 47:46 */             sleeptime = 4000;
/* 48:   */           }
/* 49:   */         }
/* 50:48 */         else if (diff < 8000L) {
/* 51:49 */           sleeptime = 5000;
/* 52:   */         } else {
/* 53:51 */           sleeptime = 6000;
/* 54:   */         }
/* 55:53 */         sleep(sleeptime);
/* 56:   */       }
/* 57:   */       catch (Exception e)
/* 58:   */       {
/* 59:56 */         e.printStackTrace();
/* 60:   */       }
/* 61:   */     }
/* 62:   */   }
/* 63:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.work.PollProcessorStatus
 * JD-Core Version:    0.7.0.1
 */