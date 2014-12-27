/*  1:   */ package cn.com.voltronic.solar.socket;
/*  2:   */ 
/*  3:   */ import java.io.IOException;
/*  4:   */ import java.io.PrintStream;
/*  5:   */ import java.net.DatagramPacket;
/*  6:   */ import java.net.DatagramSocket;
/*  7:   */ import java.net.InetAddress;
/*  8:   */ import java.net.SocketException;
/*  9:   */ import java.net.UnknownHostException;
/* 10:   */ 
/* 11:   */ public class SystemTrayUDPClient
/* 12:   */ {
/* 13:15 */   private DatagramSocket ds = null;
/* 14:   */   private InetAddress inetAddress;
/* 15:19 */   private static int REV_SIZE = 256;
/* 16:   */   private int port;
/* 17:   */   
/* 18:   */   public SystemTrayUDPClient(String serverIp, int port)
/* 19:   */   {
/* 20:   */     try
/* 21:   */     {
/* 22:27 */       this.ds = new DatagramSocket();
/* 23:28 */       this.inetAddress = InetAddress.getByName(serverIp);
/* 24:29 */       this.port = port;
/* 25:30 */       this.ds.setSoTimeout(2000);
/* 26:   */     }
/* 27:   */     catch (SocketException e)
/* 28:   */     {
/* 29:32 */       e.printStackTrace();
/* 30:   */     }
/* 31:   */     catch (UnknownHostException e)
/* 32:   */     {
/* 33:34 */       e.printStackTrace();
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setPort(int port)
/* 38:   */   {
/* 39:41 */     this.port = port;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void displayMessage(String caption, String text)
/* 43:   */   {
/* 44:   */     try
/* 45:   */     {
/* 46:46 */       String message = caption + "###" + text + "###INFO";
/* 47:47 */       send(message.getBytes("UTF-8"));
/* 48:   */     }
/* 49:   */     catch (Exception e)
/* 50:   */     {
/* 51:49 */       System.err.println(e.getMessage());
/* 52:   */     }
/* 53:   */   }
/* 54:   */   
/* 55:   */   public final void send(byte[] bytes)
/* 56:   */     throws IOException
/* 57:   */   {
/* 58:54 */     DatagramPacket dp = new DatagramPacket(bytes, bytes.length, 
/* 59:55 */       this.inetAddress, this.port);
/* 60:56 */     this.ds.send(dp);
/* 61:   */   }
/* 62:   */   
/* 63:   */   public byte[] receive()
/* 64:   */     throws IOException
/* 65:   */   {
/* 66:73 */     byte[] buffer = new byte[REV_SIZE];
/* 67:74 */     DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
/* 68:75 */     this.ds.receive(dp);
/* 69:76 */     return buffer;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public String receiveString()
/* 73:   */     throws IOException
/* 74:   */   {
/* 75:79 */     byte[] buffer = new byte[REV_SIZE];
/* 76:80 */     DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
/* 77:81 */     this.ds.receive(dp);
/* 78:82 */     String info = new String(dp.getData(), 0, dp.getLength());
/* 79:83 */     return info;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public void close()
/* 83:   */   {
/* 84:88 */     if (this.ds != null) {
/* 85:89 */       this.ds.close();
/* 86:   */     }
/* 87:   */   }
/* 88:   */   
/* 89:   */   public void setUpdatedImage(boolean isConnected) {}
/* 90:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.socket.SystemTrayUDPClient
 * JD-Core Version:    0.7.0.1
 */