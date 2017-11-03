package webitc.com;

import java.nio.ByteBuffer;
import webitc.gui.ppd.PpdInnerPower;
import webitc.gui.ppd.PpdOuterPower;

public class ComGetPpdData
  extends ComAbstract
{
  public static final int fPcOneInPntSize = 20;
  public static final int fPcOneOutPntSize = 12;
  public static final int fPcOneTimeSize = 8;
  
  public ComGetPpdData() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60404) {
      return false;
    }
    return getRecvSize() == 32 + (8 + 20 * getInPntNum() + 12 * getOutPntNum()) * getHours();
  }
  
  public int getHours()
  {
    return getRecvArg2();
  }
  
  public int getInPntNum()
  {
    return getRecvArg3();
  }
  
  public PpdInnerPower getInnerPower(int paramInt1, int paramInt2)
  {
    int i = 32 + (8 + 20 * getInPntNum() + 12 * getOutPntNum()) * paramInt1;
    int j = i + 8 + 20 * paramInt2;
    int k = ComAbstract.getInt(mReceiveBuffer, i);
    boolean bool = ComAbstract.getBoolean(mReceiveBuffer, i + 4);
    char c1 = (char)mReceiveBuffer.get(i + 5);
    char c2 = (char)mReceiveBuffer.get(i + 6);
    char c3 = (char)mReceiveBuffer.get(i + 7);
    int m = 0;
    int n = 0;
    int i1 = 0;
    int i2 = 0;
    if ((paramInt2 >= 0) && (paramInt2 < getInPntNum()))
    {
      m = ComAbstract.getInt(mReceiveBuffer, j + 4);
      n = ComAbstract.getInt(mReceiveBuffer, j + 8);
      i1 = ComAbstract.getInt(mReceiveBuffer, j + 12);
      i2 = ComAbstract.getInt(mReceiveBuffer, j + 16);
    }
    return new PpdInnerPower(bool, k, c1, c2, c3, m, n, i1, i2);
  }
  
  public int getOutPntNum()
  {
    return getRecvArg4();
  }
  
  public PpdOuterPower getOuterPower(int paramInt1, int paramInt2)
  {
    int i = 32 + (8 + 20 * getInPntNum() + 12 * getOutPntNum()) * paramInt1;
    int j = i + 8 + 20 * getInPntNum() + 12 * paramInt2;
    int k = 0;
    int m = 0;
    int n = 0;
    if ((paramInt2 >= 0) && (paramInt2 < getOutPntNum()))
    {
      k = ComAbstract.getInt(mReceiveBuffer, j);
      m = ComAbstract.getInt(mReceiveBuffer, j + 4);
      n = ComAbstract.getInt(mReceiveBuffer, j + 8);
    }
    return new PpdOuterPower(k, m, n);
  }
  
  public int getUnitNum(int paramInt1, int paramInt2)
  {
    int i = 32 + (8 + 20 * getInPntNum() + 12 * getOutPntNum()) * paramInt1;
    int j = i + 8 + 20 * paramInt2;
    return ComAbstract.getInt(mReceiveBuffer, j);
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60403);
    setSendSize(32);
  }
  
  public void setCollectionPeriod(int paramInt1, int paramInt2)
  {
    setSendArg1(paramInt1);
    setSendArg2(paramInt2);
  }
}
