package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelDriveMode_mBtnAuto_actionAdapter
  implements ActionListener
{
  PanelDriveMode adaptee;
  
  PanelDriveMode_mBtnAuto_actionAdapter(PanelDriveMode paramPanelDriveMode)
  {
    adaptee = paramPanelDriveMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnAuto_actionPerformed(paramActionEvent);
  }
}
