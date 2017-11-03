package webitc;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.LayoutManager;
import java.awt.Window;
import javax.swing.FocusManager;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import webitc.common.AppletAbst;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.frame.Job;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.frame.ThreadPntRequest;
import webitc.frame.ThreadUpdate;
import webitc.gui.AuthListener;
import webitc.gui.PanelMain;
import webitc.gui.PanelMainAbstract;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.DlgWait;
import webitc.gui.common.DlgWaitWithCancel;
import webitc.gui.login.PanelLogin;
import webitc.gui.simple.PanelMainSimple;
import webitc.job.JobForceLogout;
import webitc.job.JobGetSysInit;
import webitc.job.JobGetSysVersion;
import webitc.job.JobLogout;

public class AppletMain
  extends AppletAbst
  implements AuthListener
{
  private static boolean mInStop = false;
  private static int mInstanceCount = 0;
  private PanelLogin mPanelLogin = null;
  private PanelMainAbstract mPanelMain = null;
  private int mPanelMode = 0;
  
  public AppletMain()
  {
    mInstanceCount += 1;
  }
  
  public void authSucceeded(int paramInt)
  {
    mPanelMode = paramInt;
    DataMgr localDataMgr;
    DataUserInfo localDataUserInfo;
    ThreadUpdate localThreadUpdate;
    Object localObject;
    ThreadPntRequest localThreadPntRequest;
    Result localResult;
    if (paramInt == 0)
    {
      localDataMgr = DataMgr.getInstance();
      localDataUserInfo = localDataMgr.getLoginUser();
      localThreadUpdate = ThreadUpdate.createInstance(localDataUserInfo.getUserID(), AppletAbst.sUUID);
      setSimpleMode(false);
      localObject = new PanelMain();
      mPanelMain = ((PanelMainAbstract)localObject);
      ((PanelMain)localObject).addAuthListener(this);
      localThreadUpdate.addUpdateListener(((PanelMain)localObject).getPanelMonitor());
      localThreadUpdate.start();
      localThreadPntRequest = ThreadPntRequest.createInstance();
      localThreadPntRequest.start();
      getContentPane().remove(mPanelLogin);
      getContentPane().setLayout(new GridLayout(1, 1));
      getContentPane().add(mPanelMain);
      getContentPane().paintAll(getGraphics());
      localResult = ThreadUpdate.interruptedUpdate(localDataMgr.getZoneIDList());
      if (!CommonUse.waitJob(localResult)) {
        return;
      }
      ((PanelMain)localObject).resetTreeSelection();
    }
    else
    {
      localDataMgr = DataMgr.getInstance();
      localDataUserInfo = localDataMgr.getLoginUser();
      localThreadUpdate = ThreadUpdate.createInstance(localDataUserInfo.getUserID(), AppletAbst.sUUID);
      setSimpleMode(true);
      localObject = new PanelMainSimple();
      mPanelMain = ((PanelMainAbstract)localObject);
      ((PanelMainSimple)localObject).addAuthListener(this);
      localThreadUpdate.addUpdateListener(((PanelMainSimple)localObject).getPanelMonitor());
      localThreadUpdate.start();
      localThreadPntRequest = ThreadPntRequest.createInstance();
      localThreadPntRequest.start();
      getContentPane().remove(mPanelLogin);
      getContentPane().setLayout(new GridLayout(1, 1));
      getContentPane().add(mPanelMain);
      getContentPane().paintAll(getGraphics());
      localResult = ThreadUpdate.interruptedUpdate(localDataMgr.getZoneIDList());
      if (!CommonUse.waitJob(localResult)) {
        return;
      }
      ((PanelMainSimple)localObject).resetTreeSelection();
    }
  }
  
  private void beforeLogin()
  {
    DataMgr.createInstance();
    getContentPane().setBackground(new Color(6724095));
    mPanelLogin = new PanelLogin();
    mPanelLogin.addAuthListener(this);
    if (getContentPane().getComponentCount() == 1) {
      getContentPane().remove(mPanelMain);
    }
    DlgAbstract.setEnable(true);
    GridBagLayout localGridBagLayout = new GridBagLayout();
    GridBagConstraints localGridBagConstraints = new GridBagConstraints();
    getContentPane().setLayout(localGridBagLayout);
    gridx = 0;
    gridy = 0;
    gridheight = 1;
    gridwidth = 1;
    weightx = 0.0D;
    weighty = 0.0D;
    anchor = 10;
    insets = new Insets(2, 2, 2, 2);
    getContentPane().add(mPanelLogin, localGridBagConstraints);
    getRootPane().setFocusTraversalPolicy(new ContainerOrderFocusTraversalPolicy()
    {
      public Component getDefaultComponent(Container paramAnonymousContainer)
      {
        return mPanelLogin;
      }
      
      public Component getInitialComponent(Window paramAnonymousWindow)
      {
        return mPanelLogin;
      }
    });
    mPanelLogin.setInitialFocus();
    setSize(850, 450);
  }
  
  public void destroy()
  {
    mInstanceCount -= 1;
    mInStop = false;
  }
  
  public void forceLogout()
  {
    JobForceLogout localJobForceLogout = new JobForceLogout();
    localJobForceLogout.run();
    getContentPane().removeAll();
    JLabel localJLabel = new JLabel(StrRes.getStr("IDS_SYSERR_CLOSE_BROWSER"), 0);
    getContentPane().add(localJLabel);
    getContentPane().paintAll(getGraphics());
  }
  
  public String getCookie()
  {
    return CookieClerk.getSetting();
  }
  
  public int getInstanceCount()
  {
    return mInstanceCount;
  }
  
  public void init()
  {
    for (;;)
    {
      if (!mInStop) {
        break;
      }
    }
    if (!isValidVMVersion()) {
      return;
    }
    Object localObject2;
    if (mInstanceCount > 1)
    {
      localObject1 = new GridBagLayout();
      localObject2 = new GridBagConstraints();
      getContentPane().setLayout((LayoutManager)localObject1);
      gridx = 0;
      gridy = 0;
      gridheight = 1;
      gridwidth = 1;
      weightx = 0.0D;
      weighty = 0.0D;
      anchor = 10;
      insets = new Insets(2, 2, 2, 2);
      getContentPane().add(new JLabel(StrRes.getStr("IDS_LOGIN_ERROR_TITLE")), localObject2);
      return;
    }
    FocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new RestraintKeyEventDispatcher());
    Object localObject1 = ThreadAppCom.createInstance();
    ((ThreadAppCom)localObject1).start();
    try
    {
      super.init();
      localObject2 = new JobGetSysVersion();
      Result localResult = ThreadAppCom.getInstance().addJob((Job)localObject2);
      while (!localResult.isFinished()) {
        Thread.sleep(300L);
      }
      if (((JobGetSysVersion)localObject2).getException() != null)
      {
        CommonUse.CommErr(((JobGetSysVersion)localObject2).getException());
        return;
      }
      SystemInfo.setInfo((JobGetSysVersion)localObject2);
      localObject2 = new JobGetSysInit();
      localResult = ThreadAppCom.getInstance().addJob((Job)localObject2);
      while (!localResult.isFinished()) {
        Thread.sleep(300L);
      }
      if (((JobGetSysInit)localObject2).getException() != null)
      {
        CommonUse.CommErr(((JobGetSysInit)localObject2).getException());
        return;
      }
      SystemInfo.setInfo((JobGetSysInit)localObject2);
      beforeLogin();
    }
    catch (Exception localException)
    {
      CommonUse.AppErr(localException, "AppletMain.init");
    }
  }
  
  public static boolean isInStop()
  {
    return mInStop;
  }
  
  public boolean isValidVMVersion()
  {
    String str1 = System.getProperty("java.version");
    String str2 = str1;
    String str3 = null;
    int i = str1.indexOf("_");
    if (i != -1)
    {
      str2 = str1.substring(0, i);
      str3 = str1.substring(i + 1);
    }
    String[] arrayOfString = str2.split("\\.");
    if (arrayOfString.length != 3) {
      return false;
    }
    String str4 = arrayOfString[0];
    String str5 = arrayOfString[1];
    String str6 = arrayOfString[2];
    int j = Integer.valueOf(str4).intValue();
    int k = Integer.valueOf(str5).intValue();
    int m = Integer.valueOf(str6).intValue();
    int n = 0;
    if (str3 != null) {
      n = Integer.valueOf(str3).intValue();
    }
    if (j > 1) {
      return true;
    }
    if ((j == 1) && (k > 6)) {
      return true;
    }
    if ((j == 1) && (k == 6) && (m > 0)) {
      return true;
    }
    return (j == 1) && (k == 6) && (m == 0) && (n >= 0);
  }
  
  public void logout()
  {
    logoutPrivate();
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        setSimpleMode(false);
        AppletMain.this.beforeLogin();
      }
    });
  }
  
  private void logoutPrivate()
  {
    JobLogout localJobLogout = new JobLogout();
    Result localResult = ThreadAppCom.getInstance().addJob(localJobLogout);
    if (!CommonUse.waitJob(localResult)) {}
  }
  
  public void paint(Graphics paramGraphics)
  {
    super.paint(paramGraphics);
    if (mPanelLogin == null) {
      return;
    }
    mPanelLogin.setInitialFocus();
  }
  
  public void setCookie(String paramString)
  {
    if ((CookieClerk.setSetting(paramString) == true) && (mPanelLogin != null)) {
      mPanelLogin.textInit();
    }
  }
  
  public void stop()
  {
    if (mInstanceCount > 1) {
      return;
    }
    if (!isValidVMVersion()) {
      return;
    }
    mInStop = true;
    DlgAbstract.setEnable(false);
    for (;;)
    {
      if ((!DlgWait.isWaiting()) && (!DlgWaitWithCancel.isWaiting()) && (!CommonUse.isWaitJob())) {
        break;
      }
    }
    DlgAbstract.closeAll();
    if (mPanelMain != null) {
      mPanelMain.appletStopped();
    }
    logoutPrivate();
    DlgWait.deleteInstance();
    DlgWaitWithCancel.deleteInstance();
  }
}
