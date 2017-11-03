package webitc.frame;

class JobShutdownThread
  extends Job
{
  JobShutdownThread() {}
  
  public boolean isShutdownThread()
  {
    return true;
  }
  
  protected void runPrivate()
    throws Exception
  {}
}
