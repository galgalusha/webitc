package webitc.gui.monitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DlgSetInner_mChkRKKOnOff_itemAdapter
  implements ItemListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mChkRKKOnOff_itemAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkRKKOnOff_itemStateChanged();
  }
}
