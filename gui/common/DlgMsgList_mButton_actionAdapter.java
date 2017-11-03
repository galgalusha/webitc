package webitc.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgMsgList_mButton_actionAdapter
  implements ActionListener
{
  DlgMsgList adaptee;
  
  DlgMsgList_mButton_actionAdapter(DlgMsgList paramDlgMsgList)
  {
    adaptee = paramDlgMsgList;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mButton_actionPerformed(paramActionEvent);
  }
}
