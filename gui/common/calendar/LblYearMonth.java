package webitc.gui.common.calendar;

import java.util.Calendar;
import javax.swing.JLabel;
import webitc.common.StrRes;

public class LblYearMonth
  extends JLabel
{
  public LblYearMonth()
  {
    setText("");
  }
  
  public void setCalendar(Calendar paramCalendar)
  {
    int i = paramCalendar.get(1);
    int j = paramCalendar.get(2) + 1;
    setText(StrRes.getYMStr(i, j));
  }
}
