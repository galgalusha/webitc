package webitc.data.point;

import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.Temperature;

public class PntSet
{
  public boolean mChangeWindDirect;
  public boolean mChangeWindVolume;
  public boolean mFilterClear;
  public EnumPntStat mOnOffMode;
  public EnumRemcCode mRemoconOnOffMode;
  public EnumPntStat mRemoconRunMode;
  public EnumPntStat mRemoconTempMode;
  public EnumDrvMode mRunMode;
  public Temperature mSetTemp;
  public EnumVentMode mVentMode;
  public EnumVentVol mVentVol;
  public int mWindDirect;
  public int mWindVolume;
  
  public PntSet(EnumPntStat paramEnumPntStat1, EnumDrvMode paramEnumDrvMode, EnumVentMode paramEnumVentMode, EnumVentVol paramEnumVentVol, Temperature paramTemperature, EnumRemcCode paramEnumRemcCode, EnumPntStat paramEnumPntStat2, EnumPntStat paramEnumPntStat3, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, boolean paramBoolean3, int paramInt2)
  {
    mOnOffMode = paramEnumPntStat1;
    mRunMode = paramEnumDrvMode;
    mVentMode = paramEnumVentMode;
    mVentVol = paramEnumVentVol;
    mSetTemp = paramTemperature;
    mRemoconOnOffMode = paramEnumRemcCode;
    mRemoconRunMode = paramEnumPntStat2;
    mRemoconTempMode = paramEnumPntStat3;
    mFilterClear = paramBoolean1;
    mChangeWindDirect = paramBoolean2;
    mWindDirect = paramInt1;
    mChangeWindVolume = paramBoolean3;
    mWindVolume = paramInt2;
  }
  
  public PntSet()
  {
    mOnOffMode = EnumPntStat.ELSE;
    mRunMode = EnumDrvMode.ELSE;
    mVentMode = EnumVentMode.ELSE;
    mVentVol = EnumVentVol.ELSE;
    mSetTemp = new Temperature();
    mRemoconOnOffMode = EnumRemcCode.ELSE;
    mRemoconRunMode = EnumPntStat.ELSE;
    mRemoconTempMode = EnumPntStat.ELSE;
    mFilterClear = false;
    mChangeWindDirect = false;
    mWindDirect = 0;
    mChangeWindVolume = false;
    mWindVolume = -1;
  }
  
  public boolean hasOperation()
  {
    return (mOnOffMode != EnumPntStat.ELSE) || (mRunMode != EnumDrvMode.ELSE) || (mVentMode != EnumVentMode.ELSE) || (mVentVol != EnumVentVol.ELSE) || (mSetTemp.fEnable) || (mRemoconOnOffMode != EnumRemcCode.ELSE) || (mRemoconRunMode != EnumPntStat.ELSE) || (mRemoconTempMode != EnumPntStat.ELSE) || (mFilterClear) || (mChangeWindDirect) || (mChangeWindVolume);
  }
}
