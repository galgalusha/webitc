package webitc.gui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgLang_mBtnCancel_actionAdapter
  implements ActionListener
{
  DlgLang adaptee;
  
  DlgLang_mBtnCancel_actionAdapter(DlgLang paramDlgLang)
  {
    adaptee = paramDlgLang;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnCancel_actionPerformed();
  }
}
