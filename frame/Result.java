package webitc.frame;

public abstract class Result
{
  protected boolean mCommErrorOccured = false;
  protected Exception mException = null;
  protected boolean mFinished = false;
  protected Job mJob;
  
  public Result(Job paramJob)
  {
    mJob = paramJob;
  }
  
  public synchronized boolean cancel()
  {
    if ((mJob.withCancel() == true) && (!mFinished))
    {
      mJob.cancel();
      return true;
    }
    return false;
  }
  
  public synchronized Exception getException()
  {
    return mException;
  }
  
  public abstract Job getJob();
  
  public synchronized boolean isCommErrorOccured()
  {
    return mCommErrorOccured;
  }
  
  public synchronized boolean isFinished()
  {
    return mFinished;
  }
}
