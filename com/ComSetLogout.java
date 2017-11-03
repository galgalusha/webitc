package webitc.com;

import webitc.common.AppletAbst;
import webitc.common.UUID;

public class ComSetLogout
  extends ComAbstract
{
  public ComSetLogout() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60005) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60004);
    setSendSize(32);
    setSendArg1(AppletAbst.sUUID.getArg1());
    setSendArg2(AppletAbst.sUUID.getArg2());
    setSendArg3(AppletAbst.sUUID.getArg3());
    setSendArg4(AppletAbst.sUUID.getArg4());
  }
  
  public void setUserID(int paramInt)
  {
    setSendId(paramInt);
  }
}
