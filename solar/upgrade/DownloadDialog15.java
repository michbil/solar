/*  1:   */ package cn.com.voltronic.solar.upgrade;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.constants.Constants;
/*  4:   */ import cn.com.voltronic.solar.view.component.AALabel;
/*  5:   */ import java.awt.Container;
/*  6:   */ import javax.swing.JProgressBar;
/*  7:   */ 
/*  8:   */ public class DownloadDialog15
/*  9:   */   extends DownloadDialog
/* 10:   */ {
/* 11:   */   private static final long serialVersionUID = 7387520309371404645L;
/* 12:   */   
/* 13:   */   public DownloadDialog15()
/* 14:   */   {
/* 15:18 */     initComponents();
/* 16:19 */     setIconImage(Constants.CONNECTEDIMG);
/* 17:   */   }
/* 18:   */   
/* 19:   */   public void init(Download download)
/* 20:   */   {
/* 21:23 */     this.progress.setValue(0);
/* 22:24 */     this.note.setText("message.prepareupgrade");
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void initComponents()
/* 26:   */   {
/* 27:28 */     this.note = new AALabel();
/* 28:29 */     this.note.setText("message.prepareupgrade");
/* 29:30 */     this.progress = new JProgressBar(0, 100);
/* 30:31 */     this.progress.setStringPainted(true);
/* 31:32 */     getContentPane().setLayout(null);
/* 32:33 */     getContentPane().add(this.note);
/* 33:34 */     this.note.setBounds(60, 10, 350, 15);
/* 34:35 */     getContentPane().add(this.progress);
/* 35:36 */     this.progress.setBounds(10, 45, 350, 20);
/* 36:   */   }
/* 37:   */   
/* 38:   */   public static void main(String[] args)
/* 39:   */   {
/* 40:40 */     DownloadDialog15 download = new DownloadDialog15();
/* 41:41 */     download.setSize(380, 120);
/* 42:42 */     download.setVisible(true);
/* 43:   */   }
/* 44:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.DownloadDialog15
 * JD-Core Version:    0.7.0.1
 */