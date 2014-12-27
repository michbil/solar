/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.Component;
/*  4:   */ import java.io.Serializable;
/*  5:   */ import javax.swing.JCheckBox;
/*  6:   */ import javax.swing.JList;
/*  7:   */ import javax.swing.ListCellRenderer;
/*  8:   */ import javax.swing.UIManager;
/*  9:   */ import javax.swing.border.Border;
/* 10:   */ import javax.swing.border.EmptyBorder;
/* 11:   */ 
/* 12:   */ public class ComboCheckBoxRenderer
/* 13:   */   extends JCheckBox
/* 14:   */   implements ListCellRenderer, Serializable
/* 15:   */ {
/* 16:   */   private static final long serialVersionUID = -3632842295675073408L;
/* 17:   */   protected static Border noFocusBorder;
/* 18:   */   
/* 19:   */   public ComboCheckBoxRenderer()
/* 20:   */   {
/* 21:27 */     if (noFocusBorder == null) {
/* 22:28 */       noFocusBorder = new EmptyBorder(1, 1, 1, 1);
/* 23:   */     }
/* 24:30 */     setOpaque(true);
/* 25:31 */     setBorder(noFocusBorder);
/* 26:   */   }
/* 27:   */   
/* 28:   */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/* 29:   */   {
/* 30:36 */     setComponentOrientation(list.getComponentOrientation());
/* 31:37 */     if (isSelected)
/* 32:   */     {
/* 33:38 */       setBackground(list.getSelectionBackground());
/* 34:39 */       setForeground(list.getSelectionForeground());
/* 35:   */     }
/* 36:   */     else
/* 37:   */     {
/* 38:41 */       setBackground(list.getBackground());
/* 39:42 */       setForeground(list.getForeground());
/* 40:   */     }
/* 41:45 */     if ((value instanceof ComboCheckBoxEntry))
/* 42:   */     {
/* 43:46 */       ComboCheckBoxEntry item = (ComboCheckBoxEntry)value;
/* 44:47 */       setSelected(item.isChecked());
/* 45:48 */       setEnabled(item.isEnable());
/* 46:49 */       setToolTipText(item.getValue());
/* 47:50 */       setText(item.getValue());
/* 48:   */     }
/* 49:   */     else
/* 50:   */     {
/* 51:52 */       setText(value == null ? "" : value.toString());
/* 52:   */     }
/* 53:54 */     setFont(list.getFont());
/* 54:55 */     setBorder(cellHasFocus ? 
/* 55:56 */       UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
/* 56:   */     
/* 57:58 */     return this;
/* 58:   */   }
/* 59:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.ComboCheckBoxRenderer
 * JD-Core Version:    0.7.0.1
 */