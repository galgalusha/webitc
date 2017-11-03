package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionButtons_mBtnExec_actionAdapter
  implements ActionListener
{
  PanelActionButtons adaptee;
  
  PanelActionButtons_mBtnExec_actionAdapter(PanelActionButtons paramPanelActionButtons)
  {
    adaptee = paramPanelActionButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnExec_actionPerformed(paramActionEvent);
  }
}
