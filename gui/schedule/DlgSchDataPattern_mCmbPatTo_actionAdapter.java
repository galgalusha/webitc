package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgSchDataPattern_mCmbPatTo_actionAdapter
  implements ActionListener
{
  DlgSchDataPattern adaptee;
  
  DlgSchDataPattern_mCmbPatTo_actionAdapter(DlgSchDataPattern paramDlgSchDataPattern)
  {
    adaptee = paramDlgSchDataPattern;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mCmbPatTo_actionPerformed();
  }
}
