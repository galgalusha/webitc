package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.gui.common.AnimationPanel;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;

public class PanelProperties
  extends PanelAbstract
{
  public static final int DIR_1 = 0;
  public static final int DIR_2 = 1;
  public static final int DIR_3 = 2;
  public static final int DIR_4 = 3;
  public static final int DIR_5 = 4;
  public static final int DIR_SWING = 5;
  public static final int PANEL_ALL = 7;
  public static final int PANEL_DIR = 2;
  public static final int PANEL_TEMP = 1;
  public static final int PANEL_VOL = 4;
  public static final int VOL_HIGH = 1;
  public static final int VOL_LOW = 0;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  SimpleBar mBarDirection = new SimpleBar();
  SimpleBar mBarTemp = new SimpleBar();
  SimpleBar mBarVol = new SimpleBar();
  ArrowButton mBtnDownDir = new ArrowButton();
  ArrowButton mBtnDownTemp = new ArrowButton();
  ArrowButton mBtnDownVol = new ArrowButton();
  ArrowButton mBtnUpDir = new ArrowButton();
  ArrowButton mBtnUpTemp = new ArrowButton();
  ArrowButton mBtnUpVol = new ArrowButton();
  boolean mChangeDir = false;
  boolean mChangeTemp = false;
  boolean mChangeVol = false;
  private int mDir = 0;
  SimpleBar mDummyBar = new SimpleBar();
  JLabel mLabelFanVol = new JLabel();
  TempLabel mLabelTemp = new TempLabel();
  AnimationPanel mLblFanDir = new AnimationPanel(0);
  private PanelPropertiesListener mListener = null;
  private int mTempHighLimit = 0;
  private int mTempLowLimit = 0;
  private int mVol = 0;
  
  public PanelProperties()
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
    return mDir;
  }
  
  public int getCurrentTemp()
  {
    return mLabelTemp.getTemp();
  }
  
  public int getCurrentVol()
  {
    return mVol;
  }
  
  public boolean isChangeDir()
  {
    return mChangeDir;
  }
  
  public boolean isChangeTemp()
  {
    return mChangeTemp;
  }
  
  public boolean isChangeVol()
  {
    return mChangeVol;
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBackground(ColorRes.SIMPLE_MODE_BG);
    mBarTemp.setText(StrRes.getStr("IDS_COMMON_SETTEMP"));
    mBarTemp.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mBarTemp.setShadow(true);
    mLabelTemp.setMinimumSize(new Dimension(100, 40));
    mLabelTemp.setPreferredSize(new Dimension(100, 40));
    mBtnUpTemp.setDirection(1);
    mBtnUpTemp.addActionListener(new PanelProperties_mBtnUpTemp_actionAdapter(this));
    mBtnDownTemp.setDirection(3);
    mBtnDownTemp.addActionListener(new PanelProperties_mBtnDownTemp_actionAdapter(this));
    mBarDirection.setText(StrRes.getStr("IDS_WEBACSET_FAN_DIR"));
    mBarDirection.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mBarDirection.setShadow(true);
    mLblFanDir.setBackground(ColorRes.SIMPLE_MODE_BG);
    mLblFanDir.setPreferredSize(new Dimension(50, 50));
    updateDirIcon();
    mBtnUpDir.setDirection(1);
    mBtnUpDir.addActionListener(new PanelProperties_mBtnUpDir_actionAdapter(this));
    mBtnDownDir.setDirection(3);
    mBtnDownDir.addActionListener(new PanelProperties_mBtnDownDir_actionAdapter(this));
    mBarVol.setText(StrRes.getStr("IDS_WEBACSET_FAN"));
    mBarVol.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mBarVol.setShadow(true);
    updateVolIcon();
    mBtnUpVol.setDirection(1);
    mBtnUpVol.addActionListener(new PanelProperties_mBtnUpVol_actionAdapter(this));
    mBtnDownVol.setDirection(3);
    mBtnDownVol.addActionListener(new PanelProperties_mBtnDownVol_actionAdapter(this));
    mDummyBar.setText(" ");
    mDummyBar.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mDummyBar.setShadow(true);
    add(mBarTemp, new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 0), 0, 0));
    add(mBarDirection, new GridBagConstraints(2, 0, 2, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 5, 5, 0), 0, 0));
    add(mBarVol, new GridBagConstraints(4, 0, 2, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 5, 5, 0), 0, 0));
    add(mDummyBar, new GridBagConstraints(6, 0, 1, 3, 1.0D, 0.0D, 18, 2, new Insets(0, 5, 5, 0), 0, 0));
    add(mLabelTemp, new GridBagConstraints(0, 1, 1, 2, 0.0D, 0.0D, 10, 0, new Insets(0, 30, 0, 10), 0, 0));
    add(mBtnUpTemp, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 3, 3), 0, 0));
    add(mBtnDownTemp, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 3), 0, 0));
    add(mLblFanDir, new GridBagConstraints(2, 1, 1, 2, 0.0D, 0.0D, 10, 0, new Insets(5, 40, 0, 20), 0, 0));
    add(mBtnUpDir, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 3, 3), 0, 0));
    add(mBtnDownDir, new GridBagConstraints(3, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 3), 0, 0));
    add(mLabelFanVol, new GridBagConstraints(4, 1, 1, 2, 0.0D, 0.0D, 10, 0, new Insets(0, 40, 0, 30), 0, 0));
    add(mBtnUpVol, new GridBagConstraints(5, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 3, 3), 0, 0));
    add(mBtnDownVol, new GridBagConstraints(5, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 3), 0, 0));
  }
  
  void mBtnDownDir_actionPerformed(ActionEvent paramActionEvent)
  {
    switch (mDir)
    {
    case 0: 
      mDir = 1;
      break;
    case 1: 
      mDir = 2;
      break;
    case 2: 
      mDir = 3;
      break;
    case 3: 
      mDir = 4;
      break;
    case 4: 
      mDir = 5;
      break;
    case 5: 
      mDir = 0;
      break;
    default: 
      return;
    }
    updateDirIcon();
    mChangeDir = true;
    if (mListener != null) {
      mListener.notifyChangeDir(mDir);
    }
  }
  
  void mBtnDownTemp_actionPerformed(ActionEvent paramActionEvent)
  {
    mLabelTemp.downTemp();
    updateTempButton();
    mChangeTemp = true;
    if (mListener != null) {
      mListener.notifyChangeTemp(mLabelTemp.getTemp());
    }
  }
  
  void mBtnDownVol_actionPerformed(ActionEvent paramActionEvent)
  {
    switch (mVol)
    {
    case 0: 
      mVol = 1;
      break;
    case 1: 
      mVol = 0;
      break;
    default: 
      return;
    }
    updateVolIcon();
    mChangeVol = true;
    if (mListener != null) {
      mListener.notifyChangeVol(mVol);
    }
  }
  
  void mBtnUpDir_actionPerformed(ActionEvent paramActionEvent)
  {
    switch (mDir)
    {
    case 0: 
      mDir = 5;
      break;
    case 1: 
      mDir = 0;
      break;
    case 2: 
      mDir = 1;
      break;
    case 3: 
      mDir = 2;
      break;
    case 4: 
      mDir = 3;
      break;
    case 5: 
      mDir = 4;
      break;
    default: 
      return;
    }
    updateDirIcon();
    mChangeDir = true;
    if (mListener != null) {
      mListener.notifyChangeDir(mDir);
    }
  }
  
  void mBtnUpTemp_actionPerformed(ActionEvent paramActionEvent)
  {
    mLabelTemp.upTemp();
    updateTempButton();
    mChangeTemp = true;
    if (mListener != null) {
      mListener.notifyChangeTemp(mLabelTemp.getTemp());
    }
  }
  
  void mBtnUpVol_actionPerformed(ActionEvent paramActionEvent)
  {
    switch (mVol)
    {
    case 0: 
      mVol = 1;
      break;
    case 1: 
      mVol = 0;
      break;
    default: 
      return;
    }
    updateVolIcon();
    mChangeVol = true;
    if (mListener != null) {
      mListener.notifyChangeVol(mVol);
    }
  }
  
  public void resetChangeFlag()
  {
    mChangeTemp = false;
    mChangeDir = false;
    mChangeVol = false;
  }
  
  public void setCurrentState(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 < 0) || (paramInt2 > 1) || (paramInt3 < 0) || (paramInt3 > 5)) {
      return;
    }
    if ((paramInt1 < mTempLowLimit) || (paramInt1 > mTempHighLimit)) {
      paramInt1 = mTempLowLimit;
    }
    mChangeTemp = false;
    mChangeDir = false;
    mChangeVol = false;
    mLabelTemp.setTemp(paramInt1);
    mVol = paramInt2;
    updateVolIcon();
    if ((paramInt3 != 5) || (mDir != 5))
    {
      mDir = paramInt3;
      updateDirIcon();
    }
    updateTempButton();
  }
  
  public void setEnabled(boolean paramBoolean, int paramInt)
  {
    if ((paramInt & 0x1) != 0)
    {
      mLabelTemp.setEnabled(paramBoolean);
      if (paramBoolean == true)
      {
        updateTempButton();
      }
      else
      {
        mBtnUpTemp.setEnabled(paramBoolean);
        mBtnDownTemp.setEnabled(paramBoolean);
      }
    }
    if ((paramInt & 0x2) != 0)
    {
      if (!paramBoolean)
      {
        mLblFanDir.endAnimation();
        mLblFanDir.clearAnimationImage();
        mLblFanDir.addAnimationImage(IconRes.getIcon(101));
        mLblFanDir.updateImage(0);
      }
      mBtnUpDir.setEnabled(paramBoolean);
      mBtnDownDir.setEnabled(paramBoolean);
      if (mDir == 5) {
        mLblFanDir.beginAnimation();
      }
    }
    if ((paramInt & 0x4) != 0)
    {
      mLabelFanVol.setIcon(IconRes.getIcon(112));
      mBtnUpVol.setEnabled(paramBoolean);
      mBtnDownVol.setEnabled(paramBoolean);
    }
  }
  
  public void setListener(PanelPropertiesListener paramPanelPropertiesListener)
  {
    mListener = paramPanelPropertiesListener;
  }
  
  public void setTempLimit(int paramInt1, int paramInt2)
  {
    mTempLowLimit = paramInt1;
    mTempHighLimit = paramInt2;
  }
  
  public void setVisible(boolean paramBoolean)
  {
    super.setVisible(paramBoolean);
    mLblFanDir.setVisible(paramBoolean);
  }
  
  private void updateDirIcon()
  {
    if ((!mBtnUpDir.isEnabled()) && (!mBtnDownDir.isEnabled())) {
      return;
    }
    mLblFanDir.endAnimation();
    mLblFanDir.clearAnimationImage();
    switch (mDir)
    {
    case 0: 
      mLblFanDir.addAnimationImage(IconRes.getIcon(102));
      mLblFanDir.updateImage(0);
      break;
    case 1: 
      mLblFanDir.addAnimationImage(IconRes.getIcon(103));
      mLblFanDir.updateImage(0);
      break;
    case 2: 
      mLblFanDir.addAnimationImage(IconRes.getIcon(104));
      mLblFanDir.updateImage(0);
      break;
    case 3: 
      mLblFanDir.addAnimationImage(IconRes.getIcon(105));
      mLblFanDir.updateImage(0);
      break;
    case 4: 
      mLblFanDir.addAnimationImage(IconRes.getIcon(106));
      mLblFanDir.updateImage(0);
      break;
    case 5: 
      mLblFanDir.addAnimationImage(IconRes.getIcon(107));
      mLblFanDir.addAnimationImage(IconRes.getIcon(108));
      mLblFanDir.addAnimationImage(IconRes.getIcon(109));
      mLblFanDir.addAnimationImage(IconRes.getIcon(110));
      mLblFanDir.addAnimationImage(IconRes.getIcon(111));
      mLblFanDir.addAnimationImage(IconRes.getIcon(110));
      mLblFanDir.addAnimationImage(IconRes.getIcon(109));
      mLblFanDir.addAnimationImage(IconRes.getIcon(108));
      mLblFanDir.beginAnimation();
      break;
    }
  }
  
  private void updateTempButton()
  {
    if (!mLabelTemp.isEnabled()) {
      return;
    }
    if (mLabelTemp.getTemp() >= mTempHighLimit) {
      mBtnUpTemp.setEnabled(false);
    } else {
      mBtnUpTemp.setEnabled(true);
    }
    if (mLabelTemp.getTemp() <= mTempLowLimit) {
      mBtnDownTemp.setEnabled(false);
    } else {
      mBtnDownTemp.setEnabled(true);
    }
  }
  
  private void updateVolIcon()
  {
    if ((!mBtnUpVol.isEnabled()) && (!mBtnDownVol.isEnabled())) {
      return;
    }
    switch (mVol)
    {
    case 0: 
      mLabelFanVol.setIcon(IconRes.getIcon(113));
      break;
    case 1: 
      mLabelFanVol.setIcon(IconRes.getIcon(114));
      break;
    }
  }
  
  public static abstract interface PanelPropertiesListener
  {
    public abstract void notifyChangeDir(int paramInt);
    
    public abstract void notifyChangeTemp(int paramInt);
    
    public abstract void notifyChangeVol(int paramInt);
  }
}
