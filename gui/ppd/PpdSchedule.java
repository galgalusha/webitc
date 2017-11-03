package webitc.gui.ppd;

public class PpdSchedule
{
  public static final int FRI = 5;
  public static final int MON = 1;
  public static final int MONTH_NUM = 12;
  public static final int SAT = 6;
  public static final int SUN = 0;
  public static final int THU = 4;
  public static final int TUE = 2;
  public static final int WDAY_NUM = 7;
  public static final int WED = 3;
  private PpdPeriod[] mExclusionTime = new PpdPeriod[7];
  private int mExclusionWDay = 0;
  private PpdPeriod mNightTime = new PpdPeriod(true);
  private int[] mSpecialDay = new int[12];
  
  public PpdSchedule()
  {
    try
    {
      for (int i = 0; i < 7; i++) {
        mExclusionTime[i] = new PpdPeriod(false);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public PpdPeriod getExclusionTime(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 6)) {
      return null;
    }
    return mExclusionTime[paramInt];
  }
  
  public int getExclusionTimeInt(int paramInt)
  {
    PpdPeriod localPpdPeriod = getExclusionTime(paramInt);
    if (localPpdPeriod == null) {
      return 0;
    }
    return localPpdPeriod.getInt();
  }
  
  public int getExclusionWDayBitmap()
  {
    return mExclusionWDay;
  }
  
  public PpdPeriod getNightTime()
  {
    return mNightTime;
  }
  
  public int getNightTimeInt()
  {
    return mNightTime.getInt();
  }
  
  public int getSpecialDayBitmap(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 12)) {
      return 0;
    }
    return mSpecialDay[(paramInt - 1)];
  }
  
  public boolean isExclusionWDay(int paramInt)
  {
    if ((paramInt < 0) || (paramInt > 6)) {
      return false;
    }
    return (mExclusionWDay >> paramInt & 0x1) == 1;
  }
  
  public boolean isSpecialDay(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 1) || (paramInt1 > 12)) {
      return false;
    }
    if ((paramInt2 < 1) || (paramInt2 > 31)) {
      return false;
    }
    return (mSpecialDay[(paramInt1 - 1)] >> paramInt2 - 1 & 0x1) == 1;
  }
  
  public void setExclusionTime(int paramInt, PpdPeriod paramPpdPeriod)
  {
    if ((paramInt < 0) || (paramInt > 6)) {
      return;
    }
    mExclusionTime[paramInt] = paramPpdPeriod;
  }
  
  public void setExclusionTimeInt(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 > 6)) {
      return;
    }
    mExclusionTime[paramInt1].setInt(paramInt2);
  }
  
  public void setExclusionWDay(int paramInt, boolean paramBoolean)
  {
    if ((paramInt < 0) || (paramInt > 6)) {
      return;
    }
    if (paramBoolean) {
      mExclusionWDay |= 1 << paramInt;
    } else {
      mExclusionWDay &= (mExclusionWDay ^ 1 << paramInt);
    }
  }
  
  public void setExclusionWDayBitmap(int paramInt)
  {
    mExclusionWDay = paramInt;
  }
  
  public void setNightTime(PpdPeriod paramPpdPeriod)
  {
    mNightTime = paramPpdPeriod;
  }
  
  public void setNightTimeInt(int paramInt)
  {
    mNightTime.setInt(paramInt);
  }
  
  public void setSpecialDay(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if ((paramInt1 < 1) || (paramInt1 > 12)) {
      return;
    }
    if ((paramInt2 < 1) || (paramInt2 > 31)) {
      return;
    }
    if (paramBoolean) {
      mSpecialDay[(paramInt1 - 1)] |= 1 << paramInt2 - 1;
    } else {
      mSpecialDay[(paramInt1 - 1)] &= (mSpecialDay[(paramInt1 - 1)] ^ 1 << paramInt2 - 1);
    }
  }
  
  public void setSpecialDayBitmap(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 1) || (paramInt1 > 12)) {
      return;
    }
    mSpecialDay[(paramInt1 - 1)] = paramInt2;
  }
}
