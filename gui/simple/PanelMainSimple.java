package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import webitc.common.StrRes;
import webitc.data.ID;
import webitc.gui.ButtonEventListener;
import webitc.gui.MainTreeModel;
import webitc.gui.PanelMainAbstract;
import webitc.gui.TreeNodeAbst;
import webitc.gui.common.ColorRes;
import webitc.gui.common.CommonUse;

public class PanelMainSimple
  extends PanelMainAbstract
  implements ButtonEventListener, TreeSelectionListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  PanelMonitorSimple mMonitor = new PanelMonitorSimple();
  JSplitPane mSplit = new JSplitPane();
  SimpleTree mTree = new SimpleTree();
  
  public PanelMainSimple()
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
  
  public void appletStopped()
  {
    mMonitor.appletStopped();
  }
  
  public void buttonSelected(int paramInt)
  {
    if (paramInt == 2)
    {
      if (CommonUse.showConfirmDlg(StrRes.getStr("IDS_WEBMAIN_CONFORM_LOGOFF"), StrRes.getStr("IDS_UTILMESSAGE_CONFIRM")) == true)
      {
        mMonitor.appletStopped();
        fireLogoutEvent();
      }
    }
    else if (paramInt == 3) {
      mMonitor.refresh();
    }
  }
  
  public PanelMonitorSimple getPanelMonitor()
  {
    return mMonitor;
  }
  
  private void jbInit()
  {
    setBackground(ColorRes.SIMPLE_MODE_FRAME);
    setMinimumSize(new Dimension(750, 450));
    setPreferredSize(new Dimension(750, 450));
    mMonitor.getMainToolBar().addButtonEventListener(this);
    mSplit.setDividerLocation(1);
    mSplit.setLeftComponent(mTree.getScrollPane());
    mSplit.setOneTouchExpandable(true);
    mSplit.setDividerLocation(200);
    mSplit.setRightComponent(mMonitor);
    setLayout(gridBagLayout1);
    add(mSplit, new GridBagConstraints(0, 0, 1, 3, 1.0D, 1.0D, 10, 1, new Insets(2, 2, 2, 2), 0, 0));
    MainTreeModel localMainTreeModel = new MainTreeModel();
    localMainTreeModel.setZone();
    mTree.setModel(localMainTreeModel);
    mTree.addTreeSelectionListener(this);
    mTree.expandRow(0);
  }
  
  public void resetTreeSelection()
  {
    mTree.setSelectionRow(0);
  }
  
  public void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
  {
    TreePath localTreePath = paramTreeSelectionEvent.getNewLeadSelectionPath();
    if (localTreePath == null) {
      return;
    }
    TreeNodeAbst localTreeNodeAbst = (TreeNodeAbst)localTreePath.getLastPathComponent();
    switch (localTreeNodeAbst.getType())
    {
    case 1: 
      mMonitor.rootSelected();
      break;
    case 2: 
      ID localID = (ID)localTreeNodeAbst.getUserObject();
      mMonitor.zoneSelected(localID);
    }
  }
}
