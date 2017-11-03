package webitc.com;

import java.io.PrintStream;

public class ComGetPswd
  extends ComAbstract
{
  public ComGetPswd() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60604) {
      return false;
    }
    return getRecvSize() == 48;
  }
  
  public String getPassword()
  {
    try
    {
      return ComAbstract.getAscii(mReceiveBuffer, 32, 16);
    }
    catch (Exception localException)
    {
      System.out.println(localException.toString());
    }
    return null;
  }
  
  public int getUserID()
  {
    return getRecvId();
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60603);
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
