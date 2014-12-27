package cn.com.voltronic.solar.protocol;

import cn.com.voltronic.solar.data.bean.AutoMenuList;
import cn.com.voltronic.solar.data.bean.BaseInfo;
import cn.com.voltronic.solar.data.bean.ComboBoxParameter;
import cn.com.voltronic.solar.data.bean.HistoryDataChartColumns;
import cn.com.voltronic.solar.data.bean.HistoryDataColumns;
import cn.com.voltronic.solar.data.bean.HistoryFaultDataColumns;
import cn.com.voltronic.solar.data.bean.MoreInfo;
import cn.com.voltronic.solar.data.bean.ProductInfo;
import cn.com.voltronic.solar.data.bean.PurchaseInfo;
import cn.com.voltronic.solar.data.bean.RadioParameter;
import cn.com.voltronic.solar.data.bean.RatingInfo;
import cn.com.voltronic.solar.data.bean.RestoreInfo;
import cn.com.voltronic.solar.data.bean.SpinnerParameter;

public abstract interface IProtocol
{
  public abstract String getProtocolID();
  
  public abstract String getSerialNo();
  
  public abstract void setOutputMode(int paramInt);
  
  public abstract int getOutputMode();
  
  public abstract boolean matchProtocol(Object paramObject);
  
  public abstract ProductInfo getProductInfo();
  
  public abstract RatingInfo getRatingInfo();
  
  public abstract PurchaseInfo getPurchaseInfo();
  
  public abstract BaseInfo getBaseInfo();
  
  public abstract MoreInfo getMoreInfo();
  
  public abstract RestoreInfo getResotreInfo();
  
  public abstract RadioParameter getRadioParameter();
  
  public abstract SpinnerParameter getSpinnerParameter();
  
  public abstract ComboBoxParameter getComboBoxParameter();
  
  public abstract HistoryFaultDataColumns getHistoryFaultColumns();
  
  public abstract HistoryDataColumns getHistoryColumns();
  
  public abstract HistoryDataChartColumns getHistoryChartColumns();
  
  public abstract AutoMenuList getMenuList();
  
  public abstract boolean getDelayChanging();
  
  public abstract void setDelayChanging(boolean paramBoolean);
}


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.protocol.IProtocol
 * JD-Core Version:    0.7.0.1
 */