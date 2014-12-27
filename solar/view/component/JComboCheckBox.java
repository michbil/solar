/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import java.awt.Container;
/*   4:    */ import java.awt.Dimension;
/*   5:    */ import java.awt.event.ActionEvent;
/*   6:    */ import java.awt.event.ActionListener;
/*   7:    */ import java.io.PrintStream;
/*   8:    */ import java.util.ArrayList;
/*   9:    */ import java.util.List;
/*  10:    */ import java.util.Vector;
/*  11:    */ import javax.swing.DefaultComboBoxModel;
/*  12:    */ import javax.swing.JButton;
/*  13:    */ import javax.swing.JComboBox;
/*  14:    */ import javax.swing.JFrame;
/*  15:    */ import javax.swing.JPanel;
/*  16:    */ 
/*  17:    */ public class JComboCheckBox
/*  18:    */   extends JComboBox
/*  19:    */ {
/*  20:    */   private static final long serialVersionUID = 4376982506265668003L;
/*  21: 25 */   private int maxWidth = 300;
/*  22:    */   
/*  23:    */   public JComboCheckBox()
/*  24:    */   {
/*  25: 29 */     setRenderer(new ComboCheckBoxRenderer());
/*  26: 30 */     updateUI();
/*  27:    */   }
/*  28:    */   
/*  29:    */   public JComboCheckBox(String[] items)
/*  30:    */   {
/*  31: 35 */     setRenderer(new ComboCheckBoxRenderer());
/*  32: 36 */     addItems(items);
/*  33: 37 */     updateUI();
/*  34:    */   }
/*  35:    */   
/*  36:    */   public JComboCheckBox(Vector<String> items)
/*  37:    */   {
/*  38: 42 */     setRenderer(new ComboCheckBoxRenderer());
/*  39: 43 */     addItems((String[])items.toArray(new String[0]));
/*  40: 44 */     updateUI();
/*  41:    */   }
/*  42:    */   
/*  43:    */   public JComboCheckBox(int maxWidth)
/*  44:    */   {
/*  45: 49 */     this.maxWidth = maxWidth;
/*  46: 50 */     setRenderer(new ComboCheckBoxRenderer());
/*  47: 51 */     updateUI();
/*  48:    */   }
/*  49:    */   
/*  50:    */   public JComboCheckBox(String[] items, int maxWidth)
/*  51:    */   {
/*  52: 56 */     this.maxWidth = maxWidth;
/*  53: 57 */     setRenderer(new ComboCheckBoxRenderer());
/*  54: 58 */     addItems(items);
/*  55: 59 */     updateUI();
/*  56:    */   }
/*  57:    */   
/*  58:    */   public JComboCheckBox(Vector<String> items, int maxWidth)
/*  59:    */   {
/*  60: 64 */     this.maxWidth = maxWidth;
/*  61: 65 */     setRenderer(new ComboCheckBoxRenderer());
/*  62: 66 */     addItems((String[])items.toArray(new String[0]));
/*  63: 67 */     updateUI();
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void addItems(String[] items)
/*  67:    */   {
/*  68: 71 */     for (int i = 0; i < items.length; i++)
/*  69:    */     {
/*  70: 72 */       String string = items[i];
/*  71: 73 */       addItem(new ComboCheckBoxEntry(String.valueOf(i + 1), string));
/*  72:    */     }
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void addItems(List<CheckBoxData> items)
/*  76:    */   {
/*  77: 78 */     for (int i = 0; i < items.size(); i++)
/*  78:    */     {
/*  79: 79 */       String value = ((CheckBoxData)items.get(i)).getName();
/*  80: 80 */       boolean check = ((CheckBoxData)items.get(i)).isSelected();
/*  81: 81 */       boolean enable = ((CheckBoxData)items.get(i)).isEnable();
/*  82: 82 */       addItem(new ComboCheckBoxEntry(check, enable, String.valueOf(i + 1), value));
/*  83:    */     }
/*  84:    */   }
/*  85:    */   
/*  86:    */   public void addItem(ComboCheckBoxEntry item)
/*  87:    */   {
/*  88: 87 */     super.addItem(item);
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void addItem(boolean checked, boolean state, String id, String value)
/*  92:    */   {
/*  93: 91 */     super.addItem(new ComboCheckBoxEntry(checked, state, id, value));
/*  94:    */   }
/*  95:    */   
/*  96:    */   public String[] getCheckedCodes()
/*  97:    */   {
/*  98: 96 */     Vector values = new Vector();
/*  99: 97 */     DefaultComboBoxModel model = (DefaultComboBoxModel)getModel();
/* 100: 98 */     for (int i = 0; i < model.getSize(); i++)
/* 101:    */     {
/* 102: 99 */       ComboCheckBoxEntry item = (ComboCheckBoxEntry)model.getElementAt(i);
/* 103:100 */       boolean checked = item.isChecked();
/* 104:101 */       if (checked) {
/* 105:102 */         values.add(item.getUniqueCode());
/* 106:    */       }
/* 107:    */     }
/* 108:105 */     String[] retVal = new String[values.size()];
/* 109:106 */     values.copyInto(retVal);
/* 110:107 */     return retVal;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public String[] getCheckedValues()
/* 114:    */   {
/* 115:112 */     Vector values = new Vector();
/* 116:113 */     DefaultComboBoxModel model = (DefaultComboBoxModel)getModel();
/* 117:114 */     for (int i = 0; i < model.getSize(); i++)
/* 118:    */     {
/* 119:115 */       ComboCheckBoxEntry item = (ComboCheckBoxEntry)model.getElementAt(i);
/* 120:116 */       boolean checked = item.isChecked();
/* 121:117 */       if (checked) {
/* 122:118 */         values.add(item.getValue());
/* 123:    */       }
/* 124:    */     }
/* 125:121 */     String[] retVal = new String[values.size()];
/* 126:122 */     values.copyInto(retVal);
/* 127:123 */     return retVal;
/* 128:    */   }
/* 129:    */   
/* 130:    */   public List<String> getCheckedLabels()
/* 131:    */   {
/* 132:127 */     List<String> retVal = new ArrayList();
/* 133:128 */     DefaultComboBoxModel model = (DefaultComboBoxModel)getModel();
/* 134:129 */     for (int i = 0; i < model.getSize(); i++)
/* 135:    */     {
/* 136:130 */       ComboCheckBoxEntry item = (ComboCheckBoxEntry)model.getElementAt(i);
/* 137:131 */       if (item.isChecked()) {
/* 138:132 */         retVal.add(item.getValue());
/* 139:    */       }
/* 140:    */     }
/* 141:135 */     return retVal;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public List<CheckBoxData> getCheckBoxDatas()
/* 145:    */   {
/* 146:139 */     List<CheckBoxData> retVal = new ArrayList();
/* 147:140 */     DefaultComboBoxModel model = (DefaultComboBoxModel)getModel();
/* 148:141 */     for (int i = 0; i < model.getSize(); i++)
/* 149:    */     {
/* 150:142 */       ComboCheckBoxEntry item = (ComboCheckBoxEntry)model.getElementAt(i);
/* 151:143 */       CheckBoxData data = new CheckBoxData();
/* 152:144 */       data.setName(item.getValue());
/* 153:145 */       data.setSelected(item.isChecked());
/* 154:146 */       data.setEnable(item.isEnable());
/* 155:147 */       retVal.add(data);
/* 156:    */     }
/* 157:149 */     return retVal;
/* 158:    */   }
/* 159:    */   
/* 160:    */   public void updateUI()
/* 161:    */   {
/* 162:154 */     setUI(new ComboCheckBoxUI(this.maxWidth));
/* 163:    */   }
/* 164:    */   
/* 165:    */   public static void main(String[] args)
/* 166:    */   {
/* 167:158 */     JFrame frame = new JFrame();
/* 168:159 */     frame.setSize(650, 580);
/* 169:160 */     frame.setDefaultCloseOperation(3);
/* 170:    */     
/* 171:162 */     JPanel jPanel = new JPanel();
/* 172:    */     
/* 173:164 */     String[] values = { "1111111111111111111", "22", "33333333333333333333", 
/* 174:165 */       "4444444444444", "55555555555555555", "6", "77", "6", "77", "6", "77" };
/* 175:166 */     JComboCheckBox checkBox = new JComboCheckBox(values);
/* 176:    */     
/* 177:168 */     checkBox.setPreferredSize(new Dimension(150, 30));
/* 178:169 */     jPanel.add(checkBox);
/* 179:    */     
/* 180:171 */     JButton btnCode = new JButton("Code");
/* 181:172 */     btnCode.addActionListener(new ActionListener()
/* 182:    */     {
/* 183:    */       public void actionPerformed(ActionEvent e)
/* 184:    */       {
/* 185:175 */         for (String string : JComboCheckBox.this.getCheckedCodes()) {
/* 186:176 */           System.out.print(string + " ");
/* 187:    */         }
/* 188:178 */         System.out.println("");
/* 189:    */       }
/* 190:180 */     });
/* 191:181 */     jPanel.add(btnCode);
/* 192:    */     
/* 193:183 */     JButton btnValue = new JButton("Value");
/* 194:184 */     btnValue.addActionListener(new ActionListener()
/* 195:    */     {
/* 196:    */       public void actionPerformed(ActionEvent e)
/* 197:    */       {
/* 198:187 */         for (String string : JComboCheckBox.this.getCheckedValues()) {
/* 199:188 */           System.out.print(string + " ");
/* 200:    */         }
/* 201:190 */         System.out.println("");
/* 202:    */       }
/* 203:192 */     });
/* 204:193 */     jPanel.add(btnValue);
/* 205:    */     
/* 206:195 */     frame.getContentPane().add(jPanel);
/* 207:196 */     frame.setVisible(true);
/* 208:    */   }
/* 209:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.JComboCheckBox
 * JD-Core Version:    0.7.0.1
 */