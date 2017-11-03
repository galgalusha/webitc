package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgExample_mBtnOK_actionAdapter
  implements ActionListener
{
  DlgExample adaptee;
  
  DlgExample_mBtnOK_actionAdapter(DlgExample paramDlgExample)
  {
    adaptee = paramDlgExample;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnOK_actionPerformed();
  }
}
