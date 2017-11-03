package webitc.gui.common.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.DefaultTableModel;
import webitc.common.StrRes;
import webitc.data.DayType;

public class TableModelCalendar
  extends DefaultTableModel
{
  public TableModelCalendar(Calendar paramCalendar, ArrayList paramArrayList, int paramInt)
  {
    if ((paramInt & 0x1) == 1) {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_0") + StrRes.getStr("IDS_KBD_SYM_ON_8"));
    } else {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_0"));
    }
    if ((paramInt >> 1 & 0x1) == 1) {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_1") + StrRes.getStr("IDS_KBD_SYM_ON_8"));
    } else {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_1"));
    }
    if ((paramInt >> 2 & 0x1) == 1) {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_2") + StrRes.getStr("IDS_KBD_SYM_ON_8"));
    } else {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_2"));
    }
    if ((paramInt >> 3 & 0x1) == 1) {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_3") + StrRes.getStr("IDS_KBD_SYM_ON_8"));
    } else {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_3"));
    }
    if ((paramInt >> 4 & 0x1) == 1) {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_4") + StrRes.getStr("IDS_KBD_SYM_ON_8"));
    } else {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_4"));
    }
    if ((paramInt >> 5 & 0x1) == 1) {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_5") + StrRes.getStr("IDS_KBD_SYM_ON_8"));
    } else {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_5"));
    }
    if ((paramInt >> 6 & 0x1) == 1) {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_6") + StrRes.getStr("IDS_KBD_SYM_ON_8"));
    } else {
      addColumn(StrRes.getStr("IDS_COMMON_WDAY_6"));
    }
    setRowCount(6);
    setData(paramCalendar, paramArrayList);
  }
  
  public TableModelCalendar(Calendar paramCalendar, ArrayList paramArrayList)
  {
    this(paramCalendar, paramArrayList, 0);
  }
  
  public int getDayType(int paramInt)
  {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++)
      {
        DayType localDayType = (DayType)getValueAt(i, j);
        if (fDay == paramInt) {
          return fType;
        }
      }
    }
    return 12;
  }
  
  public void setData(Calendar paramCalendar, ArrayList paramArrayList)
  {
    int i = paramCalendar.get(7) - 1;
    int j = paramCalendar.getActualMaximum(5);
    DayType localDayType1 = 0;
    for (int k = 0; k < i; k++) {
      setValueAt(DayType.DISABLE_DAY, localDayType1, k);
    }
    for (int m = 0; m < j; m++)
    {
      localDayType2 = (DayType)paramArrayList.get(m);
      setValueAt(localDayType2, localDayType1, i);
      i++;
      if (i >= 7)
      {
        localDayType1++;
        i = 0;
      }
    }
    for (DayType localDayType2 = localDayType1; localDayType2 < 6; localDayType2++)
    {
      for (int n = i; n < 7; n++) {
        setValueAt(DayType.DISABLE_DAY, localDayType2, n);
      }
      i = 0;
    }
  }
  
  public void setDayType(int paramInt1, int paramInt2)
  {
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++)
      {
        DayType localDayType = (DayType)getValueAt(i, j);
        if (fDay == paramInt1) {
          setValueAt(new DayType(paramInt1, paramInt2), i, j);
        }
      }
    }
  }
}
