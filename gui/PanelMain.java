package webitc.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import webitc.common.StrRes;
import webitc.data.ID;
import webitc.frame.ThreadUpdate;
import webitc.gui.common.CommonUse;
import webitc.gui.history.PanelHistory;
import webitc.gui.interlock.PanelInterlock;
import webitc.gui.monitor.PanelMonitor;
import webitc.gui.ppd.PanelPpd;
import webitc.gui.schedule.PanelSchedule;
import webitc.gui.system.PanelSystem;

public class PanelMain
  extends PanelMainAbstract
  implements TreeSelectionListener, ButtonEventListener
{
  private final int HISTORY_SIZE = 20;
  private PanelHistory mHistory = null;
  private final LinkedList mHistoryList = new LinkedList();
  private int mHistoryPos = -1;
  private PanelInterlock mInterlock = null;
  private final JMainToolBar mMainBar = new JMainToolBar();
  private PanelMonitor mMonitor = null;
  private PanelPpd mPpd = null;
  private PanelSchedule mSchedule = null;
  private final JSplitPane mSplit = new JSplitPane();
  private PanelSystem mSystem = null;
  private final JMainTree mTree = new JMainTree();
  
  public PanelMain()
  {
    try
    {
      mMainBar.setBounds(new Rectangle(0, 0, 600, 30));
      mMainBar.addButtonEventListener(this);
      mSplit.setDividerLocation(0);
      mSplit.setLeftComponent(mTree.getScrollPane());
      mSplit.setOneTouchExpandable(true);
      mSplit.setDividerLocation(140);
      Dimension localDimension = new Dimension(100, 50);
      mTree.setMinimumSize(localDimension);
      mSplit.setBounds(new Rectangle(0, 37, 510, 430));
      GridBagLayout localGridBagLayout = new GridBagLayout();
      GridBagConstraints localGridBagConstraints = new GridBagConstraints();
      setLayout(localGridBagLayout);
      gridx = 0;
      gridy = 0;
      gridheight = 1;
      gridwidth = 1;
      weightx = 0.0D;
      weighty = 0.0D;
      fill = 2;
      anchor = 18;
      add(mMainBar, localGridBagConstraints);
      gridx = 0;
      gridy = 1;
      gridheight = 1;
      gridwidth = 1;
      weightx = 1.0D;
      weighty = 1.0D;
      fill = 1;
      anchor = 18;
      add(mSplit, localGridBagConstraints);
      mMonitor = new PanelMonitor();
      mSchedule = new PanelSchedule();
      mPpd = new PanelPpd();
      mInterlock = new PanelInterlock();
      mHistory = new PanelHistory();
      mSystem = new PanelSystem();
      mSplit.setRightComponent(mMonitor);
      MainTreeModel localMainTreeModel = new MainTreeModel();
      localMainTreeModel.setZone();
      mTree.setModel(localMainTreeModel);
      mTree.addTreeSelectionListener(this);
      mTree.expandRow(0);
      addAuthListener(mSchedule);
      addAuthListener(mPpd);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void appletStopped()
  {
    if (mSchedule != null) {
      mSchedule.panelDisselected();
    }
  }
  
  public void buttonSelected(int paramInt)
  {
    TreePath localTreePath1;
    TreePath localTreePath2;
    switch (paramInt)
    {
    case 0: 
      mHistoryPos -= 1;
      localTreePath1 = (TreePath)mHistoryList.get(mHistoryPos);
      localTreePath2 = mTree.getSelectionPath();
      mTree.removeTreeSelectionListener(this);
      mTree.setSelectionPath(localTreePath1);
      mTree.addTreeSelectionListener(this);
      treeSelected((TreeNodeAbst)localTreePath2.getLastPathComponent(), (TreeNodeAbst)localTreePath1.getLastPathComponent());
      mMainBar.setEnabledForeward(true);
      if (mHistoryPos == 0) {
        mMainBar.setEnabledBackward(false);
      } else {
        mMainBar.setEnabledBackward(true);
      }
      break;
    case 1: 
      mHistoryPos += 1;
      localTreePath1 = (TreePath)mHistoryList.get(mHistoryPos);
      localTreePath2 = mTree.getSelectionPath();
      mTree.removeTreeSelectionListener(this);
      mTree.setSelectionPath(localTreePath1);
      mTree.addTreeSelectionListener(this);
      treeSelected((TreeNodeAbst)localTreePath2.getLastPathComponent(), (TreeNodeAbst)localTreePath1.getLastPathComponent());
      mMainBar.setEnabledBackward(true);
      if (mHistoryPos + 1 >= mHistoryList.size()) {
        mMainBar.setEnabledForeward(false);
      } else {
        mMainBar.setEnabledForeward(true);
      }
      break;
    case 2: 
      if (CommonUse.showConfirmDlg(StrRes.getStr("IDS_WEBMAIN_CONFORM_LOGOFF"), StrRes.getStr("IDS_UTILMESSAGE_CONFIRM")) == true) {
        fireLogoutEvent();
      }
      break;
    }
  }
  
  public PanelMonitor getPanelMonitor()
  {
    return mMonitor;
  }
  
  private void refresh()
  {
    mTree.repaint();
  }
  
  public void resetTreeSelection()
  {
    mTree.setSelectionRow(0);
  }
  
  public void statusUpdated()
  {
    refresh();
  }
  
  private void treeSelected(TreeNodeAbst paramTreeNodeAbst1, TreeNodeAbst paramTreeNodeAbst2)
  {
    if (paramTreeNodeAbst1 != null) {
      switch (paramTreeNodeAbst1.getType())
      {
      case 3: 
        mSchedule.panelDisselected();
      }
    }
    int i = mSplit.getDividerLocation();
    switch (paramTreeNodeAbst2.getType())
    {
    case 1: 
      if (mSplit.getRightComponent() != mMonitor)
      {
        mSplit.setRightComponent(mMonitor);
        mSplit.setDividerLocation(i);
      }
      mMonitor.rootSelected();
      break;
    case 2: 
      if (mSplit.getRightComponent() != mMonitor)
      {
        mSplit.setRightComponent(mMonitor);
        mSplit.setDividerLocation(i);
      }
      ID localID = (ID)paramTreeNodeAbst2.getUserObject();
      mMonitor.zoneSelected(localID);
      break;
    case 3: 
      ThreadUpdate.interruptedUpdate(new ArrayList());
      mSplit.setRightComponent(mSchedule);
      mSplit.setDividerLocation(i);
      mSchedule.panelSelected();
      break;
    case 4: 
      ThreadUpdate.interruptedUpdate(new ArrayList());
      mSplit.setRightComponent(mPpd);
      mSplit.setDividerLocation(i);
      mPpd.panelSelected();
      break;
    case 5: 
      ThreadUpdate.interruptedUpdate(new ArrayList());
      mSplit.setRightComponent(mInterlock);
      mSplit.setDividerLocation(i);
      break;
    case 6: 
      ThreadUpdate.interruptedUpdate(new ArrayList());
      mSplit.setRightComponent(mHistory);
      mSplit.setDividerLocation(i);
      break;
    case 7: 
      ThreadUpdate.interruptedUpdate(new ArrayList());
      mSplit.setRightComponent(mSystem);
      mSplit.setDividerLocation(i);
    }
  }
  
  public void valueChanged(TreeSelectionEvent paramTreeSelectionEvent)
  {
    TreePath localTreePath1 = paramTreeSelectionEvent.getNewLeadSelectionPath();
    if (localTreePath1 == null) {
      return;
    }
    TreePath localTreePath2 = paramTreeSelectionEvent.getOldLeadSelectionPath();
    TreeNodeAbst localTreeNodeAbst1 = null;
    if (localTreePath2 != null) {
      localTreeNodeAbst1 = (TreeNodeAbst)localTreePath2.getLastPathComponent();
    }
    TreeNodeAbst localTreeNodeAbst2 = (TreeNodeAbst)localTreePath1.getLastPathComponent();
    treeSelected(localTreeNodeAbst1, localTreeNodeAbst2);
    if (mHistoryPos + 1 >= 20)
    {
      mHistoryList.removeFirst();
      mHistoryPos -= 1;
    }
    while (mHistoryList.size() - 1 > mHistoryPos) {
      mHistoryList.removeLast();
    }
    mHistoryList.addLast(localTreePath1);
    mHistoryPos += 1;
    if (mHistoryPos != 0) {
      mMainBar.setEnabledBackward(true);
    } else {
      mMainBar.setEnabledBackward(false);
    }
    mMainBar.setEnabledForeward(false);
  }
}
