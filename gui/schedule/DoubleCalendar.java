package webitc.gui.schedule;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.data.CommonCalendar;
import webitc.data.DayType;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.calendar.LblYearMonth;
import webitc.gui.common.calendar.MonthCalendar;
import webitc.gui.common.calendar.MonthCalendarListener;
import webitc.gui.common.calendar.TableModelCalendar;

class DoubleCalendar
  extends PanelAbstract
  implements MonthCalendarListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JButton mBtnNext = new JButton();
  JButton mBtnPrev = new JButton();
  MonthCalendar mCalendar1 = new MonthCalendar(false);
  MonthCalendar mCalendar2 = new MonthCalendar(false);
  private CommonCalendar mCommonCalendar = null;
  private Calendar mCurrentMonth;
  LblYearMonth mLblNextMonth = new LblYearMonth();
  LblYearMonth mLblPrevMonth = new LblYearMonth();
  private ArrayList mListenerList = new ArrayList();
  private TableModelCalendar mModel1;
  private TableModelCalendar mModel2;
  JPanel mPnlLeft = new JPanel();
  JPanel mPnlRight = new JPanel();
  private Calendar mSelectDate;
  
  public DoubleCalendar()
  {
    mCalendar1.addListener(this);
    mCalendar2.addListener(this);
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void addListener(CalendarListener paramCalendarListener)
  {
    mListenerList.add(paramCalendarListener);
  }
  
  public void dateSelected(MonthCalendar paramMonthCalendar)
  {
    DayType localDayType = paramMonthCalendar.getSelectedDate();
    if (localDayType == null) {
      return;
    }
    if (localDayType == DayType.DISABLE_DAY) {
      return;
    }
    int i;
    int j;
    if (paramMonthCalendar.equals(mCalendar1) == true)
    {
      i = mCurrentMonth.get(1);
      j = mCurrentMonth.get(2);
    }
    else if (paramMonthCalendar.equals(mCalendar2) == true)
    {
      Calendar localCalendar = (Calendar)mCurrentMonth.clone();
      localCalendar.add(2, 1);
      i = localCalendar.get(1);
      j = localCalendar.get(2);
    }
    else
    {
      throw new FatalException("DoubleCalendar.dateSelected");
    }
    setSelectedDate(i, j, fDay);
    updateDate();
  }
  
  public void errorPerformed()
  {
    mCalendar1.setEnabled(false);
    mCalendar2.setEnabled(false);
    mBtnPrev.setEnabled(false);
    mBtnNext.setEnabled(false);
    mLblPrevMonth.setEnabled(false);
    mLblNextMonth.setEnabled(false);
  }
  
  public Calendar getSelectedDate()
  {
    return mSelectDate;
  }
  
  private void jbInit()
    throws Exception
  {
    mBtnPrev.setText(StrRes.getStr("IDS_COMMON_LEFT"));
    mBtnPrev.addActionListener(new DoubleCalendar_mBtnPrev_actionAdapter(this));
    setLayout(gridBagLayout1);
    mPnlLeft.setLayout(gridBagLayout2);
    mPnlRight.setLayout(gridBagLayout3);
    mBtnNext.setText(StrRes.getStr("IDS_COMMON_RIGHT"));
    mBtnNext.addActionListener(new DoubleCalendar_mBtnNext_actionAdapter(this));
    mLblNextMonth.setHorizontalAlignment(4);
    mPnlLeft.add(mBtnPrev, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlLeft.add(mLblPrevMonth, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 10, 0, 0), 0, 0));
    mPnlLeft.add(mCalendar1.getScrollPane(), new GridBagConstraints(0, 1, 2, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRight.add(mBtnNext, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlRight.add(mLblNextMonth, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 13, 2, new Insets(0, 0, 0, 10), 0, 0));
    mPnlRight.add(mCalendar2.getScrollPane(), new GridBagConstraints(0, 1, 2, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mPnlLeft, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 10), 0, 0));
    add(mPnlRight, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 10, 0, 0), 0, 0));
  }
  
  void mBtnNext_actionPerformed()
  {
    mCurrentMonth.add(2, 1);
    updateCalendar();
    updateDate();
  }
  
  void mBtnPrev_actionPerformed()
  {
    mCurrentMonth.add(2, -1);
    updateCalendar();
    updateDate();
  }
  
  public void setCalendar(CommonCalendar paramCommonCalendar, Calendar paramCalendar)
  {
    mCommonCalendar = paramCommonCalendar;
    mCurrentMonth = mCommonCalendar.getStartMonth();
    setSelectedDate(paramCalendar.get(1), paramCalendar.get(2), paramCalendar.get(5));
    updateCalendar();
    updateDate();
  }
  
  public void setDayType(int paramInt, Calendar paramCalendar)
  {
    Calendar localCalendar = (Calendar)mCurrentMonth.clone();
    localCalendar.add(2, 1);
    TableModelCalendar localTableModelCalendar;
    if ((mCurrentMonth.get(1) == mSelectDate.get(1)) && (mCurrentMonth.get(2) == mSelectDate.get(2)))
    {
      localTableModelCalendar = (TableModelCalendar)mCalendar1.getModel();
      localTableModelCalendar.setDayType(paramCalendar.get(5), paramInt);
    }
    else if ((localCalendar.get(1) == mSelectDate.get(1)) && (localCalendar.get(2) == mSelectDate.get(2)))
    {
      localTableModelCalendar = (TableModelCalendar)mCalendar2.getModel();
      localTableModelCalendar.setDayType(paramCalendar.get(5), paramInt);
    }
    else
    {
      throw new FatalException("DoubleCalendar.setDayType");
    }
  }
  
  private void setSelectedDate(int paramInt1, int paramInt2, int paramInt3)
  {
    mSelectDate = new GregorianCalendar(paramInt1, paramInt2, paramInt3);
    for (int i = 0; i < mListenerList.size(); i++)
    {
      CalendarListener localCalendarListener = (CalendarListener)mListenerList.get(i);
      localCalendarListener.dateSelected((Calendar)mSelectDate.clone());
    }
  }
  
  private void updateCalendar()
  {
    ArrayList localArrayList = mCommonCalendar.getDayTypeList(mCurrentMonth);
    mModel1 = new TableModelCalendar(mCurrentMonth, localArrayList);
    mCalendar1.setCalendarModel(mModel1);
    mLblPrevMonth.setCalendar(mCurrentMonth);
    Calendar localCalendar1 = (Calendar)mCurrentMonth.clone();
    localCalendar1.add(2, 1);
    localArrayList = mCommonCalendar.getDayTypeList(localCalendar1);
    mModel2 = new TableModelCalendar(localCalendar1, localArrayList);
    mCalendar2.setCalendarModel(mModel2);
    mLblNextMonth.setCalendar(localCalendar1);
    Calendar localCalendar2 = mCommonCalendar.getStartMonth();
    Calendar localCalendar3 = mCommonCalendar.getEndMonth();
    if ((mCurrentMonth.get(1) == localCalendar2.get(1)) && (mCurrentMonth.get(2) == localCalendar2.get(2))) {
      mBtnPrev.setEnabled(false);
    } else {
      mBtnPrev.setEnabled(true);
    }
    if ((localCalendar1.get(1) == localCalendar3.get(1)) && (localCalendar1.get(2) == localCalendar3.get(2))) {
      mBtnNext.setEnabled(false);
    } else {
      mBtnNext.setEnabled(true);
    }
  }
  
  private void updateDate() {}
}
