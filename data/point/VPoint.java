package webitc.data.point;

import webitc.common.StrRes;
import webitc.data.ID;

public abstract class VPoint
  extends VAbst
{
  private PropPntCommon mCommonProp;
  
  public VPoint(PropPntCommon paramPropPntCommon)
  {
    mCommonProp = paramPropPntCommon;
  }
  
  public synchronized PropPntCommon getCommonProp()
  {
    return mCommonProp;
  }
  
  public String getDetailName()
  {
    return mCommonProp.fDetailName;
  }
  
  public synchronized String getDrvModeStr()
  {
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
  
  public synchronized String getDrvVentModeStr()
  {
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
  
  public ID getID()
  {
    return mCommonProp.fPntID;
  }
  
  public synchronized String getRoomTempStr()
  {
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
  
  public synchronized String getSetTempStr()
  {
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
  
  public synchronized String getShortName()
  {
    return mCommonProp.fShortName;
  }
  
  public synchronized PntState getState()
  {
    return new PntState(mCommState, mTargetState);
  }
}
