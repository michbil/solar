/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.EventData;
/*   4:    */ import cn.com.voltronic.solar.data.bean.EventDataRecord;
/*   5:    */ import cn.com.voltronic.solar.data.bean.EventStatis;
/*   6:    */ import cn.com.voltronic.solar.data.bean.EventStatisItem;
/*   7:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   8:    */ import cn.com.voltronic.solar.exception.EventsHandler;
/*   9:    */ import cn.com.voltronic.solar.util.DateUtils;
/*  10:    */ import java.sql.Connection;
/*  11:    */ import java.sql.PreparedStatement;
/*  12:    */ import java.sql.ResultSet;
/*  13:    */ import java.sql.Timestamp;
/*  14:    */ import java.util.ArrayList;
/*  15:    */ import java.util.List;
/*  16:    */ 
/*  17:    */ public class EventRecordDao
/*  18:    */   extends BaseDao
/*  19:    */ {
/*  20:    */   private static final String INSERT_EVENTRECORD = "insert into event_record (prodid,serialno,eventid,trandate) values(?,?,?,?)";
/*  21:    */   private static final String QUERY_EVENTRECORD = "select * from event_record where serialno = ? and trandate BETWEEN ? and ? order by trandate desc";
/*  22:    */   private static final String QUERY_ALLDEVICE = "select DISTINCT serialno from event_record order by serialno";
/*  23:    */   private static final String DELETE_EVENTRECORD = "delete from event_record where serialno = ? and eventid = ? and trandate = ?";
/*  24:    */   private static final String DELETE_EVENTRECORD_ALL = "delete from event_record where serialno = ? and trandate BETWEEN ? and ?";
/*  25:    */   public static final String QUERY_EVENTDATA_ST = " select prodid, eventid,count(eventid) as ecount FROM event_record WHERE serialno=? and trandate BETWEEN ? AND ? group by eventid, prodid";
/*  26:    */   
/*  27:    */   public boolean insertEvent(EventDataRecord data)
/*  28:    */   {
/*  29: 43 */     java.util.Date reDate = DateUtils.getDateParseDate(data.getTrandate());
/*  30: 44 */     if (reDate == null) {
/*  31: 45 */       return false;
/*  32:    */     }
/*  33: 47 */     boolean result = false;
/*  34: 48 */     Connection conn = DBManager.getConnection();
/*  35: 49 */     PreparedStatement ps = null;
/*  36:    */     try
/*  37:    */     {
/*  38: 51 */       ps = conn.prepareStatement("insert into event_record (prodid,serialno,eventid,trandate) values(?,?,?,?)");
/*  39: 52 */       ps.setString(1, data.getProdId());
/*  40: 53 */       ps.setString(2, data.getSerialNo());
/*  41: 54 */       ps.setString(3, data.getEventId());
/*  42: 55 */       ps.setTimestamp(4, new Timestamp(reDate.getTime()));
/*  43: 56 */       if (ps.executeUpdate() > 0) {
/*  44: 57 */         result = true;
/*  45:    */       }
/*  46: 59 */       conn.commit();
/*  47:    */     }
/*  48:    */     catch (Exception e)
/*  49:    */     {
/*  50: 61 */       result = false;
/*  51:    */     }
/*  52:    */     finally
/*  53:    */     {
/*  54: 63 */       close(ps);
/*  55:    */     }
/*  56: 65 */     return result;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public List<EventStatisItem> queryEventStatistics(String serialno, java.util.Date begin, java.util.Date end, boolean includeInfo)
/*  60:    */   {
/*  61: 78 */     List<EventStatis> lists = new ArrayList();
/*  62: 79 */     Connection conn = DBManager.getConnection();
/*  63: 80 */     PreparedStatement ps = null;
/*  64: 81 */     ResultSet rs = null;
/*  65: 82 */     if (conn != null) {
/*  66:    */       try
/*  67:    */       {
/*  68: 84 */         ps = conn.prepareStatement(" select prodid, eventid,count(eventid) as ecount FROM event_record WHERE serialno=? and trandate BETWEEN ? AND ? group by eventid, prodid");
/*  69: 85 */         ps.setString(1, serialno);
/*  70: 86 */         ps.setDate(2, new java.sql.Date(begin.getTime()));
/*  71: 87 */         ps.setDate(3, new java.sql.Date(end.getTime() + 86400000L));
/*  72: 88 */         rs = ps.executeQuery();
/*  73: 89 */         while (rs.next())
/*  74:    */         {
/*  75: 90 */           EventStatis data = new EventStatis();
/*  76: 91 */           data.setProdid(rs.getString("prodid"));
/*  77: 92 */           data.setEventId(rs.getString("eventid"));
/*  78: 93 */           data.setCount(rs.getInt("ecount"));
/*  79: 94 */           lists.add(data);
/*  80:    */         }
/*  81:    */       }
/*  82:    */       catch (Exception e)
/*  83:    */       {
/*  84: 97 */         e.printStackTrace();
/*  85:    */       }
/*  86:    */       finally
/*  87:    */       {
/*  88: 99 */         close(rs, ps);
/*  89:    */       }
/*  90:    */     }
/*  91:102 */     List<EventStatisItem> eventStatisItem = new ArrayList();
/*  92:103 */     for (int i = 0; i < lists.size(); i++)
/*  93:    */     {
/*  94:104 */       String eventid = ((EventStatis)lists.get(i)).getEventId();
/*  95:105 */       int level = EventsHandler.getEventLevel(EventsHandler.getDocumentByProdid(((EventStatis)lists.get(i)).getProdid()), eventid);
/*  96:106 */       String name = EventsHandler.getEventname(EventsHandler.getDocumentByProdid(((EventStatis)lists.get(i)).getProdid()), eventid);
/*  97:107 */       EventStatisItem items = new EventStatisItem();
/*  98:108 */       items.setEventId(eventid);
/*  99:109 */       items.setCount(((EventStatis)lists.get(i)).getCount());
/* 100:110 */       items.setLevel(level);
/* 101:111 */       items.setName(name);
/* 102:113 */       if (includeInfo) {
/* 103:114 */         eventStatisItem.add(items);
/* 104:116 */       } else if (items.getLevel() != 3) {
/* 105:117 */         eventStatisItem.add(items);
/* 106:    */       }
/* 107:    */     }
/* 108:121 */     return eventStatisItem;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public boolean alreadyInsert(EventDataRecord data)
/* 112:    */   {
/* 113:128 */     boolean result = false;
/* 114:129 */     Connection conn = DBManager.getConnection();
/* 115:130 */     PreparedStatement ps = null;
/* 116:131 */     ResultSet rs = null;
/* 117:    */     try
/* 118:    */     {
/* 119:133 */       ps = 
/* 120:134 */         conn.prepareStatement("select * from event_record where serialno=? and trandate=? and eventid=?");
/* 121:135 */       ps.setString(1, data.getSerialNo());
/* 122:136 */       ps.setTimestamp(2, new Timestamp(data.getTrandate().getTime()));
/* 123:137 */       ps.setString(3, data.getEventId());
/* 124:138 */       rs = ps.executeQuery();
/* 125:139 */       while (rs.next()) {
/* 126:140 */         result = true;
/* 127:    */       }
/* 128:142 */       conn.commit();
/* 129:    */     }
/* 130:    */     catch (Exception e)
/* 131:    */     {
/* 132:144 */       e.printStackTrace();
/* 133:    */     }
/* 134:    */     finally
/* 135:    */     {
/* 136:146 */       close(rs, ps);
/* 137:    */     }
/* 138:148 */     return result;
/* 139:    */   }
/* 140:    */   
/* 141:    */   public List<EventDataRecord> queryEvent(String serialno, java.util.Date fromDate, java.util.Date toDate, boolean includeInfo)
/* 142:    */   {
/* 143:161 */     Connection conn = DBManager.getConnection();
/* 144:162 */     PreparedStatement ps = null;
/* 145:163 */     EventDataRecord event = null;
/* 146:164 */     ResultSet rs = null;
/* 147:165 */     List<EventDataRecord> list = new ArrayList();
/* 148:    */     try
/* 149:    */     {
/* 150:167 */       ps = conn.prepareStatement("select * from event_record where serialno = ? and trandate BETWEEN ? and ? order by trandate desc");
/* 151:168 */       ps.setString(1, serialno);
/* 152:169 */       ps.setDate(2, new java.sql.Date(fromDate.getTime()));
/* 153:170 */       ps.setDate(3, new java.sql.Date(toDate.getTime() + 86400000L));
/* 154:171 */       rs = ps.executeQuery();
/* 155:172 */       conn.commit();
/* 156:173 */       while (rs.next())
/* 157:    */       {
/* 158:174 */         event = new EventDataRecord();
/* 159:175 */         String prodid = rs.getString("prodid");
/* 160:176 */         event.setProdId(prodid);
/* 161:177 */         event.setSerialNo(rs.getString("serialno"));
/* 162:178 */         String eventId = rs.getString("eventid");
/* 163:179 */         event.setEventId(eventId);
/* 164:180 */         event.setTrandate(rs.getTimestamp("trandate"));
/* 165:181 */         EventData eventData = EventsHandler.getEventById(prodid, 
/* 166:182 */           eventId);
/* 167:183 */         int level = eventData.getEventLevel();
/* 168:184 */         if (includeInfo) {
/* 169:185 */           list.add(event);
/* 170:187 */         } else if (level != 3) {
/* 171:188 */           list.add(event);
/* 172:    */         }
/* 173:    */       }
/* 174:    */     }
/* 175:    */     catch (Exception ex)
/* 176:    */     {
/* 177:193 */       ex.printStackTrace();
/* 178:    */     }
/* 179:    */     finally
/* 180:    */     {
/* 181:195 */       close(rs, ps);
/* 182:    */     }
/* 183:197 */     return list;
/* 184:    */   }
/* 185:    */   
/* 186:    */   public List<String> queryDevices()
/* 187:    */   {
/* 188:206 */     Connection conn = DBManager.getConnection();
/* 189:207 */     PreparedStatement ps = null;
/* 190:208 */     List<String> list = new ArrayList();
/* 191:209 */     ResultSet rs = null;
/* 192:    */     try
/* 193:    */     {
/* 194:211 */       ps = conn.prepareStatement("select DISTINCT serialno from event_record order by serialno");
/* 195:212 */       rs = ps.executeQuery();
/* 196:213 */       conn.commit();
/* 197:214 */       while (rs.next()) {
/* 198:215 */         list.add(rs.getString("serialno"));
/* 199:    */       }
/* 200:    */     }
/* 201:    */     catch (Exception ex)
/* 202:    */     {
/* 203:218 */       ex.printStackTrace();
/* 204:    */     }
/* 205:    */     finally
/* 206:    */     {
/* 207:220 */       close(rs, ps);
/* 208:    */     }
/* 209:222 */     return list;
/* 210:    */   }
/* 211:    */   
/* 212:    */   public boolean deleteEventRecord(String serialno, String eventid, java.util.Date deldate)
/* 213:    */   {
/* 214:231 */     boolean result = false;
/* 215:232 */     Connection conn = DBManager.getConnection();
/* 216:233 */     PreparedStatement ps = null;
/* 217:    */     try
/* 218:    */     {
/* 219:235 */       ps = conn.prepareStatement("delete from event_record where serialno = ? and eventid = ? and trandate = ?");
/* 220:236 */       ps.setString(1, serialno);
/* 221:237 */       ps.setString(2, eventid);
/* 222:238 */       ps.setTimestamp(3, new Timestamp(deldate.getTime()));
/* 223:239 */       if (ps.executeUpdate() > 0) {
/* 224:240 */         result = true;
/* 225:    */       }
/* 226:242 */       conn.commit();
/* 227:    */     }
/* 228:    */     catch (Exception ex)
/* 229:    */     {
/* 230:244 */       result = false;
/* 231:    */     }
/* 232:    */     finally
/* 233:    */     {
/* 234:246 */       close(ps);
/* 235:    */     }
/* 236:248 */     return result;
/* 237:    */   }
/* 238:    */   
/* 239:    */   public boolean deleteEventRecordAll(String serialno, java.util.Date fromDate, java.util.Date toDate)
/* 240:    */   {
/* 241:257 */     boolean result = false;
/* 242:258 */     Connection conn = DBManager.getConnection();
/* 243:259 */     PreparedStatement ps = null;
/* 244:    */     try
/* 245:    */     {
/* 246:261 */       ps = conn.prepareStatement("delete from event_record where serialno = ? and trandate BETWEEN ? and ?");
/* 247:262 */       ps.setString(1, serialno);
/* 248:263 */       ps.setDate(2, new java.sql.Date(fromDate.getTime()));
/* 249:264 */       ps.setDate(3, new java.sql.Date(toDate.getTime() + 86400000L));
/* 250:265 */       if (ps.executeUpdate() > 0) {
/* 251:266 */         result = true;
/* 252:    */       }
/* 253:268 */       conn.commit();
/* 254:    */     }
/* 255:    */     catch (Exception ex)
/* 256:    */     {
/* 257:270 */       result = false;
/* 258:    */     }
/* 259:    */     finally
/* 260:    */     {
/* 261:272 */       close(ps);
/* 262:    */     }
/* 263:274 */     return result;
/* 264:    */   }
/* 265:    */   
/* 266:    */   public boolean deleteAll()
/* 267:    */   {
/* 268:278 */     boolean result = false;
/* 269:279 */     Connection conn = DBManager.getConnection();
/* 270:280 */     PreparedStatement ps = null;
/* 271:    */     try
/* 272:    */     {
/* 273:282 */       ps = conn.prepareStatement("delete from event_record;");
/* 274:283 */       if (ps.executeUpdate() > 0) {
/* 275:284 */         result = true;
/* 276:    */       }
/* 277:286 */       conn.commit();
/* 278:    */     }
/* 279:    */     catch (Exception ex)
/* 280:    */     {
/* 281:288 */       result = false;
/* 282:    */     }
/* 283:    */     finally
/* 284:    */     {
/* 285:290 */       close(ps);
/* 286:    */     }
/* 287:292 */     return result;
/* 288:    */   }
/* 289:    */   
/* 290:    */   public static void main(String[] args)
/* 291:    */   {
/* 292:296 */     EventRecordDao dao = new EventRecordDao();
/* 293:297 */     EventDataRecord data = new EventDataRecord();
/* 294:298 */     data.setEventId("1004");
/* 295:299 */     data.setSerialNo("90000000000000");
/* 296:300 */     data.setTrandate(new java.util.Date());
/* 297:301 */     dao.insertEvent(data);
/* 298:    */   }
/* 299:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.EventRecordDao
 * JD-Core Version:    0.7.0.1
 */