package webitc.gui.schedule;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import webitc.common.StrRes;
import webitc.data.CommonCalendar;
import webitc.data.schedule.AbstSchedule;
import webitc.data.schedule.Daily;
import webitc.data.schedule.Program;
import webitc.data.schedule.SchException;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.common.BorderRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.ITCListCellRenderer;
import webitc.gui.common.JScrollList;
import webitc.job.JobSetAllSchedule;

public class DlgSchData
  extends DlgAbstract
{
  private static final int DELETE_ALL = 2;
  private static final int DELETE_CALENDAR = 3;
  private static final int OVERWRITE_ALL = 0;
  private static final int OVERWRITE_CALENDAR = 1;
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  JButton mBtnCancel = new JButton();
  JButton mBtnExe = new JButton();
  JButton mBtnOK = new JButton();
  JComboBox mCmbOp = new JComboBox();
  JLabel mLblOpe = new JLabel();
  JScrollList mLstFrom = new JScrollList();
  JScrollList mLstTo = new JScrollList();
  JPanel mPnlCopyFrom = new JPanel();
  JPanel mPnlCopyTo = new JPanel();
  JPanel mPnlOKCancel = new JPanel();
  JPanel mPnlOpe = new JPanel();
  private ArrayList mProgramList;
  
  public DlgSchData(ArrayList paramArrayList)
  {
    mProgramList = paramArrayList;
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
  
  private boolean confirmApply()
  {
    StringBuffer localStringBuffer = new StringBuffer("<html>");
    Program localProgram1;
    Program localProgram2;
    switch (mCmbOp.getSelectedIndex())
    {
    case 0: 
      localProgram1 = (Program)mProgramList.get(mLstFrom.getSelectedIndex());
      localProgram2 = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_DATA_OW_ALL") + "<br>");
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_FROM_SCHEDULE") + " : " + localProgram1.getName() + "<br>");
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_TO_SCHEDULE") + " : " + localProgram2.getName());
      break;
    case 1: 
      localProgram1 = (Program)mProgramList.get(mLstFrom.getSelectedIndex());
      localProgram2 = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_DATA_OW_CAL") + "<br>");
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_FROM_SCHEDULE") + " : " + localProgram1.getName() + "<br>");
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_TO_SCHEDULE") + " : " + localProgram2.getName());
      break;
    case 2: 
      localProgram1 = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_DATA_DEL_ALL") + "<br>");
      localStringBuffer.append(StrRes.getStr("IDS_SCH_SCHEDULE_NAME") + " : " + localProgram1.getName());
      break;
    case 3: 
      localProgram1 = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      localStringBuffer.append(StrRes.getStr("IDS_SCHDDATAOPE_DATA_DEL_CAL") + "<br>");
      localStringBuffer.append(StrRes.getStr("IDS_SCH_SCHEDULE_NAME") + " : " + localProgram1.getName());
      break;
    default: 
      return false;
    }
    localStringBuffer.append("</html>");
    return CommonUse.showConfirmDlg(localStringBuffer, StrRes.getStr("IDS_UTILMESSAGE_CONFIRM"));
  }
  
  public void errorPerformed()
  {
    mBtnOK.setEnabled(false);
    mLstFrom.setEnabled(false);
    mLstTo.setEnabled(false);
    mCmbOp.setEnabled(false);
    mBtnExe.setEnabled(false);
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SCH_DATA_OPE"));
    getContentPane().setLayout(gridBagLayout1);
    mPnlOpe.setLayout(gridBagLayout2);
    mPnlCopyFrom.setLayout(gridBagLayout3);
    mPnlCopyFrom.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCHDDATAOPE_FROM_SCHEDULE")));
    mPnlCopyTo.setLayout(gridBagLayout4);
    mPnlCopyTo.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCHDDATAOPE_TO_SCHEDULE")));
    mBtnOK.setText(StrRes.getStr("IDS_COMMON_OK"));
    mBtnOK.addActionListener(new DlgSchData_mBtnOK_actionAdapter(this));
    mPnlOKCancel.setLayout(gridBagLayout5);
    mLblOpe.setText(StrRes.getStr("IDS_SCHDDATAOPE_CONTENT"));
    mBtnExe.setText(StrRes.getStr("IDS_SCHDDATAOPE_APPLY"));
    mBtnExe.addActionListener(new DlgSchData_mBtnExe_actionAdapter(this));
    mBtnCancel.setText(StrRes.getStr("IDS_COMMON_CANCEL"));
    mBtnCancel.addActionListener(new DlgSchData_mBtnCancel_actionAdapter(this));
    mCmbOp.addActionListener(new DlgSchData_mCmbOp_actionAdapter(this));
    getContentPane().add(mPnlOpe, new GridBagConstraints(0, 0, 2, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    getContentPane().add(mPnlCopyFrom, new GridBagConstraints(0, 1, 1, 1, 0.5D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mPnlCopyTo, new GridBagConstraints(1, 1, 1, 1, 0.5D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mPnlOKCancel, new GridBagConstraints(0, 2, 2, 1, 0.0D, 0.0D, 13, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlOKCancel.add(mBtnOK, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 6, 5), 0, 0));
    mPnlOpe.add(mLblOpe, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlOpe.add(mCmbOp, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlOpe.add(mBtnExe, new GridBagConstraints(2, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPnlCopyFrom.add(mLstFrom.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    mPnlCopyTo.add(mLstTo.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    mPnlOKCancel.add(mBtnCancel, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(5, 5, 6, 12), 0, 0));
  }
  
  void mBtnCancel_actionPerformed()
  {
    closeDialog(false);
  }
  
  void mBtnExe_actionPerformed()
  {
    if (!confirmApply()) {
      return;
    }
    Program localProgram;
    Object localObject;
    switch (mCmbOp.getSelectedIndex())
    {
    case 0: 
      localProgram = (Program)mProgramList.get(mLstFrom.getSelectedIndex());
      localObject = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      ((Program)localObject).setActive(localProgram.isActive());
      ((Program)localObject).setCalendar((CommonCalendar)localProgram.getCalendar().clone());
      ((Program)localObject).setWeekly((AbstSchedule)localProgram.getWeekly().clone());
      ((Program)localObject).setException((SchException)localProgram.getException().clone());
      break;
    case 1: 
      localProgram = (Program)mProgramList.get(mLstFrom.getSelectedIndex());
      localObject = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      ((Program)localObject).setCalendar((CommonCalendar)localProgram.getCalendar().clone());
      break;
    case 2: 
      localProgram = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      localObject = localProgram.getCalendar();
      ((CommonCalendar)localObject).clear();
      localProgram.setCalendar((CommonCalendar)localObject);
      AbstSchedule localAbstSchedule = new AbstSchedule(7L);
      localProgram.setWeekly(localAbstSchedule);
      SchException localSchException = localProgram.getException();
      for (int i = 0; i < 10; i++)
      {
        Daily localDaily = localSchException.getDaily(i);
        localDaily.removeAllEvent();
        localSchException.setDaily(i, localDaily);
      }
      localProgram.setException(localSchException);
      localProgram.setActive(false);
      break;
    case 3: 
      localProgram = (Program)mProgramList.get(mLstTo.getSelectedIndex());
      localObject = localProgram.getCalendar();
      ((CommonCalendar)localObject).clear();
      localProgram.setCalendar((CommonCalendar)localObject);
      break;
    }
  }
  
  void mBtnOK_actionPerformed()
  {
    JobSetAllSchedule localJobSetAllSchedule = new JobSetAllSchedule(mProgramList);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobSetAllSchedule);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    closeDialog(true);
  }
  
  void mCmbOp_actionPerformed()
  {
    updateGUI();
  }
  
  private void mLstFrom_valueChanged()
  {
    updateGUI();
  }
  
  private void mLstTo_valueChanged()
  {
    updateGUI();
  }
  
  private void partsInit()
  {
    mCmbOp.addItem(StrRes.getStr("IDS_SCHDDATAOPE_DATA_OW_ALL"));
    mCmbOp.addItem(StrRes.getStr("IDS_SCHDDATAOPE_DATA_OW_CAL"));
    mCmbOp.addItem(StrRes.getStr("IDS_SCHDDATAOPE_DATA_DEL_ALL"));
    mCmbOp.addItem(StrRes.getStr("IDS_SCHDDATAOPE_DATA_DEL_CAL"));
    Vector localVector = new Vector(8);
    for (int i = 0; i < mProgramList.size(); i++)
    {
      Program localProgram = (Program)mProgramList.get(i);
      localVector.add(localProgram.getName());
    }
    mLstFrom.setCellRenderer(new ITCListCellRenderer());
    mLstFrom.setListData(localVector);
    mLstFrom.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (paramAnonymousListSelectionEvent.getValueIsAdjusting() == true) {
          return;
        }
        DlgSchData.this.mLstFrom_valueChanged();
      }
    });
    mLstFrom.setSelectedIndex(0);
    mLstTo.setCellRenderer(new ITCListCellRenderer());
    mLstTo.setListData(localVector);
    mLstTo.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (paramAnonymousListSelectionEvent.getValueIsAdjusting() == true) {
          return;
        }
        DlgSchData.this.mLstTo_valueChanged();
      }
    });
    mLstTo.setSelectedIndex(0);
  }
  
  private void updateGUI()
  {
    if ((mCmbOp.getSelectedIndex() == 0) || (mCmbOp.getSelectedIndex() == 1))
    {
      mLstFrom.setEnabled(true);
      if (mLstFrom.getSelectedIndex() == -1) {
        mLstFrom.setSelectedIndex(0);
      }
      if (mLstFrom.getSelectedIndex() == mLstTo.getSelectedIndex()) {
        mBtnExe.setEnabled(false);
      } else {
        mBtnExe.setEnabled(true);
      }
    }
    else
    {
      mLstFrom.setEnabled(false);
      mLstFrom.clearSelection();
      mBtnExe.setEnabled(true);
    }
  }
}
