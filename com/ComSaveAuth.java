package webitc.com;

public class ComSaveAuth
  extends ComAbstract
{
  public ComSaveAuth() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60616) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60615);
    setSendSize(32);
  }
}
