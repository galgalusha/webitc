package webitc.gui.common;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumLang;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.job.JobGetStrLen;

public class DlgSetName
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnOK = new JButton();
  private final int mMaxLength;
  JTextField mTxtName = new JTextField();
  
  public DlgSetName(String paramString, int paramInt)
  {
    mMaxLength = paramInt;
    try
    {
      jbInit();
      mTxtName.setText(paramString);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private boolean check()
  {
    JobGetStrLen localJobGetStrLen = new JobGetStrLen(mTxtName.getText());
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetStrLen);
    if (!CommonUse.waitJob(localResult)) {
      return false;
    }
    if (localJobGetStrLen.getWidth() > mMaxLength)
    {
      if ((SystemInfo.getLang() == EnumLang.JAPANESE) || (SystemInfo.getLang() == EnumLang.CHINESE) || (SystemInfo.getLang() == EnumLang.KOREAN)) {
        CommonUse.showErrorDlg("<html>" + StrRes.getStr("IDS_KBD_LONG_STR_1") + "<br>" + StrRes.getStr("IDS_COMMON_STRLEN_HANKAKU") + ": " + mMaxLength + "  " + StrRes.getStr("IDS_COMMON_STRLEN_ZENKAKU") + ": " + mMaxLength / 2 + "</html>", StrRes.getStr("IDS_COMMON_ERROR"));
      } else {
        CommonUse.showErrorDlg("<html>" + StrRes.getStr("IDS_KBD_LONG_STR_1") + "<br>" + StrRes.getStr("IDS_COMMON_STRLEN") + ": " + mMaxLength + "</html>", StrRes.getStr("IDS_COMMON_ERROR"));
      }
      return false;
    }
    return true;
  }
  
  public void errorPerformed()
  {
    mTxtName.setEnabled(false);
    mBtnOK.setEnabled(false);
  }
  
  public String getName()
  {
    return mTxtName.getText();
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_COMMON_CHANGE_NAME"));
    getRootPane().setDefaultButton(mBtnOK);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSetName_mBtnOK_actionAdapter(this));
    getContentPane().setLayout(gridBagLayout1);
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSetName_mBtnCancel_actionAdapter(this));
    mTxtName.setMinimumSize(new Dimension(11, 20));
    mTxtName.setPreferredSize(new Dimension(200, 20));
    getContentPane().add(mBtnCancel, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 5, 6, 12), 0, 0));
    getContentPane().add(mTxtName, new GridBagConstraints(0, 0, 3, 1, 1.0D, 0.0D, 10, 2, new Insets(15, 20, 15, 20), 0, 0));
    getContentPane().add(mBtnOK, new GridBagConstraints(1, 1, 1, 1, 1.0D, 0.0D, 13, 0, new Insets(5, 5, 6, 5), 0, 0));
  }
  
  void mBtnCancel_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(false);
  }
  
  void mBtnOK_actionPerformed(ActionEvent paramActionEvent)
  {
    if (check() == true) {
      closeDialog(true);
    }
  }
}
