package webitc.job;

import webitc.com.ComGetPntDetailInfo;
import webitc.com.SockHttp;
import webitc.common.FatalException;
import webitc.data.ID;
import webitc.data.point.PntInfo;
import webitc.frame.Job;

public class JobGetPntDetailInfo
  extends Job
{
  private ComGetPntDetailInfo mCom = new ComGetPntDetailInfo();
  
  public JobGetPntDetailInfo(ID paramID)
  {
    if (fType != 0) {
      throw new FatalException("JobGetPntDetailInfo.JobGetPntDetailInfo");
    }
    mCom.setSendId(fID);
  }
  
  public PntInfo getPntInfo()
  {
    return mCom.getPntInfo();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
