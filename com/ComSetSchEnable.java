package webitc.com;

public class ComSetSchEnable
  extends ComAbstract
{
  public ComSetSchEnable() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60207) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60206);
    setSendSize(32);
  }
  
  public void setProgram(int paramInt, boolean paramBoolean)
  {
    setSendId(paramInt);
    setSendArg1(paramBoolean ? 1 : 0);
  }
}
