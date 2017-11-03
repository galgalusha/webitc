package webitc.gui;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TableModelIcon
  extends DefaultTableModel
{
  private int mColumn = 0;
  private int mRow = 0;
  public int mTotalColumn;
  
  public TableModelIcon(int paramInt)
  {
    mTotalColumn = paramInt;
    setColumnCount(paramInt);
  }
  
  public void addData(Object paramObject)
  {
    if (mColumn == 0) {
      addRow((Vector)null);
    }
    setValueAt(paramObject, mRow, mColumn);
    mColumn += 1;
    if (mColumn >= mTotalColumn)
    {
      mRow += 1;
      mColumn = 0;
    }
  }
  
  public void removeAllData()
  {
    getDataVector().clear();
    mColumn = (this.mRow = 0);
  }
}
