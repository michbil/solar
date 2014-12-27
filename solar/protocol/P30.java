/*   1:    */ package cn.com.voltronic.solar.protocol;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.communicate.IComUSBHandler;
/*   5:    */ import cn.com.voltronic.solar.communicate.ModbusHandler;
/*   6:    */ import cn.com.voltronic.solar.communicate.SNMPHandler;
/*   7:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   8:    */ import cn.com.voltronic.solar.data.bean.AutoComboBoxItem;
/*   9:    */ import cn.com.voltronic.solar.data.bean.AutoLabelItem;
/*  10:    */ import cn.com.voltronic.solar.data.bean.AutoMenuItem;
/*  11:    */ import cn.com.voltronic.solar.data.bean.AutoMenuList;
/*  12:    */ import cn.com.voltronic.solar.data.bean.AutoMenuOne;
/*  13:    */ import cn.com.voltronic.solar.data.bean.AutoMenuTwo;
/*  14:    */ import cn.com.voltronic.solar.data.bean.AutoRadioItem;
/*  15:    */ import cn.com.voltronic.solar.data.bean.AutoSpinnerItem;
/*  16:    */ import cn.com.voltronic.solar.data.bean.BaseInfo;
/*  17:    */ import cn.com.voltronic.solar.data.bean.ComboBoxParameter;
/*  18:    */ import cn.com.voltronic.solar.data.bean.ConfigData;
/*  19:    */ import cn.com.voltronic.solar.data.bean.HistoryChart;
/*  20:    */ import cn.com.voltronic.solar.data.bean.HistoryData;
/*  21:    */ import cn.com.voltronic.solar.data.bean.HistoryDataChartColumns;
/*  22:    */ import cn.com.voltronic.solar.data.bean.HistoryDataColumns;
/*  23:    */ import cn.com.voltronic.solar.data.bean.HistoryFaultDataColumns;
/*  24:    */ import cn.com.voltronic.solar.data.bean.MachineInfo;
/*  25:    */ import cn.com.voltronic.solar.data.bean.MoreInfo;
/*  26:    */ import cn.com.voltronic.solar.data.bean.ProductInfo;
/*  27:    */ import cn.com.voltronic.solar.data.bean.PurchaseInfo;
/*  28:    */ import cn.com.voltronic.solar.data.bean.RadioParameter;
/*  29:    */ import cn.com.voltronic.solar.data.bean.RatingInfo;
/*  30:    */ import cn.com.voltronic.solar.data.bean.RestoreInfo;
/*  31:    */ import cn.com.voltronic.solar.data.bean.SpinnerParameter;
/*  32:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  33:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  34:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  35:    */ import java.util.ArrayList;
/*  36:    */ import java.util.List;
/*  37:    */ 
/*  38:    */ public class P30
/*  39:    */   implements IProtocol
/*  40:    */ {
/*  41: 49 */   private String serialNo = "";
/*  42: 50 */   private volatile boolean delaychanging = false;
/*  43: 53 */   private int outputmode = 0;
/*  44:    */   
/*  45:    */   public String getProtocolID()
/*  46:    */   {
/*  47: 59 */     return "P30";
/*  48:    */   }
/*  49:    */   
/*  50:    */   public boolean matchProtocol(Object handler)
/*  51:    */   {
/*  52: 64 */     if ((handler instanceof IComUSBHandler))
/*  53:    */     {
/*  54: 65 */       IComUSBHandler _handler = (IComUSBHandler)handler;
/*  55: 66 */       String dataStr = "(NAK";
/*  56: 67 */       for (int i = 0; i < 5; i++)
/*  57:    */       {
/*  58: 68 */         if (!dataStr.equals("(NAK")) {
/*  59:    */           break;
/*  60:    */         }
/*  61:    */         try
/*  62:    */         {
/*  63: 70 */           dataStr = _handler.excuteSimpleCommand("QPI");
/*  64:    */         }
/*  65:    */         catch (Exception e)
/*  66:    */         {
/*  67: 73 */           return false;
/*  68:    */         }
/*  69:    */       }
/*  70: 79 */       if ((dataStr.startsWith("(PI")) && 
/*  71: 80 */         (dataStr.endsWith("30")) && ((this instanceof P30))) {
/*  72:    */         try
/*  73:    */         {
/*  74: 82 */           String serialNoStr = _handler.getSerialNo();
/*  75: 83 */           if ((serialNoStr != null) && (!"".equals(serialNoStr))) {
/*  76: 84 */             this.serialNo = serialNoStr;
/*  77:    */           } else {
/*  78: 86 */             return false;
/*  79:    */           }
/*  80: 88 */           dataStr = _handler.excuteSimpleCommand("QPIRI");
/*  81: 89 */           if ((!"".equals(dataStr)) && (!dataStr.equals("(NAK")))
/*  82:    */           {
/*  83: 90 */             String[] ratingInfo = dataStr.split(" ");
/*  84: 91 */             if ((ratingInfo[15].equals("2")) || (ratingInfo[15].equals("3"))) {
/*  85: 92 */               this.outputmode = 1;
/*  86: 94 */             } else if (ratingInfo.length > 21) {
/*  87:    */               try
/*  88:    */               {
/*  89: 96 */                 this.outputmode = Integer.parseInt(ratingInfo[21]);
/*  90:    */               }
/*  91:    */               catch (Exception localException1) {}
/*  92:    */             }
/*  93:    */           }
/*  94:105 */           return true;
/*  95:    */         }
/*  96:    */         catch (Exception ex)
/*  97:    */         {
/*  98:103 */           return false;
/*  99:    */         }
/* 100:    */       }
/* 101:108 */       return false;
/* 102:    */     }
/* 103:109 */     if (!(handler instanceof ModbusHandler)) {
/* 104:111 */       (handler instanceof SNMPHandler);
/* 105:    */     }
/* 106:114 */     return false;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public BaseInfo getBaseInfo()
/* 110:    */   {
/* 111:125 */     BaseInfo baseInfo = new BaseInfo();
/* 112:126 */     List<AutoLabelItem> item = new ArrayList();
/* 113:127 */     item.add(new AutoLabelItem("message.acVoltage[:]", "{workInfo.getGridVoltageR}", "V"));
/* 114:128 */     item.add(new AutoLabelItem("message.acFrequency[:]", "{workInfo.getGridFrequency}", "Hz"));
/* 115:129 */     item.add(new AutoLabelItem("message.pvInputVoltage[:]", "{workInfo.getPvInputVoltage1}", "V"));
/* 116:    */     
/* 117:131 */     item.add(new AutoLabelItem("message.pvInputCurrent[:]", "{workInfo.getPvInputCurrent}", "A"));
/* 118:    */     
/* 119:133 */     item.add(new AutoLabelItem("message.batteryVoltage[:]", "{workInfo.getPBatteryVoltage}", "V"));
/* 120:134 */     item.add(new AutoLabelItem("message.batteryCapacity[:]", "{workInfo.getBatteryCapacity}", "%"));
/* 121:135 */     item.add(new AutoLabelItem("message.chargeCurrent[:]", "{workInfo.getChargingCurrent}", "A"));
/* 122:    */     
/* 123:137 */     item.add(new AutoLabelItem("Battery Discharge Current[:]", "{workInfo.getBatDisCurrent}", "A"));
/* 124:    */     
/* 125:    */ 
/* 126:140 */     item.add(new AutoLabelItem("message.outputVoltage[:]", "{workInfo.getAcOutputVoltageR}", "V"));
/* 127:141 */     item.add(new AutoLabelItem("message.outputFrequency[:]", "{workInfo.getAcOutputFrequency}", "Hz"));
/* 128:142 */     item.add(new AutoLabelItem("Output apparent power:", "{workInfo.getAcOutputApparentPower}", "VA"));
/* 129:143 */     item.add(new AutoLabelItem("Output active power:", "{workInfo.getAcOutputActivePower}", "W"));
/* 130:144 */     item.add(new AutoLabelItem("Load percent:", "{workInfo.getOutputLoadPercent}", "%"));
/* 131:146 */     if (this.outputmode == 1)
/* 132:    */     {
/* 133:147 */       item.add(new AutoLabelItem("Total Charging Current:", "{workInfo.getTtlChargingCurrent}", "A"));
/* 134:148 */       item.add(new AutoLabelItem("Total Output apparent power:", "{workInfo.getAcTtlOutputApparentPower}", "VA"));
/* 135:149 */       item.add(new AutoLabelItem("Total Output active power:", "{workInfo.getAcTtlOutputActivePower}", "W"));
/* 136:150 */       item.add(new AutoLabelItem("Total Output percent:", "{workInfo.getAcTtlOutputPercent}", "%"));
/* 137:    */     }
/* 138:154 */     baseInfo.setBaseInfoList(item);
/* 139:155 */     return baseInfo;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public MoreInfo getMoreInfo()
/* 143:    */   {
/* 144:161 */     return null;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public ProductInfo getProductInfo()
/* 148:    */   {
/* 149:166 */     ProductInfo productInfo = new ProductInfo();
/* 150:167 */     List<AutoLabelItem> infos = new ArrayList();
/* 151:    */     
/* 152:169 */     infos.add(new AutoLabelItem("message.machinetype[:]", "{machineInfo.getMachineType}", ""));
/* 153:170 */     infos.add(new AutoLabelItem("message.topology[:]", "{machineInfo.getTopology}", ""));
/* 154:    */     
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:176 */     infos.add(new AutoLabelItem("Main CPU version:", "{machineInfo.getMainFirmwareVersion}", ""));
/* 160:177 */     infos.add(new AutoLabelItem("Secondary CPU version:", "{machineInfo.getSlaveFirmwareVersion}", ""));
/* 161:178 */     productInfo.setInfos(infos);
/* 162:179 */     return productInfo;
/* 163:    */   }
/* 164:    */   
/* 165:    */   public PurchaseInfo getPurchaseInfo()
/* 166:    */   {
/* 167:185 */     return null;
/* 168:    */   }
/* 169:    */   
/* 170:    */   public RatingInfo getRatingInfo()
/* 171:    */   {
/* 172:190 */     RatingInfo ratingInfo = new RatingInfo();
/* 173:191 */     List<AutoLabelItem> infos = new ArrayList();
/* 174:192 */     infos.add(new AutoLabelItem("Nominal AC voltage:", "{machineInfo.getGridRatingVoltage}", "V"));
/* 175:    */     
/* 176:194 */     infos.add(new AutoLabelItem("Nominal AC current:", "{machineInfo.getGridRatingCurrent}", "A"));
/* 177:    */     
/* 178:196 */     infos.add(new AutoLabelItem("message.batteryRatingVoltage[:]", "{machineInfo.getBatteryRatingVoltage}", "V"));
/* 179:197 */     infos.add(new AutoLabelItem("Nominal output voltage:", "{machineInfo.getAcOutputRatingVoltage}", "V"));
/* 180:198 */     infos.add(new AutoLabelItem("Nominal output frequency:", "{machineInfo.getAcOutputRatingFrequency}", "Hz"));
/* 181:199 */     infos.add(new AutoLabelItem("Nominal output current:", "{machineInfo.getAcOutputRatingCurrent}", "A"));
/* 182:200 */     infos.add(new AutoLabelItem("Nominal output apparent power:", "{machineInfo.getAcOutputRatingApparentPower}", "VA"));
/* 183:201 */     infos.add(new AutoLabelItem("Nominal output active power:", "{machineInfo.getAcOutputRatingActivePower}", "W"));
/* 184:202 */     ratingInfo.setInfos(infos);
/* 185:203 */     return ratingInfo;
/* 186:    */   }
/* 187:    */   
/* 188:    */   public RestoreInfo getResotreInfo()
/* 189:    */   {
/* 190:209 */     AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/* 191:210 */     MachineInfo machineInfo = null;
/* 192:211 */     if (processor != null) {
/* 193:212 */       machineInfo = (MachineInfo)processor.getBeanBag().getBean(
/* 194:213 */         "machineinfo");
/* 195:    */     }
/* 196:216 */     RestoreInfo restoreInfo = new RestoreInfo();
/* 197:217 */     List<AutoLabelItem> infos = new ArrayList();
/* 198:    */     
/* 199:219 */     infos.add(new AutoLabelItem("AC output frequency:", "{defaultData.getAcOutputFrequency}", "Hz"));
/* 200:221 */     if ((is1200or5200(machineInfo)) || (is1K3KV15_50or2_20above(machineInfo)))
/* 201:    */     {
/* 202:222 */       infos.add(new AutoLabelItem("message.maxChargingVoltage[:]", "{defaultData.getMaxChargingVoltage}", "V"));
/* 203:223 */       infos.add(new AutoLabelItem("message.maxFloatingVoltage[:]", "{defaultData.getFloatChargingVoltage}", "V"));
/* 204:    */     }
/* 205:226 */     infos.add(new AutoLabelItem("Max charging current:", "{defaultData.getMaxChargingCurrent}", "A"));
/* 206:228 */     if (isSupportChargingCurrentComBox(machineInfo)) {
/* 207:229 */       infos.add(new AutoLabelItem("Max AC charging current:", "{defaultData.getMaxACChargingCurrent}", "A"));
/* 208:    */     }
/* 209:232 */     if ((is1200or5200(machineInfo)) || (is1K3KV15_50or2_20above(machineInfo))) {
/* 210:233 */       infos.add(new AutoLabelItem("message.batteryCutoffVoltage[:]", "{defaultData.getBatteryCutoffVoltage}", "A"));
/* 211:    */     }
/* 212:235 */     if ((is1210or5210(machineInfo)) || (is1K3KV15_50or2_20above(machineInfo))) {
/* 213:236 */       infos.add(new AutoLabelItem("Back to discharge voltage:", "{defaultData.getReDischargeVoltage}", "V"));
/* 214:    */     }
/* 215:243 */     if (!is5KwV1_12orBelow(machineInfo)) {
/* 216:245 */       if (is1210or5210(machineInfo)) {
/* 217:246 */         infos.add(new AutoLabelItem("Back to grid voltage:", "{defaultData.getReChangingVoltage}", "V"));
/* 218:    */       } else {
/* 219:248 */         infos.add(new AutoLabelItem("Back to grid voltage for SBU priority:", "{defaultData.getReChangingVoltage}", "V"));
/* 220:    */       }
/* 221:    */     }
/* 222:253 */     if (is4k5kwV11_00orabove(machineInfo)) {
/* 223:254 */       infos.add(new AutoLabelItem("Output Mode:", "{defaultData.getOutputMode}", " "));
/* 224:    */     }
/* 225:267 */     infos.add(new AutoLabelItem("AC input voltage range:", "{defaultData.getAcInputVoltage}", ""));
/* 226:268 */     infos.add(new AutoLabelItem("Output source priority:", "{defaultData.getOutputSourcePriority}", ""));
/* 227:269 */     infos.add(new AutoLabelItem("Charger source priority:", "{defaultData.getChargerSourcePriority}", ""));
/* 228:270 */     infos.add(new AutoLabelItem("Battery type:", "{defaultData.getBatteryType}", ""));
/* 229:    */     
/* 230:272 */     infos.add(new AutoLabelItem("Enable/disable silence buzzer or open buzzer:", "{defaultData.getCapableA}", ""));
/* 231:273 */     infos.add(new AutoLabelItem("Enable/Disable power saving:", "{defaultData.getCapableJ}", ""));
/* 232:274 */     infos.add(new AutoLabelItem("Enable/Disable overload restart:", "{defaultData.getCapableU}", ""));
/* 233:275 */     infos.add(new AutoLabelItem("Enable/Disable over temperature restart:", "{defaultData.getCapableV}", ""));
/* 234:276 */     infos.add(new AutoLabelItem("Enable/Disable LCD backlight on:", "{defaultData.getCapableX}", ""));
/* 235:277 */     infos.add(new AutoLabelItem("Enable/Disable alarm on when primary source interrupt:", "{defaultData.getCapableY}", ""));
/* 236:280 */     if (is1200or5200(machineInfo)) {
/* 237:282 */       infos.add(new AutoLabelItem("Enable/Disable LCD come back to default page after 1 min.", "{defaultData.getCapableK}", ""));
/* 238:    */     }
/* 239:291 */     if (!is5KwV1_12orBelow(machineInfo)) {
/* 240:292 */       infos.add(new AutoLabelItem("Enable/disable overload bypass:", "{defaultData.getCapableB}", ""));
/* 241:    */     }
/* 242:296 */     restoreInfo.setInfos(infos);
/* 243:297 */     return restoreInfo;
/* 244:    */   }
/* 245:    */   
/* 246:    */   public RadioParameter getRadioParameter()
/* 247:    */   {
/* 248:303 */     AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/* 249:304 */     MachineInfo machineInfo = null;
/* 250:305 */     if (processor != null) {
/* 251:306 */       machineInfo = (MachineInfo)processor.getBeanBag().getBean(
/* 252:307 */         "machineinfo");
/* 253:    */     }
/* 254:310 */     RadioParameter radioParameter = new RadioParameter();
/* 255:311 */     List<AutoRadioItem> list = new ArrayList();
/* 256:312 */     list.add(new AutoRadioItem("message.pepdA[:]", "message.enable", "message.disable", "{cappbility.isCapableA}", "CapableA", false));
/* 257:313 */     if (this.outputmode == 0) {
/* 258:314 */       list.add(new AutoRadioItem("Power saving mode:", "message.enable", "message.disable", "{cappbility.isCapableJ}", "CapableJ", false));
/* 259:    */     }
/* 260:316 */     list.add(new AutoRadioItem("Backlight:", "message.enable", "message.disable", "{cappbility.isCapableX}", "CapableX", false));
/* 261:317 */     list.add(new AutoRadioItem("Overload auto restart:", "message.enable", "message.disable", "{cappbility.isCapableU}", "CapableU", false));
/* 262:318 */     list.add(new AutoRadioItem("Over temperature auto restart:", "message.enable", "message.disable", "{cappbility.isCapableV}", "CapableV", false));
/* 263:319 */     list.add(new AutoRadioItem("Beeps while primary source interrupt:", "message.enable", "message.disable", "{cappbility.isCapableY}", "CapableY", false));
/* 264:324 */     if (!is5KwV1_12orBelow(machineInfo)) {
/* 265:325 */       list.add(new AutoRadioItem("Overload bypass:", "message.enable", "message.disable", "{cappbility.isCapableB}", "CapableB", false));
/* 266:    */     }
/* 267:328 */     if ((is1200or5200(machineInfo)) || (is1K3KV15_33or1_18above(machineInfo))) {
/* 268:330 */       list.add(new AutoRadioItem("LCD come back to default page after 1 min. : ", "message.enable", "message.disable", "{cappbility.isCapableK}", "CapableK", false));
/* 269:    */     }
/* 270:335 */     radioParameter.setRadioParameterList(list);
/* 271:336 */     return radioParameter;
/* 272:    */   }
/* 273:    */   
/* 274:    */   public SpinnerParameter getSpinnerParameter()
/* 275:    */   {
/* 276:341 */     SpinnerParameter spinnerParameter = new SpinnerParameter();
/* 277:342 */     List<AutoSpinnerItem> list = new ArrayList();
/* 278:343 */     MachineInfo machineInfo = null;
/* 279:    */     
/* 280:345 */     AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/* 281:346 */     if (processor != null)
/* 282:    */     {
/* 283:347 */       machineInfo = (MachineInfo)processor.getBeanBag().getBean(
/* 284:348 */         "machineinfo");
/* 285:349 */       ConfigData config = (ConfigData)processor.getBeanBag().getBean("configdata");
/* 286:    */       
/* 287:351 */       double step = config.getMaxMaxChargeCurrent() - config.getMinMaxChargeCurrent();
/* 288:352 */       if (step > 10.0D) {
/* 289:353 */         step = 10.0D;
/* 290:    */       }
/* 291:362 */       if (!isSupportChargingCurrentComBox(machineInfo)) {
/* 292:363 */         list.add(new AutoSpinnerItem("Max. charger current:", "{configData.getMinMaxChargeCurrent}", "{configData.getMaxMaxChargeCurrent}", String.valueOf(step), "{configData.getMaxChargeCurrent}", "A", "MaxChargeCurrent", false));
/* 293:    */       }
/* 294:    */     }
/* 295:367 */     if ((is4k5kwV11_00orabove(machineInfo)) || (is1K3KV15_50or2_20above(machineInfo)))
/* 296:    */     {
/* 297:368 */       list.add(new AutoSpinnerItem("message.maxChargingVoltage[:]", "{configData.getMinMaxChargeVoltage}", "{configData.getMaxMaxChargeVoltage}", 
/* 298:369 */         "0.1", "{configData.getMaxChargeVoltage}", "V", "setMaxChargingVoltage", false));
/* 299:    */       
/* 300:    */ 
/* 301:372 */       list.add(new AutoSpinnerItem("message.maxFloatingVoltage[:]", "{configData.getMinFloatingChargeVoltage}", "{configData.getMaxFloatingChargeVoltage}", 
/* 302:373 */         "0.1", "{configData.getFloatingChargeVoltage}", "V", "setFloatingVoltage", false));
/* 303:    */     }
/* 304:375 */     if ((processor != null) && (
/* 305:376 */       (is1200or5200(machineInfo)) || (is1K3KV15_50or2_20above(machineInfo)))) {
/* 306:377 */       list.add(new AutoSpinnerItem("message.batteryCutoffVoltage[:]", "{configData.getMinBatteryUnder}", "{configData.getMaxBatteryUnder}", 
/* 307:378 */         "0.1", "{configData.getBatteryUnder}", "V", "setBatteryCutoffVoltage", false));
/* 308:    */     }
/* 309:382 */     spinnerParameter.setSpinnerParameterList(list);
/* 310:383 */     return spinnerParameter;
/* 311:    */   }
/* 312:    */   
/* 313:    */   public ComboBoxParameter getComboBoxParameter()
/* 314:    */   {
/* 315:390 */     AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/* 316:391 */     MachineInfo machineInfo = null;
/* 317:392 */     ConfigData config = null;
/* 318:393 */     if (processor != null)
/* 319:    */     {
/* 320:394 */       machineInfo = (MachineInfo)processor.getBeanBag().getBean(
/* 321:395 */         "machineinfo");
/* 322:396 */       config = (ConfigData)processor.getBeanBag().getBean("configdata");
/* 323:    */     }
/* 324:399 */     ComboBoxParameter comboBoxParameter = new ComboBoxParameter();
/* 325:400 */     List<AutoComboBoxItem> list = new ArrayList();
/* 326:402 */     if (is1200or5200(machineInfo)) {
/* 327:403 */       list.add(new AutoComboBoxItem("Charger source priority:", new String[] { "Utility", "Solar first", "Utility and Solar", "Solar only" }, "{configData.getChargerSource}", "", "PChargerSource", false));
/* 328:404 */     } else if (this.outputmode == 0) {
/* 329:405 */       if (is1K3KV15_33or1_18above(machineInfo)) {
/* 330:406 */         list.add(new AutoComboBoxItem("Charger source priority:", new String[] { "Utility", "Solar first", "Utility and Solar", "Solar only" }, "{configData.getChargerSource}", "", "ChargerSource", false));
/* 331:    */       } else {
/* 332:408 */         list.add(new AutoComboBoxItem("Charger source priority:", new String[] { "Utility", "Solar first", "Utility and Solar" }, "{configData.getChargerSource}", "", "ChargerSource", false));
/* 333:    */       }
/* 334:    */     }
/* 335:416 */     if (is5KwV1_12orBelow(machineInfo)) {
/* 336:417 */       list.add(new AutoComboBoxItem("Output source priority:", new String[] { "Utility", "Solar" }, "{configData.getOutputSource}", "", "OutputSource", false));
/* 337:420 */     } else if (this.outputmode == 0) {
/* 338:421 */       list.add(new AutoComboBoxItem("Output source priority:", new String[] { "Utility", "Solar", "SBU" }, "{configData.getOutputSource}", "", "OutputSource", false));
/* 339:423 */     } else if (is1200or5200(machineInfo)) {
/* 340:424 */       list.add(new AutoComboBoxItem("Output source priority:", new String[] { "Utility", "SBU" }, "{configData.getOutputSource}", "", "OutputSource", false));
/* 341:    */     } else {
/* 342:426 */       list.add(new AutoComboBoxItem("Output source priority:", new String[] { "Utility" }, "{configData.getOutputSource}", "", "OutputSource", false));
/* 343:    */     }
/* 344:432 */     list.add(new AutoComboBoxItem("AC input range:", new String[] { "Appliance", "UPS" }, "{configData.getAcInputRange}", "", "ACInputRange", false));
/* 345:435 */     if (!is4k5kwV11_00orabove(machineInfo))
/* 346:    */     {
/* 347:436 */       if (is1K3KV15_50or2_20above(machineInfo)) {
/* 348:437 */         list.add(new AutoComboBoxItem("Battery type:", new String[] { "AGM", "Flooded", "User" }, "{configData.getBatteryType}", "", "BatteryType", false));
/* 349:    */       } else {
/* 350:439 */         list.add(new AutoComboBoxItem("Battery type:", new String[] { "AGM", "Flooded" }, "{configData.getBatteryType}", "", "BatteryType", false));
/* 351:    */       }
/* 352:    */     }
/* 353:    */     else
/* 354:    */     {
/* 355:442 */       list.add(new AutoComboBoxItem("Battery type:", new String[] { "AGM", "Flooded", "User" }, "{configData.getBatteryType}", "", "BatteryType", false));
/* 356:443 */       list.add(new AutoComboBoxItem("Output Mode:", new String[] { "Single", "Parallel", 
/* 357:444 */         "Phase R of 3 phase output", "Phase S of 3 phase output", "Phase T of 3 phase output" }, 
/* 358:445 */         "{configData.getOutputMode}", "", "OutputMode", false));
/* 359:    */     }
/* 360:448 */     list.add(new AutoComboBoxItem("Output frequency:", new String[] { "50", "60" }, "{configData.getOutputFrequency}", "Hz", "OutputFrequency", false));
/* 361:451 */     if (processor != null) {
/* 362:466 */       if ((machineInfo != null) && (config != null))
/* 363:    */       {
/* 364:467 */         if (!is5KwV1_12orBelow(machineInfo)) {
/* 365:470 */           if (is1210or5210(machineInfo))
/* 366:    */           {
/* 367:471 */             if (config.getBatteryVoltage() > 40.0D) {
/* 368:472 */               list.add(new AutoComboBoxItem("Back to grid voltage:", new String[] { "44.0", "45.0", "46.0", "47.0", "48.0", "49.0", "50.0", "51.0" }, "{configData.getRechargeVoltage}", "V", "ReChargeVoltage", false));
/* 369:473 */             } else if (config.getBatteryVoltage() > 20.0D) {
/* 370:474 */               list.add(new AutoComboBoxItem("Back to grid voltage:", new String[] { "22.0", "22.5", "23.0", "23.5", "24.0", "24.5", "25.0", "25.5" }, "{configData.getRechargeVoltage}", "V", "ReChargeVoltage", false));
/* 371:    */             } else {
/* 372:476 */               list.add(new AutoComboBoxItem("Back to grid voltage:", new String[] { "11.0", "11.3", "11.5", "11.8", "12.0", "12.3", "12.5", "12.8" }, "{configData.getRechargeVoltage}", "V", "ReChargeVoltage", false));
/* 373:    */             }
/* 374:    */           }
/* 375:480 */           else if (config.getBatteryVoltage() > 40.0D) {
/* 376:481 */             list.add(new AutoComboBoxItem("Back to grid voltage for SBU priority:", new String[] { "44.0", "45.0", "46.0", "47.0", "48.0", "49.0", "50.0", "51.0" }, "{configData.getRechargeVoltage}", "V", "ReChargeVoltage", false));
/* 377:482 */           } else if (config.getBatteryVoltage() > 20.0D) {
/* 378:483 */             list.add(new AutoComboBoxItem("Back to grid voltage for SBU priority:", new String[] { "22.0", "22.5", "23.0", "23.5", "24.0", "24.5", "25.0", "25.5" }, "{configData.getRechargeVoltage}", "V", "ReChargeVoltage", false));
/* 379:    */           } else {
/* 380:485 */             list.add(new AutoComboBoxItem("Back to grid voltage for SBU priority:", new String[] { "11.0", "11.3", "11.5", "11.8", "12.0", "12.3", "12.5", "12.8" }, "{configData.getRechargeVoltage}", "V", "ReChargeVoltage", false));
/* 381:    */           }
/* 382:    */         }
/* 383:489 */         if (isSupportChargingCurrentComBox(machineInfo))
/* 384:    */         {
/* 385:490 */           list.add(new AutoComboBoxItem("Max. charging current:", config.getMaxChargeCurrentCombox(machineInfo, this.outputmode), "{configData.getMaxChargeCurrentInt}", "A", "PMaxChargeCurrent", false));
/* 386:491 */           list.add(new AutoComboBoxItem("Max. AC charging current:", config.getAcChargingCurrentComBox(), "{configData.getMaxacchargingcurrentInt}", "A", "PMaxACChargeCurrent", false));
/* 387:    */         }
/* 388:494 */         if (is1210or5210(machineInfo)) {
/* 389:495 */           if (config.getBatteryVoltage() > 40.0D) {
/* 390:496 */             list.add(new AutoComboBoxItem("Back to discharge voltage:", new String[] { "48.0", "49.0", "50.0", "51.0", "52.0", "53.0", "54.0", "55.0", "56.0", "57.0", "58.0", "FULL" }, "{configData.getReDischargeVoltage}", "V", "ReDisChargeVoltage", false));
/* 391:497 */           } else if (config.getBatteryVoltage() > 20.0D) {
/* 392:498 */             list.add(new AutoComboBoxItem("Back to discharge voltage:", new String[] { "24.0", "24.5", "25.0", "25.5", "26.0", "26.5", "27.0", "27.5", "28.0", "28.5", "29.0", "FULL" }, "{configData.getReDischargeVoltage}", "V", "ReDisChargeVoltage", false));
/* 393:    */           } else {
/* 394:500 */             list.add(new AutoComboBoxItem("Back to discharge voltage:", new String[] { "12.0", "12.3", "12.5", "12.8", "13.0", "13.3", "13.5", "13.8", "14.0", "14.3", "14.5", "FULL" }, "{configData.getReDischargeVoltage}", "V", "ReDisChargeVoltage", false));
/* 395:    */           }
/* 396:    */         }
/* 397:    */       }
/* 398:    */     }
/* 399:516 */     comboBoxParameter.setComboBoxParameterList(list);
/* 400:517 */     return comboBoxParameter;
/* 401:    */   }
/* 402:    */   
/* 403:    */   private boolean is5KwV1_12orBelow(MachineInfo machineInfo)
/* 404:    */   {
/* 405:521 */     if ((machineInfo == null) || (
/* 406:522 */       (machineInfo.getAcOutputRatingApparentPower() == 5000.0D) && 
/* 407:523 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00001.13") < 0))) {
/* 408:524 */       return true;
/* 409:    */     }
/* 410:526 */     return false;
/* 411:    */   }
/* 412:    */   
/* 413:    */   private boolean is4k5kwV11_00orabove(MachineInfo machineInfo)
/* 414:    */   {
/* 415:531 */     if (machineInfo == null) {
/* 416:532 */       return false;
/* 417:    */     }
/* 418:533 */     if (((machineInfo.getAcOutputRatingApparentPower() == 4000.0D) || 
/* 419:534 */       (machineInfo.getAcOutputRatingApparentPower() == 5000.0D)) && (
/* 420:535 */       ((machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00011.00") >= 0) && 
/* 421:536 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00050.00") < 0)) || 
/* 422:537 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00051.00") >= 0))) {
/* 423:538 */       return true;
/* 424:    */     }
/* 425:540 */     return false;
/* 426:    */   }
/* 427:    */   
/* 428:    */   private boolean is1K3KV15_33or1_18above(MachineInfo machineInfo)
/* 429:    */   {
/* 430:544 */     if (machineInfo == null) {
/* 431:545 */       return false;
/* 432:    */     }
/* 433:546 */     if ((machineInfo.getAcOutputRatingApparentPower() == 1000.0D) || 
/* 434:547 */       (machineInfo.getAcOutputRatingApparentPower() == 2000.0D) || 
/* 435:548 */       (machineInfo.getAcOutputRatingApparentPower() == 3000.0D))
/* 436:    */     {
/* 437:549 */       if (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00015.33") >= 0) {
/* 438:550 */         return true;
/* 439:    */       }
/* 440:551 */       if ((machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00001.18") >= 0) && 
/* 441:552 */         (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00010.00") < 0)) {
/* 442:553 */         return true;
/* 443:    */       }
/* 444:    */     }
/* 445:556 */     return false;
/* 446:    */   }
/* 447:    */   
/* 448:    */   private boolean is1K3KV15_50or2_20above(MachineInfo machineInfo)
/* 449:    */   {
/* 450:560 */     if (machineInfo == null) {
/* 451:561 */       return false;
/* 452:    */     }
/* 453:562 */     if ((machineInfo.getAcOutputRatingApparentPower() == 1000.0D) || 
/* 454:563 */       (machineInfo.getAcOutputRatingApparentPower() == 2000.0D) || 
/* 455:564 */       (machineInfo.getAcOutputRatingApparentPower() == 3000.0D))
/* 456:    */     {
/* 457:565 */       if (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00015.50") >= 0) {
/* 458:566 */         return true;
/* 459:    */       }
/* 460:567 */       if ((machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00002.20") >= 0) && 
/* 461:568 */         (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00010.00") < 0)) {
/* 462:569 */         return true;
/* 463:    */       }
/* 464:    */     }
/* 465:572 */     return false;
/* 466:    */   }
/* 467:    */   
/* 468:    */   private boolean is1200or5200(MachineInfo machineInfo)
/* 469:    */   {
/* 470:578 */     if (machineInfo == null) {
/* 471:579 */       return false;
/* 472:    */     }
/* 473:581 */     if (((machineInfo.getAcOutputRatingApparentPower() == 4000.0D) || 
/* 474:582 */       (machineInfo.getAcOutputRatingApparentPower() == 5000.0D)) && (
/* 475:583 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00052.00") >= 0) || (
/* 476:584 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00050.00") < 0) && 
/* 477:585 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00012.00") >= 0)))) {
/* 478:586 */       return true;
/* 479:    */     }
/* 480:588 */     return false;
/* 481:    */   }
/* 482:    */   
/* 483:    */   private boolean is1210or5210(MachineInfo machineInfo)
/* 484:    */   {
/* 485:593 */     if (machineInfo == null) {
/* 486:595 */       return false;
/* 487:    */     }
/* 488:597 */     if (((machineInfo.getAcOutputRatingApparentPower() == 4000.0D) || 
/* 489:598 */       (machineInfo.getAcOutputRatingApparentPower() == 5000.0D)) && (
/* 490:599 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00052.10") >= 0) || (
/* 491:600 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00050.00") < 0) && 
/* 492:601 */       (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00012.10") >= 0)))) {
/* 493:602 */       return true;
/* 494:    */     }
/* 495:603 */     if ((machineInfo.getAcOutputRatingApparentPower() == 1000.0D) || 
/* 496:604 */       (machineInfo.getAcOutputRatingApparentPower() == 2000.0D) || 
/* 497:605 */       (machineInfo.getAcOutputRatingApparentPower() == 3000.0D))
/* 498:    */     {
/* 499:606 */       if (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00015.50") >= 0) {
/* 500:607 */         return true;
/* 501:    */       }
/* 502:608 */       if ((machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00002.20") >= 0) && 
/* 503:609 */         (machineInfo.getMainFirmwareVersion().compareToIgnoreCase("00010.00") < 0)) {
/* 504:610 */         return true;
/* 505:    */       }
/* 506:    */     }
/* 507:613 */     return false;
/* 508:    */   }
/* 509:    */   
/* 510:    */   private boolean isSupportChargingCurrentComBox(MachineInfo machineInfo)
/* 511:    */   {
/* 512:618 */     if (machineInfo == null) {
/* 513:619 */       return false;
/* 514:    */     }
/* 515:621 */     return machineInfo.isChargeCurrentComBox();
/* 516:    */   }
/* 517:    */   
/* 518:    */   public HistoryDataColumns getHistoryColumns()
/* 519:    */   {
/* 520:627 */     HistoryDataColumns columns = new HistoryDataColumns();
/* 521:628 */     List<HistoryData> list = new ArrayList();
/* 522:629 */     list.add(new HistoryData("message.workMode", "{workInfo.getWorkMode}", true, false));
/* 523:630 */     list.add(new HistoryData("message.time", "{workInfo.getCurrentTime}", true, false));
/* 524:631 */     list.add(new HistoryData("message.acVoltage", "{workInfo.getGridVoltageR}", true, false));
/* 525:632 */     list.add(new HistoryData("message.acFrequency", "{workInfo.getGridFrequency}", true, false));
/* 526:633 */     list.add(new HistoryData("message.pvInputVoltage", "{workInfo.getPvInputVoltage1}", true, true));
/* 527:634 */     list.add(new HistoryData("AC output apparent power", "{workInfo.getAcOutputApparentPower}", true, true));
/* 528:635 */     list.add(new HistoryData("AC output active power", "{workInfo.getAcOutputActivePower}", true, true));
/* 529:636 */     list.add(new HistoryData("message.batteryVoltage", "{workInfo.getPBatteryVoltage}", true, true));
/* 530:637 */     list.add(new HistoryData("message.batteryCapacity", "{workInfo.getBatteryCapacity}", true, true));
/* 531:638 */     list.add(new HistoryData("message.chargeCurrent", "{workInfo.getChargingCurrent}", true, true));
/* 532:639 */     list.add(new HistoryData("message.outputVoltage", "{workInfo.getAcOutputVoltageR}", true, true));
/* 533:640 */     list.add(new HistoryData("message.outputFrequency", "{workInfo.getAcOutputFrequency}", true, true));
/* 534:641 */     if (this.outputmode == 1)
/* 535:    */     {
/* 536:642 */       list.add(new HistoryData("Total Charging Current", "{workInfo.getTtlChargingCurrent}", true, true));
/* 537:643 */       list.add(new HistoryData("Total Output apparent power", "{workInfo.getAcTtlOutputApparentPower}", true, true));
/* 538:644 */       list.add(new HistoryData("Total Output active power", "{workInfo.getAcTtlOutputActivePower}", true, true));
/* 539:645 */       list.add(new HistoryData("Total Output percent", "{workInfo.getAcTtlOutputPercent}", true, true));
/* 540:    */     }
/* 541:648 */     columns.setColumns(list);
/* 542:649 */     return columns;
/* 543:    */   }
/* 544:    */   
/* 545:    */   public HistoryFaultDataColumns getHistoryFaultColumns()
/* 546:    */   {
/* 547:654 */     HistoryFaultDataColumns columns = new HistoryFaultDataColumns();
/* 548:655 */     List<HistoryData> list = new ArrayList();
/* 549:656 */     list.add(new HistoryData("message.trandate", "{faultData.getTrandate}", true, true));
/* 550:657 */     list.add(new HistoryData("message.faultString", "{faultData.getFaultString}", true, true));
/* 551:658 */     list.add(new HistoryData("message.gridVoltageR", "{faultData.getGridVoltage}", true, true));
/* 552:659 */     list.add(new HistoryData("message.gridFrequency", "{faultData.getGridFrequency}", true, true));
/* 553:660 */     list.add(new HistoryData("message.pvInputVoltage", "{faultData.getPvinputvoltage1}", true, true));
/* 554:661 */     list.add(new HistoryData("message.outputLoadPercent", "{faultData.getOutputLoadPercent}", true, true));
/* 555:662 */     list.add(new HistoryData("message.acOutputVoltage", "{faultData.getOutputLoadVoltage}", true, true));
/* 556:663 */     list.add(new HistoryData("message.acOutputCurrent", "{faultData.getOutputLoadCurrent}", true, true));
/* 557:664 */     list.add(new HistoryData("message.acOutputFrequency", "{faultData.getOutputLoadFrequency}", true, true));
/* 558:665 */     list.add(new HistoryData("message.batteryVoltage", "{faultData.getBatteryVoltage}", true, true));
/* 559:666 */     list.add(new HistoryData("message.temperature", "{faultData.getMaxTemperature}", true, true));
/* 560:667 */     columns.setColumns(list);
/* 561:668 */     return columns;
/* 562:    */   }
/* 563:    */   
/* 564:    */   public HistoryDataChartColumns getHistoryChartColumns()
/* 565:    */   {
/* 566:673 */     HistoryDataChartColumns columns = new HistoryDataChartColumns();
/* 567:674 */     List<HistoryChart> list = new ArrayList();
/* 568:675 */     list.add(new HistoryChart("message.acVoltage", "{workInfo.getGridVoltageR}", 300.0D, "V", true));
/* 569:676 */     list.add(new HistoryChart("message.acFrequency", "{workInfo.getGridFrequency}", 70.0D, "Hz", true));
/* 570:677 */     list.add(new HistoryChart("message.pvInputVoltage", "{workInfo.getPvInputVoltage1}", 500.0D, "V", true));
/* 571:678 */     list.add(new HistoryChart("AC output apparent power", "{workInfo.getAcOutputApparentPower}", 3000.0D, "VA", true));
/* 572:679 */     list.add(new HistoryChart("AC output active power", "{workInfo.getAcOutputActivePower}", 3000.0D, "W", true));
/* 573:680 */     list.add(new HistoryChart("message.batteryVoltage", "{workInfo.getPBatteryVoltage}", 500.0D, "V", true));
/* 574:681 */     list.add(new HistoryChart("message.batteryCapacity", "{workInfo.getBatteryCapacity}", 100.0D, "%", true));
/* 575:682 */     list.add(new HistoryChart("message.chargeCurrent", "{workInfo.getChargingCurrent}", 100.0D, "A", true));
/* 576:683 */     list.add(new HistoryChart("message.outputVoltage", "{workInfo.getAcOutputVoltageR}", 300.0D, "V", true));
/* 577:684 */     list.add(new HistoryChart("message.outputFrequency", "{workInfo.getAcOutputFrequency}", 70.0D, "Hz", true));
/* 578:686 */     if (this.outputmode == 1)
/* 579:    */     {
/* 580:687 */       list.add(new HistoryChart("Total Charging Current", "{workInfo.getTtlChargingCurrent}", 100.0D, "A", true));
/* 581:688 */       list.add(new HistoryChart("Total Output apparent power", "{workInfo.getAcTtlOutputApparentPower}", 20000.0D, "VA", true));
/* 582:689 */       list.add(new HistoryChart("Total Output active power", "{workInfo.getAcTtlOutputActivePower}", 20000.0D, "W", true));
/* 583:690 */       list.add(new HistoryChart("Total Output percent", "{workInfo.getAcTtlOutputPercent}", 100.0D, "%", true));
/* 584:    */     }
/* 585:693 */     columns.setColumns(list);
/* 586:694 */     return columns;
/* 587:    */   }
/* 588:    */   
/* 589:    */   public String getSerialNo()
/* 590:    */   {
/* 591:699 */     return this.serialNo;
/* 592:    */   }
/* 593:    */   
/* 594:    */   public AutoMenuList getMenuList()
/* 595:    */   {
/* 596:704 */     AutoMenuList menuList = new AutoMenuList();
/* 597:705 */     List<AutoMenuOne> menus = new ArrayList();
/* 598:    */     
/* 599:707 */     List<AutoMenuTwo> items1 = new ArrayList();
/* 600:708 */     items1.add(new AutoMenuTwo(new AutoMenuItem("message.baseSet", 101, ""), null));
/* 601:709 */     items1.add(new AutoMenuTwo(new AutoMenuItem("message.passwordSet", 102, ""), null));
/* 602:    */     
/* 603:711 */     items1.add(new AutoMenuTwo(new AutoMenuItem("message.smsinfoSet", 104, ""), null));
/* 604:712 */     items1.add(new AutoMenuTwo(new AutoMenuItem("message.emailSet", 105, ""), null));
/* 605:713 */     items1.add(new AutoMenuTwo(new AutoMenuItem("message.electricEventSet", 106, ""), null));
/* 606:714 */     items1.add(new AutoMenuTwo(new AutoMenuItem("message.setcom", 107, ""), null));
/* 607:    */     
/* 608:716 */     List<AutoMenuTwo> items2 = new ArrayList();
/* 609:717 */     items2.add(new AutoMenuTwo(new AutoMenuItem("message.parametersSetting", 201, ""), null));
/* 610:718 */     items2.add(new AutoMenuTwo(new AutoMenuItem("message.factoryReset", 202, ""), null));
/* 611:    */     
/* 612:    */ 
/* 613:    */ 
/* 614:722 */     List<AutoMenuTwo> items3 = new ArrayList();
/* 615:    */     
/* 616:724 */     items3.add(new AutoMenuTwo(new AutoMenuItem("message.queryData", 302, ""), null));
/* 617:    */     
/* 618:726 */     items3.add(new AutoMenuTwo(new AutoMenuItem("message.queryEvent", 304, ""), null));
/* 619:    */     
/* 620:    */ 
/* 621:    */ 
/* 622:    */ 
/* 623:    */ 
/* 624:    */ 
/* 625:    */ 
/* 626:    */ 
/* 627:    */ 
/* 628:    */ 
/* 629:    */ 
/* 630:    */ 
/* 631:    */ 
/* 632:    */ 
/* 633:    */ 
/* 634:    */ 
/* 635:743 */     List<AutoMenuTwo> items5 = new ArrayList();
/* 636:744 */     items5.add(new AutoMenuTwo(new AutoMenuItem("message.about", 501, ""), null));
/* 637:    */     
/* 638:746 */     List<AutoMenuItem> items = new ArrayList();
/* 639:    */     
/* 640:748 */     CustomerConfig custormerConfig = GlobalVariables.customerConfig;
/* 641:749 */     if (custormerConfig.isEnglish()) {
/* 642:750 */       items.add(new AutoMenuItem("English", 5021, ""));
/* 643:    */     }
/* 644:752 */     if (custormerConfig.isTurkish()) {
/* 645:753 */       items.add(new AutoMenuItem("Turkish", 5022, ""));
/* 646:    */     }
/* 647:755 */     if (custormerConfig.isRussian()) {
/* 648:756 */       items.add(new AutoMenuItem("Russian", 5023, ""));
/* 649:    */     }
/* 650:758 */     if (items.size() < 2) {
/* 651:759 */       items = null;
/* 652:    */     }
/* 653:762 */     items5.add(new AutoMenuTwo(new AutoMenuItem("message.onlineHelp", 502, ""), items));
/* 654:    */     
/* 655:764 */     menus.add(new AutoMenuOne("[" + GlobalVariables.customerConfig.getCustomerName() + "]" + 
/* 656:765 */       "message.pvConfig", 1, items1));
/* 657:766 */     menus.add(new AutoMenuOne("message.pvControl", 2, items2));
/* 658:767 */     menus.add(new AutoMenuOne("message.pvView", 3, items3));
/* 659:    */     
/* 660:769 */     menus.add(new AutoMenuOne("message.help", 5, items5));
/* 661:    */     
/* 662:771 */     menuList.setMenuList(menus);
/* 663:772 */     return menuList;
/* 664:    */   }
/* 665:    */   
/* 666:    */   public boolean getDelayChanging()
/* 667:    */   {
/* 668:778 */     return this.delaychanging;
/* 669:    */   }
/* 670:    */   
/* 671:    */   public void setDelayChanging(boolean delaychange)
/* 672:    */   {
/* 673:783 */     this.delaychanging = delaychange;
/* 674:    */   }
/* 675:    */   
/* 676:    */   public int getOutputMode()
/* 677:    */   {
/* 678:789 */     return this.outputmode;
/* 679:    */   }
/* 680:    */   
/* 681:    */   public void setOutputMode(int outputmode)
/* 682:    */   {
/* 683:794 */     this.outputmode = outputmode;
/* 684:    */   }
/* 685:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.protocol.P30
 * JD-Core Version:    0.7.0.1
 */