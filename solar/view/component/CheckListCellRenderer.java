/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.Component;
/*  4:   */ import javax.swing.JCheckBox;
/*  5:   */ import javax.swing.JList;
/*  6:   */ import javax.swing.ListCellRenderer;
/*  7:   */ import javax.swing.UIManager;
/*  8:   */ import javax.swing.border.Border;
/*  9:   */ import javax.swing.border.EmptyBorder;
/* 10:   */ 
/* 11:   */ public class CheckListCellRenderer
/* 12:   */   extends JCheckBox
/* 13:   */   implements ListCellRenderer
/* 14:   */ {
/* 15:   */   private static final long serialVersionUID = 3123926731556855561L;
/* 16:21 */   protected static Border m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
/* 17:   */   
/* 18:   */   public CheckListCellRenderer()
/* 19:   */   {
/* 20:25 */     setOpaque(true);
/* 21:26 */     setBorder(m_noFocusBorder);
/* 22:   */   }
/* 23:   */   
/* 24:   */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 25:   */   {
/* 26:31 */     setBackground(isSelected ? list.getSelectionBackground() : 
/* 27:32 */       list.getBackground());
/* 28:33 */     setForeground(isSelected ? list.getSelectionForeground() : 
/* 29:34 */       list.getForeground());
/* 30:35 */     setFont(list.getFont());
/* 31:36 */     setBorder(cellHasFocus ? 
/* 32:37 */       UIManager.getBorder("List.focusCellHighlightBorder ") : m_noFocusBorder);
/* 33:38 */     CheckBoxData data = (CheckBoxData)value;
/* 34:39 */     setText(data.getName());
/* 35:40 */     setSelected(data.isSelected());
/* 36:41 */     return this;
/* 37:   */   }
/* 38:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.CheckListCellRenderer
 * JD-Core Version:    0.7.0.1
 */