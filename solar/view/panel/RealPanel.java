/*   1:    */ package cn.com.voltronic.solar.view.panel;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.constants.Constants;
/*   4:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*   5:    */ import javax.swing.ImageIcon;
/*   6:    */ import javax.swing.JLabel;
/*   7:    */ import javax.swing.JPanel;
/*   8:    */ 
/*   9:    */ public class RealPanel
/*  10:    */   extends JPanel
/*  11:    */ {
/*  12:    */   private static final long serialVersionUID = -7664649546219184701L;
/*  13:    */   private JLabel infinibgLabel;
/*  14:    */   private JLabel infiniRightLabel1;
/*  15:    */   private JLabel infiniRightLabel2;
/*  16:    */   private JLabel infiniDownLabel1;
/*  17:    */   private JLabel infiniDownLabel2;
/*  18:    */   private JLabel infiniUPLabel1;
/*  19:    */   private JLabel infiniUPLabel2;
/*  20:    */   private JLabel infiniLeftUPLabel;
/*  21:    */   private JLabel infiniLeftDownLabel;
/*  22:    */   private JLabel infiniUPLeftLabel;
/*  23:    */   private JLabel infiniUPRightLabel;
/*  24:    */   private JLabel infiniDownRightLabel;
/*  25:    */   private JLabel infiniDownUpDownLabel;
/*  26:    */   private JLabel infiniACLossLabel;
/*  27:    */   private JLabel infiniBatteryLossLabel;
/*  28:    */   private JLabel infiniFaultModeLabel;
/*  29:    */   private JLabel infiniOverLoadLabel;
/*  30:    */   private JLabel workModeV;
/*  31:    */   private JLabel chargeSourceC;
/*  32:    */   private JLabel outputSourceC;
/*  33:    */   private JLabel chargeSourceV;
/*  34:    */   private JLabel outputSourceV;
/*  35:    */   
/*  36:    */   public RealPanel()
/*  37:    */   {
/*  38: 46 */     initComponents();
/*  39:    */   }
/*  40:    */   
/*  41:    */   private void initComponents()
/*  42:    */   {
/*  43: 51 */     this.workModeV = new JLabel();
/*  44: 52 */     this.chargeSourceC = new JLabel();
/*  45: 53 */     this.outputSourceC = new JLabel();
/*  46: 54 */     this.chargeSourceV = new JLabel();
/*  47: 55 */     this.outputSourceV = new JLabel();
/*  48:    */     
/*  49: 57 */     this.infinibgLabel = new JLabel();
/*  50: 58 */     this.infiniRightLabel1 = new JLabel();
/*  51: 59 */     this.infiniRightLabel2 = new JLabel();
/*  52: 60 */     this.infiniDownLabel1 = new JLabel();
/*  53: 61 */     this.infiniDownLabel2 = new JLabel();
/*  54: 62 */     this.infiniUPLabel1 = new JLabel();
/*  55: 63 */     this.infiniUPLabel2 = new JLabel();
/*  56: 64 */     this.infiniLeftUPLabel = new JLabel();
/*  57: 65 */     this.infiniLeftDownLabel = new JLabel();
/*  58: 66 */     this.infiniUPLeftLabel = new JLabel();
/*  59: 67 */     this.infiniUPRightLabel = new JLabel();
/*  60: 68 */     this.infiniDownRightLabel = new JLabel();
/*  61: 69 */     this.infiniDownUpDownLabel = new JLabel();
/*  62: 70 */     this.infiniACLossLabel = new JLabel();
/*  63: 71 */     this.infiniBatteryLossLabel = new JLabel();
/*  64: 72 */     this.infiniFaultModeLabel = new JLabel();
/*  65: 73 */     this.infiniOverLoadLabel = new JLabel();
/*  66:    */     
/*  67: 75 */     setLayout(null);
/*  68:    */     
/*  69: 77 */     this.workModeV.setText("---");
/*  70: 78 */     add(this.workModeV);
/*  71: 79 */     this.workModeV.setBounds(310, 50, 200, 15);
/*  72:    */     
/*  73: 81 */     this.chargeSourceC.setText("Source:");
/*  74: 82 */     this.chargeSourceC.setBounds(180, 270, 50, 15);
/*  75: 83 */     this.chargeSourceC.setVisible(false);
/*  76: 84 */     add(this.chargeSourceC);
/*  77: 85 */     this.outputSourceC.setText("Source:");
/*  78: 86 */     this.outputSourceC.setBounds(330, 180, 50, 15);
/*  79: 87 */     this.outputSourceC.setVisible(false);
/*  80: 88 */     add(this.outputSourceC);
/*  81:    */     
/*  82: 90 */     this.chargeSourceV.setText("---");
/*  83: 91 */     this.chargeSourceV.setVisible(false);
/*  84: 92 */     add(this.chargeSourceV);
/*  85: 93 */     this.chargeSourceV.setBounds(230, 270, 200, 15);
/*  86: 94 */     this.outputSourceV.setText("---");
/*  87: 95 */     this.outputSourceV.setVisible(false);
/*  88: 96 */     add(this.outputSourceV);
/*  89: 97 */     this.outputSourceV.setBounds(380, 180, 200, 15);
/*  90:    */     
/*  91:    */ 
/*  92:100 */     this.infinibgLabel.setIcon(new ImageIcon(Constants.INFINI_BG1));
/*  93:101 */     this.infinibgLabel.setBounds(10, 20, 450, 250);
/*  94:102 */     this.infinibgLabel.setVisible(true);
/*  95:    */     
/*  96:104 */     this.infiniRightLabel1.setIcon(new ImageIcon(Constants.RIGHT_ARROW1));
/*  97:105 */     this.infiniRightLabel1.setBounds(102, 144, 98, 11);
/*  98:106 */     this.infiniRightLabel1.setVisible(false);
/*  99:    */     
/* 100:108 */     this.infiniRightLabel2.setIcon(new ImageIcon(Constants.RIGHT_ARROW2));
/* 101:109 */     this.infiniRightLabel2.setBounds(259, 144, 94, 11);
/* 102:110 */     this.infiniRightLabel2.setVisible(false);
/* 103:    */     
/* 104:112 */     this.infiniDownLabel1.setIcon(new ImageIcon(Constants.DOWN1));
/* 105:113 */     this.infiniDownLabel1.setBounds(224, 83, 10, 36);
/* 106:114 */     this.infiniDownLabel1.setVisible(false);
/* 107:    */     
/* 108:116 */     this.infiniDownLabel2.setIcon(new ImageIcon(Constants.DOWN2));
/* 109:117 */     this.infiniDownLabel2.setBounds(224, 178, 10, 43);
/* 110:118 */     this.infiniDownLabel2.setVisible(false);
/* 111:    */     
/* 112:120 */     this.infiniUPLabel1.setIcon(new ImageIcon(Constants.UP1));
/* 113:121 */     this.infiniUPLabel1.setBounds(224, 83, 10, 36);
/* 114:122 */     this.infiniUPLabel1.setVisible(false);
/* 115:    */     
/* 116:124 */     this.infiniUPLabel2.setIcon(new ImageIcon(Constants.UP2));
/* 117:125 */     this.infiniUPLabel2.setBounds(224, 178, 10, 42);
/* 118:126 */     this.infiniUPLabel2.setVisible(false);
/* 119:    */     
/* 120:128 */     this.infiniLeftUPLabel.setIcon(new ImageIcon(Constants.LeftUP));
/* 121:129 */     this.infiniLeftUPLabel.setBounds(191, 110, 43, 34);
/* 122:130 */     this.infiniLeftUPLabel.setVisible(false);
/* 123:    */     
/* 124:132 */     this.infiniLeftDownLabel.setIcon(new ImageIcon(Constants.LeftDown));
/* 125:133 */     this.infiniLeftDownLabel.setBounds(191, 145, 34, 43);
/* 126:134 */     this.infiniLeftDownLabel.setVisible(false);
/* 127:    */     
/* 128:136 */     this.infiniUPLeftLabel.setIcon(new ImageIcon(Constants.UPLeft));
/* 129:137 */     this.infiniUPLeftLabel.setBounds(191, 110, 43, 34);
/* 130:138 */     this.infiniUPLeftLabel.setVisible(false);
/* 131:    */     
/* 132:140 */     this.infiniUPRightLabel.setIcon(new ImageIcon(Constants.UPRight));
/* 133:141 */     this.infiniUPRightLabel.setBounds(234, 110, 34, 34);
/* 134:142 */     this.infiniUPRightLabel.setVisible(false);
/* 135:    */     
/* 136:144 */     this.infiniDownRightLabel.setIcon(new ImageIcon(Constants.DownRight));
/* 137:145 */     this.infiniDownRightLabel.setBounds(224, 155, 44, 32);
/* 138:146 */     this.infiniDownRightLabel.setVisible(false);
/* 139:    */     
/* 140:148 */     this.infiniDownUpDownLabel.setIcon(new ImageIcon(Constants.DOWNUPDOWN));
/* 141:149 */     this.infiniDownUpDownLabel.setBounds(224, 83, 10, 36);
/* 142:150 */     this.infiniDownUpDownLabel.setVisible(false);
/* 143:    */     
/* 144:152 */     this.infiniACLossLabel.setIcon(new ImageIcon(Constants.ACLOSS));
/* 145:153 */     this.infiniACLossLabel.setBounds(196, 35, 67, 47);
/* 146:154 */     this.infiniACLossLabel.setVisible(false);
/* 147:    */     
/* 148:156 */     this.infiniBatteryLossLabel.setIcon(new ImageIcon(Constants.BATTERYLOSS));
/* 149:157 */     this.infiniBatteryLossLabel.setBounds(199, 221, 61, 34);
/* 150:158 */     this.infiniBatteryLossLabel.setVisible(false);
/* 151:    */     
/* 152:160 */     this.infiniFaultModeLabel.setIcon(new ImageIcon(Constants.FAULTMODE));
/* 153:161 */     this.infiniFaultModeLabel.setBounds(177, 92, 105, 111);
/* 154:162 */     this.infiniFaultModeLabel.setVisible(false);
/* 155:    */     
/* 156:164 */     this.infiniOverLoadLabel.setIcon(new ImageIcon(Constants.OVERLOAD));
/* 157:165 */     this.infiniOverLoadLabel.setBounds(358, 119, 76, 47);
/* 158:166 */     this.infiniOverLoadLabel.setVisible(false);
/* 159:    */     
/* 160:168 */     add(this.infiniFaultModeLabel);
/* 161:169 */     add(this.infiniRightLabel1);
/* 162:170 */     add(this.infiniRightLabel2);
/* 163:171 */     add(this.infiniDownLabel1);
/* 164:172 */     add(this.infiniDownLabel2);
/* 165:173 */     add(this.infiniUPLabel1);
/* 166:174 */     add(this.infiniUPLabel2);
/* 167:175 */     add(this.infiniLeftUPLabel);
/* 168:176 */     add(this.infiniLeftDownLabel);
/* 169:177 */     add(this.infiniUPLeftLabel);
/* 170:178 */     add(this.infiniUPRightLabel);
/* 171:179 */     add(this.infiniDownRightLabel);
/* 172:180 */     add(this.infiniDownUpDownLabel);
/* 173:181 */     add(this.infiniACLossLabel);
/* 174:182 */     add(this.infiniBatteryLossLabel);
/* 175:183 */     add(this.infiniOverLoadLabel);
/* 176:184 */     add(this.infinibgLabel);
/* 177:    */   }
/* 178:    */   
/* 179:    */   public void setP30VisibleFalse()
/* 180:    */   {
/* 181:188 */     this.infinibgLabel.setVisible(false);
/* 182:189 */     this.infiniRightLabel1.setVisible(false);
/* 183:190 */     this.infiniRightLabel2.setVisible(false);
/* 184:191 */     this.infiniDownLabel1.setVisible(false);
/* 185:192 */     this.infiniDownLabel2.setVisible(false);
/* 186:193 */     this.infiniUPLabel1.setVisible(false);
/* 187:194 */     this.infiniUPLabel2.setVisible(false);
/* 188:195 */     this.infiniLeftUPLabel.setVisible(false);
/* 189:196 */     this.infiniLeftDownLabel.setVisible(false);
/* 190:197 */     this.infiniUPLeftLabel.setVisible(false);
/* 191:198 */     this.infiniUPRightLabel.setVisible(false);
/* 192:199 */     this.infiniDownRightLabel.setVisible(false);
/* 193:200 */     this.infiniDownUpDownLabel.setVisible(false);
/* 194:201 */     this.infiniACLossLabel.setVisible(false);
/* 195:202 */     this.infiniBatteryLossLabel.setVisible(false);
/* 196:203 */     this.infiniFaultModeLabel.setVisible(false);
/* 197:204 */     this.infiniOverLoadLabel.setVisible(false);
/* 198:205 */     this.chargeSourceC.setVisible(false);
/* 199:206 */     this.chargeSourceV.setVisible(false);
/* 200:207 */     this.outputSourceC.setVisible(false);
/* 201:208 */     this.outputSourceV.setVisible(false);
/* 202:    */   }
/* 203:    */   
/* 204:    */   public void setDefaultValues()
/* 205:    */   {
/* 206:212 */     this.workModeV.setText("---");
/* 207:213 */     this.chargeSourceV.setText("---");
/* 208:214 */     this.outputSourceV.setText("---");
/* 209:215 */     this.chargeSourceC.setVisible(false);
/* 210:216 */     this.chargeSourceV.setVisible(false);
/* 211:217 */     this.outputSourceC.setVisible(false);
/* 212:218 */     this.outputSourceV.setVisible(false);
/* 213:219 */     this.infinibgLabel.setVisible(true);
/* 214:220 */     this.infiniRightLabel1.setVisible(false);
/* 215:221 */     this.infiniRightLabel2.setVisible(false);
/* 216:222 */     this.infiniDownLabel1.setVisible(false);
/* 217:223 */     this.infiniDownLabel2.setVisible(false);
/* 218:224 */     this.infiniUPLabel1.setVisible(false);
/* 219:225 */     this.infiniUPLabel2.setVisible(false);
/* 220:226 */     this.infiniLeftUPLabel.setVisible(false);
/* 221:227 */     this.infiniLeftDownLabel.setVisible(false);
/* 222:228 */     this.infiniUPLeftLabel.setVisible(false);
/* 223:229 */     this.infiniUPRightLabel.setVisible(false);
/* 224:230 */     this.infiniDownRightLabel.setVisible(false);
/* 225:231 */     this.infiniDownUpDownLabel.setVisible(false);
/* 226:232 */     this.infiniACLossLabel.setVisible(false);
/* 227:233 */     this.infiniBatteryLossLabel.setVisible(false);
/* 228:234 */     this.infiniFaultModeLabel.setVisible(false);
/* 229:235 */     this.infiniOverLoadLabel.setVisible(false);
/* 230:    */   }
/* 231:    */   
/* 232:    */   public void setPowerOnMode(WorkInfo workInfo)
/* 233:    */   {
/* 234:239 */     this.infiniRightLabel1.setVisible(false);
/* 235:240 */     this.infiniRightLabel2.setVisible(false);
/* 236:241 */     this.infiniDownLabel1.setVisible(false);
/* 237:242 */     this.infiniDownLabel2.setVisible(false);
/* 238:243 */     this.infiniUPLabel1.setVisible(false);
/* 239:244 */     this.infiniUPLabel2.setVisible(false);
/* 240:245 */     this.infiniLeftUPLabel.setVisible(false);
/* 241:246 */     this.infiniLeftDownLabel.setVisible(false);
/* 242:247 */     this.infiniUPLeftLabel.setVisible(false);
/* 243:248 */     this.infiniUPRightLabel.setVisible(false);
/* 244:249 */     this.infiniDownRightLabel.setVisible(false);
/* 245:250 */     this.infiniDownUpDownLabel.setVisible(false);
/* 246:251 */     this.infiniBatteryLossLabel.setVisible(false);
/* 247:252 */     this.infiniFaultModeLabel.setVisible(false);
/* 248:253 */     if (workInfo.isLineLoss()) {
/* 249:254 */       this.infiniACLossLabel.setVisible(true);
/* 250:    */     } else {
/* 251:256 */       this.infiniACLossLabel.setVisible(false);
/* 252:    */     }
/* 253:258 */     if (workInfo.isOverLoad()) {
/* 254:259 */       this.infiniOverLoadLabel.setVisible(true);
/* 255:    */     } else {
/* 256:261 */       this.infiniOverLoadLabel.setVisible(false);
/* 257:    */     }
/* 258:    */   }
/* 259:    */   
/* 260:    */   public void setStandbyMode(WorkInfo workInfo)
/* 261:    */   {
/* 262:266 */     if (workInfo.isSCCchargeOn())
/* 263:    */     {
/* 264:267 */       this.infiniRightLabel1.setVisible(true);
/* 265:268 */       this.infiniLeftDownLabel.setVisible(true);
/* 266:269 */       this.infiniDownLabel2.setVisible(true);
/* 267:    */     }
/* 268:    */     else
/* 269:    */     {
/* 270:271 */       this.infiniRightLabel1.setVisible(false);
/* 271:    */     }
/* 272:274 */     if (workInfo.isACchargeOn())
/* 273:    */     {
/* 274:275 */       this.infiniDownLabel1.setVisible(true);
/* 275:276 */       this.infiniUPLeftLabel.setVisible(true);
/* 276:277 */       this.infiniLeftDownLabel.setVisible(true);
/* 277:278 */       this.infiniDownLabel2.setVisible(true);
/* 278:    */     }
/* 279:    */     else
/* 280:    */     {
/* 281:280 */       this.infiniDownLabel1.setVisible(false);
/* 282:281 */       this.infiniUPLeftLabel.setVisible(false);
/* 283:    */     }
/* 284:284 */     if ((!workInfo.isSCCchargeOn()) && (!workInfo.isACchargeOn()))
/* 285:    */     {
/* 286:285 */       this.infiniLeftDownLabel.setVisible(false);
/* 287:286 */       this.infiniDownLabel2.setVisible(false);
/* 288:    */     }
/* 289:289 */     this.infiniRightLabel2.setVisible(false);
/* 290:290 */     this.infiniUPLabel1.setVisible(false);
/* 291:291 */     this.infiniUPLabel2.setVisible(false);
/* 292:292 */     this.infiniLeftUPLabel.setVisible(false);
/* 293:293 */     this.infiniUPRightLabel.setVisible(false);
/* 294:294 */     this.infiniDownRightLabel.setVisible(false);
/* 295:295 */     this.infiniDownUpDownLabel.setVisible(false);
/* 296:296 */     this.infiniBatteryLossLabel.setVisible(false);
/* 297:297 */     this.infiniFaultModeLabel.setVisible(false);
/* 298:298 */     if (workInfo.isLineLoss()) {
/* 299:299 */       this.infiniACLossLabel.setVisible(true);
/* 300:    */     } else {
/* 301:301 */       this.infiniACLossLabel.setVisible(false);
/* 302:    */     }
/* 303:303 */     if (workInfo.isOverLoad()) {
/* 304:304 */       this.infiniOverLoadLabel.setVisible(true);
/* 305:    */     } else {
/* 306:306 */       this.infiniOverLoadLabel.setVisible(false);
/* 307:    */     }
/* 308:    */   }
/* 309:    */   
/* 310:    */   public void setLineMode(WorkInfo workInfo)
/* 311:    */   {
/* 312:311 */     if (workInfo.isSCCchargeOn())
/* 313:    */     {
/* 314:312 */       this.infiniRightLabel1.setVisible(true);
/* 315:313 */       this.infiniLeftDownLabel.setVisible(true);
/* 316:314 */       this.infiniDownLabel2.setVisible(true);
/* 317:    */     }
/* 318:    */     else
/* 319:    */     {
/* 320:316 */       this.infiniRightLabel1.setVisible(false);
/* 321:    */     }
/* 322:319 */     if (workInfo.isACchargeOn())
/* 323:    */     {
/* 324:320 */       this.infiniDownLabel1.setVisible(true);
/* 325:321 */       this.infiniUPLeftLabel.setVisible(true);
/* 326:322 */       this.infiniLeftDownLabel.setVisible(true);
/* 327:323 */       this.infiniDownLabel2.setVisible(true);
/* 328:    */     }
/* 329:    */     else
/* 330:    */     {
/* 331:325 */       this.infiniDownLabel1.setVisible(false);
/* 332:326 */       this.infiniUPLeftLabel.setVisible(false);
/* 333:    */     }
/* 334:329 */     if ((!workInfo.isSCCchargeOn()) && (!workInfo.isACchargeOn()))
/* 335:    */     {
/* 336:330 */       this.infiniLeftDownLabel.setVisible(false);
/* 337:331 */       this.infiniDownLabel2.setVisible(false);
/* 338:    */     }
/* 339:334 */     this.infiniUPRightLabel.setVisible(true);
/* 340:335 */     this.infiniRightLabel2.setVisible(true);
/* 341:    */     
/* 342:337 */     this.infiniUPLabel1.setVisible(false);
/* 343:338 */     this.infiniUPLabel2.setVisible(false);
/* 344:339 */     this.infiniLeftUPLabel.setVisible(false);
/* 345:340 */     this.infiniDownRightLabel.setVisible(false);
/* 346:341 */     this.infiniDownUpDownLabel.setVisible(false);
/* 347:342 */     this.infiniBatteryLossLabel.setVisible(false);
/* 348:343 */     this.infiniFaultModeLabel.setVisible(false);
/* 349:344 */     if (workInfo.isLineLoss()) {
/* 350:345 */       this.infiniACLossLabel.setVisible(true);
/* 351:    */     } else {
/* 352:347 */       this.infiniACLossLabel.setVisible(false);
/* 353:    */     }
/* 354:349 */     if (workInfo.isOverLoad()) {
/* 355:350 */       this.infiniOverLoadLabel.setVisible(true);
/* 356:    */     } else {
/* 357:352 */       this.infiniOverLoadLabel.setVisible(false);
/* 358:    */     }
/* 359:    */   }
/* 360:    */   
/* 361:    */   public void setBatteryMode(WorkInfo workInfo)
/* 362:    */   {
/* 363:357 */     if (workInfo.isSCCchargeOn())
/* 364:    */     {
/* 365:358 */       this.infiniRightLabel1.setVisible(true);
/* 366:359 */       this.infiniLeftDownLabel.setVisible(true);
/* 367:360 */       this.infiniDownLabel2.setVisible(true);
/* 368:    */     }
/* 369:    */     else
/* 370:    */     {
/* 371:362 */       this.infiniRightLabel1.setVisible(false);
/* 372:363 */       this.infiniLeftDownLabel.setVisible(false);
/* 373:364 */       this.infiniDownLabel2.setVisible(false);
/* 374:    */     }
/* 375:366 */     this.infiniUPLabel2.setVisible(true);
/* 376:367 */     this.infiniRightLabel2.setVisible(true);
/* 377:368 */     this.infiniDownRightLabel.setVisible(true);
/* 378:    */     
/* 379:370 */     this.infiniDownLabel1.setVisible(false);
/* 380:371 */     this.infiniUPLeftLabel.setVisible(false);
/* 381:372 */     this.infiniUPLabel1.setVisible(false);
/* 382:373 */     this.infiniLeftUPLabel.setVisible(false);
/* 383:374 */     this.infiniUPRightLabel.setVisible(false);
/* 384:375 */     this.infiniDownUpDownLabel.setVisible(false);
/* 385:376 */     this.infiniBatteryLossLabel.setVisible(false);
/* 386:377 */     this.infiniFaultModeLabel.setVisible(false);
/* 387:378 */     if (workInfo.isLineLoss()) {
/* 388:379 */       this.infiniACLossLabel.setVisible(true);
/* 389:    */     } else {
/* 390:381 */       this.infiniACLossLabel.setVisible(false);
/* 391:    */     }
/* 392:383 */     if (workInfo.isOverLoad()) {
/* 393:384 */       this.infiniOverLoadLabel.setVisible(true);
/* 394:    */     } else {
/* 395:386 */       this.infiniOverLoadLabel.setVisible(false);
/* 396:    */     }
/* 397:    */   }
/* 398:    */   
/* 399:    */   public void setFaultMode(WorkInfo workInfo)
/* 400:    */   {
/* 401:391 */     if (workInfo.isSCCchargeOn())
/* 402:    */     {
/* 403:392 */       this.infiniRightLabel1.setVisible(true);
/* 404:393 */       this.infiniLeftDownLabel.setVisible(true);
/* 405:394 */       this.infiniDownLabel2.setVisible(true);
/* 406:    */     }
/* 407:    */     else
/* 408:    */     {
/* 409:396 */       this.infiniRightLabel1.setVisible(false);
/* 410:397 */       this.infiniLeftDownLabel.setVisible(false);
/* 411:398 */       this.infiniDownLabel2.setVisible(false);
/* 412:    */     }
/* 413:400 */     this.infiniRightLabel2.setVisible(false);
/* 414:401 */     this.infiniDownLabel1.setVisible(false);
/* 415:402 */     this.infiniUPLabel1.setVisible(false);
/* 416:403 */     this.infiniUPLabel2.setVisible(false);
/* 417:404 */     this.infiniLeftUPLabel.setVisible(false);
/* 418:405 */     this.infiniUPLeftLabel.setVisible(false);
/* 419:406 */     this.infiniUPRightLabel.setVisible(false);
/* 420:407 */     this.infiniDownRightLabel.setVisible(false);
/* 421:408 */     this.infiniDownUpDownLabel.setVisible(false);
/* 422:409 */     this.infiniBatteryLossLabel.setVisible(false);
/* 423:410 */     this.infiniFaultModeLabel.setVisible(true);
/* 424:411 */     if (workInfo.isLineLoss()) {
/* 425:412 */       this.infiniACLossLabel.setVisible(true);
/* 426:    */     } else {
/* 427:414 */       this.infiniACLossLabel.setVisible(false);
/* 428:    */     }
/* 429:416 */     if (workInfo.isOverLoad()) {
/* 430:417 */       this.infiniOverLoadLabel.setVisible(true);
/* 431:    */     } else {
/* 432:419 */       this.infiniOverLoadLabel.setVisible(false);
/* 433:    */     }
/* 434:    */   }
/* 435:    */   
/* 436:    */   public void refreshWork(WorkInfo workInfo)
/* 437:    */   {
/* 438:424 */     if (workInfo != null)
/* 439:    */     {
/* 440:425 */       String workMode = workInfo.getWorkMode();
/* 441:426 */       String prodid = workInfo.getProdid();
/* 442:427 */       this.workModeV.setText(workMode);
/* 443:428 */       if (workInfo.isChargeOn())
/* 444:    */       {
/* 445:429 */         this.chargeSourceC.setVisible(true);
/* 446:430 */         this.chargeSourceV.setVisible(true);
/* 447:    */       }
/* 448:    */       else
/* 449:    */       {
/* 450:432 */         this.chargeSourceC.setVisible(false);
/* 451:433 */         this.chargeSourceV.setVisible(false);
/* 452:    */       }
/* 453:434 */       if (workInfo.isHasLoad())
/* 454:    */       {
/* 455:435 */         this.outputSourceC.setVisible(true);
/* 456:436 */         this.outputSourceV.setVisible(true);
/* 457:    */       }
/* 458:    */       else
/* 459:    */       {
/* 460:438 */         this.outputSourceC.setVisible(false);
/* 461:439 */         this.outputSourceV.setVisible(false);
/* 462:    */       }
/* 463:441 */       this.chargeSourceV.setText(workInfo.getChargeSource());
/* 464:442 */       this.outputSourceV.setText(workInfo.getLoadSource());
/* 465:443 */       if (prodid.equals("P30"))
/* 466:    */       {
/* 467:444 */         if (workMode.equals("Power On Mode")) {
/* 468:445 */           setPowerOnMode(workInfo);
/* 469:446 */         } else if (workMode.equals("Standby Mode")) {
/* 470:447 */           setStandbyMode(workInfo);
/* 471:448 */         } else if (workMode.equals("Line Mode")) {
/* 472:449 */           setLineMode(workInfo);
/* 473:450 */         } else if (workMode.equals("Battery Mode")) {
/* 474:451 */           setBatteryMode(workInfo);
/* 475:452 */         } else if (workMode.equals("Fault Mode")) {
/* 476:453 */           setFaultMode(workInfo);
/* 477:454 */         } else if (workMode.equals("Shutdown Mode")) {
/* 478:455 */           setDefaultValues();
/* 479:    */         }
/* 480:    */       }
/* 481:    */       else {
/* 482:458 */         setDefaultValues();
/* 483:    */       }
/* 484:    */     }
/* 485:    */   }
/* 486:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.panel.RealPanel
 * JD-Core Version:    0.7.0.1
 */