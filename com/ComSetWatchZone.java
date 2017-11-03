package webitc.com;

import java.io.PrintStream;
import webitc.data.ID;

public class ComSetWatchZone
  extends ComAbstract
{
  public static final int fOneZoneSize = 4;
  
  public ComSetWatchZone() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60614) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  public int getUserID()
  {
    return getRecvId();
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60613);
  }
  
  public void setUserID(int paramInt)
  {
    try
    {
      setSendId(paramInt);
    }
    catch (Exception localException)
    {
      System.out.println(localException.toString());
    }
  }
  
  public void setZoneList(ID[] paramArrayOfID)
  {
    try
    {
      setSendArg1(paramArrayOfID.length);
      for (int i = 0; i < paramArrayOfID.length; i++) {
        ComAbstract.setInt(fID, mSendBuffer, 32 + 4 * i);
      }
      setSendSize(paramArrayOfID.length * 4 + 32);
    }
    catch (Exception localException)
    {
      System.out.println(localException.toString());
    }
  }
}
