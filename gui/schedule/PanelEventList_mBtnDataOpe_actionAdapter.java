package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelEventList_mBtnDataOpe_actionAdapter
  implements ActionListener
{
  PanelEventList adaptee;
  
  PanelEventList_mBtnDataOpe_actionAdapter(PanelEventList paramPanelEventList)
  {
    adaptee = paramPanelEventList;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnDataOpe_actionPerformed();
  }
}
