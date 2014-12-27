/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   4:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*   5:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   6:    */ import java.io.PrintStream;
/*   7:    */ import java.math.BigDecimal;
/*   8:    */ import java.text.SimpleDateFormat;
/*   9:    */ import java.util.Date;
/*  10:    */ 
/*  11:    */ public class VolUtil
/*  12:    */ {
/*  13: 19 */   public static final SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat(
/*  14: 20 */     "yyyy-MM-dd HH:mm:ss");
/*  15:    */   
/*  16:    */   public static String getFormatTimestamp(Date date)
/*  17:    */   {
/*  18: 29 */     return FORMAT_TIMESTAMP.format(date);
/*  19:    */   }
/*  20:    */   
/*  21:    */   public static int parseInt(String str)
/*  22:    */   {
/*  23: 39 */     int value = 0;
/*  24: 40 */     if ((str == null) || (str.equals(""))) {
/*  25: 41 */       return value;
/*  26:    */     }
/*  27:    */     try
/*  28:    */     {
/*  29: 44 */       value = Integer.parseInt(str);
/*  30:    */     }
/*  31:    */     catch (Exception localException) {}
/*  32: 48 */     return value;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public static double parseDouble(String str)
/*  36:    */   {
/*  37: 58 */     double value = 0.0D;
/*  38: 59 */     if ((str == null) || (str.equals(""))) {
/*  39: 60 */       return value;
/*  40:    */     }
/*  41:    */     try
/*  42:    */     {
/*  43: 63 */       value = Double.parseDouble(str.trim());
/*  44:    */     }
/*  45:    */     catch (Exception localException) {}
/*  46: 67 */     return value;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public static float parseFloat(String str)
/*  50:    */   {
/*  51: 77 */     float value = 0.0F;
/*  52: 78 */     if ((str == null) || (str.equals(""))) {
/*  53: 79 */       return value;
/*  54:    */     }
/*  55:    */     try
/*  56:    */     {
/*  57: 82 */       value = Float.parseFloat(str);
/*  58:    */     }
/*  59:    */     catch (Exception localException) {}
/*  60: 86 */     return value;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public static boolean parseBoolean(String str)
/*  64:    */   {
/*  65: 96 */     boolean value = false;
/*  66: 97 */     if ((str == null) || (str.equals(""))) {
/*  67: 98 */       return value;
/*  68:    */     }
/*  69:    */     try
/*  70:    */     {
/*  71:101 */       value = Boolean.parseBoolean(str);
/*  72:    */     }
/*  73:    */     catch (Exception localException) {}
/*  74:105 */     return value;
/*  75:    */   }
/*  76:    */   
/*  77:    */   public static boolean checkEmailLenth(String value, int length)
/*  78:    */   {
/*  79:118 */     return (value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) && (value.length() <= length);
/*  80:    */   }
/*  81:    */   
/*  82:    */   public static boolean checkEmail(String value)
/*  83:    */   {
/*  84:129 */     return 
/*  85:130 */       value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
/*  86:    */   }
/*  87:    */   
/*  88:    */   public static boolean checkURL(String value)
/*  89:    */   {
/*  90:140 */     return value.matches("[a-zA-z]+://[^\\s]*");
/*  91:    */   }
/*  92:    */   
/*  93:    */   public static boolean checkIP(String value)
/*  94:    */   {
/*  95:150 */     return value.matches("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
/*  96:    */   }
/*  97:    */   
/*  98:    */   public static boolean checkIPSec(String value)
/*  99:    */   {
/* 100:160 */     return value.matches("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])");
/* 101:    */   }
/* 102:    */   
/* 103:    */   public static boolean checkNull(String value)
/* 104:    */   {
/* 105:170 */     return (value == null) || ("".equals(value.trim()));
/* 106:    */   }
/* 107:    */   
/* 108:    */   public static boolean checkNumeric(String str)
/* 109:    */   {
/* 110:180 */     if ((str == null) || ("".equalsIgnoreCase(str))) {
/* 111:181 */       return false;
/* 112:    */     }
/* 113:183 */     int i = str.length();
/* 114:    */     do
/* 115:    */     {
/* 116:184 */       if (!Character.isDigit(str.charAt(i))) {
/* 117:185 */         return false;
/* 118:    */       }
/* 119:183 */       i--;
/* 120:183 */     } while (i >= 0);
/* 121:188 */     return true;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public static boolean checkPhoneNum(String str)
/* 125:    */   {
/* 126:198 */     if ((str == null) || ("".equalsIgnoreCase(str)) || 
/* 127:199 */       (str.startsWith("-")) || (str.endsWith("-"))) {
/* 128:200 */       return false;
/* 129:    */     }
/* 130:202 */     int i = str.length();
/* 131:    */     do
/* 132:    */     {
/* 133:203 */       if ((!Character.isDigit(str.charAt(i))) && 
/* 134:204 */         (str.charAt(i) != '-')) {
/* 135:205 */         return false;
/* 136:    */       }
/* 137:202 */       i--;
/* 138:202 */     } while (i >= 0);
/* 139:209 */     return true;
/* 140:    */   }
/* 141:    */   
/* 142:    */   public static String leftZero(String value, int len)
/* 143:    */   {
/* 144:219 */     if ((value.length() > len) || (len == 0)) {
/* 145:220 */       return value;
/* 146:    */     }
/* 147:222 */     StringBuffer result = new StringBuffer();
/* 148:223 */     for (int item = 0; item < len - value.length(); item++) {
/* 149:224 */       result.append("0");
/* 150:    */     }
/* 151:226 */     return value;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public static double round(double value, int scale)
/* 155:    */   {
/* 156:240 */     BigDecimal temp = new BigDecimal(Double.toString(value));
/* 157:241 */     return temp.setScale(scale, 4).doubleValue();
/* 158:    */   }
/* 159:    */   
/* 160:    */   public static void setTemperature(WorkInfo workInfo)
/* 161:    */   {
/* 162:245 */     if ((workInfo.getWorkMode() != null) && 
/* 163:246 */       (workInfo.getWorkMode().length() > 0) && 
/* 164:247 */       (GlobalVariables.globalConfig.getTemperFormat().equals(
/* 165:248 */       "Fahrenheit"))) {
/* 166:    */       try
/* 167:    */       {
/* 168:250 */         double v = workInfo.getMaxTemperature();
/* 169:251 */         double temp = v * 1.8D + 32.0D;
/* 170:252 */         workInfo.setMaxTemperature(round(temp, 1));
/* 171:    */       }
/* 172:    */       catch (Exception e)
/* 173:    */       {
/* 174:254 */         e.printStackTrace();
/* 175:    */       }
/* 176:    */     }
/* 177:    */   }
/* 178:    */   
/* 179:    */   public static String getTempUnit()
/* 180:    */   {
/* 181:261 */     String unitText = "℃";
/* 182:262 */     if (GlobalVariables.globalConfig.getTemperFormat().equals(
/* 183:263 */       "Fahrenheit")) {
/* 184:264 */       unitText = "℉";
/* 185:    */     }
/* 186:266 */     return unitText;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public static String getTemperature(String temperature)
/* 190:    */   {
/* 191:270 */     if (GlobalVariables.globalConfig.getTemperFormat().equals(
/* 192:271 */       "Fahrenheit"))
/* 193:    */     {
/* 194:272 */       float temp = 0.0F;
/* 195:    */       try
/* 196:    */       {
/* 197:274 */         float v = Float.parseFloat(temperature);
/* 198:275 */         temp = (float)(v * 1.8D + 32.0D);
/* 199:    */       }
/* 200:    */       catch (Exception localException) {}
/* 201:278 */       return String.valueOf(round(temp, 1));
/* 202:    */     }
/* 203:280 */     return temperature;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public static void main(String[] args)
/* 207:    */   {
/* 208:285 */     System.out.println(parseDouble("29.1"));
/* 209:    */   }
/* 210:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.VolUtil
 * JD-Core Version:    0.7.0.1
 */