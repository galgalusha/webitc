package webitc.gui.system;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgAbstract;

public class DlgUserEdit
  extends DlgAbstract
  implements PanelOkCancel.PanelOkCancelListener
{
  private final int PWD_DISCORD = -3;
  private final int PWD_INVALID_CHAR = -4;
  private final int PWD_TOO_LONG = -1;
  private final int PWD_TOO_SHORT = -2;
  private final int PWD_VALID = 0;
  private final int USR_DUPLICATE = -3;
  private final int USR_INVALID_CHAR = -4;
  private final int USR_TOO_LONG = -1;
  private final int USR_TOO_SHORT = -2;
  private final int USR_VALID = 0;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private String mErrDlgTitle;
  private IUserEdit mInterface = null;
  JLabel mLabelPass1 = new JLabel();
  JLabel mLabelPass2 = new JLabel();
  JLabel mLabelUser = new JLabel();
  private String mNewPswd;
  PanelOkCancel mOkCancel = new PanelOkCancel(this);
  JPasswordField mPass1 = new JPasswordField();
  JPasswordField mPass2 = new JPasswordField();
  JTextField mUser = new JTextField();
  private String mUserName = null;
  
  public DlgUserEdit(IUserEdit paramIUserEdit, String paramString1, String paramString2)
  {
    try
    {
      setTitle(paramString1);
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    mInterface = paramIUserEdit;
    if (paramString2 != null)
    {
      mUser.setText(paramString2);
      mUser.setEditable(false);
      mUser.setFocusable(false);
      mUserName = paramString2;
      mErrDlgTitle = StrRes.getStr("IDD_SYSPASSWORD_MODIFY");
    }
    else
    {
      mErrDlgTitle = StrRes.getStr("IDS_SYSTEM_ADDUSER");
    }
  }
  
  int checkPassword()
  {
    String str1 = new String(mPass1.getPassword());
    String str2 = new String(mPass2.getPassword());
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
  
  int checkUserName()
  {
    String str = mUser.getText();
    if (!str.matches("[a-zA-Z0-9]*")) {
      return -4;
    }
    if (str.length() < 1) {
      return -2;
    }
    if (str.length() > 15) {
      return -1;
    }
    if (mInterface.isExistUser(str) == true) {
      return -3;
    }
    return 0;
  }
  
  public void errorPerformed()
  {
    mPass1.setEnabled(false);
    mPass2.setEnabled(false);
    mUser.setEnabled(false);
    mOkCancel.setEnabled(1, false);
  }
  
  public String getPassword()
  {
    return mNewPswd;
  }
  
  public String getUserName()
  {
    return mUserName;
  }
  
  private void jbInit()
    throws Exception
  {
    getContentPane().setLayout(gridBagLayout1);
    mLabelUser.setText(StrRes.getStr("IDS_COMMON_USERNAME"));
    mLabelPass1.setText(StrRes.getStr("IDS_COMMON_PASSWORD"));
    mLabelPass2.setText(StrRes.getStr("IDS_SYSTEM_CONFIRM_PASSWORD"));
    mUser.setPreferredSize(new Dimension(200, 20));
    mUser.setText("");
    mPass2.setPreferredSize(new Dimension(200, 22));
    mPass2.setText("");
    mPass1.setPreferredSize(new Dimension(200, 22));
    mPass1.setText("");
    getContentPane().add(mLabelUser, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mLabelPass1, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mLabelPass2, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mPass1, new GridBagConstraints(1, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mPass2, new GridBagConstraints(1, 2, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mUser, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mOkCancel, new GridBagConstraints(0, 3, 2, 1, 1.0D, 0.0D, 13, 2, new Insets(20, 10, 6, 12), 0, 0));
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyOkSelected()
  {
    if (mUser.isEditable() == true)
    {
      i = checkUserName();
      if (i == 0)
      {
        mUserName = mUser.getText();
      }
      else
      {
        if (i == -1)
        {
          CommonUse.showErrorDlg("<html>" + StrRes.getStr("IDS_SYSTEM_USERNAME_TOO_LONG") + "<br>" + StrRes.getStr("IDS_COMMON_STRLEN") + ": " + 15 + "</html>", mErrDlgTitle);
          return;
        }
        if (i == -2)
        {
          CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_USERNAME_TOO_SHORT"), mErrDlgTitle);
          return;
        }
        if (i == -3)
        {
          CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_EXIST_SAME_USERNAME"), mErrDlgTitle);
          return;
        }
        if (i == -4)
        {
          CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_USER_CHARERROR"), mErrDlgTitle);
          return;
        }
      }
    }
    if (mUserName == null) {
      throw new FatalException("DlgUserEdit.checkUserName");
    }
    int i = checkPassword();
    if (i == 0)
    {
      mNewPswd = String.valueOf(mPass1.getPassword());
      closeDialog(true);
      return;
    }
    if (i == -1)
    {
      CommonUse.showErrorDlg("<html>" + StrRes.getStr("IDS_SYSTEM_PASSWORD_TOO_LONG") + "<br>" + StrRes.getStr("IDS_COMMON_STRLEN") + ": " + 15 + "</html>", mErrDlgTitle);
      return;
    }
    if (i == -2)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_PASSWORD_TOO_SHORT"), mErrDlgTitle);
      return;
    }
    if (i == -3)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_PASSWORD_DISACCORD"), mErrDlgTitle);
      return;
    }
    if (i == -4)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_PASSWORD_CHARERROR"), mErrDlgTitle);
      return;
    }
    throw new FatalException("DlgUserEdit.checkPassword");
  }
  
  public void paint(Graphics paramGraphics)
  {
    super.paint(paramGraphics);
    if (!mUser.isEditable()) {
      mPass1.requestFocusInWindow();
    }
  }
  
  static abstract interface IUserEdit
  {
    public abstract boolean isExistUser(String paramString);
  }
}
