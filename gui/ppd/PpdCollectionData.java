package webitc.gui.ppd;

import java.util.ArrayList;
import java.util.Calendar;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.VZone;

public class PpdCollectionData
{
  public static final int D3_PORT_NUM = 2;
  public static final int MAX_UNIT_NUM = 128;
  public static final int PORT_INDOOR_NUM = 64;
  private Calendar mEndTime = null;
  private ArrayList mInnerPowerArray = new ArrayList();
  private ArrayList mInvalidTimeStrArray = new ArrayList();
  private boolean mIsCollectionTypePeriod = false;
  private PpdUnitProp mMarkUnit = null;
  private long mNoDistributionPower = 0L;
  private ArrayList mOuterPowerArray = new ArrayList();
  private Calendar mStartTime = null;
  private ArrayList mUnitArray = new ArrayList();
  private ArrayList mValidTimeArray = new ArrayList();
  private static PpdCollectionData sInstance = null;
  
  private PpdCollectionData() {}
  
  public void addInnerPower(int paramInt, PpdInnerPower paramPpdInnerPower)
  {
    if ((mMarkUnit == null) || (paramInt != mMarkUnit.getUnitNum()))
    {
      mMarkUnit = getUnitProp(paramInt);
      if (mMarkUnit == null) {
        return;
      }
    }
    mMarkUnit.addPower(paramPpdInnerPower);
    if (mMarkUnit.getIndoorType() == 1) {
      for (int i = 0; i < mInnerPowerArray.size(); i++)
      {
        PpdOuterPower localPpdOuterPower1 = (PpdOuterPower)mInnerPowerArray.get(i);
        int j = mMarkUnit.getAddress();
        if (localPpdOuterPower1.getKeitou() == j)
        {
          PpdOuterPower localPpdOuterPower2 = new PpdOuterPower(j, paramPpdInnerPower.getDayPower(), paramPpdInnerPower.getNightPower());
          localPpdOuterPower1.addPower(localPpdOuterPower2);
          return;
        }
      }
    }
  }
  
  public void addInvalidTime(PpdInnerPower paramPpdInnerPower)
  {
    if (!paramPpdInnerPower.isEnable()) {
      mInvalidTimeStrArray.add(paramPpdInnerPower.getTimeStr());
    } else {
      mValidTimeArray.add(paramPpdInnerPower.getTime());
    }
    if ((mStartTime == null) || (paramPpdInnerPower.getTime().before(mStartTime))) {
      mStartTime = ((Calendar)paramPpdInnerPower.getTime().clone());
    }
    if ((mEndTime == null) || (paramPpdInnerPower.getTime().after(mEndTime))) {
      mEndTime = ((Calendar)paramPpdInnerPower.getTime().clone());
    }
  }
  
  public void addOuterPower(PpdOuterPower paramPpdOuterPower)
  {
    for (int i = 0; i < mOuterPowerArray.size(); i++)
    {
      PpdOuterPower localPpdOuterPower = (PpdOuterPower)mOuterPowerArray.get(i);
      if (localPpdOuterPower.getKeitou() == paramPpdOuterPower.getKeitou())
      {
        localPpdOuterPower.addPower(paramPpdOuterPower);
        return;
      }
    }
    mOuterPowerArray.add(paramPpdOuterPower);
  }
  
  public void addUnit(PpdUnitProp paramPpdUnitProp)
  {
    if (paramPpdUnitProp == null) {
      return;
    }
    mUnitArray.add(paramPpdUnitProp);
    mMarkUnit = paramPpdUnitProp;
    for (int i = 0; i < mInnerPowerArray.size(); i++)
    {
      localPpdOuterPower = (PpdOuterPower)mInnerPowerArray.get(i);
      if (localPpdOuterPower.getKeitou() == paramPpdUnitProp.getAddress()) {
        return;
      }
    }
    PpdOuterPower localPpdOuterPower = new PpdOuterPower(paramPpdUnitProp.getAddress(), 0, 0);
    mInnerPowerArray.add(localPpdOuterPower);
  }
  
  public void clear()
  {
    mUnitArray.clear();
    mMarkUnit = null;
    mIsCollectionTypePeriod = false;
    mStartTime = null;
    mEndTime = null;
    mInvalidTimeStrArray.clear();
    mValidTimeArray.clear();
    mInnerPowerArray.clear();
    mOuterPowerArray.clear();
    mNoDistributionPower = 0L;
  }
  
  public static void create()
  {
    if (sInstance == null) {
      sInstance = new PpdCollectionData();
    }
  }
  
