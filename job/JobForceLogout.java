package webitc.job;

import webitc.frame.Job;
import webitc.frame.Result;
import webitc.frame.ThreadPntRequest;
import webitc.frame.ThreadUpdate;

public class JobForceLogout
  extends Job
{
  public JobForceLogout() {}
  
  protected void runPrivate()
    throws Exception
  {
    Result localResult;
    if (ThreadPntRequest.getInstance() != null)
    {
      localResult = ThreadPntRequest.getInstance().shutdownThread();
      while (!localResult.isFinished()) {
        Thread.sleep(100L);
      }
      ThreadPntRequest.deleteInstance();
    }
    if (ThreadUpdate.getInstance() != null)
    {
      localResult = ThreadUpdate.getInstance().shutdownThread();
      while (!localResult.isFinished()) {
        Thread.sleep(100L);
      }
      ThreadUpdate.deleteInstance();
    }
  }
}
