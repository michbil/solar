/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.BuyInfo;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import java.sql.Connection;
/*   6:    */ import java.sql.PreparedStatement;
/*   7:    */ import java.sql.ResultSet;
/*   8:    */ 
/*   9:    */ public class BuyInfoDao
/*  10:    */   extends BaseDao
/*  11:    */ {
/*  12:    */   public static final String INSERT_BUYINFO = " INSERT INTO BUYINFO(prodid,serialno,purchasedate,batpurchasedate,warranty,batwarranty,pncode,useprompt,batuseperiod) VALUES(?,?,?,?,?,?,?,?,?)";
/*  13:    */   public static final String UPDATA_BUYINFO = " UPDATE BUYINFO SET purchasedate=?,batpurchasedate=?,warranty=?,batwarranty=?,pncode=?,useprompt=?,batuseperiod=? WHERE serialno=? ";
/*  14:    */   public static final String QUERY_BUYINFO = " SELECT * FROM BUYINFO WHERE serialno=? ";
/*  15:    */   
/*  16:    */   public boolean addBuyinfo(BuyInfo buyinfo)
/*  17:    */   {
/*  18: 28 */     boolean result = false;
/*  19: 29 */     Connection conn = DBManager.getConnection();
/*  20: 30 */     PreparedStatement ps = null;
/*  21: 31 */     if (conn != null) {
/*  22:    */       try
/*  23:    */       {
/*  24: 33 */         ps = conn.prepareStatement(" INSERT INTO BUYINFO(prodid,serialno,purchasedate,batpurchasedate,warranty,batwarranty,pncode,useprompt,batuseperiod) VALUES(?,?,?,?,?,?,?,?,?)");
/*  25: 34 */         ps.setString(1, buyinfo.getProdid());
/*  26: 35 */         ps.setString(2, buyinfo.getSerialno());
/*  27: 36 */         ps.setDate(3, new java.sql.Date(
/*  28: 37 */           buyinfo.getPurchasedate().getTime()));
/*  29: 38 */         if (buyinfo.getBatpurchasedate().equals("")) {
/*  30: 39 */           ps.setDate(4, null);
/*  31:    */         } else {
/*  32: 41 */           ps.setDate(4, new java.sql.Date(
/*  33: 42 */             buyinfo.getBatpurchasedate().getTime()));
/*  34:    */         }
/*  35: 44 */         ps.setInt(5, buyinfo.getWarranty());
/*  36: 45 */         ps.setInt(6, buyinfo.getBatwarranty());
/*  37: 46 */         ps.setString(7, buyinfo.getPncode());
/*  38: 47 */         ps.setBoolean(8, buyinfo.isUseprompt());
/*  39: 48 */         ps.setInt(9, buyinfo.getBatuseperiod());
/*  40: 49 */         if (ps.executeUpdate() > 0) {
/*  41: 50 */           result = true;
/*  42:    */         }
/*  43:    */       }
/*  44:    */       catch (Exception e)
/*  45:    */       {
/*  46: 53 */         e.printStackTrace();
/*  47:    */       }
/*  48:    */       finally
/*  49:    */       {
/*  50: 55 */         close(ps);
/*  51:    */       }
/*  52:    */     }
/*  53: 58 */     return result;
/*  54:    */   }
/*  55:    */   
/*  56:    */   public boolean updataBuyinfo(BuyInfo buyinfo)
/*  57:    */   {
/*  58: 68 */     boolean result = false;
/*  59: 69 */     Connection conn = DBManager.getConnection();
/*  60: 70 */     PreparedStatement ps = null;
/*  61: 71 */     if (conn != null) {
/*  62:    */       try
/*  63:    */       {
/*  64: 73 */         ps = conn.prepareStatement(" UPDATE BUYINFO SET purchasedate=?,batpurchasedate=?,warranty=?,batwarranty=?,pncode=?,useprompt=?,batuseperiod=? WHERE serialno=? ");
/*  65: 74 */         ps.setDate(1, new java.sql.Date(
/*  66: 75 */           buyinfo.getPurchasedate().getTime()));
/*  67: 76 */         if (buyinfo.getBatpurchasedate().equals("")) {
/*  68: 77 */           ps.setDate(2, null);
/*  69:    */         } else {
/*  70: 79 */           ps.setDate(2, new java.sql.Date(
/*  71: 80 */             buyinfo.getBatpurchasedate().getTime()));
/*  72:    */         }
/*  73: 82 */         ps.setInt(3, buyinfo.getWarranty());
/*  74: 83 */         ps.setInt(4, buyinfo.getBatwarranty());
/*  75: 84 */         ps.setString(5, buyinfo.getPncode());
/*  76: 85 */         ps.setBoolean(6, buyinfo.isUseprompt());
/*  77: 86 */         ps.setInt(7, buyinfo.getBatuseperiod());
/*  78: 87 */         ps.setString(8, buyinfo.getSerialno());
/*  79: 88 */         if (ps.executeUpdate() > 0) {
/*  80: 89 */           result = true;
/*  81:    */         }
/*  82:    */       }
/*  83:    */       catch (Exception e)
/*  84:    */       {
/*  85: 92 */         e.printStackTrace();
/*  86:    */       }
/*  87:    */       finally
/*  88:    */       {
/*  89: 94 */         close(ps);
/*  90:    */       }
/*  91:    */     }
/*  92: 97 */     return result;
/*  93:    */   }
/*  94:    */   
/*  95:    */   public boolean isExistBuyinfo(String serialno)
/*  96:    */   {
/*  97:108 */     boolean result = false;
/*  98:109 */     Connection conn = DBManager.getConnection();
/*  99:110 */     PreparedStatement ps = null;
/* 100:111 */     ResultSet rs = null;
/* 101:112 */     if (conn != null) {
/* 102:    */       try
/* 103:    */       {
/* 104:114 */         ps = conn.prepareStatement(" SELECT * FROM BUYINFO WHERE serialno=? ");
/* 105:115 */         ps.setString(1, serialno);
/* 106:116 */         rs = ps.executeQuery();
/* 107:117 */         while (rs.next()) {
/* 108:118 */           result = true;
/* 109:    */         }
/* 110:    */       }
/* 111:    */       catch (Exception ex)
/* 112:    */       {
/* 113:121 */         ex.printStackTrace();
/* 114:    */       }
/* 115:    */       finally
/* 116:    */       {
/* 117:123 */         close(rs, ps);
/* 118:    */       }
/* 119:    */     }
/* 120:126 */     return result;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public BuyInfo queryBuyinfo(String serialno)
/* 124:    */   {
/* 125:137 */     BuyInfo result = null;
/* 126:138 */     Connection conn = DBManager.getConnection();
/* 127:139 */     PreparedStatement ps = null;
/* 128:140 */     ResultSet rs = null;
/* 129:141 */     if (conn != null) {
/* 130:    */       try
/* 131:    */       {
/* 132:143 */         ps = conn.prepareStatement(" SELECT * FROM BUYINFO WHERE serialno=? ");
/* 133:144 */         ps.setString(1, serialno);
/* 134:145 */         rs = ps.executeQuery();
/* 135:146 */         while (rs.next())
/* 136:    */         {
/* 137:147 */           result = new BuyInfo();
/* 138:148 */           result.setSerialno(serialno);
/* 139:149 */           result.setPurchasedate(rs.getDate("purchasedate"));
/* 140:150 */           result.setBatpurchasedate(rs.getDate("batpurchasedate"));
/* 141:151 */           result.setWarranty(rs.getInt("warranty"));
/* 142:152 */           result.setBatwarranty(rs.getInt("batwarranty"));
/* 143:153 */           result.setUseprompt(rs.getBoolean("useprompt"));
/* 144:154 */           result.setBatuseperiod(rs.getInt("batuseperiod"));
/* 145:155 */           result.setPncode(rs.getString("pncode"));
/* 146:    */         }
/* 147:    */       }
/* 148:    */       catch (Exception e)
/* 149:    */       {
/* 150:158 */         e.printStackTrace();
/* 151:    */       }
/* 152:    */       finally
/* 153:    */       {
/* 154:160 */         close(rs, ps);
/* 155:    */       }
/* 156:    */     }
/* 157:163 */     return result;
/* 158:    */   }
/* 159:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.BuyInfoDao
 * JD-Core Version:    0.7.0.1
 */