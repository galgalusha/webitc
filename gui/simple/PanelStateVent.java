package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.gui.common.ColorRes;
import webitc.gui.common.ItcIconInfo;
import webitc.gui.common.PanelAbstract;

public class PanelStateVent
  extends PanelAbstract
  implements IPanelState, PanelOnOff.PanelOnOffListener, PanelVentMode.PanelVentModeListener, PanelVentProperties.PanelPropertiesListener
{
  public static final Dimension PANEL_SIZE = new Dimension(750, 180);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  boolean mChange = false;
  ID mCurrentPntID = null;
  JLabel mLabel = new JLabel();
  private IPanelState.PanelStateListener mListener = null;
  JLabel mPadding = new JLabel();
  PanelVentStateIcon mPanelIcon = new PanelVentStateIcon();
  PanelOnOff mPanelOnOff = new PanelOnOff();
  PanelVentProperties mPanelProperties = new PanelVentProperties();
  PanelVentMode mPanelVentMode = new PanelVentMode();
  private final JScrollPane mScroll = new JScrollPane();
  SimpleBar mVerticalBar = new SimpleBar();
  
  public PanelStateVent()
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
    if (mPanelVentMode.isChange() == true)
    {
      EnumVentMode localEnumVentMode = mPanelVentMode.getCurrentState();
      mVentMode = localEnumVentMode;
    }
    else
    {
      mVentMode = EnumVentMode.ELSE;
    }
    int i;
    boolean bool;
    if ((mPanelProperties.isChangeVol() == true) || (mPanelProperties.isChangeFreshUp() == true))
    {
      i = mPanelProperties.getCurrentVol();
      bool = mPanelProperties.getCurrentFreshUp();
    }
    switch (i)
    {
    case 0: 
      if (bool == true) {
        mVentVol = EnumVentVol.FRESH_LOW;
      } else {
        mVentVol = EnumVentVol.NORMAL_LOW;
      }
      break;
    case 1: 
      if (bool == true) {
        mVentVol = EnumVentVol.FRESH_HIGH;
      } else {
        mVentVol = EnumVentVol.NORMAL_HIGH;
      }
      break;
    case 2: 
      if (bool == true) {
        mVentVol = EnumVentVol.FRESH_AUTO;
      } else {
        mVentVol = EnumVentVol.NORMAL_AUTO;
      }
      break;
    default: 
      mVentVol = EnumVentVol.ELSE;
      break;
      mVentVol = EnumVentVol.ELSE;
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
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBackground(ColorRes.SIMPLE_MODE_BG);
    setPreferredSize(PANEL_SIZE);
    mLabel.setPreferredSize(new Dimension(150, 20));
    mPanelIcon.setMinimumSize(new Dimension(170, 120));
    mPanelIcon.setPreferredSize(new Dimension(170, 120));
    mVerticalBar.setPreferredSize(new Dimension(3, 120));
    mVerticalBar.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mVerticalBar.setGraduation(false);
    add(mLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    add(mPanelIcon, new GridBagConstraints(0, 1, 1, 2, 0.0D, 0.0D, 17, 3, new Insets(5, 5, 5, 5), 0, 0));
    add(mVerticalBar, new GridBagConstraints(1, 0, 1, 4, 0.0D, 0.0D, 10, 3, new Insets(3, 0, 0, 0), 0, 0));
    add(mPanelOnOff, new GridBagConstraints(2, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(3, 5, 5, 5), 0, 0));
    add(mPanelVentMode, new GridBagConstraints(2, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 5, 3, 10), 0, 0));
    add(mPanelProperties, new GridBagConstraints(2, 2, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 5, 0, 10), 0, 0));
    add(mPadding, new GridBagConstraints(0, 4, 3, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPanelOnOff.setListener(this);
    mPanelVentMode.setListener(this);
    mPanelProperties.setListener(this);
    mScroll.getViewport().setView(this);
    mScroll.setPreferredSize(PANEL_SIZE);
  }
  
  public void notifyAutoVent()
  {
    updateIconState();
    update();
  }
  
  public void notifyChangeFreshUp(boolean paramBoolean)
  {
    updateIconState();
    update();
  }
  
  public void notifyChangeVol(int paramInt)
  {
    updateIconState();
    update();
  }
  
  public void notifyEndSendOperation()
  {
    mPanelOnOff.endRemocon();
  }
  
  public void notifyNormalVent()
  {
    updateIconState();
    update();
  }
  
  public void notifyOff()
  {
    updateIconState();
    update();
  }
  
  public void notifyOn()
  {
    updateIconState();
    update();
  }
  
  public void notifyTotalVent()
  {
    updateIconState();
    update();
  }
  
  public void resetChangeFlag()
  {
    mChange = false;
    mPanelOnOff.resetChangeFlag();
    mPanelVentMode.resetChangeFlag();
    mPanelProperties.resetChangeFlag();
  }
  
  public void setID(ID paramID)
  {
    mCurrentPntID = paramID;
    mChange = false;
    String str = DataMgr.getInstance().getDetailName(paramID);
    mLabel.setText(str);
    mLabel.setToolTipText(str);
    updateSign(paramID);
    PntTarget localPntTarget = DataMgr.getInstance().getPntTarget(paramID, false);
    PntCurrent localPntCurrent = DataMgr.getInstance().getPntCurrent(paramID);
    PntState localPntState = DataMgr.getInstance().getState(paramID);
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
    mPanelOnOff.setCurrentState(SimpleCommon.getOnOff(fOnOffState));
    if ((fValidVentMode & 0x1) != 0) {
      mPanelVentMode.setEnabled(true, 2);
    } else {
      mPanelVentMode.setEnabled(false, 2);
    }
    if ((fValidVentMode & 0x2) != 0) {
      mPanelVentMode.setEnabled(true, 1);
    } else {
      mPanelVentMode.setEnabled(false, 1);
    }
    if ((fValidVentMode & 0x4) != 0) {
      mPanelVentMode.setEnabled(true, 4);
    } else {
      mPanelVentMode.setEnabled(false, 4);
    }
    mPanelVentMode.setCurrentState(mVentMode);
    int i = SimpleCommon.getVentVol(mVentVol);
    boolean bool = SimpleCommon.getVentFreshUp(mVentVol);
    mPanelProperties.setValidVentVol(fValidVentVol);
    mPanelProperties.setCurrentState(i, bool);
    updateIconState();
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
    mPanelIcon.setVisible(paramBoolean);
    mPanelProperties.setVisible(paramBoolean);
  }
  
  public void update()
  {
    mChange = true;
    if (mListener != null)
    {
      mListener.notifyOperation();
      mPanelOnOff.beginRemocon();
    }
  }
  
  private void updateIconState()
  {
    int i = 4096;
    if (mPanelOnOff.getCurrentState() == 8192)
    {
      EnumVentMode localEnumVentMode = mPanelVentMode.getCurrentState();
      i = SimpleCommon.getVentMode(localEnumVentMode);
    }
    int j = mPanelProperties.getCurrentVol();
    boolean bool = mPanelProperties.getCurrentFreshUp();
    int k = SimpleCommon.getState(mCurrentPntID);
    mPanelIcon.setIconState(k, i, j, bool);
  }
  
  private void updateSign(ID paramID)
  {
    ItcIconInfo localItcIconInfo = DataMgr.getInstance().getIconInfo(paramID);
    if ((fIconAppend & 0x2) != 0) {
      mPanelIcon.setControlSign(true);
    } else {
      mPanelIcon.setControlSign(false);
    }
    if ((fIconAppend & 0x4) != 0) {
      mPanelIcon.setCtrlTargetSign(true);
    } else {
      mPanelIcon.setCtrlTargetSign(false);
    }
    if ((fIconAppend & 0x8) != 0) {
      mPanelIcon.setFilterSign(true);
    } else {
      mPanelIcon.setFilterSign(false);
    }
  }
}
