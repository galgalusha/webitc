package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelScheduleSetting_mBarTue_actionAdapter
  implements ActionListener
{
  PanelEventList adaptee;
  
  PanelScheduleSetting_mBarTue_actionAdapter(PanelEventList paramPanelEventList)
  {
    adaptee = paramPanelEventList;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBarTue_actionPerformed();
  }
}
