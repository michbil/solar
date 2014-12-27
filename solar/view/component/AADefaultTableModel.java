/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.util.List;
/*  4:   */ import javax.swing.table.DefaultTableModel;
/*  5:   */ 
/*  6:   */ public class AADefaultTableModel
/*  7:   */   extends DefaultTableModel
/*  8:   */   implements I18NListener
/*  9:   */ {
/* 10:   */   private static final long serialVersionUID = -6735830642455513641L;
/* 11:   */   private Object[][] object;
/* 12:   */   private Object[] arr;
/* 13:   */   
/* 14:   */   public AADefaultTableModel()
/* 15:   */   {
/* 16:20 */     compList.add(this);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public AADefaultTableModel(Object[][] dataVector, Object[] columnIdentifiers)
/* 20:   */   {
/* 21:25 */     this.object = dataVector;
/* 22:26 */     this.arr = columnIdentifiers;
/* 23:27 */     setDataVector(dataVector, columnIdentifiers);
/* 24:28 */     compList.add(this);
/* 25:   */   }
/* 26:   */   
/* 27:   */   public boolean isCellEditable(int row, int column)
/* 28:   */   {
/* 29:34 */     return false;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void setDataVector(Object[][] dataVector, Object[] columnIdentifiers)
/* 33:   */   {
/* 34:   */     try
/* 35:   */     {
/* 36:40 */       this.object = dataVector;
/* 37:41 */       this.arr = columnIdentifiers;
/* 38:42 */       Object[][] obj = (Object[][])null;
/* 39:   */       try
/* 40:   */       {
/* 41:44 */         obj = new Object[dataVector.length][columnIdentifiers.length];
/* 42:45 */         for (int k = 0; k < dataVector.length; k++) {
/* 43:46 */           for (int h = 0; h < columnIdentifiers.length; h++)
/* 44:   */           {
/* 45:47 */             Object str = dataVector[k][h];
/* 46:48 */             if (str != null) {
/* 47:49 */               obj[k][h] = bd.getString(str.toString());
/* 48:   */             }
/* 49:   */           }
/* 50:   */         }
/* 51:   */       }
/* 52:   */       catch (Exception ex)
/* 53:   */       {
/* 54:54 */         obj = dataVector;
/* 55:55 */         ex.printStackTrace();
/* 56:   */       }
/* 57:57 */       Object[] str = new Object[columnIdentifiers.length];
/* 58:58 */       for (int i = 0; i < columnIdentifiers.length; i++) {
/* 59:59 */         str[i] = bd.getString(columnIdentifiers[i].toString());
/* 60:   */       }
/* 61:61 */       super.setDataVector(obj, str);
/* 62:   */     }
/* 63:   */     catch (Exception ex)
/* 64:   */     {
/* 65:63 */       super.setDataVector(dataVector, columnIdentifiers);
/* 66:   */     }
/* 67:   */   }
/* 68:   */   
/* 69:   */   public void changeLang()
/* 70:   */   {
/* 71:70 */     setDataVector(this.object, this.arr);
/* 72:   */   }
/* 73:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AADefaultTableModel
 * JD-Core Version:    0.7.0.1
 */