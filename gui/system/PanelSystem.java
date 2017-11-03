package webitc.gui.system;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.ButtonEventListener;
import webitc.gui.common.CommonUse;
import webitc.gui.common.PanelAbstract;
import webitc.job.JobChangePswd;
import webitc.job.JobGetPswd;

public class PanelSystem
  extends PanelAbstract
  implements ButtonEventListener, SystemTable.SystemTableListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton mBtnSet = new JButton();
  private SystemTable mIconPanel = new SystemTable();
  JPanel mPnlOperation = new JPanel();
  
  public PanelSystem()
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
  
  public void buttonSelected(int paramInt)
  {
    switch (paramInt)
    {
    case 20: 
      doChangePassword();
      break;
    case 21: 
      doUserSetting();
      break;
    case 23: 
      doVersionInfo();
    }
  }
  
  private void doChangePassword()
  {
    DataUserInfo localDataUserInfo = DataMgr.getInstance().getLoginUser();
    String str = getCurPassword(localDataUserInfo.getUserID());
    if (str == null) {
      return;
    }
    DlgChangePswd localDlgChangePswd = new DlgChangePswd(str);
    localDlgChangePswd.doModal();
    if (!localDlgChangePswd.isOK()) {
      return;
    }
    JobChangePswd localJobChangePswd = new JobChangePswd(localDataUserInfo.getUserID(), localDlgChangePswd.getNewPassword(), true);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobChangePswd);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobChangePswd.getResult() == 1) {
      CommonUse.showInformationDlg(StrRes.getStr("IDS_SYSTEM_MSG_MODIFY_PASSWORD"), StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
    } else {
      throw new FatalException("SystemTable.doChangePassword");
    }
  }
  
  private void doUserSetting()
  {
    DlgUserSetting localDlgUserSetting = new DlgUserSetting();
    localDlgUserSetting.doModal();
  }
  
  private void doVersionInfo()
  {
    DlgVersionInfo localDlgVersionInfo = new DlgVersionInfo();
    localDlgVersionInfo.doModal();
  }
  
  private String getCurPassword(int paramInt)
  {
    JobGetPswd localJobGetPswd = new JobGetPswd(paramInt);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetPswd);
    if (!CommonUse.waitJob(localResult)) {
      return null;
    }
    if (localJobGetPswd.getResult() == 1) {
      return localJobGetPswd.getPassword();
    }
    throw new FatalException("SystemTable.getCurPassword");
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    mBtnSet.setText(StrRes.getStr("IDS_COMMON_SETUP"));
    mBtnSet.addActionListener(new PanelSystem_mBtnSet_actionAdapter(this));
    mPnlOperation.setLayout(gridBagLayout2);
    setMinimumSize(new Dimension(220, 19));
    setPreferredSize(new Dimension(220, 19));
    add(mIconPanel.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 18, 1, new Insets(0, 0, 0, 0), 0, 0));
    add(mPnlOperation, new GridBagConstraints(1, 0, 1, 1, 0.0D, 1.0D, 11, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOperation.add(mBtnSet, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 11, 0, new Insets(5, 5, 5, 5), 0, 0));
    mIconPanel.addButtonEventListener(this);
    mIconPanel.setListener(this);
  }
  
  void mBtnSet_actionPerformed(ActionEvent paramActionEvent)
  {
    int i = mIconPanel.getSelectedID();
    buttonSelected(i);
  }
  
  public void notifyChangeSelect(int paramInt)
  {
    if (paramInt == -1) {
      mBtnSet.setEnabled(false);
    } else {
      mBtnSet.setEnabled(true);
    }
  }
}
