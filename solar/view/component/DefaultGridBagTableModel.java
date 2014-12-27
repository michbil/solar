/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import java.awt.Point;
/*   4:    */ import java.util.Arrays;
/*   5:    */ import java.util.List;
/*   6:    */ import java.util.Vector;
/*   7:    */ import javax.swing.event.TableModelEvent;
/*   8:    */ import javax.swing.event.TableModelListener;
/*   9:    */ import javax.swing.table.AbstractTableModel;
/*  10:    */ 
/*  11:    */ public class DefaultGridBagTableModel
/*  12:    */   implements GridBagModel, TableModelListener
/*  13:    */ {
/*  14:    */   protected AbstractTableModel model;
/*  15:    */   protected List<List<Point>> gridInfo;
/*  16:    */   
/*  17:    */   DefaultGridBagTableModel(AbstractTableModel model)
/*  18:    */   {
/*  19: 24 */     this.gridInfo = new Vector();
/*  20: 25 */     setTableModel(model);
/*  21:    */   }
/*  22:    */   
/*  23:    */   public void setTableModel(AbstractTableModel model)
/*  24:    */   {
/*  25: 29 */     if ((model != null) && (model != this.model))
/*  26:    */     {
/*  27: 30 */       if (this.model != null) {
/*  28: 31 */         this.model.removeTableModelListener(this);
/*  29:    */       }
/*  30: 34 */       model.removeTableModelListener(this);
/*  31: 35 */       model.addTableModelListener(this);
/*  32: 36 */       this.model = model;
/*  33: 37 */       clearMergence();
/*  34:    */     }
/*  35:    */   }
/*  36:    */   
/*  37:    */   public void clearMergence()
/*  38:    */   {
/*  39: 42 */     if (this.gridInfo == null) {
/*  40: 43 */       this.gridInfo = new Vector();
/*  41:    */     } else {
/*  42: 45 */       this.gridInfo.clear();
/*  43:    */     }
/*  44: 47 */     if (this.model == null) {
/*  45: 48 */       return;
/*  46:    */     }
/*  47: 51 */     int row = this.model.getRowCount();
/*  48:    */     do
/*  49:    */     {
/*  50: 52 */       List<Point> infos = new Vector();
/*  51: 53 */       this.gridInfo.add(infos);
/*  52: 54 */       int col = this.model.getColumnCount();
/*  53:    */       do
/*  54:    */       {
/*  55: 55 */         infos.add(getDefaultPoint());col--;
/*  56: 54 */       } while (col >= 0);
/*  57: 51 */       row--;
/*  58: 51 */     } while (row >= 0);
/*  59:    */   }
/*  60:    */   
/*  61:    */   public Point getDefaultPoint()
/*  62:    */   {
/*  63: 61 */     return new Point(1, 1);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public boolean canMergeCells(int[] rows, int[] columns)
/*  67:    */   {
/*  68: 66 */     if ((rows == null) || (columns == null)) {
/*  69: 66 */       return false;
/*  70:    */     }
/*  71: 67 */     Arrays.sort(rows);
/*  72: 68 */     for (int index = 0; index < rows.length - 1; index++) {
/*  73: 69 */       if (rows[(index + 1)] - rows[index] > 1) {
/*  74: 70 */         return false;
/*  75:    */       }
/*  76:    */     }
/*  77: 72 */     Arrays.sort(columns);
/*  78: 73 */     for (int index = 0; index < columns.length - 1; index++) {
/*  79: 74 */       if (columns[(index + 1)] - columns[index] > 1) {
/*  80: 75 */         return false;
/*  81:    */       }
/*  82:    */     }
/*  83: 77 */     return true;
/*  84:    */   }
/*  85:    */   
/*  86:    */   public int getCellState(int row, int column)
/*  87:    */   {
/*  88: 82 */     Point grid = getGrid(row, column);
/*  89: 83 */     if (grid == null) {
/*  90: 83 */       return 0;
/*  91:    */     }
/*  92: 84 */     if ((grid.x > 1) || (grid.y > 1)) {
/*  93: 85 */       return 1;
/*  94:    */     }
/*  95: 86 */     if ((grid.x <= 0) || (grid.y <= 0)) {
/*  96: 87 */       return -1;
/*  97:    */     }
/*  98: 88 */     return 0;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public int getColumnGrid(int row, int column)
/* 102:    */   {
/* 103: 93 */     if ((this.gridInfo != null) && (row >= 0) && (row < this.gridInfo.size()))
/* 104:    */     {
/* 105: 94 */       List<Point> gridRow = (List)this.gridInfo.get(row);
/* 106: 95 */       if ((gridRow != null) && (column >= 0) && (column < gridRow.size()))
/* 107:    */       {
/* 108: 96 */         Point point = (Point)gridRow.get(column);
/* 109: 97 */         if (point != null) {
/* 110: 98 */           return point.x;
/* 111:    */         }
/* 112:    */       }
/* 113:    */     }
/* 114:101 */     return 1;
/* 115:    */   }
/* 116:    */   
/* 117:    */   public Point getGrid(int row, int column)
/* 118:    */   {
/* 119:106 */     if ((this.gridInfo != null) && (row >= 0) && (row < this.gridInfo.size()))
/* 120:    */     {
/* 121:107 */       List<Point> gridRow = (List)this.gridInfo.get(row);
/* 122:108 */       if ((gridRow != null) && (column >= 0) && (column < gridRow.size())) {
/* 123:109 */         return (Point)gridRow.get(column);
/* 124:    */       }
/* 125:    */     }
/* 126:112 */     return getDefaultPoint();
/* 127:    */   }
/* 128:    */   
/* 129:    */   public int getRowGrid(int row, int column)
/* 130:    */   {
/* 131:117 */     if ((this.gridInfo != null) && (row >= 0) && (row < this.gridInfo.size()))
/* 132:    */     {
/* 133:118 */       List<Point> gridRow = (List)this.gridInfo.get(row);
/* 134:119 */       if ((gridRow != null) && (column >= 0) && (column < gridRow.size()))
/* 135:    */       {
/* 136:120 */         Point point = (Point)gridRow.get(column);
/* 137:121 */         if (point != null) {
/* 138:122 */           return point.y;
/* 139:    */         }
/* 140:    */       }
/* 141:    */     }
/* 142:125 */     return 1;
/* 143:    */   }
/* 144:    */   
/* 145:    */   protected boolean setGrid(int row, int column, Point grid)
/* 146:    */   {
/* 147:129 */     if ((this.gridInfo != null) && (row >= 0) && (row < this.gridInfo.size()))
/* 148:    */     {
/* 149:130 */       List<Point> gridRow = (List)this.gridInfo.get(row);
/* 150:131 */       if ((gridRow != null) && (column >= 0) && (column < gridRow.size()))
/* 151:    */       {
/* 152:132 */         Point point = (Point)gridRow.get(column);
/* 153:133 */         if (point != null) {
/* 154:134 */           point.setLocation(grid);
/* 155:    */         } else {
/* 156:137 */           gridRow.set(column, grid.getLocation());
/* 157:    */         }
/* 158:139 */         return true;
/* 159:    */       }
/* 160:    */     }
/* 161:142 */     return false;
/* 162:    */   }
/* 163:    */   
/* 164:    */   public boolean spliteCellAt(int row, int column)
/* 165:    */   {
/* 166:147 */     if ((this.gridInfo != null) && (row >= 0) && (row < this.gridInfo.size()))
/* 167:    */     {
/* 168:148 */       List<Point> gridRow = (List)this.gridInfo.get(row);
/* 169:149 */       if ((gridRow != null) && (column >= 0) && (column < gridRow.size()))
/* 170:    */       {
/* 171:150 */         Point point = (Point)gridRow.get(column);
/* 172:151 */         if (point != null)
/* 173:    */         {
/* 174:152 */           point = point.getLocation();
/* 175:153 */           for (int a = 0; a < point.y; a++) {
/* 176:154 */             for (int b = 0; b < point.x; b++) {
/* 177:155 */               setGrid(row + a, column + b, getDefaultPoint());
/* 178:    */             }
/* 179:    */           }
/* 180:    */         }
/* 181:    */         else
/* 182:    */         {
/* 183:160 */           gridRow.set(column, getDefaultPoint());
/* 184:    */         }
/* 185:162 */         return true;
/* 186:    */       }
/* 187:    */     }
/* 188:165 */     return false;
/* 189:    */   }
/* 190:    */   
/* 191:    */   public void tableChanged(TableModelEvent e) {}
/* 192:    */   
/* 193:    */   public boolean mergeCells(int[] rows, int[] columns)
/* 194:    */   {
/* 195:178 */     if (!canMergeCells(rows, columns)) {
/* 196:179 */       return false;
/* 197:    */     }
/* 198:180 */     Arrays.sort(rows);
/* 199:181 */     Arrays.sort(columns);
/* 200:182 */     return mergeCells(rows[0], rows[(rows.length - 1)], columns[0], columns[(columns.length - 1)]);
/* 201:    */   }
/* 202:    */   
/* 203:    */   public boolean mergeCells(int startRow, int endRow, int startColumn, int endColumn)
/* 204:    */   {
/* 205:187 */     setGrid(startRow, startColumn, new Point(endColumn - startColumn + 1, endRow - startRow + 1));
/* 206:188 */     for (int row = startRow; row <= endRow; row++) {
/* 207:189 */       for (int col = startColumn; col <= endColumn; col++) {
/* 208:190 */         if ((row != startRow) || (col != startColumn)) {
/* 209:193 */           setGrid(row, col, new Point(-1, -1));
/* 210:    */         }
/* 211:    */       }
/* 212:    */     }
/* 213:196 */     return true;
/* 214:    */   }
/* 215:    */   
/* 216:    */   public String toString()
/* 217:    */   {
/* 218:200 */     if (this.gridInfo == null) {
/* 219:201 */       return "";
/* 220:    */     }
/* 221:202 */     StringBuffer sb = new StringBuffer();
/* 222:203 */     for (List<Point> rowInfo : this.gridInfo)
/* 223:    */     {
/* 224:204 */       for (Point grid : rowInfo) {
/* 225:205 */         sb.append("[" + grid.x + "," + grid.y + "], ");
/* 226:    */       }
/* 227:207 */       sb.append("\n");
/* 228:    */     }
/* 229:209 */     return sb.toString();
/* 230:    */   }
/* 231:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.DefaultGridBagTableModel
 * JD-Core Version:    0.7.0.1
 */