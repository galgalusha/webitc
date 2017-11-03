package webitc.com;

import java.nio.ByteBuffer;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntTarget;

public class ComGetPntTarget
  extends ComAbstract
{
  public ComGetPntTarget() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60111) {
      return false;
    }
    return getRecvSize() == 68;
  }
  
  public PntTarget getInnerTarget()
  {
    int i = 32;
    return new PntTarget(ComAbstract.getInt(mReceiveBuffer, i), ComAbstract.getInt(mReceiveBuffer, i + 4), ComAbstract.getInt(mReceiveBuffer, i + 8), ComAbstract.getInt(mReceiveBuffer, i + 12) == 1, ComAbstract.getFloat(mReceiveBuffer, i + 16), new Temperature(mReceiveBuffer.get(i + 20), mReceiveBuffer.get(i + 21)), new Temperature(mReceiveBuffer.get(i + 22), mReceiveBuffer.get(i + 23)), new Temperature(mReceiveBuffer.get(i + 24), mReceiveBuffer.get(i + 25)), new Temperature(mReceiveBuffer.get(i + 26), mReceiveBuffer.get(i + 27)), mReceiveBuffer.get(i + 28) == 1, mReceiveBuffer.get(i + 29), mReceiveBuffer.get(i + 30) == 1, mReceiveBuffer.get(i + 31), mReceiveBuffer.get(i + 32) == 1, mReceiveBuffer.get(i + 33) == 1, mReceiveBuffer.get(i + 34) == 1);
  }
  
  public boolean getPntEnable()
  {
    return getRecvId() == 1;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60110);
    setSendSize(32);
  }
  
  public void setID(ID paramID)
  {
    setSendId(fID);
    setSendArg1(fType);
  }
  
  protected void setInnerTarget(PntTarget paramPntTarget)
  {
    int i = 32;
    ComAbstract.setInt(fValidRunMode, mReceiveBuffer, i);
    ComAbstract.setInt(fValidVentMode, mReceiveBuffer, i + 4);
    ComAbstract.setInt(fValidVentVol, mReceiveBuffer, i + 8);
    ComAbstract.setInt(fValidSetTemp ? 1 : 0, mReceiveBuffer, i + 12);
    ComAbstract.setFloat(fTempStep, mReceiveBuffer, i + 16);
    mReceiveBuffer.put(i + 20, fCoolUpper.getCentigradeInteger());
    mReceiveBuffer.put(i + 21, fCoolUpper.getCentigradeDecimal());
    mReceiveBuffer.put(i + 22, fCoolLower.getCentigradeInteger());
    mReceiveBuffer.put(i + 23, fCoolLower.getCentigradeDecimal());
    mReceiveBuffer.put(i + 24, fWarmUpper.getCentigradeInteger());
    mReceiveBuffer.put(i + 25, fWarmUpper.getCentigradeDecimal());
    mReceiveBuffer.put(i + 26, fWarmLower.getCentigradeInteger());
    mReceiveBuffer.put(i + 27, fWarmLower.getCentigradeDecimal());
    mReceiveBuffer.put(i + 28, (byte)(fChildMode ? 1 : 0));
    mReceiveBuffer.put(i + 29, (byte)fRkkValidBit);
    mReceiveBuffer.put(i + 30, (byte)(fValidWindDirect ? 1 : 0));
    mReceiveBuffer.put(i + 31, (byte)fValidWindVolume);
    mReceiveBuffer.put(i + 32, (byte)(fValidD3Warning ? 1 : 0));
    mReceiveBuffer.put(i + 33, (byte)(fValidON ? 1 : 0));
    mReceiveBuffer.put(i + 34, (byte)(fValidOFF ? 1 : 0));
  }
}
