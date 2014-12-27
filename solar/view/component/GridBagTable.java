/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Component;
/*   5:    */ import java.awt.Point;
/*   6:    */ import java.awt.Rectangle;
/*   7:    */ import java.util.Enumeration;
/*   8:    */ import java.util.EventObject;
/*   9:    */ import java.util.Hashtable;
/*  10:    */ import javax.swing.DefaultCellEditor;
/*  11:    */ import javax.swing.JTable;
/*  12:    */ import javax.swing.SwingUtilities;
/*  13:    */ import javax.swing.event.TableModelEvent;
/*  14:    */ import javax.swing.table.AbstractTableModel;
/*  15:    */ import javax.swing.table.JTableHeader;
/*  16:    */ import javax.swing.table.TableColumn;
/*  17:    */ import javax.swing.table.TableColumnModel;
/*  18:    */ 
/*  19:    */ public class GridBagTable
/*  20:    */   extends JTable
/*  21:    */ {
/*  22:    */   private static final long serialVersionUID = -2795793946785144254L;
/*  23:    */   GridBagModel gridBagModel;
/*  24:    */   
/*  25:    */   public GridBagModel getGridBagModel()
/*  26:    */   {
/*  27: 24 */     return this.gridBagModel;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public void setGridBagModel(GridBagModel gridBagModel)
/*  31:    */   {
/*  32: 28 */     if ((gridBagModel != null) && (gridBagModel != this.gridBagModel)) {
/*  33: 29 */       this.gridBagModel = gridBagModel;
/*  34:    */     }
/*  35:    */   }
/*  36:    */   
/*  37:    */   public GridBagTable(AbstractTableModel dm)
/*  38:    */   {
/*  39: 33 */     super(dm);
/*  40: 34 */     getTableHeader().setReorderingAllowed(false);
/*  41: 35 */     this.gridBagModel = new DefaultGridBagTableModel(dm);
/*  42:    */     
/*  43: 37 */     getTableHeader().setForeground(Color.white);
/*  44:    */   }
/*  45:    */   
/*  46:    */   public void clearMergence()
/*  47:    */   {
/*  48: 41 */     if (this.gridBagModel != null) {
/*  49: 42 */       this.gridBagModel.clearMergence();
/*  50:    */     }
/*  51:    */   }
/*  52:    */   
/*  53:    */   private void updateSubComponentUI(Object componentShell)
/*  54:    */   {
/*  55: 47 */     if (componentShell == null) {
/*  56: 48 */       return;
/*  57:    */     }
/*  58: 50 */     Component component = null;
/*  59: 51 */     if ((componentShell instanceof Component)) {
/*  60: 52 */       component = (Component)componentShell;
/*  61:    */     }
/*  62: 54 */     if ((componentShell instanceof DefaultCellEditor)) {
/*  63: 55 */       component = ((DefaultCellEditor)componentShell).getComponent();
/*  64:    */     }
/*  65: 58 */     if (component != null) {
/*  66: 59 */       SwingUtilities.updateComponentTreeUI(component);
/*  67:    */     }
/*  68:    */   }
/*  69:    */   
/*  70:    */   public void updateUI()
/*  71:    */   {
/*  72: 66 */     TableColumnModel cm = getColumnModel();
/*  73: 67 */     for (int column = 0; column < cm.getColumnCount(); column++)
/*  74:    */     {
/*  75: 68 */       TableColumn aColumn = cm.getColumn(column);
/*  76: 69 */       updateSubComponentUI(aColumn.getCellRenderer());
/*  77: 70 */       updateSubComponentUI(aColumn.getCellEditor());
/*  78: 71 */       updateSubComponentUI(aColumn.getHeaderRenderer());
/*  79:    */     }
/*  80: 75 */     Enumeration defaultRenderers = this.defaultRenderersByColumnClass.elements();
/*  81: 76 */     while (defaultRenderers.hasMoreElements()) {
/*  82: 77 */       updateSubComponentUI(defaultRenderers.nextElement());
/*  83:    */     }
/*  84: 81 */     Enumeration defaultEditors = this.defaultEditorsByColumnClass.elements();
/*  85: 82 */     while (defaultEditors.hasMoreElements()) {
/*  86: 83 */       updateSubComponentUI(defaultEditors.nextElement());
/*  87:    */     }
/*  88: 87 */     if ((this.tableHeader != null) && (this.tableHeader.getParent() == null)) {
/*  89: 88 */       this.tableHeader.updateUI();
/*  90:    */     }
/*  91: 90 */     setUI(new GridBagTableUI());
/*  92:    */   }
/*  93:    */   
/*  94:    */   public Rectangle getGridCellRect(int row, int column, boolean includeSpacing)
/*  95:    */   {
/*  96: 94 */     return super.getCellRect(row, column, includeSpacing);
/*  97:    */   }
/*  98:    */   
/*  99:    */   public Rectangle getCellRect(int row, int column, boolean includeSpacing)
/* 100:    */   {
/* 101: 98 */     Rectangle cellRect = super.getCellRect(row, column, includeSpacing);
/* 102: 99 */     int cols = this.gridBagModel.getColumnGrid(row, column);
/* 103:100 */     TableColumnModel cm = getColumnModel();
/* 104:101 */     for (int n = 1; n < cols; n++) {
/* 105:102 */       cellRect.width += cm.getColumn(column + n).getWidth();
/* 106:    */     }
/* 107:103 */     int rows = this.gridBagModel.getRowGrid(row, column);
/* 108:104 */     for (int n = 1; n < rows; n++) {
/* 109:105 */       cellRect.height += getRowHeight(row + n);
/* 110:    */     }
/* 111:106 */     return cellRect;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public void tableChanged(TableModelEvent e)
/* 115:    */   {
/* 116:110 */     super.tableChanged(e);
/* 117:    */   }
/* 118:    */   
/* 119:    */   public boolean mergeCells(int startRow, int endRow, int startColumn, int endColumn)
/* 120:    */   {
/* 121:115 */     if (this.gridBagModel.mergeCells(startRow, endRow, startColumn, endColumn))
/* 122:    */     {
/* 123:116 */       repaint();
/* 124:117 */       return true;
/* 125:    */     }
/* 126:119 */     return false;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public boolean mergeCells(int[] rows, int[] columns)
/* 130:    */   {
/* 131:123 */     if (this.gridBagModel.mergeCells(rows, columns))
/* 132:    */     {
/* 133:124 */       repaint();
/* 134:125 */       return true;
/* 135:    */     }
/* 136:127 */     return false;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public boolean spliteCellAt(int row, int column)
/* 140:    */   {
/* 141:131 */     if (this.gridBagModel.spliteCellAt(row, column))
/* 142:    */     {
/* 143:132 */       repaint();
/* 144:133 */       return true;
/* 145:    */     }
/* 146:135 */     return false;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
/* 150:    */   {
/* 151:139 */     if (this.gridBagModel.getCellState(rowIndex, columnIndex) != -1) {
/* 152:140 */       super.changeSelection(rowIndex, columnIndex, toggle, extend);
/* 153:    */     }
/* 154:141 */     int rowraw = rowIndex;
/* 155:143 */     for (int col = 0; col < getColumnCount(); col++)
/* 156:    */     {
/* 157:144 */       int status = this.gridBagModel.getCellState(rowIndex, col);
/* 158:145 */       if ((status == -1) || (status == 1))
/* 159:    */       {
/* 160:146 */         columnIndex = col;
/* 161:147 */         break;
/* 162:    */       }
/* 163:    */     }
/* 164:152 */     for (int row = rowIndex; row >= 0; row--) {
/* 165:153 */       for (int col = columnIndex; col >= 0; col--)
/* 166:    */       {
/* 167:154 */         Point p = this.gridBagModel.getGrid(row, col);
/* 168:155 */         if ((col + p.x > columnIndex) && (row + p.y > rowIndex))
/* 169:    */         {
/* 170:156 */           rowIndex = row;
/* 171:157 */           columnIndex = col;
/* 172:158 */           break;
/* 173:    */         }
/* 174:    */       }
/* 175:    */     }
/* 176:163 */     for (int row = rowIndex + 1; row < getRowCount(); row++)
/* 177:    */     {
/* 178:164 */       if (this.gridBagModel.getCellState(row, columnIndex) != -1) {
/* 179:    */         break;
/* 180:    */       }
/* 181:165 */       rowraw = row;
/* 182:    */     }
/* 183:171 */     super.changeSelection(rowIndex, columnIndex, toggle, extend);
/* 184:172 */     if (rowraw != rowIndex) {
/* 185:173 */       super.changeSelection(rowraw, 0, toggle, true);
/* 186:    */     }
/* 187:176 */     repaint();
/* 188:    */   }
/* 189:    */   
/* 190:    */   public boolean editCellAt(int rowIndex, int columnIndex, EventObject e)
/* 191:    */   {
/* 192:180 */     if (this.gridBagModel.getCellState(rowIndex, columnIndex) != -1) {
/* 193:181 */       return super.editCellAt(rowIndex, columnIndex, e);
/* 194:    */     }
/* 195:183 */     for (int row = rowIndex; row >= 0; row--) {
/* 196:184 */       for (int col = columnIndex; col >= 0; col--)
/* 197:    */       {
/* 198:185 */         Point p = this.gridBagModel.getGrid(row, col);
/* 199:186 */         if ((col + p.x > columnIndex) && (row + p.y > rowIndex))
/* 200:    */         {
/* 201:187 */           rowIndex = row;
/* 202:188 */           columnIndex = col;
/* 203:189 */           break;
/* 204:    */         }
/* 205:    */       }
/* 206:    */     }
/* 207:193 */     return super.editCellAt(rowIndex, columnIndex, e);
/* 208:    */   }
/* 209:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.GridBagTable
 * JD-Core Version:    0.7.0.1
 */