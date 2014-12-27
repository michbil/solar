/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.dao.DeviceDao;
/*   8:    */ import cn.com.voltronic.solar.dao.WorkDataDao;
/*   9:    */ import cn.com.voltronic.solar.data.bean.DeviceBean;
/*  10:    */ import cn.com.voltronic.solar.data.bean.HistoryData;
/*  11:    */ import cn.com.voltronic.solar.data.bean.HistoryDataColumns;
/*  12:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*  13:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  14:    */ import cn.com.voltronic.solar.protocol.P30;
/*  15:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  16:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  17:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  18:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  19:    */ import cn.com.voltronic.solar.view.component.AADefaultTableModel;
/*  20:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  21:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  22:    */ import cn.com.voltronic.solar.view.component.AATabbedPane;
/*  23:    */ import cn.com.voltronic.solar.view.component.CheckBoxData;
/*  24:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  25:    */ import cn.com.voltronic.solar.view.component.I18NBundle;
/*  26:    */ import cn.com.voltronic.solar.view.component.I18NListener;
/*  27:    */ import cn.com.voltronic.solar.view.component.JComboCheckBox;
/*  28:    */ import cn.com.voltronic.solar.view.component.MyCalendarPanel;
/*  29:    */ import com.itextpdf.text.BaseColor;
/*  30:    */ import com.itextpdf.text.Document;
/*  31:    */ import com.itextpdf.text.Font;
/*  32:    */ import com.itextpdf.text.PageSize;
/*  33:    */ import com.itextpdf.text.Paragraph;
/*  34:    */ import com.itextpdf.text.pdf.BaseFont;
/*  35:    */ import com.itextpdf.text.pdf.PdfPCell;
/*  36:    */ import com.itextpdf.text.pdf.PdfPTable;
/*  37:    */ import com.itextpdf.text.pdf.PdfWriter;
/*  38:    */ import java.awt.BorderLayout;
/*  39:    */ import java.awt.Color;
/*  40:    */ import java.awt.Container;
/*  41:    */ import java.awt.Frame;
/*  42:    */ import java.awt.event.ActionEvent;
/*  43:    */ import java.awt.event.ActionListener;
/*  44:    */ import java.awt.event.ItemEvent;
/*  45:    */ import java.awt.event.ItemListener;
/*  46:    */ import java.awt.event.MouseAdapter;
/*  47:    */ import java.awt.event.MouseEvent;
/*  48:    */ import java.io.File;
/*  49:    */ import java.io.FileOutputStream;
/*  50:    */ import java.io.IOException;
/*  51:    */ import java.text.DateFormat;
/*  52:    */ import java.text.SimpleDateFormat;
/*  53:    */ import java.util.ArrayList;
/*  54:    */ import java.util.Date;
/*  55:    */ import java.util.List;
/*  56:    */ import javax.swing.BorderFactory;
/*  57:    */ import javax.swing.DefaultComboBoxModel;
/*  58:    */ import javax.swing.GroupLayout;
/*  59:    */ import javax.swing.GroupLayout.Alignment;
/*  60:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  61:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  62:    */ import javax.swing.ImageIcon;
/*  63:    */ import javax.swing.JComboBox;
/*  64:    */ import javax.swing.JFileChooser;
/*  65:    */ import javax.swing.JPanel;
/*  66:    */ import javax.swing.JScrollPane;
/*  67:    */ import javax.swing.JTable;
/*  68:    */ import javax.swing.JTextField;
/*  69:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  70:    */ import javax.swing.table.JTableHeader;
/*  71:    */ import javax.swing.table.TableColumn;
/*  72:    */ import javax.swing.table.TableColumnModel;
/*  73:    */ 
/*  74:    */ public class HistoryDataJDialog
/*  75:    */   extends AADialog
/*  76:    */ {
/*  77:    */   private static final long serialVersionUID = 7150098148061598284L;
/*  78:    */   private HistoryDataChart chartPanel;
/*  79:    */   private JPanel dataPanel;
/*  80:    */   private AAButton deleteAllButton;
/*  81:    */   private AAButton deleteButton;
/*  82:    */   private AAButton closeButton;
/*  83:    */   private AAButton viewButton;
/*  84:    */   private AAButton saveAsButton;
/*  85:    */   private JComboBox jComboBox1;
/*  86:    */   private AALabel jLabel1;
/*  87:    */   private AALabel jLabel2;
/*  88:    */   private AALabel jLabel3;
/*  89:    */   private AALabel jLabel4;
/*  90:    */   private AALabel jLabel5;
/*  91:    */   private AALabel jLabel6;
/*  92:    */   private AALabel jLabel7;
/*  93:    */   private AALabel jLabel8;
/*  94:    */   private AALabel jLabel9;
/*  95:    */   private JPanel jPanel3;
/*  96:    */   private JPanel jPanel4;
/*  97:    */   private JScrollPane jScrollPane1;
/*  98:    */   private JTable jTable1;
/*  99:    */   private JTextField jTextField1;
/* 100:    */   private JTextField jTextField2;
/* 101:    */   private AATabbedPane mainTabbedPane;
/* 102:    */   private JComboCheckBox viewColumns;
/* 103:104 */   private boolean canDelete = false;
/* 104:105 */   private WorkDataDao workDao = null;
/* 105:106 */   private DeviceDao deviceDao = null;
/* 106:107 */   private IProtocol protocol = null;
/* 107:    */   private JFileChooser fDialog;
/* 108:109 */   private AADefaultTableModel tableModel = null;
/* 109:110 */   private List<WorkInfo> workInfolist = new ArrayList();
/* 110:111 */   public WorkInfo workInfo = null;
/* 111:112 */   private final int MAX_SIZE = 21;
/* 112:113 */   private int MAX_COLUMN = 8;
/* 113:114 */   private String[] tableTile = { "Input date", "Fault message", 
/* 114:115 */     "Grid voltage", "Output current", 
/* 115:116 */     "Grid frequency", "PV1 input voltage", 
/* 116:117 */     "PV1 input frequency", "Temperature" };
/* 117:    */   
/* 118:    */   public HistoryDataJDialog(Frame parent, boolean modal)
/* 119:    */   {
/* 120:120 */     super(parent, modal);
/* 121:121 */     this.protocol = new P30();
/* 122:122 */     this.deviceDao = new DeviceDao();
/* 123:123 */     this.workDao = new WorkDataDao();
/* 124:124 */     this.workInfo = new WorkInfo();
/* 125:125 */     initComponents();
/* 126:126 */     setTitle("message.queryData");
/* 127:127 */     setLocationRelativeTo(null);
/* 128:128 */     setVisible(true);
/* 129:    */   }
/* 130:    */   
/* 131:    */   private List<HistoryData> getHistoryDataColumns()
/* 132:    */   {
/* 133:132 */     List<HistoryData> list = new ArrayList();
/* 134:133 */     Object selected = this.jComboBox1.getSelectedItem();
/* 135:134 */     if (selected != null)
/* 136:    */     {
/* 137:135 */       String serialno = selected.toString().trim();
/* 138:136 */       DeviceBean bean = this.deviceDao.queryDevicebySerialno("P30", serialno);
/* 139:138 */       if ((bean != null) && (bean.getProdid().equals("P30")))
/* 140:    */       {
/* 141:139 */         P30 temp = new P30();
/* 142:140 */         temp.setOutputMode(bean.getParallel());
/* 143:141 */         this.protocol = temp;
/* 144:    */       }
/* 145:    */       else
/* 146:    */       {
/* 147:143 */         this.protocol = new P30();
/* 148:    */       }
/* 149:    */     }
/* 150:146 */     HistoryDataColumns data = this.protocol.getHistoryColumns();
/* 151:147 */     list = data.getColumns();
/* 152:148 */     return list;
/* 153:    */   }
/* 154:    */   
/* 155:    */   private List<HistoryData> getHistoryDataColumnsView()
/* 156:    */   {
/* 157:152 */     List<HistoryData> columns = getHistoryDataColumns();
/* 158:153 */     List<HistoryData> newdata = new ArrayList();
/* 159:154 */     String[] values = getTableTitle();
/* 160:155 */     for (int i = 0; i < columns.size(); i++) {
/* 161:156 */       for (int j = 0; j < values.length; j++) {
/* 162:157 */         if (bd.getString(((HistoryData)columns.get(i)).getName()).equals(values[j]))
/* 163:    */         {
/* 164:158 */           newdata.add((HistoryData)columns.get(i));
/* 165:159 */           break;
/* 166:    */         }
/* 167:    */       }
/* 168:    */     }
/* 169:163 */     return newdata;
/* 170:    */   }
/* 171:    */   
/* 172:    */   private String[] getTableTitle()
/* 173:    */   {
/* 174:167 */     List<CheckBoxData> values = this.viewColumns.getCheckBoxDatas();
/* 175:168 */     List<CheckBoxData> selects = new ArrayList();
/* 176:169 */     for (int i = 0; i < values.size(); i++) {
/* 177:170 */       if (((CheckBoxData)values.get(i)).isSelected()) {
/* 178:171 */         selects.add((CheckBoxData)values.get(i));
/* 179:    */       }
/* 180:    */     }
/* 181:174 */     this.MAX_COLUMN = selects.size();
/* 182:175 */     String[] str = new String[selects.size()];
/* 183:176 */     for (int i = 0; i < selects.size(); i++) {
/* 184:177 */       str[i] = bd.getString(((CheckBoxData)selects.get(i)).getName());
/* 185:    */     }
/* 186:179 */     return str;
/* 187:    */   }
/* 188:    */   
/* 189:    */   private List<CheckBoxData> getCheckColumns()
/* 190:    */   {
/* 191:183 */     List<HistoryData> list = getHistoryDataColumns();
/* 192:184 */     this.MAX_COLUMN = list.size();
/* 193:185 */     List<CheckBoxData> listdata = new ArrayList();
/* 194:186 */     for (int i = 0; i < list.size(); i++)
/* 195:    */     {
/* 196:187 */       CheckBoxData data = new CheckBoxData(bd.getString(((HistoryData)list.get(i)).getName()), ((HistoryData)list.get(i)).isView(), ((HistoryData)list.get(i)).isEnable());
/* 197:188 */       listdata.add(data);
/* 198:    */     }
/* 199:190 */     return listdata;
/* 200:    */   }
/* 201:    */   
/* 202:    */   private void initComponents()
/* 203:    */   {
/* 204:194 */     this.mainTabbedPane = new AATabbedPane();
/* 205:195 */     this.dataPanel = new JPanel();
/* 206:196 */     this.jPanel3 = new JPanel();
/* 207:197 */     this.jLabel1 = new AALabel();
/* 208:198 */     this.jComboBox1 = new JComboBox();
/* 209:199 */     this.jComboBox1.setModel(new DefaultComboBoxModel(queryDevices()));
/* 210:200 */     this.viewColumns = new JComboCheckBox();
/* 211:201 */     this.viewColumns.addItems(getCheckColumns());
/* 212:202 */     this.jLabel2 = new AALabel();
/* 213:203 */     this.jTextField1 = new JTextField();
/* 214:204 */     this.jLabel3 = new AALabel();
/* 215:205 */     this.jLabel4 = new AALabel();
/* 216:206 */     this.jLabel5 = new AALabel();
/* 217:207 */     this.jLabel6 = new AALabel();
/* 218:208 */     this.jLabel7 = new AALabel();
/* 219:209 */     this.jLabel8 = new AALabel();
/* 220:210 */     this.jLabel9 = new AALabel();
/* 221:211 */     this.viewButton = new AAButton();
/* 222:212 */     this.jTextField2 = new JTextField();
/* 223:213 */     this.jScrollPane1 = new JScrollPane();
/* 224:214 */     this.jPanel4 = new JPanel();
/* 225:215 */     this.deleteAllButton = new AAButton();
/* 226:216 */     this.deleteButton = new AAButton();
/* 227:217 */     this.closeButton = new AAButton();
/* 228:218 */     this.saveAsButton = new AAButton();
/* 229:219 */     this.chartPanel = new HistoryDataChart();
/* 230:220 */     this.tableTile = getTableTitle();
/* 231:221 */     this.jTable1 = new JTable()
/* 232:    */     {
/* 233:    */       private static final long serialVersionUID = -2097364819095589911L;
/* 234:    */       
/* 235:    */       public boolean isCellSelected(int row, int column)
/* 236:    */       {
/* 237:226 */         if (row >= HistoryDataJDialog.this.workInfolist.size()) {
/* 238:227 */           return false;
/* 239:    */         }
/* 240:229 */         return super.isCellSelected(row, column);
/* 241:    */       }
/* 242:232 */     };
/* 243:233 */     this.jTable1.getTableHeader().setReorderingAllowed(false);
/* 244:234 */     this.jTable1.getTableHeader().setForeground(Color.white);
/* 245:235 */     this.jTable1.setSelectionMode(0);
/* 246:236 */     this.tableModel = new AADefaultTableModel(new Object[21][this.MAX_COLUMN], this.tableTile)
/* 247:    */     {
/* 248:    */       private static final long serialVersionUID = -4444769250474742659L;
/* 249:    */       
/* 250:    */       public boolean isCellEditable(int row, int column)
/* 251:    */       {
/* 252:241 */         return false;
/* 253:    */       }
/* 254:243 */     };
/* 255:244 */     this.jTable1.setModel(this.tableModel);
/* 256:245 */     setDefaultCloseOperation(2);
/* 257:246 */     this.dataPanel.setLayout(new BorderLayout());
/* 258:247 */     this.jPanel3.setBorder(BorderFactory.createEtchedBorder());
/* 259:248 */     this.jLabel1.setText("message.device[:]");
/* 260:249 */     this.jLabel6.setText("message.viewselect[:]");
/* 261:250 */     this.jLabel2.setText("message.selectDate[:]");
/* 262:    */     
/* 263:252 */     this.jComboBox1.addItemListener(new ItemListener()
/* 264:    */     {
/* 265:    */       public void itemStateChanged(ItemEvent ie)
/* 266:    */       {
/* 267:254 */         HistoryDataJDialog.this.canDelete = false;
/* 268:    */         
/* 269:256 */         HistoryDataJDialog.this.viewColumns.removeAllItems();
/* 270:257 */         HistoryDataJDialog.this.viewColumns.addItems(HistoryDataJDialog.this.getCheckColumns());
/* 271:    */       }
/* 272:260 */     });
/* 273:261 */     this.jLabel3.setIcon(new ImageIcon(Constants.DATE));
/* 274:262 */     this.jLabel3.addMouseListener(new MouseAdapter()
/* 275:    */     {
/* 276:    */       public void mouseClicked(MouseEvent e)
/* 277:    */       {
/* 278:265 */         HistoryDataJDialog.this.canDelete = false;
/* 279:266 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryDataJDialog.this.jTextField1);
/* 280:267 */         calendarPanel.setBounds(HistoryDataJDialog.this.getX() + HistoryDataJDialog.this.jLabel3.getX() + 30, HistoryDataJDialog.this.getY() + 
/* 281:268 */           HistoryDataJDialog.this.jLabel3.getY() + 10, calendarPanel.getWidth(), 
/* 282:269 */           calendarPanel.getHeight());
/* 283:270 */         calendarPanel.setVisible(true);
/* 284:    */       }
/* 285:272 */     });
/* 286:273 */     this.jTextField1.setEditable(false);
/* 287:274 */     this.jTextField1.setBackground(I18NListener.bgColor);
/* 288:275 */     this.jTextField1.setText(DateUtils.getNowDate());
/* 289:276 */     this.jTextField1.addMouseListener(new MouseAdapter()
/* 290:    */     {
/* 291:    */       public void mouseClicked(MouseEvent e)
/* 292:    */       {
/* 293:279 */         HistoryDataJDialog.this.canDelete = false;
/* 294:280 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryDataJDialog.this.jTextField1);
/* 295:281 */         calendarPanel.setBounds(HistoryDataJDialog.this.getX() + HistoryDataJDialog.this.jLabel3.getX() + 30, HistoryDataJDialog.this.getY() + 
/* 296:282 */           HistoryDataJDialog.this.jLabel3.getY() + 10, calendarPanel.getWidth(), 
/* 297:283 */           calendarPanel.getHeight());
/* 298:284 */         calendarPanel.setVisible(true);
/* 299:    */       }
/* 300:286 */     });
/* 301:287 */     this.jLabel4.setText("--");
/* 302:288 */     this.jLabel5.setIcon(new ImageIcon(Constants.DATE));
/* 303:289 */     this.jLabel5.addMouseListener(new MouseAdapter()
/* 304:    */     {
/* 305:    */       public void mouseClicked(MouseEvent e)
/* 306:    */       {
/* 307:292 */         HistoryDataJDialog.this.canDelete = false;
/* 308:293 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryDataJDialog.this.jTextField2);
/* 309:294 */         calendarPanel.setBounds(HistoryDataJDialog.this.getX() + HistoryDataJDialog.this.jLabel5.getX() + 30, HistoryDataJDialog.this.getY() + 
/* 310:295 */           HistoryDataJDialog.this.jLabel5.getY() + 10, calendarPanel.getWidth(), 
/* 311:296 */           calendarPanel.getHeight());
/* 312:297 */         calendarPanel.setVisible(true);
/* 313:    */       }
/* 314:299 */     });
/* 315:300 */     this.jTextField2.setEditable(false);
/* 316:301 */     this.jTextField2.setBackground(I18NListener.bgColor);
/* 317:302 */     this.jTextField2.setText(DateUtils.getNowDate());
/* 318:303 */     this.jTextField2.addMouseListener(new MouseAdapter()
/* 319:    */     {
/* 320:    */       public void mouseClicked(MouseEvent e)
/* 321:    */       {
/* 322:306 */         HistoryDataJDialog.this.canDelete = false;
/* 323:307 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryDataJDialog.this.jTextField2);
/* 324:308 */         calendarPanel.setBounds(HistoryDataJDialog.this.getX() + HistoryDataJDialog.this.jLabel5.getX() + 30, HistoryDataJDialog.this.getY() + 
/* 325:309 */           HistoryDataJDialog.this.jLabel5.getY() + 10, calendarPanel.getWidth(), 
/* 326:310 */           calendarPanel.getHeight());
/* 327:311 */         calendarPanel.setVisible(true);
/* 328:    */       }
/* 329:314 */     });
/* 330:315 */     this.viewButton.setText("message.view");
/* 331:316 */     this.viewButton.addActionListener(new ActionListener()
/* 332:    */     {
/* 333:    */       public void actionPerformed(ActionEvent e)
/* 334:    */       {
/* 335:319 */         HistoryDataJDialog.this.canDelete = true;
/* 336:320 */         HistoryDataJDialog.this.queryHistoryWorkInfo();
/* 337:    */       }
/* 338:323 */     });
/* 339:324 */     this.jLabel7.setText("message.totalrow[:]");
/* 340:325 */     this.jLabel8.setText("0");
/* 341:326 */     this.jLabel9.setText("message.row");
/* 342:    */     
/* 343:328 */     this.jScrollPane1.setViewportView(this.jTable1);
/* 344:329 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 345:330 */     this.jPanel3.setLayout(jPanel3Layout);
/* 346:331 */     jPanel3Layout.setHorizontalGroup(
/* 347:332 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 348:333 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 349:334 */       .addContainerGap()
/* 350:335 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 351:336 */       .addComponent(this.jScrollPane1, -1, 950, 32767)
/* 352:337 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 353:338 */       .addComponent(this.jLabel1)
/* 354:339 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 355:340 */       .addComponent(this.jComboBox1, -2, 140, -2)
/* 356:341 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 357:342 */       .addComponent(this.jLabel6)
/* 358:343 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 359:344 */       .addComponent(this.viewColumns, -2, 140, -2)
/* 360:345 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 361:346 */       .addComponent(this.jLabel2)
/* 362:347 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 363:348 */       .addComponent(this.jTextField1, -2, 104, -2)
/* 364:349 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 365:350 */       .addComponent(this.jLabel3)
/* 366:351 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 367:352 */       .addComponent(this.jLabel4)
/* 368:353 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 369:354 */       .addComponent(this.jTextField2, -2, 104, -2)
/* 370:355 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 371:356 */       .addComponent(this.jLabel5)
/* 372:357 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 373:358 */       .addComponent(this.viewButton)))
/* 374:359 */       .addContainerGap()));
/* 375:    */     
/* 376:361 */     jPanel3Layout.setVerticalGroup(
/* 377:362 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 378:363 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 379:364 */       .addContainerGap()
/* 380:365 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 381:366 */       .addComponent(this.jLabel5)
/* 382:367 */       .addComponent(this.jTextField2, -2, -1, -2)
/* 383:368 */       .addComponent(this.jLabel4)
/* 384:369 */       .addComponent(this.jLabel3)
/* 385:370 */       .addComponent(this.jTextField1, -2, -1, -2)
/* 386:371 */       .addComponent(this.jComboBox1, -2, -1, -2)
/* 387:372 */       .addComponent(this.viewColumns, -2, -1, -2)
/* 388:373 */       .addComponent(this.jLabel6)
/* 389:374 */       .addComponent(this.jLabel1)
/* 390:375 */       .addComponent(this.jLabel2)
/* 391:376 */       .addComponent(this.viewButton))
/* 392:377 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 393:378 */       .addComponent(this.jScrollPane1, -1, 447, 32767)
/* 394:379 */       .addContainerGap()));
/* 395:    */     
/* 396:381 */     this.dataPanel.add(this.jPanel3, "Center");
/* 397:382 */     this.jPanel4.setBorder(BorderFactory.createEtchedBorder());
/* 398:383 */     this.deleteButton.setText("message.del");
/* 399:384 */     this.deleteButton.addActionListener(new ActionListener()
/* 400:    */     {
/* 401:    */       public void actionPerformed(ActionEvent e)
/* 402:    */       {
/* 403:387 */         if (!SolarPowerTray.isLogin)
/* 404:    */         {
/* 405:388 */           new LoginJDialog(new Frame(), true);
/* 406:389 */           return;
/* 407:    */         }
/* 408:391 */         HistoryDataJDialog.this.deleteDatas();
/* 409:    */       }
/* 410:393 */     });
/* 411:394 */     this.deleteAllButton.setText("message.deleteAll");
/* 412:395 */     this.deleteAllButton.addActionListener(new ActionListener()
/* 413:    */     {
/* 414:    */       public void actionPerformed(ActionEvent e)
/* 415:    */       {
/* 416:398 */         if (!SolarPowerTray.isLogin)
/* 417:    */         {
/* 418:399 */           new LoginJDialog(new Frame(), true);
/* 419:400 */           return;
/* 420:    */         }
/* 421:402 */         HistoryDataJDialog.this.clearDatas();
/* 422:    */       }
/* 423:404 */     });
/* 424:405 */     this.closeButton.setText("message.close");
/* 425:406 */     this.closeButton.addActionListener(new ActionListener()
/* 426:    */     {
/* 427:    */       public void actionPerformed(ActionEvent e)
/* 428:    */       {
/* 429:409 */         HistoryDataJDialog.this.dispose();
/* 430:    */       }
/* 431:411 */     });
/* 432:412 */     this.saveAsButton.setText("message.saveas");
/* 433:413 */     this.saveAsButton.addActionListener(new ActionListener()
/* 434:    */     {
/* 435:    */       public void actionPerformed(ActionEvent e)
/* 436:    */       {
/* 437:416 */         if (!SolarPowerTray.isLogin)
/* 438:    */         {
/* 439:417 */           new LoginJDialog(new Frame(), true);
/* 440:418 */           return;
/* 441:    */         }
/* 442:420 */         HistoryDataJDialog.this.saveAs();
/* 443:    */       }
/* 444:422 */     });
/* 445:423 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 446:424 */     this.jPanel4.setLayout(jPanel4Layout);
/* 447:425 */     jPanel4Layout.setHorizontalGroup(
/* 448:426 */       jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 449:427 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 450:428 */       jPanel4Layout.createSequentialGroup().addContainerGap()
/* 451:429 */       .addComponent(this.jLabel7)
/* 452:430 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 453:431 */       .addComponent(this.jLabel8)
/* 454:432 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 455:433 */       .addComponent(this.jLabel9)
/* 456:434 */       .addContainerGap(400, 32767)
/* 457:435 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 400, 32767)
/* 458:436 */       .addComponent(this.saveAsButton)
/* 459:437 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 460:438 */       .addComponent(this.deleteButton)
/* 461:439 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 462:440 */       .addComponent(this.deleteAllButton)
/* 463:441 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 464:442 */       .addComponent(this.closeButton)
/* 465:443 */       .addGap(12, 12, 12)));
/* 466:    */     
/* 467:445 */     jPanel4Layout.setVerticalGroup(
/* 468:446 */       jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 469:447 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 470:448 */       jPanel4Layout.createSequentialGroup().addContainerGap(-1, 32767)
/* 471:449 */       .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 472:450 */       .addComponent(this.jLabel7)
/* 473:451 */       .addComponent(this.jLabel8)
/* 474:452 */       .addComponent(this.jLabel9)
/* 475:453 */       .addComponent(this.closeButton)
/* 476:454 */       .addComponent(this.deleteButton)
/* 477:455 */       .addComponent(this.deleteAllButton)
/* 478:456 */       .addComponent(this.saveAsButton))
/* 479:    */       
/* 480:458 */       .addContainerGap()));
/* 481:    */     
/* 482:460 */     this.dataPanel.add(this.jPanel4, "Last");
/* 483:461 */     this.mainTabbedPane.addTab("message.dataview", this.dataPanel);
/* 484:462 */     this.chartPanel.closeChartButton.addActionListener(new ActionListener()
/* 485:    */     {
/* 486:    */       public void actionPerformed(ActionEvent e)
/* 487:    */       {
/* 488:465 */         HistoryDataJDialog.this.dispose();
/* 489:    */       }
/* 490:467 */     });
/* 491:468 */     this.mainTabbedPane.addTab("message.digram", this.chartPanel);
/* 492:469 */     getContentPane().add(this.mainTabbedPane, "Center");
/* 493:470 */     pack();
/* 494:    */   }
/* 495:    */   
/* 496:    */   public void queryHistoryWorkInfo()
/* 497:    */   {
/* 498:474 */     if (this.jComboBox1.getSelectedItem() != null)
/* 499:    */     {
/* 500:475 */       String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 501:476 */       String startDate = this.jTextField1.getText().trim();
/* 502:477 */       String endDate = this.jTextField2.getText().trim();
/* 503:478 */       if ((serialno != null) && (!"".equals(serialno)) && (startDate != null) && 
/* 504:479 */         (!"".equals(startDate)) && (endDate != null) && 
/* 505:480 */         (!"".equals(endDate)))
/* 506:    */       {
/* 507:481 */         this.jLabel8.setText("0");
/* 508:482 */         DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 509:483 */         Date fromDate = new Date();
/* 510:484 */         Date toDate = new Date();
/* 511:    */         try
/* 512:    */         {
/* 513:486 */           fromDate = format.parse(startDate);
/* 514:487 */           toDate = format.parse(endDate);
/* 515:    */         }
/* 516:    */         catch (Exception ex)
/* 517:    */         {
/* 518:489 */           ex.printStackTrace();
/* 519:    */         }
/* 520:491 */         this.workInfolist = this.workDao.queryWorkInfo("P30", serialno, fromDate, toDate);
/* 521:492 */         int size = this.workInfolist.size();
/* 522:493 */         if (size < 21) {
/* 523:494 */           size = 21;
/* 524:    */         }
/* 525:496 */         String[] tableTitle = getTableTitle();
/* 526:497 */         List<HistoryData> lists = getHistoryDataColumnsView();
/* 527:498 */         Object[][] obj = new Object[size][this.MAX_COLUMN];
/* 528:499 */         for (int i = 0; i < this.workInfolist.size(); i++)
/* 529:    */         {
/* 530:500 */           this.workInfo = ((WorkInfo)this.workInfolist.get(i));
/* 531:501 */           for (int j = 0; j < lists.size(); j++) {
/* 532:    */             try
/* 533:    */             {
/* 534:503 */               if (((HistoryData)lists.get(j)).getValue().equals("{workInfo.getCurrentTime}"))
/* 535:    */               {
/* 536:504 */                 String dateStr = PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this);
/* 537:505 */                 obj[i][j] = 
/* 538:506 */                   DateUtils.getFormatDateStr(dateStr, GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 539:    */               }
/* 540:    */               else
/* 541:    */               {
/* 542:508 */                 obj[i][j] = PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this);
/* 543:    */               }
/* 544:    */             }
/* 545:    */             catch (Exception ex)
/* 546:    */             {
/* 547:511 */               ex.printStackTrace();
/* 548:    */             }
/* 549:    */           }
/* 550:    */         }
/* 551:515 */         if (this.workInfolist.size() < 21) {
/* 552:516 */           for (int i = this.workInfolist.size(); i < 21; i++) {
/* 553:517 */             for (int j = 0; j < this.MAX_COLUMN; j++) {
/* 554:518 */               obj[i][j] = null;
/* 555:    */             }
/* 556:    */           }
/* 557:    */         }
/* 558:522 */         this.tableModel.setDataVector(obj, tableTitle);
/* 559:523 */         if (this.workInfolist != null) {
/* 560:524 */           this.jLabel8.setText(this.workInfolist.size());
/* 561:    */         } else {
/* 562:526 */           this.jLabel8.setText("0");
/* 563:    */         }
/* 564:    */       }
/* 565:    */     }
/* 566:    */   }
/* 567:    */   
/* 568:    */   public void setTableFormate()
/* 569:    */   {
/* 570:534 */     TableColumnModel tcm = this.jTable1.getColumnModel();
/* 571:535 */     TableColumn tc0 = tcm.getColumn(0);
/* 572:536 */     TableColumn tc1 = tcm.getColumn(1);
/* 573:537 */     TableColumn tc2 = tcm.getColumn(2);
/* 574:538 */     TableColumn tc3 = tcm.getColumn(3);
/* 575:539 */     tc0.setPreferredWidth(30);
/* 576:540 */     tc1.setPreferredWidth(30);
/* 577:541 */     tc2.setPreferredWidth(100);
/* 578:542 */     tc3.setPreferredWidth(250);
/* 579:    */   }
/* 580:    */   
/* 581:    */   public String[] queryDevices()
/* 582:    */   {
/* 583:546 */     List<String> devicelist = this.deviceDao.querySerialnoAll("P30");
/* 584:547 */     String[] devices = new String[devicelist.size()];
/* 585:548 */     for (int i = 0; i < devicelist.size(); i++) {
/* 586:549 */       devices[i] = ((String)devicelist.get(i));
/* 587:    */     }
/* 588:551 */     return devices;
/* 589:    */   }
/* 590:    */   
/* 591:    */   public void deleteDatas()
/* 592:    */   {
/* 593:555 */     if (this.jComboBox1.getSelectedItem() != null)
/* 594:    */     {
/* 595:556 */       String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 596:557 */       int re = DisplayMessage.showConfirmDialog("message.deleteConfirm", "message.info");
/* 597:558 */       if ((re == 0) && 
/* 598:559 */         (serialno != null) && (!"".equals(serialno)))
/* 599:    */       {
/* 600:560 */         int row = this.jTable1.getSelectedRow();
/* 601:561 */         if (row > -1)
/* 602:    */         {
/* 603:562 */           Object obj = this.tableModel.getValueAt(row, 1);
/* 604:563 */           if (obj != null)
/* 605:    */           {
/* 606:564 */             SimpleDateFormat sdf = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 607:565 */             Date trandate = new Date();
/* 608:    */             try
/* 609:    */             {
/* 610:567 */               trandate = sdf.parse(obj.toString());
/* 611:    */             }
/* 612:    */             catch (Exception ex)
/* 613:    */             {
/* 614:569 */               ex.printStackTrace();
/* 615:    */             }
/* 616:571 */             boolean result = this.workDao.removeWork("P30", serialno, trandate);
/* 617:572 */             if (result) {
/* 618:573 */               queryHistoryWorkInfo();
/* 619:    */             } else {
/* 620:575 */               DisplayMessage.showErrorDialog("message.operationfailure");
/* 621:    */             }
/* 622:    */           }
/* 623:    */           else
/* 624:    */           {
/* 625:578 */             DisplayMessage.showErrorDialog("message.pleaseselect");
/* 626:    */           }
/* 627:    */         }
/* 628:    */         else
/* 629:    */         {
/* 630:581 */           DisplayMessage.showErrorDialog("message.pleaseselect");
/* 631:    */         }
/* 632:    */       }
/* 633:    */     }
/* 634:    */   }
/* 635:    */   
/* 636:    */   public void clearDatas()
/* 637:    */   {
/* 638:589 */     if ((this.canDelete) && 
/* 639:590 */       (this.jComboBox1.getSelectedItem() != null))
/* 640:    */     {
/* 641:591 */       String serialno = this.jComboBox1.getSelectedItem().toString().trim();
/* 642:592 */       String startDate = this.jTextField1.getText().trim();
/* 643:593 */       String endDate = this.jTextField2.getText().trim();
/* 644:594 */       int re = DisplayMessage.showConfirmDialog("message.deleteConfirm", "message.info");
/* 645:595 */       if ((re == 0) && 
/* 646:596 */         (serialno != null) && (!"".equals(serialno)) && (startDate != null) && 
/* 647:597 */         (!"".equals(startDate)) && (endDate != null) && 
/* 648:598 */         (!"".equals(endDate)))
/* 649:    */       {
/* 650:599 */         DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 651:600 */         Date fromDate = new Date();
/* 652:601 */         Date toDate = new Date();
/* 653:    */         try
/* 654:    */         {
/* 655:603 */           fromDate = format.parse(startDate);
/* 656:604 */           toDate = format.parse(endDate);
/* 657:    */         }
/* 658:    */         catch (Exception ex)
/* 659:    */         {
/* 660:606 */           ex.printStackTrace();
/* 661:    */         }
/* 662:608 */         boolean result = this.workDao.removeAllWork("P30", serialno, fromDate, 
/* 663:609 */           toDate);
/* 664:610 */         if (result) {
/* 665:611 */           queryHistoryWorkInfo();
/* 666:    */         } else {
/* 667:613 */           DisplayMessage.showErrorDialog("message.operationfailure");
/* 668:    */         }
/* 669:    */       }
/* 670:    */     }
/* 671:    */   }
/* 672:    */   
/* 673:    */   public int getSaveDialog()
/* 674:    */   {
/* 675:622 */     String saveFileName = "History_datas.pdf";
/* 676:623 */     this.fDialog = new JFileChooser();
/* 677:624 */     PDFFileFilter txtFilter = new PDFFileFilter();
/* 678:625 */     this.fDialog.addChoosableFileFilter(txtFilter);
/* 679:626 */     this.fDialog.setFileFilter(txtFilter);
/* 680:627 */     this.fDialog.setAcceptAllFileFilterUsed(true);
/* 681:628 */     this.fDialog.setSelectedFile(new File(saveFileName));
/* 682:629 */     return this.fDialog.showSaveDialog(this);
/* 683:    */   }
/* 684:    */   
/* 685:    */   public void saveAs()
/* 686:    */   {
/* 687:633 */     int result = getSaveDialog();
/* 688:634 */     if (result == 0)
/* 689:    */     {
/* 690:635 */       String filepathStr = this.fDialog.getSelectedFile().getPath();
/* 691:636 */       File newFile = new File(filepathStr);
/* 692:637 */       if (newFile.exists())
/* 693:    */       {
/* 694:638 */         int re = 
/* 695:639 */           DisplayMessage.showConfirmDialog(
/* 696:640 */           "message.confirmoverwrite", 
/* 697:641 */           "message.info");
/* 698:642 */         if (re == 0) {
/* 699:643 */           createPdf(filepathStr);
/* 700:    */         } else {
/* 701:645 */           saveAs();
/* 702:    */         }
/* 703:    */       }
/* 704:    */       else
/* 705:    */       {
/* 706:    */         try
/* 707:    */         {
/* 708:649 */           newFile.createNewFile();
/* 709:    */         }
/* 710:    */         catch (IOException e)
/* 711:    */         {
/* 712:651 */           e.printStackTrace();
/* 713:    */         }
/* 714:653 */         createPdf(filepathStr);
/* 715:    */       }
/* 716:    */     }
/* 717:    */   }
/* 718:    */   
/* 719:    */   public void createPdf(String path)
/* 720:    */   {
/* 721:659 */     Document pdf = new Document(PageSize.A4);
/* 722:    */     try
/* 723:    */     {
/* 724:661 */       PdfWriter.getInstance(pdf, new FileOutputStream(path));
/* 725:662 */       pdf.addTitle("History datas");
/* 726:663 */       pdf.addAuthor(GlobalVariables.customerConfig.getCustomerName());
/* 727:664 */       pdf.open();
/* 728:    */       
/* 729:    */ 
/* 730:667 */       BaseFont bf = BaseFont.createFont(Constants.PDF_STYLE_PATH + "simsun.ttc,1", "Identity-H", false);
/* 731:668 */       Font datafont = new Font(bf, 12.0F, 0, BaseColor.BLACK);
/* 732:669 */       String[] titles = getTableTitle();
/* 733:670 */       PdfPTable table = new PdfPTable(titles.length);
/* 734:671 */       for (int i = 0; i < titles.length; i++)
/* 735:    */       {
/* 736:672 */         PdfPCell cell = new PdfPCell(new Paragraph(bd.getString(titles[i]), datafont));
/* 737:673 */         cell.setHorizontalAlignment(1);
/* 738:674 */         table.addCell(cell);
/* 739:    */       }
/* 740:676 */       for (int i = 0; i < this.workInfolist.size(); i++)
/* 741:    */       {
/* 742:677 */         List<HistoryData> lists = getHistoryDataColumnsView();
/* 743:678 */         this.workInfo = ((WorkInfo)this.workInfolist.get(i));
/* 744:679 */         for (int j = 0; j < lists.size(); j++) {
/* 745:680 */           if (((HistoryData)lists.get(j)).getValue().equals("{workInfo.getCurrentTime}")) {
/* 746:681 */             table.addCell(new Paragraph(
/* 747:682 */               DateUtils.getFormatDateStr(PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this), GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss"), datafont));
/* 748:    */           } else {
/* 749:684 */             table.addCell(new Paragraph(PageUtils.getResultByName(((HistoryData)lists.get(j)).getValue(), this), datafont));
/* 750:    */           }
/* 751:    */         }
/* 752:    */       }
/* 753:689 */       table.setWidthPercentage(100.0F);
/* 754:690 */       pdf.add(table);
/* 755:691 */       Thread.sleep(1500L);
/* 756:    */     }
/* 757:    */     catch (Exception ie)
/* 758:    */     {
/* 759:694 */       DisplayMessage.showErrorDialog("message.saveerror");
/* 760:    */       try
/* 761:    */       {
/* 762:697 */         if (pdf != null) {
/* 763:698 */           pdf.close();
/* 764:    */         }
/* 765:    */       }
/* 766:    */       catch (Exception ex)
/* 767:    */       {
/* 768:701 */         ex.printStackTrace();
/* 769:    */       }
/* 770:    */     }
/* 771:    */     finally
/* 772:    */     {
/* 773:    */       try
/* 774:    */       {
/* 775:697 */         if (pdf != null) {
/* 776:698 */           pdf.close();
/* 777:    */         }
/* 778:    */       }
/* 779:    */       catch (Exception ex)
/* 780:    */       {
/* 781:701 */         ex.printStackTrace();
/* 782:    */       }
/* 783:    */     }
/* 784:    */   }
/* 785:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.HistoryDataJDialog
 * JD-Core Version:    0.7.0.1
 */