package webitc.frame;

import javax.swing.SwingUtilities;
import webitc.gui.common.CommonUse;

abstract class ThreadTimer
  extends ThreadAbst
{
  private boolean mExceptionOccured = false;
  protected Job mPeriodicalJob;
  protected int mPeriodicalTime;
  
  public ThreadTimer(Job paramJob, int paramInt)
  {
    super("iTC Periodical");
    mPeriodicalJob = paramJob;
    mPeriodicalTime = paramInt;
  }
  
  public Result addJob(Job paramJob)
  {
    Result localResult = super.addJob(paramJob);
    interrupt();
    return localResult;
  }
  
  protected void afterPeriodicalJob(Job paramJob)
  {
    final Exception localException1 = paramJob.getException();
    if (localException1 != null)
    {
      mExceptionOccured = true;
      try
      {
        SwingUtilities.invokeLater(new Runnable()
        {
          private final Exception val$e;
          
          public void run()
          {
            CommonUse.CommErr(localException1);
          }
        });
      }
      catch (Exception localException2) {}
    }
  }
  
  public void run()
  {
    try
    {
      FutureResult localFutureResult;
      Job localJob;
      for (;;)
      {
        Thread.sleep(mPeriodicalTime);
        if (!mExceptionOccured)
        {
          mPeriodicalJob.run();
          afterPeriodicalJob(mPeriodicalJob);
          if (mJobQueue.getQueueSize() != 0) {
            interrupt();
          }
        }
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      localFutureResult = mJobQueue.getFutureResult();
      localJob = localFutureResult.getJobFromWorker();
      if (localJob.isShutdownThread() == true)
      {
        localJob.run();
        afterJob(localJob);
        localFutureResult.finish();
      }
      else
      {
        if (!mExceptionOccured)
        {
          localJob.run();
          afterJob(localJob);
          localFutureResult.finish();
        }
        else
        {
          localFutureResult.finishCommError();
        }
        if (mJobQueue.getQueueSize() != 0) {
          interrupt();
        }
      }
    }
  }
}
