package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelScheduleSetting_mRdoEx9_actionAdapter
  implements ActionListener
{
  PanelEventList adaptee;
  
  PanelScheduleSetting_mRdoEx9_actionAdapter(PanelEventList paramPanelEventList)
  {
    adaptee = paramPanelEventList;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoEx9_actionPerformed();
  }
}
