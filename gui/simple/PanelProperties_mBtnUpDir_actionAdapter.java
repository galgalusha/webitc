package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelProperties_mBtnUpDir_actionAdapter
  implements ActionListener
{
  PanelProperties adaptee;
  
  PanelProperties_mBtnUpDir_actionAdapter(PanelProperties paramPanelProperties)
  {
    adaptee = paramPanelProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnUpDir_actionPerformed(paramActionEvent);
  }
}
