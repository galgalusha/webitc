package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelDriveMode_mBtnFan_actionAdapter
  implements ActionListener
{
  PanelDriveMode adaptee;
  
  PanelDriveMode_mBtnFan_actionAdapter(PanelDriveMode paramPanelDriveMode)
  {
    adaptee = paramPanelDriveMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnFan_actionPerformed(paramActionEvent);
  }
}
