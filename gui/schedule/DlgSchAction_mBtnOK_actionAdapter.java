package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchAction_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgSchAction adaptee;
  
  DlgSchAction_mBtnOK_actionAdapter(DlgSchAction paramDlgSchAction)
  {
    adaptee = paramDlgSchAction;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed(paramActionEvent);
  }
}
