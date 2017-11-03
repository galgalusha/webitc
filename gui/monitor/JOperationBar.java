package webitc.gui.monitor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.Port;
import webitc.gui.JButtonBar;

class JOperationBar
  extends JButtonBar
{
  private JButton mAllOff = new JButton();
  private JButton mAllOn = new JButton();
  private JButton mInfo = new JButton();
  private JButton mOff = new JButton();
  private JButton mOn = new JButton();
  private JButton mSetting = new JButton();
  
  public JOperationBar()
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
  
  private void btnAllOff_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(7);
  }
  
  private void btnAllOn_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(6);
  }
  
  private void btnInfo_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(10);
  }
  
  private void btnOff_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(9);
  }
  
  private void btnOn_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(8);
  }
  
  private void btnSetting_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(11);
  }
  
  private void jbInit()
    throws Exception
  {
    GridBagLayout localGridBagLayout = new GridBagLayout();
    setLayout(localGridBagLayout);
    GridBagConstraints localGridBagConstraints = new GridBagConstraints();
    anchor = 18;
    fill = 2;
    gridx = 0;
    mAllOn.setText(StrRes.getStr("IDS_CCMAIN_START_ALL"));
    localGridBagLayout.setConstraints(mAllOn, localGridBagConstraints);
    mAllOn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOperationBar.this.btnAllOn_actionPerformed(paramAnonymousActionEvent);
      }
    });
    add(mAllOn, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.16D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
    mAllOff.setText(StrRes.getStr("IDS_CCMAIN_STOP_ALL"));
    localGridBagLayout.setConstraints(mAllOff, localGridBagConstraints);
    mAllOff.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOperationBar.this.btnAllOff_actionPerformed(paramAnonymousActionEvent);
      }
    });
    add(mAllOff, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.16D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
    mOn.setText(StrRes.getStr("IDS_COMMON_START"));
    localGridBagLayout.setConstraints(mOn, localGridBagConstraints);
    mOn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOperationBar.this.btnOn_actionPerformed(paramAnonymousActionEvent);
      }
    });
    add(mOn, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.16D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
    mOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    localGridBagLayout.setConstraints(mOff, localGridBagConstraints);
    mOff.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOperationBar.this.btnOff_actionPerformed(paramAnonymousActionEvent);
      }
    });
    add(mOff, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.16D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
    mInfo.setText(StrRes.getStr("IDS_CCMAIN_INFO"));
    localGridBagLayout.setConstraints(mInfo, localGridBagConstraints);
    mInfo.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOperationBar.this.btnInfo_actionPerformed(paramAnonymousActionEvent);
      }
    });
    add(mInfo, new GridBagConstraints(0, 4, 1, 1, 0.0D, 0.16D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
    mSetting.setText(StrRes.getStr("IDS_COMMON_SETUP"));
    localGridBagLayout.setConstraints(mSetting, localGridBagConstraints);
    mSetting.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JOperationBar.this.btnSetting_actionPerformed(paramAnonymousActionEvent);
      }
    });
    add(mSetting, new GridBagConstraints(0, 5, 1, 1, 0.0D, 0.16D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  public void updateButtons(ID paramID)
  {
    DataMgr localDataMgr = DataMgr.getInstance();
    Port localPort = localDataMgr.getPort();
    if (paramID != null)
    {
      mOn.setEnabled(localDataMgr.isValidON(paramID));
      mOff.setEnabled(localDataMgr.isValidOFF(paramID));
      if (localDataMgr.isValidOperation(paramID) == true) {
        mSetting.setEnabled(true);
      } else {
        mSetting.setEnabled(false);
      }
      mInfo.setEnabled(true);
    }
    else
    {
      mOn.setEnabled(false);
      mOff.setEnabled(false);
      mInfo.setEnabled(false);
      mSetting.setEnabled(false);
    }
    mAllOn.setEnabled(fAllVaildON);
    mAllOff.setEnabled(fAllVaildOFF);
  }
}
