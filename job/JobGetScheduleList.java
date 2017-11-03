package webitc.job;

import webitc.com.ComGetScheduleAll;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobGetScheduleList
  extends Job
{
  private ComGetScheduleAll mCmd = new ComGetScheduleAll();
  
  public JobGetScheduleList() {}
  
  public int getScheduleNum()
  {
    return mCmd.getScheduleNum();
  }
  
  public String getScheudleName(int paramInt)
  {
    return mCmd.getScheudleName(paramInt);
  }
  
  public boolean isScheduleEnable(int paramInt)
  {
    return mCmd.isScheduleEnable(paramInt);
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
