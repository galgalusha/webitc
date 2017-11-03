package webitc.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;
import webitc.common.AppletAbst;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumComStat;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumHCSel;
import webitc.common.enum2.EnumInnerType;
import webitc.common.enum2.EnumLang;
import webitc.common.enum2.EnumModelType;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.CommonCalendar;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntInfo;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.data.point.PropPntCommon;
import webitc.data.point.PropZone;
import webitc.data.point.TargetErr;
import webitc.data.point.VAbst;
import webitc.data.point.VD3Inner;
import webitc.data.point.VPoint;
import webitc.data.point.VZone;
import webitc.data.point.ZoneInfo;
import webitc.data.schedule.AbstSchedule;
import webitc.data.schedule.Daily;
import webitc.data.schedule.DefaultAction;
import webitc.data.schedule.Program;
import webitc.data.schedule.SchEvent;
import webitc.data.schedule.SchException;

class VirtualITC
{
  private DataMgr mMgr = new DataMgr();
  private EnumModelType mModelType = EnumModelType.ITC;
  private int mPpdCollectionDate = 25;
  private int mPpdCollectionType = 0;
  private int[] mPpdContractPwr = new int[12];
  private int mPpdExclusionDay = 0;
  private int[] mPpdExclusionTime = new int[7];
  private int mPpdNightTime = 0;
  private int[] mPpdSpecialDay = new int[12];
  private ArrayList mProgramList = new ArrayList();
  private int mUserId = 100;
  private ArrayList mUserList = new ArrayList();
  private final PntCurrent sACCurrent = new PntCurrent(EnumDrvMode.COOL, EnumVentMode.ELSE, EnumVentVol.ELSE, 0, new Temperature(true, 25.0F), new Temperature(true, 28.0F), EnumRemcCode.ENABLE, EnumPntStat.ON, EnumPntStat.ON, 0, 1, 1, 1, 1);
  private final PntTarget sACTarget = new PntTarget(775, 0, 0, true, 0.1F, new Temperature((byte)35, (byte)0), new Temperature((byte)20, (byte)0), new Temperature((byte)30, (byte)0), new Temperature((byte)15, (byte)0), false, 15, true, 2, true, true, true);
  private final PntCurrent sD3DIOCurrent = new PntCurrent(EnumDrvMode.ELSE, EnumVentMode.ELSE, EnumVentVol.ELSE, 0, new Temperature(false, 25.0F), new Temperature(false, 28.0F), EnumRemcCode.ELSE, EnumPntStat.ELSE, EnumPntStat.ELSE, 0, 1, 1, -1, -1);
  private final PntTarget sD3DIOTarget = new PntTarget(0, 0, 0, false, 0.1F, new Temperature((byte)35, (byte)0), new Temperature((byte)20, (byte)0), new Temperature((byte)30, (byte)0), new Temperature((byte)15, (byte)0), false, 0, false, 0, true, true, true);
  private final PntTarget sD3DITarget = new PntTarget(0, 0, 0, false, 0.1F, new Temperature((byte)35, (byte)0), new Temperature((byte)20, (byte)0), new Temperature((byte)30, (byte)0), new Temperature((byte)15, (byte)0), false, 0, false, 0, true, false, false);
  private final PntState sPntState = new PntState(EnumComStat.NORMAL, new TargetErr(), EnumPntStat.OFF);
  private final PntCurrent sVentCurrent = new PntCurrent(EnumDrvMode.ELSE, EnumVentMode.AUTO, EnumVentVol.NORMAL_AUTO, 0, new Temperature(true, 25.0F), new Temperature(true, 28.0F), EnumRemcCode.ENABLE, EnumPntStat.ELSE, EnumPntStat.ELSE, 0, 1, 1, -1, -1);
  private final PntTarget sVentTarget = new PntTarget(0, 7, 63, false, 0.1F, new Temperature((byte)35, (byte)0), new Temperature((byte)20, (byte)0), new Temperature((byte)30, (byte)0), new Temperature((byte)15, (byte)0), false, 1, false, 0, true, true, true);
  
