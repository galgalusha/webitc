package webitc.gui.monitor;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumPntType;
import webitc.common.enum2.EnumRemcCode;
import webitc.data.Temperature;
import webitc.data.point.PntCurrent;
import webitc.data.point.PntSet;
import webitc.data.point.PntState;
import webitc.data.point.PntTarget;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorIcon;
import webitc.gui.common.ColorRes;
import webitc.gui.common.DlgAbstract;

class DlgSetInner
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout10 = new GridBagLayout();
  GridBagLayout gridBagLayout11 = new GridBagLayout();
  GridBagLayout gridBagLayout12 = new GridBagLayout();
  GridBagLayout gridBagLayout13 = new GridBagLayout();
  GridBagLayout gridBagLayout14 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  GridBagLayout gridBagLayout8 = new GridBagLayout();
  GridBagLayout gridBagLayout9 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnOK = new JButton();
  JButton mBtnVent = new JButton();
  JCheckBox mChkDrvMode = new JCheckBox();
  JCheckBox mChkFanDir = new JCheckBox();
  JCheckBox mChkFanVol = new JCheckBox();
  JCheckBox mChkFilter = new JCheckBox();
  JCheckBox mChkOnOff = new JCheckBox();
  JCheckBox mChkRKKDrvMode = new JCheckBox();
  JCheckBox mChkRKKOnOff = new JCheckBox();
  JCheckBox mChkRKKSetTemp = new JCheckBox();
  JCheckBox mChkSetTemp = new JCheckBox();
  JComboBox mCmbFanDir = new JComboBox();
  JComboBox mCmbSetTemp1 = new JComboBox();
  JComboBox mCmbSetTemp10 = new JComboBox();
  private final String mDetailName;
  private final String mDrvVentModeStr;
  ButtonGroup mGrpDrvMode = new ButtonGroup();
  ButtonGroup mGrpFan = new ButtonGroup();
  ButtonGroup mGrpOnOff = new ButtonGroup();
  ButtonGroup mGrpRkkDrvMode = new ButtonGroup();
  ButtonGroup mGrpRkkOnOff = new ButtonGroup();
  ButtonGroup mGrpRkkSetTemp = new ButtonGroup();
  JLabel mLblDot = new JLabel();
  JLabel mLblDrvMode = new JLabel();
  JLabel mLblFanDir1 = new JLabel();
  JLabel mLblFanDir2 = new JLabel();
  JLabel mLblFilter = new JLabel();
  JLabel mLblRoomTemp = new JLabel();
  JLabel mLblStatus = new JLabel();
  JLabel mLblTemp = new JLabel();
  JPanel mPnlDrvMode = new JPanel();
  JPanel mPnlFanDir = new JPanel();
  JPanel mPnlFanVol = new JPanel();
  JPanel mPnlFilter = new JPanel();
  JPanel mPnlMain = new JPanel();
  JPanel mPnlOKCancel = new JPanel();
  JPanel mPnlOnOff = new JPanel();
  JPanel mPnlRKK = new JPanel();
  JPanel mPnlRkkDrvMode = new JPanel();
  JPanel mPnlRkkOnOff = new JPanel();
  JPanel mPnlRkkSetTemp = new JPanel();
  JPanel mPnlSetTemp = new JPanel();
  JPanel mPnlState = new JPanel();
  private final PntCurrent mPntCurrent;
  private PntSet mPntSet = new PntSet();
  private final PntState mPntState;
  private final PntTarget mPntTarget;
  private final EnumPntType mPntType;
  JRadioButton mRdoAuto = new JRadioButton();
  JRadioButton mRdoCool = new JRadioButton();
  JRadioButton mRdoDrvModePermit = new JRadioButton();
  JRadioButton mRdoDrvModeProhibit = new JRadioButton();
  JRadioButton mRdoFan = new JRadioButton();
  JRadioButton mRdoFan1 = new JRadioButton();
  JRadioButton mRdoFan7 = new JRadioButton();
  JRadioButton mRdoHeat = new JRadioButton();
  JRadioButton mRdoOff = new JRadioButton();
  JRadioButton mRdoOn = new JRadioButton();
  JRadioButton mRdoOnOffPermit = new JRadioButton();
  JRadioButton mRdoOnOffPermitOff = new JRadioButton();
  JRadioButton mRdoOnOffProhibit = new JRadioButton();
  JRadioButton mRdoSetTempPermit = new JRadioButton();
  JRadioButton mRdoSetTempProhibit = new JRadioButton();
  JRadioButton mRdoTemp = new JRadioButton();
  private final String mShortName;
  JTextField mTxtDrvMode = new JTextField();
  JTextField mTxtName = new JTextField();
  JTextField mTxtRoomTemp = new JTextField();
  JTextField mTxtStatus = new JTextField();
  
  public DlgSetInner(EnumPntType paramEnumPntType, String paramString1, String paramString2, String paramString3, PntState paramPntState, PntTarget paramPntTarget, PntCurrent paramPntCurrent)
  {
    mPntType = paramEnumPntType;
    mShortName = paramString1;
    mDetailName = paramString2;
    mDrvVentModeStr = paramString3;
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
    mChkDrvMode.setEnabled(false);
    mChkFanVol.setEnabled(false);
    mChkSetTemp.setEnabled(false);
    mRdoOn.setEnabled(false);
    mRdoOff.setEnabled(false);
    mRdoCool.setEnabled(false);
    mRdoHeat.setEnabled(false);
    mRdoFan.setEnabled(false);
    mRdoTemp.setEnabled(false);
    mRdoAuto.setEnabled(false);
    mRdoFan7.setEnabled(false);
    mRdoFan1.setEnabled(false);
    mCmbSetTemp10.setEnabled(false);
    mTxtName.setEnabled(false);
    mChkFilter.setEnabled(false);
    mRdoSetTempPermit.setEnabled(false);
    mRdoOnOffPermit.setEnabled(false);
    mRdoSetTempProhibit.setEnabled(false);
    mRdoDrvModePermit.setEnabled(false);
    mRdoOnOffProhibit.setEnabled(false);
    mRdoDrvModeProhibit.setEnabled(false);
    mRdoOnOffPermitOff.setEnabled(false);
    mChkRKKOnOff.setEnabled(false);
    mChkRKKDrvMode.setEnabled(false);
    mChkRKKSetTemp.setEnabled(false);
    mChkFanDir.setEnabled(false);
    mCmbFanDir.setEnabled(false);
    mTxtStatus.setEnabled(false);
    mTxtDrvMode.setEnabled(false);
    mTxtRoomTemp.setEnabled(false);
    mBtnVent.setEnabled(false);
    mCmbSetTemp1.setEnabled(false);
    mBtnOK.setEnabled(false);
  }
  
  public PntSet getPntSet()
  {
    return mPntSet;
  }
  
  private void initParts()
  {
    if (mPntType == EnumPntType.ZONE) {
      mLblDrvMode.setText(StrRes.getStr("IDS_WEBMAIN_LIST_DRVMODE"));
    } else {
      mLblDrvMode.setText(StrRes.getStr("IDS_COMMON_DRVMODE"));
    }
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
    if (mPntTarget.fValidRunMode == 0) {
      mChkDrvMode.setEnabled(false);
    }
    if ((mPntCurrent.mDrvMode == EnumDrvMode.COOL) || (mPntCurrent.mDrvMode == EnumDrvMode.DRY)) {
      mRdoCool.setSelected(true);
    } else if (mPntCurrent.mDrvMode == EnumDrvMode.HEAT) {
      mRdoHeat.setSelected(true);
    } else if (mPntCurrent.mDrvMode == EnumDrvMode.FAN) {
      mRdoFan.setSelected(true);
    } else if (mPntCurrent.mDrvMode == EnumDrvMode.SUBMIT) {
      mRdoTemp.setSelected(true);
    } else if ((mPntCurrent.mDrvMode == EnumDrvMode.AUTOCOOL) || (mPntCurrent.mDrvMode == EnumDrvMode.AUTOHEAT)) {
      mRdoAuto.setSelected(true);
    } else if ((mPntTarget.fValidRunMode & 0x4) != 0) {
      mRdoCool.setSelected(true);
    } else if ((mPntTarget.fValidRunMode & 0x2) != 0) {
      mRdoHeat.setSelected(true);
    } else if ((mPntTarget.fValidRunMode & 0x1) != 0) {
      mRdoFan.setSelected(true);
    } else if ((mPntTarget.fValidRunMode & 0x10) != 0) {
      mRdoTemp.setSelected(true);
    } else if (((mPntTarget.fValidRunMode & 0x200) != 0) || ((mPntTarget.fValidRunMode & 0x100) != 0)) {
      mRdoAuto.setSelected(true);
    } else {
      mRdoCool.setSelected(true);
    }
    mCmbSetTemp10.removeAllItems();
    int i;
    if (mPntTarget.fValidSetTemp == true)
    {
      int k;
      int m;
      int n;
      if (SystemInfo.isCentigrade() == true)
      {
        Temperature localTemperature1 = mPntTarget.getSetTempLower();
        Temperature localTemperature2 = mPntTarget.getSetTempUpper();
        k = localTemperature1.getCentigradeInteger();
        m = localTemperature2.getCentigradeInteger();
        if ((localTemperature2.getCentigradeInteger() < 0) && (mPntTarget.fTempStep > 0.1F)) {
          if (mPntTarget.fTempStep <= 0.5F)
          {
            if (localTemperature2.getCentigradeDecimal() > 5) {
              m--;
            }
          }
          else if (localTemperature2.getCentigradeDecimal() != 0) {
            m--;
          }
        }
        if ((localTemperature1.getCentigradeInteger() > 0) && (mPntTarget.fTempStep > 0.1F)) {
          if (mPntTarget.fTempStep <= 0.5F)
          {
            if (localTemperature1.getCentigradeDecimal() > 5) {
              k++;
            }
          }
          else if (localTemperature1.getCentigradeDecimal() != 0) {
            k++;
          }
        }
        for (n = k; n <= m; n++) {
          mCmbSetTemp10.addItem(" " + n + " ");
        }
        int i2 = mPntCurrent.mSetTemp.getCentigradeInteger();
        int i1;
        if ((i2 - k < 0) || (i2 - k > m - k)) {
          i1 = 0;
        } else {
          i1 = i2 - k;
        }
        mCmbSetTemp10.setSelectedIndex(i1);
        mCmbSetTemp1.removeAllItems();
        if (mPntTarget.fTempStep <= 0.1F)
        {
          for (int i3 = 0; i3 <= 9; i3++) {
            mCmbSetTemp1.addItem(" " + i3 + " ");
          }
          i1 = mPntCurrent.mSetTemp.getCentigradeDecimal();
          mCmbSetTemp1.setSelectedIndex(i1);
        }
        else if (mPntTarget.fTempStep <= 0.5F)
        {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
          mCmbSetTemp1.addItem(" " + Integer.toString(5) + " ");
          if (mPntCurrent.mSetTemp.getDecimal(1) == 0) {
            mCmbSetTemp1.setSelectedIndex(0);
          } else {
            mCmbSetTemp1.setSelectedIndex(1);
          }
        }
        else
        {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
        }
      }
      else
      {
        i = mPntTarget.getSetTempLower().getFahrenheit();
        int j = mPntTarget.getSetTempUpper().getFahrenheit();
        for (k = i; k <= j; k++) {
          mCmbSetTemp10.addItem(" " + k + " ");
        }
        n = mPntCurrent.mSetTemp.getFahrenheit();
        if ((n - i < 0) || (n - i > j - i)) {
          m = 0;
        } else {
          m = n - i;
        }
        mCmbSetTemp10.setSelectedIndex(m);
        mCmbSetTemp1.setVisible(false);
        mLblDot.setVisible(false);
        mLblTemp.setText(StrRes.getStr("IDS_COMMON_UNIT_TEMP_F"));
      }
      mCmbSetTemp10_actionPerformed();
    }
    else
    {
      mChkSetTemp.setEnabled(false);
      mCmbSetTemp10.setEnabled(false);
      mCmbSetTemp1.setEnabled(false);
    }
    if (mPntTarget.fValidWindVolume == 0)
    {
      mChkFanVol.setEnabled(false);
      mRdoFan7.setEnabled(false);
      mRdoFan1.setEnabled(false);
    }
    else if (mPntCurrent.mWindVolume == 0)
    {
      mRdoFan1.setSelected(true);
    }
    else
    {
      mRdoFan7.setSelected(true);
    }
    if (!mPntTarget.fValidWindDirect)
    {
      mChkFanDir.setEnabled(false);
      mCmbFanDir.setEnabled(false);
    }
    else
    {
      for (i = 0; i <= 7; i++) {
        mCmbFanDir.addItem(" " + i + " ");
      }
      if (mPntCurrent.mWindDirect != -1) {
        mCmbFanDir.setSelectedIndex(mPntCurrent.mWindDirect);
      } else {
        mCmbFanDir.setSelectedIndex(0);
      }
    }
    if (mPntCurrent.mFilterSign == 0) {
      mChkFilter.setEnabled(false);
    } else {
      mChkFilter.setEnabled(true);
    }
    if ((mPntTarget.fRkkValidBit & 0x1) != 0)
    {
      mChkRKKOnOff.setEnabled(true);
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
      mChkRKKOnOff.setEnabled(false);
    }
    if ((mPntTarget.fRkkValidBit & 0x4) != 0)
    {
      mChkRKKDrvMode.setEnabled(true);
      if (mPntCurrent.mRemoconRunMode == EnumPntStat.OFF) {
        mRdoDrvModeProhibit.setSelected(true);
      } else {
        mRdoDrvModePermit.setSelected(true);
      }
    }
    else
    {
      mChkRKKDrvMode.setEnabled(false);
    }
    if ((mPntTarget.fRkkValidBit & 0x8) != 0)
    {
      mChkRKKSetTemp.setEnabled(true);
      if (mPntCurrent.mRemoconTempMode == EnumPntStat.OFF) {
        mRdoSetTempProhibit.setSelected(true);
      } else {
        mRdoSetTempPermit.setSelected(true);
      }
    }
    else
    {
      mChkRKKSetTemp.setEnabled(false);
    }
    mChkRKKOnOff_itemStateChanged();
    mChkRKKDrvMode_itemStateChanged();
    mChkRKKSetTemp_itemStateChanged();
  }
  
  private void jbInit()
    throws Exception
  {
    if (mPntType == EnumPntType.ZONE)
    {
      setTitle(StrRes.getStr("IDS_SYSZONECONF_ZONE_SETUP"));
      if ((mPntTarget.fValidVentMode == 0) && (mPntTarget.fValidVentVol == 0)) {
        mBtnVent.setEnabled(false);
      }
    }
    else
    {
      setTitle(StrRes.getStr("IDS_SYSGRPCONF_GRP_SETUP"));
      mBtnVent.setVisible(false);
    }
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSetInner_mBtnCancel_actionAdapter(this));
    getContentPane().setLayout(gridBagLayout1);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSetInner_mBtnOK_actionAdapter(this));
    mPnlMain.setLayout(gridBagLayout2);
    mPnlOnOff.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_ONOFF")));
    mPnlOnOff.setLayout(gridBagLayout3);
    mPnlDrvMode.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_DRVMODE")));
    mPnlDrvMode.setLayout(gridBagLayout4);
    mPnlFanVol.setLayout(gridBagLayout5);
    mPnlFanVol.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_FAN")));
    mPnlSetTemp.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_SETTEMP")));
    mPnlSetTemp.setLayout(gridBagLayout6);
    mPnlState.setLayout(gridBagLayout7);
    mPnlState.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_STATUS")));
    mPnlState.setDebugGraphicsOptions(0);
    mLblStatus.setText(StrRes.getStr("IDS_COMMON_STATUS"));
    mLblRoomTemp.setText(StrRes.getStr("IDS_COMMON_ROOMTEMP"));
    mChkOnOff.addItemListener(new DlgSetInner_mChkOnOff_itemAdapter(this));
    mChkDrvMode.addItemListener(new DlgSetInner_mChkDrvMode_itemAdapter(this));
    mChkFanVol.addItemListener(new DlgSetInner_mChkFanVol_itemAdapter(this));
    mChkSetTemp.addItemListener(new DlgSetInner_mChkSetTemp_itemAdapter(this));
    mRdoCool.addActionListener(new DlgSetInner_mRdoCool_actionAdapter(this));
    mRdoHeat.addActionListener(new DlgSetInner_mRdoHeat_actionAdapter(this));
    mRdoFan.addActionListener(new DlgSetInner_mRdoFan_actionAdapter(this));
    mRdoTemp.addActionListener(new DlgSetInner_mRdoTemp_actionAdapter(this));
    mRdoAuto.addActionListener(new DlgSetInner_mRdoAuto_actionAdapter(this));
    mTxtName.setEditable(false);
    mTxtName.setText("");
    mChkFilter.setText(StrRes.getStr("IDS_WEBACSET_RESET"));
    mChkFilter.setActionCommand(StrRes.getStr("IDS_WEBACSET_RESET"));
    mChkFilter.setMargin(new Insets(0, 2, 0, 2));
    mPnlFilter.setLayout(gridBagLayout8);
    mPnlFilter.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_FILTER")));
    mRdoSetTempPermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoSetTempPermit.setText(StrRes.getStr("IDS_COMMON_RC_PERMIT"));
    mRdoOnOffPermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffPermit.setText(StrRes.getStr("IDS_COMMON_RC_PERMIT"));
    mRdoSetTempProhibit.setMargin(new Insets(0, 2, 0, 2));
    mRdoSetTempProhibit.setText(StrRes.getStr("IDS_COMMON_RC_PROHIBIT"));
    mPnlRkkDrvMode.setLayout(gridBagLayout9);
    mPnlRKK.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_RC_ONOFF_SETUP")));
    mPnlRKK.setLayout(gridBagLayout10);
    mPnlRkkSetTemp.setLayout(gridBagLayout12);
    mPnlRkkSetTemp.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_RC_SETPOINT_SETUP")));
    mRdoDrvModePermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoDrvModePermit.setText(StrRes.getStr("IDS_COMMON_RC_PERMIT"));
    mRdoOnOffProhibit.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffProhibit.setText(StrRes.getStr("IDS_COMMON_RC_PROHIBIT"));
    mRdoDrvModeProhibit.setMargin(new Insets(0, 2, 0, 2));
    mRdoDrvModeProhibit.setText(StrRes.getStr("IDS_COMMON_RC_PROHIBIT"));
    mRdoOnOffPermitOff.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffPermitOff.setText(StrRes.getStr("IDS_COMMON_RC_ONOFF_STOPONLY"));
    mChkRKKOnOff.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRKKOnOff.addItemListener(new DlgSetInner_mChkRKKOnOff_itemAdapter(this));
    mChkRKKOnOff.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mPnlRkkOnOff.setLayout(gridBagLayout11);
    mPnlRkkOnOff.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_ONOFF")));
    mPnlRkkDrvMode.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_RC_MODE_SETUP")));
    mPnlFanDir.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_FAN_DIR")));
    mPnlFanDir.setLayout(gridBagLayout13);
    mChkFanDir.setMargin(new Insets(0, 2, 0, 2));
    mChkFanDir.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkFanDir.addItemListener(new DlgSetInner_mChkFanDir_itemAdapter(this));
    mChkOnOff.setMargin(new Insets(0, 2, 0, 2));
    mChkOnOff.setMnemonic('0');
    mRdoOn.setMargin(new Insets(0, 2, 0, 2));
    mRdoOff.setMargin(new Insets(0, 2, 0, 2));
    mChkDrvMode.setMargin(new Insets(0, 2, 0, 2));
    mChkDrvMode.setMnemonic('0');
    mRdoCool.setMargin(new Insets(0, 2, 0, 2));
    mRdoCool.setMnemonic('0');
    mRdoHeat.setMargin(new Insets(0, 2, 0, 2));
    mRdoHeat.setMnemonic('0');
    mRdoFan.setMargin(new Insets(0, 2, 0, 2));
    mRdoFan.setMnemonic('0');
    mRdoTemp.setMargin(new Insets(0, 2, 0, 2));
    mRdoTemp.setMnemonic('0');
    mRdoAuto.setMargin(new Insets(0, 2, 0, 2));
    mRdoAuto.setMnemonic('0');
    mChkSetTemp.setMargin(new Insets(0, 2, 0, 2));
    mChkFanVol.setMargin(new Insets(0, 2, 0, 2));
    mChkFanVol.setMnemonic('0');
    mRdoFan1.setMargin(new Insets(0, 2, 0, 2));
    mRdoFan1.setMnemonic('0');
    mRdoFan7.setMargin(new Insets(0, 2, 0, 2));
    mRdoFan7.setMnemonic('0');
    mLblFilter.setToolTipText("");
    mLblFilter.setHorizontalAlignment(2);
    mLblFilter.setHorizontalTextPosition(2);
    mLblFilter.setIconTextGap(10);
    mLblFilter.setText(StrRes.getStr("IDS_WEBACSET_FILTER"));
    mTxtStatus.setEditable(false);
    mTxtStatus.setText("jTextField1");
    mTxtDrvMode.setEditable(false);
    mTxtDrvMode.setText("jTextField1");
    mTxtRoomTemp.setEditable(false);
    mTxtRoomTemp.setText("jTextField1");
    mPnlOKCancel.setLayout(gridBagLayout14);
    mPnlOKCancel.setToolTipText("");
    mPnlOKCancel.setInputVerifier(null);
    mBtnVent.setText(StrRes.getStr("IDS_COMMON_VENT_SET"));
    mBtnVent.addActionListener(new DlgSetInner_mBtnVent_actionAdapter(this));
    mLblDot.setText(StrRes.getStr("IDS_UTILTENKEY_DOT"));
    mLblTemp.setText(StrRes.getStr("IDS_COMMON_UNIT_TEMP"));
    mCmbSetTemp10.addActionListener(new DlgSetInner_mCmbSetTemp10_actionAdapter(this));
    mChkRKKDrvMode.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRKKDrvMode.addItemListener(new DlgSetInner_mChkRKKDrvMode_itemAdapter(this));
    mChkRKKDrvMode.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRKKSetTemp.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRKKSetTemp.addItemListener(new DlgSetInner_mChkRKKSetTemp_itemAdapter(this));
    mChkRKKSetTemp.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mLblFanDir1.setText("   ");
    mLblFanDir2.setText("      ");
    getContentPane().add(mPnlMain, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 10, 3, new Insets(5, 5, 5, 5), 0, 0));
    mChkOnOff.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkOnOff.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkDrvMode.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkDrvMode.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkFanVol.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkFanVol.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkSetTemp.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkSetTemp.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mRdoOn.setText(StrRes.getStr("IDS_COMMON_START"));
    mRdoOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    mRdoCool.setText(StrRes.getStr("IDS_COMMON_MODE_COOL"));
    mRdoHeat.setText(StrRes.getStr("IDS_COMMON_MODE_HEAT"));
    mRdoFan.setText(StrRes.getStr("IDS_COMMON_MODE_FAN"));
    mRdoTemp.setText(StrRes.getStr("IDS_COMMON_MODE_SETPOINT"));
    mRdoAuto.setText(StrRes.getStr("IDS_COMMON_MODE_AUTO"));
    mRdoFan7.setText(StrRes.getStr("IDS_COMMON_FAN_STEP_HIGH"));
    mRdoFan1.setText(StrRes.getStr("IDS_COMMON_FAN_STEP_LOW"));
    mPnlMain.add(mPnlOnOff, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOnOff.add(mChkOnOff, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOnOff.add(mRdoOn, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlOnOff.add(mRdoOff, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlMain.add(mPnlDrvMode, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlDrvMode.add(mRdoCool, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlDrvMode.add(mChkDrvMode, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlDrvMode.add(mRdoHeat, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlDrvMode.add(mRdoAuto, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlDrvMode.add(mRdoTemp, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlDrvMode.add(mRdoFan, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 2), 4, 0));
    mPnlMain.add(mPnlFanVol, new GridBagConstraints(0, 4, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFanVol.add(mChkFanVol, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 11, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFanVol.add(mRdoFan7, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlFanVol.add(mRdoFan1, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlMain.add(mPnlSetTemp, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlSetTemp.add(mChkSetTemp, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlSetTemp.add(mCmbSetTemp10, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlSetTemp.add(mCmbSetTemp1, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlSetTemp.add(mLblDot, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlSetTemp.add(mLblTemp, new GridBagConstraints(4, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlMain.add(mPnlState, new GridBagConstraints(0, 1, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlState.add(mLblStatus, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(2, 2, 2, 2), 0, 0));
    mPnlState.add(mLblDrvMode, new GridBagConstraints(0, 1, 1, 2, 0.0D, 0.0D, 13, 0, new Insets(2, 2, 2, 2), 0, 0));
    mPnlState.add(mLblRoomTemp, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(2, 5, 2, 2), 0, 0));
    mPnlState.add(mLblFilter, new GridBagConstraints(2, 1, 2, 2, 0.0D, 0.0D, 17, 2, new Insets(2, 5, 2, 2), 0, 0));
    mPnlState.add(mTxtStatus, new GridBagConstraints(1, 0, 1, 1, 0.5D, 0.0D, 17, 2, new Insets(2, 0, 2, 0), 0, 0));
    mPnlState.add(mTxtDrvMode, new GridBagConstraints(1, 1, 1, 1, 0.5D, 0.0D, 17, 2, new Insets(2, 0, 2, 0), 0, 0));
    mPnlState.add(mTxtRoomTemp, new GridBagConstraints(3, 0, 1, 1, 0.5D, 0.0D, 17, 2, new Insets(2, 0, 2, 0), 0, 0));
    mPnlMain.add(mTxtName, new GridBagConstraints(0, 0, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlMain.add(mPnlFilter, new GridBagConstraints(1, 3, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFilter.add(mChkFilter, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 20), 0, 0));
    mPnlMain.add(mPnlRKK, new GridBagConstraints(0, 5, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRKK.add(mPnlRkkOnOff, new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkOnOff.add(mRdoOnOffProhibit, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkOnOff.add(mRdoOnOffPermitOff, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkOnOff.add(mRdoOnOffPermit, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkOnOff.add(mChkRKKOnOff, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 2, 0));
    mPnlRKK.add(mPnlRkkDrvMode, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkDrvMode.add(mRdoDrvModeProhibit, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkDrvMode.add(mRdoDrvModePermit, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkDrvMode.add(mChkRKKDrvMode, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 2, 0));
    mPnlRKK.add(mPnlRkkSetTemp, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkSetTemp.add(mRdoSetTempProhibit, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkSetTemp.add(mRdoSetTempPermit, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 4, 0));
    mPnlRkkSetTemp.add(mChkRKKSetTemp, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 2, 0));
    mGrpOnOff.add(mRdoOn);
    mGrpOnOff.add(mRdoOff);
    mGrpDrvMode.add(mRdoCool);
    mGrpDrvMode.add(mRdoHeat);
    mGrpDrvMode.add(mRdoFan);
    mGrpDrvMode.add(mRdoTemp);
    mGrpDrvMode.add(mRdoAuto);
    mGrpFan.add(mRdoFan7);
    mGrpFan.add(mRdoFan1);
    mPnlMain.add(mPnlFanDir, new GridBagConstraints(1, 4, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFanDir.add(mChkFanDir, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFanDir.add(mCmbFanDir, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFanDir.add(mLblFanDir1, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlFanDir.add(mLblFanDir2, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    getContentPane().add(mPnlOKCancel, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOKCancel.add(mBtnCancel, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 5, 6, 12), 0, 0));
    mPnlOKCancel.add(mBtnOK, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 13, 0, new Insets(5, 5, 6, 5), 0, 0));
    mGrpRkkOnOff.add(mRdoOnOffPermit);
    mGrpRkkOnOff.add(mRdoOnOffPermitOff);
    mGrpRkkOnOff.add(mRdoOnOffProhibit);
    mGrpRkkDrvMode.add(mRdoDrvModeProhibit);
    mGrpRkkDrvMode.add(mRdoDrvModePermit);
    mGrpRkkSetTemp.add(mRdoSetTempPermit);
    mGrpRkkSetTemp.add(mRdoSetTempProhibit);
    mPnlOKCancel.add(mBtnVent, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 6, 5), 0, 0));
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
  
  void mBtnVent_actionPerformed(ActionEvent paramActionEvent)
  {
    DlgSetVentSub localDlgSetVentSub = new DlgSetVentSub(mPntTarget, mPntCurrent, mPntSet);
    localDlgSetVentSub.doModal();
  }
  
  void mChkDrvMode_itemStateChanged()
  {
    if (mChkDrvMode.isSelected() == true)
    {
      if ((mPntTarget.fValidRunMode & 0x4) != 0) {
        mRdoCool.setEnabled(true);
      } else {
        mRdoCool.setEnabled(false);
      }
      if ((mPntTarget.fValidRunMode & 0x2) != 0) {
        mRdoHeat.setEnabled(true);
      } else {
        mRdoHeat.setEnabled(false);
      }
      if ((mPntTarget.fValidRunMode & 0x1) != 0) {
        mRdoFan.setEnabled(true);
      } else {
        mRdoFan.setEnabled(false);
      }
      if ((mPntTarget.fValidRunMode & 0x10) != 0)
      {
        mRdoTemp.setEnabled(true);
        if (((mRdoCool.isSelected() == true) && (!mRdoCool.isEnabled())) || ((mRdoHeat.isSelected() == true) && (!mRdoHeat.isEnabled()))) {
          mRdoTemp.setSelected(true);
        }
      }
      else
      {
        mRdoTemp.setEnabled(false);
      }
      if (((mPntTarget.fValidRunMode & 0x200) != 0) || ((mPntTarget.fValidRunMode & 0x100) != 0)) {
        mRdoAuto.setEnabled(true);
      } else {
        mRdoAuto.setEnabled(false);
      }
    }
    else
    {
      mRdoCool.setEnabled(false);
      mRdoHeat.setEnabled(false);
      mRdoFan.setEnabled(false);
      mRdoTemp.setEnabled(false);
      mRdoAuto.setEnabled(false);
    }
    mChkSetTemp_itemStateChanged();
  }
  
  void mChkFanDir_itemStateChanged()
  {
    if (mChkFanDir.isSelected() == true) {
      mCmbFanDir.setEnabled(true);
    } else {
      mCmbFanDir.setEnabled(false);
    }
  }
  
  void mChkFanVol_itemStateChanged()
  {
    if (mChkFanVol.isSelected() == true)
    {
      mRdoFan7.setEnabled(true);
      mRdoFan1.setEnabled(true);
    }
    else
    {
      mRdoFan7.setEnabled(false);
      mRdoFan1.setEnabled(false);
    }
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
  
  void mChkRKKDrvMode_itemStateChanged()
  {
    if (mChkRKKDrvMode.isSelected() == true)
    {
      if ((mPntTarget.fRkkValidBit & 0x4) != 0)
      {
        mRdoDrvModePermit.setEnabled(true);
        mRdoDrvModeProhibit.setEnabled(true);
      }
      else
      {
        mRdoDrvModePermit.setEnabled(false);
        mRdoDrvModeProhibit.setEnabled(false);
      }
    }
    else
    {
      mRdoDrvModePermit.setEnabled(false);
      mRdoDrvModeProhibit.setEnabled(false);
    }
  }
  
  void mChkRKKOnOff_itemStateChanged()
  {
    if (mChkRKKOnOff.isSelected() == true)
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
  
  void mChkRKKSetTemp_itemStateChanged()
  {
    if (mChkRKKSetTemp.isSelected() == true)
    {
      if ((mPntTarget.fRkkValidBit & 0x8) != 0)
      {
        mRdoSetTempPermit.setEnabled(true);
        mRdoSetTempProhibit.setEnabled(true);
      }
      else
      {
        mRdoSetTempPermit.setEnabled(false);
        mRdoSetTempProhibit.setEnabled(false);
      }
    }
    else
    {
      mRdoSetTempPermit.setEnabled(false);
      mRdoSetTempProhibit.setEnabled(false);
    }
  }
  
  void mChkSetTemp_itemStateChanged()
  {
    if ((mPntTarget.fValidSetTemp == true) && (((mChkDrvMode.isSelected() == true) && (!mRdoFan.isSelected())) || ((!mChkDrvMode.isSelected()) && (mPntCurrent.mDrvMode != EnumDrvMode.FAN))))
    {
      mChkSetTemp.setEnabled(true);
      if (mChkSetTemp.isSelected() == true)
      {
        mCmbSetTemp10.setEnabled(true);
        mCmbSetTemp1.setEnabled(true);
      }
      else
      {
        mCmbSetTemp10.setEnabled(false);
        mCmbSetTemp1.setEnabled(false);
      }
    }
    else
    {
      mChkSetTemp.setEnabled(false);
      mCmbSetTemp10.setEnabled(false);
      mCmbSetTemp1.setEnabled(false);
    }
  }
  
  void mCmbSetTemp10_actionPerformed()
  {
    if (!SystemInfo.isCentigrade()) {
      return;
    }
    if (mCmbSetTemp1.getSelectedIndex() == -1) {
      return;
    }
    String str = (String)mCmbSetTemp1.getSelectedItem();
    str = str.trim();
    int i = Integer.parseInt(str);
    int j = 0;
    int k;
    int m;
    if (mCmbSetTemp10.getSelectedIndex() == mCmbSetTemp10.getItemCount() - 1)
    {
      if (mPntTarget.getSetTempUpper().getCentigradeInteger() >= 0)
      {
        mCmbSetTemp1.removeAllItems();
        k = mPntTarget.getSetTempUpper().getCentigradeDecimal();
        if (mPntTarget.fTempStep <= 0.1F)
        {
          for (m = 0; m <= k; m++)
          {
            mCmbSetTemp1.addItem(" " + m + " ");
            if (i == m) {
              j = m;
            }
          }
        }
        else if (mPntTarget.fTempStep <= 0.5F)
        {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
          if (k >= 5)
          {
            mCmbSetTemp1.addItem(" " + Integer.toString(5) + " ");
            if (i == 5) {
              j = 1;
            }
          }
        }
        else
        {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
        }
      }
      else
      {
        mCmbSetTemp1.removeAllItems();
        k = mPntTarget.getSetTempUpper().getCentigradeDecimal();
        if (mPntTarget.fTempStep <= 0.1F) {
          for (m = k; m <= 9; m++)
          {
            mCmbSetTemp1.addItem(" " + m + " ");
            if (i == m) {
              j = m - k;
            }
          }
        } else if (mPntTarget.fTempStep <= 0.5F)
        {
          if (k <= 5) {
            mCmbSetTemp1.addItem(" " + Integer.toString(5) + " ");
          } else {
            mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
          }
        }
        else {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
        }
      }
    }
    else if (mCmbSetTemp10.getSelectedIndex() == 0)
    {
      if (mPntTarget.getSetTempLower().getCentigradeInteger() > 0)
      {
        mCmbSetTemp1.removeAllItems();
        k = mPntTarget.getSetTempLower().getCentigradeDecimal();
        if (mPntTarget.fTempStep <= 0.1F) {
          for (m = k; m <= 9; m++)
          {
            mCmbSetTemp1.addItem(" " + m + " ");
            if (i == m) {
              j = m - k;
            }
          }
        } else if (mPntTarget.fTempStep <= 0.5F)
        {
          if (k == 0)
          {
            mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
            mCmbSetTemp1.addItem(" " + Integer.toString(5) + " ");
            if (i == 5) {
              j = 1;
            }
          }
          else
          {
            mCmbSetTemp1.addItem(" " + Integer.toString(5) + " ");
          }
        }
        else {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
        }
      }
      else
      {
        mCmbSetTemp1.removeAllItems();
        k = mPntTarget.getSetTempLower().getCentigradeDecimal();
        if (mPntTarget.fTempStep <= 0.1F)
        {
          for (m = 0; m <= k; m++)
          {
            mCmbSetTemp1.addItem(" " + m + " ");
            if (i == m) {
              j = m;
            }
          }
        }
        else if (mPntTarget.fTempStep <= 0.5F)
        {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
          if (k >= 5)
          {
            mCmbSetTemp1.addItem(" " + Integer.toString(5) + " ");
            if (i == 5) {
              j = 1;
            }
          }
        }
        else
        {
          mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
        }
      }
    }
    else if (mPntTarget.fTempStep <= 0.1F)
    {
      mCmbSetTemp1.removeAllItems();
      for (k = 0; k <= 9; k++)
      {
        mCmbSetTemp1.addItem(" " + k + " ");
        if (i == k) {
          j = k;
        }
      }
    }
    else if (mPntTarget.fTempStep <= 0.5F)
    {
      mCmbSetTemp1.removeAllItems();
      mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
      mCmbSetTemp1.addItem(" " + Integer.toString(5) + " ");
      if (i == 5) {
        j = 1;
      }
    }
    else
    {
      mCmbSetTemp1.removeAllItems();
      mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
    }
    mCmbSetTemp1.setSelectedIndex(j);
  }
  
  void mRdoAuto_actionPerformed(ActionEvent paramActionEvent)
  {
    mChkSetTemp_itemStateChanged();
  }
  
  void mRdoCool_actionPerformed(ActionEvent paramActionEvent)
  {
    mChkSetTemp_itemStateChanged();
  }
  
  void mRdoFan_actionPerformed(ActionEvent paramActionEvent)
  {
    mChkSetTemp_itemStateChanged();
  }
  
  void mRdoHeat_actionPerformed(ActionEvent paramActionEvent)
  {
    mChkSetTemp_itemStateChanged();
  }
  
  void mRdoTemp_actionPerformed(ActionEvent paramActionEvent)
  {
    mChkSetTemp_itemStateChanged();
  }
  
  private void pntInit()
  {
    mTxtName.setText(mShortName + " ( " + mDetailName + " )");
    mTxtStatus.setText(mPntState.getStr());
    mTxtDrvMode.setText(mDrvVentModeStr + " ( " + mPntCurrent.getSetTempStr() + " )");
    mTxtRoomTemp.setText(mPntCurrent.mRoomTemp.getStr());
    if (mPntCurrent.mFilterSign != 0) {
      mLblFilter.setIcon(new ColorIcon(ColorRes.FILTER_SIGN));
    }
    initParts();
    mChkOnOff_itemStateChanged();
    mChkDrvMode_itemStateChanged();
    mChkFanVol_itemStateChanged();
    mChkSetTemp_itemStateChanged();
    mChkFanDir_itemStateChanged();
    mChkRKKOnOff_itemStateChanged();
    mChkRKKDrvMode_itemStateChanged();
    mChkRKKSetTemp_itemStateChanged();
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
    if ((mChkDrvMode.isEnabled() == true) && (mChkDrvMode.isSelected() == true))
    {
      if (mRdoCool.isSelected() == true) {
        mPntSet.mRunMode = EnumDrvMode.COOL;
      } else if (mRdoHeat.isSelected() == true) {
        mPntSet.mRunMode = EnumDrvMode.HEAT;
      } else if (mRdoFan.isSelected() == true) {
        mPntSet.mRunMode = EnumDrvMode.FAN;
      } else if (mRdoAuto.isSelected() == true) {
        mPntSet.mRunMode = EnumDrvMode.AUTOCOOL;
      } else if (mRdoTemp.isSelected() == true) {
        mPntSet.mRunMode = EnumDrvMode.SUBMIT;
      } else {
        mPntSet.mRunMode = EnumDrvMode.ELSE;
      }
    }
    else {
      mPntSet.mRunMode = EnumDrvMode.ELSE;
    }
    if ((mChkSetTemp.isEnabled() == true) && (mChkSetTemp.isSelected() == true))
    {
      Temperature localTemperature = mPntTarget.getSetTempLower();
      if (SystemInfo.isCentigrade() == true)
      {
        String str = (String)mCmbSetTemp10.getSelectedItem();
        str = str.trim();
        int j = Integer.parseInt(str);
        str = (String)mCmbSetTemp1.getSelectedItem();
        str = str.trim();
        int k = Integer.parseInt(str);
        float f = j + (j >= 0 ? k / 10.0F : -k / 10.0F);
        mPntSet.mSetTemp = new Temperature(true, f);
      }
      else
      {
        int i = mCmbSetTemp10.getSelectedIndex() + localTemperature.getFahrenheit();
        mPntSet.mSetTemp = new Temperature(true, Temperature.convertFtoC(i));
      }
    }
    else
    {
      mPntSet.mSetTemp = new Temperature(false, 0.0F);
    }
    if ((mChkFanVol.isEnabled() == true) && (mChkFanVol.isSelected() == true))
    {
      if (mRdoFan1.isSelected() == true)
      {
        mPntSet.mChangeWindVolume = true;
        mPntSet.mWindVolume = 0;
      }
      else if (mRdoFan7.isSelected() == true)
      {
        mPntSet.mChangeWindVolume = true;
        mPntSet.mWindVolume = 2;
      }
      else
      {
        mPntSet.mChangeWindVolume = false;
      }
    }
    else {
      mPntSet.mChangeWindVolume = false;
    }
    if ((mChkFanDir.isEnabled() == true) && (mChkFanDir.isSelected() == true))
    {
      mPntSet.mChangeWindDirect = true;
      mPntSet.mWindDirect = mCmbFanDir.getSelectedIndex();
    }
    else
    {
      mPntSet.mChangeWindDirect = false;
    }
    if (mChkFilter.isSelected() == true) {
      mPntSet.mFilterClear = true;
    } else {
      mPntSet.mFilterClear = false;
    }
    if (mChkRKKOnOff.isSelected() == true)
    {
      if (mRdoOnOffProhibit.isSelected() == true) {
        mPntSet.mRemoconOnOffMode = EnumRemcCode.DISABLE;
      } else if (mRdoOnOffPermitOff.isSelected() == true) {
        mPntSet.mRemoconOnOffMode = EnumRemcCode.STOP;
      } else {
        mPntSet.mRemoconOnOffMode = EnumRemcCode.ENABLE;
      }
    }
    else {
      mPntSet.mRemoconOnOffMode = EnumRemcCode.ELSE;
    }
    if (mChkRKKDrvMode.isSelected() == true)
    {
      if (mRdoDrvModeProhibit.isSelected() == true) {
        mPntSet.mRemoconRunMode = EnumPntStat.OFF;
      } else {
        mPntSet.mRemoconRunMode = EnumPntStat.ON;
      }
    }
    else {
      mPntSet.mRemoconRunMode = EnumPntStat.ELSE;
    }
    if (mChkRKKSetTemp.isSelected() == true)
    {
      if (mRdoSetTempProhibit.isSelected() == true) {
        mPntSet.mRemoconTempMode = EnumPntStat.OFF;
      } else {
        mPntSet.mRemoconTempMode = EnumPntStat.ON;
      }
    }
    else {
      mPntSet.mRemoconTempMode = EnumPntStat.ELSE;
    }
  }
}
