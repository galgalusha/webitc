package webitc.common;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import webitc.common.enum2.EnumLang;
import webitc.common.enum2.EnumModelType;
import webitc.common.enum2.EnumSystemType;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.job.JobGetSysInit;
import webitc.job.JobGetSysVersion;

public class SystemInfo
{
  public static final int OPT_CHANGE_OVER = 8;
  public static final int OPT_DEMAND = 1;
  public static final int OPT_ECO_CTRL = 2;
  public static final int OPT_HMO = 16;
  public static final int OPT_INTERLOCK = 256;
  public static final int OPT_LON_GW = 128;
  public static final int OPT_SLIDING_TEMP = 64;
  public static final int OPT_TEMP_LIMIT = 32;
  public static final int OPT_TS_CTRL = 4;
  public static final int PPDDISP_DAY_EP = 2;
  public static final int PPDDISP_EP = 1;
  public static final int PPDDISP_GAS = 16;
  public static final int PPDDISP_NIGHT_EP = 4;
  public static final int PPDDISP_NO_DISTRIBUTE = 4096;
  public static final int PPDDISP_STOP_DAY_EP = 512;
  public static final int PPDDISP_STOP_EP = 256;
  public static final int PPDDISP_STOP_NIGHT_EP = 1024;
  public static final int PPDTYPE_ACLOAD = 0;
  public static final int PPDTYPE_DISABLE = -2;
  public static final int PPDTYPE_RUNTIME = 2;
  public static final int PPDTYPE_THERMOON = 1;
  public static final int PPDTYPE_TIMEUNIT = 3;
  public static final int PPDTYPE_UNKNOWN = -1;
  private static boolean mCentigrade;
  private static int mD3PortNum = 1;
  private static boolean mGreenIsOff;
  private static Locale mLocale;
  private static int mMajorVersion;
  private static int mMinorVersion;
  private static EnumModelType mModelType;
  private static int mOption;
  private static int mPPD;
  private static int mScheduleLowerTemp;
  private static int mScheduleUpperTemp;
  private static EnumSystemType mSystemType = EnumSystemType.ELSE;
  private static TimeZone mTimeZone;
  private static String mVersionStr;
  
  static
  {
    mModelType = EnumModelType.ELSE;
    mMajorVersion = -1;
    mMinorVersion = -1;
    mVersionStr = "";
    mGreenIsOff = true;
    mLocale = new Locale("en", "");
    mTimeZone = TimeZone.getDefault();
    mCentigrade = true;
    mPPD = 0;
    mOption = 0;
    mScheduleUpperTemp = 35;
    mScheduleLowerTemp = 15;
  }
  
  public SystemInfo() {}
  
  public static String getAppletVersion()
  {
    Package localPackage = AppletAbst.getInstance().getClass().getPackage();
    if (localPackage != null)
    {
      String str = localPackage.getSpecificationVersion();
      if (str != null) {
        return str;
      }
    }
    return "";
  }
  
  public static int getD3PortNum()
  {
    return mD3PortNum;
  }
  
  public static EnumLang getLang()
  {
    return EnumLang.getEnum(mLocale.getLanguage());
  }
  
  public static Calendar getLocalTime(int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance(mTimeZone);
    localCalendar.setTimeInMillis(paramInt * 1000L);
    return localCalendar;
  }
  
  public static int getMajorVersion()
  {
    return mMajorVersion;
  }
  
  public static int getMinorVersion()
  {
    return mMinorVersion;
  }
  
  public static EnumModelType getModelType()
  {
    return mModelType;
  }
  
  public static int getPpdDisplayPattern()
  {
    EnumModelType localEnumModelType = getModelType();
    int i = getPpdType();
    int j = 0;
    if (localEnumModelType == EnumModelType.VEUP)
    {
      if (i == 0) {
        j = 5654;
      } else if ((i == 1) || (i == 2)) {
        j = 273;
      } else if (i == 3) {
        j = 17;
      }
    }
    else if (localEnumModelType == EnumModelType.ITC)
    {
      if (i == 0) {
        j = 257;
      }
    }
    else if ((localEnumModelType == EnumModelType.ACS) || (localEnumModelType == EnumModelType.EXC)) {
      if ((i == 0) || (i == 1) || (i == 2)) {
        j = 273;
      } else if (i == 3) {
        j = 17;
      }
    }
    return j;
  }
  
