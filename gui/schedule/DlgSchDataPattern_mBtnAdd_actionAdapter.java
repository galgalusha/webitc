package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchDataPattern_mBtnAdd_actionAdapter
  implements ActionListener
{
  DlgSchDataPattern adaptee;
  
  DlgSchDataPattern_mBtnAdd_actionAdapter(DlgSchDataPattern paramDlgSchDataPattern)
  {
    adaptee = paramDlgSchDataPattern;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnAdd_actionPerformed();
  }
}