  public VirtualITC()
  {
    VZone localVZone = mMgr.addZone(new PropZone(ID.ZONE_ALL, 15, StrRes.getStr("IDS_COMMON_NAME_ZONE_ALL"), StrRes.getStr("IDS_COMMON_DISCRIPTION_ZONE_ALL")));
    localVZone.setPntReady();
    localVZone.setPntStateInfo(sPntState, (PntCurrent)sACCurrent.clone());
    Object localObject2;
    Object localObject3;
    Object localObject4;
    try
    {
      String str1 = AppletAbst.getInstance().getCodeBase().toString() + "webdata_" + SystemInfo.getLang().toString() + ".txt";
      localObject1 = null;
      try
      {
        localObject1 = new URI(str1);
      }
      catch (URISyntaxException localURISyntaxException)
      {
        str1 = AppletAbst.getInstance().getCodeBase().getPath() + "webdata_" + SystemInfo.getLang().toString() + ".txt";
        String str2 = AppletAbst.getInstance().getCodeBase().getAuthority();
        localObject1 = new URI("file", str2, str1, null, null);
      }
      catch (Exception localException2)
      {
        throw localException2;
      }
      localObject2 = new FileInputStream(new File((URI)localObject1));
      localObject3 = new InputStreamReader((InputStream)localObject2, "UTF-8");
      BufferedReader localBufferedReader = new BufferedReader((Reader)localObject3);
      localObject4 = localBufferedReader.readLine();
      if (localObject4 != null) {
        if (((String)localObject4).indexOf("ITC") != -1) {
          mModelType = EnumModelType.ITC;
        } else if (((String)localObject4).indexOf("VEUP") != -1) {
          mModelType = EnumModelType.VEUP;
        } else if (((String)localObject4).indexOf("ACS") != -1) {
          mModelType = EnumModelType.ACS;
        } else if (((String)localObject4).indexOf("EXC") != -1) {
          mModelType = EnumModelType.EXC;
        }
      }
      int k = 0;
      int m = 0;
      String str3;
      while ((str3 = localBufferedReader.readLine()) != null) {
        if (str3.equals("") != true)
        {
          int n = 0;
          int i1 = Integer.parseInt(str3);
          String str4;
          if ((str4 = localBufferedReader.readLine()) == null)
          {
            localBufferedReader.close();
            return;
          }
          String str5;
          if ((str5 = localBufferedReader.readLine()) == null)
          {
            localBufferedReader.close();
            return;
          }
          n = Integer.parseInt(str5);
          String str6;
          if ((str6 = localBufferedReader.readLine()) == null)
          {
            localBufferedReader.close();
            return;
          }
          String str7;
          if ((str7 = localBufferedReader.readLine()) == null)
          {
            localBufferedReader.close();
            return;
          }
          Object localObject5;
          Object localObject6;
          Object localObject7;
          Object localObject8;
          if (str4.equals("A") == true)
          {
            localObject5 = new ID(0, i1);
            localObject6 = new PropPntCommon((ID)localObject5, k, m, n, str6, str7, EnumPntType.D3_INNER, EnumInnerType.NORMAL);
            localObject7 = (VD3Inner)mMgr.addPnt(EnumPntType.D3_INNER, EnumInnerType.NORMAL, (PropPntCommon)localObject6);
            ((VD3Inner)localObject7).setInnerTarget((PntTarget)sACTarget.clone());
            ((VD3Inner)localObject7).setOrgInnerTarget((PntTarget)sACTarget.clone());
            if (i1 == 1)
            {
              localObject8 = (PntCurrent)sACCurrent.clone();
              mFilterSign = 1;
              ((VD3Inner)localObject7).setPntStateInfo(sPntState, (PntCurrent)localObject8);
            }
            else
            {
              ((VD3Inner)localObject7).setPntStateInfo(sPntState, (PntCurrent)sACCurrent.clone());
            }
            localVZone.addPntID((ID)localObject5);
            localObject8 = addPntTarget(localVZone.getPntTarget(), ((VD3Inner)localObject7).getPntTarget());
            localVZone.setInnerTarget((PntTarget)localObject8);
            localVZone.setOrgInnerTarget((PntTarget)localObject8);
          }
          else if (str4.equals("V") == true)
          {
            localObject5 = new ID(0, i1);
            localObject6 = new PropPntCommon((ID)localObject5, k, m, n, str6, str7, EnumPntType.D3_INNER, EnumInnerType.VENT);
            localObject7 = (VD3Inner)mMgr.addPnt(EnumPntType.D3_INNER, EnumInnerType.VENT, (PropPntCommon)localObject6);
            ((VD3Inner)localObject7).setInnerTarget((PntTarget)sVentTarget.clone());
            ((VD3Inner)localObject7).setOrgInnerTarget((PntTarget)sVentTarget.clone());
            ((VD3Inner)localObject7).setPntStateInfo(sPntState, (PntCurrent)sVentCurrent.clone());
            localVZone.addPntID((ID)localObject5);
            localObject8 = addPntTarget(localVZone.getPntTarget(), ((VD3Inner)localObject7).getPntTarget());
            localVZone.setInnerTarget((PntTarget)localObject8);
            localVZone.setOrgInnerTarget((PntTarget)localObject8);
          }
          else if (str4.equals("O") == true)
          {
            localObject5 = new ID(0, i1);
            localObject6 = new PropPntCommon((ID)localObject5, k, m, n, str6, str7, EnumPntType.D3_DIO, EnumInnerType.NORMAL);
            localObject7 = (VD3Inner)mMgr.addPnt(EnumPntType.D3_DIO, EnumInnerType.NORMAL, (PropPntCommon)localObject6);
            ((VD3Inner)localObject7).setInnerTarget((PntTarget)sD3DIOTarget.clone());
            ((VD3Inner)localObject7).setOrgInnerTarget((PntTarget)sD3DIOTarget.clone());
            ((VD3Inner)localObject7).setPntStateInfo(sPntState, (PntCurrent)sD3DIOCurrent.clone());
            localVZone.addPntID((ID)localObject5);
            localObject8 = addPntTarget(localVZone.getPntTarget(), ((VD3Inner)localObject7).getPntTarget());
            localVZone.setInnerTarget((PntTarget)localObject8);
            localVZone.setOrgInnerTarget((PntTarget)localObject8);
          }
          else if (str4.equals("I") == true)
          {
            localObject5 = new ID(0, i1);
            localObject6 = new PropPntCommon((ID)localObject5, k, m, n, str6, str7, EnumPntType.D3_DI, EnumInnerType.NORMAL);
            localObject7 = (VD3Inner)mMgr.addPnt(EnumPntType.D3_DI, EnumInnerType.NORMAL, (PropPntCommon)localObject6);
            ((VD3Inner)localObject7).setInnerTarget((PntTarget)sD3DITarget.clone());
            ((VD3Inner)localObject7).setOrgInnerTarget((PntTarget)sD3DITarget.clone());
            ((VD3Inner)localObject7).setPntStateInfo(sPntState, (PntCurrent)sD3DIOCurrent.clone());
            localVZone.addPntID((ID)localObject5);
            localObject8 = addPntTarget(localVZone.getPntTarget(), ((VD3Inner)localObject7).getPntTarget());
            localVZone.setInnerTarget((PntTarget)localObject8);
            localVZone.setOrgInnerTarget((PntTarget)localObject8);
          }
          else if (str4.equals("Z") == true)
          {
            if ((localObject5 = localBufferedReader.readLine()) == null)
            {
              localBufferedReader.close();
              return;
            }
            localObject6 = new ID(1, i1);
            localObject7 = new PropZone((ID)localObject6, n, str6, str7);
            localObject8 = mMgr.addZone((PropZone)localObject7);
            ((VZone)localObject8).setPntReady();
            ((VZone)localObject8).setPntStateInfo(sPntState, (PntCurrent)sACCurrent.clone());
            int i2 = 0;
            int i3 = 0;
            VPoint localVPoint;
            PntTarget localPntTarget;
            while ((i3 = ((String)localObject5).indexOf(",", i2)) != -1)
            {
              i4 = Integer.parseInt(((String)localObject5).substring(i2, i3));
              ((VZone)localObject8).addPntID(new ID(0, i4));
              localVPoint = mMgr.getPntFromID(new ID(0, i4));
              localPntTarget = addPntTarget(((VZone)localObject8).getPntTarget(), localVPoint.getPntTarget());
              ((VZone)localObject8).setInnerTarget(localPntTarget);
              ((VZone)localObject8).setOrgInnerTarget(localPntTarget);
              i2 = i3 + 1;
            }
            int i4 = Integer.parseInt(((String)localObject5).substring(i2));
            if (i4 != 0)
            {
              ((VZone)localObject8).addPntID(new ID(0, i4));
              localVPoint = mMgr.getPntFromID(new ID(0, i4));
              localPntTarget = addPntTarget(((VZone)localObject8).getPntTarget(), localVPoint.getPntTarget());
              ((VZone)localObject8).setInnerTarget(localPntTarget);
              ((VZone)localObject8).setOrgInnerTarget(localPntTarget);
            }
          }
          if (!str4.equals("Z"))
          {
            m++;
            if (m >= 64)
            {
              m = 0;
              k++;
            }
          }
        }
      }
      localBufferedReader.close();
    }
    catch (Exception localException1)
    {
      System.out.println(localException1.toString());
    }
    for (int i = 0; i < 8; i++)
    {
      localObject1 = new Program();
      ((Program)localObject1).setActive(false);
      ((Program)localObject1).setName("Schedule " + (i + 1));
      localObject2 = new AbstSchedule(7L);
      localObject3 = new SchException(10L);
      for (int j = 0; j < 10; j++) {
        ((SchException)localObject3).setExceptionName(j, "Ex " + j);
      }
      localObject4 = new CommonCalendar();
      ((CommonCalendar)localObject4).setStartMonth(2004, 4);
      ((Program)localObject1).setWeekly((AbstSchedule)localObject2);
      ((Program)localObject1).setException((SchException)localObject3);
      ((Program)localObject1).setCalendar((CommonCalendar)localObject4);
      mProgramList.add(localObject1);
    }
    Object localObject1 = new DataUserInfo(0, "service", "daikin");
    mUserList.add(localObject1);
    localObject1 = new DataUserInfo(1, "admin", "");
    mUserList.add(localObject1);
    localObject1 = new DataUserInfo(99, "guest", "guest");
    mUserList.add(localObject1);
  }
  
