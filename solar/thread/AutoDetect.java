/*   1:    */ package cn.com.voltronic.solar.thread;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.UpgradeConfig;
/*   4:    */ import cn.com.voltronic.solar.constants.Constants;
/*   5:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   6:    */ import cn.com.voltronic.solar.util.MyMD5;
/*   7:    */ import cn.com.voltronic.solar.util.ReadDatFile;
/*   8:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*   9:    */ import com.sun.org.apache.xml.internal.security.utils.Base64;
/*  10:    */ import java.awt.TrayIcon.MessageType;
/*  11:    */ import java.io.FileNotFoundException;
/*  12:    */ import java.io.PrintStream;
/*  13:    */ import java.net.HttpURLConnection;
/*  14:    */ import java.net.URL;
/*  15:    */ import java.util.Properties;
/*  16:    */ 
/*  17:    */ public class AutoDetect
/*  18:    */   extends Thread
/*  19:    */ {
/*  20:    */   public void run()
/*  21:    */   {
/*  22:    */     for (;;)
/*  23:    */     {
/*  24: 27 */       HttpURLConnection httpUrl = null;
/*  25: 28 */       URL url = null;
/*  26: 29 */       String downloadDir = "";
/*  27:    */       try
/*  28:    */       {
/*  29:    */         try
/*  30:    */         {
/*  31: 32 */           downloadDir = GlobalVariables.upgradeConfig.getFromURL();
/*  32:    */         }
/*  33:    */         catch (Exception e)
/*  34:    */         {
/*  35: 34 */           System.err.println("下载路径不存在!");
/*  36:    */         }
/*  37: 93 */         if (httpUrl == null) {
/*  38:    */           continue;
/*  39:    */         }
/*  40: 94 */         httpUrl.disconnect();
/*  41:    */       }
/*  42:    */       catch (FileNotFoundException xx)
/*  43:    */       {
/*  44:    */         String proxy;
/*  45:    */         String port;
/*  46:    */         Boolean useProxy;
/*  47:    */         boolean useAuth;
/*  48:    */         Properties systemProperties;
/*  49:    */         String username;
/*  50:    */         String pwd;
/*  51:    */         String password;
/*  52:    */         String encoded;
/*  53:    */         String fileName;
/*  54:    */         float length;
/*  55:    */         long time;
/*  56:    */         String oldFileName;
/*  57:    */         float oldLength;
/*  58:    */         long oldTime;
/*  59: 89 */         System.err.println("文件不存在");
/*  60:    */       }
/*  61:    */       catch (Exception ex)
/*  62:    */       {
/*  63: 91 */         System.err.println("网络不能连接");
/*  64:    */       }
/*  65:    */       finally
/*  66:    */       {
/*  67: 93 */         if (httpUrl == null) {
/*  68:    */           break label425;
/*  69:    */         }
/*  70: 94 */         httpUrl.disconnect();
/*  71:    */       }
/*  72: 37 */       url = new URL(downloadDir);
/*  73: 38 */       proxy = GlobalVariables.upgradeConfig.getProxyIp();
/*  74: 39 */       port = GlobalVariables.upgradeConfig.getProxyPort();
/*  75: 40 */       useProxy = Boolean.valueOf(GlobalVariables.upgradeConfig.isUseProxy());
/*  76: 41 */       useAuth = GlobalVariables.upgradeConfig.isUseAuth();
/*  77: 42 */       if (useProxy.booleanValue())
/*  78:    */       {
/*  79: 43 */         systemProperties = System.getProperties();
/*  80: 44 */         systemProperties.setProperty("http.proxyHost", proxy);
/*  81: 45 */         systemProperties.setProperty("http.proxyPort", port);
/*  82:    */       }
/*  83: 47 */       httpUrl = (HttpURLConnection)url.openConnection();
/*  84: 49 */       if ((useProxy.booleanValue()) && (useAuth))
/*  85:    */       {
/*  86: 50 */         username = GlobalVariables.upgradeConfig.getAuthName();
/*  87: 51 */         pwd = MyMD5.restore(GlobalVariables.upgradeConfig.getAuthPassword());
/*  88: 52 */         password = username + ":" + pwd;
/*  89: 53 */         encoded = Base64.encode(password.getBytes());
/*  90: 54 */         httpUrl.setRequestProperty("Proxy-Authorization", "Basic " + encoded);
/*  91:    */       }
/*  92: 56 */       httpUrl.setConnectTimeout(20000);
/*  93: 57 */       httpUrl.connect();
/*  94: 58 */       httpUrl.getInputStream();
/*  95:    */       
/*  96: 60 */       fileName = downloadDir.trim().substring(
/*  97: 61 */         downloadDir.lastIndexOf("/") + 1);
/*  98: 62 */       length = httpUrl.getContentLength();
/*  99: 63 */       time = httpUrl.getLastModified();
/* 100: 64 */       oldFileName = "";
/* 101: 65 */       oldLength = 0.0F;
/* 102: 66 */       oldTime = 0L;
/* 103:    */       try
/* 104:    */       {
/* 105: 68 */         oldFileName = ReadDatFile.getString("fileName");
/* 106: 69 */         oldLength = Float.parseFloat(
/* 107: 70 */           ReadDatFile.getString("fileSize"));
/* 108: 71 */         oldTime = Long.parseLong(
/* 109: 72 */           ReadDatFile.getString("lastModified"));
/* 110:    */       }
/* 111:    */       catch (Exception localException1) {}
/* 112: 76 */       if ((fileName.equals(oldFileName)) && (length == oldLength) && 
/* 113: 77 */         (time == oldTime)) {
/* 114: 78 */         System.err.println("已经是最新程序");
/* 115: 81 */       } else if ((Constants.IS_OS_MAC) || (Constants.IS_OS_MAC_OSX)) {
/* 116: 82 */         DisplayMessage.showInfoDialog("message.hasupdate");
/* 117:    */       } else {
/* 118: 84 */         DisplayMessage.showMesgToTary("message.info", "message.hasupdate", 
/* 119: 85 */           TrayIcon.MessageType.INFO);
/* 120:    */       }
/* 121:    */       try
/* 122:    */       {
/* 123:    */         label425:
/* 124: 99 */         Thread.sleep(3600000L);
/* 125:    */       }
/* 126:    */       catch (InterruptedException e)
/* 127:    */       {
/* 128:102 */         e.printStackTrace();
/* 129:    */       }
/* 130:    */     }
/* 131:    */   }
/* 132:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.thread.AutoDetect
 * JD-Core Version:    0.7.0.1
 */