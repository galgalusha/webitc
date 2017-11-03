package webitc.job;

import java.util.ArrayList;
import webitc.com.ComGetPntState;
import webitc.com.SockHttp;
import webitc.common.UUID;
import webitc.data.DataMgr;
import webitc.data.point.Port;
import webitc.frame.Job;

public class JobUpdatePntState
  extends Job
{
  private ArrayList mIDList = new ArrayList();
  private int mResult = -10;
  private final UUID mUUID;
  private final int mUserID;
  
  public JobUpdatePntState(int paramInt, UUID paramUUID)
  {
    mUserID = paramInt;
    mUUID = paramUUID;
  }
  
  public int getAuthResult()
  {
    return mResult;
  }
  
  public ArrayList getIDList()
  {
    return mIDList;
  }
  
  protected void runPrivate()
    throws Exception
  {
    ComGetPntState localComGetPntState = new ComGetPntState();
    localComGetPntState.setUserID(mUserID);
    localComGetPntState.setUUID(mUUID);
    localComGetPntState.setIDArray(mIDList);
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(localComGetPntState);
    if (localComGetPntState.getReturn() != 1)
    {
      mResult = localComGetPntState.getReturn();
      return;
    }
    DataMgr localDataMgr = DataMgr.getInstance();
    if (localDataMgr.getMiddleVer() != localComGetPntState.getMiddleVer())
    {
      mResult = -20;
      return;
    }
    Port localPort = new Port(localComGetPntState.isControlCenter(0), localComGetPntState.isForceStop(0), localComGetPntState.isM8(0), localComGetPntState.isComError(0), localComGetPntState.isControlCenter(1), localComGetPntState.isForceStop(1), localComGetPntState.isM8(1), localComGetPntState.isComError(1), localComGetPntState.isAllValidON(), localComGetPntState.isAllValidOFF());
    localDataMgr.setPort(localPort);
    int i = localComGetPntState.getPntNum();
    for (int j = 0; j < i; j++) {
      if (localComGetPntState.getPntEnable(j) == true) {
        localDataMgr.updateStatus(localComGetPntState.getPntID(j), localComGetPntState.getCommState(j), localComGetPntState.getTargetErr(j), localComGetPntState.getOnOffState(j), localComGetPntState.getDrvMode(j), localComGetPntState.getVentMode(j), localComGetPntState.getWindDirect(j), localComGetPntState.getWindVolume(j), localComGetPntState.getVentVolume(j), localComGetPntState.getSetTemp(j), localComGetPntState.getRoomTemp(j), localComGetPntState.getIconMode(j), localComGetPntState.getIconAppend(j), localComGetPntState.isValidON(j), localComGetPntState.isValidOFF(j), localComGetPntState.isValidOperation(j), localComGetPntState.getValidRunMode(j), localComGetPntState.getValidVentMode(j), localComGetPntState.getValidVentVol(j), localComGetPntState.getTempCoolUpper(j), localComGetPntState.getTempCoolLower(j), localComGetPntState.getTempWarmUpper(j), localComGetPntState.getTempWarmLower(j), localComGetPntState.isValidSetTemp(j), localComGetPntState.isValidWindDirect(j), localComGetPntState.getValidWindVolume(j));
      }
    }
    mResult = 1;
  }
  
  public void setIDList(ArrayList paramArrayList)
  {
    mIDList = paramArrayList;
  }
}
