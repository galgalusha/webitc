package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionButtons_mBtnSetPeriod_actionAdapter
  implements ActionListener
{
  PanelActionButtons adaptee;
  
  PanelActionButtons_mBtnSetPeriod_actionAdapter(PanelActionButtons paramPanelActionButtons)
  {
    adaptee = paramPanelActionButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnSetPeriod_actionPerformed(paramActionEvent);
  }
}
