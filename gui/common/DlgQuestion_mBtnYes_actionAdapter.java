package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgQuestion_mBtnYes_actionAdapter
  implements ActionListener
{
  DlgQuestion adaptee;
  
  DlgQuestion_mBtnYes_actionAdapter(DlgQuestion paramDlgQuestion)
  {
    adaptee = paramDlgQuestion;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnYes_actionPerformed(paramActionEvent);
  }
}
