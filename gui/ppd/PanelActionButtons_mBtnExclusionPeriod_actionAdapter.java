package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionButtons_mBtnExclusionPeriod_actionAdapter
  implements ActionListener
{
  PanelActionButtons adaptee;
  
  PanelActionButtons_mBtnExclusionPeriod_actionAdapter(PanelActionButtons paramPanelActionButtons)
  {
    adaptee = paramPanelActionButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnExclusionPeriod_actionPerformed(paramActionEvent);
  }
}
