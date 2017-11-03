package webitc.gui.monitor;

import java.awt.Component;
import javax.swing.JTable;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.gui.common.ITCTableCellRenderer;
import webitc.gui.common.IconRes;
import webitc.gui.common.ItcIconInfo;

class StatusRenderer
  extends ITCTableCellRenderer
{
  StatusRenderer() {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    setColor(paramBoolean1);
    ItcIconInfo localItcIconInfo = DataMgr.getInstance().getIconInfo((ID)paramObject);
    if (fIconMode == 3) {
      setIcon(IconRes.getIcon(5));
    } else if (fIconMode == 2) {
      setIcon(IconRes.getIcon(4));
    } else if (fIconMode == 1) {
      setIcon(IconRes.getIcon(3));
    } else if (fIconMode == 0) {
      setIcon(IconRes.getIcon(2));
    }
    StringBuffer localStringBuffer = new StringBuffer();
    if ((fIconAppend & 0x1) != 0) {
      localStringBuffer.append("S");
    }
    if ((fIconAppend & 0x2) != 0) {
      localStringBuffer.append("P");
    }
    if ((fIconAppend & 0x4) != 0) {
      localStringBuffer.append("A");
    }
    if ((fIconAppend & 0x8) != 0) {
      localStringBuffer.append("F");
    }
    setText(localStringBuffer.toString());
    return this;
  }
}
