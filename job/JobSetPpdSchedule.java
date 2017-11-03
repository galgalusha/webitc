package webitc.job;

import webitc.com.ComSetPpdSchedule;
import webitc.com.SockHttp;
import webitc.frame.Job;
import webitc.gui.ppd.PpdSchedule;

public class JobSetPpdSchedule
  extends Job
{
  ComSetPpdSchedule mCmd = new ComSetPpdSchedule();
  
  public JobSetPpdSchedule(PpdSchedule paramPpdSchedule)
  {
    mCmd.setPpdSchedule(paramPpdSchedule);
  }
  
  public int getResult()
  {
    return mCmd.getReturn();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
