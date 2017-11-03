package webitc.frame;

public abstract class ThreadAbst
  extends Thread
{
  protected ResultQueue mJobQueue = new ResultQueue();
  
  public ThreadAbst(String paramString)
  {
    super(paramString);
  }
  
  public Result addJob(Job paramJob)
  {
    FutureResult localFutureResult = new FutureResult(paramJob);
    mJobQueue.addFutureResult(localFutureResult);
    return localFutureResult;
  }
  
  protected void afterJob(Job paramJob) {}
  
  public void run()
  {
    for (;;)
    {
      FutureResult localFutureResult = mJobQueue.getFutureResult();
      Job localJob = localFutureResult.getJobFromWorker();
      localJob.run();
      if (localJob.getException() != null) {
        localFutureResult.setException(localJob.getException());
      }
      afterJob(localJob);
      localFutureResult.finish();
      if (localJob.isShutdownThread() == true) {
        break;
      }
    }
  }
  
  public Result shutdownThread()
  {
    return addJob(new JobShutdownThread());
  }
}
