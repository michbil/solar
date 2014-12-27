/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   4:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   5:    */ import java.sql.Timestamp;
/*   6:    */ import java.text.DateFormat;
/*   7:    */ import java.text.ParseException;
/*   8:    */ import java.text.SimpleDateFormat;
/*   9:    */ import java.util.Calendar;
/*  10:    */ import java.util.Date;
/*  11:    */ 
/*  12:    */ public class DateUtils
/*  13:    */   extends org.apache.commons.lang.time.DateUtils
/*  14:    */ {
/*  15:    */   public static boolean isValidDate(String dateStr, String pattern)
/*  16:    */   {
/*  17: 28 */     boolean isValid = false;
/*  18: 29 */     if ((pattern == null) || (pattern.length() < 1)) {
/*  19: 30 */       pattern = "yyyy-MM-dd";
/*  20:    */     }
/*  21:    */     try
/*  22:    */     {
/*  23: 33 */       SimpleDateFormat sdf = new SimpleDateFormat(pattern);
/*  24:    */       
/*  25: 35 */       String date = sdf.format(sdf.parse(dateStr));
/*  26: 36 */       if (date.equalsIgnoreCase(dateStr)) {
/*  27: 37 */         isValid = true;
/*  28:    */       }
/*  29:    */     }
/*  30:    */     catch (Exception e)
/*  31:    */     {
/*  32: 40 */       isValid = false;
/*  33:    */     }
/*  34: 43 */     if (!isValid) {
/*  35: 44 */       isValid = isValidDatePatterns(dateStr, "");
/*  36:    */     }
/*  37: 46 */     return isValid;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public static Date getDateParseDate(Date date)
/*  41:    */   {
/*  42: 50 */     SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat(
/*  43: 51 */       GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/*  44: 52 */     String source = FORMAT_TIMESTAMP.format(date);
/*  45:    */     try
/*  46:    */     {
/*  47: 54 */       return FORMAT_TIMESTAMP.parse(source);
/*  48:    */     }
/*  49:    */     catch (ParseException e)
/*  50:    */     {
/*  51: 56 */       e.printStackTrace();
/*  52:    */     }
/*  53: 58 */     return null;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public static boolean isValidDatePatterns(String dateStr, String patterns)
/*  57:    */   {
/*  58: 62 */     if ((patterns == null) || (patterns.length() < 1)) {
/*  59: 63 */       patterns = "yyyy-MM-dd;dd/MM/yyyy;yyyy/MM/dd;yyyy/M/d h:mm";
/*  60:    */     }
/*  61: 65 */     boolean isValid = false;
/*  62: 66 */     String[] patternArr = patterns.split(";");
/*  63: 67 */     for (int i = 0; i < patternArr.length; i++) {
/*  64:    */       try
/*  65:    */       {
/*  66: 69 */         SimpleDateFormat sdf = new SimpleDateFormat(patternArr[i]);
/*  67:    */         
/*  68: 71 */         String date = sdf.format(sdf.parse(dateStr));
/*  69: 72 */         if (date.equalsIgnoreCase(dateStr)) {
/*  70: 73 */           isValid = true;
/*  71:    */         }
/*  72:    */       }
/*  73:    */       catch (Exception e)
/*  74:    */       {
/*  75: 77 */         isValid = false;
/*  76:    */       }
/*  77:    */     }
/*  78: 80 */     return isValid;
/*  79:    */   }
/*  80:    */   
/*  81:    */   public static String getDateFormat(boolean isFull)
/*  82:    */   {
/*  83: 84 */     String date = GlobalVariables.globalConfig.getDateFormat();
/*  84: 85 */     if (date.equals("yyyy-MM-dd")) {
/*  85: 86 */       date = isFull ? "yyyy-MM-dd" : "yyyy-MM";
/*  86: 87 */     } else if (date.equals("yyyy/MM/dd")) {
/*  87: 88 */       date = isFull ? "yyyy/MM/dd" : "yyyy/MM";
/*  88: 89 */     } else if (date.equals("MM-dd-yyyy")) {
/*  89: 90 */       date = isFull ? "MM-dd-yyyy" : "MM-dd";
/*  90: 91 */     } else if (date.equals("MM/dd/yyyy")) {
/*  91: 92 */       date = isFull ? "MM/dd/yyyy" : "MM/dd";
/*  92:    */     } else {
/*  93: 94 */       date = isFull ? "yyyy-MM-dd" : "yyyy-MM";
/*  94:    */     }
/*  95: 96 */     return date;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public static String getFormatDateStr(String dateStr, String pattern)
/*  99:    */   {
/* 100:100 */     if ((pattern == null) || (pattern.length() < 1)) {
/* 101:101 */       pattern = "yyyy-MM-dd HH:mm:ss";
/* 102:    */     }
/* 103:    */     try
/* 104:    */     {
/* 105:104 */       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 106:105 */       SimpleDateFormat format = new SimpleDateFormat(pattern);
/* 107:106 */       return format.format(sdf.parse(dateStr));
/* 108:    */     }
/* 109:    */     catch (Exception e)
/* 110:    */     {
/* 111:109 */       e.printStackTrace();
/* 112:    */     }
/* 113:111 */     return null;
/* 114:    */   }
/* 115:    */   
/* 116:    */   public static String getFormatDate(String dateStr, String pattern)
/* 117:    */   {
/* 118:115 */     if ((pattern == null) || (pattern.length() < 1)) {
/* 119:116 */       pattern = "yyyy-MM-dd";
/* 120:    */     }
/* 121:    */     try
/* 122:    */     {
/* 123:119 */       SimpleDateFormat sdf = new SimpleDateFormat(
/* 124:120 */         GlobalVariables.globalConfig.getDateFormat());
/* 125:121 */       SimpleDateFormat format = new SimpleDateFormat(pattern);
/* 126:122 */       return format.format(sdf.parse(dateStr));
/* 127:    */     }
/* 128:    */     catch (Exception e)
/* 129:    */     {
/* 130:125 */       e.printStackTrace();
/* 131:    */     }
/* 132:128 */     return null;
/* 133:    */   }
/* 134:    */   
/* 135:    */   public static String getFormatDate(Date date, String pattern)
/* 136:    */   {
/* 137:132 */     if ((pattern == null) || (pattern.length() < 1)) {
/* 138:133 */       pattern = "yyyy-MM-dd";
/* 139:    */     }
/* 140:    */     try
/* 141:    */     {
/* 142:136 */       SimpleDateFormat sdf = new SimpleDateFormat(pattern);
/* 143:137 */       return sdf.format(date);
/* 144:    */     }
/* 145:    */     catch (Exception e)
/* 146:    */     {
/* 147:140 */       e.printStackTrace();
/* 148:    */     }
/* 149:143 */     return null;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public static Date parseDate(String s)
/* 153:    */   {
/* 154:147 */     DateFormat df = DateFormat.getDateInstance();
/* 155:148 */     Date myDate = null;
/* 156:    */     try
/* 157:    */     {
/* 158:150 */       myDate = df.parse(s);
/* 159:    */     }
/* 160:    */     catch (ParseException e)
/* 161:    */     {
/* 162:152 */       e.printStackTrace();
/* 163:    */     }
/* 164:154 */     return myDate;
/* 165:    */   }
/* 166:    */   
/* 167:    */   public static Date parseDate_yyyy_MM_dd(String s)
/* 168:    */   {
/* 169:158 */     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 170:159 */     Date myDate = null;
/* 171:    */     try
/* 172:    */     {
/* 173:161 */       myDate = df.parse(s);
/* 174:    */     }
/* 175:    */     catch (ParseException e)
/* 176:    */     {
/* 177:163 */       e.printStackTrace();
/* 178:    */     }
/* 179:165 */     return myDate;
/* 180:    */   }
/* 181:    */   
/* 182:    */   public static String getNowDate(String pattern)
/* 183:    */   {
/* 184:169 */     Date date = new Date();
/* 185:170 */     String strdate = "";
/* 186:171 */     SimpleDateFormat FORMAT_DATE = new SimpleDateFormat(
/* 187:172 */       GlobalVariables.globalConfig.getDateFormat());
/* 188:173 */     if ((pattern == null) || (pattern.length() < 1)) {
/* 189:174 */       strdate = FORMAT_DATE.format(date);
/* 190:    */     } else {
/* 191:    */       try
/* 192:    */       {
/* 193:177 */         SimpleDateFormat sdf = new SimpleDateFormat(pattern);
/* 194:178 */         strdate = sdf.format(date);
/* 195:    */       }
/* 196:    */       catch (Exception e)
/* 197:    */       {
/* 198:180 */         strdate = FORMAT_DATE.format(date);
/* 199:    */       }
/* 200:    */     }
/* 201:184 */     return strdate;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public static String getNowDate()
/* 205:    */   {
/* 206:188 */     Date date = new Date();
/* 207:189 */     String pattern = GlobalVariables.globalConfig.getDateFormat();
/* 208:190 */     SimpleDateFormat formate = new SimpleDateFormat(
/* 209:191 */       pattern);
/* 210:192 */     return formate.format(date);
/* 211:    */   }
/* 212:    */   
/* 213:    */   public static String getFormatTimestamp(Date date)
/* 214:    */   {
/* 215:196 */     SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat(
/* 216:197 */       GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 217:198 */     return FORMAT_TIMESTAMP.format(date);
/* 218:    */   }
/* 219:    */   
/* 220:    */   public static Date parseTimestamp(String s)
/* 221:    */   {
/* 222:202 */     Date date = null;
/* 223:    */     try
/* 224:    */     {
/* 225:204 */       SimpleDateFormat FORMAT_TIMESTAMP = new SimpleDateFormat(
/* 226:205 */         GlobalVariables.globalConfig.getDateFormat() + " HH:mm:ss");
/* 227:206 */       date = FORMAT_TIMESTAMP.parse(s);
/* 228:    */     }
/* 229:    */     catch (Exception e)
/* 230:    */     {
/* 231:208 */       e.printStackTrace();
/* 232:    */     }
/* 233:210 */     return date;
/* 234:    */   }
/* 235:    */   
/* 236:    */   public static Date parseDate(String s, String pattern)
/* 237:    */   {
/* 238:214 */     Date date = null;
/* 239:215 */     if ((s != null) && (isValidDate(s, pattern))) {
/* 240:    */       try
/* 241:    */       {
/* 242:217 */         SimpleDateFormat sdf = new SimpleDateFormat(pattern);
/* 243:218 */         date = sdf.parse(s);
/* 244:    */       }
/* 245:    */       catch (Exception e)
/* 246:    */       {
/* 247:220 */         e.printStackTrace();
/* 248:    */       }
/* 249:    */     }
/* 250:223 */     return date;
/* 251:    */   }
/* 252:    */   
/* 253:    */   public static String getFormatDate(Date date)
/* 254:    */   {
/* 255:227 */     SimpleDateFormat FORMAT_DATE = new SimpleDateFormat(
/* 256:228 */       GlobalVariables.globalConfig.getDateFormat());
/* 257:229 */     return FORMAT_DATE.format(date);
/* 258:    */   }
/* 259:    */   
/* 260:    */   public static void main(String[] args) {}
/* 261:    */   
/* 262:    */   public static boolean isFullYear(Calendar startdate, Calendar enddate)
/* 263:    */   {
/* 264:246 */     return (startdate.get(1) == enddate.get(1)) && (startdate.get(2) == 0) && (startdate.get(5) == 1) && (enddate.get(2) == 11) && (enddate.get(5) == 31);
/* 265:    */   }
/* 266:    */   
/* 267:    */   public static boolean isFullMonth(Calendar startdate, Calendar enddate)
/* 268:    */   {
/* 269:251 */     Calendar nextdate = Calendar.getInstance();
/* 270:252 */     nextdate.setTime(enddate.getTime());
/* 271:253 */     nextdate.add(5, 1);
/* 272:    */     
/* 273:255 */     return (startdate.get(5) == 1) && (nextdate.get(5) == 1);
/* 274:    */   }
/* 275:    */   
/* 276:    */   public static Calendar getEndofMonth(Calendar date)
/* 277:    */   {
/* 278:259 */     Calendar enddate = Calendar.getInstance();
/* 279:260 */     enddate.setTime(date.getTime());
/* 280:261 */     enddate.add(2, 1);
/* 281:262 */     enddate.set(5, 1);
/* 282:263 */     enddate.add(5, -1);
/* 283:264 */     return enddate;
/* 284:    */   }
/* 285:    */   
/* 286:    */   public static Calendar getStartofMonth(Calendar date)
/* 287:    */   {
/* 288:268 */     Calendar startdate = Calendar.getInstance();
/* 289:269 */     startdate.set(date.get(1), date.get(2), 1);
/* 290:270 */     return startdate;
/* 291:    */   }
/* 292:    */   
/* 293:    */   public static int getCurrentYear()
/* 294:    */   {
/* 295:279 */     Calendar c = Calendar.getInstance();
/* 296:280 */     return c.get(1);
/* 297:    */   }
/* 298:    */   
/* 299:    */   public static int getCurrentMonth()
/* 300:    */   {
/* 301:288 */     Calendar c = Calendar.getInstance();
/* 302:289 */     return c.get(2);
/* 303:    */   }
/* 304:    */   
/* 305:    */   public static int getCurrentWeek()
/* 306:    */   {
/* 307:297 */     Calendar c = Calendar.getInstance();
/* 308:298 */     return c.get(7);
/* 309:    */   }
/* 310:    */   
/* 311:    */   public static int getCurrentDay()
/* 312:    */   {
/* 313:306 */     Calendar c = Calendar.getInstance();
/* 314:307 */     return c.get(5);
/* 315:    */   }
/* 316:    */   
/* 317:    */   public static int getCurrentHour()
/* 318:    */   {
/* 319:315 */     Calendar c = Calendar.getInstance();
/* 320:316 */     return c.get(11);
/* 321:    */   }
/* 322:    */   
/* 323:    */   public static Calendar getNextMonth(Calendar date)
/* 324:    */   {
/* 325:320 */     Calendar enddate = Calendar.getInstance();
/* 326:321 */     enddate.setTime(date.getTime());
/* 327:322 */     enddate.add(2, 1);
/* 328:323 */     enddate.set(5, 1);
/* 329:324 */     return enddate;
/* 330:    */   }
/* 331:    */   
/* 332:    */   public static Calendar getShortdate(Calendar longdate)
/* 333:    */   {
/* 334:328 */     Calendar cal = (Calendar)longdate.clone();
/* 335:329 */     cal.set(11, 0);
/* 336:330 */     cal.set(12, 0);
/* 337:331 */     cal.set(13, 0);
/* 338:332 */     cal.set(14, 0);
/* 339:333 */     return cal;
/* 340:    */   }
/* 341:    */   
/* 342:    */   public static boolean sqlAfter(Timestamp first0, Calendar second, int field)
/* 343:    */   {
/* 344:337 */     Calendar first = Calendar.getInstance();
/* 345:338 */     first.setTime(first0);
/* 346:339 */     if (field == 2)
/* 347:    */     {
/* 348:340 */       int firstyear = first.get(1);
/* 349:341 */       int secondyear = second.get(1);
/* 350:342 */       if (firstyear > secondyear) {
/* 351:343 */         return true;
/* 352:    */       }
/* 353:344 */       if (firstyear == secondyear) {
/* 354:345 */         return first.get(2) > first.get(2);
/* 355:    */       }
/* 356:347 */       return false;
/* 357:    */     }
/* 358:349 */     if (field == 5) {
/* 359:350 */       return getShortdate(first).after(
/* 360:351 */         getShortdate(second));
/* 361:    */     }
/* 362:352 */     if (field == 11)
/* 363:    */     {
/* 364:353 */       if (getShortdate(first).compareTo(
/* 365:354 */         getShortdate(second)) == 0) {
/* 366:355 */         return 
/* 367:356 */           first.get(11) > second.get(11);
/* 368:    */       }
/* 369:358 */       return first.after(second);
/* 370:    */     }
/* 371:360 */     if (field == 12)
/* 372:    */     {
/* 373:361 */       Calendar fircopy = (Calendar)first.clone();
/* 374:362 */       fircopy.set(13, 0);
/* 375:363 */       fircopy.set(14, 0);
/* 376:    */       
/* 377:365 */       Calendar seccopy = (Calendar)second.clone();
/* 378:    */       
/* 379:367 */       seccopy.set(13, 0);
/* 380:368 */       seccopy.set(14, 0);
/* 381:    */       
/* 382:370 */       return fircopy.after(seccopy);
/* 383:    */     }
/* 384:373 */     return first.after(second);
/* 385:    */   }
/* 386:    */   
/* 387:    */   public static Calendar getEndofDayFullTime(Calendar date)
/* 388:    */   {
/* 389:383 */     Calendar end = date;
/* 390:384 */     end.set(11, 23);
/* 391:385 */     end.set(12, 59);
/* 392:386 */     end.set(13, 59);
/* 393:387 */     return end;
/* 394:    */   }
/* 395:    */   
/* 396:    */   public static Timestamp getEndofDayFullTime(Date date)
/* 397:    */   {
/* 398:392 */     Calendar cal = Calendar.getInstance();
/* 399:    */     
/* 400:394 */     cal.setTime(date);
/* 401:395 */     cal.set(11, 23);
/* 402:396 */     cal.set(12, 59);
/* 403:397 */     cal.set(13, 59);
/* 404:    */     
/* 405:399 */     return new Timestamp(cal.getTime().getTime());
/* 406:    */   }
/* 407:    */   
/* 408:    */   public static Calendar getStartofDayFullTime(Calendar date)
/* 409:    */   {
/* 410:408 */     Calendar start = date;
/* 411:409 */     start.set(11, 0);
/* 412:410 */     start.set(12, 0);
/* 413:411 */     start.set(13, 0);
/* 414:412 */     return start;
/* 415:    */   }
/* 416:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.DateUtils
 * JD-Core Version:    0.7.0.1
 */