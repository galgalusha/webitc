package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVentProperties_mBtnUp_actionAdapter
  implements ActionListener
{
  PanelVentProperties adaptee;
  
  PanelVentProperties_mBtnUp_actionAdapter(PanelVentProperties paramPanelVentProperties)
  {
    adaptee = paramPanelVentProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnUp_actionPerformed(paramActionEvent);
  }
}
