/*   1:    */ package cn.com.voltronic.solar.upgrade;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.constants.Constants;
/*   4:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   5:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*   6:    */ import cn.com.voltronic.solar.view.component.AAPanel;
/*   7:    */ import cn.com.voltronic.solar.view.component.AATextField;
/*   8:    */ import java.awt.Container;
/*   9:    */ import java.awt.event.ActionEvent;
/*  10:    */ import java.awt.event.ActionListener;
/*  11:    */ import java.awt.event.WindowAdapter;
/*  12:    */ import java.awt.event.WindowEvent;
/*  13:    */ import java.io.File;
/*  14:    */ import javax.swing.GroupLayout;
/*  15:    */ import javax.swing.GroupLayout.Alignment;
/*  16:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  17:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  18:    */ import javax.swing.JFileChooser;
/*  19:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  20:    */ 
/*  21:    */ public class UpByUserDialog16
/*  22:    */   extends UpByUserDialog
/*  23:    */ {
/*  24:    */   private static final long serialVersionUID = 981628408963933591L;
/*  25: 28 */   private static UpByUserDialog16 dialog = null;
/*  26:    */   
/*  27:    */   public UpByUserDialog16()
/*  28:    */   {
/*  29: 31 */     setTitle("Manually Update");
/*  30: 32 */     initComponents();
/*  31: 33 */     addWindowListener(new WindowAdapter()
/*  32:    */     {
/*  33:    */       public void windowClosing(WindowEvent e)
/*  34:    */       {
/*  35: 36 */         UpgradeUtil.refreshStatus(true, true);
/*  36:    */       }
/*  37: 39 */     });
/*  38: 40 */     setIconImage(Constants.CONNECTEDIMG);
/*  39:    */   }
/*  40:    */   
/*  41:    */   public static UpByUserDialog16 getInstance()
/*  42:    */   {
/*  43: 44 */     if (dialog == null) {
/*  44: 45 */       dialog = new UpByUserDialog16();
/*  45:    */     }
/*  46: 47 */     return dialog;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void initComponents()
/*  50:    */   {
/*  51: 52 */     this.jPanel1 = new AAPanel();
/*  52: 53 */     this.selectFile = new AALabel();
/*  53: 54 */     this.filepath = new AATextField();
/*  54: 55 */     this.browser = new AAButton();
/*  55: 56 */     this.note = new AALabel();
/*  56: 57 */     this.update = new AAButton();
/*  57: 58 */     this.selectFile.setText("message.selectfile");
/*  58: 59 */     this.browser.setText("message.browser");
/*  59: 60 */     this.note.setText("message.prepareupgrade");
/*  60: 61 */     this.update.setText("message.upgrade");
/*  61: 62 */     this.update.setEnabled(false);
/*  62: 63 */     this.filepath.setEditable(false);
/*  63: 64 */     setDefaultCloseOperation(2);
/*  64: 65 */     this.browser.addActionListener(new ActionListener()
/*  65:    */     {
/*  66:    */       public void actionPerformed(ActionEvent e)
/*  67:    */       {
/*  68: 67 */         int result = UpByUserDialog16.this.getFileDialog();
/*  69: 68 */         if (result == 0)
/*  70:    */         {
/*  71: 69 */           UpByUserDialog16.this.filepathStr = UpByUserDialog16.this.fDialog.getSelectedFile().getPath();
/*  72: 70 */           UpByUserDialog16.this.filepath.setText(UpByUserDialog16.this.filepathStr);
/*  73: 71 */           UpByUserDialog16.this.update.setEnabled(true);
/*  74:    */         }
/*  75:    */       }
/*  76: 75 */     });
/*  77: 76 */     this.update.addActionListener(new ActionListener()
/*  78:    */     {
/*  79:    */       public void actionPerformed(ActionEvent e)
/*  80:    */       {
/*  81: 78 */         if ((UpByUserDialog16.this.filepathStr != null) && (!"".equals(UpByUserDialog16.this.filepathStr))) {
/*  82: 79 */           UpByUserDialog16.this.updateAction();
/*  83:    */         }
/*  84:    */       }
/*  85: 82 */     });
/*  86: 83 */     setLayOut();
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void setLayOut()
/*  90:    */   {
/*  91: 90 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  92: 91 */     this.jPanel1.setLayout(jPanel1Layout);
/*  93: 92 */     jPanel1Layout.setHorizontalGroup(
/*  94: 93 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  95: 94 */       .addGroup(jPanel1Layout.createSequentialGroup()
/*  96: 95 */       .addContainerGap()
/*  97: 96 */       .addComponent(this.selectFile)
/*  98: 97 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  99: 98 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 100: 99 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 101:100 */       .addComponent(this.note)
/* 102:101 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 103:102 */       .addComponent(this.update))
/* 104:103 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 105:104 */       .addComponent(this.filepath, -2, 194, -2)
/* 106:105 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 107:106 */       .addComponent(this.browser)))
/* 108:107 */       .addContainerGap(15, 32767)));
/* 109:    */     
/* 110:109 */     jPanel1Layout.setVerticalGroup(
/* 111:110 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 112:111 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 113:112 */       jPanel1Layout.createSequentialGroup().addContainerGap(17, 32767)
/* 114:113 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 115:114 */       .addComponent(this.selectFile)
/* 116:115 */       .addComponent(this.filepath, -2, -1, -2)
/* 117:116 */       .addComponent(this.browser))
/* 118:117 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 119:118 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 120:119 */       .addComponent(this.update)
/* 121:120 */       .addComponent(this.note))
/* 122:121 */       .addContainerGap()));
/* 123:    */     
/* 124:123 */     getContentPane().add(this.jPanel1, "Center");
/* 125:124 */     pack();
/* 126:    */   }
/* 127:    */   
/* 128:    */   public static void main(String[] args)
/* 129:    */   {
/* 130:128 */     new UpByUserDialog16().setVisible(true);
/* 131:    */   }
/* 132:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.UpByUserDialog16
 * JD-Core Version:    0.7.0.1
 */