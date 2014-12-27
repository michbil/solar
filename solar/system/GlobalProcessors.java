/*   1:    */ package cn.com.voltronic.solar.system;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.communicate.ICommunicateDevice;
/*   4:    */ import cn.com.voltronic.solar.configure.LimitCOMConfig;
/*   5:    */ import cn.com.voltronic.solar.configure.SmsConfig;
/*   6:    */ import cn.com.voltronic.solar.data.bean.LocalComs;
/*   7:    */ import cn.com.voltronic.solar.exception.EventsHandler;
/*   8:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   9:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  10:    */ import cn.com.voltronic.solar.view.MainJFrame;
/*  11:    */ import gnu.io.CommPortIdentifier;
/*  12:    */ import gnu.io.PortInUseException;
/*  13:    */ import gnu.io.SerialPort;
/*  14:    */ import java.util.ArrayList;
/*  15:    */ import java.util.Collection;
/*  16:    */ import java.util.Date;
/*  17:    */ import java.util.Enumeration;
/*  18:    */ import java.util.HashMap;
/*  19:    */ import java.util.Iterator;
/*  20:    */ import java.util.List;
/*  21:    */ import java.util.Map;
/*  22:    */ import org.apache.commons.lang.StringUtils;
/*  23:    */ 
/*  24:    */ public class GlobalProcessors
/*  25:    */ {
/*  26: 29 */   private static AbstractProcessor currentProcessor = null;
/*  27: 34 */   private static Map<String, AbstractProcessor> commProcesserMap = new HashMap();
/*  28: 35 */   private static Object addremove = Integer.valueOf(1);
/*  29:    */   
/*  30:    */   public static AbstractProcessor getCurrentProcessor()
/*  31:    */   {
/*  32: 39 */     return currentProcessor;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static void setCurrentProcesserIfExist(String portName)
/*  36:    */   {
/*  37: 43 */     AbstractProcessor processer = null;
/*  38:    */     
/*  39: 45 */     Collection<AbstractProcessor> processers = getProcessersCopy();
/*  40: 46 */     if (StringUtils.isNotEmpty(portName)) {
/*  41: 47 */       synchronized (addremove)
/*  42:    */       {
/*  43: 48 */         processer = (AbstractProcessor)commProcesserMap.get(portName);
/*  44:    */       }
/*  45:    */     }
/*  46: 51 */     if (processers.size() > 0) {
/*  47: 52 */       processer = (AbstractProcessor)processers.iterator().next();
/*  48:    */     } else {
/*  49: 54 */       currentProcessor = null;
/*  50:    */     }
/*  51: 57 */     if (processer != null) {
/*  52: 58 */       if (processer.getParalleltype() == 2)
/*  53:    */       {
/*  54: 59 */         if (currentProcessor.isParent(processer)) {
/*  55: 61 */           currentProcessor = processer;
/*  56:    */         } else {
/*  57: 63 */           for (AbstractProcessor parent : getProcessersCopy()) {
/*  58: 64 */             if ((parent.getParalleltype() == 1) && 
/*  59: 65 */               (parent.isParent(processer)))
/*  60:    */             {
/*  61: 66 */               if (currentProcessor != null) {
/*  62: 67 */                 currentProcessor.setThreadPriority(5);
/*  63:    */               }
/*  64: 69 */               currentProcessor = processer;
/*  65: 70 */               parent.setThreadPriority(10);
/*  66: 71 */               break;
/*  67:    */             }
/*  68:    */           }
/*  69:    */         }
/*  70:    */       }
/*  71:    */       else
/*  72:    */       {
/*  73: 78 */         if (currentProcessor != null) {
/*  74: 79 */           currentProcessor.setThreadPriority(5);
/*  75:    */         }
/*  76: 81 */         currentProcessor = processer;
/*  77: 82 */         currentProcessor.setThreadPriority(10);
/*  78:    */       }
/*  79:    */     }
/*  80: 86 */     MainJFrame.getNewInstance().refreshWorkInfo();
/*  81:    */   }
/*  82:    */   
/*  83:    */   private static AbstractProcessor getProcessorByDeviceName(String deviceName)
/*  84:    */   {
/*  85:116 */     AbstractProcessor processor = null;
/*  86:117 */     for (String key : commProcesserMap.keySet()) {
/*  87:118 */       if (key.startsWith(deviceName))
/*  88:    */       {
/*  89:119 */         processor = (AbstractProcessor)commProcesserMap.get(key);
/*  90:120 */         break;
/*  91:    */       }
/*  92:    */     }
/*  93:123 */     return processor;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public static void addProcessor(String key, AbstractProcessor processor)
/*  97:    */   {
/*  98:128 */     synchronized (addremove)
/*  99:    */     {
/* 100:129 */       commProcesserMap.put(key, processor);
/* 101:130 */       EventsHandler.handleEvent(processor.getProtocol().getProtocolID(), 
/* 102:131 */         processor.getSerialNo(), new Date(), 
/* 103:132 */         "3001");
/* 104:133 */       MainJFrame.getNewInstance().refreshDeviceTree();
/* 105:134 */       MainJFrame.getNewInstance().refreshWorkInfo();
/* 106:    */     }
/* 107:    */   }
/* 108:    */   
/* 109:    */   public static void removeProcessor(String key)
/* 110:    */   {
/* 111:139 */     synchronized (addremove)
/* 112:    */     {
/* 113:140 */       AbstractProcessor processor = getProcessorByDeviceName(key);
/* 114:141 */       if (processor != null)
/* 115:    */       {
/* 116:142 */         EventsHandler.handleEvent(processor.getProtocol().getProtocolID(), 
/* 117:143 */           processor.getSerialNo(), new Date(), 
/* 118:144 */           "3002");
/* 119:145 */         commProcesserMap.remove(key);
/* 120:147 */         if ((currentProcessor != null) && 
/* 121:148 */           (currentProcessor.processorKey().equals(key))) {
/* 122:149 */           setCurrentProcesserIfExist("");
/* 123:    */         }
/* 124:154 */         MainJFrame.getNewInstance().refreshDeviceTree();
/* 125:155 */         MainJFrame.getNewInstance().refreshWorkInfo();
/* 126:156 */         MainJFrame.getNewInstance().setDefaultValues();
/* 127:    */       }
/* 128:    */     }
/* 129:    */   }
/* 130:    */   
/* 131:    */   public static void closeAllProcessers()
/* 132:    */   {
/* 133:169 */     Collection<AbstractProcessor> processers = commProcesserMap.values();
/* 134:170 */     for (AbstractProcessor processer : processers) {
/* 135:171 */       processer.close();
/* 136:    */     }
/* 137:173 */     commProcesserMap.clear();
/* 138:174 */     currentProcessor = null;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public static List<String> getSolarList()
/* 142:    */   {
/* 143:178 */     List<String> solarList = new ArrayList();
/* 144:179 */     for (AbstractProcessor processor : getProcessersCopy()) {
/* 145:180 */       solarList.add(processor.processorKey());
/* 146:    */     }
/* 147:182 */     return solarList;
/* 148:    */   }
/* 149:    */   
/* 150:    */   public static AbstractProcessor findProcessor(String id)
/* 151:    */   {
/* 152:189 */     if (commProcesserMap.containsKey(id)) {
/* 153:190 */       return (AbstractProcessor)commProcesserMap.get(id);
/* 154:    */     }
/* 155:192 */     return null;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public static AbstractProcessor findProcessorBySerialno(String serialno)
/* 159:    */   {
/* 160:196 */     AbstractProcessor processor = null;
/* 161:197 */     Map<String, AbstractProcessor> maps = getProcessorMapCopy();
/* 162:198 */     for (String key : maps.keySet()) {
/* 163:199 */       if (key.endsWith(serialno))
/* 164:    */       {
/* 165:200 */         processor = (AbstractProcessor)maps.get(key);
/* 166:201 */         break;
/* 167:    */       }
/* 168:    */     }
/* 169:204 */     return processor;
/* 170:    */   }
/* 171:    */   
/* 172:    */   public static Collection<AbstractProcessor> getProcessersCopy()
/* 173:    */   {
/* 174:208 */     synchronized (addremove)
/* 175:    */     {
/* 176:209 */       List<AbstractProcessor> copy = new ArrayList();
/* 177:210 */       for (AbstractProcessor process : commProcesserMap.values()) {
/* 178:211 */         copy.add(process);
/* 179:    */       }
/* 180:213 */       return copy;
/* 181:    */     }
/* 182:    */   }
/* 183:    */   
/* 184:    */   public static Map<String, AbstractProcessor> getProcessorMapCopy()
/* 185:    */   {
/* 186:222 */     synchronized (addremove)
/* 187:    */     {
/* 188:223 */       Map<String, AbstractProcessor> copy = new HashMap();
/* 189:224 */       copy.putAll(commProcesserMap);
/* 190:225 */       return copy;
/* 191:    */     }
/* 192:    */   }
/* 193:    */   
/* 194:    */   public static Object getUsedHandlerbyName(String name)
/* 195:    */   {
/* 196:230 */     Collection<AbstractProcessor> listCopy = getProcessersCopy();
/* 197:231 */     for (AbstractProcessor processor : listCopy) {
/* 198:232 */       if (processor != null)
/* 199:    */       {
/* 200:233 */         ICommunicateDevice device = processor.getHandler();
/* 201:234 */         if (device != null) {
/* 202:236 */           if (device.getDeviceName().startsWith(name)) {
/* 203:237 */             return device;
/* 204:    */           }
/* 205:    */         }
/* 206:    */       }
/* 207:    */     }
/* 208:242 */     return null;
/* 209:    */   }
/* 210:    */   
/* 211:    */   public static List<String> getExcludeComs()
/* 212:    */   {
/* 213:251 */     String notComs = GlobalVariables.limitCOMConfig.getLimitcoms();
/* 214:252 */     List<String> nonComs = new ArrayList();
/* 215:253 */     String[] strComs = notComs.split(",");
/* 216:254 */     for (int coms_i = 0; coms_i < strComs.length; coms_i++) {
/* 217:255 */       nonComs.add(strComs[coms_i]);
/* 218:    */     }
/* 219:257 */     return nonComs;
/* 220:    */   }
/* 221:    */   
/* 222:    */   public static String getSMSCom()
/* 223:    */   {
/* 224:265 */     String str = GlobalVariables.smsConfig.getComPort();
/* 225:266 */     return str.trim();
/* 226:    */   }
/* 227:    */   
/* 228:    */   public static ArrayList<LocalComs> getLocalComs()
/* 229:    */   {
/* 230:271 */     ArrayList<LocalComs> ports = new ArrayList();
/* 231:    */     try
/* 232:    */     {
/* 233:273 */       Enumeration portEn = CommPortIdentifier.getPortIdentifiers();
/* 234:275 */       while (portEn.hasMoreElements())
/* 235:    */       {
/* 236:276 */         LocalComs allCom = new LocalComs();
/* 237:277 */         CommPortIdentifier portId = 
/* 238:278 */           (CommPortIdentifier)portEn.nextElement();
/* 239:280 */         if (portId.getPortType() == 1)
/* 240:    */         {
/* 241:281 */           String comName = portId.getName();
/* 242:282 */           if (commPortUsed(portId)) {
/* 243:283 */             allCom.setUsed(true);
/* 244:    */           } else {
/* 245:285 */             allCom.setUsed(false);
/* 246:    */           }
/* 247:287 */           if (getExcludeComs().contains(portId.getName())) {
/* 248:288 */             allCom.setSelected(true);
/* 249:    */           } else {
/* 250:290 */             allCom.setSelected(false);
/* 251:    */           }
/* 252:292 */           allCom.setComName(comName);
/* 253:293 */           ports.add(allCom);
/* 254:    */         }
/* 255:    */       }
/* 256:    */     }
/* 257:    */     catch (Exception e)
/* 258:    */     {
/* 259:297 */       e.printStackTrace();
/* 260:    */     }
/* 261:299 */     return ports;
/* 262:    */   }
/* 263:    */   
/* 264:    */   public static int getProcesserSize()
/* 265:    */   {
/* 266:307 */     return commProcesserMap.size();
/* 267:    */   }
/* 268:    */   
/* 269:    */   public static boolean commPortUsed(CommPortIdentifier portId)
/* 270:    */   {
/* 271:    */     try
/* 272:    */     {
/* 273:312 */       SerialPort port = (SerialPort)portId.open("Arista", 2400);
/* 274:313 */       if (port != null) {
/* 275:314 */         port.close();
/* 276:    */       }
/* 277:    */     }
/* 278:    */     catch (PortInUseException e)
/* 279:    */     {
/* 280:317 */       return true;
/* 281:    */     }
/* 282:319 */     return false;
/* 283:    */   }
/* 284:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.system.GlobalProcessors
 * JD-Core Version:    0.7.0.1
 */