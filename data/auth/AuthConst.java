package webitc.data.auth;

public class AuthConst
{
  public static final int AUTH_FAILED = -1;
  public static final int AUTH_OK = 1;
  public static final int CONNECTED_MAX_USER = -4;
  public static final int CONNECTED_SAME_USER_GUI = -3;
  public static final int CONNECTED_SAME_USER_WEB = -2;
  public static final int CONNECTION_TIMEOVER = -10;
  public static final int FORCED_LOGFF = -11;
  public static final int ILLEGAL_PASSWORD = -27;
  public static final int ILLEGAL_USER_NAME = -23;
  public static final int MAX_LENOF_PASS = 15;
  public static final int MAX_LENOF_USER_NAME = 15;
  public static final int MAX_REGISTERED_USER = -20;
  public static final int MIN_LENOF_PASS = 0;
  public static final int MIN_LENOF_USER_NAME = 1;
  public static final int NUMOF_NORMAL_USER = 64;
  public static final int OTHER_ERROR = -30;
  public static final String PATTERN_PASSWORD = "[a-zA-Z0-9]*";
  public static final String PATTERN_USR_NAME = "[a-zA-Z0-9]*";
  public static final int ROOT_ID = 1;
  public static final String ROOT_NAME = "admin";
  public static final int SAME_USER_NAME = -24;
  public static final int SERVICE_ID = 0;
  public static final String SERVICE_NAME = "service";
  public static final int TOO_LONG_PASSWORD = -26;
  public static final int TOO_LONG_USER_NAME = -22;
  public static final int TOO_SHORT_PASSWORD = -25;
  public static final int TOO_SHORT_USER_NAME = -21;
  
  public AuthConst() {}
}
