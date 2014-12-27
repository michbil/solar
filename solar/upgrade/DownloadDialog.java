package cn.com.voltronic.solar.upgrade;

import cn.com.voltronic.solar.view.component.AALabel;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public abstract class DownloadDialog
  extends JFrame
{
  private static final long serialVersionUID = -5934470461432301042L;
  protected AALabel note;
  protected JProgressBar progress;
  
  public abstract void initComponents();
}


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.upgrade.DownloadDialog
 * JD-Core Version:    0.7.0.1
 */