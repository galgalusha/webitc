package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchMain_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgSchMain adaptee;
  
  DlgSchMain_mBtnOK_actionAdapter(DlgSchMain paramDlgSchMain)
  {
    adaptee = paramDlgSchMain;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed(paramActionEvent);
  }
}
