package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetVentSub_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSetVentSub adaptee;
  
  DlgSetVentSub_mBtnCancel_actionAdapter(DlgSetVentSub paramDlgSetVentSub)
  {
    adaptee = paramDlgSetVentSub;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed();
  }
}
