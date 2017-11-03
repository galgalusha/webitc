package webitc.job;

import java.util.ArrayList;
import webitc.com.ComSetSchProgram;
import webitc.com.SockHttp;
import webitc.data.schedule.Program;
import webitc.frame.Job;

public class JobSetAllSchedule
  extends Job
{
  private ArrayList mProgramList;
  
  public JobSetAllSchedule(ArrayList paramArrayList)
  {
    mProgramList = paramArrayList;
  }
  
  protected void runPrivate()
    throws Exception
  {
    for (int i = 0; i < 8; i++)
    {
      ComSetSchProgram localComSetSchProgram = new ComSetSchProgram();
      localComSetSchProgram.setPartOfAllSet(true);
      localComSetSchProgram.setProgram(i, (Program)mProgramList.get(i));
      SockHttp localSockHttp = SockHttp.getInstance();
      localSockHttp.requestReply(localComSetSchProgram);
    }
  }
}
