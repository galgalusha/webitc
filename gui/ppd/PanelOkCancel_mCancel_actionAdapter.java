package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelOkCancel_mCancel_actionAdapter
  implements ActionListener
{
  PanelOkCancel adaptee;
  
  PanelOkCancel_mCancel_actionAdapter(PanelOkCancel paramPanelOkCancel)
  {
    adaptee = paramPanelOkCancel;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mCancel_actionPerformed(paramActionEvent);
  }
}
