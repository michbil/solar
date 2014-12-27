/*   1:    */ package cn.com.voltronic.solar.processor;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.communicate.ICommunicateDevice;
/*   5:    */ import cn.com.voltronic.solar.comusbprocessor.ParallSubProcessor;
/*   6:    */ import cn.com.voltronic.solar.control.AbstractControlModule;
/*   7:    */ import cn.com.voltronic.solar.dao.DeviceDao;
/*   8:    */ import cn.com.voltronic.solar.data.bean.DeviceBean;
/*   9:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*  10:    */ import cn.com.voltronic.solar.exception.EventsHandler;
/*  11:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  12:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  13:    */ import cn.com.voltronic.solar.util.RunTools;
/*  14:    */ import java.util.HashMap;
/*  15:    */ import net.wimpi.modbus.util.BitVector;
/*  16:    */ 
/*  17:    */ public abstract class AbstractProcessor
/*  18:    */ {
/*  19:    */   public static final int POLL_TIME_PERIOD = 2000;
/*  20:    */   public static final int ENERGY_TIME_PERIOD = 30000;
/*  21:    */   protected ICommunicateDevice _handler;
/*  22:    */   protected BeanBag _beanbag;
/*  23:    */   protected AbstractControlModule _control;
/*  24:    */   protected IProtocol _protocol;
/*  25:    */   protected String _deviceName;
/*  26: 29 */   protected String _faultID = "00";
/*  27: 30 */   protected boolean hasFault = false;
/*  28: 32 */   protected int _paralleltype = 0;
/*  29: 33 */   protected int _parallelnum = 0;
/*  30: 35 */   protected int _outputmode = 0;
/*  31:    */   protected String _serialNo;
/*  32: 38 */   protected HashMap<String, ParallSubProcessor> subMap = null;
/*  33: 40 */   protected int parallkey = 0;
/*  34: 45 */   protected String _processKey = "";
/*  35: 50 */   private boolean startQuerySelfTestResult = false;
/*  36: 54 */   public boolean isfirstquery = true;
/*  37: 55 */   public boolean isfirstenery = true;
/*  38:    */   private WorkMonitor monitorThread;
/*  39: 62 */   public int reconnectTimes = 0;
/*  40:    */   
/*  41:    */   public String getDeviceName()
/*  42:    */   {
/*  43: 65 */     return this._deviceName;
/*  44:    */   }
/*  45:    */   
/*  46:    */   public int getParalleltype()
/*  47:    */   {
/*  48: 69 */     return this._paralleltype;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void setOutputMode(int mode)
/*  52:    */   {
/*  53: 73 */     this._outputmode = mode;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public int getOutputMode()
/*  57:    */   {
/*  58: 76 */     return this._outputmode;
/*  59:    */   }
/*  60:    */   
/*  61:    */   public void setDeviceName(String name)
/*  62:    */   {
/*  63: 80 */     this._deviceName = name;
/*  64:    */   }
/*  65:    */   
/*  66:    */   public boolean isParent(AbstractProcessor subProcess)
/*  67:    */   {
/*  68: 84 */     if ((this._paralleltype == 1) && 
/*  69: 85 */       (this.subMap != null) && (this.subMap.containsKey(subProcess.getSerialNo()))) {
/*  70: 86 */       return true;
/*  71:    */     }
/*  72: 89 */     return false;
/*  73:    */   }
/*  74:    */   
/*  75: 92 */   private volatile boolean _closing = false;
/*  76:    */   
/*  77:    */   public String getSerialNo()
/*  78:    */   {
/*  79: 97 */     return this._serialNo;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public void setSerialNo(String serialno)
/*  83:    */   {
/*  84:101 */     this._serialNo = serialno;
/*  85:    */   }
/*  86:    */   
/*  87:    */   protected String getValidate(String Command)
/*  88:    */   {
/*  89:105 */     int temp = 0;
/*  90:106 */     for (int i = 0; i < Command.length(); i++)
/*  91:    */     {
/*  92:107 */       char ch = Command.charAt(i);
/*  93:108 */       temp += ch;
/*  94:    */     }
/*  95:110 */     temp &= 0xFF;
/*  96:111 */     String ascii = temp;
/*  97:112 */     int index = ascii.length();
/*  98:113 */     while (index < 3)
/*  99:    */     {
/* 100:114 */       ascii = "0" + ascii;
/* 101:115 */       index++;
/* 102:    */     }
/* 103:117 */     return ascii;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public void saveDevice()
/* 107:    */   {
/* 108:121 */     String prodid = getProtocol().getProtocolID();
/* 109:122 */     String serialno = getSerialNo();
/* 110:123 */     DeviceDao dao = new DeviceDao();
/* 111:124 */     if ((serialno != null) && (!"".equals(serialno)) && (!serialno.equals("(NAK")) && 
/* 112:125 */       (!serialno.equals("(ACK")) && 
/* 113:126 */       (prodid != null) && (!"".equals(prodid)))
/* 114:    */     {
/* 115:127 */       DeviceBean bean = new DeviceBean();
/* 116:128 */       bean.setProdid(prodid);
/* 117:129 */       bean.setSerialno(serialno);
/* 118:130 */       bean.setMpptnumber(1);
/* 119:131 */       bean.setModetype("00");
/* 120:132 */       bean.setParallel(this._outputmode);
/* 121:133 */       this.subMap = new HashMap();
/* 122:134 */       dao.InsertOrUpdateDevice(bean);
/* 123:    */     }
/* 124:    */   }
/* 125:    */   
/* 126:    */   public void startMonitor()
/* 127:    */     throws Exception
/* 128:    */   {
/* 129:141 */     if (this._paralleltype != 2)
/* 130:    */     {
/* 131:142 */       if ((this._closing) || (this._handler == null))
/* 132:    */       {
/* 133:144 */         GlobalProcessors.removeProcessor(processorKey());
/* 134:145 */         return;
/* 135:    */       }
/* 136:147 */       saveDevice();
/* 137:148 */       this.monitorThread = new WorkMonitor(this);
/* 138:149 */       this.monitorThread.setPriority(5);
/* 139:150 */       this.monitorThread.start();
/* 140:    */       
/* 141:    */ 
/* 142:153 */       GlobalProcessors.setCurrentProcesserIfExist(processorKey());
/* 143:    */     }
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void close()
/* 147:    */   {
/* 148:159 */     this._closing = true;
/* 149:160 */     if (this.subMap != null)
/* 150:    */     {
/* 151:161 */       for (AbstractProcessor process : this.subMap.values()) {
/* 152:162 */         if (!process.getSerialNo().equalsIgnoreCase(getSerialNo())) {
/* 153:163 */           process.close();
/* 154:    */         }
/* 155:    */       }
/* 156:166 */       this.subMap.clear();
/* 157:    */     }
/* 158:168 */     if ((this._paralleltype != 2) && (getHandler() != null))
/* 159:    */     {
/* 160:169 */       getHandler().close();
/* 161:170 */       this._handler = null;
/* 162:    */     }
/* 163:173 */     GlobalProcessors.removeProcessor(processorKey());
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void setParallKey(int key)
/* 167:    */   {
/* 168:178 */     this.parallkey = key;
/* 169:    */   }
/* 170:    */   
/* 171:    */   public int getParallKey()
/* 172:    */   {
/* 173:182 */     return this.parallkey;
/* 174:    */   }
/* 175:    */   
/* 176:    */   public boolean isClosing()
/* 177:    */   {
/* 178:187 */     return this._closing;
/* 179:    */   }
/* 180:    */   
/* 181:    */   public AbstractProcessor(ICommunicateDevice handler, IProtocol protocol)
/* 182:    */   {
/* 183:191 */     this._closing = false;
/* 184:192 */     this._handler = handler;
/* 185:193 */     this._handler.setNotifyProcess(this);
/* 186:194 */     this._protocol = protocol;
/* 187:195 */     initControlModule();
/* 188:196 */     initBeanBag();
/* 189:197 */     initProtocol();
/* 190:198 */     this._serialNo = this._protocol.getSerialNo();
/* 191:    */   }
/* 192:    */   
/* 193:    */   public abstract boolean queryDefaultData();
/* 194:    */   
/* 195:    */   public String reGenProcesorKey()
/* 196:    */   {
/* 197:205 */     if (this._outputmode == 0) {
/* 198:206 */       this._processKey = (getDeviceName() + "_" + getSerialNo());
/* 199:208 */     } else if (this._outputmode == 1) {
/* 200:209 */       this._processKey = (getDeviceName() + "_" + getSerialNo() + "_PARALLEL");
/* 201:210 */     } else if (this._outputmode == 2) {
/* 202:211 */       this._processKey = (getDeviceName() + "_" + getSerialNo() + "_R PHASE");
/* 203:212 */     } else if (this._outputmode == 3) {
/* 204:213 */       this._processKey = (getDeviceName() + "_" + getSerialNo() + "_S PHASE");
/* 205:214 */     } else if (this._outputmode == 4) {
/* 206:215 */       this._processKey = (getDeviceName() + "_" + getSerialNo() + "_T PHASE");
/* 207:    */     } else {
/* 208:217 */       this._processKey = (getDeviceName() + "_" + getSerialNo());
/* 209:    */     }
/* 210:220 */     return this._processKey;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public String processorKey()
/* 214:    */   {
/* 215:226 */     if (this._processKey.isEmpty()) {
/* 216:227 */       if (this._outputmode == 0) {
/* 217:228 */         this._processKey = (getDeviceName() + "_" + getSerialNo());
/* 218:230 */       } else if (this._outputmode == 1) {
/* 219:231 */         this._processKey = (getDeviceName() + "_" + getSerialNo() + "_PARALLEL");
/* 220:232 */       } else if (this._outputmode == 2) {
/* 221:233 */         this._processKey = (getDeviceName() + "_" + getSerialNo() + "_R PHASE");
/* 222:234 */       } else if (this._outputmode == 3) {
/* 223:235 */         this._processKey = (getDeviceName() + "_" + getSerialNo() + "_S PHASE");
/* 224:236 */       } else if (this._outputmode == 4) {
/* 225:237 */         this._processKey = (getDeviceName() + "_" + getSerialNo() + "_T PHASE");
/* 226:    */       } else {
/* 227:239 */         this._processKey = (getDeviceName() + "_" + getSerialNo());
/* 228:    */       }
/* 229:    */     }
/* 230:243 */     return this._processKey;
/* 231:    */   }
/* 232:    */   
/* 233:    */   protected abstract void initBeanBag();
/* 234:    */   
/* 235:    */   public BeanBag getBeanBag()
/* 236:    */   {
/* 237:249 */     return this._beanbag;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public ICommunicateDevice getHandler()
/* 241:    */   {
/* 242:253 */     return this._handler;
/* 243:    */   }
/* 244:    */   
/* 245:    */   protected abstract void initControlModule();
/* 246:    */   
/* 247:    */   public abstract void querySelfTestResult();
/* 248:    */   
/* 249:    */   public abstract boolean supportSelfTest();
/* 250:    */   
/* 251:    */   public AbstractControlModule getControlModule()
/* 252:    */   {
/* 253:263 */     return this._control;
/* 254:    */   }
/* 255:    */   
/* 256:    */   public abstract void initProtocol();
/* 257:    */   
/* 258:    */   public IProtocol getProtocol()
/* 259:    */   {
/* 260:269 */     return this._protocol;
/* 261:    */   }
/* 262:    */   
/* 263:    */   public boolean executeControl(String commandMethod, Object[] paras)
/* 264:    */   {
/* 265:273 */     return this._control.execute(commandMethod, paras);
/* 266:    */   }
/* 267:    */   
/* 268:    */   public abstract boolean pollQuery();
/* 269:    */   
/* 270:    */   public abstract boolean pollQueryStatus();
/* 271:    */   
/* 272:    */   public abstract boolean queryMachineInfo();
/* 273:    */   
/* 274:    */   public abstract boolean queryCapability();
/* 275:    */   
/* 276:    */   public abstract boolean queryConfigData();
/* 277:    */   
/* 278:    */   public boolean executeQuery(String queryMethod)
/* 279:    */   {
/* 280:287 */     return ((Boolean)RunTools.runMethod(this, queryMethod, null)).booleanValue();
/* 281:    */   }
/* 282:    */   
/* 283:    */   public double parseDoubleV(String str)
/* 284:    */     throws Exception
/* 285:    */   {
/* 286:291 */     return Double.parseDouble(str);
/* 287:    */   }
/* 288:    */   
/* 289:    */   public final void refreshWarningStatus(BitVector warnStatus, BitVector oldwarnings, int index, String eventCode, WorkInfo workinfo)
/* 290:    */   {
/* 291:297 */     if (warnStatus.getBit(index))
/* 292:    */     {
/* 293:298 */       EventsHandler.handleEvent(workinfo.getProdid(), 
/* 294:299 */         workinfo.getSerialno(), workinfo.getCurrentTime(), eventCode);
/* 295:300 */       workinfo.addWarning(eventCode);
/* 296:    */     }
/* 297:    */   }
/* 298:    */   
/* 299:    */   public void setThreadPriority(int priority)
/* 300:    */   {
/* 301:308 */     if (this.monitorThread != null) {
/* 302:309 */       this.monitorThread.setPriority(priority);
/* 303:    */     }
/* 304:    */   }
/* 305:    */   
/* 306:    */   public int getThreadPriority()
/* 307:    */   {
/* 308:314 */     int kmoniter = 5;
/* 309:315 */     int kenergy = 5;
/* 310:316 */     if (this.monitorThread != null) {
/* 311:317 */       kmoniter = this.monitorThread.getPriority();
/* 312:    */     }
/* 313:319 */     if ((kmoniter == 10) || (kenergy == 10)) {
/* 314:320 */       return 10;
/* 315:    */     }
/* 316:322 */     return 5;
/* 317:    */   }
/* 318:    */   
/* 319:    */   public boolean isStartQuerySelfTestResult()
/* 320:    */   {
/* 321:326 */     return this.startQuerySelfTestResult;
/* 322:    */   }
/* 323:    */   
/* 324:    */   public void setStartQuerySelfTestResult(boolean startQuerySelfTestResult)
/* 325:    */   {
/* 326:330 */     this.startQuerySelfTestResult = startQuerySelfTestResult;
/* 327:    */   }
/* 328:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.processor.AbstractProcessor
 * JD-Core Version:    0.7.0.1
 */