/*   1:    */ package cn.com.voltronic.solar.util;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.lang.reflect.Constructor;
/*   5:    */ import java.lang.reflect.InvocationTargetException;
/*   6:    */ import java.lang.reflect.Method;
/*   7:    */ import java.util.ArrayList;
/*   8:    */ import java.util.List;
/*   9:    */ 
/*  10:    */ public class RunTools
/*  11:    */ {
/*  12:    */   private static boolean typeEqual(Class class1, Class class2)
/*  13:    */   {
/*  14: 13 */     if (class1 == class2) {
/*  15: 14 */       return true;
/*  16:    */     }
/*  17: 16 */     Class[][] sameclass = { { Integer.TYPE, Integer.class }, 
/*  18: 17 */       { Long.TYPE, Long.class }, { Float.TYPE, Float.class }, 
/*  19: 18 */       { Double.TYPE, Double.class }, 
/*  20: 19 */       { Boolean.TYPE, Boolean.class } };
/*  21: 21 */     for (int row_i = 0; row_i < sameclass.length; row_i++) {
/*  22: 22 */       if (((class1 == sameclass[row_i][0]) || (class1 == sameclass[row_i][1])) && (
/*  23: 23 */         (class2 == sameclass[row_i][0]) || (class2 == sameclass[row_i][1]))) {
/*  24: 24 */         return true;
/*  25:    */       }
/*  26:    */     }
/*  27: 27 */     return false;
/*  28:    */   }
/*  29:    */   
/*  30:    */   private static boolean class2Extendfrom1(Class class1, Class class2)
/*  31:    */   {
/*  32: 32 */     if (class1 == Object.class) {
/*  33: 33 */       return true;
/*  34:    */     }
/*  35: 35 */     Class superclass = class2;
/*  36: 36 */     while (superclass != Object.class)
/*  37:    */     {
/*  38: 37 */       if (class1 == superclass) {
/*  39: 38 */         return true;
/*  40:    */       }
/*  41: 40 */       if (class1.isInterface()) {
/*  42: 41 */         for (Class iface : superclass.getInterfaces()) {
/*  43: 42 */           if (iface == class1) {
/*  44: 43 */             return true;
/*  45:    */           }
/*  46:    */         }
/*  47:    */       }
/*  48: 47 */       superclass = superclass.getSuperclass();
/*  49:    */     }
/*  50: 49 */     return false;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public static void logFile(String caption, String data)
/*  54:    */   {
/*  55: 60 */     System.out.println(caption + "=" + data);
/*  56:    */   }
/*  57:    */   
/*  58:    */   private static Method findMethod(Object obj, String methodName, Object[] paras)
/*  59:    */   {
/*  60: 66 */     Method method = null;
/*  61: 67 */     int paralen = 0;
/*  62: 68 */     if (paras != null) {
/*  63: 69 */       paralen = paras.length;
/*  64:    */     }
/*  65: 72 */     Method[] methods = obj.getClass().getMethods();
/*  66: 73 */     for (Method method1 : methods) {
/*  67: 74 */       if (method1.getName().equals(methodName))
/*  68:    */       {
/*  69: 75 */         Class[] ptypes = method1.getParameterTypes();
/*  70: 76 */         if (ptypes.length == paralen)
/*  71:    */         {
/*  72: 77 */           if (paralen == 0)
/*  73:    */           {
/*  74: 78 */             method = method1;
/*  75: 79 */             break;
/*  76:    */           }
/*  77: 81 */           boolean equal = true;
/*  78: 82 */           for (int index = 0; index < paralen; index++) {
/*  79: 83 */             if (!typeEqual(ptypes[index], 
/*  80: 84 */               paras[index].getClass())) {
/*  81: 85 */               if (!class2Extendfrom1(ptypes[index], 
/*  82: 86 */                 paras[index].getClass()))
/*  83:    */               {
/*  84: 87 */                 equal = false;
/*  85: 88 */                 break;
/*  86:    */               }
/*  87:    */             }
/*  88:    */           }
/*  89: 91 */           if (equal)
/*  90:    */           {
/*  91: 92 */             method = method1;
/*  92: 93 */             break;
/*  93:    */           }
/*  94:    */         }
/*  95:    */       }
/*  96:    */     }
/*  97: 99 */     return method;
/*  98:    */   }
/*  99:    */   
/* 100:    */   private static Constructor findConstructor(Class cls, Object[] paras)
/* 101:    */   {
/* 102:104 */     Constructor constructor = null;
/* 103:105 */     int paralen = paras.length;
/* 104:    */     
/* 105:107 */     Constructor[] constructors = cls.getConstructors();
/* 106:108 */     for (Constructor constructor1 : constructors)
/* 107:    */     {
/* 108:109 */       Class[] ptypes = constructor1.getParameterTypes();
/* 109:110 */       if (ptypes.length == paralen)
/* 110:    */       {
/* 111:111 */         boolean equal = true;
/* 112:112 */         for (int index = 0; index < paralen; index++) {
/* 113:114 */           if ((!typeEqual(ptypes[index], paras[index].getClass())) && 
/* 114:115 */             (!class2Extendfrom1(ptypes[index], 
/* 115:116 */             paras[index].getClass())))
/* 116:    */           {
/* 117:117 */             equal = false;
/* 118:118 */             break;
/* 119:    */           }
/* 120:    */         }
/* 121:121 */         if (equal)
/* 122:    */         {
/* 123:122 */           constructor = constructor1;
/* 124:123 */           break;
/* 125:    */         }
/* 126:    */       }
/* 127:    */     }
/* 128:127 */     return constructor;
/* 129:    */   }
/* 130:    */   
/* 131:    */   private static Object runMethod(Object obj, Method method, Object[] paras)
/* 132:    */   {
/* 133:131 */     Object result = null;
/* 134:132 */     if (method != null) {
/* 135:    */       try
/* 136:    */       {
/* 137:134 */         if (method.getParameterTypes().length == 0) {
/* 138:135 */           result = method.invoke(obj, new Object[0]);
/* 139:    */         } else {
/* 140:137 */           result = method.invoke(obj, paras);
/* 141:    */         }
/* 142:    */       }
/* 143:    */       catch (IllegalArgumentException e)
/* 144:    */       {
/* 145:140 */         e.printStackTrace();
/* 146:    */       }
/* 147:    */       catch (IllegalAccessException e)
/* 148:    */       {
/* 149:142 */         e.printStackTrace();
/* 150:    */       }
/* 151:    */       catch (InvocationTargetException e)
/* 152:    */       {
/* 153:145 */         e.printStackTrace();
/* 154:    */       }
/* 155:    */     }
/* 156:148 */     return result;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public static Object runMethod(Object obj, String methodName, Object[] paras)
/* 160:    */   {
/* 161:152 */     Method method = findMethod(obj, methodName, paras);
/* 162:153 */     return runMethod(obj, method, paras);
/* 163:    */   }
/* 164:    */   
/* 165:    */   public static Object newInstance(Class instanceclass, Object[] params)
/* 166:    */   {
/* 167:159 */     Object instance = null;
/* 168:    */     try
/* 169:    */     {
/* 170:161 */       if ((params == null) || (params.length == 0))
/* 171:    */       {
/* 172:162 */         instance = instanceclass.newInstance();
/* 173:    */       }
/* 174:    */       else
/* 175:    */       {
/* 176:164 */         Constructor con = findConstructor(instanceclass, params);
/* 177:165 */         if (con != null) {
/* 178:166 */           instance = con.newInstance(params);
/* 179:    */         }
/* 180:    */       }
/* 181:    */     }
/* 182:    */     catch (Exception e)
/* 183:    */     {
/* 184:170 */       e.printStackTrace();
/* 185:    */     }
/* 186:172 */     return instance;
/* 187:    */   }
/* 188:    */   
/* 189:    */   public static List<Method> getGetters(Object obj)
/* 190:    */   {
/* 191:176 */     List<Method> getters = new ArrayList();
/* 192:177 */     Method[] methods = obj.getClass().getMethods();
/* 193:178 */     for (Method method : methods)
/* 194:    */     {
/* 195:179 */       String getterName = method.getName();
/* 196:180 */       if (((getterName.startsWith("get")) || (getterName.startsWith("is"))) && 
/* 197:181 */         (method.getParameterTypes().length == 0) && 
/* 198:182 */         (!Void.TYPE.equals(method.getReturnType())))
/* 199:    */       {
/* 200:183 */         String setterName = "";
/* 201:184 */         if (getterName.startsWith("get")) {
/* 202:185 */           setterName = getterName.replaceFirst("get", "set");
/* 203:    */         } else {
/* 204:187 */           setterName = getterName.replaceFirst("is", "set");
/* 205:    */         }
/* 206:    */         try
/* 207:    */         {
/* 208:190 */           Method setter = obj.getClass().getMethod(setterName, 
/* 209:191 */             new Class[] { method.getReturnType() });
/* 210:192 */           if ((setter != null) && 
/* 211:193 */             (Void.TYPE.equals(setter.getReturnType()))) {
/* 212:194 */             getters.add(method);
/* 213:    */           }
/* 214:    */         }
/* 215:    */         catch (Exception localException) {}
/* 216:    */       }
/* 217:    */     }
/* 218:202 */     return getters;
/* 219:    */   }
/* 220:    */   
/* 221:    */   public static List<Method> getSetters(Object obj)
/* 222:    */   {
/* 223:206 */     List<Method> setters = new ArrayList();
/* 224:207 */     Method[] methods = obj.getClass().getMethods();
/* 225:208 */     for (Method method : methods)
/* 226:    */     {
/* 227:210 */       String setterName = method.getName();
/* 228:212 */       if ((setterName.startsWith("set")) && 
/* 229:213 */         (method.getParameterTypes().length == 1) && 
/* 230:214 */         (Void.TYPE.equals(method.getReturnType()))) {
/* 231:215 */         for (int i = 0; i < 2; i++)
/* 232:    */         {
/* 233:216 */           String getterName = "";
/* 234:217 */           if (i == 0) {
/* 235:218 */             getterName = setterName.replaceFirst("set", "get");
/* 236:    */           } else {
/* 237:220 */             getterName = setterName.replaceFirst("set", "is");
/* 238:    */           }
/* 239:    */           try
/* 240:    */           {
/* 241:223 */             Method getter = obj.getClass().getMethod(getterName, 
/* 242:224 */               new Class[0]);
/* 243:225 */             if ((getter != null) && 
/* 244:226 */               (getter.getReturnType().equals(
/* 245:227 */               method.getParameterTypes()[0]))) {
/* 246:228 */               setters.add(method);
/* 247:    */             }
/* 248:    */           }
/* 249:    */           catch (Exception localException) {}
/* 250:    */         }
/* 251:    */       }
/* 252:    */     }
/* 253:237 */     return setters;
/* 254:    */   }
/* 255:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.RunTools
 * JD-Core Version:    0.7.0.1
 */