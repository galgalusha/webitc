package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumDrvMode;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntTarget;
import webitc.gui.common.ColorRes;
import webitc.gui.common.ItcIconInfo;
import webitc.gui.common.PanelAbstract;

public class TabInner
  extends PanelAbstract
  implements PanelDriveMode.PanelDriveModeListener, PanelProperties.PanelPropertiesListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private int mCurrentErr = 0;
  private int mCurrentOnOff = 4096;
  private ITabInner mListener = null;
  JLabel mPadding = new JLabel();
  PanelDriveMode mPanelDriveMode = new PanelDriveMode();
  PanelStateIcon mPanelIcon = new PanelStateIcon();
  PanelProperties mPanelProperties = new PanelProperties();
  boolean mValidSetTemp = false;
  SimpleBar mVerticalBar = new SimpleBar();
  
  public TabInner()
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
  
  public int getCurrentDir()
  {
    return mPanelProperties.getCurrentDir();
  }
  
  public EnumDrvMode getCurrentDriveMode()
  {
    return mPanelDriveMode.getCurrentState();
  }
  
  public float getCurrentTemp()
  {
    if (SystemInfo.isCentigrade() == true) {
      return mPanelProperties.getCurrentTemp();
    }
    return Temperature.convertFtoC(mPanelProperties.getCurrentTemp());
  }
  
  public int getCurrentVol()
  {
    return mPanelProperties.getCurrentVol();
  }
  
  public boolean isChangeDir()
  {
    return mPanelProperties.isChangeDir();
  }
  
  public boolean isChangeDriveMode()
  {
    return mPanelDriveMode.isChange();
  }
  
  public boolean isChangeTemp()
  {
    return mPanelProperties.isChangeTemp();
  }
  
  public boolean isChangeVol()
  {
    return mPanelProperties.isChangeVol();
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBackground(ColorRes.SIMPLE_MODE_BG);
    setPreferredSize(new Dimension(550, 160));
    mPanelIcon.setMinimumSize(new Dimension(170, 120));
    mPanelIcon.setPreferredSize(new Dimension(170, 120));
    mVerticalBar.setPreferredSize(new Dimension(3, 120));
    mVerticalBar.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mVerticalBar.setGraduation(false);
    mPadding.setText("");
    add(mPanelIcon, new GridBagConstraints(0, 0, 1, 2, 0.0D, 0.0D, 17, 3, new Insets(5, 5, 5, 5), 0, 0));
    add(mVerticalBar, new GridBagConstraints(1, 0, 1, 2, 0.0D, 0.0D, 10, 3, new Insets(5, 0, 0, 0), 0, 0));
    add(mPanelDriveMode, new GridBagConstraints(2, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(5, 5, 3, 10), 0, 0));
    add(mPanelProperties, new GridBagConstraints(2, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 5, 0, 10), 0, 0));
    add(mPadding, new GridBagConstraints(0, 2, 3, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPanelDriveMode.setListener(this);
    mPanelProperties.setListener(this);
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
  
  public void notifyChangeOnOff(int paramInt)
  {
    mCurrentOnOff = paramInt;
    updateIconState();
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
  
  public void notifySubmit()
  {
    updateIconState();
    update();
  }
  
  private void notifyToListener()
  {
    if (mListener != null) {
      mListener.notifyChangeSetting();
    }
  }
  
  public void resetChangeFlag()
  {
    mPanelDriveMode.resetChangeFlag();
    mPanelProperties.resetChangeFlag();
  }
  
  public void setAnimationVisible(boolean paramBoolean)
  {
    mPanelIcon.setVisible(paramBoolean);
    mPanelProperties.setVisible(paramBoolean);
  }
  
  public void setCurrent(ID paramID)
  {
    updateSign(paramID);
    PntTarget localPntTarget = DataMgr.getInstance().getPntTarget(paramID, false);
    PntCurrent localPntCurrent = DataMgr.getInstance().getPntCurrent(paramID);
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
  
  public void setListener(ITabInner paramITabInner)
  {
    mListener = paramITabInner;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    mPanelIcon.setVisible(paramBoolean);
    mPanelProperties.setVisible(paramBoolean);
  }
  
  public void update()
  {
    if (mPanelDriveMode.getCurrentState() == EnumDrvMode.FAN) {
      mPanelProperties.setEnabled(false, 1);
    } else if (mValidSetTemp == true) {
      mPanelProperties.setEnabled(true, 1);
    }
    notifyToListener();
  }
  
  private void updateIconState()
  {
    int i = 4096;
    if (mCurrentOnOff == 8192)
    {
      EnumDrvMode localEnumDrvMode = mPanelDriveMode.getCurrentState();
      i = SimpleCommon.getDrvMode(localEnumDrvMode);
    }
    int j = mPanelProperties.getCurrentDir();
    int k = mPanelProperties.getCurrentVol();
    mPanelIcon.setIconState(mCurrentErr, i, k, j);
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
