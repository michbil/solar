/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   4:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   5:    */ import cn.com.voltronic.solar.constants.Constants;
/*   6:    */ import cn.com.voltronic.solar.dao.PriceDao;
/*   7:    */ import cn.com.voltronic.solar.data.bean.Electrovalence;
/*   8:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   9:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  10:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  11:    */ import cn.com.voltronic.solar.view.component.AADefaultTableModel;
/*  12:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  13:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  14:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  15:    */ import cn.com.voltronic.solar.view.component.I18NListener;
/*  16:    */ import cn.com.voltronic.solar.view.component.MyCalendarPanel;
/*  17:    */ import java.awt.BorderLayout;
/*  18:    */ import java.awt.Color;
/*  19:    */ import java.awt.Container;
/*  20:    */ import java.awt.Frame;
/*  21:    */ import java.awt.event.ActionEvent;
/*  22:    */ import java.awt.event.ActionListener;
/*  23:    */ import java.awt.event.KeyAdapter;
/*  24:    */ import java.awt.event.KeyEvent;
/*  25:    */ import java.awt.event.MouseAdapter;
/*  26:    */ import java.awt.event.MouseEvent;
/*  27:    */ import java.text.DateFormat;
/*  28:    */ import java.text.SimpleDateFormat;
/*  29:    */ import java.util.ArrayList;
/*  30:    */ import java.util.Date;
/*  31:    */ import java.util.List;
/*  32:    */ import javax.swing.BorderFactory;
/*  33:    */ import javax.swing.GroupLayout;
/*  34:    */ import javax.swing.GroupLayout.Alignment;
/*  35:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  36:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  37:    */ import javax.swing.ImageIcon;
/*  38:    */ import javax.swing.JPanel;
/*  39:    */ import javax.swing.JScrollPane;
/*  40:    */ import javax.swing.JTable;
/*  41:    */ import javax.swing.JTextField;
/*  42:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  43:    */ import javax.swing.table.JTableHeader;
/*  44:    */ 
/*  45:    */ public class PriceJDialog
/*  46:    */   extends AADialog
/*  47:    */ {
/*  48:    */   private static final long serialVersionUID = -2110412845255459296L;
/*  49:    */   private AAButton addButton;
/*  50:    */   private AAButton cancelButton;
/*  51:    */   private JTextField dateTextField;
/*  52:    */   private AAButton deleteButton;
/*  53:    */   private AALabel jLabel1;
/*  54:    */   private AALabel jLabel2;
/*  55:    */   private AALabel jLabel3;
/*  56:    */   private JPanel jPanel1;
/*  57:    */   private JPanel jPanel2;
/*  58:    */   private JPanel jPanel3;
/*  59:    */   private JScrollPane jScrollPane1;
/*  60:    */   private JTable priceTable;
/*  61:    */   private JTextField priceTextField;
/*  62: 64 */   private PriceDao priceDao = null;
/*  63: 66 */   private final int MAX_SIZE = 12;
/*  64: 67 */   private final int MAX_COLUMN = 3;
/*  65: 68 */   private AADefaultTableModel tableModel = null;
/*  66: 69 */   private List<Electrovalence> pricelist = new ArrayList();
/*  67: 70 */   private String[] tableTitle = {
/*  68: 71 */     "message.effectdate", "message.electrovalence", "message.trandate" };
/*  69:    */   
/*  70:    */   public PriceJDialog(Frame parent, boolean modal)
/*  71:    */   {
/*  72: 75 */     super(parent, modal);
/*  73: 76 */     this.priceDao = new PriceDao();
/*  74: 77 */     initComponents();
/*  75: 78 */     setTitle("message.electrovalenceSet");
/*  76: 79 */     setSize(getWidth(), 422);
/*  77: 80 */     setLocationRelativeTo(null);
/*  78: 81 */     setVisible(true);
/*  79:    */   }
/*  80:    */   
/*  81:    */   private void initComponents()
/*  82:    */   {
/*  83: 86 */     this.jPanel1 = new JPanel();
/*  84: 87 */     this.jScrollPane1 = new JScrollPane();
/*  85: 88 */     this.jPanel3 = new JPanel();
/*  86: 89 */     this.jLabel1 = new AALabel();
/*  87: 90 */     this.dateTextField = new JTextField();
/*  88: 91 */     this.jLabel2 = new AALabel();
/*  89: 92 */     this.jLabel3 = new AALabel();
/*  90: 93 */     this.priceTextField = new JTextField();
/*  91: 94 */     this.addButton = new AAButton();
/*  92: 95 */     this.deleteButton = new AAButton();
/*  93: 96 */     this.jPanel2 = new JPanel();
/*  94: 97 */     this.cancelButton = new AAButton();
/*  95:    */     
/*  96: 99 */     this.priceTable = new JTable()
/*  97:    */     {
/*  98:    */       private static final long serialVersionUID = -2097364819095589911L;
/*  99:    */       
/* 100:    */       public boolean isCellSelected(int row, int column)
/* 101:    */       {
/* 102:102 */         if (row >= PriceJDialog.this.pricelist.size()) {
/* 103:103 */           return false;
/* 104:    */         }
/* 105:105 */         return super.isCellSelected(row, column);
/* 106:    */       }
/* 107:108 */     };
/* 108:109 */     this.priceTable.getTableHeader().setReorderingAllowed(false);
/* 109:110 */     this.priceTable.getTableHeader().setForeground(Color.white);
/* 110:111 */     this.priceTable.setSelectionMode(0);
/* 111:112 */     this.tableModel = new AADefaultTableModel(new Object[12][3], 
/* 112:113 */       this.tableTitle)
/* 113:    */       {
/* 114:    */         private static final long serialVersionUID = -4444769250474742659L;
/* 115:    */         
/* 116:    */         public boolean isCellEditable(int row, int column)
/* 117:    */         {
/* 118:116 */           return false;
/* 119:    */         }
/* 120:118 */       };
/* 121:119 */       this.priceTable.setModel(this.tableModel);
/* 122:    */       
/* 123:121 */       setTableValues();
/* 124:    */       
/* 125:123 */       setDefaultCloseOperation(2);
/* 126:    */       
/* 127:125 */       this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 128:126 */       this.jPanel1.setLayout(new BorderLayout());
/* 129:    */       
/* 130:128 */       this.jScrollPane1.setViewportView(this.priceTable);
/* 131:    */       
/* 132:130 */       this.jPanel1.add(this.jScrollPane1, "Center");
/* 133:    */       
/* 134:132 */       this.jLabel1.setText("message.effectdate[:]");
/* 135:    */       
/* 136:134 */       this.dateTextField.setEditable(false);
/* 137:135 */       this.dateTextField.setBackground(I18NListener.bgColor);
/* 138:136 */       this.dateTextField.setText(DateUtils.getNowDate());
/* 139:137 */       this.dateTextField.addMouseListener(new MouseAdapter()
/* 140:    */       {
/* 141:    */         public void mouseClicked(MouseEvent e)
/* 142:    */         {
/* 143:140 */           MyCalendarPanel calendarPanel = new MyCalendarPanel(PriceJDialog.this.dateTextField);
/* 144:141 */           calendarPanel.setBounds(PriceJDialog.this.getX() + PriceJDialog.this.jLabel2.getX() + 30, PriceJDialog.this.getY() + 
/* 145:142 */             PriceJDialog.this.jLabel2.getY() + 10, calendarPanel.getWidth(), 
/* 146:143 */             calendarPanel.getHeight());
/* 147:144 */           calendarPanel.setVisible(true);
/* 148:    */         }
/* 149:147 */       });
/* 150:148 */       this.jLabel2.setIcon(new ImageIcon(Constants.DATE));
/* 151:149 */       this.jLabel2.addMouseListener(new MouseAdapter()
/* 152:    */       {
/* 153:    */         public void mouseClicked(MouseEvent e)
/* 154:    */         {
/* 155:152 */           MyCalendarPanel calendarPanel = new MyCalendarPanel(PriceJDialog.this.dateTextField);
/* 156:153 */           calendarPanel.setBounds(PriceJDialog.this.getX() + PriceJDialog.this.jLabel2.getX() + 30, PriceJDialog.this.getY() + 
/* 157:154 */             PriceJDialog.this.jLabel2.getY() + 10, calendarPanel.getWidth(), 
/* 158:155 */             calendarPanel.getHeight());
/* 159:156 */           calendarPanel.setVisible(true);
/* 160:    */         }
/* 161:159 */       });
/* 162:160 */       this.jLabel3.setText("message.electrovalence[:]");
/* 163:    */       
/* 164:162 */       this.priceTextField.setText("0.0");
/* 165:163 */       this.priceTextField.addKeyListener(new KeyAdapter()
/* 166:    */       {
/* 167:    */         public void keyTyped(KeyEvent e)
/* 168:    */         {
/* 169:165 */           char c = e.getKeyChar();
/* 170:166 */           if ((c == '.') && (PriceJDialog.this.priceTextField.getText().toString().indexOf('.') != -1)) {
/* 171:167 */             e.consume();
/* 172:    */           }
/* 173:169 */           if ((Character.isDigit(c)) || (c == '.') || (c == '\b') || (c == '')) {
/* 174:170 */             return;
/* 175:    */           }
/* 176:172 */           e.consume();
/* 177:    */         }
/* 178:    */         
/* 179:    */         public void keyPressed(KeyEvent e)
/* 180:    */         {
/* 181:175 */           if (e.isControlDown()) {
/* 182:176 */             e.consume();
/* 183:    */           }
/* 184:    */         }
/* 185:180 */       });
/* 186:181 */       this.addButton.setText("message.add");
/* 187:182 */       this.addButton.addActionListener(new ActionListener()
/* 188:    */       {
/* 189:    */         public void actionPerformed(ActionEvent e)
/* 190:    */         {
/* 191:185 */           if (!SolarPowerTray.isLogin)
/* 192:    */           {
/* 193:186 */             new LoginJDialog(new Frame(), true);
/* 194:187 */             return;
/* 195:    */           }
/* 196:189 */           PriceJDialog.this.addPrice();
/* 197:    */         }
/* 198:192 */       });
/* 199:193 */       this.deleteButton.setText("message.del");
/* 200:194 */       this.deleteButton.addActionListener(new ActionListener()
/* 201:    */       {
/* 202:    */         public void actionPerformed(ActionEvent e)
/* 203:    */         {
/* 204:197 */           if (!SolarPowerTray.isLogin)
/* 205:    */           {
/* 206:198 */             new LoginJDialog(new Frame(), true);
/* 207:199 */             return;
/* 208:    */           }
/* 209:201 */           PriceJDialog.this.deletePrice();
/* 210:    */         }
/* 211:204 */       });
/* 212:205 */       GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 213:206 */       this.jPanel3.setLayout(jPanel3Layout);
/* 214:207 */       jPanel3Layout.setHorizontalGroup(
/* 215:208 */         jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 216:209 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 217:210 */         .addContainerGap()
/* 218:211 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 219:212 */         .addComponent(this.jLabel3)
/* 220:213 */         .addComponent(this.jLabel1))
/* 221:214 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 222:215 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 223:216 */         .addComponent(this.priceTextField)
/* 224:217 */         .addComponent(this.dateTextField, -1, 111, 32767))
/* 225:218 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 226:219 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 227:220 */         .addComponent(this.jLabel2)
/* 228:221 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 229:222 */         .addComponent(this.addButton)
/* 230:223 */         .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 231:224 */         .addComponent(this.deleteButton)))
/* 232:225 */         .addContainerGap(42, 32767)));
/* 233:    */       
/* 234:227 */       jPanel3Layout.setVerticalGroup(
/* 235:228 */         jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 236:229 */         .addGroup(jPanel3Layout.createSequentialGroup()
/* 237:230 */         .addContainerGap()
/* 238:231 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 239:232 */         .addComponent(this.jLabel2)
/* 240:233 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 241:234 */         .addComponent(this.jLabel1)
/* 242:235 */         .addComponent(this.dateTextField, -2, -1, -2)))
/* 243:236 */         .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 244:237 */         .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 245:238 */         .addComponent(this.jLabel3)
/* 246:239 */         .addComponent(this.priceTextField, -2, -1, -2)
/* 247:240 */         .addComponent(this.addButton)
/* 248:241 */         .addComponent(this.deleteButton))
/* 249:242 */         .addContainerGap(-1, 32767)));
/* 250:    */       
/* 251:    */ 
/* 252:245 */       this.jPanel1.add(this.jPanel3, "Last");
/* 253:    */       
/* 254:247 */       getContentPane().add(this.jPanel1, "Center");
/* 255:    */       
/* 256:249 */       this.cancelButton.setText("message.close");
/* 257:250 */       this.cancelButton.addActionListener(new ActionListener()
/* 258:    */       {
/* 259:    */         public void actionPerformed(ActionEvent e)
/* 260:    */         {
/* 261:253 */           PriceJDialog.this.dispose();
/* 262:    */         }
/* 263:255 */       });
/* 264:256 */       this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 265:257 */       GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 266:258 */       this.jPanel2.setLayout(jPanel2Layout);
/* 267:259 */       jPanel2Layout.setHorizontalGroup(
/* 268:260 */         jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 269:261 */         .addGroup(GroupLayout.Alignment.TRAILING, 
/* 270:262 */         jPanel2Layout.createSequentialGroup().addContainerGap(241, 32767)
/* 271:263 */         .addComponent(this.cancelButton)
/* 272:264 */         .addContainerGap()));
/* 273:    */       
/* 274:266 */       jPanel2Layout.setVerticalGroup(
/* 275:267 */         jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 276:268 */         .addGroup(jPanel2Layout.createSequentialGroup()
/* 277:269 */         .addContainerGap()
/* 278:270 */         .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 279:271 */         .addComponent(this.cancelButton))
/* 280:    */         
/* 281:273 */         .addContainerGap(-1, 32767)));
/* 282:    */       
/* 283:    */ 
/* 284:276 */       getContentPane().add(this.jPanel2, "South");
/* 285:    */       
/* 286:278 */       pack();
/* 287:    */     }
/* 288:    */     
/* 289:    */     private void addPrice()
/* 290:    */     {
/* 291:282 */       int message = DisplayMessage.showConfirmDialog("message.confirmAdd", "message.confirm");
/* 292:283 */       if (message == 1) {
/* 293:284 */         return;
/* 294:    */       }
/* 295:286 */       String dateStr = this.dateTextField.getText().trim();
/* 296:287 */       String price = this.priceTextField.getText().trim();
/* 297:288 */       if ((price == null) || ("".equals(price)))
/* 298:    */       {
/* 299:289 */         this.priceTextField.requestFocus(true);
/* 300:290 */         return;
/* 301:    */       }
/* 302:292 */       DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 303:293 */       Date effectiveDate = new Date();
/* 304:294 */       double priceDouble = 0.0D;
/* 305:    */       try
/* 306:    */       {
/* 307:296 */         effectiveDate = format.parse(dateStr);
/* 308:297 */         priceDouble = Double.parseDouble(price);
/* 309:    */       }
/* 310:    */       catch (Exception ex)
/* 311:    */       {
/* 312:299 */         ex.printStackTrace();
/* 313:    */       }
/* 314:301 */       if (priceDouble <= 0.0D)
/* 315:    */       {
/* 316:302 */         this.priceTextField.requestFocus(true);
/* 317:303 */         return;
/* 318:    */       }
/* 319:305 */       if (!this.priceDao.isExist(effectiveDate))
/* 320:    */       {
/* 321:306 */         Date inputDate = new Date();
/* 322:307 */         Electrovalence data = new Electrovalence();
/* 323:308 */         data.setEffectdate(effectiveDate);
/* 324:309 */         data.setPrice(priceDouble);
/* 325:310 */         data.setTrandate(inputDate);
/* 326:311 */         boolean result = this.priceDao.addElectrovalence(data);
/* 327:312 */         if (result) {
/* 328:313 */           setTableValues();
/* 329:    */         } else {
/* 330:315 */           DisplayMessage.showErrorDialog("message.addfailure");
/* 331:    */         }
/* 332:    */       }
/* 333:    */       else
/* 334:    */       {
/* 335:318 */         DisplayMessage.showErrorDialog("message.adderror");
/* 336:    */       }
/* 337:    */     }
/* 338:    */     
/* 339:    */     private void deletePrice()
/* 340:    */     {
/* 341:323 */       int message = DisplayMessage.showConfirmDialog("message.confirmDelete", "message.confirm");
/* 342:324 */       if (message == 1) {
/* 343:325 */         return;
/* 344:    */       }
/* 345:327 */       int index = this.priceTable.getSelectedRow();
/* 346:328 */       if (index == -1)
/* 347:    */       {
/* 348:329 */         DisplayMessage.showErrorDialog("message.pleaseselect");
/* 349:330 */         return;
/* 350:    */       }
/* 351:    */       try
/* 352:    */       {
/* 353:333 */         Object object = this.tableModel.getValueAt(index, 0);
/* 354:334 */         if (object == null)
/* 355:    */         {
/* 356:335 */           DisplayMessage.showErrorDialog("message.pleaseselect");
/* 357:336 */           return;
/* 358:    */         }
/* 359:338 */         String effectStr = this.tableModel.getValueAt(index, 0).toString().trim();
/* 360:339 */         String price = this.tableModel.getValueAt(index, 1).toString().trim();
/* 361:340 */         String tranStr = this.tableModel.getValueAt(index, 2).toString().trim();
/* 362:341 */         DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 363:342 */         Date effectdate = new Date();
/* 364:343 */         Date trandate = new Date();
/* 365:    */         try
/* 366:    */         {
/* 367:345 */           effectdate = format.parse(effectStr);
/* 368:346 */           trandate = format.parse(tranStr);
/* 369:    */         }
/* 370:    */         catch (Exception ex)
/* 371:    */         {
/* 372:348 */           ex.printStackTrace();
/* 373:    */         }
/* 374:350 */         Electrovalence electrovalence = new Electrovalence();
/* 375:351 */         electrovalence.setEffectdate(effectdate);
/* 376:352 */         electrovalence.setPrice(Double.parseDouble(price));
/* 377:353 */         electrovalence.setTrandate(trandate);
/* 378:354 */         boolean result = this.priceDao.removeElectrovalence(electrovalence);
/* 379:355 */         if (result) {
/* 380:356 */           setTableValues();
/* 381:    */         } else {
/* 382:358 */           DisplayMessage.showErrorDialog("message.delfailure");
/* 383:    */         }
/* 384:    */       }
/* 385:    */       catch (Exception localException1) {}
/* 386:    */     }
/* 387:    */     
/* 388:    */     private void setTableValues()
/* 389:    */     {
/* 390:366 */       this.pricelist = this.priceDao.queryElectrovalence();
/* 391:367 */       int size = this.pricelist.size();
/* 392:368 */       if (size < 12) {
/* 393:369 */         size = 12;
/* 394:    */       }
/* 395:371 */       Object[][] obj = new Object[size][3];
/* 396:372 */       for (int i = 0; i < this.pricelist.size(); i++)
/* 397:    */       {
/* 398:373 */         obj[i][0] = DateUtils.getFormatDate(((Electrovalence)this.pricelist.get(i)).getEffectdate());
/* 399:374 */         obj[i][1] = Double.valueOf(((Electrovalence)this.pricelist.get(i)).getPrice());
/* 400:375 */         obj[i][2] = DateUtils.getFormatTimestamp(((Electrovalence)this.pricelist.get(i)).getTrandate());
/* 401:    */       }
/* 402:377 */       if (this.pricelist.size() < 12) {
/* 403:378 */         for (int i = this.pricelist.size(); i < 12; i++) {
/* 404:379 */           for (int j = 0; j < 3; j++) {
/* 405:380 */             obj[i][j] = null;
/* 406:    */           }
/* 407:    */         }
/* 408:    */       }
/* 409:384 */       this.tableModel.setDataVector(obj, this.tableTitle);
/* 410:    */     }
/* 411:    */   }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.PriceJDialog
 * JD-Core Version:    0.7.0.1
 */