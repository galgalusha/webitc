package webitc.frame;

public class FutureResult
  extends Result
{
  public FutureResult(Job paramJob)
  {
    super(paramJob);
  }
  
  public synchronized void finish()
  {
    mFinished = true;
    notifyAll();
  }
  
  public synchronized void finishCommError()
  {
    mFinished = true;
    mCommErrorOccured = true;
    notifyAll();
  }
  
  public synchronized Job getJob()
  {
    while (!isFinished()) {
      try
      {
        wait();
      }
      catch (InterruptedException localInterruptedException) {}
    }
    return mJob;
  }
  
  public synchronized Job getJobFromWorker()
  {
    return mJob;
  }
  
  public synchronized void setException(Exception paramException)
  {
    mException = paramException;
  }
}
