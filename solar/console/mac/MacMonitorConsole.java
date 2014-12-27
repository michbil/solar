/*   1:    */ package cn.com.voltronic.solar.console.mac;
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
/*  30:    */ import java.awt.event.ActionEvent;
/*  31:    */ import java.awt.event.ActionListener;
/*  32:    */ import java.awt.event.WindowAdapter;
/*  33:    */ import java.awt.event.WindowEvent;
/*  34:    */ import java.io.PrintStream;
/*  35:    */ import java.util.ArrayList;
/*  36:    */ import java.util.List;
/*  37:    */ import javax.swing.ImageIcon;
/*  38:    */ import javax.swing.JLabel;
/*  39:    */ import javax.swing.JTextPane;
/*  40:    */ 
/*  41:    */ public class MacMonitorConsole
/*  42:    */ {
/*  43: 40 */   private DatagramServer udpServerSocket = null;
/*  44: 41 */   public EventInfoDialog dialog = null;
/*  45:    */   public volatile List<String> infoList;
/*  46: 43 */   public volatile boolean isopen = false;
/*  47: 44 */   public volatile int pvCounter = 0;
/*  48:    */   public JLabel icon;
/*  49: 46 */   private static SolarPowerTray consoleTray = null;
/*  50:    */   
/*  51:    */   public static SolarPowerTray getConsoleTray()
/*  52:    */   {
/*  53: 49 */     return consoleTray;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void startRun()
/*  57:    */   {
/*  58:    */     try
/*  59:    */     {
/*  60: 54 */       this.udpServerSocket = SystemTrayUDPServer.getServer();
/*  61:    */       try
/*  62:    */       {
/*  63: 56 */         inint();
/*  64:    */       }
/*  65:    */       catch (Exception e)
/*  66:    */       {
/*  67: 58 */         System.err.println("系统初始化失败！");
/*  68: 59 */         if (this.udpServerSocket == null) {
/*  69:    */           return;
/*  70:    */         }
/*  71:    */       }
/*  72: 60 */       this.udpServerSocket.close();
/*  73:    */     }
/*  74:    */     catch (Exception e)
/*  75:    */     {
/*  76: 64 */       System.err.println("托盘已经打开！");
/*  77: 65 */       String port = "38694";
/*  78: 66 */       UdpClient client = null;
/*  79:    */       try
/*  80:    */       {
/*  81: 68 */         port = GlobalVariables.globalConfig.getUdpPort();
/*  82: 69 */         client = new UdpClient("localhost", port);
/*  83: 70 */         client.send("OPENPAGE");
/*  84:    */       }
/*  85:    */       catch (Exception ex)
/*  86:    */       {
/*  87: 72 */         ex.printStackTrace();
/*  88:    */       }
/*  89:    */       finally
/*  90:    */       {
/*  91: 74 */         if (client != null) {
/*  92: 75 */           client.disconnect();
/*  93:    */         }
/*  94:    */       }
/*  95:    */     }
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void inint()
/*  99:    */   {
/* 100: 82 */     GlobalVariables.initConfig(Constants.CONFIG_PATH);
/* 101:    */     
/* 102:    */ 
/* 103: 85 */     initMonitorProcess();
/* 104:    */     
/* 105:    */ 
/* 106: 88 */     consoleTray = new MacMonitorTray();
/* 107:    */     
/* 108: 90 */     PageThread thread = new PageThread();
/* 109: 91 */     thread.start();
/* 110:    */     
/* 111: 93 */     initDaoManager();
/* 112:    */     
/* 113: 95 */     SearchDevice searchdevice = new SearchDevice();
/* 114: 96 */     searchdevice.start();
/* 115:    */     
/* 116:    */ 
/* 117: 99 */     RecordWorkinfo record = new RecordWorkinfo();
/* 118:100 */     record.start();
/* 119:    */     
/* 120:    */ 
/* 121:103 */     PollProcessorStatus pollstatus = new PollProcessorStatus();
/* 122:104 */     pollstatus.setPriority(10);
/* 123:105 */     pollstatus.start();
/* 124:    */     
/* 125:    */ 
/* 126:108 */     this.icon = consoleTray.icon;
/* 127:    */     
/* 128:110 */     boolean useautoDetect = GlobalVariables.upgradeConfig.isAutoUpgrade();
/* 129:111 */     if (useautoDetect)
/* 130:    */     {
/* 131:112 */       AutoDetect autoDetect = new AutoDetect();
/* 132:113 */       autoDetect.setDaemon(true);
/* 133:114 */       autoDetect.start();
/* 134:    */     }
/* 135:116 */     this.infoList = new ArrayList();
/* 136:117 */     EventListener eventListener = new EventListener();
/* 137:118 */     eventListener.start();
/* 138:    */   }
/* 139:    */   
/* 140:    */   public static void main(String[] args)
/* 141:    */   {
/* 142:124 */     MacMonitorConsole console = new MacMonitorConsole();
/* 143:125 */     console.startRun();
/* 144:    */   }
/* 145:    */   
/* 146:    */   private void initDaoManager()
/* 147:    */   {
/* 148:129 */     DaoManager.registerDAO(P30BeanBag.class, WorkDataDao.class);
/* 149:    */   }
/* 150:    */   
/* 151:    */   private void initMonitorProcess()
/* 152:    */   {
/* 153:133 */     ProcessorCategories.registerProcessor(P30.class, SerialHandler.class, 
/* 154:134 */       P30ComUSBProcessor.class);
/* 155:135 */     ProcessorCategories.registerProcessor(P30.class, SerialHandlerSolaris.class, 
/* 156:136 */       P30ComUSBProcessor.class);
/* 157:137 */     ProcessorCategories.registerProcessor(P30.class, USBHandler.class, 
/* 158:138 */       P30ComUSBProcessor.class);
/* 159:    */   }
/* 160:    */   
/* 161:    */   class EventListener
/* 162:    */     extends Thread
/* 163:    */   {
/* 164:    */     EventListener() {}
/* 165:    */     
/* 166:    */     public void run()
/* 167:    */     {
/* 168:143 */       boolean isUpgrade = false;
/* 169:    */       try
/* 170:    */       {
/* 171:    */         for (;;)
/* 172:    */         {
/* 173:146 */           String rec = MacMonitorConsole.this.udpServerSocket.receive().trim();
/* 174:148 */           if (rec.startsWith("(Event:"))
/* 175:    */           {
/* 176:149 */             String event = rec.substring(7, 
/* 177:150 */               rec.lastIndexOf("13"));
/* 178:151 */             String eventaddn = event + "\n";
/* 179:    */             
/* 180:153 */             String startEvent = eventaddn.substring(0, 
/* 181:154 */               eventaddn.lastIndexOf("]") + 1);
/* 182:155 */             String endEvent = eventaddn.substring(
/* 183:156 */               eventaddn.lastIndexOf("]") + 2);
/* 184:157 */             MacMonitorConsole.this.infoList
/* 185:158 */               .add(startEvent + "\n" + "     " + endEvent);
/* 186:159 */             if (!MacMonitorConsole.this.isopen)
/* 187:    */             {
/* 188:160 */               MacMonitorConsole.this.dialog = EventInfoDialog.getInstance();
/* 189:161 */               MacMonitorConsole.this.dialog.setVisible(true);
/* 190:162 */               MacMonitorConsole.this.isopen = true;
/* 191:163 */               MacMonitorConsole.this.dialog.addWindowListener(new WindowAdapter()
/* 192:    */               {
/* 193:    */                 public void windowClosing(WindowEvent e)
/* 194:    */                 {
/* 195:166 */                   MacMonitorConsole.this.isopen = false;
/* 196:167 */                   MacMonitorConsole.this.infoList.clear();
/* 197:168 */                   MacMonitorConsole.this.dialog.dispose();
/* 198:    */                 }
/* 199:170 */               });
/* 200:171 */               MacMonitorConsole.this.dialog.jButton1.addActionListener(new ActionListener()
/* 201:    */               {
/* 202:    */                 public void actionPerformed(ActionEvent evt)
/* 203:    */                 {
/* 204:173 */                   MacMonitorConsole.this.isopen = false;
/* 205:174 */                   MacMonitorConsole.this.infoList.clear();
/* 206:175 */                   MacMonitorConsole.this.dialog.dispose();
/* 207:    */                 }
/* 208:177 */               });
/* 209:178 */               StringBuffer sb = new StringBuffer();
/* 210:179 */               for (int i = 0; i < MacMonitorConsole.this.infoList.size(); i++) {
/* 211:180 */                 sb.append((String)MacMonitorConsole.this.infoList.get(i));
/* 212:    */               }
/* 213:182 */               MacMonitorConsole.this.dialog.getEventTextPanel().setText(
/* 214:183 */                 sb.toString());
/* 215:184 */               MacMonitorConsole.this.dialog.getEventTextPanel().setCaretPosition(
/* 216:185 */                 MacMonitorConsole.this.dialog.getEventTextPanel().getText()
/* 217:186 */                 .length());
/* 218:    */             }
/* 219:    */             else
/* 220:    */             {
/* 221:189 */               StringBuffer sb = new StringBuffer();
/* 222:190 */               for (int i = 0; i < MacMonitorConsole.this.infoList.size(); i++) {
/* 223:191 */                 sb.append((String)MacMonitorConsole.this.infoList.get(i));
/* 224:    */               }
/* 225:193 */               MacMonitorConsole.this.dialog.getEventTextPanel().setText(
/* 226:194 */                 sb.toString());
/* 227:195 */               MacMonitorConsole.this.dialog.getEventTextPanel().setCaretPosition(
/* 228:196 */                 MacMonitorConsole.this.dialog.getEventTextPanel().getText()
/* 229:197 */                 .length());
/* 230:    */             }
/* 231:    */           }
/* 232:199 */           else if (rec.startsWith("(PVCount:"))
/* 233:    */           {
/* 234:    */             try
/* 235:    */             {
/* 236:201 */               String count = rec.substring(9);
/* 237:202 */               MacMonitorConsole.this.pvCounter = Integer.parseInt(count);
/* 238:203 */               if (MacMonitorConsole.this.pvCounter > 0)
/* 239:    */               {
/* 240:204 */                 MacMonitorConsole.this.icon.setIcon(new ImageIcon(Constants.CONNECTEDIMG_DEVICE)); continue;
/* 241:    */               }
/* 242:206 */               MacMonitorConsole.this.icon.setIcon(new ImageIcon(Constants.CONNECTEDIMG));
/* 243:    */             }
/* 244:    */             catch (Exception localException1) {}
/* 245:    */           }
/* 246:    */           else
/* 247:    */           {
/* 248:210 */             if (rec.startsWith("(exit:myself"))
/* 249:    */             {
/* 250:211 */               SystemEnv.stoped = 0;
/* 251:212 */               SystemEnv.stopping = true;
/* 252:213 */               break;
/* 253:    */             }
/* 254:214 */             if (rec.startsWith("(exit:upgrade"))
/* 255:    */             {
/* 256:215 */               isUpgrade = true;
/* 257:216 */               SystemEnv.stoped = 0;
/* 258:217 */               SystemEnv.stopping = true;
/* 259:218 */               break;
/* 260:    */             }
/* 261:219 */             if (rec.equals("OPENPAGE"))
/* 262:    */             {
/* 263:220 */               MainJFrame.getNewInstance().setVisible(true);
/* 264:221 */               MainJFrame.getNewInstance().setState(0);
/* 265:    */             }
/* 266:    */           }
/* 267:    */         }
/* 268:    */       }
/* 269:    */       catch (Exception localException2) {}
/* 270:226 */       if (MacMonitorConsole.this.udpServerSocket != null) {
/* 271:227 */         MacMonitorConsole.this.udpServerSocket.close();
/* 272:    */       }
/* 273:229 */       int iexit = 0;
/* 274:230 */       while (SystemEnv.stoped < 3)
/* 275:    */       {
/* 276:    */         try
/* 277:    */         {
/* 278:232 */           Thread.sleep(1000L);
/* 279:    */         }
/* 280:    */         catch (Exception e)
/* 281:    */         {
/* 282:234 */           e.printStackTrace();
/* 283:    */         }
/* 284:236 */         iexit++;
/* 285:237 */         if (iexit > 20) {
/* 286:    */           break;
/* 287:    */         }
/* 288:240 */         System.out.println("stopping" + SystemEnv.stoped);
/* 289:    */       }
/* 290:242 */       System.out.println("stopped ok");
/* 291:243 */       if (isUpgrade) {
/* 292:244 */         StartUpgrade.runUpgrade();
/* 293:    */       }
/* 294:246 */       System.exit(0);
/* 295:    */     }
/* 296:    */   }
/* 297:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.console.mac.MacMonitorConsole
 * JD-Core Version:    0.7.0.1
 */