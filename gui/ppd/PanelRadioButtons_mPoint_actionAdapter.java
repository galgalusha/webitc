package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelRadioButtons_mPoint_actionAdapter
  implements ActionListener
{
  PanelRadioButtons adaptee;
  
  PanelRadioButtons_mPoint_actionAdapter(PanelRadioButtons paramPanelRadioButtons)
  {
    adaptee = paramPanelRadioButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mPoint_actionPerformed(paramActionEvent);
  }
}
