package webitc.frame;

public class ThreadAppCom
  extends ThreadAbst
{
  private static ThreadAppCom mObj;
  
  private ThreadAppCom()
  {
    super("iTC Application");
  }
  
  public static ThreadAppCom createInstance()
  {
    mObj = new ThreadAppCom();
    return mObj;
  }
  
  public static ThreadAppCom getInstance()
  {
    return mObj;
  }
}
