/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.EmailConfig;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.configure.SmsConfig;
/*   6:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   7:    */ import cn.com.voltronic.solar.dao.EventCfgDao;
/*   8:    */ import cn.com.voltronic.solar.data.bean.EventData;
/*   9:    */ import cn.com.voltronic.solar.data.bean.Eventcfg;
/*  10:    */ import cn.com.voltronic.solar.exception.EventsHandler;
/*  11:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  12:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  13:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  14:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  15:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  16:    */ import cn.com.voltronic.solar.view.component.AACheckBox;
/*  17:    */ import cn.com.voltronic.solar.view.component.AADefaultTableModel;
/*  18:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  19:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  20:    */ import cn.com.voltronic.solar.view.component.CheckBoxData;
/*  21:    */ import cn.com.voltronic.solar.view.component.CheckListCellRenderer;
/*  22:    */ import cn.com.voltronic.solar.view.component.CheckListener;
/*  23:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  24:    */ import java.awt.Color;
/*  25:    */ import java.awt.Container;
/*  26:    */ import java.awt.Frame;
/*  27:    */ import java.awt.event.ActionEvent;
/*  28:    */ import java.awt.event.ActionListener;
/*  29:    */ import java.awt.event.MouseAdapter;
/*  30:    */ import java.awt.event.MouseEvent;
/*  31:    */ import java.util.ArrayList;
/*  32:    */ import java.util.List;
/*  33:    */ import javax.swing.GroupLayout;
/*  34:    */ import javax.swing.GroupLayout.Alignment;
/*  35:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  36:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  37:    */ import javax.swing.JList;
/*  38:    */ import javax.swing.JPanel;
/*  39:    */ import javax.swing.JScrollPane;
/*  40:    */ import javax.swing.JSplitPane;
/*  41:    */ import javax.swing.JTable;
/*  42:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  43:    */ import javax.swing.ListModel;
/*  44:    */ import javax.swing.table.JTableHeader;
/*  45:    */ import javax.swing.table.TableColumn;
/*  46:    */ import javax.swing.table.TableColumnModel;
/*  47:    */ import org.dom4j.Element;
/*  48:    */ 
/*  49:    */ public class EventActionDialog
/*  50:    */   extends AADialog
/*  51:    */ {
/*  52:    */   private static final long serialVersionUID = -7387470322905229037L;
/*  53:    */   private AAButton jButton1;
/*  54:    */   private AAButton jButton2;
/*  55:    */   private AACheckBox jCheckBox1;
/*  56:    */   private AACheckBox jCheckBox2;
/*  57:    */   private AALabel jLabel1;
/*  58:    */   private AALabel jLabel2;
/*  59:    */   private AALabel jLabel3;
/*  60:    */   private JList jList1;
/*  61:    */   private JList jList2;
/*  62:    */   private JPanel jPanel1;
/*  63:    */   private JScrollPane jScrollPane1;
/*  64:    */   private JScrollPane jScrollPane2;
/*  65:    */   private JScrollPane jScrollPane3;
/*  66:    */   private JScrollPane jScrollPane4;
/*  67:    */   private JSplitPane jSplitPane1;
/*  68:    */   private JTable jTable1;
/*  69: 72 */   private AADefaultTableModel tableModel = null;
/*  70: 73 */   private CheckListCellRenderer jList1Renderer = null;
/*  71: 74 */   private CheckListCellRenderer jList2Renderer = null;
/*  72: 76 */   private final int MAX_SIZE = 25;
/*  73: 77 */   private final int MAX_COLUMN = 3;
/*  74: 78 */   private String[] tableTile = { "ID", "message.level", "message.eventName" };
/*  75: 79 */   private List<EventData> eventlist = new ArrayList();
/*  76:    */   private String saveEventId;
/*  77:    */   
/*  78:    */   public EventActionDialog(Frame parent, boolean modal)
/*  79:    */   {
/*  80: 83 */     super(parent, modal);
/*  81: 84 */     initComponents();
/*  82: 85 */     queryEventData();
/*  83: 86 */     setTitle("message.electricEventSet");
/*  84: 87 */     setLocationRelativeTo(null);
/*  85: 88 */     setVisible(true);
/*  86:    */   }
/*  87:    */   
/*  88:    */   private void queryEventData()
/*  89:    */   {
/*  90: 92 */     String language = "en_US";
/*  91:    */     try
/*  92:    */     {
/*  93: 94 */       language = GlobalVariables.globalConfig.getLanguage();
/*  94:    */     }
/*  95:    */     catch (Exception ex)
/*  96:    */     {
/*  97: 96 */       ex.printStackTrace();
/*  98:    */     }
/*  99: 98 */     List<Element> list = null;
/* 100: 99 */     String protocol = "P30";
/* 101:    */     try
/* 102:    */     {
/* 103:101 */       protocol = GlobalProcessors.getCurrentProcessor().getProtocol().getProtocolID();
/* 104:    */     }
/* 105:    */     catch (Exception localException1) {}
/* 106:105 */     if ((protocol == null) || (protocol.equals(""))) {
/* 107:106 */       protocol = "P30";
/* 108:    */     }
/* 109:108 */     if (protocol.equals("P30")) {
/* 110:109 */       list = EventsHandler.getP30AllEvent();
/* 111:    */     } else {
/* 112:111 */       list = EventsHandler.getP30AllEvent();
/* 113:    */     }
/* 114:113 */     EventData data = null;
/* 115:114 */     for (Element element : list)
/* 116:    */     {
/* 117:115 */       data = new EventData();
/* 118:116 */       String id = element.attributeValue("id");
/* 119:117 */       int level = Integer.parseInt(element.attributeValue("level"));
/* 120:118 */       String name = null;
/* 121:119 */       if (language.equals("zh_CN")) {
/* 122:120 */         name = element.attributeValue("name");
/* 123:121 */       } else if (language.equals("fr_FR")) {
/* 124:122 */         name = element.attributeValue("frName");
/* 125:123 */       } else if (language.equals("de_DE")) {
/* 126:124 */         name = element.attributeValue("deName");
/* 127:125 */       } else if (language.equals("it_IT")) {
/* 128:126 */         name = element.attributeValue("itName");
/* 129:127 */       } else if (language.equals("pl_PL")) {
/* 130:128 */         name = element.attributeValue("plName");
/* 131:129 */       } else if (language.equals("pt_PT")) {
/* 132:130 */         name = element.attributeValue("ptName");
/* 133:131 */       } else if (language.equals("ru_RU")) {
/* 134:132 */         name = element.attributeValue("rusName");
/* 135:133 */       } else if (language.equals("es_ES")) {
/* 136:134 */         name = element.attributeValue("spName");
/* 137:135 */       } else if (language.equals("uk_UA")) {
/* 138:136 */         name = element.attributeValue("ukrName");
/* 139:137 */       } else if (language.equals("tr_TR")) {
/* 140:138 */         name = element.attributeValue("turName");
/* 141:139 */       } else if (language.equals("zh_TW")) {
/* 142:140 */         name = element.attributeValue("twName");
/* 143:    */       } else {
/* 144:142 */         name = element.attributeValue("enName");
/* 145:    */       }
/* 146:144 */       data.setEventId(id);
/* 147:145 */       data.setEventLevel(level);
/* 148:146 */       data.setEventName(name);
/* 149:147 */       this.eventlist.add(data);
/* 150:    */     }
/* 151:149 */     int size = this.eventlist.size();
/* 152:150 */     if (size < 25) {
/* 153:151 */       size = 25;
/* 154:    */     }
/* 155:153 */     Object[][] obj = new Object[size][3];
/* 156:154 */     for (int i = 0; i < this.eventlist.size(); i++)
/* 157:    */     {
/* 158:155 */       EventData eventData = (EventData)this.eventlist.get(i);
/* 159:156 */       int level = eventData.getEventLevel();
/* 160:157 */       String levelStr = "";
/* 161:158 */       if (level == 1) {
/* 162:159 */         levelStr = "message.fault";
/* 163:160 */       } else if (level == 2) {
/* 164:161 */         levelStr = "message.warning";
/* 165:162 */       } else if (level == 3) {
/* 166:163 */         levelStr = "message.info";
/* 167:    */       }
/* 168:165 */       obj[i][0] = eventData.getEventId();
/* 169:166 */       obj[i][1] = levelStr;
/* 170:167 */       obj[i][2] = eventData.getEventName();
/* 171:    */     }
/* 172:169 */     if (this.eventlist.size() < 25) {
/* 173:170 */       for (int i = this.eventlist.size(); i < 25; i++) {
/* 174:171 */         for (int j = 0; j < 3; j++) {
/* 175:172 */           obj[i][j] = null;
/* 176:    */         }
/* 177:    */       }
/* 178:    */     }
/* 179:176 */     this.tableModel.setDataVector(obj, this.tableTile);
/* 180:177 */     setTableFormate();
/* 181:178 */     this.jSplitPane1.setDividerLocation(0.5D);
/* 182:    */   }
/* 183:    */   
/* 184:    */   private void initComponents()
/* 185:    */   {
/* 186:183 */     this.jSplitPane1 = new JSplitPane();
/* 187:184 */     this.jScrollPane1 = new JScrollPane();
/* 188:185 */     this.jScrollPane4 = new JScrollPane();
/* 189:186 */     this.jPanel1 = new JPanel();
/* 190:187 */     this.jLabel1 = new AALabel();
/* 191:188 */     this.jCheckBox1 = new AACheckBox();
/* 192:189 */     this.jCheckBox2 = new AACheckBox();
/* 193:190 */     this.jLabel2 = new AALabel();
/* 194:191 */     this.jScrollPane2 = new JScrollPane();
/* 195:192 */     this.jLabel3 = new AALabel();
/* 196:193 */     this.jScrollPane3 = new JScrollPane();
/* 197:194 */     this.jButton1 = new AAButton();
/* 198:195 */     this.jButton2 = new AAButton();
/* 199:    */     
/* 200:197 */     EmailConfig emailInfo = GlobalVariables.emailConfig;
/* 201:198 */     String recievers = emailInfo.getRecievers().trim();
/* 202:199 */     String[] reArr = new String[0];
/* 203:200 */     if (!"".equalsIgnoreCase(recievers)) {
/* 204:201 */       reArr = recievers.split(";");
/* 205:    */     }
/* 206:203 */     CheckBoxData[] datas = new CheckBoxData[reArr.length];
/* 207:204 */     for (int i = 0; i < reArr.length; i++) {
/* 208:205 */       datas[i] = new CheckBoxData(reArr[i], false, true);
/* 209:    */     }
/* 210:207 */     this.jList1 = new JList(datas);
/* 211:208 */     this.jList1Renderer = new CheckListCellRenderer();
/* 212:209 */     this.jList1Renderer.setEnabled(false);
/* 213:210 */     this.jList1.setCellRenderer(this.jList1Renderer);
/* 214:211 */     this.jList1.setSelectionMode(0);
/* 215:212 */     CheckListener lst = new CheckListener(this.jList1);
/* 216:213 */     this.jList1.addMouseListener(lst);
/* 217:214 */     this.jList1.addKeyListener(lst);
/* 218:215 */     this.jList1.setEnabled(false);
/* 219:    */     
/* 220:217 */     SmsConfig smsInfo = GlobalVariables.smsConfig;
/* 221:218 */     if (smsInfo == null) {
/* 222:219 */       smsInfo = new SmsConfig();
/* 223:    */     }
/* 224:221 */     String mobileNum = smsInfo.getMobileNums().trim();
/* 225:222 */     String[] mnArr = new String[0];
/* 226:223 */     if (!"".equalsIgnoreCase(mobileNum)) {
/* 227:224 */       mnArr = mobileNum.split(";");
/* 228:    */     }
/* 229:226 */     CheckBoxData[] datas2 = new CheckBoxData[mnArr.length];
/* 230:227 */     for (int i = 0; i < mnArr.length; i++) {
/* 231:228 */       datas2[i] = new CheckBoxData(mnArr[i], false, true);
/* 232:    */     }
/* 233:230 */     this.jList2 = new JList(datas2);
/* 234:231 */     this.jList2Renderer = new CheckListCellRenderer();
/* 235:232 */     this.jList2Renderer.setEnabled(false);
/* 236:233 */     this.jList2.setCellRenderer(this.jList2Renderer);
/* 237:234 */     this.jList2.setSelectionMode(0);
/* 238:235 */     CheckListener lst2 = new CheckListener(this.jList2);
/* 239:236 */     this.jList2.addMouseListener(lst2);
/* 240:237 */     this.jList2.addKeyListener(lst2);
/* 241:238 */     this.jList2.setEnabled(false);
/* 242:    */     
/* 243:240 */     this.jTable1 = new JTable()
/* 244:    */     {
/* 245:    */       private static final long serialVersionUID = -2097364819095589911L;
/* 246:    */       
/* 247:    */       public boolean isCellSelected(int row, int column)
/* 248:    */       {
/* 249:244 */         if (row >= EventActionDialog.this.eventlist.size()) {
/* 250:245 */           return false;
/* 251:    */         }
/* 252:247 */         return super.isCellSelected(row, column);
/* 253:    */       }
/* 254:250 */     };
/* 255:251 */     this.jTable1.getTableHeader().setReorderingAllowed(false);
/* 256:252 */     this.jTable1.getTableHeader().setForeground(Color.white);
/* 257:253 */     this.jTable1.setSelectionMode(0);
/* 258:    */     
/* 259:255 */     setDefaultCloseOperation(2);
/* 260:    */     
/* 261:257 */     this.tableModel = new AADefaultTableModel(new Object[25][3], this.tableTile)
/* 262:    */     {
/* 263:    */       private static final long serialVersionUID = -6182498251644323805L;
/* 264:    */       
/* 265:    */       public boolean isCellEditable(int rowIndex, int columnIndex)
/* 266:    */       {
/* 267:260 */         return false;
/* 268:    */       }
/* 269:262 */     };
/* 270:263 */     this.jTable1.setModel(this.tableModel);
/* 271:264 */     this.jTable1.addMouseListener(new MouseAdapter()
/* 272:    */     {
/* 273:    */       public void mouseClicked(MouseEvent e)
/* 274:    */       {
/* 275:267 */         EventActionDialog.this.mouseClickedTable(e);
/* 276:    */       }
/* 277:270 */     });
/* 278:271 */     setTableFormate();
/* 279:    */     
/* 280:273 */     this.jScrollPane1.setViewportView(this.jTable1);
/* 281:    */     
/* 282:275 */     this.jSplitPane1.setLeftComponent(this.jScrollPane1);
/* 283:    */     
/* 284:277 */     this.jLabel1.setText("message.response[:]");
/* 285:    */     
/* 286:279 */     this.jCheckBox1.setText("message.recordMethod");
/* 287:280 */     this.jCheckBox1.setEnabled(false);
/* 288:281 */     this.jCheckBox1.addActionListener(new ActionListener()
/* 289:    */     {
/* 290:    */       public void actionPerformed(ActionEvent e)
/* 291:    */       {
/* 292:284 */         EventActionDialog.this.jButton2.setEnabled(true);
/* 293:    */       }
/* 294:287 */     });
/* 295:288 */     this.jCheckBox2.setText("message.warningMessage");
/* 296:289 */     this.jCheckBox2.setEnabled(false);
/* 297:290 */     this.jCheckBox2.addActionListener(new ActionListener()
/* 298:    */     {
/* 299:    */       public void actionPerformed(ActionEvent e)
/* 300:    */       {
/* 301:293 */         EventActionDialog.this.jButton2.setEnabled(true);
/* 302:    */       }
/* 303:296 */     });
/* 304:297 */     this.jList1.addMouseListener(new MouseAdapter()
/* 305:    */     {
/* 306:    */       public void mouseClicked(MouseEvent e)
/* 307:    */       {
/* 308:300 */         if (EventActionDialog.this.jList1Renderer.isEnabled()) {
/* 309:301 */           EventActionDialog.this.jButton2.setEnabled(true);
/* 310:    */         }
/* 311:    */       }
/* 312:305 */     });
/* 313:306 */     this.jList2.addMouseListener(new MouseAdapter()
/* 314:    */     {
/* 315:    */       public void mouseClicked(MouseEvent e)
/* 316:    */       {
/* 317:309 */         if (EventActionDialog.this.jList2Renderer.isEnabled()) {
/* 318:310 */           EventActionDialog.this.jButton2.setEnabled(true);
/* 319:    */         }
/* 320:    */       }
/* 321:314 */     });
/* 322:315 */     this.jLabel2.setText("message.receivemail");
/* 323:    */     
/* 324:317 */     this.jScrollPane2.setViewportView(this.jList1);
/* 325:    */     
/* 326:319 */     this.jLabel3.setText("message.receivesms");
/* 327:    */     
/* 328:321 */     this.jScrollPane3.setViewportView(this.jList2);
/* 329:    */     
/* 330:323 */     this.jButton1.setText("message.close");
/* 331:324 */     this.jButton1.addActionListener(new ActionListener()
/* 332:    */     {
/* 333:    */       public void actionPerformed(ActionEvent e)
/* 334:    */       {
/* 335:327 */         EventActionDialog.this.dispose();
/* 336:    */       }
/* 337:330 */     });
/* 338:331 */     this.jButton2.setText("message.apply");
/* 339:332 */     this.jButton2.setEnabled(false);
/* 340:333 */     this.jButton2.addActionListener(new ActionListener()
/* 341:    */     {
/* 342:    */       public void actionPerformed(ActionEvent e)
/* 343:    */       {
/* 344:336 */         if (!SolarPowerTray.isLogin)
/* 345:    */         {
/* 346:337 */           new LoginJDialog(new Frame(), true);
/* 347:338 */           return;
/* 348:    */         }
/* 349:340 */         EventActionDialog.this.saveEventAction(e);
/* 350:    */       }
/* 351:343 */     });
/* 352:344 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 353:345 */     this.jPanel1.setLayout(jPanel1Layout);
/* 354:346 */     jPanel1Layout.setHorizontalGroup(
/* 355:347 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 356:348 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 357:349 */       .addContainerGap()
/* 358:350 */       .addComponent(this.jLabel1)
/* 359:351 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 360:352 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 361:353 */       .addComponent(this.jScrollPane3, GroupLayout.Alignment.LEADING)
/* 362:354 */       .addComponent(this.jLabel3, GroupLayout.Alignment.LEADING)
/* 363:355 */       .addComponent(this.jScrollPane2, GroupLayout.Alignment.LEADING, -1, 210, 32767)
/* 364:356 */       .addComponent(this.jLabel2, GroupLayout.Alignment.LEADING)
/* 365:357 */       .addComponent(this.jCheckBox2, GroupLayout.Alignment.LEADING)
/* 366:358 */       .addComponent(this.jCheckBox1, GroupLayout.Alignment.LEADING))
/* 367:359 */       .addContainerGap(77, 32767))
/* 368:360 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 369:361 */       jPanel1Layout.createSequentialGroup().addContainerGap(206, 32767)
/* 370:362 */       .addComponent(this.jButton2)
/* 371:363 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 372:364 */       .addComponent(this.jButton1)
/* 373:365 */       .addContainerGap()));
/* 374:    */     
/* 375:367 */     jPanel1Layout.setVerticalGroup(
/* 376:368 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 377:369 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 378:370 */       .addGap(31, 31, 31)
/* 379:371 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 380:372 */       .addComponent(this.jLabel1)
/* 381:373 */       .addComponent(this.jCheckBox1))
/* 382:374 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 383:375 */       .addComponent(this.jCheckBox2)
/* 384:376 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 385:377 */       .addComponent(this.jLabel2)
/* 386:378 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 387:379 */       .addComponent(this.jScrollPane2, -2, 90, -2)
/* 388:380 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 389:381 */       .addComponent(this.jLabel3)
/* 390:382 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 391:383 */       .addComponent(this.jScrollPane3, -2, 90, -2)
/* 392:384 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 58, 32767)
/* 393:385 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 394:386 */       .addComponent(this.jButton2)
/* 395:387 */       .addComponent(this.jButton1))
/* 396:388 */       .addGap(19, 19, 19)));
/* 397:    */     
/* 398:    */ 
/* 399:391 */     this.jScrollPane4.setViewportView(this.jPanel1);
/* 400:    */     
/* 401:393 */     this.jSplitPane1.setRightComponent(this.jScrollPane4);
/* 402:    */     
/* 403:395 */     getContentPane().add(this.jSplitPane1, "Center");
/* 404:    */     
/* 405:397 */     pack();
/* 406:    */   }
/* 407:    */   
/* 408:    */   public void mouseClickedTable(MouseEvent e)
/* 409:    */   {
/* 410:401 */     int row = this.jTable1.getSelectedRow();
/* 411:402 */     Object obj = this.jTable1.getValueAt(row, 0);
/* 412:403 */     if (obj != null)
/* 413:    */     {
/* 414:404 */       this.jCheckBox1.setEnabled(true);
/* 415:405 */       this.jCheckBox2.setEnabled(true);
/* 416:406 */       this.jList1.setEnabled(true);
/* 417:407 */       this.jList1Renderer.setEnabled(true);
/* 418:408 */       this.jList2.setEnabled(true);
/* 419:409 */       this.jList2Renderer.setEnabled(true);
/* 420:410 */       this.jButton2.setEnabled(true);
/* 421:    */       
/* 422:412 */       this.jCheckBox1.setSelected(false);
/* 423:413 */       this.jCheckBox2.setSelected(false);
/* 424:414 */       CheckBoxData[] listDataDefault = new CheckBoxData[this.jList1.getModel().getSize()];
/* 425:415 */       for (int j = 0; j < this.jList1.getModel().getSize(); j++)
/* 426:    */       {
/* 427:416 */         CheckBoxData checkBoxData = (CheckBoxData)this.jList1.getModel().getElementAt(j);
/* 428:417 */         checkBoxData.setSelected(false);
/* 429:418 */         listDataDefault[j] = checkBoxData;
/* 430:    */       }
/* 431:420 */       this.jList1.setListData(listDataDefault);
/* 432:421 */       CheckBoxData[] listDataDefault2 = new CheckBoxData[this.jList2.getModel().getSize()];
/* 433:422 */       for (int j = 0; j < this.jList2.getModel().getSize(); j++)
/* 434:    */       {
/* 435:423 */         CheckBoxData checkBoxData = (CheckBoxData)this.jList2.getModel().getElementAt(j);
/* 436:424 */         checkBoxData.setSelected(false);
/* 437:425 */         listDataDefault2[j] = checkBoxData;
/* 438:    */       }
/* 439:427 */       this.jList2.setListData(listDataDefault2);
/* 440:    */       
/* 441:429 */       this.saveEventId = obj.toString().trim();
/* 442:430 */       EventCfgDao eventCfgDao = new EventCfgDao();
/* 443:431 */       List<Eventcfg> eventCfgList = eventCfgDao.queryEventcfg(this.saveEventId);
/* 444:432 */       for (int i = 0; i < eventCfgList.size(); i++)
/* 445:    */       {
/* 446:433 */         Eventcfg cfg = (Eventcfg)eventCfgList.get(i);
/* 447:434 */         if (cfg.getAction() == 1)
/* 448:    */         {
/* 449:435 */           this.jCheckBox1.setSelected(true);
/* 450:    */         }
/* 451:436 */         else if (cfg.getAction() == 2)
/* 452:    */         {
/* 453:437 */           this.jCheckBox2.setSelected(true);
/* 454:    */         }
/* 455:438 */         else if (cfg.getAction() == 3)
/* 456:    */         {
/* 457:439 */           CheckBoxData[] listData = new CheckBoxData[this.jList1.getModel().getSize()];
/* 458:440 */           for (int j = 0; j < this.jList1.getModel().getSize(); j++)
/* 459:    */           {
/* 460:441 */             CheckBoxData checkBoxData = (CheckBoxData)this.jList1.getModel().getElementAt(j);
/* 461:442 */             if (cfg.getReceive().equals(checkBoxData.getName())) {
/* 462:443 */               checkBoxData.setSelected(true);
/* 463:    */             }
/* 464:445 */             listData[j] = checkBoxData;
/* 465:    */           }
/* 466:447 */           this.jList1.setListData(listData);
/* 467:    */         }
/* 468:448 */         else if (cfg.getAction() == 4)
/* 469:    */         {
/* 470:449 */           CheckBoxData[] listData = new CheckBoxData[this.jList2.getModel().getSize()];
/* 471:450 */           for (int j = 0; j < this.jList2.getModel().getSize(); j++)
/* 472:    */           {
/* 473:451 */             CheckBoxData checkBoxData = (CheckBoxData)this.jList2.getModel().getElementAt(j);
/* 474:452 */             if (cfg.getReceive().equals(checkBoxData.getName())) {
/* 475:453 */               checkBoxData.setSelected(true);
/* 476:    */             }
/* 477:455 */             listData[j] = checkBoxData;
/* 478:    */           }
/* 479:457 */           this.jList2.setListData(listData);
/* 480:    */         }
/* 481:    */       }
/* 482:    */     }
/* 483:    */   }
/* 484:    */   
/* 485:    */   public void saveEventAction(ActionEvent e)
/* 486:    */   {
/* 487:464 */     if ((this.saveEventId != null) && (!"".equalsIgnoreCase(this.saveEventId)))
/* 488:    */     {
/* 489:465 */       this.jButton2.setEnabled(false);
/* 490:466 */       EventCfgDao eventCfgDao = new EventCfgDao();
/* 491:467 */       eventCfgDao.removeEventcfg(this.saveEventId);
/* 492:468 */       if (this.jCheckBox1.isSelected())
/* 493:    */       {
/* 494:469 */         Eventcfg eventCfg = new Eventcfg();
/* 495:470 */         eventCfg.setAction(1);
/* 496:471 */         eventCfg.setEventid(this.saveEventId);
/* 497:472 */         eventCfg.setReceive(null);
/* 498:473 */         eventCfgDao.insertCfg(eventCfg);
/* 499:    */       }
/* 500:475 */       if (this.jCheckBox2.isSelected())
/* 501:    */       {
/* 502:476 */         Eventcfg eventCfg = new Eventcfg();
/* 503:477 */         eventCfg.setAction(2);
/* 504:478 */         eventCfg.setEventid(this.saveEventId);
/* 505:479 */         eventCfg.setReceive(null);
/* 506:480 */         eventCfgDao.insertCfg(eventCfg);
/* 507:    */       }
/* 508:482 */       for (int i = 0; i < this.jList1.getModel().getSize(); i++)
/* 509:    */       {
/* 510:483 */         CheckBoxData checkBoxData = (CheckBoxData)this.jList1.getModel().getElementAt(i);
/* 511:484 */         if (checkBoxData.isSelected())
/* 512:    */         {
/* 513:485 */           Eventcfg eventCfg = new Eventcfg();
/* 514:486 */           eventCfg.setAction(3);
/* 515:487 */           eventCfg.setEventid(this.saveEventId);
/* 516:488 */           eventCfg.setReceive(checkBoxData.getName());
/* 517:489 */           eventCfgDao.insertCfg(eventCfg);
/* 518:    */         }
/* 519:    */       }
/* 520:492 */       for (int j = 0; j < this.jList2.getModel().getSize(); j++)
/* 521:    */       {
/* 522:493 */         CheckBoxData checkBoxData = (CheckBoxData)this.jList2.getModel().getElementAt(j);
/* 523:494 */         if (checkBoxData.isSelected())
/* 524:    */         {
/* 525:495 */           Eventcfg eventCfg = new Eventcfg();
/* 526:496 */           eventCfg.setAction(4);
/* 527:497 */           eventCfg.setEventid(this.saveEventId);
/* 528:498 */           eventCfg.setReceive(checkBoxData.getName());
/* 529:499 */           eventCfgDao.insertCfg(eventCfg);
/* 530:    */         }
/* 531:    */       }
/* 532:    */     }
/* 533:    */     else
/* 534:    */     {
/* 535:503 */       DisplayMessage.showWarningDialog("message.noselect");
/* 536:    */     }
/* 537:    */   }
/* 538:    */   
/* 539:    */   public void setTableFormate()
/* 540:    */   {
/* 541:508 */     TableColumnModel tcm = this.jTable1.getColumnModel();
/* 542:509 */     TableColumn tc0 = tcm.getColumn(0);
/* 543:510 */     TableColumn tc1 = tcm.getColumn(1);
/* 544:511 */     TableColumn tc2 = tcm.getColumn(2);
/* 545:512 */     tc0.setPreferredWidth(30);
/* 546:513 */     tc1.setPreferredWidth(40);
/* 547:514 */     tc2.setPreferredWidth(250);
/* 548:    */   }
/* 549:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.EventActionDialog
 * JD-Core Version:    0.7.0.1
 */