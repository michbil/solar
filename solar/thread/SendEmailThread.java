/*  1:   */ package cn.com.voltronic.solar.thread;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*  4:   */ import cn.com.voltronic.solar.configure.EmailConfig;
/*  5:   */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*  6:   */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  7:   */ import cn.com.voltronic.solar.util.EmailSendUtil;
/*  8:   */ import org.apache.commons.lang.StringUtils;
/*  9:   */ 
/* 10:   */ public class SendEmailThread
/* 11:   */   extends Thread
/* 12:   */ {
/* 13:   */   private String serialno;
/* 14:   */   private String eventName;
/* 15:   */   private String time;
/* 16:   */   private String emailReceiver;
/* 17:   */   
/* 18:   */   public SendEmailThread(String serialno, String time, String eventName, String emailReceiver)
/* 19:   */   {
/* 20:22 */     this.serialno = serialno;
/* 21:23 */     this.time = time;
/* 22:24 */     this.eventName = eventName;
/* 23:25 */     this.emailReceiver = emailReceiver;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public void run()
/* 27:   */   {
/* 28:30 */     String content = "[" + this.serialno + "] [" + this.time + "] occor " + this.eventName;
/* 29:31 */     if (GlobalVariables.globalConfig.getLanguage().equals("zh_CN")) {
/* 30:32 */       content = "[" + this.serialno + "] [" + this.time + "] 发生  " + this.eventName;
/* 31:   */     } else {
/* 32:34 */       content = "[" + this.serialno + "] [" + this.time + "] occur " + this.eventName;
/* 33:   */     }
/* 34:37 */     EmailConfig emailConfig = GlobalVariables.emailConfig;
/* 35:   */     try
/* 36:   */     {
/* 37:39 */       ConfigureTools.wrapProperties(emailConfig);
/* 38:40 */       if ((this.emailReceiver != null) && (!"".equals(this.emailReceiver)))
/* 39:   */       {
/* 40:41 */         EmailSendUtil sendmail = new EmailSendUtil(emailConfig);
/* 41:42 */         if ((StringUtils.isNotEmpty(sendmail.getHost())) && 
/* 42:43 */           (StringUtils.isNotEmpty(sendmail.getUsername())) && 
/* 43:44 */           (StringUtils.isNotEmpty(this.emailReceiver))) {
/* 44:45 */           sendmail.sendMail(this.emailReceiver, this.eventName, content);
/* 45:   */         }
/* 46:   */       }
/* 47:   */     }
/* 48:   */     catch (Exception e)
/* 49:   */     {
/* 50:49 */       e.printStackTrace();
/* 51:   */     }
/* 52:   */   }
/* 53:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.thread.SendEmailThread
 * JD-Core Version:    0.7.0.1
 */