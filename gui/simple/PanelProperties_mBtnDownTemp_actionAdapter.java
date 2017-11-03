package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelProperties_mBtnDownTemp_actionAdapter
  implements ActionListener
{
  PanelProperties adaptee;
  
  PanelProperties_mBtnDownTemp_actionAdapter(PanelProperties paramPanelProperties)
  {
    adaptee = paramPanelProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnDownTemp_actionPerformed(paramActionEvent);
  }
}
