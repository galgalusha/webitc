package webitc.data.point;

import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntType;
import webitc.data.ID;

public class PropPntCommon
{
  public final int fAddress;
  public final String fDetailName;
  public final int fIconID;
  public final EnumInnerType fInnerType;
  public final ID fPntID;
  public final EnumPntType fPntType;
  public final int fPortNum;
  public final String fShortName;
  
  public PropPntCommon(ID paramID, int paramInt1, int paramInt2, int paramInt3, String paramString1, String paramString2, EnumPntType paramEnumPntType, EnumInnerType paramEnumInnerType)
  {
    fPntID = paramID;
    fIconID = paramInt3;
    fPortNum = paramInt1;
    fAddress = paramInt2;
    fDetailName = paramString2;
    fShortName = paramString1;
    fPntType = paramEnumPntType;
    fInnerType = paramEnumInnerType;
  }
}
