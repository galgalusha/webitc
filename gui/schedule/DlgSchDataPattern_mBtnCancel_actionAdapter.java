package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchDataPattern_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgSchDataPattern adaptee;
  
  DlgSchDataPattern_mBtnCancel_actionAdapter(DlgSchDataPattern paramDlgSchDataPattern)
  {
    adaptee = paramDlgSchDataPattern;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed();
  }
}
