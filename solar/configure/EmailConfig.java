/*  1:   */ package cn.com.voltronic.solar.configure;
/*  2:   */ 
/*  3:   */ public class EmailConfig
/*  4:   */   implements IConfigBean
/*  5:   */ {
/*  6:13 */   private String smtp = "smtp.test.com";
/*  7:14 */   private int port = 25;
/*  8:15 */   private String account = "account";
/*  9:16 */   private Boolean needAuth = Boolean.valueOf(true);
/* 10:17 */   private String password = "";
/* 11:18 */   private String sender = "account@test.com";
/* 12:19 */   private Boolean isShowExchange = Boolean.valueOf(false);
/* 13:   */   private String recievers;
/* 14:   */   
/* 15:   */   public String getRecievers()
/* 16:   */   {
/* 17:23 */     return this.recievers;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setRecievers(String recievers)
/* 21:   */   {
/* 22:27 */     this.recievers = recievers;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public String getSmtp()
/* 26:   */   {
/* 27:31 */     return this.smtp;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setSmtp(String smtp)
/* 31:   */   {
/* 32:35 */     this.smtp = smtp;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public int getPort()
/* 36:   */   {
/* 37:39 */     return this.port;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void setPort(int port)
/* 41:   */   {
/* 42:43 */     this.port = port;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public String getAccount()
/* 46:   */   {
/* 47:47 */     return this.account;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void setAccount(String account)
/* 51:   */   {
/* 52:51 */     this.account = account;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public Boolean getNeedAuth()
/* 56:   */   {
/* 57:55 */     return this.needAuth;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void setNeedAuth(Boolean needAuth)
/* 61:   */   {
/* 62:59 */     this.needAuth = needAuth;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public String getPassword()
/* 66:   */   {
/* 67:63 */     return this.password;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void setPassword(String password)
/* 71:   */   {
/* 72:67 */     this.password = password;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public String getSender()
/* 76:   */   {
/* 77:71 */     return this.sender;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public void setSender(String sender)
/* 81:   */   {
/* 82:75 */     this.sender = sender;
/* 83:   */   }
/* 84:   */   
/* 85:   */   public Boolean getIsShowExchange()
/* 86:   */   {
/* 87:79 */     return this.isShowExchange;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public void setIsShowExchange(Boolean isShowExchange)
/* 91:   */   {
/* 92:83 */     this.isShowExchange = isShowExchange;
/* 93:   */   }
/* 94:   */   
/* 95:   */   public String getSection()
/* 96:   */   {
/* 97:89 */     return "EmailInfo";
/* 98:   */   }
/* 99:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.EmailConfig
 * JD-Core Version:    0.7.0.1
 */