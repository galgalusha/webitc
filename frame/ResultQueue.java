package webitc.frame;

import java.util.LinkedList;

public class ResultQueue
{
  private final LinkedList mQueue = new LinkedList();
  
  public ResultQueue() {}
  
  public synchronized void addFutureResult(FutureResult paramFutureResult)
  {
    mQueue.addLast(paramFutureResult);
    notifyAll();
  }
  
  public synchronized FutureResult getFutureResult()
  {
    while (getQueueSize() <= 0) {
      try
      {
        wait();
      }
      catch (InterruptedException localInterruptedException) {}
    }
    return (FutureResult)mQueue.removeFirst();
  }
  
  public synchronized int getQueueSize()
  {
    return mQueue.size();
  }
}
