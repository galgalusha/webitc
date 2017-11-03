package webitc.com;

import java.io.PrintStream;
import webitc.common.enum2.EnumModelType;
import webitc.common.enum2.EnumSystemType;

public class ComGetSysVersion
  extends ComAbstract
{
  public ComGetSysVersion() {}
  
  public boolean afterReceive()
  {
    if (getRecvCom() != 60009) {
      return false;
    }
    if (checkAfter4_1()) {
      return getRecvSize() == 64;
    }
    return getRecvSize() == 32;
  }
  
  private boolean checkAfter4_1()
  {
    return (getMajorVersion() >= 4) && (getMinorVersion() >= 1);
  }
  
  public int getMajorVersion()
  {
    return getRecvArg3();
  }
  
  public int getMinorVersion()
  {
    return getRecvArg4();
  }
  
  public EnumModelType getModelType()
  {
    return EnumModelType.getEnum(getRecvArg2());
  }
  
  public EnumSystemType getSystemType()
  {
    return EnumSystemType.getEnum(getRecvId());
  }
  
  public String getVersionStr()
  {
    if (checkAfter4_1()) {
      try
      {
        return ComAbstract.getAscii(mReceiveBuffer, 32, 32);
      }
      catch (Exception localException)
      {
        System.out.println(localException.toString());
        return null;
      }
    }
    String str = "Ver";
    str = str + getMajorVersion();
    str = str + ".";
    str = str + getMinorVersion();
    str = str + ".00";
    return str;
  }
  
  public void resetBuffer()
  {
    super.resetBuffer();
    setSendCom(60008);
    setSendSize(32);
  }
}
