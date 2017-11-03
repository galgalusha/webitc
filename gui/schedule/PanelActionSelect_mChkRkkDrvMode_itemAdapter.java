package webitc.gui.schedule;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class PanelActionSelect_mChkRkkDrvMode_itemAdapter
  implements ItemListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mChkRkkDrvMode_itemAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkRkkDrvMode_itemStateChanged(false);
  }
}
