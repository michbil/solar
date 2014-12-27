/*   1:    */ package cn.com.voltronic.solar.view.panel;
/*   2:    */ 
/*   3:    */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*   4:    */ import cn.com.voltronic.solar.data.bean.AutoLabelItem;
/*   5:    */ import cn.com.voltronic.solar.data.bean.MachineInfo;
/*   6:    */ import cn.com.voltronic.solar.data.bean.ProductInfo;
/*   7:    */ import cn.com.voltronic.solar.data.bean.RatingInfo;
/*   8:    */ import cn.com.voltronic.solar.data.bean.RefreshProductInfo;
/*   9:    */ import cn.com.voltronic.solar.data.bean.RefreshRatingInfo;
/*  10:    */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  11:    */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  12:    */ import cn.com.voltronic.solar.protocol.P30;
/*  13:    */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*  14:    */ import cn.com.voltronic.solar.util.PageUtils;
/*  15:    */ import cn.com.voltronic.solar.view.component.AATextField;
/*  16:    */ import cn.com.voltronic.solar.view.component.AATitleBorder;
/*  17:    */ import java.awt.BorderLayout;
/*  18:    */ import java.awt.Dimension;
/*  19:    */ import java.util.ArrayList;
/*  20:    */ import java.util.List;
/*  21:    */ import javax.swing.JPanel;
/*  22:    */ import javax.swing.JScrollPane;
/*  23:    */ import javax.swing.JSplitPane;
/*  24:    */ import javax.swing.JTextField;
/*  25:    */ 
/*  26:    */ public class StatusPanel
/*  27:    */   extends JSplitPane
/*  28:    */ {
/*  29:    */   private static final long serialVersionUID = -8507396759503040868L;
/*  30:    */   private JPanel topPanel;
/*  31:    */   public RealPanel realPanel;
/*  32:    */   public JScrollPane baseJSPanel;
/*  33:    */   public BasePanel basePanel;
/*  34:    */   private JPanel centerPanel;
/*  35:    */   private JPanel productPanel;
/*  36:    */   private JPanel ratingPanel;
/*  37:    */   private JScrollPane topScrollPane;
/*  38:    */   private JScrollPane centerScrollPane;
/*  39: 44 */   public MachineInfo machineInfo = null;
/*  40:    */   private IProtocol protocol;
/*  41: 46 */   private List<RefreshRatingInfo> ratingList = null;
/*  42: 47 */   private List<RefreshProductInfo> productList = null;
/*  43:    */   
/*  44:    */   public StatusPanel()
/*  45:    */   {
/*  46: 50 */     this.machineInfo = new MachineInfo();
/*  47: 51 */     this.ratingList = new ArrayList();
/*  48: 52 */     this.productList = new ArrayList();
/*  49: 53 */     this.protocol = new P30();
/*  50:    */     try
/*  51:    */     {
/*  52: 55 */       AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/*  53: 56 */       this.protocol = processor.getProtocol();
/*  54: 57 */       this.machineInfo = ((MachineInfo)processor.getBeanBag().getBean("machineinfo"));
/*  55:    */     }
/*  56:    */     catch (Exception localException) {}
/*  57: 60 */     if (this.protocol == null) {
/*  58: 61 */       this.protocol = new P30();
/*  59:    */     }
/*  60: 63 */     initCompents();
/*  61:    */   }
/*  62:    */   
/*  63:    */   private void initCompents()
/*  64:    */   {
/*  65: 68 */     this.topScrollPane = new JScrollPane();
/*  66: 69 */     this.topPanel = new JPanel();
/*  67: 70 */     this.realPanel = new RealPanel();
/*  68: 71 */     this.basePanel = new BasePanel();
/*  69: 72 */     this.centerScrollPane = new JScrollPane();
/*  70: 73 */     this.centerPanel = new JPanel();
/*  71: 74 */     this.productPanel = new JPanel();
/*  72: 75 */     this.ratingPanel = new JPanel();
/*  73:    */     
/*  74: 77 */     setDividerLocation(320);
/*  75: 78 */     setOrientation(0);
/*  76:    */     
/*  77: 80 */     this.topPanel.setLayout(new BorderLayout());
/*  78:    */     
/*  79: 82 */     this.topPanel.add(this.realPanel, "Center");
/*  80: 83 */     this.baseJSPanel = new JScrollPane();
/*  81: 84 */     this.baseJSPanel.setPreferredSize(new Dimension(510, 200));
/*  82: 85 */     this.baseJSPanel.setViewportView(this.basePanel);
/*  83: 86 */     this.topPanel.add(this.baseJSPanel, "East");
/*  84:    */     
/*  85: 88 */     this.topScrollPane.setViewportView(this.topPanel);
/*  86:    */     
/*  87: 90 */     setTopComponent(this.topScrollPane);
/*  88:    */     
/*  89: 92 */     this.centerPanel.setLayout(new BorderLayout());
/*  90:    */     
/*  91: 94 */     getProductInfo();
/*  92:    */     
/*  93: 96 */     getRatingInfo();
/*  94:    */     
/*  95: 98 */     this.productPanel.setPreferredSize(new Dimension(420, 200));
/*  96:    */     
/*  97:100 */     this.centerPanel.add(this.productPanel, "West");
/*  98:    */     
/*  99:102 */     this.centerPanel.add(this.ratingPanel, "Center");
/* 100:    */     
/* 101:104 */     this.centerScrollPane.setViewportView(this.centerPanel);
/* 102:    */     
/* 103:106 */     setBottomComponent(this.centerScrollPane);
/* 104:    */   }
/* 105:    */   
/* 106:    */   private void getProductInfo()
/* 107:    */   {
/* 108:110 */     this.productPanel.setBorder(new AATitleBorder("message.productinfo"));
/* 109:111 */     ProductInfo productInfo = this.protocol.getProductInfo();
/* 110:112 */     List<AutoLabelItem> list = productInfo.getInfos();
/* 111:113 */     this.productList = PageUtils.setInfoLayout(list, this.productPanel, this);
/* 112:    */   }
/* 113:    */   
/* 114:    */   private void getRatingInfo()
/* 115:    */   {
/* 116:117 */     this.ratingPanel.setBorder(new AATitleBorder("message.ratinginfo"));
/* 117:118 */     RatingInfo ratingInfo = this.protocol.getRatingInfo();
/* 118:119 */     List<AutoLabelItem> list = ratingInfo.getInfos();
/* 119:120 */     this.ratingList = PageUtils.setTextLayout(list, this.ratingPanel, this);
/* 120:    */   }
/* 121:    */   
/* 122:    */   public void setDefaultValues()
/* 123:    */   {
/* 124:124 */     for (int i = 0; i < this.productList.size(); i++) {
/* 125:125 */       ((RefreshProductInfo)this.productList.get(i)).getTextField().setText("---");
/* 126:    */     }
/* 127:127 */     for (int i = 0; i < this.ratingList.size(); i++) {
/* 128:128 */       ((RefreshRatingInfo)this.ratingList.get(i)).getTextField().setText("0.0");
/* 129:    */     }
/* 130:    */   }
/* 131:    */   
/* 132:    */   public synchronized void refreshMachineInfo(MachineInfo machineInfo)
/* 133:    */   {
/* 134:133 */     this.machineInfo = machineInfo;
/* 135:134 */     for (int i = 0; i < this.productList.size(); i++) {
/* 136:135 */       ((RefreshProductInfo)this.productList.get(i)).getTextField().setText(PageUtils.getResultByName(((RefreshProductInfo)this.productList.get(i)).getValue(), this));
/* 137:    */     }
/* 138:137 */     for (int i = 0; i < this.ratingList.size(); i++) {
/* 139:138 */       ((RefreshRatingInfo)this.ratingList.get(i)).getTextField().setText(PageUtils.getResultByName(((RefreshRatingInfo)this.ratingList.get(i)).getValue(), this));
/* 140:    */     }
/* 141:    */   }
/* 142:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.panel.StatusPanel
 * JD-Core Version:    0.7.0.1
 */