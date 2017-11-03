package webitc.gui.schedule;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JToggleButton;
import webitc.common.enum2.EnumDrvMode;
import webitc.common.enum2.EnumPntStat;
import webitc.common.enum2.EnumRemcCode;
import webitc.common.enum2.EnumVentMode;
import webitc.common.enum2.EnumVentVol;
import webitc.data.schedule.Daily;
import webitc.data.schedule.DefaultAction;
import webitc.data.schedule.SchEvent;
import webitc.gui.common.ColorRes;

class ScheduleBar
  extends JToggleButton
{
  private Daily mDaily;
  
  public ScheduleBar()
  {
    setSize(250, 15);
    setPreferredSize(new Dimension(250, 15));
    setMinimumSize(new Dimension(250, 15));
  }
  
  public void paint(Graphics paramGraphics)
  {
    paramGraphics.setColor(Color.WHITE);
    paramGraphics.fillRect(2, 0, 238, 15);
    if (isEnabled())
    {
      if (isSelected())
      {
        paramGraphics.setColor(ColorRes.CALENDAR_BLUE);
        paramGraphics.fillRect(2, 10, 238, 3);
      }
      if (mDaily == null) {
        return;
      }
      int i = -1;
      for (int j = 0; j < mDaily.getEventCount(); j++)
      {
        SchEvent localSchEvent = mDaily.getEvent(j);
        int k = (localSchEvent.getHour() * 60 + localSchEvent.getMin()) / 30 * 5;
        if (k == i)
        {
          paramGraphics.setColor(ColorRes.CALENDAR_BLACK);
        }
        else
        {
          i = k;
          DefaultAction localDefaultAction = (DefaultAction)localSchEvent.getAction();
          if ((localDefaultAction.getDrvMode() != EnumDrvMode.ELSE) || (localDefaultAction.getRemcCode() != EnumRemcCode.ELSE) || (localDefaultAction.getRemcDrvMode() != EnumPntStat.ELSE) || (localDefaultAction.getRemcSetPoint() != EnumPntStat.ELSE) || (localDefaultAction.getVentMode() != EnumVentMode.ELSE) || (localDefaultAction.getVentVol() != EnumVentVol.ELSE)) {
            paramGraphics.setColor(ColorRes.CALENDAR_BLACK);
          } else if (localDefaultAction.getOnOff() == EnumPntStat.ON) {
            paramGraphics.setColor(ColorRes.ON);
          } else if (localDefaultAction.getOnOff() == EnumPntStat.OFF) {
            paramGraphics.setColor(ColorRes.OFF);
          } else {
            paramGraphics.setColor(ColorRes.CALENDAR_BLACK);
          }
        }
        paramGraphics.fillRect(k + 2, 0, 3, 15);
      }
    }
  }
  
  public void setDaily(Daily paramDaily)
  {
    mDaily = paramDaily;
  }
}
