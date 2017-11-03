package webitc.gui.common;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.ID;

public class ZoneTable
  extends JScrollTable
{
  private DetailNameRenderer mDetailNameRenderer = new DetailNameRenderer();
  private DefaultTableModel mModel = new DefaultTableModel();
  private NameRenderer mNameRenderer = new NameRenderer();
  private boolean mSort = false;
  
  public ZoneTable(EnumTableID paramEnumTableID)
  {
    super(paramEnumTableID);
    initZoneTable();
  }
  
  public void addRow(ID paramID)
  {
    if (paramID == null) {
      return;
    }
    ID[] arrayOfID = { paramID, paramID };
    if (!mSort)
    {
      mModel.addRow(arrayOfID);
      setRowSelectionInterval(getRowCount() - 1, getRowCount() - 1);
    }
    else
    {
      for (int i = 0; i < mModel.getRowCount(); i++)
      {
        ID localID = (ID)mModel.getValueAt(i, 0);
        if (fID > fID)
        {
          mModel.insertRow(i, arrayOfID);
          setRowSelectionInterval(i, i);
          return;
        }
      }
      mModel.addRow(arrayOfID);
      setRowSelectionInterval(getRowCount() - 1, getRowCount() - 1);
    }
  }
  
  public void addRow(ID paramID, boolean paramBoolean)
  {
    if (paramID == null) {
      return;
    }
    ID[] arrayOfID = { paramID, paramID };
    if (!mSort)
    {
      mModel.addRow(arrayOfID);
      if (paramBoolean == true) {
        setRowSelectionInterval(getRowCount() - 1, getRowCount() - 1);
      }
    }
    else
    {
      for (int i = 0; i < mModel.getRowCount(); i++)
      {
        ID localID = (ID)mModel.getValueAt(i, 0);
        if (fID > fID)
        {
          mModel.insertRow(i, arrayOfID);
          if (paramBoolean == true) {
            setRowSelectionInterval(i, i);
          }
          return;
        }
      }
      mModel.addRow(arrayOfID);
      if (paramBoolean == true) {
        setRowSelectionInterval(getRowCount() - 1, getRowCount() - 1);
      }
    }
  }
  
  public void downRow()
  {
    int i = getSelectedRow();
    if ((i == -1) || (i + 1 >= getRowCount())) {
      return;
    }
    mModel.moveRow(i, i, i + 1);
    setRowSelectionInterval(i + 1, i + 1);
  }
  
  public ID getSelectedID()
  {
    int i = getSelectedRow();
    if (i != -1) {
      return (ID)mModel.getValueAt(i, 0);
    }
    return null;
  }
  
  private void initZoneTable()
  {
    setSelectionMode(0);
    mModel.addColumn(StrRes.getStr("IDS_COMMON_NAME"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_DISCRIPTION"));
    setModel(mModel);
    TableColumnModel localTableColumnModel = getColumnModel();
    localTableColumnModel.getColumn(0).setCellRenderer(mNameRenderer);
    localTableColumnModel.getColumn(0).setPreferredWidth(80);
    localTableColumnModel.getColumn(1).setCellRenderer(mDetailNameRenderer);
    localTableColumnModel.getColumn(1).setPreferredWidth(150);
    getScrollPane().setPreferredSize(new Dimension(235, 250));
  }
  
  public void removeAllRow()
  {
    mModel.setRowCount(0);
  }
  
  public void removeRow(ID paramID)
  {
    if (paramID == null) {
      return;
    }
    int i = getSelectedRow();
    if (i == -1) {
      i = 0;
    }
    for (int j = 0; j < mModel.getRowCount(); j++)
    {
      ID localID = (ID)mModel.getValueAt(j, 0);
      if (localID.equals(paramID) == true)
      {
        mModel.removeRow(j);
        break;
      }
    }
    if (getRowCount() == 0) {
      return;
    }
    if (i >= getRowCount()) {
      i = getRowCount() - 1;
    }
    setRowSelectionInterval(i, i);
  }
  
  public int setSelectedID(ID paramID)
  {
    if (paramID == null)
    {
      removeRowSelectionInterval(0, getRowCount() - 1);
      return -1;
    }
    for (int i = 0; i < mModel.getRowCount(); i++)
    {
      ID localID = (ID)mModel.getValueAt(i, 0);
      if (localID.equals(paramID) == true)
      {
        setRowSelectionInterval(i, i);
        return i;
      }
    }
    return -1;
  }
  
  public void setSort(boolean paramBoolean)
  {
    mSort = paramBoolean;
  }
  
  public void setVisibleSelection(boolean paramBoolean)
  {
    mNameRenderer.mVisibleSelection = paramBoolean;
    mDetailNameRenderer.mVisibleSelection = paramBoolean;
  }
  
  public void setZoneList(int[] paramArrayOfInt)
  {
    removeAllRow();
    if (paramArrayOfInt == null) {
      return;
    }
    for (int i = 0; i < paramArrayOfInt.length; i++)
    {
      ID localID = new ID(1, paramArrayOfInt[i]);
      addRow(localID, false);
    }
    if (getRowCount() != 0) {
      setRowSelectionInterval(0, 0);
    }
  }
  
  public void setZoneList(ID[] paramArrayOfID)
  {
    removeAllRow();
    if (paramArrayOfID == null) {
      return;
    }
    for (int i = 0; i < paramArrayOfID.length; i++) {
      addRow(paramArrayOfID[i], false);
    }
    if (getRowCount() != 0) {
      setRowSelectionInterval(0, 0);
    }
  }
  
  public void setZoneList(ArrayList paramArrayList)
  {
    removeAllRow();
    if (paramArrayList == null) {
      return;
    }
    for (int i = 0; i < paramArrayList.size(); i++)
    {
      ID localID = (ID)paramArrayList.get(i);
      addRow(localID, false);
    }
    if (getRowCount() != 0) {
      setRowSelectionInterval(0, 0);
    }
  }
  
  public void upRow()
  {
    int i = getSelectedRow();
    if ((i == -1) || (i == 0)) {
      return;
    }
    mModel.moveRow(i, i, i - 1);
    setRowSelectionInterval(i - 1, i - 1);
  }
  
  class DetailNameRenderer
    extends ITCTableCellRenderer
  {
    public boolean mVisibleSelection = true;
    
    DetailNameRenderer() {}
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      setEnabled(paramJTable.isEnabled());
      boolean bool = mVisibleSelection == true ? paramBoolean1 : false;
      setColor(bool);
      if (paramObject != null)
      {
        ID localID = (ID)paramObject;
        DataMgr localDataMgr = DataMgr.getInstance();
        setText(localDataMgr.getDetailName(localID));
      }
      return this;
    }
  }
  
  class NameRenderer
    extends ITCTableCellRenderer
  {
    public boolean mVisibleSelection = true;
    
    NameRenderer() {}
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      setEnabled(paramJTable.isEnabled());
      boolean bool = mVisibleSelection == true ? paramBoolean1 : false;
      setColor(bool);
      if (paramObject != null)
      {
        ID localID = (ID)paramObject;
        DataMgr localDataMgr = DataMgr.getInstance();
        setText(localDataMgr.getShortName(localID));
      }
      return this;
    }
  }
}
