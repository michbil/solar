/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class AutoMenuTwo
/*  7:   */ {
/*  8:   */   private AutoMenuItem item;
/*  9:   */   private List<AutoMenuItem> items;
/* 10:   */   
/* 11:   */   public AutoMenuTwo(AutoMenuItem item, List<AutoMenuItem> items)
/* 12:   */   {
/* 13:19 */     this.items = new ArrayList();
/* 14:20 */     this.item = item;
/* 15:21 */     if (items != null) {
/* 16:22 */       this.items.addAll(items);
/* 17:   */     } else {
/* 18:24 */       this.items = null;
/* 19:   */     }
/* 20:   */   }
/* 21:   */   
/* 22:   */   public AutoMenuItem getItem()
/* 23:   */   {
/* 24:29 */     return this.item;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setItem(AutoMenuItem item)
/* 28:   */   {
/* 29:33 */     this.item = item;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public List<AutoMenuItem> getItems()
/* 33:   */   {
/* 34:37 */     return this.items;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setItems(List<AutoMenuItem> items)
/* 38:   */   {
/* 39:41 */     this.items = items;
/* 40:   */   }
/* 41:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.AutoMenuTwo
 * JD-Core Version:    0.7.0.1
 */