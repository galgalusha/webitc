package webitc.job;

import webitc.com.ComSetPntState;
import webitc.com.SockHttp;
import webitc.data.ID;
import webitc.data.point.PntSet;
import webitc.frame.Job;

public class JobSetPntState
  extends Job
{
  private ComSetPntState mCmd = new ComSetPntState();
  
  public JobSetPntState(ID paramID, PntSet paramPntSet)
  {
    mCmd.setOperation(paramID, paramPntSet);
  }
  
  public boolean getPntEnable()
  {
    return mCmd.getPntEnable();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
