package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionSelect_mRdoOff_actionAdapter
  implements ActionListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mRdoOff_actionAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoOff_actionPerformed();
  }
}
