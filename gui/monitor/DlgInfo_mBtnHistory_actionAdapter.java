package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgInfo_mBtnHistory_actionAdapter
  implements ActionListener
{
  DlgInfo adaptee;
  
  DlgInfo_mBtnHistory_actionAdapter(DlgInfo paramDlgInfo)
  {
    adaptee = paramDlgInfo;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnHistory_actionPerformed();
  }
}
