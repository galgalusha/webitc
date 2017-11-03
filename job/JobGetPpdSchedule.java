package webitc.job;

import java.util.Calendar;
import webitc.com.ComGetPpdSchedule;
import webitc.com.SockHttp;
import webitc.frame.Job;
import webitc.gui.ppd.PpdSchedule;

public class JobGetPpdSchedule
  extends Job
{
  ComGetPpdSchedule mCmd = new ComGetPpdSchedule();
  
  public JobGetPpdSchedule() {}
  
  public Calendar getDate()
  {
    return mCmd.getRecvCalendar();
  }
  
  public PpdSchedule getPpdSchedule()
  {
    return mCmd.getPpdSchedule();
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
