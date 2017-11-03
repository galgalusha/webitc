package webitc.com;

import webitc.gui.ppd.PpdSchedule;

public class ComGetPpdSchedule
  extends ComAbstract
{
  private PpdSchedule mSchedule = new PpdSchedule();
  
  public ComGetPpdSchedule() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60410) {
      return false;
    }
    return getRecvSize() == 108;
  }
  
  public PpdSchedule getPpdSchedule()
  {
    mSchedule.setExclusionWDayBitmap(getRecvArg2());
    mSchedule.setNightTimeInt(getRecvArg3());
    for (int i = 0; i < 12; i++) {
      mSchedule.setSpecialDayBitmap(i + 1, ComAbstract.getInt(mReceiveBuffer, 4 * i + 32));
    }
    for (int j = 0; j < 7; j++) {
      mSchedule.setExclusionTimeInt(j, ComAbstract.getInt(mReceiveBuffer, 4 * j + 32 + 48));
    }
    return mSchedule;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60409);
    setSendSize(32);
  }
}
