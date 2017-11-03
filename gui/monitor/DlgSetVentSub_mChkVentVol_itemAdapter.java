package webitc.gui.monitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DlgSetVentSub_mChkVentVol_itemAdapter
  implements ItemListener
{
  DlgSetVentSub adaptee;
  
  DlgSetVentSub_mChkVentVol_itemAdapter(DlgSetVentSub paramDlgSetVentSub)
  {
    adaptee = paramDlgSetVentSub;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkVentVol_itemStateChanged();
  }
}
