package webitc.com;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import webitc.common.enum2.EnumHCSel;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntType;
import webitc.data.point.PntInfo;
import webitc.data.point.TargetErr;

public class ComGetPntDetailInfo
  extends ComAbstract
{
  public ComGetPntDetailInfo() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60119) {
      return false;
    }
    return getRecvSize() == 224;
  }
  
  public PntInfo getPntInfo()
  {
    String str1 = ComAbstract.getUcs2(mReceiveBuffer, 32, 16);
    String str2 = ComAbstract.getUcs2(mReceiveBuffer, 48, 64);
    EnumPntType localEnumPntType = EnumPntType.getEnum(ComAbstract.getShort(mReceiveBuffer, 112));
    EnumInnerType localEnumInnerType;
    if (localEnumPntType == EnumPntType.D3_INNER) {
      localEnumInnerType = EnumInnerType.getEnum(ComAbstract.getShort(mReceiveBuffer, 114));
    } else {
      localEnumInnerType = EnumInnerType.UNKNOWN;
    }
    String str3 = ComAbstract.getAscii(mReceiveBuffer, 116, 8);
    int i = ComAbstract.getInt(mReceiveBuffer, 124);
    boolean bool1 = ComAbstract.getBoolean(mReceiveBuffer, 128);
    EnumHCSel localEnumHCSel = EnumHCSel.getEnum(mReceiveBuffer.get(129));
    int j = mReceiveBuffer.get(130);
    boolean bool2 = ComAbstract.getBoolean(mReceiveBuffer, 131);
    float f = ComAbstract.getFloat(mReceiveBuffer, 132);
    TargetErr localTargetErr1 = new TargetErr(mReceiveBuffer, 136, true, false);
    ArrayList localArrayList = new ArrayList();
    for (int k = 0; k < 10; k++)
    {
      TargetErr localTargetErr2 = new TargetErr(mReceiveBuffer, 144 + 8 * k, true, false);
      localArrayList.add(localTargetErr2);
    }
    return new PntInfo(str1, str2, localEnumPntType, localEnumInnerType, str3, i, bool1, localEnumHCSel, j, bool2, f, localTargetErr1, localArrayList);
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60118);
    setSendSize(32);
  }
  
  protected void setPntInfo(PntInfo paramPntInfo)
  {
    ComAbstract.setUcs2(fShortName, mReceiveBuffer, 32, 16);
    ComAbstract.setUcs2(fDetailName, mReceiveBuffer, 48, 64);
    ComAbstract.setShort((short)fPntType.getEnumValue(), mReceiveBuffer, 112);
    ComAbstract.setShort((short)fInnerType.getEnumValue(), mReceiveBuffer, 114);
    ComAbstract.setAscii(fAddrStr, mReceiveBuffer, 116, 8);
    ComAbstract.setInt(fAutoCtrlBits, mReceiveBuffer, 124);
    ComAbstract.setBoolean(fChildMode, mReceiveBuffer, 128);
    mReceiveBuffer.put(129, (byte)fCoolHeatOpt.getEnumValue());
    mReceiveBuffer.put(130, (byte)fOuterAddr);
    ComAbstract.setBoolean(fValidRoomTemp, mReceiveBuffer, 131);
    ComAbstract.setFloat(fRoomTemp, mReceiveBuffer, 132);
    mError.setBuffer(mReceiveBuffer, 136, true, false);
    for (int i = 0; i < mErrorHistory.size(); i++)
    {
      TargetErr localTargetErr = (TargetErr)mErrorHistory.get(i);
      localTargetErr.setBuffer(mReceiveBuffer, 144 + 8 * i, true, false);
    }
  }
}
