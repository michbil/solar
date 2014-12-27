/*   1:    */ package cn.com.voltronic.solar.configure;
/*   2:    */ 
/*   3:    */ public class UpgradeConfig
/*   4:    */   implements IConfigBean
/*   5:    */ {
/*   6:    */   private String fromURL;
/*   7:    */   private String installPath;
/*   8:    */   private String savePath;
/*   9:    */   
/*  10:    */   public String getInstallPath()
/*  11:    */   {
/*  12: 16 */     return this.installPath;
/*  13:    */   }
/*  14:    */   
/*  15:    */   public void setInstallPath(String installPath)
/*  16:    */   {
/*  17: 20 */     this.installPath = installPath;
/*  18:    */   }
/*  19:    */   
/*  20: 25 */   private boolean autoUpgrade = true;
/*  21:    */   private boolean useProxy;
/*  22:    */   private String proxyIp;
/*  23:    */   private String proxyPort;
/*  24:    */   private boolean useAuth;
/*  25:    */   private String authName;
/*  26:    */   private String authPassword;
/*  27:    */   
/*  28:    */   public String getFromURL()
/*  29:    */   {
/*  30: 40 */     return this.fromURL;
/*  31:    */   }
/*  32:    */   
/*  33:    */   public void setFromURL(String fromURL)
/*  34:    */   {
/*  35: 44 */     this.fromURL = fromURL;
/*  36:    */   }
/*  37:    */   
/*  38:    */   public String getSavePath()
/*  39:    */   {
/*  40: 48 */     return this.savePath;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public void setSavePath(String savePath)
/*  44:    */   {
/*  45: 52 */     this.savePath = savePath;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public boolean isAutoUpgrade()
/*  49:    */   {
/*  50: 56 */     return this.autoUpgrade;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void setAutoUpgrade(boolean autoUpgrade)
/*  54:    */   {
/*  55: 60 */     this.autoUpgrade = autoUpgrade;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public boolean isUseProxy()
/*  59:    */   {
/*  60: 64 */     return this.useProxy;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void setUseProxy(boolean useProxy)
/*  64:    */   {
/*  65: 68 */     this.useProxy = useProxy;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public String getProxyIp()
/*  69:    */   {
/*  70: 72 */     return this.proxyIp;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void setProxyIp(String proxyIp)
/*  74:    */   {
/*  75: 76 */     this.proxyIp = proxyIp;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public String getProxyPort()
/*  79:    */   {
/*  80: 80 */     return this.proxyPort;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void setProxyPort(String proxyPort)
/*  84:    */   {
/*  85: 84 */     this.proxyPort = proxyPort;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public boolean isUseAuth()
/*  89:    */   {
/*  90: 88 */     return this.useAuth;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void setUseAuth(boolean useAuth)
/*  94:    */   {
/*  95: 92 */     this.useAuth = useAuth;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public String getAuthName()
/*  99:    */   {
/* 100: 96 */     return this.authName;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void setAuthName(String authName)
/* 104:    */   {
/* 105:100 */     this.authName = authName;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public String getAuthPassword()
/* 109:    */   {
/* 110:104 */     return this.authPassword;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void setAuthPassword(String authPassword)
/* 114:    */   {
/* 115:108 */     this.authPassword = authPassword;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public String getSection()
/* 119:    */   {
/* 120:113 */     return "UPGRADE";
/* 121:    */   }
/* 122:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.UpgradeConfig
 * JD-Core Version:    0.7.0.1
 */