package webitc.data.schedule;

import java.util.ArrayList;

public class SchException
  extends AbstSchedule
  implements Cloneable
{
  private ArrayList mNameArray = new ArrayList();
  
  public SchException(long paramLong)
  {
    super(paramLong);
    for (int i = 0; i < paramLong; i++) {
      mNameArray.add(new String());
    }
  }
  
  public Object clone()
  {
    int i = getDailyNum();
    SchException localSchException = new SchException(i);
    for (int j = 0; j < i; j++)
    {
      Daily localDaily = (Daily)getDaily(j).clone();
      localSchException.setDaily(j, localDaily);
      localSchException.setExceptionName(j, getExceptionName(j));
    }
    return localSchException;
  }
  
  public String getExceptionName(int paramInt)
  {
    return (String)mNameArray.get(paramInt);
  }
  
  public boolean setExceptionName(int paramInt, String paramString)
  {
    if (paramInt >= mNameArray.size()) {
      return false;
    }
    mNameArray.set(paramInt, paramString);
    return true;
  }
}
