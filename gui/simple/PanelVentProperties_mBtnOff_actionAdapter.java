package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVentProperties_mBtnOff_actionAdapter
  implements ActionListener
{
  PanelVentProperties adaptee;
  
  PanelVentProperties_mBtnOff_actionAdapter(PanelVentProperties paramPanelVentProperties)
  {
    adaptee = paramPanelVentProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOff_actionPerformed(paramActionEvent);
  }
}
