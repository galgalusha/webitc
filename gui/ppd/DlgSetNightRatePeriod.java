package webitc.gui.ppd;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import webitc.common.StrRes;
import webitc.gui.common.DlgAbstract;

public class DlgSetNightRatePeriod
  extends DlgAbstract
  implements PanelOkCancel.PanelOkCancelListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  PanelOkCancel mButtons = new PanelOkCancel(this);
  JComboBox mCmbEnd = new JComboBox();
  JComboBox mCmbStart = new JComboBox();
  JLabel mLblChilder = new JLabel();
  JLabel mLblEnd = new JLabel();
  JLabel mLblStart = new JLabel();
  private PpdSchedule mSchedule = null;
  
  public DlgSetNightRatePeriod(PpdSchedule paramPpdSchedule)
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
    mCmbStart.setEnabled(false);
    mCmbEnd.setEnabled(false);
    mButtons.setEnabled(1, false);
  }
  
  public PpdSchedule getSchedule()
  {
    return mSchedule;
  }
  
  private void jbInit()
    throws Exception
  {
    getContentPane().setLayout(gridBagLayout1);
    setTitle(StrRes.getStr("IDS_PPDNIGHTRATETIME_TITLE"));
    mLblStart.setText(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_START"));
    mLblEnd.setText(StrRes.getStr("IDS_PPDEXCLUSIONPERIOD_END"));
    mLblChilder.setText(StrRes.getStr("IDS_KBD_SYM_ON_0"));
    getContentPane().add(mLblStart, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblChilder, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mLblEnd, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(10, 10, 0, 10), 0, 0));
    getContentPane().add(mCmbStart, new GridBagConstraints(0, 1, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 0), 0, 0));
    getContentPane().add(mCmbEnd, new GridBagConstraints(2, 1, 1, 1, 0.5D, 0.0D, 10, 2, new Insets(10, 10, 0, 20), 0, 0));
    getContentPane().add(mButtons, new GridBagConstraints(0, 2, 3, 1, 0.0D, 0.0D, 14, 0, new Insets(10, 10, 10, 10), 0, 0));
    Dimension localDimension = new Dimension(150, 20);
    mCmbStart.setPreferredSize(localDimension);
    mCmbEnd.setPreferredSize(localDimension);
    for (int i = 0; i <= 24; i++)
    {
      StringBuffer localStringBuffer = new StringBuffer(StrRes.itoa(i, true, 2));
      localStringBuffer.append(StrRes.getStr("IDS_COMMON_SEPARATER_COLON"));
      localStringBuffer.append(StrRes.itoa(0, true, 2));
      if (i != 24) {
        mCmbStart.addItem(localStringBuffer);
      }
      mCmbEnd.addItem(localStringBuffer);
    }
    setSchedule(mSchedule);
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyOkSelected()
  {
    PpdPeriod localPpdPeriod = new PpdPeriod(true);
    localPpdPeriod.setStartHour((char)mCmbStart.getSelectedIndex());
    localPpdPeriod.setEndHour((char)mCmbEnd.getSelectedIndex());
    mSchedule.setNightTime(localPpdPeriod);
    closeDialog(true);
  }
  
  private void setSchedule(PpdSchedule paramPpdSchedule)
  {
    PpdPeriod localPpdPeriod = paramPpdSchedule.getNightTime();
    mCmbStart.setSelectedIndex(localPpdPeriod.getStartHour());
    mCmbEnd.setSelectedIndex(localPpdPeriod.getEndHour());
  }
}
