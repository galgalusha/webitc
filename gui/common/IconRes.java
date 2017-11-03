package webitc.gui.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import webitc.common.AppletAbst;
import webitc.common.FatalException;

public class IconRes
{
  public static final int AUTH = 17;
  public static final int BACKWORD = 11;
  public static final int CIRCLE_SIGN_AUTO = 23;
  public static final int CIRCLE_SIGN_DEMAND = 22;
  public static final int CIRCLE_SIGN_FILTER = 24;
  public static final int CIRCLE_SIGN_FORCE_STOP = 21;
  private static final int COLORED_AUTO = 2;
  private static final int COLORED_AUTO_FRESHUP = 8;
  private static final int COLORED_AUTO_VENT = 5;
  private static final int COLORED_COOL = 1;
  private static final int COLORED_FAN = 3;
  private static final int COLORED_HEAT = 0;
  private static final int COLORED_NORMAL_FRESHUP = 10;
  private static final int COLORED_NORMAL_VENT = 7;
  private static final int COLORED_SUBMIT = 4;
  private static final int COLORED_TOTAL_FRESHUP = 9;
  private static final int COLORED_TOTAL_VENT = 6;
  public static final int DAIKIN = 1;
  public static final int ERROR = 26;
  public static final int FOREWORD = 12;
  public static final int HELP = 16;
  public static final int ICON = 14;
  public static final int INFO = 28;
  public static final int LIST = 15;
  public static final int LOCALE = 25;
  public static final int LOGOFF = 19;
  private static final int NUM_OF_ICON = 181;
  private static final int NUM_OF_MAIN_ICON = 18;
  public static final int QUESTION = 29;
  public static final int RC_BACK = 0;
  public static final int REFRESH = 13;
  public static final int SIMPLE_AC = 35;
  public static final int SIMPLE_AC_LIST = 93;
  private static final int SIMPLE_AC_LIST_BASE = 93;
  public static final int SIMPLE_AC_TABLE = 86;
  private static final int SIMPLE_AC_TABLE_BASE = 86;
  public static final int SIMPLE_AUTO_FAN_1_VOL_1 = 56;
  public static final int SIMPLE_AUTO_FAN_1_VOL_2 = 57;
  public static final int SIMPLE_AUTO_FAN_2_VOL_1 = 58;
  public static final int SIMPLE_AUTO_FAN_2_VOL_2 = 59;
  public static final int SIMPLE_AUTO_FAN_3_VOL_1 = 60;
  public static final int SIMPLE_AUTO_FAN_3_VOL_2 = 61;
  public static final int SIMPLE_AUTO_FAN_4_VOL_1 = 62;
  public static final int SIMPLE_AUTO_FAN_4_VOL_2 = 63;
  public static final int SIMPLE_AUTO_FAN_5_VOL_1 = 64;
  public static final int SIMPLE_AUTO_FAN_5_VOL_2 = 65;
  public static final int SIMPLE_AUTO_FAN_LIST = 96;
  public static final int SIMPLE_AUTO_FAN_TABLE = 89;
  public static final int SIMPLE_AUTO_VENT = 121;
  public static final int SIMPLE_COOL_FAN_1_VOL_1 = 36;
  public static final int SIMPLE_COOL_FAN_1_VOL_2 = 37;
  public static final int SIMPLE_COOL_FAN_2_VOL_1 = 38;
  public static final int SIMPLE_COOL_FAN_2_VOL_2 = 39;
  public static final int SIMPLE_COOL_FAN_3_VOL_1 = 40;
  public static final int SIMPLE_COOL_FAN_3_VOL_2 = 41;
  public static final int SIMPLE_COOL_FAN_4_VOL_1 = 42;
  public static final int SIMPLE_COOL_FAN_4_VOL_2 = 43;
  public static final int SIMPLE_COOL_FAN_5_VOL_1 = 44;
  public static final int SIMPLE_COOL_FAN_5_VOL_2 = 45;
  public static final int SIMPLE_COOL_FAN_LIST = 94;
  public static final int SIMPLE_COOL_FAN_TABLE = 87;
  public static final int SIMPLE_ERR_AC_BIG = 163;
  public static final int SIMPLE_ERR_AC_LIST = 167;
  public static final int SIMPLE_ERR_AC_TABLE = 165;
  public static final int SIMPLE_ERR_COM_BIG = 164;
  public static final int SIMPLE_ERR_COM_LIST = 168;
  public static final int SIMPLE_ERR_COM_TABLE = 166;
  public static final int SIMPLE_EXPANSION = 161;
  public static final int SIMPLE_FAN_1 = 102;
  public static final int SIMPLE_FAN_1_VOL_1 = 66;
  public static final int SIMPLE_FAN_1_VOL_2 = 67;
  public static final int SIMPLE_FAN_2 = 103;
  public static final int SIMPLE_FAN_2_VOL_1 = 68;
  public static final int SIMPLE_FAN_2_VOL_2 = 69;
  public static final int SIMPLE_FAN_3 = 104;
  public static final int SIMPLE_FAN_3_VOL_1 = 70;
  public static final int SIMPLE_FAN_3_VOL_2 = 71;
  public static final int SIMPLE_FAN_4 = 105;
  public static final int SIMPLE_FAN_4_VOL_1 = 72;
  public static final int SIMPLE_FAN_4_VOL_2 = 73;
  public static final int SIMPLE_FAN_5 = 106;
  public static final int SIMPLE_FAN_5_VOL_1 = 74;
  public static final int SIMPLE_FAN_5_VOL_2 = 75;
  private static final int SIMPLE_FAN_BASE = 101;
  public static final int SIMPLE_FAN_LIST = 97;
  public static final int SIMPLE_FAN_STOP = 101;
  public static final int SIMPLE_FAN_SWING_1 = 107;
  public static final int SIMPLE_FAN_SWING_2 = 108;
  public static final int SIMPLE_FAN_SWING_3 = 109;
  public static final int SIMPLE_FAN_SWING_4 = 110;
  public static final int SIMPLE_FAN_SWING_5 = 111;
  public static final int SIMPLE_FAN_TABLE = 90;
  public static final int SIMPLE_FAN_VENT_AUTO = 127;
  public static final int SIMPLE_FAN_VENT_HIGH = 126;
  public static final int SIMPLE_FAN_VENT_LOW = 125;
  public static final int SIMPLE_FAN_VENT_STOP = 124;
  public static final int SIMPLE_HEAT_FAN_1_VOL_1 = 46;
  public static final int SIMPLE_HEAT_FAN_1_VOL_2 = 47;
  public static final int SIMPLE_HEAT_FAN_2_VOL_1 = 48;
  public static final int SIMPLE_HEAT_FAN_2_VOL_2 = 49;
  public static final int SIMPLE_HEAT_FAN_3_VOL_1 = 50;
  public static final int SIMPLE_HEAT_FAN_3_VOL_2 = 51;
  public static final int SIMPLE_HEAT_FAN_4_VOL_1 = 52;
  public static final int SIMPLE_HEAT_FAN_4_VOL_2 = 53;
  public static final int SIMPLE_HEAT_FAN_5_VOL_1 = 54;
  public static final int SIMPLE_HEAT_FAN_5_VOL_2 = 55;
  public static final int SIMPLE_HEAT_FAN_LIST = 95;
  public static final int SIMPLE_HEAT_FAN_TABLE = 88;
  private static final int SIMPLE_ICON_AUTO_BASE = 56;
  private static final int SIMPLE_ICON_BASE = 35;
  private static final int SIMPLE_ICON_COOL_BASE = 36;
  private static final int SIMPLE_ICON_FAN_BASE = 66;
  private static final int SIMPLE_ICON_HEAT_BASE = 46;
  private static final int SIMPLE_ICON_NUM = 12;
  private static final int SIMPLE_ICON_SUBMIT_BASE = 76;
  private static final int SIMPLE_LIGHT_BASE = 149;
  public static final int SIMPLE_LIGHT_OFF_BIG = 149;
  public static final int SIMPLE_LIGHT_OFF_LIST = 153;
  public static final int SIMPLE_LIGHT_OFF_TABLE = 151;
  public static final int SIMPLE_LIGHT_ON_BIG = 150;
  public static final int SIMPLE_LIGHT_ON_LIST = 154;
  public static final int SIMPLE_LIGHT_ON_TABLE = 152;
  public static final int SIMPLE_NORMAL_VENT = 123;
  private static final int SIMPLE_OTHER_BASE = 161;
  public static final int SIMPLE_REDUCTION = 162;
  public static final int SIMPLE_REFRESH = 176;
  public static final int SIMPLE_REMOCON_0 = 177;
  public static final int SIMPLE_REMOCON_1 = 178;
  public static final int SIMPLE_REMOCON_2 = 179;
  public static final int SIMPLE_REMOCON_3 = 180;
  public static final int SIMPLE_ROOMTEMP_LIST = 100;
  public static final int SIMPLE_SIGN_CONTROL = 173;
  public static final int SIMPLE_SIGN_CTRLTARGET = 172;
  public static final int SIMPLE_SIGN_FILTER = 171;
  public static final int SIMPLE_SIGN_FORCE_STOP = 174;
  public static final int SIMPLE_SIGN_LOCK = 170;
  public static final int SIMPLE_SIGN_ROOMTEMP = 169;
  public static final int SIMPLE_SUBMIT_FAN_1_VOL_1 = 76;
  public static final int SIMPLE_SUBMIT_FAN_1_VOL_2 = 77;
  public static final int SIMPLE_SUBMIT_FAN_2_VOL_1 = 78;
  public static final int SIMPLE_SUBMIT_FAN_2_VOL_2 = 79;
  public static final int SIMPLE_SUBMIT_FAN_3_VOL_1 = 80;
  public static final int SIMPLE_SUBMIT_FAN_3_VOL_2 = 81;
  public static final int SIMPLE_SUBMIT_FAN_4_VOL_1 = 82;
  public static final int SIMPLE_SUBMIT_FAN_4_VOL_2 = 83;
  public static final int SIMPLE_SUBMIT_FAN_5_VOL_1 = 84;
  public static final int SIMPLE_SUBMIT_FAN_5_VOL_2 = 85;
  public static final int SIMPLE_SUBMIT_FAN_LIST = 98;
  public static final int SIMPLE_SUBMIT_FAN_TABLE = 91;
  private static final int SIMPLE_SWITCH_BASE = 155;
  private static final int SIMPLE_SWITCH_G_BIG = 6;
  private static final int SIMPLE_SWITCH_G_MID = 8;
  private static final int SIMPLE_SWITCH_G_SMALL = 10;
  public static final int SIMPLE_SWITCH_OFF_BIG = 155;
  public static final int SIMPLE_SWITCH_OFF_LIST = 159;
  public static final int SIMPLE_SWITCH_OFF_TABLE = 157;
  public static final int SIMPLE_SWITCH_ON_BIG = 156;
  public static final int SIMPLE_SWITCH_ON_LIST = 160;
  public static final int SIMPLE_SWITCH_ON_TABLE = 158;
  private static final int SIMPLE_SWITCH_R_BIG = 7;
  private static final int SIMPLE_SWITCH_R_MID = 9;
  private static final int SIMPLE_SWITCH_R_SMALL = 11;
  public static final int SIMPLE_THERMOMETER_LIST = 99;
  public static final int SIMPLE_THERMOMETER_TABLE = 92;
  public static final int SIMPLE_TOTAL_VENT = 122;
  public static final int SIMPLE_VENT_AUTO_AUTO = 131;
  public static final int SIMPLE_VENT_AUTO_FRESHUP = 138;
  public static final int SIMPLE_VENT_AUTO_HIGH = 130;
  public static final int SIMPLE_VENT_AUTO_LIST = 146;
  public static final int SIMPLE_VENT_AUTO_LOW = 129;
  public static final int SIMPLE_VENT_AUTO_TABLE = 142;
  private static final int SIMPLE_VENT_BASE = 121;
  public static final int SIMPLE_VENT_BIG = 128;
  public static final int SIMPLE_VENT_LIST = 145;
  public static final int SIMPLE_VENT_NORMAL_AUTO = 137;
  public static final int SIMPLE_VENT_NORMAL_FRESHUP = 140;
  public static final int SIMPLE_VENT_NORMAL_HIGH = 136;
  public static final int SIMPLE_VENT_NORMAL_LIST = 148;
  public static final int SIMPLE_VENT_NORMAL_LOW = 135;
  public static final int SIMPLE_VENT_NORMAL_TABLE = 144;
  public static final int SIMPLE_VENT_TABLE = 141;
  public static final int SIMPLE_VENT_TOTAL_AUTO = 134;
  public static final int SIMPLE_VENT_TOTAL_FRESHUP = 139;
  public static final int SIMPLE_VENT_TOTAL_HIGH = 133;
  public static final int SIMPLE_VENT_TOTAL_LIST = 147;
  public static final int SIMPLE_VENT_TOTAL_LOW = 132;
  public static final int SIMPLE_VENT_TOTAL_TABLE = 143;
  public static final int SIMPLE_VOL_1 = 113;
  public static final int SIMPLE_VOL_2 = 114;
  private static final int SIMPLE_VOL_BASE = 112;
  public static final int SIMPLE_VOL_STOP = 112;
  private static final int SIMPLE_ZONE_BASE = 115;
  private static final int SIMPLE_ZONE_G_BIG = 0;
  private static final int SIMPLE_ZONE_G_MID = 2;
  private static final int SIMPLE_ZONE_G_SMALL = 4;
  public static final int SIMPLE_ZONE_OFF_BIG = 115;
  public static final int SIMPLE_ZONE_OFF_MID = 117;
  public static final int SIMPLE_ZONE_OFF_SMALL = 119;
  public static final int SIMPLE_ZONE_ON_BIG = 116;
  public static final int SIMPLE_ZONE_ON_MID = 118;
  public static final int SIMPLE_ZONE_ON_SMALL = 120;
  private static final int SIMPLE_ZONE_R_BIG = 1;
  private static final int SIMPLE_ZONE_R_MID = 3;
  private static final int SIMPLE_ZONE_R_SMALL = 5;
  public static final int SIMPLE_ZONE_THERMOMETER_TABLE = 175;
  public static final int SMALL_COMM_ERR = 5;
  public static final int SMALL_HOME = 6;
  public static final int SMALL_OFF = 2;
  public static final int SMALL_ON = 3;
  public static final int SMALL_PPD = 31;
  public static final int SMALL_SCHEDULE = 30;
  public static final int SMALL_STAT_ERR = 4;
  public static final int SMALL_SYSTEM_SETTING = 32;
  public static final int SMALL_ZONE_COMM_ERR = 10;
  public static final int SMALL_ZONE_OFF = 8;
  public static final int SMALL_ZONE_ON = 7;
  public static final int SMALL_ZONE_STAT_ERR = 9;
  public static final int SYSTEM = 20;
  public static final int SYSTEM_PASSWORD = 33;
  public static final int SYSTEM_USER = 34;
  public static final int WAIT = 18;
  public static final int WARNING = 27;
  private static boolean mGreenIsOff = true;
  private static ImageIcon[] mImageIconArray = new ImageIcon['µ'];
  private static ItcIcon[] mItcIconArray = new ItcIcon[72];
  private static ImageIcon[] mSimpleIconArray = new ImageIcon[12];
  
