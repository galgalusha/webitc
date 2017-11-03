package webitc.common.enum2;

public class EnumPpdCollectionType
  extends Enum2
{
  public static final EnumPpdCollectionType ELSE = new EnumPpdCollectionType("Else");
  public static final EnumPpdCollectionType INNER = new EnumPpdCollectionType("Inner");
  public static final EnumPpdCollectionType SUM = new EnumPpdCollectionType("Sum");
  public static final EnumPpdCollectionType ZONE = new EnumPpdCollectionType("Zone");
  
  private EnumPpdCollectionType(String paramString)
  {
    super(paramString);
  }
  
  public static EnumPpdCollectionType getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return INNER;
    case 1: 
      return ZONE;
    case 2: 
      return SUM;
    }
    return ELSE;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == INNER.toString()) {
      return 0;
    }
    if (str == ZONE.toString()) {
      return 1;
    }
    if (str == SUM.toString()) {
      return 2;
    }
    return -1;
  }
}
