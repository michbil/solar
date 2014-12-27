/*  1:   */ package cn.com.voltronic.solar.view.component;
/*  2:   */ 
/*  3:   */ import java.awt.event.ActionEvent;
/*  4:   */ import java.awt.event.ActionListener;
/*  5:   */ import java.awt.event.MouseAdapter;
/*  6:   */ import java.awt.event.MouseMotionListener;
/*  7:   */ import java.io.PrintStream;
/*  8:   */ 
/*  9:   */ public class InactionMouseListner
/* 10:   */   extends MouseAdapter
/* 11:   */   implements MouseMotionListener, ActionListener
/* 12:   */ {
/* 13:   */   public void actionPerformed(ActionEvent e)
/* 14:   */   {
/* 15:26 */     System.out.println("actionPerformed");
/* 16:   */   }
/* 17:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.InactionMouseListner
 * JD-Core Version:    0.7.0.1
 */