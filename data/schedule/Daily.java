package webitc.data.schedule;

import java.util.ArrayList;

public class Daily
  implements Cloneable
{
  ArrayList mEventArray = new ArrayList();
  
  public Daily() {}
  
  public boolean addEvent(SchEvent paramSchEvent)
  {
    if (getEventCount() >= 16) {
      return false;
    }
    mEventArray.add(paramSchEvent);
    return true;
  }
  
  public Object clone()
  {
    Daily localDaily = new Daily();
    for (int i = 0; i < getEventCount(); i++)
    {
      SchEvent localSchEvent = getEvent(i);
      localDaily.addEvent((SchEvent)localSchEvent.clone());
    }
    return localDaily;
  }
  
  public SchEvent getEvent(int paramInt)
  {
    return (SchEvent)mEventArray.get(paramInt);
  }
  
  public ArrayList getEventArray()
  {
    return mEventArray;
  }
  
  public int getEventCount()
  {
    return mEventArray.size();
  }
  
  public void removeAllEvent()
  {
    mEventArray.clear();
  }
  
  public boolean removeEvent(int paramInt)
  {
    if (getEventCount() >= paramInt) {
      return false;
    }
    mEventArray.remove(paramInt);
    return true;
  }
  
  public void setEventArray(ArrayList paramArrayList)
  {
    mEventArray = paramArrayList;
  }
}
