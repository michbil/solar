/*  1:   */ package cn.com.voltronic.solar.modbusprocessor;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.communicate.ModbusHandler;
/*  4:   */ import cn.com.voltronic.solar.processor.AbstractProcessor;
/*  5:   */ import cn.com.voltronic.solar.protocol.IProtocol;
/*  6:   */ import net.wimpi.modbus.procimg.Register;
/*  7:   */ 
/*  8:   */ public abstract class AbstractModbusProcessor
/*  9:   */   extends AbstractProcessor
/* 10:   */ {
/* 11:   */   public AbstractModbusProcessor(ModbusHandler handler, IProtocol protocol)
/* 12:   */   {
/* 13:11 */     super(handler, protocol);
/* 14:   */   }
/* 15:   */   
/* 16:   */   public ModbusHandler getHandler()
/* 17:   */   {
/* 18:16 */     return (ModbusHandler)super.getHandler();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static long convert3Regiters(Register sign, Register first, Register second)
/* 22:   */   {
/* 23:21 */     int factor = 1;
/* 24:22 */     if (sign.getValue() > 0) {
/* 25:23 */       factor = -1;
/* 26:   */     }
/* 27:25 */     long high = first.getValue();
/* 28:26 */     long result = high << 16;
/* 29:27 */     result += second.getValue();
/* 30:28 */     return factor * result;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public static long convert2Regiters(Register first, Register second)
/* 34:   */   {
/* 35:33 */     int factor = 1;
/* 36:34 */     long high = first.getValue();
/* 37:35 */     long result = high << 16;
/* 38:36 */     result += second.getValue();
/* 39:37 */     return factor * result;
/* 40:   */   }
/* 41:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.modbusprocessor.AbstractModbusProcessor
 * JD-Core Version:    0.7.0.1
 */