/*    1:     */ package cn.com.voltronic.solar.comusbprocessor;
/*    2:     */ 
/*    3:     */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*    4:     */ import cn.com.voltronic.solar.beanbag.P30BeanBag;
/*    5:     */ import cn.com.voltronic.solar.communicate.IComUSBHandler;
/*    6:     */ import cn.com.voltronic.solar.communicate.ICommunicateDevice;
/*    7:     */ import cn.com.voltronic.solar.control.P30ComUSBControlModule;
/*    8:     */ import cn.com.voltronic.solar.data.bean.Capability;
/*    9:     */ import cn.com.voltronic.solar.data.bean.ConfigData;
/*   10:     */ import cn.com.voltronic.solar.data.bean.DefaultData;
/*   11:     */ import cn.com.voltronic.solar.data.bean.MachineInfo;
/*   12:     */ import cn.com.voltronic.solar.data.bean.ProtocolInfo;
/*   13:     */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*   14:     */ import cn.com.voltronic.solar.exception.EventsHandler;
/*   15:     */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   16:     */ import cn.com.voltronic.solar.protocol.IProtocol;
/*   17:     */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   18:     */ import cn.com.voltronic.solar.util.VolUtil;
/*   19:     */ import cn.com.voltronic.solar.view.ParameterJDialog;
/*   20:     */ import java.io.PrintStream;
/*   21:     */ import java.util.ArrayList;
/*   22:     */ import java.util.Date;
/*   23:     */ import java.util.HashMap;
/*   24:     */ import java.util.List;
/*   25:     */ import java.util.Map.Entry;
/*   26:     */ 
/*   27:     */ public class P30ComUSBProcessor
/*   28:     */   extends AbstractComUSBProcessor
/*   29:     */ {
/*   30:  35 */   private boolean refreshConfig = false;
/*   31:  37 */   private int _preparalleltype = 0;
/*   32:  38 */   private boolean bfirsttime = true;
/*   33:     */   
/*   34:     */   public P30ComUSBProcessor(ICommunicateDevice handler, IProtocol protocol)
/*   35:     */   {
/*   36:  43 */     super(handler, protocol);
/*   37:     */   }
/*   38:     */   
/*   39:     */   protected void initBeanBag()
/*   40:     */   {
/*   41:  48 */     this._beanbag = new P30BeanBag();
/*   42:     */   }
/*   43:     */   
/*   44:     */   protected void initControlModule()
/*   45:     */   {
/*   46:  53 */     this._control = new P30ComUSBControlModule(getHandler());
/*   47:     */   }
/*   48:     */   
/*   49:     */   public void initProtocol()
/*   50:     */   {
/*   51:  58 */     ProtocolInfo info = (ProtocolInfo)getBeanBag().getBean(
/*   52:  59 */       "protocolinfo");
/*   53:  60 */     info.setProdid(this._protocol.getProtocolID());
/*   54:  61 */     info.setBaseInfo(this._protocol.getBaseInfo());
/*   55:  62 */     info.setProductInfo(this._protocol.getProductInfo());
/*   56:  63 */     info.setRatingInfo(this._protocol.getRatingInfo());
/*   57:     */     try
/*   58:     */     {
/*   59:  65 */       info.setSerialno(this._protocol.getSerialNo());
/*   60:     */     }
/*   61:     */     catch (Exception e)
/*   62:     */     {
/*   63:  68 */       e.printStackTrace();
/*   64:     */     }
/*   65:  70 */     info.setMoreInfo(this._protocol.getMoreInfo());
/*   66:     */   }
/*   67:     */   
/*   68:     */   public P30BeanBag getBeanBag()
/*   69:     */   {
/*   70:  77 */     return (P30BeanBag)this._beanbag;
/*   71:     */   }
/*   72:     */   
/*   73:     */   private void setFaultcode(WorkInfo workinfo, String faultcode)
/*   74:     */   {
/*   75:  82 */     if (faultcode.equals("00")) {
/*   76:  83 */       workinfo.clearWarning();
/*   77:  84 */     } else if (faultcode.equals("01"))
/*   78:     */     {
/*   79:  85 */       if (!workinfo.getWarnings().contains("1001"))
/*   80:     */       {
/*   81:  86 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*   82:  87 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*   83:  88 */           "1001");
/*   84:  89 */         workinfo.addWarning("1001");
/*   85:     */       }
/*   86:     */     }
/*   87:  91 */     else if (faultcode.equals("02"))
/*   88:     */     {
/*   89:  92 */       if (!workinfo.getWarnings().contains("1011"))
/*   90:     */       {
/*   91:  93 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*   92:  94 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*   93:  95 */           "1011");
/*   94:  96 */         workinfo.addWarning("1011");
/*   95:     */       }
/*   96:     */     }
/*   97:  98 */     else if (faultcode.equals("03"))
/*   98:     */     {
/*   99:  99 */       if (!workinfo.getWarnings().contains("1002"))
/*  100:     */       {
/*  101: 100 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  102: 101 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  103: 102 */           "1002");
/*  104: 103 */         workinfo.addWarning("1002");
/*  105:     */       }
/*  106:     */     }
/*  107: 106 */     else if (faultcode.equals("04"))
/*  108:     */     {
/*  109: 107 */       if (!workinfo.getWarnings().contains("2011"))
/*  110:     */       {
/*  111: 108 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  112: 109 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  113: 110 */           "2011");
/*  114: 111 */         workinfo.addWarning("2011");
/*  115:     */       }
/*  116:     */     }
/*  117: 113 */     else if (faultcode.equals("05"))
/*  118:     */     {
/*  119: 114 */       if (!workinfo.getWarnings().contains("2005"))
/*  120:     */       {
/*  121: 115 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  122: 116 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  123: 117 */           "2005");
/*  124: 118 */         workinfo.addWarning("2005");
/*  125:     */       }
/*  126:     */     }
/*  127: 120 */     else if (faultcode.equals("06"))
/*  128:     */     {
/*  129: 121 */       if (!workinfo.getWarnings().contains("2007"))
/*  130:     */       {
/*  131: 122 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  132: 123 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  133: 124 */           "2007");
/*  134: 125 */         workinfo.addWarning("2007");
/*  135:     */       }
/*  136:     */     }
/*  137: 127 */     else if (faultcode.equals("07"))
/*  138:     */     {
/*  139: 128 */       if (!workinfo.getWarnings().contains("1003"))
/*  140:     */       {
/*  141: 129 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  142: 130 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  143: 131 */           "1003");
/*  144: 132 */         workinfo.addWarning("1003");
/*  145:     */       }
/*  146:     */     }
/*  147: 135 */     else if (faultcode.equals("08"))
/*  148:     */     {
/*  149: 136 */       if (!workinfo.getWarnings().contains("2001"))
/*  150:     */       {
/*  151: 137 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  152: 138 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  153: 139 */           "2001");
/*  154: 140 */         workinfo.addWarning("2001");
/*  155:     */       }
/*  156:     */     }
/*  157: 142 */     else if ((faultcode.equals("09")) || (faultcode.equals("52")))
/*  158:     */     {
/*  159: 143 */       if (!workinfo.getWarnings().contains("2003"))
/*  160:     */       {
/*  161: 144 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  162: 145 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  163: 146 */           "2003");
/*  164: 147 */         workinfo.addWarning("2003");
/*  165:     */       }
/*  166:     */     }
/*  167: 149 */     else if (!faultcode.equals("11")) {
/*  168: 151 */       if (faultcode.equals("53"))
/*  169:     */       {
/*  170: 152 */         if (!workinfo.getWarnings().contains("1005"))
/*  171:     */         {
/*  172: 153 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  173: 154 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  174: 155 */             "1005");
/*  175: 156 */           workinfo.addWarning("1005");
/*  176:     */         }
/*  177:     */       }
/*  178: 158 */       else if (faultcode.equals("54"))
/*  179:     */       {
/*  180: 159 */         if (!workinfo.getWarnings().contains("1006"))
/*  181:     */         {
/*  182: 160 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  183: 161 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  184: 162 */             "1006");
/*  185: 163 */           workinfo.addWarning("1006");
/*  186:     */         }
/*  187:     */       }
/*  188: 165 */       else if (faultcode.equals("55"))
/*  189:     */       {
/*  190: 166 */         if (!workinfo.getWarnings().contains("1007"))
/*  191:     */         {
/*  192: 167 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  193: 168 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  194: 169 */             "1007");
/*  195: 170 */           workinfo.addWarning("1007");
/*  196:     */         }
/*  197:     */       }
/*  198: 172 */       else if (faultcode.equals("56"))
/*  199:     */       {
/*  200: 173 */         if (!workinfo.getWarnings().contains("1008"))
/*  201:     */         {
/*  202: 174 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  203: 175 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  204: 176 */             "1008");
/*  205: 177 */           workinfo.addWarning("1008");
/*  206:     */         }
/*  207:     */       }
/*  208: 179 */       else if (faultcode.equals("57"))
/*  209:     */       {
/*  210: 180 */         if (!workinfo.getWarnings().contains("1009"))
/*  211:     */         {
/*  212: 181 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  213: 182 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  214: 183 */             "1009");
/*  215: 184 */           workinfo.addWarning("1009");
/*  216:     */         }
/*  217:     */       }
/*  218: 186 */       else if (faultcode.equals("58"))
/*  219:     */       {
/*  220: 187 */         if (!workinfo.getWarnings().contains("2006"))
/*  221:     */         {
/*  222: 188 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  223: 189 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  224: 190 */             "2006");
/*  225: 191 */           workinfo.addWarning("2006");
/*  226:     */         }
/*  227:     */       }
/*  228: 193 */       else if (faultcode.equals("60"))
/*  229:     */       {
/*  230: 194 */         if (!workinfo.getWarnings().contains("1060"))
/*  231:     */         {
/*  232: 195 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  233: 196 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  234: 197 */             "1060");
/*  235: 198 */           workinfo.addWarning("1060");
/*  236:     */         }
/*  237:     */       }
/*  238: 200 */       else if (faultcode.equals("71"))
/*  239:     */       {
/*  240: 201 */         if (!workinfo.getWarnings().contains("1071"))
/*  241:     */         {
/*  242: 202 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  243: 203 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  244: 204 */             "1071");
/*  245: 205 */           workinfo.addWarning("1071");
/*  246:     */         }
/*  247:     */       }
/*  248: 207 */       else if (faultcode.equals("72"))
/*  249:     */       {
/*  250: 208 */         if (!workinfo.getWarnings().contains("1072"))
/*  251:     */         {
/*  252: 209 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  253: 210 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  254: 211 */             "1072");
/*  255: 212 */           workinfo.addWarning("1072");
/*  256:     */         }
/*  257:     */       }
/*  258: 214 */       else if (faultcode.equals("80"))
/*  259:     */       {
/*  260: 215 */         if (!workinfo.getWarnings().contains("1080"))
/*  261:     */         {
/*  262: 216 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  263: 217 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  264: 218 */             "1080");
/*  265: 219 */           workinfo.addWarning("1080");
/*  266:     */         }
/*  267:     */       }
/*  268: 221 */       else if (faultcode.equals("81"))
/*  269:     */       {
/*  270: 222 */         if (!workinfo.getWarnings().contains("1081"))
/*  271:     */         {
/*  272: 223 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  273: 224 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  274: 225 */             "1081");
/*  275: 226 */           workinfo.addWarning("1081");
/*  276:     */         }
/*  277:     */       }
/*  278: 228 */       else if (faultcode.equals("82"))
/*  279:     */       {
/*  280: 229 */         if (!workinfo.getWarnings().contains("1082"))
/*  281:     */         {
/*  282: 230 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  283: 231 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  284: 232 */             "1082");
/*  285: 233 */           workinfo.addWarning("1082");
/*  286:     */         }
/*  287:     */       }
/*  288: 235 */       else if (faultcode.equals("83"))
/*  289:     */       {
/*  290: 236 */         if (!workinfo.getWarnings().contains("1083"))
/*  291:     */         {
/*  292: 237 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  293: 238 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  294: 239 */             "1083");
/*  295: 240 */           workinfo.addWarning("1083");
/*  296:     */         }
/*  297:     */       }
/*  298: 242 */       else if (faultcode.equals("84"))
/*  299:     */       {
/*  300: 243 */         if (!workinfo.getWarnings().contains("1084"))
/*  301:     */         {
/*  302: 244 */           EventsHandler.handleEvent(workinfo.getProdid(), 
/*  303: 245 */             workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  304: 246 */             "1084");
/*  305: 247 */           workinfo.addWarning("1084");
/*  306:     */         }
/*  307:     */       }
/*  308: 249 */       else if ((faultcode.equals("85")) && 
/*  309: 250 */         (!workinfo.getWarnings().contains("1085")))
/*  310:     */       {
/*  311: 251 */         EventsHandler.handleEvent(workinfo.getProdid(), 
/*  312: 252 */           workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  313: 253 */           "1085");
/*  314: 254 */         workinfo.addWarning("1085");
/*  315:     */       }
/*  316:     */     }
/*  317:     */   }
/*  318:     */   
/*  319:     */   private boolean explainQPGSN(AbstractProcessor processor, String[] retArr)
/*  320:     */   {
/*  321:     */     try
/*  322:     */     {
/*  323: 261 */       WorkInfo workInfo = (WorkInfo)processor.getBeanBag().getBean("workinfo");
/*  324: 262 */       workInfo.setProdid("P30");
/*  325: 263 */       workInfo.setSerialno(processor.getSerialNo());
/*  326: 264 */       ConfigData configdata = (ConfigData)processor.getBeanBag().getBean("configdata");
/*  327:     */       
/*  328: 266 */       String workMode = retArr[2].trim();
/*  329: 267 */       workInfo.setWorkMode("Standby Mode");
/*  330: 268 */       if (workMode.equals("P")) {
/*  331: 269 */         workInfo.setWorkMode("Power On Mode");
/*  332: 270 */       } else if (workMode.equals("S")) {
/*  333: 271 */         workInfo.setWorkMode("Standby Mode");
/*  334: 272 */       } else if (workMode.equals("L")) {
/*  335: 273 */         workInfo.setWorkMode("Line Mode");
/*  336: 274 */       } else if (workMode.equals("B")) {
/*  337: 275 */         workInfo.setWorkMode("Battery Mode");
/*  338: 276 */       } else if (workMode.equals("F")) {
/*  339: 277 */         workInfo.setWorkMode("Fault Mode");
/*  340: 278 */       } else if (workMode.equals("D")) {
/*  341: 279 */         workInfo.setWorkMode("Shutdown Mode");
/*  342:     */       }
/*  343: 283 */       String faultcode = retArr[3];
/*  344: 284 */       setFaultcode(workInfo, faultcode);
/*  345:     */       
/*  346:     */ 
/*  347:     */ 
/*  348: 288 */       double gridVoltageR = VolUtil.parseDouble(retArr[4]);
/*  349: 289 */       workInfo.setGridVoltageR(gridVoltageR);
/*  350:     */       
/*  351: 291 */       double gridFrequency = VolUtil.parseDouble(retArr[5]);
/*  352: 292 */       workInfo.setGridFrequency(gridFrequency);
/*  353:     */       
/*  354: 294 */       double acOutputVoltageR = VolUtil.parseDouble(retArr[6]);
/*  355: 295 */       workInfo.setAcOutputVoltageR(acOutputVoltageR);
/*  356: 296 */       double acOutputFrequency = VolUtil.parseDouble(retArr[7]);
/*  357: 297 */       workInfo.setAcOutputFrequency(acOutputFrequency);
/*  358:     */       
/*  359: 299 */       double acOutputApparentPower = VolUtil.parseDouble(retArr[8]);
/*  360: 300 */       workInfo.setAcOutputApparentPower(acOutputApparentPower);
/*  361:     */       
/*  362: 302 */       double acOutputActivePower = VolUtil.parseDouble(retArr[9]);
/*  363: 303 */       workInfo.setAcOutputActivePower(acOutputActivePower);
/*  364:     */       
/*  365: 305 */       int outputLoadPercent = VolUtil.parseInt(retArr[10]);
/*  366: 306 */       workInfo.setOutputLoadPercent(outputLoadPercent);
/*  367:     */       
/*  368:     */ 
/*  369: 309 */       double pBatteryVoltage = VolUtil.parseDouble(retArr[11]);
/*  370: 310 */       workInfo.setPBatteryVoltage(pBatteryVoltage);
/*  371:     */       
/*  372: 312 */       double chargingCurrent = VolUtil.parseDouble(retArr[12]);
/*  373: 313 */       workInfo.setChargingCurrent(chargingCurrent);
/*  374:     */       
/*  375: 315 */       int batteryCapacity = VolUtil.parseInt(retArr[13]);
/*  376: 316 */       workInfo.setBatteryCapacity(batteryCapacity);
/*  377:     */       
/*  378: 318 */       double pvInputVoltage1 = VolUtil.parseDouble(retArr[14]);
/*  379: 319 */       workInfo.setPvInputVoltage1(pvInputVoltage1);
/*  380:     */       
/*  381: 321 */       double ttlchargingCurrent = VolUtil.parseDouble(retArr[15]);
/*  382: 322 */       workInfo.setTtlChargingCurrent(ttlchargingCurrent);
/*  383:     */       
/*  384: 324 */       double ttlAcoutputVA = VolUtil.parseDouble(retArr[16]);
/*  385: 325 */       workInfo.setAcTtlOutputApparentPower(ttlAcoutputVA);
/*  386:     */       
/*  387: 327 */       double ttlAcoutputW = VolUtil.parseDouble(retArr[17]);
/*  388: 328 */       workInfo.setAcTtlOutputActivePower(ttlAcoutputW);
/*  389:     */       
/*  390:     */ 
/*  391: 331 */       double ttlAcoutputPercent = VolUtil.parseDouble(retArr[18]);
/*  392: 332 */       workInfo.setAcTtlOutputPercent(ttlAcoutputPercent);
/*  393:     */       
/*  394: 334 */       String status = retArr[19];
/*  395: 335 */       workInfo.setCurrentTime(new Date());
/*  396: 337 */       if (status.length() >= 8)
/*  397:     */       {
/*  398: 338 */         if (status.charAt(7) == '1') {
/*  399: 339 */           this.refreshConfig = true;
/*  400:     */         }
/*  401: 341 */         if (status.charAt(6) == '1') {
/*  402: 342 */           workInfo.setHasLoad(true);
/*  403:     */         } else {
/*  404: 344 */           workInfo.setHasLoad(false);
/*  405:     */         }
/*  406: 346 */         if (status.charAt(5) == '1')
/*  407:     */         {
/*  408: 347 */           workInfo.setLineLoss(true);
/*  409: 348 */           if (!workInfo.getWarnings().contains("2004"))
/*  410:     */           {
/*  411: 349 */             EventsHandler.handleEvent(workInfo.getProdid(), 
/*  412: 350 */               workInfo.getSerialno(), workInfo.getCurrentTime(), 
/*  413: 351 */               "2004");
/*  414: 352 */             workInfo.addWarning("2004");
/*  415:     */           }
/*  416:     */         }
/*  417:     */         else
/*  418:     */         {
/*  419: 355 */           workInfo.setLineLoss(false);
/*  420: 356 */           if (workInfo.getWarnings().contains("2004")) {
/*  421: 357 */             workInfo.getWarnings().remove("2004");
/*  422:     */           }
/*  423:     */         }
/*  424: 361 */         if ((status.charAt(1) == '1') || (status.charAt(2) == '1')) {
/*  425: 362 */           workInfo.setChargeOn(true);
/*  426:     */         } else {
/*  427: 364 */           workInfo.setChargeOn(false);
/*  428:     */         }
/*  429: 366 */         if (status.charAt(1) == '1') {
/*  430: 367 */           workInfo.setACchargeOn(true);
/*  431:     */         } else {
/*  432: 369 */           workInfo.setACchargeOn(false);
/*  433:     */         }
/*  434: 371 */         if (status.charAt(2) == '1') {
/*  435: 372 */           workInfo.setSCCchargeOn(true);
/*  436:     */         } else {
/*  437: 374 */           workInfo.setSCCchargeOn(false);
/*  438:     */         }
/*  439: 376 */         if (workInfo.isChargeOn())
/*  440:     */         {
/*  441: 377 */           if ((workInfo.isSCCchargeOn()) && (workInfo.isACchargeOn())) {
/*  442: 378 */             workInfo.setChargeSource("Solar and Utility");
/*  443: 379 */           } else if (workInfo.isSCCchargeOn()) {
/*  444: 380 */             workInfo.setChargeSource("Solar");
/*  445: 381 */           } else if (workInfo.isACchargeOn()) {
/*  446: 382 */             workInfo.setChargeSource("Utility");
/*  447:     */           }
/*  448:     */         }
/*  449:     */         else {
/*  450: 385 */           workInfo.setChargeSource("---");
/*  451:     */         }
/*  452: 387 */         if (workInfo.isHasLoad())
/*  453:     */         {
/*  454: 388 */           if (workInfo.getWorkMode().equals("Line Mode")) {
/*  455: 389 */             workInfo.setLoadSource("Utility");
/*  456: 390 */           } else if (workInfo.getWorkMode().equals("Battery Mode")) {
/*  457: 391 */             workInfo.setLoadSource("Battery");
/*  458:     */           }
/*  459:     */         }
/*  460:     */         else {
/*  461: 394 */           workInfo.setLoadSource("---");
/*  462:     */         }
/*  463:     */       }
/*  464: 398 */       if (retArr.length > 20)
/*  465:     */       {
/*  466: 399 */         int outputtype = 0;
/*  467:     */         try
/*  468:     */         {
/*  469: 401 */           outputtype = Integer.parseInt(retArr[20]);
/*  470:     */         }
/*  471:     */         catch (Exception e)
/*  472:     */         {
/*  473: 403 */           e.printStackTrace();
/*  474:     */         }
/*  475: 405 */         processor.setOutputMode(outputtype);
/*  476: 407 */         if ((processor instanceof ParallSubProcessor)) {
/*  477: 408 */           configdata.setSubOutputMode(processor.getSerialNo(), outputtype);
/*  478:     */         } else {
/*  479: 410 */           configdata.setOutputMode(outputtype);
/*  480:     */         }
/*  481:     */       }
/*  482: 413 */       if (retArr.length > 23)
/*  483:     */       {
/*  484: 414 */         String chargingSource = retArr[21];
/*  485: 415 */         String chargeSource = "Utility";
/*  486: 416 */         if (chargingSource.equals("0")) {
/*  487: 417 */           chargeSource = "Utility";
/*  488: 418 */         } else if (chargingSource.equals("1")) {
/*  489: 419 */           chargeSource = "Solar first";
/*  490: 420 */         } else if (chargingSource.equals("2")) {
/*  491: 421 */           chargeSource = "Utility and Solar";
/*  492: 422 */         } else if (chargingSource.equals("3")) {
/*  493: 423 */           chargeSource = "Solar only";
/*  494:     */         }
/*  495: 425 */         if ((processor instanceof ParallSubProcessor)) {
/*  496: 426 */           configdata.setChargerSource(processor.getSerialNo(), chargeSource);
/*  497:     */         } else {
/*  498: 428 */           configdata.setChargerSource(chargeSource);
/*  499:     */         }
/*  500: 431 */         String maxchangestr = retArr[22];
/*  501: 432 */         int maxchargecurrent = VolUtil.parseInt(maxchangestr);
/*  502: 434 */         if ((processor instanceof ParallSubProcessor)) {
/*  503: 435 */           configdata.setMaxChargeCurrent(processor.getSerialNo(), maxchargecurrent);
/*  504:     */         } else {
/*  505: 437 */           configdata.setMaxChargeCurrent(maxchargecurrent);
/*  506:     */         }
/*  507: 441 */         String maxmaxchargestr = retArr[23];
/*  508: 442 */         int maxmaxcharge = VolUtil.parseInt(maxmaxchargestr);
/*  509: 443 */         if ((processor instanceof ParallSubProcessor))
/*  510:     */         {
/*  511: 444 */           configdata.setMinMaxChargeCurrent(processor.getSerialNo(), 10.0D);
/*  512: 445 */           configdata.setMaxMaxChargeCurrent(processor.getSerialNo(), maxmaxcharge);
/*  513:     */         }
/*  514:     */         else
/*  515:     */         {
/*  516: 447 */           configdata.setMaxMaxChargeCurrent(maxmaxcharge);
/*  517: 448 */           configdata.setMinMaxChargeCurrent(10.0D);
/*  518:     */         }
/*  519:     */       }
/*  520: 452 */       if (retArr.length > 24)
/*  521:     */       {
/*  522: 453 */         int maxacchargecurrent = VolUtil.parseInt(retArr[24]);
/*  523: 454 */         if ((processor instanceof ParallSubProcessor)) {
/*  524: 455 */           configdata.setMaxacchargingcurrent(processor.getSerialNo(), maxacchargecurrent);
/*  525:     */         } else {
/*  526: 457 */           configdata.setMaxacchargingcurrent(maxacchargecurrent);
/*  527:     */         }
/*  528:     */       }
/*  529: 461 */       if (retArr.length > 25)
/*  530:     */       {
/*  531: 462 */         int pvInputCurrent = VolUtil.parseInt(retArr[25]);
/*  532: 463 */         workInfo.setPvInputCurrent(pvInputCurrent);
/*  533:     */       }
/*  534: 466 */       if (retArr.length > 26)
/*  535:     */       {
/*  536: 467 */         int batDischargeCurrent = VolUtil.parseInt(retArr[26]);
/*  537: 468 */         workInfo.setBatDisCurrent(batDischargeCurrent);
/*  538:     */       }
/*  539:     */     }
/*  540:     */     catch (Exception e)
/*  541:     */     {
/*  542: 475 */       return false;
/*  543:     */     }
/*  544: 477 */     return true;
/*  545:     */   }
/*  546:     */   
/*  547:     */   private boolean pollQueryParallel()
/*  548:     */   {
/*  549: 482 */     boolean result = true;
/*  550: 483 */     boolean bParentLoss = true;
/*  551: 484 */     int parall_i = 0;
/*  552: 485 */     ArrayList<String> curList = new ArrayList();
/*  553: 486 */     ArrayList<String> delList = new ArrayList();
/*  554: 487 */     IComUSBHandler handler = (IComUSBHandler)getHandler();
/*  555: 488 */     if (handler == null) {
/*  556: 489 */       return false;
/*  557:     */     }
/*  558:     */     try
/*  559:     */     {
/*  560:     */       String[] spiltArray;
/*  561: 494 */       for (parall_i = 0; parall_i < this._parallelnum; parall_i++)
/*  562:     */       {
/*  563: 495 */         String ret = handler.excuteCommand("QPGS" + parall_i, true);
/*  564:     */         
/*  565: 497 */         System.out.println("QPGS" + parall_i + "=>" + ret);
/*  566: 498 */         spiltArray = ret.split(" ");
/*  567: 499 */         int outputtype = 0;
/*  568: 500 */         if (spiltArray.length > 20) {
/*  569: 501 */           outputtype = VolUtil.parseInt(spiltArray[20]);
/*  570:     */         }
/*  571: 504 */         if ((spiltArray[0].length() >= 2) && (spiltArray[0].charAt(1) == '1') && (outputtype != 0))
/*  572:     */         {
/*  573: 505 */           String serial = spiltArray[1].trim();
/*  574: 506 */           curList.add(serial);
/*  575: 507 */           ParallSubProcessor processor = null;
/*  576: 508 */           if (this.subMap.containsKey(serial))
/*  577:     */           {
/*  578: 509 */             processor = (ParallSubProcessor)this.subMap.get(serial);
/*  579: 510 */             processor.setParallKey(parall_i);
/*  580: 511 */             explainQPGSN(processor, spiltArray);
/*  581: 512 */             String oldKey = processor.processorKey();
/*  582: 513 */             if (!processor.reGenProcesorKey().equalsIgnoreCase(oldKey))
/*  583:     */             {
/*  584: 514 */               GlobalProcessors.removeProcessor(oldKey);
/*  585: 515 */               GlobalProcessors.addProcessor(processor.processorKey(), processor);
/*  586:     */             }
/*  587:     */           }
/*  588: 518 */           else if (serial.equalsIgnoreCase(getSerialNo()))
/*  589:     */           {
/*  590: 519 */             setParallKey(parall_i);
/*  591: 520 */             explainQPGSN(this, spiltArray);
/*  592: 521 */             String oldKey = processorKey();
/*  593: 522 */             if (!reGenProcesorKey().equalsIgnoreCase(oldKey))
/*  594:     */             {
/*  595: 523 */               GlobalProcessors.removeProcessor(oldKey);
/*  596: 524 */               GlobalProcessors.addProcessor(processorKey(), this);
/*  597:     */             }
/*  598: 526 */             bParentLoss = false;
/*  599:     */           }
/*  600:     */           else
/*  601:     */           {
/*  602: 528 */             processor = new ParallSubProcessor(this, new P30BeanBag());
/*  603: 529 */             processor.setDeviceName(getDeviceName());
/*  604: 530 */             processor.setSerialNo(serial);
/*  605: 531 */             processor.setParallKey(parall_i);
/*  606: 532 */             explainQPGSN(processor, spiltArray);
/*  607: 533 */             this.subMap.put(serial, processor);
/*  608: 534 */             processor.saveDevice();
/*  609: 535 */             GlobalProcessors.addProcessor(processor.processorKey(), processor);
/*  610:     */           }
/*  611:     */         }
/*  612:     */       }
/*  613: 540 */       for (Map.Entry<String, ParallSubProcessor> entry : this.subMap.entrySet()) {
/*  614: 541 */         if (curList.indexOf(entry.getKey()) < 0)
/*  615:     */         {
/*  616: 542 */           ((ParallSubProcessor)entry.getValue()).close();
/*  617: 543 */           delList.add((String)entry.getKey());
/*  618:     */         }
/*  619:     */       }
/*  620: 546 */       for (String key : delList) {
/*  621: 547 */         this.subMap.remove(key);
/*  622:     */       }
/*  623: 549 */       if (bParentLoss) {
/*  624: 550 */         close();
/*  625:     */       }
/*  626:     */     }
/*  627:     */     catch (Exception ex)
/*  628:     */     {
/*  629: 554 */       ex.printStackTrace();
/*  630:     */       
/*  631: 556 */       result = false;
/*  632:     */     }
/*  633: 558 */     return result;
/*  634:     */   }
/*  635:     */   
/*  636:     */   public boolean pollQueryStatus()
/*  637:     */   {
/*  638: 565 */     boolean result = true;
/*  639: 566 */     IComUSBHandler handler = (IComUSBHandler)getHandler();
/*  640: 567 */     if (handler == null) {
/*  641: 568 */       return false;
/*  642:     */     }
/*  643: 570 */     if (this._paralleltype != 2)
/*  644:     */     {
/*  645: 571 */       WorkInfo workinfo = (WorkInfo)getBeanBag().getBean(
/*  646: 572 */         "workinfo");
/*  647:     */       try
/*  648:     */       {
/*  649: 574 */         workinfo.setProdid("P30");
/*  650: 575 */         workinfo.setSerialno(getProtocol().getSerialNo());
/*  651:     */         
/*  652:     */ 
/*  653: 578 */         String qpiws = handler.excuteCommand("QPIWS", true);
/*  654: 580 */         if ((!"".equals(qpiws)) && (!qpiws.equals("(NAK")))
/*  655:     */         {
/*  656: 581 */           String qpiwsStr = qpiws.substring(1);
/*  657: 582 */           if (qpiwsStr.charAt(1) == '1') {
/*  658: 583 */             this.hasFault = true;
/*  659:     */           } else {
/*  660: 585 */             this.hasFault = false;
/*  661:     */           }
/*  662: 587 */           if (qpiwsStr.charAt(2) == '1')
/*  663:     */           {
/*  664: 588 */             if (!workinfo.getWarnings().contains("2001"))
/*  665:     */             {
/*  666: 589 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  667: 590 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  668: 591 */                 "2001");
/*  669: 592 */               workinfo.addWarning("2001");
/*  670:     */             }
/*  671:     */           }
/*  672: 595 */           else if (workinfo.getWarnings().contains("2001")) {
/*  673: 596 */             workinfo.getWarnings().remove("2001");
/*  674:     */           }
/*  675: 599 */           if (qpiwsStr.charAt(3) == '1')
/*  676:     */           {
/*  677: 600 */             if (!workinfo.getWarnings().contains("2002"))
/*  678:     */             {
/*  679: 601 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  680: 602 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  681: 603 */                 "2002");
/*  682: 604 */               workinfo.addWarning("2002");
/*  683:     */             }
/*  684:     */           }
/*  685: 607 */           else if (workinfo.getWarnings().contains("2002")) {
/*  686: 608 */             workinfo.getWarnings().remove("2002");
/*  687:     */           }
/*  688: 611 */           if (qpiwsStr.charAt(4) == '1')
/*  689:     */           {
/*  690: 612 */             if (!workinfo.getWarnings().contains("2003"))
/*  691:     */             {
/*  692: 613 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  693: 614 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  694: 615 */                 "2003");
/*  695: 616 */               workinfo.addWarning("2003");
/*  696:     */             }
/*  697:     */           }
/*  698: 619 */           else if (workinfo.getWarnings().contains("2003")) {
/*  699: 620 */             workinfo.getWarnings().remove("2003");
/*  700:     */           }
/*  701: 623 */           if (qpiwsStr.charAt(5) == '1')
/*  702:     */           {
/*  703: 624 */             workinfo.setLineLoss(true);
/*  704: 625 */             if (!workinfo.getWarnings().contains("2004"))
/*  705:     */             {
/*  706: 626 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  707: 627 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  708: 628 */                 "2004");
/*  709: 629 */               workinfo.addWarning("2004");
/*  710:     */             }
/*  711:     */           }
/*  712:     */           else
/*  713:     */           {
/*  714: 632 */             workinfo.setLineLoss(false);
/*  715: 633 */             if (workinfo.getWarnings().contains("2004")) {
/*  716: 634 */               workinfo.getWarnings().remove("2004");
/*  717:     */             }
/*  718:     */           }
/*  719: 637 */           if (qpiwsStr.charAt(6) == '1')
/*  720:     */           {
/*  721: 638 */             if (!workinfo.getWarnings().contains("2005"))
/*  722:     */             {
/*  723: 639 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  724: 640 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  725: 641 */                 "2005");
/*  726: 642 */               workinfo.addWarning("2005");
/*  727:     */             }
/*  728:     */           }
/*  729: 645 */           else if (workinfo.getWarnings().contains("2005")) {
/*  730: 646 */             workinfo.getWarnings().remove("2005");
/*  731:     */           }
/*  732: 649 */           if (qpiwsStr.charAt(7) == '1')
/*  733:     */           {
/*  734: 650 */             if (!workinfo.getWarnings().contains("2006"))
/*  735:     */             {
/*  736: 651 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  737: 652 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  738: 653 */                 "2006");
/*  739: 654 */               workinfo.addWarning("2006");
/*  740:     */             }
/*  741:     */           }
/*  742: 657 */           else if (workinfo.getWarnings().contains("2006")) {
/*  743: 658 */             workinfo.getWarnings().remove("2006");
/*  744:     */           }
/*  745: 661 */           if (qpiwsStr.charAt(8) == '1')
/*  746:     */           {
/*  747: 662 */             if (!workinfo.getWarnings().contains("2007"))
/*  748:     */             {
/*  749: 663 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  750: 664 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  751: 665 */                 "2007");
/*  752: 666 */               workinfo.addWarning("2007");
/*  753:     */             }
/*  754:     */           }
/*  755: 669 */           else if (workinfo.getWarnings().contains("2007")) {
/*  756: 670 */             workinfo.getWarnings().remove("2007");
/*  757:     */           }
/*  758: 673 */           if (qpiwsStr.charAt(9) == '1')
/*  759:     */           {
/*  760: 674 */             if (this.hasFault)
/*  761:     */             {
/*  762: 675 */               if (!workinfo.getWarnings().contains("1011"))
/*  763:     */               {
/*  764: 676 */                 EventsHandler.handleEvent(workinfo.getProdid(), 
/*  765: 677 */                   workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  766: 678 */                   "1011");
/*  767: 679 */                 workinfo.addWarning("1011");
/*  768:     */               }
/*  769:     */             }
/*  770: 682 */             else if (!workinfo.getWarnings().contains("2008"))
/*  771:     */             {
/*  772: 683 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  773: 684 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  774: 685 */                 "2008");
/*  775: 686 */               workinfo.addWarning("2008");
/*  776:     */             }
/*  777:     */           }
/*  778:     */           else
/*  779:     */           {
/*  780: 690 */             if (workinfo.getWarnings().contains("2008")) {
/*  781: 691 */               workinfo.getWarnings().remove("2008");
/*  782:     */             }
/*  783: 693 */             if (workinfo.getWarnings().contains("1011")) {
/*  784: 694 */               workinfo.getWarnings().remove("1011");
/*  785:     */             }
/*  786:     */           }
/*  787: 697 */           if (qpiwsStr.charAt(10) == '1')
/*  788:     */           {
/*  789: 698 */             if (this.hasFault)
/*  790:     */             {
/*  791: 699 */               if (!workinfo.getWarnings().contains("1001"))
/*  792:     */               {
/*  793: 700 */                 EventsHandler.handleEvent(workinfo.getProdid(), 
/*  794: 701 */                   workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  795: 702 */                   "1001");
/*  796: 703 */                 workinfo.addWarning("1001");
/*  797:     */               }
/*  798:     */             }
/*  799: 706 */             else if (!workinfo.getWarnings().contains("2009"))
/*  800:     */             {
/*  801: 707 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  802: 708 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  803: 709 */                 "2009");
/*  804: 710 */               workinfo.addWarning("2009");
/*  805:     */             }
/*  806:     */           }
/*  807:     */           else
/*  808:     */           {
/*  809: 714 */             if (workinfo.getWarnings().contains("2009")) {
/*  810: 715 */               workinfo.getWarnings().remove("2009");
/*  811:     */             }
/*  812: 717 */             if (workinfo.getWarnings().contains("1001")) {
/*  813: 718 */               workinfo.getWarnings().remove("1001");
/*  814:     */             }
/*  815:     */           }
/*  816: 721 */           if (qpiwsStr.charAt(11) == '1')
/*  817:     */           {
/*  818: 722 */             if (this.hasFault)
/*  819:     */             {
/*  820: 723 */               if (!workinfo.getWarnings().contains("1002"))
/*  821:     */               {
/*  822: 724 */                 EventsHandler.handleEvent(workinfo.getProdid(), 
/*  823: 725 */                   workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  824: 726 */                   "1002");
/*  825: 727 */                 workinfo.addWarning("1002");
/*  826:     */               }
/*  827:     */             }
/*  828: 730 */             else if (!workinfo.getWarnings().contains("2010"))
/*  829:     */             {
/*  830: 731 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  831: 732 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  832: 733 */                 "2010");
/*  833: 734 */               workinfo.addWarning("2010");
/*  834:     */             }
/*  835:     */           }
/*  836:     */           else
/*  837:     */           {
/*  838: 738 */             if (workinfo.getWarnings().contains("2010")) {
/*  839: 739 */               workinfo.getWarnings().remove("2010");
/*  840:     */             }
/*  841: 741 */             if (workinfo.getWarnings().contains("1002")) {
/*  842: 742 */               workinfo.getWarnings().remove("1002");
/*  843:     */             }
/*  844:     */           }
/*  845: 745 */           if (qpiwsStr.charAt(12) == '1')
/*  846:     */           {
/*  847: 746 */             if (!workinfo.getWarnings().contains("2011"))
/*  848:     */             {
/*  849: 747 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  850: 748 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  851: 749 */                 "2011");
/*  852: 750 */               workinfo.addWarning("2011");
/*  853:     */             }
/*  854:     */           }
/*  855: 753 */           else if (workinfo.getWarnings().contains("2011")) {
/*  856: 754 */             workinfo.getWarnings().remove("2011");
/*  857:     */           }
/*  858: 757 */           if (qpiwsStr.charAt(13) == '1')
/*  859:     */           {
/*  860: 758 */             if (!workinfo.getWarnings().contains("2012"))
/*  861:     */             {
/*  862: 759 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  863: 760 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  864: 761 */                 "2012");
/*  865: 762 */               workinfo.addWarning("2012");
/*  866:     */             }
/*  867:     */           }
/*  868: 765 */           else if (workinfo.getWarnings().contains("2012")) {
/*  869: 766 */             workinfo.getWarnings().remove("2012");
/*  870:     */           }
/*  871: 769 */           if (qpiwsStr.charAt(14) == '1')
/*  872:     */           {
/*  873: 770 */             if (!workinfo.getWarnings().contains("2013"))
/*  874:     */             {
/*  875: 771 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  876: 772 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  877: 773 */                 "2013");
/*  878: 774 */               workinfo.addWarning("2013");
/*  879:     */             }
/*  880:     */           }
/*  881: 777 */           else if (workinfo.getWarnings().contains("2013")) {
/*  882: 778 */             workinfo.removeWarning("2013");
/*  883:     */           }
/*  884: 782 */           if (qpiwsStr.charAt(15) == '1')
/*  885:     */           {
/*  886: 783 */             if (!workinfo.getWarnings().contains("2014"))
/*  887:     */             {
/*  888: 784 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  889: 785 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  890: 786 */                 "2014");
/*  891: 787 */               workinfo.addWarning("2014");
/*  892:     */             }
/*  893:     */           }
/*  894: 790 */           else if (workinfo.getWarnings().contains("2014")) {
/*  895: 791 */             workinfo.removeWarning("2014");
/*  896:     */           }
/*  897: 794 */           if (qpiwsStr.charAt(16) == '1')
/*  898:     */           {
/*  899: 795 */             if (this.hasFault)
/*  900:     */             {
/*  901: 796 */               if (!workinfo.getWarnings().contains("1003"))
/*  902:     */               {
/*  903: 797 */                 EventsHandler.handleEvent(workinfo.getProdid(), 
/*  904: 798 */                   workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  905: 799 */                   "1003");
/*  906: 800 */                 workinfo.addWarning("1003");
/*  907:     */               }
/*  908:     */             }
/*  909: 803 */             else if (!workinfo.getWarnings().contains("2015"))
/*  910:     */             {
/*  911: 804 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  912: 805 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  913: 806 */                 "2015");
/*  914: 807 */               workinfo.addWarning("2015");
/*  915:     */             }
/*  916:     */           }
/*  917:     */           else
/*  918:     */           {
/*  919: 811 */             if (workinfo.getWarnings().contains("2015")) {
/*  920: 812 */               workinfo.removeWarning("2015");
/*  921:     */             }
/*  922: 814 */             if (workinfo.getWarnings().contains("1003")) {
/*  923: 815 */               workinfo.removeWarning("1003");
/*  924:     */             }
/*  925:     */           }
/*  926: 835 */           if (qpiwsStr.charAt(18) == '1')
/*  927:     */           {
/*  928: 836 */             if (!workinfo.getWarnings().contains("1004"))
/*  929:     */             {
/*  930: 837 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  931: 838 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  932: 839 */                 "1004");
/*  933: 840 */               workinfo.addWarning("1004");
/*  934:     */             }
/*  935:     */           }
/*  936: 843 */           else if (workinfo.getWarnings().contains("1004")) {
/*  937: 844 */             workinfo.removeWarning("1004");
/*  938:     */           }
/*  939: 847 */           if (qpiwsStr.charAt(19) == '1')
/*  940:     */           {
/*  941: 848 */             if (!workinfo.getWarnings().contains("1005"))
/*  942:     */             {
/*  943: 849 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  944: 850 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  945: 851 */                 "1005");
/*  946: 852 */               workinfo.addWarning("1005");
/*  947:     */             }
/*  948:     */           }
/*  949: 855 */           else if (workinfo.getWarnings().contains("1005")) {
/*  950: 856 */             workinfo.removeWarning("1005");
/*  951:     */           }
/*  952: 859 */           if (qpiwsStr.charAt(20) == '1')
/*  953:     */           {
/*  954: 860 */             if (!workinfo.getWarnings().contains("1006"))
/*  955:     */             {
/*  956: 861 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  957: 862 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  958: 863 */                 "1006");
/*  959: 864 */               workinfo.addWarning("1006");
/*  960:     */             }
/*  961:     */           }
/*  962: 867 */           else if (workinfo.getWarnings().contains("1006")) {
/*  963: 868 */             workinfo.removeWarning("1006");
/*  964:     */           }
/*  965: 871 */           if (qpiwsStr.charAt(21) == '1')
/*  966:     */           {
/*  967: 872 */             if (!workinfo.getWarnings().contains("1007"))
/*  968:     */             {
/*  969: 873 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  970: 874 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  971: 875 */                 "1007");
/*  972: 876 */               workinfo.addWarning("1007");
/*  973:     */             }
/*  974:     */           }
/*  975: 879 */           else if (workinfo.getWarnings().contains("1007")) {
/*  976: 880 */             workinfo.removeWarning("1007");
/*  977:     */           }
/*  978: 883 */           if (qpiwsStr.charAt(22) == '1')
/*  979:     */           {
/*  980: 884 */             if (!workinfo.getWarnings().contains("1008"))
/*  981:     */             {
/*  982: 885 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  983: 886 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  984: 887 */                 "1008");
/*  985: 888 */               workinfo.addWarning("1008");
/*  986:     */             }
/*  987:     */           }
/*  988: 891 */           else if (workinfo.getWarnings().contains("1008")) {
/*  989: 892 */             workinfo.removeWarning("1008");
/*  990:     */           }
/*  991: 895 */           if (qpiwsStr.charAt(23) == '1')
/*  992:     */           {
/*  993: 896 */             if (!workinfo.getWarnings().contains("1009"))
/*  994:     */             {
/*  995: 897 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/*  996: 898 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/*  997: 899 */                 "1009");
/*  998: 900 */               workinfo.addWarning("1009");
/*  999:     */             }
/* 1000:     */           }
/* 1001: 903 */           else if (workinfo.getWarnings().contains("1009")) {
/* 1002: 904 */             workinfo.removeWarning("1009");
/* 1003:     */           }
/* 1004: 907 */           if (qpiwsStr.charAt(24) == '1')
/* 1005:     */           {
/* 1006: 908 */             if (!workinfo.getWarnings().contains("1010"))
/* 1007:     */             {
/* 1008: 909 */               EventsHandler.handleEvent(workinfo.getProdid(), 
/* 1009: 910 */                 workinfo.getSerialno(), workinfo.getCurrentTime(), 
/* 1010: 911 */                 "1010");
/* 1011: 912 */               workinfo.addWarning("1010");
/* 1012:     */             }
/* 1013:     */           }
/* 1014: 915 */           else if (workinfo.getWarnings().contains("1010")) {
/* 1015: 916 */             workinfo.removeWarning("1010");
/* 1016:     */           }
/* 1017: 922 */           if (qpiwsStr.charAt(15) == '1') {
/* 1018: 923 */             workinfo.setOverLoad(true);
/* 1019:     */           } else {
/* 1020: 925 */             workinfo.setOverLoad(false);
/* 1021:     */           }
/* 1022:     */         }
/* 1023: 928 */         this.reconnectTimes = 0;
/* 1024:     */       }
/* 1025:     */       catch (Exception e)
/* 1026:     */       {
/* 1027: 930 */         result = false;
/* 1028: 931 */         e.printStackTrace();
/* 1029: 932 */         workinfo.clear();
/* 1030: 933 */         this.reconnectTimes += 1;
/* 1031:     */       }
/* 1032:     */     }
/* 1033: 936 */     return result;
/* 1034:     */   }
/* 1035:     */   
/* 1036:     */   public boolean pollQuery()
/* 1037:     */   {
/* 1038: 941 */     boolean result = true;
/* 1039: 942 */     IComUSBHandler handler = (IComUSBHandler)getHandler();
/* 1040: 943 */     if (handler == null)
/* 1041:     */     {
/* 1042: 944 */       close();
/* 1043: 945 */       return false;
/* 1044:     */     }
/* 1045: 950 */     if (this.refreshConfig)
/* 1046:     */     {
/* 1047: 951 */       getProtocol().setDelayChanging(true);
/* 1048: 952 */       queryMachineInfo();
/* 1049: 953 */       queryConfigData();
/* 1050: 954 */       queryCapability();
/* 1051: 955 */       this.refreshConfig = false;
/* 1052:     */     }
/* 1053: 958 */     if (this.bfirsttime)
/* 1054:     */     {
/* 1055: 959 */       this._preparalleltype = this._paralleltype;
/* 1056: 960 */       this.bfirsttime = false;
/* 1057:     */     }
/* 1058: 962 */     else if (this._preparalleltype != this._paralleltype)
/* 1059:     */     {
/* 1060: 963 */       close();
/* 1061: 964 */       return false;
/* 1062:     */     }
/* 1063: 967 */     if (!ParameterJDialog.startQueryThread) {
/* 1064: 969 */       getProtocol().setDelayChanging(false);
/* 1065:     */     }
/* 1066: 972 */     if (!getSerialNo().equals(handler.getSerialNo()))
/* 1067:     */     {
/* 1068: 973 */       close();
/* 1069: 974 */       return false;
/* 1070:     */     }
/* 1071: 977 */     if (this._paralleltype != 0) {
/* 1072: 978 */       return pollQueryParallel();
/* 1073:     */     }
/* 1074: 983 */     WorkInfo workInfo = (WorkInfo)getBeanBag().getBean("workinfo");
/* 1075:     */     try
/* 1076:     */     {
/* 1077: 985 */       workInfo.setProdid("P30");
/* 1078: 986 */       workInfo.setSerialno(handler.getSerialNo());
/* 1079: 987 */       String qmodStrs = handler.excuteCommand("QMOD", true);
/* 1080: 989 */       if ((!"".equals(qmodStrs)) && (!qmodStrs.equals("(NAK")))
/* 1081:     */       {
/* 1082: 990 */         String qmodStr = qmodStrs.substring(1);
/* 1083: 991 */         String workMode = "Standby Mode";
/* 1084: 992 */         if (qmodStr.equals("P")) {
/* 1085: 993 */           workMode = "Power On Mode";
/* 1086: 994 */         } else if (qmodStr.equals("S")) {
/* 1087: 995 */           workMode = "Standby Mode";
/* 1088: 996 */         } else if (qmodStr.equals("L")) {
/* 1089: 997 */           workMode = "Line Mode";
/* 1090: 998 */         } else if (qmodStr.equals("B")) {
/* 1091: 999 */           workMode = "Battery Mode";
/* 1092:1000 */         } else if (qmodStr.equals("F")) {
/* 1093:1001 */           workMode = "Fault Mode";
/* 1094:1002 */         } else if (qmodStr.equals("D")) {
/* 1095:1003 */           workMode = "Shutdown Mode";
/* 1096:     */         }
/* 1097:1005 */         workInfo.setWorkMode(workMode);
/* 1098:     */       }
/* 1099:1008 */       String qpigsStr = handler.excuteCommand("QPIGS", true);
/* 1100:1010 */       if ((!"".equals(qpigsStr)) && (!qpigsStr.equals("(NAK")))
/* 1101:     */       {
/* 1102:1013 */         String[] qpigsArr = new String[18];
/* 1103:1014 */         qpigsArr[0] = qpigsStr.substring(0, 6);
/* 1104:1015 */         qpigsArr[1] = qpigsStr.substring(7, 11);
/* 1105:1016 */         qpigsArr[2] = qpigsStr.substring(12, 17);
/* 1106:1017 */         qpigsArr[3] = qpigsStr.substring(18, 22);
/* 1107:1018 */         qpigsArr[4] = qpigsStr.substring(23, 27);
/* 1108:1019 */         qpigsArr[5] = qpigsStr.substring(28, 32);
/* 1109:1020 */         qpigsArr[6] = qpigsStr.substring(33, 36);
/* 1110:1021 */         qpigsArr[7] = qpigsStr.substring(37, 40);
/* 1111:1022 */         qpigsArr[8] = qpigsStr.substring(41, 46);
/* 1112:1023 */         qpigsArr[9] = qpigsStr.substring(47, 49);
/* 1113:1024 */         qpigsArr[10] = qpigsStr.substring(50, 53);
/* 1114:1025 */         qpigsArr[11] = qpigsStr.substring(54, 58);
/* 1115:1026 */         qpigsArr[12] = qpigsStr.substring(59, 63);
/* 1116:1027 */         qpigsArr[13] = qpigsStr.substring(64, 69);
/* 1117:1028 */         qpigsArr[14] = qpigsStr.substring(70, 75);
/* 1118:1029 */         qpigsArr[15] = qpigsStr.substring(76, 81);
/* 1119:1030 */         qpigsArr[16] = qpigsStr.substring(82, 90);
/* 1120:     */         
/* 1121:     */ 
/* 1122:1033 */         double gridVoltageR = VolUtil.parseDouble(qpigsArr[0]
/* 1123:1034 */           .substring(1));
/* 1124:1035 */         workInfo.setGridVoltageR(gridVoltageR);
/* 1125:1036 */         double gridFrequency = VolUtil.parseDouble(qpigsArr[1]);
/* 1126:1037 */         workInfo.setGridFrequency(gridFrequency);
/* 1127:1038 */         double acOutputVoltageR = VolUtil.parseDouble(qpigsArr[2]);
/* 1128:1039 */         workInfo.setAcOutputVoltageR(acOutputVoltageR);
/* 1129:1040 */         double acOutputFrequency = VolUtil.parseDouble(qpigsArr[3]);
/* 1130:1041 */         workInfo.setAcOutputFrequency(acOutputFrequency);
/* 1131:     */         
/* 1132:1043 */         double acOutputApparentPower = VolUtil.parseDouble(qpigsArr[4]);
/* 1133:1044 */         workInfo.setAcOutputApparentPower(acOutputApparentPower);
/* 1134:     */         
/* 1135:1046 */         double acOutputActivePower = VolUtil.parseDouble(qpigsArr[5]);
/* 1136:1047 */         workInfo.setAcOutputActivePower(acOutputActivePower);
/* 1137:1048 */         int outputLoadPercent = VolUtil.parseInt(qpigsArr[6]);
/* 1138:1049 */         workInfo.setOutputLoadPercent(outputLoadPercent);
/* 1139:1050 */         double pBusVoltage = VolUtil.parseDouble(qpigsArr[7]);
/* 1140:1051 */         workInfo.setPBUSVoltage(pBusVoltage);
/* 1141:1052 */         double pBatteryVoltage = VolUtil.parseDouble(qpigsArr[8]);
/* 1142:1053 */         workInfo.setPBatteryVoltage(pBatteryVoltage);
/* 1143:     */         
/* 1144:1055 */         double chargingCurrent = VolUtil.parseDouble(qpigsArr[9]);
/* 1145:1056 */         workInfo.setChargingCurrent(chargingCurrent);
/* 1146:1057 */         int batteryCapacity = VolUtil.parseInt(qpigsArr[10]);
/* 1147:1058 */         workInfo.setBatteryCapacity(batteryCapacity);
/* 1148:1059 */         int pvInputPower1 = VolUtil.parseInt(qpigsArr[11]);
/* 1149:1060 */         workInfo.setPvInputPower1(pvInputPower1);
/* 1150:     */         
/* 1151:1062 */         int pvInputCurrent = VolUtil.parseInt(qpigsArr[12]);
/* 1152:1063 */         workInfo.setPvInputCurrent(pvInputCurrent);
/* 1153:     */         
/* 1154:     */ 
/* 1155:     */ 
/* 1156:1067 */         double pvInputVoltage1 = VolUtil.parseDouble(qpigsArr[13]);
/* 1157:1068 */         workInfo.setPvInputVoltage1(pvInputVoltage1);
/* 1158:1069 */         double pvInputVoltage2 = VolUtil.parseDouble(qpigsArr[14]);
/* 1159:1070 */         workInfo.setPvInputVoltage2(pvInputVoltage2);
/* 1160:     */         
/* 1161:     */ 
/* 1162:1073 */         int batDischargeCurrent = VolUtil.parseInt(qpigsArr[15]);
/* 1163:1074 */         workInfo.setBatDisCurrent(batDischargeCurrent);
/* 1164:     */         
/* 1165:     */ 
/* 1166:1077 */         workInfo.setCurrentTime(new Date());
/* 1167:     */         
/* 1168:1079 */         String deviceStatus = qpigsArr[16];
/* 1169:     */         
/* 1170:     */ 
/* 1171:1082 */         char ch0 = deviceStatus.charAt(0);
/* 1172:1083 */         if (ch0 == '1') {
/* 1173:1084 */           workInfo.setCustomerV("1");
/* 1174:     */         } else {
/* 1175:1086 */           workInfo.setCustomerV("0");
/* 1176:     */         }
/* 1177:1089 */         char ch1 = deviceStatus.charAt(1);
/* 1178:1090 */         if (ch1 == '1')
/* 1179:     */         {
/* 1180:1091 */           getProtocol().setDelayChanging(true);
/* 1181:1092 */           queryMachineInfo();
/* 1182:1093 */           queryCapability();
/* 1183:1094 */           queryConfigData();
/* 1184:     */         }
/* 1185:1098 */         char ch2 = deviceStatus.charAt(2);
/* 1186:1099 */         if (ch2 == '1') {
/* 1187:1100 */           queryMachineInfo();
/* 1188:     */         }
/* 1189:1103 */         char ch3 = deviceStatus.charAt(3);
/* 1190:1104 */         if (ch3 == '1') {
/* 1191:1105 */           workInfo.setHasLoad(true);
/* 1192:     */         } else {
/* 1193:1107 */           workInfo.setHasLoad(false);
/* 1194:     */         }
/* 1195:1110 */         char ch5 = deviceStatus.charAt(5);
/* 1196:1111 */         if (ch5 == '1') {
/* 1197:1112 */           workInfo.setChargeOn(true);
/* 1198:     */         } else {
/* 1199:1114 */           workInfo.setChargeOn(false);
/* 1200:     */         }
/* 1201:1117 */         char ch6 = deviceStatus.charAt(6);
/* 1202:1118 */         if (ch6 == '1') {
/* 1203:1119 */           workInfo.setSCCchargeOn(true);
/* 1204:     */         } else {
/* 1205:1121 */           workInfo.setSCCchargeOn(false);
/* 1206:     */         }
/* 1207:1123 */         char ch7 = deviceStatus.charAt(7);
/* 1208:1124 */         if (ch7 == '1') {
/* 1209:1125 */           workInfo.setACchargeOn(true);
/* 1210:     */         } else {
/* 1211:1127 */           workInfo.setACchargeOn(false);
/* 1212:     */         }
/* 1213:1131 */         if (workInfo.isChargeOn())
/* 1214:     */         {
/* 1215:1132 */           if ((workInfo.isSCCchargeOn()) && (workInfo.isACchargeOn())) {
/* 1216:1133 */             workInfo.setChargeSource("Solar and Utility");
/* 1217:1134 */           } else if (workInfo.isSCCchargeOn()) {
/* 1218:1135 */             workInfo.setChargeSource("Solar");
/* 1219:1136 */           } else if (workInfo.isACchargeOn()) {
/* 1220:1137 */             workInfo.setChargeSource("Utility");
/* 1221:     */           }
/* 1222:     */         }
/* 1223:     */         else {
/* 1224:1140 */           workInfo.setChargeSource("---");
/* 1225:     */         }
/* 1226:1143 */         if (workInfo.isHasLoad())
/* 1227:     */         {
/* 1228:1144 */           if (workInfo.getWorkMode().equals("Line Mode")) {
/* 1229:1145 */             workInfo.setLoadSource("Utility");
/* 1230:1146 */           } else if (workInfo.getWorkMode().equals("Battery Mode")) {
/* 1231:1147 */             workInfo.setLoadSource("Battery");
/* 1232:     */           }
/* 1233:     */         }
/* 1234:     */         else {
/* 1235:1150 */           workInfo.setLoadSource("---");
/* 1236:     */         }
/* 1237:     */       }
/* 1238:     */     }
/* 1239:     */     catch (Exception ex)
/* 1240:     */     {
/* 1241:1154 */       result = false;
/* 1242:1155 */       ex.printStackTrace();
/* 1243:     */     }
/* 1244:1157 */     return result;
/* 1245:     */   }
/* 1246:     */   
/* 1247:     */   public boolean queryMachineInfo()
/* 1248:     */   {
/* 1249:1162 */     IComUSBHandler handler = (IComUSBHandler)getHandler();
/* 1250:1163 */     if (handler == null) {
/* 1251:1164 */       return false;
/* 1252:     */     }
/* 1253:1166 */     boolean result = true;
/* 1254:1167 */     MachineInfo machineInfo = (MachineInfo)getBeanBag().getBean(
/* 1255:1168 */       "machineinfo");
/* 1256:     */     try
/* 1257:     */     {
/* 1258:1170 */       machineInfo.setSerialno(handler.getSerialNo());
/* 1259:1171 */       String mainFirmwareVersion = handler.excuteCommand("QVFW", 
/* 1260:1172 */         true);
/* 1261:1173 */       if ((!"".equals(mainFirmwareVersion)) && 
/* 1262:1174 */         (!mainFirmwareVersion.equals("(NAK"))) {
/* 1263:1175 */         machineInfo.setMainFirmwareVersion(
/* 1264:1176 */           mainFirmwareVersion.substring(7).trim());
/* 1265:     */       }
/* 1266:1178 */       System.out.println("mainFirmwareVersion=" + mainFirmwareVersion);
/* 1267:1179 */       String slaveFirmwareVersion = handler.excuteCommand("QVFW2", 
/* 1268:1180 */         true);
/* 1269:1182 */       if ((!"".equals(slaveFirmwareVersion)) && 
/* 1270:1183 */         (!slaveFirmwareVersion.equals("(NAK"))) {
/* 1271:1184 */         machineInfo.setSlaveFirmwareVersion(
/* 1272:1185 */           slaveFirmwareVersion.substring(8).trim());
/* 1273:     */       }
/* 1274:1187 */       String qpiriStr = handler.excuteCommand("QPIRI", true);
/* 1275:1188 */       if ((!"".equals(qpiriStr)) && (!qpiriStr.equals("(NAK")))
/* 1276:     */       {
/* 1277:1189 */         String[] ratingInfo = qpiriStr.split(" ");
/* 1278:1190 */         double gridRatingVoltage = VolUtil.parseDouble(ratingInfo[0]
/* 1279:1191 */           .substring(1));
/* 1280:1192 */         double gridRatingCurrent = VolUtil.parseDouble(ratingInfo[1]);
/* 1281:1193 */         double acOutputRatingVoltage = VolUtil.parseDouble(ratingInfo[2]);
/* 1282:1194 */         double acOutputRatingFrequency = VolUtil.parseDouble(ratingInfo[3]);
/* 1283:1195 */         double acOutputRatingCurrent = VolUtil.parseDouble(ratingInfo[4]);
/* 1284:1196 */         double acOutputRatingApparentPower = VolUtil.parseDouble(ratingInfo[5]);
/* 1285:1197 */         double acOutputRatingActivePower = VolUtil.parseDouble(ratingInfo[6]);
/* 1286:1198 */         double batteryRatingVoltage = VolUtil.parseDouble(ratingInfo[7]);
/* 1287:1199 */         String batteryTypeStr = ratingInfo[12];
/* 1288:1200 */         double perMPPTRatingCurrent = VolUtil.parseDouble(ratingInfo[13]);
/* 1289:1201 */         String inputVoltageRangeStr = ratingInfo[15];
/* 1290:1202 */         String outputSourceStr = ratingInfo[16];
/* 1291:1203 */         String chargeSourceStr = ratingInfo[17];
/* 1292:     */         
/* 1293:1205 */         String machineTypeStr = ratingInfo[19];
/* 1294:1206 */         String topologyStr = ratingInfo[20];
/* 1295:1207 */         machineInfo.setGridRatingVoltage(gridRatingVoltage);
/* 1296:1208 */         machineInfo.setGridRatingCurrent(gridRatingCurrent);
/* 1297:1209 */         machineInfo.setAcOutputRatingVoltage(acOutputRatingVoltage);
/* 1298:1210 */         machineInfo.setAcOutputRatingFrequency(acOutputRatingFrequency);
/* 1299:1211 */         machineInfo.setAcOutputRatingCurrent(acOutputRatingCurrent);
/* 1300:1212 */         machineInfo.setAcOutputRatingApparentPower(acOutputRatingApparentPower);
/* 1301:1213 */         machineInfo.setAcOutputRatingActivePower(acOutputRatingActivePower);
/* 1302:1214 */         machineInfo.setBatteryRatingVoltage(batteryRatingVoltage);
/* 1303:1215 */         machineInfo.setPerMPPTRatingCurrent(perMPPTRatingCurrent);
/* 1304:1216 */         String batteryType = "---";
/* 1305:1217 */         if (batteryTypeStr.equals("0")) {
/* 1306:1218 */           batteryType = "AGM";
/* 1307:1219 */         } else if (batteryTypeStr.equals("1")) {
/* 1308:1220 */           batteryType = "Flooded";
/* 1309:     */         }
/* 1310:1222 */         machineInfo.setBatteryType(batteryType);
/* 1311:1223 */         String inputVoltageRange = "---";
/* 1312:1224 */         System.out.println("15=" + inputVoltageRangeStr);
/* 1313:1225 */         if (inputVoltageRangeStr.equals("0")) {
/* 1314:1226 */           inputVoltageRange = "Appliance";
/* 1315:1229 */         } else if (inputVoltageRangeStr.equals("1")) {
/* 1316:1230 */           inputVoltageRange = "UPS";
/* 1317:     */         }
/* 1318:1239 */         if (ratingInfo.length > 21) {
/* 1319:     */           try
/* 1320:     */           {
/* 1321:1241 */             int outputmode = Integer.parseInt(ratingInfo[21]);
/* 1322:1242 */             this._protocol.setOutputMode(outputmode);
/* 1323:1243 */             this._outputmode = outputmode;
/* 1324:1244 */             if (outputmode != 0)
/* 1325:     */             {
/* 1326:1245 */               this._paralleltype = 1;
/* 1327:1246 */               this._parallelnum = VolUtil.parseInt(ratingInfo[18]);
/* 1328:     */             }
/* 1329:     */             else
/* 1330:     */             {
/* 1331:1248 */               this._paralleltype = 0;
/* 1332:     */             }
/* 1333:     */           }
/* 1334:     */           catch (Exception localException1) {}
/* 1335:     */         }
/* 1336:1255 */         machineInfo.setInputVoltageRange(inputVoltageRange);
/* 1337:1256 */         String outputSource = "---";
/* 1338:1257 */         if (outputSourceStr.equals("0")) {
/* 1339:1258 */           outputSource = "Utility";
/* 1340:1259 */         } else if (outputSourceStr.equals("1")) {
/* 1341:1260 */           outputSource = "Solar";
/* 1342:     */         }
/* 1343:1262 */         machineInfo.setOutputSource(outputSource);
/* 1344:1263 */         String chargeSource = "---";
/* 1345:1264 */         if (chargeSourceStr.equals("0")) {
/* 1346:1265 */           chargeSource = "Utility";
/* 1347:1266 */         } else if (chargeSourceStr.equals("1")) {
/* 1348:1267 */           chargeSource = "Solar";
/* 1349:1268 */         } else if (chargeSourceStr.equals("2")) {
/* 1350:1269 */           chargeSource = "Solar and Utility";
/* 1351:     */         }
/* 1352:1271 */         machineInfo.setChargeSource(chargeSource);
/* 1353:     */         
/* 1354:1273 */         String machineType = "---";
/* 1355:1274 */         if (machineTypeStr.equals("00")) {
/* 1356:1275 */           machineType = "Grid tie";
/* 1357:1276 */         } else if (machineTypeStr.equals("01")) {
/* 1358:1277 */           machineType = "Stand alone";
/* 1359:1278 */         } else if (machineTypeStr.equals("10")) {
/* 1360:1279 */           machineType = "Hybrid";
/* 1361:     */         }
/* 1362:1281 */         machineInfo.setMachineType(machineType);
/* 1363:1282 */         String topology = "---";
/* 1364:1283 */         if (topologyStr.equals("0")) {
/* 1365:1284 */           topology = "transformerless";
/* 1366:1285 */         } else if (topologyStr.equals("1")) {
/* 1367:1286 */           topology = "transformer";
/* 1368:     */         }
/* 1369:1288 */         machineInfo.setTopology(topology);
/* 1370:     */         
/* 1371:1290 */         System.out.println("after query queryMachineInfo");
/* 1372:     */       }
/* 1373:     */     }
/* 1374:     */     catch (Exception e)
/* 1375:     */     {
/* 1376:1293 */       e.printStackTrace();
/* 1377:1294 */       result = false;
/* 1378:     */     }
/* 1379:1296 */     return result;
/* 1380:     */   }
/* 1381:     */   
/* 1382:     */   public boolean queryCapability()
/* 1383:     */   {
/* 1384:1301 */     boolean result = true;
/* 1385:1302 */     IComUSBHandler handler = (IComUSBHandler)getHandler();
/* 1386:1303 */     if (handler == null) {
/* 1387:1304 */       return false;
/* 1388:     */     }
/* 1389:1306 */     Capability capability = (Capability)getBeanBag().getBean(
/* 1390:1307 */       "capability");
/* 1391:     */     try
/* 1392:     */     {
/* 1393:1309 */       String qflagStr = handler.excuteCommand("QFLAG", true);
/* 1394:1310 */       System.out.println("qflagStr:" + qflagStr);
/* 1395:1311 */       if ((!"".equals(qflagStr)) && (!qflagStr.equals("(NAK")))
/* 1396:     */       {
/* 1397:1312 */         String before = qflagStr.substring(qflagStr.indexOf("E"), 
/* 1398:1313 */           qflagStr.indexOf("D"));
/* 1399:1314 */         if (before.indexOf('a') != -1) {
/* 1400:1315 */           capability.setCapableA(true);
/* 1401:     */         } else {
/* 1402:1317 */           capability.setCapableA(false);
/* 1403:     */         }
/* 1404:1319 */         if (before.indexOf('j') != -1) {
/* 1405:1320 */           capability.setCapableJ(true);
/* 1406:     */         } else {
/* 1407:1322 */           capability.setCapableJ(false);
/* 1408:     */         }
/* 1409:1324 */         if (before.indexOf('k') != -1) {
/* 1410:1325 */           capability.setCapableK(true);
/* 1411:     */         } else {
/* 1412:1327 */           capability.setCapableK(false);
/* 1413:     */         }
/* 1414:1330 */         if (before.indexOf('u') != -1) {
/* 1415:1331 */           capability.setCapableU(true);
/* 1416:     */         } else {
/* 1417:1333 */           capability.setCapableU(false);
/* 1418:     */         }
/* 1419:1335 */         if (before.indexOf('v') != -1) {
/* 1420:1336 */           capability.setCapableV(true);
/* 1421:     */         } else {
/* 1422:1338 */           capability.setCapableV(false);
/* 1423:     */         }
/* 1424:1340 */         if (before.indexOf('w') != -1) {
/* 1425:1341 */           capability.setCapableW(true);
/* 1426:     */         } else {
/* 1427:1343 */           capability.setCapableW(false);
/* 1428:     */         }
/* 1429:1345 */         if (before.indexOf('x') != -1) {
/* 1430:1346 */           capability.setCapableX(true);
/* 1431:     */         } else {
/* 1432:1348 */           capability.setCapableX(false);
/* 1433:     */         }
/* 1434:1350 */         if (before.indexOf('y') != -1) {
/* 1435:1351 */           capability.setCapableY(true);
/* 1436:     */         } else {
/* 1437:1353 */           capability.setCapableY(false);
/* 1438:     */         }
/* 1439:1355 */         if (before.indexOf('z') != -1) {
/* 1440:1356 */           capability.setCapableZ(true);
/* 1441:     */         } else {
/* 1442:1358 */           capability.setCapableZ(false);
/* 1443:     */         }
/* 1444:1360 */         if (before.indexOf('b') != -1) {
/* 1445:1361 */           capability.setCapableB(true);
/* 1446:     */         } else {
/* 1447:1363 */           capability.setCapableB(false);
/* 1448:     */         }
/* 1449:1365 */         if (before.indexOf('p') != -1) {
/* 1450:1366 */           capability.setCapableP(true);
/* 1451:     */         } else {
/* 1452:1368 */           capability.setCapableP(false);
/* 1453:     */         }
/* 1454:1370 */         if (before.indexOf('g') != -1) {
/* 1455:1371 */           capability.setCapableG(true);
/* 1456:     */         } else {
/* 1457:1373 */           capability.setCapableG(false);
/* 1458:     */         }
/* 1459:     */       }
/* 1460:     */     }
/* 1461:     */     catch (Exception e)
/* 1462:     */     {
/* 1463:1377 */       e.printStackTrace();
/* 1464:1378 */       result = false;
/* 1465:1379 */       System.err.println(e.getMessage());
/* 1466:     */     }
/* 1467:1381 */     return result;
/* 1468:     */   }
/* 1469:     */   
/* 1470:     */   public boolean queryConfigData()
/* 1471:     */   {
/* 1472:1386 */     boolean result = true;
/* 1473:     */     try
/* 1474:     */     {
/* 1475:1388 */       ConfigData configdata = (ConfigData)getBeanBag().getBean(
/* 1476:1389 */         "configdata");
/* 1477:     */       
/* 1478:1391 */       MachineInfo machine = (MachineInfo)getBeanBag().getBean("machineinfo");
/* 1479:     */       
/* 1480:1393 */       IComUSBHandler handler = (IComUSBHandler)getHandler();
/* 1481:1394 */       if (handler == null) {
/* 1482:1395 */         throw new Exception("handler is null");
/* 1483:     */       }
/* 1484:1397 */       String qpiriStr = handler.excuteCommand("QPIRI", true);
/* 1485:1399 */       if ((!"".equals(qpiriStr)) && (!qpiriStr.equals("(NAK")))
/* 1486:     */       {
/* 1487:1400 */         String[] ratingInfo = qpiriStr.split(" ");
/* 1488:1401 */         String outputVoltage = ratingInfo[2];
/* 1489:1402 */         configdata.setOutputVoltage(outputVoltage.substring(0, 3));
/* 1490:1403 */         String outputFrequency = ratingInfo[3];
/* 1491:1404 */         configdata.setOutputFrequency(outputFrequency.substring(0, 2));
/* 1492:     */         
/* 1493:1406 */         configdata.setBatteryVoltage(VolUtil.parseDouble(ratingInfo[7]));
/* 1494:     */         
/* 1495:1408 */         configdata.setRechargeVoltage(VolUtil.parseDouble(ratingInfo[8]));
/* 1496:     */         
/* 1497:     */ 
/* 1498:1411 */         double batteryUnder = VolUtil.parseDouble(ratingInfo[9]);
/* 1499:1412 */         configdata.setBatteryUnder(batteryUnder);
/* 1500:1414 */         if (configdata.getBatteryVoltage() > 40.0D)
/* 1501:     */         {
/* 1502:1415 */           configdata.setMinBatteryUnder(40.0D);
/* 1503:1416 */           configdata.setMaxBatteryUnder(48.0D);
/* 1504:     */         }
/* 1505:1417 */         else if (configdata.getBatteryVoltage() > 20.0D)
/* 1506:     */         {
/* 1507:1418 */           configdata.setMinBatteryUnder(20.0D);
/* 1508:1419 */           configdata.setMaxBatteryUnder(24.0D);
/* 1509:     */         }
/* 1510:     */         else
/* 1511:     */         {
/* 1512:1421 */           configdata.setMinBatteryUnder(10.0D);
/* 1513:1422 */           configdata.setMaxBatteryUnder(12.0D);
/* 1514:     */         }
/* 1515:1425 */         String batteryTypeStr = ratingInfo[12];
/* 1516:     */         
/* 1517:     */ 
/* 1518:     */ 
/* 1519:1429 */         double maxChargeCurrent = VolUtil.parseDouble(ratingInfo[13]);
/* 1520:1430 */         double currentChargeCurrent = VolUtil.parseDouble(ratingInfo[14]);
/* 1521:1431 */         configdata.setMaxChargeCurrent(currentChargeCurrent);
/* 1522:1432 */         configdata.setMinMaxChargeCurrent(10.0D);
/* 1523:1434 */         if (machine.isChargeCurrentComBox()) {
/* 1524:1435 */           configdata.setMaxacchargingcurrent(maxChargeCurrent);
/* 1525:     */         } else {
/* 1526:1437 */           configdata.setMaxMaxChargeCurrent(maxChargeCurrent);
/* 1527:     */         }
/* 1528:1441 */         double maxChargeVoltage = VolUtil.parseDouble(ratingInfo[10]);
/* 1529:1442 */         configdata.setMaxChargeVoltage(maxChargeVoltage);
/* 1530:1444 */         if (configdata.getBatteryVoltage() > 40.0D)
/* 1531:     */         {
/* 1532:1445 */           configdata.setMinMaxChargeVoltage(48.0D);
/* 1533:1446 */           configdata.setMaxMaxChargeVoltage(58.399999999999999D);
/* 1534:     */         }
/* 1535:1447 */         else if (configdata.getBatteryVoltage() > 20.0D)
/* 1536:     */         {
/* 1537:1448 */           configdata.setMinMaxChargeVoltage(24.0D);
/* 1538:1449 */           configdata.setMaxMaxChargeVoltage(29.210000000000001D);
/* 1539:     */         }
/* 1540:     */         else
/* 1541:     */         {
/* 1542:1451 */           configdata.setMinMaxChargeVoltage(12.0D);
/* 1543:1452 */           configdata.setMaxMaxChargeVoltage(14.6D);
/* 1544:     */         }
/* 1545:1464 */         double floatChargVoltage = VolUtil.parseDouble(ratingInfo[11]);
/* 1546:1465 */         configdata.setFloatingChargeVoltage(floatChargVoltage);
/* 1547:1467 */         if (configdata.getBatteryVoltage() > 40.0D)
/* 1548:     */         {
/* 1549:1468 */           configdata.setMinFloatingChargeVoltage(48.0D);
/* 1550:1469 */           configdata.setMaxFloatingChargeVoltage(58.399999999999999D);
/* 1551:     */         }
/* 1552:1470 */         else if (configdata.getBatteryVoltage() > 20.0D)
/* 1553:     */         {
/* 1554:1471 */           configdata.setMinFloatingChargeVoltage(24.0D);
/* 1555:1472 */           configdata.setMaxFloatingChargeVoltage(29.210000000000001D);
/* 1556:     */         }
/* 1557:     */         else
/* 1558:     */         {
/* 1559:1474 */           configdata.setMinFloatingChargeVoltage(12.0D);
/* 1560:1475 */           configdata.setMaxFloatingChargeVoltage(14.6D);
/* 1561:     */         }
/* 1562:1478 */         String inputVoltageRangeStr = ratingInfo[15];
/* 1563:1479 */         String outputSourceStr = ratingInfo[16];
/* 1564:1480 */         String chargeSourceStr = ratingInfo[17];
/* 1565:1481 */         String batteryType = "AGM";
/* 1566:1482 */         if (batteryTypeStr.equals("0")) {
/* 1567:1483 */           batteryType = "AGM";
/* 1568:1484 */         } else if (batteryTypeStr.equals("1")) {
/* 1569:1485 */           batteryType = "Flooded";
/* 1570:1486 */         } else if (batteryTypeStr.equals("2")) {
/* 1571:1487 */           batteryType = "User";
/* 1572:     */         }
/* 1573:1489 */         configdata.setBatteryType(batteryType);
/* 1574:1490 */         String inputVoltageRange = "Appliance";
/* 1575:1491 */         if (inputVoltageRangeStr.equals("0")) {
/* 1576:1492 */           inputVoltageRange = "Appliance";
/* 1577:1494 */         } else if (inputVoltageRangeStr.equals("1")) {
/* 1578:1495 */           inputVoltageRange = "UPS";
/* 1579:     */         }
/* 1580:1505 */         if (ratingInfo.length > 21) {
/* 1581:     */           try
/* 1582:     */           {
/* 1583:1507 */             int outputmode = Integer.parseInt(ratingInfo[21]);
/* 1584:1508 */             this._protocol.setOutputMode(outputmode);
/* 1585:1509 */             if (outputmode != 0)
/* 1586:     */             {
/* 1587:1510 */               this._paralleltype = 1;
/* 1588:1511 */               this._parallelnum = VolUtil.parseInt(ratingInfo[18]);
/* 1589:     */             }
/* 1590:     */             else
/* 1591:     */             {
/* 1592:1513 */               this._paralleltype = 0;
/* 1593:     */             }
/* 1594:     */           }
/* 1595:     */           catch (Exception localException1) {}
/* 1596:     */         }
/* 1597:1519 */         if (ratingInfo.length > 22) {
/* 1598:1521 */           if (VolUtil.round(VolUtil.parseDouble(ratingInfo[22]), 0) == 0.0D) {
/* 1599:1523 */             configdata.setReDischargeVoltage("FULL");
/* 1600:     */           } else {
/* 1601:1527 */             configdata.setReDischargeVoltage(ratingInfo[22]);
/* 1602:     */           }
/* 1603:     */         }
/* 1604:1531 */         configdata.setAcInputRange(inputVoltageRange);
/* 1605:1532 */         String outputSource = "Utility";
/* 1606:1533 */         if (outputSourceStr.equals("0")) {
/* 1607:1534 */           outputSource = "Utility";
/* 1608:1535 */         } else if (outputSourceStr.equals("1")) {
/* 1609:1536 */           outputSource = "Solar";
/* 1610:1537 */         } else if (outputSourceStr.equals("2")) {
/* 1611:1539 */           outputSource = "SBU";
/* 1612:     */         }
/* 1613:1541 */         configdata.setOutputSource(outputSource);
/* 1614:1542 */         String chargeSource = "Utility";
/* 1615:1543 */         if (chargeSourceStr.equals("0")) {
/* 1616:1544 */           chargeSource = "Utility";
/* 1617:1545 */         } else if (chargeSourceStr.equals("1")) {
/* 1618:1546 */           chargeSource = "Solar first";
/* 1619:1547 */         } else if (chargeSourceStr.equals("2")) {
/* 1620:1548 */           chargeSource = "Utility and Solar";
/* 1621:1549 */         } else if (chargeSourceStr.equals("3")) {
/* 1622:1550 */           chargeSource = "Solar only";
/* 1623:     */         }
/* 1624:1552 */         configdata.setChargerSource(chargeSource);
/* 1625:     */         
/* 1626:     */ 
/* 1627:1555 */         qpiriStr = handler.excuteCommand("QMCHGCR", true);
/* 1628:1556 */         if ((!"".equals(qpiriStr)) && (!qpiriStr.equals("(NAK")))
/* 1629:     */         {
/* 1630:1557 */           ratingInfo = qpiriStr.substring(1).split(" ");
/* 1631:1558 */           if (machine.isChargeCurrentComBox())
/* 1632:     */           {
/* 1633:1559 */             for (int index = 0; index < ratingInfo.length; index++) {
/* 1634:     */               try
/* 1635:     */               {
/* 1636:1561 */                 ratingInfo[index] = String.format("%d", new Object[] { Integer.valueOf(Integer.parseInt(ratingInfo[index])) });
/* 1637:     */               }
/* 1638:     */               catch (Exception localException2) {}
/* 1639:     */             }
/* 1640:1566 */             configdata.setChargingCurrentComBox(ratingInfo);
/* 1641:     */           }
/* 1642:     */           else
/* 1643:     */           {
/* 1644:1570 */             configdata.setMinMaxChargeCurrent(VolUtil.parseDouble(ratingInfo[0].substring(1)));
/* 1645:1571 */             configdata.setMaxMaxChargeCurrent(VolUtil.parseDouble(ratingInfo[1]));
/* 1646:     */           }
/* 1647:     */         }
/* 1648:1574 */         String qpacchargstr = handler.excuteCommand("QMUCHGCR", true);
/* 1649:1575 */         if ((!"".equals(qpacchargstr)) && (!qpacchargstr.equals("(NAK")))
/* 1650:     */         {
/* 1651:1576 */           ratingInfo = qpacchargstr.substring(1).split(" ");
/* 1652:1577 */           for (int index = 0; index < ratingInfo.length; index++) {
/* 1653:     */             try
/* 1654:     */             {
/* 1655:1579 */               ratingInfo[index] = String.format("%d", new Object[] { Integer.valueOf(Integer.parseInt(ratingInfo[index])) });
/* 1656:     */             }
/* 1657:     */             catch (Exception localException3) {}
/* 1658:     */           }
/* 1659:1584 */           configdata.setAcChargingCurrentComBox(ratingInfo);
/* 1660:     */         }
/* 1661:     */       }
/* 1662:     */     }
/* 1663:     */     catch (Exception e)
/* 1664:     */     {
/* 1665:1588 */       result = false;
/* 1666:1589 */       e.printStackTrace();
/* 1667:     */     }
/* 1668:1591 */     return result;
/* 1669:     */   }
/* 1670:     */   
/* 1671:     */   public void querySelfTestResult() {}
/* 1672:     */   
/* 1673:     */   public boolean queryDefaultData()
/* 1674:     */   {
/* 1675:1600 */     DefaultData defaultData = (DefaultData)getBeanBag().getBean(
/* 1676:1601 */       "defaultdata");
/* 1677:     */     
/* 1678:1603 */     MachineInfo machineInfo = (MachineInfo)getBeanBag().getBean("machineinfo");
/* 1679:     */     
/* 1680:1605 */     IComUSBHandler handler = (IComUSBHandler)getHandler();
/* 1681:1606 */     if (handler == null) {
/* 1682:1607 */       return false;
/* 1683:     */     }
/* 1684:1609 */     boolean result = true;
/* 1685:     */     try
/* 1686:     */     {
/* 1687:1611 */       String qdiStr = handler.excuteCommand("QDI", true);
/* 1688:1612 */       if ((!"".equals(qdiStr)) && (!qdiStr.equals("(NAK")))
/* 1689:     */       {
/* 1690:1613 */         System.out.println("qdiStr:" + qdiStr);
/* 1691:1614 */         String[] qdiArr = qdiStr.split(" ");
/* 1692:1615 */         double acOutputVoltage = VolUtil.parseDouble(qdiArr[0]
/* 1693:1616 */           .substring(1));
/* 1694:1617 */         double acOutputFrequency = VolUtil.parseDouble(qdiArr[1]);
/* 1695:     */         
/* 1696:     */ 
/* 1697:1620 */         defaultData.setMaxACChargingCurrent(VolUtil.parseInt(qdiArr[2]));
/* 1698:     */         
/* 1699:1622 */         defaultData.setBatteryCutoffVoltage(VolUtil.parseDouble(qdiArr[3]));
/* 1700:     */         
/* 1701:1624 */         defaultData.setFloatChargingVoltage(VolUtil.parseDouble(qdiArr[4]));
/* 1702:     */         
/* 1703:     */ 
/* 1704:     */ 
/* 1705:1628 */         double maxChargingVoltage = VolUtil.parseDouble(qdiArr[5]);
/* 1706:     */         
/* 1707:     */ 
/* 1708:1631 */         defaultData.setReChangingVoltage(VolUtil.parseDouble(qdiArr[6]));
/* 1709:     */         
/* 1710:1633 */         int maxChargingCurrent = VolUtil.parseInt(qdiArr[7]);
/* 1711:1634 */         int acInputVoltage = VolUtil.parseInt(qdiArr[8]);
/* 1712:1635 */         int outputSourcePriority = VolUtil.parseInt(qdiArr[9]);
/* 1713:1636 */         int chargerSourcePriority = VolUtil.parseInt(qdiArr[10]);
/* 1714:1637 */         int batteryType = VolUtil.parseInt(qdiArr[11]);
/* 1715:1638 */         int capableA = VolUtil.parseInt(qdiArr[12]);
/* 1716:1639 */         int capableJ = VolUtil.parseInt(qdiArr[13]);
/* 1717:1640 */         int capableU = VolUtil.parseInt(qdiArr[14]);
/* 1718:1641 */         int capableV = VolUtil.parseInt(qdiArr[15]);
/* 1719:1642 */         int capableX = VolUtil.parseInt(qdiArr[16]);
/* 1720:1643 */         int capableY = VolUtil.parseInt(qdiArr[17]);
/* 1721:1644 */         int capableZ = VolUtil.parseInt(qdiArr[18]);
/* 1722:1645 */         defaultData.setAcOutputVoltage(acOutputVoltage);
/* 1723:1646 */         defaultData.setAcOutputFrequency(acOutputFrequency);
/* 1724:1647 */         defaultData.setMaxChargingVoltage(maxChargingVoltage);
/* 1725:1648 */         defaultData.setMaxChargingCurrent(maxChargingCurrent);
/* 1726:1649 */         if (acInputVoltage == 0) {
/* 1727:1650 */           defaultData.setAcInputVoltage("Appliance");
/* 1728:     */         } else {
/* 1729:1652 */           defaultData.setAcInputVoltage("UPS");
/* 1730:     */         }
/* 1731:1654 */         if (outputSourcePriority == 0) {
/* 1732:1655 */           defaultData.setOutputSourcePriority("Utility");
/* 1733:1656 */         } else if (outputSourcePriority == 1) {
/* 1734:1657 */           defaultData.setOutputSourcePriority("Solar");
/* 1735:1658 */         } else if (outputSourcePriority == 2) {
/* 1736:1660 */           defaultData.setOutputSourcePriority("SBU");
/* 1737:     */         }
/* 1738:1662 */         if (chargerSourcePriority == 0) {
/* 1739:1663 */           defaultData.setChargerSourcePriority("Utility");
/* 1740:1664 */         } else if (chargerSourcePriority == 1) {
/* 1741:1665 */           defaultData.setChargerSourcePriority("Solar");
/* 1742:1666 */         } else if (chargerSourcePriority == 2) {
/* 1743:1668 */           defaultData.setChargerSourcePriority("Utility and Solar");
/* 1744:1669 */         } else if (chargerSourcePriority == 3) {
/* 1745:1670 */           defaultData.setChargerSourcePriority("Solar only");
/* 1746:     */         }
/* 1747:1672 */         if (batteryType == 0) {
/* 1748:1673 */           defaultData.setBatteryType("AGM");
/* 1749:     */         } else {
/* 1750:1675 */           defaultData.setBatteryType("Flooded");
/* 1751:     */         }
/* 1752:1677 */         if (capableA == 0) {
/* 1753:1678 */           defaultData.setCapableA("Disable");
/* 1754:     */         } else {
/* 1755:1680 */           defaultData.setCapableA("Enable");
/* 1756:     */         }
/* 1757:1682 */         if (capableJ == 0) {
/* 1758:1683 */           defaultData.setCapableJ("Disable");
/* 1759:     */         } else {
/* 1760:1685 */           defaultData.setCapableJ("Enable");
/* 1761:     */         }
/* 1762:1687 */         if (capableU == 0) {
/* 1763:1688 */           defaultData.setCapableU("Disable");
/* 1764:     */         } else {
/* 1765:1690 */           defaultData.setCapableU("Enable");
/* 1766:     */         }
/* 1767:1692 */         if (capableV == 0) {
/* 1768:1693 */           defaultData.setCapableV("Disable");
/* 1769:     */         } else {
/* 1770:1695 */           defaultData.setCapableV("Enable");
/* 1771:     */         }
/* 1772:1697 */         if (capableX == 0) {
/* 1773:1698 */           defaultData.setCapableX("Disable");
/* 1774:     */         } else {
/* 1775:1700 */           defaultData.setCapableX("Enable");
/* 1776:     */         }
/* 1777:1702 */         if (capableY == 0) {
/* 1778:1703 */           defaultData.setCapableY("Disable");
/* 1779:     */         } else {
/* 1780:1705 */           defaultData.setCapableY("Enable");
/* 1781:     */         }
/* 1782:1707 */         if (capableZ == 0) {
/* 1783:1708 */           defaultData.setCapableZ("Disable");
/* 1784:     */         } else {
/* 1785:1710 */           defaultData.setCapableZ("Enable");
/* 1786:     */         }
/* 1787:1713 */         if (qdiArr.length >= 20)
/* 1788:     */         {
/* 1789:1714 */           int capableB = VolUtil.parseInt(qdiArr[19]);
/* 1790:1715 */           if (capableB == 0) {
/* 1791:1716 */             defaultData.setCapableB("Disable");
/* 1792:     */           } else {
/* 1793:1718 */             defaultData.setCapableB("Enable");
/* 1794:     */           }
/* 1795:     */         }
/* 1796:1721 */         if (qdiArr.length >= 21)
/* 1797:     */         {
/* 1798:1722 */           int capableK = VolUtil.parseInt(qdiArr[20]);
/* 1799:1723 */           if (capableK == 0) {
/* 1800:1724 */             defaultData.setCapableK("Disable");
/* 1801:     */           } else {
/* 1802:1726 */             defaultData.setCapableK("Enable");
/* 1803:     */           }
/* 1804:     */         }
/* 1805:1730 */         if (qdiArr.length >= 22)
/* 1806:     */         {
/* 1807:1731 */           String[] modes = {
/* 1808:1732 */             "Single", "Parallel", 
/* 1809:1733 */             "Phase R of 3 phase output", "Phase S of 3 phase output", "Phase T of 3 phase output" };
/* 1810:     */           
/* 1811:1735 */           int outputmode = VolUtil.parseInt(qdiArr[21]);
/* 1812:1736 */           if (((outputmode >= 0 ? 1 : 0) & (outputmode <= 4 ? 1 : 0)) != 0) {
/* 1813:1737 */             defaultData.setOutputMode(modes[outputmode]);
/* 1814:     */           }
/* 1815:     */         }
/* 1816:1741 */         if (qdiArr.length >= 23) {
/* 1817:1742 */           defaultData.setReDischargeVoltage(VolUtil.parseDouble(qdiArr[22]));
/* 1818:     */         }
/* 1819:     */       }
/* 1820:     */     }
/* 1821:     */     catch (Exception e)
/* 1822:     */     {
/* 1823:1746 */       result = false;
/* 1824:1747 */       e.printStackTrace();
/* 1825:     */     }
/* 1826:1749 */     return result;
/* 1827:     */   }
/* 1828:     */   
/* 1829:     */   public boolean supportSelfTest()
/* 1830:     */   {
/* 1831:1754 */     return false;
/* 1832:     */   }
/* 1833:     */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.comusbprocessor.P30ComUSBProcessor
 * JD-Core Version:    0.7.0.1
 */