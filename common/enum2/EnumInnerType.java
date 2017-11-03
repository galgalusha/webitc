package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumInnerType
  extends Enum2
{
  public static final EnumInnerType AIRHAN;
  public static final EnumInnerType CHILLER = new EnumInnerType("Chiller");
  public static final EnumInnerType FFU;
  public static final EnumInnerType NORMAL = new EnumInnerType("Normal");
  public static final EnumInnerType UNKNOWN = new EnumInnerType("Unknown");
  public static final EnumInnerType VENT;
  
  static
  {
    AIRHAN = new EnumInnerType("Airhan");
    FFU = new EnumInnerType("FFU");
    VENT = new EnumInnerType("Vent");
  }
  
  private EnumInnerType(String paramString)
  {
    super(paramString);
  }
  
  public static EnumInnerType getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 100: 
      return UNKNOWN;
    case 0: 
      return NORMAL;
    case 1: 
      return AIRHAN;
    case 2: 
      return FFU;
    case 3: 
      return VENT;
    case 4: 
      return CHILLER;
    }
    return UNKNOWN;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == UNKNOWN.toString()) {
      return 100;
    }
    if (str == NORMAL.toString()) {
      return 0;
    }
    if (str == AIRHAN.toString()) {
      return 1;
    }
    if (str == FFU.toString()) {
      return 2;
    }
    if (str == VENT.toString()) {
      return 3;
    }
    if (str == CHILLER.toString()) {
      return 4;
    }
    return 100;
  }
  
  public String getStr()
  {
    String str = toString();
    if (str == NORMAL.toString()) {
      return StrRes.getStr("IDS_COMMON_IU");
    }
    if (str == AIRHAN.toString()) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    if (str == FFU.toString()) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    if (str == VENT.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT");
    }
    if (str == CHILLER.toString()) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
