/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.ModbusSet;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import java.sql.Connection;
/*   6:    */ import java.sql.PreparedStatement;
/*   7:    */ import java.sql.ResultSet;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.List;
/*  10:    */ 
/*  11:    */ public class ModbusDao
/*  12:    */   extends BaseDao
/*  13:    */ {
/*  14:    */   public static final String INSERT_MODBUSSET = "insert into modbusset(portname,baudrate,databit,stopbit,parity,deviceid) values(?,?,?,?,?,?) ";
/*  15:    */   public static final String QUERY_MODBUSSET_ALL = "select * from modbusset ";
/*  16:    */   public static final String QUERY_MODBUSSET = "select * from modbusset where portname=? ";
/*  17:    */   public static final String UPDATE_MODBUSSET = "update modbusset set baudrate=?,databit=?,stopbit=?,parity=?,deviceid=? where portname=? ";
/*  18:    */   public static final String INSERT_MODBUSDEVICE = "insert into modbuspwd(serialno,password) values(?,?) ";
/*  19:    */   public static final String QUERY_MODBUSDEVICE = "select password from modbuspwd where serialno = ? ";
/*  20:    */   public static final String UPDATE_MODBUSDEVICE = "update modbuspwd set password=? where serialno=? ";
/*  21:    */   
/*  22:    */   public List<ModbusSet> queryModbusSet()
/*  23:    */   {
/*  24: 37 */     List<ModbusSet> result = new ArrayList();
/*  25: 38 */     Connection conn = DBManager.getConnection();
/*  26: 39 */     PreparedStatement ps = null;
/*  27: 40 */     ResultSet rs = null;
/*  28:    */     try
/*  29:    */     {
/*  30: 42 */       ps = conn.prepareStatement("select * from modbusset ");
/*  31: 43 */       rs = ps.executeQuery();
/*  32: 44 */       while (rs.next())
/*  33:    */       {
/*  34: 45 */         ModbusSet data = new ModbusSet();
/*  35: 46 */         data.setPortName(rs.getString("portname"));
/*  36: 47 */         data.setBaudrate(rs.getInt("baudrate"));
/*  37: 48 */         data.setDataBit(rs.getInt("databit"));
/*  38: 49 */         data.setStopBit(rs.getInt("stopbit"));
/*  39: 50 */         data.setParity(rs.getInt("parity"));
/*  40: 51 */         data.setDeviceIds(rs.getString("deviceid"));
/*  41: 52 */         result.add(data);
/*  42:    */       }
/*  43:    */     }
/*  44:    */     catch (Exception e)
/*  45:    */     {
/*  46: 55 */       e.printStackTrace();
/*  47:    */     }
/*  48:    */     finally
/*  49:    */     {
/*  50: 57 */       close(rs, ps);
/*  51:    */     }
/*  52: 59 */     return result;
/*  53:    */   }
/*  54:    */   
/*  55:    */   public ModbusSet queryModbusSetByPort(String portName)
/*  56:    */   {
/*  57: 63 */     ModbusSet result = null;
/*  58: 64 */     Connection conn = DBManager.getConnection();
/*  59: 65 */     PreparedStatement ps = null;
/*  60: 66 */     ResultSet rs = null;
/*  61:    */     try
/*  62:    */     {
/*  63: 68 */       ps = conn.prepareStatement("select * from modbusset where portname=? ");
/*  64: 69 */       ps.setString(1, portName);
/*  65: 70 */       rs = ps.executeQuery();
/*  66: 71 */       while (rs.next())
/*  67:    */       {
/*  68: 72 */         result = new ModbusSet();
/*  69: 73 */         result.setPortName(rs.getString("portname"));
/*  70: 74 */         result.setBaudrate(rs.getInt("baudrate"));
/*  71: 75 */         result.setDataBit(rs.getInt("databit"));
/*  72: 76 */         result.setStopBit(rs.getInt("stopbit"));
/*  73: 77 */         result.setParity(rs.getInt("parity"));
/*  74: 78 */         result.setDeviceIds(rs.getString("deviceid"));
/*  75:    */       }
/*  76:    */     }
/*  77:    */     catch (Exception e)
/*  78:    */     {
/*  79: 81 */       e.printStackTrace();
/*  80:    */     }
/*  81:    */     finally
/*  82:    */     {
/*  83: 83 */       close(rs, ps);
/*  84:    */     }
/*  85: 85 */     return result;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public boolean addModbusSet(ModbusSet data)
/*  89:    */   {
/*  90: 89 */     boolean result = false;
/*  91: 90 */     Connection conn = DBManager.getConnection();
/*  92: 91 */     PreparedStatement ps = null;
/*  93:    */     try
/*  94:    */     {
/*  95: 93 */       ps = conn.prepareStatement("insert into modbusset(portname,baudrate,databit,stopbit,parity,deviceid) values(?,?,?,?,?,?) ");
/*  96: 94 */       ps.setString(1, data.getPortName());
/*  97: 95 */       ps.setInt(2, data.getBaudrate());
/*  98: 96 */       ps.setInt(3, data.getDataBit());
/*  99: 97 */       ps.setInt(4, data.getStopBit());
/* 100: 98 */       ps.setInt(5, data.getParity());
/* 101:100 */       if (data.getDeviceIds().endsWith(",")) {
/* 102:102 */         data.setDeviceIds(data.getDeviceIds().substring(0, data.getDeviceIds().length() - 1));
/* 103:    */       }
/* 104:104 */       ps.setString(6, data.getDeviceIds());
/* 105:105 */       if (ps.executeUpdate() > 0) {
/* 106:106 */         result = true;
/* 107:    */       }
/* 108:    */     }
/* 109:    */     catch (Exception e)
/* 110:    */     {
/* 111:110 */       e.printStackTrace();
/* 112:    */     }
/* 113:    */     finally
/* 114:    */     {
/* 115:112 */       close(ps);
/* 116:    */     }
/* 117:114 */     return result;
/* 118:    */   }
/* 119:    */   
/* 120:    */   public boolean updateModbusSet(ModbusSet data)
/* 121:    */   {
/* 122:118 */     boolean result = false;
/* 123:119 */     Connection conn = DBManager.getConnection();
/* 124:120 */     PreparedStatement ps = null;
/* 125:    */     try
/* 126:    */     {
/* 127:122 */       ps = conn.prepareStatement("update modbusset set baudrate=?,databit=?,stopbit=?,parity=?,deviceid=? where portname=? ");
/* 128:123 */       ps.setInt(1, data.getBaudrate());
/* 129:124 */       ps.setInt(2, data.getDataBit());
/* 130:125 */       ps.setInt(3, data.getStopBit());
/* 131:126 */       ps.setInt(4, data.getParity());
/* 132:128 */       if (data.getDeviceIds().endsWith(",")) {
/* 133:130 */         data.setDeviceIds(data.getDeviceIds().substring(0, data.getDeviceIds().length() - 1));
/* 134:    */       }
/* 135:132 */       ps.setString(5, data.getDeviceIds());
/* 136:133 */       ps.setString(6, data.getPortName());
/* 137:134 */       if (ps.executeUpdate() > 0) {
/* 138:135 */         result = true;
/* 139:    */       }
/* 140:    */     }
/* 141:    */     catch (Exception e)
/* 142:    */     {
/* 143:138 */       e.printStackTrace();
/* 144:    */     }
/* 145:    */     finally
/* 146:    */     {
/* 147:140 */       close(ps);
/* 148:    */     }
/* 149:142 */     return result;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public String queryModbusPassword(String serialno)
/* 153:    */   {
/* 154:146 */     String result = "";
/* 155:147 */     Connection conn = DBManager.getConnection();
/* 156:148 */     PreparedStatement ps = null;
/* 157:149 */     ResultSet rs = null;
/* 158:150 */     if (conn != null) {
/* 159:    */       try
/* 160:    */       {
/* 161:152 */         ps = conn.prepareStatement("select password from modbuspwd where serialno = ? ");
/* 162:153 */         ps.setString(1, serialno);
/* 163:154 */         rs = ps.executeQuery();
/* 164:155 */         while (rs.next()) {
/* 165:157 */           result = rs.getString("password");
/* 166:    */         }
/* 167:    */       }
/* 168:    */       catch (Exception e)
/* 169:    */       {
/* 170:160 */         e.printStackTrace();
/* 171:    */       }
/* 172:    */       finally
/* 173:    */       {
/* 174:162 */         close(rs, ps);
/* 175:    */       }
/* 176:    */     }
/* 177:165 */     return result;
/* 178:    */   }
/* 179:    */   
/* 180:    */   public boolean addModbusPassword(String serialno, String password)
/* 181:    */   {
/* 182:169 */     boolean result = false;
/* 183:170 */     Connection conn = DBManager.getConnection();
/* 184:171 */     PreparedStatement ps = null;
/* 185:    */     try
/* 186:    */     {
/* 187:173 */       ps = conn.prepareStatement("insert into modbuspwd(serialno,password) values(?,?) ");
/* 188:174 */       ps.setString(1, serialno);
/* 189:175 */       ps.setString(2, password);
/* 190:176 */       if (ps.executeUpdate() > 0) {
/* 191:177 */         result = true;
/* 192:    */       }
/* 193:    */     }
/* 194:    */     catch (Exception e)
/* 195:    */     {
/* 196:180 */       e.printStackTrace();
/* 197:    */     }
/* 198:    */     finally
/* 199:    */     {
/* 200:182 */       close(ps);
/* 201:    */     }
/* 202:184 */     return result;
/* 203:    */   }
/* 204:    */   
/* 205:    */   public boolean updateModbusPassword(String serialno, String password)
/* 206:    */   {
/* 207:188 */     boolean result = false;
/* 208:189 */     Connection conn = DBManager.getConnection();
/* 209:190 */     PreparedStatement ps = null;
/* 210:    */     try
/* 211:    */     {
/* 212:192 */       ps = conn.prepareStatement("update modbuspwd set password=? where serialno=? ");
/* 213:193 */       ps.setString(1, password);
/* 214:194 */       ps.setString(2, serialno);
/* 215:195 */       if (ps.executeUpdate() > 0) {
/* 216:197 */         result = true;
/* 217:    */       }
/* 218:    */     }
/* 219:    */     catch (Exception e)
/* 220:    */     {
/* 221:200 */       e.printStackTrace();
/* 222:    */     }
/* 223:    */     finally
/* 224:    */     {
/* 225:202 */       close(ps);
/* 226:    */     }
/* 227:204 */     return result;
/* 228:    */   }
/* 229:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.ModbusDao
 * JD-Core Version:    0.7.0.1
 */