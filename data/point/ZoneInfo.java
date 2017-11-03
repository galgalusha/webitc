package webitc.data.point;

import java.util.ArrayList;

public class ZoneInfo
{
  public static final int CHANGE_OVER = 16;
  public static final int DEMAND = 2;
  public static final int HMO = 4;
  public static final int SCHEDULE = 1;
  public static final int TEMP_LIMIT = 8;
  public final int fAutoCtrlBits;
  public final String fDetailName;
  public final ArrayList fErrorHistory;
  public final int fIntervalON;
  public final int fMemberNum;
  public final String fShortName;
  
  public ZoneInfo(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, ArrayList paramArrayList)
  {
    fDetailName = paramString2;
    fShortName = paramString1;
    fMemberNum = paramInt1;
    fIntervalON = paramInt2;
    fAutoCtrlBits = paramInt3;
    fErrorHistory = paramArrayList;
  }
}
