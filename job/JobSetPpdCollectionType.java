package webitc.job;

import java.util.Calendar;
import webitc.com.ComSetPpdCollectionType;
import webitc.com.SockHttp;
import webitc.frame.Job;

public class JobSetPpdCollectionType
  extends Job
{
  ComSetPpdCollectionType mCmd = new ComSetPpdCollectionType();
  
  public JobSetPpdCollectionType(boolean paramBoolean, int paramInt, int[] paramArrayOfInt)
  {
    mCmd.setCollectionType(paramBoolean);
    mCmd.setCalcDate(paramInt);
    mCmd.setContractPwr(paramArrayOfInt);
  }
  
  public Calendar getDate()
  {
    return mCmd.getRecvCalendar();
  }
  
  public int getResult()
  {
    return mCmd.getReturn();
  }
  
  protected void runPrivate()
    throws Exception
  {
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(mCmd);
  }
}
