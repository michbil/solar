/*  1:   */ package cn.com.voltronic.solar.dao;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*  4:   */ import java.sql.PreparedStatement;
/*  5:   */ import java.sql.ResultSet;
/*  6:   */ import java.sql.SQLException;
/*  7:   */ import java.util.Calendar;
/*  8:   */ import java.util.Date;
/*  9:   */ 
/* 10:   */ public class BaseDao
/* 11:   */   implements IDao
/* 12:   */ {
/* 13:   */   private BeanBag _beanbag;
/* 14:   */   
/* 15:   */   protected void close(ResultSet rs, PreparedStatement ps)
/* 16:   */   {
/* 17:22 */     if (rs != null) {
/* 18:   */       try
/* 19:   */       {
/* 20:24 */         rs.close();
/* 21:   */       }
/* 22:   */       catch (SQLException localSQLException) {}
/* 23:   */     }
/* 24:28 */     if (ps != null) {
/* 25:   */       try
/* 26:   */       {
/* 27:30 */         ps.close();
/* 28:   */       }
/* 29:   */       catch (SQLException localSQLException1) {}
/* 30:   */     }
/* 31:   */   }
/* 32:   */   
/* 33:   */   protected void close(PreparedStatement ps)
/* 34:   */   {
/* 35:37 */     close(null, ps);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public int getWorkdataYear(Date date)
/* 39:   */   {
/* 40:41 */     Calendar ca = Calendar.getInstance();
/* 41:42 */     ca.setTime(date);
/* 42:43 */     return ca.get(1);
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setBeanBag(BeanBag bag)
/* 46:   */   {
/* 47:48 */     this._beanbag = bag;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public BeanBag getBeanBag()
/* 51:   */   {
/* 52:52 */     return this._beanbag;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public boolean writeRecords()
/* 56:   */   {
/* 57:57 */     return false;
/* 58:   */   }
/* 59:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.BaseDao
 * JD-Core Version:    0.7.0.1
 */