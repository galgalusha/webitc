package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumDrvMode
  extends Enum2
{
  public static final EnumDrvMode AUTOCOOL = new EnumDrvMode("AutoCool");
  public static final EnumDrvMode AUTOHEAT = new EnumDrvMode("AutoHeat");
  public static final EnumDrvMode COOL;
  public static final EnumDrvMode DRY = new EnumDrvMode("Dry");
  public static final EnumDrvMode ELSE = new EnumDrvMode("Else");
  public static final EnumDrvMode FAN = new EnumDrvMode("Fan");
  public static final EnumDrvMode HEAT = new EnumDrvMode("Heat");
  public static final EnumDrvMode SUBMIT;
  public static final EnumDrvMode VENT = new EnumDrvMode("Vent");
  
  static
  {
    COOL = new EnumDrvMode("Cool");
    SUBMIT = new EnumDrvMode("Submit");
  }
  
  private EnumDrvMode(String paramString)
  {
    super(paramString);
  }
  
  public static EnumDrvMode getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return FAN;
    case 2: 
      return HEAT;
    case 4: 
      return COOL;
    case 16: 
      return SUBMIT;
    case 32: 
      return VENT;
    case 64: 
      return DRY;
    case 256: 
      return AUTOHEAT;
    case 512: 
      return AUTOCOOL;
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
      return 4;
    }
    if (str == SUBMIT.toString()) {
      return 16;
    }
    if (str == DRY.toString()) {
      return 64;
    }
    if (str == AUTOCOOL.toString()) {
      return 512;
    }
    if (str == AUTOHEAT.toString()) {
      return 256;
    }
    if (str == VENT.toString()) {
      return 32;
    }
    return 4096;
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
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
