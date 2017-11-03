package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetInner_mBtnVent_actionAdapter
  implements ActionListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mBtnVent_actionAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnVent_actionPerformed(paramActionEvent);
  }
}
