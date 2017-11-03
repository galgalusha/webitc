package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetInner_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSetInner adaptee;
  
  DlgSetInner_mBtnCancel_actionAdapter(DlgSetInner paramDlgSetInner)
  {
    adaptee = paramDlgSetInner;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed();
  }
}
