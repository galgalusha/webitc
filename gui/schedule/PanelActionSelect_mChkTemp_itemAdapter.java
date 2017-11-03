package webitc.gui.schedule;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class PanelActionSelect_mChkTemp_itemAdapter
  implements ItemListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mChkTemp_itemAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkTemp_itemStateChanged(false);
  }
}
