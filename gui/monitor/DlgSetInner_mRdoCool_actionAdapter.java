package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetInner_mRdoCool_actionAdapter
  implements ActionListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mRdoCool_actionAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoCool_actionPerformed(paramActionEvent);
  }
}
