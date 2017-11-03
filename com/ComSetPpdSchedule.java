package webitc.com;

import webitc.gui.ppd.PpdSchedule;

public class ComSetPpdSchedule
  extends ComAbstract
{
  public ComSetPpdSchedule() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60412) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60411);
    setSendSize(108);
  }
  
  public void setPpdSchedule(PpdSchedule paramPpdSchedule)
  {
    setSendArg1(paramPpdSchedule.getExclusionWDayBitmap());
    setSendArg2(paramPpdSchedule.getNightTimeInt());
    for (int i = 0; i < 12; i++) {
      ComAbstract.setInt(paramPpdSchedule.getSpecialDayBitmap(i + 1), mSendBuffer, i * 4 + 32);
    }
    for (int j = 0; j < 7; j++) {
      ComAbstract.setInt(paramPpdSchedule.getExclusionTimeInt(j), mSendBuffer, j * 4 + 32 + 48);
    }
  }
}
