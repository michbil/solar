/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.EnergyBean;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import cn.com.voltronic.solar.util.DateUtils;
/*   6:    */ import java.io.PrintStream;
/*   7:    */ import java.sql.Connection;
/*   8:    */ import java.sql.PreparedStatement;
/*   9:    */ import java.sql.ResultSet;
/*  10:    */ import java.util.ArrayList;
/*  11:    */ import java.util.Calendar;
/*  12:    */ import java.util.List;
/*  13:    */ 
/*  14:    */ public class EnergyDao
/*  15:    */   extends BaseDao
/*  16:    */ {
/*  17:    */   private static final String QUERY_YEAR = " SELECT * FROM yenergy WHERE serialno=? and iyear BETWEEN ? AND ? order by iyear";
/*  18:    */   private static final String QUERY_MONTH = " SELECT * FROM menergy WHERE serialno=? and yearmonth BETWEEN ? and ? order by yearmonth ";
/*  19:    */   private static final String QUERY_DAY = " SELECT * FROM denergy WHERE serialno=? and trandate BETWEEN ? and ? order by trandate ";
/*  20:    */   private static final String QUERY_HOUR = " SELECT * FROM henergy WHERE serialno=? and trandate BETWEEN ? and ? order by trandate,ihour ";
/*  21:    */   private static final String QUERY_HOUR_SUM = " SELECT sum(energy) as energy FROM henergy WHERE serialno=? and trandate =? ";
/*  22:    */   private static final String INSERT_YEAR = " INSERT INTO yenergy(prodid,serialno,iyear,energy,isComplete) VALUES(?,?,?,?,?) ";
/*  23:    */   private static final String INSERT_MONTH = " INSERT INTO menergy(prodid,serialno,yearmonth,energy,isComplete) VALUES(?,?,?,?,?) ";
/*  24:    */   private static final String INSERT_DAY = " INSERT INTO denergy(prodid,serialno,trandate,energy,isComplete) VALUES(?,?,?,?,?) ";
/*  25:    */   private static final String INSERT_HOUR = " INSERT INTO henergy(prodid,serialno,trandate,ihour, energy,isComplete) VALUES(?,?,?,?,?,?) ";
/*  26:    */   private static final String DELETE_HOUR = " delete from henergy where serialno=? and trandate=? and ihour=? ";
/*  27:    */   private static final String DELETE_DAY = " DELETE FROM denergy where serialno=? and trandate=? ";
/*  28:    */   private static final String DELETE_MONTH = " DELETE FROM menergy where serialno=? and yearmonth=? ";
/*  29:    */   private static final String DELETE_YEAR = " DELETE FROM yenergy where serialno=? and iyear=? ";
/*  30:    */   private static final String DELETE_YEAR_ALL = "delete from yenergy";
/*  31:    */   private static final String DELETE_MONTH_ALL = "delete from menergy";
/*  32:    */   private static final String DELETE_DAY_ALL = "delete from denergy";
/*  33:    */   private static final String DELETE_HOUR_ALL = "delete from henergy";
/*  34:    */   public static final String QUERY_PRODID_Y = " SELECT distinct prodid FROM yenergy";
/*  35:    */   public static final String QUERY_PRODID_M = " SELECT distinct prodid FROM menergy";
/*  36:    */   public static final String QUERY_PRODID_D = " SELECT distinct prodid FROM denergy";
/*  37:    */   public static final String QUERY_PRODID_H = " SELECT distinct prodid FROM henergy";
/*  38:    */   public static final String QUERY_SERIALNO_Y = " SELECT distinct serialno FROM yenergy";
/*  39:    */   public static final String QUERY_SERIALNO_M = " SELECT distinct serialno FROM menergy";
/*  40:    */   public static final String QUERY_SERIALNO_D = " SELECT distinct serialno FROM denergy";
/*  41:    */   public static final String QUERY_SERIALNO_H = " SELECT distinct serialno FROM henergy";
/*  42:    */   public static final String INSERT_ENERGYBEGINDATE = " INSERT INTO energybegindate(prodid,serialno,begindate) VALUES(?,?,?) ";
/*  43:    */   public static final String QUERY_ENERGYBEGINDATE = " SELECT * FROM energybegindate where serialno=? ";
/*  44:    */   public static final String UPDATE_ENERGYBEGINDATE = " UPDATE energybegindate SET begindate=? WHERE serialno=? ";
/*  45:    */   
/*  46:    */   public double querySummaryHour(String serial, Calendar curdate)
/*  47:    */   {
/*  48: 55 */     Connection conn = DBManager.getConnection();
/*  49: 56 */     PreparedStatement ps = null;
/*  50: 57 */     ResultSet rs = null;
/*  51: 58 */     double quantity = 0.0D;
/*  52:    */     try
/*  53:    */     {
/*  54: 60 */       ps = conn.prepareStatement(" SELECT sum(energy) as energy FROM henergy WHERE serialno=? and trandate =? ");
/*  55: 61 */       ps.setString(1, serial);
/*  56: 62 */       ps.setDate(2, calendartoSqlDate(curdate));
/*  57: 63 */       rs = ps.executeQuery();
/*  58: 64 */       while (rs.next()) {
/*  59: 65 */         quantity = rs.getDouble(1);
/*  60:    */       }
/*  61:    */     }
/*  62:    */     catch (Exception e)
/*  63:    */     {
/*  64: 68 */       e.printStackTrace();
/*  65:    */     }
/*  66:    */     finally
/*  67:    */     {
/*  68: 70 */       close(rs, ps);
/*  69:    */     }
/*  70: 72 */     return quantity;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public void transactEnergyFromChangetime(String serialno, Calendar newtime)
/*  74:    */   {
/*  75: 76 */     Connection conn = DBManager.getConnection();
/*  76: 77 */     PreparedStatement ps = null;
/*  77:    */     try
/*  78:    */     {
/*  79: 80 */       Calendar trandate = DateUtils.getShortdate(newtime);
/*  80: 81 */       ps = conn
/*  81: 82 */         .prepareStatement(" update henergy set isComplete=0 where serialno=? and (trandate > ? or (trandate = ? and ihour >= ?))");
/*  82: 83 */       ps.setString(1, serialno);
/*  83: 84 */       ps.setDate(2, calendartoSqlDate(trandate));
/*  84: 85 */       ps.setDate(3, calendartoSqlDate(trandate));
/*  85: 86 */       ps.setInt(4, newtime.get(11));
/*  86: 87 */       ps.executeUpdate();
/*  87:    */       
/*  88: 89 */       ps = conn
/*  89: 90 */         .prepareStatement(" update denergy set isComplete=0 where serialno=? and trandate >= ? ");
/*  90: 91 */       ps.setString(1, serialno);
/*  91: 92 */       ps.setDate(2, calendartoSqlDate(trandate));
/*  92: 93 */       ps.executeUpdate();
/*  93:    */       
/*  94: 95 */       ps = conn
/*  95: 96 */         .prepareStatement(" update menergy set isComplete=0 where serialno=? and yearmonth >= ?");
/*  96: 97 */       ps.setString(1, serialno);
/*  97: 98 */       ps.setString(2, 
/*  98: 99 */         DateUtils.getFormatDate(trandate.getTime(), "yyyyMM"));
/*  99:100 */       ps.executeUpdate();
/* 100:    */       
/* 101:102 */       ps = conn
/* 102:103 */         .prepareStatement(" update yenergy set isComplete=0 where serialno=? and iyear >= ?");
/* 103:104 */       ps.setString(1, serialno);
/* 104:105 */       ps.setInt(2, trandate.get(1));
/* 105:106 */       ps.executeUpdate();
/* 106:    */     }
/* 107:    */     catch (Exception e)
/* 108:    */     {
/* 109:108 */       e.printStackTrace();
/* 110:    */     }
/* 111:    */     finally
/* 112:    */     {
/* 113:110 */       close(ps);
/* 114:    */     }
/* 115:    */   }
/* 116:    */   
/* 117:    */   public List<String> querySerialno(String mark)
/* 118:    */   {
/* 119:115 */     Connection conn = DBManager.getConnection();
/* 120:116 */     PreparedStatement ps = null;
/* 121:117 */     ResultSet rs = null;
/* 122:118 */     List<String> list = new ArrayList();
/* 123:    */     try
/* 124:    */     {
/* 125:120 */       if (mark == null) {
/* 126:121 */         mark = "";
/* 127:    */       }
/* 128:123 */       if (mark.equals("year")) {
/* 129:124 */         ps = conn.prepareStatement(" SELECT distinct serialno FROM yenergy");
/* 130:125 */       } else if (mark.equals("month")) {
/* 131:126 */         ps = conn.prepareStatement(" SELECT distinct serialno FROM menergy");
/* 132:127 */       } else if (mark.equals("day")) {
/* 133:128 */         ps = conn.prepareStatement(" SELECT distinct serialno FROM denergy");
/* 134:129 */       } else if (mark.equals("hour")) {
/* 135:130 */         ps = conn.prepareStatement(" SELECT distinct serialno FROM henergy");
/* 136:    */       } else {
/* 137:132 */         ps = conn.prepareStatement(" SELECT distinct serialno FROM yenergy");
/* 138:    */       }
/* 139:134 */       rs = ps.executeQuery();
/* 140:135 */       while (rs.next()) {
/* 141:136 */         list.add(rs.getString("serialno"));
/* 142:    */       }
/* 143:    */     }
/* 144:    */     catch (Exception e)
/* 145:    */     {
/* 146:139 */       e.printStackTrace();
/* 147:    */     }
/* 148:    */     finally
/* 149:    */     {
/* 150:141 */       close(rs, ps);
/* 151:    */     }
/* 152:143 */     return list;
/* 153:    */   }
/* 154:    */   
/* 155:    */   public List<String> queryProdid(String mark)
/* 156:    */   {
/* 157:147 */     Connection conn = DBManager.getConnection();
/* 158:148 */     PreparedStatement ps = null;
/* 159:149 */     ResultSet rs = null;
/* 160:150 */     List<String> list = new ArrayList();
/* 161:    */     try
/* 162:    */     {
/* 163:152 */       if (mark == null) {
/* 164:153 */         mark = "";
/* 165:    */       }
/* 166:155 */       if (mark.equals("year")) {
/* 167:156 */         ps = conn.prepareStatement(" SELECT distinct prodid FROM yenergy");
/* 168:157 */       } else if (mark.equals("month")) {
/* 169:158 */         ps = conn.prepareStatement(" SELECT distinct prodid FROM menergy");
/* 170:159 */       } else if (mark.equals("day")) {
/* 171:160 */         ps = conn.prepareStatement(" SELECT distinct prodid FROM denergy");
/* 172:161 */       } else if (mark.equals("hour")) {
/* 173:162 */         ps = conn.prepareStatement(" SELECT distinct prodid FROM henergy");
/* 174:    */       } else {
/* 175:164 */         ps = conn.prepareStatement(" SELECT distinct prodid FROM yenergy");
/* 176:    */       }
/* 177:166 */       rs = ps.executeQuery();
/* 178:167 */       while (rs.next()) {
/* 179:168 */         list.add(rs.getString("prodid"));
/* 180:    */       }
/* 181:    */     }
/* 182:    */     catch (Exception e)
/* 183:    */     {
/* 184:171 */       e.printStackTrace();
/* 185:    */     }
/* 186:    */     finally
/* 187:    */     {
/* 188:173 */       close(rs, ps);
/* 189:    */     }
/* 190:175 */     return list;
/* 191:    */   }
/* 192:    */   
/* 193:    */   private java.sql.Date calendartoSqlDate(Calendar date)
/* 194:    */   {
/* 195:179 */     java.sql.Date sqlDate = new java.sql.Date(date.getTime().getTime());
/* 196:180 */     return sqlDate;
/* 197:    */   }
/* 198:    */   
/* 199:    */   private Calendar sqlDatetoCalendar(java.sql.Date jdate)
/* 200:    */   {
/* 201:184 */     Calendar cdate = Calendar.getInstance();
/* 202:185 */     cdate.setTime(new java.sql.Date(jdate.getTime()));
/* 203:186 */     return cdate;
/* 204:    */   }
/* 205:    */   
/* 206:    */   public List<EnergyBean> queryYear(String serial, int startyear, int endyear)
/* 207:    */   {
/* 208:190 */     Connection conn = DBManager.getConnection();
/* 209:191 */     PreparedStatement ps = null;
/* 210:192 */     ResultSet rs = null;
/* 211:193 */     List<EnergyBean> list = new ArrayList();
/* 212:    */     try
/* 213:    */     {
/* 214:195 */       ps = conn.prepareStatement(" SELECT * FROM yenergy WHERE serialno=? and iyear BETWEEN ? AND ? order by iyear");
/* 215:196 */       ps.setString(1, serial);
/* 216:197 */       ps.setInt(2, startyear);
/* 217:198 */       ps.setInt(3, endyear);
/* 218:199 */       rs = ps.executeQuery();
/* 219:200 */       while (rs.next())
/* 220:    */       {
/* 221:201 */         EnergyBean bean = new EnergyBean();
/* 222:202 */         bean.setQueryperiod(0);
/* 223:203 */         bean.setYear(rs.getInt("iyear"));
/* 224:204 */         bean.setQuantity(rs.getDouble("energy"));
/* 225:205 */         bean.setComplete(rs.getBoolean("isComplete"));
/* 226:206 */         list.add(bean);
/* 227:    */       }
/* 228:    */     }
/* 229:    */     catch (Exception e)
/* 230:    */     {
/* 231:209 */       e.printStackTrace();
/* 232:    */     }
/* 233:    */     finally
/* 234:    */     {
/* 235:211 */       close(rs, ps);
/* 236:    */     }
/* 237:213 */     return list;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public List<EnergyBean> queryMonth(String serial, String startyearmonth, String endyearmonth)
/* 241:    */   {
/* 242:218 */     Connection conn = DBManager.getConnection();
/* 243:219 */     PreparedStatement ps = null;
/* 244:220 */     ResultSet rs = null;
/* 245:221 */     List<EnergyBean> list = new ArrayList();
/* 246:    */     try
/* 247:    */     {
/* 248:223 */       ps = conn.prepareStatement(" SELECT * FROM menergy WHERE serialno=? and yearmonth BETWEEN ? and ? order by yearmonth ");
/* 249:224 */       ps.setString(1, serial);
/* 250:225 */       ps.setString(2, startyearmonth);
/* 251:226 */       ps.setString(3, endyearmonth);
/* 252:227 */       rs = ps.executeQuery();
/* 253:228 */       while (rs.next())
/* 254:    */       {
/* 255:229 */         EnergyBean bean = new EnergyBean();
/* 256:230 */         bean.setQueryperiod(1);
/* 257:231 */         bean.setYearmonth(rs.getString("yearmonth"));
/* 258:232 */         bean.setQuantity(rs.getDouble("energy"));
/* 259:233 */         bean.setComplete(rs.getBoolean("isComplete"));
/* 260:234 */         list.add(bean);
/* 261:    */       }
/* 262:    */     }
/* 263:    */     catch (Exception e)
/* 264:    */     {
/* 265:237 */       e.printStackTrace();
/* 266:    */     }
/* 267:    */     finally
/* 268:    */     {
/* 269:239 */       close(rs, ps);
/* 270:    */     }
/* 271:242 */     return list;
/* 272:    */   }
/* 273:    */   
/* 274:    */   public List<EnergyBean> queryDay(String serial, Calendar startdate, Calendar enddate)
/* 275:    */   {
/* 276:247 */     Connection conn = DBManager.getConnection();
/* 277:248 */     PreparedStatement ps = null;
/* 278:249 */     ResultSet rs = null;
/* 279:250 */     List<EnergyBean> list = new ArrayList();
/* 280:    */     try
/* 281:    */     {
/* 282:252 */       ps = conn.prepareStatement(" SELECT * FROM denergy WHERE serialno=? and trandate BETWEEN ? and ? order by trandate ");
/* 283:253 */       ps.setString(1, serial);
/* 284:254 */       ps.setDate(2, calendartoSqlDate(startdate));
/* 285:255 */       ps.setDate(3, calendartoSqlDate(enddate));
/* 286:256 */       rs = ps.executeQuery();
/* 287:257 */       while (rs.next())
/* 288:    */       {
/* 289:258 */         EnergyBean bean = new EnergyBean();
/* 290:259 */         bean.setQueryperiod(2);
/* 291:260 */         bean.setTrandate(sqlDatetoCalendar(rs.getDate("trandate")));
/* 292:261 */         bean.setQuantity(rs.getDouble("energy"));
/* 293:262 */         bean.setComplete(rs.getBoolean("isComplete"));
/* 294:263 */         list.add(bean);
/* 295:    */       }
/* 296:    */     }
/* 297:    */     catch (Exception e)
/* 298:    */     {
/* 299:266 */       e.printStackTrace();
/* 300:    */     }
/* 301:    */     finally
/* 302:    */     {
/* 303:268 */       close(rs, ps);
/* 304:    */     }
/* 305:270 */     return list;
/* 306:    */   }
/* 307:    */   
/* 308:    */   public List<EnergyBean> queryHour(String serial, Calendar startdate, int starthour, Calendar enddate, int endhour)
/* 309:    */   {
/* 310:276 */     Connection conn = DBManager.getConnection();
/* 311:277 */     PreparedStatement ps = null;
/* 312:278 */     ResultSet rs = null;
/* 313:279 */     List<EnergyBean> list = new ArrayList();
/* 314:    */     try
/* 315:    */     {
/* 316:281 */       ps = conn.prepareStatement(" SELECT * FROM henergy WHERE serialno=? and trandate BETWEEN ? and ? order by trandate,ihour ");
/* 317:282 */       ps.setString(1, serial);
/* 318:283 */       ps.setDate(2, calendartoSqlDate(startdate));
/* 319:284 */       ps.setDate(3, calendartoSqlDate(enddate));
/* 320:285 */       rs = ps.executeQuery();
/* 321:287 */       while (rs.next())
/* 322:    */       {
/* 323:288 */         Calendar cal = sqlDatetoCalendar(rs.getDate("trandate"));
/* 324:289 */         boolean append = true;
/* 325:291 */         if ((cal.compareTo(startdate) == 0) && 
/* 326:292 */           (rs.getInt("ihour") < starthour)) {
/* 327:293 */           append = false;
/* 328:294 */         } else if ((cal.compareTo(enddate) == 0) && 
/* 329:295 */           (rs.getInt("ihour") > endhour)) {
/* 330:296 */           append = false;
/* 331:    */         }
/* 332:298 */         if (append)
/* 333:    */         {
/* 334:299 */           EnergyBean bean = new EnergyBean();
/* 335:300 */           bean.setQueryperiod(3);
/* 336:301 */           bean.setTrandate(cal);
/* 337:302 */           bean.setHour(rs.getInt("ihour"));
/* 338:303 */           bean.setQuantity(rs.getDouble("energy"));
/* 339:304 */           bean.setComplete(rs.getBoolean("isComplete"));
/* 340:305 */           list.add(bean);
/* 341:    */         }
/* 342:    */       }
/* 343:    */     }
/* 344:    */     catch (Exception e)
/* 345:    */     {
/* 346:309 */       e.printStackTrace();
/* 347:    */     }
/* 348:    */     finally
/* 349:    */     {
/* 350:311 */       close(rs, ps);
/* 351:    */     }
/* 352:313 */     return list;
/* 353:    */   }
/* 354:    */   
/* 355:    */   public boolean insertYear(String prodid, String serial, EnergyBean bean)
/* 356:    */   {
/* 357:317 */     boolean result = false;
/* 358:318 */     Connection conn = DBManager.getConnection();
/* 359:319 */     PreparedStatement ps = null;
/* 360:    */     label141:
/* 361:    */     try
/* 362:    */     {
/* 363:321 */       if (deleteYear(prodid, serial, bean.getYear()))
/* 364:    */       {
/* 365:322 */         if (bean.getQuantity() <= 0.0D) {
/* 366:    */           break label141;
/* 367:    */         }
/* 368:323 */         ps = conn.prepareStatement(" INSERT INTO yenergy(prodid,serialno,iyear,energy,isComplete) VALUES(?,?,?,?,?) ");
/* 369:324 */         ps.setString(1, prodid);
/* 370:325 */         ps.setString(2, serial);
/* 371:326 */         ps.setInt(3, bean.getYear());
/* 372:327 */         ps.setDouble(4, bean.getQuantity());
/* 373:328 */         ps.setBoolean(5, bean.isComplete());
/* 374:329 */         if (ps.executeUpdate() > 0) {
/* 375:330 */           result = true;
/* 376:    */         }
/* 377:    */       }
/* 378:    */     }
/* 379:    */     catch (Exception e)
/* 380:    */     {
/* 381:334 */       e.printStackTrace();
/* 382:    */     }
/* 383:    */     finally
/* 384:    */     {
/* 385:336 */       close(ps);
/* 386:    */     }
/* 387:338 */     return result;
/* 388:    */   }
/* 389:    */   
/* 390:    */   public boolean deleteYear(String prodid, String serial, int year)
/* 391:    */   {
/* 392:342 */     boolean result = true;
/* 393:343 */     Connection conn = DBManager.getConnection();
/* 394:344 */     PreparedStatement ps = null;
/* 395:    */     try
/* 396:    */     {
/* 397:346 */       ps = conn.prepareStatement(" DELETE FROM yenergy where serialno=? and iyear=? ");
/* 398:347 */       ps.setString(1, serial);
/* 399:348 */       ps.setInt(2, year);
/* 400:349 */       if (ps.executeUpdate() < 0) {
/* 401:350 */         result = false;
/* 402:    */       }
/* 403:    */     }
/* 404:    */     catch (Exception e)
/* 405:    */     {
/* 406:353 */       result = false;
/* 407:    */     }
/* 408:    */     finally
/* 409:    */     {
/* 410:355 */       close(ps);
/* 411:    */     }
/* 412:357 */     return result;
/* 413:    */   }
/* 414:    */   
/* 415:    */   public boolean deleteAll()
/* 416:    */   {
/* 417:361 */     boolean result = true;
/* 418:362 */     Connection conn = DBManager.getConnection();
/* 419:363 */     PreparedStatement ps = null;
/* 420:    */     try
/* 421:    */     {
/* 422:365 */       ps = conn.prepareStatement("delete from yenergy");
/* 423:366 */       if (ps.executeUpdate() < 0) {
/* 424:367 */         result = false;
/* 425:    */       }
/* 426:369 */       ps = conn.prepareStatement("delete from menergy");
/* 427:370 */       if (ps.executeUpdate() < 0) {
/* 428:371 */         result = false;
/* 429:    */       }
/* 430:373 */       ps = conn.prepareStatement("delete from denergy");
/* 431:374 */       if (ps.executeUpdate() < 0) {
/* 432:375 */         result = false;
/* 433:    */       }
/* 434:377 */       ps = conn.prepareStatement("delete from henergy");
/* 435:378 */       if (ps.executeUpdate() < 0) {
/* 436:379 */         result = false;
/* 437:    */       }
/* 438:    */     }
/* 439:    */     catch (Exception e)
/* 440:    */     {
/* 441:382 */       result = false;
/* 442:    */     }
/* 443:    */     finally
/* 444:    */     {
/* 445:384 */       close(ps);
/* 446:    */     }
/* 447:386 */     return result;
/* 448:    */   }
/* 449:    */   
/* 450:    */   public boolean deleteYearPriod(String serial, int startyear, int endyear)
/* 451:    */   {
/* 452:390 */     boolean result = true;
/* 453:391 */     Connection conn = DBManager.getConnection();
/* 454:392 */     PreparedStatement ps = null;
/* 455:    */     try
/* 456:    */     {
/* 457:394 */       ps = 
/* 458:395 */         conn.prepareStatement("DELETE FROM yenergy where serialno=? and iyear BETWEEN ? and ? ");
/* 459:396 */       ps.setString(1, serial);
/* 460:397 */       ps.setInt(2, startyear);
/* 461:398 */       ps.setInt(3, endyear);
/* 462:399 */       if (ps.executeUpdate() < 0) {
/* 463:400 */         result = false;
/* 464:    */       }
/* 465:    */     }
/* 466:    */     catch (Exception e)
/* 467:    */     {
/* 468:403 */       result = false;
/* 469:    */     }
/* 470:    */     finally
/* 471:    */     {
/* 472:405 */       close(ps);
/* 473:    */     }
/* 474:407 */     return result;
/* 475:    */   }
/* 476:    */   
/* 477:    */   public boolean insertMonth(String prodid, String serial, EnergyBean bean)
/* 478:    */   {
/* 479:411 */     boolean result = false;
/* 480:412 */     Connection conn = DBManager.getConnection();
/* 481:413 */     PreparedStatement ps = null;
/* 482:    */     label141:
/* 483:    */     try
/* 484:    */     {
/* 485:415 */       if (deleteMonth(prodid, serial, bean.getYearmonth()))
/* 486:    */       {
/* 487:416 */         if (bean.getQuantity() <= 0.0D) {
/* 488:    */           break label141;
/* 489:    */         }
/* 490:417 */         ps = conn.prepareStatement(" INSERT INTO menergy(prodid,serialno,yearmonth,energy,isComplete) VALUES(?,?,?,?,?) ");
/* 491:418 */         ps.setString(1, prodid);
/* 492:419 */         ps.setString(2, serial);
/* 493:420 */         ps.setString(3, bean.getYearmonth());
/* 494:421 */         ps.setDouble(4, bean.getQuantity());
/* 495:422 */         ps.setBoolean(5, bean.isComplete());
/* 496:423 */         if (ps.executeUpdate() > 0) {
/* 497:424 */           result = true;
/* 498:    */         }
/* 499:    */       }
/* 500:    */     }
/* 501:    */     catch (Exception e)
/* 502:    */     {
/* 503:428 */       e.printStackTrace();
/* 504:    */     }
/* 505:    */     finally
/* 506:    */     {
/* 507:430 */       close(ps);
/* 508:    */     }
/* 509:432 */     return result;
/* 510:    */   }
/* 511:    */   
/* 512:    */   public boolean deleteMonth(String prodid, String serial, String yearmonth)
/* 513:    */   {
/* 514:436 */     boolean result = true;
/* 515:437 */     Connection conn = DBManager.getConnection();
/* 516:438 */     PreparedStatement ps = null;
/* 517:    */     try
/* 518:    */     {
/* 519:440 */       ps = conn.prepareStatement(" DELETE FROM menergy where serialno=? and yearmonth=? ");
/* 520:441 */       ps.setString(1, serial);
/* 521:442 */       ps.setString(2, yearmonth);
/* 522:443 */       if (ps.executeUpdate() < 0) {
/* 523:444 */         result = false;
/* 524:    */       }
/* 525:    */     }
/* 526:    */     catch (Exception e)
/* 527:    */     {
/* 528:447 */       result = false;
/* 529:    */     }
/* 530:    */     finally
/* 531:    */     {
/* 532:449 */       close(ps);
/* 533:    */     }
/* 534:451 */     return result;
/* 535:    */   }
/* 536:    */   
/* 537:    */   public boolean deleteMonthPriod(String serial, String startyearmonth, String endyearmonth)
/* 538:    */   {
/* 539:456 */     boolean result = true;
/* 540:457 */     Connection conn = DBManager.getConnection();
/* 541:458 */     PreparedStatement ps = null;
/* 542:    */     try
/* 543:    */     {
/* 544:460 */       ps = 
/* 545:461 */         conn.prepareStatement("DELETE FROM menergy where serialno=? and yearmonth BETWEEN ? and ?");
/* 546:462 */       ps.setString(1, serial);
/* 547:463 */       ps.setString(2, startyearmonth);
/* 548:464 */       ps.setString(3, endyearmonth);
/* 549:465 */       if (ps.executeUpdate() < 0) {
/* 550:466 */         result = false;
/* 551:    */       }
/* 552:    */     }
/* 553:    */     catch (Exception e)
/* 554:    */     {
/* 555:469 */       result = false;
/* 556:    */     }
/* 557:    */     finally
/* 558:    */     {
/* 559:471 */       close(ps);
/* 560:    */     }
/* 561:473 */     return result;
/* 562:    */   }
/* 563:    */   
/* 564:    */   public boolean insertDay(String prodid, String serial, EnergyBean bean)
/* 565:    */   {
/* 566:477 */     boolean result = false;
/* 567:478 */     Connection conn = DBManager.getConnection();
/* 568:479 */     PreparedStatement ps = null;
/* 569:    */     try
/* 570:    */     {
/* 571:481 */       if (deleteDay(prodid, serial, bean))
/* 572:    */       {
/* 573:482 */         ps = conn.prepareStatement(" INSERT INTO denergy(prodid,serialno,trandate,energy,isComplete) VALUES(?,?,?,?,?) ");
/* 574:483 */         ps.setString(1, prodid);
/* 575:484 */         ps.setString(2, serial);
/* 576:485 */         ps.setDate(3, calendartoSqlDate(bean.getTrandate()));
/* 577:486 */         ps.setDouble(4, bean.getQuantity());
/* 578:487 */         ps.setBoolean(5, bean.isComplete());
/* 579:488 */         if (ps.executeUpdate() > 0) {
/* 580:489 */           result = true;
/* 581:    */         }
/* 582:    */       }
/* 583:    */     }
/* 584:    */     catch (Exception e)
/* 585:    */     {
/* 586:493 */       e.printStackTrace();
/* 587:    */     }
/* 588:    */     finally
/* 589:    */     {
/* 590:495 */       close(ps);
/* 591:    */     }
/* 592:497 */     return result;
/* 593:    */   }
/* 594:    */   
/* 595:    */   public boolean deleteDay(String prodid, String serial, EnergyBean bean)
/* 596:    */   {
/* 597:501 */     boolean result = true;
/* 598:502 */     Connection conn = DBManager.getConnection();
/* 599:503 */     PreparedStatement ps = null;
/* 600:    */     try
/* 601:    */     {
/* 602:505 */       ps = conn.prepareStatement(" DELETE FROM denergy where serialno=? and trandate=? ");
/* 603:506 */       ps.setString(1, serial);
/* 604:507 */       ps.setDate(2, calendartoSqlDate(bean.getTrandate()));
/* 605:508 */       if (ps.executeUpdate() < 0) {
/* 606:509 */         result = false;
/* 607:    */       }
/* 608:    */     }
/* 609:    */     catch (Exception e)
/* 610:    */     {
/* 611:512 */       result = false;
/* 612:    */     }
/* 613:    */     finally
/* 614:    */     {
/* 615:514 */       close(ps);
/* 616:    */     }
/* 617:516 */     return result;
/* 618:    */   }
/* 619:    */   
/* 620:    */   public boolean deleteDayPriod(String serial, Calendar startDate, Calendar endDate)
/* 621:    */   {
/* 622:521 */     boolean result = true;
/* 623:522 */     Connection conn = DBManager.getConnection();
/* 624:523 */     PreparedStatement ps = null;
/* 625:    */     try
/* 626:    */     {
/* 627:525 */       ps = 
/* 628:526 */         conn.prepareStatement("DELETE FROM denergy where serialno=? and trandate BETWEEN ? and ?");
/* 629:527 */       ps.setString(1, serial);
/* 630:528 */       ps.setDate(2, calendartoSqlDate(startDate));
/* 631:529 */       ps.setDate(3, calendartoSqlDate(endDate));
/* 632:530 */       if (ps.executeUpdate() < 0) {
/* 633:531 */         result = false;
/* 634:    */       }
/* 635:    */     }
/* 636:    */     catch (Exception e)
/* 637:    */     {
/* 638:534 */       result = false;
/* 639:    */     }
/* 640:    */     finally
/* 641:    */     {
/* 642:536 */       close(ps);
/* 643:    */     }
/* 644:538 */     return result;
/* 645:    */   }
/* 646:    */   
/* 647:    */   public boolean insertHour(String prodid, String serial, EnergyBean bean)
/* 648:    */   {
/* 649:542 */     boolean result = false;
/* 650:543 */     Connection conn = DBManager.getConnection();
/* 651:544 */     PreparedStatement ps = null;
/* 652:    */     try
/* 653:    */     {
/* 654:546 */       if (deleteHour(prodid, serial, bean))
/* 655:    */       {
/* 656:547 */         ps = conn.prepareStatement(" INSERT INTO henergy(prodid,serialno,trandate,ihour, energy,isComplete) VALUES(?,?,?,?,?,?) ");
/* 657:548 */         ps.setString(1, prodid);
/* 658:549 */         ps.setString(2, serial);
/* 659:550 */         ps.setDate(3, calendartoSqlDate(bean.getTrandate()));
/* 660:551 */         ps.setInt(4, bean.getHour());
/* 661:552 */         ps.setDouble(5, bean.getQuantity());
/* 662:553 */         ps.setBoolean(6, bean.isComplete());
/* 663:554 */         if (ps.executeUpdate() > 0) {
/* 664:555 */           result = true;
/* 665:    */         }
/* 666:    */       }
/* 667:    */     }
/* 668:    */     catch (Exception e)
/* 669:    */     {
/* 670:560 */       e.printStackTrace();
/* 671:    */     }
/* 672:    */     finally
/* 673:    */     {
/* 674:562 */       close(ps);
/* 675:    */     }
/* 676:564 */     return result;
/* 677:    */   }
/* 678:    */   
/* 679:    */   public boolean deleteHour(String prodid, String serial, EnergyBean bean)
/* 680:    */   {
/* 681:568 */     boolean result = true;
/* 682:569 */     Connection conn = DBManager.getConnection();
/* 683:570 */     PreparedStatement ps = null;
/* 684:571 */     if (conn != null) {
/* 685:    */       try
/* 686:    */       {
/* 687:573 */         ps = conn.prepareStatement(" delete from henergy where serialno=? and trandate=? and ihour=? ");
/* 688:574 */         ps.setString(1, serial);
/* 689:575 */         ps.setDate(2, calendartoSqlDate(bean.getTrandate()));
/* 690:576 */         ps.setInt(3, bean.getHour());
/* 691:577 */         if (ps.executeUpdate() < 0) {
/* 692:578 */           result = false;
/* 693:    */         }
/* 694:    */       }
/* 695:    */       catch (Exception e)
/* 696:    */       {
/* 697:581 */         e.printStackTrace();
/* 698:582 */         result = false;
/* 699:    */       }
/* 700:    */       finally
/* 701:    */       {
/* 702:584 */         close(ps);
/* 703:    */       }
/* 704:    */     } else {
/* 705:587 */       result = false;
/* 706:    */     }
/* 707:589 */     return result;
/* 708:    */   }
/* 709:    */   
/* 710:    */   public boolean deleteHourPriod(String serial, Calendar date, int starthour, int endhour)
/* 711:    */   {
/* 712:594 */     boolean result = true;
/* 713:595 */     Connection conn = DBManager.getConnection();
/* 714:596 */     PreparedStatement ps = null;
/* 715:597 */     if (conn != null) {
/* 716:    */       try
/* 717:    */       {
/* 718:599 */         ps = 
/* 719:600 */           conn.prepareStatement("delete from henergy where serialno=? and trandate=? and ihour BETWEEN ? and ? ");
/* 720:601 */         ps.setString(1, serial);
/* 721:602 */         ps.setDate(2, calendartoSqlDate(date));
/* 722:603 */         ps.setInt(3, starthour);
/* 723:604 */         ps.setInt(4, endhour);
/* 724:605 */         if (ps.executeUpdate() < 0) {
/* 725:606 */           result = false;
/* 726:    */         }
/* 727:    */       }
/* 728:    */       catch (Exception e)
/* 729:    */       {
/* 730:609 */         e.printStackTrace();
/* 731:610 */         result = false;
/* 732:    */       }
/* 733:    */       finally
/* 734:    */       {
/* 735:612 */         close(ps);
/* 736:    */       }
/* 737:    */     } else {
/* 738:615 */       result = false;
/* 739:    */     }
/* 740:617 */     return result;
/* 741:    */   }
/* 742:    */   
/* 743:    */   public boolean deleteDayMergeHour(String serial, Calendar trandate)
/* 744:    */   {
/* 745:621 */     boolean result = true;
/* 746:622 */     Connection conn = DBManager.getConnection();
/* 747:623 */     PreparedStatement ps = null;
/* 748:    */     try
/* 749:    */     {
/* 750:625 */       ps = 
/* 751:626 */         conn.prepareStatement("DELETE FROM henergy where serialno=? and trandate=? ");
/* 752:627 */       ps.setString(1, serial);
/* 753:628 */       ps.setDate(2, calendartoSqlDate(trandate));
/* 754:629 */       if (ps.executeUpdate() < 0) {
/* 755:630 */         result = false;
/* 756:    */       }
/* 757:    */     }
/* 758:    */     catch (Exception e)
/* 759:    */     {
/* 760:633 */       e.printStackTrace();
/* 761:634 */       result = false;
/* 762:    */     }
/* 763:    */     finally
/* 764:    */     {
/* 765:636 */       close(ps);
/* 766:    */     }
/* 767:638 */     return result;
/* 768:    */   }
/* 769:    */   
/* 770:    */   public boolean deleteMonthMergeHour(String serial, Calendar trandate)
/* 771:    */   {
/* 772:642 */     boolean result = true;
/* 773:643 */     Connection conn = DBManager.getConnection();
/* 774:644 */     PreparedStatement ps = null;
/* 775:    */     try
/* 776:    */     {
/* 777:646 */       ps = 
/* 778:647 */         conn.prepareStatement("DELETE FROM henergy where serialno=? and year(trandate)=? and month(trandate)=?");
/* 779:648 */       ps.setString(1, serial);
/* 780:649 */       ps.setInt(2, trandate.get(1));
/* 781:650 */       ps.setInt(3, trandate.get(2) + 1);
/* 782:651 */       if (ps.executeUpdate() < 0) {
/* 783:652 */         result = false;
/* 784:    */       }
/* 785:    */     }
/* 786:    */     catch (Exception e)
/* 787:    */     {
/* 788:655 */       e.printStackTrace();
/* 789:656 */       result = false;
/* 790:    */     }
/* 791:    */     finally
/* 792:    */     {
/* 793:658 */       close(ps);
/* 794:    */     }
/* 795:660 */     return result;
/* 796:    */   }
/* 797:    */   
/* 798:    */   public boolean deleteMonthMergeDay(String serial, Calendar trandate)
/* 799:    */   {
/* 800:664 */     boolean result = true;
/* 801:665 */     Connection conn = DBManager.getConnection();
/* 802:666 */     PreparedStatement ps = null;
/* 803:    */     try
/* 804:    */     {
/* 805:668 */       ps = 
/* 806:669 */         conn.prepareStatement("DELETE FROM denergy where serialno=? and year(trandate)=? and month(trandate)=?");
/* 807:670 */       ps.setString(1, serial);
/* 808:671 */       ps.setInt(2, trandate.get(1));
/* 809:672 */       ps.setInt(3, trandate.get(2) + 1);
/* 810:673 */       if (ps.executeUpdate() < 0) {
/* 811:674 */         result = false;
/* 812:    */       }
/* 813:    */     }
/* 814:    */     catch (Exception e)
/* 815:    */     {
/* 816:677 */       e.printStackTrace();
/* 817:678 */       result = false;
/* 818:    */     }
/* 819:    */     finally
/* 820:    */     {
/* 821:680 */       close(ps);
/* 822:    */     }
/* 823:682 */     return result;
/* 824:    */   }
/* 825:    */   
/* 826:    */   public boolean updateDay(String serialno, Calendar trandate)
/* 827:    */   {
/* 828:686 */     boolean result = false;
/* 829:687 */     Connection conn = DBManager.getConnection();
/* 830:688 */     PreparedStatement ps = null;
/* 831:    */     try
/* 832:    */     {
/* 833:690 */       ps = 
/* 834:691 */         conn.prepareStatement(" update denergy set isComplete=0 where serialno=? and trandate = ? ");
/* 835:692 */       ps.setString(1, serialno);
/* 836:693 */       ps.setDate(2, 
/* 837:694 */         calendartoSqlDate(DateUtils.getShortdate(trandate)));
/* 838:695 */       if (ps.executeUpdate() >= 0) {
/* 839:696 */         result = true;
/* 840:    */       }
/* 841:    */     }
/* 842:    */     catch (Exception e)
/* 843:    */     {
/* 844:699 */       e.printStackTrace();
/* 845:    */     }
/* 846:    */     finally
/* 847:    */     {
/* 848:701 */       close(ps);
/* 849:    */     }
/* 850:703 */     return result;
/* 851:    */   }
/* 852:    */   
/* 853:    */   public boolean updateMonth(String serialno, Calendar trandate)
/* 854:    */   {
/* 855:707 */     boolean result = false;
/* 856:708 */     Connection conn = DBManager.getConnection();
/* 857:709 */     PreparedStatement ps = null;
/* 858:    */     try
/* 859:    */     {
/* 860:711 */       ps = 
/* 861:712 */         conn.prepareStatement(" update menergy set isComplete=0 where serialno=? and yearmonth = ? ");
/* 862:713 */       ps.setString(1, serialno);
/* 863:714 */       ps.setString(2, 
/* 864:715 */         DateUtils.getFormatDate(trandate.getTime(), "yyyyMM"));
/* 865:716 */       if (ps.executeUpdate() >= 0) {
/* 866:717 */         result = true;
/* 867:    */       }
/* 868:    */     }
/* 869:    */     catch (Exception e)
/* 870:    */     {
/* 871:720 */       e.printStackTrace();
/* 872:    */     }
/* 873:    */     finally
/* 874:    */     {
/* 875:722 */       close(ps);
/* 876:    */     }
/* 877:724 */     return result;
/* 878:    */   }
/* 879:    */   
/* 880:    */   public boolean updateYear(String serialno, Calendar trandate)
/* 881:    */   {
/* 882:728 */     boolean result = false;
/* 883:729 */     Connection conn = DBManager.getConnection();
/* 884:730 */     PreparedStatement ps = null;
/* 885:    */     try
/* 886:    */     {
/* 887:732 */       ps = 
/* 888:733 */         conn.prepareStatement(" update yenergy set isComplete=0 where serialno=? and iyear = ? ");
/* 889:734 */       ps.setString(1, serialno);
/* 890:735 */       ps.setInt(2, trandate.get(1));
/* 891:736 */       if (ps.executeUpdate() >= 0) {
/* 892:737 */         result = true;
/* 893:    */       }
/* 894:    */     }
/* 895:    */     catch (Exception e)
/* 896:    */     {
/* 897:740 */       e.printStackTrace();
/* 898:    */     }
/* 899:    */     finally
/* 900:    */     {
/* 901:742 */       close(ps);
/* 902:    */     }
/* 903:744 */     return result;
/* 904:    */   }
/* 905:    */   
/* 906:    */   public boolean insertEnergyBeginDate(String prodid, String serial, Calendar date)
/* 907:    */   {
/* 908:749 */     boolean result = false;
/* 909:750 */     Connection conn = DBManager.getConnection();
/* 910:751 */     PreparedStatement ps = null;
/* 911:    */     try
/* 912:    */     {
/* 913:753 */       ps = conn.prepareStatement(" INSERT INTO energybegindate(prodid,serialno,begindate) VALUES(?,?,?) ");
/* 914:754 */       ps.setString(1, prodid);
/* 915:755 */       ps.setString(2, serial);
/* 916:756 */       ps.setDate(3, calendartoSqlDate(date));
/* 917:757 */       if (ps.executeUpdate() > 0) {
/* 918:758 */         result = true;
/* 919:    */       }
/* 920:    */     }
/* 921:    */     catch (Exception e)
/* 922:    */     {
/* 923:761 */       e.printStackTrace();
/* 924:    */     }
/* 925:    */     finally
/* 926:    */     {
/* 927:763 */       close(ps);
/* 928:    */     }
/* 929:765 */     return result;
/* 930:    */   }
/* 931:    */   
/* 932:    */   public Calendar queryEnergyBeginDate(String serialno)
/* 933:    */   {
/* 934:769 */     Calendar result = null;
/* 935:770 */     Connection conn = DBManager.getConnection();
/* 936:771 */     PreparedStatement ps = null;
/* 937:772 */     ResultSet rs = null;
/* 938:    */     try
/* 939:    */     {
/* 940:774 */       ps = conn.prepareStatement(" SELECT * FROM energybegindate where serialno=? ");
/* 941:775 */       ps.setString(1, serialno);
/* 942:776 */       rs = ps.executeQuery();
/* 943:777 */       while (rs.next()) {
/* 944:778 */         result = sqlDatetoCalendar(rs.getDate("begindate"));
/* 945:    */       }
/* 946:    */     }
/* 947:    */     catch (Exception e)
/* 948:    */     {
/* 949:781 */       e.printStackTrace();
/* 950:    */     }
/* 951:    */     finally
/* 952:    */     {
/* 953:783 */       close(rs, ps);
/* 954:    */     }
/* 955:785 */     return result;
/* 956:    */   }
/* 957:    */   
/* 958:    */   public boolean updateEnergyBeginDate(String prodid, String serialno, Calendar date)
/* 959:    */   {
/* 960:790 */     boolean result = true;
/* 961:791 */     Connection conn = DBManager.getConnection();
/* 962:792 */     PreparedStatement ps = null;
/* 963:    */     try
/* 964:    */     {
/* 965:794 */       ps = conn.prepareStatement(" UPDATE energybegindate SET begindate=? WHERE serialno=? ");
/* 966:795 */       ps.setString(1, serialno);
/* 967:796 */       ps.setDate(2, calendartoSqlDate(date));
/* 968:797 */       ps.executeUpdate();
/* 969:    */     }
/* 970:    */     catch (Exception e)
/* 971:    */     {
/* 972:799 */       result = false;
/* 973:    */     }
/* 974:    */     finally
/* 975:    */     {
/* 976:801 */       close(ps);
/* 977:    */     }
/* 978:803 */     return result;
/* 979:    */   }
/* 980:    */   
/* 981:    */   public static void main(String[] args)
/* 982:    */   {
/* 983:807 */     EnergyDao dao = new EnergyDao();
/* 984:808 */     boolean re = dao.deleteAll();
/* 985:809 */     System.out.println("result:" + re);
/* 986:    */   }
/* 987:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.EnergyDao
 * JD-Core Version:    0.7.0.1
 */