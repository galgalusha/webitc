package webitc.gui.monitor;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.PntState;
import webitc.data.point.VZone;
import webitc.gui.TableModelIcon;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.IconRes;
import webitc.gui.common.ItcIconInfo;
import webitc.gui.common.JScrollTable;

class JACTableIcon
  extends JScrollTable
  implements AcDataDisplayer
{
  private static final int HEIGHT = 70;
  private static final int WIDTH = 75;
  AcDataSelectionListener mListener = null;
  private TableModelIcon mModel = new TableModelIcon(4);
  
  public JACTableIcon()
  {
    super(EnumTableID.UNKNOWN);
    setModel(mModel);
    setColumnModel();
    setSelectionMode(0);
    setColumnSelectionAllowed(true);
    setTableHeader(null);
    setRowHeight(70);
    setShowHorizontalLines(false);
    setShowVerticalLines(false);
    setShowGrid(false);
    setIntercellSpacing(new Dimension(0, 0));
    getScrollPane().getViewport().setBackground(ColorRes.C_NO_SELECTED_B);
  }
  
  public void columnSelectionChanged(ListSelectionEvent paramListSelectionEvent)
  {
    super.columnSelectionChanged(paramListSelectionEvent);
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    if (mListener != null) {
      mListener.acDataSelected(getSelectedPnt());
    }
  }
  
  public ID getSelectedPnt()
  {
    int i = getSelectedRow();
    int j = getSelectedColumn();
    if ((i != -1) && (j != -1) && (mModel.getRowCount() > i) && (mModel.getColumnCount() > j)) {
      return (ID)mModel.getValueAt(i, j);
    }
    return null;
  }
  
  public void refresh()
  {
    paintAll(getGraphics());
  }
  
  public void scrollWndResized(Dimension paramDimension)
  {
    if (mModel == null) {
      return;
    }
    int i = mModel.getColumnCount();
    int j = (int)(paramDimension.getWidth() / 75.0D);
    if (j <= 0) {
      j = 1;
    }
    if (i == j) {
      return;
    }
    int k = getSelectedRow();
    int m = getSelectedColumn();
    ID localID;
    if ((k != -1) && (m != -1)) {
      localID = (ID)mModel.getValueAt(k, m);
    } else {
      localID = null;
    }
    TableModelIcon localTableModelIcon = new TableModelIcon(j);
    Vector localVector1 = mModel.getDataVector();
    for (int n = 0; n < localVector1.size(); n++) {
      if (localVector1.get(n) != null)
      {
        Vector localVector2 = (Vector)localVector1.get(n);
        for (int i1 = 0; i1 < localVector2.size(); i1++) {
          if (localVector2.get(i1) != null) {
            localTableModelIcon.addData((ID)localVector2.get(i1));
          }
        }
      }
    }
    mModel = localTableModelIcon;
    setModel(mModel);
    setColumnModel();
    setSelectedPnt(localID);
    if (mListener != null) {
      mListener.acDataSelected(localID);
    }
  }
  
  public void setAcDataSelectionListener(AcDataSelectionListener paramAcDataSelectionListener)
  {
    mListener = paramAcDataSelectionListener;
  }
  
  private void setColumnModel()
  {
    DefaultTableColumnModel localDefaultTableColumnModel = (DefaultTableColumnModel)getColumnModel();
    for (int i = 0; i < mModel.getColumnCount(); i++)
    {
      IconRenderer localIconRenderer = new IconRenderer();
      localDefaultTableColumnModel.getColumn(i).setCellRenderer(localIconRenderer);
      localDefaultTableColumnModel.getColumn(i).setPreferredWidth(75);
    }
  }
  
  public void setSelectedPnt(ID paramID)
  {
    if (paramID == null) {
      return;
    }
    for (int i = 0; i < mModel.getRowCount(); i++) {
      for (int j = 0; j < mModel.getColumnCount(); j++) {
        if (paramID.equals((ID)mModel.getValueAt(i, j)))
        {
          AcDataSelectionListener localAcDataSelectionListener = mListener;
          mListener = null;
          changeSelection(i, j, false, false);
          mListener = localAcDataSelectionListener;
          return;
        }
      }
    }
  }
  
  public void showRoot()
  {
    AcDataSelectionListener localAcDataSelectionListener = mListener;
    mListener = null;
    int i = (int)(getScrollWndSize().getWidth() / 75.0D);
    if (i <= 0) {
      i = 1;
    }
    mModel = new TableModelIcon(i);
    ID[] arrayOfID = DataMgr.getInstance().getLoginUser().getZoneIDs();
    for (int j = 0; j < arrayOfID.length; j++) {
      mModel.addData(arrayOfID[j]);
    }
    setModel(mModel);
    setColumnModel();
    if (arrayOfID.length > 0) {
      changeSelection(0, 0, false, false);
    }
    mListener = localAcDataSelectionListener;
  }
  
  public void showZone(ID paramID)
  {
    AcDataSelectionListener localAcDataSelectionListener = mListener;
    mListener = null;
    int i = (int)(getScrollWndSize().getWidth() / 75.0D);
    if (i <= 0) {
      i = 1;
    }
    mModel = new TableModelIcon(i);
    VZone localVZone = DataMgr.getInstance().getZoneFromID(paramID);
    for (int j = 0; j < localVZone.getPntNum(); j++)
    {
      ID localID = localVZone.getPntID(j);
      mModel.addData(localID);
    }
    setModel(mModel);
    setColumnModel();
    if (localVZone.getPntNum() > 0) {
      changeSelection(0, 0, false, false);
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
  
  class IconRenderer
    extends DefaultTableCellRenderer
  {
    IconRenderer() {}
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      super.setForeground(ColorRes.C_NO_SELECTED_F);
      super.setBackground(ColorRes.C_NO_SELECTED_B);
      if ((paramBoolean1 == true) && (paramObject != null)) {
        setBorder(BorderRes.getBorder(2));
      } else {
        setBorder(null);
      }
      if (paramObject != null)
      {
        setVerticalTextPosition(3);
        setHorizontalTextPosition(0);
        setHorizontalAlignment(0);
        setIconTextGap(2);
        ItcIconInfo localItcIconInfo = DataMgr.getInstance().getIconInfo((ID)paramObject);
        String str = DataMgr.getInstance().getShortName((ID)paramObject);
        setText(fName);
        PntState localPntState = DataMgr.getInstance().getState((ID)paramObject);
        setIcon(IconRes.getItcIcon(fIconID, fIconMode, fIconAppend));
      }
      else
      {
        setText("");
        setIcon(null);
      }
      return this;
    }
  }
}
