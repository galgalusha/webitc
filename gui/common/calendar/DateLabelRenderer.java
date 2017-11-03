package webitc.gui.common.calendar;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import webitc.common.FatalException;
import webitc.data.DayType;
import webitc.gui.common.BorderRes;
import webitc.gui.common.ColorRes;

public class DateLabelRenderer
  extends DefaultTableCellRenderer
  implements TableCellRenderer
{
  private final Color mBackground;
  private final boolean mCanSelect;
  
  public DateLabelRenderer(Color paramColor, boolean paramBoolean)
  {
    mBackground = paramColor;
    mCanSelect = paramBoolean;
    setFont(DateLabel.sFont);
    setHorizontalAlignment(0);
  }
  
  public Component getTableCellRendererComponent(JTable paramJTable, Object paramObject, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2)
  {
    if (!paramJTable.isEnabled())
    {
      setEnabled(false);
      setBackground(mBackground);
      setText("");
      setBorder(null);
      return this;
    }
    if (paramObject != null)
    {
      DayType localDayType = (DayType)paramObject;
      if (localDayType.isEnable() == true)
      {
        if ((mCanSelect) && (paramBoolean1)) {
          setBackground(ColorRes.CALENDAR_SELECTED_BG);
        } else {
          setBackground(mBackground);
        }
        super.setEnabled(true);
        switch (fType)
        {
        case 0: 
          setForeground(ColorRes.CALENDAR_BLACK);
          setBorder(null);
          break;
        case 1: 
          super.setForeground(ColorRes.CALENDAR_RED);
          setBorder(BorderRes.getBorder(4));
          break;
        case 2: 
          super.setForeground(ColorRes.CALENDAR_GREEN);
          setBorder(BorderRes.getBorder(5));
          break;
        case 3: 
          super.setForeground(ColorRes.CALENDAR_BLUE);
          setBorder(BorderRes.getBorder(6));
          break;
        case 4: 
          super.setForeground(ColorRes.CALENDAR_BLACK);
          setBorder(BorderRes.getBorder(3));
          break;
        case 5: 
          setForeground(ColorRes.CALENDAR_RED);
          setBorder(BorderRes.getBorder(7));
          break;
        case 6: 
          super.setForeground(ColorRes.CALENDAR_GREEN);
          setBorder(BorderRes.getBorder(7));
          break;
        case 7: 
          super.setForeground(ColorRes.CALENDAR_BLUE);
          setBorder(BorderRes.getBorder(7));
          break;
        case 8: 
          super.setForeground(ColorRes.CALENDAR_BLACK);
          setBorder(BorderRes.getBorder(7));
          break;
        case 9: 
          super.setForeground(ColorRes.CALENDAR_RED);
          setBorder(null);
          break;
        case 10: 
          setForeground(ColorRes.CALENDAR_GREEN);
          setBorder(null);
          break;
        case 11: 
          setBackground(ColorRes.CALENDAR_SELECTED_BG);
          setBorder(null);
          break;
        case 12: 
          super.setEnabled(false);
          break;
        default: 
          throw new FatalException("DateLabel.getTableCellRendererComponent");
        }
        setText(Integer.toString(fDay));
        return this;
      }
      setBackground(ColorRes.SYSTEM_BACK);
      setText("");
      setBorder(null);
      return this;
    }
    setBackground(mBackground);
    setText("");
    setBorder(null);
    return this;
  }
}
