package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.gui.common.ColorRes;
import webitc.gui.common.ItcIconInfo;
import webitc.gui.common.PanelAbstract;

public class PanelState
  extends PanelAbstract
  implements IPanelState, PanelOnOff.PanelOnOffListener, PanelDriveMode.PanelDriveModeListener, PanelProperties.PanelPropertiesListener
{
  public static final Dimension PANEL_SIZE = new Dimension(700, 180);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private boolean mChange = false;
  private ID mCurrentPntID = null;
  JLabel mLabel = new JLabel();
  private IPanelState.PanelStateListener mListener = null;
  JLabel mPadding = new JLabel();
  JLabel mPadding2 = new JLabel();
  PanelDriveMode mPanelDriveMode = new PanelDriveMode();
  PanelStateIcon mPanelIcon = new PanelStateIcon();
  PanelOnOff mPanelOnOff = new PanelOnOff();
  PanelProperties mPanelProperties = new PanelProperties();
  final JScrollPane mScroll = new JScrollPane();
  boolean mValidSetTemp = false;
  SimpleBar mVerticalBar = new SimpleBar();
  
  public PanelState()
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
    if (mPanelDriveMode.isChange() == true)
    {
      EnumDrvMode localEnumDrvMode = mPanelDriveMode.getCurrentState();
      mRunMode = localEnumDrvMode;
    }
    else
    {
      mRunMode = EnumDrvMode.ELSE;
    }
    if (mPanelProperties.isChangeTemp() == true)
    {
      float f = mPanelProperties.getCurrentTemp();
      if (!SystemInfo.isCentigrade()) {
        f = Temperature.convertFtoC(mPanelProperties.getCurrentTemp());
      }
      mSetTemp = new Temperature(true, f);
    }
    else
    {
      mSetTemp = new Temperature(false, 0.0F);
    }
    int i;
    if (mPanelProperties.isChangeVol() == true)
    {
      i = mPanelProperties.getCurrentVol();
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
    if (mPanelProperties.isChangeDir() == true)
    {
      mChangeWindDirect = true;
      i = mPanelProperties.getCurrentDir();
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
    mLabel.setPreferredSize(new Dimension(155, 20));
    mPanelIcon.setMinimumSize(new Dimension(170, 130));
    mPanelIcon.setPreferredSize(new Dimension(170, 130));
    mVerticalBar.setPreferredSize(new Dimension(3, 120));
    mVerticalBar.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mVerticalBar.setGraduation(false);
    add(mLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    add(mPanelIcon, new GridBagConstraints(0, 1, 1, 3, 0.0D, 0.0D, 17, 3, new Insets(5, 5, 5, 5), 0, 0));
    add(mVerticalBar, new GridBagConstraints(1, 0, 1, 4, 0.0D, 0.0D, 10, 3, new Insets(3, 0, 0, 0), 0, 0));
    add(mPanelOnOff, new GridBagConstraints(2, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(3, 5, 5, 5), 0, 0));
    add(mPanelDriveMode, new GridBagConstraints(2, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 5, 3, 10), 0, 0));
    add(mPanelProperties, new GridBagConstraints(2, 2, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 5, 0, 10), 0, 0));
    add(mPadding2, new GridBagConstraints(0, 4, 2, 1, 0.0D, 1.0D, 10, 3, new Insets(0, 0, 0, 0), 0, 0));
    add(mPadding, new GridBagConstraints(2, 3, 1, 2, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPanelOnOff.setListener(this);
    mPanelDriveMode.setListener(this);
    mPanelProperties.setListener(this);
    mScroll.getViewport().setView(this);
    mScroll.setPreferredSize(PANEL_SIZE);
  }
  
  public void notifyAuto()
  {
    updateIconState();
    update();
  }
  
  public void notifyChangeDir(int paramInt)
  {
    updateIconState();
    update();
  }
  
  public void notifyChangeTemp(int paramInt)
  {
    update();
  }
  
  public void notifyChangeVol(int paramInt)
  {
    updateIconState();
    update();
  }
  
  public void notifyCool()
  {
    updateIconState();
    update();
  }
  
  public void notifyEndSendOperation()
  {
    mPanelOnOff.endRemocon();
  }
  
  public void notifyFan()
  {
    updateIconState();
    update();
  }
  
  public void notifyHeat()
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
  
  public void notifySubmit()
  {
    updateIconState();
    update();
  }
  
  public void resetChangeFlag()
  {
    mChange = false;
    mPanelOnOff.resetChangeFlag();
    mPanelDriveMode.resetChangeFlag();
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
    if ((fValidRunMode & 0x4) != 0) {
      mPanelDriveMode.setEnabled(true, 1);
    } else {
      mPanelDriveMode.setEnabled(false, 1);
    }
    if ((fValidRunMode & 0x2) != 0) {
      mPanelDriveMode.setEnabled(true, 2);
    } else {
      mPanelDriveMode.setEnabled(false, 2);
    }
    if ((fValidRunMode & 0x1) != 0) {
      mPanelDriveMode.setEnabled(true, 4);
    } else {
      mPanelDriveMode.setEnabled(false, 4);
    }
    if (((fValidRunMode & 0x200) != 0) || ((fValidRunMode & 0x100) != 0)) {
      mPanelDriveMode.setEnabled(true, 8);
    } else {
      mPanelDriveMode.setEnabled(false, 8);
    }
    if ((fValidRunMode & 0x10) != 0) {
      mPanelDriveMode.setEnabled(true, 16);
    } else {
      mPanelDriveMode.setEnabled(false, 16);
    }
    mPanelDriveMode.setCurrentState(mDrvMode);
    if (fValidSetTemp == true)
    {
      mPanelProperties.setEnabled(true, 1);
      if (SystemInfo.isCentigrade() == true)
      {
        Temperature localTemperature1 = localPntTarget.getSetTempLower();
        Temperature localTemperature2 = localPntTarget.getSetTempUpper();
        k = localTemperature1.getCentigradeInteger();
        if ((k > 0) && (localTemperature1.getCentigradeDecimal() != 0)) {
          k++;
        }
        int m = localTemperature2.getCentigradeInteger();
        if ((m < 0) && (localTemperature2.getCentigradeDecimal() != 0)) {
          m--;
        }
        mPanelProperties.setTempLimit(k, m);
      }
      else
      {
        i = localPntTarget.getSetTempLower().getFahrenheit();
        j = localPntTarget.getSetTempUpper().getFahrenheit();
        mPanelProperties.setTempLimit(i, j);
      }
    }
    else
    {
      mPanelProperties.setEnabled(false, 1);
    }
    if (mPanelDriveMode.getCurrentState() == EnumDrvMode.FAN) {
      mPanelProperties.setEnabled(false, 1);
    }
    mValidSetTemp = fValidSetTemp;
    int i = SimpleCommon.getSetTemp(paramID);
    if (fValidWindVolume == 0) {
      mPanelProperties.setEnabled(false, 4);
    } else {
      mPanelProperties.setEnabled(true, 4);
    }
    int j = SimpleCommon.getVol(mWindVolume);
    if (!fValidWindDirect) {
      mPanelProperties.setEnabled(false, 2);
    } else {
      mPanelProperties.setEnabled(true, 2);
    }
    int k = SimpleCommon.getFanDir(mWindDirect);
    mPanelProperties.setCurrentState(i, j, k);
    updateIconState();
  }
  
  public void setListener(IPanelState.PanelStateListener paramPanelStateListener)
  {
    mListener = paramPanelStateListener;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    mScroll.setVisible(paramBoolean);
    mPanelOnOff.setVisible(paramBoolean);
    mPanelIcon.setVisible(paramBoolean);
    mPanelProperties.setVisible(paramBoolean);
  }
  
  public void update()
  {
    mChange = true;
    if (mPanelDriveMode.getCurrentState() == EnumDrvMode.FAN) {
      mPanelProperties.setEnabled(false, 1);
    } else if (mValidSetTemp == true) {
      mPanelProperties.setEnabled(true, 1);
    }
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
      EnumDrvMode localEnumDrvMode = mPanelDriveMode.getCurrentState();
      i = SimpleCommon.getDrvMode(localEnumDrvMode);
    }
    int j = mPanelProperties.getCurrentDir();
    int k = mPanelProperties.getCurrentVol();
    int m = SimpleCommon.getState(mCurrentPntID);
    mPanelIcon.setIconState(m, i, k, j);
  }
  
  private void updateSign(ID paramID)
  {
    String str = SimpleCommon.getRoomTempStrNoUnit(paramID);
    mPanelIcon.setRoomTemp(str);
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
