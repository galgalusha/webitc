package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumSystemType
  extends Enum2
{
  public static final EnumSystemType BIPS_SERIES = new EnumSystemType("BIPS_SERIES");
  public static final EnumSystemType ELSE = new EnumSystemType("Else");
  public static final EnumSystemType ITC_SERIES;
  public static final EnumSystemType LC_SERIES = new EnumSystemType("LC_SERIES");
  
  static
  {
    ITC_SERIES = new EnumSystemType("ITC_SERIES");
  }
  
  private EnumSystemType(String paramString)
  {
    super(paramString);
  }
  
  public static EnumSystemType getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return LC_SERIES;
    case 1: 
      return ITC_SERIES;
    case 2: 
      return BIPS_SERIES;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == LC_SERIES.toString()) {
      return 0;
    }
    if (str == ITC_SERIES.toString()) {
      return 1;
    }
    if (str == BIPS_SERIES.toString()) {
      return 2;
    }
    return -1;
  }
  
  public String getStr()
  {
    String str = toString();
    if (str == LC_SERIES.toString()) {
      return StrRes.getStr("IDS_COMMON_MODELNAME_LC");
    }
    if (str == ITC_SERIES.toString()) {
      return StrRes.getStr("IDS_COMMON_MODELNAME_ITC");
    }
    if (str == BIPS_SERIES.toString()) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
