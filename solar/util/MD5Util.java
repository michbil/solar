/*  1:   */ package cn.com.voltronic.solar.util;
/*  2:   */ 
/*  3:   */ import java.io.PrintStream;
/*  4:   */ import java.security.MessageDigest;
/*  5:   */ 
/*  6:   */ public class MD5Util
/*  7:   */ {
/*  8: 8 */   private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", 
/*  9: 9 */     "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
/* 10:   */   
/* 11:   */   public static boolean validatePassword(String password, String inputString)
/* 12:   */   {
/* 13:18 */     if (password.equals(encodeByMD5(inputString))) {
/* 14:19 */       return true;
/* 15:   */     }
/* 16:21 */     return false;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public static String encodeByMD5(String originString)
/* 20:   */   {
/* 21:27 */     if (originString != null) {
/* 22:   */       try
/* 23:   */       {
/* 24:30 */         MessageDigest md = MessageDigest.getInstance("MD5");
/* 25:   */         
/* 26:32 */         byte[] results = md.digest(originString.getBytes());
/* 27:   */         
/* 28:34 */         String resultString = byteArrayToHexString(results);
/* 29:35 */         return resultString.toUpperCase();
/* 30:   */       }
/* 31:   */       catch (Exception ex)
/* 32:   */       {
/* 33:37 */         ex.printStackTrace();
/* 34:   */       }
/* 35:   */     }
/* 36:40 */     return null;
/* 37:   */   }
/* 38:   */   
/* 39:   */   private static String byteArrayToHexString(byte[] b)
/* 40:   */   {
/* 41:49 */     StringBuffer resultSb = new StringBuffer();
/* 42:50 */     for (int i = 0; i < b.length; i++) {
/* 43:51 */       resultSb.append(byteToHexString(b[i]));
/* 44:   */     }
/* 45:53 */     return resultSb.toString();
/* 46:   */   }
/* 47:   */   
/* 48:   */   private static String byteToHexString(byte b)
/* 49:   */   {
/* 50:58 */     int n = b;
/* 51:59 */     if (n < 0) {
/* 52:60 */       n += 256;
/* 53:   */     }
/* 54:61 */     int d1 = n / 16;
/* 55:62 */     int d2 = n % 16;
/* 56:63 */     return hexDigits[d1] + hexDigits[d2];
/* 57:   */   }
/* 58:   */   
/* 59:   */   public static void main(String[] args)
/* 60:   */   {
/* 61:67 */     String str = encodeByMD5("administrator");
/* 62:68 */     System.out.println(str);
/* 63:   */   }
/* 64:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.MD5Util
 * JD-Core Version:    0.7.0.1
 */