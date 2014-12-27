/*   1:    */ package cn.com.voltronic.solar.communicate;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   4:    */ import java.io.PrintStream;
/*   5:    */ import net.wimpi.modbus.ModbusException;
/*   6:    */ import net.wimpi.modbus.facade.ModbusSerialMaster;
/*   7:    */ import net.wimpi.modbus.procimg.InputRegister;
/*   8:    */ import net.wimpi.modbus.procimg.Register;
/*   9:    */ import net.wimpi.modbus.util.BitVector;
/*  10:    */ 
/*  11:    */ public class ModbusHandler
/*  12:    */   implements ICommunicateDevice
/*  13:    */ {
/*  14:    */   private ModbusHandlerMaster _master;
/*  15:    */   private ModbusSerialMaster _modbus;
/*  16:    */   private int _machineID;
/*  17:    */   private boolean _active;
/*  18: 17 */   private int _errorcount = 0;
/*  19: 19 */   private boolean mustClose = false;
/*  20:    */   private static final int MIN_PERIOD = 80;
/*  21:    */   public static final int DELAY_SHORT = 1;
/*  22:    */   public static final int DELAY_MIDDLE = 2;
/*  23:    */   public static final int DELAY_LONG = 3;
/*  24:    */   public long previoustime;
/*  25: 30 */   public int preiouslength = 0;
/*  26: 32 */   public int delaycount = 0;
/*  27: 33 */   public int successcount = 0;
/*  28:    */   private AbstractProcessor notifyProcesser;
/*  29:    */   
/*  30:    */   public void setMustClose()
/*  31:    */   {
/*  32: 38 */     this.mustClose = true;
/*  33: 39 */     if (this.notifyProcesser != null) {
/*  34: 40 */       this.notifyProcesser.close();
/*  35:    */     }
/*  36:    */   }
/*  37:    */   
/*  38:    */   public boolean isMustClose()
/*  39:    */   {
/*  40: 44 */     return this.mustClose;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void setDelayLevel(int level)
/*  44:    */   {
/*  45: 48 */     if (level == 1) {
/*  46: 49 */       this._modbus.setTransDelayMS(this._master.getShort_delay());
/*  47: 50 */     } else if (level == 2) {
/*  48: 51 */       this._modbus.setTransDelayMS(this._master.getNormal_delay());
/*  49:    */     } else {
/*  50: 53 */       this._modbus.setTransDelayMS(this._master.getLong_delay());
/*  51:    */     }
/*  52:    */   }
/*  53:    */   
/*  54:    */   private void countErrorandNotifyProcesser(int count, boolean success)
/*  55:    */   {
/*  56: 59 */     if (success)
/*  57:    */     {
/*  58: 60 */       this._errorcount = 0;
/*  59: 61 */       this._master.resetError();
/*  60:    */     }
/*  61:    */     else
/*  62:    */     {
/*  63: 63 */       this._errorcount += 1;
/*  64:    */     }
/*  65: 65 */     if (success)
/*  66:    */     {
/*  67: 66 */       this.successcount += 1;
/*  68: 67 */       if (this._modbus.getCurrentTryTimes() > 0)
/*  69:    */       {
/*  70: 68 */         this.delaycount += 1;
/*  71: 69 */         int shortdelay = this._master.getShort_delay();
/*  72: 70 */         if (shortdelay < this._master.getNormal_delay())
/*  73:    */         {
/*  74: 71 */           this._master.setShort_delay(shortdelay + 5);
/*  75: 72 */           this.successcount = 0;
/*  76: 73 */           this.delaycount = 0;
/*  77:    */         }
/*  78: 74 */         else if (((this.successcount >= 20) && (this.delaycount * 20 > this.successcount)) || ((this.successcount < 20) && (this.delaycount > 1)))
/*  79:    */         {
/*  80: 75 */           if (shortdelay < this._master.getLong_delay()) {
/*  81: 76 */             this._master.setShort_delay(shortdelay + 5);
/*  82:    */           }
/*  83: 78 */           this.successcount = 0;
/*  84: 79 */           this.delaycount = 0;
/*  85:    */         }
/*  86:    */       }
/*  87:    */     }
/*  88: 83 */     if ((this._errorcount >= 2) && (this.notifyProcesser != null))
/*  89:    */     {
/*  90: 85 */       System.out.println(" notify process remove" + this.notifyProcesser.processorKey());
/*  91: 86 */       this.notifyProcesser.close();
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   public ModbusHandler(ModbusHandlerMaster master, ModbusSerialMaster modbus, int machineID)
/*  96:    */   {
/*  97: 91 */     this._master = master;
/*  98: 92 */     this._modbus = modbus;
/*  99: 93 */     this._machineID = machineID;
/* 100: 94 */     this._active = false;
/* 101: 95 */     this.previoustime = System.currentTimeMillis();
/* 102:    */   }
/* 103:    */   
/* 104:    */   private void autoAdjustDelaytime(int count)
/* 105:    */   {
/* 106:100 */     long diff = System.currentTimeMillis() - this.previoustime;
/* 107:101 */     if ((this.preiouslength < 4) && (diff < 80L)) {
/* 108:102 */       this._modbus.setTransDelayMS(this._master.getLong_delay());
/* 109:    */     } else {
/* 110:104 */       this._modbus.setTransDelayMS(this._master.getShort_delay());
/* 111:    */     }
/* 112:106 */     this.preiouslength = count;
/* 113:107 */     this.previoustime = System.currentTimeMillis();
/* 114:    */   }
/* 115:    */   
/* 116:    */   public boolean isActive()
/* 117:    */   {
/* 118:111 */     return this._active;
/* 119:    */   }
/* 120:    */   
/* 121:    */   public void setActive(boolean active)
/* 122:    */   {
/* 123:114 */     this._active = active;
/* 124:    */   }
/* 125:    */   
/* 126:    */   public int getMachineID()
/* 127:    */   {
/* 128:118 */     return this._machineID;
/* 129:    */   }
/* 130:    */   
/* 131:    */   public ModbusHandlerMaster getMaster()
/* 132:    */   {
/* 133:122 */     return this._master;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public BitVector readCoils(int ref, int count)
/* 137:    */     throws Exception
/* 138:    */   {
/* 139:127 */     BitVector result = null;
/* 140:128 */     this._master.lock();
/* 141:    */     try
/* 142:    */     {
/* 143:131 */       if (this._modbus != null) {
/* 144:133 */         result = this._modbus.readCoils(this._machineID, ref, count);
/* 145:    */       }
/* 146:    */     }
/* 147:    */     finally
/* 148:    */     {
/* 149:136 */       countErrorandNotifyProcesser(count, result != null);
/* 150:137 */       this._master.unlock();
/* 151:    */     }
/* 152:140 */     return result;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public boolean writeCoil(int ref, boolean state)
/* 156:    */     throws Exception
/* 157:    */   {
/* 158:147 */     this._master.lock();
/* 159:148 */     boolean result = false;
/* 160:    */     try
/* 161:    */     {
/* 162:151 */       if (this._modbus != null) {
/* 163:152 */         result = this._modbus.writeCoil(this._machineID, ref, state);
/* 164:    */       }
/* 165:    */     }
/* 166:    */     finally
/* 167:    */     {
/* 168:155 */       countErrorandNotifyProcesser(1, result);
/* 169:156 */       this._master.unlock();
/* 170:    */     }
/* 171:160 */     return result;
/* 172:    */   }
/* 173:    */   
/* 174:    */   public void writeMultipleCoils(int ref, BitVector coils)
/* 175:    */     throws Exception
/* 176:    */   {
/* 177:172 */     this._master.lock();
/* 178:173 */     boolean result = false;
/* 179:    */     try
/* 180:    */     {
/* 181:175 */       if (this._modbus != null)
/* 182:    */       {
/* 183:176 */         this._modbus.writeMultipleCoils(this._machineID, ref, coils);
/* 184:177 */         result = true;
/* 185:    */       }
/* 186:    */     }
/* 187:    */     finally
/* 188:    */     {
/* 189:180 */       countErrorandNotifyProcesser(1, result);
/* 190:181 */       this._master.unlock();
/* 191:    */     }
/* 192:    */   }
/* 193:    */   
/* 194:    */   public BitVector readInputDiscretes(int ref, int count)
/* 195:    */     throws Exception
/* 196:    */   {
/* 197:190 */     this._master.lock();
/* 198:191 */     BitVector result = null;
/* 199:    */     try
/* 200:    */     {
/* 201:194 */       if (this._modbus != null) {
/* 202:195 */         result = this._modbus.readInputDiscretes(this._machineID, ref, count);
/* 203:    */       }
/* 204:    */     }
/* 205:    */     finally
/* 206:    */     {
/* 207:198 */       countErrorandNotifyProcesser(1, result != null);
/* 208:199 */       this._master.unlock();
/* 209:    */     }
/* 210:202 */     return result;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public InputRegister[] readInputRegisters(int ref, int count)
/* 214:    */     throws Exception
/* 215:    */   {
/* 216:208 */     this._master.lock();
/* 217:209 */     InputRegister[] result = (InputRegister[])null;
/* 218:    */     try
/* 219:    */     {
/* 220:212 */       if (this._modbus != null) {
/* 221:213 */         result = this._modbus.readInputRegisters(this._machineID, ref, count);
/* 222:    */       }
/* 223:    */     }
/* 224:    */     finally
/* 225:    */     {
/* 226:216 */       countErrorandNotifyProcesser(1, result != null);
/* 227:217 */       this._master.unlock();
/* 228:    */     }
/* 229:220 */     return result;
/* 230:    */   }
/* 231:    */   
/* 232:    */   public Register[] readMultipleRegisters(int ref, int count)
/* 233:    */     throws Exception
/* 234:    */   {
/* 235:226 */     this._master.lock();
/* 236:    */     
/* 237:228 */     Register[] result = (Register[])null;
/* 238:    */     try
/* 239:    */     {
/* 240:231 */       if (this._modbus != null)
/* 241:    */       {
/* 242:232 */         autoAdjustDelaytime(count);
/* 243:    */         
/* 244:    */ 
/* 245:    */ 
/* 246:    */ 
/* 247:    */ 
/* 248:    */ 
/* 249:    */ 
/* 250:240 */         result = this._modbus.readMultipleRegisters(this._machineID, ref, count);
/* 251:    */       }
/* 252:    */     }
/* 253:    */     catch (ModbusException e)
/* 254:    */     {
/* 255:244 */       this._master.processError(this._active, e.getMessage());
/* 256:245 */       throw e;
/* 257:    */     }
/* 258:    */     finally
/* 259:    */     {
/* 260:247 */       countErrorandNotifyProcesser(count, result != null);
/* 261:248 */       this._master.unlock();
/* 262:    */     }
/* 263:251 */     return result;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public void writeSingleRegister(int ref, Register register)
/* 267:    */     throws Exception
/* 268:    */   {
/* 269:257 */     this._master.lock();
/* 270:258 */     boolean result = false;
/* 271:    */     try
/* 272:    */     {
/* 273:260 */       if (this._modbus != null)
/* 274:    */       {
/* 275:261 */         this._modbus.writeSingleRegister(this._machineID, ref, register);
/* 276:262 */         result = true;
/* 277:    */       }
/* 278:    */     }
/* 279:    */     finally
/* 280:    */     {
/* 281:265 */       countErrorandNotifyProcesser(1, result);
/* 282:266 */       this._master.unlock();
/* 283:    */     }
/* 284:    */   }
/* 285:    */   
/* 286:    */   public void writeMultipleRegisters(int ref, Register[] registers)
/* 287:    */     throws Exception
/* 288:    */   {
/* 289:274 */     this._master.lock();
/* 290:    */     
/* 291:276 */     boolean result = false;
/* 292:    */     try
/* 293:    */     {
/* 294:278 */       if (this._modbus != null)
/* 295:    */       {
/* 296:279 */         autoAdjustDelaytime(registers.length);
/* 297:    */         
/* 298:    */ 
/* 299:    */ 
/* 300:    */ 
/* 301:    */ 
/* 302:    */ 
/* 303:    */ 
/* 304:287 */         this._modbus.writeMultipleRegisters(this._machineID, ref, registers);
/* 305:    */         
/* 306:289 */         result = true;
/* 307:    */       }
/* 308:    */     }
/* 309:    */     catch (ModbusException e)
/* 310:    */     {
/* 311:292 */       this._master.processError(this._active, e.getMessage());
/* 312:293 */       throw e;
/* 313:    */     }
/* 314:    */     finally
/* 315:    */     {
/* 316:296 */       countErrorandNotifyProcesser(registers.length, result);
/* 317:297 */       this._master.unlock();
/* 318:    */     }
/* 319:    */   }
/* 320:    */   
/* 321:    */   public String getDeviceName()
/* 322:    */   {
/* 323:304 */     return this._master.getDeviceName() + "[" + this._machineID + "]";
/* 324:    */   }
/* 325:    */   
/* 326:    */   public void close()
/* 327:    */   {
/* 328:307 */     setActive(false);
/* 329:308 */     this._master.close(this._machineID);
/* 330:    */   }
/* 331:    */   
/* 332:    */   public void setNotifyProcess(AbstractProcessor process)
/* 333:    */   {
/* 334:313 */     this.notifyProcesser = process;
/* 335:    */   }
/* 336:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.ModbusHandler
 * JD-Core Version:    0.7.0.1
 */