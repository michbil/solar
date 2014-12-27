/*    1:     */ package cn.com.voltronic.solar.util;
/*    2:     */ 
/*    3:     */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*    4:     */ import cn.com.voltronic.solar.data.bean.AutoComboBoxItem;
/*    5:     */ import cn.com.voltronic.solar.data.bean.AutoLabelItem;
/*    6:     */ import cn.com.voltronic.solar.data.bean.AutoRadioItem;
/*    7:     */ import cn.com.voltronic.solar.data.bean.AutoSpinnerItem;
/*    8:     */ import cn.com.voltronic.solar.data.bean.RefreshProductInfo;
/*    9:     */ import cn.com.voltronic.solar.data.bean.RefreshRatingInfo;
/*   10:     */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   11:     */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   12:     */ import cn.com.voltronic.solar.view.LoginJDialog;
/*   13:     */ import cn.com.voltronic.solar.view.component.AAButton;
/*   14:     */ import cn.com.voltronic.solar.view.component.AAComboBox;
/*   15:     */ import cn.com.voltronic.solar.view.component.AALabel;
/*   16:     */ import cn.com.voltronic.solar.view.component.AARadioButton;
/*   17:     */ import cn.com.voltronic.solar.view.component.AATextField;
/*   18:     */ import cn.com.voltronic.solar.view.component.ComponentFactory;
/*   19:     */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*   20:     */ import cn.com.voltronic.solar.view.component.I18NListener;
/*   21:     */ import java.awt.BorderLayout;
/*   22:     */ import java.awt.Frame;
/*   23:     */ import java.awt.event.ActionEvent;
/*   24:     */ import java.awt.event.ActionListener;
/*   25:     */ import java.io.PrintStream;
/*   26:     */ import java.lang.reflect.Field;
/*   27:     */ import java.lang.reflect.Method;
/*   28:     */ import java.util.ArrayList;
/*   29:     */ import java.util.List;
/*   30:     */ import javax.swing.GroupLayout;
/*   31:     */ import javax.swing.GroupLayout.Alignment;
/*   32:     */ import javax.swing.GroupLayout.ParallelGroup;
/*   33:     */ import javax.swing.GroupLayout.SequentialGroup;
/*   34:     */ import javax.swing.JPanel;
/*   35:     */ import javax.swing.JSpinner;
/*   36:     */ import javax.swing.JTextField;
/*   37:     */ import javax.swing.event.ChangeEvent;
/*   38:     */ import javax.swing.event.ChangeListener;
/*   39:     */ 
/*   40:     */ public class PageUtils
/*   41:     */ {
/*   42:  46 */   private static JSpinner jSpinnerBulkVoltage = null;
/*   43:  47 */   private static JSpinner jSpinnerFloatVoltage = null;
/*   44:     */   
/*   45:     */   public static String getResultByName(String text, Object object)
/*   46:     */   {
/*   47:  49 */     int index = 0;
/*   48:  50 */     int indexend = 0;
/*   49:  51 */     while (((index = text.indexOf("{", indexend)) >= 0) && (text.length() > 3))
/*   50:     */     {
/*   51:  52 */       indexend = text.indexOf("}", index + 1);
/*   52:  53 */       if (indexend > index)
/*   53:     */       {
/*   54:  54 */         String replace = text.substring(index, indexend + 1);
/*   55:  55 */         String classmtd = replace.substring(1, replace.length() - 1);
/*   56:  56 */         int classindex = classmtd.indexOf(".");
/*   57:  57 */         if (classindex > 0)
/*   58:     */         {
/*   59:  58 */           String classname = classmtd.substring(0, classindex);
/*   60:  59 */           String method = classmtd.substring(classindex + 1);
/*   61:     */           try
/*   62:     */           {
/*   63:  61 */             Field[] field = object.getClass().getDeclaredFields();
/*   64:  62 */             for (Field f : field) {
/*   65:  63 */               if (f.getName().equals(classname))
/*   66:     */               {
/*   67:  64 */                 if (!f.isAccessible()) {
/*   68:  65 */                   f.setAccessible(true);
/*   69:     */                 }
/*   70:  67 */                 Object obj = f.get(object);
/*   71:  68 */                 Method[] methods = obj.getClass().getMethods();
/*   72:  69 */                 for (Method m : methods) {
/*   73:  70 */                   if (m.getName().equals(method))
/*   74:     */                   {
/*   75:  71 */                     Object instead = m.invoke(obj, new Object[0]);
/*   76:  72 */                     if (instead == null) {
/*   77:  73 */                       instead = "";
/*   78:     */                     }
/*   79:  75 */                     text = instead.toString();
/*   80:  76 */                     indexend = 0;
/*   81:  77 */                     break;
/*   82:     */                   }
/*   83:     */                 }
/*   84:  80 */                 break;
/*   85:     */               }
/*   86:     */             }
/*   87:     */           }
/*   88:     */           catch (Exception ex)
/*   89:     */           {
/*   90:  84 */             ex.printStackTrace();
/*   91:     */           }
/*   92:     */         }
/*   93:     */       }
/*   94:     */     }
/*   95:  89 */     return text;
/*   96:     */   }
/*   97:     */   
/*   98:     */   public static void setSpinnerLayout(List<AutoSpinnerItem> list, JPanel panel, Object object)
/*   99:     */   {
/*  100:  94 */     panel.setLayout(new BorderLayout());
/*  101:  95 */     int index = 2;
/*  102:  96 */     if (list.size() % 2 == 0) {
/*  103:  97 */       index = list.size() / 2;
/*  104:     */     } else {
/*  105:  99 */       index = list.size() / 2 + 1;
/*  106:     */     }
/*  107: 101 */     JPanel leftPanel = new JPanel();
/*  108: 102 */     JPanel rightPanel = new JPanel();
/*  109:     */     
/*  110:     */ 
/*  111: 105 */     GroupLayout layout = new GroupLayout(leftPanel);
/*  112: 106 */     leftPanel.setLayout(layout);
/*  113: 107 */     layout.setAutoCreateGaps(true);
/*  114: 108 */     layout.setAutoCreateContainerGaps(true);
/*  115:     */     
/*  116: 110 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/*  117: 111 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/*  118:     */     
/*  119: 113 */     GroupLayout.ParallelGroup p1 = layout
/*  120: 114 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  121: 115 */     GroupLayout.ParallelGroup p2 = layout
/*  122: 116 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  123: 117 */     GroupLayout.ParallelGroup p3 = layout
/*  124: 118 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  125: 119 */     GroupLayout.ParallelGroup p4 = layout
/*  126: 120 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  127: 123 */     for (int i = 0; i < index; i++)
/*  128:     */     {
/*  129: 124 */       AALabel caption1 = new AALabel();
/*  130: 125 */       double minValue1 = VolUtil.parseDouble(getResultByName(((AutoSpinnerItem)list.get(i)).getMinValue(), object));
/*  131: 126 */       double maxValue1 = VolUtil.parseDouble(getResultByName(((AutoSpinnerItem)list.get(i)).getMaxValue(), object));
/*  132: 127 */       double step1 = VolUtil.parseDouble(((AutoSpinnerItem)list.get(i)).getStep());
/*  133: 128 */       double nvalue1 = VolUtil.parseDouble(getResultByName(((AutoSpinnerItem)list.get(i)).getValue(), object));
/*  134: 129 */       AALabel unit1 = new AALabel();
/*  135: 130 */       AAButton button1 = new AAButton();
/*  136: 131 */       button1.setEnabled(((AutoSpinnerItem)list.get(i)).isEnable());
/*  137: 132 */       final JSpinner value1 = ComponentFactory.createNumberSpinner(minValue1, maxValue1, step1, nvalue1);
/*  138:     */       
/*  139:     */ 
/*  140: 135 */       value1.addChangeListener(new ChangeListener()
/*  141:     */       {
/*  142:     */         public void stateChanged(ChangeEvent e)
/*  143:     */         {
/*  144: 138 */           PageUtils.this.setEnabled(true);
/*  145:     */         }
/*  146: 140 */       });
/*  147: 141 */       caption1.setText(((AutoSpinnerItem)list.get(i)).getCaption());
/*  148: 142 */       unit1.setText(((AutoSpinnerItem)list.get(i)).getUnit());
/*  149: 143 */       button1.setText("message.apply");
/*  150: 144 */       button1.setName(((AutoSpinnerItem)list.get(i)).getButtonName());
/*  151: 146 */       if (button1.getName().equals("setMaxChargingVoltage")) {
/*  152: 147 */         jSpinnerBulkVoltage = value1;
/*  153: 148 */       } else if (button1.getName().equals("setFloatingVoltage")) {
/*  154: 149 */         jSpinnerFloatVoltage = value1;
/*  155:     */       }
/*  156: 152 */       button1.addActionListener(new ActionListener()
/*  157:     */       {
/*  158:     */         public void actionPerformed(ActionEvent e)
/*  159:     */         {
/*  160: 155 */           if (!SolarPowerTray.isLogin)
/*  161:     */           {
/*  162: 156 */             new LoginJDialog(new Frame(), true);
/*  163: 157 */             return;
/*  164:     */           }
/*  165: 159 */           AbstractProcessor currentProcessor = GlobalProcessors.getCurrentProcessor();
/*  166: 160 */           if (currentProcessor != null)
/*  167:     */           {
/*  168: 161 */             PageUtils.this.setEnabled(false);
/*  169: 162 */             String name = PageUtils.this.getName();
/*  170: 163 */             boolean result = false;
/*  171: 164 */             String value = value1.getValue().toString();
/*  172: 165 */             System.out.println("name=" + name);
/*  173: 166 */             if (name.equals("MaxChargeCurrent"))
/*  174:     */             {
/*  175: 167 */               result = currentProcessor.executeControl("setMaxChargingCurrent", new Object[] { Integer.valueOf(VolUtil.parseInt(value.substring(0, 2))) });
/*  176:     */             }
/*  177: 168 */             else if (name.equals("PMaxChargeCurrent"))
/*  178:     */             {
/*  179: 169 */               result = currentProcessor.executeControl("setPMaxChargingCurrent", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(VolUtil.parseInt(value.substring(0, 2))) });
/*  180:     */             }
/*  181: 171 */             else if (name.equals("BatteryLowAlarm"))
/*  182:     */             {
/*  183: 172 */               result = currentProcessor.executeControl("setBatteryLow", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  184:     */             }
/*  185: 173 */             else if (name.equals("setBatteryCutoffVoltage"))
/*  186:     */             {
/*  187: 174 */               result = currentProcessor.executeControl("setBatteryUnder", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  188:     */             }
/*  189: 175 */             else if (name.equals("setMaxChargingVoltage"))
/*  190:     */             {
/*  191: 176 */               if ((PageUtils.jSpinnerFloatVoltage != null) && (((Double)PageUtils.jSpinnerFloatVoltage.getValue()).doubleValue() > ((Double)value1.getValue()).doubleValue()))
/*  192:     */               {
/*  193: 177 */                 DisplayMessage.showInfoDialog("Bulk charging voltage can not less than floating charging voltage");
/*  194: 178 */                 return;
/*  195:     */               }
/*  196: 180 */               result = currentProcessor.executeControl(name, new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  197:     */             }
/*  198: 181 */             else if (name.equals("setFloatingVoltage"))
/*  199:     */             {
/*  200: 182 */               if ((PageUtils.jSpinnerBulkVoltage != null) && (((Double)PageUtils.jSpinnerBulkVoltage.getValue()).doubleValue() < ((Double)value1.getValue()).doubleValue()))
/*  201:     */               {
/*  202: 183 */                 DisplayMessage.showInfoDialog("Floating voltage can not larger than bulk charging voltage");
/*  203: 184 */                 return;
/*  204:     */               }
/*  205: 187 */               result = currentProcessor.executeControl(name, new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  206:     */             }
/*  207:     */             else
/*  208:     */             {
/*  209: 189 */               result = currentProcessor.executeControl(name, new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  210:     */             }
/*  211: 192 */             if (result) {
/*  212: 193 */               DisplayMessage.showInfoDialog("message.setTrue");
/*  213:     */             } else {
/*  214: 195 */               DisplayMessage.showErrorDialog("message.setFalse");
/*  215:     */             }
/*  216: 197 */             PageUtils.this.setEnabled(true);
/*  217:     */           }
/*  218:     */         }
/*  219: 201 */       });
/*  220: 202 */       p1.addComponent(caption1);
/*  221: 203 */       p2.addComponent(value1, 60, 60, 60);
/*  222: 204 */       p3.addComponent(unit1);
/*  223: 205 */       p4.addComponent(button1);
/*  224:     */       
/*  225: 207 */       vGroup.addGroup(
/*  226: 208 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption1).addComponent(value1).addComponent(
/*  227: 209 */         unit1).addComponent(button1));
/*  228: 210 */       vGroup.addGap(15, 15, 15);
/*  229:     */     }
/*  230: 213 */     hGroup.addGap(40, 40, 40);
/*  231: 214 */     hGroup.addGroup(p1);
/*  232: 215 */     hGroup.addGroup(p2);
/*  233: 216 */     hGroup.addGroup(p3);
/*  234: 217 */     hGroup.addGroup(p4);
/*  235: 218 */     hGroup.addGap(20, 20, 20);
/*  236:     */     
/*  237: 220 */     layout.setHorizontalGroup(hGroup);
/*  238: 221 */     layout.setVerticalGroup(vGroup);
/*  239:     */     
/*  240:     */ 
/*  241: 224 */     GroupLayout layout2 = new GroupLayout(rightPanel);
/*  242: 225 */     rightPanel.setLayout(layout2);
/*  243: 226 */     layout2.setAutoCreateGaps(true);
/*  244: 227 */     layout2.setAutoCreateContainerGaps(true);
/*  245:     */     
/*  246: 229 */     GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
/*  247: 230 */     GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
/*  248:     */     
/*  249: 232 */     GroupLayout.ParallelGroup p5 = layout2
/*  250: 233 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  251: 234 */     GroupLayout.ParallelGroup p6 = layout2
/*  252: 235 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  253: 236 */     GroupLayout.ParallelGroup p7 = layout2
/*  254: 237 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  255: 238 */     GroupLayout.ParallelGroup p8 = layout2
/*  256: 239 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  257: 242 */     for (int j = index; j < list.size(); j++)
/*  258:     */     {
/*  259: 243 */       AALabel caption2 = new AALabel();
/*  260: 244 */       double minValue2 = VolUtil.parseDouble(getResultByName(((AutoSpinnerItem)list.get(j)).getMinValue(), object));
/*  261: 245 */       double maxValue2 = VolUtil.parseDouble(getResultByName(((AutoSpinnerItem)list.get(j)).getMaxValue(), object));
/*  262: 246 */       double step2 = VolUtil.parseDouble(((AutoSpinnerItem)list.get(j)).getStep());
/*  263: 247 */       double nvalue2 = VolUtil.parseDouble(getResultByName(((AutoSpinnerItem)list.get(j)).getValue(), object));
/*  264: 248 */       AALabel unit2 = new AALabel();
/*  265: 249 */       AAButton button2 = new AAButton();
/*  266: 250 */       button2.setEnabled(((AutoSpinnerItem)list.get(j)).isEnable());
/*  267: 251 */       final JSpinner value2 = ComponentFactory.createNumberSpinner(minValue2, maxValue2, step2, nvalue2);
/*  268: 252 */       value2.addChangeListener(new ChangeListener()
/*  269:     */       {
/*  270:     */         public void stateChanged(ChangeEvent e)
/*  271:     */         {
/*  272: 255 */           PageUtils.this.setEnabled(true);
/*  273:     */         }
/*  274: 257 */       });
/*  275: 258 */       caption2.setText(((AutoSpinnerItem)list.get(j)).getCaption());
/*  276: 259 */       unit2.setText(((AutoSpinnerItem)list.get(j)).getUnit());
/*  277: 260 */       button2.setText("message.apply");
/*  278: 261 */       button2.setName(((AutoSpinnerItem)list.get(j)).getButtonName());
/*  279: 263 */       if (button2.getName().equals("setMaxChargingVoltage")) {
/*  280: 264 */         jSpinnerBulkVoltage = value2;
/*  281: 265 */       } else if (button2.getName().equals("setFloatingVoltage")) {
/*  282: 266 */         jSpinnerFloatVoltage = value2;
/*  283:     */       }
/*  284: 269 */       button2.addActionListener(new ActionListener()
/*  285:     */       {
/*  286:     */         public void actionPerformed(ActionEvent e)
/*  287:     */         {
/*  288: 272 */           if (!SolarPowerTray.isLogin)
/*  289:     */           {
/*  290: 273 */             new LoginJDialog(new Frame(), true);
/*  291: 274 */             return;
/*  292:     */           }
/*  293: 276 */           AbstractProcessor currentProcessor = GlobalProcessors.getCurrentProcessor();
/*  294: 277 */           if (currentProcessor != null)
/*  295:     */           {
/*  296: 278 */             PageUtils.this.setEnabled(false);
/*  297: 279 */             String name = PageUtils.this.getName();
/*  298:     */             
/*  299: 281 */             boolean result = false;
/*  300: 282 */             String value = value2.getValue().toString();
/*  301: 283 */             if (name.equals("MaxChargeCurrent"))
/*  302:     */             {
/*  303: 284 */               result = currentProcessor.executeControl("setMaxChargingCurrent", new Object[] { Integer.valueOf(VolUtil.parseInt(value.substring(0, 2))) });
/*  304:     */             }
/*  305: 286 */             else if (name.equals("PMaxChargeCurrent"))
/*  306:     */             {
/*  307: 287 */               result = currentProcessor.executeControl("setPMaxChargingCurrent", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(VolUtil.parseInt(value.substring(0, 2))) });
/*  308:     */             }
/*  309: 289 */             else if (name.equals("BatteryLowAlarm"))
/*  310:     */             {
/*  311: 290 */               result = currentProcessor.executeControl("setBatteryLow", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  312:     */             }
/*  313: 291 */             else if (name.equals("setBatteryCutoffVoltage"))
/*  314:     */             {
/*  315: 292 */               result = currentProcessor.executeControl("setBatteryUnder", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  316:     */             }
/*  317: 293 */             else if (name.equals("setMaxChargingVoltage"))
/*  318:     */             {
/*  319: 294 */               if ((PageUtils.jSpinnerFloatVoltage != null) && (((Double)PageUtils.jSpinnerFloatVoltage.getValue()).doubleValue() > ((Double)value2.getValue()).doubleValue()))
/*  320:     */               {
/*  321: 295 */                 DisplayMessage.showInfoDialog("Bulk charging voltage can not less than floating charging voltage");
/*  322: 296 */                 return;
/*  323:     */               }
/*  324: 298 */               result = currentProcessor.executeControl(name, new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  325:     */             }
/*  326: 299 */             else if (name.equals("setFloatingVoltage"))
/*  327:     */             {
/*  328: 300 */               if ((PageUtils.jSpinnerBulkVoltage != null) && (((Double)PageUtils.jSpinnerBulkVoltage.getValue()).doubleValue() < ((Double)value2.getValue()).doubleValue()))
/*  329:     */               {
/*  330: 301 */                 DisplayMessage.showInfoDialog("Floating voltage can not larger than bulk charging voltage");
/*  331: 302 */                 return;
/*  332:     */               }
/*  333: 305 */               result = currentProcessor.executeControl(name, new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  334:     */             }
/*  335:     */             else
/*  336:     */             {
/*  337: 308 */               result = currentProcessor.executeControl(name, new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  338:     */             }
/*  339: 310 */             if (result) {
/*  340: 311 */               DisplayMessage.showInfoDialog("message.setTrue");
/*  341:     */             } else {
/*  342: 313 */               DisplayMessage.showErrorDialog("message.setFalse");
/*  343:     */             }
/*  344: 315 */             PageUtils.this.setEnabled(true);
/*  345:     */           }
/*  346:     */         }
/*  347: 319 */       });
/*  348: 320 */       p5.addComponent(caption2);
/*  349: 321 */       p6.addComponent(value2, 60, 60, 60);
/*  350: 322 */       p7.addComponent(unit2);
/*  351: 323 */       p8.addComponent(button2);
/*  352:     */       
/*  353: 325 */       vGroup2.addGroup(
/*  354: 326 */         layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption2).addComponent(value2).addComponent(
/*  355: 327 */         unit2).addComponent(button2));
/*  356: 328 */       vGroup2.addGap(15, 15, 15);
/*  357:     */     }
/*  358: 330 */     hGroup2.addGap(20, 20, 20);
/*  359: 331 */     hGroup2.addGroup(p5);
/*  360: 332 */     hGroup2.addGroup(p6);
/*  361: 333 */     hGroup2.addGroup(p7);
/*  362: 334 */     hGroup2.addGroup(p8);
/*  363: 335 */     hGroup2.addGap(40, 40, 40);
/*  364:     */     
/*  365: 337 */     layout2.setHorizontalGroup(hGroup2);
/*  366: 338 */     layout2.setVerticalGroup(vGroup2);
/*  367: 339 */     panel.add(leftPanel, "Center");
/*  368: 340 */     panel.add(rightPanel, "East");
/*  369:     */   }
/*  370:     */   
/*  371:     */   public static void setComboBoxLayout(List<AutoComboBoxItem> list, JPanel panel, Object object)
/*  372:     */   {
/*  373: 345 */     panel.setLayout(new BorderLayout());
/*  374: 346 */     int index = 2;
/*  375: 347 */     if (list.size() % 2 == 0) {
/*  376: 348 */       index = list.size() / 2;
/*  377:     */     } else {
/*  378: 350 */       index = list.size() / 2 + 1;
/*  379:     */     }
/*  380: 352 */     JPanel leftPanel = new JPanel();
/*  381: 353 */     JPanel rightPanel = new JPanel();
/*  382:     */     
/*  383:     */ 
/*  384: 356 */     GroupLayout layout = new GroupLayout(leftPanel);
/*  385: 357 */     leftPanel.setLayout(layout);
/*  386: 358 */     layout.setAutoCreateGaps(true);
/*  387: 359 */     layout.setAutoCreateContainerGaps(true);
/*  388:     */     
/*  389: 361 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/*  390: 362 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/*  391:     */     
/*  392: 364 */     GroupLayout.ParallelGroup p1 = layout
/*  393: 365 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  394: 366 */     GroupLayout.ParallelGroup p2 = layout
/*  395: 367 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  396: 368 */     GroupLayout.ParallelGroup p3 = layout
/*  397: 369 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  398: 370 */     GroupLayout.ParallelGroup p4 = layout
/*  399: 371 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  400: 374 */     for (int i = 0; i < index; i++)
/*  401:     */     {
/*  402: 375 */       AALabel caption1 = new AALabel();
/*  403: 376 */       String[] selects1 = ((AutoComboBoxItem)list.get(i)).getSelects();
/*  404: 377 */       String nvalue1 = getResultByName(((AutoComboBoxItem)list.get(i)).getValue(), object);
/*  405: 378 */       AALabel unit1 = new AALabel();
/*  406: 379 */       AAButton button1 = new AAButton();
/*  407: 380 */       button1.setEnabled(((AutoComboBoxItem)list.get(i)).isEnable());
/*  408: 381 */       final AAComboBox value1 = ComponentFactory.createComboBox(selects1, nvalue1);
/*  409: 383 */       if (((AutoComboBoxItem)list.get(i)).getButtonName().equalsIgnoreCase("OutputMode")) {
/*  410: 384 */         value1.setSelectedIndex(Integer.parseInt(nvalue1));
/*  411:     */       }
/*  412: 387 */       value1.addActionListener(new ActionListener()
/*  413:     */       {
/*  414:     */         public void actionPerformed(ActionEvent e)
/*  415:     */         {
/*  416: 390 */           PageUtils.this.setEnabled(true);
/*  417:     */         }
/*  418: 392 */       });
/*  419: 393 */       caption1.setText(((AutoComboBoxItem)list.get(i)).getCaption());
/*  420: 394 */       unit1.setText(((AutoComboBoxItem)list.get(i)).getUnit());
/*  421: 395 */       button1.setText("message.apply");
/*  422: 396 */       button1.setName(((AutoComboBoxItem)list.get(i)).getButtonName());
/*  423: 397 */       button1.addActionListener(new ActionListener()
/*  424:     */       {
/*  425:     */         public void actionPerformed(ActionEvent e)
/*  426:     */         {
/*  427: 400 */           if (!SolarPowerTray.isLogin)
/*  428:     */           {
/*  429: 401 */             new LoginJDialog(new Frame(), true);
/*  430: 402 */             return;
/*  431:     */           }
/*  432: 404 */           AbstractProcessor currentProcessor = GlobalProcessors.getCurrentProcessor();
/*  433: 405 */           if (currentProcessor != null)
/*  434:     */           {
/*  435: 406 */             PageUtils.this.setEnabled(false);
/*  436: 407 */             String name = PageUtils.this.getName();
/*  437: 408 */             boolean result = false;
/*  438: 409 */             String value = value1.getSelectedItem().toString();
/*  439: 410 */             if (name.equals("ChargerSource"))
/*  440:     */             {
/*  441: 411 */               if (value.equals("Utility")) {
/*  442: 412 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "00" });
/*  443: 413 */               } else if (value.equals("Solar first")) {
/*  444: 414 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "01" });
/*  445: 415 */               } else if (value.equals("Utility and Solar")) {
/*  446: 416 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "02" });
/*  447: 417 */               } else if (value.equals("Solar only")) {
/*  448: 418 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "03" });
/*  449:     */               }
/*  450:     */             }
/*  451: 420 */             else if (name.equals("PChargerSource"))
/*  452:     */             {
/*  453: 421 */               if (value.equals("Utility")) {
/*  454: 422 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "00" });
/*  455: 423 */               } else if (value.equals("Solar first")) {
/*  456: 424 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "01" });
/*  457: 425 */               } else if (value.equals("Utility and Solar")) {
/*  458: 426 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "02" });
/*  459: 427 */               } else if (value.equals("Solar only")) {
/*  460: 428 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "03" });
/*  461:     */               }
/*  462:     */             }
/*  463: 431 */             else if (name.equals("BatteryType"))
/*  464:     */             {
/*  465: 432 */               if (value.equals("AGM")) {
/*  466: 433 */                 result = currentProcessor.executeControl("setBatteryType", new Object[] { "00" });
/*  467: 434 */               } else if (value.equals("Flooded")) {
/*  468: 435 */                 result = currentProcessor.executeControl("setBatteryType", new Object[] { "01" });
/*  469: 436 */               } else if (value.equals("User")) {
/*  470: 437 */                 result = currentProcessor.executeControl("setBatteryType", new Object[] { "02" });
/*  471:     */               }
/*  472:     */             }
/*  473: 439 */             else if (name.equals("OutputSource"))
/*  474:     */             {
/*  475: 440 */               if (value.equals("Utility")) {
/*  476: 441 */                 result = currentProcessor.executeControl("setOutputSource", new Object[] { "00" });
/*  477: 442 */               } else if (value.equals("Solar")) {
/*  478: 443 */                 result = currentProcessor.executeControl("setOutputSource", new Object[] { "01" });
/*  479: 444 */               } else if (value.equals("SBU")) {
/*  480: 446 */                 result = currentProcessor.executeControl("setOutputSource", new Object[] { "02" });
/*  481:     */               }
/*  482:     */             }
/*  483: 448 */             else if (name.equals("ACInputRange"))
/*  484:     */             {
/*  485: 449 */               if (value.equals("Appliance")) {
/*  486: 450 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "00" });
/*  487: 451 */               } else if (value.equals("UPS")) {
/*  488: 452 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "01" });
/*  489: 453 */               } else if (value.equals("Parallel 1")) {
/*  490: 454 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "02" });
/*  491: 455 */               } else if (value.equals("Parallel 2")) {
/*  492: 456 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "03" });
/*  493:     */               }
/*  494:     */             }
/*  495: 458 */             else if (name.equals("OutputFrequency")) {
/*  496: 459 */               result = currentProcessor.executeControl("setOutputFrequency", new Object[] { Integer.valueOf(VolUtil.parseInt(value)) });
/*  497: 460 */             } else if (name.equals("OutputVoltage")) {
/*  498: 461 */               result = currentProcessor.executeControl("setOutputVoltage", new Object[] { Integer.valueOf(VolUtil.parseInt(value)) });
/*  499: 462 */             } else if (name.equals("MaxChargeVoltage")) {
/*  500: 463 */               result = currentProcessor.executeControl("setMaxChargingVoltage", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  501: 464 */             } else if (name.equalsIgnoreCase("ReChargeVoltage")) {
/*  502: 466 */               result = currentProcessor.executeControl("setRechargingVol", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  503: 467 */             } else if (name.equalsIgnoreCase("ReDisChargeVoltage")) {
/*  504: 468 */               result = currentProcessor.executeControl("setReDisChargeVoltage", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  505: 469 */             } else if (name.equalsIgnoreCase("OutputMode")) {
/*  506: 470 */               result = currentProcessor.executeControl("setOutputMode", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(value1.getSelectedIndex()) });
/*  507: 471 */             } else if (name.equals("PMaxChargeCurrent")) {
/*  508: 472 */               result = currentProcessor.executeControl("setPMaxChargingCurrent", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(VolUtil.parseInt(value)) });
/*  509: 473 */             } else if (name.equals("PMaxACChargeCurrent")) {
/*  510: 474 */               result = currentProcessor.executeControl("setPMaxACChargeCurrent", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(VolUtil.parseInt(value)) });
/*  511:     */             }
/*  512: 476 */             if (result) {
/*  513: 477 */               DisplayMessage.showInfoDialog("message.setTrue");
/*  514:     */             } else {
/*  515: 479 */               DisplayMessage.showErrorDialog("message.setFalse");
/*  516:     */             }
/*  517: 481 */             PageUtils.this.setEnabled(true);
/*  518:     */           }
/*  519:     */         }
/*  520: 485 */       });
/*  521: 486 */       p1.addComponent(caption1);
/*  522: 487 */       p2.addComponent(value1, 100, 200, 200);
/*  523: 488 */       p3.addComponent(unit1);
/*  524: 489 */       p4.addComponent(button1);
/*  525:     */       
/*  526: 491 */       vGroup.addGroup(
/*  527: 492 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption1).addComponent(value1).addComponent(
/*  528: 493 */         unit1).addComponent(button1));
/*  529: 494 */       vGroup.addGap(15, 15, 15);
/*  530:     */     }
/*  531: 497 */     hGroup.addGap(40, 40, 40);
/*  532: 498 */     hGroup.addGroup(p1);
/*  533: 499 */     hGroup.addGroup(p2);
/*  534: 500 */     hGroup.addGroup(p3);
/*  535: 501 */     hGroup.addGroup(p4);
/*  536: 502 */     hGroup.addGap(20, 20, 20);
/*  537:     */     
/*  538: 504 */     layout.setHorizontalGroup(hGroup);
/*  539: 505 */     layout.setVerticalGroup(vGroup);
/*  540:     */     
/*  541:     */ 
/*  542: 508 */     GroupLayout layout2 = new GroupLayout(rightPanel);
/*  543: 509 */     rightPanel.setLayout(layout2);
/*  544: 510 */     layout2.setAutoCreateGaps(true);
/*  545: 511 */     layout2.setAutoCreateContainerGaps(true);
/*  546:     */     
/*  547: 513 */     GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
/*  548: 514 */     GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
/*  549:     */     
/*  550: 516 */     GroupLayout.ParallelGroup p5 = layout2
/*  551: 517 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  552: 518 */     GroupLayout.ParallelGroup p6 = layout2
/*  553: 519 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  554: 520 */     GroupLayout.ParallelGroup p7 = layout2
/*  555: 521 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  556: 522 */     GroupLayout.ParallelGroup p8 = layout2
/*  557: 523 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  558: 526 */     for (int j = index; j < list.size(); j++)
/*  559:     */     {
/*  560: 527 */       AALabel caption2 = new AALabel();
/*  561: 528 */       String[] selects2 = ((AutoComboBoxItem)list.get(j)).getSelects();
/*  562: 529 */       String nvalue2 = getResultByName(((AutoComboBoxItem)list.get(j)).getValue(), object);
/*  563: 530 */       AALabel unit2 = new AALabel();
/*  564: 531 */       AAButton button2 = new AAButton();
/*  565: 532 */       button2.setEnabled(((AutoComboBoxItem)list.get(j)).isEnable());
/*  566: 533 */       final AAComboBox value2 = ComponentFactory.createComboBox(selects2, nvalue2);
/*  567: 535 */       if (((AutoComboBoxItem)list.get(j)).getButtonName().equalsIgnoreCase("OutputMode")) {
/*  568: 536 */         value2.setSelectedIndex(Integer.parseInt(nvalue2));
/*  569:     */       }
/*  570: 539 */       value2.addActionListener(new ActionListener()
/*  571:     */       {
/*  572:     */         public void actionPerformed(ActionEvent e)
/*  573:     */         {
/*  574: 542 */           PageUtils.this.setEnabled(true);
/*  575:     */         }
/*  576: 544 */       });
/*  577: 545 */       caption2.setText(((AutoComboBoxItem)list.get(j)).getCaption());
/*  578: 546 */       unit2.setText(((AutoComboBoxItem)list.get(j)).getUnit());
/*  579: 547 */       button2.setText("message.apply");
/*  580: 548 */       button2.setName(((AutoComboBoxItem)list.get(j)).getButtonName());
/*  581: 549 */       button2.addActionListener(new ActionListener()
/*  582:     */       {
/*  583:     */         public void actionPerformed(ActionEvent e)
/*  584:     */         {
/*  585: 552 */           if (!SolarPowerTray.isLogin)
/*  586:     */           {
/*  587: 553 */             new LoginJDialog(new Frame(), true);
/*  588: 554 */             return;
/*  589:     */           }
/*  590: 556 */           AbstractProcessor currentProcessor = GlobalProcessors.getCurrentProcessor();
/*  591: 557 */           if (currentProcessor != null)
/*  592:     */           {
/*  593: 558 */             PageUtils.this.setEnabled(false);
/*  594: 559 */             String name = PageUtils.this.getName();
/*  595: 560 */             boolean result = false;
/*  596: 561 */             String value = value2.getSelectedItem().toString();
/*  597: 562 */             if (name.equals("ChargerSource"))
/*  598:     */             {
/*  599: 563 */               if (value.equals("Utility")) {
/*  600: 564 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "00" });
/*  601: 565 */               } else if (value.equals("Solar first")) {
/*  602: 566 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "01" });
/*  603: 567 */               } else if (value.equals("Utility and Solar")) {
/*  604: 568 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "02" });
/*  605: 569 */               } else if (value.equals("Solar only")) {
/*  606: 570 */                 result = currentProcessor.executeControl("setChargerSource", new Object[] { "03" });
/*  607:     */               }
/*  608:     */             }
/*  609: 572 */             else if (name.equals("PChargerSource"))
/*  610:     */             {
/*  611: 573 */               if (value.equals("Utility")) {
/*  612: 574 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "00" });
/*  613: 575 */               } else if (value.equals("Solar first")) {
/*  614: 576 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "01" });
/*  615: 577 */               } else if (value.equals("Utility and Solar")) {
/*  616: 578 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "02" });
/*  617: 579 */               } else if (value.equals("Solar only")) {
/*  618: 580 */                 result = currentProcessor.executeControl("setPChargerSource", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), "03" });
/*  619:     */               }
/*  620:     */             }
/*  621: 583 */             else if (name.equals("BatteryType"))
/*  622:     */             {
/*  623: 584 */               if (value.equals("AGM")) {
/*  624: 585 */                 result = currentProcessor.executeControl("setBatteryType", new Object[] { "00" });
/*  625: 586 */               } else if (value.equals("Flooded")) {
/*  626: 587 */                 result = currentProcessor.executeControl("setBatteryType", new Object[] { "01" });
/*  627: 588 */               } else if (value.equals("User")) {
/*  628: 589 */                 result = currentProcessor.executeControl("setBatteryType", new Object[] { "02" });
/*  629:     */               }
/*  630:     */             }
/*  631: 591 */             else if (name.equals("OutputSource"))
/*  632:     */             {
/*  633: 592 */               if (value.equals("Utility")) {
/*  634: 593 */                 result = currentProcessor.executeControl("setOutputSource", new Object[] { "00" });
/*  635: 594 */               } else if (value.equals("Solar")) {
/*  636: 595 */                 result = currentProcessor.executeControl("setOutputSource", new Object[] { "01" });
/*  637: 596 */               } else if (value.equals("SBU")) {
/*  638: 598 */                 result = currentProcessor.executeControl("setOutputSource", new Object[] { "02" });
/*  639:     */               }
/*  640:     */             }
/*  641: 600 */             else if (name.equals("ACInputRange"))
/*  642:     */             {
/*  643: 601 */               if (value.equals("Appliance")) {
/*  644: 602 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "00" });
/*  645: 603 */               } else if (value.equals("UPS")) {
/*  646: 604 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "01" });
/*  647: 605 */               } else if (value.equals("Parallel 1")) {
/*  648: 606 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "02" });
/*  649: 607 */               } else if (value.equals("Parallel 2")) {
/*  650: 608 */                 result = currentProcessor.executeControl("setGridWorkRange", new Object[] { "03" });
/*  651:     */               }
/*  652:     */             }
/*  653: 611 */             else if (name.equals("OutputFrequency")) {
/*  654: 612 */               result = currentProcessor.executeControl("setOutputFrequency", new Object[] { Integer.valueOf(VolUtil.parseInt(value)) });
/*  655: 613 */             } else if (name.equals("OutputVoltage")) {
/*  656: 614 */               result = currentProcessor.executeControl("setOutputVoltage", new Object[] { Integer.valueOf(VolUtil.parseInt(value)) });
/*  657: 615 */             } else if (name.equals("MaxChargeVoltage")) {
/*  658: 616 */               result = currentProcessor.executeControl("setMaxChargingVoltage", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  659: 617 */             } else if (name.equals("ReChargeVoltage")) {
/*  660: 618 */               result = currentProcessor.executeControl("setRechargingVol", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  661: 619 */             } else if (name.equalsIgnoreCase("ReDisChargeVoltage")) {
/*  662: 620 */               result = currentProcessor.executeControl("setReDisChargeVoltage", new Object[] { Double.valueOf(VolUtil.round(VolUtil.parseDouble(value), 1)) });
/*  663: 621 */             } else if (name.equals("OutputMode")) {
/*  664: 622 */               result = currentProcessor.executeControl("setOutputMode", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(value2.getSelectedIndex()) });
/*  665: 623 */             } else if (name.equals("PMaxChargeCurrent")) {
/*  666: 624 */               result = currentProcessor.executeControl("setPMaxChargingCurrent", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(VolUtil.parseInt(value)) });
/*  667: 625 */             } else if (name.equals("PMaxACChargeCurrent")) {
/*  668: 626 */               result = currentProcessor.executeControl("setPMaxACChargeCurrent", new Object[] { Integer.valueOf(currentProcessor.getParallKey()), Integer.valueOf(VolUtil.parseInt(value)) });
/*  669:     */             }
/*  670: 628 */             if (result) {
/*  671: 629 */               DisplayMessage.showInfoDialog("message.setTrue");
/*  672:     */             } else {
/*  673: 631 */               DisplayMessage.showErrorDialog("message.setFalse");
/*  674:     */             }
/*  675: 633 */             PageUtils.this.setEnabled(true);
/*  676:     */           }
/*  677:     */         }
/*  678: 637 */       });
/*  679: 638 */       p5.addComponent(caption2);
/*  680: 639 */       p6.addComponent(value2, 100, 200, 200);
/*  681: 640 */       p7.addComponent(unit2);
/*  682: 641 */       p8.addComponent(button2);
/*  683:     */       
/*  684: 643 */       vGroup2.addGroup(
/*  685: 644 */         layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption2).addComponent(value2).addComponent(
/*  686: 645 */         unit2).addComponent(button2));
/*  687: 646 */       vGroup2.addGap(15, 15, 15);
/*  688:     */     }
/*  689: 648 */     hGroup2.addGap(20, 20, 20);
/*  690: 649 */     hGroup2.addGroup(p5);
/*  691: 650 */     hGroup2.addGroup(p6);
/*  692: 651 */     hGroup2.addGroup(p7);
/*  693: 652 */     hGroup2.addGroup(p8);
/*  694: 653 */     hGroup2.addGap(40, 40, 40);
/*  695:     */     
/*  696: 655 */     layout2.setHorizontalGroup(hGroup2);
/*  697: 656 */     layout2.setVerticalGroup(vGroup2);
/*  698: 657 */     panel.add(leftPanel, "Center");
/*  699: 658 */     panel.add(rightPanel, "East");
/*  700:     */   }
/*  701:     */   
/*  702:     */   public static void setRadioLayout(List<AutoRadioItem> list, JPanel panel, Object object)
/*  703:     */   {
/*  704: 663 */     panel.setLayout(new BorderLayout());
/*  705: 664 */     int index = 2;
/*  706: 665 */     if (list.size() % 2 == 0) {
/*  707: 666 */       index = list.size() / 2;
/*  708:     */     } else {
/*  709: 668 */       index = list.size() / 2 + 1;
/*  710:     */     }
/*  711: 670 */     JPanel leftPanel = new JPanel();
/*  712: 671 */     JPanel rightPanel = new JPanel();
/*  713:     */     
/*  714:     */ 
/*  715: 674 */     GroupLayout layout = new GroupLayout(leftPanel);
/*  716: 675 */     leftPanel.setLayout(layout);
/*  717: 676 */     layout.setAutoCreateGaps(true);
/*  718: 677 */     layout.setAutoCreateContainerGaps(true);
/*  719:     */     
/*  720: 679 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/*  721: 680 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/*  722:     */     
/*  723: 682 */     GroupLayout.ParallelGroup p1 = layout
/*  724: 683 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  725: 684 */     GroupLayout.ParallelGroup p2 = layout
/*  726: 685 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  727: 686 */     GroupLayout.ParallelGroup p3 = layout
/*  728: 687 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  729: 688 */     GroupLayout.ParallelGroup p4 = layout
/*  730: 689 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  731: 692 */     for (int i = 0; i < index; i++)
/*  732:     */     {
/*  733: 693 */       AALabel caption1 = new AALabel();
/*  734: 694 */       final AARadioButton valueLabel1 = new AARadioButton();
/*  735: 695 */       final AARadioButton valueLabel2 = new AARadioButton();
/*  736: 696 */       final AAButton button1 = new AAButton();
/*  737: 697 */       button1.setEnabled(((AutoRadioItem)list.get(i)).isEnable());
/*  738: 698 */       caption1.setText(((AutoRadioItem)list.get(i)).getCaption());
/*  739: 699 */       valueLabel1.setText(((AutoRadioItem)list.get(i)).getRadioLabel1());
/*  740: 700 */       valueLabel2.setText(((AutoRadioItem)list.get(i)).getRadioLabel2());
/*  741: 701 */       boolean value1 = VolUtil.parseBoolean(getResultByName(((AutoRadioItem)list.get(i)).getValue(), 
/*  742: 702 */         object));
/*  743: 703 */       if (value1)
/*  744:     */       {
/*  745: 704 */         valueLabel1.setSelected(true);
/*  746: 705 */         valueLabel2.setSelected(false);
/*  747:     */       }
/*  748:     */       else
/*  749:     */       {
/*  750: 707 */         valueLabel1.setSelected(false);
/*  751: 708 */         valueLabel2.setSelected(true);
/*  752:     */       }
/*  753: 710 */       valueLabel1.addActionListener(new ActionListener()
/*  754:     */       {
/*  755:     */         public void actionPerformed(ActionEvent e)
/*  756:     */         {
/*  757: 713 */           PageUtils.this.setSelected(true);
/*  758: 714 */           valueLabel2.setSelected(false);
/*  759: 715 */           button1.setEnabled(true);
/*  760:     */         }
/*  761: 717 */       });
/*  762: 718 */       valueLabel2.addActionListener(new ActionListener()
/*  763:     */       {
/*  764:     */         public void actionPerformed(ActionEvent e)
/*  765:     */         {
/*  766: 721 */           PageUtils.this.setSelected(false);
/*  767: 722 */           valueLabel2.setSelected(true);
/*  768: 723 */           button1.setEnabled(true);
/*  769:     */         }
/*  770: 725 */       });
/*  771: 726 */       button1.setText("message.apply");
/*  772: 727 */       button1.setName(((AutoRadioItem)list.get(i)).getButtonName());
/*  773: 728 */       button1.addActionListener(new ActionListener()
/*  774:     */       {
/*  775:     */         public void actionPerformed(ActionEvent e)
/*  776:     */         {
/*  777: 731 */           if (!SolarPowerTray.isLogin)
/*  778:     */           {
/*  779: 732 */             new LoginJDialog(new Frame(), true);
/*  780: 733 */             return;
/*  781:     */           }
/*  782: 735 */           AbstractProcessor currentProcessor = GlobalProcessors.getCurrentProcessor();
/*  783: 736 */           if (currentProcessor != null)
/*  784:     */           {
/*  785: 737 */             PageUtils.this.setEnabled(false);
/*  786: 738 */             String name = PageUtils.this.getName();
/*  787: 739 */             boolean result = false;
/*  788: 740 */             if (name.equals("CapableA"))
/*  789:     */             {
/*  790: 741 */               if (valueLabel1.isSelected()) {
/*  791: 742 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "a", Boolean.valueOf(true) });
/*  792: 743 */               } else if (valueLabel2.isSelected()) {
/*  793: 744 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "a", Boolean.valueOf(false) });
/*  794:     */               }
/*  795:     */             }
/*  796: 746 */             else if (name.equals("CapableB"))
/*  797:     */             {
/*  798: 747 */               if (valueLabel1.isSelected()) {
/*  799: 748 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "b", Boolean.valueOf(true) });
/*  800: 749 */               } else if (valueLabel2.isSelected()) {
/*  801: 750 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "b", Boolean.valueOf(false) });
/*  802:     */               }
/*  803:     */             }
/*  804: 752 */             else if (name.equals("CapableK"))
/*  805:     */             {
/*  806: 753 */               if (valueLabel1.isSelected()) {
/*  807: 754 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "k", Boolean.valueOf(true) });
/*  808: 755 */               } else if (valueLabel2.isSelected()) {
/*  809: 756 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "k", Boolean.valueOf(false) });
/*  810:     */               }
/*  811:     */             }
/*  812: 759 */             else if (name.equals("CapableP"))
/*  813:     */             {
/*  814: 760 */               if (valueLabel1.isSelected()) {
/*  815: 761 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "p", Boolean.valueOf(true) });
/*  816: 762 */               } else if (valueLabel2.isSelected()) {
/*  817: 763 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "p", Boolean.valueOf(false) });
/*  818:     */               }
/*  819:     */             }
/*  820: 765 */             else if (name.equals("CapableG"))
/*  821:     */             {
/*  822: 766 */               if (valueLabel1.isSelected()) {
/*  823: 767 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "g", Boolean.valueOf(true) });
/*  824: 768 */               } else if (valueLabel2.isSelected()) {
/*  825: 769 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "g", Boolean.valueOf(false) });
/*  826:     */               }
/*  827:     */             }
/*  828: 771 */             else if (name.equals("CapableJ"))
/*  829:     */             {
/*  830: 772 */               if (valueLabel1.isSelected()) {
/*  831: 773 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "j", Boolean.valueOf(true) });
/*  832: 774 */               } else if (valueLabel2.isSelected()) {
/*  833: 775 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "j", Boolean.valueOf(false) });
/*  834:     */               }
/*  835:     */             }
/*  836: 777 */             else if (name.equals("CapableU"))
/*  837:     */             {
/*  838: 778 */               if (valueLabel1.isSelected()) {
/*  839: 779 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "u", Boolean.valueOf(true) });
/*  840: 780 */               } else if (valueLabel2.isSelected()) {
/*  841: 781 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "u", Boolean.valueOf(false) });
/*  842:     */               }
/*  843:     */             }
/*  844: 783 */             else if (name.equals("CapableV"))
/*  845:     */             {
/*  846: 784 */               if (valueLabel1.isSelected()) {
/*  847: 785 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "v", Boolean.valueOf(true) });
/*  848: 786 */               } else if (valueLabel2.isSelected()) {
/*  849: 787 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "v", Boolean.valueOf(false) });
/*  850:     */               }
/*  851:     */             }
/*  852: 789 */             else if (name.equals("CapableW"))
/*  853:     */             {
/*  854: 790 */               if (valueLabel1.isSelected()) {
/*  855: 791 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "w", Boolean.valueOf(true) });
/*  856: 792 */               } else if (valueLabel2.isSelected()) {
/*  857: 793 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "w", Boolean.valueOf(false) });
/*  858:     */               }
/*  859:     */             }
/*  860: 795 */             else if (name.equals("CapableX"))
/*  861:     */             {
/*  862: 796 */               if (valueLabel1.isSelected()) {
/*  863: 797 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "x", Boolean.valueOf(true) });
/*  864: 798 */               } else if (valueLabel2.isSelected()) {
/*  865: 799 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "x", Boolean.valueOf(false) });
/*  866:     */               }
/*  867:     */             }
/*  868: 801 */             else if (name.equals("CapableY"))
/*  869:     */             {
/*  870: 802 */               if (valueLabel1.isSelected()) {
/*  871: 803 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "y", Boolean.valueOf(true) });
/*  872: 804 */               } else if (valueLabel2.isSelected()) {
/*  873: 805 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "y", Boolean.valueOf(false) });
/*  874:     */               }
/*  875:     */             }
/*  876: 807 */             else if (name.equals("CapableZ"))
/*  877:     */             {
/*  878: 808 */               if (valueLabel1.isSelected()) {
/*  879: 809 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "z", Boolean.valueOf(true) });
/*  880: 810 */               } else if (valueLabel2.isSelected()) {
/*  881: 811 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "z", Boolean.valueOf(false) });
/*  882:     */               }
/*  883:     */             }
/*  884: 813 */             else if (name.equals("CapabelePDG")) {
/*  885: 814 */               if (valueLabel1.isSelected()) {
/*  886: 815 */                 result = currentProcessor.executeControl("setPowerFactorLine", new Object[] { Integer.valueOf(1) });
/*  887: 816 */               } else if (valueLabel2.isSelected()) {
/*  888: 817 */                 result = currentProcessor.executeControl("setPowerFactorLine", new Object[] { Integer.valueOf(0) });
/*  889:     */               }
/*  890:     */             }
/*  891: 821 */             if (result) {
/*  892: 822 */               DisplayMessage.showInfoDialog("message.setTrue");
/*  893:     */             } else {
/*  894: 824 */               DisplayMessage.showErrorDialog("message.setFalse");
/*  895:     */             }
/*  896: 826 */             PageUtils.this.setEnabled(true);
/*  897:     */           }
/*  898:     */         }
/*  899: 830 */       });
/*  900: 831 */       p1.addComponent(caption1);
/*  901: 832 */       p2.addComponent(valueLabel1);
/*  902: 833 */       p3.addComponent(valueLabel2);
/*  903: 834 */       p4.addComponent(button1);
/*  904:     */       
/*  905: 836 */       vGroup.addGroup(
/*  906: 837 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption1).addComponent(valueLabel1).addComponent(
/*  907: 838 */         valueLabel2).addComponent(button1));
/*  908: 839 */       vGroup.addGap(15, 15, 15);
/*  909:     */     }
/*  910: 841 */     hGroup.addGap(40, 40, 40);
/*  911: 842 */     hGroup.addGroup(p1);
/*  912: 843 */     hGroup.addGroup(p2);
/*  913: 844 */     hGroup.addGroup(p3);
/*  914: 845 */     hGroup.addGroup(p4);
/*  915: 846 */     hGroup.addGap(20, 20, 20);
/*  916:     */     
/*  917: 848 */     layout.setHorizontalGroup(hGroup);
/*  918: 849 */     layout.setVerticalGroup(vGroup);
/*  919:     */     
/*  920:     */ 
/*  921: 852 */     GroupLayout layout2 = new GroupLayout(rightPanel);
/*  922: 853 */     rightPanel.setLayout(layout2);
/*  923: 854 */     layout2.setAutoCreateGaps(true);
/*  924: 855 */     layout2.setAutoCreateContainerGaps(true);
/*  925:     */     
/*  926: 857 */     GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
/*  927: 858 */     GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
/*  928:     */     
/*  929: 860 */     GroupLayout.ParallelGroup p5 = layout2
/*  930: 861 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  931: 862 */     GroupLayout.ParallelGroup p6 = layout2
/*  932: 863 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  933: 864 */     GroupLayout.ParallelGroup p7 = layout2
/*  934: 865 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  935: 866 */     GroupLayout.ParallelGroup p8 = layout2
/*  936: 867 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  937: 870 */     for (int j = index; j < list.size(); j++)
/*  938:     */     {
/*  939: 871 */       AALabel caption2 = new AALabel();
/*  940: 872 */       final AARadioButton valueLabel3 = new AARadioButton();
/*  941: 873 */       final AARadioButton valueLabel4 = new AARadioButton();
/*  942: 874 */       final AAButton button2 = new AAButton();
/*  943: 875 */       button2.setEnabled(((AutoRadioItem)list.get(j)).isEnable());
/*  944: 876 */       caption2.setText(((AutoRadioItem)list.get(j)).getCaption());
/*  945: 877 */       valueLabel3.setText(((AutoRadioItem)list.get(j)).getRadioLabel1());
/*  946: 878 */       valueLabel4.setText(((AutoRadioItem)list.get(j)).getRadioLabel2());
/*  947: 879 */       boolean value2 = VolUtil.parseBoolean(getResultByName(((AutoRadioItem)list.get(j)).getValue(), 
/*  948: 880 */         object));
/*  949: 881 */       if (value2)
/*  950:     */       {
/*  951: 882 */         valueLabel3.setSelected(true);
/*  952: 883 */         valueLabel4.setSelected(false);
/*  953:     */       }
/*  954:     */       else
/*  955:     */       {
/*  956: 885 */         valueLabel3.setSelected(false);
/*  957: 886 */         valueLabel4.setSelected(true);
/*  958:     */       }
/*  959: 888 */       valueLabel3.addActionListener(new ActionListener()
/*  960:     */       {
/*  961:     */         public void actionPerformed(ActionEvent e)
/*  962:     */         {
/*  963: 891 */           PageUtils.this.setSelected(true);
/*  964: 892 */           valueLabel4.setSelected(false);
/*  965: 893 */           button2.setEnabled(true);
/*  966:     */         }
/*  967: 895 */       });
/*  968: 896 */       valueLabel4.addActionListener(new ActionListener()
/*  969:     */       {
/*  970:     */         public void actionPerformed(ActionEvent e)
/*  971:     */         {
/*  972: 899 */           PageUtils.this.setSelected(false);
/*  973: 900 */           valueLabel4.setSelected(true);
/*  974: 901 */           button2.setEnabled(true);
/*  975:     */         }
/*  976: 903 */       });
/*  977: 904 */       button2.setText("message.apply");
/*  978: 905 */       button2.setName(((AutoRadioItem)list.get(j)).getButtonName());
/*  979: 906 */       button2.addActionListener(new ActionListener()
/*  980:     */       {
/*  981:     */         public void actionPerformed(ActionEvent e)
/*  982:     */         {
/*  983: 909 */           if (!SolarPowerTray.isLogin)
/*  984:     */           {
/*  985: 910 */             new LoginJDialog(new Frame(), true);
/*  986: 911 */             return;
/*  987:     */           }
/*  988: 913 */           AbstractProcessor currentProcessor = GlobalProcessors.getCurrentProcessor();
/*  989: 914 */           if (currentProcessor != null)
/*  990:     */           {
/*  991: 915 */             PageUtils.this.setEnabled(false);
/*  992: 916 */             String name = PageUtils.this.getName();
/*  993: 917 */             boolean result = false;
/*  994: 918 */             if (name.equals("CapableA"))
/*  995:     */             {
/*  996: 919 */               if (valueLabel3.isSelected()) {
/*  997: 920 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "a", Boolean.valueOf(true) });
/*  998: 921 */               } else if (valueLabel4.isSelected()) {
/*  999: 922 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "a", Boolean.valueOf(false) });
/* 1000:     */               }
/* 1001:     */             }
/* 1002: 924 */             else if (name.equals("CapableB"))
/* 1003:     */             {
/* 1004: 925 */               if (valueLabel3.isSelected()) {
/* 1005: 926 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "b", Boolean.valueOf(true) });
/* 1006: 927 */               } else if (valueLabel4.isSelected()) {
/* 1007: 928 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "b", Boolean.valueOf(false) });
/* 1008:     */               }
/* 1009:     */             }
/* 1010: 930 */             else if (name.equals("CapableK"))
/* 1011:     */             {
/* 1012: 931 */               if (valueLabel3.isSelected()) {
/* 1013: 932 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "k", Boolean.valueOf(true) });
/* 1014: 933 */               } else if (valueLabel4.isSelected()) {
/* 1015: 934 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "k", Boolean.valueOf(false) });
/* 1016:     */               }
/* 1017:     */             }
/* 1018: 936 */             else if (name.equals("CapableP"))
/* 1019:     */             {
/* 1020: 937 */               if (valueLabel3.isSelected()) {
/* 1021: 938 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "p", Boolean.valueOf(true) });
/* 1022: 939 */               } else if (valueLabel4.isSelected()) {
/* 1023: 940 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "p", Boolean.valueOf(false) });
/* 1024:     */               }
/* 1025:     */             }
/* 1026: 942 */             else if (name.equals("CapableG"))
/* 1027:     */             {
/* 1028: 943 */               if (valueLabel3.isSelected()) {
/* 1029: 944 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "g", Boolean.valueOf(true) });
/* 1030: 945 */               } else if (valueLabel4.isSelected()) {
/* 1031: 946 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "g", Boolean.valueOf(false) });
/* 1032:     */               }
/* 1033:     */             }
/* 1034: 948 */             else if (name.equals("CapableJ"))
/* 1035:     */             {
/* 1036: 949 */               if (valueLabel3.isSelected()) {
/* 1037: 950 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "j", Boolean.valueOf(true) });
/* 1038: 951 */               } else if (valueLabel4.isSelected()) {
/* 1039: 952 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "j", Boolean.valueOf(false) });
/* 1040:     */               }
/* 1041:     */             }
/* 1042: 954 */             else if (name.equals("CapableU"))
/* 1043:     */             {
/* 1044: 955 */               if (valueLabel3.isSelected()) {
/* 1045: 956 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "u", Boolean.valueOf(true) });
/* 1046: 957 */               } else if (valueLabel4.isSelected()) {
/* 1047: 958 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "u", Boolean.valueOf(false) });
/* 1048:     */               }
/* 1049:     */             }
/* 1050: 960 */             else if (name.equals("CapableV"))
/* 1051:     */             {
/* 1052: 961 */               if (valueLabel3.isSelected()) {
/* 1053: 962 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "v", Boolean.valueOf(true) });
/* 1054: 963 */               } else if (valueLabel4.isSelected()) {
/* 1055: 964 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "v", Boolean.valueOf(false) });
/* 1056:     */               }
/* 1057:     */             }
/* 1058: 966 */             else if (name.equals("CapableW"))
/* 1059:     */             {
/* 1060: 967 */               if (valueLabel3.isSelected()) {
/* 1061: 968 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "w", Boolean.valueOf(true) });
/* 1062: 969 */               } else if (valueLabel4.isSelected()) {
/* 1063: 970 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "w", Boolean.valueOf(false) });
/* 1064:     */               }
/* 1065:     */             }
/* 1066: 972 */             else if (name.equals("CapableX"))
/* 1067:     */             {
/* 1068: 973 */               if (valueLabel3.isSelected()) {
/* 1069: 974 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "x", Boolean.valueOf(true) });
/* 1070: 975 */               } else if (valueLabel4.isSelected()) {
/* 1071: 976 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "x", Boolean.valueOf(false) });
/* 1072:     */               }
/* 1073:     */             }
/* 1074: 978 */             else if (name.equals("CapableY"))
/* 1075:     */             {
/* 1076: 979 */               if (valueLabel3.isSelected()) {
/* 1077: 980 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "y", Boolean.valueOf(true) });
/* 1078: 981 */               } else if (valueLabel4.isSelected()) {
/* 1079: 982 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "y", Boolean.valueOf(false) });
/* 1080:     */               }
/* 1081:     */             }
/* 1082: 984 */             else if (name.equals("CapableZ"))
/* 1083:     */             {
/* 1084: 985 */               if (valueLabel3.isSelected()) {
/* 1085: 986 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "z", Boolean.valueOf(true) });
/* 1086: 987 */               } else if (valueLabel4.isSelected()) {
/* 1087: 988 */                 result = currentProcessor.executeControl("setCapability", new Object[] { "z", Boolean.valueOf(false) });
/* 1088:     */               }
/* 1089:     */             }
/* 1090: 990 */             else if (name.equals("CapabelePDG")) {
/* 1091: 991 */               if (valueLabel3.isSelected()) {
/* 1092: 992 */                 result = currentProcessor.executeControl("setPowerFactorLine", new Object[] { Integer.valueOf(1) });
/* 1093: 993 */               } else if (valueLabel4.isSelected()) {
/* 1094: 994 */                 result = currentProcessor.executeControl("setPowerFactorLine", new Object[] { Integer.valueOf(0) });
/* 1095:     */               }
/* 1096:     */             }
/* 1097: 997 */             if (result) {
/* 1098: 998 */               DisplayMessage.showInfoDialog("message.setTrue");
/* 1099:     */             } else {
/* 1100:1000 */               DisplayMessage.showErrorDialog("message.setFalse");
/* 1101:     */             }
/* 1102:1002 */             PageUtils.this.setEnabled(true);
/* 1103:     */           }
/* 1104:     */         }
/* 1105:1006 */       });
/* 1106:1007 */       p5.addComponent(caption2);
/* 1107:1008 */       p6.addComponent(valueLabel3);
/* 1108:1009 */       p7.addComponent(valueLabel4);
/* 1109:1010 */       p8.addComponent(button2);
/* 1110:     */       
/* 1111:1012 */       vGroup2.addGroup(
/* 1112:1013 */         layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption2).addComponent(valueLabel3).addComponent(valueLabel4).addComponent(
/* 1113:1014 */         button2));
/* 1114:1015 */       vGroup2.addGap(15, 15, 15);
/* 1115:     */     }
/* 1116:1017 */     hGroup2.addGap(20, 20, 20);
/* 1117:1018 */     hGroup2.addGroup(p5);
/* 1118:1019 */     hGroup2.addGroup(p6);
/* 1119:1020 */     hGroup2.addGroup(p7);
/* 1120:1021 */     hGroup2.addGroup(p8);
/* 1121:1022 */     hGroup2.addGap(40, 40, 40);
/* 1122:     */     
/* 1123:1024 */     layout2.setHorizontalGroup(hGroup2);
/* 1124:1025 */     layout2.setVerticalGroup(vGroup2);
/* 1125:1026 */     panel.add(leftPanel, "Center");
/* 1126:1027 */     panel.add(rightPanel, "East");
/* 1127:     */   }
/* 1128:     */   
/* 1129:     */   public static List<RefreshProductInfo> setInfoLayout(List<AutoLabelItem> list, JPanel panel, Object object)
/* 1130:     */   {
/* 1131:1032 */     List<RefreshProductInfo> valueList = new ArrayList();
/* 1132:1033 */     panel.setLayout(new BorderLayout());
/* 1133:1034 */     int index = list.size();
/* 1134:1035 */     JPanel leftPanel = new JPanel();
/* 1135:     */     
/* 1136:     */ 
/* 1137:1038 */     GroupLayout layout = new GroupLayout(leftPanel);
/* 1138:1039 */     leftPanel.setLayout(layout);
/* 1139:1040 */     layout.setAutoCreateGaps(true);
/* 1140:1041 */     layout.setAutoCreateContainerGaps(true);
/* 1141:     */     
/* 1142:1043 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/* 1143:1044 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/* 1144:     */     
/* 1145:1046 */     GroupLayout.ParallelGroup p1 = layout
/* 1146:1047 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/* 1147:1048 */     GroupLayout.ParallelGroup p2 = layout
/* 1148:1049 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1149:1050 */     GroupLayout.ParallelGroup p3 = layout
/* 1150:1051 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1151:1054 */     for (int i = 0; i < index; i++)
/* 1152:     */     {
/* 1153:1055 */       AALabel caption1 = new AALabel();
/* 1154:1056 */       JTextField value1 = new JTextField();
/* 1155:1057 */       value1.setEditable(false);
/* 1156:1058 */       value1.setFont(I18NListener.font12);
/* 1157:1059 */       value1.setBackground(I18NListener.bgColor);
/* 1158:1060 */       AALabel unit1 = new AALabel();
/* 1159:1061 */       caption1.setText(((AutoLabelItem)list.get(i)).getCaption());
/* 1160:1062 */       value1.setText(
/* 1161:1063 */         getResultByName(((AutoLabelItem)list.get(i)).getValue(), object));
/* 1162:1064 */       unit1.setText(((AutoLabelItem)list.get(i)).getUnit());
/* 1163:1065 */       valueList.add(new RefreshProductInfo(value1, ((AutoLabelItem)list.get(i)).getValue()));
/* 1164:1066 */       p1.addComponent(caption1);
/* 1165:1067 */       p2.addComponent(value1);
/* 1166:1068 */       p3.addComponent(unit1);
/* 1167:     */       
/* 1168:1070 */       vGroup.addGroup(
/* 1169:1071 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption1).addComponent(value1).addComponent(
/* 1170:1072 */         unit1));
/* 1171:1073 */       vGroup.addGap(15, 15, 15);
/* 1172:     */     }
/* 1173:1075 */     hGroup.addGap(55, 55, 55);
/* 1174:1076 */     hGroup.addGroup(p1);
/* 1175:1077 */     hGroup.addGroup(p2);
/* 1176:1078 */     hGroup.addGroup(p3);
/* 1177:1079 */     hGroup.addGap(55, 55, 55);
/* 1178:     */     
/* 1179:1081 */     layout.setHorizontalGroup(hGroup);
/* 1180:1082 */     layout.setVerticalGroup(vGroup);
/* 1181:     */     
/* 1182:1084 */     panel.add(leftPanel, "Center");
/* 1183:1085 */     return valueList;
/* 1184:     */   }
/* 1185:     */   
/* 1186:     */   public static List<RefreshRatingInfo> setTextLayout(List<AutoLabelItem> list, JPanel panel, Object object)
/* 1187:     */   {
/* 1188:1090 */     List<RefreshRatingInfo> productList = new ArrayList();
/* 1189:1091 */     panel.setLayout(new BorderLayout());
/* 1190:1092 */     int index = 2;
/* 1191:1093 */     if (list.size() % 2 == 0) {
/* 1192:1094 */       index = list.size() / 2;
/* 1193:     */     } else {
/* 1194:1096 */       index = list.size() / 2 + 1;
/* 1195:     */     }
/* 1196:1098 */     JPanel leftPanel = new JPanel();
/* 1197:1099 */     JPanel rightPanel = new JPanel();
/* 1198:     */     
/* 1199:     */ 
/* 1200:1102 */     GroupLayout layout = new GroupLayout(leftPanel);
/* 1201:1103 */     leftPanel.setLayout(layout);
/* 1202:1104 */     layout.setAutoCreateGaps(true);
/* 1203:1105 */     layout.setAutoCreateContainerGaps(true);
/* 1204:     */     
/* 1205:1107 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/* 1206:1108 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/* 1207:     */     
/* 1208:1110 */     GroupLayout.ParallelGroup p1 = layout
/* 1209:1111 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/* 1210:1112 */     GroupLayout.ParallelGroup p2 = layout
/* 1211:1113 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1212:1114 */     GroupLayout.ParallelGroup p3 = layout
/* 1213:1115 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1214:1118 */     for (int i = 0; i < index; i++)
/* 1215:     */     {
/* 1216:1119 */       AALabel caption1 = new AALabel();
/* 1217:1120 */       AATextField value1 = new AATextField();
/* 1218:1121 */       AALabel unit1 = new AALabel();
/* 1219:1122 */       caption1.setText(((AutoLabelItem)list.get(i)).getCaption());
/* 1220:1123 */       value1.setText(
/* 1221:1124 */         getResultByName(((AutoLabelItem)list.get(i)).getValue(), object));
/* 1222:1125 */       unit1.setText(((AutoLabelItem)list.get(i)).getUnit());
/* 1223:1126 */       productList.add(new RefreshRatingInfo(value1, ((AutoLabelItem)list.get(i)).getValue()));
/* 1224:1127 */       p1.addComponent(caption1);
/* 1225:1128 */       p2.addComponent(value1, 50, 50, 50);
/* 1226:1129 */       p3.addComponent(unit1);
/* 1227:     */       
/* 1228:1131 */       vGroup.addGroup(
/* 1229:1132 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption1).addComponent(value1).addComponent(
/* 1230:1133 */         unit1));
/* 1231:1134 */       vGroup.addGap(15, 15, 15);
/* 1232:     */     }
/* 1233:1136 */     hGroup.addGap(15, 15, 15);
/* 1234:1137 */     hGroup.addGroup(p1);
/* 1235:1138 */     hGroup.addGroup(p2);
/* 1236:1139 */     hGroup.addGroup(p3);
/* 1237:1140 */     hGroup.addGap(15, 15, 15);
/* 1238:     */     
/* 1239:1142 */     layout.setHorizontalGroup(hGroup);
/* 1240:1143 */     layout.setVerticalGroup(vGroup);
/* 1241:     */     
/* 1242:     */ 
/* 1243:1146 */     GroupLayout layout2 = new GroupLayout(rightPanel);
/* 1244:1147 */     rightPanel.setLayout(layout2);
/* 1245:1148 */     layout2.setAutoCreateGaps(true);
/* 1246:1149 */     layout2.setAutoCreateContainerGaps(true);
/* 1247:     */     
/* 1248:1151 */     GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
/* 1249:1152 */     GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
/* 1250:     */     
/* 1251:1154 */     GroupLayout.ParallelGroup p4 = layout2
/* 1252:1155 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/* 1253:1156 */     GroupLayout.ParallelGroup p5 = layout2
/* 1254:1157 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1255:1158 */     GroupLayout.ParallelGroup p6 = layout2
/* 1256:1159 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1257:1162 */     for (int j = index; j < list.size(); j++)
/* 1258:     */     {
/* 1259:1163 */       AALabel caption2 = new AALabel();
/* 1260:1164 */       AATextField value2 = new AATextField();
/* 1261:1165 */       AALabel unit2 = new AALabel();
/* 1262:1166 */       caption2.setText(((AutoLabelItem)list.get(j)).getCaption());
/* 1263:1167 */       value2.setText(
/* 1264:1168 */         getResultByName(((AutoLabelItem)list.get(j)).getValue(), object));
/* 1265:1169 */       unit2.setText(((AutoLabelItem)list.get(j)).getUnit());
/* 1266:1170 */       productList.add(new RefreshRatingInfo(value2, ((AutoLabelItem)list.get(j)).getValue()));
/* 1267:1171 */       p4.addComponent(caption2);
/* 1268:1172 */       p5.addComponent(value2, 50, 50, 50);
/* 1269:1173 */       p6.addComponent(unit2);
/* 1270:     */       
/* 1271:1175 */       vGroup2.addGroup(
/* 1272:1176 */         layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption2).addComponent(value2).addComponent(
/* 1273:1177 */         unit2));
/* 1274:1178 */       vGroup2.addGap(15, 15, 15);
/* 1275:     */     }
/* 1276:1180 */     hGroup2.addGap(15, 15, 15);
/* 1277:1181 */     hGroup2.addGroup(p4);
/* 1278:1182 */     hGroup2.addGroup(p5);
/* 1279:1183 */     hGroup2.addGroup(p6);
/* 1280:1184 */     hGroup2.addGap(15, 15, 15);
/* 1281:     */     
/* 1282:1186 */     layout2.setHorizontalGroup(hGroup2);
/* 1283:1187 */     layout2.setVerticalGroup(vGroup2);
/* 1284:1188 */     panel.add(leftPanel, "Center");
/* 1285:1189 */     panel.add(rightPanel, "East");
/* 1286:1190 */     return productList;
/* 1287:     */   }
/* 1288:     */   
/* 1289:     */   public static List<RefreshRatingInfo> setRestoreTextLayout(List<AutoLabelItem> list, JPanel panel, Object object)
/* 1290:     */   {
/* 1291:1195 */     List<RefreshRatingInfo> productList = new ArrayList();
/* 1292:1196 */     panel.setLayout(new BorderLayout());
/* 1293:1197 */     int index = 2;
/* 1294:1198 */     if (list.size() % 2 == 0) {
/* 1295:1199 */       index = list.size() / 2;
/* 1296:     */     } else {
/* 1297:1201 */       index = list.size() / 2 + 1;
/* 1298:     */     }
/* 1299:1203 */     JPanel leftPanel = new JPanel();
/* 1300:1204 */     JPanel rightPanel = new JPanel();
/* 1301:     */     
/* 1302:     */ 
/* 1303:1207 */     GroupLayout layout = new GroupLayout(leftPanel);
/* 1304:1208 */     leftPanel.setLayout(layout);
/* 1305:1209 */     layout.setAutoCreateGaps(true);
/* 1306:1210 */     layout.setAutoCreateContainerGaps(true);
/* 1307:     */     
/* 1308:1212 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/* 1309:1213 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/* 1310:     */     
/* 1311:1215 */     GroupLayout.ParallelGroup p1 = layout
/* 1312:1216 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/* 1313:1217 */     GroupLayout.ParallelGroup p2 = layout
/* 1314:1218 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1315:1219 */     GroupLayout.ParallelGroup p3 = layout
/* 1316:1220 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1317:1223 */     for (int i = 0; i < index; i++)
/* 1318:     */     {
/* 1319:1224 */       AALabel caption1 = new AALabel();
/* 1320:1225 */       AATextField value1 = new AATextField();
/* 1321:1226 */       AALabel unit1 = new AALabel();
/* 1322:1227 */       caption1.setText(((AutoLabelItem)list.get(i)).getCaption());
/* 1323:1228 */       value1.setText(
/* 1324:1229 */         getResultByName(((AutoLabelItem)list.get(i)).getValue(), object));
/* 1325:1230 */       unit1.setText(((AutoLabelItem)list.get(i)).getUnit());
/* 1326:1231 */       productList.add(new RefreshRatingInfo(value1, ((AutoLabelItem)list.get(i)).getValue()));
/* 1327:1232 */       p1.addComponent(caption1);
/* 1328:1233 */       p2.addComponent(value1, 100, 100, 100);
/* 1329:1234 */       p3.addComponent(unit1);
/* 1330:     */       
/* 1331:1236 */       vGroup.addGroup(
/* 1332:1237 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption1).addComponent(value1).addComponent(
/* 1333:1238 */         unit1));
/* 1334:1239 */       vGroup.addGap(15, 15, 15);
/* 1335:     */     }
/* 1336:1241 */     hGroup.addGap(15, 15, 15);
/* 1337:1242 */     hGroup.addGroup(p1);
/* 1338:1243 */     hGroup.addGroup(p2);
/* 1339:1244 */     hGroup.addGroup(p3);
/* 1340:1245 */     hGroup.addGap(15, 15, 15);
/* 1341:     */     
/* 1342:1247 */     layout.setHorizontalGroup(hGroup);
/* 1343:1248 */     layout.setVerticalGroup(vGroup);
/* 1344:     */     
/* 1345:     */ 
/* 1346:1251 */     GroupLayout layout2 = new GroupLayout(rightPanel);
/* 1347:1252 */     rightPanel.setLayout(layout2);
/* 1348:1253 */     layout2.setAutoCreateGaps(true);
/* 1349:1254 */     layout2.setAutoCreateContainerGaps(true);
/* 1350:     */     
/* 1351:1256 */     GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
/* 1352:1257 */     GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
/* 1353:     */     
/* 1354:1259 */     GroupLayout.ParallelGroup p4 = layout2
/* 1355:1260 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/* 1356:1261 */     GroupLayout.ParallelGroup p5 = layout2
/* 1357:1262 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1358:1263 */     GroupLayout.ParallelGroup p6 = layout2
/* 1359:1264 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/* 1360:1267 */     for (int j = index; j < list.size(); j++)
/* 1361:     */     {
/* 1362:1268 */       AALabel caption2 = new AALabel();
/* 1363:1269 */       AATextField value2 = new AATextField();
/* 1364:1270 */       AALabel unit2 = new AALabel();
/* 1365:1271 */       caption2.setText(((AutoLabelItem)list.get(j)).getCaption());
/* 1366:1272 */       value2.setText(
/* 1367:1273 */         getResultByName(((AutoLabelItem)list.get(j)).getValue(), object));
/* 1368:1274 */       unit2.setText(((AutoLabelItem)list.get(j)).getUnit());
/* 1369:1275 */       productList.add(new RefreshRatingInfo(value2, ((AutoLabelItem)list.get(j)).getValue()));
/* 1370:1276 */       p4.addComponent(caption2);
/* 1371:1277 */       p5.addComponent(value2, 100, 100, 100);
/* 1372:1278 */       p6.addComponent(unit2);
/* 1373:     */       
/* 1374:1280 */       vGroup2.addGroup(
/* 1375:1281 */         layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption2).addComponent(value2).addComponent(
/* 1376:1282 */         unit2));
/* 1377:1283 */       vGroup2.addGap(15, 15, 15);
/* 1378:     */     }
/* 1379:1285 */     hGroup2.addGap(15, 15, 15);
/* 1380:1286 */     hGroup2.addGroup(p4);
/* 1381:1287 */     hGroup2.addGroup(p5);
/* 1382:1288 */     hGroup2.addGroup(p6);
/* 1383:1289 */     hGroup2.addGap(15, 15, 15);
/* 1384:     */     
/* 1385:1291 */     layout2.setHorizontalGroup(hGroup2);
/* 1386:1292 */     layout2.setVerticalGroup(vGroup2);
/* 1387:1293 */     panel.add(leftPanel, "Center");
/* 1388:1294 */     panel.add(rightPanel, "East");
/* 1389:1295 */     return productList;
/* 1390:     */   }
/* 1391:     */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.PageUtils
 * JD-Core Version:    0.7.0.1
 */