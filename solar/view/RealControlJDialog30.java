/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   5:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*   6:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   7:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   8:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   9:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  10:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  11:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  12:    */ import java.awt.Container;
/*  13:    */ import java.awt.Frame;
/*  14:    */ import java.awt.event.ActionEvent;
/*  15:    */ import java.awt.event.ActionListener;
/*  16:    */ import javax.swing.BorderFactory;
/*  17:    */ import javax.swing.GroupLayout;
/*  18:    */ import javax.swing.GroupLayout.Alignment;
/*  19:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  20:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  21:    */ import javax.swing.JPanel;
/*  22:    */ import javax.swing.JRadioButton;
/*  23:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  24:    */ 
/*  25:    */ public class RealControlJDialog30
/*  26:    */   extends AADialog
/*  27:    */ {
/*  28:    */   private static final long serialVersionUID = -5986305231916605918L;
/*  29: 33 */   private AbstractProcessor processor = null;
/*  30:    */   private AAButton applyButton;
/*  31:    */   private AAButton cancelButton;
/*  32:    */   private JRadioButton disableRadioButton;
/*  33:    */   private JRadioButton enableRadioButton;
/*  34:    */   private AALabel jLabel2;
/*  35:    */   private JPanel jPanel1;
/*  36:    */   private JPanel jPanel2;
/*  37:    */   
/*  38:    */   public RealControlJDialog30(Frame parent, boolean modal)
/*  39:    */   {
/*  40: 43 */     super(parent, modal);
/*  41: 44 */     initComponents();
/*  42: 45 */     setTitle("message.realTimeCtrl");
/*  43: 46 */     setLocationRelativeTo(null);
/*  44: 47 */     setVisible(true);
/*  45:    */   }
/*  46:    */   
/*  47:    */   private void initComponents()
/*  48:    */   {
/*  49: 52 */     this.jPanel1 = new JPanel();
/*  50: 53 */     this.jLabel2 = new AALabel();
/*  51: 54 */     this.enableRadioButton = new JRadioButton();
/*  52: 55 */     this.disableRadioButton = new JRadioButton();
/*  53: 56 */     this.applyButton = new AAButton();
/*  54: 57 */     this.jPanel2 = new JPanel();
/*  55: 58 */     this.cancelButton = new AAButton();
/*  56:    */     
/*  57: 60 */     setDefaultCloseOperation(2);
/*  58:    */     
/*  59: 62 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*  60:    */     
/*  61: 64 */     this.jLabel2.setText("message.acoutput[:]");
/*  62:    */     
/*  63: 66 */     this.enableRadioButton.setText("ON");
/*  64: 67 */     this.enableRadioButton.setSelected(true);
/*  65: 68 */     this.enableRadioButton.addActionListener(new ActionListener()
/*  66:    */     {
/*  67:    */       public void actionPerformed(ActionEvent e)
/*  68:    */       {
/*  69: 71 */         RealControlJDialog30.this.enableRadioButton.setSelected(true);
/*  70: 72 */         RealControlJDialog30.this.disableRadioButton.setSelected(false);
/*  71:    */       }
/*  72: 75 */     });
/*  73: 76 */     this.disableRadioButton.setText("OFF");
/*  74: 77 */     this.disableRadioButton.setSelected(false);
/*  75: 78 */     this.disableRadioButton.addActionListener(new ActionListener()
/*  76:    */     {
/*  77:    */       public void actionPerformed(ActionEvent e)
/*  78:    */       {
/*  79: 81 */         RealControlJDialog30.this.enableRadioButton.setSelected(false);
/*  80: 82 */         RealControlJDialog30.this.disableRadioButton.setSelected(true);
/*  81:    */       }
/*  82: 85 */     });
/*  83: 86 */     initEnable();
/*  84:    */     
/*  85: 88 */     this.applyButton.setText("message.apply");
/*  86: 89 */     this.applyButton.addActionListener(new ActionListener()
/*  87:    */     {
/*  88:    */       public void actionPerformed(ActionEvent e)
/*  89:    */       {
/*  90: 92 */         if (!SolarPowerTray.isLogin)
/*  91:    */         {
/*  92: 93 */           new LoginJDialog(new Frame(), true);
/*  93: 94 */           return;
/*  94:    */         }
/*  95: 96 */         RealControlJDialog30.this.applyAction(e);
/*  96:    */       }
/*  97: 99 */     });
/*  98:100 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  99:101 */     this.jPanel1.setLayout(jPanel1Layout);
/* 100:102 */     jPanel1Layout.setHorizontalGroup(
/* 101:103 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 102:104 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 103:105 */       .addGap(43, 43, 43)
/* 104:106 */       .addComponent(this.jLabel2)
/* 105:107 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 106:108 */       .addComponent(this.enableRadioButton)
/* 107:109 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 108:110 */       .addComponent(this.disableRadioButton)
/* 109:111 */       .addGap(6, 6, 6)
/* 110:112 */       .addComponent(this.applyButton)
/* 111:113 */       .addContainerGap(167, 32767)));
/* 112:    */     
/* 113:115 */     jPanel1Layout.setVerticalGroup(
/* 114:116 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 115:117 */       .addGroup(jPanel1Layout.createSequentialGroup()
/* 116:118 */       .addGap(23, 23, 23)
/* 117:119 */       .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.CENTER)
/* 118:120 */       .addComponent(this.jLabel2)
/* 119:121 */       .addComponent(this.enableRadioButton)
/* 120:122 */       .addComponent(this.disableRadioButton)
/* 121:123 */       .addComponent(this.applyButton))
/* 122:124 */       .addContainerGap(203, 32767)));
/* 123:    */     
/* 124:    */ 
/* 125:127 */     getContentPane().add(this.jPanel1, "Center");
/* 126:    */     
/* 127:129 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 128:    */     
/* 129:131 */     this.cancelButton.setText("message.cancel");
/* 130:132 */     this.cancelButton.addActionListener(new ActionListener()
/* 131:    */     {
/* 132:    */       public void actionPerformed(ActionEvent e)
/* 133:    */       {
/* 134:135 */         RealControlJDialog30.this.dispose();
/* 135:    */       }
/* 136:138 */     });
/* 137:139 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 138:140 */     this.jPanel2.setLayout(jPanel2Layout);
/* 139:141 */     jPanel2Layout.setHorizontalGroup(
/* 140:142 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 141:143 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 142:144 */       jPanel2Layout.createSequentialGroup().addContainerGap(346, 32767)
/* 143:145 */       .addComponent(this.cancelButton)
/* 144:146 */       .addContainerGap()));
/* 145:    */     
/* 146:148 */     jPanel2Layout.setVerticalGroup(
/* 147:149 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 148:150 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 149:151 */       .addContainerGap()
/* 150:152 */       .addComponent(this.cancelButton)
/* 151:153 */       .addContainerGap(-1, 32767)));
/* 152:    */     
/* 153:    */ 
/* 154:156 */     getContentPane().add(this.jPanel2, "Last");
/* 155:    */     
/* 156:158 */     pack();
/* 157:    */   }
/* 158:    */   
/* 159:    */   private void initEnable()
/* 160:    */   {
/* 161:162 */     this.processor = GlobalProcessors.getCurrentProcessor();
/* 162:163 */     if (this.processor != null)
/* 163:    */     {
/* 164:164 */       WorkInfo workInfo = (WorkInfo)this.processor.getBeanBag().getBean("workinfo");
/* 165:165 */       if (workInfo.isHasLoad())
/* 166:    */       {
/* 167:166 */         this.enableRadioButton.setSelected(true);
/* 168:167 */         this.disableRadioButton.setSelected(false);
/* 169:    */       }
/* 170:    */       else
/* 171:    */       {
/* 172:169 */         this.enableRadioButton.setSelected(false);
/* 173:170 */         this.disableRadioButton.setSelected(true);
/* 174:    */       }
/* 175:    */     }
/* 176:    */   }
/* 177:    */   
/* 178:    */   private void applyAction(ActionEvent e)
/* 179:    */   {
/* 180:176 */     this.applyButton.setEnabled(false);
/* 181:177 */     boolean result = false;
/* 182:178 */     if (this.processor == null) {
/* 183:179 */       this.processor = GlobalProcessors.getCurrentProcessor();
/* 184:    */     }
/* 185:181 */     if (this.processor != null) {
/* 186:182 */       if (this.enableRadioButton.isSelected()) {
/* 187:183 */         result = this.processor.executeControl("remoteTurnOn", null);
/* 188:184 */       } else if (this.disableRadioButton.isSelected()) {
/* 189:185 */         result = this.processor.executeControl("remoteTurnOff", null);
/* 190:    */       }
/* 191:    */     }
/* 192:188 */     if (result) {
/* 193:189 */       DisplayMessage.showInfoDialog("message.setTrue");
/* 194:    */     } else {
/* 195:191 */       DisplayMessage.showErrorDialog("message.setFalse");
/* 196:    */     }
/* 197:193 */     this.applyButton.setEnabled(true);
/* 198:    */   }
/* 199:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.RealControlJDialog30
 * JD-Core Version:    0.7.0.1
 */