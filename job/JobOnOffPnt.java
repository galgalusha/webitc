package webitc.job;

import java.util.ArrayList;
import webitc.com.ComSetPntOnOff;
import webitc.com.SockHttp;
import webitc.data.ID;
import webitc.frame.Job;

public class JobOnOffPnt
  extends Job
{
  private ComSetPntOnOff mCmd = new ComSetPntOnOff();
  
  public JobOnOffPnt(ArrayList paramArrayList, boolean paramBoolean, int paramInt)
  {
    mCmd.onOffRequest(paramArrayList, paramBoolean, paramInt);
  }
  
  public JobOnOffPnt(ID paramID, boolean paramBoolean, int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramID);
    mCmd.onOffRequest(localArrayList, paramBoolean, paramInt);
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
