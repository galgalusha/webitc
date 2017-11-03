package webitc.data.schedule;

import webitc.data.CommonCalendar;

public class Program
{
  private boolean mActive = false;
  private CommonCalendar mCalendar;
  private SchException mException;
  private Execute mExecute;
  private String mName;
  private AbstSchedule mWeekly;
  
  public Program() {}
  
  public CommonCalendar getCalendar()
  {
    return mCalendar;
  }
  
  public SchException getException()
  {
    return mException;
  }
  
  public Execute getExecute()
  {
    return mExecute;
  }
  
  public String getName()
  {
    return mName;
  }
  
  public AbstSchedule getWeekly()
  {
    return mWeekly;
  }
  
  public boolean isActive()
  {
    return mActive;
  }
  
  public void setActive(boolean paramBoolean)
  {
    mActive = paramBoolean;
  }
  
  public void setCalendar(CommonCalendar paramCommonCalendar)
  {
    mCalendar = paramCommonCalendar;
  }
  
  public void setException(SchException paramSchException)
  {
    mException = paramSchException;
  }
  
  public void setExecute(Execute paramExecute)
  {
    mExecute = paramExecute;
  }
  
  public void setName(String paramString)
  {
    mName = paramString;
  }
  
  public void setWeekly(AbstSchedule paramAbstSchedule)
  {
    mWeekly = paramAbstSchedule;
  }
}
