package webitc.gui.common;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class ScrollTableColumn
  extends TableColumn
{
  private boolean bSetWidth = false;
  private changeWidthListener mListener = null;
  private int mRecordedWidth = -1;
  
  public ScrollTableColumn(int paramInt1, int paramInt2, TableCellRenderer paramTableCellRenderer, TableCellEditor paramTableCellEditor)
  {
    super(paramInt1, paramInt2, paramTableCellRenderer, paramTableCellEditor);
  }
  
  public ScrollTableColumn(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public ScrollTableColumn(int paramInt)
  {
    super(paramInt);
  }
  
  public ScrollTableColumn() {}
  
  public void setListener(changeWidthListener paramChangeWidthListener)
  {
    mListener = paramChangeWidthListener;
  }
  
  public void setPreferredWidth(int paramInt)
  {
    if ((bSetWidth == true) || (mRecordedWidth == -1))
    {
      super.setPreferredWidth(paramInt);
      if (mListener != null)
      {
        int i = getModelIndex();
        mListener.notifyChangeWidth(i, paramInt);
      }
    }
  }
  
  public void setRecordedWidth(int paramInt)
  {
    mRecordedWidth = paramInt;
    super.setPreferredWidth(paramInt);
  }
  
  public void setWidth(int paramInt)
  {
    bSetWidth = true;
    super.setWidth(paramInt);
    bSetWidth = false;
  }
  
  public static abstract interface changeWidthListener
  {
    public abstract void notifyChangeWidth(int paramInt1, int paramInt2);
  }
}
