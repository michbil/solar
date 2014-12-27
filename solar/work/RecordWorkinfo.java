/*  1:   */ package cn.com.voltronic.solar.work;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*  4:   */ import cn.com.voltronic.solar.configure.SystemEnv;
/*  5:   */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  6:   */ 
/*  7:   */ public class RecordWorkinfo
/*  8:   */   extends Thread
/*  9:   */ {
/* 10:   */   public void run()
/* 11:   */   {
/* 12:10 */     int count = 0;
/* 13:11 */     while (!SystemEnv.stopping) {
/* 14:12 */       if (count >= GlobalVariables.globalConfig.getDataRecordInterval())
/* 15:   */       {
/* 16:   */         try
/* 17:   */         {
/* 18:14 */           RecordHandler handler = new RecordHandler();
/* 19:15 */           handler.start();
/* 20:   */         }
/* 21:   */         catch (Exception e)
/* 22:   */         {
/* 23:17 */           e.printStackTrace();
/* 24:18 */           break;
/* 25:   */         }
/* 26:20 */         count = 0;
/* 27:   */       }
/* 28:   */       else
/* 29:   */       {
/* 30:   */         try
/* 31:   */         {
/* 32:23 */           sleep(1000L);
/* 33:   */         }
/* 34:   */         catch (Exception localException1) {}
/* 35:27 */         count++;
/* 36:   */       }
/* 37:   */     }
/* 38:31 */     SystemEnv.stoped += 1;
/* 39:   */   }
/* 40:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.work.RecordWorkinfo
 * JD-Core Version:    0.7.0.1
 */