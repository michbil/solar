/*  1:   */ package cn.com.voltronic.solar.system;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*  4:   */ import cn.com.voltronic.solar.dao.IDao;
/*  5:   */ import java.lang.reflect.Constructor;
/*  6:   */ import java.util.HashMap;
/*  7:   */ 
/*  8:   */ public class DaoManager
/*  9:   */ {
/* 10:12 */   private static HashMap<Class, Class> _mapping = new HashMap();
/* 11:   */   
/* 12:   */   public static void registerDAO(Class beanbag, Class daoclass)
/* 13:   */   {
/* 14:16 */     _mapping.put(beanbag, daoclass);
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static IDao getDaoInstance(BeanBag bag)
/* 18:   */   {
/* 19:21 */     IDao instance = null;
/* 20:22 */     if (_mapping.containsKey(bag.getClass())) {
/* 21:   */       try
/* 22:   */       {
/* 23:24 */         Constructor con = ((Class)_mapping.get(bag.getClass())).getConstructor(new Class[0]);
/* 24:25 */         if (con != null)
/* 25:   */         {
/* 26:26 */           instance = (IDao)con.newInstance(new Object[0]);
/* 27:27 */           instance.setBeanBag(bag);
/* 28:   */         }
/* 29:   */       }
/* 30:   */       catch (Exception e)
/* 31:   */       {
/* 32:31 */         e.printStackTrace();
/* 33:   */       }
/* 34:   */     }
/* 35:34 */     return instance;
/* 36:   */   }
/* 37:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.system.DaoManager
 * JD-Core Version:    0.7.0.1
 */