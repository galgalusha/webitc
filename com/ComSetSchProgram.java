package webitc.com;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.CommonCalendar;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.schedule.AbstSchedule;
import webitc.data.schedule.Daily;
import webitc.data.schedule.DefaultAction;
import webitc.data.schedule.Program;
import webitc.data.schedule.SchAbstAction;
import webitc.data.schedule.SchEvent;
import webitc.data.schedule.SchException;

public class ComSetSchProgram
  extends ComAbstract
{
  public ComSetSchProgram() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60205) {
      return false;
    }
    if (getRecvSize() != 32) {
      return false;
    }
    return getReturn() == 1;
  }
  
  protected Program getProgram()
  {
    Program localProgram = new Program();
    ComGetSchProgram.getProgram(localProgram, mSendBuffer, 32);
    return localProgram;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60204);
  }
  
  private static int sendAction(DefaultAction paramDefaultAction, ByteBuffer paramByteBuffer, int paramInt)
  {
    ComAbstract.setInt(getTargetIDfID, paramByteBuffer, paramInt);
    paramInt += 4;
    ComAbstract.setFloat(getSetPointfTemp, paramByteBuffer, paramInt);
    paramInt += 4;
    ComAbstract.setBoolean(getSetPointfEnable, paramByteBuffer, paramInt++);
    if (paramDefaultAction.getOnOff() == EnumPntStat.ON) {
      paramByteBuffer.put(paramInt++, (byte)1);
    } else if (paramDefaultAction.getOnOff() == EnumPntStat.OFF) {
      paramByteBuffer.put(paramInt++, (byte)2);
    } else {
      paramByteBuffer.put(paramInt++, (byte)0);
    }
    if (paramDefaultAction.getDrvMode() == EnumDrvMode.FAN) {
      paramByteBuffer.put(paramInt++, (byte)1);
    } else if (paramDefaultAction.getDrvMode() == EnumDrvMode.HEAT) {
      paramByteBuffer.put(paramInt++, (byte)2);
    } else if (paramDefaultAction.getDrvMode() == EnumDrvMode.COOL) {
      paramByteBuffer.put(paramInt++, (byte)3);
    } else if (paramDefaultAction.getDrvMode() == EnumDrvMode.SUBMIT) {
      paramByteBuffer.put(paramInt++, (byte)4);
    } else if (paramDefaultAction.getDrvMode() == EnumDrvMode.AUTOCOOL) {
      paramByteBuffer.put(paramInt++, (byte)5);
    } else if (paramDefaultAction.getDrvMode() == EnumDrvMode.AUTOHEAT) {
      paramByteBuffer.put(paramInt++, (byte)6);
    } else if (paramDefaultAction.getDrvMode() == EnumDrvMode.DRY) {
      paramByteBuffer.put(paramInt++, (byte)7);
    } else {
      paramByteBuffer.put(paramInt++, (byte)0);
    }
    if (paramDefaultAction.getVentMode() == EnumVentMode.AUTO) {
      paramByteBuffer.put(paramInt++, (byte)1);
    } else if (paramDefaultAction.getVentMode() == EnumVentMode.VENTILATION) {
      paramByteBuffer.put(paramInt++, (byte)2);
    } else if (paramDefaultAction.getVentMode() == EnumVentMode.NORMAL) {
      paramByteBuffer.put(paramInt++, (byte)3);
    } else {
      paramByteBuffer.put(paramInt++, (byte)0);
    }
    if (paramDefaultAction.getVentVol() == EnumVentVol.NORMAL_AUTO) {
      paramByteBuffer.put(paramInt++, (byte)1);
    } else if (paramDefaultAction.getVentVol() == EnumVentVol.NORMAL_LOW) {
      paramByteBuffer.put(paramInt++, (byte)2);
    } else if (paramDefaultAction.getVentVol() == EnumVentVol.NORMAL_HIGH) {
      paramByteBuffer.put(paramInt++, (byte)3);
    } else if (paramDefaultAction.getVentVol() == EnumVentVol.FRESH_AUTO) {
      paramByteBuffer.put(paramInt++, (byte)4);
    } else if (paramDefaultAction.getVentVol() == EnumVentVol.FRESH_LOW) {
      paramByteBuffer.put(paramInt++, (byte)5);
    } else if (paramDefaultAction.getVentVol() == EnumVentVol.FRESH_HIGH) {
      paramByteBuffer.put(paramInt++, (byte)6);
    } else {
      paramByteBuffer.put(paramInt++, (byte)0);
    }
    if (paramDefaultAction.getRemcCode() == EnumRemcCode.DISABLE) {
      paramByteBuffer.put(paramInt++, (byte)1);
    } else if (paramDefaultAction.getRemcCode() == EnumRemcCode.STOP) {
      paramByteBuffer.put(paramInt++, (byte)2);
    } else if (paramDefaultAction.getRemcCode() == EnumRemcCode.ENABLE) {
      paramByteBuffer.put(paramInt++, (byte)3);
    } else {
      paramByteBuffer.put(paramInt++, (byte)0);
    }
    if (paramDefaultAction.getRemcDrvMode() == EnumPntStat.ON) {
      paramByteBuffer.put(paramInt++, (byte)1);
    } else if (paramDefaultAction.getRemcDrvMode() == EnumPntStat.OFF) {
      paramByteBuffer.put(paramInt++, (byte)2);
    } else {
      paramByteBuffer.put(paramInt++, (byte)0);
    }
    if (paramDefaultAction.getRemcSetPoint() == EnumPntStat.ON) {
      paramByteBuffer.put(paramInt++, (byte)1);
    } else if (paramDefaultAction.getRemcSetPoint() == EnumPntStat.OFF) {
      paramByteBuffer.put(paramInt++, (byte)2);
    } else {
      paramByteBuffer.put(paramInt++, (byte)0);
    }
    paramByteBuffer.put(paramInt++, (byte)getTargetIDfType);
    paramInt += 3;
    return paramInt;
  }
  
  private static int sendCalendar(CommonCalendar paramCommonCalendar, ByteBuffer paramByteBuffer, int paramInt)
  {
    Calendar localCalendar = paramCommonCalendar.getStartMonth();
    ComAbstract.setShort((short)localCalendar.get(1), paramByteBuffer, paramInt);
    paramInt += 2;
    paramByteBuffer.put(paramInt++, (byte)(localCalendar.get(2) + 1));
    paramByteBuffer.put(paramInt++, (byte)localCalendar.get(5));
    paramByteBuffer.position(paramInt);
    paramByteBuffer.put(paramCommonCalendar.getNowDayType());
    paramInt += 32;
    paramByteBuffer.position(paramInt);
    paramByteBuffer.put(paramCommonCalendar.getRotDayType());
    paramInt += 384;
    ComAbstract.setBoolean(paramCommonCalendar.getRotation(), paramByteBuffer, paramInt);
    paramInt += 4;
    return paramInt;
  }
  
  private static int sendDaily(Daily paramDaily, ByteBuffer paramByteBuffer, int paramInt)
  {
    ComAbstract.setInt(paramDaily.getEventCount(), paramByteBuffer, paramInt);
    paramInt += 4;
    for (int i = 0; i < paramDaily.getEventCount(); i++)
    {
      SchEvent localSchEvent = paramDaily.getEvent(i);
      paramInt = sendEvent(localSchEvent, paramByteBuffer, paramInt);
    }
    return paramInt;
  }
  
  private static int sendEvent(SchEvent paramSchEvent, ByteBuffer paramByteBuffer, int paramInt)
  {
    paramByteBuffer.put(paramInt++, (byte)paramSchEvent.getHour());
    paramByteBuffer.put(paramInt++, (byte)paramSchEvent.getMin());
    paramInt += 2;
    ComAbstract.setShort((short)paramSchEvent.getAction().getType(), paramByteBuffer, paramInt);
    paramInt += 2;
    ComAbstract.setShort((short)20, paramByteBuffer, paramInt);
    paramInt += 2;
    paramInt = sendAction((DefaultAction)paramSchEvent.getAction(), paramByteBuffer, paramInt);
    return paramInt;
  }
  
  private static int sendException(SchException paramSchException, ByteBuffer paramByteBuffer, int paramInt)
  {
    ComAbstract.setInt(paramSchException.getDailyArray().size(), paramByteBuffer, paramInt);
    paramInt += 4;
    for (int i = 0; i < paramSchException.getDailyArray().size(); i++)
    {
      Daily localDaily = paramSchException.getDaily(i);
      paramInt = sendDaily(localDaily, paramByteBuffer, paramInt);
    }
    for (int j = 0; j < 10; j++)
    {
      String str = paramSchException.getExceptionName(j);
      ComAbstract.setUcs2(str, paramByteBuffer, paramInt, 16);
      paramInt += 16;
    }
    return paramInt;
  }
  
  private static int sendWeekly(AbstSchedule paramAbstSchedule, ByteBuffer paramByteBuffer, int paramInt)
  {
    ComAbstract.setInt(paramAbstSchedule.getDailyArray().size(), paramByteBuffer, paramInt);
    paramInt += 4;
    for (int i = 0; i < paramAbstSchedule.getDailyArray().size(); i++)
    {
      Daily localDaily = paramAbstSchedule.getDaily(i);
      paramInt = sendDaily(localDaily, paramByteBuffer, paramInt);
    }
    return paramInt;
  }
  
  public void setPartOfAllSet(boolean paramBoolean)
  {
    if (paramBoolean == true) {
      setSendArg1(1);
    } else {
      setSendArg1(0);
    }
  }
  
  protected static int setProgram(Program paramProgram, ByteBuffer paramByteBuffer, int paramInt)
  {
    ComAbstract.setUcs2(paramProgram.getName(), paramByteBuffer, paramInt, 32);
    paramInt += 32;
    ComAbstract.setBoolean(paramProgram.isActive(), paramByteBuffer, paramInt);
    paramInt += 4;
    paramInt = sendCalendar(paramProgram.getCalendar(), paramByteBuffer, paramInt);
    paramInt = sendWeekly(paramProgram.getWeekly(), paramByteBuffer, paramInt);
    paramInt = sendException(paramProgram.getException(), paramByteBuffer, paramInt);
    return paramInt;
  }
  
  public void setProgram(int paramInt, Program paramProgram)
  {
    setSendId(paramInt);
    int i = setProgram(paramProgram, mSendBuffer, 32);
    setSendSize(i);
  }
}
