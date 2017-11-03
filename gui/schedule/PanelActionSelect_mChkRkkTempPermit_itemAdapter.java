package webitc.gui.schedule;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class PanelActionSelect_mChkRkkTempPermit_itemAdapter
  implements ItemListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mChkRkkTempPermit_itemAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkRkkTempPermit_itemStateChanged(false);
  }
}
