package webitc.com;

import java.io.PrintStream;
import java.nio.ByteBuffer;

public class ComChangePswd
  extends ComAbstract
{
  public static final int AUTH_FAILED = -1;
  public static final int AUTH_OK = 1;
  
  public ComChangePswd() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60602) {
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
    setSendCom(60601);
    setSendSize(48);
  }
  
  public void setPassword(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("US-ASCII");
      mSendBuffer.position(32);
      mSendBuffer.put(arrayOfByte);
    }
    catch (Exception localException)
    {
      System.out.println(localException.toString());
    }
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
