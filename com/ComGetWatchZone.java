package webitc.com;

import java.io.PrintStream;

public class ComGetWatchZone
  extends ComAbstract
{
  public static final int fOneZoneSize = 4;
  
  public ComGetWatchZone() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60612) {
      return false;
    }
    return getRecvSize() == getRecvArg2() * 4 + 32;
  }
  
  public int getZoneID(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getZoneNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetWatchZone.getZoneID index error");
    }
    return ComAbstract.getInt(mReceiveBuffer, paramInt * 4 + 32);
  }
  
  public int getZoneNum()
  {
    return getRecvArg2();
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60611);
    setSendSize(32);
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
}
