/*  1:   */ package cn.com.voltronic.solar.upgrade;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.constants.Constants;
/*  4:   */ import cn.com.voltronic.solar.view.component.AALabel;
/*  5:   */ import java.awt.Container;
/*  6:   */ import javax.swing.GroupLayout;
/*  7:   */ import javax.swing.GroupLayout.Alignment;
/*  8:   */ import javax.swing.GroupLayout.ParallelGroup;
/*  9:   */ import javax.swing.GroupLayout.SequentialGroup;
/* 10:   */ import javax.swing.JProgressBar;
/* 11:   */ 
/* 12:   */ public class DownloadDialog16
/* 13:   */   extends DownloadDialog
/* 14:   */ {
/* 15:   */   private static final long serialVersionUID = 7387520309371404645L;
/* 16:   */   
/* 17:   */   public DownloadDialog16()
/* 18:   */   {
/* 19:19 */     initComponents();
/* 20:20 */     setIconImage(Constants.CONNECTEDIMG);
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void initComponents()
/* 24:   */   {
/* 25:24 */     this.note = new AALabel();
/* 26:25 */     this.note.setText("message.prepareupgrade");
/* 27:26 */     this.progress = new JProgressBar(0, 100);
/* 28:27 */     this.progress.setStringPainted(true);
/* 29:28 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 30:29 */     getContentPane().setLayout(layout);
/* 31:30 */     layout
/* 32:31 */       .setHorizontalGroup(layout
/* 33:32 */       .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 34:33 */       .addGroup(
/* 35:34 */       layout
/* 36:35 */       .createSequentialGroup()
/* 37:36 */       .addContainerGap()
/* 38:37 */       .addGroup(
/* 39:38 */       layout
/* 40:39 */       .createParallelGroup(
/* 41:40 */       GroupLayout.Alignment.LEADING)
/* 42:41 */       .addGroup(
/* 43:42 */       layout
/* 44:43 */       .createSequentialGroup()
/* 45:44 */       .addComponent(
/* 46:45 */       this.progress, 
/* 47:46 */       -1, 
/* 48:47 */       326, 
/* 49:48 */       32767)
/* 50:49 */       .addContainerGap())
/* 51:50 */       .addGroup(
/* 52:51 */       GroupLayout.Alignment.TRAILING, 
/* 53:52 */       layout
/* 54:53 */       .createSequentialGroup()
/* 55:54 */       .addComponent(
/* 56:55 */       this.note)
/* 57:56 */       .addGap(
/* 58:57 */       150, 
/* 59:58 */       150, 
/* 60:59 */       150)))));
/* 61:60 */     layout.setVerticalGroup(
/* 62:61 */       layout.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 63:   */       
/* 64:   */ 
/* 65:   */ 
/* 66:65 */       .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.note).addGap(18, 18, 18).addComponent(this.progress, -2, -1, -2).addContainerGap(18, 
/* 67:66 */       32767)));
/* 68:67 */     pack();
/* 69:   */   }
/* 70:   */   
/* 71:   */   public static void main(String[] args)
/* 72:   */   {
/* 73:71 */     new DownloadDialog16().setVisible(true);
/* 74:   */   }
/* 75:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.DownloadDialog16
 * JD-Core Version:    0.7.0.1
 */