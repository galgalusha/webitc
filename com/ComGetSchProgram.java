package webitc.com;

import java.nio.ByteBuffer;
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
import webitc.data.schedule.SchEvent;
import webitc.data.schedule.SchException;

public class ComGetSchProgram
  extends ComAbstract
{
  private Program mProgram;
  
  public ComGetSchProgram() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60203) {
      return false;
    }
    if (getReturn() == 0) {
      return false;
    }
    mProgram = new Program();
    int i = getProgram(mProgram, mReceiveBuffer, 32);
    if (i == -1) {
      return false;
    }
    return getRecvSize() == i;
  }
  
  protected static int getProgram(Program paramProgram, ByteBuffer paramByteBuffer, int paramInt)
  {
    String str = ComAbstract.getUcs2(paramByteBuffer, paramInt, 32);
    paramInt += 32;
    boolean bool = ComAbstract.getBoolean(paramByteBuffer, paramInt);
    paramInt += 4;
    paramProgram.setName(str);
    paramProgram.setActive(bool);
    if ((paramInt = receiveCalendar(paramProgram, paramByteBuffer, paramInt)) == -1) {
      return -1;
    }
    if ((paramInt = receiveWeekly(paramProgram, paramByteBuffer, paramInt)) == -1) {
      return -1;
    }
    if ((paramInt = receiveException(paramProgram, paramByteBuffer, paramInt)) == -1) {
      return -1;
    }
    return paramInt;
  }
  
  public Program getProgram()
  {
    return mProgram;
  }
  
  private static int receiveAction(SchEvent paramSchEvent, ByteBuffer paramByteBuffer, int paramInt)
  {
    DefaultAction localDefaultAction = new DefaultAction();
    int i = ComAbstract.getInt(paramByteBuffer, paramInt);
    paramInt += 4;
    float f = ComAbstract.getFloat(paramByteBuffer, paramInt);
    paramInt += 4;
    boolean bool = ComAbstract.getBoolean(paramByteBuffer, paramInt);
    paramInt++;
    Temperature localTemperature = new Temperature(bool, f);
    localDefaultAction.setSetPoint(localTemperature);
    switch (paramByteBuffer.get(paramInt++))
    {
    case 1: 
      localDefaultAction.setOnOff(EnumPntStat.ON);
      break;
    case 2: 
      localDefaultAction.setOnOff(EnumPntStat.OFF);
      break;
    default: 
      localDefaultAction.setOnOff(EnumPntStat.ELSE);
    }
    switch (paramByteBuffer.get(paramInt++))
    {
    case 1: 
      localDefaultAction.setDrvMode(EnumDrvMode.FAN);
      break;
    case 2: 
      localDefaultAction.setDrvMode(EnumDrvMode.HEAT);
      break;
    case 3: 
      localDefaultAction.setDrvMode(EnumDrvMode.COOL);
      break;
    case 4: 
      localDefaultAction.setDrvMode(EnumDrvMode.SUBMIT);
      break;
    case 5: 
      localDefaultAction.setDrvMode(EnumDrvMode.AUTOCOOL);
      break;
    case 6: 
      localDefaultAction.setDrvMode(EnumDrvMode.AUTOHEAT);
      break;
    case 7: 
      localDefaultAction.setDrvMode(EnumDrvMode.DRY);
      break;
    default: 
      localDefaultAction.setDrvMode(EnumDrvMode.ELSE);
    }
    switch (paramByteBuffer.get(paramInt++))
    {
    case 1: 
      localDefaultAction.setVentMode(EnumVentMode.AUTO);
      break;
    case 2: 
      localDefaultAction.setVentMode(EnumVentMode.VENTILATION);
      break;
    case 3: 
      localDefaultAction.setVentMode(EnumVentMode.NORMAL);
      break;
    default: 
      localDefaultAction.setVentMode(EnumVentMode.ELSE);
    }
    switch (paramByteBuffer.get(paramInt++))
    {
    case 1: 
      localDefaultAction.setVentVol(EnumVentVol.NORMAL_AUTO);
      break;
    case 2: 
      localDefaultAction.setVentVol(EnumVentVol.NORMAL_LOW);
      break;
    case 3: 
      localDefaultAction.setVentVol(EnumVentVol.NORMAL_HIGH);
      break;
    case 4: 
      localDefaultAction.setVentVol(EnumVentVol.FRESH_AUTO);
      break;
    case 5: 
      localDefaultAction.setVentVol(EnumVentVol.FRESH_LOW);
      break;
    case 6: 
      localDefaultAction.setVentVol(EnumVentVol.FRESH_HIGH);
      break;
    default: 
      localDefaultAction.setVentVol(EnumVentVol.ELSE);
    }
    switch (paramByteBuffer.get(paramInt++))
    {
    case 1: 
      localDefaultAction.setRemcCode(EnumRemcCode.DISABLE);
      break;
    case 2: 
      localDefaultAction.setRemcCode(EnumRemcCode.STOP);
      break;
    case 3: 
      localDefaultAction.setRemcCode(EnumRemcCode.ENABLE);
      break;
    default: 
      localDefaultAction.setRemcCode(EnumRemcCode.ELSE);
    }
    switch (paramByteBuffer.get(paramInt++))
    {
    case 1: 
      localDefaultAction.setRemcDrvMode(EnumPntStat.ON);
      break;
    case 2: 
      localDefaultAction.setRemcDrvMode(EnumPntStat.OFF);
      break;
    default: 
      localDefaultAction.setRemcDrvMode(EnumPntStat.ELSE);
    }
    switch (paramByteBuffer.get(paramInt++))
    {
    case 1: 
      localDefaultAction.setRemcSetPoint(EnumPntStat.ON);
      break;
    case 2: 
      localDefaultAction.setRemcSetPoint(EnumPntStat.OFF);
      break;
    default: 
      localDefaultAction.setRemcSetPoint(EnumPntStat.ELSE);
    }
    switch (paramByteBuffer.get(paramInt++))
    {
    case 0: 
      localDefaultAction.setTargetID(new ID(0, i));
      break;
    case 1: 
      localDefaultAction.setTargetID(new ID(1, i));
      break;
    case 2: 
    default: 
      localDefaultAction.setTargetID(new ID(2, 0));
    }
    paramSchEvent.setAction(localDefaultAction);
    paramInt += 3;
    return paramInt;
  }
  
  private static int receiveCalendar(Program paramProgram, ByteBuffer paramByteBuffer, int paramInt)
  {
    CommonCalendar localCommonCalendar = new CommonCalendar();
    int i = ComAbstract.getShort(paramByteBuffer, paramInt);
    paramInt += 2;
    localCommonCalendar.setStartMonth(i, paramByteBuffer.get(paramInt++));
    paramByteBuffer.get(paramInt++);
    localCommonCalendar.setNowDay(paramByteBuffer.array(), paramInt);
    paramInt += 32;
    localCommonCalendar.setRotDay(paramByteBuffer.array(), paramInt);
    paramInt += 384;
    localCommonCalendar.setRotation(ComAbstract.getBoolean(paramByteBuffer, paramInt));
    paramInt += 4;
    paramProgram.setCalendar(localCommonCalendar);
    return paramInt;
  }
  
  private static int receiveDaily(Daily paramDaily, ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = ComAbstract.getInt(paramByteBuffer, paramInt);
    if (i > 16) {
      return -1;
    }
    paramInt += 4;
    for (int j = 0; j < i; j++)
    {
      SchEvent localSchEvent = new SchEvent();
      if ((paramInt = receiveEvent(localSchEvent, paramByteBuffer, paramInt)) == -1) {
        return -1;
      }
      paramDaily.addEvent(localSchEvent);
    }
    return paramInt;
  }
  
  private static int receiveEvent(SchEvent paramSchEvent, ByteBuffer paramByteBuffer, int paramInt)
  {
    paramSchEvent.setHour(paramByteBuffer.get(paramInt++));
    paramSchEvent.setMin(paramByteBuffer.get(paramInt++));
    paramInt += 2;
    int i = ComAbstract.getShort(paramByteBuffer, paramInt);
    paramInt += 2;
    int j = ComAbstract.getShort(paramByteBuffer, paramInt);
    paramInt += 2;
    if (i == 1)
    {
      if ((paramInt = receiveAction(paramSchEvent, paramByteBuffer, paramInt)) == -1) {
        return -1;
      }
    }
    else {
      paramInt += j;
    }
    return paramInt;
  }
  
  private static int receiveException(Program paramProgram, ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = ComAbstract.getInt(paramByteBuffer, paramInt);
    if (i != 10) {
      return -1;
    }
    paramInt += 4;
    SchException localSchException = new SchException(i);
    for (int j = 0; j < i; j++)
    {
      Daily localDaily = new Daily();
      if ((paramInt = receiveDaily(localDaily, paramByteBuffer, paramInt)) == -1) {
        return -1;
      }
      localSchException.setDaily(j, localDaily);
    }
    for (int k = 0; k < 10; k++)
    {
      String str = ComAbstract.getUcs2(paramByteBuffer, paramInt, 16);
      paramInt += 16;
      localSchException.setExceptionName(k, str);
    }
    paramProgram.setException(localSchException);
    return paramInt;
  }
  
  private static int receiveWeekly(Program paramProgram, ByteBuffer paramByteBuffer, int paramInt)
  {
    int i = ComAbstract.getInt(paramByteBuffer, paramInt);
    if (i != 7) {
      return -1;
    }
    paramInt += 4;
    AbstSchedule localAbstSchedule = new AbstSchedule(i);
    for (int j = 0; j < i; j++)
    {
      Daily localDaily = new Daily();
      if ((paramInt = receiveDaily(localDaily, paramByteBuffer, paramInt)) == -1) {
        return -1;
      }
      localAbstSchedule.setDaily(j, localDaily);
    }
    paramProgram.setWeekly(localAbstSchedule);
    return paramInt;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60202);
    setSendSize(32);
  }
  
  protected void setProgram(Program paramProgram)
  {
    int i = ComSetSchProgram.setProgram(paramProgram, mReceiveBuffer, 32);
    setRecvSize(i);
  }
  
  public void setProgramIndex(int paramInt)
  {
    setSendId(paramInt);
  }
}
