/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import com.sun.net.ssl.internal.ssl.Provider;
/*   4:    */ import java.security.Security;
/*   5:    */ import java.util.Properties;
/*   6:    */ import javax.mail.BodyPart;
/*   7:    */ import javax.mail.Message.RecipientType;
/*   8:    */ import javax.mail.MessagingException;
/*   9:    */ import javax.mail.Multipart;
/*  10:    */ import javax.mail.Session;
/*  11:    */ import javax.mail.Transport;
/*  12:    */ import javax.mail.internet.AddressException;
/*  13:    */ import javax.mail.internet.InternetAddress;
/*  14:    */ import javax.mail.internet.MimeBodyPart;
/*  15:    */ import javax.mail.internet.MimeMessage;
/*  16:    */ import javax.mail.internet.MimeMultipart;
/*  17:    */ 
/*  18:    */ public class SendMail
/*  19:    */ {
/*  20:    */   private MimeMessage mimeMsg;
/*  21: 21 */   private String username = "";
/*  22: 23 */   private String password = "";
/*  23: 25 */   private int port = 25;
/*  24:    */   private Session session;
/*  25:    */   private Properties props;
/*  26:    */   private Multipart mp;
/*  27:    */   
/*  28:    */   public SendMail(String smtp, int port, boolean useTls)
/*  29:    */   {
/*  30: 34 */     setSmtpHostPort(smtp, port, useTls);
/*  31: 35 */     createMimeMessage();
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void setSmtpHostPort(String hostName, int port, boolean useTls)
/*  35:    */   {
/*  36: 39 */     if (this.props == null) {
/*  37: 40 */       this.props = System.getProperties();
/*  38:    */     }
/*  39: 41 */     this.props.put("mail.transport.protocol", "smtp");
/*  40: 42 */     this.props.put("mail.smtp.host", hostName);
/*  41: 43 */     if (port != 0) {
/*  42: 44 */       this.port = port;
/*  43:    */     }
/*  44: 46 */     this.props.put("mail.smtp.starttls.enable", useTls);
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void createMimeMessage()
/*  48:    */   {
/*  49: 54 */     this.session = Session.getDefaultInstance(this.props, null);
/*  50: 55 */     this.mimeMsg = new MimeMessage(this.session);
/*  51: 56 */     this.mp = new MimeMultipart();
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void setNeedAuth(boolean need)
/*  55:    */   {
/*  56: 64 */     if (this.props == null) {
/*  57: 65 */       this.props = System.getProperties();
/*  58:    */     }
/*  59: 66 */     if (need) {
/*  60: 67 */       this.props.put("mail.smtp.auth", "true");
/*  61:    */     } else {
/*  62: 69 */       this.props.put("mail.smtp.auth", "false");
/*  63:    */     }
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void setNamePass(String name, String pass)
/*  67:    */   {
/*  68: 80 */     this.username = name;
/*  69: 81 */     this.password = pass;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public void setSubject(String mailSubject)
/*  73:    */     throws MessagingException
/*  74:    */   {
/*  75: 91 */     this.mimeMsg.setSubject(mailSubject);
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void setBody(String mailBody)
/*  79:    */     throws MessagingException
/*  80:    */   {
/*  81:100 */     BodyPart bp = new MimeBodyPart();
/*  82:101 */     bp.setContent(mailBody, "text/html;charset=UTF-8");
/*  83:102 */     this.mp.addBodyPart(bp);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void addFileAffix(String filename)
/*  87:    */     throws MessagingException
/*  88:    */   {
/*  89:106 */     BodyPart bp = new MimeBodyPart();
/*  90:107 */     this.mp.addBodyPart(bp);
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void setFrom(String from)
/*  94:    */     throws AddressException, MessagingException
/*  95:    */   {
/*  96:121 */     this.mimeMsg.setFrom(new InternetAddress(from));
/*  97:    */   }
/*  98:    */   
/*  99:    */   public void setTo(String to)
/* 100:    */     throws AddressException, MessagingException
/* 101:    */   {
/* 102:125 */     if (to == null) {
/* 103:126 */       throw new MessagingException();
/* 104:    */     }
/* 105:128 */     this.mimeMsg.setRecipients(Message.RecipientType.TO, 
/* 106:129 */       InternetAddress.parse(to));
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void setCopyTo(String copyto)
/* 110:    */     throws AddressException, MessagingException
/* 111:    */   {
/* 112:142 */     if (copyto == null) {
/* 113:143 */       throw new MessagingException();
/* 114:    */     }
/* 115:145 */     this.mimeMsg.setRecipients(Message.RecipientType.CC, 
/* 116:146 */       InternetAddress.parse(copyto));
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void sendout()
/* 120:    */     throws MessagingException
/* 121:    */   {
/* 122:157 */     this.mimeMsg.setContent(this.mp);
/* 123:158 */     this.mimeMsg.saveChanges();
/* 124:    */     
/* 125:160 */     Security.addProvider(new Provider());
/* 126:    */     
/* 127:162 */     Session mailSession = Session.getInstance(this.props, null);
/* 128:163 */     mailSession.setDebug(true);
/* 129:164 */     Transport transport = mailSession.getTransport("smtp");
/* 130:165 */     transport.connect((String)this.props.get("mail.smtp.host"), this.port, 
/* 131:166 */       this.username, this.password);
/* 132:167 */     transport.sendMessage(this.mimeMsg, 
/* 133:168 */       this.mimeMsg.getRecipients(Message.RecipientType.TO));
/* 134:169 */     transport.close();
/* 135:    */   }
/* 136:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.SendMail
 * JD-Core Version:    0.7.0.1
 */