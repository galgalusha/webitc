package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgInfoErrHistory_mBtnClose_actionAdapter
  implements ActionListener
{
  DlgVersionInfo adaptee;
  
  DlgInfoErrHistory_mBtnClose_actionAdapter(DlgVersionInfo paramDlgVersionInfo)
  {
    adaptee = paramDlgVersionInfo;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnClose_actionPerformed();
  }
}
