package webitc.gui.ppd;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import webitc.common.FatalException;
import webitc.common.StrRes;
import webitc.common.SystemInfo;
import webitc.common.enum2.EnumLang;
import webitc.common.enum2.EnumPpdCollectionType;
import webitc.common.enum2.EnumTableID;
import webitc.frame.Result;
import webitc.frame.ThreadAppCom;
import webitc.gui.common.BorderRes;
import webitc.gui.common.CommonUse;
import webitc.gui.common.DlgMsgList;
import webitc.gui.common.PanelAbstract;
import webitc.job.JobPpdFileOutput;
import webitc.job.JobUpdatePpdCollectionData;

public class PanelPpdResult
  extends PanelAbstract
  implements PanelRadioButtons.RadioButtonsListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  private JButton mBtnFile = new JButton();
  private JLabel mLblCollectionPeriod = new JLabel();
  private JPanel mPanelCollectionPeriod = new JPanel();
  private PanelRadioButtons mRadioButtons = new PanelRadioButtons(this);
  private PpdTable mTable = new PpdTable(EnumTableID.PANEL_PPD);
  private JTextField mTextCollectionPeriod = new JTextField();
  
  public PanelPpdResult()
  {
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void execFileOutput()
    throws Exception
  {
    JFileChooser localJFileChooser = new JFileChooser();
    localJFileChooser.setDialogTitle(StrRes.getStr("IDS_FILEDIALOG_TITLE_SAVEFOLDER"));
    localJFileChooser.setApproveButtonToolTipText(StrRes.getStr("IDS_FILEDIALOG_SAVE_FOLDER"));
    UIManager.put("FileChooser.fileNameLabelText", StrRes.getStr("IDS_FILEDIALOG_FOLDER_NAME") + StrRes.getStr("IDS_COMMON_SEPARATER_COLON") + " ");
    UIManager.put("FileChooser.cancelButtonToolTipText", StrRes.getStr("IDS_FILEDIALOG_CLOSE"));
    Locale localLocale = new Locale(SystemInfo.getLang().toString(), "");
    Locale.setDefault(localLocale);
    localJFileChooser.setLocale(localLocale);
    localJFileChooser.updateUI();
    localJFileChooser.setFileFilter(new OrgFilter());
    localJFileChooser.setAcceptAllFileFilterUsed(false);
    localJFileChooser.setFileSelectionMode(1);
    localJFileChooser.setFileHidingEnabled(true);
    int i = localJFileChooser.showSaveDialog(this);
    if (i != 0) {
      return;
    }
    if (!CommonUse.showConfirmDlg(StrRes.getStr("IDS_PPD_MSG_CONFIRM_EXECUTION"), StrRes.getStr("IDS_PPDRESULTOUTPUT_GATHER"))) {
      return;
    }
    File localFile = localJFileChooser.getSelectedFile();
    JobPpdFileOutput localJobPpdFileOutput = new JobPpdFileOutput(localFile);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobPpdFileOutput);
    if (!CommonUse.waitJobWithCancel(localResult, localJobPpdFileOutput.getProgress(), true)) {
      return;
    }
    if (localJobPpdFileOutput.getResult() == 1)
    {
      ArrayList localArrayList1 = localJobPpdFileOutput.getSuccessFiles();
      ArrayList localArrayList2 = localJobPpdFileOutput.getFailedFiles();
      showResult(localArrayList1, localArrayList2);
    }
    else if (localJobPpdFileOutput.getResult() == -1)
    {
      CommonUse.showInformationDlg(StrRes.getStr("IDS_COMMON_CANCELED"), StrRes.getStr("IDS_SYSHISTORY_FILE_OUTPUT"));
    }
    else
    {
      throw new FatalException("PanelPpdResult.execFileOutput");
    }
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_PPDRESULTOUTPUT_RESULT")));
    mPanelCollectionPeriod.setLayout(gridBagLayout2);
    mLblCollectionPeriod.setText(StrRes.getStr("IDS_PPDCOLLECTIONPERIOD_PERIOD"));
    mTextCollectionPeriod.setEditable(false);
    mBtnFile.setText(StrRes.getStr("IDS_SYSHISTORY_FILE_OUTPUT"));
    mBtnFile.addActionListener(new PanelPpdResult_mBtnFile_actionAdapter(this));
    mBtnFile.setEnabled(false);
    mRadioButtons.setEnable(false);
    mPanelCollectionPeriod.add(mLblCollectionPeriod, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 5, 5), 0, 0));
    mPanelCollectionPeriod.add(mTextCollectionPeriod, new GridBagConstraints(1, 0, 1, 1, 1.0D, 0.0D, 17, 2, new Insets(5, 5, 5, 0), 0, 0));
    add(mTable.getScrollPane(), new GridBagConstraints(0, 2, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 10, 5, 5), 0, 0));
    add(mRadioButtons, new GridBagConstraints(0, 0, 1, 1, 0.0D, 0.0D, 17, 0, new Insets(5, 5, 0, 5), 0, 0));
    add(mPanelCollectionPeriod, new GridBagConstraints(0, 1, 1, 1, 1.0D, 0.0D, 11, 2, new Insets(10, 5, 10, 5), 0, 0));
    add(mBtnFile, new GridBagConstraints(0, 3, 1, 1, 0.0D, 0.0D, 14, 0, new Insets(10, 10, 10, 5), 0, 0));
  }
  
  void mBtnFile_actionPerformed(ActionEvent paramActionEvent)
  {
    try
    {
      execFileOutput();
    }
    catch (Exception localException)
    {
      String[] arrayOfString = new String[2];
      arrayOfString[0] = StrRes.getStr("IDS_SYSERR_AUTH");
      arrayOfString[1] = StrRes.getStr("IDS_SYSERR_CLOSE_BROWSER");
      CommonUse.showErrorDlg(arrayOfString, StrRes.getStr("IDS_SYSHISTORY_FILE_OUTPUT"));
    }
  }
  
  public void notifyPointSelected()
  {
    updateList(true);
  }
  
  public void notifySumSelected()
  {
    updateList(true);
  }
  
  public void notifyZoneSelected()
  {
    updateList(true);
  }
  
  private void showResult(ArrayList paramArrayList1, ArrayList paramArrayList2)
  {
    int i = 0;
    if (paramArrayList1.size() > 0) {
      i += paramArrayList1.size() + 1;
    }
    if (paramArrayList2.size() > 0) {
      i += paramArrayList2.size() + 1;
    }
    if (i == 0) {
      return;
    }
    String[] arrayOfString = new String[i];
    int j = 0;
    int k;
    if (paramArrayList1.size() > 0)
    {
      arrayOfString[j] = StrRes.getStr("IDS_PPDRESULTOUTPUT_FILE_CREATE");
      j++;
      k = 0;
      while (k < paramArrayList1.size())
      {
        arrayOfString[j] = ((String)paramArrayList1.get(k));
        k++;
        j++;
      }
    }
    if (paramArrayList2.size() > 0)
    {
      arrayOfString[j] = StrRes.getStr("IDS_PPDRESULTOUTPUT_FILE_ERROR");
      j++;
      k = 0;
      while (k < paramArrayList2.size())
      {
        arrayOfString[j] = ((String)paramArrayList2.get(k));
        k++;
        j++;
      }
    }
    CommonUse.showInformationDlg(arrayOfString, StrRes.getStr("IDS_PPDRESULTOUTPUT_GATHER"));
  }
  
  public void update(Calendar paramCalendar1, Calendar paramCalendar2, boolean paramBoolean, int paramInt)
  {
    JobUpdatePpdCollectionData localJobUpdatePpdCollectionData = new JobUpdatePpdCollectionData(paramCalendar1, paramCalendar2, paramBoolean, paramInt);
    mTextCollectionPeriod.setText("");
    updateList(false);
    Result localResult = ThreadAppCom.getInstance().addJob(localJobUpdatePpdCollectionData);
    if (!CommonUse.waitJobWithCancel(localResult, localJobUpdatePpdCollectionData.getProgress(), true)) {
      return;
    }
    if (localJobUpdatePpdCollectionData.getResult() == 1)
    {
      String[] arrayOfString = PpdCollectionData.getInstance().getInvalidTimeStrings();
      if (arrayOfString.length > 0)
      {
        DlgMsgList localDlgMsgList = new DlgMsgList(StrRes.getStr("IDS_PDV"), StrRes.getStr("IDS_PPDRESULTOUTPUT_NO_DATA"), arrayOfString);
        localDlgMsgList.doModal();
      }
      mTextCollectionPeriod.setText(StrRes.getYMDStr2(paramCalendar1, true) + " " + StrRes.getStr("IDS_KBD_RIGHT") + " " + StrRes.getYMDStr2(paramCalendar2, true));
      updateList(true);
    }
    else if (localJobUpdatePpdCollectionData.getResult() == -1)
    {
      CommonUse.showInformationDlg(StrRes.getStr("IDS_COMMON_CANCELED"), StrRes.getStr("IDS_PPDRESULTOUTPUT_GATHER"));
    }
    else {}
  }
  
  private void updateList(boolean paramBoolean)
  {
    mBtnFile.setEnabled(paramBoolean);
    mRadioButtons.setEnable(paramBoolean);
    EnumPpdCollectionType localEnumPpdCollectionType = mRadioButtons.getSelectedValue();
    mTable.updatePpdTable(localEnumPpdCollectionType);
  }
}
