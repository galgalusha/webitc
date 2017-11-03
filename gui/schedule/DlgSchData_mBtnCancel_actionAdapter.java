package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchData_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSchData adaptee;
  
  DlgSchData_mBtnCancel_actionAdapter(DlgSchData paramDlgSchData)
  {
    adaptee = paramDlgSchData;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed();
  }
}
