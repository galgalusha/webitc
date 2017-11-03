package webitc.data;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import webitc.common.FatalException;

public class CommonCalendar
  implements Cloneable
{
  public static final int NUM_OF_ONE_MONTH = 32;
  public static final int NUM_OF_ROT_MONTH = 12;
  public static final byte WEEKDAY = 0;
  private ByteBuffer mNowDayType = ByteBuffer.wrap(new byte[32]);
  private ByteBuffer mRotDayType = ByteBuffer.wrap(new byte['ƀ']);
  private boolean mRotation;
  private GregorianCalendar mStartDate = new GregorianCalendar();
  
  public CommonCalendar() {}
  
  public void clear()
  {
    for (int i = 0; i < 32; i++)
    {
      mNowDayType.put(i, (byte)0);
      for (int j = 0; j < 12; j++) {
        mRotDayType.put(j * 32 + i, (byte)0);
      }
    }
  }
  
  public Object clone()
  {
    CommonCalendar localCommonCalendar = new CommonCalendar();
    localCommonCalendar.setStartMonth(getStartMonth().get(1), getStartMonth().get(2) + 1);
    mNowDayType.clear();
    mNowDayType.put(mNowDayType.array());
    mRotDayType.clear();
    mRotDayType.put(mRotDayType.array());
    return localCommonCalendar;
  }
  
  public int getDayType(Calendar paramCalendar)
  {
    Calendar localCalendar = getStartMonth();
    if (paramCalendar.before(localCalendar) == true) {
      throw new FatalException("CommonCalendar.getDayType");
    }
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = paramCalendar.get(2);
    int m = paramCalendar.get(1);
    if ((m == i) && (k == j)) {
      return mNowDayType.get(paramCalendar.get(5) - 1);
    }
    int n = k;
    return mRotDayType.get(n * 32 + paramCalendar.get(5) - 1);
  }
  
  private ArrayList getDayTypeList(Calendar paramCalendar, ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = paramCalendar.getActualMaximum(5);
    ArrayList localArrayList = new ArrayList();
    for (int j = 0; j < i; j++)
    {
      int k = paramByteBuffer.get(paramInt + j);
      localArrayList.add(new DayType(j + 1, k));
    }
    return localArrayList;
  }
  
  public ArrayList getDayTypeList(Calendar paramCalendar)
  {
    Calendar localCalendar = getStartMonth();
    if (paramCalendar.before(localCalendar) == true) {
      throw new FatalException("CommonCalendar.getDayTypeList");
    }
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = paramCalendar.get(2);
    int m = paramCalendar.get(1);
    if ((m == i) && (k == j)) {
      return getDayTypeList(paramCalendar, mNowDayType, 0);
    }
    int n = k;
    return getDayTypeList(paramCalendar, mRotDayType, n * 32);
  }
  
  public Calendar getEndMonth()
  {
    Calendar localCalendar = (Calendar)mStartDate.clone();
    localCalendar.add(2, 12);
    localCalendar.set(5, localCalendar.getActualMaximum(5));
    return localCalendar;
  }
  
  public ByteBuffer getNowDayType()
  {
    mNowDayType.rewind();
    return mNowDayType;
  }
  
  public ByteBuffer getRotDayType()
  {
    mRotDayType.rewind();
    return mRotDayType;
  }
  
  public boolean getRotation()
  {
    return mRotation;
  }
  
  public Calendar getStartMonth()
  {
    return (Calendar)mStartDate.clone();
  }
  
  public void setDayType(int paramInt, Calendar paramCalendar)
  {
    Calendar localCalendar = getStartMonth();
    if (paramCalendar.before(localCalendar) == true) {
      throw new FatalException("CommonCalendar.setDayType");
    }
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = paramCalendar.get(2);
    int m = paramCalendar.get(1);
    if ((m == i) && (k == j))
    {
      mNowDayType.put(paramCalendar.get(5) - 1, (byte)paramInt);
    }
    else
    {
      int n = k;
      mRotDayType.put(n * 32 + paramCalendar.get(5) - 1, (byte)paramInt);
    }
  }
  
  public void setNowDay(byte[] paramArrayOfByte, int paramInt)
  {
    mNowDayType.rewind();
    mNowDayType.put(paramArrayOfByte, paramInt, mNowDayType.capacity());
  }
  
  public void setNowDayType(ByteBuffer paramByteBuffer)
  {
    mNowDayType = paramByteBuffer;
  }
  
  public void setRotDay(byte[] paramArrayOfByte, int paramInt)
  {
    mRotDayType.rewind();
    mRotDayType.put(paramArrayOfByte, paramInt, mRotDayType.capacity());
  }
  
  public void setRotDayType(ByteBuffer paramByteBuffer)
  {
    mRotDayType = paramByteBuffer;
  }
  
  public void setRotation(boolean paramBoolean)
  {
    mRotation = paramBoolean;
  }
  
  public void setStartMonth(int paramInt1, int paramInt2)
  {
    mStartDate = new GregorianCalendar(paramInt1, paramInt2 - 1, 1);
  }
}
