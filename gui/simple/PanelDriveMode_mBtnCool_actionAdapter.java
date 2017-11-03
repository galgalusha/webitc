package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelDriveMode_mBtnCool_actionAdapter
  implements ActionListener
{
  PanelDriveMode adaptee;
  
  PanelDriveMode_mBtnCool_actionAdapter(PanelDriveMode paramPanelDriveMode)
  {
    adaptee = paramPanelDriveMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCool_actionPerformed(paramActionEvent);
  }
}
