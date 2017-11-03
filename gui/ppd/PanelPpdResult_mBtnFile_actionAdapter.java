package webitc.gui.ppd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PanelPpdResult_mBtnFile_actionAdapter
  implements ActionListener
{
  PanelPpdResult adaptee;
  
  PanelPpdResult_mBtnFile_actionAdapter(PanelPpdResult paramPanelPpdResult)
  {
    adaptee = paramPanelPpdResult;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnFile_actionPerformed(paramActionEvent);
  }
}
