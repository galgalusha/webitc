package webitc.job;

import webitc.com.ComSetSchEnable;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobSetSchEnable
  extends Job
{
  ComSetSchEnable mCom = new ComSetSchEnable();
  
  public JobSetSchEnable(int paramInt, boolean paramBoolean)
  {
    mCom.setProgram(paramInt, paramBoolean);
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
