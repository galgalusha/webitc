package webitc.job;

import webitc.com.ComAbstract;
import webitc.com.ComGetPntOrgTarget;
import webitc.com.ComGetPntStateDetail;
import webitc.com.SockHttp;
import webitc.common.enum2.EnumPntType;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.PntTarget;
import webitc.data.point.VAbst;
import webitc.data.point.VD3Inner;
import webitc.data.point.VZone;
import webitc.frame.Job;

public class JobUpdatePntOrgTargetState
  extends Job
{
  private ID mID;
  
  public JobUpdatePntOrgTargetState(ID paramID)
  {
    mID = paramID;
  }
  
  protected void runPrivate()
    throws Exception
  {
    DataMgr localDataMgr = DataMgr.getInstance();
    VAbst localVAbst = localDataMgr.getPntAbst(mID);
    SockHttp localSockHttp = SockHttp.getInstance();
    Object localObject1;
    Object localObject2;
    PntTarget localPntTarget;
    if (localVAbst.getType() == EnumPntType.ZONE)
    {
      localObject1 = (VZone)localVAbst;
      if (!((VZone)localObject1).isOrgTargetReady())
      {
        localObject2 = new ComGetPntOrgTarget();
        ((ComGetPntOrgTarget)localObject2).setID(mID);
        localSockHttp.requestReply((ComAbstract)localObject2);
        localPntTarget = ((ComGetPntOrgTarget)localObject2).getInnerTarget();
        ((VZone)localObject1).setInnerTarget(localPntTarget);
      }
      localObject2 = new ComGetPntStateDetail();
      ((ComGetPntStateDetail)localObject2).setID(mID);
      localSockHttp.requestReply((ComAbstract)localObject2);
      ((VZone)localObject1).setPntStateInfo(((ComGetPntStateDetail)localObject2).getPntState(), ((ComGetPntStateDetail)localObject2).getInnerCurrent());
    }
    else if (localVAbst.getType() == EnumPntType.D3_INNER)
    {
      localObject1 = (VD3Inner)localVAbst;
      if (!((VD3Inner)localObject1).isOrgTargetReady())
      {
        localObject2 = new ComGetPntOrgTarget();
        ((ComGetPntOrgTarget)localObject2).setID(mID);
        localSockHttp.requestReply((ComAbstract)localObject2);
        localPntTarget = ((ComGetPntOrgTarget)localObject2).getInnerTarget();
        ((VD3Inner)localObject1).setOrgInnerTarget(localPntTarget);
      }
      localObject2 = new ComGetPntStateDetail();
      ((ComGetPntStateDetail)localObject2).setID(mID);
      localSockHttp.requestReply((ComAbstract)localObject2);
      ((VD3Inner)localObject1).setPntStateInfo(((ComGetPntStateDetail)localObject2).getPntState(), ((ComGetPntStateDetail)localObject2).getInnerCurrent());
    }
  }
}
