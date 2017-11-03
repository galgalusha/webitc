package webitc.data.schedule;

import webitc.common.StrRes;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.ID;
import webitc.data.Temperature;

public class DefaultAction
  extends SchAbstAction
  implements Cloneable
{
  public EnumDrvMode mDrvMode = EnumDrvMode.ELSE;
  public EnumPntStat mOnOff = EnumPntStat.ELSE;
  public EnumRemcCode mRemcCode = EnumRemcCode.ELSE;
  public EnumPntStat mRemcDrvMode = EnumPntStat.ELSE;
  public EnumPntStat mRemcSetPoint = EnumPntStat.ELSE;
  public Temperature mSetPoint = new Temperature();
  public ID mTargetID = ID.ID_UNREG;
  public EnumVentMode mVentMode = EnumVentMode.ELSE;
  public EnumVentVol mVentVol = EnumVentVol.ELSE;
  
  public DefaultAction() {}
  
  protected Object clone()
  {
    DefaultAction localDefaultAction = new DefaultAction();
    localDefaultAction.setTargetID(getTargetID());
    localDefaultAction.setOnOff(getOnOff());
    localDefaultAction.setDrvMode(getDrvMode());
    localDefaultAction.setVentMode(getVentMode());
    localDefaultAction.setVentVol(getVentVol());
    localDefaultAction.setSetPoint(getSetPoint());
    localDefaultAction.setRemcCode(getRemcCode());
    localDefaultAction.setRemcDrvMode(getRemcDrvMode());
    localDefaultAction.setRemcSetPoint(getRemcSetPoint());
    return localDefaultAction;
  }
  
  public EnumDrvMode getDrvMode()
  {
    return mDrvMode;
  }
  
  public String getDrvModeStr()
  {
    if (mDrvMode == EnumDrvMode.FAN) {
      return StrRes.getStr("IDS_COMMON_MODE_FAN");
    }
    if (mDrvMode == EnumDrvMode.HEAT) {
      return StrRes.getStr("IDS_COMMON_MODE_HEAT");
    }
    if (mDrvMode == EnumDrvMode.COOL) {
      return StrRes.getStr("IDS_COMMON_MODE_COOL");
    }
    if (mDrvMode == EnumDrvMode.SUBMIT) {
      return StrRes.getStr("IDS_COMMON_MODE_SETPOINT");
    }
    if (mDrvMode == EnumDrvMode.AUTOCOOL) {
      return StrRes.getStr("IDS_COMMON_MODE_AUTO");
    }
    return "";
  }
  
  public EnumPntStat getOnOff()
  {
    return mOnOff;
  }
  
  public String getOnOffStr()
  {
    if (mOnOff == EnumPntStat.ON) {
      return StrRes.getStr("IDS_COMMON_START");
    }
    if (mOnOff == EnumPntStat.OFF) {
      return StrRes.getStr("IDS_COMMON_STOP");
    }
    return "";
  }
  
  public String getRKKDrvModeStr()
  {
    if (mRemcDrvMode == EnumPntStat.ON) {
      return StrRes.getStr("IDS_COMMON_RC_PERMIT");
    }
    if (mRemcDrvMode == EnumPntStat.OFF) {
      return StrRes.getStr("IDS_COMMON_RC_PROHIBIT");
    }
    return "";
  }
  
  public String getRKKOnOffStr()
  {
    if (mRemcCode == EnumRemcCode.ENABLE) {
      return StrRes.getStr("IDS_COMMON_RC_PERMIT");
    }
    if (mRemcCode == EnumRemcCode.STOP) {
      return StrRes.getStr("IDS_COMMON_RC_ONOFF_STOPONLY");
    }
    if (mRemcCode == EnumRemcCode.DISABLE) {
      return StrRes.getStr("IDS_COMMON_RC_PROHIBIT");
    }
    return "";
  }
  
  public String getRKKSetTempStr()
  {
    if (mRemcSetPoint == EnumPntStat.ON) {
      return StrRes.getStr("IDS_COMMON_RC_PERMIT");
    }
    if (mRemcSetPoint == EnumPntStat.OFF) {
      return StrRes.getStr("IDS_COMMON_RC_PROHIBIT");
    }
    return "";
  }
  
  public EnumRemcCode getRemcCode()
  {
    return mRemcCode;
  }
  
  public EnumPntStat getRemcDrvMode()
  {
    return mRemcDrvMode;
  }
  
  public EnumPntStat getRemcSetPoint()
  {
    return mRemcSetPoint;
  }
  
  public Temperature getSetPoint()
  {
    return mSetPoint;
  }
  
  public String getSetPointStr()
  {
    if (mSetPoint.fEnable == true) {
      return mSetPoint.getStr();
    }
    return "";
  }
  
  public ID getTargetID()
  {
    return mTargetID;
  }
  
  public int getType()
  {
    return 1;
  }
  
  public EnumVentMode getVentMode()
  {
    return mVentMode;
  }
  
  public String getVentModeStr()
  {
    if (mVentMode == EnumVentMode.AUTO) {
      return StrRes.getStr("IDS_COMMON_VENT_MODE_AUTO");
    }
    if (mVentMode == EnumVentMode.VENTILATION) {
      return StrRes.getStr("IDS_COMMON_VENT_MODE_VENT");
    }
    if (mVentMode == EnumVentMode.NORMAL) {
      return StrRes.getStr("IDS_COMMON_VENT_MODE_NORMAL");
    }
    return "";
  }
  
  public EnumVentVol getVentVol()
  {
    return mVentVol;
  }
  
  public String getVentVolStr()
  {
    if (mVentVol == EnumVentVol.NORMAL_AUTO) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_AUTO");
    }
    if (mVentVol == EnumVentVol.NORMAL_LOW) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_LOW");
    }
    if (mVentVol == EnumVentVol.NORMAL_HIGH) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_HIGH");
    }
    if (mVentVol == EnumVentVol.FRESH_AUTO) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_AUTO");
    }
    if (mVentVol == EnumVentVol.FRESH_LOW) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_LOW");
    }
    if (mVentVol == EnumVentVol.FRESH_HIGH) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_HIGH");
    }
    return "";
  }
  
  public void setDrvMode(EnumDrvMode paramEnumDrvMode)
  {
    mDrvMode = paramEnumDrvMode;
  }
  
  public void setOnOff(EnumPntStat paramEnumPntStat)
  {
    mOnOff = paramEnumPntStat;
  }
  
  public void setRemcCode(EnumRemcCode paramEnumRemcCode)
  {
    mRemcCode = paramEnumRemcCode;
  }
  
  public void setRemcDrvMode(EnumPntStat paramEnumPntStat)
  {
    mRemcDrvMode = paramEnumPntStat;
  }
  
  public void setRemcSetPoint(EnumPntStat paramEnumPntStat)
  {
    mRemcSetPoint = paramEnumPntStat;
  }
  
  public void setSetPoint(Temperature paramTemperature)
  {
    mSetPoint = paramTemperature;
  }
  
  public void setTargetID(ID paramID)
  {
    mTargetID = paramID;
  }
  
  public void setVentMode(EnumVentMode paramEnumVentMode)
  {
    mVentMode = paramEnumVentMode;
  }
  
  public void setVentVol(EnumVentVol paramEnumVentVol)
  {
    mVentVol = paramEnumVentVol;
  }
}
