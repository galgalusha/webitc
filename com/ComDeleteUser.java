package webitc.com;

import java.io.PrintStream;

public class ComDeleteUser
  extends ComAbstract
{
  public ComDeleteUser() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60610) {
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
    setSendCom(60609);
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
