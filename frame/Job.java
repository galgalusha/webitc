package webitc.frame;

public abstract class Job
  implements Runnable
{
  private Exception mException = null;
  
  public Job() {}
  
  public void cancel() {}
  
  public Exception getException()
  {
    return mException;
  }
  
  public boolean isShutdownThread()
  {
    return false;
  }
  
  public void run()
  {
    try
    {
      mException = null;
      runPrivate();
    }
    catch (Exception localException)
    {
      setException(localException);
    }
  }
  
  protected abstract void runPrivate()
    throws Exception;
  
  public void runThrowsException()
    throws Exception
  {
    mException = null;
    runPrivate();
  }
  
  protected void setException(Exception paramException)
  {
    mException = paramException;
  }
  
  public boolean withCancel()
  {
    return false;
  }
}
