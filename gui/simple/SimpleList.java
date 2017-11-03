package webitc.gui.simple;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import webitc.common.StrRes;
import webitc.common.enum2.EnumTableID;
import webitc.data.DataMgr;
import webitc.data.DataUserInfo;
import webitc.data.ID;
import webitc.data.point.VZone;
import webitc.gui.common.ColorRes;
import webitc.gui.common.GraphicLibrary;
import webitc.gui.common.JScrollTable;

public class SimpleList
  extends JScrollTable
  implements SimpleAcDisplayer
{
  private static final int COLUMN_DRVMODE = 2;
  private static final int COLUMN_MARGIN = 5;
  private static final int COLUMN_NAME = 1;
  private static final int COLUMN_ROOMTEMP = 4;
  private static final int COLUMN_SETTEMP = 3;
  private static final int COLUMN_STATUS = 0;
  private static final int ROW_HEIGHT = 23;
  private static final int ROW_MARGIN = 3;
  private SimpleAcDisplayerListener mListener = null;
  private DefaultTableModel mModel = new DefaultTableModel();
  
  public SimpleList(EnumTableID paramEnumTableID)
  {
    super(paramEnumTableID);
    try
    {
      jbInit();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void addData(ID paramID)
  {
    if (paramID == null) {
      return;
    }
    ID[] arrayOfID = { paramID, paramID, paramID, paramID, paramID };
    mModel.addRow(arrayOfID);
  }
  
  public void clearData()
  {
    mModel.setRowCount(0);
  }
  
  public ID getSelectedID()
  {
    int i = getSelectedRow();
    if (i == -1) {
      return null;
    }
    ID localID = (ID)getModel().getValueAt(i, 0);
    return localID;
  }
  
  private void jbInit()
  {
    getScrollPane().getViewport().setBackground(ColorRes.SIMPLE_MODE_BG);
    getTableHeader().setBackground(ColorRes.SIMPLE_MODE_BG);
    setRowHeight(23);
    getColumnModel().setColumnMargin(5);
    setRowMargin(3);
    setShowGrid(false);
    mModel.addColumn(StrRes.getStr("IDS_COMMON_STATUS"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_NAME"));
    mModel.addColumn(StrRes.getStr("IDS_WEBMAIN_LIST_DRVMODE"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_SETTEMP"));
    mModel.addColumn(StrRes.getStr("IDS_COMMON_ROOMTEMP"));
    setModel(mModel);
    setSelectionMode(0);
    TableColumnModel localTableColumnModel = getColumnModel();
    localTableColumnModel.getColumn(0).setCellRenderer(new StateCellRenderer());
    localTableColumnModel.getColumn(0).setHeaderRenderer(new SimpleHeaderRenderer());
    localTableColumnModel.getColumn(1).setCellRenderer(new NameCellRenderer());
    localTableColumnModel.getColumn(1).setHeaderRenderer(new SimpleHeaderRenderer());
    localTableColumnModel.getColumn(2).setCellRenderer(new DriveModeCellRenderer());
    localTableColumnModel.getColumn(2).setHeaderRenderer(new SimpleHeaderRenderer());
    localTableColumnModel.getColumn(3).setCellRenderer(new SetTempCellRenderer());
    localTableColumnModel.getColumn(3).setHeaderRenderer(new SimpleHeaderRenderer());
    localTableColumnModel.getColumn(4).setCellRenderer(new RoomTempCellRenderer());
    localTableColumnModel.getColumn(4).setHeaderRenderer(new SimpleHeaderRenderer());
    localTableColumnModel.getColumn(0).setPreferredWidth(150);
    localTableColumnModel.getColumn(1).setPreferredWidth(150);
    localTableColumnModel.getColumn(2).setPreferredWidth(80);
    localTableColumnModel.getColumn(3).setPreferredWidth(80);
    localTableColumnModel.getColumn(4).setPreferredWidth(80);
  }
  
  public void refresh()
  {
    repaint();
  }
  
  public void setListener(SimpleAcDisplayerListener paramSimpleAcDisplayerListener)
  {
    mListener = paramSimpleAcDisplayerListener;
  }
  
  public boolean setRoot()
  {
    clearData();
    ID[] arrayOfID = DataMgr.getInstance().getLoginUser().getZoneIDs();
    for (int i = 0; i < arrayOfID.length; i++) {
      addData(arrayOfID[i]);
    }
    if (arrayOfID.length > 0)
    {
      setRowSelectionInterval(0, 0);
      return true;
    }
    return false;
  }
  
  public void setSelectedID(ID paramID)
  {
    if (paramID == null) {
      return;
    }
    for (int i = 0; i < mModel.getRowCount(); i++) {
      if (paramID.equals((ID)mModel.getValueAt(i, 0)))
      {
        SimpleAcDisplayerListener localSimpleAcDisplayerListener = mListener;
        mListener = null;
        setRowSelectionInterval(i, i);
        mListener = localSimpleAcDisplayerListener;
        return;
      }
    }
  }
  
  public boolean setZone(ID paramID)
  {
    clearData();
    VZone localVZone = DataMgr.getInstance().getZoneFromID(paramID);
    for (int i = 0; i < localVZone.getPntNum(); i++)
    {
      ID localID = localVZone.getPntID(i);
      addData(localID);
    }
    if (localVZone.getPntNum() > 0)
    {
      setRowSelectionInterval(0, 0);
      return true;
    }
    return false;
  }
  
  public void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    int i = getSelectedRow();
    if (i < 0) {
      return;
    }
    if (mListener != null)
    {
      ID localID = (ID)getModel().getValueAt(i, 0);
      if (localID == null) {
        return;
      }
      mListener.notifyPntSelected(localID);
    }
  }
  
  public int width()
  {
    return getWidth();
  }
  
  class SimpleHeaderRenderer
    extends DefaultTableCellRenderer
  {
    public SimpleHeaderRenderer() {}
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      setText(paramObject.toString());
      return this;
    }
    
    public void paint(Graphics paramGraphics)
    {
      Color localColor1 = getParent().getBackground();
      int i = 4;
      Color localColor2 = ColorRes.SIMPLE_MODE_TOOL_BAR;
      Color localColor3 = ColorRes.SIMPLE_MODE_TOOL_BAR_TEXT;
      int j = 2;
      Dimension localDimension = getSize();
      int k = 2;
      int m = 0;
      int n = width + 1;
      int i1 = height;
      Rectangle localRectangle1 = getScrollPane().getViewport().getViewRect();
      Rectangle localRectangle2 = getBounds();
      paramGraphics.setClip(k, m, n, i1);
      if (x + width < x + k + n) {
        paramGraphics.setClip(k, m, x + width - x - k, i1);
      }
      GraphicLibrary.drawBar(paramGraphics, new Dimension(n, i1), localColor1, localColor2, i, localColor3, getText(), j, false, true);
    }
  }
}
