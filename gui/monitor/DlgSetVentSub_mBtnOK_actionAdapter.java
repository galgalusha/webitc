package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSetVentSub_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgSetVentSub adaptee;
  
  DlgSetVentSub_mBtnOK_actionAdapter(DlgSetVentSub paramDlgSetVentSub)
  {
    adaptee = paramDlgSetVentSub;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed();
  }
}
