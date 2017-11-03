package webitc.job;

import java.util.Calendar;
import webitc.com.ComGetSchProgram;
import webitc.com.SockHttp;
import webitc.data.schedule.Program;
import webitc.frame.Job;

public class JobGetSchedule
  extends Job
{
  private ComGetSchProgram mCom = new ComGetSchProgram();
  
  public JobGetSchedule(int paramInt)
  {
    mCom.setProgramIndex(paramInt);
  }
  
  public Calendar getDate()
  {
    return mCom.getRecvCalendar();
  }
  
  public Program getProgram()
  {
    return mCom.getProgram();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
