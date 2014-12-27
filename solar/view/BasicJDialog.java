/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   7:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   8:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*   9:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  10:    */ import cn.com.voltronic.solar.view.component.ComponentFactory;
/*  11:    */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*  12:    */ import java.awt.Container;
/*  13:    */ import java.awt.Frame;
/*  14:    */ import java.awt.event.ActionEvent;
/*  15:    */ import java.awt.event.ActionListener;
/*  16:    */ import javax.swing.BorderFactory;
/*  17:    */ import javax.swing.DefaultComboBoxModel;
/*  18:    */ import javax.swing.GroupLayout;
/*  19:    */ import javax.swing.GroupLayout.Alignment;
/*  20:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  21:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  22:    */ import javax.swing.JComboBox;
/*  23:    */ import javax.swing.JPanel;
/*  24:    */ import javax.swing.JSpinner;
/*  25:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  26:    */ 
/*  27:    */ public class BasicJDialog
/*  28:    */   extends AADialog
/*  29:    */ {
/*  30:    */   private static final long serialVersionUID = -8729767514576492899L;
/*  31:    */   private AAButton applyButton;
/*  32:    */   private JPanel applyPanel;
/*  33:    */   private JPanel basicPanel;
/*  34:    */   private AAButton cancelButton;
/*  35:    */   private JComboBox dateFormatComboBox;
/*  36:    */   private AALabel dateFormatLabel;
/*  37:    */   private AALabel deviceScanLabel;
/*  38:    */   private JSpinner deviceScanSpinner;
/*  39:    */   private AALabel jLabel2;
/*  40:    */   private AALabel jLabel6;
/*  41:    */   private AALabel jLabel8;
/*  42:    */   private AALabel pageRefreshLabel;
/*  43:    */   private JSpinner pageRefreshSpinner;
/*  44:    */   private AALabel recordLabel;
/*  45:    */   private JSpinner recordSpinner;
/*  46: 55 */   private GlobalConfig global = null;
/*  47:    */   
/*  48:    */   public BasicJDialog(Frame parent, boolean modal)
/*  49:    */   {
/*  50: 58 */     super(parent, modal);
/*  51: 59 */     this.global = GlobalVariables.globalConfig;
/*  52: 60 */     initComponents();
/*  53: 61 */     setTitle("message.baseSet");
/*  54: 62 */     setLocationRelativeTo(null);
/*  55: 63 */     setVisible(true);
/*  56:    */   }
/*  57:    */   
/*  58:    */   private void initComponents()
/*  59:    */   {
/*  60: 68 */     this.basicPanel = new JPanel();
/*  61: 69 */     this.pageRefreshLabel = new AALabel();
/*  62: 70 */     this.jLabel2 = new AALabel();
/*  63: 71 */     this.deviceScanLabel = new AALabel();
/*  64: 72 */     this.jLabel6 = new AALabel();
/*  65: 73 */     this.recordLabel = new AALabel();
/*  66: 74 */     this.jLabel8 = new AALabel();
/*  67: 75 */     this.dateFormatLabel = new AALabel();
/*  68: 76 */     this.dateFormatComboBox = new JComboBox();
/*  69:    */     
/*  70:    */ 
/*  71: 79 */     this.applyPanel = new JPanel();
/*  72: 80 */     this.applyButton = new AAButton();
/*  73: 81 */     this.cancelButton = new AAButton();
/*  74:    */     
/*  75: 83 */     initValues();
/*  76:    */     
/*  77: 85 */     setDefaultCloseOperation(2);
/*  78:    */     
/*  79: 87 */     this.basicPanel.setBorder(BorderFactory.createEtchedBorder());
/*  80:    */     
/*  81: 89 */     this.pageRefreshLabel.setText("message.pageRefreshtime[:]");
/*  82:    */     
/*  83: 91 */     this.jLabel2.setText("message.seconds");
/*  84:    */     
/*  85: 93 */     this.deviceScanLabel.setText("message.scanDeviceIntervaltime[:]");
/*  86:    */     
/*  87: 95 */     this.jLabel6.setText("message.seconds");
/*  88:    */     
/*  89: 97 */     this.recordLabel.setText("message.datashz[:]");
/*  90:    */     
/*  91: 99 */     this.jLabel8.setText("message.seconds");
/*  92:    */     
/*  93:101 */     this.dateFormatLabel.setText("message.dateFormat[:]");
/*  94:    */     
/*  95:    */ 
/*  96:    */ 
/*  97:105 */     GroupLayout basicPanelLayout = new GroupLayout(this.basicPanel);
/*  98:106 */     this.basicPanel.setLayout(basicPanelLayout);
/*  99:107 */     basicPanelLayout.setHorizontalGroup(
/* 100:108 */       basicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 101:109 */       .addGroup(basicPanelLayout.createSequentialGroup()
/* 102:110 */       .addGap(52, 52, 52)
/* 103:111 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 104:    */       
/* 105:113 */       .addComponent(this.dateFormatLabel)
/* 106:114 */       .addComponent(this.recordLabel)
/* 107:115 */       .addComponent(this.deviceScanLabel)
/* 108:116 */       .addComponent(this.pageRefreshLabel))
/* 109:117 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 110:118 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 111:119 */       .addGroup(basicPanelLayout.createSequentialGroup()
/* 112:120 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 113:121 */       .addComponent(this.pageRefreshSpinner, -1, 59, 32767)
/* 114:122 */       .addComponent(this.deviceScanSpinner)
/* 115:123 */       .addComponent(this.recordSpinner))
/* 116:124 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 117:125 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 118:126 */       .addComponent(this.jLabel8)
/* 119:127 */       .addComponent(this.jLabel6)
/* 120:128 */       .addComponent(this.jLabel2)))
/* 121:    */       
/* 122:130 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 123:    */       
/* 124:132 */       .addComponent(this.dateFormatComboBox, GroupLayout.Alignment.LEADING, -2, 111, -2)))
/* 125:133 */       .addContainerGap(133, 32767)));
/* 126:    */     
/* 127:135 */     basicPanelLayout.setVerticalGroup(
/* 128:136 */       basicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 129:137 */       .addGroup(basicPanelLayout.createSequentialGroup()
/* 130:138 */       .addGap(22, 22, 22)
/* 131:139 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 132:140 */       .addComponent(this.pageRefreshLabel)
/* 133:141 */       .addComponent(this.pageRefreshSpinner, -2, -1, -2)
/* 134:142 */       .addComponent(this.jLabel2))
/* 135:143 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 136:144 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 137:145 */       .addComponent(this.deviceScanLabel)
/* 138:146 */       .addComponent(this.deviceScanSpinner, -2, -1, -2)
/* 139:147 */       .addComponent(this.jLabel6))
/* 140:148 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 141:149 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 142:150 */       .addComponent(this.recordLabel)
/* 143:151 */       .addComponent(this.recordSpinner, -2, -1, -2)
/* 144:152 */       .addComponent(this.jLabel8))
/* 145:153 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 146:154 */       .addGroup(basicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 147:155 */       .addComponent(this.dateFormatLabel)
/* 148:156 */       .addComponent(this.dateFormatComboBox, -2, -1, -2))
/* 149:    */       
/* 150:    */ 
/* 151:    */ 
/* 152:    */ 
/* 153:161 */       .addContainerGap(35, 32767)));
/* 154:    */     
/* 155:    */ 
/* 156:164 */     getContentPane().add(this.basicPanel, "Center");
/* 157:    */     
/* 158:166 */     this.applyPanel.setBorder(BorderFactory.createEtchedBorder());
/* 159:    */     
/* 160:168 */     this.applyButton.setText("message.apply");
/* 161:169 */     this.applyButton.addActionListener(new ActionListener()
/* 162:    */     {
/* 163:    */       public void actionPerformed(ActionEvent e)
/* 164:    */       {
/* 165:172 */         if (!SolarPowerTray.isLogin)
/* 166:    */         {
/* 167:173 */           new LoginJDialog(new Frame(), true);
/* 168:174 */           return;
/* 169:    */         }
/* 170:176 */         BasicJDialog.this.applySet();
/* 171:    */       }
/* 172:179 */     });
/* 173:180 */     this.cancelButton.setText("message.close");
/* 174:181 */     this.cancelButton.addActionListener(new ActionListener()
/* 175:    */     {
/* 176:    */       public void actionPerformed(ActionEvent e)
/* 177:    */       {
/* 178:184 */         BasicJDialog.this.dispose();
/* 179:    */       }
/* 180:187 */     });
/* 181:188 */     GroupLayout applyPanelLayout = new GroupLayout(this.applyPanel);
/* 182:189 */     this.applyPanel.setLayout(applyPanelLayout);
/* 183:190 */     applyPanelLayout.setHorizontalGroup(
/* 184:191 */       applyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 185:192 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 186:193 */       applyPanelLayout.createSequentialGroup().addContainerGap(244, 32767)
/* 187:194 */       .addComponent(this.applyButton)
/* 188:195 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 189:196 */       .addComponent(this.cancelButton)
/* 190:197 */       .addGap(14, 14, 14)));
/* 191:    */     
/* 192:199 */     applyPanelLayout.setVerticalGroup(
/* 193:200 */       applyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 194:201 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 195:202 */       applyPanelLayout.createSequentialGroup().addContainerGap(-1, 32767)
/* 196:203 */       .addGroup(applyPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 197:204 */       .addComponent(this.applyButton)
/* 198:205 */       .addComponent(this.cancelButton))
/* 199:206 */       .addContainerGap()));
/* 200:    */     
/* 201:    */ 
/* 202:209 */     getContentPane().add(this.applyPanel, "South");
/* 203:    */     
/* 204:211 */     pack();
/* 205:    */   }
/* 206:    */   
/* 207:    */   private void applySet()
/* 208:    */   {
/* 209:215 */     int pageRefresh = Integer.parseInt(this.pageRefreshSpinner.getValue().toString().trim());
/* 210:216 */     int deviceScan = Integer.parseInt(this.deviceScanSpinner.getValue().toString().trim());
/* 211:217 */     int record = Integer.parseInt(this.recordSpinner.getValue().toString().trim());
/* 212:218 */     int dateIndex = this.dateFormatComboBox.getSelectedIndex();
/* 213:    */     
/* 214:220 */     String dateFormat = "yyyy-MM-dd";
/* 215:221 */     if (dateIndex == 0) {
/* 216:222 */       dateFormat = "yyyy-MM-dd";
/* 217:223 */     } else if (dateIndex == 1) {
/* 218:224 */       dateFormat = "yyyy/MM/dd";
/* 219:225 */     } else if (dateIndex == 2) {
/* 220:226 */       dateFormat = "MM-dd-yyyy";
/* 221:227 */     } else if (dateIndex == 3) {
/* 222:228 */       dateFormat = "MM/dd/yyyy";
/* 223:    */     }
/* 224:236 */     this.global.setPageRefreshInterval(pageRefresh);
/* 225:237 */     this.global.setDeviceSacanInterval(deviceScan);
/* 226:238 */     this.global.setDataRecordInterval(record);
/* 227:239 */     this.global.setDateFormat(dateFormat);
/* 228:    */     try
/* 229:    */     {
/* 230:242 */       ConfigureTools.updateProperties(this.global);
/* 231:243 */       ConfigureTools.wrapProperties(GlobalVariables.globalConfig);
/* 232:244 */       dispose();
/* 233:    */     }
/* 234:    */     catch (Exception e)
/* 235:    */     {
/* 236:246 */       DisplayMessage.showErrorDialog("message.operationfailure");
/* 237:    */     }
/* 238:    */   }
/* 239:    */   
/* 240:    */   private void initValues()
/* 241:    */   {
/* 242:251 */     this.pageRefreshSpinner = ComponentFactory.createNumberSpinner(1, 600, 1, this.global.getPageRefreshInterval());
/* 243:252 */     this.deviceScanSpinner = ComponentFactory.createNumberSpinner(5, 600, 1, this.global.getDeviceSacanInterval());
/* 244:253 */     this.recordSpinner = ComponentFactory.createNumberSpinner(30, 600, 30, this.global.getDataRecordInterval());
/* 245:254 */     this.dateFormatComboBox.setModel(new DefaultComboBoxModel(new String[] { "YYYY-MM-DD", "YYYY/MM/DD", "MM-DD-YYYY", "MM/DD/YYYY" }));
/* 246:255 */     String dateFormat = this.global.getDateFormat();
/* 247:256 */     if (dateFormat.equals("yyyy-MM-dd")) {
/* 248:257 */       this.dateFormatComboBox.setSelectedIndex(0);
/* 249:258 */     } else if (dateFormat.equals("yyyy/MM/dd")) {
/* 250:259 */       this.dateFormatComboBox.setSelectedIndex(1);
/* 251:260 */     } else if (dateFormat.equals("MM-dd-yyyy")) {
/* 252:261 */       this.dateFormatComboBox.setSelectedIndex(2);
/* 253:262 */     } else if (dateFormat.equals("MM/dd/yyyy")) {
/* 254:263 */       this.dateFormatComboBox.setSelectedIndex(3);
/* 255:    */     } else {
/* 256:265 */       this.dateFormatComboBox.setSelectedIndex(0);
/* 257:    */     }
/* 258:    */   }
/* 259:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.BasicJDialog
 * JD-Core Version:    0.7.0.1
 */