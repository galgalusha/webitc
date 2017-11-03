package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchData_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgSchData adaptee;
  
  DlgSchData_mBtnOK_actionAdapter(DlgSchData paramDlgSchData)
  {
    adaptee = paramDlgSchData;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed();
  }
}
