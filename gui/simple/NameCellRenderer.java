package webitc.gui.simple;

import java.awt.Component;
import javax.swing.JTable;
import webitc.data.DataMgr;
import webitc.data.ID;

class NameCellRenderer
  extends SimpleCellRendererAbst
{
  NameCellRenderer() {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    setColor(paramBoolean1);
    if (paramObject != null)
    {
      ID localID = (ID)paramObject;
      DataMgr localDataMgr = DataMgr.getInstance();
      setText(localDataMgr.getShortName(localID));
    }
    return this;
  }
}
