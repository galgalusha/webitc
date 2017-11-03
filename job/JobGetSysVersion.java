package webitc.job;

import webitc.com.ComGetSysVersion;
import webitc.com.SockHttp;
import webitc.common.enum2.EnumModelType;
import webitc.common.enum2.EnumSystemType;
import webitc.frame.Job;

public class JobGetSysVersion
  extends Job
{
  private int mMajorVersion = -1;
  private int mMinorVersion = -1;
  private EnumModelType mModelType = EnumModelType.ELSE;
  private int mResult = 1;
  private EnumSystemType mSysType = EnumSystemType.ELSE;
  private String mVersionStr = "";
  
  public JobGetSysVersion() {}
  
  public int getMajorVersion()
  {
    return mMajorVersion;
  }
  
  public int getMinorVersion()
  {
    return mMinorVersion;
  }
  
  public EnumModelType getModelType()
  {
    return mModelType;
  }
  
  public int getResult()
  {
    return mResult;
  }
  
  public EnumSystemType getSystemType()
  {
    return mSysType;
  }
  
  public String getVersionStr()
  {
    return mVersionStr;
  }
  
  protected void runPrivate()
    throws Exception
  {
    ComGetSysVersion localComGetSysVersion = new ComGetSysVersion();
    SockHttp localSockHttp = SockHttp.getInstance();
    localSockHttp.requestReply(localComGetSysVersion);
    mResult = localComGetSysVersion.getReturn();
    if (mResult == 1)
    {
      mSysType = localComGetSysVersion.getSystemType();
      mModelType = localComGetSysVersion.getModelType();
      mMajorVersion = localComGetSysVersion.getMajorVersion();
      mMinorVersion = localComGetSysVersion.getMinorVersion();
      mVersionStr = localComGetSysVersion.getVersionStr();
    }
  }
}
