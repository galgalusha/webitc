package webitc.gui.monitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DlgSetVent_mChkRKK_itemAdapter
  implements ItemListener
{
  DlgSetVent adaptee;
  
  DlgSetVent_mChkRKK_itemAdapter(DlgSetVent paramDlgSetVent)
  {
    adaptee = paramDlgSetVent;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkRKK_itemStateChanged();
  }
}
