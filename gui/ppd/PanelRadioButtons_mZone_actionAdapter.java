package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelRadioButtons_mZone_actionAdapter
  implements ActionListener
{
  PanelRadioButtons adaptee;
  
  PanelRadioButtons_mZone_actionAdapter(PanelRadioButtons paramPanelRadioButtons)
  {
    adaptee = paramPanelRadioButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mZone_actionPerformed(paramActionEvent);
  }
}
