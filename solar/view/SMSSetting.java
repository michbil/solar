/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.SmsConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   7:    */ import cn.com.voltronic.solar.util.ModemAdapter;
/*   8:    */ import cn.com.voltronic.solar.util.VolUtil;
/*   9:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  10:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  11:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  12:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  13:    */ import gnu.io.CommPortIdentifier;
/*  14:    */ import java.awt.Container;
/*  15:    */ import java.awt.Frame;
/*  16:    */ import java.awt.event.ActionEvent;
/*  17:    */ import java.awt.event.ActionListener;
/*  18:    */ import java.util.ArrayList;
/*  19:    */ import java.util.Enumeration;
/*  20:    */ import java.util.List;
/*  21:    */ import javax.swing.BorderFactory;
/*  22:    */ import javax.swing.DefaultComboBoxModel;
/*  23:    */ import javax.swing.DefaultListModel;
/*  24:    */ import javax.swing.GroupLayout;
/*  25:    */ import javax.swing.GroupLayout.Alignment;
/*  26:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  27:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  28:    */ import javax.swing.JComboBox;
/*  29:    */ import javax.swing.JList;
/*  30:    */ import javax.swing.JPanel;
/*  31:    */ import javax.swing.JScrollPane;
/*  32:    */ import javax.swing.JTextField;
/*  33:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  34:    */ 
/*  35:    */ public class SMSSetting
/*  36:    */   extends AADialog
/*  37:    */ {
/*  38:    */   private static final long serialVersionUID = 3769040495948043931L;
/*  39:    */   private AAButton jButton1;
/*  40:    */   private AAButton jButton2;
/*  41:    */   private AAButton jButton4;
/*  42:    */   private AAButton jButton5;
/*  43:    */   private AAButton jButton6;
/*  44:    */   private JComboBox jComboBox1;
/*  45:    */   private JComboBox jComboBox2;
/*  46:    */   private AALabel jLabel1;
/*  47:    */   private AALabel jLabel10;
/*  48:    */   private AALabel jLabel2;
/*  49:    */   private AALabel jLabel4;
/*  50:    */   private AALabel jLabel8;
/*  51:    */   private AALabel jLabel9;
/*  52:    */   private JList jList1;
/*  53:    */   private JPanel jPanel1;
/*  54:    */   private JPanel jPanel2;
/*  55:    */   private JScrollPane jScrollPane1;
/*  56:    */   private JTextField jTextField5;
/*  57: 62 */   private DefaultListModel listModel = null;
/*  58: 63 */   private SmsConfig smsInfo = null;
/*  59:    */   
/*  60:    */   public SMSSetting(Frame parent, boolean modal)
/*  61:    */   {
/*  62: 66 */     super(parent, modal);
/*  63: 67 */     this.smsInfo = GlobalVariables.smsConfig;
/*  64: 68 */     if (this.smsInfo == null) {
/*  65: 69 */       this.smsInfo = new SmsConfig();
/*  66:    */     }
/*  67: 71 */     initComponents();
/*  68: 72 */     setTitle("message.smsinfoSet");
/*  69: 73 */     setLocationRelativeTo(null);
/*  70: 74 */     setVisible(true);
/*  71:    */   }
/*  72:    */   
/*  73:    */   private void initValue()
/*  74:    */   {
/*  75: 78 */     String[] arrCom = searchComm();
/*  76: 79 */     this.jComboBox1.setModel(new DefaultComboBoxModel(arrCom));
/*  77: 80 */     for (int i = 0; i < arrCom.length; i++) {
/*  78: 81 */       if (arrCom[i].equals(this.smsInfo.getComPort()))
/*  79:    */       {
/*  80: 82 */         this.jComboBox1.setSelectedItem(arrCom[i]);
/*  81: 83 */         break;
/*  82:    */       }
/*  83:    */     }
/*  84: 86 */     String[] arrBit = { "1200", "2400", "4800", "9600" };
/*  85: 87 */     this.jComboBox2.setModel(new DefaultComboBoxModel(arrBit));
/*  86: 88 */     for (int j = 0; j < arrBit.length; j++) {
/*  87: 89 */       if (arrBit[j].equals(this.smsInfo.getBit()))
/*  88:    */       {
/*  89: 90 */         this.jComboBox2.setSelectedItem(arrBit[j]);
/*  90: 91 */         break;
/*  91:    */       }
/*  92:    */     }
/*  93: 94 */     String receiveArr = this.smsInfo.getMobileNums().trim();
/*  94: 95 */     if (!"".equalsIgnoreCase(receiveArr)) {
/*  95: 97 */       if (receiveArr.indexOf(";") == -1)
/*  96:    */       {
/*  97: 98 */         this.listModel.addElement(receiveArr);
/*  98:    */       }
/*  99:    */       else
/* 100:    */       {
/* 101:100 */         String[] strings = receiveArr.split(";");
/* 102:101 */         for (int i = 0; i < strings.length; i++) {
/* 103:102 */           this.listModel.add(i, strings[i]);
/* 104:    */         }
/* 105:    */       }
/* 106:    */     }
/* 107:105 */     this.jList1.setModel(this.listModel);
/* 108:    */   }
/* 109:    */   
/* 110:    */   private String[] searchComm()
/* 111:    */   {
/* 112:109 */     String[] arr = searchLocalComms();
/* 113:110 */     return arr;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public String[] searchLocalComms()
/* 117:    */   {
/* 118:115 */     List<String> portList = new ArrayList();
/* 119:116 */     portList.add("");
/* 120:    */     try
/* 121:    */     {
/* 122:118 */       Enumeration portEn = CommPortIdentifier.getPortIdentifiers();
/* 123:119 */       while (portEn.hasMoreElements())
/* 124:    */       {
/* 125:120 */         CommPortIdentifier portId = 
/* 126:121 */           (CommPortIdentifier)portEn.nextElement();
/* 127:122 */         if (portId.getPortType() == 1) {
/* 128:123 */           portList.add(portId.getName().trim());
/* 129:    */         }
/* 130:    */       }
/* 131:    */     }
/* 132:    */     catch (Exception e)
/* 133:    */     {
/* 134:127 */       e.printStackTrace();
/* 135:    */     }
/* 136:129 */     String[] ports = new String[portList.size()];
/* 137:130 */     for (int i = 0; i < portList.size(); i++) {
/* 138:131 */       ports[i] = ((String)portList.get(i));
/* 139:    */     }
/* 140:134 */     return ports;
/* 141:    */   }
/* 142:    */   
/* 143:    */   private void initComponents()
/* 144:    */   {
/* 145:139 */     this.jPanel1 = new JPanel();
/* 146:140 */     this.jLabel1 = new AALabel();
/* 147:141 */     this.jLabel2 = new AALabel();
/* 148:142 */     this.jLabel8 = new AALabel();
/* 149:143 */     this.jLabel9 = new AALabel();
/* 150:144 */     this.jButton4 = new AAButton();
/* 151:145 */     this.jLabel10 = new AALabel();
/* 152:146 */     this.jScrollPane1 = new JScrollPane();
/* 153:147 */     this.jList1 = new JList();
/* 154:148 */     this.listModel = new DefaultListModel();
/* 155:149 */     this.jTextField5 = new JTextField();
/* 156:150 */     this.jButton5 = new AAButton();
/* 157:151 */     this.jButton6 = new AAButton();
/* 158:152 */     this.jComboBox1 = new JComboBox();
/* 159:153 */     this.jLabel4 = new AALabel();
/* 160:154 */     this.jComboBox2 = new JComboBox();
/* 161:155 */     this.jPanel2 = new JPanel();
/* 162:156 */     this.jButton1 = new AAButton();
/* 163:157 */     this.jButton2 = new AAButton();
/* 164:    */     
/* 165:159 */     initValue();
/* 166:    */     
/* 167:161 */     setDefaultCloseOperation(2);
/* 168:    */     
/* 169:163 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 170:    */     
/* 171:165 */     this.jLabel1.setText("message.serialset");
/* 172:    */     
/* 173:167 */     this.jLabel2.setText("message.serial[:]");
/* 174:    */     
/* 175:169 */     this.jLabel8.setText("message.tipinfo");
/* 176:    */     
/* 177:171 */     this.jLabel9.setText("message.note[:]");
/* 178:    */     
/* 179:173 */     this.jButton4.setText("message.test");
/* 180:174 */     this.jButton4.addActionListener(new ActionListener()
/* 181:    */     {
/* 182:    */       public void actionPerformed(ActionEvent e)
/* 183:    */       {
/* 184:177 */         if (!SolarPowerTray.isLogin)
/* 185:    */         {
/* 186:178 */           new LoginJDialog(new Frame(), true);
/* 187:179 */           return;
/* 188:    */         }
/* 189:181 */         SMSSetting.this.jButton4.setEnabled(false);
/* 190:182 */         SMSSetting.this.testMailConfig();
/* 191:    */       }
/* 192:186 */     });
/* 193:187 */     this.jLabel10.setText("message.recvMailList[:]");
/* 194:    */     
/* 195:189 */     this.jScrollPane1.setViewportView(this.jList1);
/* 196:    */     
/* 197:191 */     this.jButton5.setText("message.add");
/* 198:192 */     this.jButton5.addActionListener(new ActionListener()
/* 199:    */     {
/* 200:    */       public void actionPerformed(ActionEvent e)
/* 201:    */       {
/* 202:195 */         SMSSetting.this.addReceiver();
/* 203:    */       }
/* 204:198 */     });
/* 205:199 */     this.jButton6.setText("message.del");
/* 206:200 */     this.jButton6.addActionListener(new ActionListener()
/* 207:    */     {
/* 208:    */       public void actionPerformed(ActionEvent e)
/* 209:    */       {
/* 210:203 */         SMSSetting.this.deleteReceiver();
/* 211:    */       }
/* 212:206 */     });
/* 213:207 */     this.jLabel4.setText("message.baudrate[:]");
/* 214:    */     
/* 215:209 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/* 216:210 */     this.jPanel1.setLayout(jPanel1Layout);
/* 217:211 */     jPanel1Layout.setHorizontalGroup(
/* 218:212 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 219:213 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 220:214 */       .addGap(33, 33, 33)
/* 221:215 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 222:216 */       .addComponent(this.jLabel9)
/* 223:217 */       .addComponent(this.jLabel10)
/* 224:218 */       .addComponent(this.jLabel4)
/* 225:219 */       .addComponent(this.jLabel2))
/* 226:220 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 227:221 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 228:222 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 229:223 */       .addComponent(this.jLabel1))
/* 230:224 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 231:225 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 232:226 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 233:227 */       .addComponent(this.jScrollPane1, GroupLayout.Alignment.LEADING, -1, 149, 32767)
/* 234:228 */       .addComponent(this.jButton4, GroupLayout.Alignment.LEADING)
/* 235:229 */       .addComponent(this.jComboBox2, GroupLayout.Alignment.LEADING, 0, -1, 32767)
/* 236:230 */       .addComponent(this.jComboBox1, GroupLayout.Alignment.LEADING, 0, -1, 32767))
/* 237:231 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 238:232 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 239:233 */       .addComponent(this.jTextField5, -2, 148, -2)
/* 240:234 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 241:235 */       .addComponent(this.jButton5)
/* 242:236 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 243:237 */       .addComponent(this.jButton6))))
/* 244:238 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 245:239 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 246:240 */       .addComponent(this.jLabel8)))
/* 247:241 */       .addContainerGap(22, 32767)));
/* 248:    */     
/* 249:243 */     jPanel1Layout.setVerticalGroup(
/* 250:244 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 251:245 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 252:246 */       .addGap(23, 23, 23)
/* 253:247 */       .addComponent(this.jLabel1)
/* 254:248 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 255:249 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 256:250 */       .addComponent(this.jLabel2)
/* 257:251 */       .addComponent(this.jComboBox1, -2, -1, -2))
/* 258:252 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 259:253 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 260:254 */       .addComponent(this.jLabel4)
/* 261:255 */       .addComponent(this.jComboBox2, -2, -1, -2))
/* 262:256 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 263:257 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 264:258 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 265:259 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 266:260 */       .addComponent(this.jLabel10)
/* 267:261 */       .addComponent(this.jTextField5, -2, -1, -2))
/* 268:262 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 269:263 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 270:264 */       .addComponent(this.jButton6)
/* 271:265 */       .addComponent(this.jButton5)))
/* 272:266 */       .addComponent(this.jScrollPane1, -2, 95, -2))
/* 273:267 */       .addGap(12, 12, 12)
/* 274:268 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 275:269 */       .addComponent(this.jLabel9)
/* 276:270 */       .addComponent(this.jLabel8))
/* 277:271 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 278:272 */       .addComponent(this.jButton4)
/* 279:273 */       .addContainerGap(16, 32767)));
/* 280:    */     
/* 281:    */ 
/* 282:276 */     getContentPane().add(this.jPanel1, "Center");
/* 283:    */     
/* 284:278 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 285:    */     
/* 286:280 */     this.jButton1.setText("message.apply");
/* 287:281 */     this.jButton1.addActionListener(new ActionListener()
/* 288:    */     {
/* 289:    */       public void actionPerformed(ActionEvent e)
/* 290:    */       {
/* 291:284 */         if (!SolarPowerTray.isLogin)
/* 292:    */         {
/* 293:285 */           new LoginJDialog(new Frame(), true);
/* 294:286 */           return;
/* 295:    */         }
/* 296:288 */         SMSSetting.this.applySetting();
/* 297:    */       }
/* 298:291 */     });
/* 299:292 */     this.jButton2.setText("message.close");
/* 300:293 */     this.jButton2.addActionListener(new ActionListener()
/* 301:    */     {
/* 302:    */       public void actionPerformed(ActionEvent e)
/* 303:    */       {
/* 304:296 */         SMSSetting.this.dispose();
/* 305:    */       }
/* 306:299 */     });
/* 307:300 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 308:301 */     this.jPanel2.setLayout(jPanel2Layout);
/* 309:302 */     jPanel2Layout.setHorizontalGroup(
/* 310:303 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 311:304 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 312:305 */       jPanel2Layout.createSequentialGroup().addContainerGap(397, 32767)
/* 313:306 */       .addComponent(this.jButton1)
/* 314:307 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 315:308 */       .addComponent(this.jButton2)
/* 316:309 */       .addGap(12, 12, 12)));
/* 317:    */     
/* 318:311 */     jPanel2Layout.setVerticalGroup(
/* 319:312 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 320:313 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 321:314 */       jPanel2Layout.createSequentialGroup().addContainerGap(-1, 32767)
/* 322:315 */       .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 323:316 */       .addComponent(this.jButton2)
/* 324:317 */       .addComponent(this.jButton1))
/* 325:318 */       .addContainerGap()));
/* 326:    */     
/* 327:    */ 
/* 328:321 */     getContentPane().add(this.jPanel2, "Last");
/* 329:    */     
/* 330:323 */     pack();
/* 331:    */   }
/* 332:    */   
/* 333:    */   public void addReceiver()
/* 334:    */   {
/* 335:327 */     String receiver = this.jTextField5.getText().trim();
/* 336:328 */     if (VolUtil.checkNull(receiver))
/* 337:    */     {
/* 338:329 */       DisplayMessage.showErrorDialog("message.notnull");
/* 339:330 */       return;
/* 340:    */     }
/* 341:332 */     if (!VolUtil.checkPhoneNum(receiver))
/* 342:    */     {
/* 343:333 */       DisplayMessage.showErrorDialog("message.phonenotcorrect");
/* 344:334 */       return;
/* 345:    */     }
/* 346:336 */     if (this.listModel.contains(receiver))
/* 347:    */     {
/* 348:337 */       DisplayMessage.showErrorDialog("message.phoneexist");
/* 349:338 */       return;
/* 350:    */     }
/* 351:340 */     this.listModel.addElement(receiver);
/* 352:    */   }
/* 353:    */   
/* 354:    */   public void deleteReceiver()
/* 355:    */   {
/* 356:344 */     int index = this.jList1.getSelectedIndex();
/* 357:345 */     if (index == -1)
/* 358:    */     {
/* 359:346 */       DisplayMessage.showErrorDialog("message.pleaseselect");
/* 360:347 */       return;
/* 361:    */     }
/* 362:349 */     this.listModel.remove(index);
/* 363:    */   }
/* 364:    */   
/* 365:    */   public void applySetting()
/* 366:    */   {
/* 367:353 */     if ((this.jComboBox1.getSelectedItem() == null) || (this.jComboBox2.getSelectedItem() == null)) {
/* 368:354 */       return;
/* 369:    */     }
/* 370:356 */     String com = this.jComboBox1.getSelectedItem().toString().trim();
/* 371:357 */     String bit = this.jComboBox2.getSelectedItem().toString().trim();
/* 372:358 */     StringBuffer recievers = new StringBuffer();
/* 373:359 */     for (int i = 0; i < this.listModel.size(); i++) {
/* 374:360 */       if (i < 1) {
/* 375:361 */         recievers.append(this.listModel.get(i));
/* 376:    */       } else {
/* 377:363 */         recievers.append(";").append(this.listModel.get(i));
/* 378:    */       }
/* 379:    */     }
/* 380:366 */     this.smsInfo.setComPort(com);
/* 381:367 */     this.smsInfo.setBit(bit);
/* 382:368 */     this.smsInfo.setMobileNums(recievers.toString());
/* 383:    */     try
/* 384:    */     {
/* 385:370 */       ConfigureTools.updateProperties(this.smsInfo);
/* 386:371 */       ConfigureTools.wrapProperties(GlobalVariables.smsConfig);
/* 387:372 */       dispose();
/* 388:    */     }
/* 389:    */     catch (Exception e)
/* 390:    */     {
/* 391:374 */       DisplayMessage.showErrorDialog("message.operationfailure");
/* 392:    */     }
/* 393:    */   }
/* 394:    */   
/* 395:    */   public void testMailConfig()
/* 396:    */   {
/* 397:379 */     String comPort = this.jComboBox1.getSelectedItem().toString().trim();
/* 398:380 */     String bit = this.jComboBox2.getSelectedItem().toString().trim();
/* 399:381 */     SmsConfig smsInfo = new SmsConfig();
/* 400:382 */     smsInfo.setComPort(comPort);
/* 401:383 */     smsInfo.setBit(bit);
/* 402:384 */     boolean flag = false;
/* 403:385 */     int indexSize = this.listModel.size();
/* 404:386 */     if (indexSize > 0) {
/* 405:    */       try
/* 406:    */       {
/* 407:388 */         String[] destinations = new String[indexSize];
/* 408:389 */         for (int i = 0; i < indexSize; i++) {
/* 409:390 */           destinations[i] = this.listModel.elementAt(i).toString();
/* 410:    */         }
/* 411:392 */         ModemAdapter adapter = new ModemAdapter(
/* 412:393 */           smsInfo.getComPort(), Integer.parseInt(smsInfo.getBit()), 
/* 413:394 */           destinations, "send test success");
/* 414:395 */         flag = adapter.startSend();
/* 415:    */       }
/* 416:    */       catch (Exception ex)
/* 417:    */       {
/* 418:397 */         ex.printStackTrace();
/* 419:398 */         flag = false;
/* 420:    */       }
/* 421:    */     }
/* 422:401 */     if (flag) {
/* 423:402 */       DisplayMessage.showInfoDialog("message.testsuccess");
/* 424:    */     } else {
/* 425:404 */       DisplayMessage.showErrorDialog("message.testfailure");
/* 426:    */     }
/* 427:406 */     this.jButton4.setEnabled(true);
/* 428:    */   }
/* 429:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.SMSSetting
 * JD-Core Version:    0.7.0.1
 */