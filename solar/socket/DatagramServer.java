/*   1:    */ package cn.com.voltronic.solar.socket;
/*   2:    */ 
/*   3:    */ import java.io.IOException;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import java.net.DatagramPacket;
/*   6:    */ import java.net.DatagramSocket;
/*   7:    */ import java.net.InetAddress;
/*   8:    */ import java.net.InetSocketAddress;
/*   9:    */ import java.net.SocketException;
/*  10:    */ 
/*  11:    */ public class DatagramServer
/*  12:    */ {
/*  13: 15 */   private byte[] buffer = new byte[1024];
/*  14: 20 */   private DatagramSocket ds = null;
/*  15: 25 */   private DatagramPacket packet = null;
/*  16: 30 */   private InetSocketAddress socketAddress = null;
/*  17:    */   
/*  18:    */   public DatagramServer(String host, int port)
/*  19:    */     throws Exception
/*  20:    */   {
/*  21: 42 */     this.socketAddress = new InetSocketAddress(host, port);
/*  22: 43 */     this.ds = new DatagramSocket(this.socketAddress);
/*  23: 44 */     System.out.println("托盘启动!");
/*  24:    */   }
/*  25:    */   
/*  26:    */   public final void setSoTimeout(int timeout)
/*  27:    */     throws Exception
/*  28:    */   {
/*  29: 55 */     this.ds.setSoTimeout(timeout);
/*  30:    */   }
/*  31:    */   
/*  32:    */   public final void bind(String host, int port)
/*  33:    */     throws SocketException
/*  34:    */   {
/*  35: 69 */     this.socketAddress = new InetSocketAddress(host, port);
/*  36: 70 */     this.ds = new DatagramSocket(this.socketAddress);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public final String receive()
/*  40:    */     throws IOException
/*  41:    */   {
/*  42: 80 */     this.packet = new DatagramPacket(this.buffer, this.buffer.length);
/*  43: 81 */     this.ds.receive(this.packet);
/*  44: 82 */     String info = new String(this.packet.getData(), 0, this.packet.getLength(), "UTF-8");
/*  45: 83 */     return info;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public String getPacketIp()
/*  49:    */   {
/*  50: 87 */     return this.packet.getAddress().getHostAddress();
/*  51:    */   }
/*  52:    */   
/*  53:    */   public final void response(String info)
/*  54:    */     throws IOException
/*  55:    */   {
/*  56: 99 */     DatagramPacket dp = new DatagramPacket(this.buffer, this.buffer.length, this.packet.getAddress(), this.packet.getPort());
/*  57:100 */     dp.setData(info.getBytes());
/*  58:101 */     this.ds.send(dp);
/*  59:    */   }
/*  60:    */   
/*  61:    */   public final void setLength(int bufsize)
/*  62:    */   {
/*  63:111 */     this.packet.setLength(bufsize);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public final void close()
/*  67:    */   {
/*  68:    */     try
/*  69:    */     {
/*  70:119 */       if (this.ds != null) {
/*  71:120 */         this.ds.close();
/*  72:    */       }
/*  73:    */     }
/*  74:    */     catch (Exception ex)
/*  75:    */     {
/*  76:123 */       ex.printStackTrace();
/*  77:    */     }
/*  78:    */   }
/*  79:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.socket.DatagramServer
 * JD-Core Version:    0.7.0.1
 */