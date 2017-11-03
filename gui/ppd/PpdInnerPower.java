package webitc.gui.ppd;

import java.util.Calendar;
import webitc.common.StrRes;
import webitc.common.SystemInfo;

public class PpdInnerPower
{
  public static final PpdInnerPower InvalidPower = new PpdInnerPower(false, 0, '\000', '\000', '\000', 0, 0, 0, 0);
  private final char mDay;
  private final int mDayIdle;
  private final int mDayPower;
  private final boolean mEnable;
  private final char mHour;
  private final char mMonth;
  private final int mNightIdle;
  private final int mNightPower;
  private final int mYear;
  
  public PpdInnerPower(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this(true, 0, '\000', '\000', '\000', paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public PpdInnerPower(boolean paramBoolean, int paramInt1, char paramChar1, char paramChar2, char paramChar3, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mEnable = paramBoolean;
    mYear = paramInt1;
    mMonth = paramChar1;
    mDay = paramChar2;
    mHour = paramChar3;
    mDayPower = paramInt2;
    mNightPower = paramInt3;
    mDayIdle = paramInt4;
    mNightIdle = paramInt5;
  }
  
  public int getDayIdle()
  {
    return mDayIdle;
  }
  
  public int getDayPower()
  {
    return mDayPower;
  }
  
  public int getGas()
  {
    return mNightPower;
  }
  
  public int getNightIdle()
  {
    return mNightIdle;
  }
  
  public int getNightPower()
  {
    return mNightPower;
  }
  
  public Calendar getTime()
  {
    Calendar localCalendar = Calendar.getInstance(SystemInfo.getTimeZone());
    localCalendar.set(mYear, mMonth - '\001', mDay, mHour, 0, 0);
    return localCalendar;
  }
  
  public String getTimeStr()
  {
    return StrRes.getYMDHMStr(getTime(), true);
  }
  
  public boolean isEnable()
  {
    return mEnable;
  }
}
