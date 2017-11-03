package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumModelType
  extends Enum2
{
  public static final EnumModelType ACS = new EnumModelType("ACS");
  public static final EnumModelType ELSE = new EnumModelType("Else");
  public static final EnumModelType EXC = new EnumModelType("EXC");
  public static final EnumModelType ITC = new EnumModelType("ITC");
  public static final EnumModelType VEUP = new EnumModelType("VEUP");
  
  private EnumModelType(String paramString)
  {
    super(paramString);
  }
  
  public static EnumModelType getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return ITC;
    case 1: 
      return VEUP;
    case 6: 
      return ACS;
    case 7: 
      return EXC;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == ITC.toString()) {
      return 0;
    }
    if (str == VEUP.toString()) {
      return 1;
    }
    if (str == ACS.toString()) {
      return 6;
    }
    if (str == EXC.toString()) {
      return 7;
    }
    return -1;
  }
  
  public String getStr()
  {
    String str = toString();
    if (str == ITC.toString()) {
      return StrRes.getStr("IDS_COMMON_MODELNAME_ITC");
    }
    if (str == VEUP.toString()) {
      return StrRes.getStr("IDS_COMMON_MODELNAME_VEUP");
    }
    if (str == ACS.toString()) {
      return StrRes.getStr("IDS_COMMON_MODELNAME_ACS");
    }
    if (str == EXC.toString()) {
      return StrRes.getStr("IDS_COMMON_MODELNAME_EXC");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
