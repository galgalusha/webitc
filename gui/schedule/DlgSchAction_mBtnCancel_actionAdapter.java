package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchAction_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSchAction adaptee;
  
  DlgSchAction_mBtnCancel_actionAdapter(DlgSchAction paramDlgSchAction)
  {
    adaptee = paramDlgSchAction;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed(paramActionEvent);
  }
}
