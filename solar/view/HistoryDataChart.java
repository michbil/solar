/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   4:    */ import cn.com.voltronic.solar.constants.Constants;
/*   5:    */ import cn.com.voltronic.solar.dao.DeviceDao;
/*   6:    */ import cn.com.voltronic.solar.dao.WorkDataDao;
/*   7:    */ import cn.com.voltronic.solar.data.bean.DeviceBean;
/*   8:    */ import cn.com.voltronic.solar.data.bean.HistoryChart;
/*   9:    */ import cn.com.voltronic.solar.data.bean.HistoryDataChartColumns;
/*  10:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*  11:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  12:    */ import cn.com.voltronic.solar.protocol.P30;
/*  13:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  14:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  15:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  16:    */ import cn.com.voltronic.solar.util.VolUtil;
/*  17:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  18:    */ import cn.com.voltronic.solar.view.component.AAComboBox;
/*  19:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  20:    */ import cn.com.voltronic.solar.view.component.AAPanel;
/*  21:    */ import cn.com.voltronic.solar.view.component.AATabbedPane;
/*  22:    */ import cn.com.voltronic.solar.view.component.ComponentFactory;
/*  23:    */ import cn.com.voltronic.solar.view.component.I18NBundle;
/*  24:    */ import cn.com.voltronic.solar.view.component.I18NListener;
/*  25:    */ import cn.com.voltronic.solar.view.component.MyCalendarPanel;
/*  26:    */ import java.awt.BasicStroke;
/*  27:    */ import java.awt.BorderLayout;
/*  28:    */ import java.awt.Color;
/*  29:    */ import java.awt.Dimension;
/*  30:    */ import java.awt.event.ActionEvent;
/*  31:    */ import java.awt.event.ActionListener;
/*  32:    */ import java.awt.event.MouseAdapter;
/*  33:    */ import java.awt.event.MouseEvent;
/*  34:    */ import java.io.PrintStream;
/*  35:    */ import java.text.DateFormat;
/*  36:    */ import java.text.DecimalFormat;
/*  37:    */ import java.text.ParseException;
/*  38:    */ import java.text.SimpleDateFormat;
/*  39:    */ import java.util.ArrayList;
/*  40:    */ import java.util.Calendar;
/*  41:    */ import java.util.Date;
/*  42:    */ import java.util.List;
/*  43:    */ import javax.swing.BorderFactory;
/*  44:    */ import javax.swing.DefaultComboBoxModel;
/*  45:    */ import javax.swing.GroupLayout;
/*  46:    */ import javax.swing.GroupLayout.Alignment;
/*  47:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  48:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  49:    */ import javax.swing.ImageIcon;
/*  50:    */ import javax.swing.JComboBox;
/*  51:    */ import javax.swing.JPanel;
/*  52:    */ import javax.swing.JScrollPane;
/*  53:    */ import javax.swing.JSpinner;
/*  54:    */ import javax.swing.JTextField;
/*  55:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  56:    */ import org.jfree.chart.ChartFactory;
/*  57:    */ import org.jfree.chart.ChartPanel;
/*  58:    */ import org.jfree.chart.JFreeChart;
/*  59:    */ import org.jfree.chart.axis.DateAxis;
/*  60:    */ import org.jfree.chart.axis.DateTickUnit;
/*  61:    */ import org.jfree.chart.axis.NumberAxis;
/*  62:    */ import org.jfree.chart.labels.StandardXYToolTipGenerator;
/*  63:    */ import org.jfree.chart.plot.XYPlot;
/*  64:    */ import org.jfree.chart.renderer.xy.XYStepRenderer;
/*  65:    */ import org.jfree.data.time.Day;
/*  66:    */ import org.jfree.data.time.Hour;
/*  67:    */ import org.jfree.data.time.Minute;
/*  68:    */ import org.jfree.data.time.RegularTimePeriod;
/*  69:    */ import org.jfree.data.time.TimeSeries;
/*  70:    */ import org.jfree.data.time.TimeSeriesCollection;
/*  71:    */ import org.jfree.data.xy.XYDataset;
/*  72:    */ 
/*  73:    */ public class HistoryDataChart
/*  74:    */   extends AAPanel
/*  75:    */ {
/*  76:    */   private static final long serialVersionUID = 5818569474311920793L;
/*  77:    */   public AAButton closeChartButton;
/*  78:    */   private JPanel jPanel1;
/*  79:    */   private JPanel jPanel2;
/*  80:    */   private AALabel deviceLabel2;
/*  81:    */   private JComboBox jComboBox2;
/*  82:    */   private AAComboBox jComboBox3;
/*  83:    */   private AAComboBox periodComboBox;
/*  84:    */   private AALabel maohaoLabel;
/*  85:    */   private AALabel periodLabel;
/*  86:    */   private AALabel jLabel6;
/*  87:    */   private AALabel jLabel7;
/*  88:    */   private JScrollPane jScrollPane2;
/*  89:    */   private AATabbedPane jTabbedPane1;
/*  90:    */   private JSpinner jSpinner1;
/*  91:    */   private JSpinner jSpinner2;
/*  92:    */   private JTextField jTextField3;
/*  93:    */   private AAButton queryChartButton;
/*  94: 96 */   private WorkDataDao workDao = null;
/*  95: 97 */   private DeviceDao deviceDao = null;
/*  96: 98 */   private IProtocol protocol = null;
/*  97: 99 */   public WorkInfo workInfo = null;
/*  98:    */   
/*  99:    */   public HistoryDataChart()
/* 100:    */   {
/* 101:102 */     this.workDao = new WorkDataDao();
/* 102:103 */     this.deviceDao = new DeviceDao();
/* 103:104 */     this.protocol = new P30();
/* 104:105 */     this.workInfo = new WorkInfo();
/* 105:106 */     initComponents();
/* 106:    */   }
/* 107:    */   
/* 108:    */   private void initComponents()
/* 109:    */   {
/* 110:111 */     this.jLabel6 = new AALabel();
/* 111:112 */     this.jLabel7 = new AALabel();
/* 112:113 */     this.jTextField3 = new JTextField();
/* 113:114 */     this.jPanel1 = new JPanel();
/* 114:115 */     this.deviceLabel2 = new AALabel();
/* 115:116 */     this.jComboBox2 = new JComboBox();
/* 116:117 */     this.jComboBox3 = new AAComboBox();
/* 117:118 */     this.periodLabel = new AALabel();
/* 118:119 */     this.queryChartButton = new AAButton();
/* 119:120 */     this.periodComboBox = new AAComboBox();
/* 120:121 */     this.jTabbedPane1 = new AATabbedPane();
/* 121:122 */     this.maohaoLabel = new AALabel();
/* 122:123 */     this.jSpinner1 = ComponentFactory.createNumberSpinner(2010, DateUtils.getCurrentYear() + 200, 1, DateUtils.getCurrentYear());
/* 123:124 */     this.jSpinner2 = ComponentFactory.createNumberSpinner(0, 23, 1, DateUtils.getCurrentHour());
/* 124:125 */     this.jScrollPane2 = new JScrollPane();
/* 125:126 */     this.jPanel2 = new JPanel();
/* 126:127 */     this.closeChartButton = new AAButton();
/* 127:    */     
/* 128:129 */     setLayout(new BorderLayout());
/* 129:    */     
/* 130:131 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 131:    */     
/* 132:133 */     this.deviceLabel2.setText("message.device[:]");
/* 133:    */     
/* 134:135 */     this.jComboBox2.setModel(new DefaultComboBoxModel(queryDevices()));
/* 135:    */     
/* 136:137 */     this.periodLabel.setText("message.cycle[:]");
/* 137:    */     
/* 138:139 */     this.jLabel6.setIcon(new ImageIcon(Constants.DATE));
/* 139:140 */     this.jLabel6.addMouseListener(new MouseAdapter()
/* 140:    */     {
/* 141:    */       public void mouseClicked(MouseEvent e)
/* 142:    */       {
/* 143:143 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryDataChart.this.jTextField3);
/* 144:144 */         calendarPanel.setBounds(HistoryDataChart.this.getX() + HistoryDataChart.this.jLabel6.getX() + 30, HistoryDataChart.this.getY() + 
/* 145:145 */           HistoryDataChart.this.jLabel6.getY() + 10, calendarPanel.getWidth(), 
/* 146:146 */           calendarPanel.getHeight());
/* 147:147 */         calendarPanel.setVisible(true);
/* 148:    */       }
/* 149:150 */     });
/* 150:151 */     this.jTextField3.setEditable(false);
/* 151:152 */     this.jTextField3.setBackground(I18NListener.bgColor);
/* 152:153 */     this.jTextField3.setText(DateUtils.getNowDate());
/* 153:154 */     this.jTextField3.addMouseListener(new MouseAdapter()
/* 154:    */     {
/* 155:    */       public void mouseClicked(MouseEvent e)
/* 156:    */       {
/* 157:157 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(HistoryDataChart.this.jTextField3);
/* 158:158 */         calendarPanel.setBounds(HistoryDataChart.this.getX() + HistoryDataChart.this.jLabel6.getX() + 30, HistoryDataChart.this.getY() + 
/* 159:159 */           HistoryDataChart.this.jLabel6.getY() + 10, calendarPanel.getWidth(), 
/* 160:160 */           calendarPanel.getHeight());
/* 161:161 */         calendarPanel.setVisible(true);
/* 162:    */       }
/* 163:164 */     });
/* 164:165 */     String[] monthArr = {
/* 165:166 */       "message.january", "message.february", "message.march", "message.april", "message.may", "message.june", "message.july", "message.august", "message.september", "message.october", "message.november", "message.december" };
/* 166:    */     
/* 167:168 */     this.jComboBox3.setModel(monthArr);
/* 168:    */     
/* 169:170 */     this.jLabel7.setText("message.hour");
/* 170:    */     
/* 171:172 */     this.queryChartButton.setText("message.view");
/* 172:173 */     this.queryChartButton.addActionListener(new ActionListener()
/* 173:    */     {
/* 174:    */       public void actionPerformed(ActionEvent e)
/* 175:    */       {
/* 176:176 */         HistoryDataChart.this.queryChartAction();
/* 177:    */       }
/* 178:179 */     });
/* 179:180 */     setDataChart("year");
/* 180:    */     
/* 181:182 */     this.jTabbedPane1.setTabPlacement(2);
/* 182:183 */     this.jScrollPane2.setViewportView(this.jTabbedPane1);
/* 183:    */     
/* 184:185 */     this.periodComboBox.setModel(new String[] { "message.year", "message.month", "message.day", "message.hour" });
/* 185:186 */     this.periodComboBox.setSelectedIndex(0);
/* 186:187 */     this.periodComboBox.addActionListener(new ActionListener()
/* 187:    */     {
/* 188:    */       public void actionPerformed(ActionEvent e)
/* 189:    */       {
/* 190:190 */         HistoryDataChart.this.setPeriod();
/* 191:    */       }
/* 192:193 */     });
/* 193:194 */     setPeriod();
/* 194:195 */     this.maohaoLabel.setText(":");
/* 195:    */     
/* 196:197 */     add(this.jPanel1, "Center");
/* 197:    */     
/* 198:199 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 199:    */     
/* 200:201 */     this.closeChartButton.setText("message.close");
/* 201:    */     
/* 202:203 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 203:204 */     this.jPanel2.setLayout(jPanel2Layout);
/* 204:205 */     jPanel2Layout.setHorizontalGroup(
/* 205:206 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 206:207 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 207:208 */       jPanel2Layout.createSequentialGroup().addContainerGap(950, 32767)
/* 208:209 */       .addComponent(this.closeChartButton)
/* 209:210 */       .addGap(12, 12, 12)));
/* 210:    */     
/* 211:212 */     jPanel2Layout.setVerticalGroup(
/* 212:213 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 213:214 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 214:215 */       jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767)
/* 215:216 */       .addComponent(this.closeChartButton)
/* 216:217 */       .addContainerGap()));
/* 217:    */     
/* 218:219 */     add(this.jPanel2, "Last");
/* 219:    */   }
/* 220:    */   
/* 221:    */   public String[] queryDevices()
/* 222:    */   {
/* 223:223 */     List<String> devicelist = this.deviceDao.querySerialnoAll("P30");
/* 224:224 */     String[] devices = new String[devicelist.size()];
/* 225:225 */     for (int i = 0; i < devicelist.size(); i++) {
/* 226:226 */       devices[i] = ((String)devicelist.get(i));
/* 227:    */     }
/* 228:228 */     return devices;
/* 229:    */   }
/* 230:    */   
/* 231:    */   private void setDataChart(String mask)
/* 232:    */   {
/* 233:232 */     DeviceBean bean = null;
/* 234:233 */     this.jTabbedPane1.removeAll();
/* 235:234 */     String serialno = "";
/* 236:235 */     String prodid = "";
/* 237:236 */     if (this.jComboBox2.getSelectedItem() != null) {
/* 238:    */       try
/* 239:    */       {
/* 240:238 */         serialno = this.jComboBox2.getSelectedItem().toString().trim();
/* 241:239 */         bean = this.deviceDao.queryDevicebySerialno("P30", serialno);
/* 242:    */       }
/* 243:    */       catch (Exception ex)
/* 244:    */       {
/* 245:243 */         ex.printStackTrace();
/* 246:    */       }
/* 247:    */     }
/* 248:246 */     int year = VolUtil.parseInt(this.jSpinner1.getValue().toString());
/* 249:247 */     int month = this.jComboBox3.getSelectedIndex() + 1;
/* 250:248 */     String dateStr = this.jTextField3.getText();
/* 251:249 */     DateFormat df = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 252:250 */     Date queryDate = new Date();
/* 253:    */     try
/* 254:    */     {
/* 255:252 */       queryDate = df.parse(dateStr);
/* 256:    */     }
/* 257:    */     catch (ParseException e)
/* 258:    */     {
/* 259:254 */       e.printStackTrace();
/* 260:    */     }
/* 261:256 */     Date day = queryDate;
/* 262:257 */     int hour = VolUtil.parseInt(this.jSpinner2.getValue().toString());
/* 263:259 */     if ((bean != null) && (bean.getProdid().equalsIgnoreCase("P30")))
/* 264:    */     {
/* 265:260 */       P30 temp = new P30();
/* 266:261 */       temp.setOutputMode(bean.getParallel());
/* 267:262 */       this.protocol = temp;
/* 268:    */     }
/* 269:    */     else
/* 270:    */     {
/* 271:264 */       this.protocol = new P30();
/* 272:    */     }
/* 273:267 */     List<HistoryChart> lists = new ArrayList();
/* 274:268 */     HistoryDataChartColumns data = this.protocol.getHistoryChartColumns();
/* 275:269 */     lists = data.getColumns();
/* 276:270 */     List<WorkInfo> workInfoList = new ArrayList();
/* 277:271 */     if (mask.equals("year")) {
/* 278:272 */       workInfoList = this.workDao.getWorkDatasByYear(year, "P30", serialno);
/* 279:273 */     } else if (mask.equals("month")) {
/* 280:274 */       workInfoList = this.workDao.getWorkDatasByMonth(year, month, "P30", serialno);
/* 281:275 */     } else if (mask.equals("day")) {
/* 282:276 */       workInfoList = this.workDao.getWorkDatasByDay(queryDate, "P30", serialno);
/* 283:277 */     } else if (mask.equals("hour")) {
/* 284:278 */       workInfoList = this.workDao.getWorkDatasByHour(queryDate, hour, "P30", serialno);
/* 285:    */     }
/* 286:280 */     for (int i = 0; i < lists.size(); i++)
/* 287:    */     {
/* 288:281 */       AAPanel panel = new AAPanel();
/* 289:282 */       XYDataset xydataset = createDataset(workInfoList, bd.getString(((HistoryChart)lists.get(i)).getName()), ((HistoryChart)lists.get(i)).getValue(), year, month, day, hour, mask);
/* 290:283 */       JFreeChart chart = null;
/* 291:284 */       if (i % 2 == 0) {
/* 292:285 */         chart = createChart(xydataset, ((HistoryChart)lists.get(i)).getMaximum(), ((HistoryChart)lists.get(i)).getUnit(), mask, 1);
/* 293:    */       } else {
/* 294:287 */         chart = createChart(xydataset, ((HistoryChart)lists.get(i)).getMaximum(), ((HistoryChart)lists.get(i)).getUnit(), mask, 2);
/* 295:    */       }
/* 296:289 */       ChartPanel chartPane = createChartPanel(chart);
/* 297:290 */       panel.add(chartPane);
/* 298:291 */       this.jTabbedPane1.addTab(((HistoryChart)lists.get(i)).getName(), panel);
/* 299:    */     }
/* 300:    */   }
/* 301:    */   
/* 302:    */   private ChartPanel createChartPanel(JFreeChart chart)
/* 303:    */   {
/* 304:296 */     ChartPanel chartPane = new ChartPanel(chart);
/* 305:297 */     chartPane.setPreferredSize(new Dimension(900, 420));
/* 306:298 */     return chartPane;
/* 307:    */   }
/* 308:    */   
/* 309:    */   private XYDataset createDataset(List<WorkInfo> workInfoList, String name, String value, int year, int month, Date day, int hour, String mask)
/* 310:    */   {
/* 311:    */     try
/* 312:    */     {
/* 313:303 */       Calendar calendar = Calendar.getInstance();
/* 314:304 */       calendar.setTime(day);
/* 315:305 */       if (mask.equals("year"))
/* 316:    */       {
/* 317:306 */         TimeSeries timeseries = new TimeSeries(name, Day.class);
/* 318:307 */         Day days = new Day(1, 1, year);
/* 319:308 */         for (int i = 0; i < workInfoList.size() - 2; i++)
/* 320:    */         {
/* 321:309 */           this.workInfo = ((WorkInfo)workInfoList.get(i));
/* 322:310 */           timeseries.add(days, VolUtil.parseDouble(PageUtils.getResultByName(value, this)));
/* 323:311 */           days = (Day)days.next().next().next();
/* 324:    */         }
/* 325:313 */         return new TimeSeriesCollection(
/* 326:314 */           timeseries);
/* 327:    */       }
/* 328:316 */       if (mask.equals("month"))
/* 329:    */       {
/* 330:317 */         TimeSeries timeseries = new TimeSeries(name, Hour.class);
/* 331:318 */         Hour hours = new Hour(0, 1, month, year);
/* 332:319 */         for (int i = 0; i < workInfoList.size() - 2; i++)
/* 333:    */         {
/* 334:320 */           this.workInfo = ((WorkInfo)workInfoList.get(i));
/* 335:321 */           timeseries.add(hours, VolUtil.parseDouble(PageUtils.getResultByName(value, this)));
/* 336:322 */           hours = (Hour)hours.next().next().next().next().next().next();
/* 337:    */         }
/* 338:324 */         return new TimeSeriesCollection(
/* 339:325 */           timeseries);
/* 340:    */       }
/* 341:327 */       if (mask.equals("day"))
/* 342:    */       {
/* 343:328 */         TimeSeries timeseries = new TimeSeries(name, Minute.class);
/* 344:329 */         Minute minutes = new Minute(0, 0, calendar.get(5), calendar.get(2) + 1, calendar.get(1));
/* 345:330 */         for (int i = 0; i < workInfoList.size() - 1; i++)
/* 346:    */         {
/* 347:331 */           this.workInfo = ((WorkInfo)workInfoList.get(i));
/* 348:332 */           timeseries.add(minutes, VolUtil.parseDouble(PageUtils.getResultByName(value, this)));
/* 349:333 */           minutes = (Minute)minutes.next().next().next().next()
/* 350:334 */             .next().next().next().next().next().next().next().next().next().next().next();
/* 351:    */         }
/* 352:336 */         return new TimeSeriesCollection(
/* 353:337 */           timeseries);
/* 354:    */       }
/* 355:339 */       if (mask.equals("hour"))
/* 356:    */       {
/* 357:340 */         TimeSeries timeseries = new TimeSeries(name, Minute.class);
/* 358:341 */         Minute minutes = new Minute(0, hour, calendar.get(5), calendar.get(2) + 1, calendar.get(1));
/* 359:342 */         for (int i = 0; i < workInfoList.size() - 1; i++)
/* 360:    */         {
/* 361:343 */           this.workInfo = ((WorkInfo)workInfoList.get(i));
/* 362:344 */           timeseries.add(minutes, VolUtil.parseDouble(PageUtils.getResultByName(value, this)));
/* 363:345 */           minutes = (Minute)minutes.next();
/* 364:    */         }
/* 365:347 */         return new TimeSeriesCollection(
/* 366:348 */           timeseries);
/* 367:    */       }
/* 368:    */     }
/* 369:    */     catch (Exception ex)
/* 370:    */     {
/* 371:352 */       System.err.println("create history data dataset error");
/* 372:    */     }
/* 373:354 */     return null;
/* 374:    */   }
/* 375:    */   
/* 376:    */   private JFreeChart createChart(XYDataset xydataset, double maximum, String unit, String mask, int color)
/* 377:    */   {
/* 378:    */     try
/* 379:    */     {
/* 380:359 */       JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(
/* 381:360 */         "", "", unit, xydataset, false, true, false);
/* 382:361 */       jfreechart.setBackgroundPaint(new Color(102, 102, 102));
/* 383:362 */       XYPlot xyPlot = (XYPlot)jfreechart.getPlot();
/* 384:363 */       xyPlot.setBackgroundPaint(new Color(102, 102, 102));
/* 385:364 */       xyPlot.setDomainGridlinePaint(Constants.BG_COLOR);
/* 386:365 */       xyPlot.setDomainGridlinesVisible(true);
/* 387:366 */       xyPlot.setRangeGridlinePaint(Color.white);
/* 388:367 */       XYStepRenderer xysteprenderer = new XYStepRenderer();
/* 389:368 */       xysteprenderer.setStroke(new BasicStroke(2.0F));
/* 390:    */       
/* 391:370 */       SimpleDateFormat sdf = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 392:371 */       StandardXYToolTipGenerator ttg = new StandardXYToolTipGenerator(
/* 393:372 */         "{0}: ({1}, {2})", sdf, 
/* 394:373 */         new DecimalFormat("##0.00"));
/* 395:374 */       xysteprenderer.setToolTipGenerator(ttg);
/* 396:375 */       xysteprenderer.setDefaultEntityRadius(6);
/* 397:376 */       xysteprenderer.setItemLabelsVisible(true);
/* 398:377 */       xysteprenderer.setBaseItemLabelsVisible(true);
/* 399:378 */       if (color == 1) {
/* 400:379 */         xysteprenderer.setPaint(Color.red);
/* 401:    */       } else {
/* 402:381 */         xysteprenderer.setPaint(Color.blue);
/* 403:    */       }
/* 404:383 */       xyPlot.setRenderer(xysteprenderer);
/* 405:384 */       NumberAxis numberaxis = (NumberAxis)xyPlot.getRangeAxis();
/* 406:385 */       numberaxis.setLowerMargin(0.0D);
/* 407:386 */       numberaxis.setUpperMargin(1.0D);
/* 408:387 */       numberaxis.setRange(0.0D, maximum);
/* 409:388 */       numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/* 410:    */       
/* 411:390 */       DateAxis dateAxis = (DateAxis)xyPlot.getDomainAxis();
/* 412:391 */       dateAxis.setLowerMargin(0.001D);
/* 413:393 */       if (mask.equals("year"))
/* 414:    */       {
/* 415:394 */         DateFormat format = new SimpleDateFormat("MM");
/* 416:395 */         DateTickUnit dtu = new DateTickUnit(1, 1, format);
/* 417:396 */         dateAxis.setTickUnit(dtu);
/* 418:    */       }
/* 419:397 */       else if (mask.equals("month"))
/* 420:    */       {
/* 421:398 */         DateFormat format = new SimpleDateFormat("dd");
/* 422:399 */         DateTickUnit dtu = new DateTickUnit(2, 1, format);
/* 423:400 */         dateAxis.setTickUnit(dtu);
/* 424:    */       }
/* 425:401 */       else if (mask.equals("day"))
/* 426:    */       {
/* 427:402 */         DateFormat format = new SimpleDateFormat("HH");
/* 428:403 */         DateTickUnit dtu = new DateTickUnit(3, 1, format);
/* 429:404 */         dateAxis.setTickUnit(dtu);
/* 430:    */       }
/* 431:405 */       else if (mask.equals("hour"))
/* 432:    */       {
/* 433:406 */         DateFormat format = new SimpleDateFormat("mm");
/* 434:407 */         DateTickUnit dtu = new DateTickUnit(4, 2, format);
/* 435:408 */         dateAxis.setTickUnit(dtu);
/* 436:    */       }
/* 437:410 */       return jfreechart;
/* 438:    */     }
/* 439:    */     catch (Exception ex)
/* 440:    */     {
/* 441:412 */       System.err.println("create history data chart error");
/* 442:    */     }
/* 443:414 */     return null;
/* 444:    */   }
/* 445:    */   
/* 446:    */   private void queryChartAction()
/* 447:    */   {
/* 448:418 */     if (this.jComboBox2.getSelectedItem() != null) {
/* 449:419 */       if (this.periodComboBox.getSelectedIndex() == 0) {
/* 450:420 */         setDataChart("year");
/* 451:421 */       } else if (this.periodComboBox.getSelectedIndex() == 1) {
/* 452:422 */         setDataChart("month");
/* 453:423 */       } else if (this.periodComboBox.getSelectedIndex() == 2) {
/* 454:424 */         setDataChart("day");
/* 455:425 */       } else if (this.periodComboBox.getSelectedIndex() == 3) {
/* 456:426 */         setDataChart("hour");
/* 457:    */       }
/* 458:    */     }
/* 459:    */   }
/* 460:    */   
/* 461:    */   private void setPeriod()
/* 462:    */   {
/* 463:432 */     if (this.periodComboBox.getSelectedIndex() == 0)
/* 464:    */     {
/* 465:433 */       this.jSpinner1.setVisible(true);
/* 466:434 */       this.jTextField3.setVisible(false);
/* 467:435 */       this.jLabel6.setVisible(false);
/* 468:436 */       this.jSpinner2.setVisible(false);
/* 469:437 */       this.jLabel7.setVisible(false);
/* 470:438 */       this.jComboBox3.setVisible(false);
/* 471:439 */       GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 472:440 */       this.jPanel1.setLayout(jPanel1Layout);
/* 473:441 */       jPanel1Layout.setHorizontalGroup(
/* 474:442 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 475:443 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 476:444 */         .addContainerGap()
/* 477:445 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 478:446 */         .addComponent(this.jScrollPane2, -1, 950, 32767)
/* 479:447 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 480:448 */         .addComponent(this.deviceLabel2)
/* 481:449 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 482:450 */         .addComponent(this.jComboBox2, -2, 140, -2)
/* 483:451 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 484:452 */         .addComponent(this.periodLabel)
/* 485:453 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 486:454 */         .addComponent(this.periodComboBox, -2, 95, -2)
/* 487:455 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 488:456 */         .addComponent(this.maohaoLabel)
/* 489:457 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 490:458 */         .addComponent(this.jSpinner1, -2, 73, -2)
/* 491:459 */         .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 492:460 */         .addComponent(this.queryChartButton)))
/* 493:461 */         .addContainerGap()));
/* 494:    */       
/* 495:463 */       jPanel1Layout.setVerticalGroup(
/* 496:464 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 497:465 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 498:466 */         .addContainerGap()
/* 499:467 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 500:468 */         .addComponent(this.deviceLabel2)
/* 501:469 */         .addComponent(this.jComboBox2, -2, -1, -2)
/* 502:470 */         .addComponent(this.periodLabel)
/* 503:471 */         .addComponent(this.periodComboBox, -2, -1, -2)
/* 504:472 */         .addComponent(this.maohaoLabel)
/* 505:473 */         .addComponent(this.jSpinner1, -2, -1, -2)
/* 506:474 */         .addComponent(this.queryChartButton))
/* 507:475 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 508:476 */         .addComponent(this.jScrollPane2, -1, 447, 32767)
/* 509:477 */         .addContainerGap()));
/* 510:    */     }
/* 511:479 */     else if (this.periodComboBox.getSelectedIndex() == 1)
/* 512:    */     {
/* 513:480 */       this.jSpinner1.setVisible(true);
/* 514:481 */       this.jTextField3.setVisible(false);
/* 515:482 */       this.jLabel6.setVisible(false);
/* 516:483 */       this.jSpinner2.setVisible(false);
/* 517:484 */       this.jLabel7.setVisible(false);
/* 518:485 */       this.jComboBox3.setVisible(true);
/* 519:486 */       GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 520:487 */       this.jPanel1.setLayout(jPanel1Layout);
/* 521:488 */       jPanel1Layout.setHorizontalGroup(
/* 522:489 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 523:490 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 524:491 */         .addContainerGap()
/* 525:492 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 526:493 */         .addComponent(this.jScrollPane2, -1, 950, 32767)
/* 527:494 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 528:495 */         .addComponent(this.deviceLabel2)
/* 529:496 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 530:497 */         .addComponent(this.jComboBox2, -2, 140, -2)
/* 531:498 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 532:499 */         .addComponent(this.periodLabel)
/* 533:500 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 534:501 */         .addComponent(this.periodComboBox, -2, 95, -2)
/* 535:502 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 536:503 */         .addComponent(this.maohaoLabel)
/* 537:504 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 538:505 */         .addComponent(this.jSpinner1, -2, 73, -2)
/* 539:506 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 540:507 */         .addComponent(this.jComboBox3, -2, 90, -2)
/* 541:508 */         .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 542:509 */         .addComponent(this.queryChartButton)))
/* 543:510 */         .addContainerGap()));
/* 544:    */       
/* 545:512 */       jPanel1Layout.setVerticalGroup(
/* 546:513 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 547:514 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 548:515 */         .addContainerGap()
/* 549:516 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 550:517 */         .addComponent(this.deviceLabel2)
/* 551:518 */         .addComponent(this.jComboBox2, -2, -1, -2)
/* 552:519 */         .addComponent(this.periodLabel)
/* 553:520 */         .addComponent(this.periodComboBox, -2, -1, -2)
/* 554:521 */         .addComponent(this.maohaoLabel)
/* 555:522 */         .addComponent(this.jSpinner1, -2, -1, -2)
/* 556:523 */         .addComponent(this.queryChartButton)
/* 557:524 */         .addComponent(this.jComboBox3, -2, -1, -2))
/* 558:525 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 559:526 */         .addComponent(this.jScrollPane2, -1, 447, 32767)
/* 560:527 */         .addContainerGap()));
/* 561:    */     }
/* 562:529 */     else if (this.periodComboBox.getSelectedIndex() == 2)
/* 563:    */     {
/* 564:530 */       this.jSpinner1.setVisible(false);
/* 565:531 */       this.jTextField3.setVisible(true);
/* 566:532 */       this.jLabel6.setVisible(true);
/* 567:533 */       this.jSpinner2.setVisible(false);
/* 568:534 */       this.jLabel7.setVisible(false);
/* 569:535 */       this.jComboBox3.setVisible(false);
/* 570:536 */       GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 571:537 */       this.jPanel1.setLayout(jPanel1Layout);
/* 572:538 */       jPanel1Layout.setHorizontalGroup(
/* 573:539 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 574:540 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 575:541 */         .addContainerGap()
/* 576:542 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 577:543 */         .addComponent(this.jScrollPane2, -1, 950, 32767)
/* 578:544 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 579:545 */         .addComponent(this.deviceLabel2)
/* 580:546 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 581:547 */         .addComponent(this.jComboBox2, -2, 140, -2)
/* 582:548 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 583:549 */         .addComponent(this.periodLabel)
/* 584:550 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 585:551 */         .addComponent(this.periodComboBox, -2, 95, -2)
/* 586:552 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 587:553 */         .addComponent(this.maohaoLabel)
/* 588:554 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 589:555 */         .addComponent(this.jTextField3, -2, 96, -2)
/* 590:556 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 591:557 */         .addComponent(this.jLabel6)
/* 592:558 */         .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 593:559 */         .addComponent(this.queryChartButton)))
/* 594:560 */         .addContainerGap()));
/* 595:    */       
/* 596:562 */       jPanel1Layout.setVerticalGroup(
/* 597:563 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 598:564 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 599:565 */         .addContainerGap()
/* 600:566 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 601:567 */         .addComponent(this.queryChartButton)
/* 602:568 */         .addComponent(this.jLabel6)
/* 603:569 */         .addComponent(this.jTextField3, -2, -1, -2)
/* 604:570 */         .addComponent(this.periodComboBox, -2, -1, -2)
/* 605:571 */         .addComponent(this.maohaoLabel)
/* 606:572 */         .addComponent(this.periodLabel)
/* 607:573 */         .addComponent(this.jComboBox2, -2, -1, -2)
/* 608:574 */         .addComponent(this.deviceLabel2))
/* 609:575 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 610:576 */         .addComponent(this.jScrollPane2, -1, 447, 32767)
/* 611:577 */         .addContainerGap()));
/* 612:    */     }
/* 613:579 */     else if (this.periodComboBox.getSelectedIndex() == 3)
/* 614:    */     {
/* 615:580 */       this.jSpinner1.setVisible(false);
/* 616:581 */       this.jTextField3.setVisible(true);
/* 617:582 */       this.jLabel6.setVisible(true);
/* 618:583 */       this.jSpinner2.setVisible(true);
/* 619:584 */       this.jLabel7.setVisible(true);
/* 620:585 */       this.jComboBox3.setVisible(false);
/* 621:586 */       GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 622:587 */       this.jPanel1.setLayout(jPanel1Layout);
/* 623:588 */       jPanel1Layout.setHorizontalGroup(
/* 624:589 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 625:590 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 626:591 */         .addContainerGap()
/* 627:592 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 628:593 */         .addComponent(this.jScrollPane2, -1, 950, 32767)
/* 629:594 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 630:595 */         .addComponent(this.deviceLabel2)
/* 631:596 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 632:597 */         .addComponent(this.jComboBox2, -2, 140, -2)
/* 633:598 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 634:599 */         .addComponent(this.periodLabel)
/* 635:600 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 636:601 */         .addComponent(this.periodComboBox, -2, 95, -2)
/* 637:602 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 638:603 */         .addComponent(this.maohaoLabel)
/* 639:604 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 640:605 */         .addComponent(this.jTextField3, -2, 96, -2)
/* 641:606 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 642:607 */         .addComponent(this.jLabel6)
/* 643:608 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 644:609 */         .addComponent(this.jSpinner2, -2, 56, -2)
/* 645:610 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 646:611 */         .addComponent(this.jLabel7)
/* 647:612 */         .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 648:613 */         .addComponent(this.queryChartButton)))
/* 649:614 */         .addContainerGap()));
/* 650:    */       
/* 651:616 */       jPanel1Layout.setVerticalGroup(
/* 652:617 */         jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 653:618 */         .addGroup(jPanel1Layout.createSequentialGroup()
/* 654:619 */         .addContainerGap()
/* 655:620 */         .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 656:621 */         .addComponent(this.queryChartButton)
/* 657:622 */         .addComponent(this.jLabel7)
/* 658:623 */         .addComponent(this.jSpinner2, -2, -1, -2)
/* 659:624 */         .addComponent(this.jLabel6)
/* 660:625 */         .addComponent(this.jTextField3, -2, -1, -2)
/* 661:626 */         .addComponent(this.periodComboBox, -2, -1, -2)
/* 662:627 */         .addComponent(this.periodLabel)
/* 663:628 */         .addComponent(this.maohaoLabel)
/* 664:629 */         .addComponent(this.jComboBox2, -2, -1, -2)
/* 665:630 */         .addComponent(this.deviceLabel2))
/* 666:631 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 667:632 */         .addComponent(this.jScrollPane2, -1, 447, 32767)
/* 668:633 */         .addContainerGap()));
/* 669:    */     }
/* 670:    */   }
/* 671:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.HistoryDataChart
 * JD-Core Version:    0.7.0.1
 */