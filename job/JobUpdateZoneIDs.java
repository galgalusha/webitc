package webitc.job;

import webitc.com.ComAbstract;
import webitc.com.ComGetZonePnt;
import webitc.com.SockHttp;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.PropZone;
import webitc.data.point.VZone;
import webitc.frame.Job;
import webitc.frame.Result;
import webitc.frame.ThreadUpdate;

public class JobUpdateZoneIDs
  extends Job
{
  private boolean mCommErrorOccured = false;
  private boolean mUpdateSynchro;
  private VZone mZone;
  private boolean mZoneRoot = false;
  
  public JobUpdateZoneIDs(ID paramID, boolean paramBoolean)
  {
    mZone = DataMgr.getInstance().getZoneFromID(paramID);
    mUpdateSynchro = paramBoolean;
  }
  
  public JobUpdateZoneIDs(boolean paramBoolean)
  {
    mZoneRoot = true;
    mUpdateSynchro = paramBoolean;
  }
  
  public boolean isCommErrorOccured()
  {
    return mCommErrorOccured;
  }
  
  protected void runPrivate()
    throws Exception
  {
    Object localObject2;
    if (mZoneRoot == true)
    {
      localObject1 = DataMgr.getInstance().getLoginUser();
      if (localObject1 != null)
      {
        localObject2 = ThreadUpdate.interruptedUpdate(((DataUserInfo)localObject1).getZoneIDArray());
        if (mUpdateSynchro == true)
        {
          while (!((Result)localObject2).isFinished()) {
            Thread.sleep(50L);
          }
          if (((Result)localObject2).getException() != null) {
            setException(((Result)localObject2).getException());
          }
          mCommErrorOccured = ((Result)localObject2).isCommErrorOccured();
        }
      }
      return;
    }
    if (!mZone.isPntReady())
    {
      localObject1 = new ComGetZonePnt();
      ((ComGetZonePnt)localObject1).setZoneID(mZone.getProp().fZoneID);
      localObject2 = SockHttp.getInstance();
      ((SockHttp)localObject2).requestReply((ComAbstract)localObject1);
      for (int i = 0; i < ((ComGetZonePnt)localObject1).getPntNum(); i++) {
        mZone.addPntID(((ComGetZonePnt)localObject1).getPntID(i));
      }
      mZone.setPntReady();
    }
    Object localObject1 = ThreadUpdate.interruptedUpdate(mZone.getPntList());
    if (mUpdateSynchro == true)
    {
      while (!((Result)localObject1).isFinished()) {
        Thread.sleep(50L);
      }
      if (((Result)localObject1).getException() != null) {
        setException(((Result)localObject1).getException());
      }
      mCommErrorOccured = ((Result)localObject1).isCommErrorOccured();
    }
  }
}
