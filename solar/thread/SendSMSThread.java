/*  1:   */ package cn.com.voltronic.solar.thread;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*  4:   */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*  5:   */ import cn.com.voltronic.solar.configure.SmsConfig;
/*  6:   */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  7:   */ import cn.com.voltronic.solar.util.ModemAdapter;
/*  8:   */ 
/*  9:   */ public class SendSMSThread
/* 10:   */   extends Thread
/* 11:   */ {
/* 12:   */   private String serialno;
/* 13:   */   private String eventName;
/* 14:   */   private String time;
/* 15:   */   private String mobileNum;
/* 16:   */   
/* 17:   */   public SendSMSThread(String serialno, String time, String eventName, String mobileNum)
/* 18:   */   {
/* 19:20 */     this.serialno = serialno;
/* 20:21 */     this.time = time;
/* 21:22 */     this.eventName = eventName;
/* 22:23 */     this.mobileNum = mobileNum;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void run()
/* 26:   */   {
/* 27:28 */     String content = "[" + this.serialno + "] [" + this.time + "] occor " + this.eventName;
/* 28:29 */     if (GlobalVariables.globalConfig.getLanguage().equals("zh_CN")) {
/* 29:30 */       content = "[" + this.serialno + "] [" + this.time + "] 发生  " + this.eventName;
/* 30:   */     } else {
/* 31:32 */       content = "[" + this.serialno + "] [" + this.time + "] occur " + this.eventName;
/* 32:   */     }
/* 33:35 */     SmsConfig smsConfig = GlobalVariables.smsConfig;
/* 34:   */     try
/* 35:   */     {
/* 36:37 */       ConfigureTools.wrapProperties(smsConfig);
/* 37:38 */       if ((this.mobileNum != null) && (!"".equals(this.mobileNum))) {
/* 38:   */         try
/* 39:   */         {
/* 40:40 */           ModemAdapter adapter = new ModemAdapter(smsConfig.getComPort(), Integer.parseInt(smsConfig.getBit()), 
/* 41:41 */             this.mobileNum, content);
/* 42:42 */           adapter.startSendOne();
/* 43:   */         }
/* 44:   */         catch (Exception localException1) {}
/* 45:   */       }
/* 46:   */       return;
/* 47:   */     }
/* 48:   */     catch (Exception e1)
/* 49:   */     {
/* 50:48 */       e1.printStackTrace();
/* 51:   */     }
/* 52:   */   }
/* 53:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.thread.SendSMSThread
 * JD-Core Version:    0.7.0.1
 */