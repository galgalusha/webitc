package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumDrvVentMode
  extends Enum2
{
  public static final EnumDrvVentMode AUTO;
  public static final EnumDrvVentMode AUTOCOOL;
  public static final EnumDrvVentMode AUTOHEAT;
  public static final EnumDrvVentMode COOL;
  public static final EnumDrvVentMode DRY;
  public static final EnumDrvVentMode ELSE = new EnumDrvVentMode("Else");
  public static final EnumDrvVentMode FAN = new EnumDrvVentMode("Fan");
  public static final EnumDrvVentMode HEAT = new EnumDrvVentMode("Heat");
  public static final EnumDrvVentMode NORMAL = new EnumDrvVentMode("Normal");
  public static final EnumDrvVentMode SUBMIT;
  public static final EnumDrvVentMode VENT;
  public static final EnumDrvVentMode VENTILATION;
  
  static
  {
    COOL = new EnumDrvVentMode("Cool");
    SUBMIT = new EnumDrvVentMode("Submit");
    AUTOCOOL = new EnumDrvVentMode("AutoCool");
    AUTOHEAT = new EnumDrvVentMode("AutoHeat");
    DRY = new EnumDrvVentMode("Dry");
    VENT = new EnumDrvVentMode("Vent");
    AUTO = new EnumDrvVentMode("Auto");
    VENTILATION = new EnumDrvVentMode("Ventilation");
  }
  
  public EnumDrvVentMode(String paramString)
  {
    super(paramString);
  }
  
  public static EnumDrvVentMode getEnum(EnumVentMode paramEnumVentMode)
  {
    if (paramEnumVentMode == EnumVentMode.AUTO) {
      return AUTO;
    }
    if (paramEnumVentMode == EnumVentMode.NORMAL) {
      return NORMAL;
    }
    if (paramEnumVentMode == EnumVentMode.VENTILATION) {
      return VENTILATION;
    }
    return ELSE;
  }
  
  public static EnumDrvVentMode getEnum(EnumDrvMode paramEnumDrvMode)
  {
    if (paramEnumDrvMode == EnumDrvMode.FAN) {
      return FAN;
    }
    if (paramEnumDrvMode == EnumDrvMode.HEAT) {
      return HEAT;
    }
    if (paramEnumDrvMode == EnumDrvMode.COOL) {
      return COOL;
    }
    if (paramEnumDrvMode == EnumDrvMode.SUBMIT) {
      return SUBMIT;
    }
    if (paramEnumDrvMode == EnumDrvMode.DRY) {
      return DRY;
    }
    if (paramEnumDrvMode == EnumDrvMode.AUTOCOOL) {
      return AUTOCOOL;
    }
    if (paramEnumDrvMode == EnumDrvMode.AUTOHEAT) {
      return AUTOHEAT;
    }
    if (paramEnumDrvMode == EnumDrvMode.VENT) {
      return VENT;
    }
    return ELSE;
  }
  
  public static EnumDrvVentMode getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return FAN;
    case 2: 
      return HEAT;
    case 3: 
      return COOL;
    case 4: 
      return SUBMIT;
    case 5: 
      return DRY;
    case 6: 
      return AUTOCOOL;
    case 7: 
      return AUTOHEAT;
    case 8: 
      return AUTO;
    case 9: 
      return VENTILATION;
    case 10: 
      return NORMAL;
    case 11: 
      return VENT;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == FAN.toString()) {
      return 1;
    }
    if (str == HEAT.toString()) {
      return 2;
    }
    if (str == COOL.toString()) {
      return 3;
    }
    if (str == SUBMIT.toString()) {
      return 4;
    }
    if (str == DRY.toString()) {
      return 5;
    }
    if (str == AUTOCOOL.toString()) {
      return 6;
    }
    if (str == AUTOHEAT.toString()) {
      return 7;
    }
    if (str == AUTO.toString()) {
      return 8;
    }
    if (str == VENTILATION.toString()) {
      return 9;
    }
    if (str == NORMAL.toString()) {
      return 10;
    }
    if (str == VENT.toString()) {
      return 11;
    }
    return -1;
  }
  
  public String getStr()
  {
    String str = toString();
    if (str == FAN.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_FAN");
    }
    if (str == HEAT.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_HEAT");
    }
    if (str == COOL.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_COOL");
    }
    if (str == SUBMIT.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_SETPOINT");
    }
    if (str == DRY.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_DRY");
    }
    if (str == AUTOCOOL.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_AUTO");
    }
    if (str == AUTOHEAT.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_AUTO");
    }
    if (str == VENT.toString()) {
      return StrRes.getStr("IDS_COMMON_MODE_VENT");
    }
    if (str == AUTO.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_MODE_AUTO");
    }
    if (str == VENTILATION.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_MODE_VENT");
    }
    if (str == NORMAL.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_MODE_NORMAL");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
