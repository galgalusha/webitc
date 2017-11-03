package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelOnOff_mBtnOff_actionAdapter
  implements ActionListener
{
  PanelOnOff adaptee;
  
  PanelOnOff_mBtnOff_actionAdapter(PanelOnOff paramPanelOnOff)
  {
    adaptee = paramPanelOnOff;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOff_actionPerformed(paramActionEvent);
  }
}
