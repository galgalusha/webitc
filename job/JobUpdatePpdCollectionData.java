package webitc.job;

import java.util.Calendar;
import webitc.com.ComGetPpdData;
import webitc.com.ComGetPpdTarget;
import webitc.com.SockHttp;
import webitc.common.FatalException;
import webitc.common.SystemInfo;
import webitc.frame.JobWithCancel;
import webitc.gui.ppd.PpdCollectionData;
import webitc.gui.ppd.PpdInnerPower;
import webitc.gui.ppd.PpdOuterPower;
import webitc.gui.ppd.PpdUnitProp;

public class JobUpdatePpdCollectionData
  extends JobWithCancel
{
  private static final int MILLISECOND_PER_SECOND = 1000;
  private static final int SECOND_PER_HOUR = 3600;
  private int mContractPwr = 0;
  private Calendar mEndTime = Calendar.getInstance(SystemInfo.getTimeZone());
  private int mReturn = 0;
  private Calendar mStartTime = Calendar.getInstance(SystemInfo.getTimeZone());
  
  public JobUpdatePpdCollectionData(Calendar paramCalendar1, Calendar paramCalendar2, boolean paramBoolean, int paramInt)
  {
    mStartTime.set(paramCalendar1.get(1), paramCalendar1.get(2), paramCalendar1.get(5), 0, 0, 0);
    mEndTime.set(paramCalendar2.get(1), paramCalendar2.get(2), paramCalendar2.get(5), 0, 0, 0);
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    if (localPpdCollectionData == null) {
      throw new FatalException("JobUpdatePpdCollectionData : No PpdCollectionData");
    }
    localPpdCollectionData.clear();
    localPpdCollectionData.setCollectionTypePeriod(paramBoolean);
    mContractPwr = paramInt;
  }
  
  public int getResult()
  {
    return mReturn;
  }
  
  protected void runPrivate()
    throws Exception
  {
    PpdCollectionData localPpdCollectionData = PpdCollectionData.getInstance();
    if (localPpdCollectionData == null) {
      throw new FatalException("JpbUpdatePpdCollectionData: No PpdCollectionData");
    }
    ComGetPpdTarget localComGetPpdTarget = new ComGetPpdTarget();
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(localComGetPpdTarget);
    if (localComGetPpdTarget.getReturn() != 1)
    {
      mReturn = 0;
      return;
    }
    int i = localComGetPpdTarget.getTargetNum();
    for (int j = 0; j < i; j++)
    {
      PpdUnitProp localPpdUnitProp = localComGetPpdTarget.getUnitProp(j);
      localPpdCollectionData.addUnit(localPpdUnitProp);
    }
    int k = (int)(mStartTime.getTimeInMillis() / 1000L);
    Calendar localCalendar = (Calendar)mEndTime.clone();
    localCalendar.add(5, 1);
    int m = (int)(localCalendar.getTimeInMillis() / 1000L);
    int n = (m - k) / 3600;
    ComGetPpdData localComGetPpdData = new ComGetPpdData();
    int i1 = k;
    int i2 = 0;
    for (;;)
    {
      if (isCanceled() == true)
      {
        mReturn = -1;
        return;
      }
      int i3 = i1;
      localComGetPpdData.setCollectionPeriod(i1, i3);
      localSockHttp.requestReply(localComGetPpdData);
      if (localComGetPpdData.getReturn() != 1)
      {
        mReturn = 0;
        return;
      }
      int i4 = localComGetPpdData.getInPntNum();
      int i5 = localComGetPpdData.getOutPntNum();
      int i6 = localComGetPpdData.getHours();
      for (int i7 = 0; i7 < i6; i7++)
      {
        PpdInnerPower localPpdInnerPower = localComGetPpdData.getInnerPower(i7, -1);
        localPpdCollectionData.addInvalidTime(localPpdInnerPower);
        for (int i8 = 0; i8 < i4; i8++)
        {
          i9 = localComGetPpdData.getUnitNum(i7, i8);
          localPpdInnerPower = localComGetPpdData.getInnerPower(i7, i8);
          localPpdCollectionData.addInnerPower(i9, localPpdInnerPower);
        }
        for (int i9 = 0; i9 < i5; i9++)
        {
          PpdOuterPower localPpdOuterPower = localComGetPpdData.getOuterPower(i7, i9);
          localPpdCollectionData.addOuterPower(localPpdOuterPower);
        }
      }
      i1 += i6 * 3600;
      i2 += i6;
      if (i2 >= n) {
        break;
      }
      setProgress(i2 * 100 / n);
    }
    localPpdCollectionData.execTSCDistribution(mContractPwr);
    setProgress(100);
    mReturn = 1;
  }
}
