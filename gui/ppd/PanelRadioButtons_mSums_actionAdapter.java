package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelRadioButtons_mSums_actionAdapter
  implements ActionListener
{
  PanelRadioButtons adaptee;
  
  PanelRadioButtons_mSums_actionAdapter(PanelRadioButtons paramPanelRadioButtons)
  {
    adaptee = paramPanelRadioButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mSums_actionPerformed(paramActionEvent);
  }
}
