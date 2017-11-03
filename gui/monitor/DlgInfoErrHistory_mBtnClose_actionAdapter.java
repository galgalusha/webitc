package webitc.gui.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgInfoErrHistory_mBtnClose_actionAdapter
  implements ActionListener
{
  DlgInfoErrHistZone adaptee;
  
  DlgInfoErrHistory_mBtnClose_actionAdapter(DlgInfoErrHistZone paramDlgInfoErrHistZone)
  {
    adaptee = paramDlgInfoErrHistZone;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mBtnClose_actionPerformed();
  }
}
