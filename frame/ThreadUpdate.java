package webitc.frame;

import java.util.ArrayList;
import javax.swing.SwingUtilities;
import webitc.common.FatalException;
import webitc.common.ListenerPntUpdate;
import webitc.common.UUID;
import webitc.data.DataMgr;
import webitc.gui.common.CommonUse;
import webitc.job.JobUpdatePntState;

public class ThreadUpdate
  extends ThreadTimer
{
  private static final int EXCEPTIONAL_COUNT = 3;
  private static final int EXCEPTIONAL_TIME = 5000;
  private static final int UPDATE_TIME = 20000;
  private static int mExceptionalCount = 0;
  private ArrayList mIDList;
  private static ThreadUpdate mObj;
  private final UUID mUUID;
  private ArrayList mUpdateListenerList = new ArrayList();
  private final int mUserID;
  
  private ThreadUpdate(Job paramJob, int paramInt, UUID paramUUID)
  {
    super(paramJob, 20000);
    mUserID = paramInt;
    mUUID = paramUUID;
  }
  
  public void addUpdateListener(ListenerPntUpdate paramListenerPntUpdate)
  {
    mUpdateListenerList.add(paramListenerPntUpdate);
  }
  
  protected void afterJob(Job paramJob)
  {
    super.afterJob(paramJob);
    if (paramJob.isShutdownThread() == true) {
      return;
    }
    JobUpdatePntState localJobUpdatePntState1 = (JobUpdatePntState)paramJob;
    JobUpdatePntState localJobUpdatePntState2 = (JobUpdatePntState)mPeriodicalJob;
    localJobUpdatePntState2.setIDList(localJobUpdatePntState1.getIDList());
    try
    {
      SwingUtilities.invokeLater(new NotifyGUI(localJobUpdatePntState1.getAuthResult(), true));
    }
    catch (Exception localException)
    {
      CommonUse.AppErr(localException, "ThreadUpdate.afterJob");
    }
  }
  
  protected void afterPeriodicalJob(Job paramJob)
  {
    super.afterPeriodicalJob(paramJob);
    if (paramJob.getException() != null) {
      return;
    }
    JobUpdatePntState localJobUpdatePntState = (JobUpdatePntState)paramJob;
    try
    {
      if (mExceptionalCount < 3)
      {
        mExceptionalCount += 1;
        SwingUtilities.invokeLater(new NotifyGUI(localJobUpdatePntState.getAuthResult(), true));
      }
      else if (mExceptionalCount == 3)
      {
        mExceptionalCount += 1;
        SwingUtilities.invokeLater(new NotifyGUI(localJobUpdatePntState.getAuthResult(), false));
      }
      else
      {
        SwingUtilities.invokeLater(new NotifyGUI(localJobUpdatePntState.getAuthResult(), false));
      }
      if (mExceptionalCount > 3) {
        mPeriodicalTime = 20000;
      }
    }
    catch (Exception localException)
    {
      CommonUse.AppErr(localException, "ThreadUpdate.afterPeriodicalJob");
    }
  }
  
  public static ThreadUpdate createInstance(int paramInt, UUID paramUUID)
  {
    JobUpdatePntState localJobUpdatePntState = new JobUpdatePntState(paramInt, paramUUID);
    DataMgr localDataMgr = DataMgr.getInstance();
    localJobUpdatePntState.setIDList(localDataMgr.getZoneIDList());
    mObj = new ThreadUpdate(localJobUpdatePntState, paramInt, paramUUID);
    return mObj;
  }
  
  public static void deleteInstance()
  {
    mObj = null;
  }
  
  public static ThreadUpdate getInstance()
  {
    return mObj;
  }
  
  public ListenerPntUpdate getUpdateListener(int paramInt)
  {
    return (ListenerPntUpdate)mUpdateListenerList.get(paramInt);
  }
  
  public int getUpdateListenerNum()
  {
    return mUpdateListenerList.size();
  }
  
  public static Result interruptedUpdate(ArrayList paramArrayList)
  {
    JobUpdatePntState localJobUpdatePntState = new JobUpdatePntState(mObjmUserID, mObjmUUID);
    mObjmIDList = paramArrayList;
    localJobUpdatePntState.setIDList(mObjmIDList);
    return mObj.addJob(localJobUpdatePntState);
  }
  
  public static Result interruptedUpdate()
  {
    mObjmPeriodicalTime = 5000;
    mExceptionalCount = 0;
    JobUpdatePntState localJobUpdatePntState = new JobUpdatePntState(mObjmUserID, mObjmUUID);
    localJobUpdatePntState.setIDList(mObjmIDList);
    return mObj.addJob(localJobUpdatePntState);
  }
  
  class NotifyGUI
    implements Runnable
  {
    private final boolean mExceptionalUpdate;
    private final int mResult;
    
    public NotifyGUI(int paramInt, boolean paramBoolean)
    {
      mResult = paramInt;
      mExceptionalUpdate = paramBoolean;
    }
    
    public void run()
    {
      switch (mResult)
      {
      case 1: 
        int i;
        if (mExceptionalUpdate == true) {
          for (i = 0; i < getUpdateListenerNum(); i++) {
            getUpdateListener(i).exceptionalUpdated();
          }
        } else {
          for (i = 0; i < getUpdateListenerNum(); i++) {
            getUpdateListener(i).statusUpdated();
          }
        }
        break;
      case -10: 
        CommonUse.PingErr(mResult);
        break;
      case -11: 
        CommonUse.PingErr(mResult);
        break;
      case -20: 
        CommonUse.PingErr(mResult);
        break;
      default: 
        throw new FatalException("ThreadUpdate.NotifyGUI illegal auth state");
      }
    }
  }
}
