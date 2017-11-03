package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelSettingButtons_mEditZone_actionAdapter
  implements ActionListener
{
  PanelSettingButtons adaptee;
  
  PanelSettingButtons_mEditZone_actionAdapter(PanelSettingButtons paramPanelSettingButtons)
  {
    adaptee = paramPanelSettingButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mEditZone_actionPerformed(paramActionEvent);
  }
}
