/*  1:   */ package cn.com.voltronic.solar.control;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.util.RunTools;
/*  4:   */ 
/*  5:   */ public abstract class AbstractControlModule
/*  6:   */ {
/*  7:   */   private Object _handler;
/*  8:   */   
/*  9:   */   public AbstractControlModule(Object handler)
/* 10:   */   {
/* 11:18 */     this._handler = handler;
/* 12:   */   }
/* 13:   */   
/* 14:   */   public void setHandler(Object handler)
/* 15:   */   {
/* 16:22 */     this._handler = handler;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public Object getHandler()
/* 20:   */   {
/* 21:26 */     return this._handler;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public boolean execute(String methodcommand, Object[] paras)
/* 25:   */   {
/* 26:30 */     Object result = RunTools.runMethod(this, methodcommand, paras);
/* 27:31 */     if (result == null) {
/* 28:32 */       return false;
/* 29:   */     }
/* 30:34 */     if ((result.getClass() == Boolean.TYPE) || 
/* 31:35 */       (result.getClass() == Boolean.class)) {
/* 32:36 */       return ((Boolean)result).booleanValue();
/* 33:   */     }
/* 34:38 */     return false;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void excuteCommand(int address, int value)
/* 38:   */     throws Exception
/* 39:   */   {}
/* 40:   */   
/* 41:   */   public void excuteCommand(int address, double value)
/* 42:   */     throws Exception
/* 43:   */   {}
/* 44:   */   
/* 45:   */   public boolean excuteCommand(String command, String parmeter)
/* 46:   */     throws Exception
/* 47:   */   {
/* 48:48 */     return false;
/* 49:   */   }
/* 50:   */   
/* 51:   */   public String getFomatStr(String value, int count)
/* 52:   */   {
/* 53:52 */     if (value == null) {
/* 54:53 */       value = new String();
/* 55:   */     }
/* 56:55 */     String valueStr = value;
/* 57:56 */     int i = 0;
/* 58:57 */     int end = count - valueStr.length();
/* 59:58 */     while (i < end)
/* 60:   */     {
/* 61:59 */       valueStr = "0" + valueStr;
/* 62:60 */       i++;
/* 63:   */     }
/* 64:62 */     return valueStr;
/* 65:   */   }
/* 66:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.control.AbstractControlModule
 * JD-Core Version:    0.7.0.1
 */