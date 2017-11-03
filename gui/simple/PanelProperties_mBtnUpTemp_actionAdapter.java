package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelProperties_mBtnUpTemp_actionAdapter
  implements ActionListener
{
  PanelProperties adaptee;
  
  PanelProperties_mBtnUpTemp_actionAdapter(PanelProperties paramPanelProperties)
  {
    adaptee = paramPanelProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnUpTemp_actionPerformed(paramActionEvent);
  }
}
