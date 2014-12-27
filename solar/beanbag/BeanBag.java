/*  1:   */ package cn.com.voltronic.solar.beanbag;
/*  2:   */ 
/*  3:   */ import cn.com.voltronic.solar.data.bean.Capability;
/*  4:   */ import cn.com.voltronic.solar.data.bean.ConfigData;
/*  5:   */ import cn.com.voltronic.solar.data.bean.DefaultData;
/*  6:   */ import cn.com.voltronic.solar.data.bean.EnergyData;
/*  7:   */ import cn.com.voltronic.solar.data.bean.MachineInfo;
/*  8:   */ import cn.com.voltronic.solar.data.bean.ProtocolInfo;
/*  9:   */ import cn.com.voltronic.solar.data.bean.SelfTestResult;
/* 10:   */ import cn.com.voltronic.solar.data.bean.WorkInfo;
/* 11:   */ import java.util.HashMap;
/* 12:   */ 
/* 13:   */ public abstract class BeanBag
/* 14:   */ {
/* 15:17 */   private HashMap<String, Object> _beans = null;
/* 16:   */   
/* 17:   */   public BeanBag()
/* 18:   */   {
/* 19:21 */     this._beans = new HashMap();
/* 20:22 */     putBaseBean();
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void addBean(String name, Object bean)
/* 24:   */   {
/* 25:28 */     name = name.toUpperCase().trim();
/* 26:29 */     this._beans.put(name, bean);
/* 27:   */   }
/* 28:   */   
/* 29:   */   public void setBean(String name, Object bean)
/* 30:   */   {
/* 31:32 */     name = name.toUpperCase().trim();
/* 32:33 */     this._beans.put(name, bean);
/* 33:   */   }
/* 34:   */   
/* 35:   */   public Object getBean(String name)
/* 36:   */   {
/* 37:37 */     name = name.toUpperCase().trim();
/* 38:38 */     if (this._beans.containsKey(name)) {
/* 39:39 */       return this._beans.get(name);
/* 40:   */     }
/* 41:41 */     return null;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void putBaseBean()
/* 45:   */   {
/* 46:45 */     addBean("protocolinfo", new ProtocolInfo());
/* 47:46 */     addBean("workinfo", new WorkInfo());
/* 48:47 */     addBean("capability", new Capability());
/* 49:48 */     addBean("machineinfo", new MachineInfo());
/* 50:49 */     addBean("energydata", new EnergyData());
/* 51:50 */     addBean("configdata", new ConfigData());
/* 52:51 */     addBean("defaultdata", new DefaultData());
/* 53:52 */     addBean("selftestresult", new SelfTestResult());
/* 54:   */   }
/* 55:   */   
/* 56:   */   public HashMap<String, Object> getBeansMap()
/* 57:   */   {
/* 58:56 */     return this._beans;
/* 59:   */   }
/* 60:   */ }


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.beanbag.BeanBag
 * JD-Core Version:    0.7.0.1
 */