  private PntTarget addPntTarget(PntTarget paramPntTarget1, PntTarget paramPntTarget2)
  {
    int i = fValidRunMode | fValidRunMode;
    int j = fValidVentMode | fValidVentMode;
    int k = fValidVentVol | fValidVentVol;
    boolean bool1 = false;
    if ((fValidSetTemp == true) || (fValidSetTemp == true)) {
      bool1 = true;
    }
    float f = 0.1F;
    Temperature localTemperature1 = new Temperature((byte)35, (byte)0);
    Temperature localTemperature2 = new Temperature((byte)20, (byte)0);
    Temperature localTemperature3 = new Temperature((byte)30, (byte)0);
    Temperature localTemperature4 = new Temperature((byte)15, (byte)0);
    boolean bool2 = false;
    int m = fRkkValidBit | fRkkValidBit;
    boolean bool3 = false;
    if ((fValidWindDirect == true) || (fValidWindDirect == true)) {
      bool3 = true;
    }
    int n = 0;
    if ((fValidWindVolume != 0) || (fValidWindVolume != 0)) {
      n = 1;
    }
    boolean bool4 = false;
    boolean bool5 = true;
    boolean bool6 = true;
    return new PntTarget(i, j, k, bool1, f, localTemperature1, localTemperature2, localTemperature3, localTemperature4, bool2, m, bool3, n, bool4, bool5, bool6);
  }
  
  private void addUser(ComAddUser paramComAddUser)
  {
    initResponse(paramComAddUser, 60608);
    paramComAddUser.setRecvSize(32);
    DataUserInfo localDataUserInfo = new DataUserInfo(mUserId, ComAbstract.getAscii(mSendBuffer, 32, 16), ComAbstract.getAscii(mSendBuffer, 48, 16));
    mUserList.add(localDataUserInfo);
    paramComAddUser.setSendArg1(1);
    paramComAddUser.setRecvId(mUserId);
    mUserId += 1;
  }
  
  private void delUser(ComDeleteUser paramComDeleteUser)
  {
    initResponse(paramComDeleteUser, 60610);
    paramComDeleteUser.setRecvSize(32);
    int i = mUserList.size();
    paramComDeleteUser.setRecvArg2(i);
    for (int j = 0; j < i; j++)
    {
      DataUserInfo localDataUserInfo = (DataUserInfo)mUserList.get(j);
      if (localDataUserInfo.getUserID() == paramComDeleteUser.getSendId())
      {
        mUserList.remove(j);
        paramComDeleteUser.setSendArg1(1);
        return;
      }
    }
    paramComDeleteUser.setSendArg1(0);
  }
  
  private void getGetScheduleAll(ComGetScheduleAll paramComGetScheduleAll)
  {
    initResponse(paramComGetScheduleAll, 60201);
    int i = mProgramList.size();
    paramComGetScheduleAll.getClass();
    paramComGetScheduleAll.setRecvSize(32 + 36 * i);
    paramComGetScheduleAll.setRecvArg2(i);
    for (int j = 0; j < 8; j++)
    {
      Program localProgram = (Program)mProgramList.get(j);
      String str = localProgram.getName();
      paramComGetScheduleAll.setSchedule(j, str, localProgram.isActive());
    }
  }
  
  private void getPassword(ComGetPswd paramComGetPswd)
  {
    initResponse(paramComGetPswd, 60604);
    paramComGetPswd.setRecvSize(48);
  }
  
