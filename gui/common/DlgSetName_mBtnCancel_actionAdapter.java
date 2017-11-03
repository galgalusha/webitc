package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetName_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSetName adaptee;
  
  DlgSetName_mBtnCancel_actionAdapter(DlgSetName paramDlgSetName)
  {
    adaptee = paramDlgSetName;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed(paramActionEvent);
  }
}
