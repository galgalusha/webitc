package webitc.job;

import java.util.ArrayList;
import java.util.Calendar;
import webitc.com.ComGetPpdCollectionType;
import webitc.com.SockHttp;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.VZone;
import webitc.frame.Job;

public class JobGetPpdCollectionType
  extends Job
{
  ComGetPpdCollectionType mCmd = new ComGetPpdCollectionType();
  
  public JobGetPpdCollectionType() {}
  
  public int getCalcDate()
  {
    return mCmd.getCalcDate();
  }
  
  public int[] getContractPwr()
  {
    return mCmd.getContractPwr();
  }
  
  public Calendar getDate()
  {
    return mCmd.getRecvCalendar();
  }
  
  public int getResult()
  {
    return mCmd.getReturn();
  }
  
  public boolean isPeriodType()
  {
    return mCmd.isPeriodType();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
    DataMgr localDataMgr = DataMgr.getInstance();
    if (localDataMgr == null) {
      return;
    }
    ArrayList localArrayList = localDataMgr.getZoneIDList();
    for (int i = 0; i < localArrayList.size(); i++)
    {
      ID localID = (ID)localArrayList.get(i);
      VZone localVZone = localDataMgr.getZoneFromID(localID);
      if (!localVZone.isPntReady()) {
        updateZone(localID);
      }
    }
  }
  
  public boolean updateZone(ID paramID)
  {
    JobUpdateZoneIDs localJobUpdateZoneIDs = new JobUpdateZoneIDs(paramID, true);
    try
    {
      localJobUpdateZoneIDs.runThrowsException();
    }
    catch (Exception localException)
    {
      return false;
    }
    return localJobUpdateZoneIDs.isCommErrorOccured() != true;
  }
}
