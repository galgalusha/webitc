package webitc.com;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import webitc.common.FatalException;
import webitc.common.UUID;
import webitc.common.enum2.EnumComStat;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.TargetErr;

public class ComGetPntState
  extends ComAbstract
{
  public static final int AUTH_OK = 1;
  public static final int CONNECTION_TIMEOVER = -10;
  public static final int FORCED_LOGFF = -11;
  public static final int MIDDLE_CHANGED = -20;
  public final int fOneRecvPntSize = 48;
  public final int fOneSendPntSize = 8;
  
  public ComGetPntState() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60105) {
      return false;
    }
    return getRecvSize() == getRecvArg2() * 48 + 32;
  }
  
  public EnumComStat getCommState(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getCommState index error");
    }
    return EnumComStat.getEnum(mReceiveBuffer.get(32 + 48 * paramInt + 6));
  }
  
  public EnumDrvMode getDrvMode(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getDrvMode index error");
    }
    return EnumDrvMode.getEnum(ComAbstract.getShort(mReceiveBuffer, 32 + 48 * paramInt + 14));
  }
  
  protected ArrayList getIDArray()
  {
    ArrayList localArrayList = new ArrayList();
    int i = ComAbstract.getInt(mSendBuffer, 32);
    for (int j = 0; j < i; j++)
    {
      int k = 36 + j * 8;
      int m = ComAbstract.getInt(mSendBuffer, k);
      ID localID = new ID(m, ComAbstract.getInt(mSendBuffer, k + 4));
      localArrayList.add(localID);
    }
    return localArrayList;
  }
  
  public int getIconAppend(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getIconAppend index error");
    }
    return mReceiveBuffer.get(32 + 48 * paramInt + 13);
  }
  
  public int getIconMode(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getIconMode index error");
    }
    return mReceiveBuffer.get(32 + 48 * paramInt + 12);
  }
  
  public int getMiddleVer()
  {
    return getRecvId();
  }
  
  public EnumPntStat getOnOffState(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getOnOffState index error");
    }
    return EnumPntStat.getEnum(mReceiveBuffer.get(32 + 48 * paramInt + 11));
  }
  
  public boolean getPntEnable(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getPntEnable index error");
    }
    return mReceiveBuffer.get(32 + 48 * paramInt + 5) == 1;
  }
  
  public ID getPntID(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getPntID index error");
    }
    int i = 32 + 48 * paramInt;
    switch (mReceiveBuffer.get(i + 4))
    {
    case 0: 
      return new ID(0, ComAbstract.getInt(mReceiveBuffer, i));
    case 1: 
      return new ID(1, ComAbstract.getInt(mReceiveBuffer, i));
    }
    throw new FatalException("ComGetPntState.getPntID " + mReceiveBuffer.get(i + 4));
  }
  
  public int getPntNum()
  {
    return getRecvArg2();
  }
  
  public Temperature getRoomTemp(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getRoomTemp index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 19);
    if ((i & 0x2) != 0) {
      return new Temperature(true, ComAbstract.getFloat(mReceiveBuffer, 32 + 48 * paramInt + 24));
    }
    return new Temperature(false, 0.0F);
  }
  
  public Temperature getSetTemp(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getSetTemp index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 19);
    if ((i & 0x1) != 0) {
      return new Temperature(true, ComAbstract.getFloat(mReceiveBuffer, 32 + 48 * paramInt + 20));
    }
    return new Temperature(false, 0.0F);
  }
  
  public TargetErr getTargetErr(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getTargetState index error");
    }
    int i = 32 + 48 * paramInt + 7;
    boolean bool = mReceiveBuffer.get(i) == 1;
    String str = ComAbstract.getAscii(mReceiveBuffer, i + 1, 2);
    int j = mReceiveBuffer.get(i + 3);
    return new TargetErr(bool, j, str);
  }
  
  public Temperature getTempCoolLower(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getTempCoolLower index error");
    }
    byte b1 = mReceiveBuffer.get(32 + 48 * paramInt + 40);
    byte b2 = mReceiveBuffer.get(32 + 48 * paramInt + 41);
    return new Temperature(b1, b2);
  }
  
  public Temperature getTempCoolUpper(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getTempCoolUpper index error");
    }
    byte b1 = mReceiveBuffer.get(32 + 48 * paramInt + 38);
    byte b2 = mReceiveBuffer.get(32 + 48 * paramInt + 39);
    return new Temperature(b1, b2);
  }
  
  public Temperature getTempWarmLower(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getTempWarmLower index error");
    }
    byte b1 = mReceiveBuffer.get(32 + 48 * paramInt + 44);
    byte b2 = mReceiveBuffer.get(32 + 48 * paramInt + 45);
    return new Temperature(b1, b2);
  }
  
  public Temperature getTempWarmUpper(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getTempWarmUpper index error");
    }
    byte b1 = mReceiveBuffer.get(32 + 48 * paramInt + 42);
    byte b2 = mReceiveBuffer.get(32 + 48 * paramInt + 43);
    return new Temperature(b1, b2);
  }
  
  public int getValidRunMode(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getValidRunMode index error");
    }
    return ComAbstract.getShort(mReceiveBuffer, 32 + 48 * paramInt + 32);
  }
  
  public int getValidVentMode(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getValidVentMode index error");
    }
    return ComAbstract.getShort(mReceiveBuffer, 32 + 48 * paramInt + 34);
  }
  
  public int getValidVentVol(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getValidVentVol index error");
    }
    return ComAbstract.getShort(mReceiveBuffer, 32 + 48 * paramInt + 36);
  }
  
  public int getValidWindVolume(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getValidWindVolume index error");
    }
    return mReceiveBuffer.get(32 + 48 * paramInt + 47);
  }
  
  public EnumVentMode getVentMode(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getVentMode index error");
    }
    return EnumVentMode.getEnum(ComAbstract.getShort(mReceiveBuffer, 32 + 48 * paramInt + 16));
  }
  
  public EnumVentVol getVentVolume(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getVentMode index error");
    }
    return EnumVentVol.getEnum(ComAbstract.getShort(mReceiveBuffer, 32 + 48 * paramInt + 30));
  }
  
  public int getWindDirect(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getWindDirect index error");
    }
    return mReceiveBuffer.get(32 + 48 * paramInt + 28);
  }
  
  public int getWindVolume(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getWindVolume index error");
    }
    return mReceiveBuffer.get(32 + 48 * paramInt + 29);
  }
  
  public boolean isAllValidOFF()
  {
    return (getRecvArg4() & 0x2) != 0;
  }
  
  public boolean isAllValidON()
  {
    return (getRecvArg4() & 0x1) != 0;
  }
  
  public boolean isComError(int paramInt)
  {
    if (paramInt == 0) {
      return (getRecvArg3() & 0x8) != 0;
    }
    if (paramInt == 1) {
      return (getRecvArg3() & 0x80) != 0;
    }
    return false;
  }
  
  public boolean isControlCenter(int paramInt)
  {
    if (paramInt == 0) {
      return (getRecvArg3() & 0x1) != 0;
    }
    if (paramInt == 1) {
      return (getRecvArg3() & 0x10) != 0;
    }
    return false;
  }
  
  public boolean isForceStop(int paramInt)
  {
    if (paramInt == 0) {
      return (getRecvArg3() & 0x2) != 0;
    }
    if (paramInt == 1) {
      return (getRecvArg3() & 0x20) != 0;
    }
    return false;
  }
  
  public boolean isM8(int paramInt)
  {
    if (paramInt == 0) {
      return (getRecvArg3() & 0x4) != 0;
    }
    if (paramInt == 1) {
      return (getRecvArg3() & 0x40) != 0;
    }
    return false;
  }
  
  public boolean isValidOFF(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.isValidOFF index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 18);
    return (i & 0x2) != 0;
  }
  
  public boolean isValidON(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.isValidON index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 18);
    return (i & 0x1) != 0;
  }
  
  public boolean isValidOperation(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.isValidOperation index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 18);
    return (i & 0x4) != 0;
  }
  
  public boolean isValidRoomTemp(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getRoomTemp index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 19);
    return (i & 0x2) != 0;
  }
  
  public boolean isValidSetTemp(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.getRoomTemp index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 19);
    return (i & 0x4) != 0;
  }
  
  public boolean isValidWindDirect(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntState.isValidWindDirect index error");
    }
    int i = mReceiveBuffer.get(32 + 48 * paramInt + 46);
    return i == 1;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60104);
    setSendSize(36);
    ComAbstract.setInt(0, mSendBuffer, 32);
  }
  
  public void setIDArray(ArrayList paramArrayList)
  {
    int i = paramArrayList.size();
    setSendSize(36 + i * 8);
    ComAbstract.setInt(i, mSendBuffer, 32);
    for (int j = 0; j < i; j++)
    {
      ID localID = (ID)paramArrayList.get(j);
      int k = 36 + j * 8;
      if (fType == 0) {
        ComAbstract.setInt(0, mSendBuffer, k);
      } else {
        ComAbstract.setInt(1, mSendBuffer, k);
      }
      ComAbstract.setInt(fID, mSendBuffer, k + 4);
    }
  }
  
  protected void setPntID(ArrayList paramArrayList)
  {
    for (int i = 0; i < paramArrayList.size(); i++)
    {
      int j = 32 + 48 * i;
      ID localID = (ID)paramArrayList.get(i);
      mReceiveBuffer.put(j + 4, (byte)fType);
      mReceiveBuffer.put(j + 5, (byte)1);
      ComAbstract.setInt(fID, mReceiveBuffer, j);
    }
  }
  
  protected void setPntState(int paramInt1, EnumComStat paramEnumComStat, TargetErr paramTargetErr, EnumPntStat paramEnumPntStat, EnumDrvMode paramEnumDrvMode, EnumVentMode paramEnumVentMode, Temperature paramTemperature1, Temperature paramTemperature2, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, int paramInt4, EnumVentVol paramEnumVentVol, int paramInt5, int paramInt6, int paramInt7, Temperature paramTemperature3, Temperature paramTemperature4, Temperature paramTemperature5, Temperature paramTemperature6, boolean paramBoolean4, int paramInt8, boolean paramBoolean5)
  {
    int i = 32 + 48 * paramInt1;
    mReceiveBuffer.put(i + 6, (byte)paramEnumComStat.getEnumValue());
    boolean bool = fError;
    int j = fUnitNo;
    String str = fErrStr;
    mReceiveBuffer.put(i + 7, (byte)(bool == true ? 1 : 0));
    ComAbstract.setAscii(str, mReceiveBuffer, i + 8, 2);
    mReceiveBuffer.put(i + 10, (byte)j);
    mReceiveBuffer.put(i + 11, (byte)paramEnumPntStat.getEnumValue());
    if ((paramEnumComStat == EnumComStat.ERROR) || (paramEnumComStat == EnumComStat.WAIT)) {
      mReceiveBuffer.put(i + 12, (byte)3);
    } else if (!paramTargetErr.isNormal()) {
      mReceiveBuffer.put(i + 12, (byte)2);
    } else if (paramEnumPntStat == EnumPntStat.ON) {
      mReceiveBuffer.put(i + 12, (byte)1);
    } else {
      mReceiveBuffer.put(i + 12, (byte)0);
    }
    mReceiveBuffer.put(i + 13, (byte)paramInt2);
    ComAbstract.setShort((short)paramEnumDrvMode.getEnumValue(), mReceiveBuffer, i + 14);
    ComAbstract.setShort((short)paramEnumVentMode.getEnumValue(), mReceiveBuffer, i + 16);
    byte b1 = 0;
    if (fEnable == true) {
      b1 = (byte)(b1 | 0x1);
    }
    if (fEnable == true) {
      b1 = (byte)(b1 | 0x2);
    }
    if (paramBoolean5 == true) {
      b1 = (byte)(b1 | 0x4);
    }
    byte b2 = 0;
    if (paramBoolean1) {
      b2 = (byte)(b2 | 0x1);
    }
    if (paramBoolean2) {
      b2 = (byte)(b2 | 0x2);
    }
    if (paramBoolean3) {
      b2 = (byte)(b2 | 0x4);
    }
    mReceiveBuffer.put(i + 18, b2);
    mReceiveBuffer.put(i + 19, b1);
    ComAbstract.setFloat(fTemp, mReceiveBuffer, i + 20);
    ComAbstract.setFloat(fTemp, mReceiveBuffer, i + 24);
    mReceiveBuffer.put(i + 28, (byte)paramInt3);
    mReceiveBuffer.put(i + 29, (byte)paramInt4);
    ComAbstract.setShort((short)paramEnumVentVol.getEnumValue(), mReceiveBuffer, i + 30);
    ComAbstract.setShort((short)paramInt5, mReceiveBuffer, i + 32);
    ComAbstract.setShort((short)paramInt6, mReceiveBuffer, i + 34);
    ComAbstract.setShort((short)paramInt7, mReceiveBuffer, i + 36);
    mReceiveBuffer.put(i + 38, paramTemperature3.getCentigradeInteger());
    mReceiveBuffer.put(i + 39, paramTemperature3.getCentigradeDecimal());
    mReceiveBuffer.put(i + 40, paramTemperature4.getCentigradeInteger());
    mReceiveBuffer.put(i + 41, paramTemperature3.getCentigradeDecimal());
    mReceiveBuffer.put(i + 42, paramTemperature5.getCentigradeInteger());
    mReceiveBuffer.put(i + 43, paramTemperature5.getCentigradeDecimal());
    mReceiveBuffer.put(i + 44, paramTemperature6.getCentigradeInteger());
    mReceiveBuffer.put(i + 45, paramTemperature6.getCentigradeDecimal());
    if (paramBoolean4 == true) {
      mReceiveBuffer.put(i + 46, (byte)1);
    } else {
      mReceiveBuffer.put(i + 46, (byte)0);
    }
    mReceiveBuffer.put(i + 47, (byte)paramInt8);
  }
  
  public void setUUID(UUID paramUUID)
  {
    setSendArg1(paramUUID.getArg1());
    setSendArg2(paramUUID.getArg2());
    setSendArg3(paramUUID.getArg3());
    setSendArg4(paramUUID.getArg4());
  }
  
  public void setUserID(int paramInt)
  {
    setSendId(paramInt);
  }
}
