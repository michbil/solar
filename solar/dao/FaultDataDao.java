/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.DataBeforeFault;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import cn.com.voltronic.solar.exception.EventsHandler;
/*   6:    */ import cn.com.voltronic.solar.util.DateUtils;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.sql.Connection;
/*   9:    */ import java.sql.PreparedStatement;
/*  10:    */ import java.sql.ResultSet;
/*  11:    */ import java.sql.Timestamp;
/*  12:    */ import java.util.ArrayList;
/*  13:    */ import java.util.Calendar;
/*  14:    */ import java.util.List;
/*  15:    */ 
/*  16:    */ public class FaultDataDao
/*  17:    */   extends BaseDao
/*  18:    */ {
/*  19: 20 */   private final String DELETE_FAULTDATA = "delete from faultdata where serialno = ? and trandate = ?";
/*  20: 21 */   private final String DELETE_FAULTDATA_ALL = "delete from faultdata where serialno = ? and trandate BETWEEN ? and ?";
/*  21:    */   
/*  22:    */   public List<String> getAllProdid()
/*  23:    */   {
/*  24: 24 */     List<String> result = new ArrayList();
/*  25: 25 */     Connection conn = DBManager.getConnection();
/*  26: 26 */     PreparedStatement ps = null;
/*  27: 27 */     ResultSet rs = null;
/*  28:    */     try
/*  29:    */     {
/*  30: 29 */       ps = conn.prepareStatement("SELECT distinct prodid FROM faultdata");
/*  31: 30 */       rs = ps.executeQuery();
/*  32: 31 */       while (rs.next()) {
/*  33: 32 */         result.add(rs.getString("prodid"));
/*  34:    */       }
/*  35: 34 */       conn.commit();
/*  36:    */     }
/*  37:    */     catch (Exception e)
/*  38:    */     {
/*  39: 36 */       e.printStackTrace();
/*  40:    */     }
/*  41:    */     finally
/*  42:    */     {
/*  43: 38 */       close(rs, ps);
/*  44:    */     }
/*  45: 40 */     return result;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public String getProdidBySerialno(String serialno)
/*  49:    */   {
/*  50: 44 */     String result = "";
/*  51: 45 */     Connection conn = DBManager.getConnection();
/*  52: 46 */     PreparedStatement ps = null;
/*  53: 47 */     ResultSet rs = null;
/*  54:    */     try
/*  55:    */     {
/*  56: 49 */       ps = 
/*  57: 50 */         conn.prepareStatement("SELECT distinct prodid FROM faultdata where serialno=?");
/*  58: 51 */       ps.setString(1, serialno);
/*  59: 52 */       rs = ps.executeQuery();
/*  60: 53 */       while (rs.next()) {
/*  61: 54 */         result = rs.getString("prodid");
/*  62:    */       }
/*  63: 56 */       conn.commit();
/*  64:    */     }
/*  65:    */     catch (Exception e)
/*  66:    */     {
/*  67: 58 */       e.printStackTrace();
/*  68:    */     }
/*  69:    */     finally
/*  70:    */     {
/*  71: 60 */       close(rs, ps);
/*  72:    */     }
/*  73: 62 */     return result;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public List<String> getSerialNo()
/*  77:    */   {
/*  78: 66 */     List<String> result = new ArrayList();
/*  79: 67 */     Connection conn = DBManager.getConnection();
/*  80: 68 */     PreparedStatement ps = null;
/*  81: 69 */     ResultSet rs = null;
/*  82:    */     try
/*  83:    */     {
/*  84: 71 */       ps = 
/*  85: 72 */         conn.prepareStatement("SELECT distinct serialno FROM faultdata order by serialno");
/*  86: 73 */       rs = ps.executeQuery();
/*  87: 74 */       while (rs.next()) {
/*  88: 75 */         result.add(rs.getString("serialno"));
/*  89:    */       }
/*  90: 77 */       conn.commit();
/*  91:    */     }
/*  92:    */     catch (Exception e)
/*  93:    */     {
/*  94: 79 */       e.printStackTrace();
/*  95:    */     }
/*  96:    */     finally
/*  97:    */     {
/*  98: 81 */       close(rs, ps);
/*  99:    */     }
/* 100: 83 */     return result;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean alreadyInsert(DataBeforeFault data)
/* 104:    */   {
/* 105: 87 */     boolean result = false;
/* 106: 88 */     Connection conn = DBManager.getConnection();
/* 107: 89 */     PreparedStatement ps = null;
/* 108: 90 */     ResultSet rs = null;
/* 109:    */     try
/* 110:    */     {
/* 111: 92 */       ps = 
/* 112: 93 */         conn.prepareStatement("select * from faultdata where serialno=? and trandate=? and faultString=?");
/* 113: 94 */       ps.setString(1, data.getSerialno());
/* 114: 95 */       ps.setTimestamp(2, new Timestamp(data.getTrandate().getTime()));
/* 115: 96 */       ps.setString(3, data.getFaultString());
/* 116: 97 */       rs = ps.executeQuery();
/* 117: 98 */       while (rs.next()) {
/* 118: 99 */         result = true;
/* 119:    */       }
/* 120:101 */       conn.commit();
/* 121:    */     }
/* 122:    */     catch (Exception e)
/* 123:    */     {
/* 124:103 */       e.printStackTrace();
/* 125:    */     }
/* 126:    */     finally
/* 127:    */     {
/* 128:105 */       close(rs, ps);
/* 129:    */     }
/* 130:107 */     return result;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public boolean insertData(DataBeforeFault faultdata)
/* 134:    */   {
/* 135:111 */     boolean result = true;
/* 136:112 */     Connection conn = DBManager.getConnection();
/* 137:113 */     PreparedStatement ps = null;
/* 138:    */     try
/* 139:    */     {
/* 140:115 */       ps = 
/* 141:116 */         conn.prepareStatement(" INSERT INTO faultdata (    prodid,serialno,faultString,trandate,pvinputvoltage1,pvinputvoltage2,pvinputvoltage3,   pvinputcurrent1,pvinputcurrent2,pvinputcurrent3,inverterVoltage,inverterCurrent,   gridVoltage,gridCurrent,gridFrequency,outputLoadPercent,outputLoadCurrent,outputLoadVoltage,   outputLoadFrequency,batteryVoltage,maxTemperature,runStatus) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
/* 142:    */       
/* 143:    */ 
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:123 */       ps.setString(1, faultdata.getProdid());
/* 149:124 */       ps.setString(2, faultdata.getSerialno());
/* 150:125 */       ps.setString(3, faultdata.getFaultString());
/* 151:126 */       ps.setTimestamp(4, new Timestamp(
/* 152:127 */         faultdata.getTrandate().getTime()));
/* 153:128 */       ps.setDouble(5, faultdata.getPvinputvoltage1());
/* 154:129 */       ps.setDouble(6, faultdata.getPvinputvoltage2());
/* 155:130 */       ps.setDouble(7, faultdata.getPvinputvoltage3());
/* 156:131 */       ps.setDouble(8, faultdata.getPvinputcurrent1());
/* 157:132 */       ps.setDouble(9, faultdata.getPvinputcurrent2());
/* 158:133 */       ps.setDouble(10, faultdata.getPvinputcurrent3());
/* 159:134 */       ps.setDouble(11, faultdata.getInverterVoltage());
/* 160:135 */       ps.setDouble(12, faultdata.getInverterCurrent());
/* 161:136 */       ps.setDouble(13, faultdata.getGridVoltage());
/* 162:137 */       ps.setDouble(14, faultdata.getGridCurrent());
/* 163:138 */       ps.setDouble(15, faultdata.getGridFrequency());
/* 164:139 */       ps.setInt(16, faultdata.getOutputLoadPercent());
/* 165:140 */       ps.setDouble(17, faultdata.getOutputLoadCurrent());
/* 166:141 */       ps.setDouble(18, faultdata.getOutputLoadVoltage());
/* 167:142 */       ps.setDouble(19, faultdata.getOutputLoadFrequency());
/* 168:143 */       ps.setDouble(20, faultdata.getBatteryVoltage());
/* 169:144 */       ps.setDouble(21, faultdata.getMaxTemperature());
/* 170:145 */       ps.setString(22, faultdata.getRunStatus());
/* 171:146 */       ps.executeUpdate();
/* 172:147 */       conn.commit();
/* 173:    */     }
/* 174:    */     catch (Exception e)
/* 175:    */     {
/* 176:149 */       e.printStackTrace();
/* 177:150 */       result = false;
/* 178:    */     }
/* 179:    */     finally
/* 180:    */     {
/* 181:152 */       close(ps);
/* 182:    */     }
/* 183:154 */     return result;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public List<DataBeforeFault> queryData(String serialno, java.util.Date beginDate, java.util.Date endDate)
/* 187:    */   {
/* 188:159 */     String prodid = "P30";
/* 189:160 */     prodid = getProdidBySerialno(serialno);
/* 190:161 */     Calendar end = Calendar.getInstance();
/* 191:162 */     end.setTime(endDate);
/* 192:163 */     Calendar start = Calendar.getInstance();
/* 193:164 */     start.setTime(beginDate);
/* 194:165 */     List<DataBeforeFault> resultList = new ArrayList();
/* 195:166 */     Connection conn = DBManager.getConnection();
/* 196:167 */     PreparedStatement ps = null;
/* 197:168 */     ResultSet rs = null;
/* 198:    */     try
/* 199:    */     {
/* 200:170 */       ps = 
/* 201:171 */         conn.prepareStatement("select * from faultdata where serialno=? and trandate between ? and ? order by trandate ");
/* 202:172 */       ps.setString(1, serialno);
/* 203:173 */       ps.setTimestamp(2, new Timestamp(DateUtils.getStartofDayFullTime(
/* 204:174 */         start).getTime().getTime()));
/* 205:175 */       ps.setTimestamp(3, new Timestamp(
/* 206:176 */         DateUtils.getEndofDayFullTime(end).getTime().getTime()));
/* 207:177 */       rs = ps.executeQuery();
/* 208:178 */       conn.commit();
/* 209:179 */       while (rs.next())
/* 210:    */       {
/* 211:180 */         DataBeforeFault data = new DataBeforeFault();
/* 212:181 */         data.setSerialno(serialno);
/* 213:182 */         data.setTrandate(rs.getTimestamp("trandate"));
/* 214:183 */         String faultString = "";
/* 215:184 */         if (prodid.equals("P30")) {
/* 216:185 */           faultString = EventsHandler.getEventname(EventsHandler.getDocument30(), rs.getString("faultString"));
/* 217:    */         } else {
/* 218:187 */           faultString = EventsHandler.getEventname(EventsHandler.getDocument30(), rs.getString("faultString"));
/* 219:    */         }
/* 220:189 */         data.setFaultString(faultString);
/* 221:190 */         data.setPvinputvoltage1(rs.getDouble("pvinputvoltage1"));
/* 222:191 */         data.setPvinputvoltage2(rs.getDouble("pvinputvoltage2"));
/* 223:192 */         data.setPvinputvoltage3(rs.getDouble("pvinputvoltage3"));
/* 224:193 */         data.setPvinputcurrent1(rs.getDouble("pvinputcurrent1"));
/* 225:194 */         data.setPvinputcurrent2(rs.getDouble("pvinputcurrent2"));
/* 226:195 */         data.setPvinputcurrent3(rs.getDouble("pvinputcurrent3"));
/* 227:196 */         data.setInverterVoltage(rs.getDouble("inverterVoltage"));
/* 228:197 */         data.setInverterCurrent(rs.getDouble("inverterCurrent"));
/* 229:198 */         data.setGridCurrent(rs.getDouble("gridCurrent"));
/* 230:199 */         data.setGridVoltage(rs.getDouble("gridVoltage"));
/* 231:200 */         data.setGridFrequency(rs.getDouble("gridFrequency"));
/* 232:201 */         data.setOutputLoadPercent(rs.getInt("outputLoadPercent"));
/* 233:202 */         data.setOutputLoadCurrent(rs.getDouble("outputLoadCurrent"));
/* 234:203 */         data.setOutputLoadVoltage(rs.getDouble("outputLoadVoltage"));
/* 235:204 */         data
/* 236:205 */           .setOutputLoadFrequency(rs
/* 237:206 */           .getDouble("outputLoadFrequency"));
/* 238:207 */         data.setBatteryVoltage(rs.getDouble("batteryVoltage"));
/* 239:208 */         data.setMaxTemperature(rs.getDouble("maxTemperature"));
/* 240:209 */         data.setRunStatus(rs.getString("runStatus"));
/* 241:210 */         resultList.add(data);
/* 242:    */       }
/* 243:    */     }
/* 244:    */     catch (Exception e)
/* 245:    */     {
/* 246:213 */       e.printStackTrace();
/* 247:    */     }
/* 248:    */     finally
/* 249:    */     {
/* 250:215 */       close(rs, ps);
/* 251:    */     }
/* 252:217 */     return resultList;
/* 253:    */   }
/* 254:    */   
/* 255:    */   public boolean removeData(String serialno, java.util.Date date)
/* 256:    */   {
/* 257:221 */     boolean result = false;
/* 258:222 */     Connection conn = DBManager.getConnection();
/* 259:223 */     PreparedStatement ps = null;
/* 260:    */     try
/* 261:    */     {
/* 262:225 */       ps = conn.prepareStatement("delete from faultdata where serialno = ? and trandate = ?");
/* 263:226 */       ps.setString(1, serialno);
/* 264:227 */       ps.setTimestamp(2, new Timestamp(date.getTime()));
/* 265:228 */       if (ps.executeUpdate() > 0) {
/* 266:229 */         result = true;
/* 267:    */       }
/* 268:    */     }
/* 269:    */     catch (Exception e)
/* 270:    */     {
/* 271:232 */       result = false;
/* 272:    */     }
/* 273:    */     finally
/* 274:    */     {
/* 275:234 */       close(ps);
/* 276:    */     }
/* 277:236 */     return result;
/* 278:    */   }
/* 279:    */   
/* 280:    */   public boolean removeDataAll(String serialno, java.util.Date fromDate, java.util.Date toDate)
/* 281:    */   {
/* 282:240 */     boolean result = false;
/* 283:241 */     Connection conn = DBManager.getConnection();
/* 284:242 */     PreparedStatement ps = null;
/* 285:    */     try
/* 286:    */     {
/* 287:244 */       ps = conn.prepareStatement("delete from faultdata where serialno = ? and trandate BETWEEN ? and ?");
/* 288:245 */       ps.setString(1, serialno);
/* 289:246 */       ps.setDate(2, new java.sql.Date(fromDate.getTime()));
/* 290:247 */       ps.setDate(3, new java.sql.Date(toDate.getTime() + 86400000L));
/* 291:248 */       if (ps.executeUpdate() > 0) {
/* 292:249 */         result = true;
/* 293:    */       }
/* 294:    */     }
/* 295:    */     catch (Exception e)
/* 296:    */     {
/* 297:252 */       result = false;
/* 298:    */     }
/* 299:    */     finally
/* 300:    */     {
/* 301:254 */       close(ps);
/* 302:    */     }
/* 303:256 */     return result;
/* 304:    */   }
/* 305:    */   
/* 306:    */   public boolean removeAll()
/* 307:    */   {
/* 308:260 */     boolean result = true;
/* 309:261 */     Connection conn = DBManager.getConnection();
/* 310:262 */     PreparedStatement ps = null;
/* 311:    */     try
/* 312:    */     {
/* 313:264 */       ps = conn.prepareStatement("delete from faultdata");
/* 314:265 */       ps.executeUpdate();
/* 315:266 */       conn.commit();
/* 316:    */     }
/* 317:    */     catch (Exception e)
/* 318:    */     {
/* 319:268 */       result = false;
/* 320:    */     }
/* 321:    */     finally
/* 322:    */     {
/* 323:270 */       close(ps);
/* 324:    */     }
/* 325:272 */     return result;
/* 326:    */   }
/* 327:    */   
/* 328:    */   public static void main(String[] args)
/* 329:    */   {
/* 330:276 */     FaultDataDao dao = new FaultDataDao();
/* 331:277 */     DataBeforeFault faultdata = new DataBeforeFault();
/* 332:278 */     faultdata.setProdid("P16");
/* 333:279 */     faultdata.setSerialno("90000000000002");
/* 334:280 */     faultdata.setFaultString("1002");
/* 335:281 */     faultdata.setGridVoltage(222.0D);
/* 336:282 */     faultdata.setGridCurrent(2.0D);
/* 337:283 */     faultdata.setGridFrequency(123.0D);
/* 338:284 */     faultdata.setMaxTemperature(32.0D);
/* 339:285 */     faultdata.setPvinputcurrent1(15.0D);
/* 340:286 */     faultdata.setPvinputvoltage1(12.0D);
/* 341:287 */     faultdata.setTrandate(new java.util.Date());
/* 342:288 */     faultdata.setPvinputcurrent2(231.0D);
/* 343:289 */     faultdata.setPvinputvoltage2(43.0D);
/* 344:290 */     faultdata.setBatteryVoltage(555.0D);
/* 345:291 */     boolean re = dao.insertData(faultdata);
/* 346:292 */     System.out.println(re);
/* 347:    */   }
/* 348:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.FaultDataDao
 * JD-Core Version:    0.7.0.1
 */