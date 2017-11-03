package webitc.job;

import webitc.com.ComGetZoneDetailInfo;
import webitc.com.SockHttp;
import webitc.common.FatalException;
import webitc.data.ID;
import webitc.data.point.ZoneInfo;
import webitc.frame.Job;

public class JobGetZoneDetailInfo
  extends Job
{
  private ComGetZoneDetailInfo mCom = new ComGetZoneDetailInfo();
  
  public JobGetZoneDetailInfo(ID paramID)
  {
    if (fType != 1) {
      throw new FatalException("JobGetZoneDetailInfo.JobGetZoneDetailInfo");
    }
    mCom.setSendId(fID);
  }
  
  public ZoneInfo getZoneInfo()
  {
    return mCom.getZoneInfo();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
