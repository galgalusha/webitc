package webitc.gui.monitor;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import webitc.common.StrRes;
import webitc.data.DataMgr;
import webitc.data.point.Port;
import webitc.gui.JButtonBar;
import webitc.gui.common.IconRes;

class JMonitorBar
  extends JButtonBar
{
  Border border1;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JToggleButton mBtnIcon = new JToggleButton();
  private JToggleButton mBtnList = new JToggleButton();
  private JButton mRefresh = new JButton();
  JTextField mTxtStatus = new JTextField();
  
  public JMonitorBar()
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
  
  private void btnIcon_itemStateChanged(ItemEvent paramItemEvent)
  {
    if (paramItemEvent.getStateChange() == 1) {
      fireButtonEvent(4);
    }
  }
  
  private void btnList_itemStateChanged(ItemEvent paramItemEvent)
  {
    if (paramItemEvent.getStateChange() == 1) {
      fireButtonEvent(5);
    }
  }
  
  private void btnRefresh_actionPerformed(ActionEvent paramActionEvent)
  {
    fireButtonEvent(3);
  }
  
  private void jbInit()
    throws Exception
  {
    border1 = BorderFactory.createEtchedBorder(Color.white, new Color(165, 163, 151));
    setLayout(gridBagLayout1);
    mRefresh.setText(StrRes.getStr("IDS_WEBMAIN_REFRESH"));
    mRefresh.setIcon(IconRes.getIcon(13));
    mRefresh.setMargin(new Insets(2, 2, 2, 2));
    mRefresh.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        JMonitorBar.this.btnRefresh_actionPerformed(paramAnonymousActionEvent);
      }
    });
    mTxtStatus.setToolTipText("");
    mTxtStatus.setEditable(false);
    add(mRefresh, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 2, 0, 2), 0, 0));
    mBtnIcon.setText(StrRes.getStr("IDS_COMMON_ICON"));
    mBtnIcon.setIcon(IconRes.getIcon(14));
    mBtnIcon.setMargin(new Insets(2, 2, 2, 2));
    mBtnIcon.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent paramAnonymousItemEvent)
      {
        JMonitorBar.this.btnIcon_itemStateChanged(paramAnonymousItemEvent);
      }
    });
    add(mBtnIcon, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 2, 0, 2), 0, 0));
    mBtnList.setText(StrRes.getStr("IDS_COMMON_LIST"));
    mBtnList.setIcon(IconRes.getIcon(15));
    mBtnList.setMargin(new Insets(2, 2, 2, 2));
    mBtnList.addItemListener(new ItemListener()
    {
      public void itemStateChanged(ItemEvent paramAnonymousItemEvent)
      {
        JMonitorBar.this.btnList_itemStateChanged(paramAnonymousItemEvent);
      }
    });
    add(mBtnList, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 2, 0, 0), 0, 0));
    add(mTxtStatus, new GridBagConstraints(3, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 10, 0, 5), 0, 0));
    ButtonGroup localButtonGroup = new ButtonGroup();
    localButtonGroup.add(mBtnIcon);
    localButtonGroup.add(mBtnList);
    mBtnIcon.setSelected(true);
  }
  
  public void refresh()
  {
    DataMgr localDataMgr = DataMgr.getInstance();
    mTxtStatus.setText(localDataMgr.getPort().getPortStr());
  }
}
