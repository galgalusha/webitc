package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgModifyContractPwr_mBtnMonth4_actionAdapter
  implements ActionListener
{
  DlgModifyContractPwr adaptee;
  
  DlgModifyContractPwr_mBtnMonth4_actionAdapter(DlgModifyContractPwr paramDlgModifyContractPwr)
  {
    adaptee = paramDlgModifyContractPwr;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnMonth4_actionPerformed(paramActionEvent);
  }
}
