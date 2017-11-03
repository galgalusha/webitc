package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumHCSel
  extends Enum2
{
  public static final EnumHCSel ELSE = new EnumHCSel("Else");
  public static final EnumHCSel OFF = new EnumHCSel("Off");
  public static final EnumHCSel ON = new EnumHCSel("On");
  public static final EnumHCSel SELECTING = new EnumHCSel("Selecting");
  
  private EnumHCSel(String paramString)
  {
    super(paramString);
  }
  
  public static EnumHCSel getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case -1: 
      return SELECTING;
    case 0: 
      return OFF;
    case 1: 
      return ON;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == SELECTING.toString()) {
      return -1;
    }
    if (str == OFF.toString()) {
      return 0;
    }
    if (str == ON.toString()) {
      return 1;
    }
    return 1;
  }
  
  public String getStr()
  {
    String str = toString();
    if (str == ON.toString()) {
      return StrRes.getStr("IDS_COMMON_COOL_HEAT_SEL_ENABLE");
    }
    if (str == OFF.toString()) {
      return StrRes.getStr("IDS_COMMON_COOL_HEAT_SEL_DISABLE");
    }
    if (str == SELECTING.toString()) {
      return StrRes.getStr("IDS_COMMON_COOL_HEAT_SEL_SELECT");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
