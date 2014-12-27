/*  1:   */ package cn.com.voltronic.solar.util;
/*  2:   */ 
/*  3:   */ public class UPSCmdutils
/*  4:   */ {
/*  5:   */   private static final String CMD_SPLIT = "#";
/*  6:   */   
/*  7:   */   public static String cmdtoString(String portId, String command)
/*  8:   */   {
/*  9:12 */     return "sendCommand#" + portId + "#" + command;
/* 10:   */   }
/* 11:   */   
/* 12:   */   public static String actiontoString(String action, String para)
/* 13:   */   {
/* 14:15 */     return action + "#" + para;
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static String[] cmdStringtoArray(String cmdString)
/* 18:   */   {
/* 19:19 */     return cmdString.split("#");
/* 20:   */   }
/* 21:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.UPSCmdutils
 * JD-Core Version:    0.7.0.1
 */