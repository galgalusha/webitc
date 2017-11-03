package webitc.com;

public class ComGetUserAll
  extends ComAbstract
{
  public static final int fOneUsrSize = 20;
  
  public ComGetUserAll() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60606) {
      return false;
    }
    return getRecvSize() == getRecvArg2() * 20 + 32;
  }
  
  public int getUserID(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getUserNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetUserAll.getUserID index error");
    }
    return ComAbstract.getInt(mReceiveBuffer, paramInt * 20 + 32);
  }
  
  public String getUserName(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getUserNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetUserAll.getUserName index error");
    }
    return ComAbstract.getAscii(mReceiveBuffer, paramInt * 20 + 32 + 4, 16);
  }
  
  public int getUserNum()
  {
    return getRecvArg2();
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60605);
    setSendSize(32);
  }
}
