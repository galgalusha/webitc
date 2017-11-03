package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVentProperties_mBtnDown_actionAdapter
  implements ActionListener
{
  PanelVentProperties adaptee;
  
  PanelVentProperties_mBtnDown_actionAdapter(PanelVentProperties paramPanelVentProperties)
  {
    adaptee = paramPanelVentProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnDown_actionPerformed(paramActionEvent);
  }
}
