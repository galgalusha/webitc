package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgQuestion_mBtnNo_actionAdapter
  implements ActionListener
{
  DlgQuestion adaptee;
  
  DlgQuestion_mBtnNo_actionAdapter(DlgQuestion paramDlgQuestion)
  {
    adaptee = paramDlgQuestion;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnNo_actionPerformed(paramActionEvent);
  }
}
