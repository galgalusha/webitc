package webitc.gui.common;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class ITCListCellRenderer
  extends DefaultListCellRenderer
{
  public ITCListCellRenderer()
  {
    setHorizontalAlignment(2);
  }
  
  public Component getListCellRendererComponent(JList paramJList, Object paramObject, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setEnabled(paramJList.isEnabled());
    setColor(paramBoolean1);
    if (paramObject.toString().length() != 0) {
      setText(paramObject.toString());
    } else {
      setText(" ");
    }
    return this;
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
