package webitc.data.point;

import webitc.common.StrRes;
import webitc.data.ID;

public class PropZone
{
  public final String fDetailName;
  public final int fIconID;
  public final String fShortName;
  public final ID fZoneID;
  
  public PropZone(int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    fZoneID = new ID(1, paramInt1);
    fIconID = paramInt2;
    if (fZoneID.equals(ID.ZONE_ALL) == true)
    {
      fShortName = StrRes.getStr("IDS_COMMON_NAME_ZONE_ALL");
      fDetailName = StrRes.getStr("IDS_COMMON_DISCRIPTION_ZONE_ALL");
    }
    else
    {
      fShortName = paramString1;
      fDetailName = paramString2;
    }
  }
  
  public PropZone(ID paramID, int paramInt, String paramString1, String paramString2)
  {
    fZoneID = paramID;
    fIconID = paramInt;
    if (paramID.equals(ID.ZONE_ALL) == true)
    {
      fShortName = StrRes.getStr("IDS_COMMON_NAME_ZONE_ALL");
      fDetailName = StrRes.getStr("IDS_COMMON_DISCRIPTION_ZONE_ALL");
    }
    else
    {
      fShortName = paramString1;
      fDetailName = paramString2;
    }
  }
}
