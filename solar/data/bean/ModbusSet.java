/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ public class ModbusSet
/*  4:   */ {
/*  5:10 */   private String portName = "";
/*  6:   */   private int baudrate;
/*  7:   */   private int dataBit;
/*  8:   */   private int stopBit;
/*  9:   */   private int parity;
/* 10:20 */   private String deviceIds = "";
/* 11:   */   
/* 12:   */   public String getDeviceIds()
/* 13:   */   {
/* 14:23 */     return this.deviceIds;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public void setDeviceIds(String deviceIds)
/* 18:   */   {
/* 19:27 */     this.deviceIds = deviceIds;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public String getPortName()
/* 23:   */   {
/* 24:31 */     return this.portName;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setPortName(String portName)
/* 28:   */   {
/* 29:35 */     this.portName = portName;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public int getBaudrate()
/* 33:   */   {
/* 34:39 */     return this.baudrate;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setBaudrate(int baudrate)
/* 38:   */   {
/* 39:43 */     this.baudrate = baudrate;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public int getDataBit()
/* 43:   */   {
/* 44:47 */     return this.dataBit;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setDataBit(int dataBit)
/* 48:   */   {
/* 49:51 */     this.dataBit = dataBit;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public int getStopBit()
/* 53:   */   {
/* 54:55 */     return this.stopBit;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setStopBit(int stopBit)
/* 58:   */   {
/* 59:59 */     this.stopBit = stopBit;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public int getParity()
/* 63:   */   {
/* 64:63 */     return this.parity;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void setParity(int parity)
/* 68:   */   {
/* 69:67 */     this.parity = parity;
/* 70:   */   }
/* 71:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.ModbusSet
 * JD-Core Version:    0.7.0.1
 */