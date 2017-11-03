package webitc.com;

import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntType;
import webitc.data.ID;
import webitc.data.point.PropPntCommon;

public class ComGetPntProp
  extends ComAbstract
{
  public final int fOnePntSize = 100;
  
  public ComGetPntProp() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60103) {
      return false;
    }
    return getRecvSize() == getRecvArg2() * 100 + 32;
  }
  
  public EnumInnerType getInnerType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntProp index error");
    }
    return EnumInnerType.getEnum(ComAbstract.getShort(mReceiveBuffer, 32 + 100 * paramInt + 14));
  }
  
  public int getPntNum()
  {
    return getRecvArg2();
  }
  
  public EnumPntType getPntType(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntProp index error");
    }
    return EnumPntType.getEnum(ComAbstract.getShort(mReceiveBuffer, 32 + 100 * paramInt + 12));
  }
  
  public PropPntCommon getProp(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntProp index error");
    }
    int i = 32 + 100 * paramInt;
    ID localID = new ID(0, ComAbstract.getInt(mReceiveBuffer, i));
    PropPntCommon localPropPntCommon = new PropPntCommon(localID, ComAbstract.getInt(mReceiveBuffer, i + 4), ComAbstract.getInt(mReceiveBuffer, i + 8), ComAbstract.getInt(mReceiveBuffer, i + 16), ComAbstract.getUcs2(mReceiveBuffer, i + 20, 16), ComAbstract.getUcs2(mReceiveBuffer, i + 36, 64), getPntType(paramInt), getInnerType(paramInt));
    return localPropPntCommon;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60102);
    setSendSize(100);
  }
  
  protected void setInnerType(int paramInt, EnumInnerType paramEnumInnerType)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntProp index error");
    }
    ComAbstract.setShort((short)paramEnumInnerType.getEnumValue(), mReceiveBuffer, 32 + 100 * paramInt + 14);
  }
  
  protected void setPntType(int paramInt, EnumPntType paramEnumPntType)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntProp index error");
    }
    ComAbstract.setShort((short)paramEnumPntType.getEnumValue(), mReceiveBuffer, 32 + 100 * paramInt + 12);
  }
  
  protected void setProp(int paramInt, PropPntCommon paramPropPntCommon)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("ComGetPntProp index error");
    }
    int i = 32 + 100 * paramInt;
    ComAbstract.setInt(fPntID.fID, mReceiveBuffer, i);
    ComAbstract.setInt(fPortNum, mReceiveBuffer, i + 4);
    ComAbstract.setInt(fAddress, mReceiveBuffer, i + 8);
    ComAbstract.setInt(fIconID, mReceiveBuffer, i + 16);
    ComAbstract.setUcs2(fShortName, mReceiveBuffer, i + 20, 16);
    ComAbstract.setUcs2(fDetailName, mReceiveBuffer, i + 36, 64);
  }
}
