/*   1:    */ package cn.com.voltronic.solar.communicate;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.dao.ModbusDao;
/*   4:    */ import cn.com.voltronic.solar.data.bean.ModbusSet;
/*   5:    */ import gnu.io.CommPortIdentifier;
/*   6:    */ import java.util.ArrayList;
/*   7:    */ import java.util.List;
/*   8:    */ import net.wimpi.modbus.facade.ModbusSerialMaster;
/*   9:    */ import net.wimpi.modbus.util.SerialParameters;
/*  10:    */ 
/*  11:    */ public class ModbusHandlerMaster
/*  12:    */ {
/*  13:    */   private volatile ModbusSerialMaster _modbus;
/*  14:    */   private CommPortIdentifier _portId;
/*  15:    */   private ModbusSet _config;
/*  16:    */   private List<ModbusHandler> _handlers;
/*  17: 29 */   private static ModbusDao dao = new ModbusDao();
/*  18: 30 */   private volatile int _handlersize = 0;
/*  19:    */   private static final String WRITE_FAIL = "I/O failed to write";
/*  20:    */   private static final String READ_FAIL = "Error reading response";
/*  21:    */   private static final int MAX_WRITE_FAIL = 3;
/*  22:    */   private static final int MAX_READ_FAIL = 6;
/*  23:    */   public static final int TIME_OUT = 150;
/*  24: 40 */   private FairLock lock = new FairLock();
/*  25: 51 */   private int readfailcount = 0;
/*  26: 52 */   private int writefailcount = 0;
/*  27: 54 */   private int short_delay = 5;
/*  28: 55 */   private int normal_delay = 20;
/*  29: 56 */   private int long_delay = 50;
/*  30:    */   
/*  31:    */   public void lock()
/*  32:    */     throws Exception
/*  33:    */   {
/*  34: 60 */     this.lock.lock();
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void unlock()
/*  38:    */   {
/*  39: 63 */     this.lock.unlock();
/*  40:    */   }
/*  41:    */   
/*  42:    */   public int getShort_delay()
/*  43:    */   {
/*  44: 67 */     return this.short_delay;
/*  45:    */   }
/*  46:    */   
/*  47:    */   public void setShort_delay(int short_delay)
/*  48:    */   {
/*  49: 71 */     this.short_delay = short_delay;
/*  50:    */   }
/*  51:    */   
/*  52:    */   public int getNormal_delay()
/*  53:    */   {
/*  54: 75 */     return this.normal_delay;
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void setNormal_delay(int normal_delay)
/*  58:    */   {
/*  59: 79 */     this.normal_delay = normal_delay;
/*  60:    */   }
/*  61:    */   
/*  62:    */   public int getLong_delay()
/*  63:    */   {
/*  64: 83 */     return this.long_delay;
/*  65:    */   }
/*  66:    */   
/*  67:    */   public void setLong_delay(int long_delay)
/*  68:    */   {
/*  69: 87 */     this.long_delay = long_delay;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean maybeLost()
/*  73:    */   {
/*  74: 91 */     return this.readfailcount >= 6;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public int getActiveHandlesSize()
/*  78:    */   {
/*  79: 95 */     int activehandlers = 0;
/*  80: 96 */     for (ModbusHandler handler : this._handlers) {
/*  81: 97 */       if ((handler != null) && 
/*  82: 98 */         (handler.isActive())) {
/*  83: 99 */         activehandlers++;
/*  84:    */       }
/*  85:    */     }
/*  86:103 */     return activehandlers;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void processError(boolean isActiveHandler, String errMessage)
/*  90:    */   {
/*  91:108 */     if (errMessage.equalsIgnoreCase("Error reading response"))
/*  92:    */     {
/*  93:109 */       if (isActiveHandler)
/*  94:    */       {
/*  95:110 */         this.readfailcount += 1;
/*  96:111 */         if (this.readfailcount >= 6) {
/*  97:112 */           this._modbus.setRetries(1);
/*  98:    */         }
/*  99:    */       }
/* 100:    */     }
/* 101:115 */     else if (errMessage.equalsIgnoreCase("I/O failed to write"))
/* 102:    */     {
/* 103:116 */       this.writefailcount += 1;
/* 104:117 */       if (this.writefailcount >= 3)
/* 105:    */       {
/* 106:118 */         this._modbus.setRetries(1);
/* 107:119 */         for (ModbusHandler handler : this._handlers) {
/* 108:120 */           handler.setMustClose();
/* 109:    */         }
/* 110:    */       }
/* 111:    */     }
/* 112:    */     else
/* 113:    */     {
/* 114:124 */       resetError();
/* 115:    */     }
/* 116:    */   }
/* 117:    */   
/* 118:    */   public void resetError()
/* 119:    */   {
/* 120:129 */     this.readfailcount = 0;
/* 121:130 */     this.writefailcount = 0;
/* 122:131 */     this._modbus.setRetries(2);
/* 123:    */   }
/* 124:    */   
/* 125:    */   public static boolean isUseCom(CommPortIdentifier portId)
/* 126:    */   {
/* 127:137 */     ModbusSet deviceList = dao.queryModbusSetByPort(portId.getName());
/* 128:138 */     return (deviceList != null) && (!"".equals(deviceList.getDeviceIds()));
/* 129:    */   }
/* 130:    */   
/* 131:    */   public ModbusHandlerMaster(CommPortIdentifier portId)
/* 132:    */     throws Exception
/* 133:    */   {
/* 134:142 */     this._portId = portId;
/* 135:143 */     this._config = dao.queryModbusSetByPort(portId.getName());
/* 136:145 */     if (this._config == null) {
/* 137:146 */       this._config = getDefaultConfig(portId.getName());
/* 138:    */     }
/* 139:148 */     SerialParameters params = new SerialParameters(portId.getName(), 
/* 140:149 */       this._config.getBaudrate(), 
/* 141:150 */       0, 
/* 142:151 */       0, 
/* 143:152 */       this._config.getDataBit(), 
/* 144:153 */       this._config.getStopBit(), 
/* 145:154 */       this._config.getParity(), 
/* 146:155 */       false, 150);
/* 147:156 */     params.setEncoding("rtu");
/* 148:157 */     this._modbus = new ModbusSerialMaster(params);
/* 149:158 */     this._modbus.connect();
/* 150:159 */     this._modbus.setTransDelayMS(10);
/* 151:160 */     this._handlers = new ArrayList();
/* 152:    */   }
/* 153:    */   
/* 154:    */   private ModbusSet getDefaultConfig(String portname)
/* 155:    */   {
/* 156:181 */     ModbusSet set = new ModbusSet();
/* 157:182 */     set.setPortName(portname);
/* 158:183 */     set.setBaudrate(2400);
/* 159:184 */     set.setDataBit(8);
/* 160:185 */     set.setStopBit(2);
/* 161:186 */     set.setParity(0);
/* 162:187 */     set.setDeviceIds("0");
/* 163:188 */     return set;
/* 164:    */   }
/* 165:    */   
/* 166:    */   public ModbusHandler getTestHandler()
/* 167:    */   {
/* 168:192 */     return new ModbusHandler(this, this._modbus, 0);
/* 169:    */   }
/* 170:    */   
/* 171:    */   public void generateHandlers()
/* 172:    */   {
/* 173:196 */     int lastindex = 0;
/* 174:197 */     ModbusSet modbusSet = dao.queryModbusSetByPort(this._portId.getName());
/* 175:198 */     if (modbusSet != null)
/* 176:    */     {
/* 177:199 */       String[] deviceList = modbusSet.getDeviceIds().split(",");
/* 178:200 */       while (this._handlers.contains(null)) {
/* 179:202 */         this._handlers.remove(null);
/* 180:    */       }
/* 181:204 */       for (int i = 0; i < deviceList.length; i++)
/* 182:    */       {
/* 183:205 */         int id = Integer.parseInt(deviceList[i]);
/* 184:206 */         while ((lastindex < this._handlers.size()) && 
/* 185:207 */           (((ModbusHandler)this._handlers.get(lastindex)).getMachineID() < id)) {
/* 186:208 */           lastindex++;
/* 187:    */         }
/* 188:210 */         if (lastindex < this._handlers.size())
/* 189:    */         {
/* 190:211 */           if (((ModbusHandler)this._handlers.get(lastindex)).getMachineID() != id) {
/* 191:212 */             this._handlers.add(lastindex, new ModbusHandler(this, this._modbus, id));
/* 192:    */           }
/* 193:    */         }
/* 194:    */         else {
/* 195:215 */           this._handlers.add(new ModbusHandler(this, this._modbus, id));
/* 196:    */         }
/* 197:    */       }
/* 198:218 */       this._handlersize = this._handlers.size();
/* 199:    */     }
/* 200:    */   }
/* 201:    */   
/* 202:    */   public List<ModbusHandler> getHandlers()
/* 203:    */   {
/* 204:223 */     return this._handlers;
/* 205:    */   }
/* 206:    */   
/* 207:    */   public void close(int machineID)
/* 208:    */   {
/* 209:227 */     for (int index = 0; index < this._handlers.size(); index++)
/* 210:    */     {
/* 211:228 */       ModbusHandler handler = (ModbusHandler)this._handlers.get(index);
/* 212:229 */       if ((handler != null) && (handler.getMachineID() == machineID))
/* 213:    */       {
/* 214:230 */         this._handlersize -= 1;
/* 215:231 */         this._handlers.set(index, null);
/* 216:232 */         break;
/* 217:    */       }
/* 218:    */     }
/* 219:235 */     if (this._handlersize <= 0) {
/* 220:    */       try
/* 221:    */       {
/* 222:238 */         this._handlers.clear();
/* 223:239 */         if (this._modbus != null)
/* 224:    */         {
/* 225:242 */           this._modbus.disconnect();
/* 226:243 */           this._modbus = null;
/* 227:    */         }
/* 228:    */       }
/* 229:    */       catch (Exception e)
/* 230:    */       {
/* 231:248 */         e.printStackTrace();
/* 232:    */       }
/* 233:    */     }
/* 234:    */   }
/* 235:    */   
/* 236:    */   public String getDeviceName()
/* 237:    */   {
/* 238:254 */     String portName = this._portId.getName();
/* 239:255 */     int index = portName.lastIndexOf("/");
/* 240:256 */     if (index > 0) {
/* 241:257 */       portName = portName.substring(index + 1, portName.length());
/* 242:    */     }
/* 243:259 */     return portName;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public static void main(String[] args) {}
/* 247:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.ModbusHandlerMaster
 * JD-Core Version:    0.7.0.1
 */