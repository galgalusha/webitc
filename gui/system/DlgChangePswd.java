package webitc.gui.system;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgAbstract;

class DlgChangePswd
  extends DlgAbstract
  implements PanelOkCancel.PanelOkCancelListener
{
  private final int NEW_PWD_DISCORD = -3;
  private final int NEW_PWD_INVALID_CHAR = -4;
  private final int NEW_PWD_TOO_LONG = -1;
  private final int NEW_PWD_TOO_SHORT = -2;
  private final int NEW_PWD_VALID = 0;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private String mCurPswd;
  JPasswordField mCurPswdField = new JPasswordField();
  JLabel mCurPswdLabel = new JLabel();
  private String mNewPswd;
  JPasswordField mNewPswdField = new JPasswordField();
  JPasswordField mNewPswdField2 = new JPasswordField();
  JLabel mNewPswdLabel = new JLabel();
  JLabel mNewPswdLabel2 = new JLabel();
  PanelOkCancel mOkCancel = new PanelOkCancel(this);
  
  public DlgChangePswd(String paramString)
  {
    mCurPswd = paramString;
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  boolean checkCurPassword()
  {
    String str = new String(mCurPswdField.getPassword());
    return mCurPswd.equals(str);
  }
  
  int checkNewPassword()
  {
    String str1 = new String(mNewPswdField.getPassword());
    String str2 = new String(mNewPswdField2.getPassword());
    if ((!str1.matches("[a-zA-Z0-9]*")) && (!str2.matches("[a-zA-Z0-9]*"))) {
      return -4;
    }
    if ((str1.length() < 0) && (str2.length() < 0)) {
      return -2;
    }
    if ((str1.length() > 15) && (str2.length() > 15)) {
      return -1;
    }
    if (str1.equals(str2)) {
      return 0;
    }
    return -3;
  }
  
  public void errorPerformed()
  {
    mCurPswdField.setEnabled(false);
    mNewPswdField.setEnabled(false);
    mNewPswdField2.setEnabled(false);
    mOkCancel.setEnabled(1, false);
  }
  
  public String getNewPassword()
  {
    return mNewPswd;
  }
  
  private void jbInit()
    throws Exception
  {
    mNewPswd = null;
    mCurPswdLabel.setText(StrRes.getStr("IDS_SYSTEM_CUR_PASSWORD"));
    getContentPane().setBackground(SystemColor.control);
    setDefaultCloseOperation(1);
    setTitle(StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
    getContentPane().setLayout(gridBagLayout1);
    mNewPswdLabel.setText(StrRes.getStr("IDS_SYSTEM_NEW_PASSWORD"));
    mNewPswdLabel2.setText(StrRes.getStr("IDS_SYSTEM_CONFIRM_NEW_PASSWORD"));
    mCurPswdField.setPreferredSize(new Dimension(200, 22));
    mCurPswdField.setVerifyInputWhenFocusTarget(true);
    mCurPswdField.setText("");
    mNewPswdField.setPreferredSize(new Dimension(200, 22));
    mNewPswdField.setText("");
    mNewPswdField2.setPreferredSize(new Dimension(200, 22));
    getContentPane().add(mCurPswdLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mNewPswdLabel, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mNewPswdField, new GridBagConstraints(1, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mNewPswdLabel2, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mNewPswdField2, new GridBagConstraints(1, 2, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mCurPswdField, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mOkCancel, new GridBagConstraints(0, 3, 2, 1, 1.0D, 0.0D, 13, 2, new Insets(20, 10, 6, 12), 0, 0));
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyOkSelected()
  {
    if (!checkCurPassword())
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_WRONG_CUR_PASSWORD"), StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
      return;
    }
    int i = checkNewPassword();
    if (i == 0)
    {
      mNewPswd = String.valueOf(mNewPswdField.getPassword());
      closeDialog(true);
      return;
    }
    if (i == -1)
    {
      CommonUse.showErrorDlg("<html>" + StrRes.getStr("IDS_SYSTEM_NEW_PASSWORD_TOO_LONG") + "<br>" + StrRes.getStr("IDS_COMMON_STRLEN") + ": " + 15 + "</html>", StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
      return;
    }
    if (i == -2)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_NEW_PASSWORD_TOO_SHORT"), StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
      return;
    }
    if (i == -3)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_NEW_PASSWORD_DISACCORD"), StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
      return;
    }
    if (i == -4)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_PASSWORD_CHARERROR"), StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
      return;
    }
    throw new FatalException("DlgChangePswd.checkNewPassword");
  }
}
