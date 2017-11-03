package webitc.gui.schedule;

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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumTableID;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.DataMgr;
import webitc.data.ID;
import webitc.data.Temperature;
import webitc.data.schedule.AbstSchedule;
import webitc.data.schedule.Daily;
import webitc.data.schedule.DefaultAction;
import webitc.data.schedule.SchEvent;
import webitc.data.schedule.SchException;
import webitc.gui.PanelTime;
import webitc.gui.PanelTimeListener;
import webitc.gui.common.BorderRes;
import webitc.gui.common.PanelAbstract;

class PanelActionSelect
  extends PanelAbstract
  implements PanelTimeListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout10 = new GridBagLayout();
  GridBagLayout gridBagLayout11 = new GridBagLayout();
  GridBagLayout gridBagLayout12 = new GridBagLayout();
  GridBagLayout gridBagLayout13 = new GridBagLayout();
  GridBagLayout gridBagLayout14 = new GridBagLayout();
  GridBagLayout gridBagLayout15 = new GridBagLayout();
  GridBagLayout gridBagLayout16 = new GridBagLayout();
  GridBagLayout gridBagLayout17 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  GridBagLayout gridBagLayout8 = new GridBagLayout();
  GridBagLayout gridBagLayout9 = new GridBagLayout();
  JButton mBtnAdd = new JButton();
  JButton mBtnCopy = new JButton();
  JButton mBtnDel = new JButton();
  JButton mBtnPnt = new JButton();
  private boolean mChangingTableSelect = false;
  JCheckBox mChkDrvMode = new JCheckBox();
  JCheckBox mChkOnOff = new JCheckBox();
  JCheckBox mChkRkkDrvMode = new JCheckBox();
  JCheckBox mChkRkkOnOff = new JCheckBox();
  JCheckBox mChkRkkTempPermit = new JCheckBox();
  JCheckBox mChkTemp = new JCheckBox();
  JCheckBox mChkVentMode = new JCheckBox();
  JCheckBox mChkVentVol = new JCheckBox();
  JComboBox mCmbPattern = new JComboBox();
  JComboBox mCmbSetTemp1 = new JComboBox();
  JComboBox mCmbSetTemp10 = new JComboBox();
  SchEventTable mEventTable = new SchEventTable(EnumTableID.PANEL_ACTION_SELECT);
  private SchException mException = null;
  ButtonGroup mGrpDrvMode = new ButtonGroup();
  ButtonGroup mGrpOnOff = new ButtonGroup();
  ButtonGroup mGrpRkkDrvMode = new ButtonGroup();
  ButtonGroup mGrpRkkOnOff = new ButtonGroup();
  ButtonGroup mGrpRkkSetTemp = new ButtonGroup();
  ButtonGroup mGrpVentMode = new ButtonGroup();
  ButtonGroup mGrpVentVol = new ButtonGroup();
  private final int mHighTemp = SystemInfo.getScheduleUpperTemp();
  JLabel mLblDot = new JLabel();
  JLabel mLblPattern = new JLabel();
  JLabel mLblPntZone1 = new JLabel();
  JLabel mLblPntZone2 = new JLabel();
  JLabel mLblTemp = new JLabel();
  private final int mLowTemp = SystemInfo.getScheduleLowerTemp();
  JPanel mPnlAction = new JPanel();
  JPanel mPnlControl = new JPanel();
  JPanel mPnlDrvMode = new JPanel();
  JPanel mPnlHeader = new JPanel();
  JPanel mPnlOnOff = new JPanel();
  JPanel mPnlPnt = new JPanel();
  JPanel mPnlRKK = new JPanel();
  JPanel mPnlRkkDrvMode = new JPanel();
  JPanel mPnlRkkOnOff = new JPanel();
  JPanel mPnlRkkSetTemp = new JPanel();
  JPanel mPnlTemp = new JPanel();
  JPanel mPnlTime = new JPanel();
  JPanel mPnlVentMode = new JPanel();
  JPanel mPnlVentVol = new JPanel();
  JRadioButton mRdoAuto = new JRadioButton();
  JRadioButton mRdoCool = new JRadioButton();
  JRadioButton mRdoDrvModePermit = new JRadioButton();
  JRadioButton mRdoDrvModeProhibit = new JRadioButton();
  JRadioButton mRdoFan = new JRadioButton();
  JRadioButton mRdoHeat = new JRadioButton();
  JRadioButton mRdoModeAuto = new JRadioButton();
  JRadioButton mRdoModeNormal = new JRadioButton();
  JRadioButton mRdoModeVent = new JRadioButton();
  JRadioButton mRdoOff = new JRadioButton();
  JRadioButton mRdoOn = new JRadioButton();
  JRadioButton mRdoOnOffOffPermit = new JRadioButton();
  JRadioButton mRdoOnOffPermit = new JRadioButton();
  JRadioButton mRdoOnOffProhibit = new JRadioButton();
  JRadioButton mRdoTemp = new JRadioButton();
  JRadioButton mRdoTempPermit = new JRadioButton();
  JRadioButton mRdoTempProhibit = new JRadioButton();
  JRadioButton mRdoVolFreshAuto = new JRadioButton();
  JRadioButton mRdoVolFreshHigh = new JRadioButton();
  JRadioButton mRdoVolFreshLow = new JRadioButton();
  JRadioButton mRdoVolNormalAuto = new JRadioButton();
  JRadioButton mRdoVolNormalHigh = new JRadioButton();
  JRadioButton mRdoVolNormalLow = new JRadioButton();
  private int mSelectedDaily = -1;
  private PanelTime mTimePanel = new PanelTime();
  JTextField mTxtPnt = new JTextField();
  private AbstSchedule mWeekly = null;
  
  public PanelActionSelect()
  {
    try
    {
      jbInit();
      partsInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void addEvent(SchEvent paramSchEvent, boolean paramBoolean)
  {
    mEventTable.addEvent(paramSchEvent, paramBoolean);
    updateButtons();
  }
  
  private void changeTableSelect()
  {
    mChangingTableSelect = true;
    SchEvent localSchEvent = mEventTable.getSelectedEvent();
    if (localSchEvent != null)
    {
      DefaultAction localDefaultAction = (DefaultAction)localSchEvent.getAction();
      mTimePanel.setEnabled(true);
      mTimePanel.setTime(localSchEvent.getHour(), localSchEvent.getMin());
      mTxtPnt.setEnabled(true);
      mBtnPnt.setEnabled(true);
      String str = DataMgr.getInstance().getShortName(localDefaultAction.getTargetID());
      mTxtPnt.setText(str);
      mChkOnOff.setEnabled(true);
      if (localDefaultAction.getOnOff() == EnumPntStat.ON)
      {
        mChkOnOff.setSelected(true);
        mRdoOn.setSelected(true);
      }
      else if (localDefaultAction.getOnOff() == EnumPntStat.OFF)
      {
        mChkOnOff.setSelected(true);
        mRdoOff.setSelected(true);
      }
      else
      {
        mChkOnOff.setSelected(false);
        mRdoOn.setSelected(false);
        mRdoOff.setSelected(false);
      }
      mChkOnOff_setEnabled();
      mChkDrvMode.setEnabled(true);
      if (localDefaultAction.getDrvMode() == EnumDrvMode.COOL)
      {
        mChkDrvMode.setSelected(true);
        mRdoCool.setSelected(true);
      }
      else if (localDefaultAction.getDrvMode() == EnumDrvMode.HEAT)
      {
        mChkDrvMode.setSelected(true);
        mRdoHeat.setSelected(true);
      }
      else if (localDefaultAction.getDrvMode() == EnumDrvMode.FAN)
      {
        mChkDrvMode.setSelected(true);
        mRdoFan.setSelected(true);
      }
      else if (localDefaultAction.getDrvMode() == EnumDrvMode.SUBMIT)
      {
        mChkDrvMode.setSelected(true);
        mRdoTemp.setSelected(true);
      }
      else if (localDefaultAction.getDrvMode() == EnumDrvMode.AUTOCOOL)
      {
        mChkDrvMode.setSelected(true);
        mRdoAuto.setSelected(true);
      }
      else
      {
        mChkDrvMode.setSelected(false);
        mRdoCool.setSelected(false);
        mRdoHeat.setSelected(false);
        mRdoFan.setSelected(false);
        mRdoTemp.setSelected(false);
        mRdoAuto.setSelected(false);
      }
      mChkDrvMode_setEnabled();
      mCmbSetTemp10.removeAllItems();
      mCmbSetTemp1.removeAllItems();
      int i = mLowTemp;
      int j = mHighTemp;
      if (!SystemInfo.isCentigrade())
      {
        i = Temperature.convertCtoF(mLowTemp);
        j = Temperature.convertCtoF(mHighTemp);
      }
      for (int k = i; k <= j; k++) {
        mCmbSetTemp10.addItem(" " + k + " ");
      }
      for (int m = 0; m <= 9; m++) {
        mCmbSetTemp1.addItem(" " + m + " ");
      }
      if (!SystemInfo.isCentigrade())
      {
        mCmbSetTemp1.setVisible(false);
        mLblDot.setVisible(false);
        mLblTemp.setText(StrRes.getStr("IDS_COMMON_UNIT_TEMP_F"));
      }
      if (getSetPointfEnable == true)
      {
        mChkTemp.setSelected(true);
        int n = (int)getSetPointfTemp;
        if (!SystemInfo.isCentigrade()) {
          n = Temperature.convertCtoF(getSetPointfTemp);
        }
        if (n < i) {
          mCmbSetTemp10.setSelectedIndex(0);
        } else if (n > j) {
          mCmbSetTemp10.setSelectedIndex(j - i);
        } else {
          mCmbSetTemp10.setSelectedIndex(n - i);
        }
        mCmbSetTemp1.setSelectedIndex(localDefaultAction.getSetPoint().getDecimal(1));
      }
      else
      {
        mChkTemp.setSelected(false);
        mCmbSetTemp10.setSelectedIndex(0);
        mCmbSetTemp1.setSelectedIndex(0);
      }
      mChkTemp_setEnabled();
      mChkRkkOnOff.setEnabled(true);
      mChkRkkDrvMode.setEnabled(true);
      mChkRkkTempPermit.setEnabled(true);
      mRdoOnOffProhibit.setEnabled(true);
      mRdoOnOffOffPermit.setEnabled(true);
      mRdoOnOffPermit.setEnabled(true);
      mRdoDrvModeProhibit.setEnabled(true);
      mRdoDrvModePermit.setEnabled(true);
      mRdoTempProhibit.setEnabled(true);
      mRdoTempPermit.setEnabled(true);
      if (localDefaultAction.getRemcCode() != EnumRemcCode.ELSE) {
        mChkRkkOnOff.setSelected(true);
      } else {
        mChkRkkOnOff.setSelected(false);
      }
      if (localDefaultAction.getRemcDrvMode() != EnumPntStat.ELSE) {
        mChkRkkDrvMode.setSelected(true);
      } else {
        mChkRkkDrvMode.setSelected(false);
      }
      if (localDefaultAction.getRemcSetPoint() != EnumPntStat.ELSE) {
        mChkRkkTempPermit.setSelected(true);
      } else {
        mChkRkkTempPermit.setSelected(false);
      }
      if (localDefaultAction.getRemcCode() == EnumRemcCode.ENABLE) {
        mRdoOnOffPermit.setSelected(true);
      } else if (localDefaultAction.getRemcCode() == EnumRemcCode.STOP) {
        mRdoOnOffOffPermit.setSelected(true);
      } else if (localDefaultAction.getRemcCode() == EnumRemcCode.DISABLE) {
        mRdoOnOffProhibit.setSelected(true);
      }
      if (localDefaultAction.getRemcDrvMode() == EnumPntStat.ON) {
        mRdoDrvModePermit.setSelected(true);
      } else if (localDefaultAction.getRemcDrvMode() == EnumPntStat.OFF) {
        mRdoDrvModeProhibit.setSelected(true);
      }
      if (localDefaultAction.getRemcSetPoint() == EnumPntStat.ON) {
        mRdoTempPermit.setSelected(true);
      } else if (localDefaultAction.getRemcSetPoint() == EnumPntStat.OFF) {
        mRdoTempProhibit.setSelected(true);
      }
      mChkRkkOnOff_setEnabled();
      mChkRkkDrvMode_setEnabled();
      mChkRkkTempPermit_setEnabled();
      mChkVentMode.setEnabled(true);
      mChkVentMode.setSelected(true);
      if (localDefaultAction.getVentMode() == EnumVentMode.AUTO)
      {
        mRdoModeAuto.setSelected(true);
      }
      else if (localDefaultAction.getVentMode() == EnumVentMode.VENTILATION)
      {
        mRdoModeVent.setSelected(true);
      }
      else if (localDefaultAction.getVentMode() == EnumVentMode.NORMAL)
      {
        mRdoModeNormal.setSelected(true);
      }
      else
      {
        mRdoModeAuto.setSelected(true);
        mChkVentMode.setSelected(false);
      }
      mChkVentMode_setEnabled();
      mChkVentVol.setEnabled(true);
      mChkVentVol.setSelected(true);
      if (localDefaultAction.getVentVol() == EnumVentVol.NORMAL_AUTO)
      {
        mRdoVolNormalAuto.setSelected(true);
      }
      else if (localDefaultAction.getVentVol() == EnumVentVol.NORMAL_LOW)
      {
        mRdoVolNormalLow.setSelected(true);
      }
      else if (localDefaultAction.getVentVol() == EnumVentVol.NORMAL_HIGH)
      {
        mRdoVolNormalHigh.setSelected(true);
      }
      else if (localDefaultAction.getVentVol() == EnumVentVol.FRESH_AUTO)
      {
        mRdoVolFreshAuto.setSelected(true);
      }
      else if (localDefaultAction.getVentVol() == EnumVentVol.FRESH_LOW)
      {
        mRdoVolFreshLow.setSelected(true);
      }
      else if (localDefaultAction.getVentVol() == EnumVentVol.FRESH_HIGH)
      {
        mRdoVolFreshHigh.setSelected(true);
      }
      else
      {
        mRdoVolNormalAuto.setSelected(true);
        mChkVentVol.setSelected(false);
      }
      mChkVentVol_setEnabled();
    }
    else
    {
      mTimePanel.setEnabled(false);
      mTxtPnt.setEnabled(false);
      mBtnPnt.setEnabled(false);
      mChkOnOff.setEnabled(false);
      mChkOnOff.setSelected(false);
      mRdoOn.setEnabled(false);
      mRdoOff.setEnabled(false);
      mChkDrvMode.setEnabled(false);
      mChkDrvMode.setSelected(false);
      mRdoCool.setEnabled(false);
      mRdoHeat.setEnabled(false);
      mRdoFan.setEnabled(false);
      mRdoTemp.setEnabled(false);
      mRdoAuto.setEnabled(false);
      mChkTemp.setEnabled(false);
      mChkTemp.setSelected(false);
      mCmbSetTemp10.setEnabled(false);
      mCmbSetTemp1.setEnabled(false);
      mChkRkkOnOff.setEnabled(false);
      mChkRkkOnOff.setSelected(false);
      mChkRkkDrvMode.setEnabled(false);
      mChkRkkDrvMode.setSelected(false);
      mChkRkkTempPermit.setEnabled(false);
      mChkRkkTempPermit.setSelected(false);
      mRdoOnOffProhibit.setEnabled(false);
      mRdoOnOffOffPermit.setEnabled(false);
      mRdoOnOffPermit.setEnabled(false);
      mRdoDrvModeProhibit.setEnabled(false);
      mRdoDrvModePermit.setEnabled(false);
      mRdoTempProhibit.setEnabled(false);
      mRdoTempPermit.setEnabled(false);
      mChkVentMode.setEnabled(false);
      mRdoModeAuto.setEnabled(false);
      mRdoModeNormal.setEnabled(false);
      mRdoModeVent.setEnabled(false);
      mChkVentVol.setEnabled(false);
      mRdoVolFreshHigh.setEnabled(false);
      mRdoVolFreshAuto.setEnabled(false);
      mRdoVolFreshLow.setEnabled(false);
      mRdoVolNormalHigh.setEnabled(false);
      mRdoVolNormalLow.setEnabled(false);
      mRdoVolNormalAuto.setEnabled(false);
    }
    mChangingTableSelect = false;
  }
  
  public void deleteAllEvent()
  {
    mEventTable.deleteAllEvent();
    updateButtons();
  }
  
  public void deleteEvent()
  {
    mEventTable.deleteEvent();
    updateButtons();
  }
  
  public void errorPerformed()
  {
    mEventTable.setEnabled(false);
    mTimePanel.setEnabled(false);
    mCmbPattern.setEnabled(false);
    mChkOnOff.setEnabled(false);
    mRdoOn.setEnabled(false);
    mRdoOff.setEnabled(false);
    mChkTemp.setEnabled(false);
    mCmbSetTemp10.setEnabled(false);
    mChkDrvMode.setEnabled(false);
    mRdoCool.setEnabled(false);
    mRdoHeat.setEnabled(false);
    mRdoFan.setEnabled(false);
    mRdoTemp.setEnabled(false);
    mRdoOnOffProhibit.setEnabled(false);
    mRdoOnOffOffPermit.setEnabled(false);
    mRdoOnOffPermit.setEnabled(false);
    mRdoDrvModeProhibit.setEnabled(false);
    mRdoDrvModePermit.setEnabled(false);
    mRdoTempProhibit.setEnabled(false);
    mRdoTempPermit.setEnabled(false);
    mChkRkkOnOff.setEnabled(false);
    mRdoAuto.setEnabled(false);
    mCmbSetTemp1.setEnabled(false);
    mTxtPnt.setEnabled(false);
    mBtnPnt.setEnabled(false);
    mBtnAdd.setEnabled(false);
    mBtnCopy.setEnabled(false);
    mBtnDel.setEnabled(false);
    mRdoModeAuto.setEnabled(false);
    mChkVentMode.setEnabled(false);
    mRdoModeNormal.setEnabled(false);
    mRdoModeVent.setEnabled(false);
    mChkVentVol.setEnabled(false);
    mRdoVolFreshHigh.setEnabled(false);
    mRdoVolFreshAuto.setEnabled(false);
    mRdoVolFreshLow.setEnabled(false);
    mRdoVolNormalHigh.setEnabled(false);
    mRdoVolNormalLow.setEnabled(false);
    mRdoVolNormalAuto.setEnabled(false);
    mChkRkkDrvMode.setEnabled(false);
    mChkRkkTempPermit.setEnabled(false);
  }
  
  private SchEvent getEvent(int paramInt)
  {
    return mEventTable.getEvent(paramInt);
  }
  
  private int getEventCount()
  {
    return mEventTable.getEventCount();
  }
  
  public SchException getException()
  {
    return mException;
  }
  
  private DefaultAction getSelectedAction()
  {
    SchEvent localSchEvent = getSelectedEvent();
    if (localSchEvent != null) {
      return (DefaultAction)localSchEvent.getAction();
    }
    return null;
  }
  
  private SchEvent getSelectedEvent()
  {
    return mEventTable.getSelectedEvent();
  }
  
  public AbstSchedule getWeekly()
  {
    return mWeekly;
  }
  
  public void hourSelected()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    SchEvent localSchEvent = getSelectedEvent();
    localSchEvent.setHour(mTimePanel.getHour());
    mEventTable.sortTable(mEventTable.getSelectedRow());
    mEventTable.redraw();
  }
  
  private void jbInit()
    throws Exception
  {
    mLblPntZone1.setText(StrRes.getStr("IDS_SCH_GROUP_ZONE"));
    setLayout(gridBagLayout2);
    mPnlPnt.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCHDEVENTSETUP_TARGET")));
    mPnlTime.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCH_EXEC_TIME")));
    mPnlTime.setLayout(gridBagLayout12);
    mPnlAction.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCH_ACTION_CONTENTS")));
    mPnlAction.setLayout(gridBagLayout5);
    mPnlOnOff.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_ONOFF")));
    mPnlOnOff.setLayout(gridBagLayout3);
    mChkOnOff.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkOnOff.addItemListener(new PanelActionSelect_mChkOnOff_itemAdapter(this));
    mRdoOn.setMargin(new Insets(0, 2, 0, 2));
    mRdoOn.setText(StrRes.getStr("IDS_COMMON_START"));
    mRdoOn.addActionListener(new PanelActionSelect_mRdoOn_actionAdapter(this));
    mRdoOff.setMargin(new Insets(0, 2, 0, 2));
    mRdoOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    mRdoOff.addActionListener(new PanelActionSelect_mRdoOff_actionAdapter(this));
    mPnlTemp.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_CCOPE_LABEL_SETPOINT")));
    mPnlTemp.setLayout(gridBagLayout4);
    mChkTemp.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkTemp.addItemListener(new PanelActionSelect_mChkTemp_itemAdapter(this));
    mPnlDrvMode.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_DRVMODE")));
    mPnlDrvMode.setLayout(gridBagLayout6);
    mChkDrvMode.setAlignmentX(0.0F);
    mChkDrvMode.setAlignmentY(0.5F);
    mChkDrvMode.setBorderPainted(false);
    mChkDrvMode.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkDrvMode.addItemListener(new PanelActionSelect_mChkDrvMode_itemAdapter(this));
    mRdoCool.setMargin(new Insets(0, 2, 0, 2));
    mRdoCool.setText(StrRes.getStr("IDS_COMMON_MODE_COOL"));
    mRdoCool.addActionListener(new PanelActionSelect_mRdoCool_actionAdapter(this));
    mRdoHeat.setMargin(new Insets(0, 2, 0, 2));
    mRdoHeat.setText(StrRes.getStr("IDS_COMMON_MODE_HEAT"));
    mRdoHeat.addActionListener(new PanelActionSelect_mRdoHeat_actionAdapter(this));
    mRdoFan.setMargin(new Insets(0, 2, 0, 2));
    mRdoFan.setText(StrRes.getStr("IDS_COMMON_MODE_FAN"));
    mRdoFan.addActionListener(new PanelActionSelect_mRdoFan_actionAdapter(this));
    mRdoTemp.setMargin(new Insets(0, 2, 0, 2));
    mRdoTemp.setText(StrRes.getStr("IDS_COMMON_MODE_SETPOINT"));
    mRdoTemp.addActionListener(new PanelActionSelect_mRdoTemp_actionAdapter(this));
    mPnlRKK.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_RC_ONOFF_SETUP")));
    mPnlRKK.setLayout(gridBagLayout7);
    mPnlRkkOnOff.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_WEBACSET_ONOFF")));
    mPnlRkkOnOff.setLayout(gridBagLayout8);
    mPnlRkkDrvMode.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_RC_MODE_SETUP")));
    mPnlRkkDrvMode.setLayout(gridBagLayout9);
    mPnlRkkSetTemp.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_RC_SETPOINT_SETUP")));
    mPnlRkkSetTemp.setLayout(gridBagLayout10);
    mRdoOnOffProhibit.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffProhibit.setText(StrRes.getStr("IDS_COMMON_RC_PROHIBIT"));
    mRdoOnOffProhibit.addActionListener(new PanelActionSelect_mRdoOnOffProhibit_actionAdapter(this));
    mRdoOnOffOffPermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffOffPermit.setText(StrRes.getStr("IDS_COMMON_RC_ONOFF_STOPONLY"));
    mRdoOnOffOffPermit.addActionListener(new PanelActionSelect_mRdoOnOffOffPermit_actionAdapter(this));
    mRdoOnOffPermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoOnOffPermit.setText(StrRes.getStr("IDS_COMMON_RC_PERMIT"));
    mRdoOnOffPermit.addActionListener(new PanelActionSelect_mRdoOnOffPermit_actionAdapter(this));
    mRdoDrvModeProhibit.setMargin(new Insets(0, 2, 0, 2));
    mRdoDrvModeProhibit.setText(StrRes.getStr("IDS_COMMON_RC_PROHIBIT"));
    mRdoDrvModeProhibit.addActionListener(new PanelActionSelect_mRdoDrvModeProhibit_actionAdapter(this));
    mRdoDrvModePermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoDrvModePermit.setText(StrRes.getStr("IDS_COMMON_RC_PERMIT"));
    mRdoDrvModePermit.addActionListener(new PanelActionSelect_mRdoDrvModePermit_actionAdapter(this));
    mRdoTempProhibit.setMargin(new Insets(0, 2, 0, 2));
    mRdoTempProhibit.setText(StrRes.getStr("IDS_COMMON_RC_PROHIBIT"));
    mRdoTempProhibit.addActionListener(new PanelActionSelect_mRdoTempProhibit_actionAdapter(this));
    mRdoTempPermit.setMargin(new Insets(0, 2, 0, 2));
    mRdoTempPermit.setText(StrRes.getStr("IDS_COMMON_RC_PERMIT"));
    mRdoTempPermit.addActionListener(new PanelActionSelect_mRdoTempPermit_actionAdapter(this));
    mChkRkkOnOff.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRkkOnOff.addItemListener(new PanelActionSelect_mChkRkkOnOff_itemAdapter(this));
    mRdoAuto.setMargin(new Insets(0, 2, 0, 2));
    mRdoAuto.setText(StrRes.getStr("IDS_COMMON_MODE_AUTO"));
    mRdoAuto.addActionListener(new PanelActionSelect_mRdoAuto_actionAdapter(this));
    mCmbSetTemp10.addActionListener(new PanelActionSelect_mCmbSetTemp10_actionAdapter(this));
    mLblDot.setText(StrRes.getStr("IDS_UTILTENKEY_DOT"));
    mLblTemp.setText(StrRes.getStr("IDS_COMMON_UNIT_TEMP"));
    mCmbSetTemp1.addActionListener(new PanelActionSelect_mCmbSetTemp1_actionAdapter(this));
    mPnlPnt.setLayout(gridBagLayout11);
    mTxtPnt.setEditable(false);
    mTxtPnt.setText(StrRes.getStr("IDS_COMMON_UNREGISTERD"));
    mBtnPnt.setText(StrRes.getStr("IDS_COMMON_MODIFY"));
    mBtnPnt.addActionListener(new PanelActionSelect_mBtnPnt_actionAdapter(this));
    mBtnAdd.setText(StrRes.getStr("IDS_COMMON_ADD"));
    mBtnAdd.addActionListener(new PanelActionSelect_mBtnAdd_actionAdapter(this));
    mPnlControl.setLayout(gridBagLayout13);
    mBtnCopy.setText(StrRes.getStr("IDS_COMMON_COPY"));
    mBtnCopy.addActionListener(new PanelActionSelect_mBtnCopy_actionAdapter(this));
    mBtnDel.setText(StrRes.getStr("IDS_COMMON_DELETE"));
    mBtnDel.addActionListener(new PanelActionSelect_mBtnDel_actionAdapter(this));
    mRdoModeAuto.setText(StrRes.getStr("IDS_SCH_VENT_MODE_AUTO"));
    mRdoModeAuto.addActionListener(new PanelActionSelect_mRdoModeAuto_actionAdapter(this));
    mRdoModeAuto.setMnemonic('0');
    mRdoModeAuto.setMargin(new Insets(0, 2, 0, 2));
    mPnlVentMode.setLayout(gridBagLayout14);
    mPnlVentMode.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_VENT_MODE")));
    mChkVentMode.setMargin(new Insets(0, 2, 0, 2));
    mChkVentMode.setMnemonic('0');
    mChkVentMode.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentMode.addItemListener(new PanelActionSelect_mChkVentMode_itemAdapter(this));
    mChkVentMode.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mRdoModeNormal.setMargin(new Insets(0, 2, 0, 2));
    mRdoModeNormal.setMnemonic('0');
    mRdoModeNormal.setText(StrRes.getStr("IDS_SCH_VENT_MODE_NORMAL"));
    mRdoModeNormal.addActionListener(new PanelActionSelect_mRdoModeNormal_actionAdapter(this));
    mRdoModeVent.setMargin(new Insets(0, 2, 0, 2));
    mRdoModeVent.setMnemonic('0');
    mRdoModeVent.setText(StrRes.getStr("IDS_SCH_VENT_MODE_ALL_HEAT"));
    mRdoModeVent.addActionListener(new PanelActionSelect_mRdoModeVent_actionAdapter(this));
    mPnlVentVol.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_VENT_VOL")));
    mPnlVentVol.setLayout(gridBagLayout15);
    mChkVentVol.setMargin(new Insets(0, 2, 0, 2));
    mChkVentVol.setMnemonic('0');
    mChkVentVol.setActionCommand(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentVol.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkVentVol.addItemListener(new PanelActionSelect_mChkVentVol_itemAdapter(this));
    mRdoVolFreshHigh.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_HIGH"));
    mRdoVolFreshHigh.addActionListener(new PanelActionSelect_mRdoVolFreshHigh_actionAdapter(this));
    mRdoVolFreshAuto.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_AUTO"));
    mRdoVolFreshAuto.addActionListener(new PanelActionSelect_mRdoVolFreshAuto_actionAdapter(this));
    mRdoVolFreshLow.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_FRESH_LOW"));
    mRdoVolFreshLow.addActionListener(new PanelActionSelect_mRdoVolFreshLow_actionAdapter(this));
    mRdoVolNormalHigh.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_HIGH"));
    mRdoVolNormalHigh.addActionListener(new PanelActionSelect_mRdoVolNormalHigh_actionAdapter(this));
    mRdoVolNormalLow.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_LOW"));
    mRdoVolNormalLow.addActionListener(new PanelActionSelect_mRdoVolNormalLow_actionAdapter(this));
    mRdoVolNormalAuto.setMargin(new Insets(0, 2, 0, 2));
    mRdoVolNormalAuto.setMnemonic('0');
    mRdoVolNormalAuto.setText(StrRes.getStr("IDS_COMMON_VENT_VOL_NORMAL_AUTO"));
    mRdoVolNormalAuto.addActionListener(new PanelActionSelect_mRdoVolNormalAuto_actionAdapter(this));
    mPnlHeader.setLayout(gridBagLayout17);
    mLblPattern.setText(StrRes.getStr("IDS_SCH_PATTERN"));
    mCmbPattern.addActionListener(new PanelActionSelect_mCmbPattern_actionAdapter(this));
    mLblPntZone2.setText(StrRes.getStr("IDS_SCH_UNREG"));
    mChkRkkDrvMode.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRkkDrvMode.addItemListener(new PanelActionSelect_mChkRkkDrvMode_itemAdapter(this));
    mChkRkkTempPermit.setText(StrRes.getStr("IDS_WEBACSET_SET"));
    mChkRkkTempPermit.addItemListener(new PanelActionSelect_mChkRkkTempPermit_itemAdapter(this));
    add(mEventTable.getScrollPane(), new GridBagConstraints(0, 1, 3, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 5, 0, 5), 0, 0));
    add(mPnlTime, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mPnlAction, new GridBagConstraints(0, 3, 2, 1, 1.0D, 0.0D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlAction.add(mPnlOnOff, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 3, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOnOff.add(mChkOnOff, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlOnOff.add(mRdoOn, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlOnOff.add(mRdoOff, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlAction.add(mPnlTemp, new GridBagConstraints(1, 0, 3, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlTemp.add(mChkTemp, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(5, 5, 5, 0), 0, -5));
    mPnlTemp.add(mCmbSetTemp10, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(5, 0, 5, 5), 0, 0));
    mPnlAction.add(mPnlDrvMode, new GridBagConstraints(0, 1, 4, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlDrvMode.add(mChkDrvMode, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlDrvMode.add(mRdoCool, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlDrvMode.add(mRdoHeat, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlDrvMode.add(mRdoFan, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlDrvMode.add(mRdoTemp, new GridBagConstraints(4, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlAction.add(mPnlRKK, new GridBagConstraints(0, 2, 2, 2, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkOnOff.add(mRdoOnOffPermit, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlRkkOnOff.add(mRdoOnOffOffPermit, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlRkkOnOff.add(mRdoOnOffProhibit, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlRkkOnOff.add(mChkRkkOnOff, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkDrvMode.add(mRdoDrvModePermit, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlRkkDrvMode.add(mRdoDrvModeProhibit, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlRkkSetTemp.add(mRdoTempPermit, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlRkkSetTemp.add(mRdoTempProhibit, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, -5));
    mPnlAction.add(mPnlVentMode, new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentMode.add(mRdoModeAuto, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentMode.add(mChkVentMode, new GridBagConstraints(0, 0, 3, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentMode.add(mRdoModeVent, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentMode.add(mRdoModeNormal, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlAction.add(mPnlVentVol, new GridBagConstraints(2, 3, 1, 1, 1.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mChkVentVol, new GridBagConstraints(0, 0, 3, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolNormalAuto, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolNormalLow, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolNormalHigh, new GridBagConstraints(2, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolFreshAuto, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolFreshLow, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlVentVol.add(mRdoVolFreshHigh, new GridBagConstraints(2, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mPnlPnt, new GridBagConstraints(1, 2, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRKK.add(mPnlRkkOnOff, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRKK.add(mPnlRkkDrvMode, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRKK.add(mPnlRkkSetTemp, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlTime.add(mTimePanel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mGrpOnOff.add(mRdoOn);
    mGrpOnOff.add(mRdoOff);
    mGrpDrvMode.add(mRdoCool);
    mGrpDrvMode.add(mRdoHeat);
    mGrpDrvMode.add(mRdoFan);
    mGrpDrvMode.add(mRdoTemp);
    mGrpDrvMode.add(mRdoAuto);
    mGrpRkkOnOff.add(mRdoOnOffPermit);
    mGrpRkkOnOff.add(mRdoOnOffOffPermit);
    mGrpRkkOnOff.add(mRdoOnOffProhibit);
    mGrpRkkDrvMode.add(mRdoDrvModePermit);
    mGrpRkkDrvMode.add(mRdoDrvModeProhibit);
    mGrpRkkSetTemp.add(mRdoTempPermit);
    mGrpRkkSetTemp.add(mRdoTempProhibit);
    mPnlDrvMode.add(mRdoAuto, new GridBagConstraints(5, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlTemp.add(mLblDot, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlTemp.add(mCmbSetTemp1, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlTemp.add(mLblTemp, new GridBagConstraints(4, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlPnt.add(mTxtPnt, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlPnt.add(mBtnPnt, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 3, 0, 0), 0, 0));
    add(mPnlControl, new GridBagConstraints(1, 0, 2, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlControl.add(mBtnAdd, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 10, 0, 0), 0, 0));
    mPnlControl.add(mBtnCopy, new GridBagConstraints(3, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlControl.add(mBtnDel, new GridBagConstraints(4, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 5), 0, 0));
    mPnlControl.add(mCmbPattern, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlControl.add(mLblPattern, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 5), 0, 0));
    mGrpVentMode.add(mRdoModeAuto);
    mGrpVentMode.add(mRdoModeVent);
    mGrpVentMode.add(mRdoModeNormal);
    mGrpVentVol.add(mRdoVolNormalAuto);
    mGrpVentVol.add(mRdoVolNormalLow);
    mGrpVentVol.add(mRdoVolNormalHigh);
    mGrpVentVol.add(mRdoVolFreshAuto);
    mGrpVentVol.add(mRdoVolFreshLow);
    mGrpVentVol.add(mRdoVolFreshHigh);
    add(mPnlHeader, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlHeader.add(mLblPntZone1, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlHeader.add(mLblPntZone2, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 18, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkDrvMode.add(mChkRkkDrvMode, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRkkSetTemp.add(mChkRkkTempPermit, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  void mBtnAdd_actionPerformed()
  {
    SchEvent localSchEvent = new SchEvent();
    localSchEvent.setTime(0, 0);
    DefaultAction localDefaultAction = new DefaultAction();
    localSchEvent.setAction(localDefaultAction);
    addEvent(localSchEvent, true);
  }
  
  void mBtnCopy_actionPerformed()
  {
    SchEvent localSchEvent = (SchEvent)getSelectedEvent().clone();
    if (localSchEvent == null) {
      return;
    }
    addEvent(localSchEvent, true);
  }
  
  void mBtnDel_actionPerformed()
  {
    deleteEvent();
  }
  
  void mBtnPnt_actionPerformed(ActionEvent paramActionEvent)
  {
    SchEvent localSchEvent = getSelectedEvent();
    DefaultAction localDefaultAction = (DefaultAction)localSchEvent.getAction();
    DlgPntZoneList localDlgPntZoneList = new DlgPntZoneList(localDefaultAction.getTargetID());
    localDlgPntZoneList.doModal();
    if (localDlgPntZoneList.isOK() == true)
    {
      ID localID = localDlgPntZoneList.getSelectedID();
      localDefaultAction.setTargetID(localID);
      String str = DataMgr.getInstance().getShortName(localID);
      mTxtPnt.setText(str);
      mEventTable.redraw();
    }
  }
  
  void mChkDrvMode_itemStateChanged()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    mChkDrvMode_setEnabled();
    if (mChkDrvMode.isSelected() == true)
    {
      if (mRdoCool.isSelected() == true)
      {
        mRdoCool_actionPerformed();
      }
      else if (mRdoHeat.isSelected() == true)
      {
        mRdoHeat_actionPerformed();
      }
      else if (mRdoFan.isSelected() == true)
      {
        mRdoFan_actionPerformed();
      }
      else if (mRdoTemp.isSelected() == true)
      {
        mRdoTemp_actionPerformed();
      }
      else if (mRdoAuto.isSelected() == true)
      {
        mRdoAuto_actionPerformed();
      }
      else
      {
        mRdoCool.setSelected(true);
        mRdoCool_actionPerformed();
      }
    }
    else
    {
      DefaultAction localDefaultAction = getSelectedAction();
      localDefaultAction.setDrvMode(EnumDrvMode.ELSE);
      mEventTable.redraw();
    }
    mChkTemp_itemStateChanged(false);
  }
  
  private void mChkDrvMode_setEnabled()
  {
    if (mChkDrvMode.isSelected() == true)
    {
      mRdoCool.setEnabled(true);
      mRdoHeat.setEnabled(true);
      mRdoFan.setEnabled(true);
      mRdoTemp.setEnabled(true);
      mRdoAuto.setEnabled(true);
    }
    else
    {
      mRdoCool.setEnabled(false);
      mRdoHeat.setEnabled(false);
      mRdoFan.setEnabled(false);
      mRdoTemp.setEnabled(false);
      mRdoAuto.setEnabled(false);
    }
  }
  
  void mChkOnOff_itemStateChanged()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    mChkOnOff_setEnabled();
    if (mChkOnOff.isSelected() == true)
    {
      if (mRdoOn.isSelected() == true)
      {
        mRdoOn_actionPerformed();
      }
      else if (mRdoOff.isSelected() == true)
      {
        mRdoOff_actionPerformed();
      }
      else
      {
        mRdoOff.setSelected(true);
        mRdoOff_actionPerformed();
      }
    }
    else
    {
      DefaultAction localDefaultAction = getSelectedAction();
      localDefaultAction.setOnOff(EnumPntStat.ELSE);
      mEventTable.redraw();
    }
  }
  
  private void mChkOnOff_setEnabled()
  {
    if (mChkOnOff.isSelected() == true)
    {
      mRdoOn.setEnabled(true);
      mRdoOff.setEnabled(true);
    }
    else
    {
      mRdoOn.setSelected(false);
      mRdoOff.setSelected(false);
      mRdoOn.setEnabled(false);
      mRdoOff.setEnabled(false);
    }
  }
  
  void mChkRkkDrvMode_itemStateChanged(boolean paramBoolean)
  {
    if (paramBoolean == true) {
      return;
    }
    mChkRkkDrvMode_setEnabled();
    DefaultAction localDefaultAction = getSelectedAction();
    if (localDefaultAction == null) {
      return;
    }
    if (mChkRkkDrvMode.isSelected() == true)
    {
      if ((mRdoDrvModePermit.isEnabled() == true) && (mRdoDrvModePermit.isSelected() == true))
      {
        mRdoDrvModePermit_actionPerformed();
      }
      else if ((mRdoDrvModeProhibit.isEnabled() == true) && (mRdoDrvModeProhibit.isSelected() == true))
      {
        mRdoDrvModeProhibit_actionPerformed();
      }
      else
      {
        mRdoDrvModePermit.setSelected(true);
        mRdoDrvModePermit_actionPerformed();
      }
    }
    else {
      localDefaultAction.setRemcDrvMode(EnumPntStat.ELSE);
    }
    mEventTable.redraw();
  }
  
  private void mChkRkkDrvMode_setEnabled()
  {
    if (mChkRkkDrvMode.isSelected() == true)
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
  
  void mChkRkkOnOff_itemStateChanged(boolean paramBoolean)
  {
    if (paramBoolean == true) {
      return;
    }
    mChkRkkOnOff_setEnabled();
    DefaultAction localDefaultAction = getSelectedAction();
    if (localDefaultAction == null) {
      return;
    }
    if (mChkRkkOnOff.isSelected() == true)
    {
      if ((mRdoOnOffPermit.isEnabled() == true) && (mRdoOnOffPermit.isSelected() == true))
      {
        mRdoOnOffPermit_actionPerformed();
      }
      else if ((mRdoOnOffOffPermit.isEnabled() == true) && (mRdoOnOffOffPermit.isSelected() == true))
      {
        mRdoOnOffOffPermit_actionPerformed();
      }
      else if ((mRdoOnOffProhibit.isEnabled() == true) && (mRdoOnOffProhibit.isSelected() == true))
      {
        mRdoOnOffProhibit_actionPerformed();
      }
      else
      {
        mRdoOnOffPermit.setSelected(true);
        mRdoOnOffPermit_actionPerformed();
      }
    }
    else {
      localDefaultAction.setRemcCode(EnumRemcCode.ELSE);
    }
    mEventTable.redraw();
  }
  
  private void mChkRkkOnOff_setEnabled()
  {
    if (mChkRkkOnOff.isSelected() == true)
    {
      mRdoOnOffPermit.setEnabled(true);
      mRdoOnOffProhibit.setEnabled(true);
      mRdoOnOffOffPermit.setEnabled(true);
    }
    else
    {
      mRdoOnOffPermit.setEnabled(false);
      mRdoOnOffOffPermit.setEnabled(false);
      mRdoOnOffProhibit.setEnabled(false);
    }
  }
  
  void mChkRkkTempPermit_itemStateChanged(boolean paramBoolean)
  {
    if (paramBoolean == true) {
      return;
    }
    mChkRkkTempPermit_setEnabled();
    DefaultAction localDefaultAction = getSelectedAction();
    if (localDefaultAction == null) {
      return;
    }
    if (mChkRkkTempPermit.isSelected() == true)
    {
      if ((mRdoTempPermit.isEnabled() == true) && (mRdoTempPermit.isSelected() == true))
      {
        mRdoTempPermit_actionPerformed();
      }
      else if ((mRdoTempProhibit.isEnabled() == true) && (mRdoTempProhibit.isSelected() == true))
      {
        mRdoTempProhibit_actionPerformed();
      }
      else
      {
        mRdoTempPermit.setSelected(true);
        mRdoTempPermit_actionPerformed();
      }
    }
    else {
      localDefaultAction.setRemcSetPoint(EnumPntStat.ELSE);
    }
    mEventTable.redraw();
  }
  
  private void mChkRkkTempPermit_setEnabled()
  {
    if (mChkRkkTempPermit.isSelected() == true)
    {
      mRdoTempPermit.setEnabled(true);
      mRdoTempProhibit.setEnabled(true);
    }
    else
    {
      mRdoTempPermit.setEnabled(false);
      mRdoTempProhibit.setEnabled(false);
    }
  }
  
  void mChkTemp_itemStateChanged(boolean paramBoolean)
  {
    mChkTemp_setEnabled();
    if ((!mChkDrvMode.isSelected()) || (!mRdoFan.isSelected()))
    {
      if (mChkTemp.isSelected() == true) {
        mCmbSetTemp10_actionPerformed(false);
      } else {
        setTempChanged();
      }
    }
    else {
      setTempChanged();
    }
  }
  
  private void mChkTemp_setEnabled()
  {
    if ((!mChkDrvMode.isSelected()) || (!mRdoFan.isSelected()))
    {
      mChkTemp.setEnabled(true);
      if (mChkTemp.isSelected() == true)
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
      mChkTemp.setEnabled(false);
      mCmbSetTemp10.setEnabled(false);
      mCmbSetTemp1.setEnabled(false);
    }
  }
  
  void mChkVentMode_itemStateChanged()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    mChkVentMode_setEnabled();
    if (mChkVentMode.isSelected() == true)
    {
      if (mRdoModeAuto.isSelected() == true) {
        mRdoModeAuto_actionPerformed();
      } else if (mRdoModeVent.isSelected() == true) {
        mRdoModeVent_actionPerformed();
      } else if (mRdoModeNormal.isSelected() == true) {
        mRdoModeNormal_actionPerformed();
      }
    }
    else
    {
      DefaultAction localDefaultAction = getSelectedAction();
      localDefaultAction.setVentMode(EnumVentMode.ELSE);
    }
    mEventTable.redraw();
  }
  
  private void mChkVentMode_setEnabled()
  {
    if (mChkVentMode.isSelected() == true)
    {
      mRdoModeAuto.setEnabled(true);
      mRdoModeVent.setEnabled(true);
      mRdoModeNormal.setEnabled(true);
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
    if (mChangingTableSelect == true) {
      return;
    }
    mChkVentVol_setEnabled();
    if (mChkVentVol.isSelected() == true)
    {
      if (mRdoVolNormalAuto.isSelected() == true) {
        mRdoVolNormalAuto_actionPerformed();
      } else if (mRdoVolNormalLow.isSelected() == true) {
        mRdoVolNormalLow_actionPerformed();
      } else if (mRdoVolNormalHigh.isSelected() == true) {
        mRdoVolNormalHigh_actionPerformed();
      } else if (mRdoVolFreshAuto.isSelected() == true) {
        mRdoVolFreshAuto_actionPerformed();
      } else if (mRdoVolFreshLow.isSelected() == true) {
        mRdoVolFreshLow_actionPerformed();
      } else if (mRdoVolFreshHigh.isSelected() == true) {
        mRdoVolFreshHigh_actionPerformed();
      }
    }
    else
    {
      DefaultAction localDefaultAction = getSelectedAction();
      localDefaultAction.setVentVol(EnumVentVol.ELSE);
    }
    mEventTable.redraw();
  }
  
  private void mChkVentVol_setEnabled()
  {
    if (mChkVentVol.isSelected() == true)
    {
      mRdoVolNormalAuto.setEnabled(true);
      mRdoVolNormalLow.setEnabled(true);
      mRdoVolNormalHigh.setEnabled(true);
      mRdoVolFreshAuto.setEnabled(true);
      mRdoVolFreshLow.setEnabled(true);
      mRdoVolFreshHigh.setEnabled(true);
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
  
  void mCmbPattern_actionPerformed()
  {
    if (mWeekly == null) {
      return;
    }
    saveCurrentDaily();
    mSelectedDaily = mCmbPattern.getSelectedIndex();
    deleteAllEvent();
    Daily localDaily;
    if (mSelectedDaily < 7) {
      localDaily = mWeekly.getDaily(mSelectedDaily);
    } else {
      localDaily = mException.getDaily(mSelectedDaily - 7);
    }
    for (int i = 0; i < localDaily.getEventCount(); i++)
    {
      SchEvent localSchEvent = (SchEvent)localDaily.getEvent(i).clone();
      addEvent(localSchEvent, false);
    }
    if (mEventTable.getRowCount() != 0) {
      mEventTable.setRowSelectionInterval(0, 0);
    }
  }
  
  void mCmbSetTemp10_actionPerformed(boolean paramBoolean)
  {
    if ((paramBoolean == true) || (mChangingTableSelect == true)) {
      return;
    }
    if (mCmbSetTemp10.getSelectedIndex() == mCmbSetTemp10.getItemCount() - 1)
    {
      mCmbSetTemp1.removeAllItems();
      mCmbSetTemp1.addItem(" " + Integer.toString(0) + " ");
    }
    else if (mCmbSetTemp1.getItemCount() == 1)
    {
      for (int i = 1; i <= 9; i++) {
        mCmbSetTemp1.addItem(" " + i + " ");
      }
    }
    setTempChanged();
  }
  
  void mCmbSetTemp1_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    setTempChanged();
  }
  
  void mRdoAuto_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setDrvMode(EnumDrvMode.AUTOCOOL);
    mChkTemp_itemStateChanged(false);
    mEventTable.redraw();
  }
  
  void mRdoCool_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setDrvMode(EnumDrvMode.COOL);
    mChkTemp_itemStateChanged(false);
    mEventTable.redraw();
  }
  
  void mRdoDrvModePermit_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setRemcDrvMode(EnumPntStat.ON);
    mEventTable.redraw();
  }
  
  void mRdoDrvModeProhibit_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setRemcDrvMode(EnumPntStat.OFF);
    mEventTable.redraw();
  }
  
  void mRdoFan_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setDrvMode(EnumDrvMode.FAN);
    mChkTemp_itemStateChanged(false);
    mEventTable.redraw();
  }
  
  void mRdoHeat_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setDrvMode(EnumDrvMode.HEAT);
    mChkTemp_itemStateChanged(false);
    mEventTable.redraw();
  }
  
  void mRdoModeAuto_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentMode(EnumVentMode.AUTO);
    mEventTable.redraw();
  }
  
  void mRdoModeNormal_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentMode(EnumVentMode.NORMAL);
    mEventTable.redraw();
  }
  
  void mRdoModeVent_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentMode(EnumVentMode.VENTILATION);
    mEventTable.redraw();
  }
  
  void mRdoOff_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setOnOff(EnumPntStat.OFF);
    mEventTable.redraw();
  }
  
  void mRdoOnOffOffPermit_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setRemcCode(EnumRemcCode.STOP);
    mEventTable.redraw();
  }
  
  void mRdoOnOffPermit_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setRemcCode(EnumRemcCode.ENABLE);
    mEventTable.redraw();
  }
  
  void mRdoOnOffProhibit_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setRemcCode(EnumRemcCode.DISABLE);
    mEventTable.redraw();
  }
  
  void mRdoOn_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setOnOff(EnumPntStat.ON);
    mEventTable.redraw();
  }
  
  void mRdoTempPermit_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setRemcSetPoint(EnumPntStat.ON);
    mEventTable.redraw();
  }
  
  void mRdoTempProhibit_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setRemcSetPoint(EnumPntStat.OFF);
    mEventTable.redraw();
  }
  
  void mRdoTemp_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setDrvMode(EnumDrvMode.SUBMIT);
    mChkTemp_itemStateChanged(false);
    mEventTable.redraw();
  }
  
  void mRdoVolFreshAuto_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentVol(EnumVentVol.FRESH_AUTO);
    mEventTable.redraw();
  }
  
  void mRdoVolFreshHigh_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentVol(EnumVentVol.FRESH_HIGH);
    mEventTable.redraw();
  }
  
  void mRdoVolFreshLow_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentVol(EnumVentVol.FRESH_LOW);
    mEventTable.redraw();
  }
  
  void mRdoVolNormalAuto_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentVol(EnumVentVol.NORMAL_AUTO);
    mEventTable.redraw();
  }
  
  void mRdoVolNormalHigh_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentVol(EnumVentVol.NORMAL_HIGH);
    mEventTable.redraw();
  }
  
  void mRdoVolNormalLow_actionPerformed()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    DefaultAction localDefaultAction = getSelectedAction();
    localDefaultAction.setVentVol(EnumVentVol.NORMAL_LOW);
    mEventTable.redraw();
  }
  
  public void minSelected()
  {
    if (mChangingTableSelect == true) {
      return;
    }
    SchEvent localSchEvent = getSelectedEvent();
    localSchEvent.setMin(mTimePanel.getMin());
    mEventTable.sortTable(mEventTable.getSelectedRow());
    mEventTable.redraw();
  }
  
  private void partsInit()
  {
    mTimePanel.addListener(this);
    ListSelectionModel localListSelectionModel = mEventTable.getSelectionModel();
    localListSelectionModel.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (!paramAnonymousListSelectionEvent.getValueIsAdjusting()) {
          PanelActionSelect.this.changeTableSelect();
        }
      }
    });
    changeTableSelect();
  }
  
  public void saveCurrentDaily()
  {
    if (mSelectedDaily != -1)
    {
      Daily localDaily;
      if (mSelectedDaily < 7) {
        localDaily = mWeekly.getDaily(mSelectedDaily);
      } else {
        localDaily = mException.getDaily(mSelectedDaily - 7);
      }
      localDaily.removeAllEvent();
      for (int i = 0; i < getEventCount(); i++)
      {
        SchEvent localSchEvent = (SchEvent)getEvent(i).clone();
        localDaily.addEvent(localSchEvent);
      }
    }
  }
  
  public void setSchedule(AbstSchedule paramAbstSchedule, SchException paramSchException, int paramInt)
  {
    mWeekly = paramAbstSchedule;
    mException = paramSchException;
    mCmbPattern.removeAllItems();
    mCmbPattern.addItem(StrRes.getStr("IDS_COMMON_WDAY_0"));
    mCmbPattern.addItem(StrRes.getStr("IDS_COMMON_WDAY_1"));
    mCmbPattern.addItem(StrRes.getStr("IDS_COMMON_WDAY_2"));
    mCmbPattern.addItem(StrRes.getStr("IDS_COMMON_WDAY_3"));
    mCmbPattern.addItem(StrRes.getStr("IDS_COMMON_WDAY_4"));
    mCmbPattern.addItem(StrRes.getStr("IDS_COMMON_WDAY_5"));
    mCmbPattern.addItem(StrRes.getStr("IDS_COMMON_WDAY_6"));
    for (int i = 0; i < paramSchException.getDailyNum(); i++) {
      mCmbPattern.addItem(paramSchException.getExceptionName(i));
    }
    mCmbPattern.setSelectedIndex(paramInt);
  }
  
  public void setSelection(int paramInt)
  {
    mEventTable.setRowSelectionInterval(paramInt, paramInt);
  }
  
  private void setTempChanged()
  {
    DefaultAction localDefaultAction = getSelectedAction();
    if (localDefaultAction == null) {
      return;
    }
    if ((mChkTemp.isEnabled() == true) && (mChkTemp.isSelected() == true))
    {
      if (SystemInfo.isCentigrade() == true)
      {
        float f = mCmbSetTemp10.getSelectedIndex() + mLowTemp;
        f += mCmbSetTemp1.getSelectedIndex() / 10.0F;
        localDefaultAction.setSetPoint(new Temperature(true, f));
      }
      else
      {
        int i = Temperature.convertCtoF(mLowTemp);
        int j = mCmbSetTemp10.getSelectedIndex() + i;
        localDefaultAction.setSetPoint(new Temperature(true, Temperature.convertFtoC(j)));
      }
    }
    else {
      localDefaultAction.setSetPoint(new Temperature(false, 0.0F));
    }
    mEventTable.redraw();
  }
  
  private void updateButtons()
  {
    if (getEventCount() >= 16) {
      mBtnAdd.setEnabled(false);
    } else {
      mBtnAdd.setEnabled(true);
    }
    if (getEventCount() != 0) {
      mBtnDel.setEnabled(true);
    } else {
      mBtnDel.setEnabled(false);
    }
    if ((getEventCount() >= 16) || (getEventCount() <= 0)) {
      mBtnCopy.setEnabled(false);
    } else {
      mBtnCopy.setEnabled(true);
    }
  }
}
