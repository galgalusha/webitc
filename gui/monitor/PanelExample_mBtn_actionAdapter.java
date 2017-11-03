package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelExample_mBtn_actionAdapter
  implements ActionListener
{
  PanelExample adaptee;
  
  PanelExample_mBtn_actionAdapter(PanelExample paramPanelExample)
  {
    adaptee = paramPanelExample;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtn_actionPerformed(paramActionEvent);
  }
}
