package webitc.job;

import webitc.com.ComGetUserAll;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobGetAllUser
  extends Job
{
  private ComGetUserAll mCmd = new ComGetUserAll();
  
  public JobGetAllUser() {}
  
  public int getResult()
  {
    return mCmd.getReturn();
  }
  
  public int getUserID(int paramInt)
  {
    return mCmd.getUserID(paramInt);
  }
  
  public String getUserName(int paramInt)
  {
    return mCmd.getUserName(paramInt);
  }
  
  public int getUserNum()
  {
    return mCmd.getUserNum();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
