package webitc;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumLang;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.JScrollTable;
import webitc.gui.login.PanelLogin;

public class CookieClerk
{
  public static final String Termination = ":";
  public static final String cookie_Identifier = "SETTING=";
  public static final String cookie_Invalid_Expires = ";expires=-1";
  public static final String cookie_Valid_Expires = ";expires=Tue, 31 Dec 2030 11:59:59 UTC";
  public static final String d_Dialog = "DIALOG";
  public static final String d_Lang = "LANG";
  public static final String d_Login = "LOGIN";
  public static final String d_Table = "TABLE";
  public static final String dp_Delimiter = "@";
  public static final String lr_Delimiter = "=";
  public static final String p_GuiMode = "GUI";
  public static final String p_Type = "TYPE";
  public static final String p_User = "USER";
  public static final String v_Delimiter = ",";
  
  public CookieClerk() {}
  
  private static String assembleSentence()
  {
    return getLang() + getLoginUser() + getGuiMode() + getDialog() + getTable();
  }
  
  private static String getDialog()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    HashMap localHashMap = DlgAbstract.getSizeMap();
    Set localSet = localHashMap.keySet();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext() == true)
    {
      String str = (String)localIterator.next();
      if (str == null) {
        return localStringBuffer.toString();
      }
      Dimension localDimension = (Dimension)localHashMap.get(str);
      if (localDimension == null) {
        return localStringBuffer.toString();
      }
      localStringBuffer.append("DIALOG@" + str + "=" + String.valueOf(width) + "," + String.valueOf(height) + ":");
    }
    return localStringBuffer.toString();
  }
  
  public static String getGuiMode()
  {
    String str = String.valueOf(PanelLogin.getPreGuiMode());
    if (str == null) {
      str = "";
    }
    return "LOGIN@GUI=" + str + ":";
  }
  
  private static String getLang()
  {
    return "LANG@TYPE=" + SystemInfo.getLang().toString() + ":";
  }
  
  private static String getLoginUser()
  {
    String str = PanelLogin.getPreLoginUser();
    if (str == null) {
      return "";
    }
    return "LOGIN@USER=" + str + ":";
  }
  
  public static String getSetting()
  {
    String str = "SETTING=";
    str = str + assembleSentence();
    str = str + ";expires=Tue, 31 Dec 2030 11:59:59 UTC";
    return str;
  }
  
  private static String getTable()
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    HashMap localHashMap = JScrollTable.getTableColumnMap();
    Set localSet = localHashMap.keySet();
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext() == true)
    {
      String str = (String)localIterator.next();
      if (str == null) {
        return localStringBuffer1.toString();
      }
      String[] arrayOfString = (String[])localHashMap.get(str);
      if (arrayOfString == null) {
        return localStringBuffer1.toString();
      }
      StringBuffer localStringBuffer2 = new StringBuffer("TABLE@" + str + "=");
      for (int i = 0; i < arrayOfString.length - 1; i++) {
        localStringBuffer2.append(arrayOfString[i] + ",");
      }
      localStringBuffer2.append(arrayOfString[i] + ":");
      localStringBuffer1.append(localStringBuffer2);
    }
    return localStringBuffer1.toString();
  }
  
  private static boolean parseSentence(String paramString)
  {
    String[] arrayOfString1 = paramString.split("=");
    if (arrayOfString1.length != 2) {
      return false;
    }
    String[] arrayOfString2 = arrayOfString1[0].split("@");
    if (arrayOfString2.length != 2) {
      return false;
    }
    String str1 = arrayOfString2[0];
    String str2 = arrayOfString2[1];
    String[] arrayOfString3 = arrayOfString1[1].split(",");
    if (str1.equals("LOGIN") == true) {
      return setLogin(str2, arrayOfString3);
    }
    if (str1.equals("LANG") == true) {
      return setLang(str2, arrayOfString3);
    }
    if (str1.equals("DIALOG") == true) {
      return setDialog(str2, arrayOfString3);
    }
    if (str1.equals("TABLE") == true) {
      return setTable(str2, arrayOfString3);
    }
    return false;
  }
  
  private static boolean setDialog(String paramString, String[] paramArrayOfString)
  {
    if (paramArrayOfString.length != 2) {
      return false;
    }
    HashMap localHashMap = DlgAbstract.getSizeMap();
    int i = Integer.parseInt(paramArrayOfString[0]);
    int j = Integer.parseInt(paramArrayOfString[1]);
    localHashMap.put(paramString, new Dimension(i, j));
    return true;
  }
  
  private static boolean setGuiMode(String paramString, String[] paramArrayOfString)
  {
    if (paramArrayOfString.length != 1) {
      return false;
    }
    HashMap localHashMap = DlgAbstract.getSizeMap();
    int i = Integer.parseInt(paramArrayOfString[0]);
    int j = Integer.parseInt(paramArrayOfString[1]);
    localHashMap.put(paramString, new Dimension(i, j));
    return true;
  }
  
  private static boolean setLang(String paramString, String[] paramArrayOfString)
  {
    if (!paramString.equals("TYPE")) {
      return false;
    }
    if (paramArrayOfString.length != 1) {
      return false;
    }
    SystemInfo.setLang(EnumLang.getEnum(paramArrayOfString[0]));
    return true;
  }
  
  private static boolean setLogin(String paramString, String[] paramArrayOfString)
  {
    if (paramString.equals("USER") == true)
    {
      if (paramArrayOfString.length != 1) {
        return false;
      }
      PanelLogin.setPreLoginUser(paramArrayOfString[0]);
    }
    else if (paramString.equals("GUI") == true)
    {
      if (paramArrayOfString.length != 1) {
        return false;
      }
      PanelLogin.setPreGuiMode(Integer.parseInt(paramArrayOfString[0]));
    }
    return true;
  }
  
  public static boolean setSetting(String paramString)
  {
    if (paramString == null) {
      return false;
    }
    int i = paramString.indexOf("=") + 1;
    String str = paramString.substring(i);
    String[] arrayOfString = str.split(":");
    if (arrayOfString.length == 0) {
      return false;
    }
    for (int j = 0; j < arrayOfString.length; j++) {
      if (!parseSentence(arrayOfString[j])) {
        return false;
      }
    }
    return true;
  }
  
  private static boolean setTable(String paramString, String[] paramArrayOfString)
  {
    HashMap localHashMap = JScrollTable.getTableColumnMap();
    localHashMap.put(paramString, paramArrayOfString);
    return true;
  }
}
