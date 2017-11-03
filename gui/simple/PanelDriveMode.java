package webitc.gui.simple;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumDrvMode;
import webitc.gui.common.ColorRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.SimpleButton;
import webitc.gui.common.SimpleButtonRes;

public class PanelDriveMode
  extends PanelAbstract
{
  public static final int BTN_ALL = 31;
  public static final int BTN_AUTO = 8;
  public static final int BTN_COOL = 1;
  public static final int BTN_FAN = 4;
  public static final int BTN_HEAT = 2;
  public static final int BTN_SUBMIT = 16;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  SimpleBar mBar = new SimpleBar();
  SimpleButton mBtnAuto = SimpleButtonRes.getButton(6);
  SimpleButton mBtnCool = SimpleButtonRes.getButton(4);
  SimpleButton mBtnFan = SimpleButtonRes.getButton(5);
  SimpleButton mBtnHeat = SimpleButtonRes.getButton(7);
  SimpleButton mBtnSubmit = SimpleButtonRes.getButton(8);
  boolean mChange = false;
  private PanelDriveModeListener mListener = null;
  JLabel mPadding = new JLabel();
  private EnumDrvMode mState = EnumDrvMode.COOL;
  
  public PanelDriveMode()
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
  
  public EnumDrvMode getCurrentState()
  {
    return mState;
  }
  
  public boolean isChange()
  {
    return mChange;
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBackground(ColorRes.SIMPLE_MODE_BG);
    mBar.setText(StrRes.getStr("IDS_COMMON_DRVMODE"));
    mBar.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mBar.setShadow(true);
    mBtnCool.addActionListener(new PanelDriveMode_mBtnCool_actionAdapter(this));
    mBtnFan.addActionListener(new PanelDriveMode_mBtnFan_actionAdapter(this));
    mBtnAuto.addActionListener(new PanelDriveMode_mBtnAuto_actionAdapter(this));
    mBtnHeat.addActionListener(new PanelDriveMode_mBtnHeat_actionAdapter(this));
    mPadding.setText("");
    mBtnSubmit.addActionListener(new PanelDriveMode_mBtnSubmit_actionAdapter(this));
    add(mBar, new GridBagConstraints(0, 0, 6, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 5, 0), 0, 0));
    add(mBtnCool, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mBtnFan, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mBtnAuto, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mBtnHeat, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mBtnSubmit, new GridBagConstraints(4, 1, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mPadding, new GridBagConstraints(5, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  void mBtnAuto_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getDrvMode(getCurrentState()) == 8200) {
      return;
    }
    setCurrentState(EnumDrvMode.AUTOCOOL);
    notifyToListener();
  }
  
  void mBtnCool_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getDrvMode(getCurrentState()) == 8194) {
      return;
    }
    setCurrentState(EnumDrvMode.COOL);
    notifyToListener();
  }
  
  void mBtnFan_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getDrvMode(getCurrentState()) == 8196) {
      return;
    }
    setCurrentState(EnumDrvMode.FAN);
    notifyToListener();
  }
  
  void mBtnHeat_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getDrvMode(getCurrentState()) == 8193) {
      return;
    }
    setCurrentState(EnumDrvMode.HEAT);
    notifyToListener();
  }
  
  void mBtnSubmit_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getDrvMode(getCurrentState()) == 8208) {
      return;
    }
    setCurrentState(EnumDrvMode.SUBMIT);
    notifyToListener();
  }
  
  private void notifyToListener()
  {
    mChange = true;
    if (mListener == null) {
      return;
    }
    if (mState == EnumDrvMode.HEAT) {
      mListener.notifyHeat();
    } else if (mState == EnumDrvMode.COOL) {
      mListener.notifyCool();
    } else if (mState == EnumDrvMode.FAN) {
      mListener.notifyFan();
    } else if ((mState == EnumDrvMode.AUTOCOOL) || (mState == EnumDrvMode.AUTOHEAT)) {
      mListener.notifyAuto();
    } else if (mState == EnumDrvMode.SUBMIT) {
      mListener.notifySubmit();
    }
  }
  
  public void resetChangeFlag()
  {
    mChange = false;
  }
  
  public void setCurrentState(EnumDrvMode paramEnumDrvMode)
  {
    mChange = false;
    mState = paramEnumDrvMode;
    mBtnCool.setSelected(false);
    mBtnHeat.setSelected(false);
    mBtnAuto.setSelected(false);
    mBtnFan.setSelected(false);
    mBtnSubmit.setSelected(false);
    if ((paramEnumDrvMode == EnumDrvMode.COOL) || (paramEnumDrvMode == EnumDrvMode.DRY))
    {
      if ((!mBtnCool.isEnabled()) && (mBtnSubmit.isEnabled() == true))
      {
        mBtnSubmit.setSelected(true);
        mState = EnumDrvMode.SUBMIT;
      }
      else
      {
        mBtnCool.setSelected(true);
      }
    }
    else if (paramEnumDrvMode == EnumDrvMode.HEAT)
    {
      if ((!mBtnHeat.isEnabled()) && (mBtnSubmit.isEnabled() == true))
      {
        mBtnSubmit.setSelected(true);
        mState = EnumDrvMode.SUBMIT;
      }
      else
      {
        mBtnHeat.setSelected(true);
      }
    }
    else if ((paramEnumDrvMode == EnumDrvMode.AUTOHEAT) || (paramEnumDrvMode == EnumDrvMode.AUTOCOOL)) {
      mBtnAuto.setSelected(true);
    } else if (paramEnumDrvMode == EnumDrvMode.FAN) {
      mBtnFan.setSelected(true);
    } else if (paramEnumDrvMode == EnumDrvMode.SUBMIT) {
      mBtnSubmit.setSelected(true);
    }
  }
  
  public void setEnabled(boolean paramBoolean, int paramInt)
  {
    if ((paramInt & 0x1) != 0) {
      mBtnCool.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x2) != 0) {
      mBtnHeat.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x4) != 0) {
      mBtnFan.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x8) != 0) {
      mBtnAuto.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x10) != 0) {
      mBtnSubmit.setEnabled(paramBoolean);
    }
  }
  
  public void setListener(PanelDriveModeListener paramPanelDriveModeListener)
  {
    mListener = paramPanelDriveModeListener;
  }
  
  public static abstract interface PanelDriveModeListener
  {
    public abstract void notifyAuto();
    
    public abstract void notifyCool();
    
    public abstract void notifyFan();
    
    public abstract void notifyHeat();
    
    public abstract void notifySubmit();
  }
}
