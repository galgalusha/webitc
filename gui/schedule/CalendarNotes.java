package webitc.gui.schedule;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import webitc.common.StrRes;
import webitc.data.DayType;
import webitc.data.schedule.SchException;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorRes;
import webitc.gui.common.PanelAbstract;
import webitc.gui.common.calendar.DateLabelRenderer;

class CalendarNotes
  extends PanelAbstract
  implements PanelEventListListener
{
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  JTable mNotes = new JTable(6, 4);
  
  public CalendarNotes()
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
  
  public void errorPerformed()
  {
    mNotes.setEnabled(false);
  }
  
  public void exceptionNameChanged(int paramInt, String paramString)
  {
    DefaultTableModel localDefaultTableModel = (DefaultTableModel)mNotes.getModel();
    switch (paramInt)
    {
    case 0: 
      localDefaultTableModel.setValueAt(paramString, 1, 1);
      break;
    case 1: 
      localDefaultTableModel.setValueAt(paramString, 1, 3);
      break;
    case 2: 
      localDefaultTableModel.setValueAt(paramString, 2, 1);
      break;
    case 3: 
      localDefaultTableModel.setValueAt(paramString, 2, 3);
      break;
    case 4: 
      localDefaultTableModel.setValueAt(paramString, 3, 1);
      break;
    case 5: 
      localDefaultTableModel.setValueAt(paramString, 3, 3);
      break;
    case 6: 
      localDefaultTableModel.setValueAt(paramString, 4, 1);
      break;
    case 7: 
      localDefaultTableModel.setValueAt(paramString, 4, 3);
      break;
    case 8: 
      localDefaultTableModel.setValueAt(paramString, 5, 1);
      break;
    case 9: 
      localDefaultTableModel.setValueAt(paramString, 5, 3);
    }
  }
  
  private void initNotes()
  {
    mNotes.setRowHeight(25);
    mNotes.setTableHeader(null);
    mNotes.setShowHorizontalLines(false);
    mNotes.setShowVerticalLines(false);
    mNotes.setAutoResizeMode(0);
    mNotes.setIntercellSpacing(new Dimension(0, 0));
    mNotes.setRowMargin(0);
    mNotes.setShowGrid(false);
    DefaultTableModel local1 = new DefaultTableModel(6, 4)
    {
      public boolean isCellEditable(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return false;
      }
    };
    local1.setValueAt(new DayType(1, 0), 0, 0);
    local1.setValueAt(StrRes.getStr("IDS_SCH_WEEK"), 0, 1);
    local1.setValueAt(new DayType(2, 1), 1, 0);
    local1.setValueAt(new String(""), 1, 1);
    local1.setValueAt(new DayType(3, 2), 1, 2);
    local1.setValueAt(new String(""), 1, 3);
    local1.setValueAt(new DayType(4, 3), 2, 0);
    local1.setValueAt(new String(""), 2, 1);
    local1.setValueAt(new DayType(5, 4), 2, 2);
    local1.setValueAt(new String(""), 2, 3);
    local1.setValueAt(new DayType(6, 5), 3, 0);
    local1.setValueAt(new String(""), 3, 1);
    local1.setValueAt(new DayType(7, 6), 3, 2);
    local1.setValueAt(new String(""), 3, 3);
    local1.setValueAt(new DayType(8, 7), 4, 0);
    local1.setValueAt(new String(""), 4, 1);
    local1.setValueAt(new DayType(9, 8), 4, 2);
    local1.setValueAt(new String(""), 4, 3);
    local1.setValueAt(new DayType(10, 9), 5, 0);
    local1.setValueAt(new String(""), 5, 1);
    local1.setValueAt(new DayType(11, 10), 5, 2);
    local1.setValueAt(new String(""), 5, 3);
    mNotes.setModel(local1);
    DefaultTableColumnModel localDefaultTableColumnModel = (DefaultTableColumnModel)mNotes.getColumnModel();
    localDefaultTableColumnModel.getColumn(0).setMaxWidth(25);
    localDefaultTableColumnModel.getColumn(0).setCellRenderer(new DateLabelRenderer(ColorRes.SYSTEM_BACK, false));
    localDefaultTableColumnModel.getColumn(2).setMaxWidth(25);
    localDefaultTableColumnModel.getColumn(2).setCellRenderer(new DateLabelRenderer(ColorRes.SYSTEM_BACK, false));
    localDefaultTableColumnModel.getColumn(1).setCellRenderer(new TextRenderer());
    localDefaultTableColumnModel.getColumn(1).setPreferredWidth(125);
    localDefaultTableColumnModel.getColumn(3).setCellRenderer(new TextRenderer());
    localDefaultTableColumnModel.getColumn(3).setPreferredWidth(125);
  }
  
  private void jbInit()
    throws Exception
  {
    setLayout(gridBagLayout1);
    setBorder(BorderRes.createTitledBorder(StrRes.getStr("IDS_COMMON_EXAMPLE")));
    initNotes();
    add(mNotes, new GridBagConstraints(0, 0, 1, 1, 1.0D, 1.0D, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
  }
  
  public void setException(SchException paramSchException)
  {
    DefaultTableModel localDefaultTableModel = (DefaultTableModel)mNotes.getModel();
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(0), 1, 1);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(1), 1, 3);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(2), 2, 1);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(3), 2, 3);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(4), 3, 1);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(5), 3, 3);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(6), 4, 1);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(7), 4, 3);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(8), 5, 1);
    localDefaultTableModel.setValueAt(paramSchException.getExceptionName(9), 5, 3);
  }
  
  class TextRenderer
    extends DefaultTableCellRenderer
    implements TableCellRenderer
  {
    TextRenderer() {}
    
    public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
    {
      setHorizontalAlignment(2);
      setBackground(ColorRes.SYSTEM_BACK);
      if (paramObject != null) {
        setText(paramObject.toString());
      } else {
        setText("");
      }
      setEnabled(paramJTable.isEnabled());
      return this;
    }
  }
}
