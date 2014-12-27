package cn.com.voltronic.solar.dao;

import cn.com.voltronic.solar.beanbag.BeanBag;

public abstract interface IDao
{
  public abstract void setBeanBag(BeanBag paramBeanBag);
  
  public abstract boolean writeRecords();
}


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.dao.IDao
 * JD-Core Version:    0.7.0.1
 */