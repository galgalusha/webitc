package webitc.job;

import java.util.TimeZone;
import webitc.com.ComGetSysInit;
import webitc.com.SockHttp;
import webitc.common.enum2.EnumLang;
import webitc.frame.Job;

public class JobGetSysInit
  extends Job
{
  private ComGetSysInit mCom = new ComGetSysInit();
  
  public JobGetSysInit() {}
  
  public int getD3PortNum()
  {
    return mCom.getD3PortNum();
  }
  
  public EnumLang getDispLanguage()
  {
    return mCom.getLanguage();
  }
  
  public int getOption()
  {
    return mCom.getOption();
  }
  
  public int getPPDOption()
  {
    return mCom.getPPDOption();
  }
  
  public int getScheduleLowerTemp()
  {
    return mCom.getScheduleLowerTemp();
  }
  
  public int getScheduleUpperTemp()
  {
    return mCom.getScheduleUpperTemp();
  }
  
  public TimeZone getTimeZone()
  {
    return mCom.getTimeZone();
  }
  
  public boolean isCentigrade()
  {
    return mCom.isCentigrade();
  }
  
  public boolean isGreenOff()
  {
    return mCom.getOnOffColor() == 0;
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
