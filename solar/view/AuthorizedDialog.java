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
/*  27:    */ public class AuthorizedDialog
/*  28:    */   extends AADialog
/*  29:    */ {
/*  30:    */   private static final long serialVersionUID = -4317316281009735517L;
/*  31:    */   private AAButton loginButton;
/*  32:    */   private AAButton clearButton;
/*  33:    */   private AALabel jLabel1;
/*  34:    */   private AALabel jLabel2;
/*  35:    */   private JPasswordField jPasswordField1;
/*  36:    */   
/*  37:    */   public AuthorizedDialog(Frame parent, boolean modal)
/*  38:    */   {
/*  39: 43 */     super(parent, modal);
/*  40: 44 */     initComponents();
/*  41: 45 */     setTitle("Authorized password");
/*  42: 46 */     setLocationRelativeTo(null);
/*  43: 47 */     setVisible(true);
/*  44:    */   }
/*  45:    */   
/*  46:    */   private void initComponents()
/*  47:    */   {
/*  48: 52 */     this.jLabel1 = new AALabel();
/*  49: 53 */     this.jLabel2 = new AALabel();
/*  50: 54 */     this.jPasswordField1 = new JPasswordField();
/*  51: 55 */     this.loginButton = new AAButton();
/*  52: 56 */     this.clearButton = new AAButton();
/*  53:    */     
/*  54: 58 */     setDefaultCloseOperation(2);
/*  55:    */     
/*  56: 60 */     this.jLabel1.setText("Enter the authorized password.");
/*  57:    */     
/*  58: 62 */     this.jLabel2.setText("message.password[:]");
/*  59:    */     
/*  60: 64 */     this.jPasswordField1.addKeyListener(new KeyAdapter()
/*  61:    */     {
/*  62:    */       public void keyPressed(KeyEvent e)
/*  63:    */       {
/*  64: 67 */         if (e.getKeyCode() == 10) {
/*  65: 68 */           AuthorizedDialog.this.login();
/*  66:    */         }
/*  67:    */       }
/*  68: 72 */     });
/*  69: 73 */     this.loginButton.setText("OK");
/*  70: 74 */     this.loginButton.addKeyListener(new KeyAdapter()
/*  71:    */     {
/*  72:    */       public void keyPressed(KeyEvent e)
/*  73:    */       {
/*  74: 77 */         if (e.getKeyCode() == 10) {
/*  75: 78 */           AuthorizedDialog.this.login();
/*  76:    */         }
/*  77:    */       }
/*  78: 81 */     });
/*  79: 82 */     this.loginButton.addActionListener(new ActionListener()
/*  80:    */     {
/*  81:    */       public void actionPerformed(ActionEvent e)
/*  82:    */       {
/*  83: 85 */         AuthorizedDialog.this.login();
/*  84:    */       }
/*  85: 88 */     });
/*  86: 89 */     this.clearButton.setText("message.reset");
/*  87: 90 */     this.clearButton.addActionListener(new ActionListener()
/*  88:    */     {
/*  89:    */       public void actionPerformed(ActionEvent e)
/*  90:    */       {
/*  91: 93 */         AuthorizedDialog.this.clear();
/*  92:    */       }
/*  93: 96 */     });
/*  94: 97 */     GroupLayout layout = new GroupLayout(getContentPane());
/*  95: 98 */     getContentPane().setLayout(layout);
/*  96: 99 */     layout.setHorizontalGroup(
/*  97:100 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  98:101 */       .addGroup(layout.createSequentialGroup()
/*  99:102 */       .addGap(23, 23, 23)
/* 100:103 */       .addComponent(this.jLabel2)
/* 101:104 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 102:105 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 103:106 */       .addGroup(layout.createSequentialGroup()
/* 104:107 */       .addComponent(this.loginButton)
/* 105:108 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 106:109 */       .addComponent(this.clearButton))
/* 107:110 */       .addComponent(this.jLabel1)
/* 108:111 */       .addComponent(this.jPasswordField1, -2, 188, -2))
/* 109:112 */       .addContainerGap(43, 32767)));
/* 110:    */     
/* 111:114 */     layout.setVerticalGroup(
/* 112:115 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 113:116 */       .addGroup(layout.createSequentialGroup()
/* 114:117 */       .addGap(24, 24, 24)
/* 115:118 */       .addComponent(this.jLabel1)
/* 116:119 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 117:120 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 118:121 */       .addComponent(this.jLabel2)
/* 119:122 */       .addComponent(this.jPasswordField1, -2, -1, -2))
/* 120:123 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 121:124 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 122:125 */       .addComponent(this.loginButton)
/* 123:126 */       .addComponent(this.clearButton))
/* 124:127 */       .addContainerGap(28, 32767)));
/* 125:    */     
/* 126:    */ 
/* 127:130 */     pack();
/* 128:    */   }
/* 129:    */   
/* 130:    */   private void clear()
/* 131:    */   {
/* 132:134 */     this.jPasswordField1.setText("");
/* 133:135 */     this.jPasswordField1.requestFocus(true);
/* 134:    */   }
/* 135:    */   
/* 136:    */   private void login()
/* 137:    */   {
/* 138:139 */     boolean result = false;
/* 139:140 */     String inputString = new String(this.jPasswordField1.getPassword()).trim();
/* 140:141 */     if (inputString.length() < 6)
/* 141:    */     {
/* 142:142 */       DisplayMessage.showErrorDialog("message.pswLess6");
/* 143:143 */       clear();
/* 144:144 */       return;
/* 145:    */     }
/* 146:146 */     String password = "";
/* 147:    */     try
/* 148:    */     {
/* 149:148 */       ConfigureTools.wrapProperties(GlobalVariables.passwordConfig);
/* 150:149 */       password = GlobalVariables.passwordConfig.getManagerPassword();
/* 151:    */     }
/* 152:    */     catch (Exception e1)
/* 153:    */     {
/* 154:151 */       result = false;
/* 155:    */     }
/* 156:153 */     result = MD5Util.validatePassword(password, inputString);
/* 157:154 */     if (result)
/* 158:    */     {
/* 159:155 */       cn.com.voltronic.solar.console.SolarPowerTray.isLogin = true;
/* 160:156 */       MainJFrame.loginButton.setIcon(new ImageIcon(Constants.LOGOUTIMG));
/* 161:157 */       MainJFrame.loginButton.setToolTipText("message.logout");
/* 162:158 */       MainJFrame.loginLabel.setText("message.localManager");
/* 163:159 */       dispose();
/* 164:    */     }
/* 165:    */     else
/* 166:    */     {
/* 167:161 */       DisplayMessage.showErrorDialog("message.curPswError");
/* 168:162 */       clear();
/* 169:    */     }
/* 170:    */   }
/* 171:    */   
/* 172:    */   public static void main(String[] args)
/* 173:    */   {
/* 174:167 */     AuthorizedDialog dialog = new AuthorizedDialog(null, false);
/* 175:168 */     dialog.setVisible(true);
/* 176:    */   }
/* 177:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.AuthorizedDialog
 * JD-Core Version:    0.7.0.1
 */