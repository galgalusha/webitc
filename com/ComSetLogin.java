package webitc.com;

import java.io.PrintStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import webitc.common.AppletAbst;
import webitc.common.UUID;
import webitc.data.ID;

public class ComSetLogin
  extends ComAbstract
{
  public static final int AUTH_FAILED = -1;
  public static final int AUTH_OK = 1;
  public static final int CONNECTED_MAX_USER = -4;
  public static final int CONNECTED_SAME_USER_GUI = -3;
  public static final int CONNECTED_SAME_USER_WEB = -2;
  
  public ComSetLogin() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60003) {
      return false;
    }
    return getRecvSize() == 32 + getZoneNum() * 4;
  }
  
  public int getMiddleVer()
  {
    return getRecvId();
  }
  
  public int getUserID()
  {
    return getRecvArg2();
  }
  
  public int getZoneID(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getZoneNum())) {
      throw new ArrayIndexOutOfBoundsException("ComSetLogin index error");
    }
    return ComAbstract.getInt(mReceiveBuffer, 32 + paramInt * 4);
  }
  
  public int getZoneNum()
  {
    return getRecvArg3();
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60002);
    setSendSize(64);
    try
    {
      InetAddress localInetAddress = InetAddress.getLocalHost();
      byte[] arrayOfByte = localInetAddress.getAddress();
      int i = (arrayOfByte[0] << 24) + (arrayOfByte[1] << 16) + (arrayOfByte[2] << 8) + arrayOfByte[3];
      setSendId(i);
    }
    catch (Exception localException)
    {
      setSendId(0);
    }
    setSendArg1(AppletAbst.sUUID.getArg1());
    setSendArg2(AppletAbst.sUUID.getArg2());
    setSendArg3(AppletAbst.sUUID.getArg3());
    setSendArg4(AppletAbst.sUUID.getArg4());
  }
  
  public void setName(String paramString)
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
  
  protected void setZoneID(ArrayList paramArrayList)
  {
    int i = 0;
    for (i = 0; i < paramArrayList.size(); i++)
    {
      ID localID = (ID)paramArrayList.get(i);
      ComAbstract.setInt(fID, mReceiveBuffer, 32 + i * 4);
    }
    setRecvArg3(i);
    setRecvSize(32 + i * 4);
  }
}
