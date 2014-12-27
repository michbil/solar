/*  1:   */ package cn.com.voltronic.solar.util;
/*  2:   */ 
/*  3:   */ import java.io.File;
/*  4:   */ import java.io.FileInputStream;
/*  5:   */ import java.io.FileOutputStream;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.io.InputStream;
/*  8:   */ import java.io.PrintStream;
/*  9:   */ import java.util.Date;
/* 10:   */ import java.util.Properties;
/* 11:   */ 
/* 12:   */ public class ReadDatFile
/* 13:   */ {
/* 14:   */   private static final String FILE_NAME = "config/lastupdate.dat";
/* 15:   */   private static Properties _properties;
/* 16:20 */   private static String strfile = System.getProperty("user.dir") + "/" + 
/* 17:21 */     "config/lastupdate.dat";
/* 18:   */   
/* 19:   */   static
/* 20:   */   {
/* 21:24 */     InputStream in = null;
/* 22:   */     try
/* 23:   */     {
/* 24:26 */       File dir = new File(strfile.substring(0, strfile.lastIndexOf("/")));
/* 25:27 */       File file = new File(strfile);
/* 26:28 */       if (!dir.exists()) {
/* 27:29 */         dir.mkdirs();
/* 28:   */       }
/* 29:31 */       if (!file.exists()) {
/* 30:32 */         file.createNewFile();
/* 31:   */       }
/* 32:34 */       in = new FileInputStream(file);
/* 33:35 */       _properties = new Properties();
/* 34:36 */       _properties.load(in);
/* 35:   */     }
/* 36:   */     catch (Exception e)
/* 37:   */     {
/* 38:38 */       System.err.println(e.getMessage());
/* 39:   */       try
/* 40:   */       {
/* 41:41 */         if (in != null) {
/* 42:42 */           in.close();
/* 43:   */         }
/* 44:   */       }
/* 45:   */       catch (IOException e)
/* 46:   */       {
/* 47:45 */         e.printStackTrace();
/* 48:   */       }
/* 49:   */     }
/* 50:   */     finally
/* 51:   */     {
/* 52:   */       try
/* 53:   */       {
/* 54:41 */         if (in != null) {
/* 55:42 */           in.close();
/* 56:   */         }
/* 57:   */       }
/* 58:   */       catch (IOException e)
/* 59:   */       {
/* 60:45 */         e.printStackTrace();
/* 61:   */       }
/* 62:   */     }
/* 63:   */   }
/* 64:   */   
/* 65:   */   public static String getString(String key)
/* 66:   */     throws Exception
/* 67:   */   {
/* 68:51 */     if (_properties != null) {
/* 69:52 */       return _properties.getProperty(key);
/* 70:   */     }
/* 71:54 */     return "";
/* 72:   */   }
/* 73:   */   
/* 74:   */   public static int getInteger(String key)
/* 75:   */     throws Exception
/* 76:   */   {
/* 77:59 */     int value = Integer.parseInt(_properties.getProperty(key));
/* 78:60 */     return value;
/* 79:   */   }
/* 80:   */   
/* 81:   */   public static float getFloat(String key)
/* 82:   */     throws Exception
/* 83:   */   {
/* 84:64 */     Float value = new Float(_properties.getProperty(key));
/* 85:65 */     return value.floatValue();
/* 86:   */   }
/* 87:   */   
/* 88:   */   public static void updateProperty(String key, String value)
/* 89:   */     throws Exception
/* 90:   */   {
/* 91:70 */     _properties.setProperty(key, value);
/* 92:71 */     FileOutputStream fileOut = new FileOutputStream(strfile);
/* 93:   */     try
/* 94:   */     {
/* 95:73 */       _properties.store(fileOut, "last update time" + new Date().toString());
/* 96:   */     }
/* 97:   */     finally
/* 98:   */     {
/* 99:75 */       if (fileOut != null) {
/* :0:76 */         fileOut.close();
/* :1:   */       }
/* :2:   */     }
/* :3:   */   }
/* :4:   */   
/* :5:   */   public static void main(String[] args)
/* :6:   */   {
/* :7:   */     try
/* :8:   */     {
/* :9:83 */       System.out.println(getString("version"));
/* ;0:   */     }
/* ;1:   */     catch (Exception e)
/* ;2:   */     {
/* ;3:86 */       e.printStackTrace();
/* ;4:   */     }
/* ;5:   */   }
/* ;6:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.ReadDatFile
 * JD-Core Version:    0.7.0.1
 */