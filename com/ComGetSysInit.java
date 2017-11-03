package webitc.com;

import java.nio.ByteBuffer;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import webitc.common.enum2.EnumLang;

public class ComGetSysInit
  extends ComAbstract
{
  public static final int CHANGE_OVER = 8;
  public static final int DEMAND = 1;
  public static final int ECO_CTRL = 2;
  public static final int HMO = 16;
  public static final int SLIDING_TEMP = 64;
  public static final int TEMP_LIMIT = 32;
  public static final int TS_CTRL = 4;
  
  public ComGetSysInit() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60007) {
      return false;
    }
    return getRecvSize() == 64;
  }
  
  public int getD3PortNum()
  {
    return ComAbstract.getInt(mReceiveBuffer, 60);
  }
  
  public EnumLang getLanguage()
  {
    return EnumLang.getEnum(getRecvArg2());
  }
  
  public int getOnOffColor()
  {
    return getRecvArg3();
  }
  
  public int getOption()
  {
    return ComAbstract.getInt(mReceiveBuffer, 52);
  }
  
  public int getPPDOption()
  {
    return ComAbstract.getInt(mReceiveBuffer, 48);
  }
  
  public int getScheduleLowerTemp()
  {
    return ComAbstract.getShort(mReceiveBuffer, 58);
  }
  
  public int getScheduleUpperTemp()
  {
    return ComAbstract.getShort(mReceiveBuffer, 56);
  }
  
  public TimeZone getTimeZone()
  {
    int i = -getRecvArg4();
    String str = "GMT";
    if (i > 0) {
      str = str + "+" + i / 60 + ":";
    } else if (i < 0) {
      str = str + i / 60 + ":";
    }
    if (i % 60 < 10) {
      str = str + "0" + i % 60;
    } else {
      str = str + i % 60;
    }
    if (ComAbstract.getInt(mReceiveBuffer, 32) == 1)
    {
      int j = mReceiveBuffer.get(36) - 1;
      int k = mReceiveBuffer.get(37);
      if (k == 5) {
        k = -1;
      }
      int m = mReceiveBuffer.get(38);
      switch (m)
      {
      case 0: 
      default: 
        m = 1;
        break;
      case 1: 
        m = 2;
        break;
      case 2: 
        m = 3;
        break;
      case 3: 
        m = 4;
        break;
      case 4: 
        m = 5;
        break;
      case 5: 
        m = 6;
        break;
      case 6: 
        m = 7;
      }
      int n = mReceiveBuffer.get(39);
      int i1 = mReceiveBuffer.get(40) - 1;
      int i2 = mReceiveBuffer.get(41);
      if (i2 == 5) {
        i2 = -1;
      }
      int i3 = mReceiveBuffer.get(42);
      switch (i3)
      {
      case 0: 
      default: 
        i3 = 1;
        break;
      case 1: 
        i3 = 2;
        break;
      case 2: 
        i3 = 3;
        break;
      case 3: 
        i3 = 4;
        break;
      case 4: 
        i3 = 5;
        break;
      case 5: 
        i3 = 6;
        break;
      case 6: 
        i3 = 7;
      }
      int i4 = mReceiveBuffer.get(43);
      return new SimpleTimeZone(i * 60 * 1000, str, j, k, m, n * 60 * 60 * 1000, i1, i2, i3, i4 * 60 * 60 * 1000);
    }
    return new SimpleTimeZone(i * 60 * 1000, str);
  }
  
  public boolean isCentigrade()
  {
    return ComAbstract.getInt(mReceiveBuffer, 44) != 0;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60006);
    setSendSize(32);
  }
}
