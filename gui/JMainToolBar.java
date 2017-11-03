package webitc.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.gui.common.IconRes;

public class JMainToolBar
  extends JButtonBar
{
  private JButton mBackward = new JButton();
  private JButton mForeward = new JButton();
  private JButton mLogoff = new JButton();
  private JLabel mUserName = new JLabel();
  
  public JMainToolBar()
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
  
  private void btnBackward_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(0);
  }
  
  private void btnForeward_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(1);
  }
  
  private void btnLogoff_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(2);
  }
  
  private void jbInit()
    throws Exception
  {
    GridBagLayout localGridBagLayout = new GridBagLayout();
    GridBagConstraints localGridBagConstraints = new GridBagConstraints();
    setLayout(localGridBagLayout);
    mBackward.setText(StrRes.getStr("IDS_WEBMAIN_BACKWARD"));
    mBackward.setEnabled(false);
    mBackward.setIcon(IconRes.getIcon(11));
    mBackward.setMargin(new Insets(2, 2, 2, 2));
    mBackward.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JMainToolBar.this.btnBackward_actionPerformed(paramAnonymousActionEvent);
      }
    });
    gridx = 0;
    gridy = 0;
    gridheight = 1;
    gridwidth = 1;
    weightx = 0.0D;
    weighty = 0.0D;
    anchor = 17;
    insets = new Insets(2, 6, 2, 3);
    mForeward.setEnabled(false);
    add(mBackward, localGridBagConstraints);
    mForeward.setText(StrRes.getStr("IDS_WEBMAIN_FOREWARD"));
    mForeward.setIcon(IconRes.getIcon(12));
    mForeward.setMargin(new Insets(2, 2, 2, 2));
    mForeward.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JMainToolBar.this.btnForeward_actionPerformed(paramAnonymousActionEvent);
      }
    });
    gridx = 1;
    gridy = 0;
    gridheight = 1;
    gridwidth = 1;
    weightx = 1.0D;
    weighty = 0.0D;
    anchor = 17;
    insets = new Insets(2, 3, 2, 3);
    add(mForeward, localGridBagConstraints);
    mUserName.setText(StrRes.getStr("IDS_WEBMAIN_USER") + ": " + DataMgr.getInstance().getLoginUser().getUserName());
    gridx = 2;
    gridy = 0;
    gridheight = 1;
    gridwidth = 1;
    weightx = 1.0D;
    weighty = 0.0D;
    anchor = 13;
    insets = new Insets(2, 3, 2, 3);
    add(mUserName, localGridBagConstraints);
    mLogoff.setText(StrRes.getStr("IDS_WEBMAIN_LOGOFF"));
    mLogoff.setIcon(IconRes.getIcon(19));
    mLogoff.setMargin(new Insets(2, 2, 2, 2));
    mLogoff.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JMainToolBar.this.btnLogoff_actionPerformed(paramAnonymousActionEvent);
      }
    });
    gridx = 3;
    gridy = 0;
    gridheight = 1;
    gridwidth = 1;
    weightx = 0.0D;
    weighty = 0.0D;
    anchor = 13;
    insets = new Insets(2, 3, 2, 6);
    add(mLogoff, localGridBagConstraints);
  }
  
  public void setEnabledBackward(boolean paramBoolean)
  {
    mBackward.setEnabled(paramBoolean);
  }
  
  public void setEnabledForeward(boolean paramBoolean)
  {
    mForeward.setEnabled(paramBoolean);
  }
}
