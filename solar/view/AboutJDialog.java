/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   4:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   5:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   6:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*   7:    */ import java.awt.Container;
/*   8:    */ import java.awt.Frame;
/*   9:    */ import java.awt.event.ActionEvent;
/*  10:    */ import java.awt.event.ActionListener;
/*  11:    */ import javax.swing.GroupLayout;
/*  12:    */ import javax.swing.GroupLayout.Alignment;
/*  13:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  14:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  15:    */ import javax.swing.JLabel;
/*  16:    */ import javax.swing.JPanel;
/*  17:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  18:    */ import javax.swing.border.SoftBevelBorder;
/*  19:    */ 
/*  20:    */ public class AboutJDialog
/*  21:    */   extends AADialog
/*  22:    */ {
/*  23:    */   private static final long serialVersionUID = 1L;
/*  24:    */   private AAButton jButton1;
/*  25:    */   private JLabel jLabel2;
/*  26:    */   private JLabel jLabel4;
/*  27:    */   private JLabel jLabel5;
/*  28:    */   private JLabel jLabel6;
/*  29:    */   private JLabel jLabel7;
/*  30:    */   private JPanel jPanel1;
/*  31:    */   private JPanel jPanel2;
/*  32:    */   
/*  33:    */   public AboutJDialog(Frame parent, boolean modal)
/*  34:    */   {
/*  35: 39 */     super(parent, modal);
/*  36: 40 */     initComponents();
/*  37: 41 */     setTitle("message.about");
/*  38: 42 */     setLocationRelativeTo(null);
/*  39: 43 */     setVisible(true);
/*  40:    */   }
/*  41:    */   
/*  42:    */   private void initComponents()
/*  43:    */   {
/*  44: 48 */     this.jPanel1 = new JPanel();
/*  45: 49 */     this.jButton1 = new AAButton();
/*  46: 50 */     this.jPanel2 = new JPanel();
/*  47:    */     
/*  48: 52 */     this.jLabel2 = new JLabel();
/*  49:    */     
/*  50:    */ 
/*  51: 55 */     this.jLabel4 = new JLabel();
/*  52: 56 */     this.jLabel5 = new JLabel();
/*  53: 57 */     this.jLabel6 = new JLabel();
/*  54: 58 */     this.jLabel7 = new JLabel();
/*  55: 59 */     setDefaultCloseOperation(2);
/*  56:    */     
/*  57: 61 */     this.jPanel1.setBorder(new SoftBevelBorder(0));
/*  58:    */     
/*  59: 63 */     this.jButton1.setText("message.close");
/*  60: 64 */     this.jButton1.addActionListener(new ActionListener()
/*  61:    */     {
/*  62:    */       public void actionPerformed(ActionEvent evt)
/*  63:    */       {
/*  64: 66 */         AboutJDialog.this.close();
/*  65:    */       }
/*  66: 69 */     });
/*  67: 70 */     GroupLayout southPanelLayout = new GroupLayout(this.jPanel1);
/*  68: 71 */     this.jPanel1.setLayout(southPanelLayout);
/*  69: 72 */     southPanelLayout.setHorizontalGroup(
/*  70: 73 */       southPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  71: 74 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/*  72: 75 */       southPanelLayout.createSequentialGroup().addContainerGap(327, 32767)
/*  73: 76 */       .addComponent(this.jButton1)
/*  74: 77 */       .addContainerGap()));
/*  75:    */     
/*  76: 79 */     southPanelLayout.setVerticalGroup(
/*  77: 80 */       southPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  78: 81 */       .addGroup(southPanelLayout.createSequentialGroup()
/*  79: 82 */       .addContainerGap()
/*  80: 83 */       .addComponent(this.jButton1)
/*  81: 84 */       .addContainerGap(-1, 32767)));
/*  82:    */     
/*  83:    */ 
/*  84: 87 */     getContentPane().add(this.jPanel1, "Center");
/*  85:    */     
/*  86: 89 */     this.jPanel2.setBorder(new SoftBevelBorder(0));
/*  87:    */     
/*  88:    */ 
/*  89:    */ 
/*  90: 93 */     this.jLabel2.setText("'" + GlobalVariables.customerConfig.getVersion() + "'");
/*  91:    */     
/*  92: 95 */     this.jLabel4.setText("Powered by;");
/*  93:    */     
/*  94: 97 */     this.jLabel5.setText("Copyright(c)2005 Sun Microsystem,Inc,4150");
/*  95:    */     
/*  96: 99 */     this.jLabel6.setText("NetWork Circle,Santa Clara,California 95054,U.S.A.All");
/*  97:    */     
/*  98:101 */     this.jLabel7.setText("rights reserved.U.S.");
/*  99:    */     
/* 100:103 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 101:104 */     this.jPanel2.setLayout(jPanel2Layout);
/* 102:105 */     jPanel2Layout.setHorizontalGroup(
/* 103:106 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 104:107 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 105:108 */       .addContainerGap()
/* 106:109 */       .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 107:110 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 108:    */       
/* 109:    */ 
/* 110:113 */       .addComponent(this.jLabel2))
/* 111:    */       
/* 112:115 */       .addComponent(this.jLabel4)
/* 113:116 */       .addComponent(this.jLabel5)
/* 114:117 */       .addComponent(this.jLabel6)
/* 115:118 */       .addComponent(this.jLabel7))
/* 116:    */       
/* 117:120 */       .addContainerGap(40, 32767)));
/* 118:    */     
/* 119:122 */     jPanel2Layout.setVerticalGroup(
/* 120:123 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 121:124 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 122:125 */       .addGap(23, 23, 23)
/* 123:126 */       .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 124:    */       
/* 125:128 */       .addComponent(this.jLabel2))
/* 126:129 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 127:    */       
/* 128:131 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 129:132 */       .addComponent(this.jLabel4)
/* 130:133 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 131:134 */       .addComponent(this.jLabel5)
/* 132:135 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 133:136 */       .addComponent(this.jLabel6)
/* 134:137 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 135:138 */       .addComponent(this.jLabel7)
/* 136:139 */       .addContainerGap(19, 32767)));
/* 137:    */     
/* 138:    */ 
/* 139:142 */     getContentPane().add(this.jPanel2, "First");
/* 140:    */     
/* 141:144 */     pack();
/* 142:    */   }
/* 143:    */   
/* 144:    */   private void close()
/* 145:    */   {
/* 146:148 */     dispose();
/* 147:    */   }
/* 148:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.AboutJDialog
 * JD-Core Version:    0.7.0.1
 */