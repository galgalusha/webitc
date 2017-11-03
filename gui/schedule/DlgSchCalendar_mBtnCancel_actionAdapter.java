package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchCalendar_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSchCalendar adaptee;
  
  DlgSchCalendar_mBtnCancel_actionAdapter(DlgSchCalendar paramDlgSchCalendar)
  {
    adaptee = paramDlgSchCalendar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed();
  }
}
