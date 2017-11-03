package webitc.gui.common.calendar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.data.CommonCalendar;
import webitc.data.DayType;
import webitc.gui.common.PanelAbstract;

public class SingleCalendar
  extends PanelAbstract
  implements MonthCalendarListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton mBtnNext = new JButton();
  JButton mBtnPrev = new JButton();
  MonthCalendar mCalendar;
  private CommonCalendar mCommonCalendar;
  private Calendar mCurrentMonth;
  LblYearMonth mLblYearMonth = new LblYearMonth();
  private SingleCalendarListener mListener = null;
  private TableModelCalendar mModelCalendar;
  private SingleCalendarExtendOption mOption = null;
  JPanel mPnlHeader = new JPanel();
  
  public SingleCalendar(SingleCalendarExtendOption paramSingleCalendarExtendOption, boolean paramBoolean)
  {
    try
    {
      if (paramSingleCalendarExtendOption != null)
      {
        mCalendar = new MonthCalendar(mEditable, paramBoolean);
        if ((mStartDate != null) && (mEndDate != null))
        {
          CommonCalendar localCommonCalendar = new CommonCalendar();
          localCommonCalendar.setStartMonth(mStartDate.get(1), mStartDate.get(2) + 1);
          mOption = paramSingleCalendarExtendOption;
          jbInit();
          setCalendar(localCommonCalendar);
        }
      }
      else
      {
        mCalendar = new MonthCalendar(false);
        jbInit();
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public SingleCalendar()
  {
    try
    {
      mCalendar = new MonthCalendar(false);
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void calInit()
  {
    mCurrentMonth = mCommonCalendar.getStartMonth();
    update();
  }
  
  public void clearSelection()
  {
    mCalendar.clearSelection();
    mCommonCalendar.clear();
    update();
  }
  
  public void dateSelected(MonthCalendar paramMonthCalendar)
  {
    if ((paramMonthCalendar == null) || (paramMonthCalendar.getSelectedDate() == null)) {
      return;
    }
    int i = getSelectedDatefDay;
    if (mModelCalendar.getDayType(i) == 12) {
      return;
    }
    mCalendar.setSelectedDate(getSelectedDatefDay);
    if (mListener != null) {
      mListener.notifyDateSelected(paramMonthCalendar);
    }
  }
  
  public void errorPerformed()
  {
    mCalendar.setEnabled(false);
    mBtnPrev.setEnabled(false);
    mBtnNext.setEnabled(false);
    mLblYearMonth.setEnabled(false);
  }
  
  public int getCurrentMonth()
  {
    return mCurrentMonth.get(2) + 1;
  }
  
  public int getCurrentYear()
  {
    return mCurrentMonth.get(1);
  }
  
  private void jbInit()
    throws Exception
  {
    mBtnPrev.setText(StrRes.getStr("IDS_COMMON_LEFT"));
    mBtnPrev.addActionListener(new SingleCalendar_mBtnPrev_actionAdapter(this));
    setLayout(gridBagLayout1);
    mBtnNext.setText(StrRes.getStr("IDS_COMMON_RIGHT"));
    mBtnNext.addActionListener(new SingleCalendar_mBtnNext_actionAdapter(this));
    mLblYearMonth.setHorizontalAlignment(0);
    mLblYearMonth.setHorizontalTextPosition(0);
    mPnlHeader.setEnabled(true);
    mPnlHeader.setLayout(gridBagLayout2);
    add(mPnlHeader, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlHeader.add(mBtnPrev, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlHeader.add(mLblYearMonth, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    mPnlHeader.add(mBtnNext, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mCalendar.getScrollPane(), new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  void mBtnNext_actionPerformed(ActionEvent paramActionEvent)
  {
    mCurrentMonth.add(2, 1);
    update();
    if (mListener != null) {
      mListener.notifyNextMonth();
    }
  }
  
  void mBtnPrev_actionPerformed(ActionEvent paramActionEvent)
  {
    mCurrentMonth.add(2, -1);
    update();
    if (mListener != null) {
      mListener.notifyPrevMonth();
    }
  }
  
  public void setCalendar(CommonCalendar paramCommonCalendar)
  {
    mCommonCalendar = paramCommonCalendar;
    calInit();
  }
  
  public void setDayType(DayType paramDayType)
  {
    TableModelCalendar localTableModelCalendar = (TableModelCalendar)mCalendar.getModel();
    localTableModelCalendar.setDayType(fDay, fType);
  }
  
  public void setDayType(int paramInt, Calendar paramCalendar)
  {
    if ((mCurrentMonth.get(1) == paramCalendar.get(1)) && (mCurrentMonth.get(2) == paramCalendar.get(2)))
    {
      TableModelCalendar localTableModelCalendar = (TableModelCalendar)mCalendar.getModel();
      localTableModelCalendar.setDayType(paramCalendar.get(5), paramInt);
    }
    else
    {
      throw new FatalException("SingleCalendar.setDayType");
    }
  }
  
  public void setListener(SingleCalendarListener paramSingleCalendarListener)
  {
    if (mListener == null) {
      mCalendar.addListener(this);
    }
    mListener = paramSingleCalendarListener;
  }
  
  public void setMonth(int paramInt1, int paramInt2)
  {
    Calendar localCalendar1 = mCommonCalendar.getStartMonth();
    if (localCalendar1.get(1) > paramInt1) {
      return;
    }
    if ((localCalendar1.get(1) == paramInt1) && (localCalendar1.get(2) >= paramInt2)) {
      return;
    }
    Calendar localCalendar2;
    int i;
    if (mOption != null)
    {
      localCalendar2 = mOption.mEndDate;
      i = localCalendar2.get(2) + 1;
    }
    else
    {
      localCalendar2 = mCommonCalendar.getEndMonth();
      i = localCalendar2.get(2);
    }
    if (localCalendar2.get(1) < paramInt1) {
      return;
    }
    if ((localCalendar2.get(1) == paramInt1) && (i < paramInt2)) {
      return;
    }
    int j = paramInt1 - mCurrentMonth.get(1);
    int k = paramInt2 - mCurrentMonth.get(2);
    mCurrentMonth.add(2, j * 12 + k - 1);
    update();
  }
  
  private void update()
  {
    mLblYearMonth.setCalendar(mCurrentMonth);
    ArrayList localArrayList = mCommonCalendar.getDayTypeList(mCurrentMonth);
    if (mOption == null) {
      mModelCalendar = new TableModelCalendar(mCurrentMonth, localArrayList, 0);
    } else {
      mModelCalendar = new TableModelCalendar(mCurrentMonth, localArrayList, mOption.mHeaderCheckBitmap);
    }
    Calendar localCalendar1 = mCommonCalendar.getStartMonth();
    Calendar localCalendar2;
    if (mOption != null)
    {
      localCalendar2 = mOption.mEndDate;
      int i;
      if ((mCurrentMonth.get(1) == mOption.mStartDate.get(1)) && (mCurrentMonth.get(2) == mOption.mStartDate.get(2))) {
        for (i = 1; i < mOption.mStartDate.get(5); i++) {
          mModelCalendar.setDayType(i, 12);
        }
      }
      if ((mCurrentMonth.get(1) == localCalendar2.get(1)) && (mCurrentMonth.get(2) == localCalendar2.get(2))) {
        for (i = localCalendar2.get(5) + 1; i <= 31; i++) {
          mModelCalendar.setDayType(i, 12);
        }
      }
    }
    else
    {
      localCalendar2 = mCommonCalendar.getEndMonth();
    }
    mCalendar.setCalendarModel(mModelCalendar);
    if ((mCurrentMonth.get(1) == localCalendar1.get(1)) && (mCurrentMonth.get(2) == localCalendar1.get(2))) {
      mBtnPrev.setEnabled(false);
    } else {
      mBtnPrev.setEnabled(true);
    }
    if ((mCurrentMonth.get(1) == localCalendar2.get(1)) && (mCurrentMonth.get(2) == localCalendar2.get(2))) {
      mBtnNext.setEnabled(false);
    } else {
      mBtnNext.setEnabled(true);
    }
  }
  
  public static abstract interface SingleCalendarListener
  {
    public abstract void notifyDateSelected(MonthCalendar paramMonthCalendar);
    
    public abstract void notifyNextMonth();
    
    public abstract void notifyPrevMonth();
  }
}
