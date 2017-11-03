package webitc.gui.monitor;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.point.TargetErr;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.JScrollTable;
import webitc.gui.common.StringRenderer;

public class DlgInfoErrHistZone
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtnClose = new JButton();
  JLabel mLblNoHistory = new JLabel();
  private DefaultTableModel mModel;
  JScrollTable mTable = new JScrollTable(EnumTableID.DLG_MONITOR_INFO_ERR_ZONE);
  
  public DlgInfoErrHistZone(ArrayList paramArrayList)
  {
    try
    {
      jbInit();
      partsInit(paramArrayList);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void errorPerformed()
  {
    mTable.setEnabled(false);
  }
  
  private void initTable()
  {
    mTable.setRowHeight(20);
    String[] arrayOfString = new String[4];
    arrayOfString[0] = StrRes.getStr("IDS_DETAIL_INFO_EXEC_TIME");
    arrayOfString[1] = StrRes.getStr("IDS_COMMON_NAME");
    arrayOfString[2] = StrRes.getStr("IDS_COMMON_ERR_CODE");
    arrayOfString[3] = StrRes.getStr("IDS_COMMON_ERR_UNIT_NO");
    mModel = new DefaultTableModel(arrayOfString, 0);
    mTable.setModel(mModel);
    TableColumnModel localTableColumnModel = mTable.getColumnModel();
    localTableColumnModel.getColumn(0).setPreferredWidth(150);
    localTableColumnModel.getColumn(0).setCellRenderer(new StringRenderer());
    localTableColumnModel.getColumn(1).setPreferredWidth(80);
    localTableColumnModel.getColumn(1).setCellRenderer(new StringRenderer());
    localTableColumnModel.getColumn(2).setPreferredWidth(70);
    localTableColumnModel.getColumn(2).setCellRenderer(new StringRenderer());
    localTableColumnModel.getColumn(3).setPreferredWidth(70);
    localTableColumnModel.getColumn(3).setCellRenderer(new StringRenderer());
    mTable.getScrollPane().setPreferredSize(new Dimension(375, 250));
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_DETAIL_INFO_ERR_HISTORY"));
    mTable.setSelectionMode(0);
    getContentPane().setLayout(gridBagLayout1);
    mBtnClose.setText(StrRes.getStr("IDS_COMMON_CLOSE"));
    mBtnClose.addActionListener(new DlgInfoErrHistory_mBtnClose_actionAdapter(this));
    mLblNoHistory.setText(StrRes.getStr("IDS_DETAIL_INFO_NO_ERR"));
    getContentPane().add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mBtnClose, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 5, 6, 12), 0, 0));
    getContentPane().add(mLblNoHistory, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 0, new Insets(15, 45, 15, 45), 0, 0));
  }
  
  void mBtnClose_actionPerformed()
  {
    closeDialog(false);
  }
  
  private void partsInit(ArrayList paramArrayList)
  {
    initTable();
    String[] arrayOfString = new String[4];
    int i = 0;
    for (int j = 0; j < paramArrayList.size(); j++)
    {
      TargetErr localTargetErr = (TargetErr)paramArrayList.get(j);
      if (!localTargetErr.isNormal())
      {
        i = 1;
        arrayOfString[0] = StrRes.getYMDHMStr(localTargetErr.getErrTime(), true);
        arrayOfString[1] = DataMgr.getInstance().getShortName(fID);
        arrayOfString[2] = localTargetErr.getErrCodeStr();
        arrayOfString[3] = localTargetErr.getUnitNoStr();
        mModel.addRow(arrayOfString);
      }
    }
    if (i != 0)
    {
      mLblNoHistory.setVisible(false);
      mTable.getScrollPane().setVisible(true);
    }
    else
    {
      mLblNoHistory.setVisible(true);
      mTable.getScrollPane().setVisible(false);
    }
  }
}
