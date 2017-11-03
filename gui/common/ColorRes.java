package webitc.gui.common;

import java.awt.Color;
import javax.swing.UIManager;

public final class ColorRes
{
  public static final Color CALENDAR_BLACK;
  public static final Color CALENDAR_BLUE;
  public static final Color CALENDAR_GREEN;
  public static final Color CALENDAR_RED;
  public static final Color CALENDAR_SELECTED_BG;
  public static final Color CALENDAR_SELECTED_FG;
  public static Color COM_ERROR;
  public static final Color C_NO_SELECTED_B = new Color(16777215);
  public static final Color C_NO_SELECTED_F;
  public static final Color C_RC_BG = new Color(8228236, false);
  public static final Color C_RC_BG2;
  public static final Color C_RC_BG3;
  public static final Color C_RC_TEXT = new Color(12832468, false);
  public static final Color C_SELECTED_B;
  public static final Color C_SELECTED_F;
  public static Color ERROR;
  public static final Color FILTER_SIGN;
  public static Color OFF;
  public static Color ON;
  public static final Color SIMPLE_MODE_BG;
  public static final Color SIMPLE_MODE_BTN;
  public static final Color SIMPLE_MODE_BTN_AUTO;
  public static final Color SIMPLE_MODE_BTN_AUTO_TEXT;
  public static final Color SIMPLE_MODE_BTN_AUTO_VENT;
  public static final Color SIMPLE_MODE_BTN_AUTO_VENT_TEXT;
  public static final Color SIMPLE_MODE_BTN_BG_1;
  public static final Color SIMPLE_MODE_BTN_BG_2;
  public static final Color SIMPLE_MODE_BTN_COOL;
  public static final Color SIMPLE_MODE_BTN_COOL_TEXT;
  public static final Color SIMPLE_MODE_BTN_F;
  public static final Color SIMPLE_MODE_BTN_FAN;
  public static final Color SIMPLE_MODE_BTN_FAN_TEXT;
  public static final Color SIMPLE_MODE_BTN_G;
  public static final Color SIMPLE_MODE_BTN_HEAT;
  public static final Color SIMPLE_MODE_BTN_HEAT_TEXT;
  public static final Color SIMPLE_MODE_BTN_MAIN_TOOL_BAR;
  public static final Color SIMPLE_MODE_BTN_MAIN_TOOL_BAR_TEXT;
  public static final Color SIMPLE_MODE_BTN_NORMAL_VENT;
  public static final Color SIMPLE_MODE_BTN_NORMAL_VENT_TEXT;
  public static final Color SIMPLE_MODE_BTN_R;
  public static final Color SIMPLE_MODE_BTN_SUBMIT;
  public static final Color SIMPLE_MODE_BTN_SUBMIT_TEXT;
  public static final Color SIMPLE_MODE_BTN_TEXT;
  public static final Color SIMPLE_MODE_BTN_TOTAL_VENT;
  public static final Color SIMPLE_MODE_BTN_TOTAL_VENT_TEXT;
  public static final Color SIMPLE_MODE_FRAME;
  public static final Color SIMPLE_MODE_LIST_NO_SELECT_B;
  public static final Color SIMPLE_MODE_LIST_NO_SELECT_F;
  public static final Color SIMPLE_MODE_LIST_SELECT_B;
  public static final Color SIMPLE_MODE_LIST_SELECT_F;
  public static final Color SIMPLE_MODE_MAIN_TOOL_BAR;
  public static final Color SIMPLE_MODE_TOOL_BAR;
  public static final Color SIMPLE_MODE_TOOL_BAR_BG_1;
  public static final Color SIMPLE_MODE_TOOL_BAR_BG_2;
  public static final Color SIMPLE_MODE_TOOL_BAR_TEXT;
  public static final Color SIMPLE_MODE_TRANSPARENT_COLOR;
  public static Color SYSTEM_BACK = UIManager.getColor("Label.background");
  
