/*   1:    */ package cn.com.voltronic.solar.view.panel;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.constants.Constants;
/*   4:    */ import cn.com.voltronic.solar.data.bean.ColumnChartItem;
/*   5:    */ import cn.com.voltronic.solar.data.bean.EnergyData;
/*   6:    */ import cn.com.voltronic.solar.util.VolUtil;
/*   7:    */ import cn.com.voltronic.solar.view.component.AATabbedPane;
/*   8:    */ import java.awt.BorderLayout;
/*   9:    */ import java.awt.Color;
/*  10:    */ import java.awt.Dimension;
/*  11:    */ import java.io.PrintStream;
/*  12:    */ import java.util.List;
/*  13:    */ import javax.swing.JPanel;
/*  14:    */ import org.jfree.chart.ChartFactory;
/*  15:    */ import org.jfree.chart.ChartPanel;
/*  16:    */ import org.jfree.chart.JFreeChart;
/*  17:    */ import org.jfree.chart.axis.CategoryAxis;
/*  18:    */ import org.jfree.chart.axis.CategoryLabelPositions;
/*  19:    */ import org.jfree.chart.axis.ValueAxis;
/*  20:    */ import org.jfree.chart.labels.ItemLabelAnchor;
/*  21:    */ import org.jfree.chart.labels.ItemLabelPosition;
/*  22:    */ import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
/*  23:    */ import org.jfree.chart.plot.CategoryPlot;
/*  24:    */ import org.jfree.chart.plot.PlotOrientation;
/*  25:    */ import org.jfree.chart.renderer.category.BarRenderer;
/*  26:    */ import org.jfree.data.category.DefaultCategoryDataset;
/*  27:    */ import org.jfree.ui.TextAnchor;
/*  28:    */ 
/*  29:    */ public class PowerChartsPanel
/*  30:    */   extends AATabbedPane
/*  31:    */ {
/*  32:    */   private static final long serialVersionUID = 618814205935656976L;
/*  33: 42 */   private CategoryAxis hourCategoryAxis = null;
/*  34: 43 */   private CategoryAxis dayCategoryAxis = null;
/*  35: 44 */   private CategoryAxis monthCategoryAxis = null;
/*  36: 45 */   private CategoryAxis yearCategoryAxis = null;
/*  37: 47 */   private DefaultCategoryDataset hourdataset = null;
/*  38: 48 */   private DefaultCategoryDataset daydataset = null;
/*  39: 49 */   private DefaultCategoryDataset monthdataset = null;
/*  40: 50 */   private DefaultCategoryDataset yeardataset = null;
/*  41: 52 */   private CategoryPlot hourPlot = null;
/*  42: 53 */   private CategoryPlot dayPlot = null;
/*  43: 54 */   private CategoryPlot monthPlot = null;
/*  44: 55 */   private CategoryPlot yearPlot = null;
/*  45: 57 */   private ValueAxis hourRangeAxis = null;
/*  46: 58 */   private ValueAxis dayRangeAxis = null;
/*  47: 59 */   private ValueAxis monthRangeAxis = null;
/*  48: 60 */   private ValueAxis yearRangeAxis = null;
/*  49: 62 */   private double oldMaxH = 100.0D;
/*  50: 63 */   private double oldMaxD = 100.0D;
/*  51: 64 */   private double oldMaxM = 100.0D;
/*  52: 65 */   private double oldMaxY = 100.0D;
/*  53:    */   
/*  54:    */   public PowerChartsPanel()
/*  55:    */   {
/*  56: 68 */     this.hourdataset = new DefaultCategoryDataset();
/*  57: 69 */     this.daydataset = new DefaultCategoryDataset();
/*  58: 70 */     this.monthdataset = new DefaultCategoryDataset();
/*  59: 71 */     this.yeardataset = new DefaultCategoryDataset();
/*  60: 72 */     initComponents();
/*  61: 73 */     setTabPlacement(4);
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void refreshWork(EnergyData data)
/*  65:    */   {
/*  66:    */     try
/*  67:    */     {
/*  68: 78 */       if ((this.hourPlot != null) && (this.hourCategoryAxis != null))
/*  69:    */       {
/*  70: 79 */         this.hourPlot.setDataset(createHourDataset(data));
/*  71: 80 */         this.hourCategoryAxis.setLabel(data.getHorizontalTitleH());
/*  72:    */       }
/*  73: 82 */       if ((this.dayPlot != null) && (this.dayCategoryAxis != null))
/*  74:    */       {
/*  75: 83 */         this.dayPlot.setDataset(createDayDataset(data));
/*  76: 84 */         this.dayCategoryAxis.setLabel(data.getHorizontalTitleD());
/*  77:    */       }
/*  78: 86 */       if ((this.monthPlot != null) && (this.monthCategoryAxis != null))
/*  79:    */       {
/*  80: 87 */         this.monthPlot.setDataset(createMonthDataset(data));
/*  81: 88 */         this.monthCategoryAxis.setLabel(data.getHorizontalTitleM());
/*  82:    */       }
/*  83: 90 */       if ((this.yearPlot != null) && (this.yearCategoryAxis != null))
/*  84:    */       {
/*  85: 91 */         this.yearPlot.setDataset(createYearDataset(data));
/*  86: 92 */         this.yearCategoryAxis.setLabel(data.getHorizontalTitleY());
/*  87:    */       }
/*  88: 95 */       if (this.hourRangeAxis != null)
/*  89:    */       {
/*  90: 96 */         double maxH = data.getMaxH();
/*  91: 97 */         if (this.oldMaxH != maxH)
/*  92:    */         {
/*  93: 98 */           this.hourRangeAxis.setRange(0.0D, maxH);
/*  94: 99 */           this.oldMaxH = maxH;
/*  95:    */         }
/*  96:    */       }
/*  97:102 */       if (this.dayRangeAxis != null)
/*  98:    */       {
/*  99:103 */         double maxD = data.getMaxD();
/* 100:104 */         if (this.oldMaxD != maxD)
/* 101:    */         {
/* 102:105 */           this.dayRangeAxis.setRange(0.0D, data.getMaxD());
/* 103:106 */           this.oldMaxD = maxD;
/* 104:    */         }
/* 105:    */       }
/* 106:109 */       if (this.monthRangeAxis != null)
/* 107:    */       {
/* 108:110 */         double maxM = data.getMaxM();
/* 109:111 */         if (this.oldMaxM != maxM)
/* 110:    */         {
/* 111:112 */           this.monthRangeAxis.setRange(0.0D, data.getMaxM());
/* 112:113 */           this.oldMaxM = maxM;
/* 113:    */         }
/* 114:    */       }
/* 115:116 */       if (this.yearRangeAxis != null)
/* 116:    */       {
/* 117:117 */         double maxY = data.getMaxY();
/* 118:118 */         if (this.oldMaxY != maxY)
/* 119:    */         {
/* 120:119 */           this.yearRangeAxis.setRange(0.0D, data.getMaxY());
/* 121:120 */           this.oldMaxY = maxY;
/* 122:    */         }
/* 123:    */       }
/* 124:    */     }
/* 125:    */     catch (Exception ex)
/* 126:    */     {
/* 127:124 */       System.out.println("chart error");
/* 128:    */     }
/* 129:    */   }
/* 130:    */   
/* 131:    */   private DefaultCategoryDataset createHourDataset(EnergyData data)
/* 132:    */     throws Exception
/* 133:    */   {
/* 134:130 */     if (this.hourdataset != null) {
/* 135:131 */       this.hourdataset.clear();
/* 136:    */     } else {
/* 137:133 */       this.hourdataset = new DefaultCategoryDataset();
/* 138:    */     }
/* 139:135 */     List<ColumnChartItem> hourList = data.getEnergyHours();
/* 140:136 */     for (int i = 0; i < hourList.size(); i++) {
/* 141:137 */       this.hourdataset.addValue(VolUtil.parseDouble(
/* 142:138 */         ((ColumnChartItem)hourList.get(i)).getVerticalValue()), ((ColumnChartItem)hourList.get(i)).getShowName(), 
/* 143:139 */         ((ColumnChartItem)hourList.get(i)).getHorizontalValue());
/* 144:    */     }
/* 145:141 */     return this.hourdataset;
/* 146:    */   }
/* 147:    */   
/* 148:    */   private DefaultCategoryDataset createDayDataset(EnergyData data)
/* 149:    */     throws Exception
/* 150:    */   {
/* 151:146 */     if (this.daydataset != null) {
/* 152:147 */       this.daydataset.clear();
/* 153:    */     } else {
/* 154:149 */       this.daydataset = new DefaultCategoryDataset();
/* 155:    */     }
/* 156:151 */     List<ColumnChartItem> dayList = data.getEnergyDays();
/* 157:152 */     for (int i = 0; i < dayList.size(); i++) {
/* 158:153 */       this.daydataset.addValue(VolUtil.parseDouble(
/* 159:154 */         ((ColumnChartItem)dayList.get(i)).getVerticalValue()), ((ColumnChartItem)dayList.get(i)).getShowName(), 
/* 160:155 */         ((ColumnChartItem)dayList.get(i)).getHorizontalValue());
/* 161:    */     }
/* 162:157 */     return this.daydataset;
/* 163:    */   }
/* 164:    */   
/* 165:    */   private DefaultCategoryDataset createMonthDataset(EnergyData data)
/* 166:    */     throws Exception
/* 167:    */   {
/* 168:162 */     if (this.monthdataset != null) {
/* 169:163 */       this.monthdataset.clear();
/* 170:    */     } else {
/* 171:165 */       this.monthdataset = new DefaultCategoryDataset();
/* 172:    */     }
/* 173:167 */     List<ColumnChartItem> monthList = data.getEnergyMonths();
/* 174:168 */     for (int i = 0; i < monthList.size(); i++) {
/* 175:169 */       this.monthdataset.addValue(VolUtil.parseDouble(
/* 176:170 */         ((ColumnChartItem)monthList.get(i)).getVerticalValue()), ((ColumnChartItem)monthList.get(i)).getShowName(), 
/* 177:171 */         ((ColumnChartItem)monthList.get(i)).getHorizontalValue());
/* 178:    */     }
/* 179:173 */     return this.monthdataset;
/* 180:    */   }
/* 181:    */   
/* 182:    */   private DefaultCategoryDataset createYearDataset(EnergyData data)
/* 183:    */     throws Exception
/* 184:    */   {
/* 185:178 */     if (this.yeardataset != null) {
/* 186:179 */       this.yeardataset.clear();
/* 187:    */     } else {
/* 188:181 */       this.yeardataset = new DefaultCategoryDataset();
/* 189:    */     }
/* 190:183 */     List<ColumnChartItem> yearList = data.getEnergyYears();
/* 191:184 */     for (int i = 0; i < yearList.size(); i++) {
/* 192:185 */       this.yeardataset.addValue(VolUtil.parseDouble(
/* 193:186 */         ((ColumnChartItem)yearList.get(i)).getVerticalValue()), ((ColumnChartItem)yearList.get(i)).getShowName(), 
/* 194:187 */         ((ColumnChartItem)yearList.get(i)).getHorizontalValue());
/* 195:    */     }
/* 196:189 */     return this.yeardataset;
/* 197:    */   }
/* 198:    */   
/* 199:    */   private ChartPanel createHourChart(String title, String unit)
/* 200:    */   {
/* 201:193 */     JFreeChart hourChart = ChartFactory.createBarChart("", title, unit, 
/* 202:194 */       this.hourdataset, PlotOrientation.VERTICAL, false, true, false);
/* 203:195 */     hourChart.setBackgroundPaint(Constants.BG_COLOR);
/* 204:    */     
/* 205:197 */     this.hourPlot = hourChart.getCategoryPlot();
/* 206:198 */     this.hourPlot.setBackgroundPaint(Constants.BG_COLOR);
/* 207:199 */     this.hourPlot.setDomainGridlinePaint(Constants.BG_COLOR);
/* 208:200 */     this.hourPlot.setDomainGridlinesVisible(true);
/* 209:201 */     this.hourPlot.setRangeGridlinePaint(Color.white);
/* 210:    */     
/* 211:203 */     this.hourCategoryAxis = this.hourPlot.getDomainAxis();
/* 212:204 */     this.hourCategoryAxis.setLabelPaint(Color.white);
/* 213:205 */     this.hourCategoryAxis.setTickLabelPaint(Color.white);
/* 214:    */     
/* 215:207 */     this.hourRangeAxis = this.hourPlot.getRangeAxis();
/* 216:208 */     this.hourRangeAxis.setUpperMargin(0.1D);
/* 217:209 */     this.hourRangeAxis.setLabelPaint(Color.white);
/* 218:210 */     this.hourRangeAxis.setTickLabelPaint(Color.white);
/* 219:211 */     this.hourRangeAxis.setRange(0.0D, this.oldMaxH);
/* 220:    */     
/* 221:213 */     BarRenderer barRenderer = (BarRenderer)this.hourPlot.getRenderer();
/* 222:214 */     barRenderer
/* 223:215 */       .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
/* 224:216 */     barRenderer.setBaseItemLabelsVisible(true);
/* 225:    */     
/* 226:218 */     barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
/* 227:219 */       ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
/* 228:220 */     barRenderer.setItemLabelsVisible(true);
/* 229:    */     
/* 230:222 */     barRenderer.setPaint(new Color(232, 107, 30));
/* 231:223 */     barRenderer.setMinimumBarLength(0.02D);
/* 232:224 */     barRenderer.setMaxBarWidth(0.07000000000000001D);
/* 233:225 */     barRenderer.setItemLabelPaint(Color.white);
/* 234:    */     
/* 235:227 */     ChartPanel chartPanel = new ChartPanel(hourChart);
/* 236:228 */     chartPanel.setPreferredSize(new Dimension(620, 312));
/* 237:229 */     return chartPanel;
/* 238:    */   }
/* 239:    */   
/* 240:    */   private ChartPanel createDayChart(String title, String unit)
/* 241:    */   {
/* 242:233 */     JFreeChart dayChart = ChartFactory.createBarChart("", title, unit, 
/* 243:234 */       this.daydataset, PlotOrientation.VERTICAL, false, true, false);
/* 244:235 */     dayChart.setBackgroundPaint(Constants.BG_COLOR);
/* 245:    */     
/* 246:237 */     this.dayPlot = dayChart.getCategoryPlot();
/* 247:238 */     this.dayPlot.setBackgroundPaint(Constants.BG_COLOR);
/* 248:239 */     this.dayPlot.setDomainGridlinePaint(Constants.BG_COLOR);
/* 249:240 */     this.dayPlot.setDomainGridlinesVisible(true);
/* 250:241 */     this.dayPlot.setRangeGridlinePaint(Color.white);
/* 251:    */     
/* 252:243 */     this.dayCategoryAxis = this.dayPlot.getDomainAxis();
/* 253:244 */     this.dayCategoryAxis.setLabelPaint(Color.white);
/* 254:245 */     this.dayCategoryAxis.setTickLabelPaint(Color.white);
/* 255:246 */     this.dayCategoryAxis
/* 256:247 */       .setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
/* 257:    */     
/* 258:249 */     this.dayRangeAxis = this.dayPlot.getRangeAxis();
/* 259:250 */     this.dayRangeAxis.setUpperMargin(0.1D);
/* 260:251 */     this.dayRangeAxis.setLabelPaint(Color.white);
/* 261:252 */     this.dayRangeAxis.setTickLabelPaint(Color.white);
/* 262:253 */     this.dayRangeAxis.setRange(0.0D, this.oldMaxD);
/* 263:    */     
/* 264:255 */     BarRenderer barRenderer = (BarRenderer)this.dayPlot.getRenderer();
/* 265:256 */     barRenderer
/* 266:257 */       .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
/* 267:258 */     barRenderer.setBaseItemLabelsVisible(true);
/* 268:    */     
/* 269:260 */     barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
/* 270:261 */       ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
/* 271:262 */     barRenderer.setItemLabelsVisible(true);
/* 272:    */     
/* 273:264 */     barRenderer.setPaint(new Color(232, 107, 30));
/* 274:265 */     barRenderer.setMinimumBarLength(0.02D);
/* 275:266 */     barRenderer.setMaxBarWidth(0.07000000000000001D);
/* 276:267 */     barRenderer.setItemLabelPaint(Color.white);
/* 277:    */     
/* 278:269 */     ChartPanel chartPanel = new ChartPanel(dayChart);
/* 279:270 */     chartPanel.setPreferredSize(new Dimension(620, 312));
/* 280:271 */     return chartPanel;
/* 281:    */   }
/* 282:    */   
/* 283:    */   private ChartPanel createMonthChart(String title, String unit)
/* 284:    */   {
/* 285:275 */     JFreeChart monthChart = ChartFactory.createBarChart("", title, unit, 
/* 286:276 */       this.monthdataset, PlotOrientation.VERTICAL, false, true, false);
/* 287:277 */     monthChart.setBackgroundPaint(Constants.BG_COLOR);
/* 288:    */     
/* 289:279 */     this.monthPlot = monthChart.getCategoryPlot();
/* 290:280 */     this.monthPlot.setBackgroundPaint(Constants.BG_COLOR);
/* 291:281 */     this.monthPlot.setDomainGridlinePaint(Constants.BG_COLOR);
/* 292:282 */     this.monthPlot.setDomainGridlinesVisible(true);
/* 293:283 */     this.monthPlot.setRangeGridlinePaint(Color.white);
/* 294:    */     
/* 295:285 */     this.monthCategoryAxis = this.monthPlot.getDomainAxis();
/* 296:286 */     this.monthCategoryAxis.setLabelPaint(Color.white);
/* 297:287 */     this.monthCategoryAxis.setTickLabelPaint(Color.white);
/* 298:    */     
/* 299:289 */     this.monthRangeAxis = this.monthPlot.getRangeAxis();
/* 300:290 */     this.monthRangeAxis.setUpperMargin(0.1D);
/* 301:291 */     this.monthRangeAxis.setLabelPaint(Color.white);
/* 302:292 */     this.monthRangeAxis.setTickLabelPaint(Color.white);
/* 303:293 */     this.monthRangeAxis.setRange(0.0D, this.oldMaxM);
/* 304:    */     
/* 305:295 */     BarRenderer barRenderer = (BarRenderer)this.monthPlot.getRenderer();
/* 306:296 */     barRenderer
/* 307:297 */       .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
/* 308:298 */     barRenderer.setBaseItemLabelsVisible(true);
/* 309:    */     
/* 310:300 */     barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
/* 311:301 */       ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
/* 312:302 */     barRenderer.setItemLabelsVisible(true);
/* 313:    */     
/* 314:304 */     barRenderer.setPaint(new Color(232, 107, 30));
/* 315:305 */     barRenderer.setMinimumBarLength(0.02D);
/* 316:306 */     barRenderer.setMaxBarWidth(0.07000000000000001D);
/* 317:307 */     barRenderer.setItemLabelPaint(Color.white);
/* 318:    */     
/* 319:309 */     ChartPanel chartPanel = new ChartPanel(monthChart);
/* 320:310 */     chartPanel.setPreferredSize(new Dimension(620, 312));
/* 321:311 */     return chartPanel;
/* 322:    */   }
/* 323:    */   
/* 324:    */   private ChartPanel createYearChart(String title, String unit)
/* 325:    */   {
/* 326:315 */     JFreeChart yearChart = ChartFactory.createBarChart("", title, unit, 
/* 327:316 */       this.yeardataset, PlotOrientation.VERTICAL, false, true, false);
/* 328:317 */     yearChart.setBackgroundPaint(Constants.BG_COLOR);
/* 329:    */     
/* 330:319 */     this.yearPlot = yearChart.getCategoryPlot();
/* 331:320 */     this.yearPlot.setBackgroundPaint(Constants.BG_COLOR);
/* 332:321 */     this.yearPlot.setDomainGridlinePaint(Constants.BG_COLOR);
/* 333:322 */     this.yearPlot.setDomainGridlinesVisible(true);
/* 334:323 */     this.yearPlot.setRangeGridlinePaint(Color.white);
/* 335:    */     
/* 336:325 */     this.yearCategoryAxis = this.yearPlot.getDomainAxis();
/* 337:326 */     this.yearCategoryAxis.setLabelPaint(Color.white);
/* 338:327 */     this.yearCategoryAxis.setTickLabelPaint(Color.white);
/* 339:    */     
/* 340:329 */     this.yearRangeAxis = this.yearPlot.getRangeAxis();
/* 341:330 */     this.yearRangeAxis.setUpperMargin(0.1D);
/* 342:331 */     this.yearRangeAxis.setLabelPaint(Color.white);
/* 343:332 */     this.yearRangeAxis.setTickLabelPaint(Color.white);
/* 344:333 */     this.yearRangeAxis.setRange(0.0D, this.oldMaxY);
/* 345:    */     
/* 346:335 */     BarRenderer barRenderer = (BarRenderer)this.yearPlot.getRenderer();
/* 347:336 */     barRenderer
/* 348:337 */       .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
/* 349:338 */     barRenderer.setBaseItemLabelsVisible(true);
/* 350:    */     
/* 351:340 */     barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
/* 352:341 */       ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
/* 353:342 */     barRenderer.setItemLabelsVisible(true);
/* 354:    */     
/* 355:344 */     barRenderer.setPaint(new Color(232, 107, 30));
/* 356:345 */     barRenderer.setMinimumBarLength(0.02D);
/* 357:346 */     barRenderer.setMaxBarWidth(0.07000000000000001D);
/* 358:347 */     barRenderer.setItemLabelPaint(Color.white);
/* 359:    */     
/* 360:349 */     ChartPanel chartPanel = new ChartPanel(yearChart);
/* 361:350 */     chartPanel.setPreferredSize(new Dimension(620, 312));
/* 362:351 */     return chartPanel;
/* 363:    */   }
/* 364:    */   
/* 365:    */   public void initComponents()
/* 366:    */   {
/* 367:356 */     JPanel hourPanel = new JPanel(new BorderLayout());
/* 368:357 */     JPanel dayPanel = new JPanel(new BorderLayout());
/* 369:358 */     JPanel monthPanel = new JPanel(new BorderLayout());
/* 370:359 */     JPanel yearPanel = new JPanel(new BorderLayout());
/* 371:    */     
/* 372:    */ 
/* 373:    */ 
/* 374:    */ 
/* 375:    */ 
/* 376:    */ 
/* 377:366 */     hourPanel.add(createHourChart("", "kWh"));
/* 378:367 */     dayPanel.add(createDayChart("", "kWh"));
/* 379:368 */     monthPanel.add(createMonthChart("", "kWh"));
/* 380:369 */     yearPanel.add(createYearChart("", "kWh"));
/* 381:    */     
/* 382:371 */     addTab("message.perHour", hourPanel);
/* 383:372 */     addTab("message.perDay", dayPanel);
/* 384:373 */     addTab("message.perMonth", monthPanel);
/* 385:374 */     addTab("message.perYear", yearPanel);
/* 386:    */   }
/* 387:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.panel.PowerChartsPanel
 * JD-Core Version:    0.7.0.1
 */