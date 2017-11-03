package webitc.gui.common.calendar;

import java.util.EventListener;

public abstract interface MonthCalendarListener
  extends EventListener
{
  public abstract void dateSelected(MonthCalendar paramMonthCalendar);
}
