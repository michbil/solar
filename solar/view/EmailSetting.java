/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   5:    */ import cn.com.voltronic.solar.configure.EmailConfig;
/*   6:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   7:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   8:    */ import cn.com.voltronic.solar.util.EmailSendUtil;
/*   9:    */ import cn.com.voltronic.solar.util.InstallCert;
/*  10:    */ import cn.com.voltronic.solar.util.VolUtil;
/*  11:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  12:    */ import cn.com.voltronic.solar.view.component.AACheckBox;
/*  13:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  14:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  15:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  16:    */ import java.awt.Container;
/*  17:    */ import java.awt.Frame;
/*  18:    */ import java.awt.event.ActionEvent;
/*  19:    */ import java.awt.event.ActionListener;
/*  20:    */ import javax.swing.BorderFactory;
/*  21:    */ import javax.swing.DefaultListModel;
/*  22:    */ import javax.swing.GroupLayout;
/*  23:    */ import javax.swing.GroupLayout.Alignment;
/*  24:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  25:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  26:    */ import javax.swing.JList;
/*  27:    */ import javax.swing.JPanel;
/*  28:    */ import javax.swing.JPasswordField;
/*  29:    */ import javax.swing.JScrollPane;
/*  30:    */ import javax.swing.JTextField;
/*  31:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  32:    */ 
/*  33:    */ public class EmailSetting
/*  34:    */   extends AADialog
/*  35:    */ {
/*  36:    */   private static final long serialVersionUID = -1693380355909306633L;
/*  37:    */   private AAButton jButton1;
/*  38:    */   private AAButton jButton2;
/*  39:    */   private AAButton jButton3;
/*  40:    */   private AAButton jButton4;
/*  41:    */   private AAButton jButton5;
/*  42:    */   private AAButton jButton6;
/*  43:    */   private AACheckBox jCheckBox1;
/*  44:    */   private AACheckBox jCheckBox2;
/*  45:    */   private AALabel jLabel1;
/*  46:    */   private AALabel jLabel10;
/*  47:    */   private AALabel jLabel2;
/*  48:    */   private AALabel jLabel4;
/*  49:    */   private AALabel jLabel5;
/*  50:    */   private AALabel jLabel6;
/*  51:    */   private AALabel jLabel7;
/*  52:    */   private AALabel jLabel8;
/*  53:    */   private AALabel jLabel9;
/*  54:    */   private JList jList1;
/*  55:    */   private JPanel jPanel1;
/*  56:    */   private JPanel jPanel2;
/*  57:    */   private JPasswordField jPasswordField1;
/*  58:    */   private JScrollPane jScrollPane1;
/*  59:    */   private JTextField jTextField1;
/*  60:    */   private JTextField jTextField2;
/*  61:    */   private JTextField jTextField3;
/*  62:    */   private JTextField jTextField4;
/*  63:    */   private JTextField jTextField5;
/*  64: 66 */   private DefaultListModel listModel = null;
/*  65: 67 */   private EmailConfig emailConfig = null;
/*  66:    */   
/*  67:    */   public EmailSetting(Frame parent, boolean modal)
/*  68:    */   {
/*  69: 70 */     super(parent, modal);
/*  70: 71 */     this.emailConfig = GlobalVariables.emailConfig;
/*  71: 72 */     if (this.emailConfig == null) {
/*  72: 73 */       this.emailConfig = new EmailConfig();
/*  73:    */     }
/*  74: 75 */     initComponents();
/*  75: 76 */     setTitle("message.emailSet");
/*  76: 77 */     setLocationRelativeTo(null);
/*  77: 78 */     setVisible(true);
/*  78:    */   }
/*  79:    */   
/*  80:    */   private void initValue()
/*  81:    */   {
/*  82: 82 */     this.jTextField1.setText(this.emailConfig.getSmtp());
/*  83: 83 */     this.jTextField2.setText(this.emailConfig.getPort());
/*  84: 84 */     this.jTextField3.setText(this.emailConfig.getSender());
/*  85: 85 */     this.jTextField4.setText(this.emailConfig.getAccount());
/*  86: 86 */     this.jCheckBox2.setSelected(this.emailConfig.getNeedAuth().booleanValue());
/*  87: 87 */     this.jPasswordField1.setText(this.emailConfig.getPassword());
/*  88: 88 */     String receiveArr = this.emailConfig.getRecievers().trim();
/*  89: 89 */     if (!"".equalsIgnoreCase(receiveArr)) {
/*  90: 91 */       if (receiveArr.indexOf(";") == -1)
/*  91:    */       {
/*  92: 92 */         this.listModel.addElement(receiveArr);
/*  93:    */       }
/*  94:    */       else
/*  95:    */       {
/*  96: 94 */         String[] strings = receiveArr.split(";");
/*  97: 95 */         for (int i = 0; i < strings.length; i++) {
/*  98: 96 */           this.listModel.add(i, strings[i]);
/*  99:    */         }
/* 100:    */       }
/* 101:    */     }
/* 102: 99 */     this.jList1.setModel(this.listModel);
/* 103:    */   }
/* 104:    */   
/* 105:    */   private void initComponents()
/* 106:    */   {
/* 107:104 */     this.jPanel1 = new JPanel();
/* 108:105 */     this.jLabel1 = new AALabel();
/* 109:106 */     this.jLabel2 = new AALabel();
/* 110:107 */     this.jTextField1 = new JTextField();
/* 111:108 */     this.jLabel4 = new AALabel();
/* 112:109 */     this.jTextField2 = new JTextField();
/* 113:110 */     this.jCheckBox1 = new AACheckBox();
/* 114:111 */     this.jButton3 = new AAButton();
/* 115:112 */     this.jLabel5 = new AALabel();
/* 116:113 */     this.jTextField3 = new JTextField();
/* 117:114 */     this.jLabel6 = new AALabel();
/* 118:115 */     this.jTextField4 = new JTextField();
/* 119:116 */     this.jCheckBox2 = new AACheckBox();
/* 120:117 */     this.jLabel7 = new AALabel();
/* 121:118 */     this.jPasswordField1 = new JPasswordField();
/* 122:119 */     this.jLabel8 = new AALabel();
/* 123:120 */     this.jLabel9 = new AALabel();
/* 124:121 */     this.jButton4 = new AAButton();
/* 125:122 */     this.jLabel10 = new AALabel();
/* 126:123 */     this.jScrollPane1 = new JScrollPane();
/* 127:124 */     this.listModel = new DefaultListModel();
/* 128:125 */     this.jList1 = new JList();
/* 129:126 */     this.jTextField5 = new JTextField();
/* 130:127 */     this.jButton5 = new AAButton();
/* 131:128 */     this.jButton6 = new AAButton();
/* 132:129 */     this.jPanel2 = new JPanel();
/* 133:130 */     this.jButton1 = new AAButton();
/* 134:131 */     this.jButton2 = new AAButton();
/* 135:    */     
/* 136:133 */     initValue();
/* 137:    */     
/* 138:135 */     setDefaultCloseOperation(2);
/* 139:    */     
/* 140:137 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 141:    */     
/* 142:139 */     this.jLabel1.setText("message.serverSet");
/* 143:    */     
/* 144:141 */     this.jLabel2.setText("message.sendServer[:]");
/* 145:    */     
/* 146:143 */     this.jLabel4.setText("message.proxyport[:]");
/* 147:    */     
/* 148:145 */     this.jCheckBox1.setText("Exchange Server");
/* 149:146 */     this.jCheckBox1.addActionListener(new ActionListener()
/* 150:    */     {
/* 151:    */       public void actionPerformed(ActionEvent e)
/* 152:    */       {
/* 153:149 */         if (EmailSetting.this.jCheckBox1.isSelected()) {
/* 154:150 */           EmailSetting.this.jButton3.setEnabled(true);
/* 155:    */         } else {
/* 156:152 */           EmailSetting.this.jButton3.setEnabled(false);
/* 157:    */         }
/* 158:    */       }
/* 159:156 */     });
/* 160:157 */     this.jButton3.setText("message.apply");
/* 161:158 */     this.jButton3.setEnabled(false);
/* 162:159 */     this.jButton3.addActionListener(new ActionListener()
/* 163:    */     {
/* 164:    */       public void actionPerformed(ActionEvent e)
/* 165:    */       {
/* 166:162 */         if (!SolarPowerTray.isLogin)
/* 167:    */         {
/* 168:163 */           new LoginJDialog(new Frame(), true);
/* 169:164 */           return;
/* 170:    */         }
/* 171:166 */         if (EmailSetting.this.jCheckBox1.isSelected())
/* 172:    */         {
/* 173:167 */           String smtp = EmailSetting.this.jTextField1.getText().trim();
/* 174:168 */           if ((smtp != null) && (!"".equalsIgnoreCase(smtp)))
/* 175:    */           {
/* 176:169 */             EmailSetting.this.jButton3.setEnabled(false);
/* 177:170 */             EmailSetting.this.applyExchangeCert(smtp);
/* 178:    */           }
/* 179:    */           else
/* 180:    */           {
/* 181:172 */             DisplayMessage.showErrorDialog("message.notnull");
/* 182:    */           }
/* 183:    */         }
/* 184:    */       }
/* 185:177 */     });
/* 186:178 */     this.jLabel5.setText("message.sendfrom[:]");
/* 187:    */     
/* 188:180 */     this.jLabel6.setText("message.username[:]");
/* 189:    */     
/* 190:182 */     this.jCheckBox2.setText("message.needAuth");
/* 191:    */     
/* 192:184 */     this.jLabel7.setText("message.password[:]");
/* 193:    */     
/* 194:186 */     this.jLabel8.setText("message.tipinfo");
/* 195:    */     
/* 196:188 */     this.jLabel9.setText("message.note[:]");
/* 197:    */     
/* 198:190 */     this.jButton4.setText("message.test");
/* 199:191 */     this.jButton4.addActionListener(new ActionListener()
/* 200:    */     {
/* 201:    */       public void actionPerformed(ActionEvent e)
/* 202:    */       {
/* 203:194 */         if (!SolarPowerTray.isLogin)
/* 204:    */         {
/* 205:195 */           new LoginJDialog(new Frame(), true);
/* 206:196 */           return;
/* 207:    */         }
/* 208:198 */         EmailSetting.this.jButton4.setEnabled(false);
/* 209:199 */         EmailSetting.this.testMailConfig();
/* 210:    */       }
/* 211:202 */     });
/* 212:203 */     this.jLabel10.setText("message.recvMailList[:]");
/* 213:    */     
/* 214:205 */     this.jScrollPane1.setViewportView(this.jList1);
/* 215:    */     
/* 216:207 */     this.jButton5.setText("message.add");
/* 217:208 */     this.jButton5.addActionListener(new ActionListener()
/* 218:    */     {
/* 219:    */       public void actionPerformed(ActionEvent e)
/* 220:    */       {
/* 221:211 */         EmailSetting.this.addReceiver();
/* 222:    */       }
/* 223:214 */     });
/* 224:215 */     this.jButton6.setText("message.del");
/* 225:216 */     this.jButton6.addActionListener(new ActionListener()
/* 226:    */     {
/* 227:    */       public void actionPerformed(ActionEvent e)
/* 228:    */       {
/* 229:219 */         EmailSetting.this.deleteReceiver();
/* 230:    */       }
/* 231:222 */     });
/* 232:223 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 233:224 */     this.jPanel1.setLayout(jPanel1Layout);
/* 234:225 */     jPanel1Layout.setHorizontalGroup(
/* 235:226 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 236:227 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 237:228 */       jPanel1Layout.createSequentialGroup().addGap(31, 31, 31)
/* 238:229 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 239:230 */       .addComponent(this.jLabel9)
/* 240:231 */       .addComponent(this.jLabel10)
/* 241:232 */       .addComponent(this.jLabel7)
/* 242:233 */       .addComponent(this.jLabel6)
/* 243:234 */       .addComponent(this.jLabel5)
/* 244:235 */       .addComponent(this.jLabel2))
/* 245:236 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 246:237 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 247:238 */       .addComponent(this.jButton4)
/* 248:239 */       .addComponent(this.jCheckBox2)
/* 249:240 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 250:241 */       .addComponent(this.jCheckBox1)
/* 251:242 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 252:243 */       .addComponent(this.jButton3))
/* 253:244 */       .addComponent(this.jLabel1)
/* 254:245 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 255:246 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 256:247 */       .addComponent(this.jTextField5, GroupLayout.Alignment.LEADING)
/* 257:248 */       .addComponent(this.jScrollPane1, GroupLayout.Alignment.LEADING, 0, 0, 32767)
/* 258:249 */       .addComponent(this.jPasswordField1, GroupLayout.Alignment.LEADING)
/* 259:250 */       .addComponent(this.jTextField4, GroupLayout.Alignment.LEADING)
/* 260:251 */       .addComponent(this.jTextField3, GroupLayout.Alignment.LEADING)
/* 261:252 */       .addComponent(this.jTextField1, GroupLayout.Alignment.LEADING, -1, 213, 32767))
/* 262:253 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 263:254 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 264:255 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 265:256 */       .addComponent(this.jLabel4)
/* 266:257 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 267:258 */       .addComponent(this.jTextField2, -2, 46, -2))
/* 268:259 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 269:260 */       .addComponent(this.jButton5)
/* 270:261 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 271:262 */       .addComponent(this.jButton6))))
/* 272:263 */       .addComponent(this.jLabel8))
/* 273:264 */       .addContainerGap(51, 32767)));
/* 274:    */     
/* 275:266 */     jPanel1Layout.setVerticalGroup(
/* 276:267 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 277:268 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 278:269 */       .addGap(18, 18, 18)
/* 279:270 */       .addComponent(this.jLabel1)
/* 280:271 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 281:272 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 282:273 */       .addComponent(this.jLabel2)
/* 283:274 */       .addComponent(this.jTextField1, -2, -1, -2)
/* 284:275 */       .addComponent(this.jLabel4)
/* 285:276 */       .addComponent(this.jTextField2, -2, -1, -2))
/* 286:277 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 287:278 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 288:279 */       .addComponent(this.jCheckBox1)
/* 289:280 */       .addComponent(this.jButton3))
/* 290:281 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 291:282 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 292:283 */       .addComponent(this.jLabel5)
/* 293:284 */       .addComponent(this.jTextField3, -2, -1, -2))
/* 294:285 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 295:286 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 296:287 */       .addComponent(this.jLabel6)
/* 297:288 */       .addComponent(this.jTextField4, -2, -1, -2))
/* 298:289 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 299:290 */       .addComponent(this.jCheckBox2)
/* 300:291 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 301:292 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 302:293 */       .addComponent(this.jLabel7)
/* 303:294 */       .addComponent(this.jPasswordField1, -2, -1, -2))
/* 304:295 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 305:296 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 306:297 */       .addComponent(this.jLabel10)
/* 307:298 */       .addComponent(this.jScrollPane1, -2, 95, -2))
/* 308:299 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 309:300 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 310:301 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 311:302 */       .addComponent(this.jTextField5, -2, -1, -2)
/* 312:303 */       .addComponent(this.jButton5)
/* 313:304 */       .addComponent(this.jButton6))
/* 314:305 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 315:306 */       .addGap(36, 36, 36)
/* 316:307 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 317:308 */       .addComponent(this.jLabel9)
/* 318:309 */       .addComponent(this.jLabel8))))
/* 319:310 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 320:311 */       .addComponent(this.jButton4)
/* 321:312 */       .addContainerGap(-1, 32767)));
/* 322:    */     
/* 323:    */ 
/* 324:315 */     getContentPane().add(this.jPanel1, "Center");
/* 325:    */     
/* 326:317 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 327:    */     
/* 328:319 */     this.jButton1.setText("message.apply");
/* 329:320 */     this.jButton1.addActionListener(new ActionListener()
/* 330:    */     {
/* 331:    */       public void actionPerformed(ActionEvent e)
/* 332:    */       {
/* 333:323 */         if (!SolarPowerTray.isLogin)
/* 334:    */         {
/* 335:324 */           new LoginJDialog(new Frame(), true);
/* 336:325 */           return;
/* 337:    */         }
/* 338:327 */         EmailSetting.this.applySetting();
/* 339:    */       }
/* 340:330 */     });
/* 341:331 */     this.jButton2.setText("message.close");
/* 342:332 */     this.jButton2.addActionListener(new ActionListener()
/* 343:    */     {
/* 344:    */       public void actionPerformed(ActionEvent e)
/* 345:    */       {
/* 346:335 */         EmailSetting.this.dispose();
/* 347:    */       }
/* 348:338 */     });
/* 349:339 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 350:340 */     this.jPanel2.setLayout(jPanel2Layout);
/* 351:341 */     jPanel2Layout.setHorizontalGroup(
/* 352:342 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 353:343 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 354:344 */       jPanel2Layout.createSequentialGroup().addContainerGap(396, 32767)
/* 355:345 */       .addComponent(this.jButton1)
/* 356:346 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 357:347 */       .addComponent(this.jButton2)
/* 358:348 */       .addGap(12, 12, 12)));
/* 359:    */     
/* 360:350 */     jPanel2Layout.setVerticalGroup(
/* 361:351 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 362:352 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 363:353 */       jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767)
/* 364:354 */       .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 365:355 */       .addComponent(this.jButton2)
/* 366:356 */       .addComponent(this.jButton1))
/* 367:357 */       .addContainerGap()));
/* 368:    */     
/* 369:    */ 
/* 370:360 */     getContentPane().add(this.jPanel2, "Last");
/* 371:    */     
/* 372:362 */     pack();
/* 373:    */   }
/* 374:    */   
/* 375:    */   public void addReceiver()
/* 376:    */   {
/* 377:366 */     String receiver = this.jTextField5.getText().trim();
/* 378:367 */     if (VolUtil.checkNull(receiver))
/* 379:    */     {
/* 380:368 */       DisplayMessage.showErrorDialog("message.notnull");
/* 381:369 */       return;
/* 382:    */     }
/* 383:371 */     if (!VolUtil.checkEmail(receiver))
/* 384:    */     {
/* 385:372 */       DisplayMessage.showErrorDialog("message.emailnotcorrect");
/* 386:373 */       return;
/* 387:    */     }
/* 388:375 */     if (this.listModel.contains(receiver))
/* 389:    */     {
/* 390:376 */       DisplayMessage.showErrorDialog("message.emailexist");
/* 391:377 */       return;
/* 392:    */     }
/* 393:379 */     this.listModel.addElement(receiver);
/* 394:    */   }
/* 395:    */   
/* 396:    */   public void deleteReceiver()
/* 397:    */   {
/* 398:383 */     int index = this.jList1.getSelectedIndex();
/* 399:384 */     if (index == -1)
/* 400:    */     {
/* 401:385 */       DisplayMessage.showErrorDialog("message.pleaseselect");
/* 402:386 */       return;
/* 403:    */     }
/* 404:388 */     this.listModel.remove(index);
/* 405:    */   }
/* 406:    */   
/* 407:    */   public void applySetting()
/* 408:    */   {
/* 409:392 */     String smtp = this.jTextField1.getText().trim();
/* 410:393 */     int port = VolUtil.parseInt(this.jTextField2.getText().trim());
/* 411:394 */     String sender = this.jTextField3.getText().trim();
/* 412:395 */     String account = this.jTextField4.getText().trim();
/* 413:396 */     boolean needAuth = this.jCheckBox2.isSelected();
/* 414:397 */     String password = new String(this.jPasswordField1.getPassword()).trim();
/* 415:398 */     StringBuffer recievers = new StringBuffer();
/* 416:399 */     for (int i = 0; i < this.listModel.size(); i++) {
/* 417:400 */       if (i < 1) {
/* 418:401 */         recievers.append(this.listModel.get(i));
/* 419:    */       } else {
/* 420:403 */         recievers.append(";").append(this.listModel.get(i));
/* 421:    */       }
/* 422:    */     }
/* 423:406 */     this.emailConfig.setSmtp(smtp);
/* 424:407 */     this.emailConfig.setPort(port);
/* 425:408 */     this.emailConfig.setSender(sender);
/* 426:409 */     this.emailConfig.setAccount(account);
/* 427:410 */     this.emailConfig.setNeedAuth(Boolean.valueOf(needAuth));
/* 428:411 */     this.emailConfig.setPassword(password);
/* 429:412 */     this.emailConfig.setRecievers(recievers.toString());
/* 430:    */     try
/* 431:    */     {
/* 432:414 */       ConfigureTools.updateProperties(this.emailConfig);
/* 433:415 */       ConfigureTools.wrapProperties(GlobalVariables.emailConfig);
/* 434:416 */       dispose();
/* 435:    */     }
/* 436:    */     catch (Exception e)
/* 437:    */     {
/* 438:418 */       DisplayMessage.showErrorDialog("message.operationfailure");
/* 439:    */     }
/* 440:    */   }
/* 441:    */   
/* 442:    */   public void applyExchangeCert(String domain)
/* 443:    */   {
/* 444:    */     try
/* 445:    */     {
/* 446:424 */       InstallCert.insertCert(domain);
/* 447:425 */       DisplayMessage.showInfoDialog("message.OperationSuccess");
/* 448:    */     }
/* 449:    */     catch (Exception e)
/* 450:    */     {
/* 451:427 */       DisplayMessage.showErrorDialog("message.operationfailure");
/* 452:    */     }
/* 453:    */     finally
/* 454:    */     {
/* 455:429 */       this.jButton3.setEnabled(true);
/* 456:    */     }
/* 457:    */   }
/* 458:    */   
/* 459:    */   public void testMailConfig()
/* 460:    */   {
/* 461:434 */     String smtp = this.jTextField1.getText().trim();
/* 462:435 */     int port = VolUtil.parseInt(this.jTextField2.getText().trim());
/* 463:436 */     String sender = this.jTextField3.getText().trim();
/* 464:437 */     String account = this.jTextField4.getText().trim();
/* 465:438 */     boolean needAuth = this.jCheckBox2.isSelected();
/* 466:439 */     String password = new String(this.jPasswordField1.getPassword());
/* 467:440 */     EmailConfig info = new EmailConfig();
/* 468:441 */     info.setSmtp(smtp);
/* 469:442 */     info.setPort(port);
/* 470:443 */     info.setSender(sender);
/* 471:444 */     info.setAccount(account);
/* 472:445 */     info.setNeedAuth(Boolean.valueOf(needAuth));
/* 473:446 */     info.setPassword(password);
/* 474:447 */     String to = "";
/* 475:448 */     to = this.listModel.elementAt(0).toString();
/* 476:449 */     EmailSendUtil sendmail = new EmailSendUtil(info);
/* 477:450 */     String subject = GlobalVariables.customerConfig.getCustomerName() + " test mail";
/* 478:451 */     boolean flag = sendmail.sendMail(to, subject, info.getAccount());
/* 479:452 */     if (flag) {
/* 480:453 */       DisplayMessage.showInfoDialog("message.testsuccess");
/* 481:    */     } else {
/* 482:455 */       DisplayMessage.showErrorDialog("message.testfailure");
/* 483:    */     }
/* 484:457 */     this.jButton4.setEnabled(true);
/* 485:    */   }
/* 486:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.EmailSetting
 * JD-Core Version:    0.7.0.1
 */