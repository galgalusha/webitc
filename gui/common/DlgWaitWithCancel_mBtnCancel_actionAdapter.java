package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgWaitWithCancel_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgWaitWithCancel adaptee;
  
  DlgWaitWithCancel_mBtnCancel_actionAdapter(DlgWaitWithCancel paramDlgWaitWithCancel)
  {
    adaptee = paramDlgWaitWithCancel;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed(paramActionEvent);
  }
}
