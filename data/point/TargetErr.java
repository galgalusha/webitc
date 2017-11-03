package webitc.data.point;

import java.nio.ByteBuffer;
import java.util.Calendar;
import webitc.com.ComAbstract;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.data.ID;

public class TargetErr
{
  public static final int MAX_ERROR_HISTORY_NUM = 10;
  public final String fErrStr;
  public final boolean fError;
  public final ID fID;
  public final Calendar fTime;
  public final int fUnitNo;
  
  public TargetErr(ByteBuffer paramByteBuffer, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = paramInt;
    if (paramBoolean2)
    {
      fID = new ID(0, ComAbstract.getInt(paramByteBuffer, i));
      i += 4;
    }
    else
    {
      fID = ID.ID_UNREG;
    }
    if (paramBoolean1)
    {
      fTime = SystemInfo.getLocalTime(ComAbstract.getInt(paramByteBuffer, i));
      i += 4;
    }
    else
    {
      fTime = Calendar.getInstance(SystemInfo.getTimeZone());
    }
    if (paramByteBuffer.get(i) == 1) {
      fError = true;
    } else {
      fError = false;
    }
    fUnitNo = paramByteBuffer.get(i + 1);
    fErrStr = ComAbstract.getAscii(paramByteBuffer, i + 2, 2);
  }
  
  public TargetErr(boolean paramBoolean, int paramInt, String paramString)
  {
    fID = ID.ID_UNREG;
    fTime = Calendar.getInstance(SystemInfo.getTimeZone());
    fError = paramBoolean;
    fUnitNo = paramInt;
    fErrStr = paramString;
  }
  
  public TargetErr(boolean paramBoolean, int paramInt, String paramString, Calendar paramCalendar)
  {
    fID = ID.ID_UNREG;
    fTime = paramCalendar;
    fError = paramBoolean;
    fUnitNo = paramInt;
    fErrStr = paramString;
  }
  
  public TargetErr()
  {
    fID = ID.ID_UNREG;
    fTime = Calendar.getInstance(SystemInfo.getTimeZone());
    fError = false;
    fUnitNo = 0;
    fErrStr = "--";
  }
  
  public String getErrCodeStr()
  {
    return fErrStr;
  }
  
  public Calendar getErrTime()
  {
    return fTime;
  }
  
  public String getErrTypeStr()
  {
    if (isNormal()) {
      return StrRes.getStr("IDS_COMMON_ERR_LEVEL_0");
    }
    return StrRes.getStr("IDS_COMMON_ERR_LEVEL_5");
  }
  
  public String getUnitNoStr()
  {
    if (isNormal()) {
      return StrRes.getStr("IDS_COMMON_UNKNOWN");
    }
    return Integer.toString(fUnitNo);
  }
  
  public boolean isNormal()
  {
    return !fError;
  }
  
  public void setBuffer(ByteBuffer paramByteBuffer, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = paramInt;
    if (paramBoolean2)
    {
      ComAbstract.setInt(fID.fID, paramByteBuffer, i);
      i += 4;
    }
    if (paramBoolean1)
    {
      ComAbstract.setInt((int)(fTime.getTimeInMillis() / 1000L), paramByteBuffer, i);
      i += 4;
    }
    if (fError == true) {
      paramByteBuffer.put(i, (byte)1);
    } else {
      paramByteBuffer.put(i, (byte)0);
    }
    paramByteBuffer.put(i + 1, (byte)fUnitNo);
    ComAbstract.setAscii(fErrStr, paramByteBuffer, i + 2, 2);
  }
}
