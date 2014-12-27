package cn.com.voltronic.solar.communicate;

public abstract interface IComUSBHandler
{
  public abstract int getMpptTrackNumber();
  
  public abstract String getSerialNo();
  
  public abstract String getModeType();
  
  public abstract boolean isSupportQCTH();
  
  public abstract boolean isSupportQPPS();
  
  public abstract String getDeviceModel();
  
  public abstract String getDeviceName();
  
  public abstract void close();
  
  public abstract String excuteSimpleCommand(String paramString);
  
  public abstract String excuteCommand(String paramString, boolean paramBoolean);
}


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.communicate.IComUSBHandler
 * JD-Core Version:    0.7.0.1
 */