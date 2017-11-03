package webitc.gui.schedule;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.PrintStream;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JPanel;
import webitc.common.StrRes;
import webitc.data.CommonCalendar;
import webitc.data.schedule.SchException;
import webitc.gui.common.DlgAbstract;

class DlgSchCalendar
  extends DlgAbstract
  implements CalendarListener, CalendarSelectListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnOK = new JButton();
  private DoubleCalendar mCalendar = new DoubleCalendar();
  private CommonCalendar mCommonCalendar;
  JPanel mPnlOKCancel = new JPanel();
  private CalendarSelect mSelect = new CalendarSelect();
  
  public DlgSchCalendar(CommonCalendar paramCommonCalendar, Calendar paramCalendar, SchException paramSchException)
  {
    mCommonCalendar = ((CommonCalendar)paramCommonCalendar.clone());
    mCalendar.setCalendar(mCommonCalendar, paramCalendar);
    mSelect.setException(paramSchException);
    mSelect.setDayType(0);
    mCalendar.addListener(this);
    mSelect.addListener(this);
    try
    {
      setResizable(true);
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void dateSelected(Calendar paramCalendar)
  {
    if (mSelect.isCheckBoxSelected() == true)
    {
      int i = mSelect.getDayType();
      mCommonCalendar.setDayType(i, paramCalendar);
      mCalendar.setDayType(i, paramCalendar);
    }
  }
  
  public void errorPerformed()
  {
    mCalendar.errorPerformed();
    mSelect.errorPerformed();
    mBtnOK.setEnabled(false);
  }
  
  public CommonCalendar getCalendar()
  {
    return mCommonCalendar;
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SCH_CHANGE_CALENDAR"));
    mPnlOKCancel.setLayout(gridBagLayout1);
    getContentPane().setLayout(gridBagLayout2);
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSchCalendar_mBtnOK_actionAdapter(this));
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSchCalendar_mBtnCancel_actionAdapter(this));
    getContentPane().add(mCalendar, new GridBagConstraints(0, 0, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mSelect, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 10, 2, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mPnlOKCancel, new GridBagConstraints(0, 2, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOKCancel.add(mBtnOK, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 6, 5), 0, 0));
    mPnlOKCancel.add(mBtnCancel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 6, 12), 0, 0));
  }
  
  void mBtnCancel_actionPerformed()
  {
    closeDialog(false);
  }
  
  void mBtnOK_actionPerformed()
  {
    System.out.println(getSize());
    closeDialog(true);
  }
  
  public void typeSelected(int paramInt) {}
}
