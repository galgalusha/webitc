package webitc.com;

public class ComSetPpdCollectionType
  extends ComAbstract
{
  public ComSetPpdCollectionType() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60408) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60407);
    setSendSize(80);
  }
  
  public void setCalcDate(int paramInt)
  {
    setSendArg2(paramInt);
  }
  
  public void setCollectionType(boolean paramBoolean)
  {
    setSendArg1(paramBoolean ? 0 : 1);
  }
  
  public void setContractPwr(int[] paramArrayOfInt)
  {
    int i = 32;
    for (int j = 0; j < paramArrayOfInt.length; j++) {
      ComAbstract.setInt(paramArrayOfInt[j], mSendBuffer, i + j * 4);
    }
  }
}
