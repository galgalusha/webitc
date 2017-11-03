package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelVerticalButtons_mBtn2_actionAdapter
  implements ActionListener
{
  PanelVerticalButtons adaptee;
  
  PanelVerticalButtons_mBtn2_actionAdapter(PanelVerticalButtons paramPanelVerticalButtons)
  {
    adaptee = paramPanelVerticalButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtn2_actionPerformed(paramActionEvent);
  }
}
