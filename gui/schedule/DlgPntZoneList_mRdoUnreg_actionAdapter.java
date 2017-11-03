package webitc.gui.schedule;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DlgPntZoneList_mRdoUnreg_actionAdapter
  implements ActionListener
{
  DlgPntZoneList adaptee;
  
  DlgPntZoneList_mRdoUnreg_actionAdapter(DlgPntZoneList paramDlgPntZoneList)
  {
    adaptee = paramDlgPntZoneList;
  }
  
  public void actionPerformed(ActionEvent paramActionEvent)
  {
    adaptee.mRdoUnreg_actionPerformed();
  }
}
