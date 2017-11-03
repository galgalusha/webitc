package webitc.com;

import java.util.ArrayList;
import webitc.data.ID;

public class ComSetPntOnOff
  extends ComAbstract
{
  public final int fOneSendPntSize = 8;
  
  public ComSetPntOnOff() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60107) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  protected ID getID(int paramInt)
  {
    int i = 32 + paramInt * 8;
    switch (ComAbstract.getInt(mSendBuffer, i))
    {
    case 0: 
      return new ID(0, ComAbstract.getInt(mSendBuffer, i + 4));
    case 1: 
      return new ID(1, ComAbstract.getInt(mSendBuffer, i + 4));
    }
    return ID.ZONE_ALL;
  }
  
  public void onOffRequest(ArrayList paramArrayList, boolean paramBoolean, int paramInt)
  {
    setSendArg2(paramBoolean ? 1 : 0);
    setSendArg3(paramInt);
    int i = paramArrayList.size();
    setSendSize(32 + i * 8);
    setSendArg4(i);
    for (int j = 0; j < i; j++)
    {
      ID localID = (ID)paramArrayList.get(j);
      int k = 32 + j * 8;
      if (fType == 0) {
        ComAbstract.setInt(0, mSendBuffer, k);
      } else {
        ComAbstract.setInt(1, mSendBuffer, k);
      }
      ComAbstract.setInt(fID, mSendBuffer, k + 4);
    }
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60106);
    setSendSize(32);
  }
}
