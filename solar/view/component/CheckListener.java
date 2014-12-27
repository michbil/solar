/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.event.KeyEvent;
/*  4:   */ import java.awt.event.KeyListener;
/*  5:   */ import java.awt.event.MouseEvent;
/*  6:   */ import java.awt.event.MouseListener;
/*  7:   */ import javax.swing.JList;
/*  8:   */ import javax.swing.ListModel;
/*  9:   */ 
/* 10:   */ public class CheckListener
/* 11:   */   implements MouseListener, KeyListener
/* 12:   */ {
/* 13:   */   protected JList m_list;
/* 14:   */   
/* 15:   */   public CheckListener(JList m_list)
/* 16:   */   {
/* 17:20 */     this.m_list = m_list;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void mouseClicked(MouseEvent e)
/* 21:   */   {
/* 22:24 */     doCheck();
/* 23:   */   }
/* 24:   */   
/* 25:   */   public void mousePressed(MouseEvent e) {}
/* 26:   */   
/* 27:   */   public void mouseReleased(MouseEvent e) {}
/* 28:   */   
/* 29:   */   public void mouseEntered(MouseEvent e) {}
/* 30:   */   
/* 31:   */   public void mouseExited(MouseEvent e) {}
/* 32:   */   
/* 33:   */   public void keyPressed(KeyEvent e)
/* 34:   */   {
/* 35:40 */     if (e.getKeyChar() == ' ') {
/* 36:41 */       doCheck();
/* 37:   */     }
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void keyTyped(KeyEvent e) {}
/* 41:   */   
/* 42:   */   public void keyReleased(KeyEvent e) {}
/* 43:   */   
/* 44:   */   protected void doCheck()
/* 45:   */   {
/* 46:53 */     int index = this.m_list.getSelectedIndex();
/* 47:54 */     if (index < 0) {
/* 48:55 */       return;
/* 49:   */     }
/* 50:56 */     CheckBoxData data = (CheckBoxData)this.m_list.getModel().getElementAt(index);
/* 51:57 */     data.invertSelected();
/* 52:58 */     this.m_list.repaint();
/* 53:   */   }
/* 54:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.CheckListener
 * JD-Core Version:    0.7.0.1
 */