package webitc.com;

import java.util.ArrayList;
import webitc.common.FatalException;
import webitc.data.ID;

public class ComGetZonePnt
  extends ComAbstract
{
  public static final int fOnePntSize = 4;
  
  public ComGetZonePnt() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60109) {
      return false;
    }
    return getRecvSize() == getRecvArg2() * 4 + 32;
  }
  
  public ID getPntID(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetZonePnt.getPntID");
    }
    return new ID(0, ComAbstract.getInt(mReceiveBuffer, 32 + paramInt * 4));
  }
  
  public int getPntNum()
  {
    return getRecvArg2();
  }
  
  public boolean getZoneEnable()
  {
    return getRecvId() == 1;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60108);
    setSendSize(32);
  }
  
  protected void setPntList(ArrayList paramArrayList)
  {
    setRecvArg2(paramArrayList.size());
    for (int i = 0; i < paramArrayList.size(); i++)
    {
      ID localID = (ID)paramArrayList.get(i);
      ComAbstract.setInt(fID, mReceiveBuffer, 32 + i * 4);
    }
  }
  
  public void setZoneID(ID paramID)
  {
    if (fType != 1) {
      throw new FatalException("ComGetZonePnt.setZoneID");
    }
    setSendId(fID);
  }
}
