package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelScheduleSetting_mBarEx2_actionAdapter
  implements ActionListener
{
  PanelEventList adaptee;
  
  PanelScheduleSetting_mBarEx2_actionAdapter(PanelEventList paramPanelEventList)
  {
    adaptee = paramPanelEventList;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBarEx2_actionPerformed();
  }
}
