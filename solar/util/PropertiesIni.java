/*  1:   */ package cn.com.voltronic.solar.util;
/*  2:   */ 
/*  3:   */ import java.io.FileInputStream;
/*  4:   */ import java.io.FileOutputStream;
/*  5:   */ import java.io.IOException;
/*  6:   */ import java.io.InputStream;
/*  7:   */ import java.io.PrintStream;
/*  8:   */ import java.util.Date;
/*  9:   */ import java.util.Properties;
/* 10:   */ 
/* 11:   */ public class PropertiesIni
/* 12:   */ {
/* 13:   */   private Properties _properties;
/* 14:   */   private String strfile;
/* 15:   */   
/* 16:   */   public String getStrfile()
/* 17:   */   {
/* 18:21 */     return this.strfile;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public void setStrfile(String strfile)
/* 22:   */   {
/* 23:25 */     this.strfile = strfile;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public PropertiesIni(String strfile)
/* 27:   */   {
/* 28:29 */     this.strfile = strfile;
/* 29:30 */     InputStream in = null;
/* 30:   */     try
/* 31:   */     {
/* 32:32 */       in = new FileInputStream(strfile);
/* 33:33 */       this._properties = new Properties();
/* 34:34 */       this._properties.load(in);
/* 35:   */     }
/* 36:   */     catch (Exception e)
/* 37:   */     {
/* 38:36 */       System.err.println(e.getMessage());
/* 39:   */       try
/* 40:   */       {
/* 41:39 */         if (in != null) {
/* 42:40 */           in.close();
/* 43:   */         }
/* 44:   */       }
/* 45:   */       catch (IOException e)
/* 46:   */       {
/* 47:43 */         e.printStackTrace();
/* 48:   */       }
/* 49:   */     }
/* 50:   */     finally
/* 51:   */     {
/* 52:   */       try
/* 53:   */       {
/* 54:39 */         if (in != null) {
/* 55:40 */           in.close();
/* 56:   */         }
/* 57:   */       }
/* 58:   */       catch (IOException e)
/* 59:   */       {
/* 60:43 */         e.printStackTrace();
/* 61:   */       }
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public String getString(String key)
/* 66:   */   {
/* 67:49 */     if (this._properties != null) {
/* 68:50 */       return this._properties.getProperty(key);
/* 69:   */     }
/* 70:52 */     return "";
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void updateProperty(String key, String value)
/* 74:   */     throws Exception
/* 75:   */   {
/* 76:58 */     this._properties.setProperty(key, value);
/* 77:59 */     FileOutputStream fileOut = new FileOutputStream(this.strfile);
/* 78:   */     try
/* 79:   */     {
/* 80:61 */       this._properties.store(fileOut, "last update time" + 
/* 81:62 */         new Date().toString());
/* 82:   */     }
/* 83:   */     finally
/* 84:   */     {
/* 85:64 */       if (fileOut != null) {
/* 86:65 */         fileOut.close();
/* 87:   */       }
/* 88:   */     }
/* 89:   */   }
/* 90:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.PropertiesIni
 * JD-Core Version:    0.7.0.1
 */