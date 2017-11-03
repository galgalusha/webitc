package webitc.job;

import webitc.com.ComAddUser;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobAddUser
  extends Job
{
  ComAddUser mCmd = new ComAddUser();
  
  public JobAddUser(String paramString1, String paramString2)
  {
    mCmd.setUserName(paramString1);
    mCmd.setPassword(paramString2);
  }
  
  public int getResult()
  {
    return mCmd.getReturn();
  }
  
  public int getUserID()
  {
    return mCmd.getUserID();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
