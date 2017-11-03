package webitc.gui.schedule;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.data.schedule.AbstSchedule;
import webitc.data.schedule.Daily;
import webitc.data.schedule.Program;
import webitc.data.schedule.SchException;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorIcon;
import webitc.gui.common.ColorRes;
import webitc.gui.common.DlgSetName;
import webitc.gui.common.PanelAbstract;

class PanelEventList
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  ScheduleBar mBarEx1 = new ScheduleBar();
  ScheduleBar mBarEx10 = new ScheduleBar();
  ScheduleBar mBarEx2 = new ScheduleBar();
  ScheduleBar mBarEx3 = new ScheduleBar();
  ScheduleBar mBarEx4 = new ScheduleBar();
  ScheduleBar mBarEx5 = new ScheduleBar();
  ScheduleBar mBarEx6 = new ScheduleBar();
  ScheduleBar mBarEx7 = new ScheduleBar();
  ScheduleBar mBarEx8 = new ScheduleBar();
  ScheduleBar mBarEx9 = new ScheduleBar();
  ScheduleBar mBarFri = new ScheduleBar();
  ScheduleBar mBarMon = new ScheduleBar();
  ScheduleBar mBarSat = new ScheduleBar();
  ScheduleBar mBarSun = new ScheduleBar();
  ScheduleBar mBarThr = new ScheduleBar();
  ScheduleBar mBarTue = new ScheduleBar();
  ScheduleBar mBarWed = new ScheduleBar();
  JButton mBtnChange = new JButton();
  JButton mBtnDataOpe = new JButton();
  JButton mBtnNameChange = new JButton();
  ButtonGroup mGrpBar = new ButtonGroup();
  ButtonGroup mGrpSch = new ButtonGroup();
  JLabel mLblHour = new JLabel();
  JLabel mLblNo = new JLabel();
  JLabel mLblOff = new JLabel();
  JLabel mLblOn = new JLabel();
  JLabel mLblOther = new JLabel();
  private ArrayList mListenerList = new ArrayList();
  JPanel mPnlEx = new JPanel();
  private Program mProgram;
  JRadioButton mRdoEx1 = new JRadioButton();
  JRadioButton mRdoEx10 = new JRadioButton();
  JRadioButton mRdoEx2 = new JRadioButton();
  JRadioButton mRdoEx3 = new JRadioButton();
  JRadioButton mRdoEx4 = new JRadioButton();
  JRadioButton mRdoEx5 = new JRadioButton();
  JRadioButton mRdoEx6 = new JRadioButton();
  JRadioButton mRdoEx7 = new JRadioButton();
  JRadioButton mRdoEx8 = new JRadioButton();
  JRadioButton mRdoEx9 = new JRadioButton();
  JRadioButton mRdoFri = new JRadioButton();
  JRadioButton mRdoMon = new JRadioButton();
  JRadioButton mRdoSat = new JRadioButton();
  JRadioButton mRdoSun = new JRadioButton();
  JRadioButton mRdoThr = new JRadioButton();
  JRadioButton mRdoTue = new JRadioButton();
  JRadioButton mRdoWed = new JRadioButton();
  
  public PanelEventList()
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
  
  public void addListener(PanelEventListListener paramPanelEventListListener)
  {
    mListenerList.add(paramPanelEventListListener);
  }
  
  public void errorPerformed()
  {
    mLblNo.setEnabled(false);
    mBarSun.setEnabled(false);
    mBarMon.setEnabled(false);
    mBarTue.setEnabled(false);
    mBarWed.setEnabled(false);
    mBarThr.setEnabled(false);
    mBarFri.setEnabled(false);
    mBarSat.setEnabled(false);
    mBarEx1.setEnabled(false);
    mBarEx2.setEnabled(false);
    mBarEx3.setEnabled(false);
    mBarEx4.setEnabled(false);
    mBarEx5.setEnabled(false);
    mBarEx6.setEnabled(false);
    mBarEx7.setEnabled(false);
    mBarEx8.setEnabled(false);
    mBarEx9.setEnabled(false);
    mBarEx10.setEnabled(false);
    mRdoSun.setEnabled(false);
    mRdoMon.setEnabled(false);
    mRdoTue.setEnabled(false);
    mRdoWed.setEnabled(false);
    mRdoThr.setEnabled(false);
    mRdoFri.setEnabled(false);
    mRdoSat.setEnabled(false);
    mRdoEx1.setEnabled(false);
    mRdoEx2.setEnabled(false);
    mRdoEx3.setEnabled(false);
    mRdoEx4.setEnabled(false);
    mRdoEx5.setEnabled(false);
    mRdoEx6.setEnabled(false);
    mRdoEx7.setEnabled(false);
    mRdoEx8.setEnabled(false);
    mRdoEx9.setEnabled(false);
    mRdoEx10.setEnabled(false);
    mLblOn.setEnabled(false);
    mLblOff.setEnabled(false);
    mLblOther.setEnabled(false);
    mBtnChange.setEnabled(false);
    mBtnNameChange.setEnabled(false);
    mBtnDataOpe.setEnabled(false);
  }
  
  private int getgetSelectedDailyIndex()
  {
    if (mRdoSun.isSelected() == true) {
      return 0;
    }
    if (mRdoMon.isSelected() == true) {
      return 1;
    }
    if (mRdoTue.isSelected() == true) {
      return 2;
    }
    if (mRdoWed.isSelected() == true) {
      return 3;
    }
    if (mRdoThr.isSelected() == true) {
      return 4;
    }
    if (mRdoFri.isSelected() == true) {
      return 5;
    }
    if (mRdoSat.isSelected() == true) {
      return 6;
    }
    if (mRdoEx1.isSelected() == true) {
      return 7;
    }
    if (mRdoEx2.isSelected() == true) {
      return 8;
    }
    if (mRdoEx3.isSelected() == true) {
      return 9;
    }
    if (mRdoEx4.isSelected() == true) {
      return 10;
    }
    if (mRdoEx5.isSelected() == true) {
      return 11;
    }
    if (mRdoEx6.isSelected() == true) {
      return 12;
    }
    if (mRdoEx7.isSelected() == true) {
      return 13;
    }
    if (mRdoEx8.isSelected() == true) {
      return 14;
    }
    if (mRdoEx9.isSelected() == true) {
      return 15;
    }
    if (mRdoEx10.isSelected() == true) {
      return 16;
    }
    throw new FatalException("PanelPntZoneList.mBtnChange_actionPerformed");
  }
  
  private void jbInit()
    throws Exception
  {
    setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCH_PATTERN_LIST")));
    setLayout(gridBagLayout1);
    mLblNo.setVerifyInputWhenFocusTarget(true);
    mLblNo.setText("0        3       6        9       12     15      18     21    24");
    mRdoSun.setMargin(new Insets(0, 2, 0, 2));
    mRdoSun.setMnemonic('0');
    mRdoSun.setText(StrRes.getStr("IDS_COMMON_WDAY_0"));
    mRdoSun.addActionListener(new PanelScheduleSetting_mRdoSun_actionAdapter(this));
    mRdoMon.setText(StrRes.getStr("IDS_COMMON_WDAY_1"));
    mRdoMon.addActionListener(new PanelScheduleSetting_mRdoMon_actionAdapter(this));
    mRdoMon.setMargin(new Insets(0, 2, 0, 2));
    mRdoMon.setMnemonic('0');
    mRdoTue.setText(StrRes.getStr("IDS_COMMON_WDAY_2"));
    mRdoTue.addActionListener(new PanelScheduleSetting_mRdoTue_actionAdapter(this));
    mRdoTue.setMargin(new Insets(0, 2, 0, 2));
    mRdoTue.setMnemonic('0');
    mRdoWed.setText(StrRes.getStr("IDS_COMMON_WDAY_3"));
    mRdoWed.addActionListener(new PanelScheduleSetting_mRdoWed_actionAdapter(this));
    mRdoWed.setMargin(new Insets(0, 2, 0, 2));
    mRdoWed.setMnemonic('0');
    mRdoThr.setText(StrRes.getStr("IDS_COMMON_WDAY_4"));
    mRdoThr.addActionListener(new PanelScheduleSetting_mRdoThr_actionAdapter(this));
    mRdoThr.setMargin(new Insets(0, 2, 0, 2));
    mRdoThr.setMnemonic('0');
    mRdoFri.setText(StrRes.getStr("IDS_COMMON_WDAY_5"));
    mRdoFri.addActionListener(new PanelScheduleSetting_mRdoFri_actionAdapter(this));
    mRdoFri.setMargin(new Insets(0, 2, 0, 2));
    mRdoFri.setMnemonic('0');
    mRdoSat.setText(StrRes.getStr("IDS_COMMON_WDAY_6"));
    mRdoSat.addActionListener(new PanelScheduleSetting_mRdoSat_actionAdapter(this));
    mRdoSat.setMargin(new Insets(0, 2, 0, 2));
    mRdoSat.setMnemonic('0');
    mRdoEx1.setText("");
    mRdoEx1.addActionListener(new PanelScheduleSetting_mRdoEx1_actionAdapter(this));
    mRdoEx1.setVerifyInputWhenFocusTarget(true);
    mRdoEx1.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx1.setMnemonic('0');
    mRdoEx1.setSelected(false);
    mRdoEx2.setText("");
    mRdoEx2.addActionListener(new PanelScheduleSetting_mRdoEx2_actionAdapter(this));
    mRdoEx2.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx2.setMnemonic('0');
    mRdoEx3.setText("");
    mRdoEx3.addActionListener(new PanelScheduleSetting_mRdoEx3_actionAdapter(this));
    mRdoEx3.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx3.setMnemonic('0');
    mRdoEx4.setText("");
    mRdoEx4.addActionListener(new PanelScheduleSetting_mRdoEx4_actionAdapter(this));
    mRdoEx4.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx4.setMnemonic('0');
    mRdoEx5.setText("");
    mRdoEx5.addActionListener(new PanelScheduleSetting_mRdoEx5_actionAdapter(this));
    mRdoEx5.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx5.setMnemonic('0');
    mRdoEx6.setText("");
    mRdoEx6.addActionListener(new PanelScheduleSetting_mRdoEx6_actionAdapter(this));
    mRdoEx6.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx6.setMnemonic('0');
    mRdoEx7.setText("");
    mRdoEx7.addActionListener(new PanelScheduleSetting_mRdoEx7_actionAdapter(this));
    mRdoEx7.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx7.setMnemonic('0');
    mRdoEx8.setText("");
    mRdoEx8.addActionListener(new PanelScheduleSetting_mRdoEx8_actionAdapter(this));
    mRdoEx8.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx8.setMnemonic('0');
    mRdoEx9.setText("");
    mRdoEx9.addActionListener(new PanelScheduleSetting_mRdoEx9_actionAdapter(this));
    mRdoEx9.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx9.setMnemonic('0');
    mRdoEx10.setText("");
    mRdoEx10.addActionListener(new PanelScheduleSetting_mRdoEx10_actionAdapter(this));
    mRdoEx10.setMargin(new Insets(0, 2, 0, 2));
    mRdoEx10.setMnemonic('0');
    mLblOn.setText(StrRes.getStr("IDS_COMMON_START"));
    mLblOn.setIcon(new ColorIcon(ColorRes.ON));
    mLblOff.setText(StrRes.getStr("IDS_COMMON_STOP"));
    mLblOff.setIcon(new ColorIcon(ColorRes.OFF));
    mLblOther.setText(StrRes.getStr("IDS_COMMON_OTHER"));
    mLblOther.setIcon(new ColorIcon(ColorRes.CALENDAR_BLACK));
    mPnlEx.setLayout(gridBagLayout2);
    mBtnChange.setMargin(new Insets(2, 7, 2, 7));
    mBtnChange.setText(StrRes.getStr("IDS_SCH_CHANGE_PATTERN"));
    mBtnChange.addActionListener(new PanelScheduleSetting_mBtnChange_actionAdapter(this));
    mBarSun.addActionListener(new PanelScheduleSetting_mBarSun_actionAdapter(this));
    mBarMon.addActionListener(new PanelScheduleSetting_mBarMon_actionAdapter(this));
    mBarTue.addActionListener(new PanelScheduleSetting_mBarTue_actionAdapter(this));
    mBarWed.addActionListener(new PanelScheduleSetting_mBarWed_actionAdapter(this));
    mBarThr.addActionListener(new PanelScheduleSetting_mBarThr_actionAdapter(this));
    mBarFri.addActionListener(new PanelScheduleSetting_mBarFri_actionAdapter(this));
    mBarSat.addActionListener(new PanelScheduleSetting_mBarSat_actionAdapter(this));
    mBarEx1.addActionListener(new PanelScheduleSetting_mBarEx1_actionAdapter(this));
    mBarEx2.addActionListener(new PanelScheduleSetting_mBarEx2_actionAdapter(this));
    mBarEx3.addActionListener(new PanelScheduleSetting_mBarEx3_actionAdapter(this));
    mBarEx4.addActionListener(new PanelScheduleSetting_mBarEx4_actionAdapter(this));
    mBarEx5.addActionListener(new PanelScheduleSetting_mBarEx5_actionAdapter(this));
    mBarEx6.addActionListener(new PanelScheduleSetting_mBarEx6_actionAdapter(this));
    mBarEx7.addActionListener(new PanelScheduleSetting_mBarEx7_actionAdapter(this));
    mBarEx8.addActionListener(new PanelScheduleSetting_mBarEx8_actionAdapter(this));
    mBarEx9.addActionListener(new PanelScheduleSetting_mBarEx9_actionAdapter(this));
    mBarEx10.addActionListener(new PanelScheduleSetting_mBarEx10_actionAdapter(this));
    mBtnNameChange.setMargin(new Insets(2, 7, 2, 7));
    mBtnNameChange.setText(StrRes.getStr("IDS_COMMON_CHANGE_NAME"));
    mBtnNameChange.addActionListener(new PanelScheduleSetting_mBtnNameChange_actionAdapter(this));
    mLblHour.setText(StrRes.getStr("IDS_COMMON_UNIT_HOUR"));
    mBtnDataOpe.setText(StrRes.getStr("IDS_SCH_DATA_OPE"));
    mBtnDataOpe.addActionListener(new PanelEventList_mBtnDataOpe_actionAdapter(this));
    mPnlEx.add(mLblOther, new GridBagConstraints(0, 2, 1, 1, 0.0D, 1.0D, 18, 0, new Insets(3, 5, 0, 5), 0, 0));
    mPnlEx.add(mLblOff, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    add(mBarSun, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mLblNo, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarMon, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarTue, new GridBagConstraints(1, 3, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarWed, new GridBagConstraints(1, 4, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarThr, new GridBagConstraints(1, 5, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarFri, new GridBagConstraints(1, 6, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarSat, new GridBagConstraints(1, 7, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx1, new GridBagConstraints(1, 8, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(5, 0, 0, 0), 0, 0));
    add(mBarEx2, new GridBagConstraints(1, 9, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx3, new GridBagConstraints(1, 10, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx4, new GridBagConstraints(1, 11, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx5, new GridBagConstraints(1, 12, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx6, new GridBagConstraints(1, 13, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx7, new GridBagConstraints(1, 14, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx8, new GridBagConstraints(1, 15, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx9, new GridBagConstraints(1, 16, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBarEx10, new GridBagConstraints(1, 17, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoSun, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoMon, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoTue, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoWed, new GridBagConstraints(0, 4, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoThr, new GridBagConstraints(0, 5, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoFri, new GridBagConstraints(0, 6, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoSat, new GridBagConstraints(0, 7, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx1, new GridBagConstraints(0, 8, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(5, 0, 0, 0), 0, 0));
    add(mRdoEx2, new GridBagConstraints(0, 9, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx3, new GridBagConstraints(0, 10, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx4, new GridBagConstraints(0, 11, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx5, new GridBagConstraints(0, 12, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx6, new GridBagConstraints(0, 13, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx7, new GridBagConstraints(0, 14, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx8, new GridBagConstraints(0, 15, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx9, new GridBagConstraints(0, 16, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mRdoEx10, new GridBagConstraints(0, 17, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mPnlEx, new GridBagConstraints(0, 18, 2, 1, 1.0D, 1.0D, 18, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPnlEx.add(mLblOn, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 5, 0, 5), 0, 0));
    mPnlEx.add(mBtnChange, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 8, 0, 0), 0, 0));
    mPnlEx.add(mBtnNameChange, new GridBagConstraints(1, 1, 1, 1, 1.0D, 0.0D, 17, 0, new Insets(0, 8, 0, 0), 0, 0));
    mPnlEx.add(mBtnDataOpe, new GridBagConstraints(1, 2, 1, 1, 1.0D, 0.0D, 18, 0, new Insets(0, 8, 0, 0), 0, 0));
    add(mLblHour, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mGrpSch.add(mRdoSun);
    mGrpSch.add(mRdoMon);
    mGrpSch.add(mRdoTue);
    mGrpSch.add(mRdoWed);
    mGrpSch.add(mRdoThr);
    mGrpSch.add(mRdoFri);
    mGrpSch.add(mRdoSat);
    mGrpSch.add(mRdoEx1);
    mGrpSch.add(mRdoEx2);
    mGrpSch.add(mRdoEx3);
    mGrpSch.add(mRdoEx4);
    mGrpSch.add(mRdoEx5);
    mGrpSch.add(mRdoEx6);
    mGrpSch.add(mRdoEx7);
    mGrpSch.add(mRdoEx8);
    mGrpSch.add(mRdoEx9);
    mGrpSch.add(mRdoEx10);
    mGrpBar.add(mBarSun);
    mGrpBar.add(mBarMon);
    mGrpBar.add(mBarTue);
    mGrpBar.add(mBarWed);
    mGrpBar.add(mBarThr);
    mGrpBar.add(mBarFri);
    mGrpBar.add(mBarSat);
    mGrpBar.add(mBarEx1);
    mGrpBar.add(mBarEx2);
    mGrpBar.add(mBarEx3);
    mGrpBar.add(mBarEx4);
    mGrpBar.add(mBarEx5);
    mGrpBar.add(mBarEx6);
    mGrpBar.add(mBarEx7);
    mGrpBar.add(mBarEx8);
    mGrpBar.add(mBarEx9);
    mGrpBar.add(mBarEx10);
    mRdoSun.setSelected(true);
    mBarSun.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBarEx10_actionPerformed()
  {
    mRdoEx10.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx1_actionPerformed()
  {
    mRdoEx1.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx2_actionPerformed()
  {
    mRdoEx2.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx3_actionPerformed()
  {
    mRdoEx3.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx4_actionPerformed()
  {
    mRdoEx4.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx5_actionPerformed()
  {
    mRdoEx5.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx6_actionPerformed()
  {
    mRdoEx6.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx7_actionPerformed()
  {
    mRdoEx7.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx8_actionPerformed()
  {
    mRdoEx8.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarEx9_actionPerformed()
  {
    mRdoEx9.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mBarFri_actionPerformed()
  {
    mRdoFri.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBarMon_actionPerformed()
  {
    mRdoMon.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBarSat_actionPerformed()
  {
    mRdoSat.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBarSun_actionPerformed()
  {
    mRdoSun.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBarThr_actionPerformed()
  {
    mRdoThr.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBarTue_actionPerformed()
  {
    mRdoTue.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBarWed_actionPerformed()
  {
    mRdoWed.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mBtnChange_actionPerformed()
  {
    DlgSchAction localDlgSchAction = new DlgSchAction(mProgram, getgetSelectedDailyIndex());
    localDlgSchAction.doModal();
    if (localDlgSchAction.isOK() == true) {
      update();
    }
  }
  
  void mBtnDataOpe_actionPerformed()
  {
    DlgSchDataPattern localDlgSchDataPattern = new DlgSchDataPattern(mProgram);
    localDlgSchDataPattern.doModal();
    if (localDlgSchDataPattern.isOK() == true) {
      update();
    }
  }
  
  void mBtnNameChange_actionPerformed()
  {
    int i;
    JRadioButton localJRadioButton;
    if (mRdoEx1.isSelected() == true)
    {
      i = 0;
      localJRadioButton = mRdoEx1;
    }
    else if (mRdoEx2.isSelected() == true)
    {
      i = 1;
      localJRadioButton = mRdoEx2;
    }
    else if (mRdoEx3.isSelected() == true)
    {
      i = 2;
      localJRadioButton = mRdoEx3;
    }
    else if (mRdoEx4.isSelected() == true)
    {
      i = 3;
      localJRadioButton = mRdoEx4;
    }
    else if (mRdoEx5.isSelected() == true)
    {
      i = 4;
      localJRadioButton = mRdoEx5;
    }
    else if (mRdoEx6.isSelected() == true)
    {
      i = 5;
      localJRadioButton = mRdoEx6;
    }
    else if (mRdoEx7.isSelected() == true)
    {
      i = 6;
      localJRadioButton = mRdoEx7;
    }
    else if (mRdoEx8.isSelected() == true)
    {
      i = 7;
      localJRadioButton = mRdoEx8;
    }
    else if (mRdoEx9.isSelected() == true)
    {
      i = 8;
      localJRadioButton = mRdoEx9;
    }
    else if (mRdoEx10.isSelected() == true)
    {
      i = 9;
      localJRadioButton = mRdoEx10;
    }
    else
    {
      throw new FatalException("PanelScheduleSetting.mBtnNameChange_actionPerformed");
    }
    DlgSetName localDlgSetName = new DlgSetName(mProgram.getException().getExceptionName(i), 8);
    localDlgSetName.doModal();
    if (localDlgSetName.isOK() == true)
    {
      SchException localSchException = mProgram.getException();
      localSchException.setExceptionName(i, localDlgSetName.getName());
      localJRadioButton.setText(localDlgSetName.getName());
      for (int j = 0; j < mListenerList.size(); j++)
      {
        PanelEventListListener localPanelEventListListener = (PanelEventListListener)mListenerList.get(j);
        localPanelEventListListener.exceptionNameChanged(i, localDlgSetName.getName());
      }
    }
  }
  
  void mRdoEx10_actionPerformed()
  {
    mBarEx10.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx1_actionPerformed()
  {
    mBarEx1.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx2_actionPerformed()
  {
    mBarEx2.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx3_actionPerformed()
  {
    mBarEx3.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx4_actionPerformed()
  {
    mBarEx4.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx5_actionPerformed()
  {
    mBarEx5.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx6_actionPerformed()
  {
    mBarEx6.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx7_actionPerformed()
  {
    mBarEx7.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx8_actionPerformed()
  {
    mBarEx8.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoEx9_actionPerformed()
  {
    mBarEx9.setSelected(true);
    mBtnNameChange.setEnabled(true);
  }
  
  void mRdoFri_actionPerformed()
  {
    mBarFri.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mRdoMon_actionPerformed()
  {
    mBarMon.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mRdoSat_actionPerformed()
  {
    mBarSat.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mRdoSun_actionPerformed()
  {
    mBarSun.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mRdoThr_actionPerformed()
  {
    mBarThr.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mRdoTue_actionPerformed()
  {
    mBarTue.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  void mRdoWed_actionPerformed()
  {
    mBarWed.setSelected(true);
    mBtnNameChange.setEnabled(false);
  }
  
  protected void setProgram(Program paramProgram)
  {
    mProgram = paramProgram;
    update();
  }
  
  private void update()
  {
    updateScheduleBar(mRdoSun, mBarSun, StrRes.getStr("IDS_COMMON_WDAY_0"), mProgram.getWeekly().getDaily(0));
    updateScheduleBar(mRdoMon, mBarMon, StrRes.getStr("IDS_COMMON_WDAY_1"), mProgram.getWeekly().getDaily(1));
    updateScheduleBar(mRdoTue, mBarTue, StrRes.getStr("IDS_COMMON_WDAY_2"), mProgram.getWeekly().getDaily(2));
    updateScheduleBar(mRdoWed, mBarWed, StrRes.getStr("IDS_COMMON_WDAY_3"), mProgram.getWeekly().getDaily(3));
    updateScheduleBar(mRdoThr, mBarThr, StrRes.getStr("IDS_COMMON_WDAY_4"), mProgram.getWeekly().getDaily(4));
    updateScheduleBar(mRdoFri, mBarFri, StrRes.getStr("IDS_COMMON_WDAY_5"), mProgram.getWeekly().getDaily(5));
    updateScheduleBar(mRdoSat, mBarSat, StrRes.getStr("IDS_COMMON_WDAY_6"), mProgram.getWeekly().getDaily(6));
    updateScheduleBar(mRdoEx1, mBarEx1, mProgram.getException().getExceptionName(0), mProgram.getException().getDaily(0));
    updateScheduleBar(mRdoEx2, mBarEx2, mProgram.getException().getExceptionName(1), mProgram.getException().getDaily(1));
    updateScheduleBar(mRdoEx3, mBarEx3, mProgram.getException().getExceptionName(2), mProgram.getException().getDaily(2));
    updateScheduleBar(mRdoEx4, mBarEx4, mProgram.getException().getExceptionName(3), mProgram.getException().getDaily(3));
    updateScheduleBar(mRdoEx5, mBarEx5, mProgram.getException().getExceptionName(4), mProgram.getException().getDaily(4));
    updateScheduleBar(mRdoEx6, mBarEx6, mProgram.getException().getExceptionName(5), mProgram.getException().getDaily(5));
    updateScheduleBar(mRdoEx7, mBarEx7, mProgram.getException().getExceptionName(6), mProgram.getException().getDaily(6));
    updateScheduleBar(mRdoEx8, mBarEx8, mProgram.getException().getExceptionName(7), mProgram.getException().getDaily(7));
    updateScheduleBar(mRdoEx9, mBarEx9, mProgram.getException().getExceptionName(8), mProgram.getException().getDaily(8));
    updateScheduleBar(mRdoEx10, mBarEx10, mProgram.getException().getExceptionName(9), mProgram.getException().getDaily(9));
  }
  
  private void updateScheduleBar(JRadioButton paramJRadioButton, ScheduleBar paramScheduleBar, String paramString, Daily paramDaily)
  {
    paramJRadioButton.setText(paramString);
    paramScheduleBar.setDaily(paramDaily);
    paramScheduleBar.updateUI();
  }
}
