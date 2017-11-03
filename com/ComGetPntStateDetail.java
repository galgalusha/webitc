package webitc.com;

import java.nio.ByteBuffer;
import webitc.common.enum2.EnumComStat;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntState;
import webitc.data.point.TargetErr;

public class ComGetPntStateDetail
  extends ComAbstract
{
  public ComGetPntStateDetail() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60115) {
      return false;
    }
    return getRecvSize() == 80;
  }
  
  public PntCurrent getInnerCurrent()
  {
    int i = ComAbstract.getInt(mReceiveBuffer, 60);
    Temperature localTemperature1;
    if ((i & 0x1) != 0) {
      localTemperature1 = new Temperature(true, ComAbstract.getFloat(mReceiveBuffer, 64));
    } else {
      localTemperature1 = new Temperature(false, 0.0F);
    }
    Temperature localTemperature2;
    if ((i & 0x2) != 0) {
      localTemperature2 = new Temperature(true, ComAbstract.getFloat(mReceiveBuffer, 68));
    } else {
      localTemperature2 = new Temperature(false, 0.0F);
    }
    return new PntCurrent(EnumDrvMode.getEnum(ComAbstract.getInt(mReceiveBuffer, 44)), EnumVentMode.getEnum(ComAbstract.getInt(mReceiveBuffer, 48)), EnumVentVol.getEnum(ComAbstract.getInt(mReceiveBuffer, 52)), ComAbstract.getInt(mReceiveBuffer, 56), localTemperature1, localTemperature2, EnumRemcCode.getEnum(mReceiveBuffer.get(72)), EnumPntStat.getEnum(mReceiveBuffer.get(73)), EnumPntStat.getEnum(mReceiveBuffer.get(74)), mReceiveBuffer.get(75), mReceiveBuffer.get(76), mReceiveBuffer.get(77), mReceiveBuffer.get(78), mReceiveBuffer.get(79));
  }
  
  public boolean getPntEnable()
  {
    return getRecvId() == 1;
  }
  
  public PntState getPntState()
  {
    return new PntState(EnumComStat.getEnum(ComAbstract.getInt(mReceiveBuffer, 32)), new TargetErr(mReceiveBuffer, 36, false, false), EnumPntStat.getEnum(ComAbstract.getInt(mReceiveBuffer, 40)));
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60114);
    setSendSize(32);
  }
  
  public void setID(ID paramID)
  {
    setSendId(fID);
    setSendArg1(fType);
  }
  
  protected void setInnerCurrent(PntCurrent paramPntCurrent)
  {
    int i = 0;
    if (mSetTemp.fEnable) {
      i |= 0x1;
    }
    if (mRoomTemp.fEnable) {
      i |= 0x2;
    }
    ComAbstract.setInt(i, mReceiveBuffer, 60);
    ComAbstract.setFloat(mSetTemp.fTemp, mReceiveBuffer, 64);
    ComAbstract.setFloat(mRoomTemp.fTemp, mReceiveBuffer, 68);
    ComAbstract.setInt(mDrvMode.getEnumValue(), mReceiveBuffer, 44);
    ComAbstract.setInt(mVentMode.getEnumValue(), mReceiveBuffer, 48);
    ComAbstract.setInt(mVentVol.getEnumValue(), mReceiveBuffer, 52);
    ComAbstract.setInt(mDefrostMode, mReceiveBuffer, 56);
    mReceiveBuffer.put(72, (byte)mRemoconOnOffMode.getEnumValue());
    mReceiveBuffer.put(73, (byte)mRemoconRunMode.getEnumValue());
    mReceiveBuffer.put(74, (byte)mRemoconTempMode.getEnumValue());
    mReceiveBuffer.put(75, (byte)mFilterSign);
    mReceiveBuffer.put(76, (byte)mThermoOff);
    mReceiveBuffer.put(77, (byte)mThermoMode);
    mReceiveBuffer.put(78, (byte)mWindDirect);
    mReceiveBuffer.put(79, (byte)mWindVolume);
  }
  
  protected void setPntState(PntState paramPntState)
  {
    ComAbstract.setInt(fCommState.getEnumValue(), mReceiveBuffer, 32);
    fTargetState.setBuffer(mReceiveBuffer, 36, false, false);
    ComAbstract.setInt(fOnOffState.getEnumValue(), mReceiveBuffer, 40);
  }
}
