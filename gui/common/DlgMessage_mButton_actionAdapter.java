package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgMessage_mButton_actionAdapter
  implements ActionListener
{
  DlgMessage adaptee;
  
  DlgMessage_mButton_actionAdapter(DlgMessage paramDlgMessage)
  {
    adaptee = paramDlgMessage;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mButton_actionPerformed(paramActionEvent);
  }
}
