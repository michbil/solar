/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import javax.swing.filechooser.FileFilter;
/*   5:    */ 
/*   6:    */ class TxtFileFilter
/*   7:    */   extends FileFilter
/*   8:    */ {
/*   9:    */   public String getDescription()
/*  10:    */   {
/*  11:790 */     return "*.txt(Txt File)";
/*  12:    */   }
/*  13:    */   
/*  14:    */   public boolean accept(File file)
/*  15:    */   {
/*  16:794 */     String name = file.getName();
/*  17:795 */     return name.toLowerCase().endsWith(".txt");
/*  18:    */   }
/*  19:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.TxtFileFilter
 * JD-Core Version:    0.7.0.1
 */