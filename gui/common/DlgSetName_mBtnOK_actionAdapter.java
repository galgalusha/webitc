package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetName_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgSetName adaptee;
  
  DlgSetName_mBtnOK_actionAdapter(DlgSetName paramDlgSetName)
  {
    adaptee = paramDlgSetName;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed(paramActionEvent);
  }
}
