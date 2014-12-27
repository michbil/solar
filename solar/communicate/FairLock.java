/*  1:   */ package cn.com.voltronic.solar.communicate;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class FairLock
/*  7:   */ {
/*  8: 7 */   private boolean isLocked = false;
/*  9: 8 */   private Thread lockingThread = null;
/* 10:10 */   private List<QueueObject> waitingThreads = new ArrayList();
/* 11:   */   
/* 12:   */   public void lock()
/* 13:   */     throws InterruptedException
/* 14:   */   {
/* 15:13 */     QueueObject queueObject = new QueueObject();
/* 16:14 */     boolean isLockedForThisThread = true;
/* 17:15 */     synchronized (this)
/* 18:   */     {
/* 19:16 */       this.waitingThreads.add(queueObject);
/* 20:   */     }
/* 21:19 */     while (isLockedForThisThread)
/* 22:   */     {
/* 23:20 */       synchronized (this)
/* 24:   */       {
/* 25:21 */         isLockedForThisThread = 
/* 26:22 */           (this.isLocked) || (this.waitingThreads.get(0) != queueObject);
/* 27:23 */         if (!isLockedForThisThread)
/* 28:   */         {
/* 29:24 */           this.isLocked = true;
/* 30:25 */           this.waitingThreads.remove(queueObject);
/* 31:26 */           this.lockingThread = Thread.currentThread();
/* 32:27 */           return;
/* 33:   */         }
/* 34:   */       }
/* 35:   */       try
/* 36:   */       {
/* 37:31 */         queueObject.doWait();
/* 38:   */       }
/* 39:   */       catch (InterruptedException e)
/* 40:   */       {
/* 41:33 */         synchronized (this)
/* 42:   */         {
/* 43:33 */           this.waitingThreads.remove(queueObject);
/* 44:   */         }
/* 45:34 */         throw e;
/* 46:   */       }
/* 47:   */     }
/* 48:   */   }
/* 49:   */   
/* 50:   */   public synchronized void unlock()
/* 51:   */   {
/* 52:40 */     if (this.lockingThread != Thread.currentThread()) {
/* 53:41 */       throw new IllegalMonitorStateException(
/* 54:42 */         "Calling thread has not locked this lock");
/* 55:   */     }
/* 56:44 */     this.isLocked = false;
/* 57:45 */     this.lockingThread = null;
/* 58:46 */     if (this.waitingThreads.size() > 0) {
/* 59:47 */       ((QueueObject)this.waitingThreads.get(0)).doNotify();
/* 60:   */     }
/* 61:   */   }
/* 62:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.FairLock
 * JD-Core Version:    0.7.0.1
 */