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
/*  14:    */ import java.io.PrintStream;
/*  15:    */ import javax.swing.JFileChooser;
/*  16:    */ 
/*  17:    */ public class UpByUserDialog15
/*  18:    */   extends UpByUserDialog
/*  19:    */ {
/*  20:    */   private static final long serialVersionUID = 981628408963933591L;
/*  21: 29 */   private static UpByUserDialog15 dialog = null;
/*  22:    */   
/*  23:    */   public UpByUserDialog15()
/*  24:    */   {
/*  25: 32 */     setTitle("Manually Update");
/*  26: 33 */     initComponents();
/*  27: 34 */     addWindowListener(new WindowAdapter()
/*  28:    */     {
/*  29:    */       public void windowClosing(WindowEvent e)
/*  30:    */       {
/*  31: 37 */         UpgradeUtil.refreshStatus(true, true);
/*  32:    */       }
/*  33: 39 */     });
/*  34: 40 */     setIconImage(Constants.CONNECTEDIMG);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public static UpByUserDialog15 getInstance()
/*  38:    */   {
/*  39: 44 */     if (dialog == null) {
/*  40: 45 */       dialog = new UpByUserDialog15();
/*  41:    */     }
/*  42: 47 */     return dialog;
/*  43:    */   }
/*  44:    */   
/*  45:    */   public void initComponents()
/*  46:    */   {
/*  47: 52 */     this.jPanel1 = new AAPanel();
/*  48: 53 */     this.selectFile = new AALabel();
/*  49: 54 */     this.filepath = new AATextField();
/*  50: 55 */     this.browser = new AAButton();
/*  51: 56 */     this.note = new AALabel();
/*  52: 57 */     this.update = new AAButton();
/*  53: 58 */     setDefaultCloseOperation(2);
/*  54:    */     
/*  55: 60 */     this.selectFile.setText("message.selectfile");
/*  56: 61 */     this.browser.setText("message.browser");
/*  57: 62 */     this.note.setText("message.prepareupgrade");
/*  58: 63 */     this.update.setText("message.upgrade");
/*  59: 64 */     this.update.setEnabled(false);
/*  60: 65 */     this.filepath.setEditable(false);
/*  61: 66 */     this.browser.addActionListener(new ActionListener()
/*  62:    */     {
/*  63:    */       public void actionPerformed(ActionEvent e)
/*  64:    */       {
/*  65: 68 */         int result = UpByUserDialog15.this.getFileDialog();
/*  66: 69 */         if (result == 0)
/*  67:    */         {
/*  68: 70 */           UpByUserDialog15.this.filepathStr = UpByUserDialog15.this.fDialog.getSelectedFile().getPath();
/*  69: 71 */           UpByUserDialog15.this.filepath.setText(UpByUserDialog15.this.filepathStr);
/*  70: 72 */           UpByUserDialog15.this.update.setEnabled(true);
/*  71:    */         }
/*  72:    */       }
/*  73: 76 */     });
/*  74: 77 */     this.update.addActionListener(new ActionListener()
/*  75:    */     {
/*  76:    */       public void actionPerformed(ActionEvent e)
/*  77:    */       {
/*  78: 79 */         UpByUserDialog15.this.updateAction();
/*  79:    */       }
/*  80: 82 */     });
/*  81: 83 */     this.jPanel1.setLayout(null);
/*  82:    */     
/*  83: 85 */     this.jPanel1.add(this.selectFile);
/*  84: 86 */     this.selectFile.setBounds(10, 21, 150, 15);
/*  85: 87 */     this.jPanel1.add(this.filepath);
/*  86: 88 */     this.filepath.setBounds(160, 18, 250, 21);
/*  87:    */     
/*  88: 90 */     this.jPanel1.add(this.browser);
/*  89: 91 */     this.browser.setBounds(420, 17, 100, 23);
/*  90:    */     
/*  91: 93 */     this.jPanel1.add(this.update);
/*  92: 94 */     this.update.setBounds(420, 50, 100, 23);
/*  93:    */     
/*  94: 96 */     this.jPanel1.add(this.note);
/*  95: 97 */     this.note.setBounds(160, 54, 250, 15);
/*  96:    */     
/*  97: 99 */     getContentPane().add(this.jPanel1, "Center");
/*  98:    */     
/*  99:101 */     pack();
/* 100:    */   }
/* 101:    */   
/* 102:    */   public static void main(String[] args)
/* 103:    */   {
/* 104:105 */     UpByUserDialog15 user = new UpByUserDialog15();
/* 105:106 */     user.setSize(550, 120);
/* 106:107 */     user.setVisible(true);
/* 107:    */     
/* 108:109 */     System.out.println(" closeing=" + user.getDefaultCloseOperation());
/* 109:    */     
/* 110:111 */     System.out.println("out of main");
/* 111:    */   }
/* 112:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.UpByUserDialog15
 * JD-Core Version:    0.7.0.1
 */