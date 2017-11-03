package webitc.data;

public class ID
{
  public static final ID ID_UNKNOWN = new ID(3, 0);
  public static final ID ID_UNREG;
  public static final int PNT = 0;
  public static final int UNKNOWN = 3;
  public static final int UNREG = 2;
  public static final int ZONE = 1;
  public static final ID ZONE_ALL = new ID(1, 0);
  public final int fID;
  public final int fType;
  
  static
  {
    ID_UNREG = new ID(2, 0);
  }
  
  public ID(int paramInt1, int paramInt2)
  {
    if ((paramInt1 != 0) && (paramInt1 != 1) && (paramInt1 != 2) && (paramInt1 != 3)) {
      throw new ArrayIndexOutOfBoundsException("ID.ID type error");
    }
    fType = paramInt1;
    fID = paramInt2;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject.getClass() != getClass()) {
      return false;
    }
    ID localID = (ID)paramObject;
    return (fType == fType) && (fID == fID);
  }
  
  public int hashCode()
  {
    if (fType == 0) {
      return fID;
    }
    return -fID;
  }
}
