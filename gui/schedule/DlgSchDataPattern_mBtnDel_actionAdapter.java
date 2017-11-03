package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchDataPattern_mBtnDel_actionAdapter
  implements ActionListener
{
  DlgSchDataPattern adaptee;
  
  DlgSchDataPattern_mBtnDel_actionAdapter(DlgSchDataPattern paramDlgSchDataPattern)
  {
    adaptee = paramDlgSchDataPattern;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnDel_actionPerformed();
  }
}
