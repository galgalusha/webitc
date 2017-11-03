package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelDriveMode_mBtnHeat_actionAdapter
  implements ActionListener
{
  PanelDriveMode adaptee;
  
  PanelDriveMode_mBtnHeat_actionAdapter(PanelDriveMode paramPanelDriveMode)
  {
    adaptee = paramPanelDriveMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnHeat_actionPerformed(paramActionEvent);
  }
}
