/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import java.awt.Font;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ 
/*  8:   */ public abstract interface I18NListener
/*  9:   */ {
/* 10:13 */   public static final Font font12 = new Font(null, 0, 12);
/* 11:14 */   public static final Font font13 = new Font(null, 0, 13);
/* 12:15 */   public static final Font font14 = new Font(null, 0, 14);
/* 13:17 */   public static final Color bgColor = new Color(102, 102, 102);
/* 14:18 */   public static final Color fontColor = new Color(255, 255, 255);
/* 15:20 */   public static final Color selectColor = new Color(255, 135, 135);
/* 16:22 */   public static final I18NBundle bd = new I18NBundle();
/* 17:23 */   public static final List<I18NListener> compList = new ArrayList();
/* 18:   */   
/* 19:   */   public abstract void changeLang();
/* 20:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.I18NListener
 * JD-Core Version:    0.7.0.1
 */