  public IconRes() {}
  
  private static Image getColoredImage(Image paramImage, int paramInt)
  {
    switch (paramInt)
    {
    case 0: 
      return GraphicLibrary.getColoredImage(paramImage, 3.0D, 0.5D, 0.3D);
    case 1: 
      return GraphicLibrary.getColoredImage(paramImage, 0.4D, 0.6D, 5.0D);
    case 2: 
      return GraphicLibrary.getColoredImage(paramImage, 0.4D, 3.0D, 0.2D);
    case 3: 
      return GraphicLibrary.getColoredImage(paramImage, 0.7D, 5.0D, 2.0D);
    case 4: 
      return GraphicLibrary.getColoredImage(paramImage, 1.0D, 0.3D, 2.0D);
    case 5: 
      return GraphicLibrary.getColoredImage(paramImage, 0.7D, 3.0D, 0.6D);
    case 6: 
      return GraphicLibrary.getColoredImage(paramImage, 2.0D, 1.0D, 1.0D);
    case 7: 
      return GraphicLibrary.getColoredImage(paramImage, 0.9D, 4.0D, 3.0D);
    case 8: 
      return GraphicLibrary.getColoredImage(paramImage, 0.9D, 3.0D, 0.8D);
    case 9: 
      return GraphicLibrary.getColoredImage(paramImage, 4.0D, 2.0D, 2.0D);
    case 10: 
      return GraphicLibrary.getColoredImage(paramImage, 0.9D, 4.0D, 3.0D);
    }
    return null;
  }
  
