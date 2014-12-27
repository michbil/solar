/*   1:    */ package cn.com.voltronic.solar.view.component;
/*   2:    */ 
/*   3:    */ import java.awt.Toolkit;
/*   4:    */ import java.awt.datatransfer.Clipboard;
/*   5:    */ import java.awt.datatransfer.DataFlavor;
/*   6:    */ import java.awt.datatransfer.Transferable;
/*   7:    */ import java.awt.event.ActionEvent;
/*   8:    */ import java.awt.event.ActionListener;
/*   9:    */ import java.awt.event.MouseEvent;
/*  10:    */ import java.awt.event.MouseListener;
/*  11:    */ import java.util.List;
/*  12:    */ import javax.swing.JMenuItem;
/*  13:    */ import javax.swing.JPopupMenu;
/*  14:    */ import javax.swing.JTextPane;
/*  15:    */ import javax.swing.KeyStroke;
/*  16:    */ 
/*  17:    */ public class AATextPanel
/*  18:    */   extends JTextPane
/*  19:    */   implements MouseListener, I18NListener
/*  20:    */ {
/*  21:    */   private static final long serialVersionUID = -3221375772900197360L;
/*  22: 28 */   private JPopupMenu pop = null;
/*  23: 30 */   private JMenuItem copy = null;
/*  24:    */   
/*  25:    */   public AATextPanel()
/*  26:    */   {
/*  27: 34 */     compList.add(this);
/*  28: 35 */     setFont(font13);
/*  29: 36 */     setBackground(bgColor);
/*  30: 37 */     setSelectionColor(selectColor);
/*  31: 38 */     init();
/*  32: 39 */     setOpaque(false);
/*  33:    */   }
/*  34:    */   
/*  35:    */   private void init()
/*  36:    */   {
/*  37: 43 */     addMouseListener(this);
/*  38: 44 */     this.pop = new JPopupMenu();
/*  39: 45 */     this.pop.add(this.copy = new JMenuItem("Copy"));
/*  40: 46 */     this.copy.setAccelerator(KeyStroke.getKeyStroke(67, 2));
/*  41: 47 */     this.copy.addActionListener(new ActionListener()
/*  42:    */     {
/*  43:    */       public void actionPerformed(ActionEvent e)
/*  44:    */       {
/*  45: 49 */         AATextPanel.this.action(e);
/*  46:    */       }
/*  47: 51 */     });
/*  48: 52 */     add(this.pop);
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void action(ActionEvent e)
/*  52:    */   {
/*  53: 56 */     String str = e.getActionCommand();
/*  54: 57 */     if (str.equals(this.copy.getText())) {
/*  55: 58 */       copy();
/*  56:    */     }
/*  57:    */   }
/*  58:    */   
/*  59:    */   public JPopupMenu getPop()
/*  60:    */   {
/*  61: 63 */     return this.pop;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public void setPop(JPopupMenu pop)
/*  65:    */   {
/*  66: 67 */     this.pop = pop;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public boolean isClipboardString()
/*  70:    */   {
/*  71: 71 */     boolean b = false;
/*  72: 72 */     Clipboard clipboard = getToolkit().getSystemClipboard();
/*  73: 73 */     Transferable content = clipboard.getContents(this);
/*  74:    */     try
/*  75:    */     {
/*  76: 75 */       if ((content.getTransferData(DataFlavor.stringFlavor) instanceof String)) {
/*  77: 76 */         b = true;
/*  78:    */       }
/*  79:    */     }
/*  80:    */     catch (Exception localException) {}
/*  81: 80 */     return b;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public boolean isCanCopy()
/*  85:    */   {
/*  86: 84 */     boolean b = false;
/*  87: 85 */     int start = getSelectionStart();
/*  88: 86 */     int end = getSelectionEnd();
/*  89: 87 */     if ((start != end) && 
/*  90: 88 */       (!"".equals(getSelectedText().trim()))) {
/*  91: 89 */       b = true;
/*  92:    */     }
/*  93: 92 */     return b;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public void mouseClicked(MouseEvent e) {}
/*  97:    */   
/*  98:    */   public void mouseEntered(MouseEvent e) {}
/*  99:    */   
/* 100:    */   public void mouseExited(MouseEvent e) {}
/* 101:    */   
/* 102:    */   public void mousePressed(MouseEvent e)
/* 103:    */   {
/* 104:105 */     if (e.getButton() == 3)
/* 105:    */     {
/* 106:106 */       this.copy.setEnabled(isCanCopy());
/* 107:107 */       this.pop.show(this, e.getX(), e.getY());
/* 108:    */     }
/* 109:    */   }
/* 110:    */   
/* 111:    */   public void mouseReleased(MouseEvent e) {}
/* 112:    */   
/* 113:    */   public void changeLang() {}
/* 114:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.AATextPanel
 * JD-Core Version:    0.7.0.1
 */