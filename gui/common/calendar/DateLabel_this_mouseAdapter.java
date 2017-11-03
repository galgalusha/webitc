package webitc.gui.common.calendar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DateLabel_this_mouseAdapter
  extends MouseAdapter
{
  DateLabel adaptee;
  
  DateLabel_this_mouseAdapter(DateLabel paramDateLabel)
  {
    adaptee = paramDateLabel;
  }
  
  public void mouseClicked(MouseEvent paramMouseEvent)
  {
    adaptee.this_mouseClicked(paramMouseEvent);
  }
}
