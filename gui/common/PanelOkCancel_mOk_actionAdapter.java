package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelOkCancel_mOk_actionAdapter
  implements ActionListener
{
  PanelOkCancel adaptee;
  
  PanelOkCancel_mOk_actionAdapter(PanelOkCancel paramPanelOkCancel)
  {
    adaptee = paramPanelOkCancel;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mOk_actionPerformed(paramActionEvent);
  }
}
