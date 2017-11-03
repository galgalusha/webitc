package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgModifyContractPwr_mBtnMonth12_actionAdapter
  implements ActionListener
{
  DlgModifyContractPwr adaptee;
  
  DlgModifyContractPwr_mBtnMonth12_actionAdapter(DlgModifyContractPwr paramDlgModifyContractPwr)
  {
    adaptee = paramDlgModifyContractPwr;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnMonth12_actionPerformed(paramActionEvent);
  }
}
