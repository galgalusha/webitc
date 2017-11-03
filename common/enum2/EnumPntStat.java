package webitc.common.enum2;

public class EnumPntStat
  extends Enum2
{
  public static final EnumPntStat ELSE = new EnumPntStat("Else");
  public static final EnumPntStat OFF = new EnumPntStat("Off");
  public static final EnumPntStat ON = new EnumPntStat("On");
  
  private EnumPntStat(String paramString)
  {
    super(paramString);
  }
  
  public static EnumPntStat getEnum(int paramInt)
  {
    switch (paramInt)
    {
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
    if (str == ON.toString()) {
      return 1;
    }
    if (str == OFF.toString()) {
      return 0;
    }
    return 2;
  }
}
