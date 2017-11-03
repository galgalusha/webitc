package webitc.data.point;

import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.enum2.EnumComStat;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumDrvVentMode;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.gui.common.ItcIconInfo;

public class VD3Inner
  extends VPoint
{
  private int mIconAppend = 0;
  private int mIconMode = 0;
  private PntCurrent mInnerCurrent = new PntCurrent();
  private EnumPntStat mOnOffState = EnumPntStat.ELSE;
  private PntTarget mOrgTarget = null;
  private boolean mOrgTargetReady = false;
  private PntTarget mTarget = new PntTarget();
  
  public VD3Inner(PropPntCommon paramPropPntCommon)
  {
    super(paramPropPntCommon);
  }
  
  public synchronized String getDrvModeStr()
  {
    return mInnerCurrent.mDrvMode.getStr();
  }
  
  public synchronized EnumDrvVentMode getDrvVentMode()
  {
    if (getCommonPropfInnerType == EnumInnerType.VENT) {
      return EnumDrvVentMode.getEnum(mInnerCurrent.mVentMode);
    }
    return EnumDrvVentMode.getEnum(mInnerCurrent.mDrvMode);
  }
  
  public synchronized String getDrvVentModeStr()
  {
    if (getCommonPropfInnerType == EnumInnerType.VENT) {
      return mInnerCurrent.mVentMode.getStr();
    }
    return mInnerCurrent.mDrvMode.getStr();
  }
  
  public synchronized int getIconAppend()
  {
    return mIconAppend;
  }
  
  public ItcIconInfo getIconInfo()
  {
    return new ItcIconInfo(getShortName(), getCommonPropfIconID, mIconMode, mIconAppend);
  }
  
  public EnumInnerType getInnerType()
  {
    return getCommonPropfInnerType;
  }
  
  public synchronized PntTarget getOrgPntTarget()
  {
    if (!mOrgTargetReady) {
      throw new FatalException("VD3Inner.getOrgInnerTarget Target info is not ready");
    }
    return mOrgTarget;
  }
  
  public synchronized PntCurrent getPntCurrent()
  {
    return (PntCurrent)mInnerCurrent.clone();
  }
  
  public synchronized PntTarget getPntTarget()
  {
    return mTarget;
  }
  
  public synchronized String getRoomTempStr()
  {
    return mInnerCurrent.mRoomTemp.getStr();
  }
  
  public synchronized String getSetTempStr()
  {
    if ((mInnerCurrent.mDrvMode == EnumDrvMode.COOL) || (mInnerCurrent.mDrvMode == EnumDrvMode.HEAT) || (mInnerCurrent.mDrvMode == EnumDrvMode.AUTOCOOL) || (mInnerCurrent.mDrvMode == EnumDrvMode.AUTOHEAT)) {
      return mInnerCurrent.mSetTemp.getStr();
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
  
  public synchronized PntState getState()
  {
    return new PntState(mCommState, mTargetState, mOnOffState);
  }
  
  public final EnumPntType getType()
  {
    return EnumPntType.D3_INNER;
  }
  
  public static VD3Inner getUnknownPnt()
  {
    return new VD3Inner(new PropPntCommon(ID.ID_UNKNOWN, 0, 0, 0, StrRes.getStr("IDS_COMMON_NAME_UNKNOWN"), StrRes.getStr("IDS_COMMON_NAME_UNKNOWN"), EnumPntType.D3_INNER, EnumInnerType.NORMAL));
  }
  
  public synchronized boolean isOrgTargetReady()
  {
    return mOrgTargetReady;
  }
  
  public synchronized void setIconAppend(int paramInt)
  {
    mIconAppend = paramInt;
  }
  
  public synchronized void setInnerTarget(PntTarget paramPntTarget)
  {
    mTarget = paramPntTarget;
  }
  
  public synchronized void setOrgInnerTarget(PntTarget paramPntTarget)
  {
    mOrgTarget = paramPntTarget;
    mOrgTargetReady = true;
  }
  
  public synchronized void setPntStateInfo(PntState paramPntState, PntCurrent paramPntCurrent)
  {
    mCommState = fCommState;
    mTargetState = fTargetState;
    mOnOffState = fOnOffState;
    mInnerCurrent = paramPntCurrent;
  }
  
  public synchronized void updateStatus(EnumComStat paramEnumComStat, TargetErr paramTargetErr, EnumPntStat paramEnumPntStat, EnumDrvMode paramEnumDrvMode, EnumVentMode paramEnumVentMode, int paramInt1, int paramInt2, EnumVentVol paramEnumVentVol, Temperature paramTemperature1, Temperature paramTemperature2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt5, int paramInt6, int paramInt7, Temperature paramTemperature3, Temperature paramTemperature4, Temperature paramTemperature5, Temperature paramTemperature6, boolean paramBoolean4, boolean paramBoolean5, int paramInt8)
  {
    mCommState = paramEnumComStat;
    mTargetState = paramTargetErr;
    mOnOffState = paramEnumPntStat;
    mInnerCurrent.mDrvMode = paramEnumDrvMode;
    mInnerCurrent.mVentMode = paramEnumVentMode;
    mInnerCurrent.mWindDirect = paramInt1;
    mInnerCurrent.mWindVolume = paramInt2;
    mInnerCurrent.mVentVol = paramEnumVentVol;
    mInnerCurrent.mSetTemp = paramTemperature1;
    mInnerCurrent.mRoomTemp = paramTemperature2;
    mIconMode = paramInt3;
    mIconAppend = paramInt4;
    mTarget = new PntTarget(mTarget, paramBoolean1, paramBoolean2, paramInt5, paramInt6, paramInt7, paramTemperature3, paramTemperature4, paramTemperature5, paramTemperature6, paramBoolean4, paramBoolean5, paramInt8);
    mValidOperation = paramBoolean3;
  }
}
