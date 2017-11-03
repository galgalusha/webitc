package webitc.gui.schedule;

import java.util.EventListener;

public abstract interface CalendarSelectListener
  extends EventListener
{
  public abstract void typeSelected(int paramInt);
}
