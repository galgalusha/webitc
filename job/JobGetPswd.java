package webitc.job;

import webitc.com.ComGetPswd;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobGetPswd
  extends Job
{
  private String mPassword = null;
  private int mResult = 1;
  private int mUserId = -1;
  
  public JobGetPswd(int paramInt)
  {
    mUserId = paramInt;
  }
  
  public String getPassword()
  {
    return mPassword;
  }
  
  public int getResult()
  {
    return mResult;
  }
  
  protected void runPrivate()
    throws Exception
  {
    ComGetPswd localComGetPswd = new ComGetPswd();
    localComGetPswd.setUserID(mUserId);
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(localComGetPswd);
    mResult = localComGetPswd.getReturn();
    if (mResult == 1) {
      mPassword = localComGetPswd.getPassword();
    }
  }
}
