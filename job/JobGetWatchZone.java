package webitc.job;

import webitc.com.ComGetWatchZone;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobGetWatchZone
  extends Job
{
  private ComGetWatchZone mCmd = new ComGetWatchZone();
  private int mUserId = -1;
  
  public JobGetWatchZone(int paramInt)
  {
    mUserId = paramInt;
  }
  
  public int getResult()
  {
    return mCmd.getReturn();
  }
  
  public int getZoneID(int paramInt)
  {
    return mCmd.getZoneID(paramInt);
  }
  
  public int getZoneNum()
  {
    return mCmd.getZoneNum();
  }
  
  protected void runPrivate()
    throws Exception
  {
    mCmd.setUserID(mUserId);
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
