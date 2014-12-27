/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import java.awt.Component;
/*  5:   */ import java.util.ArrayList;
/*  6:   */ import java.util.List;
/*  7:   */ import javax.swing.JTabbedPane;
/*  8:   */ 
/*  9:   */ public class AATabbedPane
/* 10:   */   extends JTabbedPane
/* 11:   */   implements I18NListener
/* 12:   */ {
/* 13:   */   private static final long serialVersionUID = -2692244574252234157L;
/* 14:20 */   private String key = "";
/* 15:21 */   public Component component = null;
/* 16:22 */   private List<String> titleList = new ArrayList();
/* 17:   */   
/* 18:   */   public void addTab(String title, Component component)
/* 19:   */   {
/* 20:26 */     this.key = title;
/* 21:27 */     this.component = component;
/* 22:28 */     if (!this.titleList.contains(this.key)) {
/* 23:29 */       this.titleList.add(this.key);
/* 24:   */     }
/* 25:   */     try
/* 26:   */     {
/* 27:32 */       super.addTab(bd.getString(this.key), component);
/* 28:   */     }
/* 29:   */     catch (Exception e)
/* 30:   */     {
/* 31:34 */       super.addTab(title, component);
/* 32:   */     }
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void removeAll()
/* 36:   */   {
/* 37:41 */     this.titleList = new ArrayList();
/* 38:42 */     super.removeAll();
/* 39:   */   }
/* 40:   */   
/* 41:   */   public AATabbedPane()
/* 42:   */   {
/* 43:46 */     compList.add(this);
/* 44:47 */     setFont(font12);
/* 45:48 */     setForeground(Color.white);
/* 46:   */   }
/* 47:   */   
/* 48:   */   public void changeLang()
/* 49:   */   {
/* 50:52 */     for (int i = 0; i < this.titleList.size(); i++) {
/* 51:53 */       setTitleAt(i, bd.getString((String)this.titleList.get(i)));
/* 52:   */     }
/* 53:   */   }
/* 54:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AATabbedPane
 * JD-Core Version:    0.7.0.1
 */