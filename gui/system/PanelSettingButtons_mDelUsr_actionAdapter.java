package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelSettingButtons_mDelUsr_actionAdapter
  implements ActionListener
{
  PanelSettingButtons adaptee;
  
  PanelSettingButtons_mDelUsr_actionAdapter(PanelSettingButtons paramPanelSettingButtons)
  {
    adaptee = paramPanelSettingButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mDelUsr_actionPerformed(paramActionEvent);
  }
}
