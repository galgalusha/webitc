package webitc.gui.ppd;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumModelType;
import webitc.gui.common.DlgAbstract;

public class DlgSetExclusionPeriod
  extends DlgAbstract
  implements PanelOkCancel.PanelOkCancelListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  PanelOkCancel mButtons = new PanelOkCancel(this);
  PanelSetting mFri = new PanelSetting(StrRes.getStr("IDS_COMMON_WEEK_FRI"));
  PanelSetting mMon = new PanelSetting(StrRes.getStr("IDS_COMMON_WEEK_MON"));
  PanelSetting mSat = new PanelSetting(StrRes.getStr("IDS_COMMON_WEEK_SAT"));
  private PpdSchedule mSchedule = null;
  PanelSetting mSun = new PanelSetting(StrRes.getStr("IDS_COMMON_WEEK_SUN"));
  PanelSetting mThu = new PanelSetting(StrRes.getStr("IDS_COMMON_WEEK_THU"));
  PanelSetting mTue = new PanelSetting(StrRes.getStr("IDS_COMMON_WEEK_TUE"));
  PanelSetting mWed = new PanelSetting(StrRes.getStr("IDS_COMMON_WEEK_WED"));
  
  public DlgSetExclusionPeriod(PpdSchedule paramPpdSchedule)
  {
    try
    {
      mSchedule = paramPpdSchedule;
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mSun.errorPerformed();
    mMon.errorPerformed();
    mTue.errorPerformed();
    mWed.errorPerformed();
    mThu.errorPerformed();
    mFri.errorPerformed();
    mSat.errorPerformed();
    mButtons.setEnabled(1, false);
  }
  
  public PpdSchedule getSchedule()
  {
    mSchedule.setExclusionWDay(0, mSun.isEnableSchedule());
    mSchedule.setExclusionWDay(1, mMon.isEnableSchedule());
    mSchedule.setExclusionWDay(2, mTue.isEnableSchedule());
    mSchedule.setExclusionWDay(3, mWed.isEnableSchedule());
    mSchedule.setExclusionWDay(4, mThu.isEnableSchedule());
    mSchedule.setExclusionWDay(5, mFri.isEnableSchedule());
    mSchedule.setExclusionWDay(6, mSat.isEnableSchedule());
    mSchedule.setExclusionTime(0, mSun.getSchedule());
    mSchedule.setExclusionTime(1, mMon.getSchedule());
    mSchedule.setExclusionTime(2, mTue.getSchedule());
    mSchedule.setExclusionTime(3, mWed.getSchedule());
    mSchedule.setExclusionTime(4, mThu.getSchedule());
    mSchedule.setExclusionTime(5, mFri.getSchedule());
    mSchedule.setExclusionTime(6, mSat.getSchedule());
    return mSchedule;
  }
  
  private void jbInit()
    throws Exception
  {
    if (SystemInfo.getModelType() == EnumModelType.ITC) {
      setTitle(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_TITLE"));
    } else if ((SystemInfo.getModelType() == EnumModelType.ACS) || (SystemInfo.getModelType() == EnumModelType.EXC)) {
      setTitle(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_TITLE_2"));
    } else {
      setTitle(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_TITLE_GHP"));
    }
    getContentPane().setLayout(gridBagLayout1);
    getContentPane().add(mSun, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(20, 10, 10, 10), 0, 0));
    getContentPane().add(mMon, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 10, 10), 0, 0));
    getContentPane().add(mTue, new GridBagConstraints(0, 2, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 10, 10), 0, 0));
    getContentPane().add(mWed, new GridBagConstraints(0, 3, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 10, 10), 0, 0));
    getContentPane().add(mThu, new GridBagConstraints(0, 4, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 10, 10), 0, 0));
    getContentPane().add(mFri, new GridBagConstraints(0, 5, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 10, 10), 0, 0));
    getContentPane().add(mSat, new GridBagConstraints(0, 6, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(0, 10, 10, 10), 0, 0));
    getContentPane().add(mButtons, new GridBagConstraints(0, 7, 1, 1, 0.0D, 0.0D, 14, 0, new Insets(0, 10, 10, 10), 0, 0));
    setSchedule(mSchedule);
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyOkSelected()
  {
    closeDialog(true);
  }
  
  private void setSchedule(PpdSchedule paramPpdSchedule)
  {
    if (paramPpdSchedule == null) {
      return;
    }
    mSun.setSchedule(paramPpdSchedule.isExclusionWDay(0), paramPpdSchedule.getExclusionTime(0));
    mMon.setSchedule(paramPpdSchedule.isExclusionWDay(1), paramPpdSchedule.getExclusionTime(1));
    mTue.setSchedule(paramPpdSchedule.isExclusionWDay(2), paramPpdSchedule.getExclusionTime(2));
    mWed.setSchedule(paramPpdSchedule.isExclusionWDay(3), paramPpdSchedule.getExclusionTime(3));
    mThu.setSchedule(paramPpdSchedule.isExclusionWDay(4), paramPpdSchedule.getExclusionTime(4));
    mFri.setSchedule(paramPpdSchedule.isExclusionWDay(5), paramPpdSchedule.getExclusionTime(5));
    mSat.setSchedule(paramPpdSchedule.isExclusionWDay(6), paramPpdSchedule.getExclusionTime(6));
  }
}
