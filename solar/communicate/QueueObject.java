/*  1:   */ package cn.com.voltronic.solar.communicate;
/*  2:   */ 
/*  3:   */ public class QueueObject
/*  4:   */ {
/*  5: 6 */   private boolean isNotified = false;
/*  6:   */   
/*  7:   */   public synchronized void doWait()
/*  8:   */     throws InterruptedException
/*  9:   */   {
/* 10: 9 */     while (!this.isNotified) {
/* 11:10 */       wait();
/* 12:   */     }
/* 13:12 */     this.isNotified = false;
/* 14:   */   }
/* 15:   */   
/* 16:   */   public synchronized void doNotify()
/* 17:   */   {
/* 18:16 */     this.isNotified = true;
/* 19:17 */     notify();
/* 20:   */   }
/* 21:   */   
/* 22:   */   public boolean equals(Object o)
/* 23:   */   {
/* 24:21 */     return this == o;
/* 25:   */   }
/* 26:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.QueueObject
 * JD-Core Version:    0.7.0.1
 */