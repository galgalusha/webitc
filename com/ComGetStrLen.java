package webitc.com;

public class ComGetStrLen
  extends ComAbstract
{
  public ComGetStrLen() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60701) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  protected String getStr()
  {
    int i = getSendArg1();
    return ComAbstract.getUcs2(mSendBuffer, 32, i);
  }
  
  public int getWidth()
  {
    return getRecvArg2();
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60700);
    setSendSize(32);
  }
  
  public void setStr(String paramString)
  {
    int i = paramString.length() * 2;
    setSendSize(32 + i);
    setSendArg1(i);
    ComAbstract.setUcs2(paramString, mSendBuffer, 32, i);
  }
}
