package webitc.gui.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import webitc.common.AppletAbst;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumModelType;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.AuthListener;
import webitc.gui.common.BorderRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;
import webitc.job.JobLogin;

public class PanelLogin
  extends PanelAbstract
{
  protected ArrayList mAuthListenerList = new ArrayList();
  JButton mBtnLang = new JButton();
  private final JButton mBtnLogin = new JButton();
  JLabel mLbl1 = new JLabel();
  JLabel mLbl2 = new JLabel();
  JLabel mLbl3 = new JLabel();
  JLabel mLbl4 = new JLabel();
  JLabel mLblGuiMode = new JLabel();
  private final JLabel mLblIcon = new JLabel();
  private final JLabel mLblName = new JLabel();
  private final JLabel mLblPass = new JLabel();
  private final JLabel mLblTitle = new JLabel();
  JLabel mLblVersion = new JLabel();
  private boolean mLoginFailed = false;
  private boolean mProcLogin = false;
  JRadioButton mRadioAdvance = new JRadioButton();
  JRadioButton mRadioBasic = new JRadioButton();
  boolean mServiceLogin = false;
  private int mServiceNum = 0;
  private final JTextField mTxtName = new JTextField();
  private final JPasswordField mTxtPass = new JPasswordField();
  private static int sPreGuiMode = 1;
  private static String sPreLoginUser = null;
  
  public PanelLogin()
  {
    try
    {
      jbInit();
      textInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void addAuthListener(AuthListener paramAuthListener)
  {
    mAuthListenerList.add(paramAuthListener);
  }
  
  private void btnLogin_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mProcLogin == true) {
      return;
    }
    mProcLogin = true;
    if (!checkService())
    {
      mLoginFailed = true;
      mProcLogin = false;
      return;
    }
    String str = mTxtName.getText();
    if (!str.matches("[a-zA-Z0-9]*"))
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_FAIL_AUTH"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
      mLoginFailed = true;
      mProcLogin = false;
      return;
    }
    if (str.length() > 15)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_FAIL_AUTH"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
      mLoginFailed = true;
      mProcLogin = false;
      return;
    }
    JobLogin localJobLogin = new JobLogin(str, new String(mTxtPass.getPassword()));
    Result localResult = ThreadAppCom.getInstance().addJob(localJobLogin);
    if (!CommonUse.waitJob(localResult))
    {
      mLoginFailed = true;
      mProcLogin = false;
      return;
    }
    if (localJobLogin.getAuthResult() == 1)
    {
      if (str.compareToIgnoreCase("service") != 0) {
        sPreLoginUser = str;
      }
      mLoginFailed = false;
      if (mRadioBasic.isSelected() == true)
      {
        fireAuthEvent(1);
        sPreGuiMode = 1;
      }
      else
      {
        fireAuthEvent(0);
        sPreGuiMode = 0;
      }
      mProcLogin = false;
    }
    else
    {
      if (localJobLogin.getException() != null)
      {
        CommonUse.CommErr(localJobLogin.getException());
        mLoginFailed = true;
        mProcLogin = false;
        return;
      }
      switch (localJobLogin.getAuthResult())
      {
      case -2: 
        CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_FAIL_SAME_USER"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
        break;
      case -3: 
        CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_FAIL_GUI"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
        break;
      case -4: 
        CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_FAIL_MAX_USER"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
        break;
      case -1: 
        CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_FAIL_AUTH"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
        break;
      default: 
        CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_ERROR_TITLE"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
      }
      mLoginFailed = true;
      mProcLogin = false;
    }
  }
  
  private boolean checkService()
  {
    if ((!mServiceLogin) && (mTxtName.getText().compareToIgnoreCase("service") == 0))
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_LOGIN_FAIL_AUTH"), StrRes.getStr("IDS_LOGIN_ERROR_TITLE"));
      return false;
    }
    return true;
  }
  
  private void enterServiceMode()
  {
    mServiceLogin = true;
    setBorder(BorderRes.getBorder(1));
  }
  
  protected void fireAuthEvent(int paramInt)
  {
    for (int i = 0; i < mAuthListenerList.size(); i++)
    {
      AuthListener localAuthListener = (AuthListener)mAuthListenerList.get(i);
      localAuthListener.authSucceeded(paramInt);
    }
  }
  
  public static int getPreGuiMode()
  {
    return sPreGuiMode;
  }
  
  public static String getPreLoginUser()
  {
    return sPreLoginUser;
  }
  
  private void jbInit()
    throws Exception
  {
    setBorder(BorderRes.getBorder(0));
    if (sPreLoginUser != null) {
      mTxtName.setText(sPreLoginUser);
    }
    mLblVersion.setText(SystemInfo.getAppletVersion());
    GridBagLayout localGridBagLayout = new GridBagLayout();
    GridBagConstraints localGridBagConstraints = new GridBagConstraints();
    setLayout(localGridBagLayout);
    insets = new Insets(5, 5, 5, 5);
    gridx = 0;
    gridy = 0;
    gridheight = 1;
    gridwidth = 1;
    weightx = 0.0D;
    weighty = 0.0D;
    anchor = 13;
    mLblIcon.setIcon(IconRes.getIcon(17));
    mLbl1.setEnabled(true);
    mLbl1.setOpaque(false);
    mLbl1.setText("   ");
    mLbl1.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
      {
        PanelLogin.this.mLbl1_mouseClicked(paramAnonymousMouseEvent);
      }
    });
    mLbl2.setText("   ");
    mLbl2.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
      {
        PanelLogin.this.mLbl2_mouseClicked(paramAnonymousMouseEvent);
      }
    });
    mLbl3.setText("   ");
    mLbl3.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
      {
        PanelLogin.this.mLbl3_mouseClicked(paramAnonymousMouseEvent);
      }
    });
    mLbl4.setText("   ");
    mLbl4.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent paramAnonymousMouseEvent)
      {
        PanelLogin.this.mLbl4_mouseClicked(paramAnonymousMouseEvent);
      }
    });
    mBtnLang.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        mBtnLang_actionPerformed();
      }
    });
    mBtnLang.setHorizontalAlignment(2);
    mBtnLang.setHorizontalTextPosition(4);
    mBtnLang.setIcon(IconRes.getIcon(25));
    mBtnLang.setMargin(new Insets(2, 7, 2, 7));
    mBtnLogin.setMargin(new Insets(2, 35, 2, 35));
    add(mLblIcon, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(2, 2, 2, 2), 0, 0));
    gridx = 1;
    gridy = 0;
    gridheight = 1;
    gridwidth = 3;
    weightx = 0.0D;
    weighty = 0.0D;
    anchor = 13;
    add(mLblTitle, new GridBagConstraints(2, 1, 3, 1, 0.0D, 0.0D, 10, 0, new Insets(2, 2, 2, 2), 0, 0));
    gridx = 1;
    gridy = 1;
    gridheight = 1;
    gridwidth = 1;
    weightx = 0.0D;
    weighty = 0.0D;
    anchor = 13;
    add(mLblName, new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(4, 2, 4, 2), 0, 0));
    gridx = 2;
    gridy = 1;
    gridheight = 1;
    gridwidth = 2;
    weightx = 0.0D;
    weighty = 0.0D;
    fill = 2;
    anchor = 17;
    add(mTxtName, new GridBagConstraints(3, 2, 2, 1, 0.0D, 0.0D, 10, 2, new Insets(4, 2, 4, 2), 0, 0));
    gridx = 1;
    gridy = 2;
    gridheight = 1;
    gridwidth = 1;
    weightx = 0.0D;
    weighty = 0.0D;
    anchor = 13;
    add(mLblPass, new GridBagConstraints(2, 3, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(4, 2, 4, 2), 0, 0));
    gridx = 2;
    gridy = 2;
    gridheight = 1;
    gridwidth = 2;
    weightx = 0.0D;
    weighty = 0.0D;
    fill = 2;
    anchor = 17;
    add(mTxtPass, new GridBagConstraints(3, 3, 2, 1, 0.0D, 0.0D, 10, 2, new Insets(4, 2, 4, 2), 0, 0));
    gridx = 2;
    gridy = 3;
    gridheight = 1;
    gridwidth = 1;
    weightx = 0.0D;
    weighty = 0.0D;
    mBtnLogin.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        PanelLogin.this.btnLogin_actionPerformed(paramAnonymousActionEvent);
      }
    });
    add(mBtnLogin, new GridBagConstraints(3, 5, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(4, 2, 0, 2), 0, 0));
    add(mLbl1, new GridBagConstraints(5, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mLbl2, new GridBagConstraints(0, 6, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mLbl3, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mLbl4, new GridBagConstraints(5, 6, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mBtnLang, new GridBagConstraints(4, 5, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(4, 2, 0, 2), 0, 0));
    add(mLblVersion, new GridBagConstraints(1, 0, 4, 1, 0.0D, 0.0D, 14, 0, new Insets(5, 0, 0, 5), 0, 0));
    ButtonGroup localButtonGroup = new ButtonGroup();
    mRadioBasic.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        mRadioBasic_actionPerformed(paramAnonymousActionEvent);
      }
    });
    mRadioAdvance.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        mRadioAdvance_actionPerformed(paramAnonymousActionEvent);
      }
    });
    localButtonGroup.add(mRadioBasic);
    localButtonGroup.add(mRadioAdvance);
    if (sPreGuiMode == 0) {
      mRadioAdvance.setSelected(true);
    } else {
      mRadioBasic.setSelected(true);
    }
    add(mLblGuiMode, new GridBagConstraints(2, 4, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(4, 2, 0, 2), 0, 0));
    add(mRadioBasic, new GridBagConstraints(3, 4, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(4, 2, 0, 2), 0, 0));
    add(mRadioAdvance, new GridBagConstraints(4, 4, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(4, 2, 0, 2), 0, 0));
    if ((SystemInfo.getModelType() == EnumModelType.ACS) || (SystemInfo.getModelType() == EnumModelType.EXC)) {
      mBtnLang.setVisible(false);
    }
    AppletAbst localAppletAbst = AppletAbst.getInstance();
    if (localAppletAbst != null) {
      localAppletAbst.getRootPane().setDefaultButton(mBtnLogin);
    }
    requestFocusInWindow();
  }
  
  void mBtnLang_actionPerformed()
  {
    DlgLang localDlgLang = new DlgLang();
    localDlgLang.doModal();
    textInit();
  }
  
  private void mLbl1_mouseClicked(MouseEvent paramMouseEvent) {}
  
  private void mLbl2_mouseClicked(MouseEvent paramMouseEvent)
  {
    if (mServiceNum == 1) {
      mServiceNum = 2;
    } else {
      mServiceNum = 0;
    }
  }
  
  private void mLbl3_mouseClicked(MouseEvent paramMouseEvent)
  {
    if (mServiceNum == 2) {
      mServiceNum = 3;
    } else {
      mServiceNum = 0;
    }
  }
  
  private void mLbl4_mouseClicked(MouseEvent paramMouseEvent)
  {
    if (mServiceNum == 3) {
      enterServiceMode();
    } else {
      mServiceNum = 0;
    }
  }
  
  void mRadioAdvance_actionPerformed(ActionEvent paramActionEvent)
  {
    sPreGuiMode = 0;
  }
  
  void mRadioBasic_actionPerformed(ActionEvent paramActionEvent)
  {
    sPreGuiMode = 1;
  }
  
  public void setInitialFocus()
  {
    if ((sPreLoginUser != null) && (mTxtName.getText().equals(sPreLoginUser)))
    {
      if (mLoginFailed == true) {
        mTxtPass.select(0, String.valueOf(mTxtPass.getPassword()).length());
      }
      mTxtPass.requestFocusInWindow();
    }
    else
    {
      if (mLoginFailed == true) {
        mTxtName.select(0, mTxtName.getText().length());
      }
      mTxtName.requestFocusInWindow();
    }
  }
  
  public static void setPreGuiMode(int paramInt)
  {
    if ((paramInt != 0) && (paramInt != 1)) {
      return;
    }
    sPreGuiMode = paramInt;
  }
  
  public static void setPreLoginUser(String paramString)
  {
    if (paramString.compareToIgnoreCase("service") == 0) {
      return;
    }
    sPreLoginUser = paramString;
  }
  
  public void textInit()
  {
    if (sPreLoginUser != null) {
      mTxtName.setText(sPreLoginUser);
    }
    if (sPreGuiMode == 0) {
      mRadioAdvance.setSelected(true);
    } else {
      mRadioBasic.setSelected(true);
    }
    mBtnLang.setText(StrRes.getStr("IDS_COMMON_LANG"));
    mLblTitle.setText(StrRes.getStr("IDS_LOGIN_INPUT_NAME_PASS"));
    mLblName.setText(StrRes.getStr("IDS_COMMON_USERNAME"));
    mLblPass.setText(StrRes.getStr("IDS_COMMON_PASSWORD"));
    mBtnLogin.setText(StrRes.getStr("IDS_COMMON_OK"));
    mLblGuiMode.setText(StrRes.getStr("IDS_LOGIN_GUI_MODE"));
    mRadioBasic.setText(StrRes.getStr("IDS_LOGIN_BASIC_MODE"));
    mRadioAdvance.setText(StrRes.getStr("IDS_LOGIN_ADVANCED_MODE"));
  }
}
