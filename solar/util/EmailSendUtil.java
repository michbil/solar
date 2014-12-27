/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.EmailConfig;
/*   4:    */ import javax.mail.MessagingException;
/*   5:    */ 
/*   6:    */ public class EmailSendUtil
/*   7:    */ {
/*   8:    */   private String to;
/*   9:    */   private String from;
/*  10:    */   private String host;
/*  11:    */   private int port;
/*  12:    */   private String username;
/*  13:    */   private String password;
/*  14: 21 */   private Boolean needAuth = Boolean.valueOf(true);
/*  15:    */   
/*  16:    */   public EmailSendUtil(EmailConfig info)
/*  17:    */   {
/*  18: 24 */     this.host = info.getSmtp();
/*  19: 25 */     this.port = info.getPort();
/*  20: 26 */     this.username = info.getAccount();
/*  21: 27 */     this.needAuth = info.getNeedAuth();
/*  22: 28 */     this.password = info.getPassword();
/*  23: 29 */     this.from = info.getSender();
/*  24:    */   }
/*  25:    */   
/*  26:    */   public boolean sendMail(String to, String subject, String content)
/*  27:    */   {
/*  28: 33 */     boolean isSuccess = true;
/*  29: 34 */     SendMail themail = new SendMail(this.host, this.port, true);
/*  30: 35 */     themail.setNeedAuth(this.needAuth.booleanValue());
/*  31:    */     try
/*  32:    */     {
/*  33: 37 */       themail.setSubject(subject);
/*  34: 38 */       themail.setBody(content);
/*  35: 39 */       themail.setTo(to);
/*  36: 40 */       themail.setFrom(this.from);
/*  37: 41 */       themail.setNamePass(this.username, this.password);
/*  38: 42 */       themail.sendout();
/*  39:    */     }
/*  40:    */     catch (MessagingException e)
/*  41:    */     {
/*  42: 44 */       isSuccess = false;
/*  43:    */     }
/*  44: 46 */     if (isSuccess) {
/*  45: 47 */       return true;
/*  46:    */     }
/*  47: 49 */     SendMail themail2 = new SendMail(this.host, this.port, false);
/*  48: 50 */     themail2.setNeedAuth(this.needAuth.booleanValue());
/*  49:    */     try
/*  50:    */     {
/*  51: 52 */       themail2.setSubject(subject);
/*  52: 53 */       themail2.setBody(content);
/*  53: 54 */       themail2.setTo(to);
/*  54: 55 */       themail2.setFrom(this.from);
/*  55: 56 */       themail2.setNamePass(this.username, this.password);
/*  56: 57 */       themail2.sendout();
/*  57:    */     }
/*  58:    */     catch (MessagingException e)
/*  59:    */     {
/*  60: 59 */       return false;
/*  61:    */     }
/*  62: 61 */     return true;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public String getTo()
/*  66:    */   {
/*  67: 66 */     return this.to;
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void setTo(String to)
/*  71:    */   {
/*  72: 70 */     this.to = to;
/*  73:    */   }
/*  74:    */   
/*  75:    */   public String getFrom()
/*  76:    */   {
/*  77: 74 */     return this.from;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public void setFrom(String from)
/*  81:    */   {
/*  82: 78 */     this.from = from;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public String getHost()
/*  86:    */   {
/*  87: 82 */     return this.host;
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void setHost(String host)
/*  91:    */   {
/*  92: 86 */     this.host = host;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public String getUsername()
/*  96:    */   {
/*  97: 90 */     return this.username;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void setUsername(String username)
/* 101:    */   {
/* 102: 94 */     this.username = username;
/* 103:    */   }
/* 104:    */   
/* 105:    */   public String getPassword()
/* 106:    */   {
/* 107: 98 */     return this.password;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public void setPassword(String password)
/* 111:    */   {
/* 112:102 */     this.password = password;
/* 113:    */   }
/* 114:    */   
/* 115:    */   public Boolean getNeedAuth()
/* 116:    */   {
/* 117:106 */     return this.needAuth;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public void setNeedAuth(Boolean needAuth)
/* 121:    */   {
/* 122:110 */     this.needAuth = needAuth;
/* 123:    */   }
/* 124:    */   
/* 125:    */   public int getPort()
/* 126:    */   {
/* 127:114 */     return this.port;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public void setPort(int port)
/* 131:    */   {
/* 132:118 */     this.port = port;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public static void main(String[] args)
/* 136:    */   {
/* 137:122 */     String mailbody = "hello java mail!";
/* 138:    */     
/* 139:124 */     SendMail themail = new SendMail("voltronic.com.tw", 25, true);
/* 140:125 */     themail.setNeedAuth(true);
/* 141:    */     try
/* 142:    */     {
/* 143:127 */       themail.setSubject("test mail send");
/* 144:128 */       themail.setBody(mailbody);
/* 145:129 */       themail.setTo("lincoln_jia@voltronicpower.com.cn");
/* 146:130 */       themail.setFrom("lincoln_jia@voltronicpower.com.cn");
/* 147:131 */       themail.setNamePass("lincoln_jia", "admin111");
/* 148:132 */       themail.sendout();
/* 149:    */     }
/* 150:    */     catch (MessagingException e)
/* 151:    */     {
/* 152:134 */       e.printStackTrace();
/* 153:    */     }
/* 154:    */   }
/* 155:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.EmailSendUtil
 * JD-Core Version:    0.7.0.1
 */