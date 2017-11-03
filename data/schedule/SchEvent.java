package webitc.data.schedule;

public class SchEvent
  implements Cloneable, Comparable
{
  private SchAbstAction mAction;
  public int mHour = this.mMin = 0;
  public int mMin;
  
  public SchEvent() {}
  
  public Object clone()
  {
    SchEvent localSchEvent = new SchEvent();
    localSchEvent.setTime(getHour(), getMin());
    localSchEvent.setAction((SchAbstAction)getAction().clone());
    return localSchEvent;
  }
  
  public int compareTo(Object paramObject)
  {
    if ((paramObject == null) || (getClass() != paramObject.getClass())) {
      throw new ClassCastException("SchEvent.compareTo");
    }
    SchEvent localSchEvent = (SchEvent)paramObject;
    if (mHour * 60 + mMin < mHour * 60 + mMin) {
      return -1;
    }
    if (mHour * 60 + mMin > mHour * 60 + mMin) {
      return 1;
    }
    return 0;
  }
  
  public SchAbstAction getAction()
  {
    return mAction;
  }
  
  public int getHour()
  {
    return mHour;
  }
  
  public int getMin()
  {
    return mMin;
  }
  
  public void setAction(SchAbstAction paramSchAbstAction)
  {
    mAction = paramSchAbstAction;
  }
  
  public void setHour(int paramInt)
  {
    mHour = paramInt;
  }
  
  public void setMin(int paramInt)
  {
    mMin = paramInt;
  }
  
  public void setTime(int paramInt1, int paramInt2)
  {
    mHour = paramInt1;
    mMin = paramInt2;
  }
}
