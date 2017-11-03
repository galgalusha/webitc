package webitc.com;

import webitc.gui.ppd.PpdUnitProp;

public class ComGetPpdTarget
  extends ComAbstract
{
  static final int fPcOneInfoSize = 20;
  
  public ComGetPpdTarget() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60402) {
      return false;
    }
    return getRecvSize() == 32 + 20 * getRecvArg2();
  }
  
  public int getTargetNum()
  {
    return getRecvArg2();
  }
  
  public PpdUnitProp getUnitProp(int paramInt)
  {
    int i = 32 + 20 * paramInt;
    int j = ComAbstract.getInt(mReceiveBuffer, i);
    int k = ComAbstract.getInt(mReceiveBuffer, i + 4);
    int m = ComAbstract.getInt(mReceiveBuffer, i + 8);
    int n = ComAbstract.getInt(mReceiveBuffer, i + 12);
    int i1 = ComAbstract.getInt(mReceiveBuffer, i + 16);
    PpdUnitProp localPpdUnitProp = new PpdUnitProp(j, k, m, n, i1);
    return localPpdUnitProp;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60401);
    setSendSize(32);
  }
}
