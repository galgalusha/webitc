package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionButtons_mBtnSpecialDay_actionAdapter
  implements ActionListener
{
  PanelActionButtons adaptee;
  
  PanelActionButtons_mBtnSpecialDay_actionAdapter(PanelActionButtons paramPanelActionButtons)
  {
    adaptee = paramPanelActionButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnSpecialDay_actionPerformed(paramActionEvent);
  }
}
