package webitc.gui.ppd;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumModelType;
import webitc.data.DayType;
import webitc.gui.common.BorderRes;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.calendar.MonthCalendar;
import webitc.gui.common.calendar.SingleCalendar;
import webitc.gui.common.calendar.SingleCalendar.SingleCalendarListener;
import webitc.gui.common.calendar.SingleCalendarExtendOption;

public class DlgSetSpecialDay
  extends DlgAbstract
  implements SingleCalendar.SingleCalendarListener, PanelOkCancel.PanelOkCancelListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  PanelOkCancel mButtons = new PanelOkCancel(this);
  SingleCalendar mCalendar;
  JLabel mExclusionWDayLabel = new JLabel();
  JPanel mPanelSample = new JPanel();
  PpdSchedule mSchedule = null;
  JLabel mSpecialDayLabel = new JLabel();
  JPanel mSpecialDaySample = new JPanel();
  
  public DlgSetSpecialDay(SingleCalendarExtendOption paramSingleCalendarExtendOption, PpdSchedule paramPpdSchedule)
  {
    try
    {
      mSchedule = paramPpdSchedule;
      jbInit(paramSingleCalendarExtendOption);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mCalendar.errorPerformed();
    mButtons.setEnabled(1, false);
  }
  
  public PpdSchedule getSchedule()
  {
    return mSchedule;
  }
  
  private void jbInit(SingleCalendarExtendOption paramSingleCalendarExtendOption)
    throws Exception
  {
    getContentPane().setLayout(gridBagLayout1);
    if (SystemInfo.getModelType() == EnumModelType.ITC) {
      setTitle(StrRes.getStr("IDS_PPDSPECIALDAY_TITLE"));
    } else if ((SystemInfo.getModelType() == EnumModelType.ACS) || (SystemInfo.getModelType() == EnumModelType.EXC)) {
      setTitle(StrRes.getStr("IDS_PPDSPECIALDAY_TITLE_2"));
    } else {
      setTitle(StrRes.getStr("IDS_PPDSPECIALDAY_TITLE_GHP"));
    }
    mHeaderCheckBitmap = mSchedule.getExclusionWDayBitmap();
    mCalendar = new SingleCalendar(paramSingleCalendarExtendOption, true);
    mCalendar.setListener(this);
    mPanelSample.setLayout(gridBagLayout2);
    mSpecialDaySample.setBackground(mCalendar.getBackground());
    mSpecialDaySample.setBorder(BorderRes.getBorder(3));
    mSpecialDaySample.setPreferredSize(new Dimension(20, 20));
    mSpecialDayLabel.setText(StrRes.getStr("IDS_PPDSPECIALDAY_SPECIAL_DAY"));
    mExclusionWDayLabel.setText(StrRes.getStr("IDS_PPDSPECIALDAY_EXCLUDE_DAYS"));
    mPanelSample.add(mExclusionWDayLabel, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 10, 0, 10), 0, 0));
    mPanelSample.add(mSpecialDaySample, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 10, 0, 5), 0, 0));
    mPanelSample.add(mSpecialDayLabel, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(0, 0, 0, 10), 0, 0));
    getContentPane().add(mCalendar, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mPanelSample, new GridBagConstraints(0, 1, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 10, 0, 10), 0, 0));
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
    int i = mCalendar.getCurrentMonth();
    int j = getSelectedDatefDay;
    if (mSchedule.isSpecialDay(i, j) == true) {
      mSchedule.setSpecialDay(i, j, false);
    } else {
      mSchedule.setSpecialDay(i, j, true);
    }
    updateCal();
  }
  
  public void notifyNextMonth()
  {
    updateCal();
  }
  
  public void notifyOkSelected()
  {
    closeDialog(true);
  }
  
  public void notifyPrevMonth()
  {
    updateCal();
  }
  
  public void setDefaultDate(int paramInt1, int paramInt2)
  {
    mCalendar.setMonth(paramInt1, paramInt2);
    updateCal();
  }
  
  private void updateCal()
  {
    mCalendar.clearSelection();
    for (int i = 1; i <= 31; i++)
    {
      DayType localDayType = null;
      if (mSchedule.isSpecialDay(mCalendar.getCurrentMonth(), i)) {
        localDayType = new DayType(i, 4);
      } else {
        localDayType = new DayType(i, 0);
      }
      mCalendar.setDayType(localDayType);
    }
  }
}
