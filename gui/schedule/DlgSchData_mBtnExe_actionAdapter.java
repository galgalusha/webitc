package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchData_mBtnExe_actionAdapter
  implements ActionListener
{
  DlgSchData adaptee;
  
  DlgSchData_mBtnExe_actionAdapter(DlgSchData paramDlgSchData)
  {
    adaptee = paramDlgSchData;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnExe_actionPerformed();
  }
}
