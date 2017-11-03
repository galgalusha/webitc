package webitc.common.enum2;

public class EnumTableID
  extends Enum2
{
  public static final EnumTableID DLG_CONTRACT_PWR = new EnumTableID("T15");
  public static final EnumTableID DLG_MONITOR_INFO;
  public static final EnumTableID DLG_MONITOR_INFO_ERR_PNT;
  public static final EnumTableID DLG_MONITOR_INFO_ERR_ZONE;
  public static final EnumTableID DLG_PNT_ZONE_LIST;
  public static final EnumTableID DLG_SCH_DATA_PATTERN_FROM;
  public static final EnumTableID DLG_SCH_DATA_PATTERN_TO;
  public static final EnumTableID DLG_SYSTEM_VERSION;
  public static final EnumTableID DLG_USER_SETTING;
  public static final EnumTableID DLG_ZONE_EDIT;
  public static final EnumTableID PANEL_ACTION_SELECT;
  public static final EnumTableID PANEL_EDITABLE_ZONE_LIST;
  public static final EnumTableID PANEL_MONITOR;
  public static final EnumTableID PANEL_PPD;
  public static final EnumTableID PANEL_SCHEDULE;
  public static final EnumTableID PANEL_SIMPLE_LIST = new EnumTableID("ST1");
  public static final EnumTableID UNKNOWN = new EnumTableID("U");
  
  static
  {
    PANEL_MONITOR = new EnumTableID("T1");
    PANEL_SCHEDULE = new EnumTableID("T2");
    PANEL_ACTION_SELECT = new EnumTableID("T3");
    PANEL_PPD = new EnumTableID("T4");
    DLG_PNT_ZONE_LIST = new EnumTableID("T5");
    PANEL_EDITABLE_ZONE_LIST = new EnumTableID("T6");
    DLG_ZONE_EDIT = new EnumTableID("T7");
    DLG_USER_SETTING = new EnumTableID("T8");
    DLG_SCH_DATA_PATTERN_FROM = new EnumTableID("T9");
    DLG_SCH_DATA_PATTERN_TO = new EnumTableID("T10");
    DLG_MONITOR_INFO = new EnumTableID("T11");
    DLG_MONITOR_INFO_ERR_PNT = new EnumTableID("T12");
    DLG_MONITOR_INFO_ERR_ZONE = new EnumTableID("T13");
    DLG_SYSTEM_VERSION = new EnumTableID("T14");
  }
  
  public EnumTableID(String paramString)
  {
    super(paramString);
  }
  
  public static EnumTableID getEnum(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return PANEL_MONITOR;
    case 2: 
      return PANEL_SCHEDULE;
    case 3: 
      return PANEL_ACTION_SELECT;
    case 4: 
      return PANEL_PPD;
    case 5: 
      return DLG_PNT_ZONE_LIST;
    case 6: 
      return PANEL_EDITABLE_ZONE_LIST;
    case 7: 
      return DLG_ZONE_EDIT;
    case 8: 
      return DLG_USER_SETTING;
    case 9: 
      return DLG_SCH_DATA_PATTERN_FROM;
    case 10: 
      return DLG_SCH_DATA_PATTERN_TO;
    case 11: 
      return DLG_MONITOR_INFO;
    case 12: 
      return DLG_MONITOR_INFO_ERR_PNT;
    case 13: 
      return DLG_MONITOR_INFO_ERR_ZONE;
    case 14: 
      return DLG_SYSTEM_VERSION;
    case 15: 
      return PANEL_SIMPLE_LIST;
    case 16: 
      return DLG_CONTRACT_PWR;
    }
    return UNKNOWN;
  }
  
  public int getEnumValue()
  {
    String str = toString();
    if (str == PANEL_MONITOR.toString()) {
      return 1;
    }
    if (str == PANEL_SCHEDULE.toString()) {
      return 2;
    }
    if (str == PANEL_ACTION_SELECT.toString()) {
      return 3;
    }
    if (str == PANEL_PPD.toString()) {
      return 4;
    }
    if (str == DLG_PNT_ZONE_LIST.toString()) {
      return 5;
    }
    if (str == PANEL_EDITABLE_ZONE_LIST.toString()) {
      return 6;
    }
    if (str == DLG_ZONE_EDIT.toString()) {
      return 7;
    }
    if (str == DLG_USER_SETTING.toString()) {
      return 8;
    }
    if (str == DLG_SCH_DATA_PATTERN_FROM.toString()) {
      return 9;
    }
    if (str == DLG_USER_SETTING.toString()) {
      return 10;
    }
    if (str == DLG_MONITOR_INFO.toString()) {
      return 11;
    }
    if (str == DLG_MONITOR_INFO_ERR_PNT.toString()) {
      return 12;
    }
    if (str == DLG_MONITOR_INFO_ERR_ZONE.toString()) {
      return 13;
    }
    if (str == DLG_SYSTEM_VERSION.toString()) {
      return 14;
    }
    if (str == PANEL_SIMPLE_LIST.toString()) {
      return 15;
    }
    if (str == DLG_CONTRACT_PWR.toString()) {
      return 16;
    }
    return 0;
  }
}
