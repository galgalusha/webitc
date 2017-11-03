package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetInner_mRdoFan_actionAdapter
  implements ActionListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mRdoFan_actionAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoFan_actionPerformed(paramActionEvent);
  }
}