  public static ImageIcon getIcon(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= 181)) {
      CommonUse.AppErr(new ArrayIndexOutOfBoundsException(paramInt), "IconRes.getIcon");
    }
    if (mImageIconArray[paramInt] != null) {
      return mImageIconArray[paramInt];
    }
    Image localImage;
    switch (paramInt)
    {
    case 0: 
      throw new FatalException("IconRes.getIcon(RC_BACK)");
    case 1: 
      throw new FatalException("IconRes.getIcon(DAIKIN)");
    case 3: 
      if (mGreenIsOff == true) {
        mImageIconArray[paramInt] = getImageIcon("vrv_sred.png");
      } else {
        mImageIconArray[paramInt] = getImageIcon("vrv_sgreen.png");
      }
      break;
    case 2: 
      if (mGreenIsOff == true) {
        mImageIconArray[paramInt] = getImageIcon("vrv_sgreen2.png");
      } else {
        mImageIconArray[paramInt] = getImageIcon("vrv_sred2.png");
      }
      break;
    case 4: 
      mImageIconArray[paramInt] = getImageIcon("vrv_serr.png");
      break;
    case 5: 
      mImageIconArray[paramInt] = getImageIcon("vrv_scom.png");
      break;
    case 6: 
      mImageIconArray[paramInt] = getImageIcon("shome.png");
      break;
    case 7: 
      mImageIconArray[paramInt] = getImageIcon("szon_on.gif");
      break;
    case 8: 
      mImageIconArray[paramInt] = getImageIcon("simple_zone_off_small.gif");
      break;
    case 9: 
      mImageIconArray[paramInt] = getImageIcon("szon_err.gif");
      break;
    case 10: 
      mImageIconArray[paramInt] = getImageIcon("szon_com.gif");
      break;
    case 11: 
      mImageIconArray[paramInt] = getImageIcon("back.gif");
      break;
    case 12: 
      mImageIconArray[paramInt] = getImageIcon("fore.gif");
      break;
    case 13: 
      mImageIconArray[paramInt] = getImageIcon("refresh.gif");
      break;
    case 14: 
      mImageIconArray[paramInt] = getImageIcon("icon.gif");
      break;
    case 15: 
      mImageIconArray[paramInt] = getImageIcon("list.gif");
      break;
    case 16: 
      mImageIconArray[paramInt] = getImageIcon("help.png");
      return mImageIconArray[paramInt];
    case 17: 
      mImageIconArray[paramInt] = getImageIcon("auth.png");
      break;
    case 18: 
      mImageIconArray[paramInt] = getImageIcon("wait.gif");
      break;
    case 19: 
      mImageIconArray[paramInt] = getImageIcon("logoff.gif");
      break;
    case 20: 
      mImageIconArray[paramInt] = getImageIcon("system.png");
      break;
    case 21: 
      mImageIconArray[paramInt] = getImageIcon("circle_red.gif");
      break;
    case 22: 
      mImageIconArray[paramInt] = getImageIcon("circle_yellow.gif");
      break;
    case 23: 
      mImageIconArray[paramInt] = getImageIcon("circle_cyan.gif");
      break;
    case 24: 
      mImageIconArray[paramInt] = getImageIcon("circle_magenta.gif");
      break;
    case 25: 
      mImageIconArray[paramInt] = getImageIcon("earth.gif");
      break;
    case 26: 
      mImageIconArray[paramInt] = getImageIcon("error.gif");
      break;
    case 27: 
      mImageIconArray[paramInt] = getImageIcon("warning.gif");
      break;
    case 28: 
      mImageIconArray[paramInt] = getImageIcon("info.gif");
      break;
    case 29: 
      mImageIconArray[paramInt] = getImageIcon("question.gif");
      break;
    case 30: 
      mImageIconArray[paramInt] = getImageIcon("s_sched.gif");
      break;
    case 31: 
      mImageIconArray[paramInt] = getImageIcon("s_ppd.gif");
      break;
    case 32: 
      mImageIconArray[paramInt] = getImageIcon("s_system.gif");
      break;
    case 33: 
      mImageIconArray[paramInt] = getImageIcon("password.gif");
      break;
    case 34: 
      mImageIconArray[paramInt] = getImageIcon("user.gif");
      break;
    case 35: 
      mImageIconArray[paramInt] = getImageIcon("simple_ac_big.png");
      break;
    case 36: 
      localImage = getImageIcon("simple_fan11_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 37: 
      localImage = getImageIcon("simple_fan12_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 38: 
      localImage = getImageIcon("simple_fan21_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 39: 
      localImage = getImageIcon("simple_fan22_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 40: 
      localImage = getImageIcon("simple_fan31_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 41: 
      localImage = getImageIcon("simple_fan32_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 42: 
      localImage = getImageIcon("simple_fan41_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 43: 
      localImage = getImageIcon("simple_fan42_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 44: 
      localImage = getImageIcon("simple_fan51_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 45: 
      localImage = getImageIcon("simple_fan52_big.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 46: 
      localImage = getImageIcon("simple_fan11_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 47: 
      localImage = getImageIcon("simple_fan12_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 48: 
      localImage = getImageIcon("simple_fan21_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 49: 
      localImage = getImageIcon("simple_fan22_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 50: 
      localImage = getImageIcon("simple_fan31_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 51: 
      localImage = getImageIcon("simple_fan32_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 52: 
      localImage = getImageIcon("simple_fan41_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 53: 
      localImage = getImageIcon("simple_fan42_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 54: 
      localImage = getImageIcon("simple_fan51_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 55: 
      localImage = getImageIcon("simple_fan52_big.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 56: 
      localImage = getImageIcon("simple_fan11_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 57: 
      localImage = getImageIcon("simple_fan12_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 58: 
      localImage = getImageIcon("simple_fan21_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 59: 
      localImage = getImageIcon("simple_fan22_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 60: 
      localImage = getImageIcon("simple_fan31_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 61: 
      localImage = getImageIcon("simple_fan32_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 62: 
      localImage = getImageIcon("simple_fan41_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 63: 
      localImage = getImageIcon("simple_fan42_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 64: 
      localImage = getImageIcon("simple_fan51_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 65: 
      localImage = getImageIcon("simple_fan52_big.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 66: 
      localImage = getImageIcon("simple_fan11_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 67: 
      localImage = getImageIcon("simple_fan12_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 68: 
      localImage = getImageIcon("simple_fan21_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 69: 
      localImage = getImageIcon("simple_fan22_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 70: 
      localImage = getImageIcon("simple_fan31_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 71: 
      localImage = getImageIcon("simple_fan32_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 72: 
      localImage = getImageIcon("simple_fan41_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 73: 
      localImage = getImageIcon("simple_fan42_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 74: 
      localImage = getImageIcon("simple_fan51_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 75: 
      localImage = getImageIcon("simple_fan52_big.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 76: 
      localImage = getImageIcon("simple_fan11_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 77: 
      localImage = getImageIcon("simple_fan12_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 78: 
      localImage = getImageIcon("simple_fan21_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 79: 
      localImage = getImageIcon("simple_fan22_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 80: 
      localImage = getImageIcon("simple_fan31_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 81: 
      localImage = getImageIcon("simple_fan32_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 82: 
      localImage = getImageIcon("simple_fan41_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 83: 
      localImage = getImageIcon("simple_fan42_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 84: 
      localImage = getImageIcon("simple_fan51_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 85: 
      localImage = getImageIcon("simple_fan52_big.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 86: 
      mImageIconArray[paramInt] = getImageIcon("simple_ac_mid.png");
      break;
    case 87: 
      localImage = getImageIcon("simple_fan_mid.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 88: 
      localImage = getImageIcon("simple_fan_mid.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 89: 
      localImage = getImageIcon("simple_fan_mid.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 90: 
      localImage = getImageIcon("simple_fan_mid.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 91: 
      localImage = getImageIcon("simple_fan_mid.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 141: 
      localImage = getIcon(128).getImage();
      localImage = GraphicLibrary.getScaleImage(localImage, new Dimension(98, 50));
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 142: 
      localImage = getIcon(130).getImage();
      localImage = GraphicLibrary.getScaleImage(localImage, new Dimension(98, 50));
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 143: 
      localImage = getIcon(133).getImage();
      localImage = GraphicLibrary.getScaleImage(localImage, new Dimension(98, 50));
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 144: 
      localImage = getIcon(136).getImage();
      localImage = GraphicLibrary.getScaleImage(localImage, new Dimension(98, 50));
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 92: 
      mImageIconArray[paramInt] = getImageIcon("simple_thermometer_mid.png");
      break;
    case 175: 
      mImageIconArray[paramInt] = getImageIcon("simple_zone_thermometer_mid.png");
      break;
    case 93: 
      mImageIconArray[paramInt] = getImageIcon("simple_ac_small.png");
      break;
    case 94: 
      localImage = getImageIcon("simple_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 1);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 95: 
      localImage = getImageIcon("simple_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 0);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 96: 
      localImage = getImageIcon("simple_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 2);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 97: 
      localImage = getImageIcon("simple_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 3);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 98: 
      localImage = getImageIcon("simple_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 4);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 145: 
      mImageIconArray[paramInt] = getImageIcon("simple_vent_small.png");
      break;
    case 146: 
      localImage = getImageIcon("simple_vent_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 5);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 147: 
      localImage = getImageIcon("simple_vent_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 6);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 148: 
      localImage = getImageIcon("simple_vent_fan_small.png").getImage();
      localImage = getColoredImage(localImage, 7);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 99: 
      localImage = getImageIcon("simple_thermometer_small.gif").getImage();
      localImage = GraphicLibrary.getTransparentImage(localImage, Color.BLACK, false);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 100: 
      localImage = getImageIcon("simple_roomtemp_small.gif").getImage();
      localImage = GraphicLibrary.getTransparentImage(localImage, Color.BLACK, false);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 101: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_dir_stop.gif");
      break;
    case 102: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_dir_01.gif");
      break;
    case 103: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_dir_02.gif");
      break;
    case 104: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_dir_03.gif");
      break;
    case 105: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_dir_04.gif");
      break;
    case 106: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_dir_05.gif");
      break;
    case 107: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_swing_01.gif");
      break;
    case 108: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_swing_02.gif");
      break;
    case 109: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_swing_03.gif");
      break;
    case 110: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_swing_04.gif");
      break;
    case 111: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_swing_05.gif");
      break;
    case 112: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_vol_stop.gif");
      break;
    case 113: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_vol_01.gif");
      break;
    case 114: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_vol_02.gif");
      break;
    case 115: 
      mImageIconArray[paramInt] = getImageIcon("simple_zone_off_big.gif");
      break;
    case 116: 
      if (mGreenIsOff == true) {
        mImageIconArray[paramInt] = getSimpleIcon(1);
      } else {
        mImageIconArray[paramInt] = getSimpleIcon(0);
      }
      break;
    case 117: 
      mImageIconArray[paramInt] = getImageIcon("simple_zone_off_mid.gif");
      break;
    case 118: 
      if (mGreenIsOff == true) {
        mImageIconArray[paramInt] = getSimpleIcon(3);
      } else {
        mImageIconArray[paramInt] = getSimpleIcon(2);
      }
      break;
    case 119: 
      localImage = getImageIcon("simple_zone_off_small.gif").getImage();
      localImage = GraphicLibrary.getTransparentImage(localImage, Color.WHITE, false);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 120: 
      if (mGreenIsOff == true)
      {
        localImage = getSimpleIcon(5).getImage();
        localImage = GraphicLibrary.getTransparentImage(localImage, Color.WHITE, false);
        mImageIconArray[paramInt] = new ImageIcon(localImage);
      }
      else
      {
        localImage = getSimpleIcon(4).getImage();
        localImage = GraphicLibrary.getTransparentImage(localImage, Color.WHITE, false);
        mImageIconArray[paramInt] = new ImageIcon(localImage);
      }
      break;
    case 128: 
      mImageIconArray[paramInt] = getImageIcon("simple_vent_big.png");
      break;
    case 130: 
      localImage = getImageIcon("simple_vent_high_big.png").getImage();
      localImage = getColoredImage(localImage, 5);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 129: 
      localImage = getImageIcon("simple_vent_low_big.png").getImage();
      localImage = getColoredImage(localImage, 5);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 131: 
      localImage = getImageIcon("simple_vent_auto_big.png").getImage();
      localImage = getColoredImage(localImage, 5);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 133: 
      localImage = getImageIcon("simple_vent_high_big.png").getImage();
      localImage = getColoredImage(localImage, 6);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 132: 
      localImage = getImageIcon("simple_vent_low_big.png").getImage();
      localImage = getColoredImage(localImage, 6);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 134: 
      localImage = getImageIcon("simple_vent_auto_big.png").getImage();
      localImage = getColoredImage(localImage, 6);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 136: 
      localImage = getImageIcon("simple_vent_high_big.png").getImage();
      localImage = getColoredImage(localImage, 7);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 135: 
      localImage = getImageIcon("simple_vent_low_big.png").getImage();
      localImage = getColoredImage(localImage, 7);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 137: 
      localImage = getImageIcon("simple_vent_auto_big.png").getImage();
      localImage = getColoredImage(localImage, 7);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 138: 
      localImage = getImageIcon("simple_vent_freshup_big.png").getImage();
      localImage = getColoredImage(localImage, 8);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 139: 
      localImage = getImageIcon("simple_vent_freshup_big.png").getImage();
      localImage = getColoredImage(localImage, 9);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 140: 
      localImage = getImageIcon("simple_vent_freshup_big.png").getImage();
      localImage = getColoredImage(localImage, 10);
      mImageIconArray[paramInt] = new ImageIcon(localImage);
      break;
    case 121: 
      mImageIconArray[paramInt] = getImageIcon("simple_auto_vent.png");
      break;
    case 122: 
      mImageIconArray[paramInt] = getImageIcon("simple_total_vent.png");
      break;
    case 123: 
      mImageIconArray[paramInt] = getImageIcon("simple_normal_vent.png");
      break;
    case 124: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_vent_stop.gif");
      break;
    case 125: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_vent_01.gif");
      break;
    case 126: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_vent_02.gif");
      break;
    case 127: 
      mImageIconArray[paramInt] = getImageIcon("simple_fan_vent_auto.gif");
      break;
    case 149: 
      mImageIconArray[paramInt] = getImageIcon("simple_light_off_big.gif");
      break;
    case 150: 
      mImageIconArray[paramInt] = getImageIcon("simple_light_on_big.gif");
      break;
    case 151: 
      mImageIconArray[paramInt] = getImageIcon("simple_light_off_mid.gif");
      break;
    case 152: 
      mImageIconArray[paramInt] = getImageIcon("simple_light_on_mid.gif");
      break;
    case 153: 
      mImageIconArray[paramInt] = getImageIcon("simple_light_off_small.gif");
      break;
    case 154: 
      mImageIconArray[paramInt] = getImageIcon("simple_light_on_small.gif");
      break;
    case 155: 
      mImageIconArray[paramInt] = getImageIcon("simple_switch_off_big.gif");
      break;
    case 156: 
      if (mGreenIsOff == true) {
        mImageIconArray[paramInt] = getSimpleIcon(7);
      } else {
        mImageIconArray[paramInt] = getSimpleIcon(6);
      }
      break;
    case 157: 
      mImageIconArray[paramInt] = getImageIcon("simple_switch_off_mid.gif");
      break;
    case 158: 
      if (mGreenIsOff == true) {
        mImageIconArray[paramInt] = getSimpleIcon(9);
      } else {
        mImageIconArray[paramInt] = getSimpleIcon(8);
      }
      break;
    case 159: 
      mImageIconArray[paramInt] = getImageIcon("simple_switch_off_small.gif");
      break;
    case 160: 
      if (mGreenIsOff == true) {
        mImageIconArray[paramInt] = getSimpleIcon(11);
      } else {
        mImageIconArray[paramInt] = getSimpleIcon(10);
      }
      break;
    case 163: 
      mImageIconArray[paramInt] = getImageIcon("simple_acerr_big.gif");
      break;
    case 164: 
      mImageIconArray[paramInt] = getImageIcon("simple_comerr_big.gif");
      break;
    case 165: 
      mImageIconArray[paramInt] = getImageIcon("simple_acerr_mid.gif");
      break;
    case 166: 
      mImageIconArray[paramInt] = getImageIcon("simple_comerr_mid.gif");
      break;
    case 167: 
      mImageIconArray[paramInt] = getImageIcon("simple_acerr_small.gif");
      break;
    case 168: 
      mImageIconArray[paramInt] = getImageIcon("simple_comerr_small.gif");
      break;
    case 176: 
      mImageIconArray[paramInt] = getImageIcon("simple_refresh.gif");
      break;
    case 170: 
      mImageIconArray[paramInt] = getImageIcon("simple_sign_lock.gif");
      break;
    case 171: 
      mImageIconArray[paramInt] = getImageIcon("simple_sign_filter.gif");
      break;
    case 172: 
      mImageIconArray[paramInt] = getImageIcon("simple_ctrl_target.png");
      break;
    case 173: 
      mImageIconArray[paramInt] = getImageIcon("simple_control.png");
      break;
    case 174: 
      mImageIconArray[paramInt] = getImageIcon("simple_force_stop.png");
      break;
    case 169: 
      mImageIconArray[paramInt] = getImageIcon("simple_sign_roomtemp.gif");
      break;
    case 161: 
      mImageIconArray[paramInt] = getImageIcon("simple_expansion.gif");
      break;
    case 162: 
      mImageIconArray[paramInt] = getImageIcon("simple_reduction.gif");
      break;
    case 177: 
      mImageIconArray[paramInt] = getImageIcon("simple_remocon_0.png");
      break;
    case 178: 
      mImageIconArray[paramInt] = getImageIcon("simple_remocon_1.png");
      break;
    case 179: 
      mImageIconArray[paramInt] = getImageIcon("simple_remocon_2.png");
      break;
    case 180: 
      mImageIconArray[paramInt] = getImageIcon("simple_remocon_3.png");
      break;
    default: 
      CommonUse.AppErr(new ArrayIndexOutOfBoundsException(paramInt), "IconRes.getIcon");
    }
    return mImageIconArray[paramInt];
  }
  
  private static ImageIcon getImageIcon(String paramString)
  {
    byte[] arrayOfByte = new byte[' '];
    Class localClass = AppletAbst.getInstance().getClass();
    InputStream localInputStream = localClass.getResourceAsStream("/webitc/images/" + paramString);
    if (localInputStream == null) {
      throw new FatalException(paramString);
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      int i;
      while ((i = localInputStream.read(arrayOfByte)) != -1) {
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      return null;
    }
    catch (IOException localIOException2) {}finally
    {
      try
      {
        localInputStream.close();
        localByteArrayOutputStream.close();
        return new ImageIcon(localByteArrayOutputStream.toByteArray());
      }
      catch (IOException localIOException4) {}catch (NullPointerException localNullPointerException3) {}
    }
  }
  
  private static ItcIcon getItcIcon(String paramString)
  {
    byte[] arrayOfByte = new byte[' '];
    Class localClass = AppletAbst.getInstance().getClass();
    InputStream localInputStream = localClass.getResourceAsStream("/webitc/images/" + paramString);
    if (localInputStream == null) {
      throw new FatalException(paramString);
    }
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      int i;
      while ((i = localInputStream.read(arrayOfByte)) != -1) {
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
      }
      return null;
    }
    catch (IOException localIOException2) {}finally
    {
      try
      {
        localInputStream.close();
        localByteArrayOutputStream.close();
        return new ItcIcon(localByteArrayOutputStream.toByteArray());
      }
      catch (IOException localIOException4) {}catch (NullPointerException localNullPointerException3) {}
    }
  }
  
  public static ItcIcon getItcIcon(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt1 * 4 + paramInt2;
    Object localObject;
    if (mItcIconArray[i] != null)
    {
      localObject = (ItcIcon)mItcIconArray[i].clone();
      ((ItcIcon)localObject).setState(paramInt3);
      return localObject;
    }
    if (paramInt1 > 9) {
      localObject = Integer.toString(paramInt1);
    } else {
      localObject = new String("0" + paramInt1);
    }
    switch (paramInt2)
    {
    case 1: 
      if (mGreenIsOff == true) {
        mItcIconArray[i] = getItcIcon("icon" + (String)localObject + "_1.gif");
      } else {
        mItcIconArray[i] = getItcIcon("icon" + (String)localObject + "_5.gif");
      }
      break;
    case 0: 
      if (mGreenIsOff == true) {
        mItcIconArray[i] = getItcIcon("icon" + (String)localObject + "_0.gif");
      } else {
        mItcIconArray[i] = getItcIcon("icon" + (String)localObject + "_4.gif");
      }
      break;
    case 2: 
      mItcIconArray[i] = getItcIcon("icon" + (String)localObject + "_2.gif");
      break;
    case 3: 
      mItcIconArray[i] = getItcIcon("icon" + (String)localObject + "_3.gif");
      break;
    default: 
      CommonUse.AppErr(new ArrayIndexOutOfBoundsException(paramInt2), "IconRes.getStateIcon");
    }
    ItcIcon localItcIcon = (ItcIcon)mItcIconArray[i].clone();
    localItcIcon.setState(paramInt3);
    return localItcIcon;
  }
  
  private static ImageIcon getSimpleIcon(int paramInt)
  {
    if (mSimpleIconArray[paramInt] != null) {
      return mSimpleIconArray[paramInt];
    }
    switch (paramInt)
    {
    case 0: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_zone_green_big.gif");
      break;
    case 1: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_zone_red_big.gif");
      break;
    case 2: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_zone_green_mid.gif");
      break;
    case 3: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_zone_red_mid.gif");
      break;
    case 4: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_zone_green_small.gif");
      break;
    case 5: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_zone_red_small.gif");
      break;
    case 6: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_switch_green_big.gif");
      break;
    case 7: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_switch_red_big.gif");
      break;
    case 8: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_switch_green_mid.gif");
      break;
    case 9: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_switch_red_mid.gif");
      break;
    case 10: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_switch_green_small.gif");
      break;
    case 11: 
      mSimpleIconArray[paramInt] = getImageIcon("simple_switch_red_small.gif");
      break;
    default: 
      CommonUse.AppErr(new ArrayIndexOutOfBoundsException(paramInt), "IconRes.getSimpleIcon");
      return null;
    }
    return mSimpleIconArray[paramInt];
  }
  
  public static void setGreenOff(boolean paramBoolean)
  {
    mGreenIsOff = paramBoolean;
    mImageIconArray[3] = null;
    mImageIconArray[2] = null;
    for (int i = 0; i < 18; i++)
    {
      mItcIconArray[(i * 1)] = null;
      mItcIconArray[(i * 0)] = null;
    }
  }
}
