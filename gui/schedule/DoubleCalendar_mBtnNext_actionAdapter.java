package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DoubleCalendar_mBtnNext_actionAdapter
  implements ActionListener
{
  DoubleCalendar adaptee;
  
  DoubleCalendar_mBtnNext_actionAdapter(DoubleCalendar paramDoubleCalendar)
  {
    adaptee = paramDoubleCalendar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnNext_actionPerformed();
  }
}
