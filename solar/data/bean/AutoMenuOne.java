/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class AutoMenuOne
/*  7:   */ {
/*  8:14 */   private String label = "";
/*  9:   */   private int data;
/* 10:   */   private List<AutoMenuTwo> items;
/* 11:   */   
/* 12:   */   public AutoMenuOne(String label, int data, List<AutoMenuTwo> items)
/* 13:   */   {
/* 14:19 */     this.items = new ArrayList();
/* 15:20 */     this.label = label;
/* 16:21 */     this.data = data;
/* 17:22 */     this.items.addAll(items);
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String getLabel()
/* 21:   */   {
/* 22:26 */     return this.label;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void setLabel(String label)
/* 26:   */   {
/* 27:30 */     this.label = label;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public int getData()
/* 31:   */   {
/* 32:34 */     return this.data;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public void setData(int data)
/* 36:   */   {
/* 37:38 */     this.data = data;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public List<AutoMenuTwo> getItems()
/* 41:   */   {
/* 42:42 */     return this.items;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public void setItems(List<AutoMenuTwo> items)
/* 46:   */   {
/* 47:46 */     this.items = items;
/* 48:   */   }
/* 49:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.AutoMenuOne
 * JD-Core Version:    0.7.0.1
 */