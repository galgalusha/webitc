package webitc.gui.simple;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.SimpleButton;
import webitc.gui.common.SimpleButtonRes;

public class PanelVentProperties
  extends PanelAbstract
{
  public static final int PANEL_ALL = 3;
  public static final int PANEL_FRESHUP = 2;
  public static final int PANEL_VOL = 1;
  public static final int VOL_AUTO = 2;
  public static final int VOL_HIGH = 1;
  public static final int VOL_LOW = 0;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  SimpleBar mBarFreshUp = new SimpleBar();
  SimpleBar mBarVol = new SimpleBar();
  ArrowButton mBtnDown = new ArrowButton();
  SimpleButton mBtnOff = SimpleButtonRes.getButton(1);
  SimpleButton mBtnOn = SimpleButtonRes.getButton(0);
  ArrowButton mBtnUp = new ArrowButton();
  private boolean mChangeFreshUp = false;
  private boolean mChangeVol = false;
  private boolean mFreshUp = false;
  JLabel mLabelVol = new JLabel();
  private PanelPropertiesListener mListener = null;
  SimpleBar mPadding = new SimpleBar();
  private int mValidVentVol = 0;
  private int mVol = 0;
  
  public PanelVentProperties()
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
  
  public boolean getCurrentFreshUp()
  {
    return mFreshUp;
  }
  
  public int getCurrentVol()
  {
    return mVol;
  }
  
  public boolean isChangeFreshUp()
  {
    return mChangeFreshUp;
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
    setMinimumSize(new Dimension(400, 80));
    mBarVol.setText(StrRes.getStr("IDS_COMMON_VENT_VOL"));
    mBarVol.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mBarVol.setShadow(true);
    mLabelVol.setMinimumSize(new Dimension(110, 44));
    mLabelVol.setPreferredSize(new Dimension(110, 44));
    mBtnUp.setDirection(1);
    mBtnUp.addActionListener(new PanelVentProperties_mBtnUp_actionAdapter(this));
    mBtnDown.setDirection(3);
    mBtnDown.addActionListener(new PanelVentProperties_mBtnDown_actionAdapter(this));
    mBarFreshUp.setText(StrRes.getStr("IDS_COMMON_FRESHUP"));
    mBarFreshUp.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mBarFreshUp.setShadow(true);
    mPadding.setText(" ");
    mPadding.setBackground(ColorRes.SIMPLE_MODE_TOOL_BAR);
    mPadding.setShadow(true);
    mBtnOff.setSelected(true);
    mBtnOn.addActionListener(new PanelVentProperties_mBtnOn_actionAdapter(this));
    mBtnOff.addActionListener(new PanelVentProperties_mBtnOff_actionAdapter(this));
    add(mBarVol, new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 5, 0), 0, 0));
    add(mBarFreshUp, new GridBagConstraints(2, 0, 2, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 5, 5, 0), 0, 0));
    add(mPadding, new GridBagConstraints(4, 0, 1, 3, 1.0D, 0.0D, 18, 2, new Insets(0, 5, 0, 0), 0, 0));
    add(mLabelVol, new GridBagConstraints(0, 1, 1, 2, 0.0D, 0.0D, 10, 0, new Insets(0, 10, 0, 0), 0, 0));
    add(mBtnUp, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 3, 3), 0, 0));
    add(mBtnDown, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 3), 0, 0));
    add(mBtnOn, new GridBagConstraints(2, 1, 1, 2, 0.0D, 0.0D, 13, 2, new Insets(0, 10, 0, 5), 0, 0));
    add(mBtnOff, new GridBagConstraints(3, 1, 1, 2, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 5), 0, 0));
  }
  
  void mBtnDown_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mVol == 0)
    {
      if (((mValidVentVol & 0x8) != 0) || ((mValidVentVol & 0x1) != 0)) {
        mVol = 2;
      } else if (((mValidVentVol & 0x20) != 0) || ((mValidVentVol & 0x4) != 0)) {
        mVol = 1;
      }
    }
    else if (mVol == 1)
    {
      if (((mValidVentVol & 0x10) != 0) || ((mValidVentVol & 0x2) != 0)) {
        mVol = 0;
      } else if (((mValidVentVol & 0x8) != 0) || ((mValidVentVol & 0x1) != 0)) {
        mVol = 2;
      }
    }
    else if (mVol == 2) {
      if (((mValidVentVol & 0x20) != 0) || ((mValidVentVol & 0x4) != 0)) {
        mVol = 1;
      } else if (((mValidVentVol & 0x10) != 0) || ((mValidVentVol & 0x2) != 0)) {
        mVol = 0;
      }
    }
    updateVolIcon();
    mChangeVol = true;
    if (mListener != null) {
      mListener.notifyChangeVol(mVol);
    }
  }
  
  void mBtnOff_actionPerformed(ActionEvent paramActionEvent)
  {
    if (!mFreshUp) {
      return;
    }
    mFreshUp = false;
    updateFreshUpButton();
    mChangeFreshUp = true;
    if (mListener != null) {
      mListener.notifyChangeFreshUp(mFreshUp);
    }
  }
  
  void mBtnOn_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mFreshUp == true) {
      return;
    }
    mFreshUp = true;
    updateFreshUpButton();
    mChangeFreshUp = true;
    if (mListener != null) {
      mListener.notifyChangeFreshUp(mFreshUp);
    }
  }
  
  void mBtnUp_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mVol == 0)
    {
      if (((mValidVentVol & 0x20) != 0) || ((mValidVentVol & 0x4) != 0)) {
        mVol = 1;
      } else if (((mValidVentVol & 0x8) != 0) || ((mValidVentVol & 0x1) != 0)) {
        mVol = 2;
      }
    }
    else if (mVol == 1)
    {
      if (((mValidVentVol & 0x8) != 0) || ((mValidVentVol & 0x1) != 0)) {
        mVol = 2;
      } else if (((mValidVentVol & 0x10) != 0) || ((mValidVentVol & 0x2) != 0)) {
        mVol = 0;
      }
    }
    else if (mVol == 2) {
      if (((mValidVentVol & 0x10) != 0) || ((mValidVentVol & 0x2) != 0)) {
        mVol = 0;
      } else if (((mValidVentVol & 0x20) != 0) || ((mValidVentVol & 0x4) != 0)) {
        mVol = 1;
      }
    }
    updateVolIcon();
    mChangeVol = true;
    if (mListener != null) {
      mListener.notifyChangeVol(mVol);
    }
  }
  
  public void resetChangeFlag()
  {
    mChangeVol = false;
    mChangeFreshUp = false;
  }
  
  public void setCurrentState(int paramInt, boolean paramBoolean)
  {
    if ((paramInt != 0) && (paramInt != 1) && (paramInt != 2)) {
      return;
    }
    mChangeVol = false;
    mChangeFreshUp = false;
    mVol = paramInt;
    mFreshUp = paramBoolean;
    updateVolIcon();
  }
  
  public void setEnabled(boolean paramBoolean, int paramInt)
  {
    if ((paramInt & 0x1) != 0)
    {
      if (!paramBoolean) {
        mLabelVol.setIcon(IconRes.getIcon(124));
      }
      mBtnUp.setEnabled(paramBoolean);
      mBtnDown.setEnabled(paramBoolean);
    }
    if ((paramInt & 0x2) != 0)
    {
      mBtnOn.setEnabled(paramBoolean);
      mBtnOff.setEnabled(paramBoolean);
    }
  }
  
  public void setListener(PanelPropertiesListener paramPanelPropertiesListener)
  {
    mListener = paramPanelPropertiesListener;
  }
  
  public void setValidVentVol(int paramInt)
  {
    mValidVentVol = paramInt;
    if (((paramInt & 0x8) != 0) || ((paramInt & 0x20) != 0) || ((paramInt & 0x10) != 0)) {
      mBtnOn.setEnabled(true);
    } else {
      mBtnOn.setEnabled(false);
    }
    if (((paramInt & 0x1) != 0) || ((paramInt & 0x4) != 0) || ((paramInt & 0x2) != 0)) {
      mBtnOff.setEnabled(true);
    } else {
      mBtnOff.setEnabled(false);
    }
  }
  
  private void updateFreshUpButton()
  {
    if (mVol == 0)
    {
      if (mFreshUp == true)
      {
        if ((mValidVentVol & 0x10) != 0)
        {
          mBtnOn.setSelected(true);
          mBtnOff.setSelected(false);
        }
        else if ((mValidVentVol & 0x2) != 0)
        {
          mBtnOn.setSelected(false);
          mBtnOff.setSelected(true);
        }
      }
      else if ((mValidVentVol & 0x2) != 0)
      {
        mBtnOff.setSelected(true);
        mBtnOn.setSelected(false);
      }
      else if ((mValidVentVol & 0x10) != 0)
      {
        mBtnOff.setSelected(false);
        mBtnOn.setSelected(true);
      }
      if ((mValidVentVol & 0x10) != 0) {
        mBtnOn.setEnabled(true);
      } else {
        mBtnOn.setEnabled(false);
      }
      if ((mValidVentVol & 0x2) != 0) {
        mBtnOff.setEnabled(true);
      } else {
        mBtnOff.setEnabled(false);
      }
    }
    else if (mVol == 1)
    {
      if (mFreshUp == true)
      {
        if ((mValidVentVol & 0x20) != 0)
        {
          mBtnOn.setSelected(true);
          mBtnOff.setSelected(false);
        }
        else if ((mValidVentVol & 0x4) != 0)
        {
          mBtnOn.setSelected(false);
          mBtnOff.setSelected(true);
        }
      }
      else if ((mValidVentVol & 0x4) != 0)
      {
        mBtnOff.setSelected(true);
        mBtnOn.setSelected(false);
      }
      else if ((mValidVentVol & 0x20) != 0)
      {
        mBtnOff.setSelected(false);
        mBtnOn.setSelected(true);
      }
      if ((mValidVentVol & 0x20) != 0) {
        mBtnOn.setEnabled(true);
      } else {
        mBtnOn.setEnabled(false);
      }
      if ((mValidVentVol & 0x4) != 0) {
        mBtnOff.setEnabled(true);
      } else {
        mBtnOff.setEnabled(false);
      }
    }
    else if (mVol == 2)
    {
      if (mFreshUp == true)
      {
        if ((mValidVentVol & 0x20) != 0)
        {
          mBtnOn.setSelected(true);
          mBtnOff.setSelected(false);
        }
        else if ((mValidVentVol & 0x4) != 0)
        {
          mBtnOn.setSelected(false);
          mBtnOff.setSelected(true);
        }
      }
      else if ((mValidVentVol & 0x4) != 0)
      {
        mBtnOff.setSelected(true);
        mBtnOn.setSelected(false);
      }
      else if ((mValidVentVol & 0x20) != 0)
      {
        mBtnOff.setSelected(false);
        mBtnOn.setSelected(true);
      }
      if ((mValidVentVol & 0x20) != 0) {
        mBtnOn.setEnabled(true);
      } else {
        mBtnOn.setEnabled(false);
      }
      if ((mValidVentVol & 0x4) != 0) {
        mBtnOff.setEnabled(true);
      } else {
        mBtnOff.setEnabled(false);
      }
    }
  }
  
  private void updateVolIcon()
  {
    switch (mVol)
    {
    case 0: 
      mLabelVol.setIcon(IconRes.getIcon(125));
      if (((mValidVentVol & 0x8) != 0) || ((mValidVentVol & 0x20) != 0) || ((mValidVentVol & 0x1) != 0) || ((mValidVentVol & 0x4) != 0))
      {
        setEnabled(true, 1);
        mLabelVol.setToolTipText(StrRes.getStr("IDS_COMMON_WEAK"));
      }
      else
      {
        setEnabled(false, 1);
        mLabelVol.setToolTipText(null);
      }
      break;
    case 1: 
      mLabelVol.setIcon(IconRes.getIcon(126));
      if (((mValidVentVol & 0x10) != 0) || ((mValidVentVol & 0x8) != 0) || ((mValidVentVol & 0x2) != 0) || ((mValidVentVol & 0x1) != 0))
      {
        setEnabled(true, 1);
        mLabelVol.setToolTipText(StrRes.getStr("IDS_COMMON_STRONG"));
      }
      else
      {
        setEnabled(false, 1);
        mLabelVol.setToolTipText(null);
      }
      break;
    case 2: 
      mLabelVol.setIcon(IconRes.getIcon(127));
      if (((mValidVentVol & 0x10) != 0) || ((mValidVentVol & 0x20) != 0) || ((mValidVentVol & 0x2) != 0) || ((mValidVentVol & 0x4) != 0))
      {
        setEnabled(true, 1);
        mLabelVol.setToolTipText(StrRes.getStr("IDS_SCH_VENT_MODE_AUTO"));
      }
      else
      {
        setEnabled(false, 1);
        mLabelVol.setToolTipText(null);
      }
      break;
    default: 
      setEnabled(false, 1);
      mLabelVol.setToolTipText(null);
      return;
    }
    updateFreshUpButton();
  }
  
  public static abstract interface PanelPropertiesListener
  {
    public abstract void notifyChangeFreshUp(boolean paramBoolean);
    
    public abstract void notifyChangeVol(int paramInt);
  }
}
