/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.Cursor;
/*  4:   */ import java.awt.Desktop;
/*  5:   */ import java.awt.Desktop.Action;
/*  6:   */ import java.awt.event.MouseAdapter;
/*  7:   */ import java.awt.event.MouseEvent;
/*  8:   */ import java.net.URI;
/*  9:   */ import javax.swing.JLabel;
/* 10:   */ 
/* 11:   */ public class LinkLabel
/* 12:   */   extends JLabel
/* 13:   */ {
/* 14:   */   private static final long serialVersionUID = 6154377678777257460L;
/* 15:   */   private String text;
/* 16:   */   private String url;
/* 17:   */   private boolean isSupported;
/* 18:   */   
/* 19:   */   public LinkLabel(String text, String url)
/* 20:   */   {
/* 21:18 */     this.text = text;
/* 22:19 */     this.url = url;
/* 23:   */     try
/* 24:   */     {
/* 25:22 */       this.isSupported = ((Desktop.isDesktopSupported()) && 
/* 26:23 */         (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)));
/* 27:   */     }
/* 28:   */     catch (Exception e)
/* 29:   */     {
/* 30:27 */       this.isSupported = false;
/* 31:   */     }
/* 32:29 */     setText(false);
/* 33:30 */     addMouseListener(new MouseAdapter()
/* 34:   */     {
/* 35:   */       public void mouseEntered(MouseEvent e)
/* 36:   */       {
/* 37:34 */         LinkLabel.this.setText(LinkLabel.this.isSupported);
/* 38:35 */         if (LinkLabel.this.isSupported) {
/* 39:37 */           LinkLabel.this.setCursor(new Cursor(12));
/* 40:   */         }
/* 41:   */       }
/* 42:   */       
/* 43:   */       public void mouseExited(MouseEvent e)
/* 44:   */       {
/* 45:42 */         LinkLabel.this.setText(false);
/* 46:   */       }
/* 47:   */       
/* 48:   */       public void mouseClicked(MouseEvent e)
/* 49:   */       {
/* 50:   */         try
/* 51:   */         {
/* 52:48 */           Desktop.getDesktop().browse(new URI(LinkLabel.this.url));
/* 53:   */         }
/* 54:   */         catch (Exception localException) {}
/* 55:   */       }
/* 56:   */     });
/* 57:   */   }
/* 58:   */   
/* 59:   */   private void setText(boolean b)
/* 60:   */   {
/* 61:59 */     if (!b) {
/* 62:61 */       setText("<html><font color=blue><u>" + this.text);
/* 63:   */     } else {
/* 64:65 */       setText("<html><font color=red><u>" + this.text);
/* 65:   */     }
/* 66:   */   }
/* 67:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.LinkLabel
 * JD-Core Version:    0.7.0.1
 */