  static
  {
    C_RC_BG2 = new Color(15066070, false);
    C_RC_BG3 = new Color(16244349, false);
    FILTER_SIGN = new Color(16711935);
    ON = new Color(16711680);
    OFF = new Color(13434828);
    ERROR = new Color(16580100);
    COM_ERROR = new Color(6579964);
    CALENDAR_BLACK = new Color(0);
    CALENDAR_RED = new Color(16711680);
    CALENDAR_GREEN = new Color(50176);
    CALENDAR_BLUE = new Color(4342527);
    CALENDAR_SELECTED_FG = Color.WHITE;
    CALENDAR_SELECTED_BG = new Color(12566463);
    SIMPLE_MODE_TRANSPARENT_COLOR = Color.RED;
    SIMPLE_MODE_FRAME = new Color(13421772);
    SIMPLE_MODE_BG = Color.WHITE;
    SIMPLE_MODE_BTN_BG_1 = Color.BLACK;
    SIMPLE_MODE_BTN_BG_2 = new Color(6579372);
    SIMPLE_MODE_BTN = new Color(6724095);
    SIMPLE_MODE_BTN_TEXT = Color.WHITE;
    SIMPLE_MODE_BTN_G = new Color(14277081);
    SIMPLE_MODE_BTN_R = new Color(16298544);
    SIMPLE_MODE_BTN_F = Color.BLACK;
    SIMPLE_MODE_BTN_COOL = new Color(3368703);
    SIMPLE_MODE_BTN_COOL_TEXT = Color.BLACK;
    SIMPLE_MODE_BTN_HEAT = new Color(16737894);
    SIMPLE_MODE_BTN_HEAT_TEXT = Color.BLACK;
    SIMPLE_MODE_BTN_FAN = new Color(52479);
    SIMPLE_MODE_BTN_FAN_TEXT = Color.BLACK;
    SIMPLE_MODE_BTN_AUTO = new Color(6742835);
    SIMPLE_MODE_BTN_AUTO_TEXT = Color.BLACK;
    SIMPLE_MODE_BTN_SUBMIT = new Color(13395711);
    SIMPLE_MODE_BTN_SUBMIT_TEXT = Color.BLACK;
    SIMPLE_MODE_BTN_AUTO_VENT = new Color(10092390);
    SIMPLE_MODE_BTN_AUTO_VENT_TEXT = Color.BLACK;
    SIMPLE_MODE_BTN_TOTAL_VENT = new Color(16751001);
    SIMPLE_MODE_BTN_TOTAL_VENT_TEXT = Color.BLACK;
    SIMPLE_MODE_BTN_NORMAL_VENT = new Color(10079487);
    SIMPLE_MODE_BTN_NORMAL_VENT_TEXT = Color.BLACK;
    SIMPLE_MODE_MAIN_TOOL_BAR = new Color(6724095);
    SIMPLE_MODE_BTN_MAIN_TOOL_BAR = new Color(6724095);
    SIMPLE_MODE_BTN_MAIN_TOOL_BAR_TEXT = Color.WHITE;
    SIMPLE_MODE_TOOL_BAR = new Color(10066329);
    SIMPLE_MODE_TOOL_BAR_BG_1 = new Color(5921370);
    SIMPLE_MODE_TOOL_BAR_BG_2 = new Color(8092539);
    SIMPLE_MODE_TOOL_BAR_TEXT = Color.BLACK;
    SIMPLE_MODE_LIST_SELECT_B = new Color(10263708);
    SIMPLE_MODE_LIST_SELECT_F = Color.WHITE;
    SIMPLE_MODE_LIST_NO_SELECT_B = Color.WHITE;
    SIMPLE_MODE_LIST_NO_SELECT_F = Color.BLACK;
    C_SELECTED_F = new Color(16777215);
    C_SELECTED_B = new Color(-1073741696, true);
    C_NO_SELECTED_F = new Color(0);
  }
  
  public ColorRes() {}
  
  public static void setGreenOff(boolean paramBoolean)
  {
    if (paramBoolean == true)
    {
      ON = new Color(16711680);
      OFF = new Color(13434828);
    }
    else
    {
      ON = new Color(327172);
      OFF = new Color(16568012);
    }
  }
}
