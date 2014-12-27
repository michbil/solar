/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.constants.Constants;
/*   4:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   5:    */ import cn.com.voltronic.solar.view.component.AAFrame;
/*   6:    */ import java.awt.BorderLayout;
/*   7:    */ import java.awt.Color;
/*   8:    */ import java.awt.Container;
/*   9:    */ import javax.swing.GroupLayout;
/*  10:    */ import javax.swing.GroupLayout.Alignment;
/*  11:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  12:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  13:    */ import javax.swing.JPanel;
/*  14:    */ import javax.swing.JScrollPane;
/*  15:    */ import javax.swing.JTextPane;
/*  16:    */ 
/*  17:    */ public class EventInfoDialog
/*  18:    */   extends AAFrame
/*  19:    */ {
/*  20:    */   private static final long serialVersionUID = -1371140376082178190L;
/*  21:    */   private JTextPane eventTextPanel;
/*  22:    */   public AAButton jButton1;
/*  23:    */   private JPanel jPanel1;
/*  24:    */   private JPanel jPanel3;
/*  25:    */   private JPanel jPanel5;
/*  26:    */   private JScrollPane jScrollPane1;
/*  27: 29 */   private static EventInfoDialog dialog = null;
/*  28:    */   
/*  29:    */   public JTextPane getEventTextPanel()
/*  30:    */   {
/*  31: 32 */     return this.eventTextPanel;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void setEventTextPanel(JTextPane eventTextPanel)
/*  35:    */   {
/*  36: 36 */     this.eventTextPanel = eventTextPanel;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public static EventInfoDialog getInstance()
/*  40:    */   {
/*  41: 40 */     if (dialog == null) {
/*  42: 41 */       dialog = new EventInfoDialog();
/*  43:    */     }
/*  44: 43 */     dialog.setTitle("message.warningMessage");
/*  45: 44 */     return dialog;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public EventInfoDialog()
/*  49:    */   {
/*  50: 48 */     setTitle("message.warningMessage");
/*  51: 49 */     setIconImage(Constants.CONNECTEDIMG);
/*  52: 50 */     initComponents();
/*  53: 51 */     setLocationRelativeTo(null);
/*  54:    */   }
/*  55:    */   
/*  56:    */   private void initComponents()
/*  57:    */   {
/*  58: 56 */     this.jPanel3 = new JPanel();
/*  59: 57 */     this.jPanel1 = new JPanel();
/*  60: 58 */     this.jScrollPane1 = new JScrollPane();
/*  61: 59 */     this.eventTextPanel = new JTextPane();
/*  62: 60 */     this.jPanel5 = new JPanel();
/*  63: 61 */     this.jButton1 = new AAButton();
/*  64:    */     
/*  65: 63 */     this.jPanel3.setLayout(new BorderLayout());
/*  66:    */     
/*  67: 65 */     this.eventTextPanel.setEditable(false);
/*  68: 66 */     this.eventTextPanel.setForeground(Color.white);
/*  69: 67 */     this.jScrollPane1.setViewportView(this.eventTextPanel);
/*  70:    */     
/*  71: 69 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  72: 70 */     this.jPanel1.setLayout(jPanel1Layout);
/*  73: 71 */     jPanel1Layout.setHorizontalGroup(
/*  74: 72 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  75: 73 */       .addComponent(this.jScrollPane1, -1, 455, 32767));
/*  76:    */     
/*  77: 75 */     jPanel1Layout.setVerticalGroup(
/*  78: 76 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  79: 77 */       .addComponent(this.jScrollPane1, -1, 400, 32767));
/*  80:    */     
/*  81:    */ 
/*  82: 80 */     this.jPanel3.add(this.jPanel1, "Center");
/*  83:    */     
/*  84: 82 */     this.jButton1.setText("message.close");
/*  85:    */     
/*  86: 84 */     GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
/*  87: 85 */     this.jPanel5.setLayout(jPanel5Layout);
/*  88: 86 */     jPanel5Layout.setHorizontalGroup(
/*  89: 87 */       jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  90: 88 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/*  91: 89 */       jPanel5Layout.createSequentialGroup().addContainerGap(388, 32767)
/*  92: 90 */       .addComponent(this.jButton1)
/*  93: 91 */       .addContainerGap()));
/*  94:    */     
/*  95: 93 */     jPanel5Layout.setVerticalGroup(
/*  96: 94 */       jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  97: 95 */       .addGroup(jPanel5Layout.createSequentialGroup()
/*  98: 96 */       .addContainerGap()
/*  99: 97 */       .addComponent(this.jButton1)
/* 100: 98 */       .addContainerGap(-1, 32767)));
/* 101:    */     
/* 102:    */ 
/* 103:101 */     this.jPanel3.add(this.jPanel5, "South");
/* 104:    */     
/* 105:103 */     getContentPane().add(this.jPanel3, "Center");
/* 106:    */     
/* 107:105 */     pack();
/* 108:    */   }
/* 109:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.EventInfoDialog
 * JD-Core Version:    0.7.0.1
 */