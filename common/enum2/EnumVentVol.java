package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumVentVol
  extends Enum2
{
  public static final EnumVentVol ELSE = new EnumVentVol("Else");
  public static final EnumVentVol FRESH_AUTO;
  public static final EnumVentVol FRESH_HIGH = new EnumVentVol("FreshHigh");
  public static final EnumVentVol FRESH_LOW;
  public static final EnumVentVol NORMAL_AUTO = new EnumVentVol("NormalAuto");
  public static final EnumVentVol NORMAL_HIGH;
  public static final EnumVentVol NORMAL_LOW = new EnumVentVol("NormalLow");
  
  static
  {
    NORMAL_HIGH = new EnumVentVol("NormalHigh");
    FRESH_AUTO = new EnumVentVol("FreshAuto");
    FRESH_LOW = new EnumVentVol("FreshLow");
  }
  
  private EnumVentVol(String paramString)
  {
    super(paramString);
  }
  
  public static EnumVentVol getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return NORMAL_AUTO;
    case 2: 
      return NORMAL_LOW;
    case 4: 
      return NORMAL_HIGH;
    case 8: 
      return FRESH_AUTO;
    case 16: 
      return FRESH_LOW;
    case 32: 
      return FRESH_HIGH;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == NORMAL_AUTO.toString()) {
      return 1;
    }
    if (str == NORMAL_LOW.toString()) {
      return 2;
    }
    if (str == NORMAL_HIGH.toString()) {
      return 4;
    }
    if (str == FRESH_AUTO.toString()) {
      return 8;
    }
    if (str == FRESH_LOW.toString()) {
      return 16;
    }
    if (str == FRESH_HIGH.toString()) {
      return 32;
    }
    return 4096;
  }
  
  public String getStr()
  {
    String str = toString();
    if (str == NORMAL_AUTO.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_AUTO");
    }
    if (str == NORMAL_LOW.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_LOW");
    }
    if (str == NORMAL_HIGH.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_HIGH");
    }
    if (str == FRESH_AUTO.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_AUTO");
    }
    if (str == FRESH_LOW.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_LOW");
    }
    if (str == FRESH_HIGH.toString()) {
      return StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_HIGH");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
