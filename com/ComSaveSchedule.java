package webitc.com;

public class ComSaveSchedule
  extends ComAbstract
{
  public ComSaveSchedule() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60209) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60208);
    setSendSize(32);
  }
}
