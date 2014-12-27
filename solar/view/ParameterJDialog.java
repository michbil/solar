/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.comusbprocessor.ParallSubProcessor;
/*   5:    */ import cn.com.voltronic.solar.data.bean.AutoComboBoxItem;
/*   6:    */ import cn.com.voltronic.solar.data.bean.AutoRadioItem;
/*   7:    */ import cn.com.voltronic.solar.data.bean.AutoSpinnerItem;
/*   8:    */ import cn.com.voltronic.solar.data.bean.Capability;
/*   9:    */ import cn.com.voltronic.solar.data.bean.ComboBoxParameter;
/*  10:    */ import cn.com.voltronic.solar.data.bean.ConfigData;
/*  11:    */ import cn.com.voltronic.solar.data.bean.RadioParameter;
/*  12:    */ import cn.com.voltronic.solar.data.bean.SpinnerParameter;
/*  13:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  14:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  15:    */ import cn.com.voltronic.solar.protocol.P30;
/*  16:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  17:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  18:    */ import cn.com.voltronic.solar.view.component.AAButton;
/*  19:    */ import cn.com.voltronic.solar.view.component.AADialog;
/*  20:    */ import java.awt.BorderLayout;
/*  21:    */ import java.awt.Container;
/*  22:    */ import java.awt.Frame;
/*  23:    */ import java.awt.event.ActionEvent;
/*  24:    */ import java.awt.event.ActionListener;
/*  25:    */ import java.awt.event.WindowAdapter;
/*  26:    */ import java.awt.event.WindowEvent;
/*  27:    */ import java.util.List;
/*  28:    */ import javax.swing.BorderFactory;
/*  29:    */ import javax.swing.GroupLayout;
/*  30:    */ import javax.swing.GroupLayout.Alignment;
/*  31:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  32:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  33:    */ import javax.swing.JPanel;
/*  34:    */ 
/*  35:    */ public class ParameterJDialog
/*  36:    */   extends AADialog
/*  37:    */ {
/*  38:    */   private static final long serialVersionUID = 3827137705369649804L;
/*  39:    */   private AAButton cancelButton;
/*  40:    */   private JPanel centerPanel;
/*  41:    */   private JPanel jPanel1;
/*  42:    */   private JPanel jPanel2;
/*  43:    */   private JPanel lastPanel;
/*  44:    */   private JPanel jPanel3;
/*  45: 45 */   public ConfigData configData = null;
/*  46: 46 */   public Capability cappbility = null;
/*  47: 47 */   private AbstractProcessor processor = null;
/*  48: 48 */   private IProtocol protocol = null;
/*  49: 49 */   public static boolean startQueryThread = false;
/*  50:    */   
/*  51:    */   public ParameterJDialog(Frame parent, boolean modal)
/*  52:    */   {
/*  53: 52 */     super(parent, modal);
/*  54: 53 */     this.configData = new ConfigData();
/*  55: 54 */     this.cappbility = new Capability();
/*  56: 55 */     this.protocol = new P30();
/*  57:    */     try
/*  58:    */     {
/*  59: 57 */       this.processor = GlobalProcessors.getCurrentProcessor();
/*  60: 58 */       if (this.processor != null)
/*  61:    */       {
/*  62: 59 */         this.protocol = this.processor.getProtocol();
/*  63:    */         
/*  64:    */ 
/*  65: 62 */         this.configData = ((ConfigData)this.processor.getBeanBag().getBean("configdata"));
/*  66: 63 */         this.cappbility = ((Capability)this.processor.getBeanBag().getBean("capability"));
/*  67: 64 */         if ((this.processor instanceof ParallSubProcessor)) {
/*  68: 65 */           this.configData.setCurrentKey(this.processor.getSerialNo());
/*  69:    */         } else {
/*  70: 67 */           this.configData.setCurrentKey("SELF");
/*  71:    */         }
/*  72:    */       }
/*  73:    */     }
/*  74:    */     catch (Exception localException) {}
/*  75: 72 */     if (this.protocol == null) {
/*  76: 73 */       this.protocol = new P30();
/*  77:    */     }
/*  78: 75 */     initComponents();
/*  79: 76 */     setTitle("message.parametersSetting");
/*  80: 77 */     setLocationRelativeTo(null);
/*  81: 78 */     setVisible(true);
/*  82:    */   }
/*  83:    */   
/*  84:    */   private void initComponents()
/*  85:    */   {
/*  86: 83 */     this.centerPanel = new JPanel();
/*  87: 84 */     this.jPanel1 = new JPanel();
/*  88: 85 */     this.jPanel2 = new JPanel();
/*  89: 86 */     this.jPanel3 = new JPanel();
/*  90: 87 */     this.lastPanel = new JPanel();
/*  91: 88 */     this.cancelButton = new AAButton();
/*  92:    */     
/*  93: 90 */     addWindowListener(new WindowAdapter()
/*  94:    */     {
/*  95:    */       public void windowClosing(WindowEvent e)
/*  96:    */       {
/*  97: 93 */         ParameterJDialog.this.closeWindows();
/*  98:    */       }
/*  99: 96 */     });
/* 100: 97 */     this.centerPanel.setBorder(BorderFactory.createEtchedBorder());
/* 101: 98 */     this.centerPanel.setLayout(new BorderLayout());
/* 102:    */     
/* 103:100 */     setJPanel1Layout();
/* 104:    */     
/* 105:102 */     this.centerPanel.add(this.jPanel1, "First");
/* 106:    */     
/* 107:104 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/* 108:    */     
/* 109:106 */     setJPanel2Layout();
/* 110:    */     
/* 111:108 */     this.centerPanel.add(this.jPanel2, "Center");
/* 112:    */     
/* 113:110 */     setJPanel3Layout();
/* 114:    */     
/* 115:112 */     this.centerPanel.add(this.jPanel3, "Last");
/* 116:    */     
/* 117:114 */     getContentPane().add(this.centerPanel, "Center");
/* 118:115 */     this.lastPanel.setBorder(BorderFactory.createEtchedBorder());
/* 119:116 */     this.cancelButton.setText("message.close");
/* 120:117 */     this.cancelButton.addActionListener(new ActionListener()
/* 121:    */     {
/* 122:    */       public void actionPerformed(ActionEvent e)
/* 123:    */       {
/* 124:120 */         ParameterJDialog.this.closeWindows();
/* 125:    */       }
/* 126:123 */     });
/* 127:124 */     GroupLayout lastPanelLayout = new GroupLayout(this.lastPanel);
/* 128:125 */     this.lastPanel.setLayout(lastPanelLayout);
/* 129:126 */     lastPanelLayout.setHorizontalGroup(
/* 130:127 */       lastPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 131:128 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/* 132:129 */       lastPanelLayout.createSequentialGroup().addContainerGap(613, 32767)
/* 133:130 */       .addComponent(this.cancelButton)
/* 134:131 */       .addContainerGap()));
/* 135:    */     
/* 136:133 */     lastPanelLayout.setVerticalGroup(
/* 137:134 */       lastPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 138:135 */       .addGroup(lastPanelLayout.createSequentialGroup()
/* 139:136 */       .addContainerGap()
/* 140:137 */       .addComponent(this.cancelButton)
/* 141:138 */       .addContainerGap(-1, 32767)));
/* 142:    */     
/* 143:140 */     getContentPane().add(this.lastPanel, "Last");
/* 144:    */     
/* 145:142 */     pack();
/* 146:    */   }
/* 147:    */   
/* 148:    */   public synchronized void closeWindows()
/* 149:    */   {
/* 150:146 */     QueryParameterThread thread = new QueryParameterThread();
/* 151:147 */     thread.setPriority(10);
/* 152:148 */     thread.start();
/* 153:149 */     dispose();
/* 154:    */   }
/* 155:    */   
/* 156:    */   private void setJPanel1Layout()
/* 157:    */   {
/* 158:153 */     List<AutoRadioItem> list = this.protocol.getRadioParameter().getRadioParameterList();
/* 159:154 */     PageUtils.setRadioLayout(list, this.jPanel1, this);
/* 160:    */   }
/* 161:    */   
/* 162:    */   private void setJPanel2Layout()
/* 163:    */   {
/* 164:158 */     List<AutoComboBoxItem> list = this.protocol.getComboBoxParameter().getComboBoxParameterList();
/* 165:159 */     PageUtils.setComboBoxLayout(list, this.jPanel2, this);
/* 166:    */   }
/* 167:    */   
/* 168:    */   private void setJPanel3Layout()
/* 169:    */   {
/* 170:163 */     List<AutoSpinnerItem> list = this.protocol.getSpinnerParameter().getSpinnerParameterList();
/* 171:164 */     PageUtils.setSpinnerLayout(list, this.jPanel3, this);
/* 172:    */   }
/* 173:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.ParameterJDialog
 * JD-Core Version:    0.7.0.1
 */