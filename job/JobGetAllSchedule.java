package webitc.job;

import java.util.ArrayList;
import webitc.com.ComGetSchProgram;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobGetAllSchedule
  extends Job
{
  private ComGetSchProgram[] mCmd = new ComGetSchProgram[8];
  private ArrayList mList = new ArrayList();
  
  public JobGetAllSchedule() {}
  
  public ArrayList getProgramList()
  {
    return mList;
  }
  
  protected void runPrivate()
    throws Exception
  {
    mList.clear();
    SockHttp localSockHttp = SockHttp.getInstance();
    for (int i = 0; i < 8; i++)
    {
      mCmd[i] = new ComGetSchProgram();
      mCmd[i].setProgramIndex(i);
      localSockHttp.requestReply(mCmd[i]);
      mList.add(mCmd[i].getProgram());
    }
  }
}
