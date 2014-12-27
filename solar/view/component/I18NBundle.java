/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import java.util.List;
/*   4:    */ import java.util.Locale;
/*   5:    */ import java.util.ResourceBundle;
/*   6:    */ 
/*   7:    */ public class I18NBundle
/*   8:    */ {
/*   9: 14 */   private static final Locale locale_pl = new Locale("pl", "PL");
/*  10: 15 */   private static final Locale locale_pt = new Locale("pt", "PT");
/*  11: 16 */   private static final Locale locale_rus = new Locale("ru", "RU");
/*  12: 17 */   private static final Locale locale_sp = new Locale("es", "ES");
/*  13: 18 */   private static final Locale locale_tur = new Locale("tr", "TR");
/*  14: 19 */   private static final Locale locale_ukr = new Locale("uk", "UA");
/*  15: 20 */   private static final Locale locale_cs = new Locale("cs", "CS");
/*  16: 22 */   public static ResourceBundle resource = ResourceBundle.getBundle("messages", Locale.US);
/*  17: 23 */   private static ResourceBundle resource_pl = ResourceBundle.getBundle("messages", locale_pl);
/*  18: 24 */   private static ResourceBundle resource_us = ResourceBundle.getBundle("messages", Locale.US);
/*  19: 25 */   private static ResourceBundle resource_cn = ResourceBundle.getBundle("messages", Locale.SIMPLIFIED_CHINESE);
/*  20: 26 */   private static ResourceBundle resource_de = ResourceBundle.getBundle("messages", Locale.GERMANY);
/*  21: 27 */   private static ResourceBundle resource_fr = ResourceBundle.getBundle("messages", Locale.FRANCE);
/*  22: 28 */   private static ResourceBundle resource_it = ResourceBundle.getBundle("messages", Locale.ITALY);
/*  23: 29 */   private static ResourceBundle resource_pt = ResourceBundle.getBundle("messages", locale_pt);
/*  24: 30 */   private static ResourceBundle resource_ru = ResourceBundle.getBundle("messages", locale_rus);
/*  25: 31 */   private static ResourceBundle resource_sp = ResourceBundle.getBundle("messages", locale_sp);
/*  26: 32 */   private static ResourceBundle resource_tr = ResourceBundle.getBundle("messages", locale_tur);
/*  27: 33 */   private static ResourceBundle resource_uk = ResourceBundle.getBundle("messages", locale_ukr);
/*  28: 34 */   private static ResourceBundle resource_cs = ResourceBundle.getBundle("messages", locale_cs);
/*  29: 35 */   private static ResourceBundle resource_tw = ResourceBundle.getBundle("messages", Locale.TRADITIONAL_CHINESE);
/*  30:    */   
/*  31:    */   public String getString(String key)
/*  32:    */   {
/*  33:    */     try
/*  34:    */     {
/*  35: 39 */       return resource.getString(key);
/*  36:    */     }
/*  37:    */     catch (Exception e) {}
/*  38: 41 */     return key;
/*  39:    */   }
/*  40:    */   
/*  41:    */   public static void changeCN()
/*  42:    */   {
/*  43: 47 */     resource = resource_cn;
/*  44: 48 */     change();
/*  45:    */   }
/*  46:    */   
/*  47:    */   public static void changeDE()
/*  48:    */   {
/*  49: 52 */     resource = resource_de;
/*  50: 53 */     change();
/*  51:    */   }
/*  52:    */   
/*  53:    */   public static void changeUS()
/*  54:    */   {
/*  55: 57 */     resource = resource_us;
/*  56: 58 */     change();
/*  57:    */   }
/*  58:    */   
/*  59:    */   public static void changeIT()
/*  60:    */   {
/*  61: 62 */     resource = resource_it;
/*  62: 63 */     change();
/*  63:    */   }
/*  64:    */   
/*  65:    */   public static void changePL()
/*  66:    */   {
/*  67: 67 */     resource = resource_pl;
/*  68: 68 */     change();
/*  69:    */   }
/*  70:    */   
/*  71:    */   public static void changePT()
/*  72:    */   {
/*  73: 72 */     resource = resource_pt;
/*  74: 73 */     change();
/*  75:    */   }
/*  76:    */   
/*  77:    */   public static void changeRUS()
/*  78:    */   {
/*  79: 77 */     resource = resource_ru;
/*  80: 78 */     change();
/*  81:    */   }
/*  82:    */   
/*  83:    */   public static void changeSP()
/*  84:    */   {
/*  85: 82 */     resource = resource_sp;
/*  86: 83 */     change();
/*  87:    */   }
/*  88:    */   
/*  89:    */   public static void changeTUR()
/*  90:    */   {
/*  91: 87 */     resource = resource_tr;
/*  92: 88 */     change();
/*  93:    */   }
/*  94:    */   
/*  95:    */   public static void changeUKR()
/*  96:    */   {
/*  97: 92 */     resource = resource_uk;
/*  98: 93 */     change();
/*  99:    */   }
/* 100:    */   
/* 101:    */   public static void changeFR()
/* 102:    */   {
/* 103: 97 */     resource = resource_fr;
/* 104: 98 */     change();
/* 105:    */   }
/* 106:    */   
/* 107:    */   public static void changeTW()
/* 108:    */   {
/* 109:102 */     resource = resource_tw;
/* 110:103 */     change();
/* 111:    */   }
/* 112:    */   
/* 113:    */   public static void changeCS()
/* 114:    */   {
/* 115:107 */     resource = resource_cs;
/* 116:108 */     change();
/* 117:    */   }
/* 118:    */   
/* 119:    */   private static void change()
/* 120:    */   {
/* 121:112 */     Object[] arr = I18NListener.compList.toArray();
/* 122:113 */     Object[] arrayOfObject1 = arr;int j = arr.length;
/* 123:113 */     for (int i = 0; i < j; i++)
/* 124:    */     {
/* 125:113 */       Object obj = arrayOfObject1[i];
/* 126:114 */       I18NListener i18n = (I18NListener)obj;
/* 127:115 */       if (i18n != null) {
/* 128:116 */         i18n.changeLang();
/* 129:    */       }
/* 130:    */     }
/* 131:    */   }
/* 132:    */   
/* 133:    */   public static void main(String[] args) {}
/* 134:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.I18NBundle
 * JD-Core Version:    0.7.0.1
 */