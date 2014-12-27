/*   1:    */ package cn.com.voltronic.solar.upgrade;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.configure.UpgradeConfig;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.socket.SystemTrayUDPClient;
/*   8:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   9:    */ import cn.com.voltronic.solar.util.MyMD5;
/*  10:    */ import cn.com.voltronic.solar.util.ReadDatFile;
/*  11:    */ import cn.com.voltronic.solar.util.ReadTempDatFile;
/*  12:    */ import cn.com.voltronic.solar.util.VolUtil;
/*  13:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  14:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  15:    */ import com.sun.org.apache.xml.internal.security.utils.Base64;
/*  16:    */ import java.awt.event.WindowAdapter;
/*  17:    */ import java.awt.event.WindowEvent;
/*  18:    */ import java.io.BufferedInputStream;
/*  19:    */ import java.io.File;
/*  20:    */ import java.io.FileNotFoundException;
/*  21:    */ import java.io.FileOutputStream;
/*  22:    */ import java.io.IOException;
/*  23:    */ import java.io.InputStream;
/*  24:    */ import java.io.PrintStream;
/*  25:    */ import java.net.HttpURLConnection;
/*  26:    */ import java.net.URL;
/*  27:    */ import java.util.Properties;
/*  28:    */ import javax.swing.JProgressBar;
/*  29:    */ import org.apache.log4j.Logger;
/*  30:    */ 
/*  31:    */ public class Download
/*  32:    */   extends Thread
/*  33:    */ {
/*  34: 40 */   private Logger logger = Logger.getLogger(Download.class);
/*  35: 42 */   private static int BUFFER_SIZE = 8192;
/*  36:    */   private String fromDir;
/*  37:    */   private String toDir;
/*  38: 45 */   private DownloadDialog downloadDialog = null;
/*  39: 46 */   public volatile boolean isStop = false;
/*  40: 47 */   public volatile boolean waitting = false;
/*  41: 48 */   private HttpURLConnection httpUrl = null;
/*  42: 49 */   private InputStream inputStream = null;
/*  43: 50 */   private FileOutputStream fos = null;
/*  44: 51 */   private BufferedInputStream bis = null;
/*  45: 52 */   private String fileName = "";
/*  46:    */   private float length;
/*  47:    */   private long time;
/*  48:    */   
/*  49:    */   public Download(String fromDir, String toDir)
/*  50:    */   {
/*  51: 57 */     this.fromDir = fromDir;
/*  52: 58 */     this.toDir = toDir;
/*  53:    */   }
/*  54:    */   
/*  55:    */   private boolean openURL()
/*  56:    */   {
/*  57: 62 */     boolean result = true;
/*  58: 63 */     URL url = null;
/*  59:    */     try
/*  60:    */     {
/*  61: 65 */       url = new URL(this.fromDir);
/*  62: 66 */       boolean useProxy = GlobalVariables.upgradeConfig.isUseProxy();
/*  63: 67 */       String proxyIP = GlobalVariables.upgradeConfig.getProxyIp();
/*  64: 68 */       String proxyPort = GlobalVariables.upgradeConfig.getProxyPort();
/*  65: 69 */       if (useProxy)
/*  66:    */       {
/*  67: 70 */         Properties systemProperties = System.getProperties();
/*  68:    */         
/*  69: 72 */         systemProperties.setProperty("http.proxyHost", proxyIP);
/*  70: 73 */         systemProperties.setProperty("http.proxyPort", proxyPort);
/*  71:    */       }
/*  72: 75 */       this.httpUrl = ((HttpURLConnection)url.openConnection());
/*  73:    */       
/*  74: 77 */       boolean useAuth = GlobalVariables.upgradeConfig.isUseAuth();
/*  75: 78 */       if ((useProxy) && (useAuth))
/*  76:    */       {
/*  77: 79 */         String username = GlobalVariables.upgradeConfig.getAuthName();
/*  78: 80 */         String pwd = MyMD5.restore(GlobalVariables.upgradeConfig
/*  79: 81 */           .getAuthPassword());
/*  80: 82 */         String password = username + ":" + pwd;
/*  81: 83 */         String encoded = Base64.encode(password.getBytes());
/*  82: 84 */         this.httpUrl.setRequestProperty("Proxy-Authorization", "Basic " + 
/*  83: 85 */           encoded);
/*  84:    */       }
/*  85: 87 */       this.httpUrl.setConnectTimeout(20000);
/*  86: 88 */       this.httpUrl.connect();
/*  87: 89 */       this.inputStream = this.httpUrl.getInputStream();
/*  88:    */     }
/*  89:    */     catch (FileNotFoundException xx)
/*  90:    */     {
/*  91: 91 */       DisplayMessage.showInfoDialog("message.alreadyUpgrade");
/*  92: 92 */       UpgradeUtil.refreshStatus(true, true);
/*  93: 93 */       result = false;
/*  94:    */     }
/*  95:    */     catch (Exception ex)
/*  96:    */     {
/*  97: 95 */       ex.printStackTrace();
/*  98: 96 */       DisplayMessage.showErrorDialog("message.notcommunicate");
/*  99: 97 */       UpgradeUtil.refreshStatus(true, true);
/* 100: 98 */       result = false;
/* 101:    */     }
/* 102:100 */     return result;
/* 103:    */   }
/* 104:    */   
/* 105:    */   private boolean isNewUpdatePacket()
/* 106:    */   {
/* 107:    */     try
/* 108:    */     {
/* 109:105 */       this.fromDir = this.fromDir.trim().replace("\\", "/");
/* 110:106 */       this.fileName = this.fromDir.substring(this.fromDir.lastIndexOf("/") + 1);
/* 111:107 */       this.length = this.httpUrl.getContentLength();
/* 112:108 */       this.time = this.httpUrl.getLastModified();
/* 113:    */     }
/* 114:    */     catch (Exception ex)
/* 115:    */     {
/* 116:110 */       this.logger.info("------getMessage----------" + ex.getMessage());
/* 117:111 */       ex.printStackTrace();
/* 118:    */     }
/* 119:114 */     String oldFileName = "";
/* 120:115 */     float oldLength = 0.0F;
/* 121:116 */     long oldTime = 0L;
/* 122:    */     try
/* 123:    */     {
/* 124:118 */       oldFileName = ReadDatFile.getString("fileName");
/* 125:119 */       oldLength = Float.parseFloat(ReadDatFile.getString("fileSize"));
/* 126:120 */       oldTime = Long.parseLong(ReadDatFile.getString("lastModified"));
/* 127:    */     }
/* 128:    */     catch (Exception localException1) {}
/* 129:123 */     if ((this.fileName.equals(oldFileName)) && (this.length == oldLength) && 
/* 130:124 */       (this.time == oldTime)) {
/* 131:125 */       return true;
/* 132:    */     }
/* 133:127 */     return false;
/* 134:    */   }
/* 135:    */   
/* 136:    */   private void createdownloaddlg()
/* 137:    */   {
/* 138:132 */     if (System.getProperty("java.version").startsWith("1.6"))
/* 139:    */     {
/* 140:133 */       this.downloadDialog = new DownloadDialog16();
/* 141:    */     }
/* 142:    */     else
/* 143:    */     {
/* 144:135 */       this.downloadDialog = new DownloadDialog15();
/* 145:136 */       this.downloadDialog.setSize(380, 120);
/* 146:    */     }
/* 147:138 */     this.downloadDialog.setLocationRelativeTo(null);
/* 148:139 */     this.downloadDialog.setTitle("Download");
/* 149:140 */     this.downloadDialog.setVisible(true);
/* 150:141 */     this.downloadDialog
/* 151:142 */       .setDefaultCloseOperation(0);
/* 152:143 */     this.downloadDialog.addWindowListener(new WindowAdapter()
/* 153:    */     {
/* 154:    */       public void windowClosing(WindowEvent e)
/* 155:    */       {
/* 156:146 */         Download.this.waitting = true;
/* 157:147 */         int re = DisplayMessage.showConfirmDialog("message.stopnow", 
/* 158:148 */           "message.confirm");
/* 159:149 */         if (re == 0)
/* 160:    */         {
/* 161:150 */           Download.this.isStop = true;
/* 162:    */           
/* 163:152 */           DisplayMessage.showWarningDialog("message.interruptUpgrade");
/* 164:153 */           UpgradeUtil.refreshStatus(true, true);
/* 165:    */         }
/* 166:156 */         Download.this.waitting = false;
/* 167:    */       }
/* 168:    */     });
/* 169:    */   }
/* 170:    */   
/* 171:    */   private boolean download()
/* 172:    */   {
/* 173:162 */     boolean result = true;
/* 174:163 */     File newFile = null;
/* 175:    */     try
/* 176:    */     {
/* 177:165 */       int size = 0;
/* 178:166 */       byte[] buf = new byte[BUFFER_SIZE];
/* 179:167 */       File f = new File(this.toDir);
/* 180:168 */       if (!f.exists()) {
/* 181:169 */         f.mkdirs();
/* 182:    */       }
/* 183:171 */       newFile = new File(this.toDir + File.separator + this.fileName);
/* 184:172 */       this.bis = new BufferedInputStream(this.inputStream);
/* 185:173 */       this.fos = new FileOutputStream(newFile);
/* 186:174 */       float readLength = 0.0F;
/* 187:175 */       this.downloadDialog.note.setText("message.startdownload");
/* 188:176 */       while (!this.isStop) {
/* 189:178 */         if (this.waitting)
/* 190:    */         {
/* 191:179 */           Thread.sleep(100L);
/* 192:    */         }
/* 193:    */         else
/* 194:    */         {
/* 195:182 */           if ((size = this.bis.read(buf)) == -1) {
/* 196:    */             break;
/* 197:    */           }
/* 198:183 */           this.downloadDialog.note.setText("message.download");
/* 199:184 */           this.fos.write(buf, 0, size);
/* 200:185 */           readLength += size;
/* 201:186 */           float downloadLen = readLength / this.length * 100.0F;
/* 202:187 */           System.out.println("message.downloadof" + 
/* 203:188 */             Math.round(downloadLen) + "%");
/* 204:189 */           this.downloadDialog.progress.setValue(Math.round(downloadLen));
/* 205:    */         }
/* 206:    */       }
/* 207:    */     }
/* 208:    */     catch (Exception ex)
/* 209:    */     {
/* 210:195 */       ex.printStackTrace();
/* 211:196 */       result = false;
/* 212:    */       try
/* 213:    */       {
/* 214:199 */         if (this.fos != null) {
/* 215:200 */           this.fos.close();
/* 216:    */         }
/* 217:202 */         if (this.bis != null) {
/* 218:203 */           this.bis.close();
/* 219:    */         }
/* 220:205 */         if (this.httpUrl != null) {
/* 221:206 */           this.httpUrl.disconnect();
/* 222:    */         }
/* 223:    */       }
/* 224:    */       catch (Exception e)
/* 225:    */       {
/* 226:209 */         result = false;
/* 227:    */       }
/* 228:    */     }
/* 229:    */     finally
/* 230:    */     {
/* 231:    */       try
/* 232:    */       {
/* 233:199 */         if (this.fos != null) {
/* 234:200 */           this.fos.close();
/* 235:    */         }
/* 236:202 */         if (this.bis != null) {
/* 237:203 */           this.bis.close();
/* 238:    */         }
/* 239:205 */         if (this.httpUrl != null) {
/* 240:206 */           this.httpUrl.disconnect();
/* 241:    */         }
/* 242:    */       }
/* 243:    */       catch (Exception e)
/* 244:    */       {
/* 245:209 */         result = false;
/* 246:    */       }
/* 247:    */     }
/* 248:212 */     newFile.setLastModified(this.time);
/* 249:213 */     return result;
/* 250:    */   }
/* 251:    */   
/* 252:    */   private boolean isTruethURL()
/* 253:    */   {
/* 254:217 */     if ((this.fromDir == null) || ("".equals(this.fromDir)))
/* 255:    */     {
/* 256:218 */       DisplayMessage.showErrorDialog("message.connectionfailure");
/* 257:219 */       UpgradeUtil.refreshStatus(true, true);
/* 258:220 */       return false;
/* 259:    */     }
/* 260:222 */     if (Constants.IS_OS_WINDOWS)
/* 261:    */     {
/* 262:223 */       if (!this.fromDir.endsWith(".zip"))
/* 263:    */       {
/* 264:224 */         DisplayMessage.showErrorDialog("message.notzipfile");
/* 265:225 */         UpgradeUtil.refreshStatus(true, true);
/* 266:226 */         return false;
/* 267:    */       }
/* 268:    */     }
/* 269:228 */     else if (Constants.IS_OS_LINUX)
/* 270:    */     {
/* 271:229 */       if (!this.fromDir.endsWith(".tar.gz"))
/* 272:    */       {
/* 273:230 */         DisplayMessage.showErrorDialog("message.notzipfile");
/* 274:231 */         UpgradeUtil.refreshStatus(true, true);
/* 275:232 */         return false;
/* 276:    */       }
/* 277:    */     }
/* 278:234 */     else if (Constants.IS_OS_SOLARIS)
/* 279:    */     {
/* 280:235 */       if (!this.fromDir.endsWith(".tar.gz"))
/* 281:    */       {
/* 282:236 */         DisplayMessage.showErrorDialog("message.notzipfile");
/* 283:237 */         UpgradeUtil.refreshStatus(true, true);
/* 284:238 */         return false;
/* 285:    */       }
/* 286:    */     }
/* 287:240 */     else if (((Constants.IS_OS_MAC) || (Constants.IS_OS_MAC_OSX)) && 
/* 288:241 */       (!this.fromDir.endsWith(".zip")))
/* 289:    */     {
/* 290:242 */       DisplayMessage.showErrorDialog("message.notzipfile");
/* 291:243 */       UpgradeUtil.refreshStatus(true, true);
/* 292:244 */       return false;
/* 293:    */     }
/* 294:247 */     return true;
/* 295:    */   }
/* 296:    */   
/* 297:    */   public void run()
/* 298:    */   {
/* 299:252 */     if ((isTruethURL()) && 
/* 300:253 */       (openURL())) {
/* 301:254 */       if (!isNewUpdatePacket())
/* 302:    */       {
/* 303:255 */         createdownloaddlg();
/* 304:256 */         if (download())
/* 305:    */         {
/* 306:257 */           if (!this.isStop)
/* 307:    */           {
/* 308:259 */             this.downloadDialog.note.setText("message.downloadfinish");
/* 309:260 */             String fromZipPath = this.toDir + File.separator + 
/* 310:261 */               this.fileName;
/* 311:    */             try
/* 312:    */             {
/* 313:264 */               ReadTempDatFile.updateProperty("fileName", 
/* 314:265 */                 this.fileName.trim());
/* 315:266 */               ReadTempDatFile.updateProperty("fileSize", 
/* 316:267 */                 this.length);
/* 317:268 */               ReadTempDatFile.updateProperty("lastModified", 
/* 318:269 */                 this.time);
/* 319:270 */               GlobalVariables.upgradeConfig.setInstallPath(fromZipPath.trim());
/* 320:    */             }
/* 321:    */             catch (Exception ex)
/* 322:    */             {
/* 323:272 */               ex.printStackTrace();
/* 324:    */             }
/* 325:    */             try
/* 326:    */             {
/* 327:275 */               ConfigureTools.updateProperties(GlobalVariables.upgradeConfig);
/* 328:276 */               ConfigureTools.wrapProperties(GlobalVariables.upgradeConfig);
/* 329:    */             }
/* 330:    */             catch (Exception ex)
/* 331:    */             {
/* 332:278 */               ex.printStackTrace();
/* 333:    */             }
/* 334:281 */             String exitMask = "(exit:upgrade";
/* 335:282 */             int port = VolUtil.parseInt("38694");
/* 336:    */             try
/* 337:    */             {
/* 338:284 */               port = VolUtil.parseInt(GlobalVariables.globalConfig.getUdpPort());
/* 339:    */             }
/* 340:    */             catch (Exception e)
/* 341:    */             {
/* 342:286 */               e.printStackTrace();
/* 343:    */             }
/* 344:    */             try
/* 345:    */             {
/* 346:289 */               SystemTrayUDPClient client = new SystemTrayUDPClient(
/* 347:290 */                 "localhost", port);
/* 348:291 */               client.send(exitMask.getBytes());
/* 349:    */             }
/* 350:    */             catch (IOException e1)
/* 351:    */             {
/* 352:293 */               e1.printStackTrace();
/* 353:    */               
/* 354:295 */               StartUpgrade.runUpgrade();
/* 355:296 */               System.exit(0);
/* 356:    */             }
/* 357:    */           }
/* 358:    */         }
/* 359:    */         else
/* 360:    */         {
/* 361:300 */           DisplayMessage.showErrorDialog("message.downloadfailure");
/* 362:301 */           this.downloadDialog.dispose();
/* 363:302 */           UpgradeUtil.refreshStatus(true, true);
/* 364:    */         }
/* 365:    */       }
/* 366:    */       else
/* 367:    */       {
/* 368:305 */         DisplayMessage.showInfoDialog("message.alreadyUpgrade");
/* 369:306 */         UpgradeUtil.refreshStatus(true, true);
/* 370:    */       }
/* 371:    */     }
/* 372:    */   }
/* 373:    */   
/* 374:    */   public static void main(String[] args)
/* 375:    */   {
/* 376:315 */     String fromdir = "http://192.168.107.222:18080/examples/windows/updateViewPower.zip";
/* 377:316 */     String todir = "D:/temp";
/* 378:317 */     Download downLoad = new Download(fromdir, todir);
/* 379:318 */     downLoad.fileName = fromdir.trim()
/* 380:319 */       .substring(fromdir.lastIndexOf("/") + 1);
/* 381:320 */     if (downLoad.openURL())
/* 382:    */     {
/* 383:321 */       downLoad.length = downLoad.httpUrl.getContentLength();
/* 384:    */       
/* 385:    */ 
/* 386:324 */       downLoad.createdownloaddlg();
/* 387:325 */       downLoad.download();
/* 388:    */     }
/* 389:    */   }
/* 390:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.Download
 * JD-Core Version:    0.7.0.1
 */