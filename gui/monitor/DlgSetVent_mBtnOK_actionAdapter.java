package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetVent_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgSetVent adaptee;
  
  DlgSetVent_mBtnOK_actionAdapter(DlgSetVent paramDlgSetVent)
  {
    adaptee = paramDlgSetVent;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed();
  }
}
