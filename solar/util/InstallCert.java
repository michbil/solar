/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import java.io.FileInputStream;
/*   5:    */ import java.io.FileOutputStream;
/*   6:    */ import java.io.InputStream;
/*   7:    */ import java.io.OutputStream;
/*   8:    */ import java.security.KeyStore;
/*   9:    */ import java.security.MessageDigest;
/*  10:    */ import java.security.cert.CertificateException;
/*  11:    */ import java.security.cert.X509Certificate;
/*  12:    */ import javax.net.ssl.SSLContext;
/*  13:    */ import javax.net.ssl.SSLException;
/*  14:    */ import javax.net.ssl.SSLSocket;
/*  15:    */ import javax.net.ssl.SSLSocketFactory;
/*  16:    */ import javax.net.ssl.TrustManager;
/*  17:    */ import javax.net.ssl.TrustManagerFactory;
/*  18:    */ import javax.net.ssl.X509TrustManager;
/*  19:    */ 
/*  20:    */ public class InstallCert
/*  21:    */ {
/*  22:    */   public static void main(String[] args)
/*  23:    */   {
/*  24:    */     try
/*  25:    */     {
/*  26: 25 */       insertCert("voltronic.com.tw");
/*  27:    */     }
/*  28:    */     catch (Exception e)
/*  29:    */     {
/*  30: 27 */       e.printStackTrace();
/*  31:    */     }
/*  32:    */   }
/*  33:    */   
/*  34:    */   public static void insertCert(String address)
/*  35:    */     throws Exception
/*  36:    */   {
/*  37: 35 */     String[] c = address.split(":");
/*  38: 36 */     String host = c[0];
/*  39: 37 */     int port = c.length == 1 ? 443 : Integer.parseInt(c[1]);
/*  40: 38 */     String p = "changeit";
/*  41: 39 */     char[] passphrase = p.toCharArray();
/*  42:    */     
/*  43: 41 */     char SEP = File.separatorChar;
/*  44: 42 */     String path = System.getProperty("java.home") + SEP + "lib" + SEP + 
/*  45: 43 */       "security";
/*  46: 44 */     File file = new File(path + SEP + "jssecacerts");
/*  47: 45 */     if (!file.isFile())
/*  48:    */     {
/*  49: 46 */       File dir = new File(path);
/*  50: 47 */       file = new File(dir, "jssecacerts");
/*  51: 48 */       if (!file.isFile()) {
/*  52: 49 */         file = new File(dir, "cacerts");
/*  53:    */       }
/*  54:    */     }
/*  55: 52 */     InputStream in = null;
/*  56: 53 */     OutputStream out = null;
/*  57:    */     try
/*  58:    */     {
/*  59: 55 */       in = new FileInputStream(file);
/*  60: 56 */       KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
/*  61: 57 */       ks.load(in, passphrase);
/*  62: 58 */       in.close();
/*  63:    */       
/*  64: 60 */       SSLContext context = SSLContext.getInstance("TLS");
/*  65: 61 */       TrustManagerFactory tmf = 
/*  66: 62 */         TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
/*  67: 63 */       tmf.init(ks);
/*  68: 64 */       X509TrustManager defaultTrustManager = (X509TrustManager)tmf
/*  69: 65 */         .getTrustManagers()[0];
/*  70: 66 */       SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
/*  71: 67 */       context.init(null, new TrustManager[] { tm }, null);
/*  72: 68 */       SSLSocketFactory factory = context.getSocketFactory();
/*  73:    */       
/*  74: 70 */       SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
/*  75: 71 */       socket.setSoTimeout(10000);
/*  76:    */       try
/*  77:    */       {
/*  78: 73 */         socket.startHandshake();
/*  79: 74 */         socket.close();
/*  80:    */       }
/*  81:    */       catch (SSLException e)
/*  82:    */       {
/*  83: 76 */         e.printStackTrace();
/*  84:    */       }
/*  85: 79 */       X509Certificate[] chain = tm.chain;
/*  86: 80 */       if (chain == null) {
/*  87: 81 */         return;
/*  88:    */       }
/*  89: 83 */       MessageDigest sha1 = MessageDigest.getInstance("SHA1");
/*  90: 84 */       MessageDigest md5 = MessageDigest.getInstance("MD5");
/*  91: 85 */       for (int i = 0; i < chain.length; i++)
/*  92:    */       {
/*  93: 86 */         X509Certificate cert = chain[i];
/*  94: 87 */         sha1.update(cert.getEncoded());
/*  95: 88 */         md5.update(cert.getEncoded());
/*  96:    */       }
/*  97: 91 */       X509Certificate cert = chain[0];
/*  98: 92 */       String alias = host + "-" + 1;
/*  99: 93 */       ks.setCertificateEntry(alias, cert);
/* 100:    */       
/* 101: 95 */       out = new FileOutputStream(path + SEP + "jssecacerts");
/* 102: 96 */       ks.store(out, passphrase);
/* 103: 97 */       out.close();
/* 104:    */     }
/* 105:    */     catch (Exception ex)
/* 106:    */     {
/* 107: 99 */       ex.printStackTrace();
/* 108:    */     }
/* 109:    */     finally
/* 110:    */     {
/* 111:101 */       if (in != null) {
/* 112:102 */         in.close();
/* 113:    */       }
/* 114:104 */       if (out != null) {
/* 115:105 */         out.close();
/* 116:    */       }
/* 117:    */     }
/* 118:    */   }
/* 119:    */   
/* 120:    */   private static class SavingTrustManager
/* 121:    */     implements X509TrustManager
/* 122:    */   {
/* 123:    */     private final X509TrustManager tm;
/* 124:    */     private X509Certificate[] chain;
/* 125:    */     
/* 126:    */     SavingTrustManager(X509TrustManager tm)
/* 127:    */     {
/* 128:116 */       this.tm = tm;
/* 129:    */     }
/* 130:    */     
/* 131:    */     public X509Certificate[] getAcceptedIssuers()
/* 132:    */     {
/* 133:120 */       throw new UnsupportedOperationException();
/* 134:    */     }
/* 135:    */     
/* 136:    */     public void checkClientTrusted(X509Certificate[] chain, String authType)
/* 137:    */       throws CertificateException
/* 138:    */     {
/* 139:125 */       throw new UnsupportedOperationException();
/* 140:    */     }
/* 141:    */     
/* 142:    */     public void checkServerTrusted(X509Certificate[] chain, String authType)
/* 143:    */       throws CertificateException
/* 144:    */     {
/* 145:130 */       this.chain = chain;
/* 146:131 */       this.tm.checkServerTrusted(chain, authType);
/* 147:    */     }
/* 148:    */   }
/* 149:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.InstallCert
 * JD-Core Version:    0.7.0.1
 */