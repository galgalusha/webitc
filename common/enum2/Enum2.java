package webitc.common.enum2;

import java.util.ArrayList;
import java.util.List;

public abstract class Enum2
{
  protected static List list = new ArrayList();
  private String name;
  protected static int numberOfEnums = 0;
  private int ordinal;
  
  protected Enum2(String paramString)
  {
    name = paramString;
    ordinal = (numberOfEnums++);
    list.add(this);
  }
  
  public static Enum2 get(int paramInt)
  {
    return (Enum2)list.get(paramInt);
  }
  
  public abstract int getEnumValue();
  
  public int getOrdinal()
  {
    return ordinal;
  }
  
  public static int size()
  {
    return numberOfEnums;
  }
  
  public String toString()
  {
    return name;
  }
}
