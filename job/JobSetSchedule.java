package webitc.job;

import webitc.com.ComSetSchProgram;
import webitc.com.SockHttp;
import webitc.data.schedule.Program;
import webitc.frame.Job;

public class JobSetSchedule
  extends Job
{
  private ComSetSchProgram mCom = new ComSetSchProgram();
  
  public JobSetSchedule(int paramInt, Program paramProgram)
  {
    mCom.setPartOfAllSet(false);
    mCom.setProgram(paramInt, paramProgram);
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
