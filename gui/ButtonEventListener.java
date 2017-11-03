package webitc.gui;

import java.util.EventListener;

public abstract interface ButtonEventListener
  extends EventListener
{
  public static final int MAIN_BACKWORD = 0;
  public static final int MAIN_FOREWORD = 1;
  public static final int MAIN_LOGOUT = 2;
  public static final int MONITOR_ALLOFF = 7;
  public static final int MONITOR_ALLON = 6;
  public static final int MONITOR_ICON = 4;
  public static final int MONITOR_INFO = 10;
  public static final int MONITOR_LIST = 5;
  public static final int MONITOR_OFF = 9;
  public static final int MONITOR_ON = 8;
  public static final int MONITOR_REFRESH = 3;
  public static final int MONITOR_SETTING = 11;
  public static final int SYSTEM_PASSWORD = 20;
  public static final int SYSTEM_USER = 21;
  public static final int SYSTEM_VERSION = 23;
  public static final int SYSTEM_ZONE = 22;
  
  public abstract void buttonSelected(int paramInt);
}
