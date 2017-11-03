package webitc.com;

import java.io.PrintStream;
import java.nio.ByteBuffer;

public class ComAddUser
  extends ComAbstract
{
  public ComAddUser() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60608) {
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
    setSendCom(60607);
    setSendSize(64);
  }
  
  public void setPassword(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("US-ASCII");
      mSendBuffer.position(48);
      mSendBuffer.put(arrayOfByte);
    }
    catch (Exception localException)
    {
      System.out.println(localException.toString());
    }
  }
  
  public void setUserName(String paramString)
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
}
