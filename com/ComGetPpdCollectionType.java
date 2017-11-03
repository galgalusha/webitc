package webitc.com;

public class ComGetPpdCollectionType
  extends ComAbstract
{
  public static final int CONTRACT_POWER_MAX = 9999999;
  public static final int CONTRACT_POWER_MIN = 0;
  public static final int CONTRACT_POWER_NUM = 12;
  
  public ComGetPpdCollectionType() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60406) {
      return false;
    }
    return getRecvSize() == 80;
  }
  
  public int getCalcDate()
  {
    int i = getRecvArg3();
    if (i < 1) {
      i = 20;
    }
    if (i > 31) {
      i = 20;
    }
    return i;
  }
  
  public int[] getContractPwr()
  {
    int[] arrayOfInt = new int[12];
    int i = 32;
    for (int j = 0; j < arrayOfInt.length; j++)
    {
      int k = ComAbstract.getInt(mReceiveBuffer, i + j * 4);
      if ((k < 0) || (k > 9999999)) {
        k = 0;
      }
      arrayOfInt[j] = k;
    }
    return arrayOfInt;
  }
  
  public boolean isPeriodType()
  {
    return getRecvArg2() != 1;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60405);
    setSendSize(32);
  }
}
