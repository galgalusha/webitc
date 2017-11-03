package webitc.job;

import webitc.com.ComGetPntOrgTarget;
import webitc.com.SockHttp;
import webitc.common.enum2.EnumPntType;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.PntTarget;
import webitc.data.point.VAbst;
import webitc.data.point.VD3Inner;
import webitc.data.point.VZone;
import webitc.frame.Job;

public class JobUpdatePntOrgTarget
  extends Job
{
  private ID mID;
  
  public JobUpdatePntOrgTarget(ID paramID)
  {
    mID = paramID;
  }
  
  protected void runPrivate()
    throws Exception
  {
    DataMgr localDataMgr = DataMgr.getInstance();
    VAbst localVAbst = localDataMgr.getPntAbst(mID);
    SockHttp localSockHttp = SockHttp.getInstance();
    Object localObject;
    ComGetPntOrgTarget localComGetPntOrgTarget;
    PntTarget localPntTarget;
    if (localVAbst.getType() == EnumPntType.ZONE)
    {
      localObject = (VZone)localVAbst;
      if (!((VZone)localObject).isOrgTargetReady())
      {
        localComGetPntOrgTarget = new ComGetPntOrgTarget();
        localComGetPntOrgTarget.setID(mID);
        localSockHttp.requestReply(localComGetPntOrgTarget);
        localPntTarget = localComGetPntOrgTarget.getInnerTarget();
        ((VZone)localObject).setOrgInnerTarget(localPntTarget);
      }
    }
    else if (localVAbst.getType() == EnumPntType.D3_INNER)
    {
      localObject = (VD3Inner)localVAbst;
      if (!((VD3Inner)localObject).isOrgTargetReady())
      {
        localComGetPntOrgTarget = new ComGetPntOrgTarget();
        localComGetPntOrgTarget.setID(mID);
        localSockHttp.requestReply(localComGetPntOrgTarget);
        localPntTarget = localComGetPntOrgTarget.getInnerTarget();
        ((VD3Inner)localObject).setOrgInnerTarget(localPntTarget);
      }
    }
  }
}
