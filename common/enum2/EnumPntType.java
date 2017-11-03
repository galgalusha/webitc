package webitc.common.enum2;

import webitc.common.StrRes;

public class EnumPntType
  extends Enum2
{
  public static final EnumPntType CORE_AI;
  public static final EnumPntType CORE_AO;
  public static final EnumPntType CORE_DI;
  public static final EnumPntType CORE_DO;
  public static final EnumPntType CORE_PI;
  public static final EnumPntType D3_AI;
  public static final EnumPntType D3_AO;
  public static final EnumPntType D3_DI;
  public static final EnumPntType D3_DIO;
  public static final EnumPntType D3_INNER;
  public static final EnumPntType D3_OUTER;
  public static final EnumPntType D3_PI;
  public static final EnumPntType NLIGHT;
  public static final EnumPntType PSEUDO_AI = new EnumPntType("PseudoAi");
  public static final EnumPntType RS485;
  public static final EnumPntType UNKNOWN;
  public static final EnumPntType ZONE = new EnumPntType("Zone");
  
  static
  {
    UNKNOWN = new EnumPntType("Unknown");
    CORE_DO = new EnumPntType("CoreDo");
    CORE_DI = new EnumPntType("CoreDi");
    CORE_PI = new EnumPntType("CorePi");
    CORE_AI = new EnumPntType("CoreAi");
    CORE_AO = new EnumPntType("CoreAo");
    D3_DI = new EnumPntType("D3Di");
    D3_DIO = new EnumPntType("D3Dio");
    D3_PI = new EnumPntType("D3Pi");
    D3_AI = new EnumPntType("D3Ai");
    D3_AO = new EnumPntType("D3Ao");
    D3_INNER = new EnumPntType("D3Inner");
    D3_OUTER = new EnumPntType("D3Outer");
    NLIGHT = new EnumPntType("NLight");
    RS485 = new EnumPntType("Rs485");
  }
  
  private EnumPntType(String paramString)
  {
    super(paramString);
  }
  
  public static EnumPntType getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 100: 
      return ZONE;
    case 0: 
      return UNKNOWN;
    case 3: 
      return CORE_DO;
    case 4: 
      return CORE_DI;
    case 5: 
      return CORE_PI;
    case 1: 
      return CORE_AI;
    case 11: 
      return CORE_AO;
    case 6: 
      return D3_DI;
    case 7: 
      return D3_DIO;
    case 2: 
      return D3_PI;
    case 15: 
      return D3_AI;
    case 17: 
      return D3_AO;
    case 9: 
      return D3_INNER;
    case 8: 
      return D3_OUTER;
    case 10: 
      return NLIGHT;
    case 12: 
      return RS485;
    case 16: 
      return PSEUDO_AI;
    }
    return UNKNOWN;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == UNKNOWN.toString()) {
      return 0;
    }
    if (str == CORE_DO.toString()) {
      return 3;
    }
    if (str == CORE_DI.toString()) {
      return 4;
    }
    if (str == CORE_PI.toString()) {
      return 5;
    }
    if (str == CORE_AI.toString()) {
      return 1;
    }
    if (str == CORE_AO.toString()) {
      return 11;
    }
    if (str == D3_DI.toString()) {
      return 6;
    }
    if (str == D3_DIO.toString()) {
      return 7;
    }
    if (str == D3_PI.toString()) {
      return 2;
    }
    if (str == D3_AI.toString()) {
      return 15;
    }
    if (str == D3_AO.toString()) {
      return 17;
    }
    if (str == D3_INNER.toString()) {
      return 9;
    }
    if (str == D3_OUTER.toString()) {
      return 8;
    }
    if (str == NLIGHT.toString()) {
      return 10;
    }
    if (str == RS485.toString()) {
      return 12;
    }
    if (str == PSEUDO_AI.toString()) {
      return 16;
    }
    if (str == ZONE.toString()) {
      return 100;
    }
    return 0;
  }
  
  public String getStr()
  {
    String str = toString();
    if (str == ZONE.toString()) {
      return StrRes.getStr("IDS_COMMON_ZONE");
    }
    if (str == D3_INNER.toString()) {
      return StrRes.getStr("IDS_COMMON_IU");
    }
    if (str == D3_OUTER.toString()) {
      return StrRes.getStr("IDS_COMMON_OU");
    }
    if (str == CORE_DO.toString()) {
      return StrRes.getStr("IDS_COMMON_DO");
    }
    if (str == CORE_DI.toString()) {
      return StrRes.getStr("IDS_COMMON_DI");
    }
    if (str == CORE_PI.toString()) {
      return StrRes.getStr("IDS_COMMON_PI");
    }
    if (str == D3_DI.toString()) {
      return StrRes.getStr("IDS_COMMON_D3DI");
    }
    if (str == D3_DIO.toString()) {
      return StrRes.getStr("IDS_COMMON_D3DIO");
    }
    if (str == D3_PI.toString()) {
      return StrRes.getStr("IDS_COMMON_D3PI");
    }
    return StrRes.getStr("IDS_COMMON_UNKNOWN");
  }
}
