/*  1:   */ package cn.com.voltronic.solar.system;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  4:   */ import java.util.ArrayList;
/*  5:   */ import java.util.List;
/*  6:   */ 
/*  7:   */ public class ProcessorCategories
/*  8:   */ {
/*  9:65 */   protected static List<ProtocolProcessorItem> _protocolList = new ArrayList();
/* 10:   */   
/* 11:   */   public static void registerProcessor(Class protocol, Class handler, Class abstractProcess)
/* 12:   */   {
/* 13:70 */     _protocolList.add(new ProtocolProcessorItem(protocol, handler, 
/* 14:71 */       abstractProcess));
/* 15:   */   }
/* 16:   */   
/* 17:   */   public static AbstractProcessor getNewMonitor(Object handler)
/* 18:   */   {
/* 19:75 */     AbstractProcessor process = null;
/* 20:76 */     for (ProtocolProcessorItem item : _protocolList)
/* 21:   */     {
/* 22:77 */       process = item.getMatchProcess(handler);
/* 23:78 */       if (process != null) {
/* 24:   */         break;
/* 25:   */       }
/* 26:   */     }
/* 27:82 */     return process;
/* 28:   */   }
/* 29:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.system.ProcessorCategories
 * JD-Core Version:    0.7.0.1
 */