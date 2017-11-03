package webitc.gui.monitor;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import webitc.common.StrRes;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntSet;
import webitc.data.point.PntTarget;
import webitc.gui.common.BorderRes;
import webitc.gui.common.DlgAbstract;

public class DlgSetVentSub
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnOK = new JButton();
  JCheckBox mChkVentMode = new JCheckBox();
  JCheckBox mChkVentVol = new JCheckBox();
  ButtonGroup mGrpOnOff = new ButtonGroup();
  ButtonGroup mGrpRkkOnOff = new ButtonGroup();
  ButtonGroup mGrpVentMode = new ButtonGroup();
  ButtonGroup mGrpVentVol = new ButtonGroup();
  JPanel mPnlMain = new JPanel();
  JPanel mPnlVentMode = new JPanel();
  JPanel mPnlVentVol = new JPanel();
  private final PntCurrent mPntCurrent;
  private PntSet mPntSet;
  private final PntTarget mPntTarget;
  JRadioButton mRdoModeAuto = new JRadioButton();
  JRadioButton mRdoModeNormal = new JRadioButton();
  JRadioButton mRdoModeVent = new JRadioButton();
  JRadioButton mRdoVolFreshAuto = new JRadioButton();
  JRadioButton mRdoVolFreshHigh = new JRadioButton();
  JRadioButton mRdoVolFreshLow = new JRadioButton();
  JRadioButton mRdoVolNormalAuto = new JRadioButton();
  JRadioButton mRdoVolNormalHigh = new JRadioButton();
  JRadioButton mRdoVolNormalLow = new JRadioButton();
  
  public DlgSetVentSub(PntTarget paramPntTarget, PntCurrent paramPntCurrent, PntSet paramPntSet)
  {
    mPntTarget = paramPntTarget;
    mPntCurrent = paramPntCurrent;
    mPntSet = paramPntSet;
    try
    {
      jbInit();
      pntInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mBtnOK.setEnabled(false);
  }
  
  public PntSet getPntSet()
  {
    return mPntSet;
  }
  
  private void initParts()
  {
    if (mPntSet.mVentMode != EnumVentMode.ELSE)
    {
      if (mPntSet.mVentMode == EnumVentMode.AUTO) {
        mRdoModeAuto.setSelected(true);
      } else if (mPntSet.mVentMode == EnumVentMode.VENTILATION) {
        mRdoModeVent.setSelected(true);
      } else if (mPntSet.mVentMode == EnumVentMode.NORMAL) {
        mRdoModeNormal.setSelected(true);
      }
      mChkVentMode.setSelected(true);
    }
    else
    {
      if ((mPntTarget != null) && (mPntTarget.fValidVentMode == 0)) {
        mChkVentMode.setEnabled(false);
      }
      if (mPntCurrent.mVentMode == EnumVentMode.AUTO) {
        mRdoModeAuto.setSelected(true);
      } else if (mPntCurrent.mVentMode == EnumVentMode.VENTILATION) {
        mRdoModeVent.setSelected(true);
      } else if (mPntCurrent.mVentMode == EnumVentMode.NORMAL) {
        mRdoModeNormal.setSelected(true);
      } else if ((mPntTarget.fValidVentMode & 0x1) != 0) {
        mRdoModeAuto.setSelected(true);
      } else if ((mPntTarget.fValidVentMode & 0x2) != 0) {
        mRdoModeVent.setSelected(true);
      } else if ((mPntTarget.fValidVentMode & 0x4) != 0) {
        mRdoModeNormal.setSelected(true);
      } else {
        mRdoModeAuto.setSelected(true);
      }
    }
    if (mPntSet.mVentVol != EnumVentVol.ELSE)
    {
      if (mPntSet.mVentVol == EnumVentVol.NORMAL_AUTO) {
        mRdoVolNormalAuto.setSelected(true);
      } else if (mPntSet.mVentVol == EnumVentVol.NORMAL_LOW) {
        mRdoVolNormalLow.setSelected(true);
      } else if (mPntSet.mVentVol == EnumVentVol.NORMAL_HIGH) {
        mRdoVolNormalHigh.setSelected(true);
      } else if (mPntSet.mVentVol == EnumVentVol.FRESH_AUTO) {
        mRdoVolFreshAuto.setSelected(true);
      } else if (mPntSet.mVentVol == EnumVentVol.FRESH_LOW) {
        mRdoVolFreshLow.setSelected(true);
      } else if (mPntSet.mVentVol == EnumVentVol.FRESH_HIGH) {
        mRdoVolFreshHigh.setSelected(true);
      }
    }
    else
    {
      if ((mPntTarget != null) && (mPntTarget.fValidVentMode == 0)) {
        mChkVentVol.setEnabled(false);
      }
      if (mPntCurrent.mVentVol == EnumVentVol.NORMAL_AUTO) {
        mRdoVolNormalAuto.setSelected(true);
      } else if (mPntCurrent.mVentVol == EnumVentVol.NORMAL_LOW) {
        mRdoVolNormalLow.setSelected(true);
      } else if (mPntCurrent.mVentVol == EnumVentVol.NORMAL_HIGH) {
        mRdoVolNormalHigh.setSelected(true);
      } else if (mPntCurrent.mVentVol == EnumVentVol.FRESH_AUTO) {
        mRdoVolFreshAuto.setSelected(true);
      } else if (mPntCurrent.mVentVol == EnumVentVol.FRESH_LOW) {
        mRdoVolFreshLow.setSelected(true);
      } else if (mPntCurrent.mVentVol == EnumVentVol.FRESH_HIGH) {
        mRdoVolFreshHigh.setSelected(true);
      } else if ((mPntTarget.fValidVentVol & 0x1) != 0) {
        mRdoVolNormalAuto.setSelected(true);
      } else if ((mPntTarget.fValidVentVol & 0x2) != 0) {
        mRdoVolNormalLow.setSelected(true);
      } else if ((mPntTarget.fValidVentVol & 0x4) != 0) {
        mRdoVolNormalHigh.setSelected(true);
      } else if ((mPntTarget.fValidVentVol & 0x8) != 0) {
        mRdoVolFreshAuto.setSelected(true);
      } else if ((mPntTarget.fValidVentVol & 0x10) != 0) {
        mRdoVolFreshLow.setSelected(true);
      } else if ((mPntTarget.fValidVentVol & 0x20) != 0) {
        mRdoVolFreshHigh.setSelected(true);
      } else {
        mRdoVolNormalAuto.setSelected(true);
      }
    }
  }
  
  private void jbInit()
    throws Exception
  {
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSetVentSub_mBtnCancel_actionAdapter(this));
    getContentPane().setLayout(gridBagLayout1);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSetVentSub_mBtnOK_actionAdapter(this));
    mPnlMain.setLayout(gridBagLayout2);
    setTitle(StrRes.getStr("IDS_COMMON_VENT_SET"));
    mPnlVentMode.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_VENT_MODE")));
    mPnlVentMode.setLayout(gridBagLayout4);
    mPnlVentVol.setLayout(gridBagLayout5);
    mPnlVentVol.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_VENT_VOL")));
    mChkVentMode.addItemListener(new DlgSetVentSub_mChkVentMode_itemAdapter(this));
    mChkVentMode.setMargin(new Insets(0, 2, 0, 2));
    mChkVentMode.setMnemonic('0');
    mRdoModeAuto.setMargin(new Insets(0, 2, 0, 2));
    mRdoModeAuto.setMnemonic('0');
    mRdoModeVent.setMargin(new Insets(0, 2, 0, 2));
    mRdoModeVent.setMnemonic('0');
    mRdoModeNormal.setMargin(new Insets(0, 2, 0, 2));
    mRdoModeNormal.setMnemonic('0');
    mChkVentVol.setMargin(new Insets(0, 2, 0, 2));
    mChkVentVol.setMnemonic('0');
    mChkVentVol.addItemListener(new DlgSetVentSub_mChkVentVol_itemAdapter(this));
    mRdoVolNormalAuto.setMargin(new Insets(0, 2, 0, 2));
    mRdoVolNormalAuto.setMnemonic('0');
    mRdoVolNormalLow.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_LOW"));
    mRdoVolNormalHigh.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_HIGH"));
    mRdoVolFreshAuto.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_AUTO"));
    mRdoVolFreshLow.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_LOW"));
    mRdoVolFreshHigh.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_HIGH"));
    getContentPane().add(mBtnOK, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 13, 0, new Insets(5, 0, 6, 5), 5, 0));
    getContentPane().add(mBtnCancel, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 2, 6, 12), 0, 0));
    getContentPane().add(mPnlMain, new GridBagConstraints(0, 0, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(5, 5, 5, 5), 0, 0));
    mChkVentMode.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentMode.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentVol.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentVol.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mRdoModeAuto.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_AUTO"));
    mRdoModeVent.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_VENT"));
    mRdoModeNormal.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_NORMAL"));
    mRdoVolNormalAuto.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_AUTO"));
    mPnlMain.add(mPnlVentMode, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentMode.add(mRdoModeAuto, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlVentMode.add(mChkVentMode, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 2, 0));
    mPnlVentMode.add(mRdoModeVent, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlVentMode.add(mRdoModeNormal, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 2), 4, 0));
    mPnlMain.add(mPnlVentVol, new GridBagConstraints(0, 1, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mChkVentVol, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 11, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolNormalAuto, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mGrpVentMode.add(mRdoModeAuto);
    mGrpVentMode.add(mRdoModeVent);
    mGrpVentMode.add(mRdoModeNormal);
    mGrpVentVol.add(mRdoVolNormalAuto);
    mPnlVentVol.add(mRdoVolNormalLow, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolNormalHigh, new GridBagConstraints(4, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolFreshAuto, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolFreshLow, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolFreshHigh, new GridBagConstraints(4, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mGrpVentVol.add(mRdoVolNormalLow);
    mGrpVentVol.add(mRdoVolNormalHigh);
    mGrpVentVol.add(mRdoVolFreshAuto);
    mGrpVentVol.add(mRdoVolFreshLow);
    mGrpVentVol.add(mRdoVolFreshHigh);
  }
  
  void mBtnCancel_actionPerformed()
  {
    closeDialog(false);
  }
  
  void mBtnOK_actionPerformed()
  {
    updateInnerSet();
    closeDialog(true);
  }
  
  void mChkVentMode_itemStateChanged()
  {
    if (mChkVentMode.isSelected() == true)
    {
      if (mPntTarget != null)
      {
        if ((mPntTarget.fValidVentMode & 0x1) != 0) {
          mRdoModeAuto.setEnabled(true);
        } else {
          mRdoModeAuto.setEnabled(false);
        }
        if ((mPntTarget.fValidVentMode & 0x2) != 0) {
          mRdoModeVent.setEnabled(true);
        } else {
          mRdoModeVent.setEnabled(false);
        }
        if ((mPntTarget.fValidVentMode & 0x4) != 0) {
          mRdoModeNormal.setEnabled(true);
        } else {
          mRdoModeNormal.setEnabled(false);
        }
      }
      else
      {
        mRdoModeAuto.setEnabled(true);
        mRdoModeVent.setEnabled(true);
        mRdoModeNormal.setEnabled(true);
      }
    }
    else
    {
      mRdoModeAuto.setEnabled(false);
      mRdoModeVent.setEnabled(false);
      mRdoModeNormal.setEnabled(false);
    }
  }
  
  void mChkVentVol_itemStateChanged()
  {
    if (mChkVentVol.isSelected() == true)
    {
      if (mPntTarget != null)
      {
        if ((mPntTarget.fValidVentVol & 0x1) != 0) {
          mRdoVolNormalAuto.setEnabled(true);
        } else {
          mRdoVolNormalAuto.setEnabled(false);
        }
        if ((mPntTarget.fValidVentVol & 0x2) != 0) {
          mRdoVolNormalLow.setEnabled(true);
        } else {
          mRdoVolNormalLow.setEnabled(false);
        }
        if ((mPntTarget.fValidVentVol & 0x4) != 0) {
          mRdoVolNormalHigh.setEnabled(true);
        } else {
          mRdoVolNormalHigh.setEnabled(false);
        }
        if ((mPntTarget.fValidVentVol & 0x8) != 0) {
          mRdoVolFreshAuto.setEnabled(true);
        } else {
          mRdoVolFreshAuto.setEnabled(false);
        }
        if ((mPntTarget.fValidVentVol & 0x10) != 0) {
          mRdoVolFreshLow.setEnabled(true);
        } else {
          mRdoVolFreshLow.setEnabled(false);
        }
        if ((mPntTarget.fValidVentVol & 0x20) != 0) {
          mRdoVolFreshHigh.setEnabled(true);
        } else {
          mRdoVolFreshHigh.setEnabled(false);
        }
      }
      else
      {
        mRdoVolNormalAuto.setEnabled(true);
        mRdoVolNormalLow.setEnabled(true);
        mRdoVolNormalHigh.setEnabled(true);
        mRdoVolFreshAuto.setEnabled(true);
        mRdoVolFreshLow.setEnabled(true);
        mRdoVolFreshHigh.setEnabled(true);
      }
    }
    else
    {
      mRdoVolNormalAuto.setEnabled(false);
      mRdoVolNormalLow.setEnabled(false);
      mRdoVolNormalHigh.setEnabled(false);
      mRdoVolFreshAuto.setEnabled(false);
      mRdoVolFreshLow.setEnabled(false);
      mRdoVolFreshHigh.setEnabled(false);
    }
  }
  
  private void pntInit()
  {
    initParts();
    mChkVentMode_itemStateChanged();
    mChkVentVol_itemStateChanged();
  }
  
  private void updateInnerSet()
  {
    if ((mChkVentMode.isEnabled() == true) && (mChkVentMode.isSelected() == true))
    {
      if (mRdoModeAuto.isSelected() == true) {
        mPntSet.mVentMode = EnumVentMode.AUTO;
      } else if (mRdoModeVent.isSelected() == true) {
        mPntSet.mVentMode = EnumVentMode.VENTILATION;
      } else if (mRdoModeNormal.isSelected() == true) {
        mPntSet.mVentMode = EnumVentMode.NORMAL;
      } else {
        mPntSet.mVentMode = EnumVentMode.ELSE;
      }
    }
    else {
      mPntSet.mVentMode = EnumVentMode.ELSE;
    }
    if ((mChkVentVol.isEnabled() == true) && (mChkVentVol.isSelected() == true))
    {
      if (mRdoVolNormalAuto.isSelected() == true) {
        mPntSet.mVentVol = EnumVentVol.NORMAL_AUTO;
      } else if (mRdoVolNormalLow.isSelected() == true) {
        mPntSet.mVentVol = EnumVentVol.NORMAL_LOW;
      } else if (mRdoVolNormalHigh.isSelected() == true) {
        mPntSet.mVentVol = EnumVentVol.NORMAL_HIGH;
      } else if (mRdoVolFreshAuto.isSelected() == true) {
        mPntSet.mVentVol = EnumVentVol.FRESH_AUTO;
      } else if (mRdoVolFreshLow.isSelected() == true) {
        mPntSet.mVentVol = EnumVentVol.FRESH_LOW;
      } else if (mRdoVolFreshHigh.isSelected() == true) {
        mPntSet.mVentVol = EnumVentVol.FRESH_HIGH;
      } else {
        mPntSet.mVentVol = EnumVentVol.ELSE;
      }
    }
    else {
      mPntSet.mVentVol = EnumVentVol.ELSE;
    }
  }
}
