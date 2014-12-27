package cn.com.voltronic.solar.view.component;

import java.awt.Point;

public abstract interface GridBagModel
{
  public static final int DEFAULT = 0;
  public static final int MERGE = 1;
  public static final int COVERED = -1;
  
  public abstract Point getGrid(int paramInt1, int paramInt2);
  
  public abstract int getRowGrid(int paramInt1, int paramInt2);
  
  public abstract int getColumnGrid(int paramInt1, int paramInt2);
  
  public abstract boolean canMergeCells(int[] paramArrayOfInt1, int[] paramArrayOfInt2);
  
  public abstract int getCellState(int paramInt1, int paramInt2);
  
  public abstract boolean mergeCells(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public abstract boolean mergeCells(int[] paramArrayOfInt1, int[] paramArrayOfInt2);
  
  public abstract boolean spliteCellAt(int paramInt1, int paramInt2);
  
  public abstract void clearMergence();
}


/* Location:           F:\tmp\wp\
 * Qualified Name:     cn.com.voltronic.solar.view.component.GridBagModel
 * JD-Core Version:    0.7.0.1
 */