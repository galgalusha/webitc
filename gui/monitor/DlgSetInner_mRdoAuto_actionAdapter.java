package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetInner_mRdoAuto_actionAdapter
  implements ActionListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mRdoAuto_actionAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoAuto_actionPerformed(paramActionEvent);
  }
}
