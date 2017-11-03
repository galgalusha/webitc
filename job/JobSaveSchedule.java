package webitc.job;

import webitc.com.ComSaveSchedule;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobSaveSchedule
  extends Job
{
  ComSaveSchedule mCom = new ComSaveSchedule();
  
  public JobSaveSchedule() {}
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
