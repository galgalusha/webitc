package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import webitc.common.FatalException;
import webitc.common.ListenerPntUpdate;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.PntSet;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.frame.ThreadPntRequest;
import webitc.gui.ButtonEventListener;
import webitc.gui.common.AnimationPanel;
import webitc.gui.common.ColorRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.IconRes;
import webitc.gui.common.JScrollTable;
import webitc.gui.common.PanelAbstract;
import webitc.job.JobSetPntState;
import webitc.job.JobUpdateZoneIDs;

public class PanelMonitorSimple
  extends PanelAbstract
  implements SimpleAcDisplayerListener, PanelDisplayType.PanelDisplayTypeListener, ButtonEventListener, ListenerPntUpdate, IPanelState.PanelStateListener
{
  private static final int EX_LIST = 2;
  private static final int EX_NORMAL = 0;
  private static final int EX_STATE = 1;
  private static final int NORMAL = 0;
  private static final int OPERATION_WAIT = 1;
  private static final int REFLECT_WAIT = 2;
  private static final int TIMER_INTERVAL = 5000;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private int mExpansionState = 0;
  SimpleIconTable mIcon = new SimpleIconTable();
  SimpleList mList = new SimpleList(EnumTableID.PANEL_SIMPLE_LIST);
  SimpleMainToolBar mMainToolBar = new SimpleMainToolBar();
  PanelDisplayType mPanelDisplayType = new PanelDisplayType();
  PanelAbstract mPanelPntNone = new PanelAbstract();
  PanelState mPanelState = new PanelState();
  PanelStateLight mPanelStateLight = new PanelStateLight();
  PanelStateSwitch mPanelStateSwitch = new PanelStateSwitch();
  PanelStateTab mPanelStateTab = new PanelStateTab();
  PanelStateVent mPanelStateVent = new PanelStateVent();
  int mPreDividerLocation = 0;
  private int mPreSelectedIndex = 0;
  boolean mRootSelected = false;
  private ID mSelectedID = null;
  JSplitPane mSplit = new JSplitPane(0);
  private int mState = 0;
  private Timer mTimer = new Timer(5000, new TimerListener());
  
  public PanelMonitorSimple()
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
  
  private void addMonitorPanel(JScrollTable paramJScrollTable)
  {
    mSplit.setLeftComponent(paramJScrollTable.getScrollPane());
  }
  
  private void addStatePanel(JComponent paramJComponent)
  {
    AnimationPanel.endAnimation(0);
    if (paramJComponent != mPanelPntNone)
    {
      IPanelState localIPanelState = (IPanelState)paramJComponent;
      mSplit.setRightComponent(localIPanelState.getScrollPane());
    }
    else
    {
      mSplit.setRightComponent(paramJComponent);
    }
  }
  
  public void appletStopped()
  {
    mTimer.stop();
    IPanelState localIPanelState = getCurrentStatePanel();
    if (localIPanelState == null) {
      return;
    }
    if (!localIPanelState.isChangeSetting()) {
      return;
    }
    ID localID = localIPanelState.getCurrentPntID();
    PntSet localPntSet = localIPanelState.getCurrentPntSet();
    JobSetPntState localJobSetPntState = new JobSetPntState(localID, localPntSet);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobSetPntState);
    if (!CommonUse.waitJobNoDialog(localResult)) {}
    localIPanelState.resetChangeFlag();
  }
  
  public void buttonSelected(int paramInt)
  {
    if (paramInt == 4) {
      notifyIconButton();
    } else if (paramInt == 5) {
      notifyListButton();
    }
  }
  
  public void exceptionalUpdated()
  {
    mIcon.refresh();
    mList.refresh();
    updateStatePanel();
  }
  
  public SimpleAcDisplayer getCurrentDisplayer()
  {
    if (isAncestorOf(mIcon.getScrollPane()) == true) {
      return mIcon;
    }
    if (isAncestorOf(mList.getScrollPane()) == true) {
      return mList;
    }
    return null;
  }
  
  private IPanelState getCurrentStatePanel()
  {
    PanelAbstract localPanelAbstract = getCurrentStatePanelAbst();
    if (localPanelAbstract == mPanelPntNone) {
      return null;
    }
    return (IPanelState)getCurrentStatePanelAbst();
  }
  
  private PanelAbstract getCurrentStatePanelAbst()
  {
    if (isAncestorOf(mPanelState) == true) {
      return mPanelState;
    }
    if (isAncestorOf(mPanelStateVent) == true) {
      return mPanelStateVent;
    }
    if (isAncestorOf(mPanelStateLight) == true) {
      return mPanelStateLight;
    }
    if (isAncestorOf(mPanelStateSwitch) == true) {
      return mPanelStateSwitch;
    }
    if (isAncestorOf(mPanelStateTab) == true) {
      return mPanelStateTab;
    }
    if (isAncestorOf(mPanelPntNone) == true) {
      return mPanelPntNone;
    }
    return null;
  }
  
  public SimpleMainToolBar getMainToolBar()
  {
    return mMainToolBar;
  }
  
  public ID getSelectedID()
  {
    SimpleAcDisplayer localSimpleAcDisplayer = getCurrentDisplayer();
    if (localSimpleAcDisplayer != null) {
      return localSimpleAcDisplayer.getSelectedID();
    }
    return null;
  }
  
  private void jbInit()
  {
    setBackground(ColorRes.SIMPLE_MODE_FRAME);
    setPreferredSize(new Dimension(710, 320));
    mPanelDisplayType.setListener(this);
    String str = DataMgr.getInstance().getLoginUser().getUserName();
    mMainToolBar.setLoginName(str);
    mMainToolBar.addButtonEventListener(this);
    mList.setListener(this);
    mIcon.setListener(this);
    mPanelState.setListener(this);
    mPanelStateVent.setListener(this);
    mPanelStateLight.setListener(this);
    mPanelStateSwitch.setListener(this);
    mPanelStateTab.setListener(this);
    mPanelPntNone.setPreferredSize(new Dimension(getSizewidth, 100));
    mSplit.setOneTouchExpandable(false);
    mSplit.setDividerSize(5);
    mSplit.setResizeWeight(1.0D);
    setLayout(gridBagLayout1);
    add(mMainToolBar, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 16, 2, new Insets(0, 0, 0, 0), 0, 5));
    add(mPanelDisplayType, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 10));
    addMonitorPanel(mIcon);
    add(mSplit, new GridBagConstraints(0, 2, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPanelDisplayType.setExpansionMode(true);
  }
  
  public void notifyExpansion()
  {
    if (mExpansionState == 0)
    {
      mExpansionState = 2;
      mPreDividerLocation = mSplit.getDividerLocation();
      mSplit.setDividerLocation(0.0D);
      mSplit.setDividerSize(-1);
      mPanelState.setVisible(false);
      mPanelStateVent.setVisible(false);
      mPanelStateLight.setVisible(false);
      mPanelStateSwitch.setVisible(false);
      mPanelStateTab.setVisible(false);
      mPanelPntNone.setVisible(false);
      mPanelDisplayType.setExpansionMode(false);
    }
    else if (mExpansionState == 2)
    {
      mExpansionState = 0;
      mSplit.setDividerLocation(mPreDividerLocation);
      mSplit.setDividerSize(5);
      mPanelState.setVisible(true);
      mPanelStateVent.setVisible(true);
      mPanelStateLight.setVisible(true);
      mPanelStateSwitch.setVisible(true);
      mPanelStateTab.setVisible(true);
      mPanelPntNone.setVisible(true);
      mPanelDisplayType.setExpansionMode(true);
      updateStatePanel();
    }
  }
  
  private void notifyIconButton()
  {
    remove(mList.getScrollPane());
    addMonitorPanel(mIcon);
    validate();
    mIcon.getScrollPane().repaint();
    if (mExpansionState == 0)
    {
      setDividerPosition();
      new CallStackBreaker(mPreSelectedIndex, false);
    }
  }
  
  private void notifyListButton()
  {
    remove(mIcon.getScrollPane());
    addMonitorPanel(mList);
    validate();
    mList.getScrollPane().repaint();
    if (mExpansionState == 0)
    {
      setDividerPosition();
      new CallStackBreaker(mPreSelectedIndex, false);
    }
  }
  
  public void notifyOperation()
  {
    mMainToolBar.setRefreshEnabled(false);
    mState = 1;
    mTimer.restart();
  }
  
  public void notifyPntSelected(ID paramID)
  {
    int i = -1;
    updatePntSet();
    SimpleAcDisplayer localSimpleAcDisplayer = getCurrentDisplayer();
    if (localSimpleAcDisplayer == mIcon)
    {
      mList.setSelectedID(paramID);
      mIcon.getScrollPane().repaint();
    }
    else if (localSimpleAcDisplayer == mList)
    {
      mList.getScrollPane().repaint();
      mIcon.setSelectedID(paramID);
    }
    int j = mList.getSelectedRow();
    i = mPreSelectedIndex;
    mPreSelectedIndex = j;
    mState = 0;
    if (mExpansionState == 2) {
      return;
    }
    PanelAbstract localPanelAbstract = getCurrentStatePanelAbst();
    int k = SimpleCommon.getPntType(paramID);
    boolean bool = false;
    switch (k)
    {
    case 0: 
      if (localPanelAbstract != mPanelStateTab)
      {
        if (localPanelAbstract != null) {
          removeStatePanel(localPanelAbstract);
        }
        addStatePanel(mPanelStateTab);
        validate();
        mPanelStateTab.getScrollPane().repaint();
        setDividerPosition();
        bool = true;
      }
      break;
    case 1: 
      if (localPanelAbstract != mPanelState)
      {
        if (localPanelAbstract != null) {
          removeStatePanel(localPanelAbstract);
        }
        addStatePanel(mPanelState);
        validate();
        mPanelState.getScrollPane().repaint();
        setDividerPosition();
        bool = true;
      }
      break;
    case 2: 
      if (localPanelAbstract != mPanelStateVent)
      {
        if (localPanelAbstract != null) {
          removeStatePanel(localPanelAbstract);
        }
        addStatePanel(mPanelStateVent);
        validate();
        mPanelStateVent.getScrollPane().repaint();
        setDividerPosition();
        bool = true;
      }
      break;
    case 3: 
      if (localPanelAbstract != mPanelStateLight)
      {
        if (localPanelAbstract != null) {
          removeStatePanel(localPanelAbstract);
        }
        addStatePanel(mPanelStateLight);
        validate();
        mPanelStateLight.getScrollPane().repaint();
        setDividerPosition();
        bool = true;
      }
      break;
    case 4: 
      if (localPanelAbstract != mPanelStateSwitch)
      {
        if (localPanelAbstract != null) {
          removeStatePanel(localPanelAbstract);
        }
        addStatePanel(mPanelStateSwitch);
        validate();
        mPanelStateSwitch.getScrollPane().repaint();
        setDividerPosition();
        bool = true;
      }
      break;
    case 5: 
    default: 
      throw new FatalException("Unsupported Pnt Type!");
    }
    IPanelState localIPanelState = getCurrentStatePanel();
    if (localIPanelState != null) {
      localIPanelState.setID(paramID);
    }
    new CallStackBreaker(i, bool);
  }
  
  public void refresh()
  {
    if (mRootSelected == true) {
      updateZoneRoot(true);
    } else {
      updateZone(mSelectedID, true);
    }
    updatePntSet();
    updateStatePanel();
    mState = 0;
  }
  
  private void removeStatePanel(JComponent paramJComponent) {}
  
  public void rootSelected()
  {
    if (!updateZoneRoot(true)) {
      return;
    }
    mRootSelected = true;
    mSelectedID = null;
    updateZoneName(null);
    mIcon.setRoot();
    boolean bool = mList.setRoot();
    if (!bool)
    {
      PanelAbstract localPanelAbstract = getCurrentStatePanelAbst();
      removeStatePanel(localPanelAbstract);
      addStatePanel(mPanelPntNone);
      validate();
      mPanelPntNone.repaint();
    }
  }
  
  private void setDividerPosition()
  {
    int i = 30;
    if (isAncestorOf(mPanelState) == true)
    {
      i += PANEL_SIZEheight;
    }
    else if (isAncestorOf(mPanelStateVent) == true)
    {
      i += PANEL_SIZEheight;
    }
    else if (isAncestorOf(mPanelStateLight) == true)
    {
      i += PANEL_SIZEheight;
    }
    else if (isAncestorOf(mPanelStateSwitch) == true)
    {
      i += PANEL_SIZEheight;
    }
    else if (isAncestorOf(mPanelStateTab) == true)
    {
      i += PANEL_SIZEheight;
    }
    else
    {
      if (isAncestorOf(mPanelPntNone) == true)
      {
        mSplit.setDividerLocation(100.0D);
        return;
      }
      return;
    }
    i = mSplit.getHeight() - i;
    if (i <= 20) {
      mSplit.setDividerLocation(0.5D);
    } else {
      mSplit.setDividerLocation(i);
    }
  }
  
  public void statusUpdated()
  {
    if (mState == 2) {
      mState = 0;
    }
    mIcon.refresh();
    mList.refresh();
    updateStatePanel();
  }
  
  private void updatePntSet()
  {
    IPanelState localIPanelState = getCurrentStatePanel();
    if (localIPanelState == null)
    {
      mTimer.stop();
      mState = 0;
      return;
    }
    if (!localIPanelState.isChangeSetting())
    {
      localIPanelState.notifyEndSendOperation();
      mTimer.stop();
      mState = 0;
      return;
    }
    ID localID = localIPanelState.getCurrentPntID();
    PntSet localPntSet = localIPanelState.getCurrentPntSet();
    JobSetPntState localJobSetPntState = new JobSetPntState(localID, localPntSet);
    Result localResult = ThreadPntRequest.getInstance().addJob(localJobSetPntState);
    if (!CommonUse.waitJobNoDialog(localResult)) {}
    localIPanelState.notifyEndSendOperation();
    mTimer.stop();
    localIPanelState.resetChangeFlag();
    mMainToolBar.setRefreshEnabled(true);
    mState = 2;
  }
  
  private void updateStatePanel()
  {
    ID localID = getSelectedID();
    if (localID == null) {
      return;
    }
    if (mState != 0)
    {
      IPanelState localIPanelState = getCurrentStatePanel();
      if (localIPanelState == null) {
        notifyPntSelected(localID);
      } else if ((localIPanelState.getCurrentPntID() == null) || (!localIPanelState.getCurrentPntID().equals(localID))) {
        notifyPntSelected(localID);
      }
    }
    else
    {
      notifyPntSelected(localID);
    }
  }
  
  public boolean updateZone(ID paramID, boolean paramBoolean)
  {
    JobUpdateZoneIDs localJobUpdateZoneIDs = new JobUpdateZoneIDs(paramID, paramBoolean);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobUpdateZoneIDs);
    boolean bool = CommonUse.waitJob(localResult);
    if (localJobUpdateZoneIDs.isCommErrorOccured() == true) {
      bool = false;
    }
    return bool;
  }
  
  private void updateZoneName(ID paramID)
  {
    String str = StrRes.getStr("IDS_WEBMAIN_MONITOR_ZONE");
    if (paramID != null) {
      str = DataMgr.getInstance().getDetailName(paramID);
    }
    ImageIcon localImageIcon = IconRes.getIcon(119);
    mPanelDisplayType.setZoneName(localImageIcon, str);
  }
  
  public boolean updateZoneRoot(boolean paramBoolean)
  {
    JobUpdateZoneIDs localJobUpdateZoneIDs = new JobUpdateZoneIDs(paramBoolean);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobUpdateZoneIDs);
    boolean bool = CommonUse.waitJob(localResult);
    if (localJobUpdateZoneIDs.isCommErrorOccured() == true) {
      bool = false;
    }
    return bool;
  }
  
  public void zoneSelected(ID paramID)
  {
    if (!updateZone(paramID, true)) {
      return;
    }
    mRootSelected = false;
    mSelectedID = paramID;
    updateZoneName(paramID);
    mIcon.setZone(paramID);
    boolean bool = mList.setZone(paramID);
    if (!bool)
    {
      PanelAbstract localPanelAbstract = getCurrentStatePanelAbst();
      removeStatePanel(localPanelAbstract);
      addStatePanel(mPanelPntNone);
      validate();
      mPanelPntNone.repaint();
    }
  }
  
  class TimerListener
    implements ActionListener
  {
    TimerListener() {}
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      PanelMonitorSimple.this.updatePntSet();
    }
  }
  
  class CallStackBreaker
    implements ActionListener
  {
    private boolean mForce = false;
    private int mPreIndex = -1;
    private final Timer mTimer = new Timer(0, this);
    
    public CallStackBreaker(int paramInt, boolean paramBoolean)
    {
      mTimer.start();
      mPreIndex = paramInt;
      mForce = paramBoolean;
    }
    
    public void actionPerformed(ActionEvent paramActionEvent)
    {
      mTimer.stop();
      boolean bool = false;
      if (mPreIndex > mList.getSelectedRow()) {
        bool = true;
      } else {
        bool = false;
      }
      mList.moveRowFocus(mList.getSelectedRow(), bool);
      mIcon.moveRowFocusDerive(mIcon.getSelectedRow(), mPreIndex, mForce);
    }
  }
}
