package webitc.gui.ppd;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumModelType;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.common.BorderRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgEditDate;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.calendar.SingleCalendarExtendOption;
import webitc.job.JobGetPpdCollectionType;
import webitc.job.JobSetPpdCollectionType;

public class PanelActionButtons
  extends PanelAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JButton mBtnChangeEnd = new JButton();
  JButton mBtnChangeStart = new JButton();
  JButton mBtnContract = new JButton();
  JButton mBtnExclusionPeriod = new JButton();
  JButton mBtnExec = new JButton();
  JButton mBtnNightRate = new JButton();
  JRadioButton mBtnSetMonth = new JRadioButton();
  JRadioButton mBtnSetPeriod = new JRadioButton();
  JButton mBtnSpecialDay = new JButton();
  JPanel mCollectionPanel = new JPanel();
  JComboBox mComboDay = new JComboBox();
  int[] mContractPwr = new int[12];
  Calendar mCtrlDate = null;
  Calendar mEndDate = null;
  JLabel mLblEndPeriod = new JLabel();
  JLabel mLblSetDay = new JLabel();
  JLabel mLblStartPeriod = new JLabel();
  private ActionButtonsListener mListener = null;
  JPanel mSettingPanel = new JPanel();
  Calendar mStartDate = null;
  JTextField mTextEndPeriod = new JTextField();
  JTextField mTextStartPeriod = new JTextField();
  JLabel mVerticalDummy = new JLabel();
  
  public PanelActionButtons(ActionButtonsListener paramActionButtonsListener)
  {
    try
    {
      mListener = paramActionButtonsListener;
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public int getContractPwr(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mContractPwr.length)) {
      return 0;
    }
    return mContractPwr[paramInt];
  }
  
  public Calendar getEndTime()
  {
    if (mBtnSetPeriod.isSelected()) {
      return mEndDate;
    }
    int i = mComboDay.getSelectedIndex() + 1;
    Calendar localCalendar = (Calendar)mCtrlDate.clone();
    if (i > mCtrlDate.get(5)) {
      localCalendar.add(2, -1);
    }
    if (i > localCalendar.getActualMaximum(5))
    {
      localCalendar.add(2, 1);
      localCalendar.set(5, 1);
    }
    else
    {
      localCalendar.set(5, i);
    }
    localCalendar.add(5, -1);
    return localCalendar;
  }
  
  public Calendar getStartTime()
  {
    if (mBtnSetPeriod.isSelected()) {
      return mStartDate;
    }
    int i = mComboDay.getSelectedIndex() + 1;
    Calendar localCalendar = (Calendar)mCtrlDate.clone();
    if (i <= mCtrlDate.get(5)) {
      localCalendar.add(2, -1);
    } else {
      localCalendar.add(2, -2);
    }
    if (i > localCalendar.getActualMaximum(5))
    {
      localCalendar.add(2, 1);
      localCalendar.set(5, 1);
    }
    else
    {
      localCalendar.set(5, i);
    }
    return localCalendar;
  }
  
  public boolean isPeriodSelected()
  {
    return mBtnSetPeriod.isSelected();
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setMinimumSize(new Dimension(320, 200));
    mCollectionPanel.setLayout(gridBagLayout2);
    mCollectionPanel.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_PPD_AMOUNT_PERIOD")));
    mBtnSetPeriod.setText(StrRes.getStr("IDS_PPD_SET_PRIOD"));
    mBtnSetPeriod.addActionListener(new PanelActionButtons_mBtnSetPeriod_actionAdapter(this));
    mBtnSetMonth.setText(StrRes.getStr("IDS_PPDCOLLECTIONPERIOD_USE_MON"));
    mBtnSetMonth.addActionListener(new PanelActionButtons_mBtnSetMonth_actionAdapter(this));
    mLblStartPeriod.setText(StrRes.getStr("IDS_PPDCOLLECTIONPERIOD_START"));
    mLblEndPeriod.setText(StrRes.getStr("IDS_PPDCOLLECTIONPERIOD_END"));
    mLblSetDay.setText(StrRes.getStr("IDS_PPDCOLLECTIONPERIOD_DAY"));
    mBtnChangeStart.setText(StrRes.getStr("IDS_COMMON_MODIFY"));
    mBtnChangeStart.addActionListener(new PanelActionButtons_mBtnChangeStart_actionAdapter(this));
    mBtnChangeEnd.setText(StrRes.getStr("IDS_COMMON_MODIFY"));
    mBtnChangeEnd.addActionListener(new PanelActionButtons_mBtnChangeEnd_actionAdapter(this));
    mBtnContract.setText(StrRes.getStr("IDS_PPDCOLLECTION_CONTRACT_PWR"));
    mBtnContract.addActionListener(new PanelActionButtons_mBtnContract_actionAdapter(this));
    mBtnExec.setText(StrRes.getStr("IDS_SCHDDATAOPE_APPLY"));
    mBtnExec.addActionListener(new PanelActionButtons_mBtnExec_actionAdapter(this));
    if (SystemInfo.getModelType() == EnumModelType.ITC)
    {
      mBtnExclusionPeriod.setText(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_TITLE"));
      mBtnSpecialDay.setText(StrRes.getStr("IDS_PPDSPECIALDAY_TITLE"));
    }
    else if ((SystemInfo.getModelType() == EnumModelType.ACS) || (SystemInfo.getModelType() == EnumModelType.EXC))
    {
      mBtnExclusionPeriod.setText(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_TITLE_2"));
      mBtnSpecialDay.setText(StrRes.getStr("IDS_PPDSPECIALDAY_TITLE_2"));
    }
    else
    {
      mBtnExclusionPeriod.setText(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_TITLE_GHP"));
      mBtnSpecialDay.setText(StrRes.getStr("IDS_PPDSPECIALDAY_TITLE_GHP"));
    }
    mBtnExclusionPeriod.addActionListener(new PanelActionButtons_mBtnExclusionPeriod_actionAdapter(this));
    mBtnSpecialDay.addActionListener(new PanelActionButtons_mBtnSpecialDay_actionAdapter(this));
    mBtnNightRate.setText(StrRes.getStr("IDS_PPDNIGHTRATETIME_TITLE"));
    mBtnNightRate.addActionListener(new PanelActionButtons_mBtnNightRate_actionAdapter(this));
    ButtonGroup localButtonGroup = new ButtonGroup();
    localButtonGroup.add(mBtnSetPeriod);
    localButtonGroup.add(mBtnSetMonth);
    mBtnSetPeriod.setSelected(true);
    mTextStartPeriod.setEditable(false);
    mTextStartPeriod.setHorizontalAlignment(4);
    mTextEndPeriod.setEditable(false);
    mTextEndPeriod.setHorizontalAlignment(4);
    mCollectionPanel.add(mBtnSetPeriod, new GridBagConstraints(0, 0, 4, 1, 1.0D, 0.0D, 17, 2, new Insets(5, 10, 5, 10), 0, 0));
    mCollectionPanel.add(mBtnSetMonth, new GridBagConstraints(0, 3, 4, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 10, 5, 10), 0, 0));
    mCollectionPanel.add(mLblStartPeriod, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 30, 5, 10), 0, 0));
    mCollectionPanel.add(mLblEndPeriod, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 30, 10, 10), 0, 0));
    mCollectionPanel.add(mLblSetDay, new GridBagConstraints(0, 4, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 30, 0, 10), 0, 0));
    mCollectionPanel.add(mBtnChangeStart, new GridBagConstraints(3, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 10, 5, 10), 0, 0));
    mCollectionPanel.add(mComboDay, new GridBagConstraints(2, 4, 1, 2, 0.0D, 0.0D, 17, 0, new Insets(0, 10, 5, 10), 0, 0));
    mCollectionPanel.add(mBtnChangeEnd, new GridBagConstraints(3, 2, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 10, 5, 10), 0, 0));
    mCollectionPanel.add(mBtnContract, new GridBagConstraints(0, 6, 4, 1, 1.0D, 0.0D, 10, 2, new Insets(10, 10, 0, 10), 0, 0));
    mCollectionPanel.add(mBtnExec, new GridBagConstraints(0, 7, 4, 1, 1.0D, 0.0D, 10, 2, new Insets(5, 10, 5, 10), 0, 0));
    mCollectionPanel.add(mTextStartPeriod, new GridBagConstraints(1, 1, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 10, 5, 10), 0, 0));
    mCollectionPanel.add(mTextEndPeriod, new GridBagConstraints(1, 2, 2, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 10, 5, 10), 0, 0));
    mSettingPanel.setLayout(gridBagLayout3);
    mSettingPanel.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_SETUP")));
    mSettingPanel.add(mBtnExclusionPeriod, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(10, 10, 0, 10), 0, 0));
    mSettingPanel.add(mBtnSpecialDay, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(5, 10, 0, 10), 0, 0));
    mSettingPanel.add(mBtnNightRate, new GridBagConstraints(0, 2, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(5, 10, 5, 10), 0, 0));
    add(mCollectionPanel, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 11, 2, new Insets(0, 10, 0, 10), 0, 0));
    add(mVerticalDummy, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    add(mSettingPanel, new GridBagConstraints(0, 2, 1, 1, 1.0D, 0.0D, 15, 2, new Insets(0, 10, 0, 10), 0, 0));
    for (int i = 1; i <= 31; i++) {
      mComboDay.addItem(String.valueOf(i));
    }
    mComboDay.setSelectedIndex(19);
    updateButtons();
    if ((SystemInfo.getModelType() == EnumModelType.VEUP) && (SystemInfo.getPpdType() == 0))
    {
      mBtnContract.setVisible(true);
      mBtnNightRate.setVisible(true);
    }
    else
    {
      mBtnContract.setVisible(false);
      mBtnNightRate.setVisible(false);
    }
  }
  
  void mBtnChangeEnd_actionPerformed(ActionEvent paramActionEvent)
  {
    SingleCalendarExtendOption localSingleCalendarExtendOption = new SingleCalendarExtendOption();
    localSingleCalendarExtendOption.createStartDate(mStartDate.get(1), mStartDate.get(2) + 1, mStartDate.get(5));
    Calendar localCalendar = (Calendar)mCtrlDate.clone();
    localCalendar.add(5, -1);
    localSingleCalendarExtendOption.createEndDate(localCalendar.get(1), localCalendar.get(2) + 1, localCalendar.get(5));
    DlgEditDate localDlgEditDate = new DlgEditDate(StrRes.getStr("IDS_PPDCOLLECTIONPERIOD_END"), localSingleCalendarExtendOption);
    localDlgEditDate.setDefaultDate(mEndDate.get(1), mEndDate.get(2) + 1, mEndDate.get(5));
    localDlgEditDate.doModal();
    if (!localDlgEditDate.isOK()) {
      return;
    }
    mEndDate = localDlgEditDate.getSelectedDate();
    mTextEndPeriod.setText(StrRes.getYMDStr2(mEndDate, true));
  }
  
  void mBtnChangeStart_actionPerformed(ActionEvent paramActionEvent)
  {
    SingleCalendarExtendOption localSingleCalendarExtendOption = new SingleCalendarExtendOption();
    localSingleCalendarExtendOption.createStartDate(mCtrlDate.get(1) - 1, mCtrlDate.get(2) + 1, 1);
    localSingleCalendarExtendOption.createEndDate(mEndDate.get(1), mEndDate.get(2) + 1, mEndDate.get(5));
    DlgEditDate localDlgEditDate = new DlgEditDate(StrRes.getStr("IDS_PPDCOLLECTIONPERIOD_START"), localSingleCalendarExtendOption);
    localDlgEditDate.setDefaultDate(mStartDate.get(1), mStartDate.get(2) + 1, mStartDate.get(5));
    localDlgEditDate.doModal();
    if (!localDlgEditDate.isOK()) {
      return;
    }
    mStartDate = localDlgEditDate.getSelectedDate();
    mTextStartPeriod.setText(StrRes.getYMDStr2(mStartDate, true));
  }
  
  void mBtnContract_actionPerformed(ActionEvent paramActionEvent)
  {
    DlgSetContractPwr localDlgSetContractPwr = new DlgSetContractPwr(mContractPwr);
    localDlgSetContractPwr.doModal();
    if (!localDlgSetContractPwr.isOK()) {
      return;
    }
    mContractPwr = localDlgSetContractPwr.getContractPwr();
  }
  
  void mBtnExclusionPeriod_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyExclusionPeriodSelected();
    }
  }
  
  void mBtnExec_actionPerformed(ActionEvent paramActionEvent)
  {
    if (!CommonUse.showConfirmDlg(StrRes.getStr("IDS_PPD_MSG_CONFIRM_EXECUTION"), StrRes.getStr("IDS_PPDRESULTOUTPUT_GATHER"))) {
      return;
    }
    boolean bool = mBtnSetPeriod.isSelected();
    int i = mComboDay.getSelectedIndex() + 1;
    JobSetPpdCollectionType localJobSetPpdCollectionType = new JobSetPpdCollectionType(bool, i, mContractPwr);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobSetPpdCollectionType);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobSetPpdCollectionType.getResult() != 1) {
      throw new FatalException("PpdCollectionType set");
    }
    if (mListener != null) {
      mListener.notifyExecSelected();
    }
  }
  
  void mBtnNightRate_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifyNightRateSelected();
    }
  }
  
  void mBtnSetMonth_actionPerformed(ActionEvent paramActionEvent)
  {
    updateButtons();
  }
  
  void mBtnSetPeriod_actionPerformed(ActionEvent paramActionEvent)
  {
    updateButtons();
  }
  
  void mBtnSpecialDay_actionPerformed(ActionEvent paramActionEvent)
  {
    if (mListener != null) {
      mListener.notifySpecialDaySelected();
    }
  }
  
  private void updateButtons()
  {
    boolean bool = mBtnSetPeriod.isSelected();
    mLblStartPeriod.setEnabled(bool);
    mLblEndPeriod.setEnabled(bool);
    mTextStartPeriod.setEnabled(bool);
    mTextEndPeriod.setEnabled(bool);
    mBtnChangeStart.setEnabled(bool);
    mBtnChangeEnd.setEnabled(bool);
    mLblSetDay.setEnabled(!bool);
    mComboDay.setEnabled(!bool);
  }
  
  public void updateSettings()
  {
    JobGetPpdCollectionType localJobGetPpdCollectionType = new JobGetPpdCollectionType();
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetPpdCollectionType);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    if (localJobGetPpdCollectionType.getResult() != 1) {
      throw new FatalException("PpdCollectionType.init");
    }
    if (localJobGetPpdCollectionType.isPeriodType() == true) {
      mBtnSetPeriod.setSelected(true);
    } else {
      mBtnSetMonth.setSelected(true);
    }
    mCtrlDate = localJobGetPpdCollectionType.getDate();
    mStartDate = ((Calendar)mCtrlDate.clone());
    mStartDate.set(mCtrlDate.get(1) - 1, mCtrlDate.get(2), 1, 0, 0, 0);
    mEndDate = ((Calendar)mCtrlDate.clone());
    mEndDate.add(5, -1);
    mEndDate.set(11, 0);
    mEndDate.set(12, 0);
    mEndDate.set(13, 0);
    mTextStartPeriod.setText(StrRes.getYMDStr2(mStartDate, true));
    mTextEndPeriod.setText(StrRes.getYMDStr2(mEndDate, true));
    mComboDay.setSelectedIndex(localJobGetPpdCollectionType.getCalcDate() - 1);
    mContractPwr = localJobGetPpdCollectionType.getContractPwr();
    updateButtons();
  }
  
  static abstract interface ActionButtonsListener
  {
    public abstract void notifyExclusionPeriodSelected();
    
    public abstract void notifyExecSelected();
    
    public abstract void notifyNightRateSelected();
    
    public abstract void notifySpecialDaySelected();
  }
}
