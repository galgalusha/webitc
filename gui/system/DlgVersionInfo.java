package webitc.gui.system;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.gui.common.DlgAbstract;
import webitc.gui.common.JScrollTable;
import webitc.gui.common.StringRenderer;

public class DlgVersionInfo
  extends DlgAbstract
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JButton mBtnClose = new JButton();
  JLabel mLblNoHistory = new JLabel();
  private DefaultTableModel mModel;
  JScrollTable mTable = new JScrollTable(EnumTableID.DLG_SYSTEM_VERSION);
  
  public DlgVersionInfo()
  {
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
  
  public void errorPerformed()
  {
    mTable.setEnabled(false);
  }
  
  private void initTable()
  {
    mTable.setRowHeight(20);
    String[] arrayOfString = new String[2];
    arrayOfString[0] = StrRes.getStr("IDS_COMMON_ITEM");
    arrayOfString[1] = StrRes.getStr("IDS_COMMON_CONTENT");
    mModel = new DefaultTableModel(arrayOfString, 0);
    mTable.setModel(mModel);
    TableColumnModel localTableColumnModel = mTable.getColumnModel();
    localTableColumnModel.getColumn(0).setPreferredWidth(100);
    localTableColumnModel.getColumn(0).setCellRenderer(new StringRenderer());
    localTableColumnModel.getColumn(1).setPreferredWidth(100);
    localTableColumnModel.getColumn(1).setCellRenderer(new StringRenderer());
  }
  
  private void jbInit()
    throws Exception
  {
    setTitle(StrRes.getStr("IDS_SYSVERINFO_TITLE"));
    mTable.setSelectionMode(0);
    getContentPane().setLayout(gridBagLayout1);
    mBtnClose.setText(StrRes.getStr("IDS_COMMON_CLOSE"));
    mBtnClose.addActionListener(new DlgInfoErrHistory_mBtnClose_actionAdapter(this));
    mLblNoHistory.setText(StrRes.getStr("IDS_DETAIL_INFO_NO_ERR"));
    getContentPane().add(mTable.getScrollPane(), new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mBtnClose, new GridBagConstraints(0, 1, 1, 1, 0.0D, 0.0D, 13, 0, new Insets(5, 5, 5, 5), 0, 0));
    getContentPane().add(mLblNoHistory, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 0, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  void mBtnClose_actionPerformed()
  {
    closeDialog(false);
  }
  
  private void partsInit() {}
}
