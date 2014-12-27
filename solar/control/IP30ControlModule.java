package cn.com.voltronic.solar.control;

public abstract interface IP30ControlModule
{
  public abstract boolean setCapability(String paramString, boolean paramBoolean);
  
  public abstract boolean setPF();
  
  public abstract boolean setMaxChargingCurrent(int paramInt);
  
  public abstract boolean setMaxChargingVoltage(double paramDouble);
  
  public abstract boolean setFloatingVoltage(double paramDouble);
  
  public abstract boolean setOutputFrequency(int paramInt);
  
  public abstract boolean setOutputVoltage(int paramInt);
  
  public abstract boolean setOutputSource(String paramString);
  
  public abstract boolean setChargerSource(String paramString);
  
  public abstract boolean setGridWorkRange(String paramString);
  
  public abstract boolean setBatteryType(String paramString);
  
  public abstract boolean setBatteryLow(double paramDouble);
  
  public abstract boolean setBatteryUnder(double paramDouble);
  
  public abstract boolean setOutputMode(int paramInt1, int paramInt2);
  
  public abstract boolean setRechargingVol(double paramDouble);
  
  public abstract boolean setReDisChargeVoltage(double paramDouble);
  
  public abstract boolean setPMaxChargingCurrent(int paramInt1, int paramInt2);
  
  public abstract boolean setPChargerSource(int paramInt, String paramString);
}


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.control.IP30ControlModule
 * JD-Core Version:    0.7.0.1
 */