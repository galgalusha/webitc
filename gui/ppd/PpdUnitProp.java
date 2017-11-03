package webitc.gui.ppd;

import java.util.ArrayList;
import java.util.Calendar;
import webitc.data.ID;

public class PpdUnitProp
{
  public static final int PPD_INDOORTYPE_DRT = 4;
  public static final int PPD_INDOORTYPE_EHP = 0;
  public static final int PPD_INDOORTYPE_GHP = 2;
  public static final int PPD_INDOORTYPE_THO = 3;
  public static final int PPD_INDOORTYPE_TIMEUNIT = 5;
  public static final int PPD_INDOORTYPE_TSU = 1;
  public static final int PPD_INDOOR_TYPE_NON = -1;
  private final int mAddress;
  private Calendar mEndTime = null;
  private final int mHpw;
  private final ID mID;
  private final int mIndoorType;
  private ArrayList mPowerList = new ArrayList();
  private Calendar mStartTime = null;
  private PpdSumPower mSumPower = new PpdSumPower();
  private final int mUnitNum;
  
  public PpdUnitProp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    mUnitNum = paramInt1;
    mIndoorType = paramInt2;
    mID = new ID(0, paramInt3);
    mAddress = paramInt4;
    mHpw = paramInt5;
  }
  
  public void addPower(PpdInnerPower paramPpdInnerPower)
  {
    if (paramPpdInnerPower == null) {
      return;
    }
    mPowerList.add(paramPpdInnerPower);
    if ((mStartTime == null) || (paramPpdInnerPower.getTime().before(mStartTime))) {
      mStartTime = ((Calendar)paramPpdInnerPower.getTime().clone());
    }
    if ((mEndTime == null) || (paramPpdInnerPower.getTime().after(mEndTime))) {
      mEndTime = ((Calendar)paramPpdInnerPower.getTime().clone());
    }
    if (paramPpdInnerPower.isEnable() == true) {
      mSumPower.addPower(mIndoorType, paramPpdInnerPower);
    }
  }
  
  public int getAddress()
  {
    return mAddress;
  }
  
  public Calendar getEndTime()
  {
    return (Calendar)mEndTime.clone();
  }
  
  public int getHorsePower()
  {
    return mHpw;
  }
  
  public ID getID()
  {
    return mID;
  }
  
  public int getIndoorType()
  {
    return mIndoorType;
  }
  
  public ArrayList getInvalidTimeArray()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < mPowerList.size(); i++)
    {
      PpdInnerPower localPpdInnerPower = (PpdInnerPower)mPowerList.get(i);
      if (!localPpdInnerPower.isEnable()) {
        localArrayList.add(localPpdInnerPower.getTimeStr());
      }
    }
    return localArrayList;
  }
  
  public PpdInnerPower getPower(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mPowerList.size())) {
      return PpdInnerPower.InvalidPower;
    }
    PpdInnerPower localPpdInnerPower = (PpdInnerPower)mPowerList.get(paramInt);
    return localPpdInnerPower;
  }
  
  public Calendar getStartTime()
  {
    return (Calendar)mStartTime.clone();
  }
  
  public PpdSumPower getSumPower()
  {
    return mSumPower;
  }
  
  public int getUnitNum()
  {
    return mUnitNum;
  }
  
  public boolean isValidTime(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mPowerList.size())) {
      return false;
    }
    PpdInnerPower localPpdInnerPower = (PpdInnerPower)mPowerList.get(paramInt);
    return localPpdInnerPower.isEnable();
  }
  
  public void setTSC(int paramInt1, int paramInt2)
  {
    mSumPower.setTSC(paramInt1, paramInt2);
  }
}
