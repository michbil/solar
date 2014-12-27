/*  1:   */ package cn.com.voltronic.solar.util;
/*  2:   */ 
/*  3:   */ import java.io.BufferedReader;
/*  4:   */ import java.io.InputStream;
/*  5:   */ import java.io.InputStreamReader;
/*  6:   */ import java.io.PrintStream;
/*  7:   */ 
/*  8:   */ public class StreamClear
/*  9:   */   extends Thread
/* 10:   */ {
/* 11:   */   InputStream stream;
/* 12:   */   
/* 13:   */   StreamClear(InputStream stream)
/* 14:   */   {
/* 15:11 */     this.stream = stream;
/* 16:   */   }
/* 17:   */   
/* 18:   */   public void run()
/* 19:   */   {
/* 20:16 */     BufferedReader buff = null;
/* 21:   */     try
/* 22:   */     {
/* 23:18 */       InputStreamReader reader = new InputStreamReader(this.stream);
/* 24:19 */       buff = new BufferedReader(reader);
/* 25:20 */       String line = null;
/* 26:21 */       while ((line = buff.readLine()) != null)
/* 27:   */       {
/* 28:22 */         System.out.println(line);
/* 29:23 */         System.out.flush();
/* 30:   */       }
/* 31:   */     }
/* 32:   */     catch (Exception e)
/* 33:   */     {
/* 34:26 */       e.printStackTrace();
/* 35:   */       try
/* 36:   */       {
/* 37:29 */         if (this.stream != null) {
/* 38:30 */           this.stream.close();
/* 39:   */         }
/* 40:32 */         if (buff != null) {
/* 41:33 */           buff.close();
/* 42:   */         }
/* 43:   */       }
/* 44:   */       catch (Exception e)
/* 45:   */       {
/* 46:36 */         e.printStackTrace();
/* 47:   */       }
/* 48:   */     }
/* 49:   */     finally
/* 50:   */     {
/* 51:   */       try
/* 52:   */       {
/* 53:29 */         if (this.stream != null) {
/* 54:30 */           this.stream.close();
/* 55:   */         }
/* 56:32 */         if (buff != null) {
/* 57:33 */           buff.close();
/* 58:   */         }
/* 59:   */       }
/* 60:   */       catch (Exception e)
/* 61:   */       {
/* 62:36 */         e.printStackTrace();
/* 63:   */       }
/* 64:   */     }
/* 65:   */   }
/* 66:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.util.StreamClear
 * JD-Core Version:    0.7.0.1
 */