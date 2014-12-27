/*  1:   */ package cn.com.voltronic.solar.dbtools;
/*  2:   */ 
/*  3:   */ import java.sql.Connection;
/*  4:   */ import java.sql.DriverManager;
/*  5:   */ import java.sql.SQLException;
/*  6:   */ 
/*  7:   */ public class DBManager
/*  8:   */ {
/*  9:14 */   private static Connection conn = null;
/* 10:   */   
/* 11:   */   static
/* 12:   */   {
/* 13:   */     try
/* 14:   */     {
/* 15:18 */       Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
/* 16:   */     }
/* 17:   */     catch (ClassNotFoundException e)
/* 18:   */     {
/* 19:21 */       e.printStackTrace();
/* 20:   */     }
/* 21:   */     catch (InstantiationException e)
/* 22:   */     {
/* 23:24 */       e.printStackTrace();
/* 24:   */     }
/* 25:   */     catch (IllegalAccessException e)
/* 26:   */     {
/* 27:27 */       e.printStackTrace();
/* 28:   */     }
/* 29:   */   }
/* 30:   */   
/* 31:   */   public static Connection getConnection()
/* 32:   */   {
/* 33:   */     try
/* 34:   */     {
/* 35:33 */       if ((conn == null) || (conn.isClosed())) {
/* 36:34 */         conn = 
/* 37:35 */           DriverManager.getConnection("jdbc:derby:datas;create=false");
/* 38:   */       }
/* 39:   */     }
/* 40:   */     catch (SQLException e)
/* 41:   */     {
/* 42:38 */       e.printStackTrace();
/* 43:   */     }
/* 44:40 */     return conn;
/* 45:   */   }
/* 46:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dbtools.DBManager
 * JD-Core Version:    0.7.0.1
 */