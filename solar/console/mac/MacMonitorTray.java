/*   1:    */ package cn.com.voltronic.solar.console.mac;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*   4:    */ import cn.com.voltronic.solar.configure.GlobalConfig;
/*   5:    */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*   6:    */ import cn.com.voltronic.solar.constants.Constants;
/*   7:    */ import cn.com.voltronic.solar.socket.UdpClient;
/*   8:    */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   9:    */ import cn.com.voltronic.solar.upgrade.StartUpgrade;
/*  10:    */ import cn.com.voltronic.solar.view.ConfigFrame;
/*  11:    */ import cn.com.voltronic.solar.view.MainJFrame;
/*  12:    */ import java.awt.Color;
/*  13:    */ import java.awt.Dimension;
/*  14:    */ import java.awt.Font;
/*  15:    */ import java.awt.Graphics;
/*  16:    */ import java.awt.Point;
/*  17:    */ import java.awt.Toolkit;
/*  18:    */ import java.awt.event.ActionEvent;
/*  19:    */ import java.awt.event.ActionListener;
/*  20:    */ import java.awt.event.MouseEvent;
/*  21:    */ import java.awt.event.MouseListener;
/*  22:    */ import java.awt.event.MouseMotionListener;
/*  23:    */ import javax.swing.BorderFactory;
/*  24:    */ import javax.swing.ImageIcon;
/*  25:    */ import javax.swing.JButton;
/*  26:    */ import javax.swing.JLabel;
/*  27:    */ import javax.swing.JPanel;
/*  28:    */ 
/*  29:    */ public class MacMonitorTray
/*  30:    */   extends SolarPowerTray
/*  31:    */   implements MouseMotionListener, MouseListener, ActionListener
/*  32:    */ {
/*  33:    */   private static final long serialVersionUID = 970574584469690071L;
/*  34:    */   private JButton configItem;
/*  35:    */   public JButton updateOnline;
/*  36:    */   public JButton updateByUser;
/*  37:    */   private JButton openItem_Bt;
/*  38:    */   private JButton exitItem_Bt;
/*  39:    */   private JLabel name;
/*  40:    */   private JPanel centerPanel;
/*  41:    */   private JPanel southPanel;
/*  42: 48 */   private int x = 0;
/*  43: 49 */   private int y = 0;
/*  44: 51 */   private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/*  45: 52 */   private boolean drag = false;
/*  46: 53 */   private boolean isDrag = true;
/*  47: 54 */   private boolean automaticLocate = true;
/*  48: 56 */   private Color bgColor = new Color(18, 134, 255);
/*  49: 57 */   private Color bgColor1 = Color.white;
/*  50: 58 */   private Font font12 = new Font(null, 0, 12);
/*  51:    */   
/*  52:    */   public MacMonitorTray()
/*  53:    */   {
/*  54: 62 */     initComponents();
/*  55: 63 */     setBounds((int)this.screenSize.getWidth() - 250, 
/*  56: 64 */       (int)this.screenSize.getHeight() - 280, 158, 134);
/*  57: 65 */     setAlwaysOnTop(true);
/*  58: 66 */     setBackground(this.bgColor);
/*  59: 67 */     addMouseMotionListener(this);
/*  60: 68 */     addMouseListener(this);
/*  61: 69 */     setVisible(true);
/*  62:    */   }
/*  63:    */   
/*  64:    */   private void initComponents()
/*  65:    */   {
/*  66: 73 */     this.centerPanel = new JPanel();
/*  67: 74 */     this.configItem = new JButton();
/*  68: 75 */     this.updateOnline = new JButton();
/*  69: 76 */     this.updateByUser = new JButton();
/*  70: 77 */     this.openItem_Bt = new JButton();
/*  71: 78 */     this.exitItem_Bt = new JButton();
/*  72: 79 */     this.southPanel = new JPanel();
/*  73: 80 */     this.name = new JLabel();
/*  74: 81 */     this.icon = new JLabel();
/*  75:    */     
/*  76: 83 */     this.configItem.setText("Configuration");
/*  77: 84 */     this.configItem.addActionListener(new ActionListener()
/*  78:    */     {
/*  79:    */       public void actionPerformed(ActionEvent e)
/*  80:    */       {
/*  81: 86 */         ConfigFrame.getInstance();
/*  82:    */       }
/*  83: 89 */     });
/*  84: 90 */     this.updateOnline.setText("Online Upgrade");
/*  85: 91 */     this.updateOnline.addActionListener(new ActionListener()
/*  86:    */     {
/*  87:    */       public void actionPerformed(ActionEvent e)
/*  88:    */       {
/*  89: 93 */         StartUpgrade start = new StartUpgrade();
/*  90: 94 */         start.updateOnline();
/*  91:    */       }
/*  92: 97 */     });
/*  93: 98 */     this.updateByUser.setText("Manually Upgrade");
/*  94: 99 */     this.updateByUser.addActionListener(new ActionListener()
/*  95:    */     {
/*  96:    */       public void actionPerformed(ActionEvent e)
/*  97:    */       {
/*  98:101 */         StartUpgrade start = new StartUpgrade();
/*  99:102 */         start.updateByUser();
/* 100:    */       }
/* 101:105 */     });
/* 102:106 */     this.openItem_Bt.setText("Open Monitor");
/* 103:107 */     this.openItem_Bt.addActionListener(new ActionListener()
/* 104:    */     {
/* 105:    */       public void actionPerformed(ActionEvent arg0)
/* 106:    */       {
/* 107:109 */         MainJFrame.getNewInstance().setVisible(true);
/* 108:110 */         MainJFrame.getNewInstance().setState(0);
/* 109:    */       }
/* 110:113 */     });
/* 111:114 */     this.exitItem_Bt.setText("Exit");
/* 112:115 */     this.exitItem_Bt.addActionListener(new ActionListener()
/* 113:    */     {
/* 114:    */       public void actionPerformed(ActionEvent arg0)
/* 115:    */       {
/* 116:117 */         UdpClient client = null;
/* 117:118 */         String port = "38694";
/* 118:    */         try
/* 119:    */         {
/* 120:120 */           port = GlobalVariables.globalConfig.getUdpPort();
/* 121:    */         }
/* 122:    */         catch (Exception e1)
/* 123:    */         {
/* 124:122 */           e1.printStackTrace();
/* 125:    */         }
/* 126:    */         try
/* 127:    */         {
/* 128:125 */           client = new UdpClient("localhost", port);
/* 129:126 */           client.send("(exit:myself");
/* 130:    */         }
/* 131:    */         catch (Exception ex)
/* 132:    */         {
/* 133:128 */           ex.printStackTrace();
/* 134:    */         }
/* 135:    */         finally
/* 136:    */         {
/* 137:130 */           if (client != null) {
/* 138:131 */             client.disconnect();
/* 139:    */           }
/* 140:    */         }
/* 141:    */       }
/* 142:136 */     });
/* 143:137 */     this.configItem.setBounds(0, 0, 158, 23);
/* 144:138 */     this.updateOnline.setBounds(0, 20, 158, 23);
/* 145:139 */     this.updateByUser.setBounds(0, 40, 158, 23);
/* 146:140 */     this.openItem_Bt.setBounds(0, 60, 158, 23);
/* 147:141 */     this.exitItem_Bt.setBounds(0, 80, 158, 23);
/* 148:    */     
/* 149:143 */     this.configItem.setBackground(this.bgColor1);
/* 150:144 */     this.updateOnline.setBackground(this.bgColor1);
/* 151:145 */     this.updateByUser.setBackground(this.bgColor1);
/* 152:146 */     this.openItem_Bt.setBackground(this.bgColor1);
/* 153:147 */     this.exitItem_Bt.setBackground(this.bgColor1);
/* 154:148 */     this.configItem.setFont(this.font12);
/* 155:149 */     this.updateOnline.setFont(this.font12);
/* 156:150 */     this.updateByUser.setFont(this.font12);
/* 157:151 */     this.openItem_Bt.setFont(this.font12);
/* 158:152 */     this.exitItem_Bt.setFont(this.font12);
/* 159:    */     
/* 160:154 */     this.centerPanel.setLayout(null);
/* 161:155 */     this.centerPanel.setBackground(this.bgColor);
/* 162:156 */     this.centerPanel.setBorder(BorderFactory.createEtchedBorder());
/* 163:    */     
/* 164:158 */     this.southPanel.setBackground(this.bgColor);
/* 165:159 */     this.southPanel.setBorder(BorderFactory.createEtchedBorder());
/* 166:    */     
/* 167:161 */     this.centerPanel.add(this.configItem);
/* 168:162 */     this.centerPanel.add(this.updateOnline);
/* 169:163 */     this.centerPanel.add(this.updateByUser);
/* 170:164 */     this.centerPanel.add(this.openItem_Bt);
/* 171:165 */     this.centerPanel.add(this.exitItem_Bt);
/* 172:    */     
/* 173:167 */     add(this.centerPanel, "Center");
/* 174:    */     
/* 175:169 */     this.name.setText(GlobalVariables.customerConfig.getCustomerName());
/* 176:170 */     this.name.setFont(this.font12);
/* 177:171 */     this.name.setForeground(Color.white);
/* 178:    */     
/* 179:173 */     this.icon.setIcon(new ImageIcon(Constants.CONNECTEDIMG));
/* 180:    */     
/* 181:175 */     this.southPanel.add(this.name);
/* 182:176 */     this.southPanel.add(this.icon);
/* 183:177 */     add(this.southPanel, "South");
/* 184:    */     
/* 185:179 */     pack();
/* 186:    */   }
/* 187:    */   
/* 188:    */   public boolean getDragable()
/* 189:    */   {
/* 190:183 */     return this.isDrag;
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void setDragable(boolean yesOrNo)
/* 194:    */   {
/* 195:187 */     this.isDrag = yesOrNo;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public boolean getAntoLocate()
/* 199:    */   {
/* 200:191 */     return this.automaticLocate;
/* 201:    */   }
/* 202:    */   
/* 203:    */   public void setAntoLocate(boolean bLocate)
/* 204:    */   {
/* 205:195 */     this.automaticLocate = bLocate;
/* 206:    */   }
/* 207:    */   
/* 208:    */   public void mouseReleased(MouseEvent e)
/* 209:    */   {
/* 210:199 */     this.drag = false;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public void mousePressed(MouseEvent e)
/* 214:    */   {
/* 215:203 */     if (!this.isDrag) {
/* 216:204 */       return;
/* 217:    */     }
/* 218:205 */     this.drag = true;
/* 219:206 */     this.x = e.getX();
/* 220:207 */     this.y = e.getY();
/* 221:    */   }
/* 222:    */   
/* 223:    */   public void mouseDragged(MouseEvent e)
/* 224:    */   {
/* 225:211 */     if (!this.isDrag) {
/* 226:212 */       return;
/* 227:    */     }
/* 228:213 */     if (this.drag)
/* 229:    */     {
/* 230:214 */       int w_x = getLocation().x;
/* 231:215 */       int w_y = getLocation().y;
/* 232:    */       
/* 233:217 */       int m_x = e.getX();
/* 234:218 */       int m_y = e.getY();
/* 235:220 */       if ((m_x != this.x) || (m_y != this.y)) {
/* 236:221 */         setLocation(w_x + m_x - this.x, w_y + m_y - this.y);
/* 237:    */       }
/* 238:    */     }
/* 239:    */   }
/* 240:    */   
/* 241:    */   public void setLocation(int x, int y)
/* 242:    */   {
/* 243:227 */     if ((this.screenSize != null) && (this.automaticLocate))
/* 244:    */     {
/* 245:228 */       int w = getWidth();
/* 246:229 */       int h = getHeight();
/* 247:230 */       if (x < 0) {
/* 248:231 */         x = 0;
/* 249:    */       }
/* 250:232 */       if (y < 0) {
/* 251:233 */         y = 0;
/* 252:    */       }
/* 253:234 */       if (x + w > this.screenSize.width) {
/* 254:235 */         x = this.screenSize.width - w;
/* 255:    */       }
/* 256:236 */       if (y + h > this.screenSize.height) {
/* 257:237 */         y = this.screenSize.height - h;
/* 258:    */       }
/* 259:    */     }
/* 260:239 */     super.setLocation(x, y);
/* 261:    */   }
/* 262:    */   
/* 263:    */   public void update(Graphics g)
/* 264:    */   {
/* 265:243 */     paint(g);
/* 266:    */   }
/* 267:    */   
/* 268:    */   public void mouseMoved(MouseEvent e) {}
/* 269:    */   
/* 270:    */   public void mouseEntered(MouseEvent e) {}
/* 271:    */   
/* 272:    */   public void mouseExited(MouseEvent e) {}
/* 273:    */   
/* 274:    */   public void mouseClicked(MouseEvent e) {}
/* 275:    */   
/* 276:    */   public void actionPerformed(ActionEvent e) {}
/* 277:    */   
/* 278:    */   public void refreshUpgrade(boolean online, boolean manually)
/* 279:    */   {
/* 280:263 */     this.updateOnline.setEnabled(online);
/* 281:264 */     this.updateByUser.setEnabled(manually);
/* 282:    */   }
/* 283:    */   
/* 284:    */   public void addToSystemTray() {}
/* 285:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.console.mac.MacMonitorTray
 * JD-Core Version:    0.7.0.1
 */