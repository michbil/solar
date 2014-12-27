/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import javax.swing.filechooser.FileFilter;
/*   5:    */ 
/*   6:    */ class JavaFileFilter
/*   7:    */   extends FileFilter
/*   8:    */ {
/*   9:    */   public String getDescription()
/*  10:    */   {
/*  11:801 */     return "*.java(Java File)";
/*  12:    */   }
/*  13:    */   
/*  14:    */   public boolean accept(File file)
/*  15:    */   {
/*  16:805 */     if (!file.isDirectory())
/*  17:    */     {
/*  18:806 */       String name = file.getName();
/*  19:807 */       return name.toLowerCase().endsWith(".java");
/*  20:    */     }
/*  21:809 */     return true;
/*  22:    */   }
/*  23:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.JavaFileFilter
 * JD-Core Version:    0.7.0.1
 */