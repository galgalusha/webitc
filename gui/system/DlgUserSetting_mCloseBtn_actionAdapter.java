package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgUserSetting_mCloseBtn_actionAdapter
  implements ActionListener
{
  DlgUserSetting adaptee;
  
  DlgUserSetting_mCloseBtn_actionAdapter(DlgUserSetting paramDlgUserSetting)
  {
    adaptee = paramDlgUserSetting;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mCloseBtn_actionPerformed();
  }
}
