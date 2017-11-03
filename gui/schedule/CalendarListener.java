package webitc.gui.schedule;

import java.util.Calendar;
import java.util.EventListener;

public abstract interface CalendarListener
  extends EventListener
{
  public abstract void dateSelected(Calendar paramCalendar);
}
