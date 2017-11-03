package webitc.gui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgLang_mBtnRight_actionAdapter
  implements ActionListener
{
  DlgLang adaptee;
  
  DlgLang_mBtnRight_actionAdapter(DlgLang paramDlgLang)
  {
    adaptee = paramDlgLang;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnRight_actionPerformed();
  }
}
