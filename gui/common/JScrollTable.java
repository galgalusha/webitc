package webitc.gui.common;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import webitc.common.enum2.EnumTableID;
import webitc.gui.ButtonEventListener;

public class JScrollTable
  extends JTable
  implements ScrollTableColumn.changeWidthListener
{
  public static int MAIN_NAME = 1;
  public static int MAIN_SETEMP = 2;
  public static int MAIN_STATUS = 0;
  private static HashMap mColumnSizeMap = new HashMap();
  protected ArrayList mList = new ArrayList();
  private int mPreIndex = 0;
  private final JScrollPane mScrollPane = new JScrollPane();
  private EnumTableID mTableID = null;
  
  protected JScrollTable(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    init();
  }
  
  private JScrollTable(TableModel paramTableModel)
  {
    super(paramTableModel);
    init();
  }
  
  public JScrollTable(EnumTableID paramEnumTableID)
  {
    mTableID = paramEnumTableID;
    init();
  }
  
  private JScrollTable()
  {
    init();
  }
  
  public void addButtonEventListener(ButtonEventListener paramButtonEventListener)
  {
    mList.add(paramButtonEventListener);
  }
  
  public void createDefaultColumnsFromModel()
  {
    TableModel localTableModel = getModel();
    if (localTableModel != null)
    {
      TableColumnModel localTableColumnModel = getColumnModel();
      while (localTableColumnModel.getColumnCount() > 0) {
        localTableColumnModel.removeColumn(localTableColumnModel.getColumn(0));
      }
      for (int i = 0; i < localTableModel.getColumnCount(); i++)
      {
        ScrollTableColumn localScrollTableColumn = new ScrollTableColumn(i);
        localScrollTableColumn.setListener(this);
        int j = getRecordedColumnWidth(i);
        if (j >= 0) {
          localScrollTableColumn.setRecordedWidth(j);
        }
        addColumn(localScrollTableColumn);
      }
    }
  }
  
  protected void fireButtonEvent(int paramInt)
  {
    for (int i = 0; i < mList.size(); i++)
    {
      ButtonEventListener localButtonEventListener = (ButtonEventListener)mList.get(i);
      localButtonEventListener.buttonSelected(paramInt);
    }
  }
  
  private EnumTableID getID()
  {
    if (mTableID == null) {
      return getTableID();
    }
    return mTableID;
  }
  
  private int getRecordedColumnWidth(int paramInt)
  {
    if ((getID() == null) || (getID() == EnumTableID.UNKNOWN)) {
      return -1;
    }
    String[] arrayOfString = (String[])mColumnSizeMap.get(getID().toString());
    if ((arrayOfString == null) || (arrayOfString.length <= paramInt)) {
      return -1;
    }
    return Integer.parseInt(arrayOfString[paramInt]);
  }
  
  public JScrollPane getScrollPane()
  {
    return mScrollPane;
  }
  
  public Dimension getScrollWndSize()
  {
    return mScrollPane.getSize();
  }
  
  public static HashMap getTableColumnMap()
  {
    return mColumnSizeMap;
  }
  
  protected EnumTableID getTableID()
  {
    return EnumTableID.UNKNOWN;
  }
  
  private void init()
  {
    setAutoResizeMode(0);
    JTableHeader localJTableHeader = getTableHeader();
    localJTableHeader.setReorderingAllowed(false);
    setAutoscrolls(false);
    mScrollPane.getViewport().add(this, null);
    mScrollPane.addComponentListener(new ResizeEvent());
    mScrollPane.setSize(2048, 1600);
    getSelectionModel().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent paramAnonymousListSelectionEvent)
      {
        if (paramAnonymousListSelectionEvent.getValueIsAdjusting() == true) {
          return;
        }
        int i = getSelectedRow();
        if (i > mPreIndex) {
          moveRowFocus(i, false);
        } else if (i < mPreIndex) {
          moveRowFocus(i, true);
        }
        mPreIndex = i;
      }
    });
  }
  
  public boolean isCellEditable(int paramInt1, int paramInt2)
  {
    return false;
  }
  
  public void moveRowFocus(int paramInt, boolean paramBoolean)
  {
    if ((paramInt < 0) || (paramInt >= getRowCount())) {
      return;
    }
    if (getColumnCount() <= 0) {
      return;
    }
    Rectangle localRectangle1 = getCellRect(paramInt, 0, false);
    Rectangle localRectangle2 = mScrollPane.getViewport().getViewRect();
    if ((y + height < y + height) && (y > y)) {
      return;
    }
    JScrollBar localJScrollBar = mScrollPane.getVerticalScrollBar();
    if ((getRowCount() - 1 == paramInt) || (height == 0)) {
      mScrollPane.getViewport().scrollRectToVisible(localRectangle1);
    } else if (paramBoolean) {
      localJScrollBar.setValue(y);
    } else {
      localJScrollBar.setValue(y - (height - height));
    }
  }
  
  public void notifyChangeWidth(int paramInt1, int paramInt2)
  {
    if (getID() == EnumTableID.UNKNOWN) {
      return;
    }
    int i = getColumnCount();
    String[] arrayOfString = new String[i];
    TableColumnModel localTableColumnModel = getColumnModel();
    for (int j = 0; j < i; j++)
    {
      int k = localTableColumnModel.getColumn(j).getPreferredWidth();
      arrayOfString[j] = String.valueOf(k);
    }
    mColumnSizeMap.put(getID().toString(), arrayOfString);
  }
  
  public void redraw()
  {
    getScrollPane().updateUI();
  }
  
  public void scrollWndResized(Dimension paramDimension) {}
  
  class ResizeEvent
    implements ComponentListener
  {
    ResizeEvent() {}
    
    public void componentHidden(ComponentEvent paramComponentEvent) {}
    
    public void componentMoved(ComponentEvent paramComponentEvent) {}
    
    public void componentResized(ComponentEvent paramComponentEvent)
    {
      scrollWndResized(paramComponentEvent.getComponent().getSize());
    }
    
    public void componentShown(ComponentEvent paramComponentEvent) {}
  }
}
