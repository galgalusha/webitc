package webitc.gui.monitor;

import java.awt.Component;
import javax.swing.JTable;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.gui.common.ITCTableCellRenderer;

class DrvModeRenderer
  extends ITCTableCellRenderer
{
  DrvModeRenderer() {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    setColor(paramBoolean1);
    String str = DataMgr.getInstance().getDrvVentModeStr((ID)paramObject);
    setText(str);
    return this;
  }
}
