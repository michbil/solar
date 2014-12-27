/*   1:    */ package cn.com.voltronic.solar.dao;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.SynchroEnergyPlan;
/*   4:    */ import cn.com.voltronic.solar.dbtools.DBManager;
/*   5:    */ import java.sql.Connection;
/*   6:    */ import java.sql.PreparedStatement;
/*   7:    */ import java.sql.ResultSet;
/*   8:    */ import java.sql.Timestamp;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.Date;
/*  11:    */ import java.util.List;
/*  12:    */ 
/*  13:    */ public class SynchroEnergyDao
/*  14:    */   extends BaseDao
/*  15:    */ {
/*  16:    */   public static final String QUERY = " SELECT * FROM synchroenergyplan";
/*  17:    */   public static final String QUERY_EXIST = " SELECT * FROM synchroenergyplan where serialno = ?";
/*  18:    */   public static final String INSERT = " INSERT INTO synchroenergyplan(serialno,cycle,plan_week,PLAN_MONTH,PLAN_TIME,synchroTime) VALUES(?,?,?,?,?,?)";
/*  19:    */   public static final String DELETE = " delete from  synchroenergyplan where serialno = ?";
/*  20:    */   
/*  21:    */   public List<SynchroEnergyPlan> querySynchroEnergyPlan()
/*  22:    */   {
/*  23: 27 */     List<SynchroEnergyPlan> result = new ArrayList();
/*  24: 28 */     Connection conn = DBManager.getConnection();
/*  25: 29 */     PreparedStatement ps = null;
/*  26: 30 */     ResultSet rs = null;
/*  27:    */     try
/*  28:    */     {
/*  29: 32 */       ps = conn.prepareStatement(" SELECT * FROM synchroenergyplan");
/*  30: 33 */       rs = ps.executeQuery();
/*  31: 34 */       while (rs.next())
/*  32:    */       {
/*  33: 35 */         SynchroEnergyPlan plan = new SynchroEnergyPlan();
/*  34: 36 */         plan.setSerialno(rs.getString("serialno"));
/*  35: 37 */         plan.setCycle(rs.getString("CYCLE"));
/*  36: 38 */         plan.setPlanMonth(rs.getInt("PLAN_MONTH"));
/*  37: 39 */         plan.setPlanTime(rs.getTimestamp("PLAN_TIME"));
/*  38: 40 */         plan.setPlanWeek(rs.getInt("PLAN_WEEK"));
/*  39: 41 */         plan.setSynchroTime(rs.getInt("synchrotime"));
/*  40: 42 */         result.add(plan);
/*  41:    */       }
/*  42:    */     }
/*  43:    */     catch (Exception e)
/*  44:    */     {
/*  45: 45 */       e.printStackTrace();
/*  46:    */     }
/*  47:    */     finally
/*  48:    */     {
/*  49: 47 */       close(rs, ps);
/*  50:    */     }
/*  51: 49 */     return result;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public boolean ifPlanExist(String serialno)
/*  55:    */   {
/*  56: 53 */     boolean result = false;
/*  57: 54 */     Connection conn = DBManager.getConnection();
/*  58: 55 */     PreparedStatement ps = null;
/*  59: 56 */     ResultSet rs = null;
/*  60:    */     try
/*  61:    */     {
/*  62: 58 */       ps = conn.prepareStatement(" SELECT * FROM synchroenergyplan where serialno = ?");
/*  63: 59 */       ps.setString(1, serialno);
/*  64: 60 */       rs = ps.executeQuery();
/*  65: 61 */       if (rs.next()) {
/*  66: 62 */         result = true;
/*  67:    */       }
/*  68:    */     }
/*  69:    */     catch (Exception e)
/*  70:    */     {
/*  71: 66 */       e.printStackTrace();
/*  72:    */     }
/*  73:    */     finally
/*  74:    */     {
/*  75: 68 */       close(rs, ps);
/*  76:    */     }
/*  77: 70 */     return result;
/*  78:    */   }
/*  79:    */   
/*  80:    */   public boolean insertPlan(SynchroEnergyPlan plan)
/*  81:    */   {
/*  82: 74 */     boolean result = false;
/*  83: 75 */     Connection conn = DBManager.getConnection();
/*  84: 76 */     PreparedStatement ps = null;
/*  85:    */     try
/*  86:    */     {
/*  87: 78 */       ps = conn.prepareStatement(" INSERT INTO synchroenergyplan(serialno,cycle,plan_week,PLAN_MONTH,PLAN_TIME,synchroTime) VALUES(?,?,?,?,?,?)");
/*  88: 79 */       ps.setString(1, plan.getSerialno());
/*  89: 80 */       ps.setString(2, plan.getCycle());
/*  90: 81 */       ps.setInt(3, plan.getPlanWeek());
/*  91: 82 */       ps.setInt(4, plan.getPlanMonth());
/*  92: 83 */       ps.setTimestamp(5, new Timestamp(
/*  93: 84 */         plan.getPlanTime().getTime()));
/*  94: 85 */       ps.setInt(6, plan.getSynchroTime());
/*  95: 86 */       if (ps.executeUpdate() > 0) {
/*  96: 87 */         result = true;
/*  97:    */       }
/*  98:    */     }
/*  99:    */     catch (Exception e)
/* 100:    */     {
/* 101: 90 */       e.printStackTrace();
/* 102:    */     }
/* 103:    */     finally
/* 104:    */     {
/* 105: 92 */       close(ps);
/* 106:    */     }
/* 107: 94 */     return result;
/* 108:    */   }
/* 109:    */   
/* 110:    */   public boolean deletePlan(String serialno)
/* 111:    */   {
/* 112: 98 */     boolean result = false;
/* 113: 99 */     Connection conn = DBManager.getConnection();
/* 114:100 */     PreparedStatement ps = null;
/* 115:    */     try
/* 116:    */     {
/* 117:102 */       ps = conn.prepareStatement(" delete from  synchroenergyplan where serialno = ?");
/* 118:103 */       ps.setString(1, serialno);
/* 119:104 */       if (ps.executeUpdate() > 0) {
/* 120:105 */         result = true;
/* 121:    */       }
/* 122:    */     }
/* 123:    */     catch (Exception e)
/* 124:    */     {
/* 125:108 */       e.printStackTrace();
/* 126:    */     }
/* 127:    */     finally
/* 128:    */     {
/* 129:110 */       close(ps);
/* 130:    */     }
/* 131:112 */     return result;
/* 132:    */   }
/* 133:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.SynchroEnergyDao
 * JD-Core Version:    0.7.0.1
 */