package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelProperties_mBtnDownVol_actionAdapter
  implements ActionListener
{
  PanelProperties adaptee;
  
  PanelProperties_mBtnDownVol_actionAdapter(PanelProperties paramPanelProperties)
  {
    adaptee = paramPanelProperties;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnDownVol_actionPerformed(paramActionEvent);
  }
}