  private void getPntDetailInfo(ComGetPntDetailInfo paramComGetPntDetailInfo)
  {
    initResponse(paramComGetPntDetailInfo, 60119);
    paramComGetPntDetailInfo.setRecvSize(224);
    ID localID = new ID(0, paramComGetPntDetailInfo.getSendId());
    VD3Inner localVD3Inner = (VD3Inner)mMgr.getPntFromID(localID);
    PropPntCommon localPropPntCommon = localVD3Inner.getCommonProp();
    PntCurrent localPntCurrent = localVD3Inner.getPntCurrent();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 10; i++) {
      localArrayList.add(new TargetErr());
    }
    PntInfo localPntInfo = new PntInfo(fShortName, fDetailName, getCommonPropfPntType, localVD3Inner.getInnerType(), "1:1-01", 0, false, EnumHCSel.OFF, 0, true, mRoomTemp.fTemp, getStatefTargetState, localArrayList);
    paramComGetPntDetailInfo.setPntInfo(localPntInfo);
  }
  
  private void getPntOrgTarget(ComGetPntOrgTarget paramComGetPntOrgTarget)
  {
    initResponse(paramComGetPntOrgTarget, 60117);
    paramComGetPntOrgTarget.setRecvSize(68);
    ID localID = new ID(paramComGetPntOrgTarget.getSendArg1(), paramComGetPntOrgTarget.getSendId());
    PntTarget localPntTarget = mMgr.getOrgPntTarget(localID);
    paramComGetPntOrgTarget.setInnerTarget(localPntTarget);
  }
  
  private void getPntProp(ComGetPntProp paramComGetPntProp)
  {
    initResponse(paramComGetPntProp, 60103);
    int i = mMgr.getPntNum();
    paramComGetPntProp.getClass();
    paramComGetPntProp.setRecvSize(32 + 100 * i);
    paramComGetPntProp.setRecvArg2(i);
    for (int j = 0; j < i; j++)
    {
      VD3Inner localVD3Inner = (VD3Inner)mMgr.getPnt(j);
      paramComGetPntProp.setPntType(j, getCommonPropfPntType);
      paramComGetPntProp.setInnerType(j, localVD3Inner.getInnerType());
      paramComGetPntProp.setProp(j, localVD3Inner.getCommonProp());
    }
  }
  
  private void getPntState(ComGetPntState paramComGetPntState)
    throws Exception
  {
    updateZones();
    initResponse(paramComGetPntState, 60105);
    ArrayList localArrayList = paramComGetPntState.getIDArray();
    paramComGetPntState.getClass();
    paramComGetPntState.setRecvSize(localArrayList.size() * 48 + 32);
    paramComGetPntState.setRecvArg2(localArrayList.size());
    paramComGetPntState.setRecvArg4(3);
    paramComGetPntState.setPntID(localArrayList);
    for (int i = 0; i < localArrayList.size(); i++)
    {
      ID localID = (ID)localArrayList.get(i);
      PntState localPntState = mMgr.getState(localID);
      PntCurrent localPntCurrent = mMgr.getPntCurrent(localID);
      PntTarget localPntTarget = mMgr.getOrgPntTarget(localID);
      int j = mMgr.getPntAbst(localID).getIconAppend();
      if (mFilterSign != 0) {
        j |= 0x8;
      } else {
        j &= 0x7;
      }
      paramComGetPntState.setPntState(i, fCommState, fTargetState, fOnOffState, mDrvMode, mVentMode, mSetTemp, mRoomTemp, j, fValidON, fValidOFF, true, mWindDirect, mWindVolume, mVentVol, fValidRunMode, fValidVentMode, fValidVentVol, fCoolUpper, fCoolLower, fWarmUpper, fWarmLower, fValidWindDirect, fValidWindVolume, fValidSetTemp);
    }
  }
  
  private void getPntStateDetail(ComGetPntStateDetail paramComGetPntStateDetail)
  {
    initResponse(paramComGetPntStateDetail, 60115);
    paramComGetPntStateDetail.setRecvSize(80);
    ID localID = new ID(paramComGetPntStateDetail.getSendArg1(), paramComGetPntStateDetail.getSendId());
    paramComGetPntStateDetail.setPntState(mMgr.getState(localID));
    paramComGetPntStateDetail.setInnerCurrent(mMgr.getPntCurrent(localID));
  }
  
  private void getPntTarget(ComGetPntTarget paramComGetPntTarget)
  {
    initResponse(paramComGetPntTarget, 60111);
    paramComGetPntTarget.setRecvSize(68);
    ID localID = new ID(paramComGetPntTarget.getSendArg1(), paramComGetPntTarget.getSendId());
    PntTarget localPntTarget = mMgr.getOrgPntTarget(localID);
    paramComGetPntTarget.setInnerTarget(localPntTarget);
  }
  
  private void getPpdCollectionType(ComGetPpdCollectionType paramComGetPpdCollectionType)
  {
    initResponse(paramComGetPpdCollectionType, 60406);
    paramComGetPpdCollectionType.setRecvSize(80);
    paramComGetPpdCollectionType.setRecvArg1(1);
    paramComGetPpdCollectionType.setRecvArg2(mPpdCollectionType);
    paramComGetPpdCollectionType.setRecvArg3(mPpdCollectionDate);
    int i = 32;
    for (int j = 0; j < mPpdContractPwr.length; j++)
    {
      ComAbstract.setInt(mPpdContractPwr[j], mReceiveBuffer, i);
      i += 4;
    }
  }
  
  private void getPpdData(ComGetPpdData paramComGetPpdData)
  {
    initResponse(paramComGetPpdData, 60404);
    ArrayList localArrayList = getPpdTargetArray();
    GregorianCalendar localGregorianCalendar1 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    localGregorianCalendar1.setTimeInMillis((paramComGetPpdData.getSendArg1() + 32400) * 1000L);
    GregorianCalendar localGregorianCalendar2 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    localGregorianCalendar2.set(localGregorianCalendar1.get(1), localGregorianCalendar1.get(2), localGregorianCalendar1.get(5), 0, 0, 0);
    localGregorianCalendar1.setTimeInMillis((paramComGetPpdData.getSendArg2() + 32400 + 86400) * 1000L);
    GregorianCalendar localGregorianCalendar3 = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    localGregorianCalendar3.set(localGregorianCalendar1.get(1), localGregorianCalendar1.get(2), localGregorianCalendar1.get(5), 0, 0, 0);
    int i = (int)(localGregorianCalendar3.getTimeInMillis() - localGregorianCalendar2.getTimeInMillis()) / 1000 / 3600;
    if (i < 0) {
      paramComGetPpdData.setRecvArg1(0);
    } else {
      paramComGetPpdData.setRecvArg1(1);
    }
    paramComGetPpdData.setRecvSize(32 + (8 + localArrayList.size() * 20) * i);
    paramComGetPpdData.setRecvArg2(i);
    paramComGetPpdData.setRecvArg3(localArrayList.size());
    paramComGetPpdData.setRecvArg4(0);
    int j = 32;
    for (int k = 0; k < i; k++)
    {
      ComGetPpdData.setInt(localGregorianCalendar2.get(1), mReceiveBuffer, j);
      mReceiveBuffer.position(j + 4);
      mReceiveBuffer.put((byte)1);
      mReceiveBuffer.put((byte)(localGregorianCalendar2.get(2) + 1));
      mReceiveBuffer.put((byte)localGregorianCalendar2.get(5));
      mReceiveBuffer.put((byte)localGregorianCalendar2.get(11));
      j += 8;
      localGregorianCalendar2.add(11, 1);
      for (int m = 0; m < localArrayList.size(); m++)
      {
        VD3Inner localVD3Inner = (VD3Inner)mMgr.getPntAbst((ID)localArrayList.get(m));
        PropPntCommon localPropPntCommon = localVD3Inner.getCommonProp();
        int n = fPortNum * 64 + fAddress;
        ComGetPpdData.setInt(n, mReceiveBuffer, j);
        Random localRandom = new Random();
        ComGetPpdData.setInt(localRandom.nextInt(100), mReceiveBuffer, j + 4);
        ComGetPpdData.setInt(localRandom.nextInt(100), mReceiveBuffer, j + 8);
        ComGetPpdData.setInt(localRandom.nextInt(100), mReceiveBuffer, j + 12);
        ComGetPpdData.setInt(localRandom.nextInt(100), mReceiveBuffer, j + 16);
        j += 20;
      }
    }
  }
  
  private void getPpdSchedule(ComGetPpdSchedule paramComGetPpdSchedule)
  {
    initResponse(paramComGetPpdSchedule, 60410);
    paramComGetPpdSchedule.setRecvSize(108);
    paramComGetPpdSchedule.setRecvArg1(1);
    paramComGetPpdSchedule.setRecvArg2(mPpdExclusionDay);
    paramComGetPpdSchedule.setRecvArg3(mPpdNightTime);
    int i = 32;
    for (int j = 0; j < 12; j++)
    {
      ComGetPpdSchedule.setInt(mPpdSpecialDay[j], mReceiveBuffer, i);
      i += 4;
    }
    for (int k = 0; k < 7; k++)
    {
      ComGetPpdSchedule.setInt(mPpdExclusionTime[k], mReceiveBuffer, i);
      i += 4;
    }
  }
  
  private void getPpdTarget(ComGetPpdTarget paramComGetPpdTarget)
  {
    ArrayList localArrayList = getPpdTargetArray();
    initResponse(paramComGetPpdTarget, 60402);
    paramComGetPpdTarget.setRecvSize(32 + localArrayList.size() * 20);
    paramComGetPpdTarget.setRecvArg1(1);
    paramComGetPpdTarget.setRecvArg2(localArrayList.size());
    for (int i = 0; i < localArrayList.size(); i++)
    {
      int j = 32 + i * 20;
      VD3Inner localVD3Inner = (VD3Inner)mMgr.getPntAbst((ID)localArrayList.get(i));
      PropPntCommon localPropPntCommon = localVD3Inner.getCommonProp();
      int k = fPortNum * 64 + fAddress;
      ComAbstract.setInt(k, mReceiveBuffer, j);
      if (SystemInfo.getModelType() == EnumModelType.ITC) {
        ComAbstract.setInt(0, mReceiveBuffer, j + 4);
      } else if (SystemInfo.getModelType() == EnumModelType.VEUP)
      {
        if (i % 3 == 0) {
          ComAbstract.setInt(0, mReceiveBuffer, j + 4);
        } else if (i % 3 == 1) {
          ComAbstract.setInt(1, mReceiveBuffer, j + 4);
        } else {
          ComAbstract.setInt(2, mReceiveBuffer, j + 4);
        }
      }
      else {
        ComAbstract.setInt(2, mReceiveBuffer, j + 4);
      }
      ID localID = localVD3Inner.getID();
      ComAbstract.setInt(fID, mReceiveBuffer, j + 8);
      ComAbstract.setInt(0, mReceiveBuffer, j + 12);
      ComAbstract.setInt(0, mReceiveBuffer, j + 16);
    }
  }
  
  private ArrayList getPpdTargetArray()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < mMgr.getPntNum(); i++)
    {
      VAbst localVAbst = mMgr.getPnt(i);
      if (localVAbst.getType() == EnumPntType.D3_INNER)
      {
        VD3Inner localVD3Inner = (VD3Inner)localVAbst;
        if ((getCommonPropfPntType == EnumPntType.D3_INNER) && (localVD3Inner.getInnerType() == EnumInnerType.NORMAL)) {
          localArrayList.add(localVAbst.getID());
        }
      }
    }
    return localArrayList;
  }
  
  private void getSchedule(ComGetSchProgram paramComGetSchProgram)
  {
    initResponse(paramComGetSchProgram, 60203);
    int i = paramComGetSchProgram.getSendId();
    Program localProgram = (Program)mProgramList.get(i);
    paramComGetSchProgram.setProgram(localProgram);
  }
  
  private void getStrLen(ComGetStrLen paramComGetStrLen)
  {
    initResponse(paramComGetStrLen, 60701);
    paramComGetStrLen.setRecvSize(32);
    String str = paramComGetStrLen.getStr();
    int i = 0;
    for (int j = 0; j < str.length(); j++)
    {
      int k = str.charAt(j);
      if ((k & 0xFF80) != 0) {
        i += 2;
      } else {
        i++;
      }
    }
    paramComGetStrLen.setRecvArg2(i);
  }
  
  private void getSysInit(ComGetSysInit paramComGetSysInit)
  {
    initResponse(paramComGetSysInit, 60007);
    paramComGetSysInit.setRecvSize(64);
    String str = AppletAbst.getInstance().getParameter("lang", "en");
    if ((mModelType == EnumModelType.ACS) || (mModelType == EnumModelType.EXC)) {
      paramComGetSysInit.setRecvArg2(EnumLang.JAPANESE.getEnumValue());
    } else {
      paramComGetSysInit.setRecvArg2(EnumLang.getEnum(str).getEnumValue());
    }
    paramComGetSysInit.setRecvArg3(0);
    TimeZone localTimeZone = TimeZone.getDefault();
    int i = -(localTimeZone.getRawOffset() / 60000);
    paramComGetSysInit.setRecvArg4(i);
    ComAbstract.setInt(0, mReceiveBuffer, 32);
    ComAbstract.setInt(1, mReceiveBuffer, 44);
    ComAbstract.setInt(0, mReceiveBuffer, 48);
    ComAbstract.setInt(255, mReceiveBuffer, 52);
    ComAbstract.setShort((short)35, mReceiveBuffer, 56);
    ComAbstract.setShort((short)15, mReceiveBuffer, 58);
    ComAbstract.setInt(1, mReceiveBuffer, 60);
  }
  
  private void getSysVersion(ComGetSysVersion paramComGetSysVersion)
  {
    initResponse(paramComGetSysVersion, 60009);
    paramComGetSysVersion.setRecvSize(64);
    paramComGetSysVersion.setRecvArg1(1);
    paramComGetSysVersion.setRecvArg2(mModelType.getEnumValue());
    paramComGetSysVersion.setRecvArg3(4);
    paramComGetSysVersion.setRecvArg4(1);
    ComGetSysVersion.setAscii("Ver4.30.00", mReceiveBuffer, 32, 32);
  }
  
  private void getUserAll(ComGetUserAll paramComGetUserAll)
  {
    initResponse(paramComGetUserAll, 60606);
    int i = mUserList.size();
    paramComGetUserAll.setRecvArg2(i);
    for (int j = 0; j < i; j++)
    {
      DataUserInfo localDataUserInfo = (DataUserInfo)mUserList.get(j);
      int k = 32 + j * 20;
      ComAbstract.setInt(localDataUserInfo.getUserID(), mReceiveBuffer, k);
      ComAbstract.setAscii(localDataUserInfo.getUserName(), mReceiveBuffer, k + 4, 20);
    }
    paramComGetUserAll.setRecvSize(32 + i * 20);
  }
  
  private void getWatchZone(ComGetWatchZone paramComGetWatchZone)
  {
    initResponse(paramComGetWatchZone, 60612);
    int i = paramComGetWatchZone.getSendId();
    paramComGetWatchZone.setRecvId(i);
    for (int j = 0; j < mUserList.size(); j++)
    {
      DataUserInfo localDataUserInfo = (DataUserInfo)mUserList.get(j);
      if (i == localDataUserInfo.getUserID())
      {
        ArrayList localArrayList = localDataUserInfo.getZoneIDArray();
        int k = localArrayList.size();
        paramComGetWatchZone.setRecvArg2(k);
        for (int m = 0; m < k; m++)
        {
          ID localID = (ID)localArrayList.get(m);
          ComAbstract.setInt(fID, mReceiveBuffer, 32 + m * 4);
        }
        paramComGetWatchZone.setRecvSize(32 + k * 4);
        return;
      }
    }
  }
  
  private void getZoneDetailInfo(ComGetZoneDetailInfo paramComGetZoneDetailInfo)
  {
    initResponse(paramComGetZoneDetailInfo, 60121);
    paramComGetZoneDetailInfo.setRecvSize(244);
    ID localID = new ID(1, paramComGetZoneDetailInfo.getSendId());
    VZone localVZone = mMgr.getZoneFromID(localID);
    PropZone localPropZone = localVZone.getProp();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 10; i++) {
      localArrayList.add(new TargetErr());
    }
    ZoneInfo localZoneInfo = new ZoneInfo(fShortName, fDetailName, localVZone.getPntNum(), 0, 0, localArrayList);
    paramComGetZoneDetailInfo.setZoneInfo(localZoneInfo);
  }
  
  private void getZonePnt(ComGetZonePnt paramComGetZonePnt)
  {
    initResponse(paramComGetZonePnt, 60109);
    ID localID = new ID(1, paramComGetZonePnt.getSendId());
    VZone localVZone = (VZone)mMgr.getPntAbst(localID);
    ArrayList localArrayList = localVZone.getPntList();
    paramComGetZonePnt.setSendId(1);
    paramComGetZonePnt.setPntList(localArrayList);
    paramComGetZonePnt.setRecvSize(localArrayList.size() * 4 + 32);
  }
  
  private void getZoneProp(ComGetZoneProp paramComGetZoneProp)
  {
    initResponse(paramComGetZoneProp, 60101);
    int i = mMgr.getZoneNum();
    paramComGetZoneProp.getClass();
    paramComGetZoneProp.setRecvSize(32 + 88 * i);
    paramComGetZoneProp.setRecvArg2(i);
    for (int j = 0; j < mMgr.getZoneNum(); j++) {
      paramComGetZoneProp.setZoneProp(j, mMgr.getZone(j).getProp());
    }
  }
  
  private void initResponse(ComAbstract paramComAbstract, int paramInt)
  {
    paramComAbstract.setRecvCom(paramInt);
    paramComAbstract.setRecvTime(Calendar.getInstance());
    paramComAbstract.setRecvArg1(1);
  }
  
  private void onOffPnt(ID paramID, boolean paramBoolean)
  {
    Object localObject;
    PntCurrent localPntCurrent;
    PntState localPntState1;
    PntState localPntState2;
    if (fType == 0)
    {
      localObject = (VD3Inner)mMgr.getPntAbst(paramID);
      localPntCurrent = ((VD3Inner)localObject).getPntCurrent();
      localPntState1 = ((VD3Inner)localObject).getState();
      if (paramBoolean) {
        localPntState2 = new PntState(fCommState, fTargetState, EnumPntStat.ON);
      } else {
        localPntState2 = new PntState(EnumComStat.NORMAL, new TargetErr(), EnumPntStat.OFF);
      }
      ((VD3Inner)localObject).setPntStateInfo(localPntState2, localPntCurrent);
    }
    else
    {
      localObject = (VZone)mMgr.getPntAbst(paramID);
      localPntCurrent = ((VZone)localObject).getPntCurrent();
      localPntState1 = ((VZone)localObject).getState();
      if (paramBoolean) {
        localPntState2 = new PntState(fCommState, fTargetState, EnumPntStat.ON);
      } else {
        localPntState2 = new PntState(EnumComStat.NORMAL, new TargetErr(), EnumPntStat.OFF);
      }
      ((VZone)localObject).setPntStateInfo(localPntState2, localPntCurrent);
    }
  }
  
  public synchronized void requestReply(ComAbstract paramComAbstract)
    throws Exception
  {
    int i = paramComAbstract.getSendCom();
    switch (i)
    {
    case 60700: 
      getStrLen((ComGetStrLen)paramComAbstract);
      break;
    case 60008: 
      getSysVersion((ComGetSysVersion)paramComAbstract);
      break;
    case 60002: 
      setLogin((ComSetLogin)paramComAbstract);
      break;
    case 60004: 
      setLogout((ComSetLogout)paramComAbstract);
      break;
    case 60006: 
      getSysInit((ComGetSysInit)paramComAbstract);
      break;
    case 60100: 
      getZoneProp((ComGetZoneProp)paramComAbstract);
      break;
    case 60102: 
      getPntProp((ComGetPntProp)paramComAbstract);
      break;
    case 60104: 
      getPntState((ComGetPntState)paramComAbstract);
      break;
    case 60106: 
      setPntOnoff((ComSetPntOnOff)paramComAbstract);
      break;
    case 60108: 
      getZonePnt((ComGetZonePnt)paramComAbstract);
      break;
    case 60110: 
      getPntTarget((ComGetPntTarget)paramComAbstract);
      break;
    case 60116: 
      getPntOrgTarget((ComGetPntOrgTarget)paramComAbstract);
      break;
    case 60112: 
      setPntState((ComSetPntState)paramComAbstract);
      break;
    case 60114: 
      getPntStateDetail((ComGetPntStateDetail)paramComAbstract);
      break;
    case 60118: 
      getPntDetailInfo((ComGetPntDetailInfo)paramComAbstract);
      break;
    case 60120: 
      getZoneDetailInfo((ComGetZoneDetailInfo)paramComAbstract);
      break;
    case 60200: 
      getGetScheduleAll((ComGetScheduleAll)paramComAbstract);
      break;
    case 60202: 
      getSchedule((ComGetSchProgram)paramComAbstract);
      break;
    case 60204: 
      setSchedule((ComSetSchProgram)paramComAbstract);
      break;
    case 60206: 
      setScheduleEnable((ComSetSchEnable)paramComAbstract);
      break;
    case 60208: 
      saveSchedule((ComSaveSchedule)paramComAbstract);
      break;
    case 60409: 
      getPpdSchedule((ComGetPpdSchedule)paramComAbstract);
      break;
    case 60411: 
      setPpdSchedule((ComSetPpdSchedule)paramComAbstract);
      break;
    case 60401: 
      getPpdTarget((ComGetPpdTarget)paramComAbstract);
      break;
    case 60403: 
      getPpdData((ComGetPpdData)paramComAbstract);
      break;
    case 60405: 
      getPpdCollectionType((ComGetPpdCollectionType)paramComAbstract);
      break;
    case 60407: 
      setPpdCollectionType((ComSetPpdCollectionType)paramComAbstract);
      break;
    case 60603: 
      getPassword((ComGetPswd)paramComAbstract);
      break;
    case 60601: 
      setPassword((ComSetPswd)paramComAbstract);
      break;
    case 60605: 
      getUserAll((ComGetUserAll)paramComAbstract);
      break;
    case 60607: 
      addUser((ComAddUser)paramComAbstract);
      break;
    case 60609: 
      delUser((ComDeleteUser)paramComAbstract);
      break;
    case 60611: 
      getWatchZone((ComGetWatchZone)paramComAbstract);
      break;
    case 60613: 
      setWatchZone((ComSetWatchZone)paramComAbstract);
      break;
    case 60615: 
      saveAuth((ComSaveAuth)paramComAbstract);
      break;
    default: 
      new FatalException("VirtualITC.requestReply no support command " + i);
    }
  }
  
  private void saveAuth(ComSaveAuth paramComSaveAuth)
  {
    initResponse(paramComSaveAuth, 60616);
    paramComSaveAuth.setRecvSize(32);
  }
  
  private void saveSchedule(ComSaveSchedule paramComSaveSchedule)
  {
    initResponse(paramComSaveSchedule, 60209);
    paramComSaveSchedule.setRecvSize(32);
    for (int i = 0; i < mMgr.getPntNum(); i++)
    {
      VAbst localVAbst1 = mMgr.getPnt(i);
      localVAbst1.setIconAppend(localVAbst1.getIconAppend() & 0xB);
    }
    for (int j = 0; j < mMgr.getZoneNum(); j++)
    {
      VZone localVZone = mMgr.getZone(j);
      localVZone.setIconAppend(localVZone.getIconAppend() & 0xB);
    }
    for (int k = 0; k < mProgramList.size(); k++)
    {
      Program localProgram = (Program)mProgramList.get(k);
      if (!localProgram.isActive()) {
        return;
      }
      AbstSchedule localAbstSchedule = localProgram.getWeekly();
      Object localObject2;
      Object localObject3;
      for (int m = 0; m < localAbstSchedule.getDailyNum(); m++)
      {
        localObject1 = localAbstSchedule.getDaily(m);
        for (n = 0; n < ((Daily)localObject1).getEventCount(); n++)
        {
          localObject2 = ((Daily)localObject1).getEvent(n);
          DefaultAction localDefaultAction1 = (DefaultAction)((SchEvent)localObject2).getAction();
          localObject3 = mMgr.getPntAbst(localDefaultAction1.getTargetID());
          ((VAbst)localObject3).setIconAppend(((VAbst)localObject3).getIconAppend() | 0x4);
        }
      }
      Object localObject1 = localProgram.getException();
      for (int n = 0; n < ((SchException)localObject1).getDailyNum(); n++)
      {
        localObject2 = ((SchException)localObject1).getDaily(n);
        for (int i1 = 0; i1 < ((Daily)localObject2).getEventCount(); i1++)
        {
          localObject3 = ((Daily)localObject2).getEvent(i1);
          DefaultAction localDefaultAction2 = (DefaultAction)((SchEvent)localObject3).getAction();
          VAbst localVAbst2 = mMgr.getPntAbst(localDefaultAction2.getTargetID());
          localVAbst2.setIconAppend(localVAbst2.getIconAppend() | 0x4);
        }
      }
    }
  }
  
  private void setLogin(ComSetLogin paramComSetLogin)
  {
    initResponse(paramComSetLogin, 60003);
    paramComSetLogin.setRecvSize(32);
    paramComSetLogin.setRecvArg1(1);
    paramComSetLogin.setRecvArg2(1);
    paramComSetLogin.setZoneID(mMgr.getZoneIDList());
  }
  
  private void setLogout(ComSetLogout paramComSetLogout)
  {
    initResponse(paramComSetLogout, 60005);
    paramComSetLogout.setRecvSize(32);
    paramComSetLogout.setRecvArg1(1);
  }
  
  private void setPassword(ComSetPswd paramComSetPswd)
  {
    initResponse(paramComSetPswd, 60602);
    paramComSetPswd.setRecvSize(32);
  }
  
  private void setPntOnoff(ComSetPntOnOff paramComSetPntOnOff)
  {
    initResponse(paramComSetPntOnOff, 60107);
    paramComSetPntOnOff.setRecvSize(32);
    for (int i = 0; i < paramComSetPntOnOff.getSendArg4(); i++)
    {
      ID localID = paramComSetPntOnOff.getID(i);
      boolean bool = paramComSetPntOnOff.getSendArg2() == 1;
      onOffPnt(localID, bool);
      if (fType == 1)
      {
        VZone localVZone = (VZone)mMgr.getPntAbst(localID);
        ArrayList localArrayList = localVZone.getPntList();
        for (int j = 0; j < localArrayList.size(); j++) {
          onOffPnt((ID)localArrayList.get(j), bool);
        }
      }
    }
  }
  
  private void setPntState(ID paramID, PntSet paramPntSet)
  {
    VAbst localVAbst = mMgr.getPntAbst(paramID);
    PntCurrent localPntCurrent1 = localVAbst.getPntCurrent();
    PntState localPntState = localVAbst.getState();
    boolean bool;
    if (mOnOffMode != EnumPntStat.ELSE)
    {
      if (mOnOffMode == EnumPntStat.ON) {
        bool = true;
      } else {
        bool = false;
      }
    }
    else if (fOnOffState == EnumPntStat.ON) {
      bool = true;
    } else {
      bool = false;
    }
    EnumDrvMode localEnumDrvMode;
    if (mRunMode != EnumDrvMode.ELSE) {
      localEnumDrvMode = mRunMode;
    } else {
      localEnumDrvMode = mDrvMode;
    }
    Temperature localTemperature1;
    if (mSetTemp.fEnable) {
      localTemperature1 = mSetTemp;
    } else {
      localTemperature1 = mSetTemp;
    }
    EnumRemcCode localEnumRemcCode;
    if (mRemoconOnOffMode != EnumRemcCode.ELSE) {
      localEnumRemcCode = mRemoconOnOffMode;
    } else {
      localEnumRemcCode = mRemoconOnOffMode;
    }
    EnumPntStat localEnumPntStat1;
    if (mRemoconRunMode != EnumPntStat.ELSE) {
      localEnumPntStat1 = mRemoconRunMode;
    } else {
      localEnumPntStat1 = mRemoconRunMode;
    }
    EnumPntStat localEnumPntStat2;
    if (mRemoconTempMode != EnumPntStat.ELSE) {
      localEnumPntStat2 = mRemoconTempMode;
    } else {
      localEnumPntStat2 = mRemoconTempMode;
    }
    EnumVentMode localEnumVentMode;
    if (mVentMode != EnumVentMode.ELSE) {
      localEnumVentMode = mVentMode;
    } else {
      localEnumVentMode = mVentMode;
    }
    EnumVentVol localEnumVentVol;
    if (mVentVol != EnumVentVol.ELSE) {
      localEnumVentVol = mVentVol;
    } else {
      localEnumVentVol = mVentVol;
    }
    int i;
    if (mChangeWindDirect == true) {
      i = mWindDirect;
    } else {
      i = mWindDirect;
    }
    int j;
    if (mChangeWindVolume == true) {
      j = mWindVolume;
    } else {
      j = mWindVolume;
    }
    int k;
    if (mFilterClear == true) {
      k = 0;
    } else {
      k = mFilterSign;
    }
    Temperature localTemperature2 = new Temperature(true, mWindDirect * 2.5F + 22.5F);
    PntCurrent localPntCurrent2;
    if ((mChangeWindDirect == true) && (mWindDirect == 7)) {
      localPntCurrent2 = new PntCurrent(localEnumDrvMode, localEnumVentMode, localEnumVentVol, mDefrostMode, localTemperature1, localTemperature2, localEnumRemcCode, localEnumPntStat1, localEnumPntStat2, 1, mThermoOff, mThermoMode, i, j);
    } else {
      localPntCurrent2 = new PntCurrent(localEnumDrvMode, localEnumVentMode, localEnumVentVol, mDefrostMode, localTemperature1, localTemperature2, localEnumRemcCode, localEnumPntStat1, localEnumPntStat2, k, mThermoOff, mThermoMode, i, j);
    }
    Object localObject;
    if (mSetTemp.fEnable == true)
    {
      if (mSetTemp.fTemp > 30.0F)
      {
        localObject = new TargetErr(true, 0, "UA");
        localPntState = new PntState(fCommState, (TargetErr)localObject, fOnOffState);
        bool = true;
      }
      if (mSetTemp.fTemp > 33.0F)
      {
        localObject = new TargetErr(true, 0, "UE");
        localPntState = new PntState(EnumComStat.ERROR, (TargetErr)localObject, fOnOffState);
        bool = true;
      }
    }
    if (localVAbst.getType() == EnumPntType.ZONE)
    {
      localObject = (VZone)localVAbst;
      ((VZone)localObject).setPntStateInfo(localPntState, localPntCurrent2);
    }
    else if (localVAbst.getType() == EnumPntType.D3_INNER)
    {
      localObject = (VD3Inner)localVAbst;
      ((VD3Inner)localObject).setPntStateInfo(localPntState, localPntCurrent2);
    }
    onOffPnt(paramID, bool);
  }
  
  private void setPntState(ComSetPntState paramComSetPntState)
  {
    initResponse(paramComSetPntState, 60113);
    paramComSetPntState.setRecvSize(32);
    ID localID = paramComSetPntState.getID();
    PntSet localPntSet = paramComSetPntState.getOperation();
    setPntState(localID, localPntSet);
    if (fType == 1)
    {
      VZone localVZone = (VZone)mMgr.getPntAbst(localID);
      ArrayList localArrayList = localVZone.getPntList();
      for (int i = 0; i < localArrayList.size(); i++) {
        setPntState((ID)localArrayList.get(i), localPntSet);
      }
    }
  }
  
  private void setPpdCollectionType(ComSetPpdCollectionType paramComSetPpdCollectionType)
  {
    initResponse(paramComSetPpdCollectionType, 60408);
    mPpdCollectionType = paramComSetPpdCollectionType.getSendArg1();
    mPpdCollectionDate = paramComSetPpdCollectionType.getSendArg2();
    int i = 32;
    for (int j = 0; j < mPpdContractPwr.length; j++)
    {
      mPpdContractPwr[j] = ComAbstract.getInt(mSendBuffer, i);
      i += 4;
    }
    paramComSetPpdCollectionType.setRecvSize(32);
    paramComSetPpdCollectionType.setRecvArg1(1);
  }
  
  private void setPpdSchedule(ComSetPpdSchedule paramComSetPpdSchedule)
  {
    initResponse(paramComSetPpdSchedule, 60412);
    mPpdExclusionDay = paramComSetPpdSchedule.getSendArg1();
    mPpdNightTime = paramComSetPpdSchedule.getSendArg2();
    int i = 32;
    for (int j = 0; j < 12; j++)
    {
      mPpdSpecialDay[j] = ComSetPpdSchedule.getInt(mSendBuffer, i);
      i += 4;
    }
    for (int k = 0; k < 7; k++)
    {
      mPpdExclusionTime[k] = ComSetPpdSchedule.getInt(mSendBuffer, i);
      i += 4;
    }
    paramComSetPpdSchedule.setRecvSize(32);
    paramComSetPpdSchedule.setRecvArg1(1);
  }
  
  private void setSchedule(ComSetSchProgram paramComSetSchProgram)
  {
    initResponse(paramComSetSchProgram, 60205);
    paramComSetSchProgram.setRecvSize(32);
    int i = paramComSetSchProgram.getSendId();
    Program localProgram = paramComSetSchProgram.getProgram();
    mProgramList.set(i, localProgram);
  }
  
  private void setScheduleEnable(ComSetSchEnable paramComSetSchEnable)
  {
    initResponse(paramComSetSchEnable, 60207);
    paramComSetSchEnable.setRecvSize(32);
    int i = paramComSetSchEnable.getSendId();
    Program localProgram = (Program)mProgramList.get(i);
    localProgram.setActive(paramComSetSchEnable.getSendArg1() == 1);
  }
  
  private void setWatchZone(ComSetWatchZone paramComSetWatchZone)
  {
    initResponse(paramComSetWatchZone, 60614);
    int i = paramComSetWatchZone.getSendId();
    paramComSetWatchZone.setRecvId(i);
    Object localObject = null;
    for (int j = 0; j < mUserList.size(); j++)
    {
      DataUserInfo localDataUserInfo = (DataUserInfo)mUserList.get(j);
      if (i == localDataUserInfo.getUserID())
      {
        localObject = localDataUserInfo;
        break;
      }
    }
    if (localObject == null)
    {
      paramComSetWatchZone.setRecvArg1(0);
      return;
    }
    localObject.getZoneArray().clear();
    for (int k = 0; k < paramComSetWatchZone.getSendArg1(); k++)
    {
      int m = ComAbstract.getInt(mSendBuffer, 32 + k * 4);
      localObject.addZoneID(m);
    }
    paramComSetWatchZone.setRecvArg1(1);
    paramComSetWatchZone.setRecvSize(32);
  }
  
  private PntCurrent updateInnerCurrent(PntCurrent paramPntCurrent1, PntCurrent paramPntCurrent2)
  {
    EnumDrvMode localEnumDrvMode;
    if (mDrvMode != EnumDrvMode.ELSE) {
      localEnumDrvMode = mDrvMode;
    } else {
      localEnumDrvMode = mDrvMode;
    }
    EnumVentMode localEnumVentMode;
    if (mVentMode != EnumVentMode.ELSE) {
      localEnumVentMode = mVentMode;
    } else {
      localEnumVentMode = mVentMode;
    }
    EnumVentVol localEnumVentVol;
    if (mVentVol != EnumVentVol.ELSE) {
      localEnumVentVol = mVentVol;
    } else {
      localEnumVentVol = mVentVol;
    }
    int i;
    if (mDefrostMode != 0) {
      i = mDefrostMode;
    } else {
      i = mDefrostMode;
    }
    Temperature localTemperature1;
    if (mSetTemp.fEnable == true) {
      localTemperature1 = mSetTemp;
    } else {
      localTemperature1 = mSetTemp;
    }
    Temperature localTemperature2;
    if (mRoomTemp.fEnable == true) {
      localTemperature2 = mRoomTemp;
    } else {
      localTemperature2 = mRoomTemp;
    }
    EnumRemcCode localEnumRemcCode;
    if (mRemoconOnOffMode != EnumRemcCode.ELSE) {
      localEnumRemcCode = mRemoconOnOffMode;
    } else {
      localEnumRemcCode = mRemoconOnOffMode;
    }
    EnumPntStat localEnumPntStat1;
    if (mRemoconRunMode != EnumPntStat.ELSE) {
      localEnumPntStat1 = mRemoconRunMode;
    } else {
      localEnumPntStat1 = mRemoconRunMode;
    }
    EnumPntStat localEnumPntStat2;
    if (mRemoconTempMode != EnumPntStat.ELSE) {
      localEnumPntStat2 = mRemoconTempMode;
    } else {
      localEnumPntStat2 = mRemoconTempMode;
    }
    int j;
    if (mFilterSign != 0) {
      j = mFilterSign;
    } else {
      j = mFilterSign;
    }
    int k;
    if (mThermoOff != 0) {
      k = mThermoOff;
    } else {
      k = mThermoOff;
    }
    int m;
    if (mThermoMode != 0) {
      m = mThermoMode;
    } else {
      m = mThermoMode;
    }
    int n;
    if (mWindDirect != -1) {
      n = mWindDirect;
    } else {
      n = mWindDirect;
    }
    int i1;
    if (mWindVolume != -1) {
      i1 = mWindVolume;
    } else {
      i1 = mWindVolume;
    }
    return new PntCurrent(localEnumDrvMode, localEnumVentMode, localEnumVentVol, i, localTemperature1, localTemperature2, localEnumRemcCode, localEnumPntStat1, localEnumPntStat2, j, k, m, n, i1);
  }
  
  private PntState updatePntState(PntState paramPntState1, PntState paramPntState2)
  {
    EnumComStat localEnumComStat;
    if ((fCommState == EnumComStat.ERROR) || (fCommState == EnumComStat.ERROR)) {
      localEnumComStat = EnumComStat.ERROR;
    } else {
      localEnumComStat = EnumComStat.NORMAL;
    }
    TargetErr localTargetErr;
    if (fTargetState.isNormal() == true) {
      localTargetErr = fTargetState;
    } else {
      localTargetErr = fTargetState;
    }
    EnumPntStat localEnumPntStat;
    if (fOnOffState == EnumPntStat.ON) {
      localEnumPntStat = EnumPntStat.ON;
    } else {
      localEnumPntStat = EnumPntStat.OFF;
    }
    return new PntState(localEnumComStat, localTargetErr, localEnumPntStat);
  }
  
  private void updateZones()
  {
    for (int i = 0; i < mMgr.getZoneNum(); i++)
    {
      VZone localVZone = mMgr.getZone(i);
      PntState localPntState = new PntState(EnumComStat.WAIT, new TargetErr(), EnumPntStat.ELSE);
      PntCurrent localPntCurrent = new PntCurrent();
      for (int j = 0; j < localVZone.getPntNum(); j++)
      {
        ID localID = localVZone.getPntID(j);
        VPoint localVPoint = mMgr.getPntFromID(localID);
        localPntState = updatePntState(localPntState, localVPoint.getState());
        localPntCurrent = updateInnerCurrent(localPntCurrent, localVPoint.getPntCurrent());
      }
      localVZone.setPntStateInfo(localPntState, localPntCurrent);
    }
  }
}
