/*  1:   */ package cn.com.voltronic.solar.data.bean;
/*  2:   */ 
/*  3:   */ import java.util.Date;
/*  4:   */ 
/*  5:   */ public class SynchroEnergyPlan
/*  6:   */ {
/*  7: 7 */   private String serialno = "";
/*  8: 9 */   private String cycle = "";
/*  9:   */   private int planWeek;
/* 10:   */   private int planMonth;
/* 11:   */   private Date planTime;
/* 12:   */   private int synchroTime;
/* 13:   */   
/* 14:   */   public String getSerialno()
/* 15:   */   {
/* 16:20 */     return this.serialno;
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void setSerialno(String serialno)
/* 20:   */   {
/* 21:24 */     this.serialno = serialno;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public String getCycle()
/* 25:   */   {
/* 26:28 */     return this.cycle;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setCycle(String cycle)
/* 30:   */   {
/* 31:32 */     this.cycle = cycle;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public int getPlanWeek()
/* 35:   */   {
/* 36:36 */     return this.planWeek;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public void setPlanWeek(int planWeek)
/* 40:   */   {
/* 41:40 */     this.planWeek = planWeek;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public int getPlanMonth()
/* 45:   */   {
/* 46:44 */     return this.planMonth;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public void setPlanMonth(int planMonth)
/* 50:   */   {
/* 51:48 */     this.planMonth = planMonth;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public Date getPlanTime()
/* 55:   */   {
/* 56:52 */     return this.planTime;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public void setPlanTime(Date planTime)
/* 60:   */   {
/* 61:56 */     this.planTime = planTime;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public int getSynchroTime()
/* 65:   */   {
/* 66:60 */     return this.synchroTime;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public void setSynchroTime(int synchroTime)
/* 70:   */   {
/* 71:64 */     this.synchroTime = synchroTime;
/* 72:   */   }
/* 73:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.data.bean.SynchroEnergyPlan
 * JD-Core Version:    0.7.0.1
 */