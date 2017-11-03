package webitc.job;

import webitc.com.ComDeleteUser;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobDeleteUser
  extends Job
{
  ComDeleteUser mCmd = new ComDeleteUser();
  
  public JobDeleteUser(int paramInt)
  {
    mCmd.setUserID(paramInt);
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
