/*   1:    */ package cn.com.voltronic.solar.upgrade;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.configure.UpgradeConfig;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.socket.SystemTrayUDPClient;
/*   8:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   9:    */ import cn.com.voltronic.solar.util.ReadDatFile;
/*  10:    */ import cn.com.voltronic.solar.util.ReadTempDatFile;
/*  11:    */ import cn.com.voltronic.solar.util.VolUtil;
/*  12:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  13:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  14:    */ import cn.com.voltronic.solar.view.component.AAPanel;
/*  15:    */ import cn.com.voltronic.solar.view.component.AATextField;
/*  16:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  17:    */ import java.io.File;
/*  18:    */ import java.io.IOException;
/*  19:    */ import javax.swing.JFileChooser;
/*  20:    */ import javax.swing.JFrame;
/*  21:    */ import javax.swing.filechooser.FileFilter;
/*  22:    */ 
/*  23:    */ public abstract class UpByUserDialog
/*  24:    */   extends JFrame
/*  25:    */ {
/*  26:    */   private static final long serialVersionUID = 2002701722574427113L;
/*  27:    */   protected AAButton browser;
/*  28:    */   protected AAButton update;
/*  29:    */   protected AALabel selectFile;
/*  30:    */   protected AALabel note;
/*  31:    */   protected AAPanel jPanel1;
/*  32:    */   protected AATextField filepath;
/*  33:    */   protected JFileChooser fDialog;
/*  34:    */   protected String filepathStr;
/*  35:    */   
/*  36:    */   public abstract void initComponents();
/*  37:    */   
/*  38:    */   protected void updateAction()
/*  39:    */   {
/*  40: 46 */     String oldFileName = "";
/*  41: 47 */     float oldLength = 0.0F;
/*  42: 48 */     long oldTime = 0L;
/*  43:    */     try
/*  44:    */     {
/*  45: 50 */       oldFileName = ReadDatFile.getString("fileName");
/*  46: 51 */       oldLength = Float.parseFloat(ReadDatFile.getString("fileSize"));
/*  47: 52 */       oldTime = Long.parseLong(ReadDatFile.getString("lastModified"));
/*  48:    */     }
/*  49:    */     catch (Exception localException1) {}
/*  50: 55 */     File file = new File(this.filepathStr);
/*  51: 56 */     String fileName = file.getName();
/*  52: 57 */     long length = file.length();
/*  53: 58 */     long time = file.lastModified();
/*  54: 59 */     if ((fileName.equals(oldFileName)) && ((float)length == oldLength) && 
/*  55: 60 */       (time == oldTime))
/*  56:    */     {
/*  57: 61 */       DisplayMessage.showInfoDialog("message.alreadyUpgrade");
/*  58: 62 */       UpgradeUtil.refreshStatus(true, true);
/*  59: 63 */       return;
/*  60:    */     }
/*  61: 65 */     String fromZipPath = this.filepathStr.trim();
/*  62:    */     try
/*  63:    */     {
/*  64: 68 */       ReadTempDatFile.updateProperty("fileName", fileName.trim());
/*  65: 69 */       ReadTempDatFile.updateProperty("fileSize", length);
/*  66: 70 */       ReadTempDatFile.updateProperty("lastModified", time);
/*  67: 71 */       GlobalVariables.upgradeConfig.setInstallPath(fromZipPath.trim());
/*  68:    */     }
/*  69:    */     catch (Exception e)
/*  70:    */     {
/*  71: 73 */       e.printStackTrace();
/*  72:    */     }
/*  73:    */     try
/*  74:    */     {
/*  75: 76 */       ConfigureTools.updateProperties(GlobalVariables.upgradeConfig);
/*  76: 77 */       ConfigureTools.wrapProperties(GlobalVariables.upgradeConfig);
/*  77:    */     }
/*  78:    */     catch (Exception ex)
/*  79:    */     {
/*  80: 79 */       ex.printStackTrace();
/*  81:    */     }
/*  82: 82 */     String exitMask = "(exit:upgrade";
/*  83: 83 */     int port = VolUtil.parseInt("38694");
/*  84:    */     try
/*  85:    */     {
/*  86: 85 */       port = VolUtil.parseInt(GlobalVariables.globalConfig.getUdpPort());
/*  87:    */     }
/*  88:    */     catch (Exception e)
/*  89:    */     {
/*  90: 87 */       e.printStackTrace();
/*  91:    */     }
/*  92:    */     try
/*  93:    */     {
/*  94: 90 */       SystemTrayUDPClient client = new SystemTrayUDPClient(
/*  95: 91 */         "localhost", port);
/*  96: 92 */       client.send(exitMask.getBytes());
/*  97:    */     }
/*  98:    */     catch (IOException e1)
/*  99:    */     {
/* 100: 94 */       e1.printStackTrace();
/* 101:    */       
/* 102: 96 */       StartUpgrade.runUpgrade();
/* 103: 97 */       System.exit(0);
/* 104:    */     }
/* 105: 99 */     dispose();
/* 106:    */   }
/* 107:    */   
/* 108:    */   protected int getFileDialog()
/* 109:    */   {
/* 110:104 */     this.fDialog = new JFileChooser();
/* 111:105 */     this.fDialog.setFileFilter(new FileFilter()
/* 112:    */     {
/* 113:    */       public boolean accept(File f)
/* 114:    */       {
/* 115:108 */         if (Constants.IS_OS_WINDOWS)
/* 116:    */         {
/* 117:109 */           if ((f.getName().endsWith(".zip")) || (f.isDirectory())) {
/* 118:110 */             return true;
/* 119:    */           }
/* 120:    */         }
/* 121:112 */         else if ((Constants.IS_OS_LINUX) || (Constants.IS_OS_SOLARIS))
/* 122:    */         {
/* 123:113 */           if ((f.getName().endsWith(".tar.gz")) || (f.isDirectory())) {
/* 124:114 */             return true;
/* 125:    */           }
/* 126:    */         }
/* 127:117 */         else if ((f.getName().endsWith(".zip")) || (f.isDirectory())) {
/* 128:118 */           return true;
/* 129:    */         }
/* 130:121 */         return false;
/* 131:    */       }
/* 132:    */       
/* 133:    */       public String getDescription()
/* 134:    */       {
/* 135:125 */         if (Constants.IS_OS_WINDOWS) {
/* 136:126 */           return "*.zip";
/* 137:    */         }
/* 138:127 */         if ((Constants.IS_OS_LINUX) || (Constants.IS_OS_SOLARIS)) {
/* 139:128 */           return "*.tar.gz";
/* 140:    */         }
/* 141:130 */         return "*.zip";
/* 142:    */       }
/* 143:133 */     });
/* 144:134 */     int result = this.fDialog.showOpenDialog(this);
/* 145:135 */     return result;
/* 146:    */   }
/* 147:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.UpByUserDialog
 * JD-Core Version:    0.7.0.1
 */