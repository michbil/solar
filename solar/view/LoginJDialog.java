/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.PasswordConfig;
/*   5:    */ import cn.com.voltronic.solar.constants.Constants;
/*   6:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   7:    */ import cn.com.voltronic.solar.util.MD5Util;
/*   8:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   9:    */ import cn.com.voltronic.solar.view.component.AAButtonTip;
/*  10:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  11:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  12:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  13:    */ import java.awt.Container;
/*  14:    */ import java.awt.Frame;
/*  15:    */ import java.awt.event.ActionEvent;
/*  16:    */ import java.awt.event.ActionListener;
/*  17:    */ import java.awt.event.KeyAdapter;
/*  18:    */ import java.awt.event.KeyEvent;
/*  19:    */ import javax.swing.GroupLayout;
/*  20:    */ import javax.swing.GroupLayout.Alignment;
/*  21:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  22:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  23:    */ import javax.swing.ImageIcon;
/*  24:    */ import javax.swing.JPasswordField;
/*  25:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  26:    */ 
/*  27:    */ public class LoginJDialog
/*  28:    */   extends AADialog
/*  29:    */ {
/*  30:    */   private static final long serialVersionUID = -4317316281009735517L;
/*  31:    */   private AAButton loginButton;
/*  32:    */   private AAButton clearButton;
/*  33:    */   private AALabel jLabel1;
/*  34:    */   private AALabel jLabel2;
/*  35:    */   private JPasswordField jPasswordField1;
/*  36:    */   
/*  37:    */   public LoginJDialog(Frame parent, boolean modal)
/*  38:    */   {
/*  39: 41 */     super(parent, modal);
/*  40: 42 */     initComponents();
/*  41: 43 */     setTitle("message.login");
/*  42: 44 */     setLocationRelativeTo(null);
/*  43: 45 */     setVisible(true);
/*  44:    */   }
/*  45:    */   
/*  46:    */   private void initComponents()
/*  47:    */   {
/*  48: 50 */     this.jLabel1 = new AALabel();
/*  49: 51 */     this.jLabel2 = new AALabel();
/*  50: 52 */     this.jPasswordField1 = new JPasswordField();
/*  51: 53 */     this.loginButton = new AAButton();
/*  52: 54 */     this.clearButton = new AAButton();
/*  53:    */     
/*  54: 56 */     setDefaultCloseOperation(2);
/*  55:    */     
/*  56: 58 */     this.jLabel1.setText("message.loginfirst");
/*  57:    */     
/*  58: 60 */     this.jLabel2.setText("message.password[:]");
/*  59:    */     
/*  60: 62 */     this.jPasswordField1.addKeyListener(new KeyAdapter()
/*  61:    */     {
/*  62:    */       public void keyPressed(KeyEvent e)
/*  63:    */       {
/*  64: 65 */         if (e.getKeyCode() == 10) {
/*  65: 66 */           LoginJDialog.this.login();
/*  66:    */         }
/*  67:    */       }
/*  68: 70 */     });
/*  69: 71 */     this.loginButton.setText("message.login");
/*  70: 72 */     this.loginButton.addKeyListener(new KeyAdapter()
/*  71:    */     {
/*  72:    */       public void keyPressed(KeyEvent e)
/*  73:    */       {
/*  74: 75 */         if (e.getKeyCode() == 10) {
/*  75: 76 */           LoginJDialog.this.login();
/*  76:    */         }
/*  77:    */       }
/*  78: 79 */     });
/*  79: 80 */     this.loginButton.addActionListener(new ActionListener()
/*  80:    */     {
/*  81:    */       public void actionPerformed(ActionEvent e)
/*  82:    */       {
/*  83: 83 */         LoginJDialog.this.login();
/*  84:    */       }
/*  85: 86 */     });
/*  86: 87 */     this.clearButton.setText("message.reset");
/*  87: 88 */     this.clearButton.addActionListener(new ActionListener()
/*  88:    */     {
/*  89:    */       public void actionPerformed(ActionEvent e)
/*  90:    */       {
/*  91: 91 */         LoginJDialog.this.clear();
/*  92:    */       }
/*  93: 94 */     });
/*  94: 95 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  95: 96 */     getContentPane().setLayout(layout);
/*  96: 97 */     layout.setHorizontalGroup(
/*  97: 98 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  98: 99 */       .addGroup(layout.createSequentialGroup()
/*  99:100 */       .addGap(23, 23, 23)
/* 100:101 */       .addComponent(this.jLabel2)
/* 101:102 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 102:103 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 103:104 */       .addGroup(layout.createSequentialGroup()
/* 104:105 */       .addComponent(this.loginButton)
/* 105:106 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 106:107 */       .addComponent(this.clearButton))
/* 107:108 */       .addComponent(this.jLabel1)
/* 108:109 */       .addComponent(this.jPasswordField1, -2, 188, -2))
/* 109:110 */       .addContainerGap(43, 32767)));
/* 110:    */     
/* 111:112 */     layout.setVerticalGroup(
/* 112:113 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 113:114 */       .addGroup(layout.createSequentialGroup()
/* 114:115 */       .addGap(24, 24, 24)
/* 115:116 */       .addComponent(this.jLabel1)
/* 116:117 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 117:118 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 118:119 */       .addComponent(this.jLabel2)
/* 119:120 */       .addComponent(this.jPasswordField1, -2, -1, -2))
/* 120:121 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 121:122 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 122:123 */       .addComponent(this.loginButton)
/* 123:124 */       .addComponent(this.clearButton))
/* 124:125 */       .addContainerGap(28, 32767)));
/* 125:    */     
/* 126:    */ 
/* 127:128 */     pack();
/* 128:    */   }
/* 129:    */   
/* 130:    */   private void clear()
/* 131:    */   {
/* 132:132 */     this.jPasswordField1.setText("");
/* 133:133 */     this.jPasswordField1.requestFocus(true);
/* 134:    */   }
/* 135:    */   
/* 136:    */   private void login()
/* 137:    */   {
/* 138:137 */     boolean result = false;
/* 139:138 */     String inputString = new String(this.jPasswordField1.getPassword()).trim();
/* 140:139 */     if (inputString.length() < 6)
/* 141:    */     {
/* 142:140 */       DisplayMessage.showErrorDialog("message.pswLess6");
/* 143:141 */       clear();
/* 144:142 */       return;
/* 145:    */     }
/* 146:144 */     String password = "";
/* 147:    */     try
/* 148:    */     {
/* 149:146 */       ConfigureTools.wrapProperties(GlobalVariables.passwordConfig);
/* 150:147 */       password = GlobalVariables.passwordConfig.getManagerPassword();
/* 151:    */     }
/* 152:    */     catch (Exception e1)
/* 153:    */     {
/* 154:149 */       result = false;
/* 155:    */     }
/* 156:151 */     result = MD5Util.validatePassword(password, inputString);
/* 157:152 */     if (result)
/* 158:    */     {
/* 159:153 */       cn.com.voltronic.solar.console.SolarPowerTray.isLogin = true;
/* 160:154 */       MainJFrame.loginButton.setIcon(new ImageIcon(Constants.LOGOUTIMG));
/* 161:155 */       MainJFrame.loginButton.setToolTipText("message.logout");
/* 162:156 */       MainJFrame.loginLabel.setText("message.localManager");
/* 163:157 */       dispose();
/* 164:    */     }
/* 165:    */     else
/* 166:    */     {
/* 167:159 */       DisplayMessage.showErrorDialog("message.curPswError");
/* 168:160 */       clear();
/* 169:    */     }
/* 170:    */   }
/* 171:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.LoginJDialog
 * JD-Core Version:    0.7.0.1
 */