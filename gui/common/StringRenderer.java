package webitc.gui.common;

import java.awt.Component;
import javax.swing.JTable;

public class StringRenderer
  extends ITCTableCellRenderer
{
  public StringRenderer() {}
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    setColor(paramBoolean1);
    setText((String)paramObject);
    return this;
  }
}
