package webitc.job;

import webitc.com.ComSetLogout;
import webitc.com.SockHttp;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.frame.Job;
import webitc.frame.Result;
import webitc.frame.ThreadPntRequest;
import webitc.frame.ThreadUpdate;

public class JobLogout
  extends Job
{
  public JobLogout() {}
  
  protected void runPrivate()
    throws Exception
  {
    if (ThreadPntRequest.getInstance() == null) {
      return;
    }
    Result localResult = ThreadPntRequest.getInstance().shutdownThread();
    while (!localResult.isFinished()) {
      Thread.sleep(100L);
    }
    ThreadPntRequest.deleteInstance();
    localResult = ThreadUpdate.getInstance().shutdownThread();
    while (!localResult.isFinished()) {
      Thread.sleep(100L);
    }
    ThreadUpdate.deleteInstance();
    ComSetLogout localComSetLogout = new ComSetLogout();
    localComSetLogout.setUserID(DataMgr.getInstance().getLoginUser().getUserID());
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(localComSetLogout);
  }
}
