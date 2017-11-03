package webitc.gui.simple;

import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumDrvVentMode;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntState;
import webitc.data.point.PropPntCommon;
import webitc.data.point.VPoint;
import webitc.gui.common.ItcIconInfo;

public class SimpleCommon
{
  public static final int DRV_AUTO = 8200;
  public static final int DRV_COOL = 8194;
  public static final int DRV_ELSE = -1;
  public static final int DRV_FAN = 8196;
  public static final int DRV_HEAT = 8193;
  public static final int DRV_OFF = 4096;
  public static final int DRV_ON = 8192;
  public static final int DRV_SUBMIT = 8208;
  public static final int DRV_VENT = 8224;
  public static final int FAN_DIR_1 = 0;
  public static final int FAN_DIR_2 = 1;
  public static final int FAN_DIR_3 = 2;
  public static final int FAN_DIR_4 = 3;
  public static final int FAN_DIR_5 = 4;
  public static final int FAN_SWING = 5;
  public static final int PNT_AC = 1;
  public static final int PNT_ELSE = 5;
  public static final int PNT_LIGHT = 3;
  public static final int PNT_STATE_ACERR = 2;
  public static final int PNT_STATE_COMERR = 1;
  public static final int PNT_STATE_VALID = 0;
  public static final int PNT_SWITCH = 4;
  public static final int PNT_VENT = 2;
  public static final int PNT_ZONE = 0;
  public static final int VENT_AUTO = 8448;
  public static final int VENT_ELSE = -1;
  public static final int VENT_NORMAL = 9216;
  public static final int VENT_TOTAL = 8704;
  public static final int VOL_AUTO = 2;
  public static final int VOL_HIGH = 1;
  public static final int VOL_LOW = 0;
  
  public SimpleCommon() {}
  
  public static int getDrvMode(EnumDrvMode paramEnumDrvMode)
  {
    if ((paramEnumDrvMode == EnumDrvMode.COOL) || (paramEnumDrvMode == EnumDrvMode.DRY)) {
      return 8194;
    }
    if (paramEnumDrvMode == EnumDrvMode.HEAT) {
      return 8193;
    }
    if (paramEnumDrvMode == EnumDrvMode.FAN) {
      return 8196;
    }
    if ((paramEnumDrvMode == EnumDrvMode.AUTOCOOL) || (paramEnumDrvMode == EnumDrvMode.AUTOHEAT)) {
      return 8200;
    }
    if (paramEnumDrvMode == EnumDrvMode.SUBMIT) {
      return 8208;
    }
    if (paramEnumDrvMode == EnumDrvMode.VENT) {
      return 8224;
    }
    return -1;
  }
  
  public static int getDrvMode(ID paramID)
  {
    PntState localPntState = DataMgr.getInstance().getState(paramID);
    if (getOnOff(fOnOffState) == 4096) {
      return 4096;
    }
    EnumDrvMode localEnumDrvMode = getInstancegetPntCurrentmDrvMode;
    return getDrvMode(localEnumDrvMode);
  }
  
  public static int getDrvVentMode(EnumDrvVentMode paramEnumDrvVentMode)
  {
    if ((paramEnumDrvVentMode == EnumDrvVentMode.COOL) || (paramEnumDrvVentMode == EnumDrvVentMode.DRY)) {
      return 8194;
    }
    if (paramEnumDrvVentMode == EnumDrvVentMode.HEAT) {
      return 8193;
    }
    if (paramEnumDrvVentMode == EnumDrvVentMode.FAN) {
      return 8196;
    }
    if ((paramEnumDrvVentMode == EnumDrvVentMode.AUTOCOOL) || (paramEnumDrvVentMode == EnumDrvVentMode.AUTOHEAT)) {
      return 8200;
    }
    if (paramEnumDrvVentMode == EnumDrvVentMode.SUBMIT) {
      return 8208;
    }
    if (paramEnumDrvVentMode == EnumDrvVentMode.AUTO) {
      return 8448;
    }
    if (paramEnumDrvVentMode == EnumDrvVentMode.VENTILATION) {
      return 8704;
    }
    if (paramEnumDrvVentMode == EnumDrvVentMode.NORMAL) {
      return 9216;
    }
    if (paramEnumDrvVentMode == EnumDrvVentMode.VENT) {
      return 8224;
    }
    return -1;
  }
  
