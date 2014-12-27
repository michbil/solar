/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.Electrovalence;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import java.sql.Connection;
/*   6:    */ import java.sql.PreparedStatement;
/*   7:    */ import java.sql.ResultSet;
/*   8:    */ import java.sql.Timestamp;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.List;
/*  11:    */ 
/*  12:    */ public class PriceDao
/*  13:    */   extends BaseDao
/*  14:    */ {
/*  15:    */   private static final String INSERT_ELECTROVALENCE = " INSERT INTO ELECTROVALENCE(effectdate,price,trandate) values(?,?,?) ";
/*  16:    */   private static final String QUERY_ELECTROVALENCE = " SELECT * FROM ELECTROVALENCE order by effectdate";
/*  17:    */   private static final String DELETE_ELECTROVALENCE = " DELETE FROM  ELECTROVALENCE WHERE effectdate=? ";
/*  18:    */   private static final String QUERY_BYEFFECTDATE = "SELECT * FROM ELECTROVALENCE WHERE effectdate=?";
/*  19:    */   
/*  20:    */   public List<Electrovalence> queryElectrovalence()
/*  21:    */   {
/*  22: 32 */     List<Electrovalence> result = new ArrayList();
/*  23: 33 */     Connection conn = DBManager.getConnection();
/*  24: 34 */     PreparedStatement ps = null;
/*  25: 35 */     ResultSet rs = null;
/*  26:    */     try
/*  27:    */     {
/*  28: 37 */       ps = conn.prepareStatement(" SELECT * FROM ELECTROVALENCE order by effectdate");
/*  29: 38 */       rs = ps.executeQuery();
/*  30: 39 */       while (rs.next())
/*  31:    */       {
/*  32: 40 */         Electrovalence data = new Electrovalence();
/*  33: 41 */         data.setEffectdate(rs.getDate("effectdate"));
/*  34: 42 */         data.setPrice(rs.getDouble("price"));
/*  35: 43 */         data.setTrandate(rs.getTimestamp("trandate"));
/*  36: 44 */         result.add(data);
/*  37:    */       }
/*  38:    */     }
/*  39:    */     catch (Exception e)
/*  40:    */     {
/*  41: 47 */       e.printStackTrace();
/*  42:    */     }
/*  43:    */     finally
/*  44:    */     {
/*  45: 49 */       close(rs, ps);
/*  46:    */     }
/*  47: 51 */     return result;
/*  48:    */   }
/*  49:    */   
/*  50:    */   public boolean isExist(java.util.Date effectdate)
/*  51:    */   {
/*  52: 60 */     boolean result = false;
/*  53: 61 */     Connection conn = DBManager.getConnection();
/*  54: 62 */     PreparedStatement ps = null;
/*  55: 63 */     ResultSet rs = null;
/*  56:    */     try
/*  57:    */     {
/*  58: 65 */       ps = conn.prepareStatement("SELECT * FROM ELECTROVALENCE WHERE effectdate=?");
/*  59: 66 */       ps.setDate(1, new java.sql.Date(effectdate.getTime()));
/*  60: 67 */       rs = ps.executeQuery();
/*  61: 68 */       while (rs.next()) {
/*  62: 69 */         result = true;
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
/*  76:    */   public boolean addElectrovalence(Electrovalence data)
/*  77:    */   {
/*  78: 85 */     boolean returnvalue = false;
/*  79: 86 */     Connection conn = DBManager.getConnection();
/*  80: 87 */     PreparedStatement ps = null;
/*  81: 88 */     ResultSet rs = null;
/*  82:    */     try
/*  83:    */     {
/*  84: 90 */       ps = conn.prepareStatement(" INSERT INTO ELECTROVALENCE(effectdate,price,trandate) values(?,?,?) ");
/*  85: 91 */       ps.setDate(1, new java.sql.Date(data.getEffectdate().getTime()));
/*  86: 92 */       ps.setDouble(2, data.getPrice());
/*  87: 93 */       ps.setTimestamp(3, new Timestamp(data.getTrandate().getTime()));
/*  88: 94 */       if (ps.executeUpdate() > 0) {
/*  89: 95 */         returnvalue = true;
/*  90:    */       }
/*  91:    */     }
/*  92:    */     catch (Exception e)
/*  93:    */     {
/*  94: 98 */       e.printStackTrace();
/*  95:    */     }
/*  96:    */     finally
/*  97:    */     {
/*  98:100 */       close(rs, ps);
/*  99:    */     }
/* 100:102 */     return returnvalue;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public boolean removeElectrovalence(Electrovalence data)
/* 104:    */   {
/* 105:111 */     boolean result = false;
/* 106:112 */     Connection conn = DBManager.getConnection();
/* 107:113 */     PreparedStatement ps = null;
/* 108:    */     try
/* 109:    */     {
/* 110:115 */       ps = conn.prepareStatement(" DELETE FROM  ELECTROVALENCE WHERE effectdate=? ");
/* 111:116 */       ps.setDate(1, new java.sql.Date(data.getEffectdate().getTime()));
/* 112:117 */       if (ps.executeUpdate() > 0) {
/* 113:118 */         result = true;
/* 114:    */       }
/* 115:    */     }
/* 116:    */     catch (Exception e)
/* 117:    */     {
/* 118:121 */       e.printStackTrace();
/* 119:    */     }
/* 120:    */     finally
/* 121:    */     {
/* 122:123 */       close(ps);
/* 123:    */     }
/* 124:125 */     return result;
/* 125:    */   }
/* 126:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.PriceDao
 * JD-Core Version:    0.7.0.1
 */