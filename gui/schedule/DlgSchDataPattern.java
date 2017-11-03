package webitc.gui.schedule;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.schedule.AbstSchedule;
import webitc.data.schedule.Daily;
import webitc.data.schedule.Program;
import webitc.data.schedule.SchEvent;
import webitc.data.schedule.SchException;
import webitc.gui.common.DlgAbstract;

public class DlgSchDataPattern
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  GridBagLayout gridBagLayout8 = new GridBagLayout();
  JButton mBtnAdd = new JButton();
  JButton mBtnCancel = new JButton();
  JButton mBtnDel = new JButton();
  JButton mBtnOK = new JButton();
  JButton mBtnOverwriteAll = new JButton();
  JComboBox mCmbPatFrom = new JComboBox();
  JComboBox mCmbPatTo = new JComboBox();
  SchException mException;
  JLabel mLblCopyFrom = new JLabel();
  JLabel mLblCopyTo = new JLabel();
  JPanel mPnlListFrom = new JPanel();
  JPanel mPnlListTo = new JPanel();
  JPanel mPnlOKCancel = new JPanel();
  JPanel mPnlOpeFrom = new JPanel();
  JPanel mPnlOpeTo = new JPanel();
  JPanel mPnlPatFrom = new JPanel();
  JPanel mPnlPatTo = new JPanel();
  Program mProgram;
  SchEventTable mTblFrom = new SchEventTable(EnumTableID.DLG_SCH_DATA_PATTERN_FROM);
  SchEventTable mTblTo = new SchEventTable(EnumTableID.DLG_SCH_DATA_PATTERN_TO);
  AbstSchedule mWeekly;
  
  public DlgSchDataPattern(Program paramProgram)
  {
    mProgram = paramProgram;
    mWeekly = ((AbstSchedule)mProgram.getWeekly().clone());
    mException = ((SchException)mProgram.getException().clone());
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
  
  public void errorPerformed()
  {
    mCmbPatFrom.setEnabled(false);
    mCmbPatTo.setEnabled(false);
    mTblTo.setEnabled(false);
    mTblFrom.setEnabled(false);
    mBtnOverwriteAll.setEnabled(false);
    mBtnAdd.setEnabled(false);
    mBtnDel.setEnabled(false);
    mBtnOK.setEnabled(false);
  }
  
  private Daily getSelectedDailyFrom()
  {
    int i = mCmbPatFrom.getSelectedIndex();
    Daily localDaily;
    if (i < 7) {
      localDaily = mWeekly.getDaily(i);
    } else {
      localDaily = mException.getDaily(i - 7);
    }
    return localDaily;
  }
  
  private Daily getSelectedDailyTo()
  {
    int i = mCmbPatTo.getSelectedIndex();
    Daily localDaily;
    if (i < 7) {
      localDaily = mWeekly.getDaily(i);
    } else {
      localDaily = mException.getDaily(i - 7);
    }
    return localDaily;
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SCH_DATA_OPE"));
    getContentPane().setLayout(gridBagLayout1);
    mPnlPatFrom.setLayout(gridBagLayout2);
    mPnlPatTo.setLayout(gridBagLayout3);
    mPnlListFrom.setLayout(gridBagLayout4);
    mPnlListTo.setLayout(gridBagLayout5);
    mPnlOpeFrom.setLayout(gridBagLayout6);
    mPnlOpeTo.setLayout(gridBagLayout7);
    mPnlOKCancel.setLayout(gridBagLayout8);
    mLblCopyFrom.setText(StrRes.getStr("IDS_SCHDEVENTDATAOPE_COPY_FROM"));
    mLblCopyTo.setText(StrRes.getStr("IDS_SCHDEVENTDATAOPE_COPY_TO"));
    mBtnOverwriteAll.setText(StrRes.getStr("IDS_SCHDEVENTDATAOPE_OVERWRITE_ALL"));
    mBtnOverwriteAll.addActionListener(new DlgSchDataPattern_mBtnOverwriteAll_actionAdapter(this));
    mBtnAdd.setText(StrRes.getStr("IDS_SCHDEVENTDATAOPE_COPY"));
    mBtnAdd.addActionListener(new DlgSchDataPattern_mBtnAdd_actionAdapter(this));
    mBtnDel.setText(StrRes.getStr("IDS_SCHDEVENTDATAOPE_DELETE"));
    mBtnDel.addActionListener(new DlgSchDataPattern_mBtnDel_actionAdapter(this));
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSchDataPattern_mBtnOK_actionAdapter(this));
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSchDataPattern_mBtnCancel_actionAdapter(this));
    mCmbPatFrom.addActionListener(new DlgSchDataPattern_mCmbPatFrom_actionAdapter(this));
    mCmbPatTo.addActionListener(new DlgSchDataPattern_mCmbPatTo_actionAdapter(this));
    getContentPane().add(mPnlPatFrom, new GridBagConstraints(0, 0, 1, 1, 0.5D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlPatFrom.add(mLblCopyFrom, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlPatFrom.add(mCmbPatFrom, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mPnlPatTo, new GridBagConstraints(1, 0, 1, 1, 0.5D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlPatTo.add(mLblCopyTo, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlPatTo.add(mCmbPatTo, new GridBagConstraints(1, 0, 1, 0, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mPnlListFrom, new GridBagConstraints(0, 1, 1, 1, 0.5D, 1.0D, 10, 1, new Insets(2, 4, 2, 4), 0, 0));
    mPnlListFrom.add(mTblFrom.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPnlListTo.add(mTblTo.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
    getContentPane().add(mPnlListTo, new GridBagConstraints(1, 1, 1, 1, 0.5D, 1.0D, 10, 1, new Insets(2, 4, 2, 4), 0, 0));
    getContentPane().add(mPnlOpeFrom, new GridBagConstraints(0, 2, 1, 1, 0.5D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOpeFrom.add(mBtnOverwriteAll, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(2, 5, 2, 5), 0, 0));
    mPnlOpeFrom.add(mBtnAdd, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(2, 5, 2, 5), 0, 0));
    getContentPane().add(mPnlOpeTo, new GridBagConstraints(1, 2, 1, 1, 0.5D, 0.0D, 18, 3, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOpeTo.add(mBtnDel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 1.0D, 18, 0, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mPnlOKCancel, new GridBagConstraints(1, 3, 1, 0, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOKCancel.add(mBtnOK, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 14, 0, new Insets(5, 5, 6, 5), 0, 0));
    mPnlOKCancel.add(mBtnCancel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 6, 12), 0, 0));
  }
  
  void mBtnAdd_actionPerformed()
  {
    SchEvent localSchEvent = (SchEvent)mTblFrom.getSelectedEvent().clone();
    int i = mCmbPatTo.getSelectedIndex();
    Daily localDaily;
    if (i < 7) {
      localDaily = mWeekly.getDaily(i);
    } else {
      localDaily = mException.getDaily(i - 7);
    }
    localDaily.addEvent(localSchEvent);
    if (i < 7) {
      mWeekly.setDaily(i, localDaily);
    } else {
      mException.setDaily(i - 7, localDaily);
    }
    mTblTo.addEvent(localSchEvent, true);
    updateButtons();
  }
  
  void mBtnCancel_actionPerformed()
  {
    closeDialog(false);
  }
  
  void mBtnDel_actionPerformed()
  {
    mTblTo.deleteEvent();
    Daily localDaily = mTblTo.createDaily();
    int i = mCmbPatTo.getSelectedIndex();
    if (i < 7) {
      mWeekly.setDaily(i, localDaily);
    } else {
      mException.setDaily(i - 7, localDaily);
    }
    if (mCmbPatFrom.getSelectedIndex() == mCmbPatTo.getSelectedIndex())
    {
      int j = mTblFrom.getSelectedRow();
      int k = mTblTo.getSelectedRow();
      mTblFrom.setDaily(localDaily);
      if (j > k) {
        j--;
      }
      if (j >= 0) {
        mTblFrom.setRowSelectionInterval(j, j);
      }
    }
    updateButtons();
  }
  
  void mBtnOK_actionPerformed()
  {
    mProgram.setWeekly(mWeekly);
    mProgram.setException(mException);
    closeDialog(true);
  }
  
  void mBtnOverwriteAll_actionPerformed()
  {
    Daily localDaily = (Daily)getSelectedDailyFrom().clone();
    int i = mCmbPatTo.getSelectedIndex();
    if (i < 7) {
      mWeekly.setDaily(i, localDaily);
    } else {
      mException.setDaily(i - 7, localDaily);
    }
    mTblTo.setDaily(localDaily);
    updateButtons();
  }
  
  void mCmbPatFrom_actionPerformed()
  {
    Daily localDaily = getSelectedDailyFrom();
    mTblFrom.setDaily(localDaily);
    updateButtons();
  }
  
  void mCmbPatTo_actionPerformed()
  {
    Daily localDaily = getSelectedDailyTo();
    mTblTo.setDaily(localDaily);
    updateButtons();
  }
  
  private void partsInit()
  {
    mCmbPatFrom.addItem(StrRes.getStr("IDS_COMMON_WDAY_0"));
    mCmbPatFrom.addItem(StrRes.getStr("IDS_COMMON_WDAY_1"));
    mCmbPatFrom.addItem(StrRes.getStr("IDS_COMMON_WDAY_2"));
    mCmbPatFrom.addItem(StrRes.getStr("IDS_COMMON_WDAY_3"));
    mCmbPatFrom.addItem(StrRes.getStr("IDS_COMMON_WDAY_4"));
    mCmbPatFrom.addItem(StrRes.getStr("IDS_COMMON_WDAY_5"));
    mCmbPatFrom.addItem(StrRes.getStr("IDS_COMMON_WDAY_6"));
    mCmbPatTo.addItem(StrRes.getStr("IDS_COMMON_WDAY_0"));
    mCmbPatTo.addItem(StrRes.getStr("IDS_COMMON_WDAY_1"));
    mCmbPatTo.addItem(StrRes.getStr("IDS_COMMON_WDAY_2"));
    mCmbPatTo.addItem(StrRes.getStr("IDS_COMMON_WDAY_3"));
    mCmbPatTo.addItem(StrRes.getStr("IDS_COMMON_WDAY_4"));
    mCmbPatTo.addItem(StrRes.getStr("IDS_COMMON_WDAY_5"));
    mCmbPatTo.addItem(StrRes.getStr("IDS_COMMON_WDAY_6"));
    for (int i = 0; i < mException.getDailyNum(); i++)
    {
      mCmbPatFrom.addItem(mException.getExceptionName(i));
      mCmbPatTo.addItem(mException.getExceptionName(i));
    }
    mCmbPatFrom.setSelectedIndex(0);
    mCmbPatTo.setSelectedIndex(0);
  }
  
  private void updateButtons()
  {
    if ((mTblFrom.getEventCount() == 0) || (mCmbPatFrom.getSelectedIndex() == mCmbPatTo.getSelectedIndex())) {
      mBtnOverwriteAll.setEnabled(false);
    } else {
      mBtnOverwriteAll.setEnabled(true);
    }
    if ((mTblFrom.getEventCount() == 0) || (mCmbPatFrom.getSelectedIndex() == mCmbPatTo.getSelectedIndex()) || (mTblTo.getEventCount() == 16)) {
      mBtnAdd.setEnabled(false);
    } else {
      mBtnAdd.setEnabled(true);
    }
    if (mTblTo.getEventCount() == 0) {
      mBtnDel.setEnabled(false);
    } else {
      mBtnDel.setEnabled(true);
    }
  }
}
