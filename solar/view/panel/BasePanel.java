/*   1:    */ package cn.com.voltronic.solar.view.panel;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.data.bean.AutoLabelItem;
/*   4:    */ import cn.com.voltronic.solar.data.bean.BaseInfo;
/*   5:    */ import cn.com.voltronic.solar.data.bean.RefreshBaseInfo;
/*   6:    */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/*   7:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*   8:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*   9:    */ import cn.com.voltronic.solar.protocol.P30;
/*  10:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  11:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  12:    */ import cn.com.voltronic.solar.view.component.AALabel;
/*  13:    */ import cn.com.voltronic.solar.view.component.AATextField;
/*  14:    */ import cn.com.voltronic.solar.view.component.AATitleBorder;
/*  15:    */ import java.awt.BorderLayout;
/*  16:    */ import java.util.ArrayList;
/*  17:    */ import java.util.List;
/*  18:    */ import javax.swing.GroupLayout;
/*  19:    */ import javax.swing.GroupLayout.Alignment;
/*  20:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  21:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  22:    */ import javax.swing.JPanel;
/*  23:    */ 
/*  24:    */ public class BasePanel
/*  25:    */   extends JPanel
/*  26:    */ {
/*  27:    */   private static final long serialVersionUID = 3158917918052742446L;
/*  28: 33 */   public WorkInfo workInfo = null;
/*  29: 35 */   private List<RefreshBaseInfo> valueList = null;
/*  30:    */   
/*  31:    */   public BasePanel()
/*  32:    */   {
/*  33: 38 */     this.workInfo = new WorkInfo();
/*  34: 39 */     this.valueList = new ArrayList();
/*  35: 40 */     initComponents();
/*  36:    */   }
/*  37:    */   
/*  38:    */   public List<RefreshBaseInfo> getValueList()
/*  39:    */   {
/*  40: 44 */     return this.valueList;
/*  41:    */   }
/*  42:    */   
/*  43:    */   private void initComponents()
/*  44:    */   {
/*  45: 49 */     setBorder(new AATitleBorder("message.baseinfo"));
/*  46: 50 */     setLayout(new BorderLayout());
/*  47:    */     
/*  48: 52 */     IProtocol protocol = new P30();
/*  49:    */     try
/*  50:    */     {
/*  51: 54 */       AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/*  52: 55 */       if (processor != null) {
/*  53: 56 */         protocol = processor.getProtocol();
/*  54:    */       }
/*  55:    */     }
/*  56:    */     catch (Exception localException) {}
/*  57: 60 */     if (protocol == null) {
/*  58: 61 */       protocol = new P30();
/*  59:    */     }
/*  60: 63 */     BaseInfo baseInfo = protocol.getBaseInfo();
/*  61: 64 */     List<AutoLabelItem> list = baseInfo.getBaseInfoList();
/*  62: 65 */     int index = 2;
/*  63: 66 */     if (list.size() % 2 == 0) {
/*  64: 67 */       index = list.size() / 2;
/*  65:    */     } else {
/*  66: 69 */       index = list.size() / 2 + 1;
/*  67:    */     }
/*  68: 71 */     JPanel leftPanel = new JPanel();
/*  69: 72 */     JPanel rightPanel = new JPanel();
/*  70:    */     
/*  71:    */ 
/*  72: 75 */     GroupLayout layout = new GroupLayout(leftPanel);
/*  73: 76 */     leftPanel.setLayout(layout);
/*  74: 77 */     layout.setAutoCreateGaps(true);
/*  75: 78 */     layout.setAutoCreateContainerGaps(true);
/*  76:    */     
/*  77: 80 */     GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
/*  78: 81 */     GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
/*  79:    */     
/*  80: 83 */     GroupLayout.ParallelGroup p1 = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
/*  81: 84 */     GroupLayout.ParallelGroup p2 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
/*  82: 85 */     GroupLayout.ParallelGroup p3 = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
/*  83: 88 */     for (int i = 0; i < index; i++)
/*  84:    */     {
/*  85: 89 */       AALabel caption1 = new AALabel();
/*  86: 90 */       AATextField value1 = new AATextField();
/*  87: 91 */       AALabel unit1 = new AALabel();
/*  88: 92 */       caption1.setText(((AutoLabelItem)list.get(i)).getCaption());
/*  89: 93 */       value1.setText(
/*  90: 94 */         PageUtils.getResultByName(((AutoLabelItem)list.get(i)).getValue(), this));
/*  91: 95 */       unit1.setText(((AutoLabelItem)list.get(i)).getUnit());
/*  92: 96 */       this.valueList.add(new RefreshBaseInfo(value1, ((AutoLabelItem)list.get(i)).getValue()));
/*  93:    */       
/*  94: 98 */       p1.addComponent(caption1);
/*  95: 99 */       p2.addComponent(value1, 50, 50, 50);
/*  96:100 */       p3.addComponent(unit1);
/*  97:    */       
/*  98:102 */       vGroup.addGroup(
/*  99:103 */         layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption1).addComponent(value1).addComponent(unit1));
/* 100:104 */       vGroup.addGap(15, 15, 15);
/* 101:    */     }
/* 102:106 */     hGroup.addGap(20, 20, 20);
/* 103:107 */     hGroup.addGroup(p1);
/* 104:108 */     hGroup.addGroup(p2);
/* 105:109 */     hGroup.addGroup(p3);
/* 106:110 */     hGroup.addGap(15, 15, 15);
/* 107:    */     
/* 108:112 */     layout.setHorizontalGroup(hGroup);
/* 109:113 */     layout.setVerticalGroup(vGroup);
/* 110:    */     
/* 111:    */ 
/* 112:116 */     GroupLayout layout2 = new GroupLayout(rightPanel);
/* 113:117 */     rightPanel.setLayout(layout2);
/* 114:118 */     layout2.setAutoCreateGaps(true);
/* 115:119 */     layout2.setAutoCreateContainerGaps(true);
/* 116:    */     
/* 117:121 */     GroupLayout.SequentialGroup hGroup2 = layout2.createSequentialGroup();
/* 118:122 */     GroupLayout.SequentialGroup vGroup2 = layout2.createSequentialGroup();
/* 119:    */     
/* 120:124 */     GroupLayout.ParallelGroup p4 = layout2.createParallelGroup(GroupLayout.Alignment.TRAILING);
/* 121:125 */     GroupLayout.ParallelGroup p5 = layout2.createParallelGroup(GroupLayout.Alignment.LEADING);
/* 122:126 */     GroupLayout.ParallelGroup p6 = layout2.createParallelGroup(GroupLayout.Alignment.LEADING);
/* 123:129 */     for (int j = index; j < list.size(); j++)
/* 124:    */     {
/* 125:130 */       AALabel caption2 = new AALabel();
/* 126:131 */       AATextField value2 = new AATextField();
/* 127:132 */       AALabel unit2 = new AALabel();
/* 128:133 */       caption2.setText(((AutoLabelItem)list.get(j)).getCaption());
/* 129:134 */       value2.setText(
/* 130:135 */         PageUtils.getResultByName(((AutoLabelItem)list.get(j)).getValue(), this));
/* 131:136 */       unit2.setText(((AutoLabelItem)list.get(j)).getUnit());
/* 132:137 */       this.valueList.add(new RefreshBaseInfo(value2, ((AutoLabelItem)list.get(j)).getValue()));
/* 133:    */       
/* 134:139 */       p4.addComponent(caption2);
/* 135:140 */       p5.addComponent(value2, 50, 50, 50);
/* 136:141 */       p6.addComponent(unit2);
/* 137:    */       
/* 138:143 */       vGroup2.addGroup(
/* 139:144 */         layout2.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(caption2).addComponent(value2).addComponent(unit2));
/* 140:145 */       vGroup2.addGap(15, 15, 15);
/* 141:    */     }
/* 142:147 */     hGroup2.addGap(15, 15, 15);
/* 143:148 */     hGroup2.addGroup(p4);
/* 144:149 */     hGroup2.addGroup(p5);
/* 145:150 */     hGroup2.addGroup(p6);
/* 146:151 */     hGroup2.addGap(20, 20, 20);
/* 147:    */     
/* 148:153 */     layout2.setHorizontalGroup(hGroup2);
/* 149:154 */     layout2.setVerticalGroup(vGroup2);
/* 150:155 */     add(leftPanel, "Center");
/* 151:156 */     add(rightPanel, "East");
/* 152:    */   }
/* 153:    */   
/* 154:    */   public void setDefaultValues()
/* 155:    */   {
/* 156:160 */     for (int i = 0; i < this.valueList.size(); i++) {
/* 157:161 */       ((RefreshBaseInfo)this.valueList.get(i)).getTextField().setText("0.0");
/* 158:    */     }
/* 159:    */   }
/* 160:    */   
/* 161:    */   public synchronized void refreshWorkInfo(WorkInfo workInfo)
/* 162:    */   {
/* 163:166 */     this.workInfo = workInfo;
/* 164:167 */     for (int i = 0; i < this.valueList.size(); i++) {
/* 165:168 */       ((RefreshBaseInfo)this.valueList.get(i)).getTextField().setText(PageUtils.getResultByName(((RefreshBaseInfo)this.valueList.get(i)).getValue(), this));
/* 166:    */     }
/* 167:    */   }
/* 168:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.panel.BasePanel
 * JD-Core Version:    0.7.0.1
 */