package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionButtons_mBtnChangeStart_actionAdapter
  implements ActionListener
{
  PanelActionButtons adaptee;
  
  PanelActionButtons_mBtnChangeStart_actionAdapter(PanelActionButtons paramPanelActionButtons)
  {
    adaptee = paramPanelActionButtons;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnChangeStart_actionPerformed(paramActionEvent);
  }
}
