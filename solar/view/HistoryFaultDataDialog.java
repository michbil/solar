/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.dao.FaultDataDao;
/*   8:    */ import cn.com.voltronic.solar.data.bean.DataBeforeFault;
/*   9:    */ import cn.com.voltronic.solar.data.bean.HistoryData;
/*  10:    */ import cn.com.voltronic.solar.data.bean.HistoryFaultDataColumns;
/*  11:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  12:    */ import cn.com.voltronic.solar.protocol.P30;
/*  13:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  14:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  15:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  16:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  17:    */ import cn.com.voltronic.solar.view.component.AADefaultTableModel;
/*  18:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  19:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  20:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  21:    */ import cn.com.voltronic.solar.view.component.I18NBundle;
/*  22:    */ import cn.com.voltronic.solar.view.component.I18NListener;
/*  23:    */ import cn.com.voltronic.solar.view.component.MyCalendarPanel;
/*  24:    */ import com.itextpdf.text.BaseColor;
/*  25:    */ import com.itextpdf.text.Document;
/*  26:    */ import com.itextpdf.text.Font;
/*  27:    */ import com.itextpdf.text.PageSize;
/*  28:    */ import com.itextpdf.text.Paragraph;
/*  29:    */ import com.itextpdf.text.pdf.BaseFont;
/*  30:    */ import com.itextpdf.text.pdf.PdfPCell;
/*  31:    */ import com.itextpdf.text.pdf.PdfPTable;
/*  32:    */ import com.itextpdf.text.pdf.PdfWriter;
/*  33:    */ import java.awt.Color;
/*  34:    */ import java.awt.Container;
/*  35:    */ import java.awt.Frame;
/*  36:    */ import java.awt.event.ActionEvent;
/*  37:    */ import java.awt.event.ActionListener;
/*  38:    */ import java.awt.event.ItemEvent;
/*  39:    */ import java.awt.event.ItemListener;
/*  40:    */ import java.awt.event.MouseAdapter;
/*  41:    */ import java.awt.event.MouseEvent;
/*  42:    */ import java.io.File;
/*  43:    */ import java.io.FileOutputStream;
/*  44:    */ import java.io.IOException;
/*  45:    */ import java.text.DateFormat;
/*  46:    */ import java.text.SimpleDateFormat;
/*  47:    */ import java.util.ArrayList;
/*  48:    */ import java.util.Date;
/*  49:    */ import java.util.List;
/*  50:    */ import javax.swing.BorderFactory;
/*  51:    */ import javax.swing.DefaultComboBoxModel;
/*  52:    */ import javax.swing.GroupLayout;
/*  53:    */ import javax.swing.GroupLayout.Alignment;
/*  54:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  55:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  56:    */ import javax.swing.ImageIcon;
/*  57:    */ import javax.swing.JComboBox;
/*  58:    */ import javax.swing.JFileChooser;
/*  59:    */ import javax.swing.JPanel;
/*  60:    */ import javax.swing.JScrollPane;
/*  61:    */ import javax.swing.JTable;
/*  62:    */ import javax.swing.JTextField;
/*  63:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  64:    */ import javax.swing.table.JTableHeader;
/*  65:    */ import javax.swing.table.TableColumn;
/*  66:    */ import javax.swing.table.TableColumnModel;
/*  67:    */ 
/*  68:    */ public class HistoryFaultDataDialog
/*  69:    */   extends AADialog
/*  70:    */ {
/*  71:    */   private static final long serialVersionUID = 115849112670581892L;
/*  72: 74 */   private FaultDataDao faultDataDao = null;
/*  73:    */   private IProtocol protocol;
/*  74:    */   private AAButton deleteAllButton;
/*  75:    */   private AAButton deleteButton;
/*  76:    */   private AAButton closeButton;
/*  77:    */   private AAButton jButton3;
/*  78:    */   private AAButton saveAsButton;
/*  79:    */   private JComboBox jComboBox1;
/*  80:    */   private AALabel jLabel1;
/*  81:    */   private AALabel jLabel2;
/*  82:    */   private AALabel jLabel3;
/*  83:    */   private AALabel jLabel4;
/*  84:    */   private AALabel jLabel5;
/*  85:    */   private AALabel jLabel7;
/*  86:    */   private AALabel jLabel8;
/*  87:    */   private AALabel jLabel9;
/*  88:    */   private JPanel jPanel1;
/*  89:    */   private JPanel jPanel2;
/*  90:    */   private JScrollPane jScrollPane1;
/*  91:    */   private JTable jTable1;
/*  92:    */   private JTextField jTextField1;
/*  93:    */   private JTextField jTextField2;
/*  94:    */   private JFileChooser fDialog;
/*  95: 98 */   private AADefaultTableModel tableModel = null;
/*  96: 99 */   private List<DataBeforeFault> datalist = new ArrayList();
/*  97:100 */   public DataBeforeFault faultData = null;
/*  98:101 */   private final int MAX_SIZE = 20;
/*  99:102 */   private int MAX_COLUMN = 8;
/* 100:103 */   private String[] tableTile = null;
/* 101:104 */   private boolean canDelete = false;
/* 102:    */   
/* 103:    */   public HistoryFaultDataDialog(Frame parent, boolean modal)
/* 104:    */   {
/* 105:107 */     super(parent, modal);
/* 106:108 */     this.protocol = new P30();
/* 107:109 */     this.faultData = new DataBeforeFault();
/* 108:110 */     this.faultDataDao = new FaultDataDao();
/* 109:111 */     initComponents();
/* 110:112 */     setTitle("message.queryFaultData");
/* 111:113 */     setLocationRelativeTo(null);
/* 112:114 */     setVisible(true);
/* 113:    */   }
/* 114:    */   
/* 115:    */   private List<HistoryData> getHistoryFaultColumns()
/* 116:    */   {
/* 117:118 */     List<HistoryData> list = new ArrayList();
/* 118:119 */     Object selected = this.jComboBox1.getSelectedItem();
/* 119:120 */     if (selected != null)
/* 120:    */     {
/* 121:121 */       String serialno = selected.toString().trim();
/* 122:122 */       String prodid = this.faultDataDao.getProdidBySerialno(serialno);
/* 123:123 */       if (prodid.equals("P30")) {
/* 124:124 */         this.protocol = new P30();
/* 125:    */       } else {
/* 126:126 */         this.protocol = new P30();
/* 127:    */       }
/* 128:    */     }
/* 129:129 */     HistoryFaultDataColumns data = this.protocol.getHistoryFaultColumns();
/* 130:130 */     list = data.getColumns();
/* 131:131 */     return list;
/* 132:    */   }
/* 133:    */   
/* 134:    */   private String[] getTableTitle()
/* 135:    */   {
/* 136:135 */     List<HistoryData> list = getHistoryFaultColumns();
/* 137:136 */     this.MAX_COLUMN = list.size();
/* 138:137 */     String[] str = new String[list.size()];
/* 139:138 */     for (int i = 0; i < list.size(); i++) {
/* 140:139 */       str[i] = ((HistoryData)list.get(i)).getName();
/* 141:    */     }
/* 142:141 */     return str;
/* 143:    */   }
/* 144:    */   
/* 145:    */   private void initComponents()
/* 146:    */   {
/* 147:146 */     this.jPanel1 = new JPanel();
/* 148:147 */     this.jLabel1 = new AALabel();
/* 149:148 */     this.jComboBox1 = new JComboBox();
/* 150:149 */     this.jComboBox1.setModel(new DefaultComboBoxModel(queryDevices()));
/* 151:150 */     this.jLabel2 = new AALabel();
/* 152:151 */     this.jTextField1 = new JTextField();
/* 153:152 */     this.jLabel3 = new AALabel();
/* 154:153 */     this.jLabel4 = new AALabel();
/* 155:154 */     this.jLabel5 = new AALabel();
/* 156:155 */     this.jLabel7 = new AALabel();
/* 157:156 */     this.jLabel8 = new AALabel();
/* 158:157 */     this.jLabel9 = new AALabel();
/* 159:158 */     this.jButton3 = new AAButton();
/* 160:159 */     this.jTextField2 = new JTextField();
/* 161:160 */     this.jScrollPane1 = new JScrollPane();
/* 162:161 */     this.jPanel2 = new JPanel();
/* 163:162 */     this.deleteAllButton = new AAButton();
/* 164:163 */     this.deleteButton = new AAButton();
/* 165:164 */     this.closeButton = new AAButton();
/* 166:165 */     this.saveAsButton = new AAButton();
/* 167:166 */     this.tableTile = getTableTitle();
/* 168:167 */     this.jTable1 = new JTable()
/* 169:    */     {
/* 170:    */       private static final long serialVersionUID = -2097364819095589911L;
/* 171:    */       
/* 172:    */       public boolean isCellSelected(int row, int column)
/* 173:    */       {
/* 174:172 */         if (row >= HistoryFaultDataDialog.this.datalist.size()) {
/* 175:173 */           return false;
/* 176:    */         }
/* 177:175 */         return super.isCellSelected(row, column);
/* 178:    */       }
/* 179:178 */     };
/* 180:179 */     this.jTable1.getTableHeader().setReorderingAllowed(false);
/* 181:180 */     this.jTable1.getTableHeader().setForeground(Color.white);
/* 182:181 */     this.jTable1.setSelectionMode(0);
/* 183:    */     
/* 184:183 */     this.tableModel = new AADefaultTableModel(new Object[20][this.MAX_COLUMN], this.tableTile)
/* 185:    */     {
/* 186:    */       private static final long serialVersionUID = -4444769250474742659L;
/* 187:    */       
/* 188:    */       public boolean isCellEditable(int row, int column)
/* 189:    */       {
/* 190:188 */         return false;
/* 191:    */       }
/* 192:190 */     };
/* 193:191 */     this.jTable1.setModel(this.tableModel);
/* 194:    */     
/* 195:    */ 
/* 196:194 */     setDefaultCloseOperation(2);
/* 197:    */     
/* 198:196 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 199:    */     
/* 200:198 */     this.jLabel1.setText("message.device[:]");
/* 201:    */     
/* 202:200 */     this.jLabel2.setText("message.selectDate[:]");
/* 203:    */     
/* 204:202 */     this.jComboBox1.addItemListener(new ItemListener()
/* 205:    */     {
/* 206:    */       public void itemStateChanged(ItemEvent ie)
/* 207:    */       {
/* 208:204 */         HistoryFaultDataDialog.this.canDelete = false;
/* 209:    */       }
/* 210:206 */     });
/* 211:207 */     this.jLabel3.setIcon(new ImageIcon(Constants.DATE));
/* 212:208 */     this.jLabel3.addMouseListener(new MouseAdapter()
/* 213:    */     {
/* 214:    */       public void mouseClicked(MouseEvent e)
/* 215:    */       {
/* 216:211 */         HistoryFaultDataDialog.this.canDelete = false;
/* 217:212 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryFaultDataDialog.this.jTextField1);
/* 218:213 */         calendarPanel.setBounds(HistoryFaultDataDialog.this.getX() + HistoryFaultDataDialog.this.jLabel3.getX() + 30, HistoryFaultDataDialog.this.getY() + 
/* 219:214 */           HistoryFaultDataDialog.this.jLabel3.getY() + 10, calendarPanel.getWidth(), 
/* 220:215 */           calendarPanel.getHeight());
/* 221:216 */         calendarPanel.setVisible(true);
/* 222:    */       }
/* 223:219 */     });
/* 224:220 */     this.jTextField1.setEditable(false);
/* 225:221 */     this.jTextField1.setBackground(I18NListener.bgColor);
/* 226:222 */     this.jTextField1.setText(DateUtils.getNowDate());
/* 227:223 */     this.jTextField1.addMouseListener(new MouseAdapter()
/* 228:    */     {
/* 229:    */       public void mouseClicked(MouseEvent e)
/* 230:    */       {
/* 231:226 */         HistoryFaultDataDialog.this.canDelete = false;
/* 232:227 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryFaultDataDialog.this.jTextField1);
/* 233:228 */         calendarPanel.setBounds(HistoryFaultDataDialog.this.getX() + HistoryFaultDataDialog.this.jLabel3.getX() + 30, HistoryFaultDataDialog.this.getY() + 
/* 234:229 */           HistoryFaultDataDialog.this.jLabel3.getY() + 10, calendarPanel.getWidth(), 
/* 235:230 */           calendarPanel.getHeight());
/* 236:231 */         calendarPanel.setVisible(true);
/* 237:    */       }
/* 238:234 */     });
/* 239:235 */     this.jLabel4.setText("--");
/* 240:    */     
/* 241:237 */     this.jLabel5.setIcon(new ImageIcon(Constants.DATE));
/* 242:238 */     this.jLabel5.addMouseListener(new MouseAdapter()
/* 243:    */     {
/* 244:    */       public void mouseClicked(MouseEvent e)
/* 245:    */       {
/* 246:241 */         HistoryFaultDataDialog.this.canDelete = false;
/* 247:242 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryFaultDataDialog.this.jTextField2);
/* 248:243 */         calendarPanel.setBounds(HistoryFaultDataDialog.this.getX() + HistoryFaultDataDialog.this.jLabel5.getX() + 30, HistoryFaultDataDialog.this.getY() + 
/* 249:244 */           HistoryFaultDataDialog.this.jLabel5.getY() + 10, calendarPanel.getWidth(), 
/* 250:245 */           calendarPanel.getHeight());
/* 251:246 */         calendarPanel.setVisible(true);
/* 252:    */       }
/* 253:249 */     });
/* 254:250 */     this.jTextField2.setEditable(false);
/* 255:251 */     this.jTextField2.setBackground(I18NListener.bgColor);
/* 256:252 */     this.jTextField2.setText(DateUtils.getNowDate());
/* 257:253 */     this.jTextField2.addMouseListener(new MouseAdapter()
/* 258:    */     {
/* 259:    */       public void mouseClicked(MouseEvent e)
/* 260:    */       {
/* 261:256 */         HistoryFaultDataDialog.this.canDelete = false;
/* 262:257 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryFaultDataDialog.this.jTextField2);
/* 263:258 */         calendarPanel.setBounds(HistoryFaultDataDialog.this.getX() + HistoryFaultDataDialog.this.jLabel5.getX() + 30, HistoryFaultDataDialog.this.getY() + 
/* 264:259 */           HistoryFaultDataDialog.this.jLabel5.getY() + 10, calendarPanel.getWidth(), 
/* 265:260 */           calendarPanel.getHeight());
/* 266:261 */         calendarPanel.setVisible(true);
/* 267:    */       }
/* 268:264 */     });
/* 269:265 */     this.jButton3.setText("message.view");
/* 270:266 */     this.jButton3.addActionListener(new ActionListener()
/* 271:    */     {
/* 272:    */       public void actionPerformed(ActionEvent e)
/* 273:    */       {
/* 274:269 */         HistoryFaultDataDialog.this.canDelete = true;
/* 275:270 */         HistoryFaultDataDialog.this.queryHistoryFaultData();
/* 276:    */       }
/* 277:273 */     });
/* 278:274 */     this.jLabel7.setText("message.totalrow[:]");
/* 279:275 */     this.jLabel8.setText("0");
/* 280:276 */     this.jLabel9.setText("message.row");
/* 281:    */     
/* 282:278 */     this.jScrollPane1.setViewportView(this.jTable1);
/* 283:    */     
/* 284:280 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 285:281 */     this.jPanel1.setLayout(jPanel1Layout);
/* 286:282 */     jPanel1Layout.setHorizontalGroup(
/* 287:283 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 288:284 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 289:285 */       .addContainerGap()
/* 290:286 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 291:287 */       .addComponent(this.jScrollPane1, -1, 950, 32767)
/* 292:288 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 293:289 */       .addComponent(this.jLabel1)
/* 294:290 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 295:291 */       .addComponent(this.jComboBox1, -2, 140, -2)
/* 296:292 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 297:293 */       .addComponent(this.jLabel2)
/* 298:294 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 299:295 */       .addComponent(this.jTextField1, -2, 104, -2)
/* 300:296 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 301:297 */       .addComponent(this.jLabel3)
/* 302:298 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 303:299 */       .addComponent(this.jLabel4)
/* 304:300 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 305:301 */       .addComponent(this.jTextField2, -2, 104, -2)
/* 306:302 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 307:303 */       .addComponent(this.jLabel5)
/* 308:304 */       .addGap(10, 10, 10)
/* 309:305 */       .addComponent(this.jButton3)))
/* 310:306 */       .addContainerGap()));
/* 311:    */     
/* 312:308 */     jPanel1Layout.setVerticalGroup(
/* 313:309 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 314:310 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 315:311 */       .addContainerGap()
/* 316:312 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 317:313 */       .addComponent(this.jButton3)
/* 318:314 */       .addComponent(this.jLabel5)
/* 319:315 */       .addComponent(this.jTextField2, -2, -1, -2)
/* 320:316 */       .addComponent(this.jLabel4)
/* 321:317 */       .addComponent(this.jLabel3)
/* 322:318 */       .addComponent(this.jTextField1, -2, -1, -2)
/* 323:319 */       .addComponent(this.jLabel2)
/* 324:320 */       .addComponent(this.jComboBox1, -2, -1, -2)
/* 325:321 */       .addComponent(this.jLabel1))
/* 326:322 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 327:323 */       .addComponent(this.jScrollPane1, -1, 423, 32767)
/* 328:324 */       .addContainerGap()));
/* 329:    */     
/* 330:    */ 
/* 331:327 */     getContentPane().add(this.jPanel1, "Center");
/* 332:    */     
/* 333:329 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 334:330 */     this.deleteButton.setText("message.del");
/* 335:331 */     this.deleteButton.addActionListener(new ActionListener()
/* 336:    */     {
/* 337:    */       public void actionPerformed(ActionEvent e)
/* 338:    */       {
/* 339:334 */         if (!SolarPowerTray.isLogin)
/* 340:    */         {
/* 341:335 */           new LoginJDialog(new Frame(), true);
/* 342:336 */           return;
/* 343:    */         }
/* 344:338 */         HistoryFaultDataDialog.this.deleteFaultDatas();
/* 345:    */       }
/* 346:340 */     });
/* 347:341 */     this.deleteAllButton.setText("message.deleteAll");
/* 348:342 */     this.deleteAllButton.addActionListener(new ActionListener()
/* 349:    */     {
/* 350:    */       public void actionPerformed(ActionEvent e)
/* 351:    */       {
/* 352:345 */         if (!SolarPowerTray.isLogin)
/* 353:    */         {
/* 354:346 */           new LoginJDialog(new Frame(), true);
/* 355:347 */           return;
/* 356:    */         }
/* 357:349 */         HistoryFaultDataDialog.this.clearFaultDatas();
/* 358:    */       }
/* 359:352 */     });
/* 360:353 */     this.closeButton.setText("message.close");
/* 361:354 */     this.closeButton.addActionListener(new ActionListener()
/* 362:    */     {
/* 363:    */       public void actionPerformed(ActionEvent e)
/* 364:    */       {
/* 365:357 */         HistoryFaultDataDialog.this.dispose();
/* 366:    */       }
/* 367:360 */     });
/* 368:361 */     this.saveAsButton.setText("message.saveas");
/* 369:362 */     this.saveAsButton.addActionListener(new ActionListener()
/* 370:    */     {
/* 371:    */       public void actionPerformed(ActionEvent e)
/* 372:    */       {
/* 373:365 */         if (!SolarPowerTray.isLogin)
/* 374:    */         {
/* 375:366 */           new LoginJDialog(new Frame(), true);
/* 376:367 */           return;
/* 377:    */         }
/* 378:369 */         HistoryFaultDataDialog.this.saveAs();
/* 379:    */       }
/* 380:372 */     });
/* 381:373 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 382:374 */     this.jPanel2.setLayout(jPanel2Layout);
/* 383:375 */     jPanel2Layout.setHorizontalGroup(
/* 384:376 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 385:377 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 386:378 */       jPanel2Layout.createSequentialGroup().addContainerGap()
/* 387:379 */       .addComponent(this.jLabel7)
/* 388:380 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 389:381 */       .addComponent(this.jLabel8)
/* 390:382 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 391:383 */       .addComponent(this.jLabel9)
/* 392:384 */       .addContainerGap(450, 32767)
/* 393:385 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 460, 32767)
/* 394:386 */       .addComponent(this.saveAsButton)
/* 395:387 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 396:388 */       .addComponent(this.deleteButton)
/* 397:389 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 398:390 */       .addComponent(this.deleteAllButton)
/* 399:391 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 400:392 */       .addComponent(this.closeButton)
/* 401:393 */       .addGap(12, 12, 12)));
/* 402:    */     
/* 403:395 */     jPanel2Layout.setVerticalGroup(
/* 404:396 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 405:397 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 406:398 */       jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767)
/* 407:399 */       .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 408:400 */       .addComponent(this.jLabel7)
/* 409:401 */       .addComponent(this.jLabel8)
/* 410:402 */       .addComponent(this.jLabel9)
/* 411:403 */       .addComponent(this.closeButton)
/* 412:404 */       .addComponent(this.deleteButton)
/* 413:405 */       .addComponent(this.deleteAllButton)
/* 414:406 */       .addComponent(this.saveAsButton))
/* 415:407 */       .addContainerGap()));
/* 416:    */     
/* 417:    */ 
/* 418:410 */     getContentPane().add(this.jPanel2, "Last");
/* 419:    */     
/* 420:412 */     pack();
/* 421:    */   }
/* 422:    */   
/* 423:    */   public void queryHistoryFaultData()
/* 424:    */   {
/* 425:416 */     if (this.jComboBox1.getSelectedItem() != null)
/* 426:    */     {
/* 427:417 */       String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 428:418 */       String startDate = this.jTextField1.getText().trim();
/* 429:419 */       String endDate = this.jTextField2.getText().trim();
/* 430:420 */       if ((serialno != null) && (!"".equals(serialno)) && (startDate != null) && 
/* 431:421 */         (!"".equals(startDate)) && (endDate != null) && 
/* 432:422 */         (!"".equals(endDate)))
/* 433:    */       {
/* 434:423 */         this.jLabel8.setText("0");
/* 435:424 */         DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 436:425 */         Date fromDate = new Date();
/* 437:426 */         Date toDate = new Date();
/* 438:    */         try
/* 439:    */         {
/* 440:428 */           fromDate = format.parse(startDate);
/* 441:429 */           toDate = format.parse(endDate);
/* 442:    */         }
/* 443:    */         catch (Exception ex)
/* 444:    */         {
/* 445:431 */           ex.printStackTrace();
/* 446:    */         }
/* 447:433 */         this.datalist = this.faultDataDao.queryData(serialno, fromDate, toDate);
/* 448:434 */         int size = this.datalist.size();
/* 449:435 */         if (size < 20) {
/* 450:436 */           size = 20;
/* 451:    */         }
/* 452:438 */         String[] tableTitle = getTableTitle();
/* 453:439 */         List<HistoryData> lists = getHistoryFaultColumns();
/* 454:440 */         Object[][] obj = new Object[size][this.MAX_COLUMN];
/* 455:441 */         for (int i = 0; i < this.datalist.size(); i++)
/* 456:    */         {
/* 457:442 */           this.faultData = ((DataBeforeFault)this.datalist.get(i));
/* 458:443 */           for (int j = 0; j < lists.size(); j++) {
/* 459:    */             try
/* 460:    */             {
/* 461:445 */               if (((HistoryData)lists.get(j)).getValue().equals("{faultData.getTrandate}")) {
/* 462:446 */                 obj[i][j] = 
/* 463:447 */                   DateUtils.getFormatDateStr(PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this), GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 464:    */               } else {
/* 465:449 */                 obj[i][j] = PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this);
/* 466:    */               }
/* 467:    */             }
/* 468:    */             catch (Exception ex)
/* 469:    */             {
/* 470:452 */               ex.printStackTrace();
/* 471:    */             }
/* 472:    */           }
/* 473:    */         }
/* 474:456 */         if (this.datalist.size() < 20) {
/* 475:457 */           for (int i = this.datalist.size(); i < 20; i++) {
/* 476:458 */             for (int j = 0; j < this.MAX_COLUMN; j++) {
/* 477:459 */               obj[i][j] = null;
/* 478:    */             }
/* 479:    */           }
/* 480:    */         }
/* 481:463 */         this.tableModel.setDataVector(obj, tableTitle);
/* 482:464 */         if (this.datalist != null) {
/* 483:465 */           this.jLabel8.setText(this.datalist.size());
/* 484:    */         } else {
/* 485:467 */           this.jLabel8.setText("0");
/* 486:    */         }
/* 487:    */       }
/* 488:    */     }
/* 489:    */   }
/* 490:    */   
/* 491:    */   public void setTableFormate()
/* 492:    */   {
/* 493:474 */     TableColumnModel tcm = this.jTable1.getColumnModel();
/* 494:475 */     TableColumn tc0 = tcm.getColumn(0);
/* 495:476 */     TableColumn tc1 = tcm.getColumn(1);
/* 496:477 */     TableColumn tc2 = tcm.getColumn(2);
/* 497:478 */     TableColumn tc3 = tcm.getColumn(3);
/* 498:479 */     tc0.setPreferredWidth(30);
/* 499:480 */     tc1.setPreferredWidth(30);
/* 500:481 */     tc2.setPreferredWidth(100);
/* 501:482 */     tc3.setPreferredWidth(250);
/* 502:    */   }
/* 503:    */   
/* 504:    */   public String[] queryDevices()
/* 505:    */   {
/* 506:486 */     List<String> devicelist = this.faultDataDao.getSerialNo();
/* 507:487 */     String[] devices = new String[devicelist.size()];
/* 508:488 */     for (int i = 0; i < devicelist.size(); i++) {
/* 509:489 */       devices[i] = ((String)devicelist.get(i));
/* 510:    */     }
/* 511:491 */     return devices;
/* 512:    */   }
/* 513:    */   
/* 514:    */   public void deleteFaultDatas()
/* 515:    */   {
/* 516:495 */     if (this.jComboBox1.getSelectedItem() != null)
/* 517:    */     {
/* 518:496 */       String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 519:497 */       int re = DisplayMessage.showConfirmDialog("message.deleteConfirm", "message.info");
/* 520:498 */       if ((re == 0) && 
/* 521:499 */         (serialno != null) && (!"".equals(serialno)))
/* 522:    */       {
/* 523:500 */         int row = this.jTable1.getSelectedRow();
/* 524:501 */         if (row > -1)
/* 525:    */         {
/* 526:502 */           Object obj = this.tableModel.getValueAt(row, 0);
/* 527:503 */           if (obj != null)
/* 528:    */           {
/* 529:504 */             SimpleDateFormat sdf = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 530:505 */             Date trandate = new Date();
/* 531:    */             try
/* 532:    */             {
/* 533:507 */               trandate = sdf.parse(obj.toString());
/* 534:    */             }
/* 535:    */             catch (Exception ex)
/* 536:    */             {
/* 537:509 */               ex.printStackTrace();
/* 538:    */             }
/* 539:511 */             boolean result = this.faultDataDao.removeData(serialno, trandate);
/* 540:512 */             if (result) {
/* 541:513 */               queryHistoryFaultData();
/* 542:    */             } else {
/* 543:515 */               DisplayMessage.showErrorDialog("message.operationfailure");
/* 544:    */             }
/* 545:    */           }
/* 546:    */           else
/* 547:    */           {
/* 548:518 */             DisplayMessage.showErrorDialog("message.pleaseselect");
/* 549:    */           }
/* 550:    */         }
/* 551:    */         else
/* 552:    */         {
/* 553:521 */           DisplayMessage.showErrorDialog("message.pleaseselect");
/* 554:    */         }
/* 555:    */       }
/* 556:    */     }
/* 557:    */   }
/* 558:    */   
/* 559:    */   public void clearFaultDatas()
/* 560:    */   {
/* 561:529 */     if ((this.canDelete) && 
/* 562:530 */       (this.jComboBox1.getSelectedItem() != null))
/* 563:    */     {
/* 564:531 */       String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 565:532 */       String startDate = this.jTextField1.getText().trim();
/* 566:533 */       String endDate = this.jTextField2.getText().trim();
/* 567:534 */       int re = DisplayMessage.showConfirmDialog("message.deleteConfirm", "message.info");
/* 568:535 */       if ((re == 0) && 
/* 569:536 */         (serialno != null) && (!"".equals(serialno)) && (startDate != null) && 
/* 570:537 */         (!"".equals(startDate)) && (endDate != null) && 
/* 571:538 */         (!"".equals(endDate)))
/* 572:    */       {
/* 573:539 */         DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 574:540 */         Date fromDate = new Date();
/* 575:541 */         Date toDate = new Date();
/* 576:    */         try
/* 577:    */         {
/* 578:543 */           fromDate = format.parse(startDate);
/* 579:544 */           toDate = format.parse(endDate);
/* 580:    */         }
/* 581:    */         catch (Exception ex)
/* 582:    */         {
/* 583:546 */           ex.printStackTrace();
/* 584:    */         }
/* 585:548 */         boolean result = this.faultDataDao.removeDataAll(serialno, fromDate, 
/* 586:549 */           toDate);
/* 587:550 */         if (result) {
/* 588:551 */           queryHistoryFaultData();
/* 589:    */         } else {
/* 590:553 */           DisplayMessage.showErrorDialog("message.operationfailure");
/* 591:    */         }
/* 592:    */       }
/* 593:    */     }
/* 594:    */   }
/* 595:    */   
/* 596:    */   public int getSaveDialog()
/* 597:    */   {
/* 598:562 */     String saveFileName = "History_fault_datas.pdf";
/* 599:563 */     this.fDialog = new JFileChooser();
/* 600:564 */     PDFFileFilter txtFilter = new PDFFileFilter();
/* 601:565 */     this.fDialog.addChoosableFileFilter(txtFilter);
/* 602:566 */     this.fDialog.setFileFilter(txtFilter);
/* 603:567 */     this.fDialog.setAcceptAllFileFilterUsed(true);
/* 604:568 */     this.fDialog.setSelectedFile(new File(saveFileName));
/* 605:569 */     return this.fDialog.showSaveDialog(this);
/* 606:    */   }
/* 607:    */   
/* 608:    */   public void saveAs()
/* 609:    */   {
/* 610:573 */     int result = getSaveDialog();
/* 611:574 */     if (result == 0)
/* 612:    */     {
/* 613:575 */       String filepathStr = this.fDialog.getSelectedFile().getPath();
/* 614:576 */       File newFile = new File(filepathStr);
/* 615:577 */       if (newFile.exists())
/* 616:    */       {
/* 617:578 */         int re = 
/* 618:579 */           DisplayMessage.showConfirmDialog(
/* 619:580 */           "message.confirmoverwrite", 
/* 620:581 */           "message.info");
/* 621:582 */         if (re == 0) {
/* 622:583 */           createPdf(filepathStr);
/* 623:    */         } else {
/* 624:585 */           saveAs();
/* 625:    */         }
/* 626:    */       }
/* 627:    */       else
/* 628:    */       {
/* 629:    */         try
/* 630:    */         {
/* 631:589 */           newFile.createNewFile();
/* 632:    */         }
/* 633:    */         catch (IOException e)
/* 634:    */         {
/* 635:591 */           e.printStackTrace();
/* 636:    */         }
/* 637:593 */         createPdf(filepathStr);
/* 638:    */       }
/* 639:    */     }
/* 640:    */   }
/* 641:    */   
/* 642:    */   public void createPdf(String path)
/* 643:    */   {
/* 644:599 */     Document pdf = new Document(PageSize.A4);
/* 645:    */     try
/* 646:    */     {
/* 647:601 */       PdfWriter.getInstance(pdf, new FileOutputStream(path));
/* 648:602 */       pdf.addTitle("History fault datas");
/* 649:603 */       pdf.addAuthor(GlobalVariables.customerConfig.getCustomerName());
/* 650:604 */       pdf.open();
/* 651:605 */       BaseFont bf = BaseFont.createFont(Constants.PDF_STYLE_PATH + "simsun.ttc,1", "Identity-H", false);
/* 652:606 */       Font datafont = new Font(bf, 12.0F, 0, BaseColor.BLACK);
/* 653:607 */       String[] titles = getTableTitle();
/* 654:608 */       PdfPTable table = new PdfPTable(titles.length);
/* 655:609 */       for (int i = 0; i < titles.length; i++)
/* 656:    */       {
/* 657:610 */         PdfPCell cell = new PdfPCell(new Paragraph(bd.getString(titles[i]), datafont));
/* 658:611 */         cell.setHorizontalAlignment(1);
/* 659:612 */         table.addCell(cell);
/* 660:    */       }
/* 661:614 */       for (int i = 0; i < this.datalist.size(); i++)
/* 662:    */       {
/* 663:615 */         List<HistoryData> lists = getHistoryFaultColumns();
/* 664:616 */         this.faultData = ((DataBeforeFault)this.datalist.get(i));
/* 665:617 */         for (int j = 0; j < lists.size(); j++) {
/* 666:618 */           if (((HistoryData)lists.get(j)).getValue().equals("{faultData.getTrandate}")) {
/* 667:619 */             table.addCell(new Paragraph(
/* 668:620 */               DateUtils.getFormatDateStr(PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this), GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss"), datafont));
/* 669:    */           } else {
/* 670:622 */             table.addCell(new Paragraph(PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this), datafont));
/* 671:    */           }
/* 672:    */         }
/* 673:    */       }
/* 674:627 */       table.setWidthPercentage(100.0F);
/* 675:628 */       pdf.add(table);
/* 676:629 */       Thread.sleep(1500L);
/* 677:    */     }
/* 678:    */     catch (Exception ie)
/* 679:    */     {
/* 680:632 */       DisplayMessage.showErrorDialog("message.saveerror");
/* 681:    */       try
/* 682:    */       {
/* 683:635 */         if (pdf != null) {
/* 684:636 */           pdf.close();
/* 685:    */         }
/* 686:    */       }
/* 687:    */       catch (Exception ex)
/* 688:    */       {
/* 689:639 */         ex.printStackTrace();
/* 690:    */       }
/* 691:    */     }
/* 692:    */     finally
/* 693:    */     {
/* 694:    */       try
/* 695:    */       {
/* 696:635 */         if (pdf != null) {
/* 697:636 */           pdf.close();
/* 698:    */         }
/* 699:    */       }
/* 700:    */       catch (Exception ex)
/* 701:    */       {
/* 702:639 */         ex.printStackTrace();
/* 703:    */       }
/* 704:    */     }
/* 705:    */   }
/* 706:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.HistoryFaultDataDialog
 * JD-Core Version:    0.7.0.1
 */