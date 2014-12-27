/*    1:     */ package cn.com.voltronic.solar.data.bean;
/*    2:     */ 
/*    3:     */ import java.util.ArrayList;
/*    4:     */ import java.util.Date;
/*    5:     */ import java.util.List;
/*    6:     */ 
/*    7:     */ public class WorkInfo
/*    8:     */ {
/*    9:     */   private int workid;
/*   10:  20 */   private String prodid = "";
/*   11:  22 */   private String serialno = "";
/*   12:  25 */   private String workMode = "---";
/*   13:  28 */   private double gridVoltageR = 0.0D;
/*   14:  30 */   private double gridPowerR = 0.0D;
/*   15:  32 */   private double gridFrequency = 0.0D;
/*   16:  34 */   private double gridCurrentR = 0.0D;
/*   17:  37 */   private double acOutputVoltageR = 0.0D;
/*   18:  39 */   private double acOutputPowerR = 0.0D;
/*   19:  41 */   private double acOutputFrequency = 0.0D;
/*   20:  43 */   private double acOutputCurrentR = 0.0D;
/*   21:  45 */   private int outputLoadPercent = 0;
/*   22:  47 */   private double pBUSVoltage = 0.0D;
/*   23:  49 */   private double sBUSVoltage = 0.0D;
/*   24:  51 */   private double acOutputApparentPower = 0.0D;
/*   25:  53 */   private double acOutputActivePower = 0.0D;
/*   26:  55 */   private double acTtlOutputApparentPower = 0.0D;
/*   27:  57 */   private double acTtlOutputActivePower = 0.0D;
/*   28:  59 */   private double acTtlOutputPercent = 0.0D;
/*   29:  65 */   private double pBatteryVoltage = 0.0D;
/*   30:  67 */   private double nBatteryVoltage = 0.0D;
/*   31:  69 */   private int batteryCapacity = 0;
/*   32:  71 */   private double chargingCurrent = 0.0D;
/*   33:  73 */   private double ttlChargingCurrent = 0.0D;
/*   34:  76 */   private int batteryPieceNumber = 0;
/*   35:  78 */   private int batteryTotalCapacity = 0;
/*   36:  80 */   private int batteryRemainTime = 0;
/*   37:  85 */   private int pvInputPower1 = 0;
/*   38:  87 */   private int pvInputPower2 = 0;
/*   39:  89 */   private int pvInputPower3 = 0;
/*   40:  91 */   private double pvInputVoltage1 = 0.0D;
/*   41:  93 */   private double pvInputVoltage2 = 0.0D;
/*   42:  95 */   private double pvInputVoltage3 = 0.0D;
/*   43:  97 */   private double maxTemperature = 0.0D;
/*   44:  99 */   private double rGridVoltage = 0.0D;
/*   45: 101 */   private double sGridVoltage = 0.0D;
/*   46: 103 */   private double tGridVoltage = 0.0D;
/*   47: 105 */   private double rsGridVoltage = 0.0D;
/*   48: 107 */   private double rtGridVoltage = 0.0D;
/*   49: 109 */   private double stGridVoltage = 0.0D;
/*   50: 111 */   private double rGridCurrent = 0.0D;
/*   51: 113 */   private double sGridCurrent = 0.0D;
/*   52: 115 */   private double tGridCurrent = 0.0D;
/*   53: 117 */   private int rPhasePower = 0;
/*   54: 119 */   private int sPhasePower = 0;
/*   55: 121 */   private int tPhasePower = 0;
/*   56: 123 */   private int wholePower = 0;
/*   57: 125 */   private double rPhaseACOutputVoltage = 0.0D;
/*   58: 127 */   private double sPhaseACOutputVoltage = 0.0D;
/*   59: 129 */   private double tPhaseACOutputVoltage = 0.0D;
/*   60: 131 */   private double rsPhaseACOutputVoltage = 0.0D;
/*   61: 133 */   private double rtPhaseACOutputVoltage = 0.0D;
/*   62: 135 */   private double stPhaseACOutputVoltage = 0.0D;
/*   63: 137 */   private double rACOutputCurrent = 0.0D;
/*   64: 139 */   private double sACOutputCurrent = 0.0D;
/*   65: 141 */   private double tACOutputCurrent = 0.0D;
/*   66: 143 */   private int rPhaseACOutputLoad = 0;
/*   67: 145 */   private int sPhaseACOutputLoad = 0;
/*   68: 147 */   private int tPhaseACOutputLoad = 0;
/*   69: 149 */   private int wholeACOutputLoad = 0;
/*   70:     */   private Date currentTime;
/*   71: 153 */   private String faultInfo = "";
/*   72: 158 */   private boolean lineLoss = false;
/*   73: 163 */   private boolean noBattery = false;
/*   74: 168 */   private boolean pvLoss = false;
/*   75: 170 */   private double lowestLimtInputV = 0.0D;
/*   76: 172 */   private boolean selfTesting = false;
/*   77: 177 */   private String morphological = "";
/*   78: 182 */   private boolean epoActive = false;
/*   79: 187 */   private boolean hasLoad = false;
/*   80: 192 */   private boolean isOverLoad = false;
/*   81: 197 */   private String batteryStatus = "";
/*   82: 202 */   private String invDirection = "";
/*   83: 207 */   private String lineDirection = "";
/*   84: 212 */   private boolean isFault = false;
/*   85:     */   private boolean isChargeOn;
/*   86:     */   private boolean isSCCchargeOn;
/*   87:     */   private boolean isACchargeOn;
/*   88: 229 */   private String customerV = "0";
/*   89:     */   private String chargeSource;
/*   90:     */   private String loadSource;
/*   91:     */   private int pvInputCurrent;
/*   92:     */   private int batDisCurrent;
/*   93:     */   
/*   94:     */   public boolean isChargeOn()
/*   95:     */   {
/*   96: 244 */     return this.isChargeOn;
/*   97:     */   }
/*   98:     */   
/*   99:     */   public void setChargeOn(boolean isChargeOn)
/*  100:     */   {
/*  101: 248 */     this.isChargeOn = isChargeOn;
/*  102:     */   }
/*  103:     */   
/*  104:     */   public boolean isSCCchargeOn()
/*  105:     */   {
/*  106: 252 */     return this.isSCCchargeOn;
/*  107:     */   }
/*  108:     */   
/*  109:     */   public void setSCCchargeOn(boolean isSCCchargeOn)
/*  110:     */   {
/*  111: 256 */     this.isSCCchargeOn = isSCCchargeOn;
/*  112:     */   }
/*  113:     */   
/*  114:     */   public boolean isACchargeOn()
/*  115:     */   {
/*  116: 260 */     return this.isACchargeOn;
/*  117:     */   }
/*  118:     */   
/*  119:     */   public void setACchargeOn(boolean isACchargeOn)
/*  120:     */   {
/*  121: 264 */     this.isACchargeOn = isACchargeOn;
/*  122:     */   }
/*  123:     */   
/*  124:     */   public boolean isNoBattery()
/*  125:     */   {
/*  126: 268 */     return this.noBattery;
/*  127:     */   }
/*  128:     */   
/*  129:     */   public void setNoBattery(boolean noBattery)
/*  130:     */   {
/*  131: 272 */     this.noBattery = noBattery;
/*  132:     */   }
/*  133:     */   
/*  134:     */   public boolean isPvLoss()
/*  135:     */   {
/*  136: 276 */     return this.pvLoss;
/*  137:     */   }
/*  138:     */   
/*  139:     */   public void setPvLoss(boolean pvLoss)
/*  140:     */   {
/*  141: 280 */     this.pvLoss = pvLoss;
/*  142:     */   }
/*  143:     */   
/*  144:     */   public boolean isHasLoad()
/*  145:     */   {
/*  146: 284 */     return this.hasLoad;
/*  147:     */   }
/*  148:     */   
/*  149:     */   public void setHasLoad(boolean hasLoad)
/*  150:     */   {
/*  151: 288 */     this.hasLoad = hasLoad;
/*  152:     */   }
/*  153:     */   
/*  154:     */   public boolean isOverLoad()
/*  155:     */   {
/*  156: 292 */     return this.isOverLoad;
/*  157:     */   }
/*  158:     */   
/*  159:     */   public void setOverLoad(boolean isOverLoad)
/*  160:     */   {
/*  161: 296 */     this.isOverLoad = isOverLoad;
/*  162:     */   }
/*  163:     */   
/*  164:     */   public String getBatteryStatus()
/*  165:     */   {
/*  166: 300 */     return this.batteryStatus;
/*  167:     */   }
/*  168:     */   
/*  169:     */   public void setBatteryStatus(String batteryStatus)
/*  170:     */   {
/*  171: 304 */     this.batteryStatus = batteryStatus;
/*  172:     */   }
/*  173:     */   
/*  174:     */   public String getInvDirection()
/*  175:     */   {
/*  176: 308 */     return this.invDirection;
/*  177:     */   }
/*  178:     */   
/*  179:     */   public void setInvDirection(String invDirection)
/*  180:     */   {
/*  181: 312 */     this.invDirection = invDirection;
/*  182:     */   }
/*  183:     */   
/*  184:     */   public String getLineDirection()
/*  185:     */   {
/*  186: 316 */     return this.lineDirection;
/*  187:     */   }
/*  188:     */   
/*  189:     */   public void setLineDirection(String lineDirection)
/*  190:     */   {
/*  191: 320 */     this.lineDirection = lineDirection;
/*  192:     */   }
/*  193:     */   
/*  194:     */   public boolean isEpoActive()
/*  195:     */   {
/*  196: 324 */     return this.epoActive;
/*  197:     */   }
/*  198:     */   
/*  199:     */   public void setEpoActive(boolean epoActive)
/*  200:     */   {
/*  201: 328 */     this.epoActive = epoActive;
/*  202:     */   }
/*  203:     */   
/*  204: 331 */   private List<String> warnings = new ArrayList();
/*  205:     */   
/*  206:     */   public double getRGridVoltage()
/*  207:     */   {
/*  208: 334 */     return this.rGridVoltage;
/*  209:     */   }
/*  210:     */   
/*  211:     */   public void setRGridVoltage(double gridVoltage)
/*  212:     */   {
/*  213: 338 */     this.rGridVoltage = gridVoltage;
/*  214:     */   }
/*  215:     */   
/*  216:     */   public double getRsGridVoltage()
/*  217:     */   {
/*  218: 342 */     return this.rsGridVoltage;
/*  219:     */   }
/*  220:     */   
/*  221:     */   public void setRsGridVoltage(double rsGridVoltage)
/*  222:     */   {
/*  223: 346 */     this.rsGridVoltage = rsGridVoltage;
/*  224:     */   }
/*  225:     */   
/*  226:     */   public double getRtGridVoltage()
/*  227:     */   {
/*  228: 350 */     return this.rtGridVoltage;
/*  229:     */   }
/*  230:     */   
/*  231:     */   public void setRtGridVoltage(double rtGridVoltage)
/*  232:     */   {
/*  233: 354 */     this.rtGridVoltage = rtGridVoltage;
/*  234:     */   }
/*  235:     */   
/*  236:     */   public double getStGridVoltage()
/*  237:     */   {
/*  238: 358 */     return this.stGridVoltage;
/*  239:     */   }
/*  240:     */   
/*  241:     */   public void setStGridVoltage(double stGridVoltage)
/*  242:     */   {
/*  243: 362 */     this.stGridVoltage = stGridVoltage;
/*  244:     */   }
/*  245:     */   
/*  246:     */   public double getRGridCurrent()
/*  247:     */   {
/*  248: 366 */     return this.rGridCurrent;
/*  249:     */   }
/*  250:     */   
/*  251:     */   public void setRGridCurrent(double gridCurrent)
/*  252:     */   {
/*  253: 370 */     this.rGridCurrent = gridCurrent;
/*  254:     */   }
/*  255:     */   
/*  256:     */   public int getRPhasePower()
/*  257:     */   {
/*  258: 374 */     return this.rPhasePower;
/*  259:     */   }
/*  260:     */   
/*  261:     */   public void setRPhasePower(int phasePower)
/*  262:     */   {
/*  263: 378 */     this.rPhasePower = phasePower;
/*  264:     */   }
/*  265:     */   
/*  266:     */   public double getRPhaseACOutputVoltage()
/*  267:     */   {
/*  268: 382 */     return this.rPhaseACOutputVoltage;
/*  269:     */   }
/*  270:     */   
/*  271:     */   public void setRPhaseACOutputVoltage(double phaseACOutputVoltage)
/*  272:     */   {
/*  273: 386 */     this.rPhaseACOutputVoltage = phaseACOutputVoltage;
/*  274:     */   }
/*  275:     */   
/*  276:     */   public double getRsPhaseACOutputVoltage()
/*  277:     */   {
/*  278: 390 */     return this.rsPhaseACOutputVoltage;
/*  279:     */   }
/*  280:     */   
/*  281:     */   public void setRsPhaseACOutputVoltage(double rsPhaseACOutputVoltage)
/*  282:     */   {
/*  283: 394 */     this.rsPhaseACOutputVoltage = rsPhaseACOutputVoltage;
/*  284:     */   }
/*  285:     */   
/*  286:     */   public double getRtPhaseACOutputVoltage()
/*  287:     */   {
/*  288: 398 */     return this.rtPhaseACOutputVoltage;
/*  289:     */   }
/*  290:     */   
/*  291:     */   public void setRtPhaseACOutputVoltage(double rtPhaseACOutputVoltage)
/*  292:     */   {
/*  293: 402 */     this.rtPhaseACOutputVoltage = rtPhaseACOutputVoltage;
/*  294:     */   }
/*  295:     */   
/*  296:     */   public double getStPhaseACOutputVoltage()
/*  297:     */   {
/*  298: 406 */     return this.stPhaseACOutputVoltage;
/*  299:     */   }
/*  300:     */   
/*  301:     */   public void setStPhaseACOutputVoltage(double stPhaseACOutputVoltage)
/*  302:     */   {
/*  303: 410 */     this.stPhaseACOutputVoltage = stPhaseACOutputVoltage;
/*  304:     */   }
/*  305:     */   
/*  306:     */   public double getRACOutputCurrent()
/*  307:     */   {
/*  308: 414 */     return this.rACOutputCurrent;
/*  309:     */   }
/*  310:     */   
/*  311:     */   public void setRACOutputCurrent(double outputCurrent)
/*  312:     */   {
/*  313: 418 */     this.rACOutputCurrent = outputCurrent;
/*  314:     */   }
/*  315:     */   
/*  316:     */   public int getRPhaseACOutputLoad()
/*  317:     */   {
/*  318: 422 */     return this.rPhaseACOutputLoad;
/*  319:     */   }
/*  320:     */   
/*  321:     */   public void setRPhaseACOutputLoad(int phaseACOutputLoad)
/*  322:     */   {
/*  323: 426 */     this.rPhaseACOutputLoad = phaseACOutputLoad;
/*  324:     */   }
/*  325:     */   
/*  326:     */   public boolean isLineLoss()
/*  327:     */   {
/*  328: 430 */     return this.lineLoss;
/*  329:     */   }
/*  330:     */   
/*  331:     */   public void setLineLoss(boolean lineLoss)
/*  332:     */   {
/*  333: 434 */     this.lineLoss = lineLoss;
/*  334:     */   }
/*  335:     */   
/*  336:     */   public int getWorkid()
/*  337:     */   {
/*  338: 438 */     return this.workid;
/*  339:     */   }
/*  340:     */   
/*  341:     */   public void setWorkid(int workid)
/*  342:     */   {
/*  343: 442 */     this.workid = workid;
/*  344:     */   }
/*  345:     */   
/*  346:     */   public String getMorphological()
/*  347:     */   {
/*  348: 446 */     return this.morphological;
/*  349:     */   }
/*  350:     */   
/*  351:     */   public void setMorphological(String morphological)
/*  352:     */   {
/*  353: 450 */     this.morphological = morphological;
/*  354:     */   }
/*  355:     */   
/*  356:     */   public String getFaultInfo()
/*  357:     */   {
/*  358: 454 */     return this.faultInfo;
/*  359:     */   }
/*  360:     */   
/*  361:     */   public void setFaultInfo(String faultInfo)
/*  362:     */   {
/*  363: 458 */     this.faultInfo = faultInfo;
/*  364:     */   }
/*  365:     */   
/*  366:     */   public String getProdid()
/*  367:     */   {
/*  368: 462 */     return this.prodid;
/*  369:     */   }
/*  370:     */   
/*  371:     */   public void setProdid(String prodid)
/*  372:     */   {
/*  373: 466 */     this.prodid = prodid;
/*  374:     */   }
/*  375:     */   
/*  376:     */   public String getSerialno()
/*  377:     */   {
/*  378: 470 */     return this.serialno;
/*  379:     */   }
/*  380:     */   
/*  381:     */   public void setSerialno(String serialno)
/*  382:     */   {
/*  383: 474 */     this.serialno = serialno;
/*  384:     */   }
/*  385:     */   
/*  386:     */   public Date getCurrentTime()
/*  387:     */   {
/*  388: 478 */     return this.currentTime;
/*  389:     */   }
/*  390:     */   
/*  391:     */   public void setCurrentTime(Date currentTime)
/*  392:     */   {
/*  393: 482 */     this.currentTime = currentTime;
/*  394:     */   }
/*  395:     */   
/*  396:     */   public double getPBUSVoltage()
/*  397:     */   {
/*  398: 486 */     return this.pBUSVoltage;
/*  399:     */   }
/*  400:     */   
/*  401:     */   public void setPBUSVoltage(double voltage)
/*  402:     */   {
/*  403: 490 */     this.pBUSVoltage = (voltage > 5.0D ? voltage : 0.0D);
/*  404:     */   }
/*  405:     */   
/*  406:     */   public double getSBUSVoltage()
/*  407:     */   {
/*  408: 494 */     return this.sBUSVoltage;
/*  409:     */   }
/*  410:     */   
/*  411:     */   public void setSBUSVoltage(double voltage)
/*  412:     */   {
/*  413: 498 */     this.sBUSVoltage = (voltage > 5.0D ? voltage : 0.0D);
/*  414:     */   }
/*  415:     */   
/*  416:     */   public String getWorkMode()
/*  417:     */   {
/*  418: 502 */     return this.workMode;
/*  419:     */   }
/*  420:     */   
/*  421:     */   public void setWorkMode(String workMode)
/*  422:     */   {
/*  423: 506 */     this.workMode = workMode;
/*  424:     */   }
/*  425:     */   
/*  426:     */   public double getGridVoltageR()
/*  427:     */   {
/*  428: 510 */     return this.gridVoltageR;
/*  429:     */   }
/*  430:     */   
/*  431:     */   public void setGridVoltageR(double gridVoltageR)
/*  432:     */   {
/*  433: 514 */     this.gridVoltageR = (gridVoltageR > 5.0D ? gridVoltageR : 
/*  434: 515 */       0.0D);
/*  435:     */   }
/*  436:     */   
/*  437:     */   public double getGridPowerR()
/*  438:     */   {
/*  439: 519 */     return this.gridPowerR;
/*  440:     */   }
/*  441:     */   
/*  442:     */   public void setGridPowerR(double gridPowerR)
/*  443:     */   {
/*  444: 523 */     this.gridPowerR = gridPowerR;
/*  445:     */   }
/*  446:     */   
/*  447:     */   public double getGridFrequency()
/*  448:     */   {
/*  449: 527 */     return this.gridFrequency;
/*  450:     */   }
/*  451:     */   
/*  452:     */   public void setGridFrequency(double gridFrequency)
/*  453:     */   {
/*  454: 531 */     this.gridFrequency = gridFrequency;
/*  455:     */   }
/*  456:     */   
/*  457:     */   public double getGridCurrentR()
/*  458:     */   {
/*  459: 535 */     return this.gridCurrentR;
/*  460:     */   }
/*  461:     */   
/*  462:     */   public void setGridCurrentR(double gridCurrentR)
/*  463:     */   {
/*  464: 539 */     this.gridCurrentR = gridCurrentR;
/*  465:     */   }
/*  466:     */   
/*  467:     */   public double getAcOutputVoltageR()
/*  468:     */   {
/*  469: 543 */     return this.acOutputVoltageR;
/*  470:     */   }
/*  471:     */   
/*  472:     */   public void setAcOutputVoltageR(double acOutputVoltageR)
/*  473:     */   {
/*  474: 547 */     this.acOutputVoltageR = acOutputVoltageR;
/*  475:     */   }
/*  476:     */   
/*  477:     */   public double getAcOutputPowerR()
/*  478:     */   {
/*  479: 551 */     return this.acOutputPowerR;
/*  480:     */   }
/*  481:     */   
/*  482:     */   public void setAcOutputPowerR(double acOutputPowerR)
/*  483:     */   {
/*  484: 555 */     this.acOutputPowerR = acOutputPowerR;
/*  485:     */   }
/*  486:     */   
/*  487:     */   public double getAcOutputFrequency()
/*  488:     */   {
/*  489: 559 */     return this.acOutputFrequency;
/*  490:     */   }
/*  491:     */   
/*  492:     */   public void setAcOutputFrequency(double acOutputFrequency)
/*  493:     */   {
/*  494: 563 */     this.acOutputFrequency = acOutputFrequency;
/*  495:     */   }
/*  496:     */   
/*  497:     */   public double getAcOutputCurrentR()
/*  498:     */   {
/*  499: 567 */     return this.acOutputCurrentR;
/*  500:     */   }
/*  501:     */   
/*  502:     */   public void setAcOutputCurrentR(double acOutputCurrentR)
/*  503:     */   {
/*  504: 571 */     this.acOutputCurrentR = acOutputCurrentR;
/*  505:     */   }
/*  506:     */   
/*  507:     */   public int getOutputLoadPercent()
/*  508:     */   {
/*  509: 575 */     return this.outputLoadPercent;
/*  510:     */   }
/*  511:     */   
/*  512:     */   public void setOutputLoadPercent(int outputLoadPercent)
/*  513:     */   {
/*  514: 579 */     this.outputLoadPercent = outputLoadPercent;
/*  515:     */   }
/*  516:     */   
/*  517:     */   public double getPBatteryVoltage()
/*  518:     */   {
/*  519: 583 */     return this.pBatteryVoltage;
/*  520:     */   }
/*  521:     */   
/*  522:     */   public void setPBatteryVoltage(double batteryVoltage)
/*  523:     */   {
/*  524: 587 */     this.pBatteryVoltage = (batteryVoltage > 5.0D ? batteryVoltage : 
/*  525: 588 */       0.0D);
/*  526:     */   }
/*  527:     */   
/*  528:     */   public double getNBatteryVoltage()
/*  529:     */   {
/*  530: 592 */     return this.nBatteryVoltage;
/*  531:     */   }
/*  532:     */   
/*  533:     */   public void setNBatteryVoltage(double batteryVoltage)
/*  534:     */   {
/*  535: 596 */     this.nBatteryVoltage = (batteryVoltage > 5.0D ? batteryVoltage : 
/*  536: 597 */       0.0D);
/*  537:     */   }
/*  538:     */   
/*  539:     */   public int getBatteryCapacity()
/*  540:     */   {
/*  541: 601 */     return this.batteryCapacity;
/*  542:     */   }
/*  543:     */   
/*  544:     */   public void setBatteryCapacity(int batteryCapacity)
/*  545:     */   {
/*  546: 605 */     this.batteryCapacity = batteryCapacity;
/*  547:     */   }
/*  548:     */   
/*  549:     */   public double getChargingCurrent()
/*  550:     */   {
/*  551: 609 */     return this.chargingCurrent;
/*  552:     */   }
/*  553:     */   
/*  554:     */   public void setChargingCurrent(double chargingCurrent)
/*  555:     */   {
/*  556: 613 */     this.chargingCurrent = chargingCurrent;
/*  557:     */   }
/*  558:     */   
/*  559:     */   public int getPvInputPower1()
/*  560:     */   {
/*  561: 617 */     return this.pvInputPower1;
/*  562:     */   }
/*  563:     */   
/*  564:     */   public void setPvInputPower1(int pvInputPower1)
/*  565:     */   {
/*  566: 621 */     this.pvInputPower1 = pvInputPower1;
/*  567:     */   }
/*  568:     */   
/*  569:     */   public int getPvInputPower2()
/*  570:     */   {
/*  571: 625 */     return this.pvInputPower2;
/*  572:     */   }
/*  573:     */   
/*  574:     */   public void setPvInputPower2(int pvInputPower2)
/*  575:     */   {
/*  576: 629 */     this.pvInputPower2 = pvInputPower2;
/*  577:     */   }
/*  578:     */   
/*  579:     */   public int getPvInputPower3()
/*  580:     */   {
/*  581: 633 */     return this.pvInputPower3;
/*  582:     */   }
/*  583:     */   
/*  584:     */   public void setPvInputPower3(int pvInputPower3)
/*  585:     */   {
/*  586: 637 */     this.pvInputPower3 = pvInputPower3;
/*  587:     */   }
/*  588:     */   
/*  589:     */   public double getPvInputVoltage1()
/*  590:     */   {
/*  591: 641 */     return this.pvInputVoltage1;
/*  592:     */   }
/*  593:     */   
/*  594:     */   public void setPvInputVoltage1(double pvInputVoltage1)
/*  595:     */   {
/*  596: 645 */     this.pvInputVoltage1 = (pvInputVoltage1 > 5.0D ? pvInputVoltage1 : 
/*  597: 646 */       0.0D);
/*  598:     */   }
/*  599:     */   
/*  600:     */   public double getPvInputVoltage2()
/*  601:     */   {
/*  602: 650 */     return this.pvInputVoltage2;
/*  603:     */   }
/*  604:     */   
/*  605:     */   public void setPvInputVoltage2(double pvInputVoltage2)
/*  606:     */   {
/*  607: 654 */     this.pvInputVoltage2 = (pvInputVoltage2 > 5.0D ? pvInputVoltage2 : 
/*  608: 655 */       0.0D);
/*  609:     */   }
/*  610:     */   
/*  611:     */   public double getPvInputVoltage3()
/*  612:     */   {
/*  613: 659 */     return this.pvInputVoltage3;
/*  614:     */   }
/*  615:     */   
/*  616:     */   public void setPvInputVoltage3(double pvInputVoltage3)
/*  617:     */   {
/*  618: 663 */     this.pvInputVoltage3 = (pvInputVoltage3 > 5.0D ? pvInputVoltage3 : 
/*  619: 664 */       0.0D);
/*  620:     */   }
/*  621:     */   
/*  622:     */   public double getMaxTemperature()
/*  623:     */   {
/*  624: 668 */     return this.maxTemperature;
/*  625:     */   }
/*  626:     */   
/*  627:     */   public void setMaxTemperature(double maxTemperature)
/*  628:     */   {
/*  629: 672 */     this.maxTemperature = maxTemperature;
/*  630:     */   }
/*  631:     */   
/*  632:     */   public double getSGridVoltage()
/*  633:     */   {
/*  634: 676 */     return this.sGridVoltage;
/*  635:     */   }
/*  636:     */   
/*  637:     */   public void setSGridVoltage(double gridVoltage)
/*  638:     */   {
/*  639: 680 */     this.sGridVoltage = (gridVoltage > 5.0D ? gridVoltage : 
/*  640: 681 */       0.0D);
/*  641:     */   }
/*  642:     */   
/*  643:     */   public double getTGridVoltage()
/*  644:     */   {
/*  645: 685 */     return this.tGridVoltage;
/*  646:     */   }
/*  647:     */   
/*  648:     */   public void setTGridVoltage(double gridVoltage)
/*  649:     */   {
/*  650: 689 */     this.tGridVoltage = (gridVoltage > 5.0D ? gridVoltage : 
/*  651: 690 */       0.0D);
/*  652:     */   }
/*  653:     */   
/*  654:     */   public double getSGridCurrent()
/*  655:     */   {
/*  656: 694 */     return this.sGridCurrent;
/*  657:     */   }
/*  658:     */   
/*  659:     */   public void setSGridCurrent(double gridCurrent)
/*  660:     */   {
/*  661: 698 */     this.sGridCurrent = gridCurrent;
/*  662:     */   }
/*  663:     */   
/*  664:     */   public double getTGridCurrent()
/*  665:     */   {
/*  666: 702 */     return this.tGridCurrent;
/*  667:     */   }
/*  668:     */   
/*  669:     */   public void setTGridCurrent(double gridCurrent)
/*  670:     */   {
/*  671: 706 */     this.tGridCurrent = gridCurrent;
/*  672:     */   }
/*  673:     */   
/*  674:     */   public int getSPhasePower()
/*  675:     */   {
/*  676: 710 */     return this.sPhasePower;
/*  677:     */   }
/*  678:     */   
/*  679:     */   public void setSPhasePower(int phasePower)
/*  680:     */   {
/*  681: 714 */     this.sPhasePower = phasePower;
/*  682:     */   }
/*  683:     */   
/*  684:     */   public int getTPhasePower()
/*  685:     */   {
/*  686: 718 */     return this.tPhasePower;
/*  687:     */   }
/*  688:     */   
/*  689:     */   public void setTPhasePower(int phasePower)
/*  690:     */   {
/*  691: 722 */     this.tPhasePower = phasePower;
/*  692:     */   }
/*  693:     */   
/*  694:     */   public int getWholePower()
/*  695:     */   {
/*  696: 726 */     return this.wholePower;
/*  697:     */   }
/*  698:     */   
/*  699:     */   public void setWholePower(int wholePower)
/*  700:     */   {
/*  701: 730 */     this.wholePower = wholePower;
/*  702:     */   }
/*  703:     */   
/*  704:     */   public double getSPhaseACOutputVoltage()
/*  705:     */   {
/*  706: 734 */     return this.sPhaseACOutputVoltage;
/*  707:     */   }
/*  708:     */   
/*  709:     */   public void setSPhaseACOutputVoltage(double phaseACOutputVoltage)
/*  710:     */   {
/*  711: 738 */     this.sPhaseACOutputVoltage = (phaseACOutputVoltage > 5.0D ? phaseACOutputVoltage : 
/*  712: 739 */       0.0D);
/*  713:     */   }
/*  714:     */   
/*  715:     */   public double getTPhaseACOutputVoltage()
/*  716:     */   {
/*  717: 743 */     return this.tPhaseACOutputVoltage;
/*  718:     */   }
/*  719:     */   
/*  720:     */   public void setTPhaseACOutputVoltage(double phaseACOutputVoltage)
/*  721:     */   {
/*  722: 747 */     this.tPhaseACOutputVoltage = (phaseACOutputVoltage > 5.0D ? phaseACOutputVoltage : 
/*  723: 748 */       0.0D);
/*  724:     */   }
/*  725:     */   
/*  726:     */   public double getSACOutputCurrent()
/*  727:     */   {
/*  728: 752 */     return this.sACOutputCurrent;
/*  729:     */   }
/*  730:     */   
/*  731:     */   public void setSACOutputCurrent(double outputCurrent)
/*  732:     */   {
/*  733: 756 */     this.sACOutputCurrent = outputCurrent;
/*  734:     */   }
/*  735:     */   
/*  736:     */   public double getTACOutputCurrent()
/*  737:     */   {
/*  738: 760 */     return this.tACOutputCurrent;
/*  739:     */   }
/*  740:     */   
/*  741:     */   public void setTACOutputCurrent(double outputCurrent)
/*  742:     */   {
/*  743: 764 */     this.tACOutputCurrent = outputCurrent;
/*  744:     */   }
/*  745:     */   
/*  746:     */   public int getSPhaseACOutputLoad()
/*  747:     */   {
/*  748: 768 */     return this.sPhaseACOutputLoad;
/*  749:     */   }
/*  750:     */   
/*  751:     */   public void setSPhaseACOutputLoad(int phaseACOutputLoad)
/*  752:     */   {
/*  753: 772 */     this.sPhaseACOutputLoad = phaseACOutputLoad;
/*  754:     */   }
/*  755:     */   
/*  756:     */   public int getTPhaseACOutputLoad()
/*  757:     */   {
/*  758: 776 */     return this.tPhaseACOutputLoad;
/*  759:     */   }
/*  760:     */   
/*  761:     */   public void setTPhaseACOutputLoad(int phaseACOutputLoad)
/*  762:     */   {
/*  763: 780 */     this.tPhaseACOutputLoad = phaseACOutputLoad;
/*  764:     */   }
/*  765:     */   
/*  766:     */   public int getWholeACOutputLoad()
/*  767:     */   {
/*  768: 784 */     return this.wholeACOutputLoad;
/*  769:     */   }
/*  770:     */   
/*  771:     */   public void setWholeACOutputLoad(int wholeACOutputLoad)
/*  772:     */   {
/*  773: 788 */     this.wholeACOutputLoad = wholeACOutputLoad;
/*  774:     */   }
/*  775:     */   
/*  776:     */   public List<String> getWarnings()
/*  777:     */   {
/*  778: 792 */     return this.warnings;
/*  779:     */   }
/*  780:     */   
/*  781:     */   public void setWarnings(List<String> warnings)
/*  782:     */   {
/*  783: 796 */     this.warnings = warnings;
/*  784:     */   }
/*  785:     */   
/*  786:     */   public int getBatteryPieceNumber()
/*  787:     */   {
/*  788: 800 */     return this.batteryPieceNumber;
/*  789:     */   }
/*  790:     */   
/*  791:     */   public void setBatteryPieceNumber(int batteryPieceNumber)
/*  792:     */   {
/*  793: 804 */     this.batteryPieceNumber = batteryPieceNumber;
/*  794:     */   }
/*  795:     */   
/*  796:     */   public int getBatteryTotalCapacity()
/*  797:     */   {
/*  798: 808 */     return this.batteryTotalCapacity;
/*  799:     */   }
/*  800:     */   
/*  801:     */   public void setBatteryTotalCapacity(int batteryTotalCapacity)
/*  802:     */   {
/*  803: 812 */     this.batteryTotalCapacity = batteryTotalCapacity;
/*  804:     */   }
/*  805:     */   
/*  806:     */   public int getBatteryRemainTime()
/*  807:     */   {
/*  808: 816 */     return this.batteryRemainTime;
/*  809:     */   }
/*  810:     */   
/*  811:     */   public void setBatteryRemainTime(int batteryRemainTime)
/*  812:     */   {
/*  813: 820 */     this.batteryRemainTime = batteryRemainTime;
/*  814:     */   }
/*  815:     */   
/*  816:     */   public void clear()
/*  817:     */   {
/*  818: 824 */     this.gridVoltageR = 0.0D;
/*  819: 825 */     this.gridCurrentR = 0.0D;
/*  820: 826 */     this.gridFrequency = 0.0D;
/*  821: 827 */     this.gridPowerR = 0.0D;
/*  822: 828 */     this.pvInputPower1 = 0;
/*  823: 829 */     this.pvInputPower2 = 0;
/*  824: 830 */     this.pvInputPower3 = 0;
/*  825: 831 */     this.pvInputVoltage1 = 0.0D;
/*  826: 832 */     this.pvInputVoltage2 = 0.0D;
/*  827: 833 */     this.pvInputVoltage3 = 0.0D;
/*  828: 834 */     this.maxTemperature = 0.0D;
/*  829: 835 */     this.faultInfo = "";
/*  830: 836 */     this.warnings.clear();
/*  831: 837 */     this.acOutputVoltageR = 0.0D;
/*  832: 838 */     this.acOutputPowerR = 0.0D;
/*  833: 839 */     this.acOutputFrequency = 0.0D;
/*  834: 840 */     this.acOutputCurrentR = 0.0D;
/*  835: 841 */     this.outputLoadPercent = 0;
/*  836: 842 */     this.pBUSVoltage = 0.0D;
/*  837: 843 */     this.sBUSVoltage = 0.0D;
/*  838: 844 */     setAcOutputApparentPower(0.0D);
/*  839: 845 */     setAcOutputActivePower(0.0D);
/*  840: 846 */     setAcTtlOutputActivePower(0.0D);
/*  841: 847 */     setAcTtlOutputApparentPower(0.0D);
/*  842: 848 */     setTtlChargingCurrent(0.0D);
/*  843: 849 */     this.pBatteryVoltage = 0.0D;
/*  844: 850 */     this.nBatteryVoltage = 0.0D;
/*  845: 851 */     this.batteryCapacity = 0;
/*  846: 852 */     this.chargingCurrent = 0.0D;
/*  847: 853 */     this.batteryPieceNumber = 0;
/*  848: 854 */     this.batteryTotalCapacity = 0;
/*  849: 855 */     this.batteryRemainTime = 0;
/*  850: 856 */     this.rGridVoltage = 0.0D;
/*  851: 857 */     this.sGridVoltage = 0.0D;
/*  852: 858 */     this.tGridVoltage = 0.0D;
/*  853: 859 */     this.rsGridVoltage = 0.0D;
/*  854: 860 */     this.rtGridVoltage = 0.0D;
/*  855: 861 */     this.stGridVoltage = 0.0D;
/*  856: 862 */     this.rGridCurrent = 0.0D;
/*  857: 863 */     this.sGridCurrent = 0.0D;
/*  858: 864 */     this.tGridCurrent = 0.0D;
/*  859: 865 */     this.rPhasePower = 0;
/*  860: 866 */     this.sPhasePower = 0;
/*  861: 867 */     this.tPhasePower = 0;
/*  862: 868 */     this.wholePower = 0;
/*  863: 869 */     this.rPhaseACOutputVoltage = 0.0D;
/*  864: 870 */     this.sPhaseACOutputVoltage = 0.0D;
/*  865: 871 */     this.tPhaseACOutputVoltage = 0.0D;
/*  866: 872 */     this.rsPhaseACOutputVoltage = 0.0D;
/*  867: 873 */     this.rtPhaseACOutputVoltage = 0.0D;
/*  868: 874 */     this.stPhaseACOutputVoltage = 0.0D;
/*  869: 875 */     this.rACOutputCurrent = 0.0D;
/*  870: 876 */     this.sACOutputCurrent = 0.0D;
/*  871: 877 */     this.tACOutputCurrent = 0.0D;
/*  872: 878 */     this.rPhaseACOutputLoad = 0;
/*  873: 879 */     this.sPhaseACOutputLoad = 0;
/*  874: 880 */     this.tPhaseACOutputLoad = 0;
/*  875: 881 */     this.wholeACOutputLoad = 0;
/*  876: 882 */     this.lineLoss = false;
/*  877: 883 */     this.noBattery = false;
/*  878: 884 */     this.pvLoss = false;
/*  879: 885 */     this.selfTesting = false;
/*  880: 886 */     this.morphological = "";
/*  881: 887 */     this.epoActive = false;
/*  882: 888 */     this.hasLoad = false;
/*  883: 889 */     this.isOverLoad = false;
/*  884: 890 */     this.batteryStatus = "";
/*  885: 891 */     this.invDirection = "";
/*  886: 892 */     this.lineDirection = "";
/*  887: 893 */     this.isFault = false;
/*  888: 894 */     this.chargeSource = "---";
/*  889: 895 */     this.loadSource = "---";
/*  890:     */   }
/*  891:     */   
/*  892:     */   public void clearWarning()
/*  893:     */   {
/*  894: 902 */     this.warnings.clear();
/*  895:     */   }
/*  896:     */   
/*  897:     */   public void addWarning(String warningCode)
/*  898:     */   {
/*  899: 911 */     this.warnings.add(warningCode);
/*  900:     */   }
/*  901:     */   
/*  902:     */   public void removeWarning(String warningCode)
/*  903:     */   {
/*  904: 920 */     this.warnings.remove(warningCode);
/*  905:     */   }
/*  906:     */   
/*  907:     */   public boolean isSelfTesting()
/*  908:     */   {
/*  909: 928 */     return this.selfTesting;
/*  910:     */   }
/*  911:     */   
/*  912:     */   public void setSelfTesting(boolean selfTesting)
/*  913:     */   {
/*  914: 932 */     this.selfTesting = selfTesting;
/*  915:     */   }
/*  916:     */   
/*  917:     */   public void setFault(boolean isFault)
/*  918:     */   {
/*  919: 936 */     this.isFault = isFault;
/*  920:     */   }
/*  921:     */   
/*  922:     */   public boolean isFault()
/*  923:     */   {
/*  924: 940 */     return this.isFault;
/*  925:     */   }
/*  926:     */   
/*  927:     */   public void setLowestLimtInputV(double lowestLimtInputV)
/*  928:     */   {
/*  929: 944 */     this.lowestLimtInputV = lowestLimtInputV;
/*  930:     */   }
/*  931:     */   
/*  932:     */   public double getLowestLimtInputV()
/*  933:     */   {
/*  934: 948 */     return this.lowestLimtInputV;
/*  935:     */   }
/*  936:     */   
/*  937:     */   public void setAcOutputApparentPower(double acOutputApparentPower)
/*  938:     */   {
/*  939: 952 */     this.acOutputApparentPower = acOutputApparentPower;
/*  940:     */   }
/*  941:     */   
/*  942:     */   public double getAcOutputApparentPower()
/*  943:     */   {
/*  944: 956 */     return this.acOutputApparentPower;
/*  945:     */   }
/*  946:     */   
/*  947:     */   public void setAcOutputActivePower(double acOutputActivePower)
/*  948:     */   {
/*  949: 960 */     this.acOutputActivePower = acOutputActivePower;
/*  950:     */   }
/*  951:     */   
/*  952:     */   public double getAcOutputActivePower()
/*  953:     */   {
/*  954: 964 */     return this.acOutputActivePower;
/*  955:     */   }
/*  956:     */   
/*  957:     */   public void setChargeSource(String chargeSource)
/*  958:     */   {
/*  959: 968 */     this.chargeSource = chargeSource;
/*  960:     */   }
/*  961:     */   
/*  962:     */   public String getChargeSource()
/*  963:     */   {
/*  964: 972 */     return this.chargeSource;
/*  965:     */   }
/*  966:     */   
/*  967:     */   public void setLoadSource(String loadSource)
/*  968:     */   {
/*  969: 976 */     this.loadSource = loadSource;
/*  970:     */   }
/*  971:     */   
/*  972:     */   public String getLoadSource()
/*  973:     */   {
/*  974: 980 */     return this.loadSource;
/*  975:     */   }
/*  976:     */   
/*  977:     */   public void setCustomerV(String customerV)
/*  978:     */   {
/*  979: 984 */     this.customerV = customerV;
/*  980:     */   }
/*  981:     */   
/*  982:     */   public String getCustomerV()
/*  983:     */   {
/*  984: 988 */     return this.customerV;
/*  985:     */   }
/*  986:     */   
/*  987:     */   public double getAcTtlOutputApparentPower()
/*  988:     */   {
/*  989: 992 */     return this.acTtlOutputApparentPower;
/*  990:     */   }
/*  991:     */   
/*  992:     */   public void setAcTtlOutputApparentPower(double acTtlOutputApparentPower)
/*  993:     */   {
/*  994: 996 */     this.acTtlOutputApparentPower = acTtlOutputApparentPower;
/*  995:     */   }
/*  996:     */   
/*  997:     */   public double getAcTtlOutputActivePower()
/*  998:     */   {
/*  999:1000 */     return this.acTtlOutputActivePower;
/* 1000:     */   }
/* 1001:     */   
/* 1002:     */   public void setAcTtlOutputActivePower(double acTtlOutputActivePower)
/* 1003:     */   {
/* 1004:1004 */     this.acTtlOutputActivePower = acTtlOutputActivePower;
/* 1005:     */   }
/* 1006:     */   
/* 1007:     */   public double getTtlChargingCurrent()
/* 1008:     */   {
/* 1009:1008 */     return this.ttlChargingCurrent;
/* 1010:     */   }
/* 1011:     */   
/* 1012:     */   public void setTtlChargingCurrent(double ttlChargingCurrent)
/* 1013:     */   {
/* 1014:1012 */     this.ttlChargingCurrent = ttlChargingCurrent;
/* 1015:     */   }
/* 1016:     */   
/* 1017:     */   public double getAcTtlOutputPercent()
/* 1018:     */   {
/* 1019:1016 */     return this.acTtlOutputPercent;
/* 1020:     */   }
/* 1021:     */   
/* 1022:     */   public void setAcTtlOutputPercent(double acTtlOutputPercent)
/* 1023:     */   {
/* 1024:1020 */     this.acTtlOutputPercent = acTtlOutputPercent;
/* 1025:     */   }
/* 1026:     */   
/* 1027:     */   public int getPvInputCurrent()
/* 1028:     */   {
/* 1029:1024 */     return this.pvInputCurrent;
/* 1030:     */   }
/* 1031:     */   
/* 1032:     */   public void setPvInputCurrent(int pvInputCurrent)
/* 1033:     */   {
/* 1034:1028 */     this.pvInputCurrent = pvInputCurrent;
/* 1035:     */   }
/* 1036:     */   
/* 1037:     */   public int getBatDisCurrent()
/* 1038:     */   {
/* 1039:1032 */     return this.batDisCurrent;
/* 1040:     */   }
/* 1041:     */   
/* 1042:     */   public void setBatDisCurrent(int batDisCurrent)
/* 1043:     */   {
/* 1044:1036 */     this.batDisCurrent = batDisCurrent;
/* 1045:     */   }
/* 1046:     */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.WorkInfo
 * JD-Core Version:    0.7.0.1
 */