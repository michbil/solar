/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   4:    */ import cn.com.voltronic.solar.constants.Constants;
/*   5:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   6:    */ import java.awt.BorderLayout;
/*   7:    */ import java.awt.Color;
/*   8:    */ import java.awt.Component;
/*   9:    */ import java.awt.FlowLayout;
/*  10:    */ import java.awt.event.ActionEvent;
/*  11:    */ import java.awt.event.ActionListener;
/*  12:    */ import java.awt.event.MouseAdapter;
/*  13:    */ import java.awt.event.MouseEvent;
/*  14:    */ import java.io.Serializable;
/*  15:    */ import java.text.DateFormat;
/*  16:    */ import java.text.SimpleDateFormat;
/*  17:    */ import java.util.Calendar;
/*  18:    */ import javax.swing.AbstractAction;
/*  19:    */ import javax.swing.ActionMap;
/*  20:    */ import javax.swing.InputMap;
/*  21:    */ import javax.swing.JComboBox;
/*  22:    */ import javax.swing.JDialog;
/*  23:    */ import javax.swing.JPanel;
/*  24:    */ import javax.swing.JSpinner;
/*  25:    */ import javax.swing.JSpinner.NumberEditor;
/*  26:    */ import javax.swing.JTable;
/*  27:    */ import javax.swing.JTextField;
/*  28:    */ import javax.swing.KeyStroke;
/*  29:    */ import javax.swing.SpinnerNumberModel;
/*  30:    */ import javax.swing.UIManager;
/*  31:    */ import javax.swing.event.ChangeEvent;
/*  32:    */ import javax.swing.event.ChangeListener;
/*  33:    */ import javax.swing.table.DefaultTableCellRenderer;
/*  34:    */ import javax.swing.table.DefaultTableModel;
/*  35:    */ import javax.swing.table.JTableHeader;
/*  36:    */ import javax.swing.table.TableColumn;
/*  37:    */ import javax.swing.table.TableColumnModel;
/*  38:    */ 
/*  39:    */ public class MyCalendarPanel
/*  40:    */   extends JDialog
/*  41:    */   implements I18NListener
/*  42:    */ {
/*  43:    */   private static final long serialVersionUID = -1329364976709789113L;
/*  44:    */   private JComboBox comboBoxMonth;
/*  45:    */   private JSpinner spinnerYear;
/*  46:    */   private MyJTable table;
/*  47:    */   private MyJTableModel tableModel;
/*  48:    */   private Calendar calendar;
/*  49: 51 */   private String[] days = { "SUN", "MON", "TUR", "WED", "THU", "FRI", "SAT" };
/*  50: 52 */   private String[] months = {
/*  51: 53 */     bd.getString("message.january"), 
/*  52: 54 */     bd.getString("message.february"), 
/*  53: 55 */     bd.getString("message.march"), 
/*  54: 56 */     bd.getString("message.april"), 
/*  55: 57 */     bd.getString("message.may"), 
/*  56: 58 */     bd.getString("message.june"), 
/*  57: 59 */     bd.getString("message.july"), 
/*  58: 60 */     bd.getString("message.august"), 
/*  59: 61 */     bd.getString("message.september"), 
/*  60: 62 */     bd.getString("message.october"), 
/*  61: 63 */     bd.getString("message.november"), 
/*  62: 64 */     bd.getString("message.december") };
/*  63: 65 */   private int year = -1;
/*  64: 66 */   private int month = -1;
/*  65: 67 */   private int date = -1;
/*  66: 69 */   private boolean flag = true;
/*  67:    */   private JTextField textField;
/*  68:    */   
/*  69:    */   public MyCalendarPanel(JTextField textField)
/*  70:    */   {
/*  71: 74 */     setModal(true);
/*  72: 75 */     this.textField = textField;
/*  73: 76 */     this.calendar = Calendar.getInstance();
/*  74: 77 */     initComponent();
/*  75: 78 */     setIconImage(Constants.CONNECTEDIMG);
/*  76: 79 */     setSize(300, 300);
/*  77:    */   }
/*  78:    */   
/*  79:    */   public Calendar getCalendar()
/*  80:    */   {
/*  81: 83 */     return this.calendar;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public String getCalendarByDate()
/*  85:    */   {
/*  86: 87 */     String format = GlobalVariables.globalConfig.getDateFormat();
/*  87: 88 */     DateFormat dfmt = new SimpleDateFormat(format);
/*  88: 89 */     String time = dfmt.format(this.calendar.getTime());
/*  89: 90 */     return time;
/*  90:    */   }
/*  91:    */   
/*  92:    */   private void initComponent()
/*  93:    */   {
/*  94: 94 */     setLayout(new BorderLayout());
/*  95: 95 */     setNorthPanel();
/*  96: 96 */     setCenterPanel();
/*  97: 97 */     eventListenter();
/*  98: 98 */     setData();
/*  99:    */   }
/* 100:    */   
/* 101:    */   private void setData()
/* 102:    */   {
/* 103:102 */     this.year = this.calendar.get(1);
/* 104:103 */     this.month = this.calendar.get(2);
/* 105:104 */     this.date = this.calendar.get(5);
/* 106:    */     
/* 107:106 */     this.comboBoxMonth.setSelectedIndex(this.month);
/* 108:107 */     this.spinnerYear.setValue(new Integer(this.year));
/* 109:    */   }
/* 110:    */   
/* 111:    */   private void eventListenter()
/* 112:    */   {
/* 113:111 */     this.spinnerYear.addChangeListener(new ChangeListener()
/* 114:    */     {
/* 115:    */       public void stateChanged(ChangeEvent e)
/* 116:    */       {
/* 117:114 */         MyCalendarPanel.this.year = Integer.parseInt(String.valueOf(MyCalendarPanel.this.spinnerYear.getValue()));
/* 118:115 */         MyCalendarPanel.this.refreshTable();
/* 119:    */       }
/* 120:119 */     });
/* 121:120 */     this.comboBoxMonth.addActionListener(new ActionListener()
/* 122:    */     {
/* 123:    */       public void actionPerformed(ActionEvent e)
/* 124:    */       {
/* 125:122 */         MyCalendarPanel.this.month = MyCalendarPanel.this.comboBoxMonth.getSelectedIndex();
/* 126:123 */         MyCalendarPanel.this.refreshTable();
/* 127:    */       }
/* 128:127 */     });
/* 129:128 */     this.table.addMouseListener(new MouseAdapter()
/* 130:    */     {
/* 131:    */       public void mouseClicked(MouseEvent e)
/* 132:    */       {
/* 133:131 */         int row = MyCalendarPanel.this.table.rowAtPoint(e.getPoint());
/* 134:132 */         int col = MyCalendarPanel.this.table.columnAtPoint(e.getPoint());
/* 135:133 */         Object val = MyCalendarPanel.this.table.getValueAt(row, col);
/* 136:134 */         if ((val != null) && (row != 0))
/* 137:    */         {
/* 138:135 */           if ((MyCalendarPanel.this.table.getSelectedRow() > -1) && 
/* 139:136 */             (MyCalendarPanel.this.table.getSelectedColumn() > -1))
/* 140:    */           {
/* 141:137 */             MyCalendarPanel.this.date = Integer.parseInt(String.valueOf(
/* 142:138 */               MyCalendarPanel.this.table
/* 143:139 */               .getValueAt(MyCalendarPanel.this.table.getSelectedRow(), MyCalendarPanel.this.table.getSelectedColumn())));
/* 144:140 */             MyCalendarPanel.this.calendar.set(1, MyCalendarPanel.this.year);
/* 145:141 */             MyCalendarPanel.this.calendar.set(2, MyCalendarPanel.this.month);
/* 146:142 */             MyCalendarPanel.this.calendar.set(5, MyCalendarPanel.this.date);
/* 147:    */           }
/* 148:144 */           MyCalendarPanel.this.textField.setText(MyCalendarPanel.this.getCalendarByDate());
/* 149:145 */           MyCalendarPanel.this.textField.requestFocus();
/* 150:146 */           MyCalendarPanel.this.dispose();
/* 151:    */         }
/* 152:    */       }
/* 153:150 */     });
/* 154:151 */     AbstractAction right = new AbstractAction()
/* 155:    */     {
/* 156:    */       private static final long serialVersionUID = -3429888240127305492L;
/* 157:    */       
/* 158:    */       public void actionPerformed(ActionEvent e)
/* 159:    */       {
/* 160:156 */         if (MyCalendarPanel.this.table.getColumnCount() == MyCalendarPanel.this.table.getSelectedColumn() + 1) {
/* 161:157 */           MyCalendarPanel.this.table.changeSelection(MyCalendarPanel.this.table.getSelectedRow() + 1, 0, false, 
/* 162:158 */             false);
/* 163:    */         } else {
/* 164:160 */           MyCalendarPanel.this.table.changeSelection(MyCalendarPanel.this.table.getSelectedRow(), 
/* 165:161 */             MyCalendarPanel.this.table.getSelectedColumn() + 1, false, false);
/* 166:    */         }
/* 167:    */       }
/* 168:165 */     };
/* 169:166 */     AbstractAction left = new AbstractAction()
/* 170:    */     {
/* 171:    */       private static final long serialVersionUID = -2487040789339112435L;
/* 172:    */       
/* 173:    */       public void actionPerformed(ActionEvent e)
/* 174:    */       {
/* 175:171 */         if (MyCalendarPanel.this.table.getSelectedColumn() - 1 < 0) {
/* 176:172 */           MyCalendarPanel.this.table.changeSelection(MyCalendarPanel.this.table.getSelectedRow() - 1, 
/* 177:173 */             MyCalendarPanel.this.table.getColumnCount() - 1, false, false);
/* 178:    */         } else {
/* 179:175 */           MyCalendarPanel.this.table.changeSelection(MyCalendarPanel.this.table.getSelectedRow(), 
/* 180:176 */             MyCalendarPanel.this.table.getSelectedColumn() - 1, false, false);
/* 181:    */         }
/* 182:    */       }
/* 183:181 */     };
/* 184:182 */     InputMap inputMap = this.table
/* 185:183 */       .getInputMap(1);
/* 186:184 */     KeyStroke keyStroke = KeyStroke.getKeyStroke(39, 0);
/* 187:185 */     this.table.getActionMap().put(inputMap.get(keyStroke), right);
/* 188:186 */     keyStroke = KeyStroke.getKeyStroke(37, 0);
/* 189:187 */     this.table.getActionMap().put(inputMap.get(keyStroke), left);
/* 190:    */   }
/* 191:    */   
/* 192:    */   private void refreshTable()
/* 193:    */   {
/* 194:192 */     for (int i = this.tableModel.getRowCount() - 1; i > 0; i--) {
/* 195:193 */       this.tableModel.removeRow(i);
/* 196:    */     }
/* 197:195 */     this.calendar.set(1, this.year);
/* 198:196 */     this.calendar.set(2, this.month);
/* 199:197 */     int maxDate = this.calendar.getActualMaximum(5);
/* 200:198 */     this.calendar.set(5, 1);
/* 201:199 */     int startDay = this.calendar.get(7);
/* 202:201 */     for (int i = 0; i < 6; i++) {
/* 203:202 */       this.tableModel.addRow(new Object[7]);
/* 204:    */     }
/* 205:205 */     int selectRow = -1;
/* 206:206 */     int selectColumn = -1;
/* 207:208 */     for (int i = 0; i < maxDate; i++)
/* 208:    */     {
/* 209:209 */       int m = (startDay - 1) / 7 + 1;
/* 210:210 */       int n = (startDay - 1) % 7;
/* 211:211 */       this.tableModel.setValueAt(String.valueOf(i + 1), m, n);
/* 212:212 */       startDay++;
/* 213:213 */       if ((this.date != -1) && (this.date == i + 1))
/* 214:    */       {
/* 215:214 */         selectRow = m;
/* 216:215 */         selectColumn = n;
/* 217:    */       }
/* 218:    */     }
/* 219:219 */     this.table.changeSelection(selectRow, selectColumn, false, false);
/* 220:    */   }
/* 221:    */   
/* 222:    */   private void setCenterPanel()
/* 223:    */   {
/* 224:224 */     this.table = new MyJTable();
/* 225:225 */     this.tableModel = new MyJTableModel();
/* 226:226 */     this.tableModel.setColumnIdentifiers(this.days);
/* 227:227 */     this.tableModel.addRow(this.days);
/* 228:228 */     this.table.setModel(this.tableModel);
/* 229:229 */     for (int i = 0; i < 7; i++) {
/* 230:230 */       this.table.getColumnModel().getColumn(i).setCellRenderer(
/* 231:231 */         new DefaultTableCellRenderer2());
/* 232:    */     }
/* 233:233 */     add(this.table, "Center");
/* 234:    */   }
/* 235:    */   
/* 236:    */   private void setNorthPanel()
/* 237:    */   {
/* 238:237 */     JPanel panel = new JPanel();
/* 239:238 */     panel.setLayout(new FlowLayout());
/* 240:239 */     this.comboBoxMonth = new JComboBox();
/* 241:240 */     for (int i = 0; i < this.months.length; i++) {
/* 242:241 */       this.comboBoxMonth.addItem(this.months[i]);
/* 243:    */     }
/* 244:243 */     this.comboBoxMonth.setSelectedIndex(-1);
/* 245:244 */     panel.add(this.comboBoxMonth);
/* 246:245 */     RolloverSpinnerListModel numberModel = new RolloverSpinnerListModel(
/* 247:246 */       1980, 1980, 2099, 1);
/* 248:247 */     this.spinnerYear = new JSpinner(numberModel);
/* 249:248 */     JSpinner.NumberEditor numberEditor = new JSpinner.NumberEditor(
/* 250:249 */       this.spinnerYear, "####");
/* 251:250 */     this.spinnerYear.setEditor(numberEditor);
/* 252:251 */     panel.add(this.spinnerYear);
/* 253:    */     
/* 254:253 */     add(panel, "North");
/* 255:    */   }
/* 256:    */   
/* 257:    */   public static void main(String[] args)
/* 258:    */   {
/* 259:258 */     MyCalendarPanel calendarPanel = new MyCalendarPanel(null);
/* 260:    */     
/* 261:260 */     calendarPanel.setSize(300, 300);
/* 262:    */     
/* 263:262 */     calendarPanel.setLocationRelativeTo(null);
/* 264:263 */     calendarPanel.setVisible(true);
/* 265:    */   }
/* 266:    */   
/* 267:    */   protected void validateTree()
/* 268:    */   {
/* 269:267 */     super.validateTree();
/* 270:268 */     if (this.flag)
/* 271:    */     {
/* 272:269 */       this.table.requestFocus();
/* 273:270 */       this.flag = false;
/* 274:    */     }
/* 275:    */   }
/* 276:    */   
/* 277:    */   public void changeLang() {}
/* 278:    */   
/* 279:    */   class DateBean
/* 280:    */     implements Serializable
/* 281:    */   {
/* 282:    */     private static final long serialVersionUID = 3010787830585461136L;
/* 283:    */     private String dt;
/* 284:    */     private String view;
/* 285:    */     
/* 286:    */     public DateBean(String dt, String view)
/* 287:    */     {
/* 288:281 */       this.dt = dt;
/* 289:282 */       this.view = view;
/* 290:    */     }
/* 291:    */     
/* 292:    */     public String getDt()
/* 293:    */     {
/* 294:286 */       return this.dt;
/* 295:    */     }
/* 296:    */     
/* 297:    */     public void setDt(String dt)
/* 298:    */     {
/* 299:290 */       this.dt = dt;
/* 300:    */     }
/* 301:    */     
/* 302:    */     public String getView()
/* 303:    */     {
/* 304:294 */       return this.view;
/* 305:    */     }
/* 306:    */     
/* 307:    */     public void setView(String view)
/* 308:    */     {
/* 309:298 */       this.view = view;
/* 310:    */     }
/* 311:    */     
/* 312:    */     public String toString()
/* 313:    */     {
/* 314:303 */       return this.view;
/* 315:    */     }
/* 316:    */   }
/* 317:    */   
/* 318:    */   class RolloverSpinnerListModel
/* 319:    */     extends SpinnerNumberModel
/* 320:    */   {
/* 321:    */     private static final long serialVersionUID = 7366455425120197392L;
/* 322:    */     
/* 323:    */     public RolloverSpinnerListModel(int value, int minimum, int maximum, int stepSize)
/* 324:    */     {
/* 325:313 */       super(minimum, maximum, stepSize);
/* 326:    */     }
/* 327:    */     
/* 328:    */     public Object getNextValue()
/* 329:    */     {
/* 330:317 */       Object returnValue = super.getNextValue();
/* 331:318 */       if (returnValue == null) {
/* 332:319 */         returnValue = super.getMinimum();
/* 333:    */       }
/* 334:321 */       return returnValue;
/* 335:    */     }
/* 336:    */     
/* 337:    */     public Object getPreviousValue()
/* 338:    */     {
/* 339:325 */       Object returnValue = super.getPreviousValue();
/* 340:326 */       if (returnValue == null) {
/* 341:327 */         returnValue = super.getMaximum();
/* 342:    */       }
/* 343:329 */       return returnValue;
/* 344:    */     }
/* 345:    */   }
/* 346:    */   
/* 347:    */   class MyJTable
/* 348:    */     extends JTable
/* 349:    */   {
/* 350:    */     private static final long serialVersionUID = 2092799354481792367L;
/* 351:    */     
/* 352:    */     public MyJTable()
/* 353:    */     {
/* 354:337 */       getTableHeader().setReorderingAllowed(false);
/* 355:338 */       getTableHeader().setResizingAllowed(false);
/* 356:339 */       setSelectionMode(0);
/* 357:340 */       setCellSelectionEnabled(true);
/* 358:341 */       setGridColor(Color.white);
/* 359:    */     }
/* 360:    */     
/* 361:    */     public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
/* 362:    */     {
/* 363:346 */       if (getValueAt(rowIndex, columnIndex) == null) {
/* 364:347 */         return;
/* 365:    */       }
/* 366:350 */       if (rowIndex == 0) {
/* 367:351 */         return;
/* 368:    */       }
/* 369:354 */       super.changeSelection(rowIndex, columnIndex, toggle, extend);
/* 370:    */     }
/* 371:    */     
/* 372:    */     public void repaint(int x, int y, int width, int height)
/* 373:    */     {
/* 374:358 */       super.repaint(x, y, width, height);
/* 375:360 */       if (height > 0) {
/* 376:361 */         makeTableRowHeight();
/* 377:    */       }
/* 378:    */     }
/* 379:    */     
/* 380:    */     protected void validateTree()
/* 381:    */     {
/* 382:366 */       super.validateTree();
/* 383:    */       
/* 384:368 */       makeTableRowHeight();
/* 385:    */     }
/* 386:    */     
/* 387:    */     private void makeTableRowHeight()
/* 388:    */     {
/* 389:372 */       int rowHeight = (int)(getHeight() / 7 * 0.6D);
/* 390:374 */       if (rowHeight > 0) {
/* 391:375 */         setRowHeight(0, rowHeight);
/* 392:    */       }
/* 393:378 */       rowHeight = (getHeight() - rowHeight) / 6;
/* 394:380 */       if (rowHeight > 0) {
/* 395:381 */         for (int i = 1; i < 8; i++) {
/* 396:382 */           setRowHeight(i, rowHeight);
/* 397:    */         }
/* 398:    */       }
/* 399:    */     }
/* 400:    */   }
/* 401:    */   
/* 402:    */   class MyJTableModel
/* 403:    */     extends DefaultTableModel
/* 404:    */   {
/* 405:    */     private static final long serialVersionUID = 3655520869200784738L;
/* 406:    */     
/* 407:    */     MyJTableModel() {}
/* 408:    */     
/* 409:    */     public boolean isCellEditable(int row, int column)
/* 410:    */     {
/* 411:393 */       return false;
/* 412:    */     }
/* 413:    */   }
/* 414:    */   
/* 415:    */   class DefaultTableCellRenderer2
/* 416:    */     extends DefaultTableCellRenderer
/* 417:    */   {
/* 418:    */     private static final long serialVersionUID = -8779549993366848535L;
/* 419:    */     
/* 420:    */     DefaultTableCellRenderer2() {}
/* 421:    */     
/* 422:    */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/* 423:    */     {
/* 424:403 */       Component component = super.getTableCellRendererComponent(table, 
/* 425:404 */         value, isSelected, hasFocus, row, column);
/* 426:405 */       setHorizontalAlignment(0);
/* 427:407 */       if (row == 0)
/* 428:    */       {
/* 429:408 */         component.setBackground(
/* 430:409 */           UIManager.getColor("Table.focusCellForeground"));
/* 431:410 */         component.setForeground(
/* 432:411 */           UIManager.getColor("Table.focusCellBackground"));
/* 433:412 */         return component;
/* 434:    */       }
/* 435:414 */       if (isSelected)
/* 436:    */       {
/* 437:415 */         component.setBackground(
/* 438:416 */           UIManager.getColor("Table.focusCellForeground"));
/* 439:417 */         component.setForeground(
/* 440:418 */           UIManager.getColor("Table.focusCellBackground"));
/* 441:    */       }
/* 442:    */       else
/* 443:    */       {
/* 444:420 */         component.setBackground(
/* 445:421 */           UIManager.getColor("Table.focusCellBackground"));
/* 446:422 */         component.setForeground(
/* 447:423 */           UIManager.getColor("Table.focusCellForeground"));
/* 448:    */       }
/* 449:426 */       if ((column == 0) || (column == 6)) {
/* 450:427 */         component.setForeground(Color.red);
/* 451:    */       }
/* 452:431 */       return component;
/* 453:    */     }
/* 454:    */   }
/* 455:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.MyCalendarPanel
 * JD-Core Version:    0.7.0.1
 */