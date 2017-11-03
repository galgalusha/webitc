package webitc.gui.monitor;

import java.util.Vector;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.VZone;
import webitc.gui.common.JScrollTable;
import webitc.gui.common.StringRenderer;

class JACTableList
  extends JScrollTable
  implements AcDataDisplayer
{
  AcDataSelectionListener mListener = null;
  private TableModelList mModel = new TableModelList();
  
  public JACTableList(EnumTableID paramEnumTableID)
  {
    super(paramEnumTableID);
    setModel(mModel);
    setSelectionMode(0);
    DefaultTableColumnModel localDefaultTableColumnModel = (DefaultTableColumnModel)getColumnModel();
    localDefaultTableColumnModel.getColumn(0).setCellRenderer(new StatusRenderer());
    localDefaultTableColumnModel.getColumn(1).setCellRenderer(new StringRenderer());
    localDefaultTableColumnModel.getColumn(4).setCellRenderer(new AirTempRenderer());
    localDefaultTableColumnModel.getColumn(2).setCellRenderer(new SetTempRenderer());
    localDefaultTableColumnModel.getColumn(3).setCellRenderer(new DrvModeRenderer());
    localDefaultTableColumnModel.getColumn(5).setCellRenderer(new ErrCodeRenderer());
    localDefaultTableColumnModel.getColumn(0).setPreferredWidth(60);
    localDefaultTableColumnModel.getColumn(1).setPreferredWidth(90);
    localDefaultTableColumnModel.getColumn(2).setPreferredWidth(50);
    localDefaultTableColumnModel.getColumn(3).setPreferredWidth(50);
    localDefaultTableColumnModel.getColumn(4).setPreferredWidth(50);
    localDefaultTableColumnModel.getColumn(5).setPreferredWidth(50);
  }
  
  public ID getSelectedPnt()
  {
    int i = getSelectedRow();
    if ((i != -1) && (mModel.getRowCount() > i)) {
      return (ID)mModel.getValueAt(i, 0);
    }
    return null;
  }
  
  public void refresh()
  {
    paintAll(getGraphics());
  }
  
  public void setAcDataSelectionListener(AcDataSelectionListener paramAcDataSelectionListener)
  {
    mListener = paramAcDataSelectionListener;
  }
  
  public void setSelectedPnt(ID paramID)
  {
    if (paramID == null) {
      return;
    }
    for (int i = 0; i < mModel.getRowCount(); i++) {
      if (paramID.equals((ID)mModel.getValueAt(i, 0)))
      {
        AcDataSelectionListener localAcDataSelectionListener = mListener;
        mListener = null;
        setRowSelectionInterval(i, i);
        mListener = localAcDataSelectionListener;
        return;
      }
    }
  }
  
  public void showRoot()
  {
    AcDataSelectionListener localAcDataSelectionListener = mListener;
    mListener = null;
    mModel.getDataVector().clear();
    ID[] arrayOfID = DataMgr.getInstance().getLoginUser().getZoneIDs();
    for (int i = 0; i < arrayOfID.length; i++) {
      mModel.addData(arrayOfID[i]);
    }
    setModel(mModel);
    if (arrayOfID.length > 0) {
      addRowSelectionInterval(0, 0);
    }
    mListener = localAcDataSelectionListener;
  }
  
  public void showZone(ID paramID)
  {
    AcDataSelectionListener localAcDataSelectionListener = mListener;
    mListener = null;
    mModel.getDataVector().clear();
    VZone localVZone = DataMgr.getInstance().getZoneFromID(paramID);
    for (int i = 0; i < localVZone.getPntNum(); i++)
    {
      ID localID = localVZone.getPntID(i);
      mModel.addData(localID);
    }
    setModel(mModel);
    if (localVZone.getPntNum() > 0) {
      addRowSelectionInterval(0, 0);
    }
    mListener = localAcDataSelectionListener;
  }
  
  public void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    super.valueChanged(paramListSelectionEvent);
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    if (mListener != null) {
      mListener.acDataSelected(getSelectedPnt());
    }
  }
}
