package webitc.gui.ppd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Calendar;
import webitc.common.FatalException;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.AuthListener;
import webitc.gui.common.CommonUse;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.calendar.SingleCalendarExtendOption;
import webitc.job.JobGetPpdSchedule;
import webitc.job.JobSetPpdSchedule;

public class PanelPpd
  extends PanelAbstract
  implements PanelActionButtons.ActionButtonsListener, AuthListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  private PanelActionButtons mActionButtons = new PanelActionButtons(this);
  Calendar mCtrlDate = null;
  private PanelPpdResult mResult = new PanelPpdResult();
  
  public PanelPpd()
  {
    try
    {
      PpdCollectionData.create();
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void authSucceeded(int paramInt) {}
  
  private PpdSchedule getPpdSchedule()
  {
    JobGetPpdSchedule localJobGetPpdSchedule = new JobGetPpdSchedule();
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetPpdSchedule);
    if (!CommonUse.waitJob(localResult)) {
      return null;
    }
    if (localJobGetPpdSchedule.getResult() != 1) {
      throw new FatalException("PanelPpd.getPpdSchedule");
    }
    mCtrlDate = localJobGetPpdSchedule.getDate();
    return localJobGetPpdSchedule.getPpdSchedule();
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    add(mResult, new GridBagConstraints(0, 0, 1, 0, 1.0D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    add(mActionButtons, new GridBagConstraints(1, 0, 1, 1, 0.0D, 1.0D, 10, 3, new Insets(5, 5, 5, 5), 0, 0));
  }
  
  public void logout()
  {
    PpdCollectionData.getInstance().clear();
  }
  
  public void notifyExclusionPeriodSelected()
  {
    PpdSchedule localPpdSchedule = getPpdSchedule();
    if (localPpdSchedule == null) {
      return;
    }
    DlgSetExclusionPeriod localDlgSetExclusionPeriod = new DlgSetExclusionPeriod(localPpdSchedule);
    localDlgSetExclusionPeriod.doModal();
    if (!localDlgSetExclusionPeriod.isOK()) {
      return;
    }
    localPpdSchedule = localDlgSetExclusionPeriod.getSchedule();
    if (setPpdSchedule(localPpdSchedule) == true) {}
  }
  
  public void notifyExecSelected()
  {
    Calendar localCalendar1 = mActionButtons.getStartTime();
    Calendar localCalendar2 = mActionButtons.getEndTime();
    boolean bool = mActionButtons.isPeriodSelected();
    int i = mActionButtons.getContractPwr(localCalendar1.get(2));
    mResult.update(localCalendar1, localCalendar2, bool, i);
  }
  
  public void notifyNightRateSelected()
  {
    PpdSchedule localPpdSchedule = getPpdSchedule();
    if (localPpdSchedule == null) {
      return;
    }
    DlgSetNightRatePeriod localDlgSetNightRatePeriod = new DlgSetNightRatePeriod(localPpdSchedule);
    localDlgSetNightRatePeriod.doModal();
    if (!localDlgSetNightRatePeriod.isOK()) {
      return;
    }
    localPpdSchedule = localDlgSetNightRatePeriod.getSchedule();
    if (setPpdSchedule(localPpdSchedule) == true) {}
  }
  
  public void notifySpecialDaySelected()
  {
    PpdSchedule localPpdSchedule = getPpdSchedule();
    if (localPpdSchedule == null) {
      return;
    }
    SingleCalendarExtendOption localSingleCalendarExtendOption = new SingleCalendarExtendOption();
    localSingleCalendarExtendOption.createStartDate(mCtrlDate.get(1), mCtrlDate.get(2) + 1, 1);
    localSingleCalendarExtendOption.createEndDate(2037, 12, 31);
    DlgSetSpecialDay localDlgSetSpecialDay = new DlgSetSpecialDay(localSingleCalendarExtendOption, localPpdSchedule);
    localDlgSetSpecialDay.setDefaultDate(mCtrlDate.get(1), mCtrlDate.get(2) + 1);
    localDlgSetSpecialDay.doModal();
    if (!localDlgSetSpecialDay.isOK()) {
      return;
    }
    localPpdSchedule = localDlgSetSpecialDay.getSchedule();
    if (setPpdSchedule(localPpdSchedule) == true) {}
  }
  
  public void panelSelected()
  {
    mActionButtons.updateSettings();
    validate();
  }
  
  private boolean setPpdSchedule(PpdSchedule paramPpdSchedule)
  {
    JobSetPpdSchedule localJobSetPpdSchedule = new JobSetPpdSchedule(paramPpdSchedule);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobSetPpdSchedule);
    if (!CommonUse.waitJob(localResult)) {
      return false;
    }
    if (localJobSetPpdSchedule.getResult() != 1) {
      throw new FatalException("PanelPpd.setPpdSchedule");
    }
    return true;
  }
}
