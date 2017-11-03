package webitc.job;

import webitc.com.ComSaveAuth;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobSaveAuth
  extends Job
{
  ComSaveAuth mCom = new ComSaveAuth();
  
  public JobSaveAuth() {}
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
