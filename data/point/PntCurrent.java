package webitc.data.point;

import webitc.common.StrRes;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.Temperature;

public class PntCurrent
  implements Cloneable
{
  public int mDefrostMode;
  public EnumDrvMode mDrvMode;
  public int mFilterSign;
  public EnumRemcCode mRemoconOnOffMode;
  public EnumPntStat mRemoconRunMode;
  public EnumPntStat mRemoconTempMode;
  public Temperature mRoomTemp;
  public Temperature mSetTemp;
  public int mThermoMode;
  public int mThermoOff;
  public EnumVentMode mVentMode;
  public EnumVentVol mVentVol;
  public int mWindDirect;
  public int mWindVolume;
  
  public PntCurrent(EnumDrvMode paramEnumDrvMode, EnumVentMode paramEnumVentMode, EnumVentVol paramEnumVentVol, int paramInt1, Temperature paramTemperature1, Temperature paramTemperature2, EnumRemcCode paramEnumRemcCode, EnumPntStat paramEnumPntStat1, EnumPntStat paramEnumPntStat2, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    mDrvMode = paramEnumDrvMode;
    mVentMode = paramEnumVentMode;
    mVentVol = paramEnumVentVol;
    mDefrostMode = paramInt1;
    mSetTemp = paramTemperature1;
    mRoomTemp = paramTemperature2;
    mRemoconOnOffMode = paramEnumRemcCode;
    mRemoconRunMode = paramEnumPntStat1;
    mRemoconTempMode = paramEnumPntStat2;
    mFilterSign = paramInt2;
    mThermoOff = paramInt3;
    mThermoMode = paramInt4;
    mWindDirect = paramInt5;
    mWindVolume = paramInt6;
  }
  
  public PntCurrent()
  {
    mDrvMode = EnumDrvMode.ELSE;
    mVentMode = EnumVentMode.ELSE;
    mVentVol = EnumVentVol.ELSE;
    mDefrostMode = 0;
    mSetTemp = new Temperature();
    mRoomTemp = new Temperature();
    mRemoconOnOffMode = EnumRemcCode.ELSE;
    mRemoconRunMode = EnumPntStat.ELSE;
    mRemoconTempMode = EnumPntStat.ELSE;
    mFilterSign = 0;
    mThermoOff = 0;
    mThermoMode = 0;
    mWindDirect = -1;
    mWindVolume = -1;
  }
  
  public Object clone()
  {
    PntCurrent localPntCurrent = new PntCurrent();
    mDrvMode = mDrvMode;
    mVentMode = mVentMode;
    mVentVol = mVentVol;
    mDefrostMode = mDefrostMode;
    mSetTemp = ((Temperature)mSetTemp.clone());
    mRoomTemp = ((Temperature)mRoomTemp.clone());
    mRemoconOnOffMode = mRemoconOnOffMode;
    mRemoconRunMode = mRemoconRunMode;
    mRemoconTempMode = mRemoconTempMode;
    mFilterSign = mFilterSign;
    mThermoOff = mThermoOff;
    mThermoMode = mThermoMode;
    mWindDirect = mWindDirect;
    mWindVolume = mWindVolume;
    return localPntCurrent;
  }
  
  public String getSetTempStr()
  {
    if ((mDrvMode == EnumDrvMode.COOL) || (mDrvMode == EnumDrvMode.HEAT) || (mDrvMode == EnumDrvMode.AUTOCOOL) || (mDrvMode == EnumDrvMode.AUTOHEAT)) {
      return mSetTemp.getStr();
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
