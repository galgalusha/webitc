package webitc.gui.system;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.ID;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgAbstract;
import webitc.job.JobAddUser;
import webitc.job.JobChangePswd;
import webitc.job.JobDeleteUser;
import webitc.job.JobSaveAuth;
import webitc.job.JobSetWatchZone;

public class DlgUserSetting
  extends DlgAbstract
  implements PanelUserList.UserListListener, PanelSettingButtons.SelectedListener, DlgUserEdit.IUserEdit
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  PanelSettingButtons mButtons = new PanelSettingButtons();
  JButton mCloseBtn = new JButton();
  private boolean mNeedSave = false;
  PanelUserList mUsrList = new PanelUserList();
  PanelZoneList mZoneList;
  
  public DlgUserSetting()
  {
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void doAddUser(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null)) {
      return;
    }
    JobAddUser localJobAddUser = new JobAddUser(paramString1, paramString2);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobAddUser);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobAddUser.getResult() == 1)
    {
      CommonUse.showInformationDlg("<html>" + StrRes.getStr("IDS_SYSTEM_MSG_ADDUSER") + "<br>" + StrRes.getStr("IDS_COMMON_USERNAME") + " : \"" + paramString1 + "\"</html>", StrRes.getStr("IDS_SYSTEM_ADDUSER"));
      mUsrList.updateList();
      mUsrList.setSelectedUserName(paramString1);
      mNeedSave = true;
    }
    else
    {
      throw new FatalException("DlgUserSetting.doAddUser");
    }
  }
  
  private void doChangePassword(int paramInt, String paramString1, String paramString2)
  {
    if (paramString2 == null) {
      return;
    }
    JobChangePswd localJobChangePswd = new JobChangePswd(paramInt, paramString2, false);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobChangePswd);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobChangePswd.getResult() == 1)
    {
      CommonUse.showInformationDlg("<html>" + StrRes.getStr("IDS_SYSTEM_MSG_MODIFY_PASSWORD") + "<br>" + StrRes.getStr("IDS_COMMON_USERNAME") + " : \"" + paramString1 + "\"</html>", StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
      mNeedSave = true;
    }
    else
    {
      throw new FatalException("DlgUserSetting.doChangePassword");
    }
  }
  
  private void doDelUser(int paramInt, String paramString)
  {
    JobDeleteUser localJobDeleteUser = new JobDeleteUser(paramInt);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobDeleteUser);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobDeleteUser.getResult() == 1)
    {
      CommonUse.showInformationDlg("<html>" + StrRes.getStr("IDS_SYSTEM_MSG_DELUSER") + "<br>" + StrRes.getStr("IDS_COMMON_USERNAME") + " : \"" + paramString + "\"</html>", StrRes.getStr("IDS_SYSTEM_DELUSER"));
      int i = mUsrList.getSelectedIndex();
      mUsrList.updateList();
      mUsrList.setSelectedIndex(i);
      mNeedSave = true;
    }
    else
    {
      throw new FatalException("DlgUserSetting.doDelUser");
    }
  }
  
  private void doSetWatchZones(int paramInt, String paramString, ID[] paramArrayOfID)
  {
    if (paramArrayOfID == null) {
      return;
    }
    JobSetWatchZone localJobSetWatchZone = new JobSetWatchZone(paramInt, paramArrayOfID);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobSetWatchZone);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobSetWatchZone.getResult() == 1)
    {
      CommonUse.showInformationDlg("<html>" + StrRes.getStr("IDS_SYSTEM_MSG_MODIFY_WATCHZONE") + "<br>" + StrRes.getStr("IDS_SYSTEM_MSG_REFLECT_NEXTLOGIN") + "<br>" + StrRes.getStr("IDS_COMMON_USERNAME") + " : \"" + paramString + "\"<html>", StrRes.getStr("IDS_SYSTEM_EDITZONE"));
      int i = mUsrList.getSelectedIndex();
      mUsrList.updateList();
      mUsrList.setSelectedIndex(i);
      mNeedSave = true;
    }
    else
    {
      throw new FatalException("DlgUserSetting.doSetWatchZone");
    }
  }
  
  public void errorPerformed()
  {
    mUsrList.setEnabled(false);
    mZoneList.setEnabled(false);
    mButtons.setEnabled(1);
  }
  
  public boolean isExistUser(String paramString)
  {
    return mUsrList.isExist(paramString);
  }
  
  private void jbInit()
    throws Exception
  {
    mZoneList = new PanelZoneList(EnumTableID.DLG_USER_SETTING, StrRes.getStr("IDS_SYSTEM_WATCH_ZONE"), false);
    setTitle(StrRes.getStr("IDS_SYSTEM_SETUPUSER"));
    getContentPane().setLayout(gridBagLayout1);
    mCloseBtn.setText(StrRes.getStr("IDS_COMMON_CLOSE"));
    mCloseBtn.addActionListener(new DlgUserSetting_mCloseBtn_actionAdapter(this));
    mUsrList.setListener(this);
    mButtons.setListener(this);
    getContentPane().add(mUsrList, new GridBagConstraints(0, 0, 1, 1, 0.3D, 1.0D, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mZoneList, new GridBagConstraints(1, 0, 1, 1, 0.7D, 1.0D, 10, 1, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mButtons, new GridBagConstraints(0, 1, 2, 1, 0.0D, 0.0D, 13, 2, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mCloseBtn, new GridBagConstraints(0, 2, 2, 1, 0.0D, 0.0D, 13, 0, new Insets(10, 10, 12, 6), 0, 0));
    mUsrList.updateList();
    mUsrList.setSelectedIndex(0);
  }
  
  void mCloseBtn_actionPerformed()
  {
    if (mNeedSave == true)
    {
      JobSaveAuth localJobSaveAuth = new JobSaveAuth();
      Result localResult = ThreadAppCom.getInstance().addJob(localJobSaveAuth);
      if (!CommonUse.waitJob(localResult)) {
        closeDialog(true);
      }
    }
    closeDialog(true);
  }
  
  public void notifyAddUser()
  {
    if (mUsrList.getNormalUserNum() >= 64)
    {
      CommonUse.showErrorDlg(StrRes.getStr("IDS_SYSTEM_NOMORE_ADDUSER"), StrRes.getStr("IDS_SYSTEM_ADDUSER"));
      return;
    }
    DlgUserEdit localDlgUserEdit = new DlgUserEdit(this, StrRes.getStr("IDS_SYSTEM_ADDUSER"), null);
    localDlgUserEdit.doModal();
    if (!localDlgUserEdit.isOK()) {
      return;
    }
    doAddUser(localDlgUserEdit.getUserName(), localDlgUserEdit.getPassword());
  }
  
  public void notifyChangePassword()
  {
    int i = mUsrList.getSelectedUserID();
    String str = mUsrList.getSelectedUserName();
    if ((str == null) || (i == -1)) {
      return;
    }
    DlgUserEdit localDlgUserEdit = new DlgUserEdit(this, StrRes.getStr("IDD_SYSPASSWORD_MODIFY"), str);
    localDlgUserEdit.doModal();
    if (!localDlgUserEdit.isOK()) {
      return;
    }
    doChangePassword(i, str, localDlgUserEdit.getPassword());
  }
  
  public void notifyChangeSelect(int paramInt)
  {
    if (paramInt == 1) {
      mButtons.setEnabled(2);
    } else {
      mButtons.setEnabled(0);
    }
    mZoneList.updateTable(paramInt);
  }
  
  public void notifyDelUser()
  {
    int i = mUsrList.getSelectedUserID();
    String str = mUsrList.getSelectedUserName();
    if ((str == null) || (i == -1)) {
      return;
    }
    if (CommonUse.showConfirmDlg("<html>" + StrRes.getStr("IDS_SYSTEM_CONFIRM_DELUSER") + "<br>" + StrRes.getStr("IDS_COMMON_USERNAME") + " : \"" + str + "\"</html>", StrRes.getStr("IDS_SYSTEM_DELUSER")) == true) {
      doDelUser(i, str);
    }
  }
  
  public void notifyEditZone()
  {
    int i = mUsrList.getSelectedUserID();
    String str = mUsrList.getSelectedUserName();
    if ((str == null) || (i == -1)) {
      return;
    }
    DlgZoneEdit localDlgZoneEdit = new DlgZoneEdit(str, mZoneList.getAllZoneIDs());
    localDlgZoneEdit.doModal();
    if (!localDlgZoneEdit.isOK()) {
      return;
    }
    doSetWatchZones(i, str, localDlgZoneEdit.getEditedZoneList());
  }
}
