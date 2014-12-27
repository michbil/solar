/*   1:    */ package cn.com.voltronic.solar.constants;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Image;
/*   5:    */ import java.awt.Toolkit;
/*   6:    */ import java.io.File;
/*   7:    */ 
/*   8:    */ public class Constants
/*   9:    */ {
/*  10: 19 */   private static final String FILE_NAME = "config" + File.separator + 
/*  11: 20 */     "config.properties";
/*  12: 21 */   public static String CONFIG_PATH = System.getProperty("user.dir") + 
/*  13: 22 */     File.separator + FILE_NAME;
/*  14: 24 */   public static String PDF_PATH = System.getProperty("user.dir") + 
/*  15: 25 */     File.separator + "pdf" + File.separator;
/*  16: 27 */   public static String PDF_STYLE_PATH = System.getProperty("user.dir") + 
/*  17: 28 */     File.separator + "style" + File.separator;
/*  18: 30 */   public static final String IMAGES_PATH = System.getProperty("user.dir") + 
/*  19: 31 */     File.separator + "images" + File.separator;
/*  20: 34 */   public static final String STATUSIMAGES_PATH = System.getProperty("user.dir") + 
/*  21: 35 */     File.separator + 
/*  22: 36 */     "images" + 
/*  23: 37 */     File.separator + 
/*  24: 38 */     "status" + 
/*  25: 39 */     File.separator;
/*  26: 42 */   public static final String CONSOLEIMAGES_PATH = System.getProperty("user.dir") + 
/*  27: 43 */     File.separator + 
/*  28: 44 */     "images" + 
/*  29: 45 */     File.separator + 
/*  30: 46 */     "console" + 
/*  31: 47 */     File.separator;
/*  32: 50 */   public static final String CUSTOMERIMAGES_PATH = System.getProperty("user.dir") + 
/*  33: 51 */     File.separator + 
/*  34: 52 */     "images" + 
/*  35: 53 */     File.separator + 
/*  36: 54 */     "customer" + 
/*  37: 55 */     File.separator;
/*  38: 60 */   public static Image CONNECTEDIMG = Toolkit.getDefaultToolkit().getImage(
/*  39: 61 */     CONSOLEIMAGES_PATH + "RunInService.gif");
/*  40: 66 */   public static Image CONNECTEDIMG_DEVICE = Toolkit.getDefaultToolkit().getImage(
/*  41: 67 */     CONSOLEIMAGES_PATH + "InServiceConnect.gif");
/*  42: 72 */   public static Image HASEVENT = Toolkit.getDefaultToolkit().getImage(
/*  43: 73 */     CONSOLEIMAGES_PATH + "InServiceConnectRecvInfo.gif");
/*  44: 78 */   public static Image HASEVENT_DEVICE = Toolkit.getDefaultToolkit().getImage(
/*  45: 79 */     CONSOLEIMAGES_PATH + "InServiceNotConnectRecvInfo.gif");
/*  46: 81 */   public static Image STATUSIMG = Toolkit.getDefaultToolkit().getImage(
/*  47: 82 */     IMAGES_PATH + "state.png");
/*  48: 83 */   public static Image PARAMETERIMG = Toolkit.getDefaultToolkit().getImage(
/*  49: 84 */     IMAGES_PATH + "12.png");
/*  50: 85 */   public static Image POWERIMG = Toolkit.getDefaultToolkit().getImage(
/*  51: 86 */     IMAGES_PATH + "tongji.png");
/*  52: 87 */   public static Image DATAIMG = Toolkit.getDefaultToolkit().getImage(
/*  53: 88 */     IMAGES_PATH + "history.png");
/*  54: 89 */   public static Image EVENTIMG = Toolkit.getDefaultToolkit().getImage(
/*  55: 90 */     IMAGES_PATH + "statistics.png");
/*  56: 91 */   public static Image FAULTIMG = Toolkit.getDefaultToolkit().getImage(
/*  57: 92 */     IMAGES_PATH + "exception.png");
/*  58: 93 */   public static Image LOGOUTIMG = Toolkit.getDefaultToolkit().getImage(
/*  59: 94 */     IMAGES_PATH + "logout.png");
/*  60: 95 */   public static Image LOGINIMG = Toolkit.getDefaultToolkit().getImage(
/*  61: 96 */     IMAGES_PATH + "login.png");
/*  62: 98 */   public static Image DATE = Toolkit.getDefaultToolkit().getImage(
/*  63: 99 */     IMAGES_PATH + "date.png");
/*  64:100 */   public static Image WARNINGIMG = Toolkit.getDefaultToolkit().getImage(
/*  65:101 */     IMAGES_PATH + "warning.gif");
/*  66:102 */   public static Image WARNINGLIGHTIMG = Toolkit.getDefaultToolkit().getImage(
/*  67:103 */     IMAGES_PATH + "error.png");
/*  68:106 */   public static Image PV1 = Toolkit.getDefaultToolkit().getImage(
/*  69:107 */     STATUSIMAGES_PATH + "pv1.gif");
/*  70:108 */   public static Image PV2 = Toolkit.getDefaultToolkit().getImage(
/*  71:109 */     STATUSIMAGES_PATH + "pv2.gif");
/*  72:110 */   public static Image PV3 = Toolkit.getDefaultToolkit().getImage(
/*  73:111 */     STATUSIMAGES_PATH + "pv3.gif");
/*  74:112 */   public static Image PV4 = Toolkit.getDefaultToolkit().getImage(
/*  75:113 */     STATUSIMAGES_PATH + "pv4.gif");
/*  76:114 */   public static Image PV5 = Toolkit.getDefaultToolkit().getImage(
/*  77:115 */     STATUSIMAGES_PATH + "pv5.gif");
/*  78:116 */   public static Image AC = Toolkit.getDefaultToolkit().getImage(
/*  79:117 */     STATUSIMAGES_PATH + "ac.gif");
/*  80:118 */   public static Image ACDC = Toolkit.getDefaultToolkit().getImage(
/*  81:119 */     STATUSIMAGES_PATH + "acdc.gif");
/*  82:120 */   public static Image STATUSBG1 = Toolkit.getDefaultToolkit().getImage(
/*  83:121 */     STATUSIMAGES_PATH + "statusbg1.png");
/*  84:122 */   public static Image STATUSBG2 = Toolkit.getDefaultToolkit().getImage(
/*  85:123 */     STATUSIMAGES_PATH + "statusbg2.png");
/*  86:124 */   public static Image STATUSBG3 = Toolkit.getDefaultToolkit().getImage(
/*  87:125 */     STATUSIMAGES_PATH + "statusbg3.png");
/*  88:128 */   public static Image INFINI_BG1 = Toolkit.getDefaultToolkit().getImage(
/*  89:129 */     STATUSIMAGES_PATH + "infini_bg1.png");
/*  90:130 */   public static Image RIGHT_ARROW1 = Toolkit.getDefaultToolkit().getImage(
/*  91:131 */     STATUSIMAGES_PATH + "Right-Arrow-1.gif");
/*  92:132 */   public static Image RIGHT_ARROW2 = Toolkit.getDefaultToolkit().getImage(
/*  93:133 */     STATUSIMAGES_PATH + "Right-Arrow-2.gif");
/*  94:134 */   public static Image DOWN1 = Toolkit.getDefaultToolkit().getImage(
/*  95:135 */     STATUSIMAGES_PATH + "Down1.gif");
/*  96:136 */   public static Image DOWN2 = Toolkit.getDefaultToolkit().getImage(
/*  97:137 */     STATUSIMAGES_PATH + "Down2.gif");
/*  98:138 */   public static Image UP1 = Toolkit.getDefaultToolkit().getImage(
/*  99:139 */     STATUSIMAGES_PATH + "UP1.gif");
/* 100:140 */   public static Image UP2 = Toolkit.getDefaultToolkit().getImage(
/* 101:141 */     STATUSIMAGES_PATH + "UP2.gif");
/* 102:143 */   public static Image LeftUP = Toolkit.getDefaultToolkit().getImage(
/* 103:144 */     STATUSIMAGES_PATH + "LeftUP.gif");
/* 104:145 */   public static Image LeftDown = Toolkit.getDefaultToolkit().getImage(
/* 105:146 */     STATUSIMAGES_PATH + "LeftDown.gif");
/* 106:147 */   public static Image UPLeft = Toolkit.getDefaultToolkit().getImage(
/* 107:148 */     STATUSIMAGES_PATH + "UPLeft.gif");
/* 108:149 */   public static Image UPRight = Toolkit.getDefaultToolkit().getImage(
/* 109:150 */     STATUSIMAGES_PATH + "UPRight.gif");
/* 110:151 */   public static Image DownRight = Toolkit.getDefaultToolkit().getImage(
/* 111:152 */     STATUSIMAGES_PATH + "DownRight.gif");
/* 112:153 */   public static Image ACLOSS = Toolkit.getDefaultToolkit().getImage(
/* 113:154 */     STATUSIMAGES_PATH + "ACLOSS.gif");
/* 114:155 */   public static Image BATTERYLOSS = Toolkit.getDefaultToolkit().getImage(
/* 115:156 */     STATUSIMAGES_PATH + "BATTERYLOSS.gif");
/* 116:157 */   public static Image FAULTMODE = Toolkit.getDefaultToolkit().getImage(
/* 117:158 */     STATUSIMAGES_PATH + "FaultMode.gif");
/* 118:159 */   public static Image OVERLOAD = Toolkit.getDefaultToolkit().getImage(
/* 119:160 */     STATUSIMAGES_PATH + "OverLoad.gif");
/* 120:161 */   public static Image DOWNUPDOWN = Toolkit.getDefaultToolkit().getImage(
/* 121:162 */     STATUSIMAGES_PATH + "DownUpDown.gif");
/* 122:    */   public static final String ACK = "(ACK";
/* 123:    */   public static final String NAK = "(NAK";
/* 124:    */   public static final String OK = "(OK";
/* 125:    */   public static final String FAULT = "message.fault";
/* 126:    */   public static final String WARNING = "message.warning";
/* 127:    */   public static final String INFO = "message.info";
/* 128:    */   public static final String EVENT = "(Event:";
/* 129:    */   public static final String EXITCMD = "(exit:myself";
/* 130:    */   public static final String REDRAWTRAY = "redrawtray";
/* 131:    */   public static final String OPENPAGE = "OPENPAGE";
/* 132:    */   public static final String REDRAWOK = "redrawok";
/* 133:    */   public static final String UPGRADE_EXIT = "(exit:upgrade";
/* 134:    */   public static final String PVCOUNT = "(PVCount:";
/* 135:    */   public static final String USB = "USB";
/* 136:    */   public static final String COM = "COM";
/* 137:    */   public static final String NONE = "None";
/* 138:    */   public static final String PI = "(PI";
/* 139:    */   public static final double WORKINFO_VIRTUAL_VALUE = 5.0D;
/* 140:    */   public static final String LOCALIP = "127.0.0.1";
/* 141:    */   public static final String LOCAL = "Local";
/* 142:    */   public static final String IPALL = "0.0.0.0";
/* 143:    */   public static final String LOCALHOST = "localhost";
/* 144:    */   public static final String UDPPORT = "38694";
/* 145:    */   public static final String ADMIN = "message.localManager";
/* 146:    */   public static final String GUEST = "message.observer";
/* 147:    */   public static final String P30 = "P30";
/* 148:    */   public static final String DAILY = "daily";
/* 149:    */   public static final String WEEKLY = "weekly";
/* 150:    */   public static final String MONTHLY = "monthly";
/* 151:    */   public static final int QUERY_BY_YEAR = 0;
/* 152:    */   public static final int QUERY_BY_MONTH = 1;
/* 153:    */   public static final int QUERY_BY_DAY = 2;
/* 154:    */   public static final int QUERY_BY_HOUR = 3;
/* 155:    */   public static final String CENTIGRADE = "Centigrade";
/* 156:    */   public static final String FAHRENHEIT = "Fahrenheit";
/* 157:    */   public static final String ENGLISH = "en_US";
/* 158:    */   public static final String FRENCH = "fr_FR";
/* 159:    */   public static final String GERMAN = "de_DE";
/* 160:    */   public static final String ITALIAN = "it_IT";
/* 161:    */   public static final String POLISH = "pl_PL";
/* 162:    */   public static final String PORTUGUESE = "pt_PT";
/* 163:    */   public static final String RUSSIAN = "ru_RU";
/* 164:    */   public static final String SPANISH = "es_ES";
/* 165:    */   public static final String UKRAINIAN = "uk_UA";
/* 166:    */   public static final String TURKISH = "tr_TR";
/* 167:    */   public static final String CHINESE = "zh_CN";
/* 168:    */   public static final String CZECH = "cs_CS";
/* 169:    */   public static final String CHINESE_TW = "zh_TW";
/* 170:    */   public static final String POWERON_MODE = "Power On Mode";
/* 171:    */   public static final String STANDBY_MODE = "Standby Mode";
/* 172:    */   public static final String BYPASS_MODE = "Bypass Mode";
/* 173:    */   public static final String LINE_MODE = "Line Mode";
/* 174:    */   public static final String BATTERY_MODE = "Battery Mode";
/* 175:    */   public static final String FAULT_MODE = "Fault Mode";
/* 176:    */   public static final String SHUTDOWN_MODE = "Shutdown Mode";
/* 177:    */   public static final String GRID_MODE = "Grid Mode";
/* 178:    */   public static final String INVERTER_MODE = "Inverter";
/* 179:    */   public static final String BYPASSWITHPVCHARGING_MODE = "Bypass with PV charging";
/* 180:    */   public static final String BYPASSWITHACCHARGING_MODE = "Bypass with AC charging";
/* 181:    */   public static final String STANDBYWITHPVCHARGING_MODE = "Standby with PV charging";
/* 182:    */   public static final String STANDBYWITHACCHARGING_MODE = "Standby with AC charging";
/* 183:    */   public static final String GRIDTIEWITHBACKUP_MODE = "Grid-tie with backup";
/* 184:    */   public static final String BYPASSWITHOUTCHARGING_MODE = "Bypass without charging";
/* 185:    */   public static final String STANDBYWITHOUTCHARGING_MODE = "Standby without charging";
/* 186:    */   public static final int SELFTEST_RESULT_TESTING = 0;
/* 187:    */   public static final int SELFTEST_RESULT_SUCCESS = 1;
/* 188:    */   public static final int SELFTEST_RESULT_FAIL = 2;
/* 189:    */   public static final String GRID_TIE = "00";
/* 190:    */   public static final String OFF_GRID = "01";
/* 191:    */   public static final String HYBRID = "10";
/* 192:    */   public static final int EnerSolar_3kW_2PV_GER = 0;
/* 193:    */   public static final int EnerSolar_3kW_1PV_GER = 1;
/* 194:    */   public static final int EnerSolar_3kW_2PV_ITA = 2;
/* 195:    */   public static final int EnerSolar_3kW_2PV_UK = 3;
/* 196:    */   public static final int EnerSolar_3kW_2PV_SPAIN = 4;
/* 197:    */   public static final int EnerSolar_3kW_2PV_AUS = 5;
/* 198:    */   public static final String CYCLE_DAILY = "daily";
/* 199:    */   public static final String CYCLE_WEEKLY = "weekly";
/* 200:    */   public static final String CYCLE_MONTHLY = "monthly";
/* 201:    */   public static final String ALL = "All";
/* 202:    */   public static final String SUCCESS = "success";
/* 203:    */   public static final String FAILURE = "failure";
/* 204:    */   public static final String FORBID = "forbid";
/* 205:    */   public static final String EMAIL_INFO = "EmailInfo";
/* 206:    */   public static final String SMS_INFO = "SmsInfo";
/* 207:    */   public static final String PASSWORD_INFO = "PasswordInfo";
/* 208:    */   public static final String EVENTDATA_INFO = "EventDataInfo";
/* 209:    */   public static final String NETWEAK_INFO = "NetWeakInfo";
/* 210:    */   public static final String COM_INFO = "COMPlugAndPlay";
/* 211:    */   public static final String BEAN_MACHINEINFO = "machineinfo";
/* 212:    */   public static final String BEAN_PROTOCOLINFO = "protocolinfo";
/* 213:    */   public static final String BEAN_WORKINFO = "workinfo";
/* 214:    */   public static final String BEAN_CAPABILITY = "capability";
/* 215:    */   public static final String BEAN_CONFIGDATA = "configdata";
/* 216:    */   public static final String BEAN_ENERGYDATA = "energydata";
/* 217:    */   public static final String BEAN_SELFTESTRESULT = "selftestresult";
/* 218:    */   public static final String BEAN_DEFAULTDATA = "defaultdata";
/* 219:    */   public static final int MACUSB_SVRPORT = 33656;
/* 220:    */   public static final String MACUSB_SENDCOMMAND = "sendCommand";
/* 221:    */   public static final String MACUSB_FINDUSBDEVICES = "findUSBDevices";
/* 222:    */   public static final String MACUSB_CLOSEUSBPORT = "closeUSBPort";
/* 223:    */   public static final String MACUSB_SHUTDOWNOS = "shutdownOS";
/* 224:    */   public static final String MACUSB_SUSPENDOS = "suspendOS";
/* 225:    */   public static final String MACUSB_BEEP = "beep";
/* 226:    */   public static final String EXIT_SERVER = "EXIT";
/* 227:    */   public static final String CONNECTION_TEST = "TEST";
/* 228:    */   public static final int PARALLEL_NONE = 0;
/* 229:    */   public static final int PARALLEL_PARENT = 1;
/* 230:    */   public static final int PARALLEL_SUB = 2;
/* 231:    */   public static final int OUTPUT_SINGLE = 0;
/* 232:    */   public static final int OUTPUT_PARALLEL = 1;
/* 233:    */   public static final int OUTPUT_RPHASE = 2;
/* 234:    */   public static final int OUTPUT_SPHASE = 3;
/* 235:    */   public static final int OUTPUT_TPHASE = 4;
/* 236:    */   public static final boolean WEI_DEBUG = false;
/* 237:    */   
/* 238:    */   private static String getSystemProperty(String property)
/* 239:    */   {
/* 240:    */     try
/* 241:    */     {
/* 242:325 */       return System.getProperty(property);
/* 243:    */     }
/* 244:    */     catch (Exception ex)
/* 245:    */     {
/* 246:327 */       ex.printStackTrace();
/* 247:    */     }
/* 248:328 */     return null;
/* 249:    */   }
/* 250:    */   
/* 251:332 */   public static final Color BG_COLOR = new Color(102, 102, 102);
/* 252:334 */   public static final String OS_NAME = getSystemProperty("os.name");
/* 253:335 */   public static final boolean IS_OS_MAC = getOSMatches("Mac");
/* 254:336 */   public static final boolean IS_OS_WINDOWS = getOSMatches("Windows");
/* 255:337 */   public static final boolean IS_OS_MAC_OSX = getOSMatches("Mac OS X");
/* 256:338 */   public static final boolean IS_OS_LINUX = (getOSMatches("Linux")) || 
/* 257:339 */     (getOSMatches("LINUX"));
/* 258:340 */   public static final boolean IS_OS_SOLARIS = (getOSMatches("Solaris")) || 
/* 259:341 */     (getOSMatches("SunOS"));
/* 260:    */   
/* 261:    */   private static boolean getOSMatches(String osNamePrefix)
/* 262:    */   {
/* 263:344 */     if (OS_NAME == null) {
/* 264:345 */       return false;
/* 265:    */     }
/* 266:347 */     return OS_NAME.startsWith(osNamePrefix);
/* 267:    */   }
/* 268:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.constants.Constants
 * JD-Core Version:    0.7.0.1
 */