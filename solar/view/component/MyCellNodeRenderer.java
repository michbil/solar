/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.constants.Constants;
/*  4:   */ import java.awt.Component;
/*  5:   */ import javax.swing.ImageIcon;
/*  6:   */ import javax.swing.JTree;
/*  7:   */ import javax.swing.tree.DefaultTreeCellRenderer;
/*  8:   */ 
/*  9:   */ class MyCellNodeRenderer
/* 10:   */   extends DefaultTreeCellRenderer
/* 11:   */ {
/* 12:   */   private static final long serialVersionUID = 3914698479638122717L;
/* 13:   */   
/* 14:   */   public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
/* 15:   */   {
/* 16:67 */     super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, 
/* 17:68 */       row, hasFocus);
/* 18:69 */     String icon = value.toString();
/* 19:70 */     if (row == 0) {
/* 20:71 */       setIcon(new ImageIcon(Constants.IMAGES_PATH + "63.png"));
/* 21:73 */     } else if (icon.startsWith("COM")) {
/* 22:74 */       setIcon(new ImageIcon(Constants.IMAGES_PATH + "port.png"));
/* 23:75 */     } else if (icon.startsWith("USB")) {
/* 24:76 */       setIcon(new ImageIcon(Constants.IMAGES_PATH + "usb.png"));
/* 25:   */     } else {
/* 26:78 */       setIcon(new ImageIcon(Constants.IMAGES_PATH + "port.png"));
/* 27:   */     }
/* 28:81 */     return this;
/* 29:   */   }
/* 30:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.MyCellNodeRenderer
 * JD-Core Version:    0.7.0.1
 */