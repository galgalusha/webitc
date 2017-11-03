package webitc.data.point;

import webitc.common.enum2.EnumComStat;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumDrvVentMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.gui.common.ItcIconInfo;

public abstract class VAbst
{
  protected EnumComStat mCommState = EnumComStat.WAIT;
  protected TargetErr mTargetState = new TargetErr();
  protected boolean mValidOperation = false;
  
  public VAbst() {}
  
  public abstract String getDetailName();
  
  public abstract String getDrvModeStr();
  
  public abstract EnumDrvVentMode getDrvVentMode();
  
  public abstract String getDrvVentModeStr();
  
  public String getErrCodeStr()
  {
    return mTargetState.getErrCodeStr();
  }
  
  public abstract ID getID();
  
  public abstract int getIconAppend();
  
  public abstract ItcIconInfo getIconInfo();
  
  public abstract PntTarget getOrgPntTarget();
  
  public abstract PntCurrent getPntCurrent();
  
  public abstract PntTarget getPntTarget();
  
  public abstract String getRoomTempStr();
  
  public abstract String getSetTempStr();
  
  public abstract String getShortName();
  
  public abstract PntState getState();
  
  public abstract EnumPntType getType();
  
  public abstract boolean isOrgTargetReady();
  
  public boolean isValidOperation()
  {
    return mValidOperation;
  }
  
  public abstract void setIconAppend(int paramInt);
  
  public abstract void updateStatus(EnumComStat paramEnumComStat, TargetErr paramTargetErr, EnumPntStat paramEnumPntStat, EnumDrvMode paramEnumDrvMode, EnumVentMode paramEnumVentMode, int paramInt1, int paramInt2, EnumVentVol paramEnumVentVol, Temperature paramTemperature1, Temperature paramTemperature2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt5, int paramInt6, int paramInt7, Temperature paramTemperature3, Temperature paramTemperature4, Temperature paramTemperature5, Temperature paramTemperature6, boolean paramBoolean4, boolean paramBoolean5, int paramInt8);
}
