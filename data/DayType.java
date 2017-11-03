package webitc.data;

public class DayType
{
  public static DayType DISABLE_DAY = new DayType(0, -1);
  public static final int EXCEPTION1 = 1;
  public static final int EXCEPTION10 = 10;
  public static final int EXCEPTION2 = 2;
  public static final int EXCEPTION3 = 3;
  public static final int EXCEPTION4 = 4;
  public static final int EXCEPTION5 = 5;
  public static final int EXCEPTION6 = 6;
  public static final int EXCEPTION7 = 7;
  public static final int EXCEPTION8 = 8;
  public static final int EXCEPTION9 = 9;
  public static final int SELECTED = 11;
  public static final int UNEDITABLE = 12;
  public static final int WEEKDAY = 0;
  public final int fDay;
  public final int fType;
  
  public DayType(int paramInt1, int paramInt2)
  {
    fDay = paramInt1;
    fType = paramInt2;
  }
  
  public DayType()
  {
    fDay = 0;
    fType = -1;
  }
  
  public boolean isEnable()
  {
    return fDay != 0;
  }
}
