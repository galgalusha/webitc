package webitc.gui.schedule;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import webitc.common.StrRes;
import webitc.data.schedule.Program;
import webitc.gui.common.BorderRes;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.DlgSetName;
import webitc.gui.common.calendar.SingleCalendar;

class DlgSchMain
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JButton mBtnCalendar = new JButton();
  JButton mBtnCancel = new JButton();
  JButton mBtnChangeName = new JButton();
  JButton mBtnOK = new JButton();
  SingleCalendar mCalendar = new SingleCalendar();
  CalendarNotes mCalendarNotes = new CalendarNotes();
  JPanel mPnlCalendar = new JPanel();
  JPanel mPnlName = new JPanel();
  JPanel mPnlOKCancel = new JPanel();
  private Program mProgram;
  PanelEventList mSetting = new PanelEventList();
  private Calendar mToday;
  JTextField mTxtSchName = new JTextField();
  
  public DlgSchMain(Program paramProgram, Calendar paramCalendar)
  {
    mProgram = paramProgram;
    mToday = paramCalendar;
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
    mCalendar.errorPerformed();
    mSetting.errorPerformed();
    mBtnCalendar.setEnabled(false);
    mTxtSchName.setEnabled(false);
    mBtnChangeName.setEnabled(false);
    mCalendarNotes.errorPerformed();
    mBtnOK.setEnabled(false);
  }
  
  public Program getProgram()
  {
    return mProgram;
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SCH_SET_SCHEDULE"));
    mSetting.addListener(mCalendarNotes);
    getContentPane().setLayout(gridBagLayout1);
    mPnlCalendar.setLayout(gridBagLayout2);
    mBtnCalendar.setText(StrRes.getStr("IDS_SCH_CHANGE_CALENDAR"));
    mBtnCalendar.addActionListener(new DlgSchMain_mBtnCalendar_actionAdapter(this));
    mPnlName.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCH_SCHEDULE_NAME")));
    mPnlName.setLayout(gridBagLayout3);
    mTxtSchName.setPreferredSize(new Dimension(190, 20));
    mTxtSchName.setEditable(false);
    mTxtSchName.setText("");
    mBtnChangeName.setText(StrRes.getStr("IDS_COMMON_CHANGE_NAME"));
    mBtnChangeName.addActionListener(new DlgSchMain_mBtnChangeName_actionAdapter(this));
    mPnlCalendar.add(mCalendar, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 11, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPnlCalendar.add(mCalendarNotes, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 18, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlCalendar.add(mBtnCalendar, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 0, 0, 0), 0, 0));
    getContentPane().add(mPnlName, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    getContentPane().add(mPnlOKCancel, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 14, 0, new Insets(0, 0, 0, 5), 0, 0));
    mPnlOKCancel.add(mBtnOK, null);
    mPnlOKCancel.add(mBtnCancel, null);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSchMain_mBtnOK_actionAdapter(this));
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSchMain_mBtnCancel_actionAdapter(this));
    mPnlCalendar.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCH_CALENDAR")));
    getContentPane().add(mSetting, new GridBagConstraints(0, 0, 1, 3, 0.0D, 0.0D, 18, 1, new Insets(0, 0, 0, 0), 0, 0));
    getContentPane().add(mPnlCalendar, new GridBagConstraints(1, 1, 1, 1, 1.0D, 0.5D, 11, 1, new Insets(0, 0, 0, 0), 0, 0));
    mPnlName.add(mTxtSchName, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlName.add(mBtnChangeName, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 5, 0, 0), 0, 0));
  }
  
  void mBtnCalendar_actionPerformed(ActionEvent paramActionEvent)
  {
    DlgSchCalendar localDlgSchCalendar = new DlgSchCalendar(mProgram.getCalendar(), mToday, mProgram.getException());
    localDlgSchCalendar.doModal();
    if (localDlgSchCalendar.isOK() == true)
    {
      mProgram.setCalendar(localDlgSchCalendar.getCalendar());
      mCalendar.setCalendar(mProgram.getCalendar());
    }
  }
  
  void mBtnCancel_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(false);
  }
  
  void mBtnChangeName_actionPerformed(ActionEvent paramActionEvent)
  {
    DlgSetName localDlgSetName = new DlgSetName(mProgram.getName(), 16);
    localDlgSetName.doModal();
    if (localDlgSetName.isOK() == true)
    {
      mProgram.setName(localDlgSetName.getName());
      mTxtSchName.setText(mProgram.getName());
    }
  }
  
  void mBtnOK_actionPerformed(ActionEvent paramActionEvent)
  {
    closeDialog(true);
  }
  
  private void partsInit()
  {
    mTxtSchName.setText(mProgram.getName());
    mSetting.setProgram(mProgram);
    mCalendar.setCalendar(mProgram.getCalendar());
    mCalendarNotes.setException(mProgram.getException());
  }
}
