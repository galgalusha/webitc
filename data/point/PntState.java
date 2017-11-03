package webitc.data.point;

import webitc.common.StrRes;
import webitc.common.enum2.EnumComStat;
import webitc.common.enum2.EnumPntStat;

public class PntState
{
  public final EnumComStat fCommState;
  public final EnumPntStat fOnOffState;
  public final TargetErr fTargetState;
  
  public PntState(EnumComStat paramEnumComStat, TargetErr paramTargetErr, EnumPntStat paramEnumPntStat)
  {
    fCommState = paramEnumComStat;
    fTargetState = paramTargetErr;
    fOnOffState = paramEnumPntStat;
  }
  
  public PntState(EnumComStat paramEnumComStat, TargetErr paramTargetErr)
  {
    fCommState = paramEnumComStat;
    fTargetState = paramTargetErr;
    fOnOffState = EnumPntStat.ELSE;
  }
  
  public String getStr()
  {
    if (fCommState == EnumComStat.ERROR) {
      return StrRes.getStr("IDS_COMMON_COMM_ERR");
    }
    if (!fTargetState.isNormal()) {
      return new String(StrRes.getStr("IDS_COMMON_ERR_LEVEL_5") + "(" + fTargetState.getErrCodeStr() + ")");
    }
    if (fOnOffState == EnumPntStat.ON) {
      return StrRes.getStr("IDS_COMMON_START");
    }
    if (fOnOffState == EnumPntStat.OFF) {
      return StrRes.getStr("IDS_COMMON_STOP");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
