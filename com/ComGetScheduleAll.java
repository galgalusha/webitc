package webitc.com;

public class ComGetScheduleAll
  extends ComAbstract
{
  public final int fOneSchSize = 36;
  
  public ComGetScheduleAll() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60201) {
      return false;
    }
    return getRecvSize() == getRecvArg2() * 36 + 32;
  }
  
  public int getScheduleNum()
  {
    return getRecvArg2();
  }
  
  public String getScheudleName(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getScheduleNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetScheduleAll.getScheudleName index error");
    }
    return ComAbstract.getUcs2(mReceiveBuffer, paramInt * 36 + 32, 32);
  }
  
  public boolean isScheduleEnable(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getScheduleNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetScheduleAll.isScheduleEnable index error");
    }
    return ComAbstract.getInt(mReceiveBuffer, paramInt * 36 + 32 + 32) == 1;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60200);
    setSendSize(32);
  }
  
  protected void setSchedule(int paramInt, String paramString, boolean paramBoolean)
  {
    if ((paramInt < 0) || (paramInt >= getScheduleNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetScheduleAll.setSchedule index error");
    }
    ComAbstract.setUcs2(paramString, mReceiveBuffer, paramInt * 36 + 32, 32);
    ComAbstract.setInt(paramBoolean == true ? 1 : 0, mReceiveBuffer, paramInt * 36 + 32 + 32);
  }
}
