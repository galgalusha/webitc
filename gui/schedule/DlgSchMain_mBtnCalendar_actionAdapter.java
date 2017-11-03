package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchMain_mBtnCalendar_actionAdapter
  implements ActionListener
{
  DlgSchMain adaptee;
  
  DlgSchMain_mBtnCalendar_actionAdapter(DlgSchMain paramDlgSchMain)
  {
    adaptee = paramDlgSchMain;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCalendar_actionPerformed(paramActionEvent);
  }
}
