package webitc.gui.common.calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SingleCalendar_mBtnPrev_actionAdapter
  implements ActionListener
{
  SingleCalendar adaptee;
  
  SingleCalendar_mBtnPrev_actionAdapter(SingleCalendar paramSingleCalendar)
  {
    adaptee = paramSingleCalendar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnPrev_actionPerformed(paramActionEvent);
  }
}
