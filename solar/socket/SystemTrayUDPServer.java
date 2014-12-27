/*  1:   */ package cn.com.voltronic.solar.socket;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*  4:   */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  5:   */ import cn.com.voltronic.solar.util.VolUtil;
/*  6:   */ 
/*  7:   */ public class SystemTrayUDPServer
/*  8:   */ {
/*  9: 9 */   private static DatagramServer _udpServerSocket = null;
/* 10:   */   
/* 11:   */   public static DatagramServer getServer()
/* 12:   */     throws Exception
/* 13:   */   {
/* 14:12 */     String port = "38694";
/* 15:   */     try
/* 16:   */     {
/* 17:14 */       port = GlobalVariables.globalConfig.getUdpPort();
/* 18:   */     }
/* 19:   */     catch (Exception e)
/* 20:   */     {
/* 21:16 */       e.printStackTrace();
/* 22:   */     }
/* 23:18 */     if (_udpServerSocket == null) {
/* 24:19 */       _udpServerSocket = new DatagramServer("0.0.0.0", VolUtil.parseInt(port));
/* 25:   */     }
/* 26:21 */     return _udpServerSocket;
/* 27:   */   }
/* 28:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.socket.SystemTrayUDPServer
 * JD-Core Version:    0.7.0.1
 */