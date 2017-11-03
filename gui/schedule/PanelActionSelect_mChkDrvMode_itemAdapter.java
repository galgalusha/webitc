package webitc.gui.schedule;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class PanelActionSelect_mChkDrvMode_itemAdapter
  implements ItemListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mChkDrvMode_itemAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkDrvMode_itemStateChanged();
  }
}
