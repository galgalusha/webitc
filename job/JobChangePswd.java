package webitc.job;

import webitc.com.ComSaveAuth;
import webitc.com.ComSetPswd;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobChangePswd
  extends Job
{
  private final boolean mNeedSaveFlash;
  private final String mPassword;
  private int mResult = -1;
  private final int mUserId;
  
  public JobChangePswd(int paramInt, String paramString, boolean paramBoolean)
  {
    mUserId = paramInt;
    mPassword = paramString;
    mNeedSaveFlash = paramBoolean;
  }
  
  public int getResult()
  {
    return mResult;
  }
  
  protected void runPrivate()
    throws Exception
  {
    ComSetPswd localComSetPswd = new ComSetPswd();
    localComSetPswd.setUserID(mUserId);
    localComSetPswd.setPassword(mPassword);
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(localComSetPswd);
    mResult = localComSetPswd.getReturn();
    if (mNeedSaveFlash == true)
    {
      ComSaveAuth localComSaveAuth = new ComSaveAuth();
      localSockHttp.requestReply(localComSaveAuth);
    }
  }
}
