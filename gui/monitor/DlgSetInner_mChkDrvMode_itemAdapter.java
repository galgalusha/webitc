package webitc.gui.monitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DlgSetInner_mChkDrvMode_itemAdapter
  implements ItemListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mChkDrvMode_itemAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkDrvMode_itemStateChanged();
  }
}
