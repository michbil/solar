/*   1:    */ package cn.com.voltronic.solar.console.windows;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.P30BeanBag;
/*   4:    */ import cn.com.voltronic.solar.communicate.SerialHandler;
/*   5:    */ import cn.com.voltronic.solar.communicate.SerialHandlerSolaris;
/*   6:    */ import cn.com.voltronic.solar.communicate.USBHandler;
/*   7:    */ import cn.com.voltronic.solar.comusbprocessor.P30ComUSBProcessor;
/*   8:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   9:    */ import cn.com.voltronic.solar.configure.SystemEnv;
/*  10:    */ import cn.com.voltronic.solar.configure.UpgradeConfig;
/*  11:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*  12:    */ import cn.com.voltronic.solar.constants.Constants;
/*  13:    */ import cn.com.voltronic.solar.dao.WorkDataDao;
/*  14:    */ import cn.com.voltronic.solar.protocol.P30;
/*  15:    */ import cn.com.voltronic.solar.socket.DatagramServer;
/*  16:    */ import cn.com.voltronic.solar.socket.SystemTrayUDPServer;
/*  17:    */ import cn.com.voltronic.solar.socket.UdpClient;
/*  18:    */ import cn.com.voltronic.solar.system.DaoManager;
/*  19:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*  20:    */ import cn.com.voltronic.solar.system.ProcessorCategories;
/*  21:    */ import cn.com.voltronic.solar.thread.AutoDetect;
/*  22:    */ import cn.com.voltronic.solar.thread.PageThread;
/*  23:    */ import cn.com.voltronic.solar.upgrade.StartUpgrade;
/*  24:    */ import cn.com.voltronic.solar.util.SCcmd;
/*  25:    */ import cn.com.voltronic.solar.view.EventInfoDialog;
/*  26:    */ import cn.com.voltronic.solar.view.MainJFrame;
/*  27:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  28:    */ import cn.com.voltronic.solar.work.PollProcessorStatus;
/*  29:    */ import cn.com.voltronic.solar.work.RecordWorkinfo;
/*  30:    */ import cn.com.voltronic.solar.work.SearchDevice;
/*  31:    */ import java.awt.TrayIcon;
/*  32:    */ import java.awt.event.ActionEvent;
/*  33:    */ import java.awt.event.ActionListener;
/*  34:    */ import java.awt.event.MouseAdapter;
/*  35:    */ import java.awt.event.MouseEvent;
/*  36:    */ import java.awt.event.MouseListener;
/*  37:    */ import java.awt.event.WindowAdapter;
/*  38:    */ import java.awt.event.WindowEvent;
/*  39:    */ import java.io.PrintStream;
/*  40:    */ import java.util.ArrayList;
/*  41:    */ import java.util.List;
/*  42:    */ import javax.swing.JTextPane;
/*  43:    */ 
/*  44:    */ public class MainConsole
/*  45:    */ {
/*  46: 48 */   private DatagramServer udpServerSocket = null;
/*  47: 49 */   public EventInfoDialog dialog = null;
/*  48: 50 */   private static SolarPowerTray consoleTray = null;
/*  49: 51 */   public TrayIcon trayIcon = null;
/*  50:    */   public volatile List<String> infoList;
/*  51: 53 */   private volatile boolean hasInfo = false;
/*  52: 54 */   public volatile boolean isopen = false;
/*  53: 55 */   public volatile int pvCounter = 0;
/*  54:    */   
/*  55:    */   public static SolarPowerTray getConsoleTray()
/*  56:    */   {
/*  57: 58 */     return consoleTray;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public MainConsole()
/*  61:    */   {
/*  62:    */     try
/*  63:    */     {
/*  64: 63 */       this.udpServerSocket = SystemTrayUDPServer.getServer();
/*  65:    */       try
/*  66:    */       {
/*  67: 65 */         inint();
/*  68:    */       }
/*  69:    */       catch (Exception e)
/*  70:    */       {
/*  71: 67 */         System.err.println("系统初始化失败！");
/*  72: 68 */         if (this.udpServerSocket == null) {
/*  73:    */           return;
/*  74:    */         }
/*  75:    */       }
/*  76: 69 */       this.udpServerSocket.close();
/*  77:    */     }
/*  78:    */     catch (Exception e)
/*  79:    */     {
/*  80: 73 */       System.err.println("托盘已经打开！");
/*  81: 74 */       String port = "38694";
/*  82: 75 */       UdpClient client = null;
/*  83:    */       try
/*  84:    */       {
/*  85: 77 */         port = GlobalVariables.globalConfig.getUdpPort();
/*  86: 78 */         client = new UdpClient("localhost", port);
/*  87: 79 */         client.send("OPENPAGE");
/*  88:    */       }
/*  89:    */       catch (Exception ex)
/*  90:    */       {
/*  91: 81 */         ex.printStackTrace();
/*  92:    */       }
/*  93:    */       finally
/*  94:    */       {
/*  95: 83 */         if (client != null) {
/*  96: 84 */           client.disconnect();
/*  97:    */         }
/*  98:    */       }
/*  99:    */     }
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void inint()
/* 103:    */   {
/* 104: 91 */     GlobalVariables.initConfig(Constants.CONFIG_PATH);
/* 105:    */     
/* 106:    */ 
/* 107: 94 */     initMonitorProcess();
/* 108:    */     
/* 109:    */ 
/* 110: 97 */     consoleTray = new SystemConsoleTray();
/* 111:    */     
/* 112:    */ 
/* 113:    */ 
/* 114:101 */     SCcmd.startDaemon();
/* 115:    */     
/* 116:103 */     PageThread thread = new PageThread();
/* 117:104 */     thread.start();
/* 118:    */     
/* 119:106 */     initDaoManager();
/* 120:    */     
/* 121:108 */     SearchDevice searchdevice = new SearchDevice();
/* 122:109 */     searchdevice.start();
/* 123:    */     
/* 124:    */ 
/* 125:112 */     RecordWorkinfo record = new RecordWorkinfo();
/* 126:113 */     record.start();
/* 127:    */     
/* 128:    */ 
/* 129:116 */     PollProcessorStatus pollstatus = new PollProcessorStatus();
/* 130:117 */     pollstatus.setPriority(10);
/* 131:118 */     pollstatus.start();
/* 132:    */     
/* 133:    */ 
/* 134:121 */     this.trayIcon = SolarPowerTray.trayIcon;
/* 135:    */     
/* 136:    */ 
/* 137:124 */     boolean useautoDetect = GlobalVariables.upgradeConfig.isAutoUpgrade();
/* 138:125 */     if (useautoDetect)
/* 139:    */     {
/* 140:126 */       AutoDetect autoDetect = new AutoDetect();
/* 141:127 */       autoDetect.setDaemon(true);
/* 142:128 */       autoDetect.start();
/* 143:    */     }
/* 144:131 */     this.infoList = new ArrayList();
/* 145:132 */     responseAction();
/* 146:133 */     EventListener eventListener = new EventListener();
/* 147:134 */     eventListener.start();
/* 148:    */   }
/* 149:    */   
/* 150:    */   public static void main(String[] args)
/* 151:    */   {
/* 152:138 */     new MainConsole();
/* 153:    */   }
/* 154:    */   
/* 155:    */   private void initDaoManager()
/* 156:    */   {
/* 157:142 */     DaoManager.registerDAO(P30BeanBag.class, WorkDataDao.class);
/* 158:    */   }
/* 159:    */   
/* 160:    */   private void initMonitorProcess()
/* 161:    */   {
/* 162:146 */     ProcessorCategories.registerProcessor(P30.class, SerialHandler.class, 
/* 163:147 */       P30ComUSBProcessor.class);
/* 164:148 */     ProcessorCategories.registerProcessor(P30.class, SerialHandlerSolaris.class, 
/* 165:149 */       P30ComUSBProcessor.class);
/* 166:150 */     ProcessorCategories.registerProcessor(P30.class, USBHandler.class, 
/* 167:151 */       P30ComUSBProcessor.class);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void responseAction()
/* 171:    */   {
/* 172:155 */     MouseListener openItemAction = new MouseAdapter()
/* 173:    */     {
/* 174:    */       public void mouseClicked(MouseEvent e)
/* 175:    */       {
/* 176:158 */         if ((e.getClickCount() == 1) && (!e.isMetaDown()) && 
/* 177:159 */           (MainConsole.this.hasInfo))
/* 178:    */         {
/* 179:160 */           MainConsole.this.dialog = EventInfoDialog.getInstance();
/* 180:161 */           MainConsole.this.isopen = true;
/* 181:162 */           MainConsole.this.trayIcon.setPopupMenu(SystemConsoleTray.popup);
/* 182:163 */           MainConsole.this.dialog.addWindowListener(new WindowAdapter()
/* 183:    */           {
/* 184:    */             public void windowClosing(WindowEvent e)
/* 185:    */             {
/* 186:166 */               MainConsole.this.isopen = false;
/* 187:167 */               MainConsole.this.infoList.clear();
/* 188:168 */               MainConsole.this.dialog.dispose();
/* 189:    */             }
/* 190:170 */           });
/* 191:171 */           MainConsole.this.dialog.jButton1.addActionListener(new ActionListener()
/* 192:    */           {
/* 193:    */             public void actionPerformed(ActionEvent evt)
/* 194:    */             {
/* 195:173 */               MainConsole.this.isopen = false;
/* 196:174 */               MainConsole.this.infoList.clear();
/* 197:175 */               MainConsole.this.dialog.dispose();
/* 198:    */             }
/* 199:177 */           });
/* 200:178 */           MainConsole.this.dialog.setVisible(true);
/* 201:179 */           StringBuffer sb = new StringBuffer();
/* 202:180 */           if (MainConsole.this.infoList.size() > 20) {
/* 203:181 */             for (int i = MainConsole.this.infoList.size() - 20; i < MainConsole.this.infoList.size(); i++) {
/* 204:183 */               sb.append((String)MainConsole.this.infoList.get(i));
/* 205:    */             }
/* 206:    */           } else {
/* 207:186 */             for (int i = 0; i < MainConsole.this.infoList.size(); i++) {
/* 208:187 */               sb.append((String)MainConsole.this.infoList.get(i));
/* 209:    */             }
/* 210:    */           }
/* 211:190 */           MainConsole.this.dialog.getEventTextPanel().setText(sb.toString());
/* 212:191 */           MainConsole.this.dialog.getEventTextPanel().setCaretPosition(
/* 213:192 */             sb.toString().length());
/* 214:193 */           if (MainConsole.this.pvCounter > 0) {
/* 215:194 */             MainConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG_DEVICE);
/* 216:    */           } else {
/* 217:196 */             MainConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG);
/* 218:    */           }
/* 219:198 */           MainConsole.this.hasInfo = false;
/* 220:    */         }
/* 221:    */       }
/* 222:202 */     };
/* 223:203 */     this.trayIcon.addMouseListener(openItemAction);
/* 224:    */   }
/* 225:    */   
/* 226:    */   class EventListener
/* 227:    */     extends Thread
/* 228:    */   {
/* 229:    */     EventListener() {}
/* 230:    */     
/* 231:    */     public void run()
/* 232:    */     {
/* 233:208 */       boolean isUpgrade = false;
/* 234:    */       try
/* 235:    */       {
/* 236:    */         for (;;)
/* 237:    */         {
/* 238:211 */           String rec = MainConsole.this.udpServerSocket.receive().trim();
/* 239:213 */           if (rec.startsWith("(Event:"))
/* 240:    */           {
/* 241:214 */             String event = rec.substring(7, 
/* 242:215 */               rec.lastIndexOf("13"));
/* 243:    */             
/* 244:    */ 
/* 245:    */ 
/* 246:    */ 
/* 247:    */ 
/* 248:221 */             String eventaddn = event + "\n";
/* 249:    */             
/* 250:223 */             String startEvent = eventaddn.substring(0, 
/* 251:224 */               eventaddn.lastIndexOf("]") + 1);
/* 252:225 */             String endEvent = eventaddn.substring(
/* 253:226 */               eventaddn.lastIndexOf("]") + 2);
/* 254:227 */             MainConsole.this.infoList
/* 255:228 */               .add(startEvent + "\n" + "     " + endEvent);
/* 256:229 */             if (MainConsole.this.isopen)
/* 257:    */             {
/* 258:230 */               StringBuffer sb = new StringBuffer();
/* 259:238 */               for (int i = 0; i < MainConsole.this.infoList.size(); i++) {
/* 260:239 */                 sb.append((String)MainConsole.this.infoList.get(i));
/* 261:    */               }
/* 262:242 */               MainConsole.this.dialog.getEventTextPanel().setText(
/* 263:243 */                 sb.toString());
/* 264:244 */               MainConsole.this.dialog.getEventTextPanel().setCaretPosition(
/* 265:245 */                 MainConsole.this.dialog.getEventTextPanel().getText()
/* 266:246 */                 .length());
/* 267:    */             }
/* 268:    */             else
/* 269:    */             {
/* 270:248 */               if (MainConsole.this.pvCounter > 0) {
/* 271:249 */                 MainConsole.this.trayIcon.setImage(Constants.HASEVENT_DEVICE);
/* 272:    */               } else {
/* 273:251 */                 MainConsole.this.trayIcon.setImage(Constants.HASEVENT);
/* 274:    */               }
/* 275:253 */               MainConsole.this.hasInfo = true;
/* 276:254 */               MainConsole.this.trayIcon.setPopupMenu(null);
/* 277:    */             }
/* 278:    */           }
/* 279:256 */           else if (rec.startsWith("(PVCount:"))
/* 280:    */           {
/* 281:    */             try
/* 282:    */             {
/* 283:258 */               String count = rec.substring(9);
/* 284:259 */               MainConsole.this.pvCounter = Integer.parseInt(count);
/* 285:260 */               if (MainConsole.this.pvCounter > 0)
/* 286:    */               {
/* 287:261 */                 if ((MainConsole.this.hasInfo) && (!MainConsole.this.isopen))
/* 288:    */                 {
/* 289:262 */                   MainConsole.this.trayIcon.setImage(Constants.HASEVENT_DEVICE); continue;
/* 290:    */                 }
/* 291:264 */                 MainConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG_DEVICE); continue;
/* 292:    */               }
/* 293:267 */               if ((MainConsole.this.hasInfo) && (!MainConsole.this.isopen))
/* 294:    */               {
/* 295:268 */                 MainConsole.this.trayIcon.setImage(Constants.HASEVENT); continue;
/* 296:    */               }
/* 297:270 */               MainConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG);
/* 298:    */             }
/* 299:    */             catch (Exception localException1) {}
/* 300:    */           }
/* 301:275 */           else if (rec.equals("redrawtray"))
/* 302:    */           {
/* 303:276 */             MainConsole.consoleTray.addToSystemTray();
/* 304:277 */             MainConsole.this.udpServerSocket.response("redrawok");
/* 305:    */           }
/* 306:    */           else
/* 307:    */           {
/* 308:278 */             if (rec.startsWith("(exit:myself"))
/* 309:    */             {
/* 310:279 */               SystemEnv.stoped = 0;
/* 311:280 */               SystemEnv.stopping = true;
/* 312:281 */               SCcmd.stopDaemon();
/* 313:282 */               break;
/* 314:    */             }
/* 315:283 */             if (rec.startsWith("(exit:upgrade"))
/* 316:    */             {
/* 317:284 */               isUpgrade = true;
/* 318:285 */               SystemEnv.stoped = 0;
/* 319:286 */               SystemEnv.stopping = true;
/* 320:287 */               SCcmd.stopDaemon();
/* 321:288 */               break;
/* 322:    */             }
/* 323:289 */             if (rec.equals("OPENPAGE"))
/* 324:    */             {
/* 325:290 */               MainJFrame.getNewInstance().setVisible(true);
/* 326:291 */               MainJFrame.getNewInstance().setState(0);
/* 327:    */             }
/* 328:    */           }
/* 329:    */         }
/* 330:    */       }
/* 331:    */       catch (Exception localException2) {}
/* 332:296 */       if (MainConsole.this.udpServerSocket != null) {
/* 333:297 */         MainConsole.this.udpServerSocket.close();
/* 334:    */       }
/* 335:299 */       int iexit = 0;
/* 336:300 */       while (SystemEnv.stoped < 3)
/* 337:    */       {
/* 338:    */         try
/* 339:    */         {
/* 340:302 */           Thread.sleep(1000L);
/* 341:    */         }
/* 342:    */         catch (Exception e)
/* 343:    */         {
/* 344:304 */           e.printStackTrace();
/* 345:    */         }
/* 346:306 */         iexit++;
/* 347:307 */         if (iexit > 20) {
/* 348:    */           break;
/* 349:    */         }
/* 350:310 */         System.out.println("stopping" + SystemEnv.stoped);
/* 351:    */       }
/* 352:312 */       System.out.println("stopped ok");
/* 353:313 */       if (isUpgrade) {
/* 354:314 */         StartUpgrade.runUpgrade();
/* 355:    */       }
/* 356:316 */       System.exit(0);
/* 357:    */     }
/* 358:    */   }
/* 359:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.console.windows.MainConsole
 * JD-Core Version:    0.7.0.1
 */