/*  1:   */ package cn.com.voltronic.solar.socket;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.util.VolUtil;
/*  4:   */ import java.io.IOException;
/*  5:   */ import java.net.DatagramPacket;
/*  6:   */ import java.net.DatagramSocket;
/*  7:   */ import java.net.InetAddress;
/*  8:   */ import java.net.SocketException;
/*  9:   */ import java.net.UnknownHostException;
/* 10:   */ import java.util.Date;
/* 11:   */ 
/* 12:   */ public class UdpClient
/* 13:   */ {
/* 14:16 */   private String ip = "";
/* 15:17 */   private String port = "";
/* 16:18 */   private DatagramSocket ds = null;
/* 17:   */   private InetAddress inetAddress;
/* 18:   */   
/* 19:   */   public void setIp(String ip)
/* 20:   */   {
/* 21:23 */     this.ip = ip;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setPort(String port)
/* 25:   */   {
/* 26:27 */     this.port = port;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String getIp()
/* 30:   */   {
/* 31:31 */     return this.ip;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int getPort()
/* 35:   */   {
/* 36:   */     try
/* 37:   */     {
/* 38:36 */       return Integer.parseInt(this.port);
/* 39:   */     }
/* 40:   */     catch (Exception e)
/* 41:   */     {
/* 42:38 */       e.printStackTrace();
/* 43:   */     }
/* 44:40 */     return 0;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public UdpClient(String ip, String port)
/* 48:   */     throws SocketException, UnknownHostException
/* 49:   */   {
/* 50:44 */     this.ip = ip;
/* 51:45 */     this.port = port;
/* 52:46 */     this.ds = new DatagramSocket();
/* 53:47 */     this.ds.setSoTimeout(1000);
/* 54:48 */     this.inetAddress = InetAddress.getByName(getIp());
/* 55:   */   }
/* 56:   */   
/* 57:   */   public byte[] receive()
/* 58:   */     throws IOException
/* 59:   */   {
/* 60:52 */     byte[] buffer = new byte[64];
/* 61:53 */     DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
/* 62:54 */     this.ds.receive(dp);
/* 63:55 */     return buffer;
/* 64:   */   }
/* 65:   */   
/* 66:   */   public void disconnect()
/* 67:   */   {
/* 68:59 */     this.ds.disconnect();
/* 69:60 */     this.ds.close();
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void send(String cmd)
/* 73:   */     throws IOException
/* 74:   */   {
/* 75:64 */     cmd = cmd + '\r';
/* 76:65 */     byte[] cmdb = cmd.getBytes("UTF-8");
/* 77:66 */     int port = getPort();
/* 78:67 */     if (port > 0)
/* 79:   */     {
/* 80:68 */       DatagramPacket dp = new DatagramPacket(cmdb, cmdb.length, this.inetAddress, getPort());
/* 81:69 */       this.ds.send(dp);
/* 82:   */     }
/* 83:   */   }
/* 84:   */   
/* 85:   */   public static void main(String[] args)
/* 86:   */   {
/* 87:   */     try
/* 88:   */     {
/* 89:75 */       String serialno = "fdasdfasdfaf";
/* 90:76 */       Date trandate = new Date();
/* 91:77 */       String message = "fdsafdafd";
/* 92:   */       
/* 93:79 */       UdpClient client = new UdpClient("127.0.0.1", "38693");
/* 94:80 */       client.send("(Event:[" + serialno + "] [" + 
/* 95:81 */         VolUtil.getFormatTimestamp(trandate) + "] " + message + 
/* 96:82 */         "13");
/* 97:   */     }
/* 98:   */     catch (IOException e)
/* 99:   */     {
/* :0:84 */       e.printStackTrace();
/* :1:   */     }
/* :2:   */   }
/* :3:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.socket.UdpClient
 * JD-Core Version:    0.7.0.1
 */