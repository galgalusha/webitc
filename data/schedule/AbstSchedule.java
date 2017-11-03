package webitc.data.schedule;

import java.util.ArrayList;

public class AbstSchedule
  implements Cloneable
{
  private ArrayList mDailyArray = new ArrayList();
  
  public AbstSchedule(long paramLong)
  {
    for (int i = 0; i < paramLong; i++) {
      mDailyArray.add(new Daily());
    }
  }
  
  public Object clone()
  {
    int i = getDailyNum();
    AbstSchedule localAbstSchedule = new AbstSchedule(i);
    for (int j = 0; j < i; j++)
    {
      Daily localDaily = (Daily)getDaily(j).clone();
      localAbstSchedule.setDaily(j, localDaily);
    }
    return localAbstSchedule;
  }
  
  public Daily getDaily(int paramInt)
  {
    return (Daily)mDailyArray.get(paramInt);
  }
  
  public ArrayList getDailyArray()
  {
    return mDailyArray;
  }
  
  public int getDailyNum()
  {
    return mDailyArray.size();
  }
  
  public boolean setDaily(int paramInt, Daily paramDaily)
  {
    if (paramInt >= mDailyArray.size()) {
      return false;
    }
    mDailyArray.set(paramInt, paramDaily);
    return true;
  }
  
  public void setDailyArray(ArrayList paramArrayList)
  {
    mDailyArray = paramArrayList;
  }
}
