/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.UpgradeConfig;
/*   5:    */ import cn.com.voltronic.solar.constants.Constants;
/*   6:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   7:    */ import cn.com.voltronic.solar.util.MyMD5;
/*   8:    */ import cn.com.voltronic.solar.util.VolUtil;
/*   9:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  10:    */ import cn.com.voltronic.solar.view.component.AACheckBox;
/*  11:    */ import cn.com.voltronic.solar.view.component.AAFrame;
/*  12:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  13:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  14:    */ import cn.com.voltronic.solar.view.component.I18NListener;
/*  15:    */ import com.sun.org.apache.xml.internal.security.utils.Base64;
/*  16:    */ import java.awt.Color;
/*  17:    */ import java.awt.Container;
/*  18:    */ import java.awt.event.ActionEvent;
/*  19:    */ import java.awt.event.ActionListener;
/*  20:    */ import java.awt.event.FocusAdapter;
/*  21:    */ import java.awt.event.FocusEvent;
/*  22:    */ import java.io.File;
/*  23:    */ import java.io.FileNotFoundException;
/*  24:    */ import java.io.IOException;
/*  25:    */ import java.io.InputStream;
/*  26:    */ import java.io.PrintStream;
/*  27:    */ import java.net.HttpURLConnection;
/*  28:    */ import java.net.URL;
/*  29:    */ import java.util.Properties;
/*  30:    */ import javax.swing.BorderFactory;
/*  31:    */ import javax.swing.GroupLayout;
/*  32:    */ import javax.swing.GroupLayout.Alignment;
/*  33:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  34:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  35:    */ import javax.swing.JFileChooser;
/*  36:    */ import javax.swing.JPanel;
/*  37:    */ import javax.swing.JPasswordField;
/*  38:    */ import javax.swing.JTextField;
/*  39:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  40:    */ 
/*  41:    */ public class ConfigFrame
/*  42:    */   extends AAFrame
/*  43:    */ {
/*  44:    */   private static final long serialVersionUID = 7122283504135397965L;
/*  45:    */   private AAButton applyBut;
/*  46:    */   private AAButton browserButton2;
/*  47:    */   private AAButton cancelBut;
/*  48:    */   private AALabel downloadLabel;
/*  49:    */   private JTextField downloadTextField;
/*  50:    */   private AALabel hostIPLabel;
/*  51:    */   private JTextField hostIPTextField;
/*  52:    */   private AALabel inputError4;
/*  53:    */   private AALabel inputError5;
/*  54:    */   private AALabel inputError8;
/*  55:    */   private AAButton jButton1;
/*  56:    */   private AACheckBox jCheckBox1;
/*  57:    */   private AACheckBox jCheckBox2;
/*  58:    */   private AACheckBox jCheckBox3;
/*  59:    */   private AALabel jLabel5;
/*  60:    */   private AALabel jLabel6;
/*  61:    */   private JPanel jPanel1;
/*  62:    */   private JPanel jPanel2;
/*  63:    */   private JPanel jPanel3;
/*  64:    */   private JPasswordField jPasswordField1;
/*  65:    */   private JTextField jTextField3;
/*  66:    */   private AALabel portLabel;
/*  67:    */   private JTextField portTextField;
/*  68:    */   private JTextField saveTextField;
/*  69:    */   private AALabel savepathLabel;
/*  70:    */   private JFileChooser fDialog;
/*  71: 75 */   private static ConfigFrame configview = null;
/*  72: 76 */   private Color color = new Color(255, 0, 0);
/*  73:    */   
/*  74:    */   public ConfigFrame()
/*  75:    */   {
/*  76: 79 */     initComponents();
/*  77: 80 */     setTitle("message.pvConfig");
/*  78: 81 */     setIconImage(Constants.CONNECTEDIMG);
/*  79: 82 */     setLocationRelativeTo(null);
/*  80: 83 */     setVisible(true);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static ConfigFrame getInstance()
/*  84:    */   {
/*  85: 87 */     if (configview == null) {
/*  86: 88 */       configview = new ConfigFrame();
/*  87:    */     }
/*  88: 90 */     configview.setInitValue();
/*  89: 91 */     return configview;
/*  90:    */   }
/*  91:    */   
/*  92:    */   private void setInitValue()
/*  93:    */   {
/*  94: 95 */     String downloadDir = GlobalVariables.upgradeConfig.getFromURL();
/*  95: 96 */     String saveDir = GlobalVariables.upgradeConfig.getSavePath();
/*  96: 97 */     boolean useProxy = GlobalVariables.upgradeConfig.isUseProxy();
/*  97: 98 */     boolean autoDetect = GlobalVariables.upgradeConfig.isAutoUpgrade();
/*  98: 99 */     boolean useAuth = GlobalVariables.upgradeConfig.isUseAuth();
/*  99:100 */     String username = GlobalVariables.upgradeConfig.getAuthName();
/* 100:101 */     String password = GlobalVariables.upgradeConfig.getAuthPassword();
/* 101:102 */     String proxy = GlobalVariables.upgradeConfig.getProxyIp();
/* 102:103 */     String port = GlobalVariables.upgradeConfig.getProxyPort();
/* 103:    */     
/* 104:105 */     this.downloadTextField.setText(downloadDir);
/* 105:106 */     this.hostIPTextField.setText(proxy);
/* 106:107 */     this.portTextField.setText(port);
/* 107:108 */     this.jTextField3.setText(username);
/* 108:109 */     this.jPasswordField1.setText(MyMD5.restore(password));
/* 109:111 */     if ("".equalsIgnoreCase(saveDir))
/* 110:    */     {
/* 111:112 */       saveDir = 
/* 112:113 */         System.getProperty("user.dir") + File.separator + "UpgradeFiles";
/* 113:    */       try
/* 114:    */       {
/* 115:115 */         GlobalVariables.upgradeConfig.setSavePath(saveDir);
/* 116:116 */         ConfigureTools.updateProperties(GlobalVariables.upgradeConfig);
/* 117:117 */         ConfigureTools.wrapProperties(GlobalVariables.upgradeConfig);
/* 118:    */       }
/* 119:    */       catch (Exception localException) {}
/* 120:    */     }
/* 121:121 */     this.saveTextField.setText(saveDir);
/* 122:123 */     if (autoDetect) {
/* 123:124 */       this.jCheckBox2.setSelected(true);
/* 124:    */     } else {
/* 125:126 */       this.jCheckBox2.setSelected(false);
/* 126:    */     }
/* 127:129 */     if (useProxy)
/* 128:    */     {
/* 129:130 */       this.jCheckBox1.setSelected(true);
/* 130:131 */       this.hostIPLabel.setEnabled(true);
/* 131:132 */       this.portLabel.setEnabled(true);
/* 132:133 */       this.hostIPTextField.setEnabled(true);
/* 133:134 */       this.portTextField.setEnabled(true);
/* 134:135 */       if (useAuth)
/* 135:    */       {
/* 136:136 */         this.jCheckBox3.setSelected(true);
/* 137:137 */         this.jLabel5.setEnabled(true);
/* 138:138 */         this.jLabel6.setEnabled(true);
/* 139:139 */         this.jTextField3.setEnabled(true);
/* 140:140 */         this.jPasswordField1.setEnabled(true);
/* 141:    */       }
/* 142:    */       else
/* 143:    */       {
/* 144:142 */         this.jCheckBox3.setSelected(false);
/* 145:143 */         this.jLabel5.setEnabled(false);
/* 146:144 */         this.jLabel6.setEnabled(false);
/* 147:145 */         this.jTextField3.setEnabled(false);
/* 148:146 */         this.jPasswordField1.setEnabled(false);
/* 149:    */       }
/* 150:    */     }
/* 151:    */     else
/* 152:    */     {
/* 153:149 */       this.jCheckBox1.setSelected(false);
/* 154:150 */       this.hostIPLabel.setEnabled(false);
/* 155:151 */       this.portLabel.setEnabled(false);
/* 156:152 */       this.hostIPTextField.setEnabled(false);
/* 157:153 */       this.portTextField.setEnabled(false);
/* 158:154 */       this.jCheckBox3.setEnabled(false);
/* 159:155 */       this.jLabel5.setEnabled(false);
/* 160:156 */       this.jLabel6.setEnabled(false);
/* 161:157 */       this.jTextField3.setEnabled(false);
/* 162:158 */       this.jPasswordField1.setEnabled(false);
/* 163:    */     }
/* 164:    */   }
/* 165:    */   
/* 166:    */   private void initComponents()
/* 167:    */   {
/* 168:164 */     this.jPanel3 = new JPanel();
/* 169:165 */     this.jCheckBox1 = new AACheckBox();
/* 170:166 */     this.downloadLabel = new AALabel();
/* 171:167 */     this.savepathLabel = new AALabel();
/* 172:168 */     this.downloadTextField = new JTextField();
/* 173:169 */     this.downloadTextField.setBackground(I18NListener.bgColor);
/* 174:170 */     this.downloadTextField.setForeground(I18NListener.fontColor);
/* 175:171 */     this.saveTextField = new JTextField();
/* 176:172 */     this.saveTextField.setEditable(false);
/* 177:173 */     this.saveTextField.setBackground(I18NListener.bgColor);
/* 178:174 */     this.saveTextField.setForeground(I18NListener.fontColor);
/* 179:175 */     this.browserButton2 = new AAButton();
/* 180:176 */     this.jCheckBox2 = new AACheckBox();
/* 181:177 */     this.jPanel1 = new JPanel();
/* 182:178 */     this.hostIPLabel = new AALabel();
/* 183:179 */     this.hostIPTextField = new JTextField();
/* 184:180 */     this.hostIPTextField.setBackground(I18NListener.bgColor);
/* 185:181 */     this.hostIPTextField.setForeground(I18NListener.fontColor);
/* 186:182 */     this.portTextField = new JTextField();
/* 187:183 */     this.portTextField.setBackground(I18NListener.bgColor);
/* 188:184 */     this.portTextField.setForeground(I18NListener.fontColor);
/* 189:185 */     this.portLabel = new AALabel();
/* 190:186 */     this.jCheckBox3 = new AACheckBox();
/* 191:187 */     this.jLabel5 = new AALabel();
/* 192:188 */     this.jLabel6 = new AALabel();
/* 193:189 */     this.jTextField3 = new JTextField();
/* 194:190 */     this.jTextField3.setBackground(I18NListener.bgColor);
/* 195:191 */     this.jTextField3.setForeground(I18NListener.fontColor);
/* 196:192 */     this.jPasswordField1 = new JPasswordField();
/* 197:193 */     this.jPasswordField1.setBackground(I18NListener.bgColor);
/* 198:194 */     this.jPasswordField1.setForeground(I18NListener.fontColor);
/* 199:195 */     this.inputError5 = new AALabel();
/* 200:196 */     this.inputError4 = new AALabel();
/* 201:197 */     this.jButton1 = new AAButton();
/* 202:198 */     this.inputError8 = new AALabel();
/* 203:199 */     this.jPanel2 = new JPanel();
/* 204:200 */     this.cancelBut = new AAButton();
/* 205:201 */     this.applyBut = new AAButton();
/* 206:    */     
/* 207:203 */     setDefaultCloseOperation(2);
/* 208:    */     
/* 209:205 */     this.jPanel3.setBorder(BorderFactory.createEtchedBorder());
/* 210:    */     
/* 211:207 */     this.downloadLabel.setText("message.downloadpath");
/* 212:208 */     this.savepathLabel.setText("message.savepath");
/* 213:209 */     this.browserButton2.setText("message.browser");
/* 214:210 */     this.jButton1.setText("message.connectiontest");
/* 215:    */     
/* 216:212 */     this.jButton1.addActionListener(new ActionListener()
/* 217:    */     {
/* 218:    */       public void actionPerformed(ActionEvent e)
/* 219:    */       {
/* 220:215 */         ConfigFrame.this.testConnection();
/* 221:    */       }
/* 222:217 */     });
/* 223:218 */     this.browserButton2.addActionListener(new ActionListener()
/* 224:    */     {
/* 225:    */       public void actionPerformed(ActionEvent e)
/* 226:    */       {
/* 227:221 */         int result = ConfigFrame.this.getSaveDialog();
/* 228:222 */         if (result == 0)
/* 229:    */         {
/* 230:223 */           String filepathStr = ConfigFrame.this.fDialog.getSelectedFile().getPath();
/* 231:224 */           ConfigFrame.this.saveTextField.setText(filepathStr);
/* 232:    */         }
/* 233:    */       }
/* 234:228 */     });
/* 235:229 */     this.jCheckBox2.setText("message.upgradeAutoDetect");
/* 236:230 */     this.jCheckBox1.setText("message.useproxy");
/* 237:    */     
/* 238:232 */     this.jCheckBox1.addActionListener(new ActionListener()
/* 239:    */     {
/* 240:    */       public void actionPerformed(ActionEvent e)
/* 241:    */       {
/* 242:234 */         if (ConfigFrame.this.jCheckBox1.isSelected())
/* 243:    */         {
/* 244:235 */           ConfigFrame.this.hostIPLabel.setEnabled(true);
/* 245:236 */           ConfigFrame.this.portLabel.setEnabled(true);
/* 246:237 */           ConfigFrame.this.hostIPTextField.setEnabled(true);
/* 247:238 */           ConfigFrame.this.portTextField.setEnabled(true);
/* 248:239 */           ConfigFrame.this.jCheckBox3.setEnabled(true);
/* 249:240 */           if (ConfigFrame.this.jCheckBox3.isSelected())
/* 250:    */           {
/* 251:241 */             ConfigFrame.this.jLabel5.setEnabled(true);
/* 252:242 */             ConfigFrame.this.jLabel6.setEnabled(true);
/* 253:243 */             ConfigFrame.this.jTextField3.setEnabled(true);
/* 254:244 */             ConfigFrame.this.jPasswordField1.setEnabled(true);
/* 255:    */           }
/* 256:    */         }
/* 257:    */         else
/* 258:    */         {
/* 259:247 */           ConfigFrame.this.hostIPLabel.setEnabled(false);
/* 260:248 */           ConfigFrame.this.portLabel.setEnabled(false);
/* 261:249 */           ConfigFrame.this.hostIPTextField.setEnabled(false);
/* 262:250 */           ConfigFrame.this.portTextField.setEnabled(false);
/* 263:251 */           ConfigFrame.this.jCheckBox3.setEnabled(false);
/* 264:252 */           ConfigFrame.this.jLabel5.setEnabled(false);
/* 265:253 */           ConfigFrame.this.jLabel6.setEnabled(false);
/* 266:254 */           ConfigFrame.this.jTextField3.setEnabled(false);
/* 267:255 */           ConfigFrame.this.jPasswordField1.setEnabled(false);
/* 268:    */         }
/* 269:    */       }
/* 270:259 */     });
/* 271:260 */     this.jCheckBox3.addActionListener(new ActionListener()
/* 272:    */     {
/* 273:    */       public void actionPerformed(ActionEvent e)
/* 274:    */       {
/* 275:262 */         if (ConfigFrame.this.jCheckBox3.isSelected())
/* 276:    */         {
/* 277:263 */           ConfigFrame.this.jLabel5.setEnabled(true);
/* 278:264 */           ConfigFrame.this.jLabel6.setEnabled(true);
/* 279:265 */           ConfigFrame.this.jTextField3.setEnabled(true);
/* 280:266 */           ConfigFrame.this.jPasswordField1.setEnabled(true);
/* 281:    */         }
/* 282:    */         else
/* 283:    */         {
/* 284:268 */           ConfigFrame.this.jLabel5.setEnabled(false);
/* 285:269 */           ConfigFrame.this.jLabel6.setEnabled(false);
/* 286:270 */           ConfigFrame.this.jTextField3.setEnabled(false);
/* 287:271 */           ConfigFrame.this.jPasswordField1.setEnabled(false);
/* 288:    */         }
/* 289:    */       }
/* 290:275 */     });
/* 291:276 */     this.hostIPTextField.addFocusListener(new FocusAdapter()
/* 292:    */     {
/* 293:    */       public void focusLost(FocusEvent evt)
/* 294:    */       {
/* 295:278 */         boolean success = VolUtil.checkIP(ConfigFrame.this.hostIPTextField.getText().trim());
/* 296:279 */         if (!success)
/* 297:    */         {
/* 298:280 */           ConfigFrame.this.inputError4.setText("message.inputerror");
/* 299:281 */           ConfigFrame.this.hostIPTextField.requestFocus(true);
/* 300:    */         }
/* 301:    */         else
/* 302:    */         {
/* 303:283 */           ConfigFrame.this.inputError4.setText(" ");
/* 304:    */         }
/* 305:    */       }
/* 306:287 */     });
/* 307:288 */     this.portTextField.addFocusListener(new FocusAdapter()
/* 308:    */     {
/* 309:    */       public void focusLost(FocusEvent evt)
/* 310:    */       {
/* 311:290 */         boolean success = ConfigFrame.isNumeric(ConfigFrame.this.portTextField.getText().trim());
/* 312:291 */         if (!success)
/* 313:    */         {
/* 314:292 */           ConfigFrame.this.inputError5.setText("message.inputerror");
/* 315:293 */           ConfigFrame.this.portTextField.requestFocus(true);
/* 316:    */         }
/* 317:    */         else
/* 318:    */         {
/* 319:295 */           ConfigFrame.this.inputError5.setText(" ");
/* 320:    */         }
/* 321:    */       }
/* 322:299 */     });
/* 323:300 */     this.hostIPLabel.setText("IP");
/* 324:    */     
/* 325:302 */     this.portLabel.setText("message.proxyport");
/* 326:    */     
/* 327:304 */     this.inputError4.setForeground(this.color);
/* 328:305 */     this.inputError4.setText(" ");
/* 329:    */     
/* 330:307 */     this.inputError5.setForeground(this.color);
/* 331:308 */     this.inputError5.setText(" ");
/* 332:    */     
/* 333:310 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 334:    */     
/* 335:312 */     this.jCheckBox3.setText("message.useAuth");
/* 336:    */     
/* 337:314 */     this.jLabel5.setText("message.username");
/* 338:    */     
/* 339:316 */     this.jLabel6.setText("message.password");
/* 340:    */     
/* 341:318 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 342:    */     
/* 343:320 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 344:321 */     this.jPanel1.setLayout(jPanel1Layout);
/* 345:322 */     jPanel1Layout.setHorizontalGroup(
/* 346:323 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 347:324 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 348:325 */       .addContainerGap()
/* 349:326 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 350:327 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 351:328 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 352:329 */       .addComponent(this.portLabel)
/* 353:330 */       .addComponent(this.hostIPLabel))
/* 354:331 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 355:332 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 356:333 */       .addComponent(this.portTextField, -2, 150, -2)
/* 357:334 */       .addComponent(this.hostIPTextField, -2, 150, -2))
/* 358:335 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 359:336 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 360:337 */       .addComponent(this.inputError5)
/* 361:338 */       .addComponent(this.inputError4)))
/* 362:339 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 363:340 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 364:341 */       .addComponent(this.jCheckBox3)
/* 365:342 */       .addGap(210, 210, 210))
/* 366:343 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 367:344 */       jPanel1Layout.createSequentialGroup()
/* 368:    */       
/* 369:346 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jLabel6).addComponent(this.jLabel5))
/* 370:347 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 371:348 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 372:349 */       .addComponent(this.jPasswordField1, -2, 150, -2)
/* 373:350 */       .addComponent(this.jTextField3, -2, 150, -2))
/* 374:351 */       .addGap(61, 61, 61))))
/* 375:352 */       .addContainerGap(148, 32767)));
/* 376:    */     
/* 377:354 */     jPanel1Layout.setVerticalGroup(
/* 378:355 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 379:356 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 380:357 */       .addContainerGap()
/* 381:358 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 382:359 */       .addComponent(this.hostIPLabel)
/* 383:360 */       .addComponent(this.hostIPTextField, -2, -1, -2)
/* 384:361 */       .addComponent(this.inputError4))
/* 385:362 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 386:363 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 387:364 */       .addComponent(this.portLabel)
/* 388:365 */       .addComponent(this.portTextField, -2, -1, -2)
/* 389:366 */       .addComponent(this.inputError5))
/* 390:367 */       .addGap(6, 6, 6)
/* 391:368 */       .addComponent(this.jCheckBox3)
/* 392:369 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 393:370 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 394:371 */       .addComponent(this.jLabel5)
/* 395:372 */       .addComponent(this.jTextField3, -2, -1, -2))
/* 396:373 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 397:374 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 398:375 */       .addComponent(this.jLabel6)
/* 399:376 */       .addComponent(this.jPasswordField1, -2, -1, -2))
/* 400:377 */       .addContainerGap(23, 32767)));
/* 401:    */     
/* 402:    */ 
/* 403:380 */     this.inputError8.setForeground(this.color);
/* 404:381 */     this.inputError8.setVisible(false);
/* 405:382 */     this.inputError8.setText("message.inputerror");
/* 406:    */     
/* 407:384 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 408:385 */     this.jPanel3.setLayout(jPanel3Layout);
/* 409:386 */     jPanel3Layout.setHorizontalGroup(
/* 410:387 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 411:388 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 412:389 */       jPanel3Layout.createSequentialGroup().addGap(32, 32, 32)
/* 413:390 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 414:391 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 415:392 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 416:393 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 417:394 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 418:395 */       .addComponent(this.savepathLabel)
/* 419:396 */       .addComponent(this.downloadLabel))
/* 420:397 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 421:398 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 422:399 */       .addComponent(this.saveTextField)
/* 423:400 */       .addComponent(this.downloadTextField, -1, 243, 32767))
/* 424:401 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 425:402 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 426:403 */       .addComponent(this.browserButton2)
/* 427:404 */       .addComponent(this.inputError8)))
/* 428:405 */       .addComponent(this.jCheckBox2)
/* 429:406 */       .addComponent(this.jCheckBox1))
/* 430:407 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, 32767))
/* 431:408 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 432:409 */       .addGap(21, 21, 21)
/* 433:410 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 434:411 */       .addComponent(this.jButton1)
/* 435:412 */       .addComponent(this.jPanel1, -1, -1, 32767))))
/* 436:413 */       .addContainerGap()));
/* 437:    */     
/* 438:415 */     jPanel3Layout.setVerticalGroup(
/* 439:416 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 440:417 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 441:418 */       .addGap(26, 26, 26)
/* 442:419 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 443:420 */       .addComponent(this.downloadLabel)
/* 444:421 */       .addComponent(this.downloadTextField, -2, -1, -2)
/* 445:422 */       .addComponent(this.inputError8))
/* 446:423 */       .addGap(12, 12, 12)
/* 447:424 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 448:425 */       .addComponent(this.savepathLabel)
/* 449:426 */       .addComponent(this.browserButton2)
/* 450:427 */       .addComponent(this.saveTextField, -2, -1, -2))
/* 451:428 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 452:429 */       .addComponent(this.jCheckBox2)
/* 453:430 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 454:431 */       .addComponent(this.jCheckBox1)
/* 455:432 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 456:433 */       .addComponent(this.jPanel1, -2, -1, -2)
/* 457:434 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 458:435 */       .addComponent(this.jButton1)
/* 459:436 */       .addContainerGap(32, 32767)));
/* 460:    */     
/* 461:    */ 
/* 462:439 */     getContentPane().add(this.jPanel3, "Center");
/* 463:    */     
/* 464:441 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 465:    */     
/* 466:443 */     this.cancelBut.setText("message.close");
/* 467:444 */     this.cancelBut.addActionListener(new ActionListener()
/* 468:    */     {
/* 469:    */       public void actionPerformed(ActionEvent evt)
/* 470:    */       {
/* 471:446 */         ConfigFrame.this.cancelButActionPerformed(evt);
/* 472:    */       }
/* 473:449 */     });
/* 474:450 */     this.applyBut.setText("message.apply");
/* 475:451 */     this.applyBut.addActionListener(new ActionListener()
/* 476:    */     {
/* 477:    */       public void actionPerformed(ActionEvent evt)
/* 478:    */       {
/* 479:453 */         ConfigFrame.this.applyButActionPerformed(evt);
/* 480:    */       }
/* 481:456 */     });
/* 482:457 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 483:458 */     this.jPanel2.setLayout(jPanel2Layout);
/* 484:459 */     jPanel2Layout.setHorizontalGroup(
/* 485:460 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 486:461 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 487:462 */       jPanel2Layout.createSequentialGroup().addContainerGap(371, 32767)
/* 488:463 */       .addComponent(this.applyBut)
/* 489:464 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 490:465 */       .addComponent(this.cancelBut)
/* 491:466 */       .addGap(13, 13, 13)));
/* 492:    */     
/* 493:468 */     jPanel2Layout.setVerticalGroup(
/* 494:469 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 495:470 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 496:471 */       jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767)
/* 497:472 */       .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 498:473 */       .addComponent(this.cancelBut)
/* 499:474 */       .addComponent(this.applyBut))
/* 500:475 */       .addContainerGap()));
/* 501:    */     
/* 502:    */ 
/* 503:478 */     getContentPane().add(this.jPanel2, "South");
/* 504:    */     
/* 505:480 */     pack();
/* 506:    */   }
/* 507:    */   
/* 508:    */   private void cancelButActionPerformed(ActionEvent evt)
/* 509:    */   {
/* 510:484 */     dispose();
/* 511:    */   }
/* 512:    */   
/* 513:    */   public void dispose()
/* 514:    */   {
/* 515:488 */     super.dispose();
/* 516:489 */     configview = null;
/* 517:    */   }
/* 518:    */   
/* 519:    */   public int getOpenDialog()
/* 520:    */   {
/* 521:493 */     this.fDialog = new JFileChooser();
/* 522:494 */     this.fDialog.setFileSelectionMode(1);
/* 523:495 */     return this.fDialog.showOpenDialog(this);
/* 524:    */   }
/* 525:    */   
/* 526:    */   private int getSaveDialog()
/* 527:    */   {
/* 528:499 */     this.fDialog = new JFileChooser();
/* 529:500 */     this.fDialog.setFileSelectionMode(1);
/* 530:501 */     return this.fDialog.showSaveDialog(this);
/* 531:    */   }
/* 532:    */   
/* 533:    */   private void applyButActionPerformed(ActionEvent evt)
/* 534:    */   {
/* 535:    */     try
/* 536:    */     {
/* 537:506 */       String hostIp = this.hostIPTextField.getText().trim();
/* 538:507 */       String hostPort = this.portTextField.getText().trim();
/* 539:    */       
/* 540:509 */       String username = this.jTextField3.getText().trim();
/* 541:510 */       String password = new String(this.jPasswordField1.getPassword()).trim();
/* 542:    */       
/* 543:    */ 
/* 544:513 */       String downloadText = this.downloadTextField.getText().trim();
/* 545:514 */       String saveText = this.saveTextField.getText().trim();
/* 546:516 */       if (this.jCheckBox1.isSelected())
/* 547:    */       {
/* 548:517 */         if ((!VolUtil.checkIP(hostIp)) || (!isNumeric(hostPort)) || (isNull(hostIp)) || 
/* 549:518 */           (isNull(hostPort)))
/* 550:    */         {
/* 551:519 */           DisplayMessage.showErrorDialog("message.inputerror");
/* 552:520 */           return;
/* 553:    */         }
/* 554:522 */         if ((this.jCheckBox3.isSelected()) && (
/* 555:523 */           (isNull(username)) || (isNull(password))))
/* 556:    */         {
/* 557:524 */           DisplayMessage.showErrorDialog("message.inputerror");
/* 558:525 */           return;
/* 559:    */         }
/* 560:    */       }
/* 561:530 */       GlobalVariables.upgradeConfig.setFromURL(downloadText);
/* 562:531 */       GlobalVariables.upgradeConfig.setSavePath(saveText);
/* 563:533 */       if (this.jCheckBox2.isSelected()) {
/* 564:534 */         GlobalVariables.upgradeConfig.setAutoUpgrade(true);
/* 565:    */       } else {
/* 566:536 */         GlobalVariables.upgradeConfig.setAutoUpgrade(false);
/* 567:    */       }
/* 568:539 */       if (this.jCheckBox1.isSelected())
/* 569:    */       {
/* 570:540 */         GlobalVariables.upgradeConfig.setUseProxy(true);
/* 571:541 */         if (this.jCheckBox3.isSelected()) {
/* 572:542 */           GlobalVariables.upgradeConfig.setUseAuth(true);
/* 573:    */         } else {
/* 574:544 */           GlobalVariables.upgradeConfig.setUseAuth(false);
/* 575:    */         }
/* 576:    */       }
/* 577:    */       else
/* 578:    */       {
/* 579:547 */         GlobalVariables.upgradeConfig.setUseProxy(false);
/* 580:548 */         GlobalVariables.upgradeConfig.setUseAuth(false);
/* 581:    */       }
/* 582:550 */       GlobalVariables.upgradeConfig.setProxyIp(hostIp);
/* 583:551 */       GlobalVariables.upgradeConfig.setProxyPort(hostPort);
/* 584:552 */       GlobalVariables.upgradeConfig.setAuthName(username);
/* 585:553 */       GlobalVariables.upgradeConfig.setAuthPassword(MyMD5.transform(password));
/* 586:    */       
/* 587:555 */       ConfigureTools.updateProperties(GlobalVariables.upgradeConfig);
/* 588:556 */       ConfigureTools.wrapProperties(GlobalVariables.upgradeConfig);
/* 589:557 */       dispose();
/* 590:    */     }
/* 591:    */     catch (Exception e)
/* 592:    */     {
/* 593:559 */       e.printStackTrace();
/* 594:560 */       DisplayMessage.showErrorDialog("message.operationfailure");
/* 595:    */     }
/* 596:    */   }
/* 597:    */   
/* 598:    */   private void testConnection()
/* 599:    */   {
/* 600:565 */     HttpURLConnection httpUrl = null;
/* 601:566 */     InputStream inputStream = null;
/* 602:567 */     URL url = null;
/* 603:568 */     String dir = this.downloadTextField.getText().trim();
/* 604:569 */     System.out.println(dir);
/* 605:    */     try
/* 606:    */     {
/* 607:573 */       url = new URL(dir);
/* 608:574 */       String proxy = this.hostIPTextField.getText().trim();
/* 609:575 */       String port = this.portTextField.getText().trim();
/* 610:576 */       boolean useProxy = this.jCheckBox1.isSelected();
/* 611:577 */       boolean useAuth = this.jCheckBox3.isSelected();
/* 612:578 */       if (useProxy)
/* 613:    */       {
/* 614:579 */         Properties systemProperties = System.getProperties();
/* 615:    */         
/* 616:581 */         systemProperties.setProperty("http.proxyHost", proxy);
/* 617:582 */         systemProperties.setProperty("http.proxyPort", port);
/* 618:    */       }
/* 619:584 */       httpUrl = (HttpURLConnection)url.openConnection();
/* 620:586 */       if ((useProxy) && (useAuth))
/* 621:    */       {
/* 622:587 */         String password = this.jTextField3.getText().trim() + ":" + 
/* 623:588 */           new String(this.jPasswordField1.getPassword()).trim();
/* 624:589 */         String encoded = Base64.encode(password.getBytes());
/* 625:590 */         httpUrl.setRequestProperty("Proxy-Authorization", "Basic " + 
/* 626:591 */           encoded);
/* 627:    */       }
/* 628:594 */       httpUrl.setConnectTimeout(5000);
/* 629:595 */       httpUrl.connect();
/* 630:596 */       inputStream = httpUrl.getInputStream();
/* 631:597 */       DisplayMessage.showInfoDialog("message.connectionsuccess");
/* 632:    */     }
/* 633:    */     catch (FileNotFoundException ex)
/* 634:    */     {
/* 635:599 */       DisplayMessage.showInfoDialog("message.connectionsuccess");
/* 636:604 */       if (httpUrl != null) {
/* 637:605 */         httpUrl.disconnect();
/* 638:    */       }
/* 639:607 */       if (inputStream != null) {
/* 640:    */         try
/* 641:    */         {
/* 642:609 */           inputStream.close();
/* 643:    */         }
/* 644:    */         catch (IOException e)
/* 645:    */         {
/* 646:611 */           e.printStackTrace();
/* 647:    */         }
/* 648:    */       }
/* 649:    */     }
/* 650:    */     catch (IOException ex)
/* 651:    */     {
/* 652:602 */       DisplayMessage.showErrorDialog("message.connectionfailure");
/* 653:604 */       if (httpUrl != null) {
/* 654:605 */         httpUrl.disconnect();
/* 655:    */       }
/* 656:607 */       if (inputStream != null) {
/* 657:    */         try
/* 658:    */         {
/* 659:609 */           inputStream.close();
/* 660:    */         }
/* 661:    */         catch (IOException e)
/* 662:    */         {
/* 663:611 */           e.printStackTrace();
/* 664:    */         }
/* 665:    */       }
/* 666:    */     }
/* 667:    */     finally
/* 668:    */     {
/* 669:604 */       if (httpUrl != null) {
/* 670:605 */         httpUrl.disconnect();
/* 671:    */       }
/* 672:607 */       if (inputStream != null) {
/* 673:    */         try
/* 674:    */         {
/* 675:609 */           inputStream.close();
/* 676:    */         }
/* 677:    */         catch (IOException e)
/* 678:    */         {
/* 679:611 */           e.printStackTrace();
/* 680:    */         }
/* 681:    */       }
/* 682:    */     }
/* 683:    */   }
/* 684:    */   
/* 685:    */   private static boolean isNumeric(String str)
/* 686:    */   {
/* 687:618 */     if ((str == null) || ("".equalsIgnoreCase(str))) {
/* 688:619 */       return false;
/* 689:    */     }
/* 690:621 */     int i = str.length();
/* 691:    */     do
/* 692:    */     {
/* 693:622 */       if (!Character.isDigit(str.charAt(i))) {
/* 694:623 */         return false;
/* 695:    */       }
/* 696:621 */       i--;
/* 697:621 */     } while (i >= 0);
/* 698:626 */     int num = Integer.parseInt(str);
/* 699:627 */     if ((num > 65536) || (num < 1)) {
/* 700:628 */       return false;
/* 701:    */     }
/* 702:630 */     return true;
/* 703:    */   }
/* 704:    */   
/* 705:    */   private static boolean isNull(String str)
/* 706:    */   {
/* 707:634 */     if ((str == null) || ("".equalsIgnoreCase(str))) {
/* 708:635 */       return true;
/* 709:    */     }
/* 710:637 */     return false;
/* 711:    */   }
/* 712:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.ConfigFrame
 * JD-Core Version:    0.7.0.1
 */