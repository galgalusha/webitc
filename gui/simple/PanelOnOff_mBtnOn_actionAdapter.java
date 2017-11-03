package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelOnOff_mBtnOn_actionAdapter
  implements ActionListener
{
  PanelOnOff adaptee;
  
  PanelOnOff_mBtnOn_actionAdapter(PanelOnOff paramPanelOnOff)
  {
    adaptee = paramPanelOnOff;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOn_actionPerformed(paramActionEvent);
  }
}
