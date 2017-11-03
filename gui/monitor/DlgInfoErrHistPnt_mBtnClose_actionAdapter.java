package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgInfoErrHistPnt_mBtnClose_actionAdapter
  implements ActionListener
{
  DlgInfoErrHistPnt adaptee;
  
  DlgInfoErrHistPnt_mBtnClose_actionAdapter(DlgInfoErrHistPnt paramDlgInfoErrHistPnt)
  {
    adaptee = paramDlgInfoErrHistPnt;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnClose_actionPerformed();
  }
}
