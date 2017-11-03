package webitc.common;

import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import webitc.common.enum2.EnumLang;
import webitc.gui.common.CommonUse;

public final class StrRes
{
  private static StrRes mObj = new StrRes();
  private ResourceBundle sRes;
  
  private StrRes()
  {
    mObj = this;
    setLocale(new Locale(EnumLang.ENGLISH.toString(), ""));
  }
  
  public static String getHMSStr2(Calendar paramCalendar, boolean paramBoolean)
  {
    if (paramCalendar.get(9) == 0) {
      str = itoa(paramCalendar.get(10), paramBoolean, 2) + ":";
    } else {
      str = itoa(paramCalendar.get(10) + 12, paramBoolean, 2) + ":";
    }
    String str = str + itoa(paramCalendar.get(12), paramBoolean, 2) + ":";
    str = str + itoa(paramCalendar.get(13), paramBoolean, 2);
    return str;
  }
  
  public static String getHMStr2(Calendar paramCalendar, boolean paramBoolean)
  {
    if (paramCalendar.get(9) == 0) {
      str = itoa(paramCalendar.get(10), paramBoolean, 2) + ":";
    } else {
      str = itoa(paramCalendar.get(10) + 12, paramBoolean, 2) + ":";
    }
    String str = str + itoa(paramCalendar.get(12), paramBoolean, 2);
    return str;
  }
  
  public static String getMonthStr(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
      return getStr("IDS_COMMON_MONTH_01");
    case 2: 
      return getStr("IDS_COMMON_MONTH_02");
    case 3: 
      return getStr("IDS_COMMON_MONTH_03");
    case 4: 
      return getStr("IDS_COMMON_MONTH_04");
    case 5: 
      return getStr("IDS_COMMON_MONTH_05");
    case 6: 
      return getStr("IDS_COMMON_MONTH_06");
    case 7: 
      return getStr("IDS_COMMON_MONTH_07");
    case 8: 
      return getStr("IDS_COMMON_MONTH_08");
    case 9: 
      return getStr("IDS_COMMON_MONTH_09");
    case 10: 
      return getStr("IDS_COMMON_MONTH_10");
    case 11: 
      return getStr("IDS_COMMON_MONTH_11");
    case 12: 
      return getStr("IDS_COMMON_MONTH_12");
    }
    return new String("");
  }
  
  public static String getSecStr(int paramInt, boolean paramBoolean)
  {
    return itoa(paramInt, paramBoolean, 2) + " " + getStr("IDS_COMMON_UNIT_SEC");
  }
  
  public static String getStr(String paramString)
  {
    try
    {
      return mObjsRes.getString(paramString);
    }
    catch (MissingResourceException localMissingResourceException)
    {
      localMissingResourceException.printStackTrace();
      CommonUse.showErrorDlg(paramString, "MissingResourceException");
    }
    return new String("");
  }
  
  public static String getYMDHMSStr(Calendar paramCalendar, boolean paramBoolean)
  {
    return getYMDStr2(paramCalendar, paramBoolean) + " " + getHMSStr2(paramCalendar, paramBoolean);
  }
  
  public static String getYMDHMStr(Calendar paramCalendar, boolean paramBoolean)
  {
    return getYMDStr2(paramCalendar, paramBoolean) + " " + getHMStr2(paramCalendar, paramBoolean);
  }
  
  public static String getYMDStr2(Calendar paramCalendar, boolean paramBoolean)
  {
    if ((SystemInfo.getLang() == EnumLang.JAPANESE) || (SystemInfo.getLang() == EnumLang.CHINESE) || (SystemInfo.getLang() == EnumLang.KOREAN))
    {
      str = itoa(paramCalendar.get(1), false, 0) + "/";
      str = str + itoa(paramCalendar.get(2) + 1, paramBoolean, 2) + "/";
      str = str + itoa(paramCalendar.get(5), paramBoolean, 2);
      return str;
    }
    String str = itoa(paramCalendar.get(5), paramBoolean, 2) + "/";
    str = str + itoa(paramCalendar.get(2) + 1, paramBoolean, 2) + "/";
    str = str + itoa(paramCalendar.get(1), false, 0);
    return str;
  }
  
  public static String getYMStr(int paramInt1, int paramInt2)
  {
    if ((SystemInfo.getLang() == EnumLang.JAPANESE) || (SystemInfo.getLang() == EnumLang.CHINESE) || (SystemInfo.getLang() == EnumLang.KOREAN))
    {
      str = Integer.toString(paramInt1) + getStr("IDS_COMMON_UNIT_YEAR") + " " + Integer.toString(paramInt2) + getStr("IDS_COMMON_UNIT_MONTH");
      return str;
    }
    String str = getMonthStr(paramInt2) + " " + Integer.toString(paramInt1);
    return str;
  }
  
  public static String itoa(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    if (paramBoolean)
    {
      StringBuffer localStringBuffer = new StringBuffer(Integer.toString(paramInt1));
      while (localStringBuffer.length() < paramInt2) {
        localStringBuffer.insert(0, 0);
      }
      return localStringBuffer.toString();
    }
    return Integer.toString(paramInt1);
  }
  
  public static String ltoa(long paramLong, boolean paramBoolean, int paramInt)
  {
    if (paramBoolean)
    {
      StringBuffer localStringBuffer = new StringBuffer(Long.toString(paramLong));
      while (localStringBuffer.length() < paramInt) {
        localStringBuffer.insert(0, 0);
      }
      return localStringBuffer.toString();
    }
    return Long.toString(paramLong);
  }
  
  public static void setLocale(Locale paramLocale)
  {
    try
    {
      if (mObj != null)
      {
        Class localClass = mObj.getClass();
        InputStream localInputStream = localClass.getResourceAsStream("/webitc/Res_" + paramLocale.getLanguage() + ".properties");
        mObjsRes = new PropertyResourceBundle(localInputStream);
      }
    }
    catch (Exception localException)
    {
      throw new FatalException("StrRes.setLang");
    }
  }
}
