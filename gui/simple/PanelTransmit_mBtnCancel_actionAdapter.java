package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelTransmit_mBtnCancel_actionAdapter
  implements ActionListener
{
  PanelTransmit adaptee;
  
  PanelTransmit_mBtnCancel_actionAdapter(PanelTransmit paramPanelTransmit)
  {
    adaptee = paramPanelTransmit;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed(paramActionEvent);
  }
}
