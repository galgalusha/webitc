package webitc.data;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.SwingUtilities;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.enum2.EnumComStat;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumDrvVentMode;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.data.point.Port;
import webitc.data.point.PropPntCommon;
import webitc.data.point.PropZone;
import webitc.data.point.TargetErr;
import webitc.data.point.VAbst;
import webitc.data.point.VD3Inner;
import webitc.data.point.VPoint;
import webitc.data.point.VZone;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.common.CommonUse;
import webitc.gui.common.ItcIconInfo;
import webitc.job.JobUpdatePntOrgTarget;
import webitc.job.JobUpdatePntTarget;

public class DataMgr
{
  private DataUserInfo mLoginUser = null;
  private int mMiddleVer = 0;
  private static DataMgr mObj;
  private HashMap mPntIdMap = new HashMap();
  private ArrayList mPntList = new ArrayList();
  private Port mPort = new Port();
  private HashMap mZoneIdMap = new HashMap();
  private ArrayList mZoneList = new ArrayList();
  
  public DataMgr() {}
  
  public VPoint addPnt(EnumPntType paramEnumPntType, EnumInnerType paramEnumInnerType, PropPntCommon paramPropPntCommon)
  {
    VD3Inner localVD3Inner = new VD3Inner(paramPropPntCommon);
    mPntList.add(localVD3Inner);
    mPntIdMap.put(fPntID, localVD3Inner);
    return localVD3Inner;
  }
  
  public VZone addZone(PropZone paramPropZone)
  {
    VZone localVZone = new VZone(paramPropZone, false);
    mZoneList.add(localVZone);
    mZoneIdMap.put(fZoneID, localVZone);
    return localVZone;
  }
  
  public static DataMgr createInstance()
  {
    mObj = new DataMgr();
    return mObj;
  }
  
  public int getAddress(ID paramID)
  {
    VPoint localVPoint = getPntFromID(paramID);
    if (localVPoint == null) {
      return -1;
    }
    return getCommonPropfAddress;
  }
  
  public String getD3AddrStr(int paramInt1, int paramInt2)
  {
    return paramInt1 + 1 + ":" + (paramInt2 / 16 + 1) + "-" + StrRes.itoa(paramInt2 % 16, true, 2);
  }
  
  public String getDetailName(ID paramID)
  {
    if (fType == 2) {
      return StrRes.getStr("IDS_COMMON_UNREGISTERD");
    }
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getDetailName();
  }
  
  public EnumDrvVentMode getDrvVentMode(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getDrvVentMode();
  }
  
  public String getDrvVentModeStr(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getDrvVentModeStr();
  }
  
  public String getErrCodeStr(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getErrCodeStr();
  }
  
  public ItcIconInfo getIconInfo(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getIconInfo();
  }
  
  public static DataMgr getInstance()
  {
    return mObj;
  }
  
  public DataUserInfo getLoginUser()
  {
    return mLoginUser;
  }
  
  public int getMiddleVer()
  {
    return mMiddleVer;
  }
  
  public PntTarget getOrgPntTarget(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    if (localVAbst.isOrgTargetReady() == true) {
      return localVAbst.getOrgPntTarget();
    }
    if (SwingUtilities.isEventDispatchThread() == true)
    {
      JobUpdatePntOrgTarget localJobUpdatePntOrgTarget = new JobUpdatePntOrgTarget(paramID);
      Result localResult = ThreadAppCom.getInstance().addJob(localJobUpdatePntOrgTarget);
      if (!CommonUse.waitJob(localResult)) {
        return null;
      }
      return localVAbst.getOrgPntTarget();
    }
    throw new FatalException("DataMgr.getOrgInnerTarget Target info is not ready");
  }
  
