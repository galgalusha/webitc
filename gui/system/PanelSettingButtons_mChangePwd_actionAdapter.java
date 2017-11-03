package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelSettingButtons_mChangePwd_actionAdapter
  implements ActionListener
{
  PanelSettingButtons adaptee;
  
  PanelSettingButtons_mChangePwd_actionAdapter(PanelSettingButtons paramPanelSettingButtons)
  {
    adaptee = paramPanelSettingButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mChangePwd_actionPerformed(paramActionEvent);
  }
}
