package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelDisplayType_mBtnExpansion_actionAdapter
  implements ActionListener
{
  PanelDisplayType adaptee;
  
  PanelDisplayType_mBtnExpansion_actionAdapter(PanelDisplayType paramPanelDisplayType)
  {
    adaptee = paramPanelDisplayType;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnExpansion_actionPerformed(paramActionEvent);
  }
}
