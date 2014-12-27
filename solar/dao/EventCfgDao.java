/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.Eventcfg;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import java.io.PrintStream;
/*   6:    */ import java.sql.Connection;
/*   7:    */ import java.sql.PreparedStatement;
/*   8:    */ import java.sql.ResultSet;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.List;
/*  11:    */ 
/*  12:    */ public class EventCfgDao
/*  13:    */   extends BaseDao
/*  14:    */ {
/*  15: 19 */   private final String INSERT_CFG = "insert into EVENT_CFG(EVENTID,ACTION,RECEIVE) values(?,?,?)";
/*  16: 20 */   private final String QUERY_EVENTCFG = "SELECT * FROM EVENT_CFG WHERE EVENTID = ? ";
/*  17: 21 */   private final String DELETE_EVENTCFG = "DELETE FROM EVENT_CFG WHERE EVENTID=? ";
/*  18: 22 */   private final String DELETE_EVENTCFG_ALL = "DELETE FROM EVENT_CFG";
/*  19:    */   
/*  20:    */   public boolean insertCfg(Eventcfg eventCfg)
/*  21:    */   {
/*  22: 30 */     boolean result = true;
/*  23: 31 */     Connection conn = DBManager.getConnection();
/*  24: 32 */     PreparedStatement ps = null;
/*  25:    */     try
/*  26:    */     {
/*  27: 34 */       ps = conn.prepareStatement("insert into EVENT_CFG(EVENTID,ACTION,RECEIVE) values(?,?,?)");
/*  28: 35 */       ps.setString(1, eventCfg.getEventid());
/*  29: 36 */       ps.setInt(2, eventCfg.getAction());
/*  30: 37 */       ps.setString(3, eventCfg.getReceive());
/*  31: 38 */       ps.executeUpdate();
/*  32: 39 */       conn.commit();
/*  33:    */     }
/*  34:    */     catch (Exception ex)
/*  35:    */     {
/*  36: 41 */       result = false;
/*  37:    */     }
/*  38:    */     finally
/*  39:    */     {
/*  40: 43 */       close(ps);
/*  41:    */     }
/*  42: 45 */     return result;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public List<Eventcfg> queryEventcfg(String eventid)
/*  46:    */   {
/*  47: 56 */     List<Eventcfg> result = new ArrayList();
/*  48: 57 */     Connection conn = DBManager.getConnection();
/*  49: 58 */     PreparedStatement ps = null;
/*  50: 59 */     ResultSet rs = null;
/*  51:    */     try
/*  52:    */     {
/*  53: 61 */       ps = conn.prepareStatement("SELECT * FROM EVENT_CFG WHERE EVENTID = ? ");
/*  54: 62 */       ps.setString(1, eventid);
/*  55: 63 */       rs = ps.executeQuery();
/*  56: 64 */       while (rs.next())
/*  57:    */       {
/*  58: 65 */         Eventcfg e = new Eventcfg();
/*  59: 66 */         e.setEventid(rs.getString("eventid"));
/*  60: 67 */         e.setAction(rs.getInt("action"));
/*  61: 68 */         e.setReceive(rs.getString("receive"));
/*  62: 69 */         result.add(e);
/*  63:    */       }
/*  64:    */     }
/*  65:    */     catch (Exception e)
/*  66:    */     {
/*  67: 72 */       e.printStackTrace();
/*  68:    */     }
/*  69:    */     finally
/*  70:    */     {
/*  71: 74 */       close(rs, ps);
/*  72:    */     }
/*  73: 76 */     return result;
/*  74:    */   }
/*  75:    */   
/*  76:    */   public boolean removeEventcfg(String saveEventId)
/*  77:    */   {
/*  78: 86 */     boolean result = false;
/*  79: 87 */     Connection conn = DBManager.getConnection();
/*  80: 88 */     PreparedStatement ps = null;
/*  81: 89 */     if (conn != null) {
/*  82:    */       try
/*  83:    */       {
/*  84: 91 */         ps = conn.prepareStatement("DELETE FROM EVENT_CFG WHERE EVENTID=? ");
/*  85: 92 */         ps.setString(1, saveEventId);
/*  86: 93 */         if (ps.executeUpdate() > 0) {
/*  87: 94 */           result = true;
/*  88:    */         }
/*  89:    */       }
/*  90:    */       catch (Exception e)
/*  91:    */       {
/*  92: 97 */         e.printStackTrace();
/*  93:    */       }
/*  94:    */       finally
/*  95:    */       {
/*  96: 99 */         close(ps);
/*  97:    */       }
/*  98:    */     }
/*  99:102 */     return result;
/* 100:    */   }
/* 101:    */   
/* 102:    */   public boolean removeEventcfgAll()
/* 103:    */   {
/* 104:112 */     boolean result = false;
/* 105:113 */     Connection conn = DBManager.getConnection();
/* 106:114 */     PreparedStatement ps = null;
/* 107:115 */     if (conn != null) {
/* 108:    */       try
/* 109:    */       {
/* 110:117 */         ps = conn.prepareStatement("DELETE FROM EVENT_CFG");
/* 111:118 */         if (ps.executeUpdate() > 0) {
/* 112:119 */           result = true;
/* 113:    */         }
/* 114:    */       }
/* 115:    */       catch (Exception e)
/* 116:    */       {
/* 117:122 */         e.printStackTrace();
/* 118:    */       }
/* 119:    */       finally
/* 120:    */       {
/* 121:124 */         close(ps);
/* 122:    */       }
/* 123:    */     }
/* 124:127 */     return result;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public static void main(String[] args)
/* 128:    */   {
/* 129:131 */     EventCfgDao dao = new EventCfgDao();
/* 130:132 */     boolean re = dao.removeEventcfgAll();
/* 131:133 */     System.out.println("re:" + re);
/* 132:134 */     for (int i = 1001; i < 1099; i++)
/* 133:    */     {
/* 134:135 */       Eventcfg eventCfg = new Eventcfg();
/* 135:136 */       eventCfg.setAction(1);
/* 136:137 */       eventCfg.setEventid(i);
/* 137:138 */       eventCfg.setReceive(null);
/* 138:139 */       dao.insertCfg(eventCfg);
/* 139:    */       
/* 140:141 */       Eventcfg eventCfg2 = new Eventcfg();
/* 141:142 */       eventCfg2.setAction(2);
/* 142:143 */       eventCfg2.setEventid(i);
/* 143:144 */       eventCfg2.setReceive(null);
/* 144:145 */       dao.insertCfg(eventCfg2);
/* 145:    */     }
/* 146:147 */     for (int i = 2001; i < 2099; i++)
/* 147:    */     {
/* 148:148 */       Eventcfg eventCfg = new Eventcfg();
/* 149:149 */       eventCfg.setAction(1);
/* 150:150 */       eventCfg.setEventid(i);
/* 151:151 */       eventCfg.setReceive(null);
/* 152:152 */       dao.insertCfg(eventCfg);
/* 153:    */       
/* 154:154 */       Eventcfg eventCfg2 = new Eventcfg();
/* 155:155 */       eventCfg2.setAction(2);
/* 156:156 */       eventCfg2.setEventid(i);
/* 157:157 */       eventCfg2.setReceive(null);
/* 158:158 */       dao.insertCfg(eventCfg2);
/* 159:    */     }
/* 160:160 */     for (int i = 3001; i < 3099; i++)
/* 161:    */     {
/* 162:161 */       Eventcfg eventCfg = new Eventcfg();
/* 163:162 */       eventCfg.setAction(1);
/* 164:163 */       eventCfg.setEventid(i);
/* 165:164 */       eventCfg.setReceive(null);
/* 166:165 */       dao.insertCfg(eventCfg);
/* 167:    */       
/* 168:167 */       Eventcfg eventCfg2 = new Eventcfg();
/* 169:168 */       eventCfg2.setAction(2);
/* 170:169 */       eventCfg2.setEventid(i);
/* 171:170 */       eventCfg2.setReceive(null);
/* 172:171 */       dao.insertCfg(eventCfg2);
/* 173:    */     }
/* 174:    */   }
/* 175:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.EventCfgDao
 * JD-Core Version:    0.7.0.1
 */