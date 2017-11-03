package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchCalendar_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgSchCalendar adaptee;
  
  DlgSchCalendar_mBtnOK_actionAdapter(DlgSchCalendar paramDlgSchCalendar)
  {
    adaptee = paramDlgSchCalendar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed();
  }
}
