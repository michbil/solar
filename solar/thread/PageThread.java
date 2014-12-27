/*  1:   */ package cn.com.voltronic.solar.thread;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*  4:   */ import cn.com.voltronic.solar.configure.SystemEnv;
/*  5:   */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  6:   */ import cn.com.voltronic.solar.view.MainJFrame;
/*  7:   */ 
/*  8:   */ public class PageThread
/*  9:   */   extends Thread
/* 10:   */ {
/* 11:   */   public void run()
/* 12:   */   {
/* 13:   */     break label43;
/* 14:   */     try
/* 15:   */     {
/* 16:   */       do
/* 17:   */       {
/* 18:19 */         MainJFrame mainFrame = MainJFrame.getNewInstance();
/* 19:20 */         mainFrame.refreshWorkInfo();
/* 20:   */         try
/* 21:   */         {
/* 22:22 */           Thread.sleep(GlobalVariables.globalConfig.getPageRefreshInterval() * 1000 - 5);
/* 23:   */         }
/* 24:   */         catch (InterruptedException e)
/* 25:   */         {
/* 26:24 */           e.printStackTrace();
/* 27:   */         }
/* 28:17 */       } while (!SystemEnv.stopping);
/* 29:   */     }
/* 30:   */     catch (Exception ex)
/* 31:   */     {
/* 32:27 */       ex.printStackTrace();
/* 33:   */     }
/* 34:   */     label43:
/* 35:30 */     SystemEnv.stoped += 1;
/* 36:   */   }
/* 37:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.thread.PageThread
 * JD-Core Version:    0.7.0.1
 */