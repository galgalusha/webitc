package webitc.gui.monitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DlgSetInner_mChkRKKSetTemp_itemAdapter
  implements ItemListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mChkRKKSetTemp_itemAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkRKKSetTemp_itemStateChanged();
  }
}
