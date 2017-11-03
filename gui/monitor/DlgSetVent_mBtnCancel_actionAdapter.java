package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetVent_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSetVent adaptee;
  
  DlgSetVent_mBtnCancel_actionAdapter(DlgSetVent paramDlgSetVent)
  {
    adaptee = paramDlgSetVent;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed();
  }
}
