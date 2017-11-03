package webitc.gui.common;

import java.io.PrintStream;
import webitc.AppletMain;
import webitc.common.AppletAbst;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.frame.Progress;
import webitc.frame.Result;

public final class CommonUse
{
  private static boolean mInWaitJob = false;
  
  public CommonUse() {}
  
  public static void AppErr(Exception paramException, String paramString)
  {
    String str = StrRes.getStr("IDS_SYSERR_TITLE");
    String[] arrayOfString = new String[4];
    arrayOfString[0] = StrRes.getStr("IDS_SYSERR_BODY");
    arrayOfString[1] = StrRes.getStr("IDS_SYSERR_CLOSE_BROWSER");
    arrayOfString[2] = " ";
    arrayOfString[3] = (StrRes.getStr("IDS_SYSERR_CONTENTS") + paramException.toString());
    System.out.println(paramException.toString() + " : " + paramString);
    paramException.printStackTrace();
    showErrorDlg(arrayOfString, str);
    AppletAbst localAppletAbst = AppletAbst.getInstance();
    localAppletAbst.forceLogout();
  }
  
  public static void CommErr(Exception paramException)
  {
    if (paramException.getClass().getName().compareTo("webitc.com.AlreadyErrorException") == 0) {
      return;
    }
    DlgAbstract.notifyError();
    AppletAbst localAppletAbst = AppletAbst.getInstance();
    localAppletAbst.forceLogout();
    String str = StrRes.getStr("IDS_SYSERR_BODY");
    String[] arrayOfString = new String[4];
    arrayOfString[0] = StrRes.getStr("IDS_SYSERR_CONNECT");
    arrayOfString[1] = StrRes.getStr("IDS_SYSERR_CLOSE_BROWSER2");
    arrayOfString[2] = " ";
    arrayOfString[3] = (StrRes.getStr("IDS_SYSERR_CONTENTS") + paramException.toString());
    System.out.println(paramException.toString() + " : ");
    paramException.printStackTrace();
    showErrorDlg(arrayOfString, str);
  }
  
  public static void PingErr(int paramInt)
  {
    DlgAbstract.notifyError();
    AppletAbst localAppletAbst = AppletAbst.getInstance();
    localAppletAbst.forceLogout();
    String[] arrayOfString;
    switch (paramInt)
    {
    case -10: 
      arrayOfString = new String[2];
      arrayOfString[0] = StrRes.getStr("IDS_SYSERR_CONNECT");
      arrayOfString[1] = StrRes.getStr("IDS_SYSERR_CLOSE_BROWSER2");
      showErrorDlg(arrayOfString, StrRes.getStr("IDS_SYSERR_BODY"));
      break;
    case -11: 
      arrayOfString = new String[2];
      arrayOfString[0] = StrRes.getStr("IDS_LOGIN_FAIL_DEL_USER");
      arrayOfString[1] = StrRes.getStr("IDS_SYSERR_CLOSE_BROWSER");
      showErrorDlg(arrayOfString, StrRes.getStr("IDS_UTILMESSAGE_CONFIRM"));
      break;
    case -20: 
      arrayOfString = new String[2];
      arrayOfString[0] = StrRes.getStr("IDS_LOGIN_FAIL_MIDDLE_CHANGE");
      arrayOfString[1] = StrRes.getStr("IDS_SYSERR_CLOSE_BROWSER");
      showErrorDlg(arrayOfString, StrRes.getStr("IDS_UTILMESSAGE_CONFIRM"));
      break;
    default: 
      throw new FatalException("CommonUse.PingErr illegal auth state");
    }
  }
  
  public static boolean isWaitJob()
  {
    return mInWaitJob;
  }
  
  public static boolean showConfirmDlg(Object paramObject, String paramString)
  {
    DlgQuestion localDlgQuestion = new DlgQuestion(paramString, paramObject);
    localDlgQuestion.doModal(1);
    return localDlgQuestion.isOK();
  }
  
  public static void showErrorDlg(Object paramObject, String paramString)
  {
    DlgMessage localDlgMessage = new DlgMessage(paramString, paramObject, 26);
    localDlgMessage.doModal(1);
  }
  
  public static void showInformationDlg(Object paramObject, String paramString)
  {
    DlgMessage localDlgMessage = new DlgMessage(paramString, paramObject, 28);
    localDlgMessage.doModal(1);
  }
  
  public static synchronized boolean waitJob(Result paramResult)
  {
    mInWaitJob = true;
    if (!paramResult.isFinished())
    {
      AppletAbst localAppletAbst = AppletAbst.getInstance();
      localAppletAbst.setEnabled(false);
      try
      {
        Thread.sleep(300L);
      }
      catch (Exception localException) {}
      if ((!paramResult.isFinished()) && (!AppletMain.isInStop())) {
        DlgWait.showWaitDlg(paramResult);
      }
      localAppletAbst.setEnabled(true);
    }
    for (;;)
    {
      if (paramResult.isFinished() == true) {
        break;
      }
    }
    mInWaitJob = false;
    if ((paramResult.getException() == null) && (paramResult.isCommErrorOccured() != true)) {
      return true;
    }
    if (paramResult.getException() != null)
    {
      CommErr(paramResult.getException());
      return false;
    }
    return false;
  }
  
  public static synchronized boolean waitJobNoDialog(Result paramResult)
  {
    mInWaitJob = true;
    for (;;)
    {
      try
      {
        Thread.sleep(100L);
      }
      catch (Exception localException) {}
      if (paramResult.isFinished() == true) {
        break;
      }
    }
    mInWaitJob = false;
    if ((paramResult.getException() == null) && (paramResult.isCommErrorOccured() != true)) {
      return true;
    }
    if (paramResult.getException() != null)
    {
      CommErr(paramResult.getException());
      return false;
    }
    return false;
  }
  
  public static synchronized boolean waitJobWithCancel(Result paramResult, Progress paramProgress, boolean paramBoolean)
  {
    mInWaitJob = true;
    if (!paramResult.isFinished())
    {
      AppletAbst localAppletAbst = AppletAbst.getInstance();
      localAppletAbst.setEnabled(false);
      try
      {
        Thread.sleep(300L);
      }
      catch (Exception localException) {}
      if ((!paramResult.isFinished()) && (!AppletMain.isInStop())) {
        DlgWaitWithCancel.showWaitDlg(paramResult, paramProgress, paramBoolean);
      }
      localAppletAbst.setEnabled(true);
    }
    for (;;)
    {
      if (paramResult.isFinished() == true) {
        break;
      }
    }
    mInWaitJob = false;
    if (paramResult.getException() == null) {
      return true;
    }
    CommErr(paramResult.getException());
    return false;
  }
}
