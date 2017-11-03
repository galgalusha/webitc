package webitc.gui.monitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DlgSetVentSub_mChkVentMode_itemAdapter
  implements ItemListener
{
  DlgSetVentSub adaptee;
  
  DlgSetVentSub_mChkVentMode_itemAdapter(DlgSetVentSub paramDlgSetVentSub)
  {
    adaptee = paramDlgSetVentSub;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkVentMode_itemStateChanged();
  }
}
