package webitc.gui.system;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelSystem_mBtnSet_actionAdapter
  implements ActionListener
{
  PanelSystem adaptee;
  
  PanelSystem_mBtnSet_actionAdapter(PanelSystem paramPanelSystem)
  {
    adaptee = paramPanelSystem;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnSet_actionPerformed(paramActionEvent);
  }
}
