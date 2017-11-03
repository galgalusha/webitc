package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleMainToolBar_mBtnUpdate_actionAdapter
  implements ActionListener
{
  SimpleMainToolBar adaptee;
  
  SimpleMainToolBar_mBtnUpdate_actionAdapter(SimpleMainToolBar paramSimpleMainToolBar)
  {
    adaptee = paramSimpleMainToolBar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnUpdate_actionPerformed(paramActionEvent);
  }
}
