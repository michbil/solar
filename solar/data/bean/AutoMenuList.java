/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.util.ArrayList;
/*  4:   */ import java.util.List;
/*  5:   */ 
/*  6:   */ public class AutoMenuList
/*  7:   */ {
/*  8:   */   private List<AutoMenuOne> menuList;
/*  9:   */   
/* 10:   */   public AutoMenuList()
/* 11:   */   {
/* 12:17 */     this.menuList = new ArrayList();
/* 13:   */   }
/* 14:   */   
/* 15:   */   public List<AutoMenuOne> getMenuList()
/* 16:   */   {
/* 17:21 */     return this.menuList;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setMenuList(List<AutoMenuOne> menuList)
/* 21:   */   {
/* 22:25 */     this.menuList.addAll(menuList);
/* 23:   */   }
/* 24:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.AutoMenuList
 * JD-Core Version:    0.7.0.1
 */