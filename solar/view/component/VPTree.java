/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.Color;
/*  4:   */ import java.net.InetAddress;
/*  5:   */ import java.net.UnknownHostException;
/*  6:   */ import java.util.ArrayList;
/*  7:   */ import java.util.List;
/*  8:   */ import javax.swing.JTree;
/*  9:   */ import javax.swing.tree.DefaultMutableTreeNode;
/* 10:   */ import javax.swing.tree.DefaultTreeModel;
/* 11:   */ 
/* 12:   */ public class VPTree
/* 13:   */   extends JTree
/* 14:   */ {
/* 15:   */   private static final long serialVersionUID = 9199814511565358313L;
/* 16:   */   
/* 17:   */   public VPTree()
/* 18:   */   {
/* 19:23 */     initComponents();
/* 20:24 */     setBackground(new Color(102, 102, 102));
/* 21:25 */     setForeground(new Color(102, 102, 102));
/* 22:   */   }
/* 23:   */   
/* 24:   */   private void initComponents()
/* 25:   */   {
/* 26:28 */     List<String> devices = new ArrayList();
/* 27:29 */     refreshTree(devices);
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void refreshTree(List<String> devices)
/* 31:   */   {
/* 32:32 */     String computerName = "Computer";
/* 33:   */     try
/* 34:   */     {
/* 35:34 */       InetAddress ip = InetAddress.getLocalHost();
/* 36:35 */       computerName = ip.getCanonicalHostName();
/* 37:   */     }
/* 38:   */     catch (UnknownHostException e)
/* 39:   */     {
/* 40:37 */       e.printStackTrace();
/* 41:   */     }
/* 42:39 */     DefaultMutableTreeNode treeNode0 = new DefaultMutableTreeNode(computerName);
/* 43:40 */     for (int i = 0; i < devices.size(); i++)
/* 44:   */     {
/* 45:41 */       DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(devices.get(i));
/* 46:42 */       treeNode0.add(treeNode);
/* 47:   */     }
/* 48:44 */     setModel(new DefaultTreeModel(treeNode0));
/* 49:45 */     MyCellNodeRenderer renderer = new MyCellNodeRenderer();
/* 50:46 */     renderer.setBackgroundNonSelectionColor(new Color(102, 102, 102));
/* 51:47 */     renderer.setBackgroundSelectionColor(new Color(250, 250, 250));
/* 52:48 */     renderer.setTextNonSelectionColor(new Color(250, 250, 250));
/* 53:49 */     renderer.setTextSelectionColor(new Color(0, 0, 0));
/* 54:50 */     setCellRenderer(renderer);
/* 55:   */   }
/* 56:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.VPTree
 * JD-Core Version:    0.7.0.1
 */