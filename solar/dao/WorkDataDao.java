/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*   5:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   6:    */ import cn.com.voltronic.solar.util.DateUtils;
/*   7:    */ import cn.com.voltronic.solar.util.VolUtil;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.sql.Connection;
/*  10:    */ import java.sql.PreparedStatement;
/*  11:    */ import java.sql.ResultSet;
/*  12:    */ import java.sql.Timestamp;
/*  13:    */ import java.util.ArrayList;
/*  14:    */ import java.util.Calendar;
/*  15:    */ import java.util.List;
/*  16:    */ 
/*  17:    */ public class WorkDataDao
/*  18:    */   extends BaseDao
/*  19:    */ {
/*  20:    */   public boolean writeRecords()
/*  21:    */   {
/*  22: 30 */     WorkInfo workinfo = (WorkInfo)getBeanBag().getBean(
/*  23: 31 */       "workinfo");
/*  24: 32 */     if ((workinfo.getProdid() == null) || 
/*  25: 33 */       ("".equals(workinfo.getProdid())) || 
/*  26: 34 */       (workinfo.getSerialno() == null) || 
/*  27: 35 */       ("".equals(workinfo.getSerialno())) || 
/*  28: 36 */       (workinfo.getCurrentTime() == null) || 
/*  29: 37 */       ("---".equals(workinfo.getWorkMode())) || 
/*  30: 38 */       ("".equals(workinfo.getWorkMode()))) {
/*  31: 39 */       return false;
/*  32:    */     }
/*  33: 41 */     return insertWorkInfo(workinfo);
/*  34:    */   }
/*  35:    */   
/*  36:    */   public boolean removeWork(String prodid, String serialno, java.util.Date date)
/*  37:    */   {
/*  38: 96 */     boolean result = false;
/*  39: 97 */     Connection conn = DBManager.getConnection();
/*  40: 98 */     PreparedStatement ps = null;
/*  41:    */     try
/*  42:    */     {
/*  43:100 */       String sqlStr = "delete from WORK_DATA where prodid=? and serialno=? and currentTime=?";
/*  44:101 */       ps = conn.prepareStatement(sqlStr);
/*  45:102 */       ps.setString(1, prodid);
/*  46:103 */       ps.setString(2, serialno);
/*  47:104 */       ps.setTimestamp(3, new Timestamp(date.getTime()));
/*  48:105 */       if (ps.executeUpdate() > 0) {
/*  49:106 */         result = true;
/*  50:    */       }
/*  51:    */     }
/*  52:    */     catch (Exception e)
/*  53:    */     {
/*  54:109 */       e.printStackTrace();
/*  55:    */     }
/*  56:    */     finally
/*  57:    */     {
/*  58:111 */       close(ps);
/*  59:    */     }
/*  60:113 */     return result;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public boolean removeAllWork(String prodid, String serialno, java.util.Date beginDate, java.util.Date endDate)
/*  64:    */   {
/*  65:117 */     Calendar end = Calendar.getInstance();
/*  66:118 */     end.setTime(endDate);
/*  67:119 */     Calendar start = Calendar.getInstance();
/*  68:120 */     start.setTime(beginDate);
/*  69:121 */     boolean result = false;
/*  70:122 */     Connection conn = DBManager.getConnection();
/*  71:123 */     PreparedStatement ps = null;
/*  72:    */     try
/*  73:    */     {
/*  74:125 */       ps = 
/*  75:126 */         conn.prepareStatement("delete from WORK_DATA where prodid=? and serialno=? and currentTime between ? and ?");
/*  76:127 */       ps.setString(1, prodid);
/*  77:128 */       ps.setString(2, serialno);
/*  78:129 */       ps.setTimestamp(3, new Timestamp(DateUtils.getStartofDayFullTime(
/*  79:130 */         start).getTime().getTime()));
/*  80:131 */       ps.setTimestamp(4, new Timestamp(
/*  81:132 */         DateUtils.getEndofDayFullTime(end).getTime().getTime()));
/*  82:133 */       if (ps.executeUpdate() > 0) {
/*  83:134 */         result = true;
/*  84:    */       }
/*  85:    */     }
/*  86:    */     catch (Exception e)
/*  87:    */     {
/*  88:137 */       e.printStackTrace();
/*  89:    */     }
/*  90:    */     finally
/*  91:    */     {
/*  92:139 */       close(ps);
/*  93:    */     }
/*  94:141 */     return result;
/*  95:    */   }
/*  96:    */   
/*  97:    */   public boolean insertWorkInfo(WorkInfo workinfo)
/*  98:    */   {
/*  99:145 */     boolean result = false;
/* 100:146 */     Connection conn = DBManager.getConnection();
/* 101:147 */     PreparedStatement ps = null;
/* 102:    */     try
/* 103:    */     {
/* 104:149 */       ps = 
/* 105:150 */         conn.prepareStatement(" INSERT INTO WORK_DATA (    prodid,serialno,workMode,gridVoltageR,gridPowerR,gridCurrentR,gridFrequency,acOutputVoltageR,acOutputPowerR,   acOutputFrequency,acOutputCurrentR,outputLoadPercent,pBatteryVoltage,nBatteryVoltage,   batteryCapacity,chargingCurrent,pvInputPower1,pvInputPower2,pvInputPower3,pvInputVoltage1,   pvInputVoltage2,pvInputVoltage3,maxTemperature,rGridVoltage,sGridVoltage,tGridVoltage,rsGridVoltage,rtGridVoltage,stGridVoltage,rGridCurrent,sGridCurrent,   tGridCurrent,rPhasePower,sPhasePower,tPhasePower,wholePower,rPhaseACOutputVoltage,sPhaseACOutputVoltage,tPhaseACOutputVoltage,rsPhaseACOutputVoltage,rtPhaseACOutputVoltage,stPhaseACOutputVoltage,   rACOutputCurrent,sACOutputCurrent,tACOutputCurrent,rPhaseACOutputLoad,sPhaseACOutputLoad,tPhaseACOutputLoad,wholeACOutputLoad,   batteryPieceNumber,batteryTotalCapacity,batteryRemainTime,OUTPUTAPPARENTPOWER,OUTPUTACTIVEPOWER,   TTLCHARGINGCURRENT,TTLOUTPUTAPPARENTPOWER,TTLOUTPUTACTIVEPOWER, TTLOUTPUTPERCENT,currentTime) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
/* 106:    */       
/* 107:    */ 
/* 108:    */ 
/* 109:    */ 
/* 110:    */ 
/* 111:    */ 
/* 112:    */ 
/* 113:    */ 
/* 114:    */ 
/* 115:    */ 
/* 116:    */ 
/* 117:    */ 
/* 118:163 */       ps.setString(1, workinfo.getProdid());
/* 119:164 */       ps.setString(2, workinfo.getSerialno());
/* 120:165 */       ps.setString(3, workinfo.getWorkMode());
/* 121:166 */       ps.setDouble(4, workinfo.getGridVoltageR());
/* 122:167 */       ps.setDouble(5, workinfo.getGridPowerR());
/* 123:168 */       ps.setDouble(6, workinfo.getGridCurrentR());
/* 124:169 */       ps.setDouble(7, workinfo.getGridFrequency());
/* 125:170 */       ps.setDouble(8, workinfo.getAcOutputVoltageR());
/* 126:171 */       ps.setDouble(9, workinfo.getAcOutputPowerR());
/* 127:    */       
/* 128:173 */       ps.setDouble(10, workinfo.getAcOutputFrequency());
/* 129:174 */       ps.setDouble(11, workinfo.getAcOutputCurrentR());
/* 130:175 */       ps.setInt(12, workinfo.getOutputLoadPercent());
/* 131:176 */       ps.setDouble(13, workinfo.getPBatteryVoltage());
/* 132:177 */       ps.setDouble(14, workinfo.getNBatteryVoltage());
/* 133:    */       
/* 134:179 */       ps.setInt(15, workinfo.getBatteryCapacity());
/* 135:180 */       ps.setDouble(16, workinfo.getChargingCurrent());
/* 136:181 */       ps.setInt(17, workinfo.getPvInputPower1());
/* 137:182 */       ps.setInt(18, workinfo.getPvInputPower2());
/* 138:183 */       ps.setInt(19, workinfo.getPvInputPower3());
/* 139:184 */       ps.setDouble(20, workinfo.getPvInputVoltage1());
/* 140:    */       
/* 141:186 */       ps.setDouble(21, workinfo.getPvInputVoltage2());
/* 142:187 */       ps.setDouble(22, workinfo.getPvInputVoltage3());
/* 143:188 */       ps.setDouble(23, workinfo.getMaxTemperature());
/* 144:189 */       ps.setDouble(24, workinfo.getRGridVoltage());
/* 145:190 */       ps.setDouble(25, workinfo.getSGridVoltage());
/* 146:191 */       ps.setDouble(26, workinfo.getTGridVoltage());
/* 147:192 */       ps.setDouble(27, workinfo.getRsGridVoltage());
/* 148:193 */       ps.setDouble(28, workinfo.getRtGridVoltage());
/* 149:194 */       ps.setDouble(29, workinfo.getStGridVoltage());
/* 150:195 */       ps.setDouble(30, workinfo.getRGridCurrent());
/* 151:196 */       ps.setDouble(31, workinfo.getSGridCurrent());
/* 152:197 */       ps.setDouble(32, workinfo.getTGridCurrent());
/* 153:198 */       ps.setInt(33, workinfo.getRPhasePower());
/* 154:199 */       ps.setInt(34, workinfo.getSPhasePower());
/* 155:200 */       ps.setInt(35, workinfo.getTPhasePower());
/* 156:201 */       ps.setInt(36, workinfo.getWholePower());
/* 157:202 */       ps.setDouble(37, workinfo.getRPhaseACOutputVoltage());
/* 158:203 */       ps.setDouble(38, workinfo.getSPhaseACOutputVoltage());
/* 159:204 */       ps.setDouble(39, workinfo.getTPhaseACOutputVoltage());
/* 160:205 */       ps.setDouble(40, workinfo.getRsPhaseACOutputVoltage());
/* 161:206 */       ps.setDouble(41, workinfo.getRtPhaseACOutputVoltage());
/* 162:207 */       ps.setDouble(42, workinfo.getStPhaseACOutputVoltage());
/* 163:    */       
/* 164:209 */       ps.setDouble(43, workinfo.getRACOutputCurrent());
/* 165:210 */       ps.setDouble(44, workinfo.getSACOutputCurrent());
/* 166:211 */       ps.setDouble(45, workinfo.getTACOutputCurrent());
/* 167:212 */       ps.setInt(46, workinfo.getRPhaseACOutputLoad());
/* 168:213 */       ps.setInt(47, workinfo.getSPhaseACOutputLoad());
/* 169:214 */       ps.setInt(48, workinfo.getTPhaseACOutputLoad());
/* 170:215 */       ps.setInt(49, workinfo.getWholeACOutputLoad());
/* 171:216 */       ps.setInt(50, workinfo.getBatteryPieceNumber());
/* 172:217 */       ps.setInt(51, workinfo.getBatteryTotalCapacity());
/* 173:218 */       ps.setInt(52, workinfo.getBatteryRemainTime());
/* 174:219 */       ps.setDouble(53, workinfo.getAcOutputApparentPower());
/* 175:220 */       ps.setDouble(54, workinfo.getAcOutputActivePower());
/* 176:    */       
/* 177:222 */       ps.setDouble(55, workinfo.getTtlChargingCurrent());
/* 178:223 */       ps.setDouble(56, workinfo.getAcTtlOutputApparentPower());
/* 179:224 */       ps.setDouble(57, workinfo.getAcTtlOutputActivePower());
/* 180:225 */       ps.setDouble(58, workinfo.getAcTtlOutputPercent());
/* 181:227 */       if (workinfo.getCurrentTime() != null)
/* 182:    */       {
/* 183:228 */         ps.setTimestamp(59, new Timestamp(
/* 184:229 */           workinfo.getCurrentTime().getTime()));
/* 185:230 */         if (ps.executeUpdate() > 0) {
/* 186:231 */           result = true;
/* 187:    */         }
/* 188:    */       }
/* 189:    */     }
/* 190:    */     catch (Exception e)
/* 191:    */     {
/* 192:235 */       e.printStackTrace();
/* 193:    */     }
/* 194:    */     finally
/* 195:    */     {
/* 196:237 */       close(ps);
/* 197:    */     }
/* 198:239 */     return result;
/* 199:    */   }
/* 200:    */   
/* 201:    */   public List<WorkInfo> queryWorkInfo(String prodid, String serialno, java.util.Date beginDate, java.util.Date endDate)
/* 202:    */   {
/* 203:244 */     Calendar end = Calendar.getInstance();
/* 204:245 */     end.setTime(endDate);
/* 205:246 */     Calendar start = Calendar.getInstance();
/* 206:247 */     start.setTime(beginDate);
/* 207:248 */     List<WorkInfo> result = new ArrayList();
/* 208:249 */     Connection conn = DBManager.getConnection();
/* 209:250 */     PreparedStatement ps = null;
/* 210:251 */     ResultSet rs = null;
/* 211:    */     try
/* 212:    */     {
/* 213:253 */       ps = 
/* 214:254 */         conn.prepareStatement("select * from WORK_DATA where prodid=? and serialno=? and currenttime between ? and ? order by currenttime desc");
/* 215:255 */       ps.setString(1, prodid);
/* 216:256 */       ps.setString(2, serialno);
/* 217:257 */       ps.setTimestamp(3, new Timestamp(DateUtils.getStartofDayFullTime(
/* 218:258 */         start).getTime().getTime()));
/* 219:259 */       ps.setTimestamp(4, new Timestamp(
/* 220:260 */         DateUtils.getEndofDayFullTime(end).getTime().getTime()));
/* 221:261 */       rs = ps.executeQuery();
/* 222:262 */       conn.commit();
/* 223:263 */       while (rs.next())
/* 224:    */       {
/* 225:264 */         WorkInfo data = new WorkInfo();
/* 226:265 */         data = getWorkInDB(data, rs);
/* 227:266 */         result.add(data);
/* 228:    */       }
/* 229:    */     }
/* 230:    */     catch (Exception e)
/* 231:    */     {
/* 232:269 */       e.printStackTrace();
/* 233:    */     }
/* 234:    */     finally
/* 235:    */     {
/* 236:271 */       close(rs, ps);
/* 237:    */     }
/* 238:273 */     return result;
/* 239:    */   }
/* 240:    */   
/* 241:    */   private Calendar[] getMinMaxDate(String prodid, String serialno, int year, java.util.Date begin, java.util.Date end)
/* 242:    */   {
/* 243:278 */     Connection conn = DBManager.getConnection();
/* 244:279 */     PreparedStatement ps = null;
/* 245:280 */     ResultSet rs = null;
/* 246:281 */     Calendar[] result = new Calendar[2];
/* 247:283 */     if (conn != null) {
/* 248:    */       try
/* 249:    */       {
/* 250:285 */         ps = 
/* 251:286 */           conn.prepareStatement(" select min(currenttime) mindate, max(currenttime) maxdate from WORK_DATA where prodid=? and serialno=? and currenttime>= ? and currenttime< ? ");
/* 252:287 */         ps.setString(1, prodid);
/* 253:288 */         ps.setString(2, serialno);
/* 254:289 */         ps.setDate(3, new java.sql.Date(begin.getTime()));
/* 255:290 */         ps.setDate(4, new java.sql.Date(end.getTime()));
/* 256:291 */         rs = ps.executeQuery();
/* 257:292 */         if ((rs != null) && (rs.next()) && 
/* 258:293 */           (rs.getTimestamp("mindate") != null) && 
/* 259:294 */           (rs.getTimestamp("maxdate") != null))
/* 260:    */         {
/* 261:295 */           result[0] = Calendar.getInstance();
/* 262:296 */           result[0].setTime(rs.getTimestamp("mindate"));
/* 263:297 */           result[1] = Calendar.getInstance();
/* 264:298 */           result[1].setTime(rs.getTimestamp("maxdate"));
/* 265:    */         }
/* 266:    */       }
/* 267:    */       catch (Exception localException) {}finally
/* 268:    */       {
/* 269:304 */         close(rs, ps);
/* 270:    */       }
/* 271:    */     }
/* 272:307 */     return result;
/* 273:    */   }
/* 274:    */   
/* 275:    */   private String workInfofieldList()
/* 276:    */   {
/* 277:311 */     String field = " max(prodid) as prodid, max(serialno) serialno, max(currenttime) as currenttime, max(workMode) as workmode, max(gridVoltageR) as gridVoltageR ,max(gridPowerR) as gridPowerR,max(gridCurrentR) as gridCurrentR, max(gridFrequency) as gridFrequency,max(acOutputVoltageR) as acOutputVoltageR,max(acOutputPowerR) as acOutputPowerR, max(acOutputFrequency) as acOutputFrequency,max(acOutputCurrentR) as acOutputCurrentR,max(outputLoadPercent) as outputLoadPercent, max(pBatteryVoltage) as pBatteryVoltage,max(nBatteryVoltage) as nBatteryVoltage,max(batteryCapacity) as batteryCapacity,  max(chargingCurrent) as chargingCurrent,max(pvInputPower1) as pvInputPower1,max(pvInputPower2) as pvInputPower2,  max(pvInputPower3) as pvInputPower3,max(pvInputVoltage1) as pvInputVoltage1,max(pvInputVoltage2) as pvInputVoltage2, max(pvInputVoltage3) as pvInputVoltage3,max(maxTemperature) as maxTemperature,max(rGridVoltage) as rGridVoltage,max(sGridVoltage) as sGridVoltage, max(tGridVoltage) as tGridVoltage,max(rsGridVoltage) as rsGridVoltage,max(rtGridVoltage) as rtGridVoltage,max(stGridVoltage) as stGridVoltage, max(rGridCurrent) as rGridCurrent,max(sGridCurrent) as sGridCurrent,max(tGridCurrent) as tGridCurrent,  max(rPhasePower) as rPhasePower,max(sPhasePower) as sPhasePower,max(tPhasePower) as tPhasePower,max(wholePower) as wholePower, max(rPhaseACOutputVoltage) as rPhaseACOutputVoltage,max(sPhaseACOutputVoltage) as sPhaseACOutputVoltage,max(tPhaseACOutputVoltage) as tPhaseACOutputVoltage , max(rsPhaseACOutputVoltage) as rsPhaseACOutputVoltage,max(rtPhaseACOutputVoltage) as rtPhaseACOutputVoltage, max(stPhaseACOutputVoltage) as stPhaseACOutputVoltage,max(rACOutputCurrent) as rACOutputCurrent,max(sACOutputCurrent) as sACOutputCurrent,max(tACOutputCurrent) as tACOutputCurrent, max(rPhaseACOutputLoad) as rPhaseACOutputLoad,max(sPhaseACOutputLoad) as sPhaseACOutputLoad,max(tPhaseACOutputLoad) as tPhaseACOutputLoad, max(batteryPieceNumber) as batteryPieceNumber,max(batteryTotalCapacity) as batteryTotalCapacity,max(batteryRemainTime) as batteryRemainTime, max(wholeACOutputLoad) as wholeACOutputLoad,max(OUTPUTAPPARENTPOWER) as OUTPUTAPPARENTPOWER,max(OUTPUTACTIVEPOWER) as OUTPUTACTIVEPOWER, max(TTLCHARGINGCURRENT) as TTLCHARGINGCURRENT,max(TTLOUTPUTAPPARENTPOWER) as TTLOUTPUTAPPARENTPOWER,max(TTLOUTPUTACTIVEPOWER) as TTLOUTPUTACTIVEPOWER,max(TTLOUTPUTPERCENT) as TTLOUTPUTPERCENT ";
/* 278:    */     
/* 279:    */ 
/* 280:    */ 
/* 281:    */ 
/* 282:    */ 
/* 283:    */ 
/* 284:    */ 
/* 285:    */ 
/* 286:    */ 
/* 287:    */ 
/* 288:    */ 
/* 289:    */ 
/* 290:    */ 
/* 291:    */ 
/* 292:    */ 
/* 293:    */ 
/* 294:    */ 
/* 295:    */ 
/* 296:330 */     return field;
/* 297:    */   }
/* 298:    */   
/* 299:    */   private WorkInfo getWorkData(String prodid, String serialno, int year, Calendar mindbDate, Calendar maxdbDate, Calendar begin, Calendar enddate)
/* 300:    */   {
/* 301:336 */     Connection conn = DBManager.getConnection();
/* 302:337 */     PreparedStatement ps = null;
/* 303:338 */     ResultSet rs = null;
/* 304:339 */     WorkInfo work = new WorkInfo();
/* 305:    */     try
/* 306:    */     {
/* 307:341 */       if ((mindbDate != null) && (maxdbDate != null) && 
/* 308:342 */         (begin.compareTo(maxdbDate) <= 0) && 
/* 309:343 */         (enddate.compareTo(mindbDate) >= 0))
/* 310:    */       {
/* 311:344 */         ps = 
/* 312:345 */           conn.prepareStatement(" select " + 
/* 313:346 */           workInfofieldList() + 
/* 314:347 */           " from WORK_DATA where prodid= ? and  SERIALNO = ? and currenttime>= ? and currenttime<? order by currenttime ");
/* 315:348 */         Timestamp timeb = new Timestamp(
/* 316:349 */           begin.getTime().getTime());
/* 317:350 */         Timestamp timee = new Timestamp(
/* 318:351 */           enddate.getTime().getTime());
/* 319:    */         
/* 320:353 */         ps.setString(1, prodid);
/* 321:354 */         ps.setString(2, serialno);
/* 322:355 */         ps.setTimestamp(3, timeb);
/* 323:356 */         ps.setTimestamp(4, timee);
/* 324:357 */         rs = ps.executeQuery();
/* 325:358 */         if ((rs != null) && (rs.next())) {
/* 326:359 */           work = getWorkInDB(work, rs);
/* 327:    */         }
/* 328:    */       }
/* 329:363 */       work.setCurrentTime(begin.getTime());
/* 330:    */     }
/* 331:    */     catch (Exception e)
/* 332:    */     {
/* 333:365 */       e.printStackTrace();
/* 334:    */     }
/* 335:    */     finally
/* 336:    */     {
/* 337:367 */       close(rs, ps);
/* 338:    */     }
/* 339:369 */     return work;
/* 340:    */   }
/* 341:    */   
/* 342:    */   public List<WorkInfo> getWorkDatasByYear(int year, String prodid, String serialno)
/* 343:    */   {
/* 344:373 */     Calendar ca = Calendar.getInstance();
/* 345:374 */     ca.set(year, 0, 1, 0, 0, 0);
/* 346:    */     
/* 347:376 */     Calendar endtime = Calendar.getInstance();
/* 348:377 */     endtime.set(year + 1, 0, 1, 0, 0, 0);
/* 349:    */     
/* 350:379 */     Calendar tempDate = (Calendar)ca.clone();
/* 351:380 */     List<WorkInfo> works = new ArrayList();
/* 352:    */     try
/* 353:    */     {
/* 354:382 */       Calendar[] minmaxdate = getMinMaxDate(prodid, serialno, year, 
/* 355:383 */         ca.getTime(), endtime.getTime());
/* 356:384 */       while (ca.before(endtime))
/* 357:    */       {
/* 358:385 */         tempDate.add(5, 3);
/* 359:386 */         if (tempDate.after(endtime)) {
/* 360:387 */           tempDate = endtime;
/* 361:    */         }
/* 362:389 */         works.add(
/* 363:390 */           getWorkData(prodid, serialno, year, minmaxdate[0], minmaxdate[1], ca, tempDate));
/* 364:391 */         ca.add(6, 3);
/* 365:    */       }
/* 366:    */     }
/* 367:    */     catch (Exception e)
/* 368:    */     {
/* 369:394 */       e.printStackTrace();
/* 370:    */     }
/* 371:396 */     return works;
/* 372:    */   }
/* 373:    */   
/* 374:    */   public List<WorkInfo> getWorkDatasByMonth(int year, int month, String prodid, String serialno)
/* 375:    */   {
/* 376:401 */     Calendar ca = Calendar.getInstance();
/* 377:402 */     ca.set(year, month - 1, 1, 0, 0, 0);
/* 378:    */     
/* 379:404 */     Calendar endtime = Calendar.getInstance();
/* 380:405 */     endtime.set(year, month, 1, 0, 0, 0);
/* 381:    */     
/* 382:407 */     Calendar tempDate = (Calendar)ca.clone();
/* 383:    */     
/* 384:409 */     List<WorkInfo> works = new ArrayList();
/* 385:    */     try
/* 386:    */     {
/* 387:412 */       Calendar[] minmaxdate = getMinMaxDate(prodid, serialno, year, 
/* 388:413 */         ca.getTime(), endtime.getTime());
/* 389:414 */       while (ca.before(endtime))
/* 390:    */       {
/* 391:415 */         tempDate.add(11, 6);
/* 392:416 */         if (tempDate.after(endtime)) {
/* 393:417 */           tempDate = endtime;
/* 394:    */         }
/* 395:419 */         works.add(
/* 396:420 */           getWorkData(prodid, serialno, year, minmaxdate[0], minmaxdate[1], ca, tempDate));
/* 397:421 */         ca.add(11, 6);
/* 398:    */       }
/* 399:    */     }
/* 400:    */     catch (Exception e)
/* 401:    */     {
/* 402:424 */       e.printStackTrace();
/* 403:    */     }
/* 404:426 */     return works;
/* 405:    */   }
/* 406:    */   
/* 407:    */   public List<WorkInfo> getWorkDatasByDay(java.util.Date queryDate, String prodid, String serialno)
/* 408:    */   {
/* 409:430 */     Calendar ca = Calendar.getInstance();
/* 410:431 */     ca.setTime(queryDate);
/* 411:432 */     ca = DateUtils.getShortdate(ca);
/* 412:    */     
/* 413:434 */     Calendar endtime = (Calendar)ca.clone();
/* 414:435 */     endtime.add(5, 1);
/* 415:    */     
/* 416:437 */     Calendar tempDate = (Calendar)ca.clone();
/* 417:    */     
/* 418:439 */     List<WorkInfo> works = new ArrayList();
/* 419:    */     
/* 420:441 */     int year = ca.get(1);
/* 421:    */     try
/* 422:    */     {
/* 423:443 */       Calendar[] minmaxdate = getMinMaxDate(prodid, serialno, year, 
/* 424:444 */         ca.getTime(), endtime.getTime());
/* 425:445 */       while (ca.before(endtime))
/* 426:    */       {
/* 427:446 */         tempDate.add(12, 15);
/* 428:447 */         if (tempDate.after(endtime)) {
/* 429:448 */           tempDate = endtime;
/* 430:    */         }
/* 431:450 */         works.add(
/* 432:451 */           getWorkData(prodid, serialno, year, minmaxdate[0], minmaxdate[1], ca, tempDate));
/* 433:452 */         ca.add(12, 15);
/* 434:    */       }
/* 435:    */     }
/* 436:    */     catch (Exception localException) {}
/* 437:457 */     return works;
/* 438:    */   }
/* 439:    */   
/* 440:    */   public List<WorkInfo> getWorkDatasByHour(java.util.Date queryDate, int hour, String prodid, String serialno)
/* 441:    */   {
/* 442:462 */     Connection conn = DBManager.getConnection();
/* 443:463 */     PreparedStatement ps = null;
/* 444:464 */     ResultSet rs = null;
/* 445:465 */     Calendar ca = Calendar.getInstance();
/* 446:466 */     ca.setTime(queryDate);
/* 447:467 */     ca = DateUtils.getShortdate(ca);
/* 448:468 */     ca.set(11, hour);
/* 449:    */     
/* 450:470 */     Calendar endtime = (Calendar)ca.clone();
/* 451:471 */     endtime.add(11, 1);
/* 452:    */     
/* 453:473 */     List<WorkInfo> works = new ArrayList();
/* 454:    */     try
/* 455:    */     {
/* 456:476 */       ps = 
/* 457:477 */         conn.prepareStatement(
/* 458:478 */         " select * from WORK_DATA where prodid=? and serialno=? and currenttime>=? and currenttime<? order by currenttime ", 
/* 459:479 */         1004, 
/* 460:480 */         1007);
/* 461:    */       
/* 462:482 */       ps.setString(1, prodid);
/* 463:483 */       ps.setString(2, serialno);
/* 464:484 */       ps.setTimestamp(3, new Timestamp(ca.getTime().getTime()));
/* 465:485 */       ps.setTimestamp(4, new Timestamp(
/* 466:486 */         endtime.getTime().getTime()));
/* 467:487 */       rs = ps.executeQuery();
/* 468:    */       
/* 469:489 */       Long temp = null;
/* 470:490 */       int target = 60;
/* 471:491 */       while (rs.next())
/* 472:    */       {
/* 473:492 */         Long time = Long.valueOf(rs.getTimestamp("currentTime").getTime());
/* 474:493 */         if (temp != null)
/* 475:    */         {
/* 476:494 */           int temptarget = (int)(time.longValue() - temp.longValue()) / 1000;
/* 477:495 */           if ((temptarget < target) && (temptarget % 30 == 0) && (temptarget > 0)) {
/* 478:496 */             target = temptarget;
/* 479:    */           }
/* 480:    */         }
/* 481:499 */         temp = time;
/* 482:    */       }
/* 483:501 */       rs.beforeFirst();
/* 484:502 */       while (rs.next())
/* 485:    */       {
/* 486:503 */         java.util.Date recordTime = rs.getTimestamp("currentTime");
/* 487:504 */         long recordMillis = recordTime.getTime();
/* 488:506 */         if (recordMillis >= ca.getTimeInMillis())
/* 489:    */         {
/* 490:510 */           WorkInfo workinfo = new WorkInfo();
/* 491:511 */           while (recordMillis > ca.getTimeInMillis() + 60000L)
/* 492:    */           {
/* 493:512 */             workinfo.setCurrentTime(ca.getTime());
/* 494:513 */             works.add(workinfo);
/* 495:514 */             ca.add(13, target);
/* 496:    */           }
/* 497:516 */           WorkInfo work = new WorkInfo();
/* 498:517 */           work = getWorkInDB(work, rs);
/* 499:518 */           works.add(work);
/* 500:519 */           ca.add(13, target);
/* 501:    */         }
/* 502:    */       }
/* 503:521 */       while (ca.get(11) == hour)
/* 504:    */       {
/* 505:522 */         WorkInfo work = new WorkInfo();
/* 506:523 */         work.setCurrentTime(ca.getTime());
/* 507:524 */         works.add(work);
/* 508:525 */         ca.add(13, target);
/* 509:    */       }
/* 510:    */     }
/* 511:    */     catch (Exception e)
/* 512:    */     {
/* 513:528 */       e.printStackTrace();
/* 514:    */     }
/* 515:    */     finally
/* 516:    */     {
/* 517:530 */       close(rs, ps);
/* 518:    */     }
/* 519:532 */     return works;
/* 520:    */   }
/* 521:    */   
/* 522:    */   private WorkInfo getWorkInDB(WorkInfo work, ResultSet rs)
/* 523:    */     throws Exception
/* 524:    */   {
/* 525:536 */     WorkInfo data = work;
/* 526:537 */     data.setProdid(rs.getString("prodid"));
/* 527:538 */     data.setSerialno(rs.getString("serialno"));
/* 528:539 */     data.setWorkMode(rs.getString("workMode"));
/* 529:540 */     data.setGridVoltageR(rs.getDouble("gridVoltageR"));
/* 530:541 */     data.setGridPowerR(rs.getDouble("gridPowerR"));
/* 531:542 */     data.setGridCurrentR(rs.getDouble("gridCurrentR"));
/* 532:543 */     data.setGridFrequency(rs.getDouble("gridFrequency"));
/* 533:544 */     data.setAcOutputVoltageR(rs.getDouble("acOutputVoltageR"));
/* 534:545 */     data.setAcOutputPowerR(rs.getDouble("acOutputPowerR"));
/* 535:546 */     data.setAcOutputFrequency(rs.getDouble("acOutputFrequency"));
/* 536:547 */     data.setAcOutputCurrentR(rs.getDouble("acOutputCurrentR"));
/* 537:548 */     data.setOutputLoadPercent(rs.getInt("outputLoadPercent"));
/* 538:549 */     data.setPBatteryVoltage(rs.getDouble("pBatteryVoltage"));
/* 539:550 */     data.setNBatteryVoltage(rs.getDouble("nBatteryVoltage"));
/* 540:551 */     data.setBatteryCapacity(rs.getInt("batteryCapacity"));
/* 541:552 */     data.setChargingCurrent(rs.getDouble("chargingCurrent"));
/* 542:553 */     data.setPvInputPower1(rs.getInt("pvInputPower1"));
/* 543:554 */     data.setPvInputPower2(rs.getInt("pvInputPower2"));
/* 544:555 */     data.setPvInputPower3(rs.getInt("pvInputPower3"));
/* 545:556 */     data.setPvInputVoltage1(rs.getDouble("pvInputVoltage1"));
/* 546:557 */     data.setPvInputVoltage2(rs.getDouble("pvInputVoltage2"));
/* 547:558 */     data.setPvInputVoltage3(rs.getDouble("pvInputVoltage3"));
/* 548:559 */     data.setMaxTemperature(rs.getDouble("maxTemperature"));
/* 549:560 */     data.setRGridVoltage(rs.getDouble("rGridVoltage"));
/* 550:561 */     data.setSGridVoltage(rs.getDouble("sGridVoltage"));
/* 551:562 */     data.setTGridVoltage(rs.getDouble("tGridVoltage"));
/* 552:563 */     data.setRsGridVoltage(rs.getDouble("rsGridVoltage"));
/* 553:564 */     data.setRtGridVoltage(rs.getDouble("rtGridVoltage"));
/* 554:565 */     data.setStGridVoltage(rs.getDouble("stGridVoltage"));
/* 555:566 */     data.setRGridCurrent(rs.getDouble("rGridCurrent"));
/* 556:567 */     data.setSGridCurrent(rs.getDouble("sGridCurrent"));
/* 557:568 */     data.setTGridCurrent(rs.getDouble("tGridCurrent"));
/* 558:569 */     data.setRPhasePower(rs.getInt("rPhasePower"));
/* 559:570 */     data.setSPhasePower(rs.getInt("sPhasePower"));
/* 560:571 */     data.setTPhasePower(rs.getInt("tPhasePower"));
/* 561:572 */     data.setWholePower(rs.getInt("wholePower"));
/* 562:573 */     data.setRPhaseACOutputVoltage(rs.getDouble("rPhaseACOutputVoltage"));
/* 563:574 */     data.setSPhaseACOutputVoltage(rs.getDouble("sPhaseACOutputVoltage"));
/* 564:575 */     data.setTPhaseACOutputVoltage(rs.getDouble("tPhaseACOutputVoltage"));
/* 565:576 */     data.setRsPhaseACOutputVoltage(rs.getDouble("rsPhaseACOutputVoltage"));
/* 566:577 */     data.setRtPhaseACOutputVoltage(rs.getDouble("rtPhaseACOutputVoltage"));
/* 567:578 */     data.setStPhaseACOutputVoltage(rs.getDouble("stPhaseACOutputVoltage"));
/* 568:579 */     data.setRACOutputCurrent(rs.getDouble("rACOutputCurrent"));
/* 569:580 */     data.setSACOutputCurrent(rs.getDouble("sACOutputCurrent"));
/* 570:581 */     data.setTACOutputCurrent(rs.getDouble("tACOutputCurrent"));
/* 571:582 */     data.setRPhaseACOutputLoad(rs.getInt("rPhaseACOutputLoad"));
/* 572:583 */     data.setSPhaseACOutputLoad(rs.getInt("sPhaseACOutputLoad"));
/* 573:584 */     data.setTPhaseACOutputLoad(rs.getInt("tPhaseACOutputLoad"));
/* 574:585 */     data.setWholeACOutputLoad(rs.getInt("wholeACOutputLoad"));
/* 575:586 */     data.setBatteryPieceNumber(rs.getInt("batteryPieceNumber"));
/* 576:587 */     data.setBatteryTotalCapacity(rs.getInt("batteryTotalCapacity"));
/* 577:588 */     data.setBatteryRemainTime(rs.getInt("batteryRemainTime"));
/* 578:589 */     data.setAcOutputApparentPower(rs.getDouble("OUTPUTAPPARENTPOWER"));
/* 579:590 */     data.setAcOutputActivePower(rs.getDouble("OUTPUTACTIVEPOWER"));
/* 580:    */     
/* 581:    */ 
/* 582:593 */     data.setTtlChargingCurrent(rs.getDouble("TTLCHARGINGCURRENT"));
/* 583:594 */     data.setAcTtlOutputApparentPower(rs.getDouble("TTLOUTPUTAPPARENTPOWER"));
/* 584:595 */     data.setAcTtlOutputActivePower(rs.getDouble("TTLOUTPUTACTIVEPOWER"));
/* 585:596 */     data.setAcTtlOutputPercent(rs.getDouble("TTLOUTPUTPERCENT"));
/* 586:    */     
/* 587:598 */     data.setCurrentTime(rs.getTimestamp("currentTime"));
/* 588:599 */     VolUtil.setTemperature(data);
/* 589:600 */     return work;
/* 590:    */   }
/* 591:    */   
/* 592:    */   public static void main(String[] args)
/* 593:    */   {
/* 594:604 */     WorkDataDao dao = new WorkDataDao();
/* 595:605 */     WorkInfo workinfo = new WorkInfo();
/* 596:606 */     workinfo.setProdid("P15");
/* 597:607 */     workinfo.setSerialno("90000000000001");
/* 598:608 */     workinfo.setWorkMode("online");
/* 599:609 */     workinfo.setAcOutputCurrentR(214.0D);
/* 600:610 */     workinfo.setAcOutputFrequency(52.0D);
/* 601:611 */     workinfo.setAcOutputPowerR(500.0D);
/* 602:612 */     workinfo.setAcOutputVoltageR(530.0D);
/* 603:613 */     workinfo.setBatteryCapacity(30);
/* 604:614 */     workinfo.setChargingCurrent(22.0D);
/* 605:615 */     workinfo.setFaultInfo("1002");
/* 606:616 */     workinfo.setGridCurrentR(42.0D);
/* 607:617 */     workinfo.setGridFrequency(51.0D);
/* 608:618 */     workinfo.setGridPowerR(310.0D);
/* 609:619 */     workinfo.setGridVoltageR(280.0D);
/* 610:620 */     workinfo.setMaxTemperature(53.0D);
/* 611:621 */     workinfo.setPvInputPower1(172);
/* 612:622 */     workinfo.setPvInputPower2(393);
/* 613:623 */     workinfo.setPvInputPower3(121);
/* 614:624 */     workinfo.setPvInputVoltage1(320.0D);
/* 615:625 */     workinfo.setPvInputVoltage2(370.0D);
/* 616:626 */     workinfo.setPvInputVoltage3(143.0D);
/* 617:627 */     workinfo.setWholePower(2000);
/* 618:628 */     workinfo.setCurrentTime(new java.util.Date());
/* 619:629 */     boolean re = dao.insertWorkInfo(workinfo);
/* 620:630 */     System.out.println(re);
/* 621:    */   }
/* 622:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.WorkDataDao
 * JD-Core Version:    0.7.0.1
 */