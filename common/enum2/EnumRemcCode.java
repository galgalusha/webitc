package webitc.common.enum2;

public class EnumRemcCode
  extends Enum2
{
  public static final EnumRemcCode DISABLE;
  public static final EnumRemcCode ELSE = new EnumRemcCode("Else");
  public static final EnumRemcCode ENABLE = new EnumRemcCode("Enable");
  public static final EnumRemcCode STOP;
  
  static
  {
    DISABLE = new EnumRemcCode("Disable");
    STOP = new EnumRemcCode("Stop");
  }
  
  private EnumRemcCode(String paramString)
  {
    super(paramString);
  }
  
  public static EnumRemcCode getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return DISABLE;
    case 1: 
      return STOP;
    case 2: 
      return ENABLE;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == DISABLE.toString()) {
      return 0;
    }
    if (str == STOP.toString()) {
      return 1;
    }
    if (str == ENABLE.toString()) {
      return 2;
    }
    return -1;
  }
}
