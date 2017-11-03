package webitc.gui.simple;

import java.awt.Component;
import javax.swing.JTable;
import webitc.data.ID;
import webitc.gui.common.IconRes;

class SetTempCellRenderer
  extends SimpleCellRendererAbst
{
  SetTempCellRenderer() {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    setColor(paramBoolean1);
    if (paramObject != null)
    {
      setIcon(IconRes.getIcon(99));
      ID localID = (ID)paramObject;
      String str = SimpleCommon.getSetTempStr(localID);
      setText(str);
    }
    return this;
  }
}
