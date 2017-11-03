package webitc.common.enum2;

public class EnumComStat
  extends Enum2
{
  public static final EnumComStat ERROR = new EnumComStat("Error");
  public static final EnumComStat NORMAL = new EnumComStat("Normal");
  public static final EnumComStat WAIT = new EnumComStat("Wait");
  
  public EnumComStat(String paramString)
  {
    super(paramString);
  }
  
  public static EnumComStat getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case -1: 
      return WAIT;
    case 0: 
      return ERROR;
    case 1: 
      return NORMAL;
    }
    return WAIT;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == WAIT.toString()) {
      return -1;
    }
    if (str == ERROR.toString()) {
      return 0;
    }
    if (str == NORMAL.toString()) {
      return 1;
    }
    return -1;
  }
}
