package webitc.gui.system;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import webitc.common.StrRes;
import webitc.gui.common.PanelAbstract;

public class PanelSettingButtons
  extends PanelAbstract
{
  public static final int MODE_ERROR = 1;
  public static final int MODE_NORMAL = 0;
  public static final int MODE_ROOT = 2;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mAddUsr = new JButton();
  JButton mChangePwd = new JButton();
  JButton mDelUsr = new JButton();
  JButton mEditZone = new JButton();
  private SelectedListener mListener = null;
  
  public PanelSettingButtons()
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
  
  private void jbInit()
    throws Exception
  {
    mAddUsr.setText(StrRes.getStr("IDS_SYSTEM_ADDUSER"));
    mAddUsr.addActionListener(new PanelSettingButtons_mAddUsr_actionAdapter(this));
    mDelUsr.setText(StrRes.getStr("IDS_SYSTEM_DELUSER"));
    mDelUsr.addActionListener(new PanelSettingButtons_mDelUsr_actionAdapter(this));
    mChangePwd.setHorizontalTextPosition(11);
    mChangePwd.setText(StrRes.getStr("IDD_SYSPASSWORD_MODIFY"));
    mChangePwd.addActionListener(new PanelSettingButtons_mChangePwd_actionAdapter(this));
    mEditZone.setMnemonic('0');
    mEditZone.setText(StrRes.getStr("IDS_SYSTEM_EDITZONE"));
    mEditZone.addActionListener(new PanelSettingButtons_mEditZone_actionAdapter(this));
    setLayout(gridBagLayout1);
    add(mAddUsr, new GridBagConstraints(0, 0, 1, 1, 0.25D, 0.0D, 13, 2, new Insets(5, 10, 5, 5), 0, 0));
    add(mDelUsr, new GridBagConstraints(1, 0, 1, 1, 0.25D, 0.0D, 10, 2, new Insets(5, 5, 5, 5), 0, 0));
    add(mChangePwd, new GridBagConstraints(2, 0, 1, 1, 0.25D, 0.0D, 10, 2, new Insets(5, 5, 5, 5), 0, 0));
    add(mEditZone, new GridBagConstraints(3, 0, 1, 1, 0.25D, 0.0D, 17, 2, new Insets(5, 5, 5, 5), 0, 0));
  }
  
  void mAddUsr_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyAddUser();
    }
  }
  
  void mChangePwd_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyChangePassword();
    }
  }
  
  void mDelUsr_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyDelUser();
    }
  }
  
  void mEditZone_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyEditZone();
    }
  }
  
  public void setEnabled(int paramInt)
  {
    if (paramInt == 2)
    {
      mAddUsr.setEnabled(true);
      mDelUsr.setEnabled(false);
      mChangePwd.setEnabled(true);
      mEditZone.setEnabled(false);
      return;
    }
    boolean bool = true;
    if (paramInt == 0) {
      bool = true;
    }
    if (paramInt == 1) {
      bool = false;
    }
    mAddUsr.setEnabled(bool);
    mDelUsr.setEnabled(bool);
    mChangePwd.setEnabled(bool);
    mEditZone.setEnabled(bool);
  }
  
  public void setListener(SelectedListener paramSelectedListener)
  {
    mListener = paramSelectedListener;
  }
  
  static abstract interface SelectedListener
  {
    public abstract void notifyAddUser();
    
    public abstract void notifyChangePassword();
    
    public abstract void notifyDelUser();
    
    public abstract void notifyEditZone();
  }
}
