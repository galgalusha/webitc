package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import webitc.data.DataMgr;
import webitc.data.point.Port;
import webitc.gui.common.AnimationPanel;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.SimpleButton;
import webitc.gui.common.SimpleButtonRes;

public class PanelOnOff
  extends PanelAbstract
{
  public static final int BTN_ALL = 3;
  public static final int BTN_OFF = 2;
  public static final int BTN_ON = 1;
  public static final int OFF = 4096;
  public static final int ON = 8192;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  SimpleButton mBtnOff = SimpleButtonRes.getButton(3);
  SimpleButton mBtnOn = SimpleButtonRes.getButton(2);
  boolean mCentorControl = false;
  boolean mChange = false;
  PanelOnOffListener mListener = null;
  AnimationPanel mRemocon = new AnimationPanel();
  JLabel mSign = new JLabel();
  private int mState = 4096;
  
  public PanelOnOff()
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
  
  public void beginRemocon()
  {
    mRemocon.setVisible(true);
    if (!mRemocon.isRunning()) {
      mRemocon.beginAnimation();
    }
  }
  
  public void endRemocon()
  {
    mRemocon.setVisible(false);
    mRemocon.endAnimation();
  }
  
  public int getCurrentState()
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
    setPreferredSize(new Dimension(100, 30));
    mSign.setBackground(ColorRes.SIMPLE_MODE_BG);
    mBtnOn.addActionListener(new PanelOnOff_mBtnOn_actionAdapter(this));
    mBtnOff.addActionListener(new PanelOnOff_mBtnOff_actionAdapter(this));
    mRemocon.setPreferredSize(new Dimension(57, 30));
    mRemocon.setBackground(ColorRes.SIMPLE_MODE_BG);
    mRemocon.setVisible(false);
    mRemocon.setAnimationInterval(500);
    mRemocon.addAnimationImage(IconRes.getIcon(177));
    mRemocon.addAnimationImage(IconRes.getIcon(178));
    mRemocon.addAnimationImage(IconRes.getIcon(179));
    mRemocon.addAnimationImage(IconRes.getIcon(180));
    add(mBtnOn, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 5), 0, 0));
    add(mBtnOff, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 0), 0, 0));
    add(mRemocon, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 80, 0, 0), 0, 0));
    add(mSign, new GridBagConstraints(3, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 0, 0), 0, 0));
  }
  
  void mBtnOff_actionPerformed(ActionEvent paramActionEvent)
  {
    if (getCurrentState() == 4096) {
      return;
    }
    setCurrentState(4096);
    mChange = true;
    if (mListener != null) {
      mListener.notifyOff();
    }
  }
  
  void mBtnOn_actionPerformed(ActionEvent paramActionEvent)
  {
    if (getCurrentState() == 8192) {
      return;
    }
    setCurrentState(8192);
    mChange = true;
    if (mListener != null) {
      mListener.notifyOn();
    }
  }
  
  public void resetChangeFlag()
  {
    mChange = false;
  }
  
  public void setCurrentState(int paramInt)
  {
    mChange = false;
    mState = paramInt;
    if (paramInt == 8192)
    {
      mBtnOn.setSelected(true);
      mBtnOff.setSelected(false);
    }
    else
    {
      mBtnOn.setSelected(false);
      mBtnOff.setSelected(true);
    }
    updateSystemSign();
  }
  
  public void setEnabled(boolean paramBoolean, int paramInt)
  {
    if ((paramInt & 0x1) != 0) {
      mBtnOn.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x2) != 0) {
      mBtnOff.setEnabled(paramBoolean);
    }
  }
  
  public void setListener(PanelOnOffListener paramPanelOnOffListener)
  {
    mListener = paramPanelOnOffListener;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    if ((!paramBoolean) || (mRemocon.isRunning() == true)) {
      mRemocon.setVisible(paramBoolean);
    }
  }
  
  private void updateSystemSign()
  {
    String str = DataMgr.getInstance().getPort().getPortStr();
    if (str.compareTo("") == 0) {
      mSign.setIcon(null);
    } else {
      mSign.setIcon(IconRes.getIcon(170));
    }
    mSign.setText(str);
  }
  
  public static abstract interface PanelOnOffListener
  {
    public abstract void notifyOff();
    
    public abstract void notifyOn();
  }
}
