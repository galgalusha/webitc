package webitc.gui.common;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumLang;

public class SimpleButtonRes
{
  public static final int BUTTON_AUTO = 6;
  public static final int BUTTON_AUTO_VENT = 16;
  public static final int BUTTON_CANCEL = 12;
  public static final int BUTTON_COOL = 4;
  public static final int BUTTON_EXPANSION = 15;
  public static final int BUTTON_FAN = 5;
  public static final int BUTTON_HEAT = 7;
  public static final int BUTTON_ICON = 13;
  public static final int BUTTON_LIST = 14;
  public static final int BUTTON_LOGOFF = 10;
  public static final int BUTTON_NORMAL_VENT = 18;
  public static final int BUTTON_OFF = 1;
  public static final int BUTTON_ON = 0;
  public static final int BUTTON_REFRESH = 9;
  public static final int BUTTON_START = 2;
  public static final int BUTTON_STOP = 3;
  public static final int BUTTON_SUBMIT = 8;
  public static final int BUTTON_TOTAL_VENT = 17;
  public static final int BUTTON_TRANSMIT = 11;
  
  public SimpleButtonRes() {}
  
  public static SimpleButton getButton(int paramInt)
  {
    SimpleButton localSimpleButton = new SimpleButton();
    EnumLang localEnumLang = SystemInfo.getLang();
    Dimension localDimension1 = null;
    Dimension localDimension2 = null;
    Dimension localDimension3 = null;
    Font localFont1 = null;
    Font localFont2 = null;
    Font localFont3 = null;
    if (localEnumLang == EnumLang.JAPANESE)
    {
      localDimension1 = new Dimension(100, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(120, 35);
      localFont1 = new Font("Dialog", 0, 14);
      localFont2 = new Font("Dialog", 0, 16);
      localFont3 = localFont2;
    }
    else if (localEnumLang == EnumLang.CHINESE)
    {
      localDimension1 = new Dimension(100, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(150, 35);
      localFont1 = new Font("Dialog", 0, 14);
      localFont2 = new Font("Dialog", 0, 16);
      localFont3 = localFont2;
    }
    else if (localEnumLang == EnumLang.ENGLISH)
    {
      localDimension1 = new Dimension(100, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(150, 35);
      localFont1 = new Font("Dialog", 0, 12);
      localFont2 = new Font("Dialog", 0, 14);
      localFont3 = localFont2;
    }
    else if (localEnumLang == EnumLang.FRENCH)
    {
      localDimension1 = new Dimension(120, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(150, 35);
      localFont1 = new Font("Dialog", 0, 12);
      localFont2 = new Font("Dialog", 0, 14);
      localFont3 = localFont2;
    }
    else if (localEnumLang == EnumLang.ITALIAN)
    {
      localDimension1 = new Dimension(120, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(170, 35);
      localFont1 = new Font("Dialog", 0, 12);
      localFont2 = new Font("Dialog", 0, 14);
      localFont3 = localFont2;
    }
    else if (localEnumLang == EnumLang.SPANISH)
    {
      localDimension1 = new Dimension(140, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(180, 35);
      localFont1 = new Font("Dialog", 0, 12);
      localFont2 = new Font("Dialog", 0, 14);
      localFont3 = localFont2;
    }
    else if (localEnumLang == EnumLang.GERMAN)
    {
      localDimension1 = new Dimension(110, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(150, 35);
      localFont1 = new Font("Dialog", 0, 12);
      localFont2 = localFont1;
      localFont3 = new Font("Dialog", 0, 10);
    }
    else if (localEnumLang == EnumLang.KOREAN)
    {
      localDimension1 = new Dimension(100, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(150, 35);
      localFont1 = new Font("Dialog", 0, 14);
      localFont2 = new Font("Dialog", 0, 16);
      localFont3 = localFont2;
    }
    else
    {
      localDimension1 = new Dimension(100, 23);
      localDimension2 = localDimension1;
      localDimension3 = new Dimension(120, 35);
      localFont1 = new Font("Dialog", 0, 14);
      localFont2 = new Font("Dialog", 0, 16);
      localFont3 = localFont2;
    }
    ImageIcon localImageIcon;
    switch (paramInt)
    {
    case 0: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_ON"));
      localSimpleButton.setToggle(true);
      break;
    case 1: 
      localSimpleButton = new SimpleButton();
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_OFF"));
      localSimpleButton.setToggle(true);
      break;
    case 2: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_START"));
      localSimpleButton.setToggle(true);
      break;
    case 3: 
      localSimpleButton = new SimpleButton();
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_STOP"));
      localSimpleButton.setToggle(true);
      break;
    case 4: 
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_COOL);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_COOL_TEXT);
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_MODE_COOL"));
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont2);
      break;
    case 5: 
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_FAN);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_FAN_TEXT);
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_MODE_FAN"));
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont2);
      break;
    case 6: 
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_AUTO);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_AUTO_TEXT);
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_MODE_AUTO"));
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont2);
      break;
    case 7: 
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_HEAT);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_HEAT_TEXT);
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_MODE_HEAT"));
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont2);
      break;
    case 8: 
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_SUBMIT);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_SUBMIT_TEXT);
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_MODE_SETPOINT"));
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont3);
      break;
    case 9: 
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_MAIN_TOOL_BAR);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_MAIN_TOOL_BAR_TEXT);
      localSimpleButton.setPreferredSize(localDimension1);
      localSimpleButton.setImageIcon(IconRes.getIcon(176));
      localSimpleButton.setIconMargin(5);
      localSimpleButton.setText(StrRes.getStr("IDS_WEBMAIN_REFRESH"));
      break;
    case 10: 
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_MAIN_TOOL_BAR);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_MAIN_TOOL_BAR_TEXT);
      localSimpleButton.setPreferredSize(localDimension2);
      localSimpleButton.setImageIcon(IconRes.getIcon(19));
      localSimpleButton.setIconMargin(5);
      localSimpleButton.setText(StrRes.getStr("IDS_WEBMAIN_LOGOFF"));
      break;
    case 11: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_OK"));
      localSimpleButton.setPreferredSize(new Dimension(120, 35));
      localSimpleButton.setFont(new Font("Dialog", 0, 20));
      break;
    case 12: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
      localSimpleButton.setPreferredSize(new Dimension(120, 35));
      localSimpleButton.setFont(new Font("Dialog", 0, 20));
      break;
    case 13: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_ICON"));
      localSimpleButton.setImageIcon(IconRes.getIcon(14));
      localSimpleButton.setPreferredSize(new Dimension(100, 23));
      localSimpleButton.setIconMargin(5);
      localSimpleButton.setToggle(true);
      break;
    case 14: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_LIST"));
      localSimpleButton.setImageIcon(IconRes.getIcon(15));
      localSimpleButton.setPreferredSize(new Dimension(100, 23));
      localSimpleButton.setIconMargin(5);
      localSimpleButton.setToggle(true);
      break;
    case 15: 
      localImageIcon = IconRes.getIcon(161);
      localSimpleButton.setImageIcon(localImageIcon);
      localSimpleButton.setPreferredSize(new Dimension(localImageIcon.getIconWidth(), localImageIcon.getIconHeight()));
      localSimpleButton.setShadow(false);
      break;
    case 16: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_AUTO"));
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_AUTO_VENT);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_AUTO_VENT_TEXT);
      localImageIcon = IconRes.getIcon(121);
      localSimpleButton.setImageIcon(localImageIcon);
      localSimpleButton.setPreferredSize(localDimension3);
      localSimpleButton.doAllowNewLine(true);
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont1);
      break;
    case 17: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_VENT"));
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_TOTAL_VENT);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_TOTAL_VENT_TEXT);
      localImageIcon = IconRes.getIcon(122);
      localSimpleButton.setImageIcon(localImageIcon);
      localSimpleButton.setPreferredSize(localDimension3);
      localSimpleButton.doAllowNewLine(true);
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont1);
      break;
    case 18: 
      localSimpleButton.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_NORMAL"));
      localSimpleButton.setBgColor(ColorRes.SIMPLE_MODE_BTN_NORMAL_VENT);
      localSimpleButton.setTextColor(ColorRes.SIMPLE_MODE_BTN_NORMAL_VENT_TEXT);
      localImageIcon = IconRes.getIcon(123);
      localSimpleButton.setImageIcon(localImageIcon);
      localSimpleButton.setPreferredSize(localDimension3);
      localSimpleButton.doAllowNewLine(true);
      localSimpleButton.setToggle(true);
      localSimpleButton.setFont(localFont1);
      break;
    default: 
      CommonUse.AppErr(new FatalException("ID is not Founded"), "SimpleButtonRes::getButton( int )");
    }
    return localSimpleButton;
  }
}
