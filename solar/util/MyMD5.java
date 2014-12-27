/*  1:   */ package cn.com.voltronic.solar.util;
/*  2:   */ 
/*  3:   */ public class MyMD5
/*  4:   */ {
/*  5:   */   public static String transform(String input)
/*  6:   */   {
/*  7:11 */     if ((input == null) || ("".equalsIgnoreCase(input))) {
/*  8:12 */       return "";
/*  9:   */     }
/* 10:14 */     input = "f9l" + input + "2cs";
/* 11:15 */     String startStr = "";
/* 12:16 */     String endStr = "";
/* 13:17 */     if (input.length() % 2 == 0)
/* 14:   */     {
/* 15:18 */       startStr = input.substring(0, input.length() / 2);
/* 16:19 */       endStr = input.substring(input.length() / 2);
/* 17:   */     }
/* 18:   */     else
/* 19:   */     {
/* 20:21 */       startStr = input.substring(0, input.length() / 2);
/* 21:22 */       endStr = input.substring(input.length() / 2);
/* 22:   */     }
/* 23:24 */     String secret = "fd_" + endStr + startStr + "lf1";
/* 24:25 */     return secret.toUpperCase();
/* 25:   */   }
/* 26:   */   
/* 27:   */   public static String restore(String input)
/* 28:   */   {
/* 29:29 */     if ((input == null) || ("".equalsIgnoreCase(input))) {
/* 30:30 */       return "";
/* 31:   */     }
/* 32:32 */     input = input.toLowerCase();
/* 33:33 */     input = input.substring(3, input.length() - 3);
/* 34:34 */     String startStr = "";
/* 35:35 */     String endStr = "";
/* 36:36 */     if (input.length() % 2 == 0)
/* 37:   */     {
/* 38:37 */       startStr = input.substring(0, input.length() / 2);
/* 39:38 */       endStr = input.substring(input.length() / 2);
/* 40:   */     }
/* 41:   */     else
/* 42:   */     {
/* 43:40 */       startStr = input.substring(0, input.length() / 2 + 1);
/* 44:41 */       endStr = input.substring(input.length() / 2 + 1);
/* 45:   */     }
/* 46:43 */     String temp = endStr + startStr;
/* 47:44 */     String secret = temp.substring(3, temp.length() - 3);
/* 48:45 */     return secret;
/* 49:   */   }
/* 50:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.MyMD5
 * JD-Core Version:    0.7.0.1
 */