package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelProperties_mBtnDownDir_actionAdapter
  implements ActionListener
{
  PanelProperties adaptee;
  
  PanelProperties_mBtnDownDir_actionAdapter(PanelProperties paramPanelProperties)
  {
    adaptee = paramPanelProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnDownDir_actionPerformed(paramActionEvent);
  }
}
