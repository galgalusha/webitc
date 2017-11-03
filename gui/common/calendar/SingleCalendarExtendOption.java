package webitc.gui.common.calendar;

import java.util.Calendar;
import webitc.common.SystemInfo;

public class SingleCalendarExtendOption
{
  public boolean mEditable = false;
  public Calendar mEndDate = null;
  public int mHeaderCheckBitmap = 0;
  public Calendar mStartDate = null;
  
  public SingleCalendarExtendOption() {}
  
  public void createEndDate(int paramInt1, int paramInt2, int paramInt3)
  {
    mEndDate = Calendar.getInstance(SystemInfo.getTimeZone());
    mEndDate.set(paramInt1, paramInt2 - 1, paramInt3);
  }
  
  public void createStartDate(int paramInt1, int paramInt2, int paramInt3)
  {
    mStartDate = Calendar.getInstance(SystemInfo.getTimeZone());
    mStartDate.set(paramInt1, paramInt2 - 1, paramInt3);
  }
}
