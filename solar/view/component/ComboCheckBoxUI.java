/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import java.awt.Component;
/*   4:    */ import java.awt.Dimension;
/*   5:    */ import java.awt.Insets;
/*   6:    */ import java.awt.Rectangle;
/*   7:    */ import java.awt.event.KeyAdapter;
/*   8:    */ import java.awt.event.KeyEvent;
/*   9:    */ import java.awt.event.KeyListener;
/*  10:    */ import java.awt.event.MouseAdapter;
/*  11:    */ import java.awt.event.MouseEvent;
/*  12:    */ import java.awt.event.MouseListener;
/*  13:    */ import javax.accessibility.AccessibleContext;
/*  14:    */ import javax.swing.JComboBox;
/*  15:    */ import javax.swing.JComponent;
/*  16:    */ import javax.swing.JList;
/*  17:    */ import javax.swing.JScrollBar;
/*  18:    */ import javax.swing.JScrollPane;
/*  19:    */ import javax.swing.ListCellRenderer;
/*  20:    */ import javax.swing.ListModel;
/*  21:    */ import javax.swing.plaf.ComponentUI;
/*  22:    */ import javax.swing.plaf.basic.BasicComboPopup;
/*  23:    */ import javax.swing.plaf.basic.ComboPopup;
/*  24:    */ import javax.swing.plaf.metal.MetalComboBoxUI;
/*  25:    */ 
/*  26:    */ public class ComboCheckBoxUI
/*  27:    */   extends MetalComboBoxUI
/*  28:    */ {
/*  29: 32 */   private boolean isMultiSel = true;
/*  30: 33 */   public int maxWidth = 300;
/*  31:    */   
/*  32:    */   public ComboCheckBoxUI() {}
/*  33:    */   
/*  34:    */   public ComboCheckBoxUI(int maxWidth)
/*  35:    */   {
/*  36: 39 */     this.maxWidth = maxWidth;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public static ComponentUI createUI(JComponent c)
/*  40:    */   {
/*  41: 43 */     return new ComboCheckBoxUI();
/*  42:    */   }
/*  43:    */   
/*  44:    */   protected ComboPopup createPopup()
/*  45:    */   {
/*  46: 48 */     ComboCheckPopUp popUp = new ComboCheckPopUp(this.comboBox, this.maxWidth);
/*  47: 49 */     popUp.getAccessibleContext().setAccessibleParent(this.comboBox);
/*  48: 50 */     return popUp;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public class ComboCheckPopUp
/*  52:    */     extends BasicComboPopup
/*  53:    */   {
/*  54:    */     private static final long serialVersionUID = -37435227302289947L;
/*  55: 56 */     private int width = -1;
/*  56: 57 */     private int maxWidth = 300;
/*  57:    */     
/*  58:    */     public ComboCheckPopUp(JComboBox cBox, int maxWidth)
/*  59:    */     {
/*  60: 60 */       super();
/*  61: 61 */       this.maxWidth = maxWidth;
/*  62:    */     }
/*  63:    */     
/*  64:    */     protected JScrollPane createScroller()
/*  65:    */     {
/*  66: 66 */       return new JScrollPane(this.list, 
/*  67: 67 */         20, 
/*  68: 68 */         30);
/*  69:    */     }
/*  70:    */     
/*  71:    */     protected MouseListener createListMouseListener()
/*  72:    */     {
/*  73: 73 */       return new CheckBoxListMouseHandler();
/*  74:    */     }
/*  75:    */     
/*  76:    */     protected KeyListener createKeyListener()
/*  77:    */     {
/*  78: 78 */       return new CheckBoxKeyHandler();
/*  79:    */     }
/*  80:    */     
/*  81:    */     public void show()
/*  82:    */     {
/*  83: 84 */       Dimension popupSize = this.comboBox.getSize();
/*  84: 85 */       Insets insets = getInsets();
/*  85: 86 */       popupSize.setSize(popupSize.width - (insets.right + insets.left), 
/*  86: 87 */         getPopupHeightForRowCount(this.comboBox.getMaximumRowCount()));
/*  87:    */       
/*  88: 89 */       int maxWidthOfCell = calcPreferredWidth();
/*  89: 90 */       this.width = maxWidthOfCell;
/*  90: 92 */       if (this.comboBox.getItemCount() > this.comboBox.getMaximumRowCount()) {
/*  91: 93 */         this.width += this.scroller.getVerticalScrollBar().getPreferredSize().width;
/*  92:    */       }
/*  93: 96 */       if (this.width > this.maxWidth) {
/*  94: 97 */         this.width = this.maxWidth;
/*  95:    */       }
/*  96:100 */       if (this.width < this.comboBox.getWidth()) {
/*  97:101 */         this.width = this.comboBox.getWidth();
/*  98:    */       }
/*  99:104 */       if (maxWidthOfCell > this.width)
/* 100:    */       {
/* 101:105 */         Dimension tmp144_143 = popupSize;
/* 102:106 */         tmp144_143.height = (tmp144_143.height + this.scroller.getHorizontalScrollBar().getPreferredSize().height);
/* 103:    */       }
/* 104:109 */       Rectangle popupBounds = computePopupBounds(0, 
/* 105:110 */         this.comboBox.getBounds().height, this.width, popupSize.height);
/* 106:111 */       this.scroller.setMaximumSize(popupBounds.getSize());
/* 107:112 */       this.scroller.setPreferredSize(popupBounds.getSize());
/* 108:113 */       this.scroller.setMinimumSize(popupBounds.getSize());
/* 109:114 */       this.list.invalidate();
/* 110:115 */       syncListSelectionWithComboBoxSelection();
/* 111:116 */       this.list.ensureIndexIsVisible(this.list.getSelectedIndex());
/* 112:117 */       setLightWeightPopupEnabled(this.comboBox.isLightWeightPopupEnabled());
/* 113:118 */       show(this.comboBox, popupBounds.x, popupBounds.y);
/* 114:    */     }
/* 115:    */     
/* 116:    */     private int calcPreferredWidth()
/* 117:    */     {
/* 118:122 */       int prefferedWidth = 0;
/* 119:123 */       ListCellRenderer renderer = this.list.getCellRenderer();
/* 120:124 */       int index = 0;
/* 121:124 */       for (int count = this.list.getModel().getSize(); index < count; index++)
/* 122:    */       {
/* 123:125 */         Object element = this.list.getModel().getElementAt(index);
/* 124:126 */         Component comp = renderer.getListCellRendererComponent(this.list, 
/* 125:127 */           element, index, false, false);
/* 126:128 */         Dimension dim = comp.getPreferredSize();
/* 127:129 */         if (dim.width > prefferedWidth) {
/* 128:130 */           prefferedWidth = dim.width;
/* 129:    */         }
/* 130:    */       }
/* 131:133 */       return prefferedWidth;
/* 132:    */     }
/* 133:    */     
/* 134:    */     void syncListSelectionWithComboBoxSelection()
/* 135:    */     {
/* 136:137 */       int selectedIndex = this.comboBox.getSelectedIndex();
/* 137:138 */       if (selectedIndex == -1) {
/* 138:139 */         this.list.clearSelection();
/* 139:    */       } else {
/* 140:141 */         this.list.setSelectedIndex(selectedIndex);
/* 141:    */       }
/* 142:    */     }
/* 143:    */     
/* 144:    */     public void setPopupWidth(int width)
/* 145:    */     {
/* 146:146 */       this.width = width;
/* 147:    */     }
/* 148:    */     
/* 149:    */     protected class CheckBoxKeyHandler
/* 150:    */       extends KeyAdapter
/* 151:    */     {
/* 152:    */       protected CheckBoxKeyHandler() {}
/* 153:    */       
/* 154:    */       public void keyPressed(KeyEvent e)
/* 155:    */       {
/* 156:153 */         ComboCheckBoxUI.this.isMultiSel = true;
/* 157:    */       }
/* 158:    */       
/* 159:    */       public void keyReleased(KeyEvent e)
/* 160:    */       {
/* 161:158 */         ComboCheckBoxUI.this.isMultiSel = true;
/* 162:    */       }
/* 163:    */     }
/* 164:    */     
/* 165:    */     protected class CheckBoxListMouseHandler
/* 166:    */       extends MouseAdapter
/* 167:    */     {
/* 168:    */       protected CheckBoxListMouseHandler() {}
/* 169:    */       
/* 170:    */       public void mousePressed(MouseEvent anEvent)
/* 171:    */       {
/* 172:166 */         int index = ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this).getSelectedIndex();
/* 173:167 */         ComboCheckBoxEntry item = (ComboCheckBoxEntry)ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this).getModel()
/* 174:168 */           .getElementAt(index);
/* 175:169 */         int size = ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this).getModel().getSize();
/* 176:171 */         if (ComboCheckBoxUI.this.isMultiSel)
/* 177:    */         {
/* 178:172 */           if (item.isEnable())
/* 179:    */           {
/* 180:173 */             boolean checked = !item.isChecked();
/* 181:174 */             item.setChecked(checked);
/* 182:    */           }
/* 183:    */         }
/* 184:    */         else
/* 185:    */         {
/* 186:177 */           for (int i = 0; i < size; i++)
/* 187:    */           {
/* 188:178 */             ComboCheckBoxEntry ccbe = (ComboCheckBoxEntry)ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this)
/* 189:179 */               .getModel().getElementAt(i);
/* 190:180 */             ccbe.setChecked(false);
/* 191:    */           }
/* 192:182 */           item.setChecked(true);
/* 193:    */         }
/* 194:184 */         ComboCheckBoxUI.ComboCheckPopUp.this.updateListBoxSelectionForEvent(anEvent, false);
/* 195:185 */         Rectangle rect = ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this).getCellBounds(0, size - 1);
/* 196:186 */         ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this).repaint(rect);
/* 197:    */       }
/* 198:    */       
/* 199:    */       public void mouseReleased(MouseEvent anEvent)
/* 200:    */       {
/* 201:191 */         if (!ComboCheckBoxUI.this.isMultiSel)
/* 202:    */         {
/* 203:192 */           ComboCheckBoxEntry item = (ComboCheckBoxEntry)ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this)
/* 204:193 */             .getSelectedValue();
/* 205:194 */           if (item.isChecked())
/* 206:    */           {
/* 207:195 */             ComboCheckBoxUI.ComboCheckPopUp.access$2(ComboCheckBoxUI.ComboCheckPopUp.this).setSelectedIndex(ComboCheckBoxUI.ComboCheckPopUp.access$0(ComboCheckBoxUI.ComboCheckPopUp.this).getSelectedIndex());
/* 208:196 */             ComboCheckBoxUI.ComboCheckPopUp.access$2(ComboCheckBoxUI.ComboCheckPopUp.this).setPopupVisible(false);
/* 209:    */           }
/* 210:    */         }
/* 211:    */       }
/* 212:    */     }
/* 213:    */   }
/* 214:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.ComboCheckBoxUI
 * JD-Core Version:    0.7.0.1
 */