package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelDriveMode_mBtnSubmit_actionAdapter
  implements ActionListener
{
  PanelDriveMode adaptee;
  
  PanelDriveMode_mBtnSubmit_actionAdapter(PanelDriveMode paramPanelDriveMode)
  {
    adaptee = paramPanelDriveMode;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnSubmit_actionPerformed(paramActionEvent);
  }
}
