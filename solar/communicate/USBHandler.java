/*   1:    */ package cn.com.voltronic.solar.communicate;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   4:    */ import cn.com.voltronic.solar.util.CRCUtil;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import org.apache.commons.lang.StringUtils;
/*   7:    */ import usb.IUSBComm;
/*   8:    */ 
/*   9:    */ public class USBHandler
/*  10:    */   implements IComUSBHandler, ICommunicateDevice
/*  11:    */ {
/*  12:    */   private String usbId;
/*  13:    */   private IUSBComm usbComm;
/*  14:    */   protected AbstractProcessor notifyProcesser;
/*  15: 32 */   private int _errorcount = 0;
/*  16:    */   
/*  17:    */   public USBHandler(IUSBComm usbComm, String usbId)
/*  18:    */   {
/*  19: 35 */     this.usbComm = usbComm;
/*  20: 36 */     this.usbId = usbId;
/*  21:    */   }
/*  22:    */   
/*  23:    */   public synchronized String excuteSimpleCommand(String command)
/*  24:    */   {
/*  25: 42 */     boolean result = true;
/*  26: 43 */     String returnValue = "";
/*  27:    */     try
/*  28:    */     {
/*  29: 47 */       int time = 0;
/*  30: 50 */       while (((StringUtils.isEmpty(returnValue)) || (returnValue.startsWith("(NAK"))) && (time < 3))
/*  31:    */       {
/*  32: 51 */         returnValue = this.usbComm.sendCommand(this.usbId, command + "\r");
/*  33: 52 */         time++;
/*  34:    */       }
/*  35: 56 */       if (StringUtils.isEmpty(returnValue)) {
/*  36: 57 */         result = false;
/*  37:    */       }
/*  38: 60 */       if (CRCUtil.checkCRC(returnValue)) {
/*  39: 61 */         returnValue = returnValue.substring(0, returnValue.length() - 2);
/*  40:    */       } else {
/*  41: 66 */         returnValue = "";
/*  42:    */       }
/*  43:    */     }
/*  44:    */     catch (Exception ex)
/*  45:    */     {
/*  46: 71 */       result = false;
/*  47:    */     }
/*  48:    */     finally
/*  49:    */     {
/*  50: 73 */       countErrorandNotifyProcesser(result);
/*  51:    */     }
/*  52: 75 */     return returnValue;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public synchronized String excuteCommand(String command, boolean isResponse)
/*  56:    */   {
/*  57: 81 */     boolean result = true;
/*  58: 82 */     String returnValue = "";
/*  59:    */     try
/*  60:    */     {
/*  61: 87 */       returnValue = this.usbComm.sendCommand(this.usbId, command + "\r");
/*  62: 88 */       if (isResponse)
/*  63:    */       {
/*  64: 89 */         int time = 0;
/*  65: 90 */         while (((StringUtils.isEmpty(returnValue)) || (returnValue.startsWith("(NAK"))) && (time < 2))
/*  66:    */         {
/*  67: 91 */           returnValue = this.usbComm.sendCommand(this.usbId, command + "\r");
/*  68: 92 */           time++;
/*  69:    */         }
/*  70: 94 */         if (StringUtils.isEmpty(returnValue)) {
/*  71: 95 */           result = false;
/*  72:    */         }
/*  73:    */       }
/*  74:100 */       if (CRCUtil.checkCRC(returnValue)) {
/*  75:101 */         returnValue = returnValue.substring(0, returnValue.length() - 2);
/*  76:    */       } else {
/*  77:107 */         returnValue = "";
/*  78:    */       }
/*  79:    */     }
/*  80:    */     catch (Exception ex)
/*  81:    */     {
/*  82:118 */       result = false;
/*  83:    */     }
/*  84:    */     finally
/*  85:    */     {
/*  86:120 */       countErrorandNotifyProcesser(result);
/*  87:    */     }
/*  88:122 */     return returnValue;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void close()
/*  92:    */   {
/*  93:127 */     if (this.usbComm != null) {
/*  94:    */       try
/*  95:    */       {
/*  96:129 */         this.usbComm.closeUSBPort(this.usbId);
/*  97:    */       }
/*  98:    */       catch (Exception localException) {}
/*  99:    */     }
/* 100:    */   }
/* 101:    */   
/* 102:    */   public String getDeviceName()
/* 103:    */   {
/* 104:137 */     return "USB" + this.usbId;
/* 105:    */   }
/* 106:    */   
/* 107:    */   public void setNotifyProcess(AbstractProcessor process)
/* 108:    */   {
/* 109:142 */     this.notifyProcesser = process;
/* 110:    */   }
/* 111:    */   
/* 112:    */   private void countErrorandNotifyProcesser(boolean success)
/* 113:    */   {
/* 114:147 */     if (success) {
/* 115:148 */       this._errorcount = 0;
/* 116:    */     } else {
/* 117:150 */       this._errorcount += 1;
/* 118:    */     }
/* 119:152 */     if ((this._errorcount >= 3) && 
/* 120:153 */       (this.notifyProcesser != null))
/* 121:    */     {
/* 122:154 */       System.out.println("---------communication exception---------" + this._errorcount);
/* 123:155 */       this.notifyProcesser.close();
/* 124:    */     }
/* 125:    */   }
/* 126:    */   
/* 127:    */   public int getMpptTrackNumber()
/* 128:    */   {
/* 129:162 */     int mpptTrackNumber = 2;
/* 130:    */     try
/* 131:    */     {
/* 132:164 */       String result = excuteCommand("QPIRI", true);
/* 133:165 */       if ((result != null) && (!"".equals(result)) && 
/* 134:166 */         (!result.equals("QPIRI")))
/* 135:    */       {
/* 136:167 */         String[] arr = result.split(" ");
/* 137:168 */         mpptTrackNumber = Integer.parseInt(arr[7]);
/* 138:    */       }
/* 139:    */     }
/* 140:    */     catch (Exception ex)
/* 141:    */     {
/* 142:171 */       ex.printStackTrace();
/* 143:    */     }
/* 144:173 */     return mpptTrackNumber;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public String getSerialNo()
/* 148:    */   {
/* 149:178 */     String serialno = "";
/* 150:179 */     String serialnoStr = "";
/* 151:    */     try
/* 152:    */     {
/* 153:181 */       int i = 0;
/* 154:182 */       while (i < 3)
/* 155:    */       {
/* 156:183 */         serialnoStr = excuteCommand("QID", true);
/* 157:184 */         if ((serialnoStr != null) && (!"".equals(serialnoStr)) && 
/* 158:185 */           (!serialnoStr.equalsIgnoreCase("(NAK")) && 
/* 159:186 */           (!serialnoStr.equalsIgnoreCase("(ACK")) && 
/* 160:187 */           (!serialnoStr.equals("QID")))
/* 161:    */         {
/* 162:188 */           serialno = serialnoStr.substring(1);
/* 163:189 */           break;
/* 164:    */         }
/* 165:191 */         i++;
/* 166:    */       }
/* 167:    */     }
/* 168:    */     catch (Exception e)
/* 169:    */     {
/* 170:194 */       e.printStackTrace();
/* 171:    */     }
/* 172:196 */     return serialno;
/* 173:    */   }
/* 174:    */   
/* 175:    */   public String getModeType()
/* 176:    */   {
/* 177:201 */     String machineTypeStr = "";
/* 178:    */     try
/* 179:    */     {
/* 180:203 */       String qpiriStr = excuteCommand("QPIRI", true);
/* 181:204 */       if ((!"".equals(qpiriStr)) && (!qpiriStr.equals("(NAK")) && 
/* 182:205 */         (!qpiriStr.equals("QPIRI")))
/* 183:    */       {
/* 184:206 */         String[] ratingInfo = qpiriStr.split(" ");
/* 185:207 */         return ratingInfo[8];
/* 186:    */       }
/* 187:    */     }
/* 188:    */     catch (Exception ex)
/* 189:    */     {
/* 190:211 */       ex.printStackTrace();
/* 191:    */     }
/* 192:213 */     return machineTypeStr;
/* 193:    */   }
/* 194:    */   
/* 195:    */   public String getDeviceModel()
/* 196:    */   {
/* 197:218 */     String _deviceModel = "";
/* 198:    */     try
/* 199:    */     {
/* 200:220 */       String qdmStr = excuteCommand("QDM", true);
/* 201:221 */       if ((!"".equals(qdmStr)) && (!qdmStr.equals("(NAK")) && 
/* 202:222 */         (!qdmStr.equals("QDM")))
/* 203:    */       {
/* 204:223 */         qdmStr = qdmStr.substring(1);
/* 205:224 */         _deviceModel = qdmStr;
/* 206:    */       }
/* 207:    */     }
/* 208:    */     catch (Exception ex)
/* 209:    */     {
/* 210:227 */       ex.printStackTrace();
/* 211:    */     }
/* 212:229 */     return _deviceModel;
/* 213:    */   }
/* 214:    */   
/* 215:    */   public boolean isSupportQCTH()
/* 216:    */   {
/* 217:234 */     String qchtStr = excuteCommand("QCHT", true);
/* 218:235 */     if ((qchtStr != null) && (!"".equals(qchtStr)) && (!qchtStr.equals("(NAK"))) {
/* 219:236 */       return true;
/* 220:    */     }
/* 221:238 */     return false;
/* 222:    */   }
/* 223:    */   
/* 224:    */   public boolean isSupportQPPS()
/* 225:    */   {
/* 226:243 */     String ppsStr = excuteCommand("QPPS", true);
/* 227:244 */     if ((ppsStr != null) && (!"".equals(ppsStr)) && (!ppsStr.equals("(NAK"))) {
/* 228:245 */       return true;
/* 229:    */     }
/* 230:247 */     return false;
/* 231:    */   }
/* 232:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.USBHandler
 * JD-Core Version:    0.7.0.1
 */