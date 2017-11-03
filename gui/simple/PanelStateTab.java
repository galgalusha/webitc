package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JViewport;
import webitc.common.StrRes;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;

public class PanelStateTab
  extends PanelAbstract
  implements IPanelState, PanelOnOff.PanelOnOffListener, ITabInner
{
  public static final Dimension PANEL_SIZE = new Dimension(770, 230);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private boolean mChange = false;
  private ID mCurrentPntID = null;
  JLabel mLabel = new JLabel();
  private IPanelState.PanelStateListener mListener = null;
  PanelOnOff mPanelOnOff = new PanelOnOff();
  private final JScrollPane mScroll = new JScrollPane();
  JTabbedPane mTab = new JTabbedPane();
  TabInner mTabInner = new TabInner();
  TabInnerVent mTabInnerVent = new TabInnerVent();
  
  public PanelStateTab()
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
  
  public ID getCurrentPntID()
  {
    return mCurrentPntID;
  }
  
  public PntSet getCurrentPntSet()
  {
    PntSet localPntSet = new PntSet();
    if (mPanelOnOff.isChange() == true)
    {
      if (mPanelOnOff.getCurrentState() == 8192) {
        mOnOffMode = EnumPntStat.ON;
      } else if (mPanelOnOff.getCurrentState() == 4096) {
        mOnOffMode = EnumPntStat.OFF;
      } else {
        mOnOffMode = EnumPntStat.ELSE;
      }
    }
    else {
      mOnOffMode = EnumPntStat.ELSE;
    }
    if (mTab.indexOfComponent(mTabInner) != -1)
    {
      if (mTabInner.isChangeDriveMode() == true)
      {
        EnumDrvMode localEnumDrvMode = mTabInner.getCurrentDriveMode();
        mRunMode = localEnumDrvMode;
      }
      else
      {
        mRunMode = EnumDrvMode.ELSE;
      }
      if (mTabInner.isChangeTemp() == true) {
        mSetTemp = new Temperature(true, mTabInner.getCurrentTemp());
      } else {
        mSetTemp = new Temperature(false, 0.0F);
      }
      int i;
      if (mTabInner.isChangeVol() == true)
      {
        i = mTabInner.getCurrentVol();
        if (i == 0)
        {
          mChangeWindVolume = true;
          mWindVolume = 0;
        }
        else if (i == 1)
        {
          mChangeWindVolume = true;
          mWindVolume = 2;
        }
        else
        {
          mChangeWindVolume = false;
        }
      }
      else
      {
        mChangeWindVolume = false;
      }
      if (mTabInner.isChangeDir() == true)
      {
        mChangeWindDirect = true;
        i = mTabInner.getCurrentDir();
        if (i == 5) {
          mWindDirect = 7;
        } else {
          mWindDirect = i;
        }
      }
      else
      {
        mChangeWindDirect = false;
      }
    }
    if (mTab.indexOfComponent(mTabInnerVent) != -1)
    {
      Object localObject;
      if (mTabInnerVent.isChangeVentMode() == true)
      {
        localObject = mTabInnerVent.getCurrentVentMode();
        mVentMode = ((EnumVentMode)localObject);
      }
      else
      {
        mVentMode = EnumVentMode.ELSE;
      }
      if (mTabInnerVent.isChangeVol() == true)
      {
        localObject = mTabInnerVent.getCurrentVol();
        mVentVol = ((EnumVentVol)localObject);
      }
      else
      {
        mVentVol = EnumVentVol.ELSE;
      }
    }
    return localPntSet;
  }
  
  public Dimension getPreferredSize()
  {
    return PANEL_SIZE;
  }
  
  public JScrollPane getScrollPane()
  {
    return mScroll;
  }
  
  public boolean isChangeSetting()
  {
    return mChange;
  }
  
  private boolean isIncludeAc(PntTarget paramPntTarget)
  {
    if ((fValidRunMode != 0) && (fValidRunMode != EnumDrvMode.ELSE.getEnumValue())) {
      return true;
    }
    if (fValidSetTemp == true) {
      return true;
    }
    if (fValidWindDirect == true) {
      return true;
    }
    return fValidWindVolume != 0;
  }
  
  private boolean isIncludeVent(PntTarget paramPntTarget)
  {
    if ((fValidVentMode != 0) && (fValidVentMode != EnumVentMode.ELSE.getEnumValue())) {
      return true;
    }
    return (fValidVentVol != 0) && (fValidVentVol != EnumVentVol.ELSE.getEnumValue());
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBackground(ColorRes.SIMPLE_MODE_BG);
    setPreferredSize(PANEL_SIZE);
    mLabel.setPreferredSize(new Dimension(155, 20));
    mLabel.setMinimumSize(new Dimension(155, 20));
    add(mLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    add(mPanelOnOff, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(3, 5, 5, 5), 0, 0));
    add(mTab, new GridBagConstraints(0, 1, 2, 1, 1.0D, 1.0D, 17, 1, new Insets(3, 5, 5, 5), 0, 0));
    mPanelOnOff.setListener(this);
    mTabInner.setListener(this);
    mTabInnerVent.setListener(this);
    mScroll.getViewport().setView(this);
  }
  
  public void notifyChangeSetting()
  {
    mChange = true;
    if (mListener != null)
    {
      mListener.notifyOperation();
      mPanelOnOff.beginRemocon();
    }
  }
  
  public void notifyEndSendOperation()
  {
    mPanelOnOff.endRemocon();
  }
  
  public void notifyOff()
  {
    notifyChangeSetting();
    notifyToTab(4096);
  }
  
  public void notifyOn()
  {
    notifyChangeSetting();
    notifyToTab(8192);
  }
  
  private void notifyToTab(int paramInt)
  {
    if (mTabInner != null) {
      mTabInner.notifyChangeOnOff(paramInt);
    }
    if (mTabInnerVent != null) {
      mTabInnerVent.notifyChangeOnOff(paramInt);
    }
  }
  
  public void resetChangeFlag()
  {
    mChange = false;
    mPanelOnOff.resetChangeFlag();
    if (mTabInner != null) {
      mTabInner.resetChangeFlag();
    }
    if (mTabInnerVent != null) {
      mTabInnerVent.resetChangeFlag();
    }
  }
  
  public void setID(ID paramID)
  {
    int i = mTab.getSelectedIndex();
    if ((mCurrentPntID == null) || (!mCurrentPntID.equals(paramID))) {
      i = -1;
    }
    mCurrentPntID = paramID;
    mChange = false;
    String str = DataMgr.getInstance().getDetailName(paramID);
    mLabel.setText(str);
    mLabel.setToolTipText(str);
    mTab.removeAll();
    PntTarget localPntTarget = DataMgr.getInstance().getPntTarget(paramID, false);
    PntState localPntState = DataMgr.getInstance().getState(paramID);
    if (isIncludeAc(localPntTarget) == true)
    {
      mTabInner.setCurrent(paramID);
      mTab.addTab(StrRes.getStr("IDS_COMMON_PNT_TYPE_AC"), IconRes.getIcon(93), mTabInner);
    }
    if (isIncludeVent(localPntTarget) == true)
    {
      mTabInnerVent.setCurrent(paramID, localPntTarget);
      mTab.addTab(StrRes.getStr("IDS_COMMON_VENT"), IconRes.getIcon(145), mTabInnerVent);
    }
    if (!fValidON) {
      mPanelOnOff.setEnabled(false, 1);
    } else {
      mPanelOnOff.setEnabled(true, 1);
    }
    if (!fValidOFF) {
      mPanelOnOff.setEnabled(false, 2);
    } else {
      mPanelOnOff.setEnabled(true, 2);
    }
    int j = SimpleCommon.getOnOff(fOnOffState);
    mPanelOnOff.setCurrentState(j);
    notifyToTab(j);
    if ((i >= 0) && (i < mTab.getTabCount())) {
      mTab.setSelectedIndex(i);
    }
    if (mTab.getTabCount() == 0) {}
  }
  
  public void setListener(IPanelState.PanelStateListener paramPanelStateListener)
  {
    mListener = paramPanelStateListener;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    mPanelOnOff.setVisible(paramBoolean);
    mScroll.setVisible(paramBoolean);
    if (mTab.getSelectedComponent() == mTabInner) {
      mTabInner.setAnimationVisible(paramBoolean);
    }
  }
}
