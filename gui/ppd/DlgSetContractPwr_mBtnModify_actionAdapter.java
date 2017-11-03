package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetContractPwr_mBtnModify_actionAdapter
  implements ActionListener
{
  DlgSetContractPwr adaptee;
  
  DlgSetContractPwr_mBtnModify_actionAdapter(DlgSetContractPwr paramDlgSetContractPwr)
  {
    adaptee = paramDlgSetContractPwr;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnModify_actionPerformed(paramActionEvent);
  }
}
