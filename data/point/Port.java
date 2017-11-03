package webitc.data.point;

import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumPntStat;

public class Port
  implements Cloneable
{
  public final boolean fAllVaildOFF;
  public final boolean fAllVaildON;
  public final EnumPntStat fPort0ComError;
  public final EnumPntStat fPort0ControlCenter;
  public final EnumPntStat fPort0ForceStop;
  public final EnumPntStat fPort0M8;
  public final EnumPntStat fPort1ComError;
  public final EnumPntStat fPort1ControlCenter;
  public final EnumPntStat fPort1ForceStop;
  public final EnumPntStat fPort1M8;
  
  private Port(EnumPntStat paramEnumPntStat1, EnumPntStat paramEnumPntStat2, EnumPntStat paramEnumPntStat3, EnumPntStat paramEnumPntStat4, EnumPntStat paramEnumPntStat5, EnumPntStat paramEnumPntStat6, EnumPntStat paramEnumPntStat7, EnumPntStat paramEnumPntStat8, boolean paramBoolean1, boolean paramBoolean2)
  {
    fPort0ControlCenter = paramEnumPntStat1;
    fPort0ForceStop = paramEnumPntStat2;
    fPort0M8 = paramEnumPntStat3;
    fPort0ComError = paramEnumPntStat4;
    fPort1ControlCenter = paramEnumPntStat5;
    fPort1ForceStop = paramEnumPntStat6;
    fPort1M8 = paramEnumPntStat7;
    fPort1ComError = paramEnumPntStat8;
    fAllVaildON = paramBoolean1;
    fAllVaildOFF = paramBoolean2;
  }
  
  public Port(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, boolean paramBoolean8, boolean paramBoolean9, boolean paramBoolean10)
  {
    fAllVaildON = paramBoolean9;
    fAllVaildOFF = paramBoolean10;
    int i = SystemInfo.getD3PortNum();
    if (i == 1)
    {
      fPort0ControlCenter = (paramBoolean1 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort0ForceStop = (paramBoolean2 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort0M8 = (paramBoolean3 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort0ComError = (paramBoolean4 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort1ControlCenter = EnumPntStat.ELSE;
      fPort1ForceStop = EnumPntStat.ELSE;
      fPort1M8 = EnumPntStat.ELSE;
      fPort1ComError = EnumPntStat.ELSE;
    }
    else if (i == 2)
    {
      fPort0ControlCenter = (paramBoolean1 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort0ForceStop = (paramBoolean2 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort0M8 = (paramBoolean3 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort0ComError = (paramBoolean4 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort1ControlCenter = (paramBoolean5 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort1ForceStop = (paramBoolean6 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort1M8 = (paramBoolean7 ? EnumPntStat.ON : EnumPntStat.OFF);
      fPort1ComError = (paramBoolean8 ? EnumPntStat.ON : EnumPntStat.OFF);
    }
    else
    {
      fPort0ControlCenter = EnumPntStat.ELSE;
      fPort0ForceStop = EnumPntStat.ELSE;
      fPort0M8 = EnumPntStat.ELSE;
      fPort0ComError = EnumPntStat.ELSE;
      fPort1ControlCenter = EnumPntStat.ELSE;
      fPort1ForceStop = EnumPntStat.ELSE;
      fPort1M8 = EnumPntStat.ELSE;
      fPort1ComError = EnumPntStat.ELSE;
    }
  }
  
  public Port()
  {
    fPort0ControlCenter = EnumPntStat.OFF;
    fPort0ForceStop = EnumPntStat.OFF;
    fPort0M8 = EnumPntStat.OFF;
    fPort0ComError = EnumPntStat.OFF;
    fPort1ControlCenter = EnumPntStat.ELSE;
    fPort1ForceStop = EnumPntStat.ELSE;
    fPort1M8 = EnumPntStat.ELSE;
    fPort1ComError = EnumPntStat.ELSE;
    fAllVaildON = true;
    fAllVaildOFF = true;
  }
  
  public Object clone()
  {
    Port localPort = new Port(fPort0ControlCenter, fPort0ForceStop, fPort0M8, fPort0ComError, fPort1ControlCenter, fPort1ForceStop, fPort1M8, fPort1ComError, fAllVaildON, fAllVaildOFF);
    return localPort;
  }
  
  public String getPortStr()
  {
    if ((fPort0ComError == EnumPntStat.ON) || (fPort1ComError == EnumPntStat.ON)) {
      return StrRes.getStr("IDS_COMMON_SYS_ERR");
    }
    if ((fPort0ControlCenter == EnumPntStat.ON) || (fPort1ControlCenter == EnumPntStat.ON)) {
      return StrRes.getStr("IDS_MAINWND_CCDISABLE");
    }
    if ((fPort0ForceStop == EnumPntStat.ON) || (fPort1ForceStop == EnumPntStat.ON)) {
      return StrRes.getStr("IDS_CCLEGEND_FORCE_STOP");
    }
    if ((fPort0M8 == EnumPntStat.ON) || (fPort1M8 == EnumPntStat.ON)) {
      return StrRes.getStr("IDS_COMMON_ERR_CODE") + " M8";
    }
    return "";
  }
}
