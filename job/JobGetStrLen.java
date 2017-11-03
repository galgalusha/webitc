package webitc.job;

import webitc.com.ComGetStrLen;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobGetStrLen
  extends Job
{
  private ComGetStrLen mCom = new ComGetStrLen();
  
  public JobGetStrLen(String paramString)
  {
    mCom.setStr(paramString);
  }
  
  public int getWidth()
  {
    return mCom.getWidth();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCom);
  }
}
