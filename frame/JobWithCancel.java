package webitc.frame;

public abstract class JobWithCancel
  extends Job
{
  public static final int RET_CANCEL = -1;
  public static final int RET_NG = 0;
  public static final int RET_OK = 1;
  private boolean mCanceled = false;
  private Progress mProgress = new Progress();
  
  public JobWithCancel() {}
  
  public final synchronized void cancel()
  {
    mCanceled = true;
  }
  
  public Progress getProgress()
  {
    return mProgress;
  }
  
  public synchronized boolean isCanceled()
  {
    return mCanceled;
  }
  
  public void setProgress(int paramInt)
  {
    mProgress.setProgress(paramInt);
  }
  
  public final boolean withCancel()
  {
    return true;
  }
}
