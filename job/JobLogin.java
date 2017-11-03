package webitc.job;

import webitc.com.ComGetPntProp;
import webitc.com.ComGetZoneProp;
import webitc.com.ComSetLogin;
import webitc.com.SockHttp;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntType;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.PropPntCommon;
import webitc.data.point.PropZone;
import webitc.frame.Job;

public class JobLogin
  extends Job
{
  private String mPassword = null;
  private int mResult = -1;
  private String mUserName = null;
  
  public JobLogin(String paramString1, String paramString2)
  {
    mUserName = paramString1;
    mPassword = paramString2;
  }
  
  public int getAuthResult()
  {
    return mResult;
  }
  
  protected void runPrivate()
    throws Exception
  {
    ComSetLogin localComSetLogin = new ComSetLogin();
    localComSetLogin.setName(mUserName);
    localComSetLogin.setPassword(mPassword);
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(localComSetLogin);
    if (localComSetLogin.getReturn() != 1)
    {
      mResult = localComSetLogin.getReturn();
      return;
    }
    DataUserInfo localDataUserInfo = new DataUserInfo(localComSetLogin.getUserID(), mUserName, mPassword);
    DataMgr localDataMgr = DataMgr.getInstance();
    localDataMgr.setMiddleVer(localComSetLogin.getMiddleVer());
    ComGetZoneProp localComGetZoneProp = new ComGetZoneProp();
    localSockHttp.requestReply(localComGetZoneProp);
    for (int i = 0; i < localComGetZoneProp.getZoneNum(); i++)
    {
      PropZone localPropZone = localComGetZoneProp.getZoneProp(i);
      localDataMgr.addZone(localPropZone);
    }
    for (int j = 0; j < localComSetLogin.getZoneNum(); j++)
    {
      int k = localComSetLogin.getZoneID(j);
      ID localID = new ID(1, k);
      if (localDataMgr.isExist(localID) == true) {
        localDataUserInfo.addZoneID(k);
      }
    }
    localDataMgr.setLoginUser(localDataUserInfo);
    ComGetPntProp localComGetPntProp = new ComGetPntProp();
    localSockHttp.requestReply(localComGetPntProp);
    for (int m = 0; m < localComGetPntProp.getPntNum(); m++)
    {
      EnumPntType localEnumPntType = localComGetPntProp.getPntType(m);
      EnumInnerType localEnumInnerType = localComGetPntProp.getInnerType(m);
      PropPntCommon localPropPntCommon = localComGetPntProp.getProp(m);
      localDataMgr.addPnt(localEnumPntType, localEnumInnerType, localPropPntCommon);
    }
    mResult = 1;
  }
}
