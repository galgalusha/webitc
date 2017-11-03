package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleMainToolBar_mBtnLogout_actionAdapter
  implements ActionListener
{
  SimpleMainToolBar adaptee;
  
  SimpleMainToolBar_mBtnLogout_actionAdapter(SimpleMainToolBar paramSimpleMainToolBar)
  {
    adaptee = paramSimpleMainToolBar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnLogout_actionPerformed(paramActionEvent);
  }
}
