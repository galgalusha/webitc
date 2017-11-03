package webitc.gui.ppd;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.JScrollTable;
import webitc.gui.common.StringRenderer;

public class DlgSetContractPwr
  extends DlgAbstract
  implements PanelOkCancel.PanelOkCancelListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtnModify = new JButton();
  PanelOkCancel mButtons = new PanelOkCancel(this);
  int[] mContractPwr = null;
  int[] mEditContractPwr = null;
  DefaultTableModel mModel = new DefaultTableModel();
  JScrollTable mTable = new JScrollTable(EnumTableID.DLG_CONTRACT_PWR);
  
  public DlgSetContractPwr(int[] paramArrayOfInt)
  {
    try
    {
      mContractPwr = paramArrayOfInt;
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mBtnModify.setEnabled(false);
    mButtons.setEnabled(1, false);
  }
  
  public int[] getContractPwr()
  {
    return mContractPwr;
  }
  
  private void initTable()
  {
    mTable.setRowHeight(20);
    TableColumnModel localTableColumnModel = mTable.getColumnModel();
    mModel.addColumn(StrRes.getStr("IDS_COMMON_UNIT_MONTH"));
    mModel.addColumn(StrRes.getStr("IDS_PPDCOLLECTION_CONTRACT_PWR"));
    mTable.setModel(mModel);
    localTableColumnModel.getColumn(0).setPreferredWidth(100);
    localTableColumnModel.getColumn(0).setCellRenderer(new StringRenderer());
    localTableColumnModel.getColumn(1).setPreferredWidth(150);
    localTableColumnModel.getColumn(1).setCellRenderer(new StringRenderer());
    mTable.getScrollPane().setPreferredSize(new Dimension(255, 300));
    updateTable();
  }
  
  private void jbInit()
    throws Exception
  {
    mEditContractPwr = new int[mContractPwr.length];
    for (int i = 0; i < mEditContractPwr.length; i++) {
      mEditContractPwr[i] = mContractPwr[i];
    }
    setTitle(StrRes.getStr("IDS_PPDCOLLECTION_CONTRACT_PWR"));
    getContentPane().setLayout(gridBagLayout1);
    mBtnModify.setText(StrRes.getStr("IDS_COMMON_MODIFY"));
    mBtnModify.addActionListener(new DlgSetContractPwr_mBtnModify_actionAdapter(this));
    getContentPane().add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 1, 2, 1.0D, 1.0D, 18, 1, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mBtnModify, new GridBagConstraints(1, 0, 1, 1, 0.0D, 0.0D, 10, 0, new Insets(10, 10, 10, 10), 0, 0));
    getContentPane().add(mButtons, new GridBagConstraints(0, 2, 2, 1, 0.0D, 0.0D, 14, 0, new Insets(0, 10, 10, 10), 0, 0));
    initTable();
  }
  
  void mBtnModify_actionPerformed(ActionEvent paramActionEvent)
  {
    DlgModifyContractPwr localDlgModifyContractPwr = new DlgModifyContractPwr(mEditContractPwr);
    localDlgModifyContractPwr.doModal();
    if (!localDlgModifyContractPwr.isOK()) {
      return;
    }
    mEditContractPwr = localDlgModifyContractPwr.getContractPwr();
    updateTable();
  }
  
  public void notifyCancelSelected()
  {
    closeDialog(false);
  }
  
  public void notifyOkSelected()
  {
    mContractPwr = mEditContractPwr;
    closeDialog(true);
  }
  
  private void updateTable()
  {
    mModel.setRowCount(0);
    for (int i = 0; i < mEditContractPwr.length; i++)
    {
      String[] arrayOfString = new String[2];
      int j = i + 1;
      arrayOfString[0] = StrRes.getMonthStr(j);
      StringBuffer localStringBuffer = new StringBuffer(String.valueOf(mEditContractPwr[i]));
      localStringBuffer.append(StrRes.getStr("IDS_PPDCOLLECTION_KWH"));
      arrayOfString[1] = localStringBuffer.toString();
      mModel.addRow(arrayOfString);
    }
  }
}
