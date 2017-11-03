package webitc.gui.schedule;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class PanelActionSelect_mChkVentMode_itemAdapter
  implements ItemListener
{
  PanelActionSelect adaptee;
  
  PanelActionSelect_mChkVentMode_itemAdapter(PanelActionSelect paramPanelActionSelect)
  {
    adaptee = paramPanelActionSelect;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkVentMode_itemStateChanged();
  }
}
