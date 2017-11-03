package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelSettingButtons_mAddUsr_actionAdapter
  implements ActionListener
{
  PanelSettingButtons adaptee;
  
  PanelSettingButtons_mAddUsr_actionAdapter(PanelSettingButtons paramPanelSettingButtons)
  {
    adaptee = paramPanelSettingButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mAddUsr_actionPerformed(paramActionEvent);
  }
}
