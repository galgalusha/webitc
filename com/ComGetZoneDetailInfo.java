package webitc.com;

import java.util.ArrayList;
import webitc.common.StrRes;
import webitc.data.ID;
import webitc.data.point.TargetErr;
import webitc.data.point.ZoneInfo;

public class ComGetZoneDetailInfo
  extends ComAbstract
{
  public ComGetZoneDetailInfo() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60121) {
      return false;
    }
    return getRecvSize() == 244;
  }
  
  public ZoneInfo getZoneInfo()
  {
    ID localID = new ID(1, getSendId());
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 10; i++)
    {
      TargetErr localTargetErr = new TargetErr(mReceiveBuffer, 124 + 12 * i, true, true);
      localArrayList.add(localTargetErr);
    }
    if (localID.equals(ID.ZONE_ALL) == true) {
      return new ZoneInfo(StrRes.getStr("IDS_COMMON_NAME_ZONE_ALL"), StrRes.getStr("IDS_COMMON_DISCRIPTION_ZONE_ALL"), ComAbstract.getInt(mReceiveBuffer, 112), ComAbstract.getInt(mReceiveBuffer, 116), ComAbstract.getInt(mReceiveBuffer, 120), localArrayList);
    }
    return new ZoneInfo(ComAbstract.getUcs2(mReceiveBuffer, 32, 16), ComAbstract.getUcs2(mReceiveBuffer, 48, 64), ComAbstract.getInt(mReceiveBuffer, 112), ComAbstract.getInt(mReceiveBuffer, 116), ComAbstract.getInt(mReceiveBuffer, 120), localArrayList);
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60120);
    setSendSize(32);
  }
  
  public void setZoneInfo(ZoneInfo paramZoneInfo)
  {
    ComAbstract.setUcs2(fShortName, mReceiveBuffer, 32, 16);
    ComAbstract.setUcs2(fDetailName, mReceiveBuffer, 48, 64);
    ComAbstract.setInt(fMemberNum, mReceiveBuffer, 112);
    ComAbstract.setInt(fIntervalON, mReceiveBuffer, 116);
    ComAbstract.setInt(fAutoCtrlBits, mReceiveBuffer, 120);
    for (int i = 0; i < fErrorHistory.size(); i++)
    {
      TargetErr localTargetErr = (TargetErr)fErrorHistory.get(i);
      localTargetErr.setBuffer(mReceiveBuffer, 124 + 12 * i, true, true);
    }
  }
}
