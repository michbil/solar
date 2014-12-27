/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.ConfigureTools;
/*   4:    */ import cn.com.voltronic.solar.configure.LimitCOMConfig;
/*   5:    */ import cn.com.voltronic.solar.data.bean.LocalComs;
/*   6:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   7:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   8:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*   9:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  10:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  11:    */ import cn.com.voltronic.solar.view.component.AARadioButton;
/*  12:    */ import cn.com.voltronic.solar.view.component.I18NBundle;
/*  13:    */ import java.awt.BorderLayout;
/*  14:    */ import java.awt.Container;
/*  15:    */ import java.awt.Frame;
/*  16:    */ import java.awt.event.ActionEvent;
/*  17:    */ import java.awt.event.ActionListener;
/*  18:    */ import java.util.ArrayList;
/*  19:    */ import java.util.List;
/*  20:    */ import javax.swing.BorderFactory;
/*  21:    */ import javax.swing.GroupLayout;
/*  22:    */ import javax.swing.GroupLayout.Alignment;
/*  23:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  24:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  25:    */ import javax.swing.JLabel;
/*  26:    */ import javax.swing.JPanel;
/*  27:    */ import javax.swing.JScrollPane;
/*  28:    */ 
/*  29:    */ public class COMSetJDialog
/*  30:    */   extends AADialog
/*  31:    */ {
/*  32:    */   private static final long serialVersionUID = 3903055468928874739L;
/*  33:    */   private AAButton closeButton;
/*  34:    */   private JPanel jPanel1;
/*  35:    */   private JPanel jPanel2;
/*  36:    */   private JPanel jPanel3;
/*  37:    */   private JPanel jPanel4;
/*  38:    */   private JScrollPane jScrollPane1;
/*  39:    */   private AAButton refreshButton;
/*  40:    */   
/*  41:    */   public COMSetJDialog(Frame parent, boolean modal)
/*  42:    */   {
/*  43: 46 */     super(parent, modal);
/*  44: 47 */     initComponents();
/*  45: 48 */     setTitle(bd.getString("message.setcom"));
/*  46: 49 */     setLocationRelativeTo(null);
/*  47: 50 */     setVisible(true);
/*  48:    */   }
/*  49:    */   
/*  50:    */   private void getComStatus(JPanel panel)
/*  51:    */   {
/*  52: 54 */     if (panel != null) {
/*  53: 55 */       panel.removeAll();
/*  54:    */     }
/*  55: 57 */     List<LocalComs> coms = new ArrayList();
/*  56: 58 */     coms = GlobalProcessors.getLocalComs();
/*  57: 59 */     setCOMLayout(coms, panel);
/*  58: 60 */     panel.validate();
/*  59: 61 */     panel.repaint();
/*  60: 62 */     panel.updateUI();
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void setCOMLayout(List<LocalComs> list, JPanel panel)
/*  64:    */   {
/*  65: 66 */     int index = list.size();
/*  66: 67 */     panel.setLayout(new BorderLayout());
/*  67: 68 */     JPanel leftPanel = new JPanel();
/*  68:    */     
/*  69: 70 */     GroupLayout layout = new GroupLayout(leftPanel);
/*  70: 71 */     leftPanel.setLayout(layout);
/*  71: 72 */     layout.setAutoCreateGaps(true);
/*  72: 73 */     layout.setAutoCreateContainerGaps(true);
/*  73:    */     
/*  74: 75 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/*  75: 76 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/*  76:    */     
/*  77: 78 */     GroupLayout.ParallelGroup p1 = layout
/*  78: 79 */       .createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  79: 80 */     GroupLayout.ParallelGroup p2 = layout
/*  80: 81 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  81: 82 */     GroupLayout.ParallelGroup p3 = layout
/*  82: 83 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  83: 84 */     GroupLayout.ParallelGroup p4 = layout
/*  84: 85 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  85: 86 */     GroupLayout.ParallelGroup p5 = layout
/*  86: 87 */       .createParallelGroup(GroupLayout.Alignment.LEADING);
/*  87: 89 */     for (int i = 0; i < index; i++)
/*  88:    */     {
/*  89: 90 */       LocalComs localCom = (LocalComs)list.get(i);
/*  90: 91 */       String comName = localCom.getComName();
/*  91: 92 */       boolean selected = localCom.isSelected();
/*  92: 93 */       boolean used = localCom.isUsed();
/*  93: 94 */       final JLabel nameLabel = new JLabel();
/*  94: 95 */       nameLabel.setText(comName);
/*  95: 96 */       final AARadioButton radioButton1 = new AARadioButton();
/*  96: 97 */       radioButton1.setText("message.allowscanned");
/*  97: 98 */       final AARadioButton radioButton2 = new AARadioButton();
/*  98: 99 */       radioButton2.setText("message.forbidscanned");
/*  99:100 */       radioButton1.addActionListener(new ActionListener()
/* 100:    */       {
/* 101:    */         public void actionPerformed(ActionEvent e)
/* 102:    */         {
/* 103:103 */           radioButton1.setSelected(true);
/* 104:104 */           radioButton2.setSelected(false);
/* 105:    */         }
/* 106:106 */       });
/* 107:107 */       radioButton2.addActionListener(new ActionListener()
/* 108:    */       {
/* 109:    */         public void actionPerformed(ActionEvent e)
/* 110:    */         {
/* 111:110 */           radioButton1.setSelected(false);
/* 112:111 */           radioButton2.setSelected(true);
/* 113:    */         }
/* 114:    */       });
/* 115:114 */       if (selected)
/* 116:    */       {
/* 117:115 */         radioButton1.setSelected(false);
/* 118:116 */         radioButton2.setSelected(true);
/* 119:    */       }
/* 120:    */       else
/* 121:    */       {
/* 122:118 */         radioButton1.setSelected(true);
/* 123:119 */         radioButton2.setSelected(false);
/* 124:    */       }
/* 125:121 */       AAButton applyAButton = new AAButton();
/* 126:122 */       applyAButton.setText("message.apply");
/* 127:123 */       applyAButton.addActionListener(new ActionListener()
/* 128:    */       {
/* 129:    */         public void actionPerformed(ActionEvent e)
/* 130:    */         {
/* 131:126 */           String limitcoms = GlobalVariables.limitCOMConfig.getLimitcoms();
/* 132:127 */           LimitCOMConfig obj = GlobalVariables.limitCOMConfig;
/* 133:128 */           if (obj == null) {
/* 134:129 */             obj = new LimitCOMConfig();
/* 135:    */           }
/* 136:131 */           if (radioButton1.isSelected())
/* 137:    */           {
/* 138:132 */             String deleteName = nameLabel.getText().trim();
/* 139:133 */             if (!"".equals(limitcoms)) {
/* 140:134 */               if (limitcoms.indexOf(',') == -1)
/* 141:    */               {
/* 142:135 */                 if (limitcoms.equals(deleteName)) {
/* 143:136 */                   limitcoms = "";
/* 144:    */                 }
/* 145:    */               }
/* 146:    */               else
/* 147:    */               {
/* 148:139 */                 String[] limitArr = limitcoms.split(",");
/* 149:140 */                 limitcoms = "";
/* 150:141 */                 for (int j = 0; j < limitArr.length; j++) {
/* 151:142 */                   if (!limitArr[j].equals(deleteName)) {
/* 152:143 */                     if ("".equals(limitcoms)) {
/* 153:144 */                       limitcoms = limitArr[j];
/* 154:    */                     } else {
/* 155:146 */                       limitcoms = limitcoms + "," + limitArr[j];
/* 156:    */                     }
/* 157:    */                   }
/* 158:    */                 }
/* 159:    */               }
/* 160:    */             }
/* 161:152 */             obj.setLimitcoms(limitcoms);
/* 162:    */           }
/* 163:153 */           else if (radioButton2.isSelected())
/* 164:    */           {
/* 165:154 */             String addName = nameLabel.getText().trim();
/* 166:155 */             if ("".equals(limitcoms)) {
/* 167:156 */               limitcoms = addName;
/* 168:    */             } else {
/* 169:158 */               limitcoms = limitcoms + "," + addName;
/* 170:    */             }
/* 171:160 */             obj.setLimitcoms(limitcoms);
/* 172:    */           }
/* 173:    */           try
/* 174:    */           {
/* 175:163 */             ConfigureTools.updateProperties(obj);
/* 176:164 */             ConfigureTools.wrapProperties(GlobalVariables.limitCOMConfig);
/* 177:    */           }
/* 178:    */           catch (Exception e1)
/* 179:    */           {
/* 180:166 */             e1.printStackTrace();
/* 181:    */           }
/* 182:    */         }
/* 183:169 */       });
/* 184:170 */       AALabel useLabel = new AALabel();
/* 185:171 */       if (used) {
/* 186:172 */         useLabel.setText("message.haveused");
/* 187:    */       } else {
/* 188:174 */         useLabel.setText("");
/* 189:    */       }
/* 190:177 */       p1.addComponent(nameLabel);
/* 191:178 */       p2.addComponent(radioButton1);
/* 192:179 */       p3.addComponent(radioButton2);
/* 193:180 */       p4.addComponent(applyAButton);
/* 194:181 */       p5.addComponent(useLabel);
/* 195:    */       
/* 196:183 */       vGroup.addGroup(
/* 197:184 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(nameLabel).addComponent(radioButton1).addComponent(
/* 198:185 */         radioButton2).addComponent(applyAButton).addComponent(useLabel));
/* 199:186 */       vGroup.addGap(15, 15, 15);
/* 200:    */     }
/* 201:188 */     hGroup.addGap(20, 20, 20);
/* 202:189 */     hGroup.addGroup(p1);
/* 203:190 */     hGroup.addGroup(p2);
/* 204:191 */     hGroup.addGroup(p3);
/* 205:192 */     hGroup.addGroup(p4);
/* 206:193 */     hGroup.addGroup(p5);
/* 207:194 */     hGroup.addGap(40, 40, 40);
/* 208:    */     
/* 209:196 */     layout.setHorizontalGroup(hGroup);
/* 210:197 */     layout.setVerticalGroup(vGroup);
/* 211:198 */     panel.add(leftPanel, "Center");
/* 212:    */   }
/* 213:    */   
/* 214:    */   private void initComponents()
/* 215:    */   {
/* 216:203 */     this.jPanel1 = new JPanel();
/* 217:204 */     this.jScrollPane1 = new JScrollPane();
/* 218:205 */     this.jPanel4 = new JPanel();
/* 219:206 */     this.jPanel3 = new JPanel();
/* 220:207 */     this.refreshButton = new AAButton();
/* 221:208 */     this.jPanel2 = new JPanel();
/* 222:209 */     this.closeButton = new AAButton();
/* 223:    */     
/* 224:211 */     setDefaultCloseOperation(2);
/* 225:    */     
/* 226:213 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/* 227:214 */     this.jPanel1.setLayout(new BorderLayout());
/* 228:    */     
/* 229:216 */     getComStatus(this.jPanel4);
/* 230:217 */     this.jScrollPane1.setViewportView(this.jPanel4);
/* 231:    */     
/* 232:219 */     this.jPanel1.add(this.jScrollPane1, "Center");
/* 233:    */     
/* 234:221 */     this.refreshButton.setText("message.refresh");
/* 235:222 */     this.refreshButton.addActionListener(new ActionListener()
/* 236:    */     {
/* 237:    */       public void actionPerformed(ActionEvent e)
/* 238:    */       {
/* 239:225 */         COMSetJDialog.this.refreshButton.setEnabled(false);
/* 240:226 */         COMSetJDialog.this.getComStatus(COMSetJDialog.this.jPanel4);
/* 241:227 */         COMSetJDialog.this.refreshButton.setEnabled(true);
/* 242:    */       }
/* 243:230 */     });
/* 244:231 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/* 245:232 */     this.jPanel3.setLayout(jPanel3Layout);
/* 246:233 */     jPanel3Layout.setHorizontalGroup(
/* 247:234 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 248:235 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 249:236 */       .addContainerGap()
/* 250:237 */       .addComponent(this.refreshButton)
/* 251:238 */       .addContainerGap(-1, 32767)));
/* 252:    */     
/* 253:240 */     jPanel3Layout.setVerticalGroup(
/* 254:241 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 255:242 */       .addGroup(jPanel3Layout.createSequentialGroup()
/* 256:243 */       .addContainerGap()
/* 257:244 */       .addComponent(this.refreshButton)
/* 258:245 */       .addContainerGap(360, 32767)));
/* 259:    */     
/* 260:    */ 
/* 261:248 */     this.jPanel1.add(this.jPanel3, "East");
/* 262:    */     
/* 263:250 */     getContentPane().add(this.jPanel1, "Center");
/* 264:    */     
/* 265:252 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 266:    */     
/* 267:254 */     this.closeButton.setText("message.close");
/* 268:255 */     this.closeButton.addActionListener(new ActionListener()
/* 269:    */     {
/* 270:    */       public void actionPerformed(ActionEvent e)
/* 271:    */       {
/* 272:258 */         COMSetJDialog.this.dispose();
/* 273:    */       }
/* 274:261 */     });
/* 275:262 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/* 276:263 */     this.jPanel2.setLayout(jPanel2Layout);
/* 277:264 */     jPanel2Layout.setHorizontalGroup(
/* 278:265 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 279:266 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 280:267 */       jPanel2Layout.createSequentialGroup().addContainerGap(650, 32767)
/* 281:268 */       .addComponent(this.closeButton)
/* 282:269 */       .addContainerGap()));
/* 283:    */     
/* 284:271 */     jPanel2Layout.setVerticalGroup(
/* 285:272 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 286:273 */       .addGroup(jPanel2Layout.createSequentialGroup()
/* 287:274 */       .addContainerGap()
/* 288:275 */       .addComponent(this.closeButton)
/* 289:276 */       .addContainerGap(-1, 32767)));
/* 290:    */     
/* 291:    */ 
/* 292:279 */     getContentPane().add(this.jPanel2, "South");
/* 293:    */     
/* 294:281 */     pack();
/* 295:    */   }
/* 296:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.COMSetJDialog
 * JD-Core Version:    0.7.0.1
 */