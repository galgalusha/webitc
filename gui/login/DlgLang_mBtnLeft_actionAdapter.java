package webitc.gui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgLang_mBtnLeft_actionAdapter
  implements ActionListener
{
  DlgLang adaptee;
  
  DlgLang_mBtnLeft_actionAdapter(DlgLang paramDlgLang)
  {
    adaptee = paramDlgLang;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnLeft_actionPerformed();
  }
}
