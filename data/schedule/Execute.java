package webitc.data.schedule;

import java.util.GregorianCalendar;

public class Execute
  extends AbstSchedule
{
  private GregorianCalendar mDate;
  private int mTodayPos;
  
  public Execute(long paramLong)
  {
    super(paramLong);
  }
  
  public GregorianCalendar getDate()
  {
    return mDate;
  }
  
  public int getTodayPos()
  {
    return mTodayPos;
  }
  
  public void setDate(GregorianCalendar paramGregorianCalendar)
  {
    mDate = paramGregorianCalendar;
  }
  
  public void setDate(int paramInt1, int paramInt2, int paramInt3)
  {
    mDate = new GregorianCalendar(paramInt1, paramInt2 - 1, paramInt3);
  }
  
  public void setTodayPos(int paramInt)
  {
    mTodayPos = paramInt;
  }
}
