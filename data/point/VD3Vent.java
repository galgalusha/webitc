package webitc.data.point;

import webitc.common.enum2.EnumInnerType;

public class VD3Vent
  extends VD3Inner
{
  public VD3Vent(PropPntCommon paramPropPntCommon)
  {
    super(paramPropPntCommon);
  }
  
  public EnumInnerType getInnerType()
  {
    return EnumInnerType.VENT;
  }
}
