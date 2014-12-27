/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   5:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   6:    */ import cn.com.voltronic.solar.configure.IniProperties;
/*   7:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   8:    */ import cn.com.voltronic.solar.constants.Constants;
/*   9:    */ import cn.com.voltronic.solar.data.bean.AutoMenuItem;
/*  10:    */ import cn.com.voltronic.solar.data.bean.AutoMenuList;
/*  11:    */ import cn.com.voltronic.solar.data.bean.AutoMenuOne;
/*  12:    */ import cn.com.voltronic.solar.data.bean.AutoMenuTwo;
/*  13:    */ import cn.com.voltronic.solar.data.bean.MachineInfo;
/*  14:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*  15:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  16:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  17:    */ import cn.com.voltronic.solar.protocol.P30;
/*  18:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  19:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  20:    */ import cn.com.voltronic.solar.view.component.AAButtonTip;
/*  21:    */ import cn.com.voltronic.solar.view.component.AAJMenu;
/*  22:    */ import cn.com.voltronic.solar.view.component.AAJMenuItem;
/*  23:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  24:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  25:    */ import cn.com.voltronic.solar.view.component.I18NBundle;
/*  26:    */ import cn.com.voltronic.solar.view.component.VPTree;
/*  27:    */ import cn.com.voltronic.solar.view.panel.BasePanel;
/*  28:    */ import cn.com.voltronic.solar.view.panel.RealPanel;
/*  29:    */ import cn.com.voltronic.solar.view.panel.StatusPanel;
/*  30:    */ import com.jtattoo.plaf.smart.SmartLookAndFeel;
/*  31:    */ import java.awt.BorderLayout;
/*  32:    */ import java.awt.Container;
/*  33:    */ import java.awt.Dimension;
/*  34:    */ import java.awt.Toolkit;
/*  35:    */ import java.awt.event.ActionEvent;
/*  36:    */ import java.awt.event.ActionListener;
/*  37:    */ import java.awt.event.MouseAdapter;
/*  38:    */ import java.awt.event.MouseEvent;
/*  39:    */ import java.awt.event.WindowAdapter;
/*  40:    */ import java.awt.event.WindowEvent;
/*  41:    */ import java.io.File;
/*  42:    */ import java.io.PrintStream;
/*  43:    */ import java.util.List;
/*  44:    */ import java.util.Properties;
/*  45:    */ import javax.swing.BorderFactory;
/*  46:    */ import javax.swing.GroupLayout;
/*  47:    */ import javax.swing.GroupLayout.Alignment;
/*  48:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  49:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  50:    */ import javax.swing.ImageIcon;
/*  51:    */ import javax.swing.JFrame;
/*  52:    */ import javax.swing.JLabel;
/*  53:    */ import javax.swing.JMenuBar;
/*  54:    */ import javax.swing.JPanel;
/*  55:    */ import javax.swing.JScrollPane;
/*  56:    */ import javax.swing.JSplitPane;
/*  57:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  58:    */ import javax.swing.SwingUtilities;
/*  59:    */ import javax.swing.UIManager;
/*  60:    */ import javax.swing.tree.DefaultMutableTreeNode;
/*  61:    */ 
/*  62:    */ public class MainJFrame
/*  63:    */   extends JFrame
/*  64:    */ {
/*  65:    */   private static final long serialVersionUID = -3494660164922508506L;
/*  66: 67 */   private static MainJFrame mainFrame = null;
/*  67: 68 */   private AbstractProcessor currProcesser = null;
/*  68: 70 */   private JMenuBar menuBar = null;
/*  69:    */   private JPanel centerPanel;
/*  70:    */   private JPanel changePanel;
/*  71:    */   private AAButtonTip statusButton;
/*  72:    */   private AAButtonTip parameterButton;
/*  73:    */   private AAButtonTip dataButton;
/*  74:    */   private AAButtonTip eventButton;
/*  75:    */   public static AAButtonTip loginButton;
/*  76:    */   public static AALabel loginLabel;
/*  77:    */   private AALabel monitoredLabelC;
/*  78:    */   private AALabel monitoredLabelV;
/*  79:    */   private JSplitPane jSplitPane;
/*  80:    */   private static VPTree deviceTree;
/*  81:    */   private JScrollPane leftScrollPane;
/*  82:    */   private JScrollPane treeScrollPane;
/*  83:    */   private JPanel treePanel;
/*  84:    */   private JLabel logoLabel;
/*  85:    */   private JPanel mainPanel;
/*  86:    */   private JPanel northPanel;
/*  87:    */   private StatusPanel statusPanel;
/*  88: 93 */   private static Integer lock = Integer.valueOf(1);
/*  89: 95 */   private static Integer menuLock = Integer.valueOf(2);
/*  90: 97 */   public IProtocol protocol = null;
/*  91:    */   
/*  92:    */   static
/*  93:    */   {
/*  94:    */     try
/*  95:    */     {
/*  96:101 */       Properties props = new Properties();
/*  97:    */       
/*  98:103 */       props.put("foregroundColor", "255 255 255");
/*  99:104 */       props.put("frameColor", "130 130 130");
/* 100:105 */       props.put("gridColor", "210 210 210");
/* 101:106 */       props.put("logoString", "");
/* 102:107 */       props.put("tooltipBackgroundColor", "102 102 102");
/* 103:108 */       props.put("tooltipForegroundColor", "255 255 255");
/* 104:    */       
/* 105:110 */       props.put("selectionBackgroundColor", "230 230 230");
/* 106:    */       
/* 107:112 */       props.put("menuSelectionForegroundColor", "0 0 0");
/* 108:113 */       props.put("menuSelectionBackgroundColor", "230 230 230");
/* 109:114 */       props.put("menuSelectionBackgroundColorLight", "255 255 255");
/* 110:115 */       props.put("menuSelectionBackgroundColorDark", "230 230 230");
/* 111:116 */       props.put("menuBackgroundColor", "90 90 90");
/* 112:117 */       props.put("menuColorLight", "69 69 69");
/* 113:118 */       props.put("menuColorDark", "102 102 102");
/* 114:119 */       props.put("menuForegroundColor", "255 255 255");
/* 115:    */       
/* 116:121 */       props.put("controlColor", "102 102 102");
/* 117:122 */       props.put("controlColorLight", "102 102 102");
/* 118:123 */       props.put("controlColorDark", "69 69 69");
/* 119:    */       
/* 120:125 */       props.put("buttonForegroundColor", "255 255 255");
/* 121:126 */       props.put("buttonColor", "102 102 102");
/* 122:127 */       props.put("buttonBackgroundColor", "102 102 102");
/* 123:128 */       props.put("buttonColorLight", "230 230 230");
/* 124:129 */       props.put("buttonColorDark", "102 102 102");
/* 125:    */       
/* 126:131 */       props.put("rolloverColor", "102 102 102");
/* 127:132 */       props.put("rolloverColorLight", "102 102 102");
/* 128:133 */       props.put("rolloverColorDark", "69 69 69");
/* 129:    */       
/* 130:135 */       props.put("windowTitleForegroundColor", "0 0 0");
/* 131:136 */       props.put("windowTitleBackgroundColor", "69 69 69");
/* 132:137 */       props.put("windowTitleColorLight", "197 197 197");
/* 133:138 */       props.put("windowTitleColorDark", "69 69 69");
/* 134:139 */       props.put("windowBorderColor", "10 10 10");
/* 135:    */       
/* 136:141 */       props.put("tabAreaBackgroundColor", "102 102 102");
/* 137:142 */       props.put("inputForegroundColor", "255 255 255");
/* 138:143 */       props.put("inputBackgroundColor", "102 102 102");
/* 139:144 */       props.put("focusColor", "255 255 255");
/* 140:145 */       props.put("focusCellColor", "255 255 255");
/* 141:    */       
/* 142:147 */       props.put("backgroundColor", "102 102 102");
/* 143:148 */       props.put("backgroundColorLight", "255 255 255");
/* 144:149 */       props.put("backgroundColorDark", "102 102 102");
/* 145:    */       
/* 146:151 */       SmartLookAndFeel.setCurrentTheme(props);
/* 147:152 */       UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
/* 148:    */     }
/* 149:    */     catch (Exception ex)
/* 150:    */     {
/* 151:155 */       ex.printStackTrace();
/* 152:    */     }
/* 153:    */   }
/* 154:    */   
/* 155:    */   public MainJFrame()
/* 156:    */   {
/* 157:160 */     this.menuBar = new JMenuBar();
/* 158:161 */     initMenu();
/* 159:162 */     setJMenuBar(this.menuBar);
/* 160:163 */     initComponents();
/* 161:164 */     Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
/* 162:165 */     int width = 1280;
/* 163:166 */     int height = 800;
/* 164:167 */     if (ds.getWidth() < width) {
/* 165:168 */       width = (int)ds.getWidth();
/* 166:    */     }
/* 167:170 */     if (ds.getHeight() < height) {
/* 168:171 */       height = (int)ds.getHeight();
/* 169:    */     }
/* 170:173 */     setSize(width - 20, height - 45);
/* 171:174 */     setTitle(GlobalVariables.customerConfig.getCustomerName());
/* 172:175 */     setIconImage(Constants.CONNECTEDIMG);
/* 173:176 */     setLocationRelativeTo(null);
/* 174:177 */     setVisible(true);
/* 175:    */   }
/* 176:    */   
/* 177:    */   public static MainJFrame getNewInstance()
/* 178:    */   {
/* 179:181 */     synchronized (lock)
/* 180:    */     {
/* 181:182 */       if (mainFrame == null)
/* 182:    */       {
/* 183:183 */         mainFrame = new MainJFrame();
/* 184:184 */         mainFrame.setVisible(true);
/* 185:    */       }
/* 186:186 */       return mainFrame;
/* 187:    */     }
/* 188:    */   }
/* 189:    */   
/* 190:    */   private void initMenu()
/* 191:    */   {
/* 192:191 */     synchronized (menuLock)
/* 193:    */     {
/* 194:193 */       if (this.menuBar != null) {
/* 195:194 */         this.menuBar.removeAll();
/* 196:    */       }
/* 197:196 */       this.protocol = new P30();
/* 198:197 */       this.currProcesser = GlobalProcessors.getCurrentProcessor();
/* 199:198 */       if (this.currProcesser != null) {
/* 200:199 */         this.protocol = this.currProcesser.getProtocol();
/* 201:    */       }
/* 202:201 */       if (this.protocol == null) {
/* 203:202 */         this.protocol = new P30();
/* 204:    */       }
/* 205:204 */       AutoMenuList autoMenuList = this.protocol.getMenuList();
/* 206:205 */       List<AutoMenuOne> autoMenuOneList = autoMenuList.getMenuList();
/* 207:206 */       for (int i = 0; i < autoMenuOneList.size(); i++)
/* 208:    */       {
/* 209:207 */         List<AutoMenuTwo> autoMenuTwoList = ((AutoMenuOne)autoMenuOneList.get(i)).getItems();
/* 210:208 */         String label = ((AutoMenuOne)autoMenuOneList.get(i)).getLabel();
/* 211:    */         
/* 212:210 */         AAJMenu autoMenuOne = new AAJMenu();
/* 213:211 */         autoMenuOne.setText(label);
/* 214:212 */         for (int j = 0; j < autoMenuTwoList.size(); j++)
/* 215:    */         {
/* 216:213 */           List<AutoMenuItem> items = ((AutoMenuTwo)autoMenuTwoList.get(j)).getItems();
/* 217:214 */           if (items == null)
/* 218:    */           {
/* 219:215 */             AutoMenuItem autoMenuItem = ((AutoMenuTwo)autoMenuTwoList.get(j)).getItem();
/* 220:216 */             AAJMenuItem menuItemTwo = new AAJMenuItem();
/* 221:217 */             menuItemTwo.setText(autoMenuItem.getLabel());
/* 222:218 */             final int data = autoMenuItem.getData();
/* 223:219 */             menuItemTwo.addActionListener(new ActionListener()
/* 224:    */             {
/* 225:    */               public void actionPerformed(ActionEvent e)
/* 226:    */               {
/* 227:222 */                 MainJFrame.this.menuItemAction(data, MainJFrame.this.protocol);
/* 228:    */               }
/* 229:224 */             });
/* 230:225 */             autoMenuOne.add(menuItemTwo);
/* 231:    */           }
/* 232:    */           else
/* 233:    */           {
/* 234:227 */             AAJMenu autoMenuItem = new AAJMenu();
/* 235:228 */             autoMenuItem.setText(((AutoMenuTwo)autoMenuTwoList.get(j)).getItem().getLabel());
/* 236:229 */             for (int k = 0; k < items.size(); k++)
/* 237:    */             {
/* 238:230 */               AAJMenuItem menuItemThree = new AAJMenuItem();
/* 239:231 */               menuItemThree.setText(((AutoMenuItem)items.get(k)).getLabel());
/* 240:232 */               final int data = ((AutoMenuItem)items.get(k)).getData();
/* 241:233 */               menuItemThree.addActionListener(new ActionListener()
/* 242:    */               {
/* 243:    */                 public void actionPerformed(ActionEvent e)
/* 244:    */                 {
/* 245:236 */                   MainJFrame.this.menuItemAction(data, MainJFrame.this.protocol);
/* 246:    */                 }
/* 247:238 */               });
/* 248:239 */               autoMenuItem.add(menuItemThree);
/* 249:    */             }
/* 250:241 */             autoMenuOne.add(autoMenuItem);
/* 251:    */           }
/* 252:    */         }
/* 253:244 */         this.menuBar.add(autoMenuOne);
/* 254:    */       }
/* 255:    */     }
/* 256:    */   }
/* 257:    */   
/* 258:    */   private void menuItemAction(int data, IProtocol protocol)
/* 259:    */   {
/* 260:250 */     switch (data)
/* 261:    */     {
/* 262:    */     case 101: 
/* 263:252 */       new BasicJDialog(mainFrame, true);
/* 264:253 */       break;
/* 265:    */     case 102: 
/* 266:255 */       new PasswordJDialog(mainFrame, true);
/* 267:256 */       break;
/* 268:    */     case 103: 
/* 269:258 */       new PriceJDialog(mainFrame, true);
/* 270:259 */       break;
/* 271:    */     case 104: 
/* 272:261 */       new SMSSetting(mainFrame, true);
/* 273:262 */       break;
/* 274:    */     case 105: 
/* 275:264 */       new EmailSetting(mainFrame, true);
/* 276:265 */       break;
/* 277:    */     case 106: 
/* 278:267 */       new EventActionDialog(mainFrame, true);
/* 279:268 */       break;
/* 280:    */     case 107: 
/* 281:270 */       new COMSetJDialog(mainFrame, true);
/* 282:271 */       break;
/* 283:    */     case 201: 
/* 284:273 */       new ParameterJDialog(mainFrame, true);
/* 285:274 */       break;
/* 286:    */     case 202: 
/* 287:276 */       new RestoreJDialog(mainFrame, true);
/* 288:277 */       break;
/* 289:    */     case 203: 
/* 290:    */       break;
/* 291:    */     case 204: 
/* 292:281 */       if (protocol.getProtocolID().equals("P30")) {
/* 293:282 */         new RealControlJDialog30(mainFrame, true);
/* 294:    */       } else {
/* 295:284 */         new RealControlJDialog30(mainFrame, true);
/* 296:    */       }
/* 297:286 */       break;
/* 298:    */     case 301: 
/* 299:    */       break;
/* 300:    */     case 302: 
/* 301:290 */       new HistoryDataJDialog(mainFrame, true);
/* 302:291 */       break;
/* 303:    */     case 303: 
/* 304:293 */       new HistoryFaultDataDialog(mainFrame, true);
/* 305:294 */       break;
/* 306:    */     case 304: 
/* 307:296 */       new HistoryEventDialog(mainFrame, true);
/* 308:297 */       break;
/* 309:    */     case 305: 
/* 310:299 */       new MonitoredInfoJDialog(mainFrame, true);
/* 311:300 */       break;
/* 312:    */     case 401: 
/* 313:302 */       I18NBundle.changeUS();
/* 314:303 */       updateLanguage("en_US");
/* 315:304 */       break;
/* 316:    */     case 402: 
/* 317:306 */       I18NBundle.changeFR();
/* 318:307 */       updateLanguage("fr_FR");
/* 319:308 */       break;
/* 320:    */     case 403: 
/* 321:310 */       I18NBundle.changeDE();
/* 322:311 */       updateLanguage("de_DE");
/* 323:312 */       break;
/* 324:    */     case 404: 
/* 325:314 */       I18NBundle.changeIT();
/* 326:315 */       updateLanguage("it_IT");
/* 327:316 */       break;
/* 328:    */     case 405: 
/* 329:318 */       I18NBundle.changePL();
/* 330:319 */       updateLanguage("pl_PL");
/* 331:320 */       break;
/* 332:    */     case 406: 
/* 333:322 */       I18NBundle.changePT();
/* 334:323 */       updateLanguage("pt_PT");
/* 335:324 */       break;
/* 336:    */     case 407: 
/* 337:326 */       I18NBundle.changeRUS();
/* 338:327 */       updateLanguage("ru_RU");
/* 339:328 */       break;
/* 340:    */     case 408: 
/* 341:330 */       I18NBundle.changeSP();
/* 342:331 */       updateLanguage("es_ES");
/* 343:332 */       break;
/* 344:    */     case 409: 
/* 345:334 */       I18NBundle.changeUKR();
/* 346:335 */       updateLanguage("uk_UA");
/* 347:336 */       break;
/* 348:    */     case 410: 
/* 349:338 */       I18NBundle.changeTUR();
/* 350:339 */       updateLanguage("tr_TR");
/* 351:340 */       break;
/* 352:    */     case 411: 
/* 353:342 */       I18NBundle.changeCN();
/* 354:343 */       updateLanguage("zh_CN");
/* 355:344 */       break;
/* 356:    */     case 412: 
/* 357:346 */       I18NBundle.changeTW();
/* 358:347 */       updateLanguage("zh_TW");
/* 359:348 */       break;
/* 360:    */     case 501: 
/* 361:350 */       new AboutJDialog(mainFrame, true);
/* 362:351 */       break;
/* 363:    */     case 502: 
/* 364:353 */       CustomerConfig custormerConfig = GlobalVariables.customerConfig;
/* 365:354 */       if (custormerConfig.isEnglish())
/* 366:    */       {
/* 367:355 */         if (protocol.getProtocolID().equals("P30")) {
/* 368:356 */           openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_en_p30");
/* 369:    */         } else {
/* 370:358 */           openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_en_p30");
/* 371:    */         }
/* 372:    */       }
/* 373:360 */       else if (custormerConfig.isTurkish())
/* 374:    */       {
/* 375:361 */         if (protocol.getProtocolID().equals("P30")) {
/* 376:362 */           openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_tr_p30");
/* 377:    */         } else {
/* 378:364 */           openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_tr_p30");
/* 379:    */         }
/* 380:    */       }
/* 381:366 */       else if (custormerConfig.isRussian()) {
/* 382:367 */         if (protocol.getProtocolID().equals("P30")) {
/* 383:368 */           openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_ru_p30");
/* 384:    */         } else {
/* 385:370 */           openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_ru_p30");
/* 386:    */         }
/* 387:    */       }
/* 388:373 */       break;
/* 389:    */     case 5021: 
/* 390:375 */       if (protocol.getProtocolID().equals("P30")) {
/* 391:376 */         openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_en_p30");
/* 392:    */       } else {
/* 393:378 */         openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_en_p30");
/* 394:    */       }
/* 395:380 */       break;
/* 396:    */     case 5022: 
/* 397:382 */       if (protocol.getProtocolID().equals("P30")) {
/* 398:383 */         openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_tr_p30");
/* 399:    */       } else {
/* 400:385 */         openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_tr_p30");
/* 401:    */       }
/* 402:387 */       break;
/* 403:    */     case 5023: 
/* 404:389 */       if (protocol.getProtocolID().equals("P30")) {
/* 405:390 */         openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_ru_p30");
/* 406:    */       } else {
/* 407:392 */         openURL(System.getProperty("user.dir") + File.separator + "help" + File.separator + "help_ru_p30");
/* 408:    */       }
/* 409:394 */       break;
/* 410:    */     }
/* 411:    */   }
/* 412:    */   
/* 413:    */   private void initComponents()
/* 414:    */   {
/* 415:403 */     this.mainPanel = new JPanel();
/* 416:404 */     this.northPanel = new JPanel();
/* 417:405 */     this.statusButton = new AAButtonTip();
/* 418:406 */     this.parameterButton = new AAButtonTip();
/* 419:407 */     this.dataButton = new AAButtonTip();
/* 420:408 */     this.eventButton = new AAButtonTip();
/* 421:    */     
/* 422:410 */     loginButton = new AAButtonTip();
/* 423:411 */     loginLabel = new AALabel();
/* 424:412 */     this.monitoredLabelC = new AALabel();
/* 425:413 */     this.monitoredLabelV = new AALabel();
/* 426:414 */     this.centerPanel = new JPanel();
/* 427:415 */     this.jSplitPane = new JSplitPane();
/* 428:416 */     this.leftScrollPane = new JScrollPane();
/* 429:417 */     this.treeScrollPane = new JScrollPane();
/* 430:418 */     this.treePanel = new JPanel();
/* 431:419 */     this.logoLabel = new JLabel();
/* 432:    */     
/* 433:421 */     this.changePanel = new JPanel();
/* 434:422 */     this.statusPanel = new StatusPanel();
/* 435:423 */     deviceTree = new VPTree();
/* 436:    */     
/* 437:425 */     addWindowListener(new WindowAdapter()
/* 438:    */     {
/* 439:    */       public void windowClosing(WindowEvent e)
/* 440:    */       {
/* 441:428 */         MainJFrame.mainFrame.setVisible(false);
/* 442:    */       }
/* 443:431 */     });
/* 444:432 */     this.mainPanel.setLayout(new BorderLayout());
/* 445:    */     
/* 446:434 */     this.statusButton.setIcon(new ImageIcon(Constants.STATUSIMG));
/* 447:435 */     this.statusButton.setToolTipText("message.baseSet");
/* 448:436 */     this.statusButton.addActionListener(new ActionListener()
/* 449:    */     {
/* 450:    */       public void actionPerformed(ActionEvent e)
/* 451:    */       {
/* 452:439 */         new BasicJDialog(MainJFrame.mainFrame, true);
/* 453:    */       }
/* 454:442 */     });
/* 455:443 */     this.parameterButton.setIcon(new ImageIcon(Constants.PARAMETERIMG));
/* 456:444 */     this.parameterButton.setToolTipText("message.parametersSetting");
/* 457:445 */     this.parameterButton.addActionListener(new ActionListener()
/* 458:    */     {
/* 459:    */       public void actionPerformed(ActionEvent e)
/* 460:    */       {
/* 461:448 */         new ParameterJDialog(MainJFrame.mainFrame, true);
/* 462:    */       }
/* 463:451 */     });
/* 464:452 */     this.dataButton.setIcon(new ImageIcon(Constants.DATAIMG));
/* 465:453 */     this.dataButton.setToolTipText("message.queryData");
/* 466:454 */     this.dataButton.addActionListener(new ActionListener()
/* 467:    */     {
/* 468:    */       public void actionPerformed(ActionEvent e)
/* 469:    */       {
/* 470:457 */         new HistoryDataJDialog(MainJFrame.mainFrame, true);
/* 471:    */       }
/* 472:460 */     });
/* 473:461 */     this.eventButton.setIcon(new ImageIcon(Constants.EVENTIMG));
/* 474:462 */     this.eventButton.setToolTipText("message.queryEvent");
/* 475:463 */     this.eventButton.addActionListener(new ActionListener()
/* 476:    */     {
/* 477:    */       public void actionPerformed(ActionEvent e)
/* 478:    */       {
/* 479:466 */         new HistoryEventDialog(MainJFrame.mainFrame, true);
/* 480:    */       }
/* 481:    */     });
/* 482:479 */     if (SolarPowerTray.isLogin)
/* 483:    */     {
/* 484:480 */       loginButton.setIcon(new ImageIcon(Constants.LOGOUTIMG));
/* 485:481 */       loginButton.setToolTipText("message.logout");
/* 486:482 */       loginLabel.setText("message.localManager");
/* 487:    */     }
/* 488:    */     else
/* 489:    */     {
/* 490:484 */       loginButton.setIcon(new ImageIcon(Constants.LOGINIMG));
/* 491:485 */       loginButton.setToolTipText("message.login");
/* 492:486 */       loginLabel.setText("message.observer");
/* 493:    */     }
/* 494:488 */     loginButton.addActionListener(new ActionListener()
/* 495:    */     {
/* 496:    */       public void actionPerformed(ActionEvent e)
/* 497:    */       {
/* 498:491 */         if (!SolarPowerTray.isLogin)
/* 499:    */         {
/* 500:492 */           new LoginJDialog(MainJFrame.mainFrame, true);
/* 501:    */         }
/* 502:    */         else
/* 503:    */         {
/* 504:494 */           int result = DisplayMessage.showConfirmDialog("message.asklogout", "message.logout");
/* 505:495 */           if (result == 0)
/* 506:    */           {
/* 507:496 */             SolarPowerTray.isLogin = false;
/* 508:497 */             MainJFrame.loginButton.setIcon(new ImageIcon(Constants.LOGINIMG));
/* 509:498 */             MainJFrame.loginButton.setToolTipText("message.login");
/* 510:499 */             MainJFrame.loginLabel.setText("message.observer");
/* 511:    */           }
/* 512:    */         }
/* 513:    */       }
/* 514:504 */     });
/* 515:505 */     this.monitoredLabelC.setText("message.currentMonitor[:]");
/* 516:    */     
/* 517:507 */     this.monitoredLabelV.setText("---");
/* 518:    */     
/* 519:509 */     this.northPanel.setBorder(BorderFactory.createEtchedBorder());
/* 520:    */     
/* 521:511 */     GroupLayout northPanelLayout = new GroupLayout(this.northPanel);
/* 522:512 */     this.northPanel.setLayout(northPanelLayout);
/* 523:513 */     northPanelLayout.setHorizontalGroup(
/* 524:514 */       northPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 525:515 */       .addGroup(northPanelLayout.createSequentialGroup()
/* 526:516 */       .addGap(2, 2, 2)
/* 527:517 */       .addComponent(this.statusButton)
/* 528:518 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 529:519 */       .addComponent(this.parameterButton)
/* 530:520 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 531:521 */       .addComponent(this.dataButton)
/* 532:522 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 533:523 */       .addComponent(this.eventButton)
/* 534:524 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 535:    */       
/* 536:    */ 
/* 537:527 */       .addComponent(loginButton)
/* 538:528 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 539:529 */       .addComponent(loginLabel)
/* 540:530 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 541:531 */       .addComponent(this.monitoredLabelC)
/* 542:532 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 543:533 */       .addComponent(this.monitoredLabelV)
/* 544:534 */       .addContainerGap(56, 32767)));
/* 545:    */     
/* 546:536 */     northPanelLayout.setVerticalGroup(
/* 547:537 */       northPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 548:538 */       .addComponent(this.statusButton)
/* 549:539 */       .addComponent(this.parameterButton)
/* 550:540 */       .addComponent(this.dataButton)
/* 551:541 */       .addComponent(this.eventButton)
/* 552:    */       
/* 553:543 */       .addComponent(loginButton)
/* 554:544 */       .addGroup(northPanelLayout.createSequentialGroup()
/* 555:545 */       .addGap(18, 18, 18)
/* 556:546 */       .addGroup(northPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 557:547 */       .addComponent(loginLabel)
/* 558:548 */       .addComponent(this.monitoredLabelC)
/* 559:549 */       .addComponent(this.monitoredLabelV))));
/* 560:    */     
/* 561:    */ 
/* 562:    */ 
/* 563:553 */     this.mainPanel.add(this.northPanel, "North");
/* 564:    */     
/* 565:555 */     this.centerPanel.setLayout(new BorderLayout());
/* 566:    */     
/* 567:557 */     deviceTree.addMouseListener(new MouseAdapter()
/* 568:    */     {
/* 569:    */       public void mouseClicked(MouseEvent e)
/* 570:    */       {
/* 571:560 */         if (e.getClickCount() == 2) {
/* 572:    */           try
/* 573:    */           {
/* 574:562 */             DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)MainJFrame.deviceTree.getLastSelectedPathComponent();
/* 575:563 */             if (!selectionNode.isRoot())
/* 576:    */             {
/* 577:564 */               Object selectionObj = selectionNode.getUserObject();
/* 578:    */               
/* 579:566 */               String itemString = selectionObj.toString();
/* 580:    */               
/* 581:    */ 
/* 582:569 */               GlobalProcessors.setCurrentProcesserIfExist(itemString);
/* 583:    */               
/* 584:571 */               System.out.println(itemString + " --- " + GlobalProcessors.getCurrentProcessor().processorKey());
/* 585:    */             }
/* 586:    */           }
/* 587:    */           catch (Exception ex)
/* 588:    */           {
/* 589:576 */             ex.printStackTrace();
/* 590:    */           }
/* 591:    */         }
/* 592:    */       }
/* 593:581 */     });
/* 594:582 */     this.treePanel.setLayout(new BorderLayout());
/* 595:    */     
/* 596:584 */     this.treeScrollPane.setViewportView(deviceTree);
/* 597:    */     
/* 598:586 */     this.treePanel.add(this.treeScrollPane, "Center");
/* 599:    */     
/* 600:588 */     ImageIcon icon = null;
/* 601:    */     try
/* 602:    */     {
/* 603:590 */       icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Constants.CUSTOMERIMAGES_PATH + GlobalVariables.customerConfig.getCustomerLogo()));
/* 604:    */     }
/* 605:    */     catch (Exception localException) {}
/* 606:594 */     if (icon != null) {
/* 607:595 */       this.logoLabel.setIcon(icon);
/* 608:    */     }
/* 609:598 */     this.treePanel.add(this.logoLabel, "South");
/* 610:    */     
/* 611:600 */     this.leftScrollPane.setViewportView(this.treePanel);
/* 612:    */     
/* 613:602 */     this.jSplitPane.setLeftComponent(this.leftScrollPane);
/* 614:    */     
/* 615:604 */     this.changePanel.setLayout(new BorderLayout());
/* 616:    */     
/* 617:606 */     this.changePanel.add(this.statusPanel, "Center");
/* 618:    */     
/* 619:    */ 
/* 620:    */ 
/* 621:610 */     this.jSplitPane.setRightComponent(this.changePanel);
/* 622:    */     
/* 623:612 */     this.jSplitPane.setDividerLocation(250);
/* 624:    */     
/* 625:614 */     this.centerPanel.add(this.jSplitPane, "Center");
/* 626:    */     
/* 627:616 */     this.mainPanel.add(this.centerPanel, "Center");
/* 628:    */     
/* 629:618 */     getContentPane().add(this.mainPanel, "Center");
/* 630:    */     
/* 631:620 */     pack();
/* 632:    */   }
/* 633:    */   
/* 634:    */   private void openURL(String url)
/* 635:    */   {
/* 636:    */     try
/* 637:    */     {
/* 638:625 */       File file1 = new File(url + ".swf");
/* 639:626 */       File file2 = new File(url + ".pdf");
/* 640:627 */       File file3 = new File(url + ".doc");
/* 641:628 */       if (file1.exists())
/* 642:    */       {
/* 643:629 */         if (Constants.IS_OS_WINDOWS) {
/* 644:630 */           Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file1);
/* 645:    */         } else {
/* 646:632 */           Runtime.getRuntime().exec(file1.getAbsolutePath());
/* 647:    */         }
/* 648:    */       }
/* 649:634 */       else if (file2.exists())
/* 650:    */       {
/* 651:635 */         if (Constants.IS_OS_WINDOWS)
/* 652:    */         {
/* 653:636 */           Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file2);
/* 654:    */         }
/* 655:637 */         else if (Constants.IS_OS_LINUX)
/* 656:    */         {
/* 657:638 */           Runtime.getRuntime().exec("chmod 777 " + url + ".pdf");
/* 658:639 */           Runtime.getRuntime().exec("evince " + url + ".pdf");
/* 659:    */         }
/* 660:640 */         else if (Constants.IS_OS_SOLARIS)
/* 661:    */         {
/* 662:641 */           Runtime.getRuntime().exec("chmod 777 " + url + ".pdf");
/* 663:642 */           Runtime.getRuntime().exec("gpdf " + url + ".pdf");
/* 664:    */         }
/* 665:643 */         else if ((Constants.IS_OS_MAC) || (Constants.IS_OS_MAC_OSX))
/* 666:    */         {
/* 667:644 */           Runtime.getRuntime().exec("chmod 777 " + url + ".pdf");
/* 668:645 */           Runtime.getRuntime().exec("open " + url + ".pdf");
/* 669:    */         }
/* 670:    */         else
/* 671:    */         {
/* 672:647 */           Runtime.getRuntime().exec(file2.getAbsolutePath());
/* 673:    */         }
/* 674:    */       }
/* 675:649 */       else if (file3.exists())
/* 676:    */       {
/* 677:650 */         if (Constants.IS_OS_WINDOWS) {
/* 678:651 */           Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file3);
/* 679:    */         } else {
/* 680:653 */           Runtime.getRuntime().exec(file3.getAbsolutePath());
/* 681:    */         }
/* 682:    */       }
/* 683:    */       else {
/* 684:657 */         DisplayMessage.showErrorDialog("File is missing!");
/* 685:    */       }
/* 686:    */     }
/* 687:    */     catch (Exception ex)
/* 688:    */     {
/* 689:661 */       DisplayMessage.showErrorDialog("Please install the PDF reader.");
/* 690:    */     }
/* 691:    */   }
/* 692:    */   
/* 693:    */   private void updateLanguage(String language)
/* 694:    */   {
/* 695:    */     try
/* 696:    */     {
/* 697:667 */       GlobalVariables.properties.setProperty("GLOBAL_CONFIG", "Language", language);
/* 698:668 */       GlobalVariables.properties.store();
/* 699:669 */       ConfigureTools.wrapProperties(GlobalVariables.globalConfig);
/* 700:    */     }
/* 701:    */     catch (Exception e)
/* 702:    */     {
/* 703:671 */       e.printStackTrace();
/* 704:    */     }
/* 705:    */   }
/* 706:    */   
/* 707:    */   public void refreshDeviceTree()
/* 708:    */   {
/* 709:676 */     List<String> devices = GlobalProcessors.getSolarList();
/* 710:677 */     deviceTree.refreshTree(devices);
/* 711:678 */     SwingUtilities.invokeLater(new Runnable()
/* 712:    */     {
/* 713:    */       public void run()
/* 714:    */       {
/* 715:682 */         MainJFrame.deviceTree.updateUI();
/* 716:    */       }
/* 717:    */     });
/* 718:    */   }
/* 719:    */   
/* 720:    */   public void refreshWorkInfo()
/* 721:    */   {
/* 722:689 */     AbstractProcessor processer = GlobalProcessors.getCurrentProcessor();
/* 723:690 */     MachineInfo machineInfo = null;
/* 724:691 */     WorkInfo workInfo = null;
/* 725:692 */     if (processer != null)
/* 726:    */     {
/* 727:693 */       machineInfo = (MachineInfo)processer.getBeanBag().getBean("machineinfo");
/* 728:694 */       workInfo = (WorkInfo)processer.getBeanBag().getBean("workinfo");
/* 729:    */     }
/* 730:696 */     if (machineInfo == null) {
/* 731:697 */       machineInfo = new MachineInfo();
/* 732:    */     }
/* 733:699 */     if (workInfo == null) {
/* 734:700 */       workInfo = new WorkInfo();
/* 735:    */     }
/* 736:702 */     refreshMachineInfo(machineInfo);
/* 737:703 */     refreshWorkInfo(workInfo);
/* 738:704 */     refreshReal(workInfo);
/* 739:    */   }
/* 740:    */   
/* 741:707 */   private IProtocol oldProtocol = new P30();
/* 742:    */   
/* 743:    */   public void refreshWorkInfo(WorkInfo workInfo)
/* 744:    */   {
/* 745:709 */     this.currProcesser = GlobalProcessors.getCurrentProcessor();
/* 746:710 */     if (this.currProcesser != null)
/* 747:    */     {
/* 748:711 */       IProtocol protocol = this.currProcesser.getProtocol();
/* 749:712 */       if ((!protocol.getDelayChanging()) && (
/* 750:713 */         (!this.oldProtocol.getProtocolID().equals(protocol.getProtocolID())) || (protocol.getOutputMode() != this.oldProtocol.getOutputMode())))
/* 751:    */       {
/* 752:714 */         if (this.statusPanel.basePanel != null)
/* 753:    */         {
/* 754:715 */           this.statusPanel.baseJSPanel.remove(this.statusPanel.basePanel);
/* 755:716 */           this.statusPanel.basePanel = null;
/* 756:    */         }
/* 757:718 */         BasePanel basePanel = new BasePanel();
/* 758:719 */         this.statusPanel.basePanel = basePanel;
/* 759:720 */         this.statusPanel.baseJSPanel.setViewportView(this.statusPanel.basePanel);
/* 760:721 */         if (!this.oldProtocol.getProtocolID().equals(protocol.getProtocolID())) {
/* 761:722 */           initMenu();
/* 762:    */         }
/* 763:725 */         this.oldProtocol = protocol;
/* 764:    */       }
/* 765:729 */       if (this.statusPanel.basePanel != null) {
/* 766:730 */         this.statusPanel.basePanel.refreshWorkInfo(workInfo);
/* 767:    */       }
/* 768:    */     }
/* 769:733 */     if (this.currProcesser != null)
/* 770:    */     {
/* 771:734 */       String deviceName = this.currProcesser.getDeviceName();
/* 772:735 */       String serailNo = this.currProcesser.getSerialNo();
/* 773:736 */       if ((deviceName != null) && (!"".equals(deviceName)) && (serailNo != null) && (!"".equals(serailNo))) {
/* 774:737 */         this.monitoredLabelV.setText(deviceName + "_" + serailNo);
/* 775:    */       }
/* 776:    */     }
/* 777:    */     else
/* 778:    */     {
/* 779:740 */       this.monitoredLabelV.setText("---");
/* 780:    */     }
/* 781:    */   }
/* 782:    */   
/* 783:    */   public void refreshReal(WorkInfo workInfo)
/* 784:    */   {
/* 785:745 */     this.statusPanel.realPanel.refreshWork(workInfo);
/* 786:    */   }
/* 787:    */   
/* 788:    */   public void refreshMachineInfo(MachineInfo machineInfo)
/* 789:    */   {
/* 790:749 */     this.statusPanel.refreshMachineInfo(machineInfo);
/* 791:    */   }
/* 792:    */   
/* 793:    */   public void setDefaultValues()
/* 794:    */   {
/* 795:754 */     this.oldProtocol = new P30();
/* 796:755 */     initMenu();
/* 797:756 */     this.statusPanel.setDefaultValues();
/* 798:    */     
/* 799:758 */     this.statusPanel.baseJSPanel.remove(this.statusPanel.basePanel);
/* 800:759 */     this.statusPanel.basePanel = null;
/* 801:760 */     BasePanel basePanel = new BasePanel();
/* 802:761 */     this.statusPanel.basePanel = basePanel;
/* 803:762 */     this.statusPanel.baseJSPanel.setViewportView(this.statusPanel.basePanel);
/* 804:    */     
/* 805:764 */     this.monitoredLabelV.setText("---");
/* 806:    */     
/* 807:766 */     this.statusPanel.realPanel.setDefaultValues();
/* 808:    */     
/* 809:768 */     this.statusPanel.basePanel.setDefaultValues();
/* 810:    */   }
/* 811:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.MainJFrame
 * JD-Core Version:    0.7.0.1
 */