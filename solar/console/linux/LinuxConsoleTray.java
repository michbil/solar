/*   1:    */ package cn.com.voltronic.solar.console.linux;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.socket.UdpClient;
/*   8:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   9:    */ import cn.com.voltronic.solar.upgrade.StartUpgrade;
/*  10:    */ import cn.com.voltronic.solar.view.ConfigFrame;
/*  11:    */ import cn.com.voltronic.solar.view.MainJFrame;
/*  12:    */ import java.awt.AWTException;
/*  13:    */ import java.awt.Menu;
/*  14:    */ import java.awt.MenuItem;
/*  15:    */ import java.awt.PopupMenu;
/*  16:    */ import java.awt.SystemTray;
/*  17:    */ import java.awt.TrayIcon;
/*  18:    */ import java.awt.event.ActionEvent;
/*  19:    */ import java.awt.event.ActionListener;
/*  20:    */ 
/*  21:    */ public class LinuxConsoleTray
/*  22:    */   extends SolarPowerTray
/*  23:    */ {
/*  24:    */   private static final long serialVersionUID = -6566853073476480618L;
/*  25:    */   public static PopupMenu popup;
/*  26:    */   private MenuItem configItem;
/*  27:    */   private Menu updateItem;
/*  28:    */   public MenuItem updateOnline;
/*  29:    */   public MenuItem updateByUser;
/*  30:    */   private MenuItem openItem;
/*  31:    */   private MenuItem exitItem;
/*  32:    */   
/*  33:    */   public LinuxConsoleTray()
/*  34:    */   {
/*  35:    */     try
/*  36:    */     {
/*  37: 39 */       initTray();
/*  38:    */       
/*  39: 41 */       String language = "en_US";
/*  40:    */       try
/*  41:    */       {
/*  42: 43 */         language = GlobalVariables.globalConfig.getLanguage();
/*  43:    */       }
/*  44:    */       catch (Exception e)
/*  45:    */       {
/*  46: 45 */         e.printStackTrace();
/*  47:    */       }
/*  48: 47 */       changeLanguage(language);
/*  49:    */     }
/*  50:    */     catch (Exception e)
/*  51:    */     {
/*  52: 49 */       e.printStackTrace();
/*  53:    */     }
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void initTray()
/*  57:    */     throws AWTException
/*  58:    */   {
/*  59: 54 */     if (SystemTray.isSupported()) {
/*  60:    */       try
/*  61:    */       {
/*  62: 56 */         this.tray = SystemTray.getSystemTray();
/*  63: 57 */         popup = new PopupMenu();
/*  64: 58 */         initMenu();
/*  65: 59 */         String softwareName = GlobalVariables.customerConfig
/*  66: 60 */           .getCustomerName();
/*  67: 61 */         trayIcon = new TrayIcon(Constants.CONNECTEDIMG, softwareName, 
/*  68: 62 */           popup);
/*  69: 63 */         addToSystemTray();
/*  70:    */       }
/*  71:    */       catch (Exception e)
/*  72:    */       {
/*  73: 65 */         e.printStackTrace();
/*  74:    */       }
/*  75:    */     }
/*  76:    */   }
/*  77:    */   
/*  78:    */   public void addToSystemTray()
/*  79:    */   {
/*  80:    */     try
/*  81:    */     {
/*  82: 75 */       this.tray.remove(trayIcon);
/*  83: 76 */       this.tray.add(trayIcon);
/*  84:    */     }
/*  85:    */     catch (AWTException e)
/*  86:    */     {
/*  87: 78 */       e.printStackTrace();
/*  88:    */     }
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void initMenu()
/*  92:    */     throws Exception
/*  93:    */   {
/*  94: 83 */     this.configItem = new MenuItem("Configuration");
/*  95: 84 */     this.updateItem = new Menu("Software Upgrade");
/*  96: 85 */     this.updateOnline = new MenuItem("Online Upgrade");
/*  97: 86 */     this.updateByUser = new MenuItem("Manually Upgrade");
/*  98: 87 */     this.updateItem.add(this.updateOnline);
/*  99: 88 */     this.updateItem.add(this.updateByUser);
/* 100: 89 */     this.openItem = new MenuItem("Open Monitor");
/* 101: 90 */     this.exitItem = new MenuItem("Exit");
/* 102: 91 */     popup.add(this.configItem);
/* 103: 92 */     popup.add(this.updateItem);
/* 104: 93 */     popup.add("-");
/* 105: 94 */     popup.add(this.openItem);
/* 106: 95 */     popup.add("-");
/* 107: 96 */     popup.add(this.exitItem);
/* 108:    */     
/* 109: 98 */     this.configItem.addActionListener(new ActionListener()
/* 110:    */     {
/* 111:    */       public void actionPerformed(ActionEvent e)
/* 112:    */       {
/* 113:100 */         ConfigFrame.getInstance();
/* 114:    */       }
/* 115:103 */     });
/* 116:104 */     this.updateOnline.addActionListener(new ActionListener()
/* 117:    */     {
/* 118:    */       public void actionPerformed(ActionEvent e)
/* 119:    */       {
/* 120:106 */         StartUpgrade start = new StartUpgrade();
/* 121:107 */         start.updateOnline();
/* 122:    */       }
/* 123:110 */     });
/* 124:111 */     this.updateByUser.addActionListener(new ActionListener()
/* 125:    */     {
/* 126:    */       public void actionPerformed(ActionEvent e)
/* 127:    */       {
/* 128:113 */         StartUpgrade start = new StartUpgrade();
/* 129:114 */         start.updateByUser();
/* 130:    */       }
/* 131:117 */     });
/* 132:118 */     this.openItem.addActionListener(new ActionListener()
/* 133:    */     {
/* 134:    */       public void actionPerformed(ActionEvent e)
/* 135:    */       {
/* 136:120 */         MainJFrame.getNewInstance().setVisible(true);
/* 137:121 */         MainJFrame.getNewInstance().setState(0);
/* 138:    */       }
/* 139:124 */     });
/* 140:125 */     this.exitItem.addActionListener(new ActionListener()
/* 141:    */     {
/* 142:    */       public void actionPerformed(ActionEvent e)
/* 143:    */       {
/* 144:127 */         UdpClient client = null;
/* 145:128 */         String port = "38694";
/* 146:    */         try
/* 147:    */         {
/* 148:130 */           port = GlobalVariables.globalConfig.getUdpPort();
/* 149:    */         }
/* 150:    */         catch (Exception e1)
/* 151:    */         {
/* 152:132 */           e1.printStackTrace();
/* 153:    */         }
/* 154:    */         try
/* 155:    */         {
/* 156:135 */           client = new UdpClient("localhost", port);
/* 157:136 */           client.send("(exit:myself");
/* 158:    */         }
/* 159:    */         catch (Exception ex)
/* 160:    */         {
/* 161:138 */           ex.printStackTrace();
/* 162:    */         }
/* 163:    */         finally
/* 164:    */         {
/* 165:140 */           if (client != null) {
/* 166:141 */             client.disconnect();
/* 167:    */           }
/* 168:    */         }
/* 169:    */       }
/* 170:    */     });
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void refreshUpgrade(boolean online, boolean manually)
/* 174:    */   {
/* 175:150 */     this.updateOnline.setEnabled(online);
/* 176:151 */     this.updateByUser.setEnabled(manually);
/* 177:    */   }
/* 178:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.console.linux.LinuxConsoleTray
 * JD-Core Version:    0.7.0.1
 */