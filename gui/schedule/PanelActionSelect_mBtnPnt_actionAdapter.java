package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelActionSelect_mBtnPnt_actionAdapter
  implements ActionListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mBtnPnt_actionAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnPnt_actionPerformed(paramActionEvent);
  }
}
