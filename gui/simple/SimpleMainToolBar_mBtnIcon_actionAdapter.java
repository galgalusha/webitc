package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleMainToolBar_mBtnIcon_actionAdapter
  implements ActionListener
{
  SimpleMainToolBar adaptee;
  
  SimpleMainToolBar_mBtnIcon_actionAdapter(SimpleMainToolBar paramSimpleMainToolBar)
  {
    adaptee = paramSimpleMainToolBar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnIcon_actionPerformed(paramActionEvent);
  }
}
