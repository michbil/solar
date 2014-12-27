/*    1:     */ package cn.com.voltronic.solar.view;
/*    2:     */ 
/*    3:     */ import cn.com.voltronic.solar.beanbag.BeanBag;
/*    4:     */ import cn.com.voltronic.solar.configure.CustomerConfig;
/*    5:     */ import cn.com.voltronic.solar.console.SolarPowerTray;
/*    6:     */ import cn.com.voltronic.solar.constants.Constants;
/*    7:     */ import cn.com.voltronic.solar.data.bean.SelfTestResult;
/*    8:     */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*    9:     */ import cn.com.voltronic.solar.system.GlobalProcessors;
/*   10:     */ import cn.com.voltronic.solar.system.GlobalVariables;
/*   11:     */ import cn.com.voltronic.solar.view.component.AAButton;
/*   12:     */ import cn.com.voltronic.solar.view.component.DisplayMessage;
/*   13:     */ import com.itextpdf.text.BaseColor;
/*   14:     */ import com.itextpdf.text.Document;
/*   15:     */ import com.itextpdf.text.Paragraph;
/*   16:     */ import com.itextpdf.text.pdf.BaseFont;
/*   17:     */ import com.itextpdf.text.pdf.PdfWriter;
/*   18:     */ import java.awt.BorderLayout;
/*   19:     */ import java.awt.Color;
/*   20:     */ import java.awt.Container;
/*   21:     */ import java.awt.Frame;
/*   22:     */ import java.awt.Graphics;
/*   23:     */ import java.awt.Graphics2D;
/*   24:     */ import java.awt.event.ActionEvent;
/*   25:     */ import java.awt.event.ActionListener;
/*   26:     */ import java.awt.print.PageFormat;
/*   27:     */ import java.awt.print.Printable;
/*   28:     */ import java.awt.print.PrinterException;
/*   29:     */ import java.awt.print.PrinterJob;
/*   30:     */ import java.io.File;
/*   31:     */ import java.io.FileOutputStream;
/*   32:     */ import java.io.IOException;
/*   33:     */ import javax.swing.BorderFactory;
/*   34:     */ import javax.swing.GroupLayout;
/*   35:     */ import javax.swing.GroupLayout.Alignment;
/*   36:     */ import javax.swing.GroupLayout.ParallelGroup;
/*   37:     */ import javax.swing.GroupLayout.SequentialGroup;
/*   38:     */ import javax.swing.JDialog;
/*   39:     */ import javax.swing.JFileChooser;
/*   40:     */ import javax.swing.JLabel;
/*   41:     */ import javax.swing.JPanel;
/*   42:     */ import javax.swing.JTextArea;
/*   43:     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*   44:     */ 
/*   45:     */ public class GridTestResultDialog
/*   46:     */   extends JDialog
/*   47:     */   implements Printable
/*   48:     */ {
/*   49:     */   private static final long serialVersionUID = 6976990593398404770L;
/*   50:     */   private AAButton jButton1;
/*   51:     */   private AAButton jButton2;
/*   52:     */   private AAButton jButton3;
/*   53:     */   private JLabel modelNameLabelV;
/*   54:     */   private JLabel jLabel16;
/*   55:     */   private JLabel jLabel17;
/*   56:     */   private JLabel jLabel18;
/*   57:     */   private JLabel jLabel19;
/*   58:     */   private JLabel fwVersionLabelV;
/*   59:     */   private JLabel jLabel20;
/*   60:     */   private JLabel jLabel21;
/*   61:     */   private JLabel jLabel22;
/*   62:     */   private JLabel jLabel23;
/*   63:     */   private JLabel jLabel24;
/*   64:     */   private JLabel jLabel25;
/*   65:     */   private JLabel jLabel26;
/*   66:     */   private JLabel jLabel27;
/*   67:     */   private JLabel jLabel28;
/*   68:     */   private JLabel jLabel29;
/*   69:     */   private JLabel dateStrLabelV;
/*   70:     */   private JLabel jLabel30;
/*   71:     */   private JLabel jLabel31;
/*   72:     */   private JLabel resultLabel;
/*   73:     */   private JLabel jLabel36;
/*   74:     */   private JLabel jLabel37;
/*   75:     */   private JLabel jLabel38;
/*   76:     */   private JLabel jLabel39;
/*   77:     */   private JLabel timeStrLabelV;
/*   78:     */   private JLabel jLabel45;
/*   79:     */   private JLabel lowVoltageLabel;
/*   80:     */   private JLabel jLabel47;
/*   81:     */   private JLabel lowVoltageTLabel;
/*   82:     */   private JLabel jLabel49;
/*   83:     */   private JLabel modelNameLabelC;
/*   84:     */   private JLabel jLabel50;
/*   85:     */   private JLabel highFrequencyLabel;
/*   86:     */   private JLabel jLabel52;
/*   87:     */   private JLabel highFrequencyTLabel;
/*   88:     */   private JLabel jLabel54;
/*   89:     */   private JLabel jLabel55;
/*   90:     */   private JLabel lowFrequencyLabel;
/*   91:     */   private JLabel jLabel57;
/*   92:     */   private JLabel lowFrequencyTLabel;
/*   93:     */   private JLabel jLabel59;
/*   94:     */   private JLabel fwVersionLabelC;
/*   95:     */   private JLabel jLabel60;
/*   96:     */   private JLabel highVoltageLabel;
/*   97:     */   private JLabel jLabel62;
/*   98:     */   private JLabel highVoltageTLabel;
/*   99:     */   private JLabel jLabel64;
/*  100:     */   private JLabel dateStrLabelC;
/*  101:     */   private JLabel timeStrLabelC;
/*  102:     */   private JPanel jPanel1;
/*  103:     */   private JPanel jPanel10;
/*  104:     */   private JPanel jPanel2;
/*  105:     */   private JPanel jPanel3;
/*  106:     */   private JPanel jPanel4;
/*  107:     */   private JPanel jPanel5;
/*  108:     */   private JPanel jPanel6;
/*  109:     */   private JPanel jPanel7;
/*  110:     */   private JPanel jPanel8;
/*  111:     */   private JPanel jPanel9;
/*  112: 115 */   private SelfTestResult result = null;
/*  113: 116 */   private int PAGES = 0;
/*  114:     */   private JFileChooser fDialog;
/*  115:     */   
/*  116:     */   public GridTestResultDialog(Frame parent, boolean modal)
/*  117:     */   {
/*  118: 120 */     super(parent, modal);
/*  119: 121 */     this.result = new SelfTestResult();
/*  120: 122 */     initComponents();
/*  121: 123 */     initValues();
/*  122: 124 */     setLocationRelativeTo(null);
/*  123: 125 */     setVisible(true);
/*  124:     */   }
/*  125:     */   
/*  126:     */   private void initValues()
/*  127:     */   {
/*  128: 129 */     AbstractProcessor processor = GlobalProcessors.getCurrentProcessor();
/*  129: 130 */     if (processor != null)
/*  130:     */     {
/*  131: 131 */       this.result = 
/*  132: 132 */         ((SelfTestResult)processor.getBeanBag().getBean("selftestresult"));
/*  133: 133 */       this.modelNameLabelV.setText(this.result.getModelName());
/*  134: 134 */       this.fwVersionLabelV.setText(this.result.getFwVersion());
/*  135: 135 */       this.dateStrLabelV.setText(this.result.getDateStr());
/*  136: 136 */       this.timeStrLabelV.setText(this.result.getTimeStr());
/*  137: 137 */       this.highVoltageLabel.setText(this.result.getHighVoltage());
/*  138: 138 */       this.highVoltageTLabel.setText(this.result.getHighVoltageT());
/*  139: 139 */       this.lowVoltageLabel.setText(this.result.getLowVoltage());
/*  140: 140 */       this.lowVoltageTLabel.setText(this.result.getLowVoltageT());
/*  141: 141 */       this.highFrequencyLabel.setText(this.result.getHighFrequency());
/*  142: 142 */       this.highFrequencyTLabel.setText(this.result.getHighFrequencyT());
/*  143: 143 */       this.lowFrequencyLabel.setText(this.result.getLowFrequency());
/*  144: 144 */       this.lowFrequencyTLabel.setText(this.result.getLowFrequencyT());
/*  145: 145 */       this.resultLabel.setText(this.result.getResult());
/*  146:     */     }
/*  147:     */   }
/*  148:     */   
/*  149:     */   private void initComponents()
/*  150:     */   {
/*  151: 151 */     this.jPanel1 = new JPanel();
/*  152: 152 */     this.jPanel3 = new JPanel();
/*  153: 153 */     this.modelNameLabelV = new JLabel();
/*  154: 154 */     this.fwVersionLabelV = new JLabel();
/*  155: 155 */     this.dateStrLabelV = new JLabel();
/*  156: 156 */     this.timeStrLabelV = new JLabel();
/*  157: 157 */     this.modelNameLabelC = new JLabel();
/*  158: 158 */     this.fwVersionLabelC = new JLabel();
/*  159: 159 */     this.dateStrLabelC = new JLabel();
/*  160: 160 */     this.timeStrLabelC = new JLabel();
/*  161: 161 */     this.jPanel4 = new JPanel();
/*  162: 162 */     this.jPanel7 = new JPanel();
/*  163: 163 */     this.jLabel28 = new JLabel();
/*  164: 164 */     this.jLabel29 = new JLabel();
/*  165: 165 */     this.jLabel30 = new JLabel();
/*  166: 166 */     this.jLabel31 = new JLabel();
/*  167: 167 */     this.jLabel38 = new JLabel();
/*  168: 168 */     this.jLabel60 = new JLabel();
/*  169: 169 */     this.highVoltageLabel = new JLabel();
/*  170: 170 */     this.jLabel62 = new JLabel();
/*  171: 171 */     this.highVoltageTLabel = new JLabel();
/*  172: 172 */     this.jLabel64 = new JLabel();
/*  173: 173 */     this.jPanel8 = new JPanel();
/*  174: 174 */     this.jLabel16 = new JLabel();
/*  175: 175 */     this.jLabel17 = new JLabel();
/*  176: 176 */     this.jLabel18 = new JLabel();
/*  177: 177 */     this.jLabel19 = new JLabel();
/*  178: 178 */     this.jLabel36 = new JLabel();
/*  179: 179 */     this.jLabel45 = new JLabel();
/*  180: 180 */     this.lowVoltageLabel = new JLabel();
/*  181: 181 */     this.jLabel47 = new JLabel();
/*  182: 182 */     this.lowVoltageTLabel = new JLabel();
/*  183: 183 */     this.jLabel49 = new JLabel();
/*  184: 184 */     this.jPanel5 = new JPanel();
/*  185: 185 */     this.jPanel9 = new JPanel();
/*  186: 186 */     this.jLabel20 = new JLabel();
/*  187: 187 */     this.jLabel21 = new JLabel();
/*  188: 188 */     this.jLabel22 = new JLabel();
/*  189: 189 */     this.jLabel23 = new JLabel();
/*  190: 190 */     this.jLabel37 = new JLabel();
/*  191: 191 */     this.jLabel50 = new JLabel();
/*  192: 192 */     this.highFrequencyLabel = new JLabel();
/*  193: 193 */     this.jLabel52 = new JLabel();
/*  194: 194 */     this.highFrequencyTLabel = new JLabel();
/*  195: 195 */     this.jLabel54 = new JLabel();
/*  196: 196 */     this.jPanel10 = new JPanel();
/*  197: 197 */     this.jLabel24 = new JLabel();
/*  198: 198 */     this.jLabel25 = new JLabel();
/*  199: 199 */     this.jLabel26 = new JLabel();
/*  200: 200 */     this.jLabel27 = new JLabel();
/*  201: 201 */     this.jLabel39 = new JLabel();
/*  202: 202 */     this.jLabel55 = new JLabel();
/*  203: 203 */     this.lowFrequencyLabel = new JLabel();
/*  204: 204 */     this.jLabel57 = new JLabel();
/*  205: 205 */     this.lowFrequencyTLabel = new JLabel();
/*  206: 206 */     this.jLabel59 = new JLabel();
/*  207: 207 */     this.jPanel6 = new JPanel();
/*  208: 208 */     this.resultLabel = new JLabel();
/*  209: 209 */     this.jPanel2 = new JPanel();
/*  210: 210 */     this.jButton1 = new AAButton();
/*  211: 211 */     this.jButton2 = new AAButton();
/*  212: 212 */     this.jButton3 = new AAButton();
/*  213:     */     
/*  214: 214 */     setDefaultCloseOperation(2);
/*  215:     */     
/*  216: 216 */     this.jPanel1.setBorder(BorderFactory.createEtchedBorder());
/*  217:     */     
/*  218: 218 */     this.modelNameLabelV.setText("---");
/*  219:     */     
/*  220: 220 */     this.fwVersionLabelV.setText("---");
/*  221:     */     
/*  222: 222 */     this.dateStrLabelV.setText("---");
/*  223:     */     
/*  224: 224 */     this.timeStrLabelV.setText("---");
/*  225:     */     
/*  226: 226 */     this.modelNameLabelC.setText("Model:");
/*  227:     */     
/*  228: 228 */     this.fwVersionLabelC.setText("FW version:");
/*  229:     */     
/*  230: 230 */     this.dateStrLabelC.setText("Date:");
/*  231:     */     
/*  232: 232 */     this.timeStrLabelC.setText("Time:");
/*  233:     */     
/*  234: 234 */     GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
/*  235: 235 */     this.jPanel3.setLayout(jPanel3Layout);
/*  236: 236 */     jPanel3Layout.setHorizontalGroup(
/*  237: 237 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  238: 238 */       .addGroup(GroupLayout.Alignment.TRAILING, 
/*  239: 239 */       jPanel3Layout.createSequentialGroup().addContainerGap(449, 32767)
/*  240: 240 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  241: 241 */       .addComponent(this.modelNameLabelC, GroupLayout.Alignment.TRAILING)
/*  242: 242 */       .addComponent(this.fwVersionLabelC, GroupLayout.Alignment.TRAILING)
/*  243: 243 */       .addComponent(this.dateStrLabelC, GroupLayout.Alignment.TRAILING)
/*  244: 244 */       .addComponent(this.timeStrLabelC, GroupLayout.Alignment.TRAILING))
/*  245: 245 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  246: 246 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  247: 247 */       .addComponent(this.modelNameLabelV)
/*  248: 248 */       .addComponent(this.fwVersionLabelV)
/*  249: 249 */       .addComponent(this.dateStrLabelV)
/*  250: 250 */       .addComponent(this.timeStrLabelV))
/*  251: 251 */       .addGap(26, 26, 26)));
/*  252:     */     
/*  253: 253 */     jPanel3Layout.setVerticalGroup(
/*  254: 254 */       jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  255: 255 */       .addGroup(jPanel3Layout.createSequentialGroup()
/*  256: 256 */       .addContainerGap()
/*  257: 257 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  258: 258 */       .addComponent(this.modelNameLabelV)
/*  259: 259 */       .addComponent(this.modelNameLabelC))
/*  260: 260 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  261: 261 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  262: 262 */       .addComponent(this.fwVersionLabelV)
/*  263: 263 */       .addComponent(this.fwVersionLabelC))
/*  264: 264 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  265: 265 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  266: 266 */       .addComponent(this.dateStrLabelV)
/*  267: 267 */       .addComponent(this.dateStrLabelC))
/*  268: 268 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  269: 269 */       .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  270: 270 */       .addComponent(this.timeStrLabelV)
/*  271: 271 */       .addComponent(this.timeStrLabelC))
/*  272: 272 */       .addContainerGap(-1, 32767)));
/*  273:     */     
/*  274:     */ 
/*  275: 275 */     this.jPanel4.setLayout(new BorderLayout());
/*  276:     */     
/*  277: 277 */     this.jPanel7.setBorder(
/*  278: 278 */       BorderFactory.createTitledBorder("Maximum voltage check"));
/*  279:     */     
/*  280: 280 */     this.jLabel28.setText("Vmax");
/*  281:     */     
/*  282: 282 */     this.jLabel29.setText("1.2 Vn(276 V)");
/*  283:     */     
/*  284: 284 */     this.jLabel30.setText("Ttrip");
/*  285:     */     
/*  286: 286 */     this.jLabel31.setText("100 ms");
/*  287:     */     
/*  288: 288 */     this.jLabel38.setText("Threshold value");
/*  289:     */     
/*  290: 290 */     this.jLabel60.setText("Reading");
/*  291:     */     
/*  292: 292 */     this.highVoltageLabel.setText("0.0");
/*  293:     */     
/*  294: 294 */     this.jLabel62.setText("V");
/*  295:     */     
/*  296: 296 */     this.highVoltageTLabel.setText("0");
/*  297:     */     
/*  298: 298 */     this.jLabel64.setText("ms");
/*  299:     */     
/*  300: 300 */     GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
/*  301: 301 */     this.jPanel7.setLayout(jPanel7Layout);
/*  302: 302 */     jPanel7Layout.setHorizontalGroup(
/*  303: 303 */       jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  304: 304 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  305: 305 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  306: 306 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  307: 307 */       .addGap(32, 32, 32)
/*  308: 308 */       .addComponent(this.jLabel38))
/*  309: 309 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  310: 310 */       .addGap(22, 22, 22)
/*  311: 311 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  312: 312 */       .addComponent(this.jLabel28, GroupLayout.Alignment.TRAILING)
/*  313: 313 */       .addComponent(this.jLabel30, GroupLayout.Alignment.TRAILING))
/*  314: 314 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  315: 315 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  316: 316 */       .addComponent(this.jLabel29)
/*  317: 317 */       .addComponent(this.jLabel31))))
/*  318: 318 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  319: 319 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  320: 320 */       .addGap(52, 52, 52)
/*  321: 321 */       .addComponent(this.jLabel60))
/*  322: 322 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  323: 323 */       .addGap(44, 44, 44)
/*  324: 324 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  325: 325 */       .addComponent(this.highVoltageLabel, GroupLayout.Alignment.TRAILING)
/*  326: 326 */       .addComponent(this.highVoltageTLabel, GroupLayout.Alignment.TRAILING))
/*  327: 327 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  328: 328 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  329: 329 */       .addComponent(this.jLabel62)
/*  330: 330 */       .addComponent(this.jLabel64))))
/*  331: 331 */       .addGap(36, 36, 36)));
/*  332:     */     
/*  333: 333 */     jPanel7Layout.setVerticalGroup(
/*  334: 334 */       jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  335: 335 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  336: 336 */       .addGap(22, 22, 22)
/*  337: 337 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  338: 338 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  339: 339 */       .addComponent(this.jLabel60)
/*  340: 340 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  341: 341 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  342: 342 */       .addComponent(this.highVoltageLabel)
/*  343: 343 */       .addComponent(this.jLabel62))
/*  344: 344 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  345: 345 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  346: 346 */       .addComponent(this.highVoltageTLabel)
/*  347: 347 */       .addComponent(this.jLabel64)))
/*  348: 348 */       .addGroup(jPanel7Layout.createSequentialGroup()
/*  349: 349 */       .addComponent(this.jLabel38)
/*  350: 350 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  351: 351 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  352: 352 */       .addComponent(this.jLabel28)
/*  353: 353 */       .addComponent(this.jLabel29))
/*  354: 354 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  355: 355 */       .addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  356: 356 */       .addComponent(this.jLabel30)
/*  357: 357 */       .addComponent(this.jLabel31))))
/*  358: 358 */       .addContainerGap(34, 32767)));
/*  359:     */     
/*  360:     */ 
/*  361: 361 */     this.jPanel4.add(this.jPanel7, "Center");
/*  362:     */     
/*  363: 363 */     this.jPanel8.setBorder(
/*  364: 364 */       BorderFactory.createTitledBorder("Minimum voltage check"));
/*  365:     */     
/*  366: 366 */     this.jLabel16.setText("Vmin");
/*  367:     */     
/*  368: 368 */     this.jLabel17.setText("0.8 Vn(184 V)");
/*  369:     */     
/*  370: 370 */     this.jLabel18.setText("Ttrip");
/*  371:     */     
/*  372: 372 */     this.jLabel19.setText("100 ms");
/*  373:     */     
/*  374: 374 */     this.jLabel36.setText("Threshold value");
/*  375:     */     
/*  376: 376 */     this.jLabel45.setText("Reading");
/*  377:     */     
/*  378: 378 */     this.lowVoltageLabel.setText("0.0");
/*  379:     */     
/*  380: 380 */     this.jLabel47.setText("V");
/*  381:     */     
/*  382: 382 */     this.lowVoltageTLabel.setText("0");
/*  383:     */     
/*  384: 384 */     this.jLabel49.setText("ms");
/*  385:     */     
/*  386: 386 */     GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
/*  387: 387 */     this.jPanel8.setLayout(jPanel8Layout);
/*  388: 388 */     jPanel8Layout.setHorizontalGroup(
/*  389: 389 */       jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  390: 390 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  391: 391 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  392: 392 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  393: 393 */       .addGap(32, 32, 32)
/*  394: 394 */       .addComponent(this.jLabel36))
/*  395: 395 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  396: 396 */       .addGap(22, 22, 22)
/*  397: 397 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  398: 398 */       .addComponent(this.jLabel16, GroupLayout.Alignment.TRAILING)
/*  399: 399 */       .addComponent(this.jLabel18, GroupLayout.Alignment.TRAILING))
/*  400: 400 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  401: 401 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  402: 402 */       .addComponent(this.jLabel17)
/*  403: 403 */       .addComponent(this.jLabel19))))
/*  404: 404 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  405: 405 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  406: 406 */       .addGap(52, 52, 52)
/*  407: 407 */       .addComponent(this.jLabel45))
/*  408: 408 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  409: 409 */       .addGap(44, 44, 44)
/*  410: 410 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  411: 411 */       .addComponent(this.lowVoltageLabel, GroupLayout.Alignment.TRAILING)
/*  412: 412 */       .addComponent(this.lowVoltageTLabel, GroupLayout.Alignment.TRAILING))
/*  413: 413 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  414: 414 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  415: 415 */       .addComponent(this.jLabel47)
/*  416: 416 */       .addComponent(this.jLabel49))))
/*  417: 417 */       .addGap(40, 40, 40)));
/*  418:     */     
/*  419: 419 */     jPanel8Layout.setVerticalGroup(
/*  420: 420 */       jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  421: 421 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  422: 422 */       .addGap(22, 22, 22)
/*  423: 423 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  424: 424 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  425: 425 */       .addComponent(this.jLabel45)
/*  426: 426 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  427: 427 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  428: 428 */       .addComponent(this.lowVoltageLabel)
/*  429: 429 */       .addComponent(this.jLabel47))
/*  430: 430 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  431: 431 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  432: 432 */       .addComponent(this.lowVoltageTLabel)
/*  433: 433 */       .addComponent(this.jLabel49)))
/*  434: 434 */       .addGroup(jPanel8Layout.createSequentialGroup()
/*  435: 435 */       .addComponent(this.jLabel36)
/*  436: 436 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  437: 437 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  438: 438 */       .addComponent(this.jLabel16)
/*  439: 439 */       .addComponent(this.jLabel17))
/*  440: 440 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  441: 441 */       .addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  442: 442 */       .addComponent(this.jLabel18)
/*  443: 443 */       .addComponent(this.jLabel19))))
/*  444: 444 */       .addContainerGap(34, 32767)));
/*  445:     */     
/*  446:     */ 
/*  447: 447 */     this.jPanel4.add(this.jPanel8, "East");
/*  448:     */     
/*  449: 449 */     this.jPanel5.setLayout(new BorderLayout());
/*  450:     */     
/*  451: 451 */     this.jPanel9.setBorder(
/*  452: 452 */       BorderFactory.createTitledBorder("Maximum frequency check"));
/*  453:     */     
/*  454: 454 */     this.jLabel20.setText("Fmax");
/*  455:     */     
/*  456: 456 */     this.jLabel21.setText("50.3 Hz");
/*  457:     */     
/*  458: 458 */     this.jLabel22.setText("Ttrip");
/*  459:     */     
/*  460: 460 */     this.jLabel23.setText("100 ms");
/*  461:     */     
/*  462: 462 */     this.jLabel37.setText("Threshold value");
/*  463:     */     
/*  464: 464 */     this.jLabel50.setText("Reading");
/*  465:     */     
/*  466: 466 */     this.highFrequencyLabel.setText("0.0");
/*  467:     */     
/*  468: 468 */     this.jLabel52.setText("Hz");
/*  469:     */     
/*  470: 470 */     this.highFrequencyTLabel.setText("0");
/*  471:     */     
/*  472: 472 */     this.jLabel54.setText("ms");
/*  473:     */     
/*  474: 474 */     GroupLayout jPanel9Layout = new GroupLayout(this.jPanel9);
/*  475: 475 */     this.jPanel9.setLayout(jPanel9Layout);
/*  476: 476 */     jPanel9Layout.setHorizontalGroup(
/*  477: 477 */       jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  478: 478 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  479: 479 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  480: 480 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  481: 481 */       .addGap(32, 32, 32)
/*  482: 482 */       .addComponent(this.jLabel37))
/*  483: 483 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  484: 484 */       .addGap(22, 22, 22)
/*  485: 485 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  486: 486 */       .addComponent(this.jLabel20, GroupLayout.Alignment.TRAILING)
/*  487: 487 */       .addComponent(this.jLabel22, GroupLayout.Alignment.TRAILING))
/*  488: 488 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  489: 489 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  490: 490 */       .addComponent(this.jLabel21)
/*  491: 491 */       .addComponent(this.jLabel23))))
/*  492: 492 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  493: 493 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  494: 494 */       .addGap(52, 52, 52)
/*  495: 495 */       .addComponent(this.jLabel50))
/*  496: 496 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  497: 497 */       .addGap(44, 44, 44)
/*  498: 498 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  499: 499 */       .addComponent(this.highFrequencyLabel, GroupLayout.Alignment.TRAILING)
/*  500: 500 */       .addComponent(this.highFrequencyTLabel, GroupLayout.Alignment.TRAILING))
/*  501: 501 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  502: 502 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  503: 503 */       .addComponent(this.jLabel52)
/*  504: 504 */       .addComponent(this.jLabel54))))
/*  505: 505 */       .addGap(65, 65, 65)));
/*  506:     */     
/*  507: 507 */     jPanel9Layout.setVerticalGroup(
/*  508: 508 */       jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  509: 509 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  510: 510 */       .addGap(22, 22, 22)
/*  511: 511 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  512: 512 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  513: 513 */       .addComponent(this.jLabel50)
/*  514: 514 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  515: 515 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  516: 516 */       .addComponent(this.highFrequencyLabel)
/*  517: 517 */       .addComponent(this.jLabel52))
/*  518: 518 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  519: 519 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  520: 520 */       .addComponent(this.highFrequencyTLabel)
/*  521: 521 */       .addComponent(this.jLabel54)))
/*  522: 522 */       .addGroup(jPanel9Layout.createSequentialGroup()
/*  523: 523 */       .addComponent(this.jLabel37)
/*  524: 524 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  525: 525 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  526: 526 */       .addComponent(this.jLabel20)
/*  527: 527 */       .addComponent(this.jLabel21))
/*  528: 528 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  529: 529 */       .addGroup(jPanel9Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  530: 530 */       .addComponent(this.jLabel22)
/*  531: 531 */       .addComponent(this.jLabel23))))
/*  532: 532 */       .addContainerGap(34, 32767)));
/*  533:     */     
/*  534:     */ 
/*  535: 535 */     this.jPanel5.add(this.jPanel9, "Center");
/*  536:     */     
/*  537: 537 */     this.jPanel10.setBorder(
/*  538: 538 */       BorderFactory.createTitledBorder("Minimum frequency check"));
/*  539:     */     
/*  540: 540 */     this.jLabel24.setText("Fmin");
/*  541:     */     
/*  542: 542 */     this.jLabel25.setText("49.7 Hz");
/*  543:     */     
/*  544: 544 */     this.jLabel26.setText("Ttrip");
/*  545:     */     
/*  546: 546 */     this.jLabel27.setText("100 ms");
/*  547:     */     
/*  548: 548 */     this.jLabel39.setText("Threshold value");
/*  549:     */     
/*  550: 550 */     this.jLabel55.setText("Reading");
/*  551:     */     
/*  552: 552 */     this.lowFrequencyLabel.setText("0.0");
/*  553:     */     
/*  554: 554 */     this.jLabel57.setText("Hz");
/*  555:     */     
/*  556: 556 */     this.lowFrequencyTLabel.setText("0");
/*  557:     */     
/*  558: 558 */     this.jLabel59.setText("ms");
/*  559:     */     
/*  560: 560 */     GroupLayout jPanel10Layout = new GroupLayout(this.jPanel10);
/*  561: 561 */     this.jPanel10.setLayout(jPanel10Layout);
/*  562: 562 */     jPanel10Layout.setHorizontalGroup(
/*  563: 563 */       jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  564: 564 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  565: 565 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  566: 566 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  567: 567 */       .addGap(32, 32, 32)
/*  568: 568 */       .addComponent(this.jLabel39))
/*  569: 569 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  570: 570 */       .addGap(22, 22, 22)
/*  571: 571 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  572: 572 */       .addComponent(this.jLabel24, GroupLayout.Alignment.TRAILING)
/*  573: 573 */       .addComponent(this.jLabel26, GroupLayout.Alignment.TRAILING))
/*  574: 574 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  575: 575 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  576: 576 */       .addComponent(this.jLabel25)
/*  577: 577 */       .addComponent(this.jLabel27))))
/*  578: 578 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  579: 579 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  580: 580 */       .addGap(52, 52, 52)
/*  581: 581 */       .addComponent(this.jLabel55))
/*  582: 582 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  583: 583 */       .addGap(44, 44, 44)
/*  584: 584 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  585: 585 */       .addComponent(this.lowFrequencyLabel, GroupLayout.Alignment.TRAILING)
/*  586: 586 */       .addComponent(this.lowFrequencyTLabel, GroupLayout.Alignment.TRAILING))
/*  587: 587 */       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  588: 588 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  589: 589 */       .addComponent(this.jLabel57)
/*  590: 590 */       .addComponent(this.jLabel59))))
/*  591: 591 */       .addContainerGap(50, 32767)));
/*  592:     */     
/*  593: 593 */     jPanel10Layout.setVerticalGroup(
/*  594: 594 */       jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  595: 595 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  596: 596 */       .addGap(22, 22, 22)
/*  597: 597 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  598: 598 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  599: 599 */       .addComponent(this.jLabel55)
/*  600: 600 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  601: 601 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  602: 602 */       .addComponent(this.lowFrequencyLabel)
/*  603: 603 */       .addComponent(this.jLabel57))
/*  604: 604 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  605: 605 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  606: 606 */       .addComponent(this.lowFrequencyTLabel)
/*  607: 607 */       .addComponent(this.jLabel59)))
/*  608: 608 */       .addGroup(jPanel10Layout.createSequentialGroup()
/*  609: 609 */       .addComponent(this.jLabel39)
/*  610: 610 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  611: 611 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  612: 612 */       .addComponent(this.jLabel24)
/*  613: 613 */       .addComponent(this.jLabel25))
/*  614: 614 */       .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  615: 615 */       .addGroup(jPanel10Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  616: 616 */       .addComponent(this.jLabel26)
/*  617: 617 */       .addComponent(this.jLabel27))))
/*  618: 618 */       .addContainerGap(34, 32767)));
/*  619:     */     
/*  620:     */ 
/*  621: 621 */     this.jPanel5.add(this.jPanel10, "East");
/*  622:     */     
/*  623: 623 */     this.resultLabel.setText("---");
/*  624:     */     
/*  625: 625 */     GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
/*  626: 626 */     this.jPanel6.setLayout(jPanel6Layout);
/*  627: 627 */     jPanel6Layout.setHorizontalGroup(
/*  628: 628 */       jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  629:     */       
/*  630:     */ 
/*  631:     */ 
/*  632:     */ 
/*  633: 633 */       .addGroup(GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup().addGap(278, 278, 278).addComponent(this.resultLabel, -1, -1, 32767).addGap(268, 268, 268)));
/*  634: 634 */     jPanel6Layout.setVerticalGroup(
/*  635: 635 */       jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  636:     */       
/*  637:     */ 
/*  638:     */ 
/*  639: 639 */       .addGroup(GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup().addContainerGap(18, 32767).addComponent(this.resultLabel).addContainerGap()));
/*  640:     */     
/*  641: 641 */     GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
/*  642: 642 */     this.jPanel1.setLayout(jPanel1Layout);
/*  643: 643 */     jPanel1Layout.setHorizontalGroup(
/*  644: 644 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  645:     */       
/*  646: 646 */       .addComponent(this.jPanel3, -1, -1, 32767).addComponent(this.jPanel4, 
/*  647: 647 */       -1, 580, 32767).addComponent(
/*  648: 648 */       this.jPanel5, -1, 580, 32767)
/*  649: 649 */       .addComponent(this.jPanel6, GroupLayout.Alignment.TRAILING, 
/*  650: 650 */       -1, -1, 
/*  651: 651 */       32767));
/*  652: 652 */     jPanel1Layout.setVerticalGroup(
/*  653:     */     
/*  654: 654 */       jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  655:     */       
/*  656:     */ 
/*  657:     */ 
/*  658:     */ 
/*  659:     */ 
/*  660:     */ 
/*  661:     */ 
/*  662:     */ 
/*  663:     */ 
/*  664:     */ 
/*  665:     */ 
/*  666:     */ 
/*  667:     */ 
/*  668:     */ 
/*  669:     */ 
/*  670:     */ 
/*  671:     */ 
/*  672:     */ 
/*  673:     */ 
/*  674:     */ 
/*  675: 675 */       .addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jPanel3, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel4, -2, 150, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel5, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanel6, -2, -1, -2).addContainerGap(14, 32767)));
/*  676:     */     
/*  677: 677 */     getContentPane().add(this.jPanel1, "Center");
/*  678:     */     
/*  679: 679 */     this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
/*  680:     */     
/*  681: 681 */     this.jButton1.setText("message.close");
/*  682: 682 */     this.jButton1.addActionListener(new ActionListener()
/*  683:     */     {
/*  684:     */       public void actionPerformed(ActionEvent e)
/*  685:     */       {
/*  686: 685 */         GridTestResultDialog.this.dispose();
/*  687:     */       }
/*  688: 688 */     });
/*  689: 689 */     this.jButton2.setText("message.saveas");
/*  690: 690 */     this.jButton2.addActionListener(new ActionListener()
/*  691:     */     {
/*  692:     */       public void actionPerformed(ActionEvent e)
/*  693:     */       {
/*  694: 693 */         if (!SolarPowerTray.isLogin)
/*  695:     */         {
/*  696: 694 */           new LoginJDialog(new Frame(), true);
/*  697: 695 */           return;
/*  698:     */         }
/*  699: 697 */         GridTestResultDialog.this.saveAs();
/*  700:     */       }
/*  701: 700 */     });
/*  702: 701 */     this.jButton3.setText("message.print");
/*  703: 702 */     this.jButton3.addActionListener(new ActionListener()
/*  704:     */     {
/*  705:     */       public void actionPerformed(ActionEvent e)
/*  706:     */       {
/*  707: 705 */         if (!SolarPowerTray.isLogin)
/*  708:     */         {
/*  709: 706 */           new LoginJDialog(new Frame(), true);
/*  710: 707 */           return;
/*  711:     */         }
/*  712: 709 */         GridTestResultDialog.this.printTextAction();
/*  713:     */       }
/*  714: 712 */     });
/*  715: 713 */     GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
/*  716: 714 */     this.jPanel2.setLayout(jPanel2Layout);
/*  717: 715 */     jPanel2Layout.setHorizontalGroup(
/*  718:     */     
/*  719: 717 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  720:     */       
/*  721:     */ 
/*  722:     */ 
/*  723:     */ 
/*  724:     */ 
/*  725:     */ 
/*  726:     */ 
/*  727: 725 */       .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addContainerGap(372, 32767).addComponent(this.jButton3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1).addContainerGap()));
/*  728: 726 */     jPanel2Layout.setVerticalGroup(
/*  729: 727 */       jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  730:     */       
/*  731:     */ 
/*  732:     */ 
/*  733:     */ 
/*  734:     */ 
/*  735:     */ 
/*  736:     */ 
/*  737: 735 */       .addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2).addComponent(this.jButton3)).addContainerGap(-1, 
/*  738: 736 */       32767)));
/*  739:     */     
/*  740: 738 */     getContentPane().add(this.jPanel2, "South");
/*  741:     */     
/*  742: 740 */     pack();
/*  743:     */   }
/*  744:     */   
/*  745:     */   public int getPagesCount(String curStr)
/*  746:     */   {
/*  747: 744 */     int page = 0;
/*  748: 745 */     int count = 0;
/*  749: 746 */     String str = curStr;
/*  750: 747 */     while (str.length() > 0)
/*  751:     */     {
/*  752: 748 */       int position = str.indexOf('\n');
/*  753: 749 */       count++;
/*  754: 750 */       if (position != -1) {
/*  755: 751 */         str = str.substring(position + 1);
/*  756:     */       } else {
/*  757: 753 */         str = "";
/*  758:     */       }
/*  759:     */     }
/*  760: 756 */     if (count > 0) {
/*  761: 757 */       page = count / 54 + 1;
/*  762:     */     }
/*  763: 759 */     return page;
/*  764:     */   }
/*  765:     */   
/*  766:     */   public static String getBlankString(int len)
/*  767:     */   {
/*  768: 763 */     String str = "";
/*  769: 764 */     for (int item = 0; item < len; item++) {
/*  770: 765 */       str = str + " ";
/*  771:     */     }
/*  772: 767 */     return str;
/*  773:     */   }
/*  774:     */   
/*  775:     */   private String getPrintStr()
/*  776:     */   {
/*  777: 771 */     StringBuffer sb = new StringBuffer();
/*  778: 772 */     sb.append(getBlankString(88)).append("Model: ").append(this.result.getModelName()).append("\n");
/*  779: 773 */     sb.append("\n");
/*  780: 774 */     sb.append(getBlankString(81)).append("FWversion: ").append(this.result.getFwVersion()).append("\n");
/*  781: 775 */     sb.append("\n");
/*  782: 776 */     sb.append(getBlankString(90)).append("Date: ").append(this.result.getDateStr()).append("\n");
/*  783: 777 */     sb.append("\n");
/*  784: 778 */     sb.append(getBlankString(90)).append("Time: ").append(this.result.getTimeStr()).append("\n");
/*  785: 779 */     sb.append("\n");
/*  786: 780 */     sb.append("\n");
/*  787: 781 */     sb.append("\n");
/*  788: 782 */     sb.append("Maximum voltage check                                       Minimum voltage check                 ").append("\n");
/*  789: 783 */     sb.append("\n");
/*  790: 784 */     sb.append("   Threshold value               Reading               Threshold value               Reading     ").append("\n");
/*  791: 785 */     sb.append("\n");
/*  792: 786 */     sb.append("   Vmax 1.2Vn(276V)         ").append(this.result.getHighVoltage()).append("V").append("                      Vmin 0.8Vn(184V)        ").append(this.result.getLowVoltage()).append("V").append("     ").append("\n");
/*  793: 787 */     sb.append("\n");
/*  794: 788 */     sb.append("   Ttrip 100ms                    ").append(this.result.getHighVoltageT()).append("ms").append("                      Ttrip 100ms                  ").append(this.result.getLowVoltageT()).append("ms").append("     ").append("\n");
/*  795: 789 */     sb.append("\n");
/*  796: 790 */     sb.append("\n");
/*  797: 791 */     sb.append("\n");
/*  798: 792 */     sb.append("Maximum frequency check                                   Minimum frequency check               ").append("\n");
/*  799: 793 */     sb.append("\n");
/*  800: 794 */     sb.append("   Threshold value               Reading               Threshold value               Reading     ").append("\n");
/*  801: 795 */     sb.append("\n");
/*  802: 796 */     sb.append("   Fmax 50.3Hz                   ").append(this.result.getHighFrequency()).append("Hz").append("                        Fmin 49.7Hz               ").append(this.result.getLowFrequency()).append("Hz").append("     ").append("\n");
/*  803: 797 */     sb.append("\n");
/*  804: 798 */     sb.append("   Ttrip 100ms                     ").append(this.result.getHighFrequencyT()).append("ms").append("                         Ttrip 100ms               ").append(this.result.getLowFrequencyT()).append("ms").append("     ").append("\n");
/*  805: 799 */     sb.append("\n");
/*  806: 800 */     sb.append("\n");
/*  807: 801 */     sb.append("                                                      ").append(this.result.getResult());
/*  808: 802 */     return sb.toString();
/*  809:     */   }
/*  810:     */   
/*  811:     */   public void createPdf(String filepathStr)
/*  812:     */   {
/*  813: 806 */     Document pdf = new Document();
/*  814:     */     try
/*  815:     */     {
/*  816: 808 */       PdfWriter.getInstance(pdf, new FileOutputStream(filepathStr));
/*  817: 809 */       BaseFont bf = BaseFont.createFont(Constants.PDF_STYLE_PATH + "simsun.ttc,1", "Identity-H", false);
/*  818: 810 */       com.itextpdf.text.Font font = new com.itextpdf.text.Font(bf, 12.0F, 0, BaseColor.BLACK);
/*  819: 811 */       pdf.addTitle("Grid self-test");
/*  820: 812 */       pdf.addAuthor(GlobalVariables.customerConfig.getCustomerName());
/*  821: 813 */       pdf.open();
/*  822: 814 */       pdf.add(new Paragraph(getHeadStr(68, "Model:", this.result.getModelName()), font));
/*  823: 815 */       pdf.add(new Paragraph(getHeadStr(68, "FW version:", this.result.getFwVersion()), font));
/*  824: 816 */       pdf.add(new Paragraph(getHeadStr(68, "Date:", this.result.getDateStr()), font));
/*  825: 817 */       pdf.add(new Paragraph(getHeadStr(68, "Time:", this.result.getTimeStr()), font));
/*  826: 818 */       pdf.add(new Paragraph("  ", font));
/*  827: 819 */       pdf.add(new Paragraph(getTitleStr("Maximum voltage check", "Minimum voltage check"), font));
/*  828: 820 */       pdf.add(new Paragraph(getSubTitleStr(getBlankString(5), "Threshold value", "Reading") + getSubTitleStr(getBlankString(5), "Threshold value", "Reading"), font));
/*  829: 821 */       pdf.add(new Paragraph(getValueStr("Vmax ", "1.2 Vn(276 V)", new StringBuilder(String.valueOf(this.result.getHighVoltage())).append(" V").toString()) + getValueStr("Vmin ", "0.8 Vn(184 V)", new StringBuilder(String.valueOf(this.result.getLowVoltage())).append(" V").toString()), font));
/*  830: 822 */       pdf.add(new Paragraph(getValueStr("Ttrip", "100 ms", new StringBuilder(String.valueOf(this.result.getHighVoltageT())).append(" ms").toString()) + getValueStr("Ttrip", "200 ms", new StringBuilder(String.valueOf(this.result.getLowVoltageT())).append(" ms").toString()), font));
/*  831:     */       
/*  832: 824 */       pdf.add(new Paragraph("  ", font));
/*  833: 825 */       pdf.add(new Paragraph(getTitleStr("Maximum Frequency check", "Minimum Frequency check"), font));
/*  834: 826 */       pdf.add(new Paragraph(getSubTitleStr(getBlankString(5), "Threshold value", "Reading") + getSubTitleStr(getBlankString(5), "Threshold value", "Reading"), font));
/*  835: 827 */       pdf.add(new Paragraph(getValueStr("Fmax ", "50.3 Hz", new StringBuilder(String.valueOf(this.result.getHighFrequency())).append(" Hz").toString()) + getValueStr("Fmin ", "49.7 Hz", new StringBuilder(String.valueOf(this.result.getLowFrequency())).append(" Hz").toString()), font));
/*  836: 828 */       pdf.add(new Paragraph(getValueStr("Ttrip", "100 ms", new StringBuilder(String.valueOf(this.result.getHighFrequencyT())).append(" ms").toString()) + getValueStr("Ttrip", "100 ms", new StringBuilder(String.valueOf(this.result.getLowFrequencyT())).append(" ms").toString()), font));
/*  837: 829 */       pdf.add(new Paragraph(getResultStr(this.result.getResult()), font));
/*  838:     */     }
/*  839:     */     catch (Exception ie)
/*  840:     */     {
/*  841: 832 */       DisplayMessage.showErrorDialog("message.saveerror");
/*  842:     */       try
/*  843:     */       {
/*  844: 835 */         if (pdf != null) {
/*  845: 836 */           pdf.close();
/*  846:     */         }
/*  847:     */       }
/*  848:     */       catch (Exception ex)
/*  849:     */       {
/*  850: 839 */         ex.printStackTrace();
/*  851:     */       }
/*  852:     */     }
/*  853:     */     finally
/*  854:     */     {
/*  855:     */       try
/*  856:     */       {
/*  857: 835 */         if (pdf != null) {
/*  858: 836 */           pdf.close();
/*  859:     */         }
/*  860:     */       }
/*  861:     */       catch (Exception ex)
/*  862:     */       {
/*  863: 839 */         ex.printStackTrace();
/*  864:     */       }
/*  865:     */     }
/*  866:     */   }
/*  867:     */   
/*  868:     */   public String getHeadStr(int len, String title, String value)
/*  869:     */   {
/*  870: 845 */     return getBlankString(len - title.toUpperCase().length()) + title + " " + value;
/*  871:     */   }
/*  872:     */   
/*  873:     */   public String getTitleStr(String str1, String str2)
/*  874:     */   {
/*  875: 850 */     int halflen1 = (44 - str1.length()) / 2;
/*  876: 851 */     int halflen2 = (44 - str2.length()) / 2;
/*  877: 852 */     String str = getBlankString(halflen1) + str1 + getBlankString(44 - halflen1 - str1.length());
/*  878: 853 */     str = str + getBlankString(halflen2) + str2 + getBlankString(44 - halflen2 - str2.length());
/*  879: 854 */     return str;
/*  880:     */   }
/*  881:     */   
/*  882:     */   public String getSubTitleStr(String str1, String str2, String str3)
/*  883:     */   {
/*  884: 858 */     String str = "";
/*  885: 859 */     str = str + str1;
/*  886: 860 */     str = str + getBlankString(5);
/*  887: 861 */     str = str + str2;
/*  888: 862 */     str = str + getBlankString(5);
/*  889: 863 */     str = str + str3;
/*  890: 864 */     str = str + getBlankString(44 - str.length());
/*  891: 865 */     return str;
/*  892:     */   }
/*  893:     */   
/*  894:     */   public String getValueStr(String str1, String str2, String str3)
/*  895:     */   {
/*  896: 869 */     String str = str1 + getBlankString(5);
/*  897: 870 */     str = str + str2;
/*  898: 871 */     str = str + getBlankString(30 - str.length());
/*  899: 872 */     str = str + str3;
/*  900: 873 */     str = str + getBlankString(44 - str.length());
/*  901: 874 */     return str;
/*  902:     */   }
/*  903:     */   
/*  904:     */   public String getResultStr(String str)
/*  905:     */   {
/*  906: 878 */     String result = getBlankString((88 - str.length()) / 2) + str;
/*  907: 879 */     result = result + getBlankString(88 - result.length());
/*  908: 880 */     return result;
/*  909:     */   }
/*  910:     */   
/*  911:     */   public int getSaveDialog()
/*  912:     */   {
/*  913: 884 */     String saveFileName = "Grid_test_result.pdf";
/*  914: 885 */     this.fDialog = new JFileChooser();
/*  915:     */     
/*  916: 887 */     PDFFileFilter txtFilter = new PDFFileFilter();
/*  917: 888 */     this.fDialog.addChoosableFileFilter(txtFilter);
/*  918: 889 */     this.fDialog.setFileFilter(txtFilter);
/*  919: 890 */     this.fDialog.setAcceptAllFileFilterUsed(true);
/*  920: 891 */     this.fDialog.setSelectedFile(new File(saveFileName));
/*  921: 892 */     return this.fDialog.showSaveDialog(this);
/*  922:     */   }
/*  923:     */   
/*  924:     */   private void saveAs()
/*  925:     */   {
/*  926: 896 */     int result = getSaveDialog();
/*  927: 897 */     if (result == 0)
/*  928:     */     {
/*  929: 898 */       String filepathStr = this.fDialog.getSelectedFile().getPath();
/*  930: 899 */       File newFile = new File(filepathStr);
/*  931: 900 */       if (newFile.exists())
/*  932:     */       {
/*  933: 901 */         int re = 
/*  934: 902 */           DisplayMessage.showConfirmDialog(
/*  935: 903 */           "message.confirmoverwrite", 
/*  936: 904 */           "message.info");
/*  937: 905 */         if (re == 0) {
/*  938: 906 */           createPdf(filepathStr);
/*  939:     */         } else {
/*  940: 908 */           saveAs();
/*  941:     */         }
/*  942:     */       }
/*  943:     */       else
/*  944:     */       {
/*  945:     */         try
/*  946:     */         {
/*  947: 912 */           newFile.createNewFile();
/*  948:     */         }
/*  949:     */         catch (IOException e)
/*  950:     */         {
/*  951: 914 */           e.printStackTrace();
/*  952:     */         }
/*  953: 916 */         createPdf(filepathStr);
/*  954:     */       }
/*  955:     */     }
/*  956:     */   }
/*  957:     */   
/*  958:     */   private void printTextAction()
/*  959:     */   {
/*  960: 922 */     String printStr = getPrintStr();
/*  961: 923 */     if ((printStr != null) && (printStr.length() > 0))
/*  962:     */     {
/*  963: 924 */       this.PAGES = getPagesCount(printStr);
/*  964: 925 */       PrinterJob myPrtJob = PrinterJob.getPrinterJob();
/*  965: 926 */       PageFormat pageFormat = myPrtJob.defaultPage();
/*  966: 927 */       myPrtJob.setPrintable(this, pageFormat);
/*  967: 928 */       if (myPrtJob.printDialog()) {
/*  968:     */         try
/*  969:     */         {
/*  970: 930 */           myPrtJob.print();
/*  971:     */         }
/*  972:     */         catch (PrinterException pe)
/*  973:     */         {
/*  974: 932 */           pe.printStackTrace();
/*  975:     */         }
/*  976:     */       }
/*  977:     */     }
/*  978:     */     else
/*  979:     */     {
/*  980: 936 */       DisplayMessage.showErrorDialog("message.operationfailure");
/*  981:     */     }
/*  982:     */   }
/*  983:     */   
/*  984:     */   public String[] getDrawText(String s)
/*  985:     */   {
/*  986: 941 */     String[] drawText = new String[this.PAGES];
/*  987: 942 */     for (int i = 0; i < this.PAGES; i++) {
/*  988: 943 */       drawText[i] = "";
/*  989:     */     }
/*  990: 945 */     int suffix = 0;int lines = 0;
/*  991: 946 */     while (s.length() > 0) {
/*  992: 947 */       if (lines < 54)
/*  993:     */       {
/*  994: 948 */         int k = s.indexOf('\n');
/*  995: 949 */         if (k != -1)
/*  996:     */         {
/*  997: 950 */           lines++;
/*  998: 951 */           drawText[suffix] = (drawText[suffix] + s.substring(0, k + 1));
/*  999: 952 */           if (s.substring(k + 1).length() > 0) {
/* 1000: 953 */             s = s.substring(k + 1);
/* 1001:     */           }
/* 1002:     */         }
/* 1003:     */         else
/* 1004:     */         {
/* 1005: 955 */           lines++;
/* 1006: 956 */           drawText[suffix] = (drawText[suffix] + s);
/* 1007: 957 */           s = "";
/* 1008:     */         }
/* 1009:     */       }
/* 1010:     */       else
/* 1011:     */       {
/* 1012: 960 */         lines = 0;
/* 1013: 961 */         suffix++;
/* 1014:     */       }
/* 1015:     */     }
/* 1016: 964 */     return drawText;
/* 1017:     */   }
/* 1018:     */   
/* 1019:     */   private void drawCurrentPageText(Graphics2D g2, PageFormat pf, int page)
/* 1020:     */   {
/* 1021: 968 */     JTextArea area = new JTextArea();
/* 1022: 969 */     java.awt.Font f = area.getFont();
/* 1023: 970 */     String s = getDrawText(getPrintStr())[page];
/* 1024:     */     
/* 1025: 972 */     float ascent = 16.0F;
/* 1026: 973 */     int i = f.getSize();int lines = 0;
/* 1027: 974 */     while ((s.length() > 0) && (lines < 54))
/* 1028:     */     {
/* 1029: 975 */       int k = s.indexOf('\n');
/* 1030: 976 */       if (k != -1)
/* 1031:     */       {
/* 1032: 977 */         lines++;
/* 1033: 978 */         String drawText = s.substring(0, k);
/* 1034: 979 */         g2.drawString(drawText, 0.0F, ascent);
/* 1035: 980 */         if (s.substring(k + 1).length() > 0)
/* 1036:     */         {
/* 1037: 981 */           s = s.substring(k + 1);
/* 1038: 982 */           ascent += i;
/* 1039:     */         }
/* 1040:     */       }
/* 1041:     */       else
/* 1042:     */       {
/* 1043: 985 */         lines++;
/* 1044: 986 */         String drawText = s;
/* 1045: 987 */         g2.drawString(drawText, 0.0F, ascent);
/* 1046: 988 */         s = "";
/* 1047:     */       }
/* 1048:     */     }
/* 1049:     */   }
/* 1050:     */   
/* 1051:     */   public int print(Graphics g, PageFormat pf, int page)
/* 1052:     */     throws PrinterException
/* 1053:     */   {
/* 1054: 996 */     Graphics2D g2 = (Graphics2D)g;
/* 1055: 997 */     g2.setPaint(Color.black);
/* 1056: 998 */     this.PAGES = getPagesCount(getPrintStr());
/* 1057: 999 */     if (page >= this.PAGES) {
/* 1058:1000 */       return 1;
/* 1059:     */     }
/* 1060:1001 */     g2.translate(pf.getImageableX(), pf.getImageableY());
/* 1061:1002 */     drawCurrentPageText(g2, pf, page);
/* 1062:1003 */     return 0;
/* 1063:     */   }
/* 1064:     */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.GridTestResultDialog
 * JD-Core Version:    0.7.0.1
 */