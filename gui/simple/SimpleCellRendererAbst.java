package webitc.gui.simple;

import javax.swing.table.DefaultTableCellRenderer;
import webitc.gui.common.ColorRes;

class SimpleCellRendererAbst
  extends DefaultTableCellRenderer
{
  public SimpleCellRendererAbst()
  {
    setHorizontalAlignment(2);
  }
  
  protected void setColor(boolean paramBoolean)
  {
    if (paramBoolean == true)
    {
      super.setForeground(ColorRes.SIMPLE_MODE_LIST_SELECT_F);
      super.setBackground(ColorRes.SIMPLE_MODE_LIST_SELECT_B);
    }
    else
    {
      super.setForeground(ColorRes.SIMPLE_MODE_LIST_NO_SELECT_F);
      super.setBackground(ColorRes.SIMPLE_MODE_LIST_NO_SELECT_B);
    }
  }
}
