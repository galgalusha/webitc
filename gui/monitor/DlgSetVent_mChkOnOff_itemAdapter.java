package webitc.gui.monitor;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class DlgSetVent_mChkOnOff_itemAdapter
  implements ItemListener
{
  DlgSetVent adaptee;
  
  DlgSetVent_mChkOnOff_itemAdapter(DlgSetVent paramDlgSetVent)
  {
    adaptee = paramDlgSetVent;
  }
  
  public void itemStateChanged(ItemEvent paramItemEvent)
  {
    adaptee.mChkOnOff_itemStateChanged();
  }
}
