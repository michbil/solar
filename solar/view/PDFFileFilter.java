/*   1:    */ package cn.com.voltronic.solar.view;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import javax.swing.filechooser.FileFilter;
/*   5:    */ 
/*   6:    */ class PDFFileFilter
/*   7:    */   extends FileFilter
/*   8:    */ {
/*   9:    */   public String getDescription()
/*  10:    */   {
/*  11:816 */     return "*.pdf(PDF File)";
/*  12:    */   }
/*  13:    */   
/*  14:    */   public boolean accept(File file)
/*  15:    */   {
/*  16:820 */     String name = file.getName();
/*  17:821 */     return name.toLowerCase().endsWith(".pdf");
/*  18:    */   }
/*  19:    */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.PDFFileFilter
 * JD-Core Version:    0.7.0.1
 */