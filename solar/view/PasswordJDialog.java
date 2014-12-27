/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.PasswordConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   7:    */ import cn.com.voltronic.solar.util.MD5Util;
/*   8:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   9:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  10:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  11:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  12:    */ import java.awt.Container;
/*  13:    */ import java.awt.Frame;
/*  14:    */ import java.awt.event.ActionEvent;
/*  15:    */ import java.awt.event.ActionListener;
/*  16:    */ import javax.swing.GroupLayout;
/*  17:    */ import javax.swing.GroupLayout.Alignment;
/*  18:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  19:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  20:    */ import javax.swing.JPasswordField;
/*  21:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  22:    */ 
/*  23:    */ public class PasswordJDialog
/*  24:    */   extends AADialog
/*  25:    */ {
/*  26:    */   private static final long serialVersionUID = 2192844775828400661L;
/*  27:    */   private AAButton applyButton;
/*  28:    */   private AAButton clearButton;
/*  29:    */   private AALabel jLabel1;
/*  30:    */   private AALabel jLabel2;
/*  31:    */   private AALabel jLabel4;
/*  32:    */   private AALabel jLabel5;
/*  33:    */   private JPasswordField oldPasswordField;
/*  34:    */   private JPasswordField newPasswordField;
/*  35:    */   private JPasswordField confirmPasswordField;
/*  36:    */   
/*  37:    */   public PasswordJDialog(Frame parent, boolean modal)
/*  38:    */   {
/*  39: 42 */     super(parent, modal);
/*  40: 43 */     initComponents();
/*  41: 44 */     setTitle("message.passwordSet");
/*  42: 45 */     setLocationRelativeTo(null);
/*  43: 46 */     setVisible(true);
/*  44:    */   }
/*  45:    */   
/*  46:    */   private void initComponents()
/*  47:    */   {
/*  48: 51 */     this.jLabel1 = new AALabel();
/*  49: 52 */     this.jLabel2 = new AALabel();
/*  50: 53 */     this.oldPasswordField = new JPasswordField();
/*  51: 54 */     this.jLabel4 = new AALabel();
/*  52: 55 */     this.newPasswordField = new JPasswordField();
/*  53: 56 */     this.jLabel5 = new AALabel();
/*  54: 57 */     this.confirmPasswordField = new JPasswordField();
/*  55: 58 */     this.applyButton = new AAButton();
/*  56: 59 */     this.clearButton = new AAButton();
/*  57:    */     
/*  58: 61 */     setDefaultCloseOperation(2);
/*  59:    */     
/*  60: 63 */     this.jLabel1.setText("message.password");
/*  61:    */     
/*  62: 65 */     this.jLabel2.setText("message.currentpassword[:]");
/*  63:    */     
/*  64: 67 */     this.jLabel4.setText("message.newpassword[:]");
/*  65:    */     
/*  66: 69 */     this.jLabel5.setText("message.affirmpassword[:]");
/*  67:    */     
/*  68: 71 */     this.applyButton.setText("message.apply");
/*  69: 72 */     this.applyButton.addActionListener(new ActionListener()
/*  70:    */     {
/*  71:    */       public void actionPerformed(ActionEvent e)
/*  72:    */       {
/*  73: 75 */         if (!SolarPowerTray.isLogin)
/*  74:    */         {
/*  75: 76 */           new LoginJDialog(new Frame(), true);
/*  76: 77 */           return;
/*  77:    */         }
/*  78: 79 */         String inputOldString = new String(PasswordJDialog.this.oldPasswordField.getPassword()).trim();
/*  79: 80 */         String inputNewString = new String(PasswordJDialog.this.newPasswordField.getPassword()).trim();
/*  80: 81 */         String inputConfirmString = new String(PasswordJDialog.this.confirmPasswordField.getPassword()).trim();
/*  81: 82 */         if (inputOldString.length() < 6)
/*  82:    */         {
/*  83: 83 */           DisplayMessage.showErrorDialog("message.pswLess6");
/*  84: 84 */           PasswordJDialog.this.oldPasswordField.requestFocus(true);
/*  85: 85 */           return;
/*  86:    */         }
/*  87: 87 */         if (inputNewString.length() < 6)
/*  88:    */         {
/*  89: 88 */           DisplayMessage.showErrorDialog("message.pswLess6");
/*  90: 89 */           PasswordJDialog.this.newPasswordField.requestFocus(true);
/*  91: 90 */           return;
/*  92:    */         }
/*  93: 92 */         if (inputConfirmString.length() < 6)
/*  94:    */         {
/*  95: 93 */           DisplayMessage.showErrorDialog("message.pswLess6");
/*  96: 94 */           PasswordJDialog.this.confirmPasswordField.requestFocus(true);
/*  97: 95 */           return;
/*  98:    */         }
/*  99: 98 */         String oldPassword = "";
/* 100:    */         try
/* 101:    */         {
/* 102:100 */           oldPassword = GlobalVariables.passwordConfig.getManagerPassword();
/* 103:    */         }
/* 104:    */         catch (Exception e1)
/* 105:    */         {
/* 106:102 */           e1.printStackTrace();
/* 107:    */         }
/* 108:104 */         if (MD5Util.validatePassword(oldPassword, inputOldString))
/* 109:    */         {
/* 110:105 */           if (inputNewString.equals(inputConfirmString))
/* 111:    */           {
/* 112:    */             try
/* 113:    */             {
/* 114:107 */               PasswordConfig config = new PasswordConfig();
/* 115:108 */               config.setManagerPassword(MD5Util.encodeByMD5(inputNewString));
/* 116:109 */               ConfigureTools.updateProperties(config);
/* 117:110 */               ConfigureTools.wrapProperties(GlobalVariables.passwordConfig);
/* 118:111 */               DisplayMessage.showInfoDialog("message.modPswSuc");
/* 119:112 */               PasswordJDialog.this.clearPassword();
/* 120:113 */               PasswordJDialog.this.dispose();
/* 121:    */             }
/* 122:    */             catch (Exception ex)
/* 123:    */             {
/* 124:115 */               DisplayMessage.showErrorDialog("message.modPswFail");
/* 125:    */             }
/* 126:    */           }
/* 127:    */           else
/* 128:    */           {
/* 129:118 */             DisplayMessage.showErrorDialog("message.notSame");
/* 130:119 */             PasswordJDialog.this.newPasswordField.requestFocus(true);
/* 131:    */           }
/* 132:    */         }
/* 133:    */         else
/* 134:    */         {
/* 135:122 */           DisplayMessage.showErrorDialog("message.curPswError");
/* 136:123 */           PasswordJDialog.this.oldPasswordField.requestFocus(true);
/* 137:    */         }
/* 138:    */       }
/* 139:127 */     });
/* 140:128 */     this.clearButton.setText("message.reset");
/* 141:129 */     this.clearButton.addActionListener(new ActionListener()
/* 142:    */     {
/* 143:    */       public void actionPerformed(ActionEvent e)
/* 144:    */       {
/* 145:132 */         PasswordJDialog.this.clearPassword();
/* 146:    */       }
/* 147:135 */     });
/* 148:136 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 149:137 */     getContentPane().setLayout(layout);
/* 150:138 */     layout.setHorizontalGroup(
/* 151:139 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 152:140 */       .addGroup(layout.createSequentialGroup()
/* 153:141 */       .addGap(46, 46, 46)
/* 154:142 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 155:143 */       .addComponent(this.jLabel4)
/* 156:144 */       .addComponent(this.jLabel2)
/* 157:145 */       .addComponent(this.jLabel5))
/* 158:146 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 159:147 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 160:148 */       .addGroup(layout.createSequentialGroup()
/* 161:149 */       .addComponent(this.applyButton)
/* 162:150 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 163:151 */       .addComponent(this.clearButton))
/* 164:152 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 165:153 */       .addComponent(this.jLabel1)
/* 166:154 */       .addComponent(this.confirmPasswordField)
/* 167:155 */       .addComponent(this.newPasswordField)
/* 168:156 */       .addComponent(this.oldPasswordField, -2, 197, -2)))
/* 169:157 */       .addContainerGap(59, 32767)));
/* 170:    */     
/* 171:159 */     layout.setVerticalGroup(
/* 172:160 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 173:161 */       .addGroup(layout.createSequentialGroup()
/* 174:162 */       .addGap(22, 22, 22)
/* 175:163 */       .addComponent(this.jLabel1)
/* 176:164 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 177:165 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 178:166 */       .addComponent(this.jLabel2)
/* 179:167 */       .addComponent(this.oldPasswordField, -2, -1, -2))
/* 180:168 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 181:169 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 182:170 */       .addComponent(this.jLabel4)
/* 183:171 */       .addComponent(this.newPasswordField, -2, -1, -2))
/* 184:172 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 185:173 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 186:174 */       .addComponent(this.jLabel5)
/* 187:175 */       .addComponent(this.confirmPasswordField, -2, -1, -2))
/* 188:176 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 189:177 */       .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 190:178 */       .addComponent(this.applyButton)
/* 191:179 */       .addComponent(this.clearButton))
/* 192:180 */       .addContainerGap(29, 32767)));
/* 193:    */     
/* 194:    */ 
/* 195:183 */     pack();
/* 196:    */   }
/* 197:    */   
/* 198:    */   private void clearPassword()
/* 199:    */   {
/* 200:187 */     this.oldPasswordField.setText("");
/* 201:188 */     this.newPasswordField.setText("");
/* 202:189 */     this.confirmPasswordField.setText("");
/* 203:    */   }
/* 204:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.PasswordJDialog
 * JD-Core Version:    0.7.0.1
 */