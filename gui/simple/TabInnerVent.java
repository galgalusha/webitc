package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntTarget;
import webitc.gui.common.ColorRes;
import webitc.gui.common.ItcIconInfo;
import webitc.gui.common.PanelAbstract;

public class TabInnerVent
  extends PanelAbstract
  implements PanelVentMode.PanelVentModeListener, PanelVentProperties.PanelPropertiesListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private int mCurrentErr = 0;
  private int mCurrentOnOff = 4096;
  private ITabInner mListener = null;
  JLabel mPadding1 = new JLabel();
  PanelVentStateIcon mPanelIcon = new PanelVentStateIcon();
  PanelVentProperties mPanelProperties = new PanelVentProperties();
  PanelVentMode mPanelVentMode = new PanelVentMode();
  SimpleBar mVerticalBar = new SimpleBar();
  
  public TabInnerVent()
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
  
  public EnumVentMode getCurrentVentMode()
  {
    return mPanelVentMode.getCurrentState();
  }
  
  public EnumVentVol getCurrentVol()
  {
    EnumVentVol localEnumVentVol = EnumVentVol.ELSE;
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
        localEnumVentVol = EnumVentVol.FRESH_LOW;
      } else {
        localEnumVentVol = EnumVentVol.NORMAL_LOW;
      }
      break;
    case 1: 
      if (bool == true) {
        localEnumVentVol = EnumVentVol.FRESH_HIGH;
      } else {
        localEnumVentVol = EnumVentVol.NORMAL_HIGH;
      }
      break;
    case 2: 
      if (bool == true) {
        localEnumVentVol = EnumVentVol.FRESH_AUTO;
      } else {
        localEnumVentVol = EnumVentVol.NORMAL_AUTO;
      }
      break;
    default: 
      localEnumVentVol = EnumVentVol.ELSE;
      break;
      localEnumVentVol = EnumVentVol.ELSE;
    }
    return localEnumVentVol;
  }
  
  public boolean isChangeVentMode()
  {
    return mPanelVentMode.isChange();
  }
  
  public boolean isChangeVol()
  {
    return (mPanelProperties.isChangeVol()) || (mPanelProperties.isChangeFreshUp());
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
    add(mPanelIcon, new GridBagConstraints(0, 0, 1, 3, 0.0D, 0.0D, 17, 3, new Insets(5, 5, 5, 5), 0, 0));
    add(mVerticalBar, new GridBagConstraints(1, 0, 1, 3, 0.0D, 0.0D, 10, 3, new Insets(5, 0, 0, 0), 0, 0));
    add(mPanelVentMode, new GridBagConstraints(2, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(5, 5, 3, 10), 0, 0));
    add(mPanelProperties, new GridBagConstraints(2, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 5, 0, 10), 0, 0));
    add(mPadding1, new GridBagConstraints(0, 3, 3, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPanelVentMode.setListener(this);
    mPanelProperties.setListener(this);
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
  
  public void notifyChangeOnOff(int paramInt)
  {
    mCurrentOnOff = paramInt;
    updateIconState();
  }
  
  public void notifyChangeVol(int paramInt)
  {
    updateIconState();
    update();
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
    mPanelVentMode.resetChangeFlag();
    mPanelProperties.resetChangeFlag();
  }
  
  public void setCurrent(ID paramID, PntTarget paramPntTarget)
  {
    updateSign(paramID);
    PntCurrent localPntCurrent = DataMgr.getInstance().getPntCurrent(paramID);
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
    if (mListener != null) {
      mListener.notifyChangeSetting();
    }
  }
  
  private void updateIconState()
  {
    int i = 4096;
    if (mCurrentOnOff == 8192)
    {
      EnumVentMode localEnumVentMode = mPanelVentMode.getCurrentState();
      i = SimpleCommon.getVentMode(localEnumVentMode);
    }
    int j = mPanelProperties.getCurrentVol();
    boolean bool = mPanelProperties.getCurrentFreshUp();
    mPanelIcon.setIconState(mCurrentErr, i, j, bool);
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
