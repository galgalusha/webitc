package webitc.gui.common;

import javax.swing.table.DefaultTableCellRenderer;

public class ITCTableCellRenderer
  extends DefaultTableCellRenderer
{
  public ITCTableCellRenderer()
  {
    setHorizontalAlignment(2);
  }
  
  protected void setColor(boolean paramBoolean)
  {
    if (paramBoolean == true)
    {
      super.setForeground(ColorRes.C_SELECTED_F);
      super.setBackground(ColorRes.C_SELECTED_B);
    }
    else
    {
      super.setForeground(ColorRes.C_NO_SELECTED_F);
      super.setBackground(ColorRes.C_NO_SELECTED_B);
    }
  }
}
