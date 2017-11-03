package webitc.gui.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleMainToolBar_mBtnList_actionAdapter
  implements ActionListener
{
  SimpleMainToolBar adaptee;
  
  SimpleMainToolBar_mBtnList_actionAdapter(SimpleMainToolBar paramSimpleMainToolBar)
  {
    adaptee = paramSimpleMainToolBar;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnList_actionPerformed(paramActionEvent);
  }
}
