/*   1:    */ package cn.com.voltronic.solar.configure;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   4:    */ import cn.com.voltronic.solar.util.RunTools;
/*   5:    */ import java.lang.reflect.Method;
/*   6:    */ import java.util.List;
/*   7:    */ 
/*   8:    */ public class ConfigureTools
/*   9:    */ {
/*  10:    */   public static void wrapProperties(IConfigBean obj)
/*  11:    */     throws Exception
/*  12:    */   {
/*  13: 25 */     wrapProperties(obj, GlobalVariables.properties);
/*  14:    */   }
/*  15:    */   
/*  16:    */   public static void updateProperties(IConfigBean obj)
/*  17:    */     throws Exception
/*  18:    */   {
/*  19: 38 */     updateProperties(obj, GlobalVariables.properties);
/*  20:    */   }
/*  21:    */   
/*  22:    */   public static void wrapProperties(IConfigBean obj, IniProperties properties)
/*  23:    */     throws Exception
/*  24:    */   {
/*  25: 58 */     List<Method> setters = RunTools.getSetters(obj);
/*  26: 59 */     for (Method setter : setters)
/*  27:    */     {
/*  28: 60 */       Class paratype = setter.getParameterTypes()[0];
/*  29: 61 */       String keyName = setter.getName().substring(3);
/*  30:    */       
/*  31: 63 */       String strvalue = "";
/*  32: 64 */       if (obj.getSection() == null) {
/*  33: 65 */         strvalue = properties.getProperty(keyName);
/*  34:    */       } else {
/*  35: 67 */         strvalue = properties.getProperty(obj.getSection(), keyName);
/*  36:    */       }
/*  37: 70 */       if (strvalue == null) {
/*  38: 71 */         return;
/*  39:    */       }
/*  40: 73 */       Object value = null;
/*  41: 74 */       if ((paratype.equals(Boolean.TYPE)) || 
/*  42: 75 */         (paratype.equals(Boolean.class))) {
/*  43: 76 */         value = Boolean.valueOf(Boolean.parseBoolean(strvalue));
/*  44: 77 */       } else if ((paratype.equals(Integer.TYPE)) || 
/*  45: 78 */         (paratype.equals(Integer.class))) {
/*  46: 79 */         value = Integer.valueOf(Integer.parseInt(strvalue));
/*  47: 80 */       } else if ((paratype.equals(Long.TYPE)) || 
/*  48: 81 */         (paratype.equals(Long.class))) {
/*  49: 82 */         value = Long.valueOf(Long.parseLong(strvalue));
/*  50: 83 */       } else if ((paratype.equals(Float.TYPE)) || 
/*  51: 84 */         (paratype.equals(Float.class))) {
/*  52: 85 */         value = Float.valueOf(Float.parseFloat(strvalue));
/*  53: 86 */       } else if ((paratype.equals(Double.TYPE)) || 
/*  54: 87 */         (paratype.equals(Double.class))) {
/*  55: 88 */         value = Double.valueOf(Double.parseDouble(strvalue));
/*  56: 90 */       } else if (strvalue == null) {
/*  57: 91 */         value = "";
/*  58:    */       } else {
/*  59: 93 */         value = strvalue;
/*  60:    */       }
/*  61: 96 */       setter.invoke(obj, new Object[] { value });
/*  62:    */     }
/*  63:    */   }
/*  64:    */   
/*  65:    */   public static void updateProperties(IConfigBean obj, IniProperties properties)
/*  66:    */     throws Exception
/*  67:    */   {
/*  68:111 */     List<Method> getters = RunTools.getGetters(obj);
/*  69:112 */     for (Method getter : getters)
/*  70:    */     {
/*  71:113 */       Object value = getter.invoke(obj, new Object[0]);
/*  72:114 */       if (value == null) {
/*  73:115 */         value = "";
/*  74:    */       }
/*  75:118 */       String keyName = getter.getName();
/*  76:120 */       if (keyName.startsWith("is")) {
/*  77:121 */         keyName = keyName.substring(2);
/*  78:    */       } else {
/*  79:123 */         keyName = keyName.substring(3);
/*  80:    */       }
/*  81:125 */       if (obj.getSection() == null) {
/*  82:126 */         properties.setProperty(keyName, 
/*  83:127 */           String.valueOf(value));
/*  84:    */       } else {
/*  85:129 */         properties.setProperty(obj.getSection(), keyName, 
/*  86:130 */           String.valueOf(value));
/*  87:    */       }
/*  88:132 */       properties.store();
/*  89:    */     }
/*  90:    */   }
/*  91:    */   
/*  92:    */   public static void updatePropertiesAndStore(IConfigBean obj, IniProperties properties)
/*  93:    */     throws Exception
/*  94:    */   {
/*  95:137 */     updateProperties(obj, properties);
/*  96:138 */     properties.store();
/*  97:    */   }
/*  98:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.configure.ConfigureTools
 * JD-Core Version:    0.7.0.1
 */