  public void execTSCDistribution(int paramInt)
  {
    double d1 = 0.0D;
    for (int i = 0; i < mOuterPowerArray.size(); i++)
    {
      PpdOuterPower localPpdOuterPower1 = (PpdOuterPower)mOuterPowerArray.get(i);
      d1 += localPpdOuterPower1.getDayPower();
      d1 += localPpdOuterPower1.getNightPower();
    }
    double d2 = d1 - paramInt * 1000;
    if (d2 < 0.0D) {
      d2 = 0.0D;
    }
    long[] arrayOfLong = new long[mOuterPowerArray.size()];
    for (int j = 0; j < arrayOfLong.length; j++) {
      arrayOfLong[j] = 0L;
    }
    for (int k = 0; k < mOuterPowerArray.size(); k++)
    {
      double d3 = 0.0D;
      PpdOuterPower localPpdOuterPower2 = (PpdOuterPower)mOuterPowerArray.get(k);
      if (d2 == 0.0D)
      {
        d3 = 0.0D;
      }
      else
      {
        d4 = (localPpdOuterPower2.getDayPower() + localPpdOuterPower2.getNightPower()) / d1;
        d3 = Math.ceil(d4 * d2);
      }
      double d4 = localPpdOuterPower2.getDayPower() + localPpdOuterPower2.getNightPower() - d3;
      int n = localPpdOuterPower2.getKeitou();
      double d5 = 0.0D;
      for (int i1 = 0; i1 < 128; i1++) {
        if (isTarget(i1))
        {
          PpdUnitProp localPpdUnitProp1 = getUnitProp(i1);
          if ((localPpdUnitProp1.getAddress() == n) && (localPpdUnitProp1.getIndoorType() == 1)) {
            d5 += localPpdUnitProp1.getSumPower().getDayPower() + localPpdUnitProp1.getSumPower().getNightPower();
          }
        }
      }
      for (int i2 = 0; i2 < 128; i2++) {
        if (isTarget(i2))
        {
          PpdUnitProp localPpdUnitProp2 = getUnitProp(i2);
          if ((localPpdUnitProp2.getAddress() == n) && (localPpdUnitProp2.getIndoorType() == 1))
          {
            double d6 = localPpdUnitProp2.getSumPower().getDayPower() + localPpdUnitProp2.getSumPower().getNightPower();
            double d7 = d5;
            if (d7 == 0.0D)
            {
              arrayOfLong[k] = ((d3 + d4));
            }
            else
            {
              int i3 = (int)(d6 + d6 / d7 * d3 + 0.5D);
              int i4 = (int)(d6 / d7 * d4 + 0.5D);
              localPpdUnitProp2.setTSC(i3, i4);
            }
          }
        }
      }
    }
    for (int m = 0; m < arrayOfLong.length; m++) {
      mNoDistributionPower += arrayOfLong[m];
    }
  }
  
  public PpdSumPower getAllSum()
  {
    PpdSumPower localPpdSumPower = new PpdSumPower();
    for (int i = 0; i < mUnitArray.size(); i++)
    {
      PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(i);
      localPpdSumPower.addPower(localPpdUnitProp.getSumPower());
    }
    localPpdSumPower.setNoDistributionPower(mNoDistributionPower);
    return localPpdSumPower;
  }
  
  public int getCollectionDays()
  {
    Calendar localCalendar1 = getStartTime();
    Calendar localCalendar2 = getEndTime();
    int i = localCalendar1.get(6);
    int j = localCalendar2.get(6);
    if (localCalendar1.get(1) == localCalendar2.get(1)) {
      return j - i + 1;
    }
    return localCalendar1.getActualMaximum(6) - i + j + 1;
  }
  
  public int getCollectionType()
  {
    if (mIsCollectionTypePeriod) {
      return 0;
    }
    Calendar localCalendar = getEndTime();
    int i = localCalendar.get(1) * 100 + (localCalendar.get(2) + 1);
    return i;
  }
  
  public int getEndDateInt()
  {
    Calendar localCalendar = getEndTime();
    int i = localCalendar.get(1) * 10000 + (localCalendar.get(2) + 1) * 100 + localCalendar.get(5);
    return i;
  }
  
  public Calendar getEndTime()
  {
    if (mUnitArray.size() == 0) {
      return (Calendar)mEndTime.clone();
    }
    PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(0);
    return localPpdUnitProp.getEndTime();
  }
  
  public static PpdCollectionData getInstance()
  {
    return sInstance;
  }
  
  public ArrayList getInvalidTimeArray()
  {
    if (mUnitArray.size() == 0) {
      return mInvalidTimeStrArray;
    }
    PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(0);
    return localPpdUnitProp.getInvalidTimeArray();
  }
  
