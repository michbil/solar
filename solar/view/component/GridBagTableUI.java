/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Component;
/*   5:    */ import java.awt.ComponentOrientation;
/*   6:    */ import java.awt.Container;
/*   7:    */ import java.awt.Dimension;
/*   8:    */ import java.awt.Graphics;
/*   9:    */ import java.awt.Point;
/*  10:    */ import java.awt.Rectangle;
/*  11:    */ import java.util.Enumeration;
/*  12:    */ import javax.swing.BorderFactory;
/*  13:    */ import javax.swing.CellRendererPane;
/*  14:    */ import javax.swing.JComponent;
/*  15:    */ import javax.swing.JTable;
/*  16:    */ import javax.swing.JTable.DropLocation;
/*  17:    */ import javax.swing.UIManager;
/*  18:    */ import javax.swing.plaf.basic.BasicTableUI;
/*  19:    */ import javax.swing.table.JTableHeader;
/*  20:    */ import javax.swing.table.TableCellRenderer;
/*  21:    */ import javax.swing.table.TableColumn;
/*  22:    */ import javax.swing.table.TableColumnModel;
/*  23:    */ 
/*  24:    */ public class GridBagTableUI
/*  25:    */   extends BasicTableUI
/*  26:    */ {
/*  27:    */   public Dimension getPreferredSize(JComponent c)
/*  28:    */   {
/*  29: 29 */     long width = 0L;
/*  30: 30 */     Enumeration<TableColumn> enumeration = this.table.getColumnModel()
/*  31: 31 */       .getColumns();
/*  32: 32 */     while (enumeration.hasMoreElements())
/*  33:    */     {
/*  34: 33 */       TableColumn aColumn = (TableColumn)enumeration.nextElement();
/*  35: 34 */       width += aColumn.getPreferredWidth();
/*  36:    */     }
/*  37: 36 */     return createTableSize(width);
/*  38:    */   }
/*  39:    */   
/*  40:    */   private Dimension createTableSize(long width)
/*  41:    */   {
/*  42: 40 */     int height = 0;
/*  43: 41 */     int rowCount = this.table.getRowCount();
/*  44: 42 */     if ((rowCount > 0) && (this.table.getColumnCount() > 0))
/*  45:    */     {
/*  46: 43 */       Rectangle r = this.table.getCellRect(rowCount - 1, 0, true);
/*  47: 44 */       height = r.y + r.height;
/*  48:    */     }
/*  49: 46 */     long tmp = Math.abs(width);
/*  50: 47 */     if (tmp > 2147483647L) {
/*  51: 48 */       tmp = 2147483647L;
/*  52:    */     }
/*  53: 50 */     return new Dimension((int)tmp, height);
/*  54:    */   }
/*  55:    */   
/*  56:    */   public void paint(Graphics g, JComponent c)
/*  57:    */   {
/*  58: 54 */     Rectangle clip = g.getClipBounds();
/*  59:    */     
/*  60: 56 */     Rectangle bounds = this.table.getBounds();
/*  61:    */     
/*  62:    */ 
/*  63: 59 */     bounds.x = (bounds.y = 0);
/*  64: 60 */     if ((this.table.getRowCount() <= 0) || (this.table.getColumnCount() <= 0) || 
/*  65:    */     
/*  66:    */ 
/*  67: 63 */       (!bounds.intersects(clip)))
/*  68:    */     {
/*  69: 65 */       paintDropLines(g);
/*  70: 66 */       return;
/*  71:    */     }
/*  72: 69 */     boolean ltr = this.table.getComponentOrientation().isLeftToRight();
/*  73:    */     
/*  74: 71 */     Point upperLeft = clip.getLocation();
/*  75: 72 */     if (!ltr) {
/*  76: 73 */       upperLeft.x += 1;
/*  77:    */     }
/*  78: 76 */     Point lowerRight = new Point(clip.x + clip.width - (ltr ? 1 : 0), 
/*  79: 77 */       clip.y + clip.height);
/*  80:    */     
/*  81: 79 */     int rMin = this.table.rowAtPoint(upperLeft);
/*  82: 80 */     int rMax = this.table.rowAtPoint(lowerRight);
/*  83: 83 */     if (rMin == -1) {
/*  84: 84 */       rMin = 0;
/*  85:    */     }
/*  86: 90 */     if (rMax == -1) {
/*  87: 91 */       rMax = this.table.getRowCount() - 1;
/*  88:    */     }
/*  89: 94 */     int cMin = this.table.columnAtPoint(ltr ? upperLeft : lowerRight);
/*  90: 95 */     int cMax = this.table.columnAtPoint(ltr ? lowerRight : upperLeft);
/*  91: 97 */     if (cMin == -1) {
/*  92: 98 */       cMin = 0;
/*  93:    */     }
/*  94:103 */     if (cMax == -1) {
/*  95:104 */       cMax = this.table.getColumnCount() - 1;
/*  96:    */     }
/*  97:111 */     paintCells(g, rMin, rMax, cMin, cMax);
/*  98:    */     
/*  99:113 */     paintDropLines(g);
/* 100:    */   }
/* 101:    */   
/* 102:    */   private void paintDropLines(Graphics g)
/* 103:    */   {
/* 104:117 */     JTable.DropLocation loc = this.table.getDropLocation();
/* 105:118 */     if (loc == null) {
/* 106:119 */       return;
/* 107:    */     }
/* 108:122 */     Color color = UIManager.getColor("Table.dropLineColor");
/* 109:123 */     Color shortColor = UIManager.getColor("Table.dropLineShortColor");
/* 110:124 */     if ((color == null) && (shortColor == null)) {
/* 111:125 */       return;
/* 112:    */     }
/* 113:130 */     Rectangle rect = getHDropLineRect(loc);
/* 114:131 */     if (rect != null)
/* 115:    */     {
/* 116:132 */       int x = rect.x;
/* 117:133 */       int w = rect.width;
/* 118:134 */       if (color != null)
/* 119:    */       {
/* 120:135 */         extendRect(rect, true);
/* 121:136 */         g.setColor(color);
/* 122:137 */         g.fillRect(rect.x, rect.y, rect.width, rect.height);
/* 123:    */       }
/* 124:139 */       if ((!loc.isInsertColumn()) && (shortColor != null))
/* 125:    */       {
/* 126:140 */         g.setColor(shortColor);
/* 127:141 */         g.fillRect(x, rect.y, w, rect.height);
/* 128:    */       }
/* 129:    */     }
/* 130:145 */     rect = getVDropLineRect(loc);
/* 131:146 */     if (rect != null)
/* 132:    */     {
/* 133:147 */       int y = rect.y;
/* 134:148 */       int h = rect.height;
/* 135:149 */       if (color != null)
/* 136:    */       {
/* 137:150 */         extendRect(rect, false);
/* 138:151 */         g.setColor(color);
/* 139:152 */         g.fillRect(rect.x, rect.y, rect.width, rect.height);
/* 140:    */       }
/* 141:154 */       if ((!loc.isInsertRow()) && (shortColor != null))
/* 142:    */       {
/* 143:155 */         g.setColor(shortColor);
/* 144:156 */         g.fillRect(rect.x, y, rect.width, h);
/* 145:    */       }
/* 146:    */     }
/* 147:    */   }
/* 148:    */   
/* 149:    */   private void paintGrid(Graphics g, int rMin, int rMax, int cMin, int cMax)
/* 150:    */   {
/* 151:169 */     g.setColor(this.table.getGridColor());
/* 152:    */     
/* 153:171 */     Rectangle minCell = this.table.getCellRect(rMin, cMin, true);
/* 154:172 */     Rectangle maxCell = this.table.getCellRect(rMax, cMax, true);
/* 155:173 */     Rectangle damagedArea = minCell.union(maxCell);
/* 156:175 */     if (this.table.getShowHorizontalLines())
/* 157:    */     {
/* 158:176 */       int tableWidth = damagedArea.x + damagedArea.width;
/* 159:177 */       int y = damagedArea.y;
/* 160:178 */       for (int row = rMin; row <= rMax; row++)
/* 161:    */       {
/* 162:179 */         y += this.table.getRowHeight(row);
/* 163:180 */         g.drawLine(damagedArea.x, y - 1, tableWidth - 1, y - 1);
/* 164:    */       }
/* 165:    */     }
/* 166:183 */     if (this.table.getShowVerticalLines())
/* 167:    */     {
/* 168:184 */       TableColumnModel cm = this.table.getColumnModel();
/* 169:185 */       int tableHeight = damagedArea.y + damagedArea.height;
/* 170:187 */       if (this.table.getComponentOrientation().isLeftToRight())
/* 171:    */       {
/* 172:188 */         int x = damagedArea.x;
/* 173:189 */         for (int column = cMin; column <= cMax; column++)
/* 174:    */         {
/* 175:190 */           int w = cm.getColumn(column).getWidth();
/* 176:191 */           x += w;
/* 177:192 */           g.drawLine(x - 1, 0, x - 1, tableHeight - 1);
/* 178:    */         }
/* 179:    */       }
/* 180:    */       else
/* 181:    */       {
/* 182:195 */         int x = damagedArea.x;
/* 183:196 */         for (int column = cMax; column >= cMin; column--)
/* 184:    */         {
/* 185:197 */           int w = cm.getColumn(column).getWidth();
/* 186:198 */           x += w;
/* 187:199 */           g.drawLine(x - 1, 0, x - 1, tableHeight - 1);
/* 188:    */         }
/* 189:    */       }
/* 190:    */     }
/* 191:    */   }
/* 192:    */   
/* 193:    */   private void paintCells(Graphics g, int rMin, int rMax, int cMin, int cMax)
/* 194:    */   {
/* 195:206 */     JTableHeader header = this.table.getTableHeader();
/* 196:207 */     TableColumn draggedColumn = header == null ? null : 
/* 197:208 */       header.getDraggedColumn();
/* 198:    */     
/* 199:210 */     TableColumnModel cm = this.table.getColumnModel();
/* 200:211 */     int columnMargin = cm.getColumnMargin();
/* 201:215 */     if (this.table.getComponentOrientation().isLeftToRight()) {
/* 202:216 */       for (int row = rMin; row <= rMax; row++)
/* 203:    */       {
/* 204:    */         Rectangle cellRect;
/* 205:    */         Rectangle cellRect;
/* 206:217 */         if ((this.table instanceof GridBagTable)) {
/* 207:218 */           cellRect = ((GridBagTable)this.table).getGridCellRect(row, 
/* 208:219 */             cMin, false);
/* 209:    */         } else {
/* 210:221 */           cellRect = this.table.getCellRect(row, cMin, false);
/* 211:    */         }
/* 212:222 */         for (int column = cMin; column <= cMax; column++)
/* 213:    */         {
/* 214:223 */           TableColumn aColumn = cm.getColumn(column);
/* 215:224 */           int columnWidth = aColumn.getWidth();
/* 216:225 */           cellRect.width = (columnWidth - columnMargin);
/* 217:226 */           int oldHeight = cellRect.height;
/* 218:227 */           if ((this.table instanceof GridBagTable)) {
/* 219:228 */             if (((GridBagTable)this.table).getGridBagModel()
/* 220:229 */               .getCellState(row, column) == -1)
/* 221:    */             {
/* 222:230 */               cellRect.width = 0;
/* 223:231 */               cellRect.height = 0;
/* 224:    */             }
/* 225:    */             else
/* 226:    */             {
/* 227:233 */               int h = ((GridBagTable)this.table).getGridBagModel()
/* 228:234 */                 .getColumnGrid(row, column);
/* 229:235 */               if (h > 1) {
/* 230:236 */                 for (int n = 1; n < h; n++)
/* 231:    */                 {
/* 232:237 */                   Rectangle tmp231_229 = cellRect;
/* 233:238 */                   tmp231_229.width = (tmp231_229.width + cm.getColumn(column + n).getWidth());
/* 234:    */                 }
/* 235:    */               }
/* 236:240 */               int v = ((GridBagTable)this.table).getGridBagModel()
/* 237:241 */                 .getRowGrid(row, column);
/* 238:242 */               if (v > 1) {
/* 239:243 */                 for (int n = 1; n < v; n++)
/* 240:    */                 {
/* 241:244 */                   Rectangle tmp299_297 = cellRect;
/* 242:245 */                   tmp299_297.height = (tmp299_297.height + this.table.getRowHeight(row + n));
/* 243:    */                 }
/* 244:    */               }
/* 245:    */             }
/* 246:    */           }
/* 247:249 */           if (aColumn != draggedColumn) {
/* 248:250 */             paintCell(g, cellRect, row, column);
/* 249:    */           }
/* 250:252 */           cellRect.height = oldHeight;
/* 251:253 */           cellRect.x += columnWidth;
/* 252:    */         }
/* 253:    */       }
/* 254:    */     } else {
/* 255:257 */       for (int row = rMin; row <= rMax; row++)
/* 256:    */       {
/* 257:258 */         Rectangle cellRect = this.table.getCellRect(row, cMin, false);
/* 258:259 */         TableColumn aColumn = cm.getColumn(cMin);
/* 259:260 */         if (aColumn != draggedColumn)
/* 260:    */         {
/* 261:261 */           int columnWidth = aColumn.getWidth();
/* 262:262 */           cellRect.width = (columnWidth - columnMargin);
/* 263:263 */           paintCell(g, cellRect, row, cMin);
/* 264:    */         }
/* 265:265 */         for (int column = cMin + 1; column <= cMax; column++)
/* 266:    */         {
/* 267:266 */           aColumn = cm.getColumn(column);
/* 268:267 */           int columnWidth = aColumn.getWidth();
/* 269:    */           
/* 270:269 */           cellRect.width = (columnWidth - columnMargin);
/* 271:270 */           cellRect.x -= columnWidth;
/* 272:271 */           if (aColumn != draggedColumn) {
/* 273:272 */             paintCell(g, cellRect, row, column);
/* 274:    */           }
/* 275:    */         }
/* 276:    */       }
/* 277:    */     }
/* 278:279 */     if (draggedColumn != null) {
/* 279:280 */       paintDraggedArea(g, rMin, rMax, draggedColumn, 
/* 280:281 */         header.getDraggedDistance());
/* 281:    */     }
/* 282:285 */     this.rendererPane.removeAll();
/* 283:    */   }
/* 284:    */   
/* 285:    */   private void paintCell(Graphics g, Rectangle cellRect, int row, int column)
/* 286:    */   {
/* 287:289 */     if ((this.table.isEditing()) && (this.table.getEditingRow() == row) && 
/* 288:290 */       (this.table.getEditingColumn() == column))
/* 289:    */     {
/* 290:291 */       Component component = this.table.getEditorComponent();
/* 291:292 */       component.setBounds(cellRect);
/* 292:293 */       component.validate();
/* 293:    */     }
/* 294:    */     else
/* 295:    */     {
/* 296:295 */       TableCellRenderer renderer = this.table.getCellRenderer(row, column);
/* 297:296 */       Component component = this.table.prepareRenderer(renderer, row, column);
/* 298:298 */       if ((component instanceof JComponent)) {
/* 299:299 */         ((JComponent)component).setBorder(
/* 300:300 */           BorderFactory.createLineBorder(new Color(210, 210, 210)));
/* 301:    */       }
/* 302:302 */       this.rendererPane.paintComponent(g, component, this.table, cellRect.x, 
/* 303:303 */         cellRect.y, cellRect.width + 2, cellRect.height + 2, true);
/* 304:    */     }
/* 305:    */   }
/* 306:    */   
/* 307:    */   private Rectangle getHDropLineRect(JTable.DropLocation loc)
/* 308:    */   {
/* 309:308 */     if (!loc.isInsertRow()) {
/* 310:309 */       return null;
/* 311:    */     }
/* 312:312 */     int row = loc.getRow();
/* 313:313 */     int col = loc.getColumn();
/* 314:314 */     if (col >= this.table.getColumnCount()) {
/* 315:315 */       col--;
/* 316:    */     }
/* 317:318 */     Rectangle rect = this.table.getCellRect(row, col, true);
/* 318:320 */     if (row >= this.table.getRowCount())
/* 319:    */     {
/* 320:321 */       row--;
/* 321:322 */       Rectangle prevRect = this.table.getCellRect(row, col, true);
/* 322:323 */       prevRect.y += prevRect.height;
/* 323:    */     }
/* 324:326 */     if (rect.y == 0) {
/* 325:327 */       rect.y = -1;
/* 326:    */     } else {
/* 327:329 */       rect.y -= 2;
/* 328:    */     }
/* 329:332 */     rect.height = 3;
/* 330:    */     
/* 331:334 */     return rect;
/* 332:    */   }
/* 333:    */   
/* 334:    */   private void paintDraggedArea(Graphics g, int rMin, int rMax, TableColumn draggedColumn, int distance)
/* 335:    */   {
/* 336:339 */     int draggedColumnIndex = viewIndexForColumn(draggedColumn);
/* 337:    */     
/* 338:341 */     Rectangle minCell = this.table.getCellRect(rMin, draggedColumnIndex, true);
/* 339:342 */     Rectangle maxCell = this.table.getCellRect(rMax, draggedColumnIndex, true);
/* 340:    */     
/* 341:344 */     Rectangle vacatedColumnRect = minCell.union(maxCell);
/* 342:    */     
/* 343:    */ 
/* 344:347 */     g.setColor(this.table.getParent().getBackground());
/* 345:348 */     g.fillRect(vacatedColumnRect.x, vacatedColumnRect.y, 
/* 346:349 */       vacatedColumnRect.width, vacatedColumnRect.height);
/* 347:    */     
/* 348:    */ 
/* 349:352 */     vacatedColumnRect.x += distance;
/* 350:    */     
/* 351:    */ 
/* 352:355 */     g.setColor(this.table.getBackground());
/* 353:356 */     g.fillRect(vacatedColumnRect.x, vacatedColumnRect.y, 
/* 354:357 */       vacatedColumnRect.width, vacatedColumnRect.height);
/* 355:360 */     if (this.table.getShowVerticalLines())
/* 356:    */     {
/* 357:361 */       g.setColor(this.table.getGridColor());
/* 358:362 */       int x1 = vacatedColumnRect.x;
/* 359:363 */       int y1 = vacatedColumnRect.y;
/* 360:364 */       int x2 = x1 + vacatedColumnRect.width - 1;
/* 361:365 */       int y2 = y1 + vacatedColumnRect.height - 1;
/* 362:    */       
/* 363:367 */       g.drawLine(x1 - 1, y1, x1 - 1, y2);
/* 364:    */       
/* 365:369 */       g.drawLine(x2, y1, x2, y2);
/* 366:    */     }
/* 367:372 */     for (int row = rMin; row <= rMax; row++)
/* 368:    */     {
/* 369:374 */       Rectangle r = this.table.getCellRect(row, draggedColumnIndex, false);
/* 370:375 */       r.x += distance;
/* 371:376 */       paintCell(g, r, row, draggedColumnIndex);
/* 372:379 */       if (this.table.getShowHorizontalLines())
/* 373:    */       {
/* 374:380 */         g.setColor(this.table.getGridColor());
/* 375:381 */         Rectangle rcr = this.table
/* 376:382 */           .getCellRect(row, draggedColumnIndex, true);
/* 377:383 */         rcr.x += distance;
/* 378:384 */         int x1 = rcr.x;
/* 379:385 */         int y1 = rcr.y;
/* 380:386 */         int x2 = x1 + rcr.width - 1;
/* 381:387 */         int y2 = y1 + rcr.height - 1;
/* 382:388 */         g.drawLine(x1, y2, x2, y2);
/* 383:    */       }
/* 384:    */     }
/* 385:    */   }
/* 386:    */   
/* 387:    */   private int viewIndexForColumn(TableColumn aColumn)
/* 388:    */   {
/* 389:394 */     TableColumnModel cm = this.table.getColumnModel();
/* 390:395 */     for (int column = 0; column < cm.getColumnCount(); column++) {
/* 391:396 */       if (cm.getColumn(column) == aColumn) {
/* 392:397 */         return column;
/* 393:    */       }
/* 394:    */     }
/* 395:400 */     return -1;
/* 396:    */   }
/* 397:    */   
/* 398:    */   private Rectangle extendRect(Rectangle rect, boolean horizontal)
/* 399:    */   {
/* 400:404 */     if (rect == null) {
/* 401:405 */       return rect;
/* 402:    */     }
/* 403:408 */     if (horizontal)
/* 404:    */     {
/* 405:409 */       rect.x = 0;
/* 406:410 */       rect.width = this.table.getWidth();
/* 407:    */     }
/* 408:    */     else
/* 409:    */     {
/* 410:412 */       rect.y = 0;
/* 411:414 */       if (this.table.getRowCount() != 0)
/* 412:    */       {
/* 413:415 */         Rectangle lastRect = this.table.getCellRect(this.table.getRowCount() - 1, 
/* 414:416 */           0, true);
/* 415:417 */         rect.height = (lastRect.y + lastRect.height);
/* 416:    */       }
/* 417:    */       else
/* 418:    */       {
/* 419:419 */         rect.height = this.table.getHeight();
/* 420:    */       }
/* 421:    */     }
/* 422:423 */     return rect;
/* 423:    */   }
/* 424:    */   
/* 425:    */   private Rectangle getVDropLineRect(JTable.DropLocation loc)
/* 426:    */   {
/* 427:427 */     if (!loc.isInsertColumn()) {
/* 428:428 */       return null;
/* 429:    */     }
/* 430:431 */     boolean ltr = this.table.getComponentOrientation().isLeftToRight();
/* 431:432 */     int col = loc.getColumn();
/* 432:433 */     Rectangle rect = this.table.getCellRect(loc.getRow(), col, true);
/* 433:435 */     if (col >= this.table.getColumnCount())
/* 434:    */     {
/* 435:436 */       col--;
/* 436:437 */       rect = this.table.getCellRect(loc.getRow(), col, true);
/* 437:438 */       if (ltr) {
/* 438:439 */         rect.x += rect.width;
/* 439:    */       }
/* 440:    */     }
/* 441:441 */     else if (!ltr)
/* 442:    */     {
/* 443:442 */       rect.x += rect.width;
/* 444:    */     }
/* 445:445 */     if (rect.x == 0) {
/* 446:446 */       rect.x = -1;
/* 447:    */     } else {
/* 448:448 */       rect.x -= 2;
/* 449:    */     }
/* 450:451 */     rect.width = 3;
/* 451:    */     
/* 452:453 */     return rect;
/* 453:    */   }
/* 454:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.GridBagTableUI
 * JD-Core Version:    0.7.0.1
 */