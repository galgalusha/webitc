package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionSelect_mRdoAuto_actionAdapter
  implements ActionListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mRdoAuto_actionAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoAuto_actionPerformed();
  }
}
