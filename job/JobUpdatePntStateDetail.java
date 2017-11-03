package webitc.job;

import webitc.com.ComGetPntStateDetail;
import webitc.com.SockHttp;
import webitc.common.enum2.EnumPntType;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.VAbst;
import webitc.data.point.VD3Inner;
import webitc.data.point.VZone;
import webitc.frame.Job;

public class JobUpdatePntStateDetail
  extends Job
{
  private ID mID;
  
  public JobUpdatePntStateDetail(ID paramID)
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
    ComGetPntStateDetail localComGetPntStateDetail;
    if (localVAbst.getType() == EnumPntType.ZONE)
    {
      localObject = (VZone)localVAbst;
      localComGetPntStateDetail = new ComGetPntStateDetail();
      localComGetPntStateDetail.setID(mID);
      localSockHttp.requestReply(localComGetPntStateDetail);
      ((VZone)localObject).setPntStateInfo(localComGetPntStateDetail.getPntState(), localComGetPntStateDetail.getInnerCurrent());
    }
    else if (localVAbst.getType() == EnumPntType.D3_INNER)
    {
      localObject = (VD3Inner)localVAbst;
      localComGetPntStateDetail = new ComGetPntStateDetail();
      localComGetPntStateDetail.setID(mID);
      localSockHttp.requestReply(localComGetPntStateDetail);
      ((VD3Inner)localObject).setPntStateInfo(localComGetPntStateDetail.getPntState(), localComGetPntStateDetail.getInnerCurrent());
    }
  }
}
