package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelTransmit_mBtnSubmit_actionAdapter
  implements ActionListener
{
  PanelTransmit adaptee;
  
  PanelTransmit_mBtnSubmit_actionAdapter(PanelTransmit paramPanelTransmit)
  {
    adaptee = paramPanelTransmit;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnSubmit_actionPerformed(paramActionEvent);
  }
}
