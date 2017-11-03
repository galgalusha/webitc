package webitc.gui.common;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.swing.JTextField;
import webitc.common.StrRes;
import webitc.data.DayType;
import webitc.gui.common.calendar.MonthCalendar;
import webitc.gui.common.calendar.SingleCalendar;
import webitc.gui.common.calendar.SingleCalendar.SingleCalendarListener;
import webitc.gui.common.calendar.SingleCalendarExtendOption;

public class DlgEditDate
  extends DlgAbstract
  implements SingleCalendar.SingleCalendarListener, PanelOkCancel.PanelOkCancelListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  PanelOkCancel mButtons = new PanelOkCancel(this);
  SingleCalendar mCalendar;
  JTextField mDay = new JTextField();
  Calendar mSelectedDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
  private String mTitle;
  
  public DlgEditDate(String paramString, SingleCalendarExtendOption paramSingleCalendarExtendOption)
  {
    try
    {
      mTitle = paramString;
      jbInit(paramSingleCalendarExtendOption);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mButtons.setEnabled(1, false);
    mCalendar.errorPerformed();
  }
  
  public Calendar getSelectedDate()
  {
    return mSelectedDate;
  }
  
  private void jbInit(SingleCalendarExtendOption paramSingleCalendarExtendOption)
    throws Exception
  {
    setTitle(mTitle);
    getContentPane().setLayout(gridBagLayout1);
    mDay.setMinimumSize(new Dimension(200, 20));
    mDay.setPreferredSize(new Dimension(200, 20));
    mDay.setEditable(false);
    mDay.setText("");
    mDay.setHorizontalAlignment(4);
    mCalendar = new SingleCalendar(paramSingleCalendarExtendOption, false);
    mCalendar.setListener(this);
    getContentPane().add(mCalendar, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 10, 0, 10), 0, 0));
    getContentPane().add(mDay, new GridBagConstraints(0, 1, 2, 1, 1.0D, 0.0D, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mButtons, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 10, 10, 10), 0, 0));
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyDateSelected(MonthCalendar paramMonthCalendar)
  {
    if ((paramMonthCalendar == null) || (paramMonthCalendar.getSelectedDate() == null)) {
      return;
    }
    updateCal(mCalendar.getCurrentYear(), mCalendar.getCurrentMonth(), getSelectedDatefDay);
  }
  
  public void notifyNextMonth()
  {
    if ((mSelectedDate.get(1) == mCalendar.getCurrentYear()) && (mSelectedDate.get(2) + 1 == mCalendar.getCurrentMonth())) {
      updateCal(mSelectedDate.get(1), mSelectedDate.get(2) + 1, mSelectedDate.get(5));
    }
  }
  
  public void notifyOkSelected()
  {
    closeDialog(true);
  }
  
  public void notifyPrevMonth()
  {
    if ((mSelectedDate.get(1) == mCalendar.getCurrentYear()) && (mSelectedDate.get(2) + 1 == mCalendar.getCurrentMonth())) {
      updateCal(mSelectedDate.get(1), mSelectedDate.get(2) + 1, mSelectedDate.get(5));
    }
  }
  
  public void setDefaultDate(int paramInt1, int paramInt2, int paramInt3)
  {
    mCalendar.setMonth(paramInt1, paramInt2);
    updateCal(paramInt1, paramInt2, paramInt3);
  }
  
  private void updateCal(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 != mCalendar.getCurrentYear()) || (paramInt2 != mCalendar.getCurrentMonth())) {
      return;
    }
    mCalendar.clearSelection();
    DayType localDayType = new DayType(paramInt3, 11);
    mCalendar.setDayType(localDayType);
    mSelectedDate.set(paramInt1, paramInt2 - 1, paramInt3);
    mDay.setText(StrRes.getYMDStr2(mSelectedDate, true));
  }
}