  public static int getFanDir(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
      return paramInt;
    case 5: 
    case 6: 
      return 4;
    case 7: 
      return 5;
    }
    return 4;
  }
  
  private static int getIconType(ID paramID)
  {
    int i = getInstancegetPntFromIDgetCommonPropfIconID;
    if (i == 14) {
      return 3;
    }
    return 4;
  }
  
  public static int getOnOff(EnumPntStat paramEnumPntStat)
  {
    if (paramEnumPntStat == EnumPntStat.ON) {
      return 8192;
    }
    return 4096;
  }
  
  public static int getPntType(ID paramID)
  {
    if (fType == 1) {
      return 0;
    }
    VPoint localVPoint = DataMgr.getInstance().getPntFromID(paramID);
    EnumPntType localEnumPntType = getCommonPropfPntType;
    EnumInnerType localEnumInnerType = getCommonPropfInnerType;
    if (localEnumPntType == EnumPntType.ZONE) {
      return 0;
    }
    if (localEnumPntType == EnumPntType.D3_INNER)
    {
      if (localEnumInnerType == EnumInnerType.VENT) {
        return 2;
      }
      return 1;
    }
    int i = getIconType(paramID);
    if (i == 5) {
      return 4;
    }
    return i;
  }
  
  public static int getRoomTemp(ID paramID)
  {
    float f = getInstancegetPntCurrentmRoomTemp.fTemp;
    if (SystemInfo.isCentigrade() == true) {
      return (int)f;
    }
    return Temperature.convertCtoF(f);
  }
  
  public static String getRoomTempStr(ID paramID)
  {
    Temperature localTemperature = getInstancegetPntCurrentmRoomTemp;
    boolean bool = fEnable;
    if (!bool) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    float f = fTemp;
    if (SystemInfo.isCentigrade() == true) {
      return String.valueOf((int)f) + StrRes.getStr("IDS_COMMON_UNIT_TEMP");
    }
    return String.valueOf(Temperature.convertCtoF(f) + StrRes.getStr("IDS_COMMON_UNIT_TEMP_F"));
  }
  
  public static String getRoomTempStrNoUnit(ID paramID)
  {
    Temperature localTemperature = getInstancegetPntCurrentmRoomTemp;
    boolean bool = fEnable;
    if (!bool) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    float f = fTemp;
    if (SystemInfo.isCentigrade() == true) {
      return String.valueOf((int)f);
    }
    return String.valueOf(Temperature.convertCtoF(f));
  }
  
  public static int getSetTemp(ID paramID)
  {
    float f = getInstancegetPntCurrentmSetTemp.fTemp;
    if (SystemInfo.isCentigrade() == true) {
      return (int)f;
    }
    return Temperature.convertCtoF(f);
  }
  
  public static String getSetTempStr(ID paramID)
  {
    Temperature localTemperature = getInstancegetPntCurrentmSetTemp;
    boolean bool = fEnable;
    if (!bool) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    float f = fTemp;
    String str = null;
    EnumDrvMode localEnumDrvMode = getInstancegetPntCurrentmDrvMode;
    if ((localEnumDrvMode == EnumDrvMode.COOL) || (localEnumDrvMode == EnumDrvMode.HEAT) || (localEnumDrvMode == EnumDrvMode.AUTOCOOL) || (localEnumDrvMode == EnumDrvMode.AUTOHEAT))
    {
      if (SystemInfo.isCentigrade() == true) {
        str = String.valueOf((int)f) + StrRes.getStr("IDS_COMMON_UNIT_TEMP");
      } else {
        str = String.valueOf(Temperature.convertCtoF(f) + StrRes.getStr("IDS_COMMON_UNIT_TEMP_F"));
      }
    }
    else {
      str = StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    return str;
  }
  
  public static String getSetTempStrNoUnit(ID paramID)
  {
    Temperature localTemperature = getInstancegetPntCurrentmSetTemp;
    boolean bool = fEnable;
    if (!bool) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    float f = fTemp;
    String str = null;
    EnumDrvMode localEnumDrvMode = getInstancegetPntCurrentmDrvMode;
    if ((localEnumDrvMode == EnumDrvMode.COOL) || (localEnumDrvMode == EnumDrvMode.HEAT) || (localEnumDrvMode == EnumDrvMode.AUTOCOOL) || (localEnumDrvMode == EnumDrvMode.AUTOHEAT))
    {
      if (SystemInfo.isCentigrade() == true) {
        str = String.valueOf((int)f);
      } else {
        str = String.valueOf(Temperature.convertCtoF(f));
      }
    }
    else {
      str = StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    return str;
  }
  
  public static int getState(ID paramID)
  {
    ItcIconInfo localItcIconInfo = DataMgr.getInstance().getIconInfo(paramID);
    if (fIconMode == 3) {
      return 1;
    }
    if (fIconMode == 2) {
      return 2;
    }
    return 0;
  }
  
  public static boolean getVentFreshUp(EnumVentVol paramEnumVentVol)
  {
    if ((paramEnumVentVol == EnumVentVol.NORMAL_AUTO) || (paramEnumVentVol == EnumVentVol.NORMAL_LOW) || (paramEnumVentVol == EnumVentVol.NORMAL_HIGH)) {
      return false;
    }
    return (paramEnumVentVol == EnumVentVol.FRESH_AUTO) || (paramEnumVentVol == EnumVentVol.FRESH_LOW) || (paramEnumVentVol == EnumVentVol.FRESH_HIGH);
  }
  
  public static int getVentMode(EnumVentMode paramEnumVentMode)
  {
    int i = -1;
    if (paramEnumVentMode == EnumVentMode.AUTO) {
      i = 8448;
    } else if (paramEnumVentMode == EnumVentMode.VENTILATION) {
      i = 8704;
    } else if (paramEnumVentMode == EnumVentMode.NORMAL) {
      i = 9216;
    }
    return i;
  }
  
  public static int getVentMode(ID paramID)
  {
    PntState localPntState = DataMgr.getInstance().getState(paramID);
    if (getOnOff(fOnOffState) == 4096) {
      return 4096;
    }
    EnumVentMode localEnumVentMode = getInstancegetPntCurrentmVentMode;
    return getVentMode(localEnumVentMode);
  }
  
  public static int getVentVol(EnumVentVol paramEnumVentVol)
  {
    int i = 0;
    if ((paramEnumVentVol == EnumVentVol.NORMAL_AUTO) || (paramEnumVentVol == EnumVentVol.FRESH_AUTO)) {
      i = 2;
    } else if ((paramEnumVentVol == EnumVentVol.NORMAL_LOW) || (paramEnumVentVol == EnumVentVol.FRESH_LOW)) {
      i = 0;
    } else if ((paramEnumVentVol == EnumVentVol.NORMAL_HIGH) || (paramEnumVentVol == EnumVentVol.FRESH_HIGH)) {
      i = 1;
    }
    return i;
  }
  
  public static int getVol(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return 0;
    case 1: 
      return 1;
    }
    return 1;
  }
  
  public static boolean isDispOnOffBtn(ID paramID)
  {
    VPoint localVPoint = DataMgr.getInstance().getPntFromID(paramID);
    EnumPntType localEnumPntType = getCommonPropfPntType;
    return (localEnumPntType == EnumPntType.ZONE) || (localEnumPntType == EnumPntType.D3_INNER) || (localEnumPntType == EnumPntType.D3_DIO);
  }
}
