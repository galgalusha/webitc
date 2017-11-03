package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import webitc.common.enum2.EnumPntStat;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.gui.common.ColorRes;
import webitc.gui.common.ItcIconInfo;
import webitc.gui.common.PanelAbstract;

public class PanelStateLight
  extends PanelAbstract
  implements IPanelState, PanelOnOff.PanelOnOffListener
{
  public static final Dimension PANEL_SIZE = new Dimension(400, 150);
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  boolean mChange = false;
  ID mCurrentPntID = null;
  JLabel mLabel = new JLabel();
  private IPanelState.PanelStateListener mListener = null;
  JLabel mPadding = new JLabel();
  PanelLightStateIcon mPanelIcon = new PanelLightStateIcon();
  PanelOnOff mPanelOnOff = new PanelOnOff();
  private final JScrollPane mScroll = new JScrollPane();
  SimpleBar mVerticalBar = new SimpleBar();
  
  public PanelStateLight()
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
    add(mPanelIcon, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 3, new Insets(5, 5, 5, 5), 0, 0));
    add(mVerticalBar, new GridBagConstraints(1, 0, 1, 2, 0.0D, 0.0D, 10, 3, new Insets(3, 0, 0, 0), 0, 0));
    add(mPanelOnOff, new GridBagConstraints(2, 0, 1, 2, 1.0D, 0.0D, 17, 2, new Insets(3, 5, 5, 5), 0, 0));
    add(mPadding, new GridBagConstraints(0, 2, 3, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPanelOnOff.setListener(this);
    mScroll.getViewport().setView(this);
    mScroll.setPreferredSize(PANEL_SIZE);
  }
  
  public void notifyEndSendOperation()
  {
    mPanelOnOff.endRemocon();
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
  
  public void resetChangeFlag()
  {
    mChange = false;
    mPanelOnOff.resetChangeFlag();
  }
  
  public void setID(ID paramID)
  {
    mCurrentPntID = paramID;
    mChange = false;
    String str = DataMgr.getInstance().getDetailName(paramID);
    mLabel.setText(str);
    mLabel.setToolTipText(str);
    if (SimpleCommon.isDispOnOffBtn(paramID) == true) {
      mPanelOnOff.setVisible(true);
    } else {
      mPanelOnOff.setVisible(false);
    }
    updateSign(paramID);
    PntTarget localPntTarget = DataMgr.getInstance().getPntTarget(paramID, false);
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
    if (mPanelOnOff.getCurrentState() == 8192) {
      i = 8192;
    }
    int j = SimpleCommon.getState(mCurrentPntID);
    mPanelIcon.setIconState(j, i);
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
