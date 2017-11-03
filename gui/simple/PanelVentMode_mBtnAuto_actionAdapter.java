package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVentMode_mBtnAuto_actionAdapter
  implements ActionListener
{
  PanelVentMode adaptee;
  
  PanelVentMode_mBtnAuto_actionAdapter(PanelVentMode paramPanelVentMode)
  {
    adaptee = paramPanelVentMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnAuto_actionPerformed(paramActionEvent);
  }
}
