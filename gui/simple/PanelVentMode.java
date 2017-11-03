package webitc.gui.simple;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumVentMode;
import webitc.gui.common.ColorRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.SimpleButton;
import webitc.gui.common.SimpleButtonRes;

public class PanelVentMode
  extends PanelAbstract
{
  public static final int BTN_ALL = 7;
  public static final int BTN_AUTO = 2;
  public static final int BTN_NORMAL = 4;
  public static final int BTN_TOTAL = 1;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  SimpleBar mBar = new SimpleBar();
  SimpleButton mBtnAuto = SimpleButtonRes.getButton(16);
  SimpleButton mBtnNormal = SimpleButtonRes.getButton(18);
  SimpleButton mBtnTotal = SimpleButtonRes.getButton(17);
  private boolean mChange = false;
  private PanelVentModeListener mListener = null;
  JLabel mPadding = new JLabel();
  private EnumVentMode mState = EnumVentMode.ELSE;
  
  public PanelVentMode()
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
  
  public EnumVentMode getCurrentState()
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
    mBar.setText(StrRes.getStr("IDS_COMMON_VENT_MODE"));
    mBar.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mBar.setShadow(true);
    mBtnAuto.addActionListener(new PanelVentMode_mBtnAuto_actionAdapter(this));
    mBtnTotal.addActionListener(new PanelVentMode_mBtnTotal_actionAdapter(this));
    mBtnNormal.addActionListener(new PanelVentMode_mBtnNormal_actionAdapter(this));
    mPadding.setIconTextGap(4);
    add(mBar, new GridBagConstraints(0, 0, 4, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 5, 0), 0, 0));
    add(mBtnAuto, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mBtnTotal, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mBtnNormal, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 0), 0, 0));
    add(mPadding, new GridBagConstraints(3, 0, 1, 2, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  void mBtnAuto_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getVentMode(getCurrentState()) == 8448) {
      return;
    }
    setCurrentState(EnumVentMode.AUTO);
    notifyToListener();
  }
  
  void mBtnNormal_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getVentMode(getCurrentState()) == 9216) {
      return;
    }
    setCurrentState(EnumVentMode.NORMAL);
    notifyToListener();
  }
  
  void mBtnTotal_actionPerformed(ActionEvent paramActionEvent)
  {
    if (SimpleCommon.getVentMode(getCurrentState()) == 8704) {
      return;
    }
    setCurrentState(EnumVentMode.VENTILATION);
    notifyToListener();
  }
  
  private void notifyToListener()
  {
    mChange = true;
    if (mListener == null) {
      return;
    }
    if (mState == EnumVentMode.AUTO) {
      mListener.notifyAutoVent();
    } else if (mState == EnumVentMode.VENTILATION) {
      mListener.notifyTotalVent();
    } else if (mState == EnumVentMode.NORMAL) {
      mListener.notifyNormalVent();
    }
  }
  
  public void resetChangeFlag()
  {
    mChange = false;
  }
  
  public void setCurrentState(EnumVentMode paramEnumVentMode)
  {
    mChange = false;
    mState = paramEnumVentMode;
    mBtnAuto.setSelected(false);
    mBtnTotal.setSelected(false);
    mBtnNormal.setSelected(false);
    if (paramEnumVentMode == EnumVentMode.AUTO) {
      mBtnAuto.setSelected(true);
    } else if (paramEnumVentMode == EnumVentMode.VENTILATION) {
      mBtnTotal.setSelected(true);
    } else if (paramEnumVentMode == EnumVentMode.NORMAL) {
      mBtnNormal.setSelected(true);
    }
  }
  
  public void setEnabled(boolean paramBoolean, int paramInt)
  {
    if ((paramInt & 0x2) != 0) {
      mBtnAuto.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x1) != 0) {
      mBtnTotal.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x4) != 0) {
      mBtnNormal.setEnabled(paramBoolean);
    }
  }
  
  public void setListener(PanelVentModeListener paramPanelVentModeListener)
  {
    mListener = paramPanelVentModeListener;
  }
  
  public static abstract interface PanelVentModeListener
  {
    public abstract void notifyAutoVent();
    
    public abstract void notifyNormalVent();
    
    public abstract void notifyTotalVent();
  }
}
