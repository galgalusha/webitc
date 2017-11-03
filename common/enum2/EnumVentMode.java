package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumVentMode
  extends Enum2
{
  public static final EnumVentMode AUTO;
  public static final EnumVentMode ELSE = new EnumVentMode("Else");
  public static final EnumVentMode NORMAL = new EnumVentMode("Normal");
  public static final EnumVentMode VENTILATION;
  
  static
  {
    AUTO = new EnumVentMode("Auto");
    VENTILATION = new EnumVentMode("Ventilation");
  }
  
  private EnumVentMode(String paramString)
  {
    super(paramString);
  }
  
  public static EnumVentMode getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return AUTO;
    case 2: 
      return VENTILATION;
    case 4: 
      return NORMAL;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == AUTO.toString()) {
      return 1;
    }
    if (str == VENTILATION.toString()) {
      return 2;
    }
    if (str == NORMAL.toString()) {
      return 4;
    }
    return 4096;
  }
  
  public String getStr()
  {
    String str = toString();
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
