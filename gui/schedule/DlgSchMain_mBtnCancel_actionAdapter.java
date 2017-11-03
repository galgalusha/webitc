package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchMain_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSchMain adaptee;
  
  DlgSchMain_mBtnCancel_actionAdapter(DlgSchMain paramDlgSchMain)
  {
    adaptee = paramDlgSchMain;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed(paramActionEvent);
  }
}
