package webitc.data.point;

import java.util.ArrayList;
import webitc.common.enum2.EnumHCSel;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntType;

public class PntInfo
{
  public static final int CHANGE_OVER = 16;
  public static final int DEMAND = 2;
  public static final int HMO = 4;
  public static final int INTERLOCK = 32;
  public static final int SCHEDULE = 1;
  public static final int TEMP_LIMIT = 8;
  public final String fAddrStr;
  public final int fAutoCtrlBits;
  public final boolean fChildMode;
  public final EnumHCSel fCoolHeatOpt;
  public final String fDetailName;
  public final EnumInnerType fInnerType;
  public final int fOuterAddr;
  public final EnumPntType fPntType;
  public final float fRoomTemp;
  public final String fShortName;
  public final boolean fValidRoomTemp;
  public final TargetErr mError;
  public final ArrayList mErrorHistory;
  
  public PntInfo(String paramString1, String paramString2, EnumPntType paramEnumPntType, EnumInnerType paramEnumInnerType, String paramString3, int paramInt1, boolean paramBoolean1, EnumHCSel paramEnumHCSel, int paramInt2, boolean paramBoolean2, float paramFloat, TargetErr paramTargetErr, ArrayList paramArrayList)
  {
    fDetailName = paramString2;
    fShortName = paramString1;
    fPntType = paramEnumPntType;
    fInnerType = paramEnumInnerType;
    fAddrStr = paramString3;
    fAutoCtrlBits = paramInt1;
    fChildMode = paramBoolean1;
    fCoolHeatOpt = paramEnumHCSel;
    fOuterAddr = paramInt2;
    fValidRoomTemp = paramBoolean2;
    fRoomTemp = paramFloat;
    mError = paramTargetErr;
    mErrorHistory = paramArrayList;
  }
}
