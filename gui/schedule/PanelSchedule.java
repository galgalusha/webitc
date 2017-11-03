package webitc.gui.schedule;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.schedule.Program;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.AuthListener;
import webitc.gui.common.BorderRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.JScrollTable;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.StringRenderer;
import webitc.job.JobGetAllSchedule;
import webitc.job.JobGetSchedule;
import webitc.job.JobGetScheduleList;
import webitc.job.JobSaveSchedule;
import webitc.job.JobSetSchEnable;
import webitc.job.JobSetSchedule;

public class PanelSchedule
  extends PanelAbstract
  implements AuthListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JButton mBtnCsv = new JButton();
  JButton mBtnData = new JButton();
  JButton mBtnEdit = new JButton();
  ButtonGroup mGrpEnableDisable = new ButtonGroup();
  private DefaultTableModel mModel;
  private boolean mNeedSave = false;
  JPanel mPnlEnableDisable = new JPanel();
  JRadioButton mRdoDisable = new JRadioButton();
  JRadioButton mRdoEnable = new JRadioButton();
  private ScheduleData[] mScheduleDataArray;
  private JScrollTable mTable;
  
  public PanelSchedule()
  {
    try
    {
      jbInit();
      initTable();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void ChangeTableSelect()
  {
    if (mTable.getSelectedRow() != -1) {
      if (mScheduleDataArray[mTable.getSelectedRow()].mEnable == true) {
        mRdoEnable.setSelected(true);
      } else {
        mRdoDisable.setSelected(true);
      }
    }
  }
  
  public void authSucceeded(int paramInt) {}
  
  private void initTable()
  {
    mTable.setRowHeight(20);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = StrRes.getStr("IDS_SCH_SCHEDULE_NAME");
    arrayOfString[1] = StrRes.getStr("IDS_SCH_ENABLE_DISABLE");
    mModel = new DefaultTableModel(arrayOfString, 1);
    mTable.setModel(mModel);
    TableColumnModel localTableColumnModel = mTable.getColumnModel();
    localTableColumnModel.getColumn(0).setPreferredWidth(150);
    localTableColumnModel.getColumn(0).setCellRenderer(new StringRenderer());
    localTableColumnModel.getColumn(1).setPreferredWidth(100);
    localTableColumnModel.getColumn(1).setCellRenderer(new StringRenderer());
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    mTable = new JScrollTable(EnumTableID.PANEL_SCHEDULE);
    mTable.setSelectionMode(0);
    ListSelectionModel localListSelectionModel = mTable.getSelectionModel();
    localListSelectionModel.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        PanelSchedule.this.ChangeTableSelect();
      }
    });
    mPnlEnableDisable.setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_SCH_ENABLE_DISABLE")));
    mPnlEnableDisable.setLayout(gridBagLayout2);
    mRdoEnable.setText(StrRes.getStr("IDS_COMMON_VALID"));
    mRdoEnable.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        mRdoEnable_actionPerformed(paramAnonymousActionEvent);
      }
    });
    mRdoDisable.setText(StrRes.getStr("IDS_COMMON_INVALID"));
    mRdoDisable.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        mRdoDisable_actionPerformed(paramAnonymousActionEvent);
      }
    });
    mBtnEdit.setText(StrRes.getStr("IDS_COMMON_MODIFY"));
    mBtnEdit.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        mBtnEdit_actionPerformed(paramAnonymousActionEvent);
      }
    });
    mBtnCsv.setVisible(false);
    mBtnCsv.setText(StrRes.getStr("IDS_PPD_CSV_OUTPUT"));
    mBtnData.setText(StrRes.getStr("IDS_SCH_DATA_OPE"));
    mBtnData.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent paramAnonymousActionEvent)
      {
        mBtnData_actionPerformed();
      }
    });
    add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 1, 3, 1.0D, 1.0D, 18, 1, new Insets(0, 0, 0, 0), 0, 0));
    add(mPnlEnableDisable, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlEnableDisable.add(mRdoEnable, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 14, 0, new Insets(0, 0, 0, 0), 0, 0));
    mPnlEnableDisable.add(mRdoDisable, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
    add(mBtnEdit, new GridBagConstraints(1, 1, 1, 1, 0.0D, 0.0D, 10, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBtnCsv, new GridBagConstraints(1, 3, 1, 1, 0.0D, 0.0D, 11, 2, new Insets(0, 0, 0, 0), 0, 0));
    add(mBtnData, new GridBagConstraints(1, 2, 1, 1, 0.0D, 0.0D, 11, 2, new Insets(0, 0, 0, 0), 0, 0));
    mGrpEnableDisable.add(mRdoEnable);
    mGrpEnableDisable.add(mRdoDisable);
  }
  
  public void logout()
  {
    panelDisselected();
  }
  
  void mBtnData_actionPerformed()
  {
    JobGetAllSchedule localJobGetAllSchedule = new JobGetAllSchedule();
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetAllSchedule);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    DlgSchData localDlgSchData = new DlgSchData(localJobGetAllSchedule.getProgramList());
    localDlgSchData.doModal();
    if (localDlgSchData.isOK() == true)
    {
      updateTable();
      mNeedSave = true;
    }
  }
  
  void mBtnEdit_actionPerformed(ActionEvent paramActionEvent)
  {
    int i = mTable.getSelectedRow();
    JobGetSchedule localJobGetSchedule = new JobGetSchedule(i);
    Result localResult1 = ThreadAppCom.getInstance().addJob(localJobGetSchedule);
    if (!CommonUse.waitJob(localResult1)) {
      return;
    }
    DlgSchMain localDlgSchMain = new DlgSchMain(localJobGetSchedule.getProgram(), localJobGetSchedule.getDate());
    localDlgSchMain.doModal();
    if (localDlgSchMain.isOK() == true)
    {
      Program localProgram = localDlgSchMain.getProgram();
      JobSetSchedule localJobSetSchedule = new JobSetSchedule(i, localProgram);
      Result localResult2 = ThreadAppCom.getInstance().addJob(localJobSetSchedule);
      if (!CommonUse.waitJob(localResult2)) {
        return;
      }
      mTable.getModel().setValueAt(localProgram.getName(), i, 0);
      mNeedSave = true;
    }
  }
  
  void mRdoDisable_actionPerformed(ActionEvent paramActionEvent)
  {
    int i = mTable.getSelectedRow();
    JobSetSchEnable localJobSetSchEnable = new JobSetSchEnable(i, false);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobSetSchEnable);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    mScheduleDataArray[i].mEnable = false;
    mModel.setValueAt(StrRes.getStr("IDS_COMMON_INVALID"), i, 1);
    mNeedSave = true;
  }
  
  void mRdoEnable_actionPerformed(ActionEvent paramActionEvent)
  {
    int i = mTable.getSelectedRow();
    JobSetSchEnable localJobSetSchEnable = new JobSetSchEnable(i, true);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobSetSchEnable);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    mScheduleDataArray[i].mEnable = true;
    mModel.setValueAt(StrRes.getStr("IDS_COMMON_VALID"), i, 1);
    mNeedSave = true;
  }
  
  public void panelDisselected()
  {
    saveScheduleData();
  }
  
  public void panelSelected()
  {
    updateTable();
  }
  
  private void saveScheduleData()
  {
    if (mNeedSave == true)
    {
      JobSaveSchedule localJobSaveSchedule = new JobSaveSchedule();
      Result localResult = ThreadAppCom.getInstance().addJob(localJobSaveSchedule);
      if (!CommonUse.waitJob(localResult)) {}
      mNeedSave = false;
    }
  }
  
  private void updateTable()
  {
    JobGetScheduleList localJobGetScheduleList = new JobGetScheduleList();
    Result localResult = ThreadAppCom.getInstance().addJob(localJobGetScheduleList);
    if (!CommonUse.waitJob(localResult)) {
      return;
    }
    mScheduleDataArray = new ScheduleData[localJobGetScheduleList.getScheduleNum()];
    mModel.setRowCount(0);
    for (int i = 0; i < localJobGetScheduleList.getScheduleNum(); i++)
    {
      String[] arrayOfString = new String[2];
      arrayOfString[0] = localJobGetScheduleList.getScheudleName(i);
      if (localJobGetScheduleList.isScheduleEnable(i) == true) {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_VALID");
      } else {
        arrayOfString[1] = StrRes.getStr("IDS_COMMON_INVALID");
      }
      mModel.addRow(arrayOfString);
      mScheduleDataArray[i] = new ScheduleData(localJobGetScheduleList.getScheudleName(i), localJobGetScheduleList.isScheduleEnable(i));
    }
    mTable.setModel(mModel);
    mTable.setRowSelectionInterval(0, 0);
  }
  
  class ScheduleData
  {
    public boolean mEnable;
    public String mName;
    
    ScheduleData(String paramString, boolean paramBoolean)
    {
      mName = paramString;
      mEnable = paramBoolean;
    }
  }
}
