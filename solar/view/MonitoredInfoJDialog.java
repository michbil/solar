/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.dao.BuyInfoDao;
/*   8:    */ import cn.com.voltronic.solar.data.bean.AutoLabelItem;
/*   9:    */ import cn.com.voltronic.solar.data.bean.BuyInfo;
/*  10:    */ import cn.com.voltronic.solar.data.bean.MachineInfo;
/*  11:    */ import cn.com.voltronic.solar.data.bean.ProductInfo;
/*  12:    */ import cn.com.voltronic.solar.data.bean.RatingInfo;
/*  13:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  14:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  15:    */ import cn.com.voltronic.solar.protocol.P30;
/*  16:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  17:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  18:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  19:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  20:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  21:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  22:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  23:    */ import cn.com.voltronic.solar.view.component.AATitleBorder;
/*  24:    */ import cn.com.voltronic.solar.view.component.ComponentFactory;
/*  25:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  26:    */ import cn.com.voltronic.solar.view.component.I18NListener;
/*  27:    */ import cn.com.voltronic.solar.view.component.MyCalendarPanel;
/*  28:    */ import java.awt.Container;
/*  29:    */ import java.awt.Frame;
/*  30:    */ import java.awt.event.ActionEvent;
/*  31:    */ import java.awt.event.ActionListener;
/*  32:    */ import java.awt.event.MouseAdapter;
/*  33:    */ import java.awt.event.MouseEvent;
/*  34:    */ import java.text.DateFormat;
/*  35:    */ import java.text.SimpleDateFormat;
/*  36:    */ import java.util.Date;
/*  37:    */ import java.util.List;
/*  38:    */ import javax.swing.GroupLayout;
/*  39:    */ import javax.swing.GroupLayout.Alignment;
/*  40:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  41:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  42:    */ import javax.swing.ImageIcon;
/*  43:    */ import javax.swing.JPanel;
/*  44:    */ import javax.swing.JSpinner;
/*  45:    */ import javax.swing.JTextField;
/*  46:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  47:    */ 
/*  48:    */ public class MonitoredInfoJDialog
/*  49:    */   extends AADialog
/*  50:    */ {
/*  51:    */   private static final long serialVersionUID = 2431041539300038729L;
/*  52:    */   private AAButton closeButton;
/*  53:    */   private AAButton applyButton;
/*  54:    */   private AALabel jLabel73;
/*  55:    */   private AALabel jLabel74;
/*  56:    */   private AALabel jLabel75;
/*  57:    */   private AALabel jLabel76;
/*  58:    */   private AALabel jLabel77;
/*  59:    */   private JPanel jPanel1;
/*  60:    */   private JPanel jPanel2;
/*  61:    */   private JPanel jPanel3;
/*  62:    */   private JPanel jPanel4;
/*  63:    */   private JPanel jPanel5;
/*  64:    */   private JSpinner jSpinner1;
/*  65:    */   private JTextField jTextField1;
/*  66:    */   private JTextField jTextField2;
/*  67:    */   private IProtocol protocol;
/*  68: 71 */   public MachineInfo machineInfo = null;
/*  69: 72 */   private BuyInfoDao buyInfoDao = null;
/*  70:    */   
/*  71:    */   public MonitoredInfoJDialog(Frame parent, boolean modal)
/*  72:    */   {
/*  73: 75 */     super(parent, modal);
/*  74: 76 */     this.machineInfo = new MachineInfo();
/*  75: 77 */     this.buyInfoDao = new BuyInfoDao();
/*  76: 78 */     this.protocol = new P30();
/*  77:    */     try
/*  78:    */     {
/*  79: 80 */       AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/*  80: 81 */       this.protocol = processor.getProtocol();
/*  81: 82 */       this.machineInfo = ((MachineInfo)processor.getBeanBag().getBean("machineinfo"));
/*  82:    */     }
/*  83:    */     catch (Exception localException) {}
/*  84: 85 */     if (this.protocol == null) {
/*  85: 86 */       this.protocol = new P30();
/*  86:    */     }
/*  87: 88 */     initComponents();
/*  88: 89 */     setTitle("message.monitorPointInfo");
/*  89: 90 */     setLocationRelativeTo(null);
/*  90: 91 */     setVisible(true);
/*  91:    */   }
/*  92:    */   
/*  93:    */   private void initComponents()
/*  94:    */   {
/*  95: 96 */     this.jPanel1 = new JPanel();
/*  96: 97 */     this.jPanel3 = new JPanel();
/*  97: 98 */     this.jPanel4 = new JPanel();
/*  98: 99 */     this.jPanel5 = new JPanel();
/*  99:100 */     this.jLabel73 = new AALabel();
/* 100:101 */     this.jTextField1 = new JTextField();
/* 101:102 */     this.jLabel74 = new AALabel();
/* 102:103 */     this.jLabel75 = new AALabel();
/* 103:104 */     this.jLabel76 = new AALabel();
/* 104:105 */     this.jLabel77 = new AALabel();
/* 105:106 */     this.jTextField2 = new JTextField();
/* 106:107 */     this.applyButton = new AAButton();
/* 107:108 */     this.jPanel2 = new JPanel();
/* 108:109 */     this.closeButton = new AAButton();
/* 109:    */     
/* 110:111 */     setBuyInfo();
/* 111:    */     
/* 112:113 */     setDefaultCloseOperation(2);
/* 113:    */     
/* 114:115 */     getProductInfo();
/* 115:    */     
/* 116:117 */     getRatingInfo();
/* 117:    */     
/* 118:119 */     this.jPanel5.setBorder(new AATitleBorder("message.buyinfo"));
/* 119:    */     
/* 120:121 */     this.jLabel73.setText("message.devicebuydate[:]");
/* 121:    */     
/* 122:123 */     this.jTextField1.setEditable(false);
/* 123:124 */     this.jTextField1.setBackground(I18NListener.bgColor);
/* 124:125 */     this.jTextField1.addMouseListener(new MouseAdapter()
/* 125:    */     {
/* 126:    */       public void mouseClicked(MouseEvent e)
/* 127:    */       {
/* 128:128 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(MonitoredInfoJDialog.this.jTextField1);
/* 129:129 */         calendarPanel.setBounds(MonitoredInfoJDialog.this.getX() + MonitoredInfoJDialog.this.jLabel74.getX() + 30, MonitoredInfoJDialog.this.jPanel5.getY() + 
/* 130:130 */           MonitoredInfoJDialog.this.jLabel74.getY() + 10, calendarPanel.getWidth(), 
/* 131:131 */           calendarPanel.getHeight());
/* 132:132 */         calendarPanel.setVisible(true);
/* 133:    */       }
/* 134:135 */     });
/* 135:136 */     this.jLabel74.setIcon(new ImageIcon(Constants.DATE));
/* 136:137 */     this.jLabel74.addMouseListener(new MouseAdapter()
/* 137:    */     {
/* 138:    */       public void mouseClicked(MouseEvent e)
/* 139:    */       {
/* 140:140 */         MyCalendarPanel calendarPanel = new MyCalendarPanel(MonitoredInfoJDialog.this.jTextField1);
/* 141:141 */         calendarPanel.setBounds(MonitoredInfoJDialog.this.getX() + MonitoredInfoJDialog.this.jLabel74.getX() + 30, MonitoredInfoJDialog.this.jPanel5.getY() + 
/* 142:142 */           MonitoredInfoJDialog.this.jLabel74.getY() + 10, calendarPanel.getWidth(), 
/* 143:143 */           calendarPanel.getHeight());
/* 144:144 */         calendarPanel.setVisible(true);
/* 145:    */       }
/* 146:147 */     });
/* 147:148 */     this.jLabel75.setText("message.warranty[:]");
/* 148:    */     
/* 149:150 */     this.jLabel76.setText("message.year");
/* 150:    */     
/* 151:152 */     this.jLabel77.setText("message.pncode");
/* 152:    */     
/* 153:154 */     this.applyButton.setText("message.apply");
/* 154:155 */     this.applyButton.addActionListener(new ActionListener()
/* 155:    */     {
/* 156:    */       public void actionPerformed(ActionEvent e)
/* 157:    */       {
/* 158:158 */         if (!SolarPowerTray.isLogin)
/* 159:    */         {
/* 160:159 */           new LoginJDialog(new Frame(), true);
/* 161:160 */           return;
/* 162:    */         }
/* 163:162 */         MonitoredInfoJDialog.this.applyAction();
/* 164:    */       }
/* 165:165 */     });
/* 166:166 */     GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
/* 167:167 */     this.jPanel5.setLayout(jPanel5Layout);
/* 168:168 */     jPanel5Layout.setHorizontalGroup(
/* 169:169 */       jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 170:170 */       .addGroup(jPanel5Layout.createSequentialGroup()
/* 171:171 */       .addContainerGap()
/* 172:172 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 173:173 */       .addComponent(this.jLabel73, GroupLayout.Alignment.TRAILING)
/* 174:174 */       .addComponent(this.jLabel75, GroupLayout.Alignment.TRAILING)
/* 175:175 */       .addComponent(this.jLabel77, GroupLayout.Alignment.TRAILING))
/* 176:176 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 177:177 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 178:178 */       .addComponent(this.jTextField1, -2, 102, -2)
/* 179:179 */       .addComponent(this.jSpinner1, -1, 102, 32767)
/* 180:180 */       .addComponent(this.jTextField2, -1, 102, 32767)
/* 181:181 */       .addComponent(this.applyButton))
/* 182:182 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 183:183 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 184:184 */       .addComponent(this.jLabel74)
/* 185:185 */       .addComponent(this.jLabel76))
/* 186:186 */       .addContainerGap(408, 32767)));
/* 187:    */     
/* 188:188 */     jPanel5Layout.setVerticalGroup(
/* 189:189 */       jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 190:190 */       .addGroup(jPanel5Layout.createSequentialGroup()
/* 191:191 */       .addContainerGap()
/* 192:192 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 193:193 */       .addComponent(this.jLabel73)
/* 194:194 */       .addComponent(this.jTextField1, -2, -1, -2)
/* 195:195 */       .addComponent(this.jLabel74))
/* 196:196 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 197:197 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 198:198 */       .addComponent(this.jLabel75)
/* 199:199 */       .addComponent(this.jSpinner1, -2, -1, -2)
/* 200:200 */       .addComponent(this.jLabel76))
/* 201:201 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 202:202 */       .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 203:203 */       .addComponent(this.jLabel77)
/* 204:204 */       .addComponent(this.jTextField2, -2, -1, -2))
/* 205:205 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 206:206 */       .addComponent(this.applyButton)
/* 207:207 */       .addContainerGap(13, 32767)));
/* 208:    */     
/* 209:    */ 
/* 210:210 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 211:211 */     this.jPanel1.setLayout(jPanel1Layout);
/* 212:212 */     jPanel1Layout.setHorizontalGroup(
/* 213:213 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 214:214 */       .addComponent(this.jPanel3, -1, -1, 32767)
/* 215:215 */       .addComponent(this.jPanel4, -1, -1, 32767)
/* 216:216 */       .addComponent(this.jPanel5, -1, -1, 32767));
/* 217:    */     
/* 218:218 */     jPanel1Layout.setVerticalGroup(
/* 219:219 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 220:220 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 221:221 */       .addComponent(this.jPanel3, -2, -1, -2)
/* 222:222 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 223:223 */       .addComponent(this.jPanel4, -2, -1, -2)
/* 224:224 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 225:225 */       .addComponent(this.jPanel5, -1, -1, 32767)));
/* 226:    */     
/* 227:    */ 
/* 228:228 */     getContentPane().add(this.jPanel1, "Center");
/* 229:    */     
/* 230:230 */     this.closeButton.setText("message.close");
/* 231:231 */     this.closeButton.addActionListener(new ActionListener()
/* 232:    */     {
/* 233:    */       public void actionPerformed(ActionEvent e)
/* 234:    */       {
/* 235:234 */         MonitoredInfoJDialog.this.dispose();
/* 236:    */       }
/* 237:237 */     });
/* 238:238 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 239:239 */     this.jPanel2.setLayout(jPanel2Layout);
/* 240:240 */     jPanel2Layout.setHorizontalGroup(
/* 241:241 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 242:242 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 243:243 */       jPanel2Layout.createSequentialGroup().addContainerGap(561, 32767)
/* 244:244 */       .addComponent(this.closeButton)
/* 245:245 */       .addContainerGap()));
/* 246:    */     
/* 247:247 */     jPanel2Layout.setVerticalGroup(
/* 248:248 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 249:249 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 250:250 */       .addContainerGap()
/* 251:251 */       .addComponent(this.closeButton)
/* 252:252 */       .addContainerGap(-1, 32767)));
/* 253:    */     
/* 254:    */ 
/* 255:255 */     getContentPane().add(this.jPanel2, "Last");
/* 256:    */     
/* 257:257 */     pack();
/* 258:    */   }
/* 259:    */   
/* 260:    */   private void applyAction()
/* 261:    */   {
/* 262:261 */     AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/* 263:262 */     if (processor != null) {
/* 264:    */       try
/* 265:    */       {
/* 266:264 */         this.applyButton.setEnabled(false);
/* 267:265 */         String prodid = processor.getProtocol().getProtocolID();
/* 268:266 */         String serialno = processor.getSerialNo();
/* 269:267 */         String date = this.jTextField1.getText().trim();
/* 270:268 */         DateFormat format = new SimpleDateFormat(GlobalVariables.globalConfig.getDateFormat());
/* 271:269 */         Date purchasedate = new Date();
/* 272:    */         try
/* 273:    */         {
/* 274:271 */           purchasedate = format.parse(date);
/* 275:    */         }
/* 276:    */         catch (Exception ex)
/* 277:    */         {
/* 278:273 */           ex.printStackTrace();
/* 279:    */         }
/* 280:275 */         int warranty = Integer.parseInt(this.jSpinner1.getValue().toString().trim());
/* 281:276 */         String pncode = this.jTextField2.getText().trim();
/* 282:277 */         BuyInfo buyinfo = new BuyInfo();
/* 283:278 */         buyinfo.setSerialno(serialno);
/* 284:279 */         buyinfo.setProdid(prodid);
/* 285:280 */         buyinfo.setPurchasedate(purchasedate);
/* 286:281 */         buyinfo.setWarranty(warranty);
/* 287:282 */         buyinfo.setPncode(pncode);
/* 288:283 */         boolean result = false;
/* 289:284 */         if (this.buyInfoDao.isExistBuyinfo(serialno)) {
/* 290:285 */           result = this.buyInfoDao.updataBuyinfo(buyinfo);
/* 291:    */         } else {
/* 292:287 */           result = this.buyInfoDao.addBuyinfo(buyinfo);
/* 293:    */         }
/* 294:289 */         if (result) {
/* 295:290 */           DisplayMessage.showInfoDialog("message.OperationSuccess");
/* 296:    */         } else {
/* 297:292 */           DisplayMessage.showErrorDialog("message.operationfailure");
/* 298:    */         }
/* 299:    */       }
/* 300:    */       catch (Exception ex)
/* 301:    */       {
/* 302:295 */         DisplayMessage.showErrorDialog("message.operationfailure");
/* 303:    */       }
/* 304:    */       finally
/* 305:    */       {
/* 306:297 */         this.applyButton.setEnabled(true);
/* 307:    */       }
/* 308:    */     } else {
/* 309:300 */       DisplayMessage.showWarningDialog("message.notConnectMac");
/* 310:    */     }
/* 311:    */   }
/* 312:    */   
/* 313:    */   private void getProductInfo()
/* 314:    */   {
/* 315:305 */     this.jPanel3.setBorder(new AATitleBorder("message.productinfo"));
/* 316:306 */     ProductInfo productInfo = this.protocol.getProductInfo();
/* 317:307 */     List<AutoLabelItem> list = productInfo.getInfos();
/* 318:308 */     PageUtils.setInfoLayout(list, this.jPanel3, this);
/* 319:    */   }
/* 320:    */   
/* 321:    */   private void getRatingInfo()
/* 322:    */   {
/* 323:312 */     this.jPanel4.setBorder(new AATitleBorder("message.ratinginfo"));
/* 324:313 */     RatingInfo ratingInfo = this.protocol.getRatingInfo();
/* 325:314 */     List<AutoLabelItem> list = ratingInfo.getInfos();
/* 326:315 */     PageUtils.setTextLayout(list, this.jPanel4, this);
/* 327:    */   }
/* 328:    */   
/* 329:    */   private void setBuyInfo()
/* 330:    */   {
/* 331:319 */     AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/* 332:320 */     if (processor != null)
/* 333:    */     {
/* 334:321 */       String serialno = GlobalProcessors.getCurrentProcessor().getSerialNo();
/* 335:322 */       BuyInfo buyInfo = this.buyInfoDao.queryBuyinfo(serialno);
/* 336:323 */       if (buyInfo != null)
/* 337:    */       {
/* 338:324 */         this.jTextField1.setText(DateUtils.getFormatDate(buyInfo.getPurchasedate()));
/* 339:325 */         this.jSpinner1 = ComponentFactory.createNumberSpinner(1, 20, 1, buyInfo.getWarranty());
/* 340:326 */         this.jTextField2.setText(buyInfo.getPncode());
/* 341:    */       }
/* 342:    */       else
/* 343:    */       {
/* 344:328 */         this.jTextField1.setText(DateUtils.getNowDate());
/* 345:329 */         this.jSpinner1 = ComponentFactory.createNumberSpinner(1, 20, 1, 1);
/* 346:330 */         this.jTextField2.setText("");
/* 347:    */       }
/* 348:    */     }
/* 349:    */     else
/* 350:    */     {
/* 351:334 */       this.jTextField1.setText(DateUtils.getNowDate());
/* 352:335 */       this.jSpinner1 = ComponentFactory.createNumberSpinner(1, 20, 1, 1);
/* 353:336 */       this.jTextField2.setText("");
/* 354:    */     }
/* 355:    */   }
/* 356:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.MonitoredInfoJDialog
 * JD-Core Version:    0.7.0.1
 */