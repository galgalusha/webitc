package webitc.com;

import webitc.data.ID;
import webitc.data.point.PropZone;

public class ComGetZoneProp
  extends ComAbstract
{
  public final int fOneZoneSize = 88;
  
  public ComGetZoneProp() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60101) {
      return false;
    }
    return getRecvSize() == getRecvArg2() * 88 + 32;
  }
  
  public int getZoneNum()
  {
    return getRecvArg2();
  }
  
  public PropZone getZoneProp(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getZoneNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetZoneProp index error");
    }
    int i = 32 + 88 * paramInt;
    PropZone localPropZone = new PropZone(ComAbstract.getInt(mReceiveBuffer, i), ComAbstract.getInt(mReceiveBuffer, i + 4), ComAbstract.getUcs2(mReceiveBuffer, i + 8, 16), ComAbstract.getUcs2(mReceiveBuffer, i + 24, 64));
    return localPropZone;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60100);
    setSendSize(32);
  }
  
  protected void setZoneProp(int paramInt, PropZone paramPropZone)
  {
    int i = 32 + 88 * paramInt;
    ComAbstract.setInt(fZoneID.fID, mReceiveBuffer, i);
    ComAbstract.setInt(fIconID, mReceiveBuffer, i + 4);
    ComAbstract.setUcs2(fShortName, mReceiveBuffer, i + 8, 16);
    ComAbstract.setUcs2(fDetailName, mReceiveBuffer, i + 24, 64);
  }
}
