package webitc.frame;

public class ThreadPntRequest
  extends ThreadAbst
{
  private static ThreadPntRequest mObj = null;
  
  private ThreadPntRequest()
  {
    super("iTC PntRequest");
  }
  
  protected void afterJob(Job paramJob)
  {
    super.afterJob(paramJob);
    if (paramJob.isShutdownThread() != true) {
      ThreadUpdate.interruptedUpdate();
    }
  }
  
  public static ThreadPntRequest createInstance()
  {
    mObj = new ThreadPntRequest();
    return mObj;
  }
  
  public static void deleteInstance()
  {
    mObj = null;
  }
  
  public static ThreadPntRequest getInstance()
  {
    return mObj;
  }
}
