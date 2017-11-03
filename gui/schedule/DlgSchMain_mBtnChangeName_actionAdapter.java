package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchMain_mBtnChangeName_actionAdapter
  implements ActionListener
{
  DlgSchMain adaptee;
  
  DlgSchMain_mBtnChangeName_actionAdapter(DlgSchMain paramDlgSchMain)
  {
    adaptee = paramDlgSchMain;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnChangeName_actionPerformed(paramActionEvent);
  }
}
