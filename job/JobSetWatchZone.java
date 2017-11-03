package webitc.job;

import webitc.com.ComSetWatchZone;
import webitc.com.SockHttp;
import webitc.data.ID;
import webitc.frame.Job;

public class JobSetWatchZone
  extends Job
{
  ComSetWatchZone mCmd = new ComSetWatchZone();
  
  public JobSetWatchZone(int paramInt, ID[] paramArrayOfID)
  {
    mCmd.setUserID(paramInt);
    mCmd.setZoneList(paramArrayOfID);
  }
  
  public int getResult()
  {
    return mCmd.getReturn();
  }
  
  public int getUserID()
  {
    return mCmd.getUserID();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
