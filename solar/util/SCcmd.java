/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   4:    */ import cn.com.voltronic.solar.socket.DatagramServer;
/*   5:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   6:    */ import java.io.BufferedReader;
/*   7:    */ import java.io.IOException;
/*   8:    */ import java.io.InputStreamReader;
/*   9:    */ import java.io.PrintStream;
/*  10:    */ 
/*  11:    */ public class SCcmd
/*  12:    */ {
/*  13: 13 */   private static final String DIR = System.getProperty("user.dir");
/*  14: 14 */   private static final String DAEMONSTART = DIR + "\\WatchPowerTray.exe start";
/*  15: 15 */   private static final String DAEMONCHANGE = DIR + "\\WatchPowerTray.exe change";
/*  16: 16 */   private static final String DAEMONSTOP = DIR + "\\WatchPowerTray.exe stop";
/*  17:    */   private static final String SERVERSTATUSINAPP = "netstat -a";
/*  18:    */   public static final int RUNNING = 4;
/*  19:    */   public static final int STOPPED = 1;
/*  20: 22 */   private static BufferedReader bufferedReader = null;
/*  21:    */   
/*  22:    */   private static Process execmd_pro(String cmd)
/*  23:    */   {
/*  24: 25 */     Process proc = null;
/*  25:    */     try
/*  26:    */     {
/*  27: 27 */       proc = Runtime.getRuntime().exec(cmd);
/*  28:    */     }
/*  29:    */     catch (Exception e)
/*  30:    */     {
/*  31: 29 */       System.err.println(e.getMessage());
/*  32:    */     }
/*  33: 31 */     return proc;
/*  34:    */   }
/*  35:    */   
/*  36:    */   public static void startDaemon()
/*  37:    */   {
/*  38: 39 */     String port = "38694";
/*  39:    */     try
/*  40:    */     {
/*  41: 41 */       port = GlobalVariables.globalConfig.getUdpPort();
/*  42: 42 */       execnoreturn(DAEMONSTART + " " + port);
/*  43:    */     }
/*  44:    */     catch (Exception e)
/*  45:    */     {
/*  46: 44 */       e.printStackTrace();
/*  47:    */     }
/*  48:    */   }
/*  49:    */   
/*  50:    */   public static void changeDaemon(String oldPort, String newPort)
/*  51:    */   {
/*  52:    */     try
/*  53:    */     {
/*  54: 54 */       execnoreturn(DAEMONCHANGE + " " + oldPort + " " + newPort);
/*  55:    */     }
/*  56:    */     catch (Exception e)
/*  57:    */     {
/*  58: 56 */       e.printStackTrace();
/*  59:    */     }
/*  60:    */   }
/*  61:    */   
/*  62:    */   public static void stopDaemon()
/*  63:    */   {
/*  64: 65 */     String port = "38694";
/*  65:    */     try
/*  66:    */     {
/*  67: 67 */       port = GlobalVariables.globalConfig.getUdpPort();
/*  68: 68 */       execnoreturn(DAEMONSTOP + " " + port);
/*  69:    */     }
/*  70:    */     catch (Exception e)
/*  71:    */     {
/*  72: 70 */       e.printStackTrace();
/*  73:    */     }
/*  74:    */   }
/*  75:    */   
/*  76:    */   public static boolean isUsed(String port)
/*  77:    */   {
/*  78: 81 */     boolean result = false;
/*  79: 82 */     int re = parsingbufferinapp("netstat -a", port);
/*  80: 83 */     if (re == 4) {
/*  81: 84 */       result = true;
/*  82:    */     }
/*  83: 86 */     return result;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public static boolean isBind(String port)
/*  87:    */   {
/*  88: 97 */     boolean result = true;
/*  89: 98 */     DatagramServer udpServer = null;
/*  90:    */     try
/*  91:    */     {
/*  92:100 */       udpServer = new DatagramServer("0.0.0.0", VolUtil.parseInt(port));
/*  93:101 */       result = false;
/*  94:    */     }
/*  95:    */     catch (Exception e)
/*  96:    */     {
/*  97:103 */       result = true;
/*  98:    */     }
/*  99:    */     finally
/* 100:    */     {
/* 101:105 */       if (udpServer != null) {
/* 102:106 */         udpServer.close();
/* 103:    */       }
/* 104:    */     }
/* 105:109 */     return result;
/* 106:    */   }
/* 107:    */   
/* 108:    */   private static int parsingbufferinapp(String cmd, String port)
/* 109:    */   {
/* 110:113 */     Process proc = execmd_pro(cmd);
/* 111:114 */     bufferedReader = new BufferedReader(new InputStreamReader(
/* 112:115 */       proc.getInputStream()));
/* 113:116 */     StreamClear errorclear = new StreamClear(proc.getErrorStream());
/* 114:117 */     errorclear.start();
/* 115:118 */     return parsinginapp(bufferedReader, port);
/* 116:    */   }
/* 117:    */   
/* 118:    */   private static int parsinginapp(BufferedReader bufferedReader, String strport)
/* 119:    */   {
/* 120:123 */     int results = 1;
/* 121:124 */     String readline = null;
/* 122:    */     try
/* 123:    */     {
/* 124:    */       break label31;
/* 125:127 */       if ((readline.indexOf("LISTENING") != -1) && 
/* 126:128 */         (readline.indexOf(strport) != -1)) {
/* 127:129 */         results = 4;
/* 128:    */       }
/* 129:    */       label31:
/* 130:134 */       while ((readline = bufferedReader.readLine()) != null) {}
/* 131:    */     }
/* 132:    */     catch (IOException e)
/* 133:    */     {
/* 134:137 */       e.printStackTrace();
/* 135:    */       try
/* 136:    */       {
/* 137:140 */         if (bufferedReader != null) {
/* 138:141 */           bufferedReader.close();
/* 139:    */         }
/* 140:    */       }
/* 141:    */       catch (Exception e)
/* 142:    */       {
/* 143:144 */         e.printStackTrace();
/* 144:    */       }
/* 145:    */     }
/* 146:    */     finally
/* 147:    */     {
/* 148:    */       try
/* 149:    */       {
/* 150:140 */         if (bufferedReader != null) {
/* 151:141 */           bufferedReader.close();
/* 152:    */         }
/* 153:    */       }
/* 154:    */       catch (Exception e)
/* 155:    */       {
/* 156:144 */         e.printStackTrace();
/* 157:    */       }
/* 158:    */     }
/* 159:148 */     return results;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public static void execnoreturn(String cmd)
/* 163:    */     throws Exception
/* 164:    */   {
/* 165:153 */     Process proc = execmd_pro(cmd);
/* 166:154 */     StreamClear error = new StreamClear(proc.getErrorStream());
/* 167:155 */     StreamClear in = new StreamClear(proc.getInputStream());
/* 168:156 */     error.start();
/* 169:157 */     in.start();
/* 170:    */   }
/* 171:    */   
/* 172:    */   public static void main(String[] args) {}
/* 173:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.SCcmd
 * JD-Core Version:    0.7.0.1
 */