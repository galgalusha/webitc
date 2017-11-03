package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVerticalButtons_mBtn1_actionAdapter
  implements ActionListener
{
  PanelVerticalButtons adaptee;
  
  PanelVerticalButtons_mBtn1_actionAdapter(PanelVerticalButtons paramPanelVerticalButtons)
  {
    adaptee = paramPanelVerticalButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtn1_actionPerformed(paramActionEvent);
  }
}
