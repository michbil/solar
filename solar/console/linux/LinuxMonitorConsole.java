/*   1:    */ package cn.com.voltronic.solar.console.linux;
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
/*  24:    */ import cn.com.voltronic.solar.view.EventInfoDialog;
/*  25:    */ import cn.com.voltronic.solar.view.MainJFrame;
/*  26:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  27:    */ import cn.com.voltronic.solar.work.PollProcessorStatus;
/*  28:    */ import cn.com.voltronic.solar.work.RecordWorkinfo;
/*  29:    */ import cn.com.voltronic.solar.work.SearchDevice;
/*  30:    */ import java.awt.TrayIcon;
/*  31:    */ import java.awt.event.ActionEvent;
/*  32:    */ import java.awt.event.ActionListener;
/*  33:    */ import java.awt.event.MouseAdapter;
/*  34:    */ import java.awt.event.MouseEvent;
/*  35:    */ import java.awt.event.MouseListener;
/*  36:    */ import java.awt.event.WindowAdapter;
/*  37:    */ import java.awt.event.WindowEvent;
/*  38:    */ import java.io.PrintStream;
/*  39:    */ import java.util.ArrayList;
/*  40:    */ import java.util.List;
/*  41:    */ import javax.swing.JTextPane;
/*  42:    */ 
/*  43:    */ public class LinuxMonitorConsole
/*  44:    */   extends Thread
/*  45:    */ {
/*  46: 47 */   private DatagramServer udpServerSocket = null;
/*  47: 48 */   public EventInfoDialog dialog = null;
/*  48: 49 */   private static SolarPowerTray consoleTray = null;
/*  49: 50 */   public TrayIcon trayIcon = null;
/*  50:    */   public volatile List<String> infoList;
/*  51: 52 */   private volatile boolean hasInfo = false;
/*  52: 53 */   public volatile boolean isopen = false;
/*  53: 54 */   public volatile int pvCounter = 0;
/*  54:    */   
/*  55:    */   public static SolarPowerTray getConsoleTray()
/*  56:    */   {
/*  57: 57 */     return consoleTray;
/*  58:    */   }
/*  59:    */   
/*  60:    */   public LinuxMonitorConsole()
/*  61:    */   {
/*  62:    */     try
/*  63:    */     {
/*  64: 62 */       this.udpServerSocket = SystemTrayUDPServer.getServer();
/*  65:    */       try
/*  66:    */       {
/*  67: 64 */         inint();
/*  68:    */       }
/*  69:    */       catch (Exception e)
/*  70:    */       {
/*  71: 66 */         System.err.println("系统初始化失败！");
/*  72: 67 */         if (this.udpServerSocket == null) {
/*  73:    */           return;
/*  74:    */         }
/*  75:    */       }
/*  76: 68 */       this.udpServerSocket.close();
/*  77:    */     }
/*  78:    */     catch (Exception e)
/*  79:    */     {
/*  80: 72 */       System.err.println("托盘已经打开！");
/*  81: 73 */       String port = "38694";
/*  82: 74 */       UdpClient client = null;
/*  83:    */       try
/*  84:    */       {
/*  85: 76 */         port = GlobalVariables.globalConfig.getUdpPort();
/*  86: 77 */         client = new UdpClient("localhost", port);
/*  87: 78 */         client.send("OPENPAGE");
/*  88:    */       }
/*  89:    */       catch (Exception ex)
/*  90:    */       {
/*  91: 80 */         ex.printStackTrace();
/*  92:    */       }
/*  93:    */       finally
/*  94:    */       {
/*  95: 82 */         if (client != null) {
/*  96: 83 */           client.disconnect();
/*  97:    */         }
/*  98:    */       }
/*  99:    */     }
/* 100:    */   }
/* 101:    */   
/* 102:    */   public void inint()
/* 103:    */   {
/* 104: 90 */     GlobalVariables.initConfig(Constants.CONFIG_PATH);
/* 105:    */     
/* 106:    */ 
/* 107: 93 */     initMonitorProcess();
/* 108:    */     
/* 109:    */ 
/* 110: 96 */     consoleTray = new LinuxConsoleTray();
/* 111:    */     
/* 112: 98 */     PageThread thread = new PageThread();
/* 113: 99 */     thread.start();
/* 114:    */     
/* 115:101 */     initDaoManager();
/* 116:    */     
/* 117:103 */     SearchDevice searchdevice = new SearchDevice();
/* 118:104 */     searchdevice.start();
/* 119:    */     
/* 120:    */ 
/* 121:107 */     RecordWorkinfo record = new RecordWorkinfo();
/* 122:108 */     record.start();
/* 123:    */     
/* 124:    */ 
/* 125:111 */     PollProcessorStatus pollstatus = new PollProcessorStatus();
/* 126:112 */     pollstatus.setPriority(10);
/* 127:113 */     pollstatus.start();
/* 128:    */     
/* 129:    */ 
/* 130:116 */     this.trayIcon = SolarPowerTray.trayIcon;
/* 131:    */     
/* 132:118 */     boolean useautoDetect = GlobalVariables.upgradeConfig.isAutoUpgrade();
/* 133:119 */     if (useautoDetect)
/* 134:    */     {
/* 135:120 */       AutoDetect autoDetect = new AutoDetect();
/* 136:121 */       autoDetect.setDaemon(true);
/* 137:122 */       autoDetect.start();
/* 138:    */     }
/* 139:124 */     this.infoList = new ArrayList();
/* 140:125 */     responseAction();
/* 141:126 */     EventListener eventListener = new EventListener();
/* 142:127 */     eventListener.start();
/* 143:    */   }
/* 144:    */   
/* 145:    */   public static void main(String[] args)
/* 146:    */   {
/* 147:131 */     LinuxMonitorConsole linuxConsole = new LinuxMonitorConsole();
/* 148:132 */     linuxConsole.start();
/* 149:    */   }
/* 150:    */   
/* 151:    */   private void initDaoManager()
/* 152:    */   {
/* 153:136 */     DaoManager.registerDAO(P30BeanBag.class, WorkDataDao.class);
/* 154:    */   }
/* 155:    */   
/* 156:    */   private void initMonitorProcess()
/* 157:    */   {
/* 158:140 */     ProcessorCategories.registerProcessor(P30.class, SerialHandler.class, 
/* 159:141 */       P30ComUSBProcessor.class);
/* 160:142 */     ProcessorCategories.registerProcessor(P30.class, SerialHandlerSolaris.class, 
/* 161:143 */       P30ComUSBProcessor.class);
/* 162:144 */     ProcessorCategories.registerProcessor(P30.class, USBHandler.class, 
/* 163:145 */       P30ComUSBProcessor.class);
/* 164:    */   }
/* 165:    */   
/* 166:    */   public void responseAction()
/* 167:    */   {
/* 168:149 */     MouseListener openItemAction = new MouseAdapter()
/* 169:    */     {
/* 170:    */       public void mouseClicked(MouseEvent e)
/* 171:    */       {
/* 172:152 */         if ((e.getClickCount() == 1) && (!e.isMetaDown()) && 
/* 173:153 */           (LinuxMonitorConsole.this.hasInfo))
/* 174:    */         {
/* 175:154 */           LinuxMonitorConsole.this.dialog = EventInfoDialog.getInstance();
/* 176:155 */           LinuxMonitorConsole.this.isopen = true;
/* 177:156 */           LinuxMonitorConsole.this.trayIcon.setPopupMenu(LinuxConsoleTray.popup);
/* 178:157 */           LinuxMonitorConsole.this.dialog.addWindowListener(new WindowAdapter()
/* 179:    */           {
/* 180:    */             public void windowClosing(WindowEvent e)
/* 181:    */             {
/* 182:160 */               LinuxMonitorConsole.this.isopen = false;
/* 183:161 */               LinuxMonitorConsole.this.infoList.clear();
/* 184:162 */               LinuxMonitorConsole.this.dialog.dispose();
/* 185:    */             }
/* 186:164 */           });
/* 187:165 */           LinuxMonitorConsole.this.dialog.jButton1.addActionListener(new ActionListener()
/* 188:    */           {
/* 189:    */             public void actionPerformed(ActionEvent evt)
/* 190:    */             {
/* 191:167 */               LinuxMonitorConsole.this.isopen = false;
/* 192:168 */               LinuxMonitorConsole.this.infoList.clear();
/* 193:169 */               LinuxMonitorConsole.this.dialog.dispose();
/* 194:    */             }
/* 195:171 */           });
/* 196:172 */           LinuxMonitorConsole.this.dialog.setVisible(true);
/* 197:173 */           StringBuffer sb = new StringBuffer();
/* 198:174 */           if (LinuxMonitorConsole.this.infoList.size() > 20) {
/* 199:175 */             for (int i = LinuxMonitorConsole.this.infoList.size() - 20; i < LinuxMonitorConsole.this.infoList.size(); i++) {
/* 200:177 */               sb.append((String)LinuxMonitorConsole.this.infoList.get(i));
/* 201:    */             }
/* 202:    */           } else {
/* 203:180 */             for (int i = 0; i < LinuxMonitorConsole.this.infoList.size(); i++) {
/* 204:181 */               sb.append((String)LinuxMonitorConsole.this.infoList.get(i));
/* 205:    */             }
/* 206:    */           }
/* 207:184 */           LinuxMonitorConsole.this.dialog.getEventTextPanel().setText(sb.toString());
/* 208:185 */           LinuxMonitorConsole.this.dialog.getEventTextPanel().setCaretPosition(
/* 209:186 */             sb.toString().length());
/* 210:187 */           if (LinuxMonitorConsole.this.pvCounter > 0) {
/* 211:188 */             LinuxMonitorConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG_DEVICE);
/* 212:    */           } else {
/* 213:190 */             LinuxMonitorConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG);
/* 214:    */           }
/* 215:192 */           LinuxMonitorConsole.this.hasInfo = false;
/* 216:    */         }
/* 217:    */       }
/* 218:196 */     };
/* 219:197 */     this.trayIcon.addMouseListener(openItemAction);
/* 220:    */   }
/* 221:    */   
/* 222:    */   class EventListener
/* 223:    */     extends Thread
/* 224:    */   {
/* 225:    */     EventListener() {}
/* 226:    */     
/* 227:    */     public void run()
/* 228:    */     {
/* 229:202 */       boolean isUpgrade = false;
/* 230:    */       try
/* 231:    */       {
/* 232:    */         for (;;)
/* 233:    */         {
/* 234:205 */           String rec = LinuxMonitorConsole.this.udpServerSocket.receive().trim();
/* 235:207 */           if (rec.startsWith("(Event:"))
/* 236:    */           {
/* 237:208 */             String event = rec.substring(7, 
/* 238:209 */               rec.lastIndexOf("13"));
/* 239:    */             
/* 240:    */ 
/* 241:    */ 
/* 242:    */ 
/* 243:    */ 
/* 244:215 */             String eventaddn = event + "\n";
/* 245:    */             
/* 246:217 */             String startEvent = eventaddn.substring(0, 
/* 247:218 */               eventaddn.lastIndexOf("]") + 1);
/* 248:219 */             String endEvent = eventaddn.substring(
/* 249:220 */               eventaddn.lastIndexOf("]") + 2);
/* 250:221 */             LinuxMonitorConsole.this.infoList
/* 251:222 */               .add(startEvent + "\n" + "     " + endEvent);
/* 252:223 */             if (LinuxMonitorConsole.this.isopen)
/* 253:    */             {
/* 254:224 */               StringBuffer sb = new StringBuffer();
/* 255:232 */               for (int i = 0; i < LinuxMonitorConsole.this.infoList.size(); i++) {
/* 256:233 */                 sb.append((String)LinuxMonitorConsole.this.infoList.get(i));
/* 257:    */               }
/* 258:236 */               LinuxMonitorConsole.this.dialog.getEventTextPanel().setText(
/* 259:237 */                 sb.toString());
/* 260:238 */               LinuxMonitorConsole.this.dialog.getEventTextPanel().setCaretPosition(
/* 261:239 */                 LinuxMonitorConsole.this.dialog.getEventTextPanel().getText()
/* 262:240 */                 .length());
/* 263:    */             }
/* 264:    */             else
/* 265:    */             {
/* 266:242 */               if (LinuxMonitorConsole.this.pvCounter > 0) {
/* 267:243 */                 LinuxMonitorConsole.this.trayIcon.setImage(Constants.HASEVENT_DEVICE);
/* 268:    */               } else {
/* 269:245 */                 LinuxMonitorConsole.this.trayIcon.setImage(Constants.HASEVENT);
/* 270:    */               }
/* 271:247 */               LinuxMonitorConsole.this.hasInfo = true;
/* 272:248 */               LinuxMonitorConsole.this.trayIcon.setPopupMenu(null);
/* 273:    */             }
/* 274:    */           }
/* 275:250 */           else if (rec.startsWith("(PVCount:"))
/* 276:    */           {
/* 277:    */             try
/* 278:    */             {
/* 279:252 */               String count = rec.substring(9);
/* 280:253 */               LinuxMonitorConsole.this.pvCounter = Integer.parseInt(count);
/* 281:254 */               if (LinuxMonitorConsole.this.pvCounter > 0)
/* 282:    */               {
/* 283:255 */                 if ((LinuxMonitorConsole.this.hasInfo) && (!LinuxMonitorConsole.this.isopen))
/* 284:    */                 {
/* 285:256 */                   LinuxMonitorConsole.this.trayIcon.setImage(Constants.HASEVENT_DEVICE); continue;
/* 286:    */                 }
/* 287:258 */                 LinuxMonitorConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG_DEVICE); continue;
/* 288:    */               }
/* 289:261 */               if ((LinuxMonitorConsole.this.hasInfo) && (!LinuxMonitorConsole.this.isopen))
/* 290:    */               {
/* 291:262 */                 LinuxMonitorConsole.this.trayIcon.setImage(Constants.HASEVENT); continue;
/* 292:    */               }
/* 293:264 */               LinuxMonitorConsole.this.trayIcon.setImage(Constants.CONNECTEDIMG);
/* 294:    */             }
/* 295:    */             catch (Exception localException1) {}
/* 296:    */           }
/* 297:    */           else
/* 298:    */           {
/* 299:269 */             if (rec.startsWith("(exit:myself"))
/* 300:    */             {
/* 301:270 */               SystemEnv.stoped = 0;
/* 302:271 */               SystemEnv.stopping = true;
/* 303:272 */               break;
/* 304:    */             }
/* 305:273 */             if (rec.startsWith("(exit:upgrade"))
/* 306:    */             {
/* 307:274 */               isUpgrade = true;
/* 308:275 */               SystemEnv.stoped = 0;
/* 309:276 */               SystemEnv.stopping = true;
/* 310:277 */               break;
/* 311:    */             }
/* 312:278 */             if (rec.equals("OPENPAGE"))
/* 313:    */             {
/* 314:279 */               MainJFrame.getNewInstance().setVisible(true);
/* 315:280 */               MainJFrame.getNewInstance().setState(0);
/* 316:    */             }
/* 317:    */           }
/* 318:    */         }
/* 319:    */       }
/* 320:    */       catch (Exception localException2) {}
/* 321:285 */       if (LinuxMonitorConsole.this.udpServerSocket != null) {
/* 322:286 */         LinuxMonitorConsole.this.udpServerSocket.close();
/* 323:    */       }
/* 324:288 */       int iexit = 0;
/* 325:289 */       while (SystemEnv.stoped < 3)
/* 326:    */       {
/* 327:    */         try
/* 328:    */         {
/* 329:291 */           Thread.sleep(1000L);
/* 330:    */         }
/* 331:    */         catch (Exception e)
/* 332:    */         {
/* 333:293 */           e.printStackTrace();
/* 334:    */         }
/* 335:295 */         iexit++;
/* 336:296 */         if (iexit > 20) {
/* 337:    */           break;
/* 338:    */         }
/* 339:299 */         System.out.println("stopping" + SystemEnv.stoped);
/* 340:    */       }
/* 341:301 */       System.out.println("stopped ok");
/* 342:302 */       if (isUpgrade) {
/* 343:303 */         StartUpgrade.runUpgrade();
/* 344:    */       }
/* 345:305 */       System.exit(0);
/* 346:    */     }
/* 347:    */   }
/* 348:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.console.linux.LinuxMonitorConsole
 * JD-Core Version:    0.7.0.1
 */