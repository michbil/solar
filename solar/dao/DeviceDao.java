/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.DeviceBean;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import java.sql.Connection;
/*   6:    */ import java.sql.PreparedStatement;
/*   7:    */ import java.sql.ResultSet;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.List;
/*  10:    */ 
/*  11:    */ public class DeviceDao
/*  12:    */   extends BaseDao
/*  13:    */ {
/*  14:    */   private static final String INSERT_SERIALNO = "INSERT INTO SERIALNO(prodid,serialno,mpptnumber,modetype,parallel) VALUES(?,?,?,?,?)";
/*  15:    */   private static final String SELECT_SERIALNO = "select * from serialno where prodid=? and serialno=? order by serialno";
/*  16:    */   private static final String QUERYDATA_PRODID = " SELECT distinct prodid FROM serialno order by prodid";
/*  17:    */   private static final String QUERYDATA_SERIALNO = " SELECT distinct serialno FROM SERIALNO where prodid=?  order by serialno";
/*  18:    */   private static final String UPDATE_SERIALNO = " update serialno set mpptnumber=?,modetype=?,parallel=? where prodid=? and serialno=? ";
/*  19:    */   private static final String DELETE = "delete from serialno";
/*  20:    */   
/*  21:    */   public boolean InsertOrUpdateDevice(DeviceBean bean)
/*  22:    */   {
/*  23: 37 */     boolean result = false;
/*  24: 38 */     boolean update = false;
/*  25: 39 */     Connection conn = DBManager.getConnection();
/*  26: 40 */     PreparedStatement ps = null;
/*  27: 41 */     ResultSet rs = null;
/*  28:    */     try
/*  29:    */     {
/*  30: 43 */       ps = conn.prepareStatement("select serialno from serialno where prodid=? and serialno=? ");
/*  31: 44 */       ps.setString(1, bean.getProdid());
/*  32: 45 */       ps.setString(2, bean.getSerialno());
/*  33: 46 */       rs = ps.executeQuery();
/*  34: 47 */       if (rs.next()) {
/*  35: 48 */         update = true;
/*  36:    */       }
/*  37: 50 */       close(rs, ps);
/*  38: 51 */       rs = null;
/*  39: 53 */       if (update)
/*  40:    */       {
/*  41: 54 */         ps = conn.prepareStatement(" update serialno set mpptnumber=?,modetype=?,parallel=? where prodid=? and serialno=? ");
/*  42: 55 */         ps.setInt(1, bean.getMpptnumber());
/*  43: 56 */         ps.setString(2, bean.getModetype());
/*  44: 57 */         ps.setInt(3, bean.getParallel());
/*  45: 58 */         ps.setString(4, bean.getProdid());
/*  46: 59 */         ps.setString(5, bean.getSerialno());
/*  47:    */       }
/*  48:    */       else
/*  49:    */       {
/*  50: 62 */         ps = conn.prepareStatement("INSERT INTO SERIALNO(prodid,serialno,mpptnumber,modetype,parallel) VALUES(?,?,?,?,?)");
/*  51: 63 */         ps.setString(1, bean.getProdid());
/*  52: 64 */         ps.setString(2, bean.getSerialno());
/*  53: 65 */         ps.setInt(3, bean.getMpptnumber());
/*  54: 66 */         ps.setString(4, bean.getModetype());
/*  55: 67 */         ps.setInt(5, bean.getParallel());
/*  56:    */       }
/*  57: 70 */       if (ps.executeUpdate() > 0) {
/*  58: 71 */         result = true;
/*  59:    */       }
/*  60:    */     }
/*  61:    */     catch (Exception e)
/*  62:    */     {
/*  63: 74 */       e.printStackTrace();
/*  64:    */     }
/*  65:    */     finally
/*  66:    */     {
/*  67: 76 */       close(rs, ps);
/*  68:    */     }
/*  69: 78 */     return result;
/*  70:    */   }
/*  71:    */   
/*  72:    */   public List<String> queryProdidAll()
/*  73:    */   {
/*  74: 89 */     List<String> result = new ArrayList();
/*  75: 90 */     Connection conn = DBManager.getConnection();
/*  76: 91 */     PreparedStatement ps = null;
/*  77: 92 */     ResultSet rs = null;
/*  78: 93 */     if (conn != null) {
/*  79:    */       try
/*  80:    */       {
/*  81: 95 */         ps = conn.prepareStatement(" SELECT distinct prodid FROM serialno order by prodid");
/*  82: 96 */         rs = ps.executeQuery();
/*  83: 97 */         while (rs.next()) {
/*  84: 98 */           result.add(rs.getString("prodid"));
/*  85:    */         }
/*  86:    */       }
/*  87:    */       catch (Exception e)
/*  88:    */       {
/*  89:101 */         e.printStackTrace();
/*  90:    */       }
/*  91:    */       finally
/*  92:    */       {
/*  93:103 */         close(rs, ps);
/*  94:    */       }
/*  95:    */     }
/*  96:106 */     return result;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public List<String> querySerialnoAll(String pid)
/* 100:    */   {
/* 101:116 */     List<String> result = new ArrayList();
/* 102:117 */     Connection conn = DBManager.getConnection();
/* 103:118 */     PreparedStatement ps = null;
/* 104:119 */     ResultSet rs = null;
/* 105:120 */     if (conn != null) {
/* 106:    */       try
/* 107:    */       {
/* 108:122 */         ps = conn.prepareStatement(" SELECT distinct serialno FROM SERIALNO where prodid=?  order by serialno");
/* 109:123 */         ps.setString(1, pid);
/* 110:124 */         rs = ps.executeQuery();
/* 111:125 */         while (rs.next()) {
/* 112:126 */           result.add(rs.getString("serialno"));
/* 113:    */         }
/* 114:    */       }
/* 115:    */       catch (Exception e)
/* 116:    */       {
/* 117:129 */         e.printStackTrace();
/* 118:    */       }
/* 119:    */       finally
/* 120:    */       {
/* 121:131 */         close(rs, ps);
/* 122:    */       }
/* 123:    */     }
/* 124:134 */     return result;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public DeviceBean queryDevicebySerialno(String pid, String serialno)
/* 128:    */   {
/* 129:144 */     DeviceBean bean = null;
/* 130:145 */     Connection conn = DBManager.getConnection();
/* 131:146 */     PreparedStatement ps = null;
/* 132:147 */     ResultSet rs = null;
/* 133:148 */     if (conn != null) {
/* 134:    */       try
/* 135:    */       {
/* 136:150 */         ps = conn.prepareStatement("select * from serialno where prodid=? and serialno=? order by serialno");
/* 137:151 */         ps.setString(1, pid);
/* 138:152 */         ps.setString(2, serialno);
/* 139:153 */         rs = ps.executeQuery();
/* 140:154 */         while (rs.next())
/* 141:    */         {
/* 142:155 */           bean = new DeviceBean();
/* 143:156 */           bean.setProdid(rs.getString("prodid"));
/* 144:157 */           bean.setSerialno(rs.getString("serialno"));
/* 145:158 */           bean.setMpptnumber(rs.getInt("mpptnumber"));
/* 146:159 */           bean.setModetype(rs.getString("modetype"));
/* 147:160 */           bean.setParallel(rs.getInt("parallel"));
/* 148:    */         }
/* 149:    */       }
/* 150:    */       catch (Exception e)
/* 151:    */       {
/* 152:163 */         e.printStackTrace();
/* 153:    */       }
/* 154:    */       finally
/* 155:    */       {
/* 156:165 */         close(rs, ps);
/* 157:    */       }
/* 158:    */     }
/* 159:171 */     return bean;
/* 160:    */   }
/* 161:    */   
/* 162:    */   public boolean updateDevice(DeviceBean bean)
/* 163:    */   {
/* 164:175 */     boolean result = false;
/* 165:176 */     Connection conn = DBManager.getConnection();
/* 166:177 */     PreparedStatement ps = null;
/* 167:    */     try
/* 168:    */     {
/* 169:179 */       ps = conn.prepareStatement(" update serialno set mpptnumber=?,modetype=?,parallel=? where prodid=? and serialno=? ");
/* 170:180 */       ps.setInt(1, bean.getMpptnumber());
/* 171:181 */       ps.setString(2, bean.getModetype());
/* 172:182 */       ps.setInt(3, bean.getParallel());
/* 173:183 */       ps.setString(4, bean.getProdid());
/* 174:184 */       ps.setString(5, bean.getSerialno());
/* 175:185 */       if (ps.executeUpdate() > 0) {
/* 176:186 */         result = true;
/* 177:    */       }
/* 178:    */     }
/* 179:    */     catch (Exception e)
/* 180:    */     {
/* 181:189 */       e.printStackTrace();
/* 182:    */     }
/* 183:    */     finally
/* 184:    */     {
/* 185:191 */       close(ps);
/* 186:    */     }
/* 187:193 */     return result;
/* 188:    */   }
/* 189:    */   
/* 190:    */   public boolean deleteAll()
/* 191:    */   {
/* 192:197 */     boolean result = false;
/* 193:198 */     Connection conn = DBManager.getConnection();
/* 194:199 */     PreparedStatement ps = null;
/* 195:    */     try
/* 196:    */     {
/* 197:201 */       ps = conn.prepareStatement("delete from serialno");
/* 198:202 */       if (ps.executeUpdate() > 0) {
/* 199:203 */         result = true;
/* 200:    */       }
/* 201:    */     }
/* 202:    */     catch (Exception e)
/* 203:    */     {
/* 204:206 */       e.printStackTrace();
/* 205:    */     }
/* 206:    */     finally
/* 207:    */     {
/* 208:208 */       close(ps);
/* 209:    */     }
/* 210:210 */     return result;
/* 211:    */   }
/* 212:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.DeviceDao
 * JD-Core Version:    0.7.0.1
 */