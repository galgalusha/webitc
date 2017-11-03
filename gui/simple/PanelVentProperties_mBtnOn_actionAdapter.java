package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVentProperties_mBtnOn_actionAdapter
  implements ActionListener
{
  PanelVentProperties adaptee;
  
  PanelVentProperties_mBtnOn_actionAdapter(PanelVentProperties paramPanelVentProperties)
  {
    adaptee = paramPanelVentProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOn_actionPerformed(paramActionEvent);
  }
}
