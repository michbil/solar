/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.dao.EventRecordDao;
/*   8:    */ import cn.com.voltronic.solar.data.bean.EventData;
/*   9:    */ import cn.com.voltronic.solar.data.bean.EventDataRecord;
/*  10:    */ import cn.com.voltronic.solar.data.bean.EventStatisItem;
/*  11:    */ import cn.com.voltronic.solar.exception.EventsHandler;
/*  12:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  13:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  14:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  15:    */ import cn.com.voltronic.solar.view.component.AACheckBox;
/*  16:    */ import cn.com.voltronic.solar.view.component.AADefaultTableModel;
/*  17:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  18:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  19:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  20:    */ import cn.com.voltronic.solar.view.component.I18NBundle;
/*  21:    */ import cn.com.voltronic.solar.view.component.I18NListener;
/*  22:    */ import cn.com.voltronic.solar.view.component.MyCalendarPanel;
/*  23:    */ import com.itextpdf.text.BaseColor;
/*  24:    */ import com.itextpdf.text.Document;
/*  25:    */ import com.itextpdf.text.Font;
/*  26:    */ import com.itextpdf.text.PageSize;
/*  27:    */ import com.itextpdf.text.Paragraph;
/*  28:    */ import com.itextpdf.text.pdf.BaseFont;
/*  29:    */ import com.itextpdf.text.pdf.PdfPCell;
/*  30:    */ import com.itextpdf.text.pdf.PdfPTable;
/*  31:    */ import com.itextpdf.text.pdf.PdfWriter;
/*  32:    */ import java.awt.Color;
/*  33:    */ import java.awt.Container;
/*  34:    */ import java.awt.Dimension;
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
/*  45:    */ import java.io.PrintStream;
/*  46:    */ import java.text.DateFormat;
/*  47:    */ import java.text.SimpleDateFormat;
/*  48:    */ import java.util.ArrayList;
/*  49:    */ import java.util.Date;
/*  50:    */ import java.util.List;
/*  51:    */ import javax.swing.BorderFactory;
/*  52:    */ import javax.swing.DefaultComboBoxModel;
/*  53:    */ import javax.swing.GroupLayout;
/*  54:    */ import javax.swing.GroupLayout.Alignment;
/*  55:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  56:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  57:    */ import javax.swing.ImageIcon;
/*  58:    */ import javax.swing.JComboBox;
/*  59:    */ import javax.swing.JFileChooser;
/*  60:    */ import javax.swing.JPanel;
/*  61:    */ import javax.swing.JScrollPane;
/*  62:    */ import javax.swing.JSeparator;
/*  63:    */ import javax.swing.JTable;
/*  64:    */ import javax.swing.JTextField;
/*  65:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  66:    */ import javax.swing.table.JTableHeader;
/*  67:    */ import javax.swing.table.TableColumn;
/*  68:    */ import javax.swing.table.TableColumnModel;
/*  69:    */ import org.jfree.chart.ChartFactory;
/*  70:    */ import org.jfree.chart.ChartPanel;
/*  71:    */ import org.jfree.chart.JFreeChart;
/*  72:    */ import org.jfree.chart.axis.CategoryAxis;
/*  73:    */ import org.jfree.chart.axis.NumberAxis;
/*  74:    */ import org.jfree.chart.axis.ValueAxis;
/*  75:    */ import org.jfree.chart.labels.ItemLabelAnchor;
/*  76:    */ import org.jfree.chart.labels.ItemLabelPosition;
/*  77:    */ import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
/*  78:    */ import org.jfree.chart.plot.CategoryPlot;
/*  79:    */ import org.jfree.chart.plot.PlotOrientation;
/*  80:    */ import org.jfree.chart.renderer.category.BarRenderer;
/*  81:    */ import org.jfree.data.category.DefaultCategoryDataset;
/*  82:    */ import org.jfree.ui.TextAnchor;
/*  83:    */ 
/*  84:    */ public class HistoryEventDialog
/*  85:    */   extends AADialog
/*  86:    */ {
/*  87:    */   private static final long serialVersionUID = -2752882145880558233L;
/*  88:    */   private AAButton closeButton;
/*  89:    */   private AAButton deleteAllButton;
/*  90:    */   private AAButton deleteButton;
/*  91:    */   private AAButton saveAsButton;
/*  92:    */   private AAButton jButton3;
/*  93:    */   private AACheckBox jCheckBox1;
/*  94:    */   private JComboBox jComboBox1;
/*  95:    */   private AALabel jLabel1;
/*  96:    */   private AALabel jLabel2;
/*  97:    */   private AALabel jLabel3;
/*  98:    */   private AALabel jLabel4;
/*  99:    */   private AALabel jLabel5;
/* 100:    */   private JPanel jPanel1;
/* 101:    */   private JPanel jPanel2;
/* 102:    */   private JScrollPane jScrollPane1;
/* 103:    */   private JScrollPane jScrollPane2;
/* 104:    */   private JScrollPane jScrollPane3;
/* 105:    */   private JSeparator jSeparator1;
/* 106:    */   private JTable jTable1;
/* 107:    */   private JTable jTable2;
/* 108:    */   private JTextField jTextField1;
/* 109:    */   private JTextField jTextField2;
/* 110:114 */   private boolean canDelete = false;
/* 111:115 */   private DefaultCategoryDataset dataset = null;
/* 112:116 */   private JFreeChart chart = null;
/* 113:117 */   private CategoryPlot categoryPlot = null;
/* 114:    */   private JFileChooser fDialog;
/* 115:119 */   private EventRecordDao eventDao = null;
/* 116:120 */   private AADefaultTableModel tableModel1 = null;
/* 117:121 */   private AADefaultTableModel tableModel2 = null;
/* 118:122 */   private List<EventDataRecord> eventlist = new ArrayList();
/* 119:123 */   private List<EventStatisItem> eventtotal = new ArrayList();
/* 120:124 */   private final int MAX_SIZE = 22;
/* 121:125 */   private final int MAX_SIZE2 = 10;
/* 122:126 */   private final int MAX_COLUMN = 4;
/* 123:127 */   private String[] tableTile = { "ID", "message.level", "message.time", 
/* 124:128 */     "message.eventName" };
/* 125:129 */   private String[] tableTile2 = { "ID", "message.level", "message.eventName", 
/* 126:130 */     "message.count" };
/* 127:    */   
/* 128:    */   public HistoryEventDialog(Frame parent, boolean modal)
/* 129:    */   {
/* 130:133 */     super(parent, modal);
/* 131:134 */     initComponents();
/* 132:135 */     setTitle("message.queryEvent");
/* 133:136 */     setLocationRelativeTo(null);
/* 134:137 */     setVisible(true);
/* 135:    */   }
/* 136:    */   
/* 137:    */   private void initComponents()
/* 138:    */   {
/* 139:142 */     this.eventDao = new EventRecordDao();
/* 140:143 */     this.jPanel1 = new JPanel();
/* 141:144 */     this.jLabel1 = new AALabel();
/* 142:145 */     this.jComboBox1 = new JComboBox();
/* 143:146 */     this.jLabel2 = new AALabel();
/* 144:147 */     this.jTextField1 = new JTextField();
/* 145:148 */     this.jLabel3 = new AALabel();
/* 146:149 */     this.jLabel4 = new AALabel();
/* 147:150 */     this.jLabel5 = new AALabel();
/* 148:151 */     this.jButton3 = new AAButton();
/* 149:152 */     this.jTextField2 = new JTextField();
/* 150:153 */     this.jScrollPane1 = new JScrollPane();
/* 151:154 */     this.jScrollPane2 = new JScrollPane();
/* 152:155 */     this.jScrollPane3 = new JScrollPane();
/* 153:156 */     this.jSeparator1 = new JSeparator();
/* 154:157 */     this.jCheckBox1 = new AACheckBox();
/* 155:158 */     this.jPanel2 = new JPanel();
/* 156:159 */     this.closeButton = new AAButton();
/* 157:160 */     this.deleteAllButton = new AAButton();
/* 158:161 */     this.deleteButton = new AAButton();
/* 159:162 */     this.saveAsButton = new AAButton();
/* 160:    */     
/* 161:164 */     this.jTable1 = new JTable()
/* 162:    */     {
/* 163:    */       private static final long serialVersionUID = -2097364819095589911L;
/* 164:    */       
/* 165:    */       public boolean isCellSelected(int row, int column)
/* 166:    */       {
/* 167:168 */         if (row >= HistoryEventDialog.this.eventlist.size()) {
/* 168:169 */           return false;
/* 169:    */         }
/* 170:171 */         return super.isCellSelected(row, column);
/* 171:    */       }
/* 172:174 */     };
/* 173:175 */     this.jTable1.getTableHeader().setReorderingAllowed(false);
/* 174:176 */     this.jTable1.getTableHeader().setForeground(Color.white);
/* 175:177 */     this.jTable1.setSelectionMode(0);
/* 176:    */     
/* 177:179 */     this.tableModel1 = new AADefaultTableModel(new Object[22][4], 
/* 178:180 */       this.tableTile)
/* 179:    */       {
/* 180:    */         private static final long serialVersionUID = -4444769250474742659L;
/* 181:    */         
/* 182:    */         public boolean isCellEditable(int row, int column)
/* 183:    */         {
/* 184:184 */           return false;
/* 185:    */         }
/* 186:186 */       };
/* 187:187 */       this.jTable1.setModel(this.tableModel1);
/* 188:    */       
/* 189:189 */       this.jTable2 = new JTable()
/* 190:    */       {
/* 191:    */         private static final long serialVersionUID = 1813629617886543541L;
/* 192:    */         
/* 193:    */         public boolean isCellSelected(int row, int column)
/* 194:    */         {
/* 195:193 */           if (row >= HistoryEventDialog.this.eventtotal.size()) {
/* 196:194 */             return false;
/* 197:    */           }
/* 198:196 */           return super.isCellSelected(row, column);
/* 199:    */         }
/* 200:199 */       };
/* 201:200 */       this.jTable2.getTableHeader().setReorderingAllowed(false);
/* 202:201 */       this.jTable2.getTableHeader().setForeground(Color.white);
/* 203:202 */       this.jTable2.setSelectionMode(0);
/* 204:    */       
/* 205:204 */       this.tableModel2 = new AADefaultTableModel(new Object[10][4], 
/* 206:205 */         this.tableTile2)
/* 207:    */         {
/* 208:    */           private static final long serialVersionUID = 2752220891304139492L;
/* 209:    */           
/* 210:    */           public boolean isCellEditable(int row, int column)
/* 211:    */           {
/* 212:209 */             return false;
/* 213:    */           }
/* 214:211 */         };
/* 215:212 */         this.jTable2.setModel(this.tableModel2);
/* 216:    */         
/* 217:214 */         setTableFormate();
/* 218:    */         
/* 219:216 */         setDefaultCloseOperation(2);
/* 220:    */         
/* 221:218 */         this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 222:    */         
/* 223:220 */         this.jLabel1.setText("message.device[:]");
/* 224:    */         
/* 225:222 */         this.jComboBox1.setModel(new DefaultComboBoxModel(queryDevices()));
/* 226:    */         
/* 227:224 */         this.jLabel2.setText("message.selectDate[:]");
/* 228:    */         
/* 229:226 */         this.jComboBox1.addItemListener(new ItemListener()
/* 230:    */         {
/* 231:    */           public void itemStateChanged(ItemEvent ie)
/* 232:    */           {
/* 233:228 */             HistoryEventDialog.this.canDelete = false;
/* 234:    */           }
/* 235:231 */         });
/* 236:232 */         this.jLabel3.setIcon(new ImageIcon(Constants.DATE));
/* 237:233 */         this.jLabel3.addMouseListener(new MouseAdapter()
/* 238:    */         {
/* 239:    */           public void mouseClicked(MouseEvent e)
/* 240:    */           {
/* 241:236 */             HistoryEventDialog.this.canDelete = false;
/* 242:237 */             MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryEventDialog.this.jTextField1);
/* 243:238 */             calendarPanel.setBounds(HistoryEventDialog.this.getX() + HistoryEventDialog.this.jLabel3.getX() + 30, HistoryEventDialog.this.getY() + 
/* 244:239 */               HistoryEventDialog.this.jLabel3.getY() + 10, calendarPanel.getWidth(), 
/* 245:240 */               calendarPanel.getHeight());
/* 246:241 */             calendarPanel.setVisible(true);
/* 247:    */           }
/* 248:244 */         });
/* 249:245 */         this.jTextField1.setEditable(false);
/* 250:246 */         this.jTextField1.setBackground(I18NListener.bgColor);
/* 251:247 */         this.jTextField1.setText(DateUtils.getNowDate());
/* 252:248 */         this.jTextField1.addMouseListener(new MouseAdapter()
/* 253:    */         {
/* 254:    */           public void mouseClicked(MouseEvent e)
/* 255:    */           {
/* 256:251 */             HistoryEventDialog.this.canDelete = false;
/* 257:252 */             MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryEventDialog.this.jTextField1);
/* 258:253 */             calendarPanel.setBounds(HistoryEventDialog.this.getX() + HistoryEventDialog.this.jLabel3.getX() + 30, HistoryEventDialog.this.getY() + 
/* 259:254 */               HistoryEventDialog.this.jLabel3.getY() + 10, calendarPanel.getWidth(), 
/* 260:255 */               calendarPanel.getHeight());
/* 261:256 */             calendarPanel.setVisible(true);
/* 262:    */           }
/* 263:259 */         });
/* 264:260 */         this.jLabel4.setText("--");
/* 265:    */         
/* 266:262 */         this.jLabel5.setIcon(new ImageIcon(Constants.DATE));
/* 267:263 */         this.jLabel5.addMouseListener(new MouseAdapter()
/* 268:    */         {
/* 269:    */           public void mouseClicked(MouseEvent e)
/* 270:    */           {
/* 271:266 */             HistoryEventDialog.this.canDelete = false;
/* 272:267 */             MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryEventDialog.this.jTextField2);
/* 273:268 */             calendarPanel.setBounds(HistoryEventDialog.this.getX() + HistoryEventDialog.this.jLabel5.getX() + 30, HistoryEventDialog.this.getY() + 
/* 274:269 */               HistoryEventDialog.this.jLabel5.getY() + 10, calendarPanel.getWidth(), 
/* 275:270 */               calendarPanel.getHeight());
/* 276:271 */             calendarPanel.setVisible(true);
/* 277:    */           }
/* 278:274 */         });
/* 279:275 */         this.jTextField2.setEditable(false);
/* 280:276 */         this.jTextField2.setBackground(I18NListener.bgColor);
/* 281:277 */         this.jTextField2.setText(DateUtils.getNowDate());
/* 282:278 */         this.jTextField2.addMouseListener(new MouseAdapter()
/* 283:    */         {
/* 284:    */           public void mouseClicked(MouseEvent e)
/* 285:    */           {
/* 286:281 */             HistoryEventDialog.this.canDelete = false;
/* 287:282 */             MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryEventDialog.this.jTextField2);
/* 288:283 */             calendarPanel.setBounds(HistoryEventDialog.this.getX() + HistoryEventDialog.this.jLabel5.getX() + 30, HistoryEventDialog.this.getY() + 
/* 289:284 */               HistoryEventDialog.this.jLabel5.getY() + 10, calendarPanel.getWidth(), 
/* 290:285 */               calendarPanel.getHeight());
/* 291:286 */             calendarPanel.setVisible(true);
/* 292:    */           }
/* 293:289 */         });
/* 294:290 */         this.jCheckBox1.setText("message.includeinfo");
/* 295:291 */         this.jCheckBox1.setSelected(true);
/* 296:    */         
/* 297:293 */         this.jButton3.setText("message.view");
/* 298:294 */         this.jButton3.addActionListener(new ActionListener()
/* 299:    */         {
/* 300:    */           public void actionPerformed(ActionEvent e)
/* 301:    */           {
/* 302:297 */             HistoryEventDialog.this.canDelete = true;
/* 303:298 */             HistoryEventDialog.this.queryHistoryEventRecord();
/* 304:    */           }
/* 305:301 */         });
/* 306:302 */         this.deleteButton.setText("message.del");
/* 307:303 */         this.deleteButton.addActionListener(new ActionListener()
/* 308:    */         {
/* 309:    */           public void actionPerformed(ActionEvent e)
/* 310:    */           {
/* 311:306 */             if (!SolarPowerTray.isLogin)
/* 312:    */             {
/* 313:307 */               new LoginJDialog(new Frame(), true);
/* 314:308 */               return;
/* 315:    */             }
/* 316:310 */             HistoryEventDialog.this.deleteEvents();
/* 317:    */           }
/* 318:313 */         });
/* 319:314 */         this.deleteAllButton.setText("message.deleteAll");
/* 320:315 */         this.deleteAllButton.addActionListener(new ActionListener()
/* 321:    */         {
/* 322:    */           public void actionPerformed(ActionEvent e)
/* 323:    */           {
/* 324:318 */             if (!SolarPowerTray.isLogin)
/* 325:    */             {
/* 326:319 */               new LoginJDialog(new Frame(), true);
/* 327:320 */               return;
/* 328:    */             }
/* 329:322 */             HistoryEventDialog.this.clearEvents();
/* 330:    */           }
/* 331:325 */         });
/* 332:326 */         this.saveAsButton.setText("message.saveas");
/* 333:327 */         this.saveAsButton.addActionListener(new ActionListener()
/* 334:    */         {
/* 335:    */           public void actionPerformed(ActionEvent e)
/* 336:    */           {
/* 337:330 */             if (!SolarPowerTray.isLogin)
/* 338:    */             {
/* 339:331 */               new LoginJDialog(new Frame(), true);
/* 340:332 */               return;
/* 341:    */             }
/* 342:334 */             HistoryEventDialog.this.saveAs();
/* 343:    */           }
/* 344:337 */         });
/* 345:338 */         this.jScrollPane1.setViewportView(this.jTable1);
/* 346:    */         
/* 347:340 */         this.jScrollPane2.setViewportView(this.jTable2);
/* 348:    */         try
/* 349:    */         {
/* 350:344 */           this.dataset = new DefaultCategoryDataset();
/* 351:345 */           this.chart = 
/* 352:346 */             ChartFactory.createBarChart("", "ID", bd.getString("message.count"), this.dataset, PlotOrientation.VERTICAL, false, true, false);
/* 353:347 */           this.chart.setBackgroundPaint(Constants.BG_COLOR);
/* 354:    */           
/* 355:349 */           this.categoryPlot = this.chart.getCategoryPlot();
/* 356:350 */           this.categoryPlot.setBackgroundPaint(Constants.BG_COLOR);
/* 357:351 */           this.categoryPlot.setDomainGridlinePaint(Constants.BG_COLOR);
/* 358:352 */           this.categoryPlot.setDomainGridlinesVisible(true);
/* 359:353 */           this.categoryPlot.setRangeGridlinePaint(Color.white);
/* 360:    */           
/* 361:    */ 
/* 362:356 */           CategoryAxis categoryAxis = this.categoryPlot.getDomainAxis();
/* 363:357 */           categoryAxis.setLabelPaint(Color.white);
/* 364:358 */           categoryAxis.setTickLabelPaint(Color.white);
/* 365:    */           
/* 366:    */ 
/* 367:    */ 
/* 368:362 */           ValueAxis rangeAxis = this.categoryPlot.getRangeAxis();
/* 369:363 */           rangeAxis.setUpperMargin(0.1D);
/* 370:364 */           rangeAxis.setLabelPaint(Color.white);
/* 371:365 */           rangeAxis.setTickLabelPaint(Color.white);
/* 372:366 */           rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/* 373:    */           
/* 374:    */ 
/* 375:369 */           BarRenderer barRenderer = (BarRenderer)this.categoryPlot.getRenderer();
/* 376:370 */           barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
/* 377:371 */           barRenderer.setBaseItemLabelsVisible(true);
/* 378:    */           
/* 379:373 */           barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
/* 380:374 */             ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
/* 381:375 */           barRenderer.setItemLabelsVisible(true);
/* 382:    */           
/* 383:377 */           barRenderer.setPaint(new Color(232, 107, 30));
/* 384:    */           
/* 385:379 */           barRenderer.setMaxBarWidth(0.07000000000000001D);
/* 386:380 */           barRenderer.setItemLabelPaint(Color.white);
/* 387:    */           
/* 388:382 */           ChartPanel chartPane = new ChartPanel(this.chart);
/* 389:383 */           chartPane.setPreferredSize(new Dimension(480, 250));
/* 390:384 */           this.jScrollPane3.setViewportView(chartPane);
/* 391:    */         }
/* 392:    */         catch (Exception ex)
/* 393:    */         {
/* 394:386 */           System.err.println("history event chart error");
/* 395:    */         }
/* 396:389 */         this.jSeparator1.setOrientation(1);
/* 397:    */         
/* 398:391 */         GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 399:392 */         this.jPanel1.setLayout(jPanel1Layout);
/* 400:393 */         jPanel1Layout.setHorizontalGroup(
/* 401:394 */           jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 402:395 */           .addGroup(jPanel1Layout.createSequentialGroup()
/* 403:396 */           .addContainerGap()
/* 404:397 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 405:398 */           .addGroup(jPanel1Layout.createSequentialGroup()
/* 406:399 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 407:400 */           .addGroup(jPanel1Layout.createSequentialGroup()
/* 408:401 */           .addComponent(this.saveAsButton)
/* 409:402 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 410:403 */           .addComponent(this.deleteButton)
/* 411:404 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 412:405 */           .addComponent(this.deleteAllButton))
/* 413:406 */           .addComponent(this.jScrollPane1, -2, 550, -2))
/* 414:407 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 415:408 */           .addComponent(this.jSeparator1, -2, -1, -2)
/* 416:409 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 417:410 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 418:411 */           .addComponent(this.jScrollPane2, -1, 500, 32767)
/* 419:412 */           .addComponent(this.jScrollPane3, -1, 500, 32767)))
/* 420:413 */           .addGroup(jPanel1Layout.createSequentialGroup()
/* 421:414 */           .addComponent(this.jLabel1)
/* 422:415 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 423:416 */           .addComponent(this.jComboBox1, -2, 140, -2)
/* 424:417 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 425:418 */           .addComponent(this.jLabel2)
/* 426:419 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 427:420 */           .addComponent(this.jTextField1, -2, 104, -2)
/* 428:421 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 429:422 */           .addComponent(this.jLabel3)
/* 430:423 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 431:424 */           .addComponent(this.jLabel4)
/* 432:425 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 433:426 */           .addComponent(this.jTextField2, -2, 104, -2)
/* 434:427 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 435:428 */           .addComponent(this.jLabel5)
/* 436:429 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 437:430 */           .addComponent(this.jCheckBox1)
/* 438:431 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 439:432 */           .addComponent(this.jButton3)))
/* 440:433 */           .addContainerGap()));
/* 441:    */         
/* 442:435 */         jPanel1Layout.setVerticalGroup(
/* 443:436 */           jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 444:437 */           .addGroup(jPanel1Layout.createSequentialGroup()
/* 445:438 */           .addContainerGap()
/* 446:439 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 447:440 */           .addComponent(this.jTextField1, -2, -1, -2)
/* 448:441 */           .addComponent(this.jLabel3)
/* 449:442 */           .addComponent(this.jLabel4)
/* 450:443 */           .addComponent(this.jTextField2, -2, -1, -2)
/* 451:444 */           .addComponent(this.jLabel5)
/* 452:445 */           .addComponent(this.jLabel2)
/* 453:446 */           .addComponent(this.jComboBox1, -2, -1, -2)
/* 454:447 */           .addComponent(this.jLabel1)
/* 455:448 */           .addComponent(this.jCheckBox1)
/* 456:449 */           .addComponent(this.jButton3))
/* 457:450 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 458:451 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 459:452 */           .addComponent(this.jSeparator1, -1, 500, 32767)
/* 460:453 */           .addGroup(jPanel1Layout.createSequentialGroup()
/* 461:454 */           .addComponent(this.jScrollPane2, -2, 240, -2)
/* 462:455 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 463:456 */           .addComponent(this.jScrollPane3, -1, 260, 32767))
/* 464:457 */           .addGroup(jPanel1Layout.createSequentialGroup()
/* 465:458 */           .addComponent(this.jScrollPane1, -2, 465, -2)
/* 466:459 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 467:460 */           .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 468:461 */           .addComponent(this.deleteAllButton)
/* 469:462 */           .addComponent(this.deleteButton)
/* 470:463 */           .addComponent(this.saveAsButton))))
/* 471:464 */           .addContainerGap()));
/* 472:    */         
/* 473:    */ 
/* 474:467 */         getContentPane().add(this.jPanel1, "Center");
/* 475:    */         
/* 476:469 */         this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 477:    */         
/* 478:471 */         this.closeButton.setText("message.close");
/* 479:472 */         this.closeButton.addActionListener(new ActionListener()
/* 480:    */         {
/* 481:    */           public void actionPerformed(ActionEvent e)
/* 482:    */           {
/* 483:475 */             HistoryEventDialog.this.dispose();
/* 484:    */           }
/* 485:478 */         });
/* 486:479 */         GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 487:480 */         this.jPanel2.setLayout(jPanel2Layout);
/* 488:481 */         jPanel2Layout.setHorizontalGroup(
/* 489:482 */           jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 490:483 */           .addGroup(GroupLayout.Alignment.TRAILING, 
/* 491:484 */           jPanel2Layout.createSequentialGroup().addContainerGap(730, 32767)
/* 492:485 */           .addComponent(this.closeButton)
/* 493:486 */           .addGap(12, 12, 12)));
/* 494:    */         
/* 495:488 */         jPanel2Layout.setVerticalGroup(
/* 496:489 */           jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 497:490 */           .addGroup(GroupLayout.Alignment.TRAILING, 
/* 498:491 */           jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767)
/* 499:492 */           .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 500:493 */           .addComponent(this.closeButton))
/* 501:494 */           .addContainerGap()));
/* 502:    */         
/* 503:    */ 
/* 504:497 */         getContentPane().add(this.jPanel2, "Last");
/* 505:    */         
/* 506:499 */         pack();
/* 507:    */       }
/* 508:    */       
/* 509:    */       public void queryHistoryEventRecord()
/* 510:    */       {
/* 511:503 */         if (this.jComboBox1.getSelectedItem() != null)
/* 512:    */         {
/* 513:504 */           String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 514:505 */           String startDate = this.jTextField1.getText().trim();
/* 515:506 */           String endDate = this.jTextField2.getText().trim();
/* 516:507 */           boolean includeinfo = this.jCheckBox1.isSelected();
/* 517:508 */           if ((serialno != null) && (!"".equals(serialno)) && (startDate != null) && 
/* 518:509 */             (!"".equals(startDate)) && (endDate != null) && 
/* 519:510 */             (!"".equals(endDate)))
/* 520:    */           {
/* 521:511 */             DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 522:512 */             Date fromDate = new Date();
/* 523:513 */             Date toDate = new Date();
/* 524:    */             try
/* 525:    */             {
/* 526:515 */               fromDate = format.parse(startDate);
/* 527:516 */               toDate = format.parse(endDate);
/* 528:    */             }
/* 529:    */             catch (Exception ex)
/* 530:    */             {
/* 531:518 */               ex.printStackTrace();
/* 532:    */             }
/* 533:520 */             this.eventlist = this.eventDao.queryEvent(serialno, fromDate, toDate, includeinfo);
/* 534:521 */             this.eventtotal = this.eventDao.queryEventStatistics(serialno, fromDate, toDate, includeinfo);
/* 535:522 */             setTableLeft();
/* 536:523 */             setTableRight();
/* 537:524 */             setTableFormate();
/* 538:525 */             if (this.dataset != null) {
/* 539:526 */               this.dataset.clear();
/* 540:    */             }
/* 541:528 */             for (int i = 0; i < this.eventtotal.size(); i++) {
/* 542:529 */               this.dataset.addValue(((EventStatisItem)this.eventtotal.get(i)).getCount(), "ID", ((EventStatisItem)this.eventtotal.get(i)).getEventId());
/* 543:    */             }
/* 544:    */           }
/* 545:    */         }
/* 546:    */       }
/* 547:    */       
/* 548:    */       private void setTableRight()
/* 549:    */       {
/* 550:536 */         int size = this.eventtotal.size();
/* 551:537 */         if (size < 10) {
/* 552:538 */           size = 10;
/* 553:    */         }
/* 554:540 */         Object[][] obj = new Object[size][4];
/* 555:541 */         for (int i = 0; i < this.eventtotal.size(); i++)
/* 556:    */         {
/* 557:542 */           EventStatisItem eventStatisItem = (EventStatisItem)this.eventtotal.get(i);
/* 558:543 */           int level = eventStatisItem.getLevel();
/* 559:544 */           String levelStr = "";
/* 560:545 */           if (level == 1) {
/* 561:546 */             levelStr = "message.fault";
/* 562:547 */           } else if (level == 2) {
/* 563:548 */             levelStr = "message.warning";
/* 564:549 */           } else if (level == 3) {
/* 565:550 */             levelStr = "message.info";
/* 566:    */           }
/* 567:552 */           obj[i][0] = eventStatisItem.getEventId();
/* 568:553 */           obj[i][1] = levelStr;
/* 569:554 */           obj[i][2] = eventStatisItem.getName();
/* 570:555 */           obj[i][3] = Integer.valueOf(eventStatisItem.getCount());
/* 571:    */         }
/* 572:557 */         if (this.eventtotal.size() < 10) {
/* 573:558 */           for (int i = this.eventtotal.size(); i < 10; i++) {
/* 574:559 */             for (int j = 0; j < 4; j++) {
/* 575:560 */               obj[i][j] = null;
/* 576:    */             }
/* 577:    */           }
/* 578:    */         }
/* 579:564 */         this.tableModel2.setDataVector(obj, this.tableTile2);
/* 580:    */       }
/* 581:    */       
/* 582:    */       private void setTableLeft()
/* 583:    */       {
/* 584:568 */         int size = this.eventlist.size();
/* 585:569 */         if (size < 22) {
/* 586:570 */           size = 22;
/* 587:    */         }
/* 588:572 */         Object[][] obj = new Object[size][4];
/* 589:573 */         for (int i = 0; i < this.eventlist.size(); i++)
/* 590:    */         {
/* 591:574 */           EventData eventData = EventsHandler.getEventById(((EventDataRecord)this.eventlist.get(i)).getProdId(), 
/* 592:575 */             ((EventDataRecord)this.eventlist.get(i)).getEventId());
/* 593:576 */           int level = eventData.getEventLevel();
/* 594:577 */           String levelStr = "";
/* 595:578 */           if (level == 1) {
/* 596:579 */             levelStr = "message.fault";
/* 597:580 */           } else if (level == 2) {
/* 598:581 */             levelStr = "message.warning";
/* 599:582 */           } else if (level == 3) {
/* 600:583 */             levelStr = "message.info";
/* 601:    */           }
/* 602:585 */           obj[i][0] = eventData.getEventId();
/* 603:586 */           obj[i][1] = levelStr;
/* 604:587 */           obj[i][2] = DateUtils.getFormatTimestamp(((EventDataRecord)this.eventlist.get(i)).getTrandate());
/* 605:588 */           obj[i][3] = eventData.getEventName();
/* 606:    */         }
/* 607:590 */         if (this.eventlist.size() < 22) {
/* 608:591 */           for (int i = this.eventlist.size(); i < 22; i++) {
/* 609:592 */             for (int j = 0; j < 4; j++) {
/* 610:593 */               obj[i][j] = null;
/* 611:    */             }
/* 612:    */           }
/* 613:    */         }
/* 614:597 */         this.tableModel1.setDataVector(obj, this.tableTile);
/* 615:    */       }
/* 616:    */       
/* 617:    */       public void setTableFormate()
/* 618:    */       {
/* 619:601 */         TableColumnModel tcm = this.jTable1.getColumnModel();
/* 620:602 */         TableColumn tc0 = tcm.getColumn(0);
/* 621:603 */         TableColumn tc1 = tcm.getColumn(1);
/* 622:604 */         TableColumn tc2 = tcm.getColumn(2);
/* 623:605 */         TableColumn tc3 = tcm.getColumn(3);
/* 624:606 */         tc0.setPreferredWidth(10);
/* 625:607 */         tc1.setPreferredWidth(25);
/* 626:608 */         tc2.setPreferredWidth(80);
/* 627:609 */         tc3.setPreferredWidth(250);
/* 628:    */         
/* 629:611 */         TableColumnModel tcm2 = this.jTable2.getColumnModel();
/* 630:612 */         TableColumn tcc0 = tcm2.getColumn(0);
/* 631:613 */         TableColumn tcc1 = tcm2.getColumn(1);
/* 632:614 */         TableColumn tcc2 = tcm2.getColumn(2);
/* 633:615 */         TableColumn tcc3 = tcm2.getColumn(3);
/* 634:616 */         tcc0.setPreferredWidth(10);
/* 635:617 */         tcc1.setPreferredWidth(25);
/* 636:618 */         tcc2.setPreferredWidth(250);
/* 637:619 */         tcc3.setPreferredWidth(80);
/* 638:    */       }
/* 639:    */       
/* 640:    */       public String[] queryDevices()
/* 641:    */       {
/* 642:623 */         List<String> devicelist = this.eventDao.queryDevices();
/* 643:624 */         String[] devices = new String[devicelist.size()];
/* 644:625 */         for (int i = 0; i < devicelist.size(); i++) {
/* 645:626 */           devices[i] = ((String)devicelist.get(i));
/* 646:    */         }
/* 647:628 */         return devices;
/* 648:    */       }
/* 649:    */       
/* 650:    */       public void deleteEvents()
/* 651:    */       {
/* 652:632 */         if (this.jComboBox1.getSelectedItem() != null)
/* 653:    */         {
/* 654:633 */           String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 655:634 */           int re = DisplayMessage.showConfirmDialog("message.deleteConfirm", "message.info");
/* 656:635 */           if ((re == 0) && 
/* 657:636 */             (serialno != null) && (!"".equals(serialno)))
/* 658:    */           {
/* 659:637 */             int row = this.jTable1.getSelectedRow();
/* 660:638 */             if (row > -1)
/* 661:    */             {
/* 662:639 */               Object obj = this.tableModel1.getValueAt(row, 2);
/* 663:640 */               Object obj2 = this.tableModel1.getValueAt(row, 0);
/* 664:641 */               if ((obj != null) && (obj2 != null))
/* 665:    */               {
/* 666:642 */                 SimpleDateFormat sdf = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 667:643 */                 Date trandate = new Date();
/* 668:    */                 try
/* 669:    */                 {
/* 670:645 */                   trandate = sdf.parse(obj.toString().trim());
/* 671:    */                 }
/* 672:    */                 catch (Exception ex)
/* 673:    */                 {
/* 674:647 */                   ex.printStackTrace();
/* 675:    */                 }
/* 676:649 */                 String eventid = obj2.toString().trim();
/* 677:650 */                 boolean result = this.eventDao.deleteEventRecord(serialno, eventid, trandate);
/* 678:651 */                 if (result) {
/* 679:652 */                   queryHistoryEventRecord();
/* 680:    */                 } else {
/* 681:654 */                   DisplayMessage.showErrorDialog("message.operationfailure");
/* 682:    */                 }
/* 683:    */               }
/* 684:    */               else
/* 685:    */               {
/* 686:657 */                 DisplayMessage.showErrorDialog("message.pleaseselect");
/* 687:    */               }
/* 688:    */             }
/* 689:    */             else
/* 690:    */             {
/* 691:660 */               DisplayMessage.showErrorDialog("message.pleaseselect");
/* 692:    */             }
/* 693:    */           }
/* 694:    */         }
/* 695:    */       }
/* 696:    */       
/* 697:    */       public void clearEvents()
/* 698:    */       {
/* 699:668 */         if ((this.canDelete) && 
/* 700:669 */           (this.jComboBox1.getSelectedItem() != null))
/* 701:    */         {
/* 702:670 */           String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 703:671 */           String startDate = this.jTextField1.getText().trim();
/* 704:672 */           String endDate = this.jTextField2.getText().trim();
/* 705:673 */           int re = DisplayMessage.showConfirmDialog("message.deleteConfirm", "message.info");
/* 706:674 */           if ((re == 0) && 
/* 707:675 */             (serialno != null) && (!"".equals(serialno)) && (startDate != null) && 
/* 708:676 */             (!"".equals(startDate)) && (endDate != null) && 
/* 709:677 */             (!"".equals(endDate)))
/* 710:    */           {
/* 711:678 */             DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 712:679 */             Date fromDate = new Date();
/* 713:680 */             Date toDate = new Date();
/* 714:    */             try
/* 715:    */             {
/* 716:682 */               fromDate = format.parse(startDate);
/* 717:683 */               toDate = format.parse(endDate);
/* 718:    */             }
/* 719:    */             catch (Exception ex)
/* 720:    */             {
/* 721:685 */               ex.printStackTrace();
/* 722:    */             }
/* 723:687 */             boolean result = this.eventDao.deleteEventRecordAll(serialno, fromDate, toDate);
/* 724:688 */             if (result) {
/* 725:689 */               queryHistoryEventRecord();
/* 726:    */             } else {
/* 727:691 */               DisplayMessage.showErrorDialog("message.operationfailure");
/* 728:    */             }
/* 729:    */           }
/* 730:    */         }
/* 731:    */       }
/* 732:    */       
/* 733:    */       public int getSaveDialog()
/* 734:    */       {
/* 735:700 */         String saveFileName = "History_events.pdf";
/* 736:701 */         this.fDialog = new JFileChooser();
/* 737:702 */         PDFFileFilter txtFilter = new PDFFileFilter();
/* 738:703 */         this.fDialog.addChoosableFileFilter(txtFilter);
/* 739:704 */         this.fDialog.setFileFilter(txtFilter);
/* 740:705 */         this.fDialog.setAcceptAllFileFilterUsed(true);
/* 741:706 */         this.fDialog.setSelectedFile(new File(saveFileName));
/* 742:707 */         return this.fDialog.showSaveDialog(this);
/* 743:    */       }
/* 744:    */       
/* 745:    */       public void saveAs()
/* 746:    */       {
/* 747:711 */         int result = getSaveDialog();
/* 748:712 */         if (result == 0)
/* 749:    */         {
/* 750:713 */           String filepathStr = this.fDialog.getSelectedFile().getPath();
/* 751:714 */           File newFile = new File(filepathStr);
/* 752:715 */           if (newFile.exists())
/* 753:    */           {
/* 754:716 */             int re = 
/* 755:717 */               DisplayMessage.showConfirmDialog(
/* 756:718 */               "message.confirmoverwrite", 
/* 757:719 */               "message.info");
/* 758:720 */             if (re == 0) {
/* 759:721 */               createPdf(filepathStr);
/* 760:    */             } else {
/* 761:723 */               saveAs();
/* 762:    */             }
/* 763:    */           }
/* 764:    */           else
/* 765:    */           {
/* 766:    */             try
/* 767:    */             {
/* 768:727 */               newFile.createNewFile();
/* 769:    */             }
/* 770:    */             catch (IOException e)
/* 771:    */             {
/* 772:729 */               e.printStackTrace();
/* 773:    */             }
/* 774:731 */             createPdf(filepathStr);
/* 775:    */           }
/* 776:    */         }
/* 777:    */       }
/* 778:    */       
/* 779:    */       public void createPdf(String path)
/* 780:    */       {
/* 781:737 */         Document pdf = new Document(PageSize.A4);
/* 782:    */         try
/* 783:    */         {
/* 784:739 */           PdfWriter.getInstance(pdf, new FileOutputStream(path));
/* 785:740 */           pdf.addTitle("History event datas");
/* 786:741 */           pdf.addAuthor(GlobalVariables.customerConfig.getCustomerName());
/* 787:742 */           pdf.open();
/* 788:743 */           BaseFont bf = BaseFont.createFont(Constants.PDF_STYLE_PATH + "simsun.ttc,1", "Identity-H", false);
/* 789:744 */           Font datafont = new Font(bf, 12.0F, 0, BaseColor.BLACK);
/* 790:745 */           String[] titles = this.tableTile;
/* 791:746 */           PdfPTable table = new PdfPTable(titles.length);
/* 792:747 */           for (int i = 0; i < titles.length; i++)
/* 793:    */           {
/* 794:748 */             PdfPCell cell = new PdfPCell(new Paragraph(bd.getString(titles[i]), datafont));
/* 795:749 */             cell.setHorizontalAlignment(1);
/* 796:750 */             table.addCell(cell);
/* 797:    */           }
/* 798:752 */           for (int i = 0; i < this.eventlist.size(); i++)
/* 799:    */           {
/* 800:753 */             EventData eventData = EventsHandler.getEventById(((EventDataRecord)this.eventlist.get(i)).getProdId(), 
/* 801:754 */               ((EventDataRecord)this.eventlist.get(i)).getEventId());
/* 802:755 */             int level = eventData.getEventLevel();
/* 803:756 */             String levelStr = "";
/* 804:757 */             if (level == 1) {
/* 805:758 */               levelStr = "message.fault";
/* 806:759 */             } else if (level == 2) {
/* 807:760 */               levelStr = "message.warning";
/* 808:761 */             } else if (level == 3) {
/* 809:762 */               levelStr = "message.info";
/* 810:    */             }
/* 811:764 */             table.addCell(new Paragraph(eventData.getEventId(), datafont));
/* 812:765 */             table.addCell(new Paragraph(bd.getString(levelStr), datafont));
/* 813:766 */             table.addCell(new Paragraph(DateUtils.getFormatTimestamp(((EventDataRecord)this.eventlist.get(i)).getTrandate()), datafont));
/* 814:767 */             table.addCell(new Paragraph(eventData.getEventName(), datafont));
/* 815:    */           }
/* 816:770 */           table.setWidthPercentage(100.0F);
/* 817:771 */           pdf.add(table);
/* 818:772 */           Thread.sleep(1500L);
/* 819:    */         }
/* 820:    */         catch (Exception ie)
/* 821:    */         {
/* 822:775 */           DisplayMessage.showErrorDialog("message.saveerror");
/* 823:    */           try
/* 824:    */           {
/* 825:778 */             if (pdf != null) {
/* 826:779 */               pdf.close();
/* 827:    */             }
/* 828:    */           }
/* 829:    */           catch (Exception ex)
/* 830:    */           {
/* 831:782 */             ex.printStackTrace();
/* 832:    */           }
/* 833:    */         }
/* 834:    */         finally
/* 835:    */         {
/* 836:    */           try
/* 837:    */           {
/* 838:778 */             if (pdf != null) {
/* 839:779 */               pdf.close();
/* 840:    */             }
/* 841:    */           }
/* 842:    */           catch (Exception ex)
/* 843:    */           {
/* 844:782 */             ex.printStackTrace();
/* 845:    */           }
/* 846:    */         }
/* 847:    */       }
/* 848:    */     }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.HistoryEventDialog
 * JD-Core Version:    0.7.0.1
 */