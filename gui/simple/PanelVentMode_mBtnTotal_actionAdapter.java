package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVentMode_mBtnTotal_actionAdapter
  implements ActionListener
{
  PanelVentMode adaptee;
  
  PanelVentMode_mBtnTotal_actionAdapter(PanelVentMode paramPanelVentMode)
  {
    adaptee = paramPanelVentMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnTotal_actionPerformed(paramActionEvent);
  }
}
