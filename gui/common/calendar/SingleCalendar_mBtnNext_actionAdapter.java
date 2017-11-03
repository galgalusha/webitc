package webitc.gui.common.calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SingleCalendar_mBtnNext_actionAdapter
  implements ActionListener
{
  SingleCalendar adaptee;
  
  SingleCalendar_mBtnNext_actionAdapter(SingleCalendar paramSingleCalendar)
  {
    adaptee = paramSingleCalendar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnNext_actionPerformed(paramActionEvent);
  }
}