  public String[] getInvalidTimeStrings()
  {
    ArrayList localArrayList = getInvalidTimeArray();
    String[] arrayOfString = new String[localArrayList.size()];
    for (int i = 0; i < localArrayList.size(); i++) {
      arrayOfString[i] = ((String)localArrayList.get(i));
    }
    return arrayOfString;
  }
  
  public static String getKWattStr(long paramLong)
  {
    long l1 = paramLong / 1000L;
    long l2 = paramLong - l1 * 1000L;
    return String.valueOf(l1) + "." + StrRes.ltoa(l2, true, 3);
  }
  
  public static String getKWattStr(int paramInt)
  {
    int i = paramInt / 1000;
    int j = paramInt - i * 1000;
    return String.valueOf(i) + "." + StrRes.itoa(j, true, 3);
  }
  
  public int getStartDateInt()
  {
    Calendar localCalendar = getStartTime();
    int i = localCalendar.get(1) * 10000 + (localCalendar.get(2) + 1) * 100 + localCalendar.get(5);
    return i;
  }
  
  public Calendar getStartTime()
  {
    if (mUnitArray.size() == 0) {
      return (Calendar)mStartTime.clone();
    }
    PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(0);
    return localPpdUnitProp.getStartTime();
  }
  
  public int getTargetNum()
  {
    return mUnitArray.size();
  }
  
  public int getUnitNumFromID(ID paramID)
  {
    PpdUnitProp localPpdUnitProp = getUnitProp(paramID);
    if (localPpdUnitProp == null)
    {
      DataMgr localDataMgr = DataMgr.getInstance();
      int i = localDataMgr.getPortNum(paramID);
      int j = localDataMgr.getAddress(paramID);
      if ((i == -1) || (j == -1)) {
        return -1;
      }
      int k = i * 64 + j;
      return k;
    }
    return localPpdUnitProp.getUnitNum();
  }
  
  public int[] getUnitNumList()
  {
    int[] arrayOfInt = new int[getTargetNum()];
    for (int i = 0; i < getTargetNum(); i++)
    {
      PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(i);
      arrayOfInt[i] = localPpdUnitProp.getUnitNum();
    }
    return arrayOfInt;
  }
  
  public PpdUnitProp getUnitProp(ID paramID)
  {
    for (int i = 0; i < mUnitArray.size(); i++)
    {
      PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(i);
      if (localPpdUnitProp.getID().equals(paramID)) {
        return localPpdUnitProp;
      }
    }
    return null;
  }
  
  public PpdUnitProp getUnitProp(int paramInt)
  {
    for (int i = 0; i < mUnitArray.size(); i++)
    {
      PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(i);
      if (localPpdUnitProp.getUnitNum() == paramInt) {
        return localPpdUnitProp;
      }
    }
    return null;
  }
  
  public PpdSumPower getUnitSum(int paramInt)
  {
    PpdUnitProp localPpdUnitProp = getUnitProp(paramInt);
    if (localPpdUnitProp == null) {
      return PpdSumPower.InvalidPower;
    }
    return localPpdUnitProp.getSumPower();
  }
  
  public PpdSumPower getZoneSum(ID paramID)
  {
    DataMgr localDataMgr = DataMgr.getInstance();
    if (localDataMgr == null) {
      return PpdSumPower.InvalidPower;
    }
    VZone localVZone = localDataMgr.getZoneFromID(paramID);
    if (localVZone == null) {
      return PpdSumPower.InvalidPower;
    }
    ArrayList localArrayList = localVZone.getPntList();
    PpdSumPower localPpdSumPower = new PpdSumPower();
    for (int i = 0; i < localArrayList.size(); i++)
    {
      ID localID = (ID)localArrayList.get(i);
      PpdUnitProp localPpdUnitProp = getUnitProp(localID);
      if (localPpdUnitProp != null) {
        localPpdSumPower.addPower(localPpdUnitProp.getSumPower());
      }
    }
    return localPpdSumPower;
  }
  
  public boolean isTarget(int paramInt)
  {
    return getUnitProp(paramInt) != null;
  }
  
  public boolean isValidTime(int paramInt)
  {
    if (mUnitArray.size() == 0) {
      return false;
    }
    PpdUnitProp localPpdUnitProp = (PpdUnitProp)mUnitArray.get(0);
    return localPpdUnitProp.isValidTime(paramInt);
  }
  
  public void setCollectionTypePeriod(boolean paramBoolean)
  {
    mIsCollectionTypePeriod = paramBoolean;
  }
}
