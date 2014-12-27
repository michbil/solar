/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   5:    */ import cn.com.voltronic.solar.constants.Constants;
/*   6:    */ import cn.com.voltronic.solar.data.bean.AutoLabelItem;
/*   7:    */ import cn.com.voltronic.solar.data.bean.DefaultData;
/*   8:    */ import cn.com.voltronic.solar.data.bean.RestoreInfo;
/*   9:    */ import cn.com.voltronic.solar.exception.EventsHandler;
/*  10:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  11:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  12:    */ import cn.com.voltronic.solar.protocol.P30;
/*  13:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  14:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  15:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  16:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  17:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  18:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  19:    */ import java.awt.BorderLayout;
/*  20:    */ import java.awt.Container;
/*  21:    */ import java.awt.Frame;
/*  22:    */ import java.awt.event.ActionEvent;
/*  23:    */ import java.awt.event.ActionListener;
/*  24:    */ import java.awt.event.WindowAdapter;
/*  25:    */ import java.awt.event.WindowEvent;
/*  26:    */ import java.util.Date;
/*  27:    */ import java.util.List;
/*  28:    */ import javax.swing.BorderFactory;
/*  29:    */ import javax.swing.GroupLayout;
/*  30:    */ import javax.swing.GroupLayout.Alignment;
/*  31:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  32:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  33:    */ import javax.swing.ImageIcon;
/*  34:    */ import javax.swing.JPanel;
/*  35:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  36:    */ 
/*  37:    */ public class RestoreJDialog
/*  38:    */   extends AADialog
/*  39:    */ {
/*  40:    */   private static final long serialVersionUID = 2406300060632727302L;
/*  41:    */   private AAButton cancelButton;
/*  42:    */   private AAButton restoreButton;
/*  43:    */   private AALabel jLabel38;
/*  44:    */   private AALabel jLabel39;
/*  45:    */   private JPanel jPanel1;
/*  46:    */   private JPanel jPanel2;
/*  47:    */   private JPanel jPanel3;
/*  48:    */   private JPanel jPanel4;
/*  49:    */   private IProtocol protocol;
/*  50:    */   public DefaultData defaultData;
/*  51:    */   
/*  52:    */   public RestoreJDialog(Frame parent, boolean modal)
/*  53:    */   {
/*  54: 56 */     super(parent, modal);
/*  55: 57 */     this.defaultData = new DefaultData();
/*  56: 58 */     this.protocol = new P30();
/*  57:    */     try
/*  58:    */     {
/*  59: 60 */       AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/*  60: 61 */       if (processor != null)
/*  61:    */       {
/*  62: 62 */         this.protocol = processor.getProtocol();
/*  63:    */         
/*  64: 64 */         this.defaultData = 
/*  65: 65 */           ((DefaultData)processor.getBeanBag().getBean("defaultdata"));
/*  66:    */       }
/*  67:    */     }
/*  68:    */     catch (Exception localException) {}
/*  69: 69 */     if (this.protocol == null) {
/*  70: 70 */       this.protocol = new P30();
/*  71:    */     }
/*  72: 72 */     initComponents();
/*  73: 73 */     setTitle("message.factoryReset");
/*  74: 74 */     setLocationRelativeTo(null);
/*  75: 75 */     setVisible(true);
/*  76:    */   }
/*  77:    */   
/*  78:    */   private void initComponents()
/*  79:    */   {
/*  80: 80 */     this.jPanel1 = new JPanel();
/*  81: 81 */     this.jLabel38 = new AALabel();
/*  82: 82 */     this.jLabel39 = new AALabel();
/*  83: 83 */     this.restoreButton = new AAButton();
/*  84: 84 */     this.jPanel2 = new JPanel();
/*  85: 85 */     this.jPanel3 = new JPanel();
/*  86: 86 */     this.jPanel4 = new JPanel();
/*  87: 87 */     this.cancelButton = new AAButton();
/*  88:    */     
/*  89: 89 */     setDefaultCloseOperation(2);
/*  90:    */     
/*  91: 91 */     addWindowListener(new WindowAdapter()
/*  92:    */     {
/*  93:    */       public void windowClosing(WindowEvent e)
/*  94:    */       {
/*  95: 94 */         RestoreJDialog.this.closeWindows();
/*  96:    */       }
/*  97: 97 */     });
/*  98: 98 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*  99: 99 */     this.jPanel1.setLayout(new BorderLayout());
/* 100:100 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 101:    */     
/* 102:102 */     this.jLabel38.setIcon(new ImageIcon(Constants.WARNINGIMG));
/* 103:    */     
/* 104:104 */     this.jLabel39.setText("message.noteInit");
/* 105:    */     
/* 106:106 */     this.restoreButton.setText("message.factoryReset");
/* 107:107 */     this.restoreButton.addActionListener(new ActionListener()
/* 108:    */     {
/* 109:    */       public void actionPerformed(ActionEvent e)
/* 110:    */       {
/* 111:110 */         if (!SolarPowerTray.isLogin)
/* 112:    */         {
/* 113:111 */           new LoginJDialog(new Frame(), true);
/* 114:112 */           return;
/* 115:    */         }
/* 116:114 */         RestoreJDialog.this.toRestore();
/* 117:    */       }
/* 118:117 */     });
/* 119:118 */     getRestoreInfo();
/* 120:    */     
/* 121:120 */     this.jPanel1.add(this.jPanel3, "Center");
/* 122:    */     
/* 123:122 */     GroupLayout jPanel4Layout = new GroupLayout(this.jPanel4);
/* 124:123 */     this.jPanel4.setLayout(jPanel4Layout);
/* 125:124 */     jPanel4Layout.setHorizontalGroup(
/* 126:125 */       jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 127:126 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 128:127 */       jPanel4Layout.createSequentialGroup().addGap(65, 65, 65)
/* 129:128 */       .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 130:129 */       .addGroup(jPanel4Layout.createSequentialGroup()
/* 131:130 */       .addComponent(this.jLabel38)
/* 132:131 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 133:132 */       .addComponent(this.jLabel39, -1, 366, 32767))
/* 134:133 */       .addGroup(jPanel4Layout.createSequentialGroup()
/* 135:134 */       .addGap(160, 160, 160)
/* 136:135 */       .addComponent(this.restoreButton)
/* 137:136 */       .addGap(113, 113, 113)))
/* 138:137 */       .addGap(26, 26, 26)));
/* 139:    */     
/* 140:139 */     jPanel4Layout.setVerticalGroup(
/* 141:140 */       jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 142:141 */       .addGroup(jPanel4Layout.createSequentialGroup()
/* 143:142 */       .addContainerGap()
/* 144:143 */       .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 145:144 */       .addGroup(jPanel4Layout.createSequentialGroup()
/* 146:145 */       .addGap(16, 16, 16)
/* 147:146 */       .addComponent(this.jLabel39))
/* 148:147 */       .addComponent(this.jLabel38))
/* 149:148 */       .addGap(14, 14, 14)
/* 150:149 */       .addComponent(this.restoreButton)
/* 151:150 */       .addContainerGap(20, 32767)));
/* 152:    */     
/* 153:    */ 
/* 154:153 */     this.jPanel1.add(this.jPanel4, "South");
/* 155:    */     
/* 156:155 */     getContentPane().add(this.jPanel1, "Center");
/* 157:    */     
/* 158:157 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 159:    */     
/* 160:159 */     this.cancelButton.setText("message.close");
/* 161:160 */     this.cancelButton.addActionListener(new ActionListener()
/* 162:    */     {
/* 163:    */       public void actionPerformed(ActionEvent e)
/* 164:    */       {
/* 165:163 */         RestoreJDialog.this.closeWindows();
/* 166:    */       }
/* 167:166 */     });
/* 168:167 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 169:168 */     this.jPanel2.setLayout(jPanel2Layout);
/* 170:169 */     jPanel2Layout.setHorizontalGroup(
/* 171:170 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 172:171 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 173:172 */       jPanel2Layout.createSequentialGroup().addContainerGap(438, 32767)
/* 174:173 */       .addComponent(this.cancelButton)
/* 175:174 */       .addContainerGap()));
/* 176:    */     
/* 177:176 */     jPanel2Layout.setVerticalGroup(
/* 178:177 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 179:178 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 180:179 */       .addContainerGap()
/* 181:180 */       .addComponent(this.cancelButton)
/* 182:181 */       .addContainerGap(-1, 32767)));
/* 183:    */     
/* 184:    */ 
/* 185:184 */     getContentPane().add(this.jPanel2, "Last");
/* 186:    */     
/* 187:186 */     pack();
/* 188:    */   }
/* 189:    */   
/* 190:    */   public synchronized void closeWindows()
/* 191:    */   {
/* 192:190 */     QueryRestoreThread thread = new QueryRestoreThread();
/* 193:191 */     thread.start();
/* 194:192 */     dispose();
/* 195:    */   }
/* 196:    */   
/* 197:    */   private void getRestoreInfo()
/* 198:    */   {
/* 199:196 */     RestoreInfo restoreInfo = this.protocol.getResotreInfo();
/* 200:197 */     List<AutoLabelItem> list = restoreInfo.getInfos();
/* 201:198 */     PageUtils.setRestoreTextLayout(list, this.jPanel3, this);
/* 202:    */   }
/* 203:    */   
/* 204:    */   private void toRestore()
/* 205:    */   {
/* 206:202 */     this.restoreButton.setEnabled(false);
/* 207:203 */     int re = DisplayMessage.showConfirmDialog("message.noteInit", "message.confirm");
/* 208:204 */     if (re == 0)
/* 209:    */     {
/* 210:205 */       boolean result = false;
/* 211:206 */       AbstractProcessor currentProcessor = GlobalProcessors.getCurrentProcessor();
/* 212:207 */       if (currentProcessor != null) {
/* 213:208 */         result = currentProcessor.executeControl("setPF", null);
/* 214:    */       }
/* 215:210 */       if (result)
/* 216:    */       {
/* 217:211 */         if (currentProcessor != null) {
/* 218:212 */           EventsHandler.handleEvent(currentProcessor.getProtocol().getProtocolID(), currentProcessor.getSerialNo(), new Date(), "3010");
/* 219:    */         }
/* 220:214 */         DisplayMessage.showInfoDialog("message.setTrue");
/* 221:    */       }
/* 222:    */       else
/* 223:    */       {
/* 224:216 */         DisplayMessage.showErrorDialog("message.setFalse");
/* 225:    */       }
/* 226:    */     }
/* 227:219 */     this.restoreButton.setEnabled(true);
/* 228:    */   }
/* 229:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.RestoreJDialog
 * JD-Core Version:    0.7.0.1
 */