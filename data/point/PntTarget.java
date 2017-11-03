package webitc.data.point;

import webitc.data.Temperature;

public class PntTarget
{
  public static final int RKK_DRVMODE = 4;
  public static final int RKK_OFFONLY = 2;
  public static final int RKK_ONOFF = 1;
  public static final int RKK_SETTEMP = 8;
  public static final int RUNMODE_AUTOCOOL = 512;
  public static final int RUNMODE_AUTOHEAT = 256;
  public static final int RUNMODE_COOL = 4;
  public static final int RUNMODE_DRY = 64;
  public static final int RUNMODE_FAN = 1;
  public static final int RUNMODE_HEAT = 2;
  public static final int RUNMODE_SUBMIT = 16;
  public static final int RUNMODE_VENT = 32;
  public static final int VENTMODE_AUTO = 1;
  public static final int VENTMODE_NONE = 0;
  public static final int VENTMODE_NORMAL = 4;
  public static final int VENTMODE_VENTILATION = 2;
  public static final int VENTVOL_FRESH_AUTO = 8;
  public static final int VENTVOL_FRESH_HIGH = 32;
  public static final int VENTVOL_FRESH_LOW = 16;
  public static final int VENTVOL_NONE = 0;
  public static final int VENTVOL_NORMAL_AUTO = 1;
  public static final int VENTVOL_NORMAL_HIGH = 4;
  public static final int VENTVOL_NORMAL_LOW = 2;
  public final boolean fChildMode;
  public final Temperature fCoolLower;
  public final Temperature fCoolUpper;
  public final int fRkkValidBit;
  public final float fTempStep;
  public final boolean fValidD3Warning;
  public final boolean fValidOFF;
  public final boolean fValidON;
  public final int fValidRunMode;
  public final boolean fValidSetTemp;
  public final int fValidVentMode;
  public final int fValidVentVol;
  public final boolean fValidWindDirect;
  public final int fValidWindVolume;
  public final Temperature fWarmLower;
  public final Temperature fWarmUpper;
  
  public PntTarget(PntTarget paramPntTarget, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramPntTarget != null)
    {
      fValidRunMode = fValidRunMode;
      fValidVentMode = fValidVentMode;
      fValidVentVol = fValidVentVol;
      fValidSetTemp = fValidSetTemp;
      fTempStep = fTempStep;
      fCoolUpper = fCoolUpper;
      fCoolLower = fCoolLower;
      fWarmUpper = fWarmUpper;
      fWarmLower = fWarmLower;
      fChildMode = fChildMode;
      fRkkValidBit = fRkkValidBit;
      fValidWindDirect = fValidWindDirect;
      fValidWindVolume = fValidWindVolume;
      fValidD3Warning = fValidD3Warning;
    }
    else
    {
      fValidRunMode = 0;
      fValidVentMode = 0;
      fValidVentVol = 0;
      fValidSetTemp = false;
      fTempStep = 0.0F;
      fCoolUpper = (this.fCoolLower = this.fWarmUpper = this.fWarmLower = new Temperature());
      fChildMode = false;
      fRkkValidBit = 0;
      fValidWindDirect = false;
      fValidWindVolume = 0;
      fValidD3Warning = false;
    }
    fValidON = paramBoolean1;
    fValidOFF = paramBoolean2;
  }
  
  public PntTarget(PntTarget paramPntTarget, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3, Temperature paramTemperature1, Temperature paramTemperature2, Temperature paramTemperature3, Temperature paramTemperature4, boolean paramBoolean3, boolean paramBoolean4, int paramInt4)
  {
    if (paramPntTarget != null)
    {
      fTempStep = fTempStep;
      fChildMode = fChildMode;
      fRkkValidBit = fRkkValidBit;
      fValidD3Warning = fValidD3Warning;
    }
    else
    {
      fTempStep = 0.0F;
      fChildMode = false;
      fRkkValidBit = 0;
      fValidD3Warning = false;
    }
    fValidON = paramBoolean1;
    fValidOFF = paramBoolean2;
    fValidRunMode = paramInt1;
    fValidVentMode = paramInt2;
    fValidVentVol = paramInt3;
    fCoolUpper = paramTemperature1;
    fCoolLower = paramTemperature2;
    fWarmUpper = paramTemperature3;
    fWarmLower = paramTemperature4;
    fValidSetTemp = paramBoolean3;
    fValidWindDirect = paramBoolean4;
    fValidWindVolume = paramInt4;
  }
  
  public PntTarget(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, float paramFloat, Temperature paramTemperature1, Temperature paramTemperature2, Temperature paramTemperature3, Temperature paramTemperature4, boolean paramBoolean2, int paramInt4, boolean paramBoolean3, int paramInt5, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6)
  {
    fValidRunMode = paramInt1;
    fValidVentMode = paramInt2;
    fValidVentVol = paramInt3;
    fValidSetTemp = paramBoolean1;
    fTempStep = paramFloat;
    fCoolUpper = paramTemperature1;
    fCoolLower = paramTemperature2;
    fWarmUpper = paramTemperature3;
    fWarmLower = paramTemperature4;
    fChildMode = paramBoolean2;
    fRkkValidBit = paramInt4;
    fValidWindDirect = paramBoolean3;
    fValidWindVolume = paramInt5;
    fValidD3Warning = paramBoolean4;
    fValidON = paramBoolean5;
    fValidOFF = paramBoolean6;
  }
  
  public PntTarget()
  {
    fValidRunMode = 0;
    fValidVentMode = 0;
    fValidVentVol = 0;
    fValidSetTemp = false;
    fTempStep = 0.0F;
    fCoolUpper = (this.fCoolLower = this.fWarmUpper = this.fWarmLower = new Temperature());
    fChildMode = false;
    fRkkValidBit = 0;
    fValidWindDirect = false;
    fValidWindVolume = 0;
    fValidD3Warning = false;
    fValidON = false;
    fValidOFF = false;
  }
  
  public Object clone()
  {
    PntTarget localPntTarget = new PntTarget(fValidRunMode, fValidVentMode, fValidVentVol, fValidSetTemp, fTempStep, fCoolUpper, fCoolLower, fWarmUpper, fWarmLower, fChildMode, fRkkValidBit, fValidWindDirect, fValidWindVolume, fValidD3Warning, fValidON, fValidOFF);
    return localPntTarget;
  }
  
  public Temperature getSetTempLower()
  {
    return fCoolLower.compareTo(fWarmLower) >= 0 ? fWarmLower : fCoolLower;
  }
  
  public Temperature getSetTempUpper()
  {
    return fCoolUpper.compareTo(fWarmUpper) >= 0 ? fCoolUpper : fWarmUpper;
  }
}
