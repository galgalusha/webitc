package webitc.com;

import java.nio.ByteBuffer;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntSet;

public class ComSetPntState
  extends ComAbstract
{
  public ComSetPntState() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60113) {
      return false;
    }
    return getRecvSize() == 32;
  }
  
  protected ID getID()
  {
    return new ID(getSendArg1(), getSendId());
  }
  
  protected PntSet getOperation()
  {
    PntSet localPntSet = new PntSet();
    int i = 32;
    if (mSendBuffer.get(i + 20) == 1) {
      mOnOffMode = EnumPntStat.getEnum(ComAbstract.getInt(mSendBuffer, i));
    } else {
      mOnOffMode = EnumPntStat.ELSE;
    }
    if (mSendBuffer.get(i + 21) == 1) {
      mRunMode = EnumDrvMode.getEnum(ComAbstract.getInt(mSendBuffer, i + 4));
    } else {
      mRunMode = EnumDrvMode.ELSE;
    }
    if (mSendBuffer.get(i + 34) == 1) {
      mVentMode = EnumVentMode.getEnum(ComAbstract.getInt(mSendBuffer, i + 8));
    } else {
      mVentMode = EnumVentMode.ELSE;
    }
    if (mSendBuffer.get(i + 35) == 1) {
      mVentVol = EnumVentVol.getEnum(ComAbstract.getInt(mSendBuffer, i + 12));
    } else {
      mVentVol = EnumVentVol.ELSE;
    }
    if (mSendBuffer.get(i + 22) == 1) {
      mSetTemp = new Temperature(true, ComAbstract.getFloat(mSendBuffer, i + 16));
    } else {
      mSetTemp = new Temperature(false, 0.0F);
    }
    if (mSendBuffer.get(i + 23) == 1) {
      mRemoconOnOffMode = EnumRemcCode.getEnum(mSendBuffer.get(i + 26));
    } else {
      mRemoconOnOffMode = EnumRemcCode.ELSE;
    }
    if (mSendBuffer.get(i + 24) == 1) {
      mRemoconRunMode = EnumPntStat.getEnum(mSendBuffer.get(i + 27));
    } else {
      mRemoconRunMode = EnumPntStat.ELSE;
    }
    if (mSendBuffer.get(i + 25) == 1) {
      mRemoconTempMode = EnumPntStat.getEnum(mSendBuffer.get(i + 28));
    } else {
      mRemoconTempMode = EnumPntStat.ELSE;
    }
    if (mSendBuffer.get(i + 29) == 1) {
      mFilterClear = true;
    } else {
      mFilterClear = false;
    }
    if (mSendBuffer.get(i + 30) == 1)
    {
      mChangeWindDirect = true;
      mWindDirect = mSendBuffer.get(i + 31);
    }
    else
    {
      mChangeWindDirect = false;
      mWindDirect = 0;
    }
    if (mSendBuffer.get(i + 32) == 1)
    {
      mChangeWindVolume = true;
      mWindVolume = mSendBuffer.get(i + 33);
    }
    else
    {
      mChangeWindVolume = false;
      mWindVolume = 0;
    }
    return localPntSet;
  }
  
  public boolean getPntEnable()
  {
    return getRecvId() == 1;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60112);
    setSendSize(68);
  }
  
  public void setOperation(ID paramID, PntSet paramPntSet)
  {
    setSendId(fID);
    setSendArg1(fType);
    int i = 32;
    if (mOnOffMode != EnumPntStat.ELSE)
    {
      ComAbstract.setInt(mOnOffMode.getEnumValue(), mSendBuffer, i);
      mSendBuffer.put(i + 20, (byte)1);
    }
    else
    {
      ComAbstract.setInt(0, mSendBuffer, i);
      mSendBuffer.put(i + 20, (byte)0);
    }
    if (mRunMode != EnumDrvMode.ELSE)
    {
      ComAbstract.setInt(mRunMode.getEnumValue(), mSendBuffer, i + 4);
      mSendBuffer.put(i + 21, (byte)1);
    }
    else
    {
      ComAbstract.setInt(0, mSendBuffer, i + 4);
      mSendBuffer.put(i + 21, (byte)0);
    }
    if (mVentMode != EnumVentMode.ELSE)
    {
      ComAbstract.setInt(mVentMode.getEnumValue(), mSendBuffer, i + 8);
      mSendBuffer.put(i + 34, (byte)1);
    }
    else
    {
      ComAbstract.setInt(0, mSendBuffer, i + 8);
      mSendBuffer.put(i + 34, (byte)0);
    }
    if (mVentVol != EnumVentVol.ELSE)
    {
      ComAbstract.setInt(mVentVol.getEnumValue(), mSendBuffer, i + 12);
      mSendBuffer.put(i + 35, (byte)1);
    }
    else
    {
      ComAbstract.setInt(0, mSendBuffer, i + 12);
      mSendBuffer.put(i + 35, (byte)0);
    }
    if (mSetTemp.fEnable == true)
    {
      ComAbstract.setFloat(mSetTemp.fTemp, mSendBuffer, i + 16);
      mSendBuffer.put(i + 22, (byte)1);
    }
    else
    {
      ComAbstract.setFloat(0.0F, mSendBuffer, i + 16);
      mSendBuffer.put(i + 22, (byte)0);
    }
    if (mRemoconOnOffMode != EnumRemcCode.ELSE)
    {
      mSendBuffer.put(i + 26, (byte)mRemoconOnOffMode.getEnumValue());
      mSendBuffer.put(i + 23, (byte)1);
    }
    else
    {
      mSendBuffer.put(i + 26, (byte)0);
      mSendBuffer.put(i + 23, (byte)0);
    }
    if (mRemoconRunMode != EnumPntStat.ELSE)
    {
      mSendBuffer.put(i + 27, (byte)mRemoconRunMode.getEnumValue());
      mSendBuffer.put(i + 24, (byte)1);
    }
    else
    {
      mSendBuffer.put(i + 27, (byte)0);
      mSendBuffer.put(i + 24, (byte)0);
    }
    if (mRemoconTempMode != EnumPntStat.ELSE)
    {
      mSendBuffer.put(i + 28, (byte)mRemoconTempMode.getEnumValue());
      mSendBuffer.put(i + 25, (byte)1);
    }
    else
    {
      mSendBuffer.put(i + 28, (byte)0);
      mSendBuffer.put(i + 25, (byte)0);
    }
    if (mFilterClear == true) {
      mSendBuffer.put(i + 29, (byte)1);
    } else {
      mSendBuffer.put(i + 29, (byte)0);
    }
    if (mChangeWindDirect == true)
    {
      mSendBuffer.put(i + 30, (byte)1);
      mSendBuffer.put(i + 31, (byte)mWindDirect);
    }
    else
    {
      mSendBuffer.put(i + 30, (byte)0);
      mSendBuffer.put(i + 31, (byte)0);
    }
    if (mChangeWindVolume == true)
    {
      mSendBuffer.put(i + 32, (byte)1);
      mSendBuffer.put(i + 33, (byte)mWindVolume);
    }
    else
    {
      mSendBuffer.put(i + 32, (byte)0);
      mSendBuffer.put(i + 33, (byte)-1);
    }
  }
}
