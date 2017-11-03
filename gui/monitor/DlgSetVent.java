package webitc.gui.monitor;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import webitc.common.StrRes;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorIcon;
import webitc.gui.common.ColorRes;
import webitc.gui.common.DlgAbstract;

class DlgSetVent
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout10 = new GridBagLayout();
  GridBagLayout gridBagLayout11 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnOK = new JButton();
  JCheckBox mChkFilter = new JCheckBox();
  JCheckBox mChkOnOff = new JCheckBox();
  JCheckBox mChkRKK = new JCheckBox();
  JCheckBox mChkVentMode = new JCheckBox();
  JCheckBox mChkVentVol = new JCheckBox();
  private final String mDetailName;
  ButtonGroup mGrpOnOff = new ButtonGroup();
  ButtonGroup mGrpRkkOnOff = new ButtonGroup();
  ButtonGroup mGrpVentMode = new ButtonGroup();
  ButtonGroup mGrpVentVol = new ButtonGroup();
  JLabel mLblFilter = new JLabel();
  JLabel mLblStatus = new JLabel();
  JLabel mLblVentMode = new JLabel();
  JLabel mLblVentVol = new JLabel();
  JPanel mPnlFilter = new JPanel();
  JPanel mPnlMain = new JPanel();
  JPanel mPnlOnOff = new JPanel();
  JPanel mPnlRKK = new JPanel();
  JPanel mPnlRkkOnOff = new JPanel();
  JPanel mPnlState = new JPanel();
  JPanel mPnlVentMode = new JPanel();
  JPanel mPnlVentVol = new JPanel();
  private final PntCurrent mPntCurrent;
  private PntSet mPntSet = new PntSet();
  private final PntState mPntState;
  private final PntTarget mPntTarget;
  JRadioButton mRdoModeAuto = new JRadioButton();
  JRadioButton mRdoModeNormal = new JRadioButton();
  JRadioButton mRdoModeVent = new JRadioButton();
  JRadioButton mRdoOff = new JRadioButton();
  JRadioButton mRdoOn = new JRadioButton();
  JRadioButton mRdoOnOffPermit = new JRadioButton();
  JRadioButton mRdoOnOffPermitOff = new JRadioButton();
  JRadioButton mRdoOnOffProhibit = new JRadioButton();
  JRadioButton mRdoVolFreshAuto = new JRadioButton();
  JRadioButton mRdoVolFreshHigh = new JRadioButton();
  JRadioButton mRdoVolFreshLow = new JRadioButton();
  JRadioButton mRdoVolNormalAuto = new JRadioButton();
  JRadioButton mRdoVolNormalHigh = new JRadioButton();
  JRadioButton mRdoVolNormalLow = new JRadioButton();
  private final String mShortName;
  JTextField mTxtName = new JTextField();
  JTextField mTxtStatus = new JTextField();
  JTextField mTxtVentMode = new JTextField();
  JTextField mTxtVentVol = new JTextField();
  
  public DlgSetVent(String paramString1, String paramString2, PntState paramPntState, PntTarget paramPntTarget, PntCurrent paramPntCurrent)
  {
    mShortName = paramString1;
    mDetailName = paramString2;
    mPntTarget = paramPntTarget;
    mPntState = paramPntState;
    mPntCurrent = paramPntCurrent;
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
    mChkOnOff.setEnabled(false);
    mChkVentMode.setEnabled(false);
    mChkVentVol.setEnabled(false);
    mRdoOn.setEnabled(false);
    mRdoOff.setEnabled(false);
    mRdoModeAuto.setEnabled(false);
    mRdoModeVent.setEnabled(false);
    mRdoModeNormal.setEnabled(false);
    mRdoVolNormalAuto.setEnabled(false);
    mTxtName.setEnabled(false);
    mRdoOnOffPermit.setEnabled(false);
    mRdoOnOffProhibit.setEnabled(false);
    mRdoOnOffPermitOff.setEnabled(false);
    mChkRKK.setEnabled(false);
    mTxtStatus.setEnabled(false);
    mTxtVentVol.setEnabled(false);
    mTxtVentMode.setEnabled(false);
    mRdoVolNormalLow.setEnabled(false);
    mRdoVolNormalHigh.setEnabled(false);
    mRdoVolFreshAuto.setEnabled(false);
    mRdoVolFreshLow.setEnabled(false);
    mRdoVolFreshHigh.setEnabled(false);
    mChkFilter.setEnabled(false);
    mBtnOK.setEnabled(false);
  }
  
  public PntSet getPntSet()
  {
    return mPntSet;
  }
  
  private void initParts()
  {
    if (!mPntTarget.fValidON) {
      mRdoOn.setEnabled(false);
    }
    if (!mPntTarget.fValidOFF) {
      mRdoOff.setEnabled(false);
    }
    if ((!mPntTarget.fValidON) || (!mPntTarget.fValidOFF)) {
      mChkOnOff.setEnabled(false);
    }
    if (mPntState.fOnOffState == EnumPntStat.ON) {
      mRdoOn.setSelected(true);
    } else {
      mRdoOff.setSelected(true);
    }
    if (mPntCurrent.mVentMode == EnumVentMode.AUTO) {
      mRdoModeAuto.setSelected(true);
    } else if (mPntCurrent.mVentMode == EnumVentMode.VENTILATION) {
      mRdoModeVent.setSelected(true);
    } else if (mPntCurrent.mVentMode == EnumVentMode.NORMAL) {
      mRdoModeNormal.setSelected(true);
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
    }
    if (mPntCurrent.mFilterSign == 0) {
      mChkFilter.setEnabled(false);
    } else {
      mChkFilter.setEnabled(true);
    }
    if (mPntTarget.fRkkValidBit == 0) {
      mChkRKK.setEnabled(false);
    } else {
      mChkRKK.setEnabled(true);
    }
    if ((mPntTarget.fRkkValidBit & 0x1) != 0)
    {
      if ((mPntTarget.fRkkValidBit & 0x2) != 0) {
        mRdoOnOffPermitOff.setEnabled(true);
      } else {
        mRdoOnOffPermitOff.setEnabled(false);
      }
      if (mPntCurrent.mRemoconOnOffMode == EnumRemcCode.DISABLE) {
        mRdoOnOffProhibit.setSelected(true);
      } else if (mPntCurrent.mRemoconOnOffMode == EnumRemcCode.STOP) {
        mRdoOnOffPermitOff.setSelected(true);
      } else {
        mRdoOnOffPermit.setSelected(true);
      }
    }
    else
    {
      mRdoOnOffProhibit.setEnabled(false);
      mRdoOnOffPermitOff.setEnabled(false);
      mRdoOnOffPermit.setEnabled(false);
    }
  }
  
  private void jbInit()
    throws Exception
  {
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSetVent_mBtnCancel_actionAdapter(this));
    getContentPane().setLayout(gridBagLayout1);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSetVent_mBtnOK_actionAdapter(this));
    mPnlMain.setLayout(gridBagLayout2);
    setTitle(StrRes.getStr("IDS_SYSGRPCONF_GRP_SETUP"));
    mPnlOnOff.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_ONOFF")));
    mPnlOnOff.setLayout(gridBagLayout3);
    mPnlVentMode.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_VENT_MODE")));
    mPnlVentMode.setLayout(gridBagLayout4);
    mPnlVentVol.setLayout(gridBagLayout5);
    mPnlVentVol.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_VENT_VOL")));
    mPnlState.setLayout(gridBagLayout7);
    mPnlState.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_STATUS")));
    mPnlState.setDebugGraphicsOptions(0);
    mLblVentVol.setText(StrRes.getStr("IDS_COMMON_VENT_VOL"));
    mLblStatus.setText(StrRes.getStr("IDS_COMMON_STATUS"));
    mLblVentMode.setText(StrRes.getStr("IDS_COMMON_VENT_MODE"));
    mChkOnOff.addItemListener(new DlgSetVent_mChkOnOff_itemAdapter(this));
    mChkVentMode.addItemListener(new DlgSetVent_mChkVentMode_itemAdapter(this));
    mTxtName.setEditable(false);
    mTxtName.setText("");
    mRdoOnOffPermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffPermit.setText(StrRes.getStr("IDS_COMMON_RC_PERMIT"));
    mPnlRKK.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_RC_ONOFF_SETUP") + " ( " + StrRes.getStr("IDS_WEBACSET_ONOFF") + " )"));
    mPnlRKK.setLayout(gridBagLayout10);
    mRdoOnOffProhibit.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffProhibit.setText(StrRes.getStr("IDS_COMMON_RC_PROHIBIT"));
    mRdoOnOffPermitOff.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffPermitOff.setText(StrRes.getStr("IDS_COMMON_RC_ONOFF_STOPONLY"));
    mChkRKK.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRKK.addItemListener(new DlgSetVent_mChkRKK_itemAdapter(this));
    mChkRKK.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRKK.setMargin(new Insets(0, 2, 0, 2));
    mPnlRkkOnOff.setLayout(gridBagLayout11);
    mChkOnOff.setMargin(new Insets(0, 2, 0, 2));
    mChkOnOff.setMnemonic('0');
    mRdoOn.setMargin(new Insets(0, 2, 0, 2));
    mRdoOff.setMargin(new Insets(0, 2, 0, 2));
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
    mChkVentVol.addItemListener(new DlgSetVent_mChkVentVol_itemAdapter(this));
    mRdoVolNormalAuto.setMargin(new Insets(0, 2, 0, 2));
    mRdoVolNormalAuto.setMnemonic('0');
    mLblFilter.setToolTipText("");
    mLblFilter.setHorizontalAlignment(2);
    mLblFilter.setHorizontalTextPosition(2);
    mLblFilter.setIconTextGap(10);
    mLblFilter.setText(StrRes.getStr("IDS_WEBACSET_FILTER"));
    mTxtStatus.setEditable(false);
    mTxtVentVol.setEditable(false);
    mTxtVentMode.setEditable(false);
    mRdoVolNormalLow.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_LOW"));
    mRdoVolNormalHigh.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_HIGH"));
    mRdoVolFreshAuto.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_AUTO"));
    mRdoVolFreshLow.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_LOW"));
    mRdoVolFreshHigh.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_HIGH"));
    mPnlFilter.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_FILTER")));
    mPnlFilter.setLayout(gridBagLayout6);
    mChkFilter.setMargin(new Insets(0, 2, 0, 2));
    mChkFilter.setText(StrRes.getStr("IDS_WEBACSET_RESET"));
    getContentPane().add(mBtnOK, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 13, 0, new Insets(5, 0, 6, 5), 5, 0));
    getContentPane().add(mBtnCancel, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 2, 6, 12), 0, 0));
    getContentPane().add(mPnlMain, new GridBagConstraints(0, 0, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(5, 5, 5, 5), 0, 0));
    mChkOnOff.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkOnOff.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentMode.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentMode.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentVol.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentVol.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mRdoOn.setText(StrRes.getStr("IDS_COMMON_START"));
    mRdoOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    mRdoModeAuto.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_AUTO"));
    mRdoModeVent.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_VENT"));
    mRdoModeNormal.setText(StrRes.getStr("IDS_COMMON_VENT_MODE_NORMAL"));
    mRdoVolNormalAuto.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_AUTO"));
    mPnlMain.add(mPnlOnOff, new GridBagConstraints(0, 2, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOnOff.add(mChkOnOff, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOnOff.add(mRdoOn, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlOnOff.add(mRdoOff, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlMain.add(mPnlVentMode, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentMode.add(mRdoModeAuto, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlVentMode.add(mChkVentMode, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 2, 0));
    mPnlVentMode.add(mRdoModeVent, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlVentMode.add(mRdoModeNormal, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 2), 4, 0));
    mPnlMain.add(mPnlVentVol, new GridBagConstraints(0, 4, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mChkVentVol, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 11, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolNormalAuto, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlMain.add(mPnlState, new GridBagConstraints(0, 1, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlState.add(mLblStatus, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(2, 2, 2, 2), 0, 0));
    mPnlState.add(mLblVentVol, new GridBagConstraints(0, 1, 1, 2, 0.0D, 0.0D, 13, 0, new Insets(2, 2, 2, 2), 0, 0));
    mPnlState.add(mLblVentMode, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(2, 5, 2, 2), 0, 0));
    mPnlState.add(mLblFilter, new GridBagConstraints(2, 1, 2, 2, 0.0D, 0.0D, 17, 2, new Insets(2, 5, 2, 2), 0, 0));
    mPnlState.add(mTxtStatus, new GridBagConstraints(1, 0, 1, 1, 0.5D, 0.0D, 17, 2, new Insets(2, 0, 2, 0), 0, 0));
    mPnlState.add(mTxtVentVol, new GridBagConstraints(1, 1, 1, 1, 0.5D, 0.0D, 17, 2, new Insets(2, 0, 2, 0), 0, 0));
    mPnlState.add(mTxtVentMode, new GridBagConstraints(3, 0, 1, 1, 0.5D, 0.0D, 17, 2, new Insets(2, 0, 2, 0), 0, 0));
    mPnlMain.add(mTxtName, new GridBagConstraints(0, 0, 3, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlMain.add(mPnlRKK, new GridBagConstraints(0, 6, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRKK.add(mChkRKK, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 2, 0));
    mPnlRKK.add(mPnlRkkOnOff, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkOnOff.add(mRdoOnOffProhibit, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkOnOff.add(mRdoOnOffPermitOff, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkOnOff.add(mRdoOnOffPermit, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlMain.add(mPnlFilter, new GridBagConstraints(0, 5, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFilter.add(mChkFilter, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    mGrpOnOff.add(mRdoOn);
    mGrpOnOff.add(mRdoOff);
    mGrpVentMode.add(mRdoModeAuto);
    mGrpVentMode.add(mRdoModeVent);
    mGrpVentMode.add(mRdoModeNormal);
    mGrpVentVol.add(mRdoVolNormalAuto);
    mGrpRkkOnOff.add(mRdoOnOffPermit);
    mGrpRkkOnOff.add(mRdoOnOffPermitOff);
    mGrpRkkOnOff.add(mRdoOnOffProhibit);
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
  
  void mChkOnOff_itemStateChanged()
  {
    if (mChkOnOff.isSelected() == true)
    {
      mRdoOn.setEnabled(true);
      mRdoOff.setEnabled(true);
    }
    else
    {
      mRdoOn.setEnabled(false);
      mRdoOff.setEnabled(false);
    }
  }
  
  void mChkRKK_itemStateChanged()
  {
    if (mChkRKK.isSelected() == true)
    {
      if ((mPntTarget.fRkkValidBit & 0x1) != 0)
      {
        mRdoOnOffPermit.setEnabled(true);
        mRdoOnOffProhibit.setEnabled(true);
      }
      else
      {
        mRdoOnOffPermit.setEnabled(false);
        mRdoOnOffProhibit.setEnabled(false);
      }
      if ((mPntTarget.fRkkValidBit & 0x2) != 0) {
        mRdoOnOffPermitOff.setEnabled(true);
      } else {
        mRdoOnOffPermitOff.setEnabled(false);
      }
    }
    else
    {
      mRdoOnOffPermit.setEnabled(false);
      mRdoOnOffPermitOff.setEnabled(false);
      mRdoOnOffProhibit.setEnabled(false);
    }
  }
  
  void mChkVentMode_itemStateChanged()
  {
    if (mChkVentMode.isSelected() == true)
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
      mRdoModeAuto.setEnabled(false);
      mRdoModeVent.setEnabled(false);
      mRdoModeNormal.setEnabled(false);
    }
  }
  
  void mChkVentVol_itemStateChanged()
  {
    if (mChkVentVol.isSelected() == true)
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
    mTxtName.setText(mShortName + " ( " + mDetailName + " )");
    mTxtStatus.setText(mPntState.getStr());
    mTxtVentVol.setText(mPntCurrent.mVentVol.getStr());
    mTxtVentMode.setText(mPntCurrent.mVentMode.getStr());
    if (mPntCurrent.mFilterSign != 0) {
      mLblFilter.setIcon(new ColorIcon(ColorRes.FILTER_SIGN));
    }
    initParts();
    mChkOnOff_itemStateChanged();
    mChkVentMode_itemStateChanged();
    mChkVentVol_itemStateChanged();
    mChkRKK_itemStateChanged();
  }
  
  private void updateInnerSet()
  {
    if ((mChkOnOff.isEnabled() == true) && (mChkOnOff.isSelected() == true))
    {
      if (mRdoOn.isSelected() == true) {
        mPntSet.mOnOffMode = EnumPntStat.ON;
      } else if (mRdoOff.isSelected() == true) {
        mPntSet.mOnOffMode = EnumPntStat.OFF;
      } else {
        mPntSet.mOnOffMode = EnumPntStat.ELSE;
      }
    }
    else {
      mPntSet.mOnOffMode = EnumPntStat.ELSE;
    }
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
    if (mChkFilter.isSelected() == true) {
      mPntSet.mFilterClear = true;
    } else {
      mPntSet.mFilterClear = false;
    }
    if (mChkRKK.isSelected() == true)
    {
      if (mRdoOnOffProhibit.isSelected() == true) {
        mPntSet.mRemoconOnOffMode = EnumRemcCode.DISABLE;
      } else if (mRdoOnOffPermitOff.isSelected() == true) {
        mPntSet.mRemoconOnOffMode = EnumRemcCode.STOP;
      } else {
        mPntSet.mRemoconOnOffMode = EnumRemcCode.ENABLE;
      }
      mPntSet.mRemoconRunMode = EnumPntStat.ELSE;
      mPntSet.mRemoconTempMode = EnumPntStat.ELSE;
    }
    else
    {
      mPntSet.mRemoconOnOffMode = EnumRemcCode.ELSE;
      mPntSet.mRemoconRunMode = EnumPntStat.ELSE;
      mPntSet.mRemoconTempMode = EnumPntStat.ELSE;
    }
  }
}
