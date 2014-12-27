package cn.com.voltronic.solar.communicate;

import cn.com.voltronic.solar.processor.AbstractProcessor;

public abstract interface ICommunicateDevice
{
  public abstract String getDeviceName();
  
  public abstract void close();
  
  public abstract void setNotifyProcess(AbstractProcessor paramAbstractProcessor);
}


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.ICommunicateDevice
 * JD-Core Version:    0.7.0.1
 */