  public static int getPpdType()
  {
    return mPPD;
  }
  
  public static int getScheduleLowerTemp()
  {
    return mScheduleLowerTemp;
  }
  
  public static int getScheduleUpperTemp()
  {
    return mScheduleUpperTemp;
  }
  
  public static EnumSystemType getSystemType()
  {
    return mSystemType;
  }
  
  public static TimeZone getTimeZone()
  {
    return (TimeZone)mTimeZone.clone();
  }
  
  public static String getVersionString()
  {
    return mVersionStr;
  }
  
  public static boolean isCentigrade()
  {
    return mCentigrade;
  }
  
  public static boolean isChangeOver()
  {
    return (mOption & 0x8) != 0;
  }
  
  public static boolean isDemand()
  {
    return (mOption & 0x1) != 0;
  }
  
  public static boolean isEcoCtrl()
  {
    return (mOption & 0x2) != 0;
  }
  
  public static boolean isHMO()
  {
    return (mOption & 0x10) != 0;
  }
  
  public static boolean isSlidingTemp()
  {
    return (mOption & 0x40) != 0;
  }
  
  public static boolean isTSCtrl()
  {
    return (mOption & 0x4) != 0;
  }
  
  public static boolean isTempLimit()
  {
    return (mOption & 0x20) != 0;
  }
  
  private static void setCentigrade(boolean paramBoolean)
  {
    mCentigrade = paramBoolean;
  }
  
  private static void setGreenIsOff(boolean paramBoolean)
  {
    mGreenIsOff = paramBoolean;
    IconRes.setGreenOff(mGreenIsOff);
    ColorRes.setGreenOff(mGreenIsOff);
  }
  
  public static void setInfo(JobGetSysInit paramJobGetSysInit)
  {
    setGreenIsOff(paramJobGetSysInit.isGreenOff());
    if ((getModelType() == EnumModelType.ACS) || (getModelType() == EnumModelType.EXC)) {
      setLang(EnumLang.JAPANESE);
    } else {
      setLang(paramJobGetSysInit.getDispLanguage());
    }
    setTimeZone(paramJobGetSysInit.getTimeZone());
    setCentigrade(paramJobGetSysInit.isCentigrade());
    mPPD = paramJobGetSysInit.getPPDOption();
    mOption = paramJobGetSysInit.getOption();
    mScheduleUpperTemp = paramJobGetSysInit.getScheduleUpperTemp();
    mScheduleLowerTemp = paramJobGetSysInit.getScheduleLowerTemp();
    mD3PortNum = paramJobGetSysInit.getD3PortNum();
  }
  
  public static void setInfo(JobGetSysVersion paramJobGetSysVersion)
  {
    setSystemType(paramJobGetSysVersion.getSystemType());
    setModelType(paramJobGetSysVersion.getModelType());
    setVersion(paramJobGetSysVersion.getMajorVersion(), paramJobGetSysVersion.getMinorVersion(), paramJobGetSysVersion.getVersionStr());
  }
  
  public static void setLang(EnumLang paramEnumLang)
  {
    mLocale = new Locale(paramEnumLang.toString(), "");
    StrRes.setLocale(mLocale);
  }
  
  public static void setModelType(EnumModelType paramEnumModelType)
  {
    mModelType = paramEnumModelType;
  }
  
  public static void setSystemType(EnumSystemType paramEnumSystemType)
  {
    mSystemType = paramEnumSystemType;
  }
  
  private static void setTimeZone(TimeZone paramTimeZone)
  {
    mTimeZone = paramTimeZone;
  }
  
  public static void setVersion(int paramInt1, int paramInt2, String paramString)
  {
    mMajorVersion = paramInt1;
    mMinorVersion = paramInt2;
    mVersionStr = paramString;
  }
}