  public VAbst getPnt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getPntNum())) {
      throw new ArrayIndexOutOfBoundsException("DataMgr.getPnt index error");
    }
    return (VAbst)mPntList.get(paramInt);
  }
  
  public VAbst getPntAbst(ID paramID)
  {
    switch (fType)
    {
    case 1: 
      return getZoneFromID(paramID);
    case 0: 
      return getPntFromID(paramID);
    }
    if (!$assertionsDisabled) {
      throw new AssertionError();
    }
    return null;
  }
  
  public PntCurrent getPntCurrent(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getPntCurrent();
  }
  
  public VPoint getPntFromID(ID paramID)
  {
    VPoint localVPoint = (VPoint)mPntIdMap.get(paramID);
    if (localVPoint == null) {
      return VD3Inner.getUnknownPnt();
    }
    return localVPoint;
  }
  
  public int getPntNum()
  {
    return mPntList.size();
  }
  
  public PntTarget getPntTarget(ID paramID, boolean paramBoolean)
  {
    VAbst localVAbst = getPntAbst(paramID);
    if (paramBoolean == true)
    {
      JobUpdatePntTarget localJobUpdatePntTarget = new JobUpdatePntTarget(paramID);
      Result localResult = ThreadAppCom.getInstance().addJob(localJobUpdatePntTarget);
      if (!CommonUse.waitJob(localResult)) {
        return null;
      }
      return localVAbst.getPntTarget();
    }
    return localVAbst.getPntTarget();
  }
  
  public ArrayList getPntTypes(ID paramID)
  {
    ArrayList localArrayList = new ArrayList();
    if (fType != 1) {
      return localArrayList;
    }
    int i = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    int n = 0;
    int i1 = 0;
    VZone localVZone = getZoneFromID(paramID);
    for (int i2 = 0; i2 < localVZone.getPntNum(); i2++)
    {
      ID localID = localVZone.getPntID(i2);
      VPoint localVPoint = getPntFromID(localID);
      PropPntCommon localPropPntCommon = localVPoint.getCommonProp();
      EnumInnerType localEnumInnerType = fInnerType;
      if (localEnumInnerType == EnumInnerType.NORMAL)
      {
        if (i == 0)
        {
          i = 1;
          localArrayList.add(localEnumInnerType);
        }
      }
      else if (localEnumInnerType == EnumInnerType.VENT)
      {
        if (n == 0)
        {
          n = 1;
          localArrayList.add(localEnumInnerType);
        }
      }
      else if (localEnumInnerType == EnumInnerType.AIRHAN)
      {
        if (j == 0)
        {
          j = 1;
          localArrayList.add(localEnumInnerType);
        }
      }
      else if (localEnumInnerType == EnumInnerType.CHILLER)
      {
        if (k == 0)
        {
          k = 1;
          localArrayList.add(localEnumInnerType);
        }
      }
      else if (localEnumInnerType == EnumInnerType.FFU)
      {
        if (m == 0)
        {
          m = 1;
          localArrayList.add(localEnumInnerType);
        }
      }
      else if (i1 == 0)
      {
        i1 = 1;
        localArrayList.add(localEnumInnerType);
      }
    }
    return localArrayList;
  }
  
  public synchronized Port getPort()
  {
    return (Port)mPort.clone();
  }
  
  public int getPortNum(ID paramID)
  {
    VPoint localVPoint = getPntFromID(paramID);
    if (localVPoint == null) {
      return -1;
    }
    return getCommonPropfPortNum;
  }
  
  public String getRoomTempStr(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getRoomTempStr();
  }
  
  public String getSetTempStr(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getSetTempStr();
  }
  
  public String getShortName(int paramInt1, int paramInt2)
  {
    for (int i = 0; i < getPntNum(); i++)
    {
      VPoint localVPoint = (VPoint)mPntList.get(i);
      int j = getCommonPropfPortNum;
      int k = getCommonPropfAddress;
      if ((j == paramInt1) && (k == paramInt2) && (getIDfType == 0)) {
        return localVPoint.getShortName();
      }
    }
    return getD3AddrStr(paramInt1, paramInt2);
  }
  
  public String getShortName(ID paramID)
  {
    if (fType == 2) {
      return StrRes.getStr("IDS_COMMON_UNREGISTERD");
    }
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getShortName();
  }
  
  public PntState getState(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.getState();
  }
  
  public VZone getZone(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= getZoneNum())) {
      throw new ArrayIndexOutOfBoundsException("DataMgr.getZone index error");
    }
    return (VZone)mZoneList.get(paramInt);
  }
  
  public VZone getZoneFromID(ID paramID)
  {
    VZone localVZone = (VZone)mZoneIdMap.get(paramID);
    if (localVZone == null) {
      return VZone.getUnknownZone();
    }
    return localVZone;
  }
  
  public ArrayList getZoneIDList()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < getZoneNum(); i++) {
      localArrayList.add(getZonegetPropfZoneID);
    }
    return localArrayList;
  }
  
  public int getZoneNum()
  {
    return mZoneList.size();
  }
  
  public boolean isExist(ID paramID)
  {
    switch (fType)
    {
    case 1: 
      return mZoneIdMap.get(paramID) != null;
    case 0: 
      return mPntIdMap.get(paramID) != null;
    }
    return false;
  }
  
  public boolean isValidOFF(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return getPntTargetfValidOFF;
  }
  
  public boolean isValidON(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return getPntTargetfValidON;
  }
  
  public boolean isValidOperation(ID paramID)
  {
    VAbst localVAbst = getPntAbst(paramID);
    return localVAbst.isValidOperation();
  }
  
  public void setLoginUser(DataUserInfo paramDataUserInfo)
  {
    mLoginUser = paramDataUserInfo;
  }
  
  public void setMiddleVer(int paramInt)
  {
    mMiddleVer = paramInt;
  }
  
  public synchronized void setPort(Port paramPort)
  {
    mPort = paramPort;
  }
  
  public void updateStatus(ID paramID, EnumComStat paramEnumComStat, TargetErr paramTargetErr, EnumPntStat paramEnumPntStat, EnumDrvMode paramEnumDrvMode, EnumVentMode paramEnumVentMode, int paramInt1, int paramInt2, EnumVentVol paramEnumVentVol, Temperature paramTemperature1, Temperature paramTemperature2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt5, int paramInt6, int paramInt7, Temperature paramTemperature3, Temperature paramTemperature4, Temperature paramTemperature5, Temperature paramTemperature6, boolean paramBoolean4, boolean paramBoolean5, int paramInt8)
  {
    VAbst localVAbst = getPntAbst(paramID);
    localVAbst.updateStatus(paramEnumComStat, paramTargetErr, paramEnumPntStat, paramEnumDrvMode, paramEnumVentMode, paramInt1, paramInt2, paramEnumVentVol, paramTemperature1, paramTemperature2, paramInt3, paramInt4, paramBoolean1, paramBoolean2, paramBoolean3, paramInt5, paramInt6, paramInt7, paramTemperature3, paramTemperature4, paramTemperature5, paramTemperature6, paramBoolean4, paramBoolean5, paramInt8);
  }
}
