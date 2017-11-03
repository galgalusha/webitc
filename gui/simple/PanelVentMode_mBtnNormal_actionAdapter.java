package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVentMode_mBtnNormal_actionAdapter
  implements ActionListener
{
  PanelVentMode adaptee;
  
  PanelVentMode_mBtnNormal_actionAdapter(PanelVentMode paramPanelVentMode)
  {
    adaptee = paramPanelVentMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnNormal_actionPerformed(paramActionEvent);
  }
}
