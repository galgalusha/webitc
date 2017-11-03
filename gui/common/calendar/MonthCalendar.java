package webitc.gui.common.calendar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import webitc.data.DayType;
import webitc.gui.common.ColorRes;
import webitc.gui.common.JScrollTable;

public class MonthCalendar
  extends JScrollTable
{
  public static int COLUMN = 7;
  public static int ROW = 6;
  private final boolean mCanSelect;
  private boolean mDuringSelectDate = false;
  private final boolean mExpandCalendarColWidth;
  private ArrayList mListenerList = new ArrayList();
  
  public MonthCalendar(boolean paramBoolean1, boolean paramBoolean2)
  {
    super(ROW, COLUMN);
    mExpandCalendarColWidth = paramBoolean2;
    mCanSelect = paramBoolean1;
    setColumnModel();
    setSelectionMode(0);
    setColumnSelectionAllowed(true);
    setRowHeight(25);
    setShowHorizontalLines(false);
    setShowVerticalLines(false);
    JTableHeader localJTableHeader = getTableHeader();
    localJTableHeader.setDefaultRenderer(new MyHeaderRenderer());
    localJTableHeader.setResizingAllowed(false);
    setDefaultRenderer(Object.class, new DateLabelRenderer(ColorRes.C_NO_SELECTED_B, mCanSelect));
    Dimension localDimension = getPreferredSize();
    width += 8;
    height += 19;
    getScrollPane().setPreferredSize(localDimension);
    getScrollPane().setSize(localDimension);
    getScrollPane().setMinimumSize(localDimension);
    getScrollPane().setMaximumSize(localDimension);
  }
  
  public MonthCalendar(boolean paramBoolean)
  {
    this(paramBoolean, false);
  }
  
  public void addListener(MonthCalendarListener paramMonthCalendarListener)
  {
    mListenerList.add(paramMonthCalendarListener);
  }
  
  public void columnSelectionChanged(ListSelectionEvent paramListSelectionEvent)
  {
    super.columnSelectionChanged(paramListSelectionEvent);
    if (mDuringSelectDate == true) {
      return;
    }
    if (mListenerList == null) {
      return;
    }
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    for (int i = 0; i < mListenerList.size(); i++)
    {
      MonthCalendarListener localMonthCalendarListener = (MonthCalendarListener)mListenerList.get(i);
      localMonthCalendarListener.dateSelected(this);
    }
  }
  
  public DayType getSelectedDate()
  {
    TableModelCalendar localTableModelCalendar = (TableModelCalendar)getModel();
    if ((getSelectedColumn() != -1) && (getSelectedRow() != -1)) {
      return (DayType)localTableModelCalendar.getValueAt(getSelectedRow(), getSelectedColumn());
    }
    return null;
  }
  
  public void setCalendarModel(TableModelCalendar paramTableModelCalendar)
  {
    mDuringSelectDate = true;
    setModel(paramTableModelCalendar);
    setColumnModel();
    mDuringSelectDate = false;
  }
  
  private void setColumnModel()
  {
    DefaultTableColumnModel localDefaultTableColumnModel = (DefaultTableColumnModel)getColumnModel();
    for (int i = 0; i < COLUMN; i++) {
      if (mExpandCalendarColWidth == true)
      {
        localDefaultTableColumnModel.getColumn(i).setPreferredWidth(28);
        localDefaultTableColumnModel.getColumn(i).setWidth(28);
      }
      else
      {
        localDefaultTableColumnModel.getColumn(i).setPreferredWidth(25);
        localDefaultTableColumnModel.getColumn(i).setWidth(25);
      }
    }
    if (mExpandCalendarColWidth == true) {
      getScrollPane().setPreferredSize(new Dimension(201, 175));
    } else {
      getScrollPane().setPreferredSize(new Dimension(180, 175));
    }
  }
  
  public void setSelectedDate(int paramInt)
  {
    mDuringSelectDate = true;
    if ((paramInt < 1) || (paramInt > 31))
    {
      clearSelection();
    }
    else
    {
      clearSelection();
      TableModelCalendar localTableModelCalendar = (TableModelCalendar)getModel();
      for (int i = 0; i < ROW; i++) {
        for (int j = 0; j < COLUMN; j++)
        {
          DayType localDayType = (DayType)localTableModelCalendar.getValueAt(i, j);
          if (fDay == paramInt) {
            changeSelection(i, j, true, false);
          }
        }
      }
    }
    mDuringSelectDate = false;
  }
  
  public void valueChanged(ListSelectionEvent paramListSelectionEvent)
  {
    super.valueChanged(paramListSelectionEvent);
    if (mDuringSelectDate == true) {
      return;
    }
    if (mListenerList == null) {
      return;
    }
    if (paramListSelectionEvent.getValueIsAdjusting() == true) {
      return;
    }
    for (int i = 0; i < mListenerList.size(); i++)
    {
      MonthCalendarListener localMonthCalendarListener = (MonthCalendarListener)mListenerList.get(i);
      localMonthCalendarListener.dateSelected(this);
    }
  }
  
  class MyHeaderRenderer
    extends JLabel
    implements TableCellRenderer
  {
    private Font mFont = new Font("Dialog", 0, 11);
    
    MyHeaderRenderer() {}
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      setFont(mFont);
      setOpaque(true);
      setHorizontalAlignment(0);
      setBackground(ColorRes.SYSTEM_BACK);
      setEnabled(paramJTable.isEnabled());
      setForeground(Color.BLACK);
      setText((String)paramObject);
      return this;
    }
  }
}
